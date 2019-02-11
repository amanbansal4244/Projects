package com.sapientAssignment.feeCalculator.ImplementationLayer.formats;

import java.io.File;

import com.sapientAssignment.feeCalculator.exception.NotSupportedFileFormatException;
/**
*
* @description : This is an implementation layer.So that we can decouple implementation from an abstraction layer 
* and can developed implementation independently.
* This interface can be used to process the input file and display the transaction report. 
*        
* @author aman bansal
*/
public interface TransactionsManagerReaderIntf {

	/**
     * @description This API helps to process the input file.
     * 
     * @param  File file
     * @throws NotSupportedFileFormatException 
     */
	public void processFile(File file) throws NotSupportedFileFormatException;

	/**
     * @description This API helps to display the transactions report.
     * 
     * @throws NotSupportedFileFormatException 
     */
	public void displayTransactionsSummaryReport() throws NotSupportedFileFormatException;

}
