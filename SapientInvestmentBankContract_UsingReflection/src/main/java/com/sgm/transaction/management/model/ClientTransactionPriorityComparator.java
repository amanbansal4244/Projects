package com.sgm.transaction.management.model;

import java.util.Comparator;

import com.sgm.transaction.management.constants.ClientTransactionConstants;

public class ClientTransactionPriorityComparator implements Comparator<ClientTransaction> {
	
	
	@Override
	public int compare(ClientTransaction t1, ClientTransaction t2) {
		
		String p1 = t1.getPriorityFlag().equals(ClientTransactionConstants.HIGH_PRIORITY) ? "High" : "Normal";
		String p2 = t2.getPriorityFlag().equals(ClientTransactionConstants.HIGH_PRIORITY) ? "High" : "Normal";
		return p1.compareTo(p2);
	}
}