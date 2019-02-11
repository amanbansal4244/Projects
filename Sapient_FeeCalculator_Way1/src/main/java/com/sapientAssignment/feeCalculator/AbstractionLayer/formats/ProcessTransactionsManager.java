package com.sapientAssignment.feeCalculator.AbstractionLayer.formats;

import java.io.File;

import com.sapientAssignment.feeCalculator.ImplementationLayer.formats.TransactionsManagerReaderIntf;
import com.sapientAssignment.feeCalculator.exception.NotSupportedFileFormatException;

public class ProcessTransactionsManager extends TransactionsManager{
	
	public ProcessTransactionsManager(TransactionsManagerReaderIntf transactionsManagerReaderIntf) {
		super.transactionsManagerReaderIntf = transactionsManagerReaderIntf;
	}
	
	@Override
	public void processFile(File file) throws NotSupportedFileFormatException {
		transactionsManagerReaderIntf.processFile(file);
	}

	@Override
	public void displayTransactionsSummaryReport() throws NotSupportedFileFormatException {
		transactionsManagerReaderIntf.displayTransactionsSummaryReport();
	}

}
