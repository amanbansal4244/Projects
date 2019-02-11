package com.sapientAssignment.feeCalculator.main;

import java.io.File;
import java.io.FileNotFoundException;

import com.sapientAssignment.feeCalculator.AbstractionLayer.formats.ProcessTransactionsManager;
import com.sapientAssignment.feeCalculator.AbstractionLayer.formats.TransactionsManager;
import com.sapientAssignment.feeCalculator.ImplementationLayer.formats.TransactionsManagerReaderIntf;
import com.sapientAssignment.feeCalculator.ImplementationLayer.formats.TransactionsReaderFactory;
import com.sapientAssignment.feeCalculator.exception.NotSupportedFileFormatException;
/**
*This is an entry point to run fee calculator assignment.
*
* @author aman bansal
* @version   1.0
**/
public class Main {

	/**
	 * This API helps to read input file and process further.
	 * 
	 * @param String fileName
	 * @throws NotSupportedFileFormatException , FileNotFoundException
	 */
	private void readFile(String fileName) throws NotSupportedFileFormatException,FileNotFoundException  {
		String fileType = null;
		File transactionFile = new File(new File("").getAbsolutePath(), "src/main/resources/" + fileName);
		if(transactionFile.exists()){
			int index = fileName.lastIndexOf('.');
			if (index > 0) {
				fileType = fileName.substring(index + 1);
				TransactionsManagerReaderIntf reader = TransactionsReaderFactory.getReader(fileType);
				TransactionsManager manager = new ProcessTransactionsManager(reader);
				manager.processFile(transactionFile);
				manager.displayTransactionsSummaryReport();
			}
		}else{
			throw new FileNotFoundException();
		}
	}
	

	/**
	 * Main method to execute program.
	 * 
	 * @param args
	 * @throws NotSupportedFileFormatException 
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws NotSupportedFileFormatException, FileNotFoundException {
		String inputFileName = "CSVSampleData.csv";
		new Main().readFile(inputFileName);
	}
}
