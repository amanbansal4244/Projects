package com.sapientAssignment.feeCalculator.ImplementationLayer.formats;

import com.sapientAssignment.feeCalculator.utility.TransactionAttributesUtility;
import com.sapientAssignment.feeCalculator.utility.ProcessTransactionUtility;
/**
 * This factory creates instances of classes that implements the 'TransactionsManagerReaderIntf' interface.
 *
 *
 * @version   1.0
 * @author aman bansal
 **/
public class TransactionsReaderFactory {
	 /**
	    * Returns an implementation of a interface object based upon the supplied arguments.
	    *
	    * @param     readerType  Implementation of interface that is desired. 
	    **/
	public static TransactionsManagerReaderIntf getReader(String readerType){
		TransactionsManagerReaderIntf reader = null;

		if (readerType.equalsIgnoreCase("XML")) {
			reader = new XMLBasedTransactionsManager();
		}else if (readerType.equalsIgnoreCase("TXT")) {
			reader = new TextBasedTransactionsManager();
		}else if (readerType.equalsIgnoreCase("CSV")) {
			reader = new CsvBasedTransactionsManager();
			CsvBasedTransactionsManager csvReader = (CsvBasedTransactionsManager) reader;
			csvReader.setTransactionAttributesUtility(TransactionAttributesUtility.getInstance());
			csvReader.setProcessTransactionUtility(ProcessTransactionUtility.getInstance());
		} 
		return reader;
	}
}
