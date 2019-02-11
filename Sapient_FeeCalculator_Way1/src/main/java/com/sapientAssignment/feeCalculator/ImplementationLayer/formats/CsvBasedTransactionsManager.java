package com.sapientAssignment.feeCalculator.ImplementationLayer.formats;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;

import com.sapientAssignment.feeCalculator.constant.Constants.TransactionPriorityType;
import com.sapientAssignment.feeCalculator.constant.Constants.TransactionType;
import com.sapientAssignment.feeCalculator.exception.NotSupportedFileFormatException;
import com.sapientAssignment.feeCalculator.model.Transaction;
import com.sapientAssignment.feeCalculator.utility.TransactionAttributesUtility;
import com.sapientAssignment.feeCalculator.utility.ProcessTransactionUtility;
import com.sapientAssignment.feeCalculator.utility.sorters.ClientIdBasedSorter;
import com.sapientAssignment.feeCalculator.utility.sorters.PriorityBasedSorter;
import com.sapientAssignment.feeCalculator.utility.sorters.TransactionsBasedSorter;
import com.sapientAssignment.feeCalculator.utility.sorters.TransactionDateComparator;
import com.sapientAssignment.feeCalculator.utility.sorters.TransactionTypeBasedSorter;
/**
*
* @description : This is an implementation of 'TransactionsManagerReaderIntf' which helps to process the 'CSV' type input file.
*        
* @author aman bansal 
*/
public class CsvBasedTransactionsManager implements TransactionsManagerReaderIntf {

	protected TransactionAttributesUtility transactionAttributesUtility;
	protected ProcessTransactionUtility processTransactionUtility;

	public void setTransactionAttributesUtility(TransactionAttributesUtility transactionAttributesUtility) {
		this.transactionAttributesUtility = transactionAttributesUtility;
	}

	public void setProcessTransactionUtility(ProcessTransactionUtility processTransactionUtility) {
		this.processTransactionUtility = processTransactionUtility;
	}

	@Override
	public void processFile(File file) throws NotSupportedFileFormatException {
		String strLine = "";

		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			while ((strLine = br.readLine()) != null) {
				String[] strTokens = strLine.split(",");
				Transaction transaction = fetchTransaction(strTokens);
				processTransactionUtility.processTransaction(transaction);
			}
		} catch (IOException ioe) {
			throw new NotSupportedFileFormatException("Exception while reading CSV Sample Data Input file" + file.getName(), ioe);
		}
	}

	@Override
	public void displayTransactionsSummaryReport() {
		List<Transaction> transList = processTransactionUtility.getTransactionList();
		Collections.sort(transList, new TransactionsBasedSorter(new ClientIdBasedSorter(), new TransactionTypeBasedSorter(), new TransactionDateComparator(), new PriorityBasedSorter()));
		System.out.println("Start Transaction Report");
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("Client Id || Transaction Type || Transaction Date || Priority || Processing Fee");
		for (final Transaction transaction : transList) {
			System.out.println("--------------------------------------------------------------------------------");
			System.out.println(transaction.getClientId() + " || " + transaction.getTransactionType() + "|| " + transaction.getTransactionDate() + " || "
					+ ((transaction.getPriorityFlag().equals(TransactionPriorityType.HIGH)) ? "High" : "Normal") + " || " + transaction.getTransactionProcessingFees() + "\t|");
		}
		System.out.println("--------------------------------------------------------------------------------");
		System.out.println("END Transaction Report");
	}

	protected Transaction fetchTransaction(String[] strTokens) {
		Transaction transaction = new Transaction();
		transaction.setExternalTransactionId(strTokens[0]);
		transaction.setClientId(strTokens[1]);
		transaction.setSecurityId(strTokens[2]);
		transaction.setTransactionType(TransactionType.valueOf(strTokens[3].toUpperCase()));
		transaction.setTransactionDate(transactionAttributesUtility.getDate(strTokens[4]));
		transaction.setMarketValue(Double.parseDouble(strTokens[5]));
		transaction.setPriorityFlag("Y".equalsIgnoreCase(strTokens[6]) ? TransactionPriorityType.HIGH :  TransactionPriorityType.NORMAL);
		transaction.setTransactionProcessingFees(new Double(0));
		return transaction;
	}
}