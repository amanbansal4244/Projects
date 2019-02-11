package com.sgm.transaction.management.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import com.sgm.transaction.management.constants.ClientTransactionConstants;


public class ClientTransactionService {

	private ConcurrentHashMap<String, ClientTransaction> clientTransactionsMap = new ConcurrentHashMap<>();
	
	public void processTransaction(String[] strTokens) {
		applyProcessingRules(ClientTransactionAttributesUtility.getInstance().setTransactionRecord(strTokens));
		
	}


	public void applyProcessingRules(ClientTransaction clientTransaction) {

		if (clientTransaction.getPriorityFlag().equals(ClientTransactionConstants.HIGH_PRIORITY)) {
			clientTransaction.setTransactionProcessingFees(clientTransaction.getProcessingFees() + ClientTransactionConstants.FIVE_HUNDRED);
		}
		else {
			if ((clientTransaction.getTransactionType().equals(ClientTransactionConstants.BUY)) || (clientTransaction.getTransactionType().equals(ClientTransactionConstants.DEPOSIT))) {
				clientTransaction.setTransactionProcessingFees(clientTransaction.getProcessingFees() + ClientTransactionConstants.FIFTY);
			}
			
			if ((clientTransaction.getTransactionType().equals(ClientTransactionConstants.SELL)) || (clientTransaction.getTransactionType().equals(ClientTransactionConstants.WITHDRAW))) {
				clientTransaction.setTransactionProcessingFees(clientTransaction.getProcessingFees() + ClientTransactionConstants.HUNDRED);
			}
			
		}
		
		if (isIntraDayClientTransaction(clientTransaction)) {
			clientTransaction.setTransactionProcessingFees(clientTransaction.getProcessingFees() + ClientTransactionConstants.TEN);
		}

		clientTransactionsMap.put(clientTransaction.getExternalTransactionId(), clientTransaction);
		
	}
	
	public List<ClientTransaction> getAllTransactionRecord() {
		ArrayList<ClientTransaction> list = new ArrayList<ClientTransaction>();
		for (Entry<String, ClientTransaction> entry : clientTransactionsMap.entrySet()) { 
			ClientTransaction value = entry.getValue(); 
			list.add(value);
		}
		return list;
	}

	
	public boolean isIntraDayClientTransaction(ClientTransaction clientTransaction) {
		Collection<ClientTransaction> transactionsList = clientTransactionsMap.values();
		for ( ClientTransaction ct : transactionsList) {
			if ((((ct.getTransactionType() == ClientTransactionConstants.SELL) && (clientTransaction.getTransactionType() == ClientTransactionConstants.BUY))
					|| ((ct.getTransactionType() == ClientTransactionConstants.BUY) && (clientTransaction.getTransactionType() == ClientTransactionConstants.SELL))) 
					&& ((ct.getClientId().equals(clientTransaction.getClientId())) && (ct.getSecurityId().equals(clientTransaction.getSecurityId()))  
							&& (ct.getTransactionDate().equals(clientTransaction.getTransactionDate()))))
			{
				return true;
			}
			return false;
		}
		return false;
	}
	
}
