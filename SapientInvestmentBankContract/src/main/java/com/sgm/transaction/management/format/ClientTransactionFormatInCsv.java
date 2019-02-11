package com.sgm.transaction.management.format;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.sgm.transaction.management.constants.ClientTransactionConstants;
import com.sgm.transaction.management.execption.ClientTransactionFormatIsNotValidException;
import com.sgm.transaction.management.execption.ClientTransactionGenerateInvoiceException;
import com.sgm.transaction.management.model.ClientIdComparator;
import com.sgm.transaction.management.model.ClientTransaction;
import com.sgm.transaction.management.model.ClientTransactionComparatorUtility;
import com.sgm.transaction.management.model.ClientTransactionDateComparator;
import com.sgm.transaction.management.model.ClientTransactionPriorityComparator;
import com.sgm.transaction.management.model.ClientTransactionService;
import com.sgm.transaction.management.model.ClientTransactionTypeComparator;

public class ClientTransactionFormatInCsv implements ClientTransactionFormatInf {

	protected ClientTransactionService clientTransactionUtility; 

	public void setClientTransactionUtility(ClientTransactionService clientTransactionUtility) {
		this.clientTransactionUtility = clientTransactionUtility;
	}

	@Override
	public void processClientTransaction(File file) throws ClientTransactionFormatIsNotValidException, IOException, InterruptedException {
		ExecutorService service = null;
		String line;
		service = Executors.newFixedThreadPool(3); 
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
			while ((line = br.readLine()) != null) {
				String[] clientTransactionTokens = line.split(",");
				Thread thread = new Thread(new Runnable() {
					@Override
					public void run() {
						try{
							clientTransactionUtility.processTransaction(clientTransactionTokens);
						}catch(Exception e) {
							e.printStackTrace();
						}
					}
				            
				});
				service.execute(thread);
				Thread.sleep(5);
			
			}
		} catch (IOException ioe) {
			throw new ClientTransactionFormatIsNotValidException("Exception occured.. CSV file is not valid" + file.getName());
		}
		finally{
			service.shutdown();
		}
	}

	@Override
	public void generateInvoicing() throws ClientTransactionGenerateInvoiceException {
		List<ClientTransaction> clientTransactionList = clientTransactionUtility.getAllTransactionRecord();
		Collections.sort(clientTransactionList, 
				new ClientTransactionComparatorUtility(new ClientIdComparator(), 
						new ClientTransactionTypeComparator(), new ClientTransactionDateComparator(), 
							new ClientTransactionPriorityComparator()));
		
		System.out.println("Client Id: || Transaction Type: || Transaction Date: || Priority: || Processing Fee:");
	
		for(int i=0; i<clientTransactionList.size();i++){
			System.out.println(clientTransactionList.get(i).getClientId() + " || " + clientTransactionList.get(i).getTransactionType() + 
					" || " + clientTransactionList.get(i).getTransactionDate() + " || " 
					+ ((clientTransactionList.get(i).getPriorityFlag().equals(ClientTransactionConstants.HIGH_PRIORITY)) ? "High" : "Normal") +
					" || " + clientTransactionList.get(i).getProcessingFees() + "\t");
		}
	}

	
}