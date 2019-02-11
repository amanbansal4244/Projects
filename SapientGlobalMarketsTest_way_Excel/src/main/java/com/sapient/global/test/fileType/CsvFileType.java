package com.sapient.global.test.fileType;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.sapient.global.test.Thread.TransactionRecordThread;
import com.sapient.global.test.constants.TransactionRecordPriorityBasedTypeConstant;
import com.sapient.global.test.customExecption.FileTypeNotSupportedException;
import com.sapient.global.test.model.TransactionRecord;
import com.sapient.global.test.model.TransactionRecordClientIdSorting;
import com.sapient.global.test.model.TransactionRecordDateSorting;
import com.sapient.global.test.model.TransactionRecordPrioritySorting;
import com.sapient.global.test.model.TransactionRecordTypeSorting;
import com.sapient.global.test.utility.TransactionRecordUtility;
import com.sapient.global.test.utility.TransactionRecordSortingUtility;


public class CsvFileType implements FileType {

	protected TransactionRecordUtility transactionRecordUtility;

	public void setProcessTransactionRecordUtility(TransactionRecordUtility transactionRecordUtility) {
		this.transactionRecordUtility = transactionRecordUtility;
	}

	@Override
	public void processInputFileToCalculateTransactionRecord(File file) throws FileTypeNotSupportedException, IOException {
		BufferedReader reader = null;
		ExecutorService executorService = null;
		try {
			reader = new BufferedReader(new FileReader(file));
			String line;
		    executorService = Executors.newFixedThreadPool(5); 
			while((line = reader.readLine()) !=null) {
				Runnable transactionThread = new TransactionRecordThread(line.split(","),transactionRecordUtility);
				executorService.execute(transactionThread);
				Thread.sleep(5);
			}
			
		}catch(Exception e) {
			e.printStackTrace();
			throw new FileTypeNotSupportedException("Exception occured in reading CSV file" + file.getName());
		}finally{
			executorService.shutdown();
			reader.close();
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void printSummary() throws FileTypeNotSupportedException {
		List<TransactionRecord> list = transactionRecordUtility.getAllTransactionRecord();
		Collections.sort(list, new TransactionRecordSortingUtility(new TransactionRecordClientIdSorting(), new TransactionRecordTypeSorting(), new TransactionRecordDateSorting(), new TransactionRecordPrioritySorting()));
		
		System.out.println("Client Id: | Transaction Type:     | Transaction Date:                | Priority: | Processing Fee:");
		for(int i=0; i<list.size();i++){
			System.out.println(list.get(i).getClientId() + "         |   " + list.get(i).getTransactionType() + 
					"                | " + list.get(i).getTransactionDate() + "      | " + ((list.get(i).getPriorityFlag().equals(TransactionRecordPriorityBasedTypeConstant.HIGH_PRIORITY)) ? "High" : "Normal") +
					"      | " + list.get(i).getProcessingFees() + "\t");
		}
	}

	
}