package com.sapient.global.test.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.sapient.global.test.constants.TransactionRecordFeesConstant;
import com.sapient.global.test.constants.TransactionRecordPriorityBasedTypeConstant;
import com.sapient.global.test.constants.TransactionRecordTypeConstant;
import com.sapient.global.test.model.TransactionRecord;

public class TransactionRecordUtility {

	private volatile static TransactionRecordUtility transactionRecordUtilityObject = new TransactionRecordUtility();;
	private ConcurrentHashMap<String, TransactionRecord> conTransactionsMap = new ConcurrentHashMap<String, TransactionRecord>();
	
	private TransactionRecordUtility() {}
	
	public static TransactionRecordUtility getObject() {
		if(transactionRecordUtilityObject == null){
			synchronized (TransactionRecordUtility.class) {
				if(transactionRecordUtilityObject == null){
					transactionRecordUtilityObject = new TransactionRecordUtility();
				}
			}
		}
		return transactionRecordUtilityObject;
	}

	public void executeTransaction(String[] strTokens) {
		TransactionRecord transaction = setTransactionRecord(strTokens);
		applyProcessingRules(transaction);
		
	}

	public List<TransactionRecord> getAllTransactionRecord() {
		ArrayList<TransactionRecord> list = new ArrayList<TransactionRecord>();
		for (Entry<String, TransactionRecord> entry : conTransactionsMap.entrySet()) { 
			TransactionRecord value = entry.getValue(); 
			list.add(value);
			}
		return list;
	}

	private TransactionRecord setTransactionRecord(String[] transactionRecordTokens) {
		TransactionRecord transaction = new TransactionRecord(transactionRecordTokens[0], transactionRecordTokens[1], transactionRecordTokens[2], 
				transactionRecordTokens[3].toUpperCase(),parseDate(transactionRecordTokens[4]), Double.parseDouble(transactionRecordTokens[5]), 
				"Y".equalsIgnoreCase(transactionRecordTokens[6]) ? TransactionRecordPriorityBasedTypeConstant.HIGH_PRIORITY : TransactionRecordPriorityBasedTypeConstant.NORMAL_PRIORITY,
						new Double(0));

		return transaction;
	}

	private boolean isIntraDayTransactionRecord(TransactionRecord transactionRecord) {
		Collection<TransactionRecord> transactionsList = conTransactionsMap.values();
		for ( TransactionRecord transaction1 : transactionsList) {
			if (transaction1.isSameTransactionRecordType(transactionRecord) && transaction1.isOppositeTransactionRecordType(transactionRecord)) {
				return true;
			}
		}
		return false;
	}
	
	public Date parseDate(String date) {
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date parsedDate = null;
		try {
			parsedDate = sdf.parse(date);
			return parsedDate;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parsedDate;
	}

	public void applyProcessingRules(TransactionRecord transactionRecord) {

		if (transactionRecord.getPriorityFlag().equals(TransactionRecordPriorityBasedTypeConstant.HIGH_PRIORITY)) {
			transactionRecord.setTransactionProcessingFees(transactionRecord.getProcessingFees() + TransactionRecordFeesConstant.FIVE_HUNDRED);
		}
		else {
			if ((transactionRecord.getTransactionType().equals(TransactionRecordTypeConstant.SELL)) || (transactionRecord.getTransactionType().equals(TransactionRecordTypeConstant.WITHDRAW))) {
				transactionRecord.setTransactionProcessingFees(transactionRecord.getProcessingFees() + TransactionRecordFeesConstant.HUNDRED);
			}
			if ((transactionRecord.getTransactionType().equals(TransactionRecordTypeConstant.BUY)) || (transactionRecord.getTransactionType().equals(TransactionRecordTypeConstant.DEPOSIT))) {
				transactionRecord.setTransactionProcessingFees(transactionRecord.getProcessingFees() + TransactionRecordFeesConstant.FIFTY);
			}
		}
		
		if (isIntraDayTransactionRecord(transactionRecord)) {
			transactionRecord.setTransactionProcessingFees(transactionRecord.getProcessingFees() + TransactionRecordFeesConstant.TEN);
		}

		conTransactionsMap.put(transactionRecord.getExternalTransactionId(), transactionRecord);
		
	}

}
