package com.sapientAssignment.feeCalculator.AbstractionLayer.formats;

import java.io.File;

import com.sapientAssignment.feeCalculator.ImplementationLayer.formats.TransactionsManagerReaderIntf;
import com.sapientAssignment.feeCalculator.exception.NotSupportedFileFormatException;
/**
*
* @description : This is an abstraction layer.So that we can decouple abstraction from an implementation layer 
* and can developed abstraction independently and client code can access only the abstraction part without being 
* concerned about the implementation part.
* This class can be used to process the input file and display the transaction report. 
*         
* @author aman bansal
*/
public abstract class TransactionsManager {

	protected TransactionsManagerReaderIntf transactionsManagerReaderIntf;

	/**
     * @description This API helps to process the input file.
     * 
     * @param  File file
     * @throws NotSupportedFileFormatException 
     */
	abstract public void processFile(File file) throws NotSupportedFileFormatException;

	/**
     * @description This API helps to display the transactions report.
     * 
     * @throws NotSupportedFileFormatException 
     */
	abstract public void displayTransactionsSummaryReport() throws NotSupportedFileFormatException;
	
}
