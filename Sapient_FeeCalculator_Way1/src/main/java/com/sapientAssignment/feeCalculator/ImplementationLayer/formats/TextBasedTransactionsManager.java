package com.sapientAssignment.feeCalculator.ImplementationLayer.formats;

import java.io.File;

import com.sapientAssignment.feeCalculator.exception.NotSupportedFileFormatException;
/**
*
* @description : This is an implementation of 'TransactionsManagerReaderIntf' which helps to process the 'Text' type input file.
*
* TODO: We need to implement this strategy based on future requirement.
*        
* @author aman bansal
*/
public class TextBasedTransactionsManager implements TransactionsManagerReaderIntf {

	
	@Override
	public void processFile(File file) throws NotSupportedFileFormatException {
		throw new NotSupportedFileFormatException("Text type file format is not supported as of now.");
	}

	@Override
	public void displayTransactionsSummaryReport() throws NotSupportedFileFormatException {
		throw new NotSupportedFileFormatException("Text type file format is not supported as of now.");

	}

}
