package com.sgm.transaction.management.model;

import java.util.Comparator;

public class ClientTransactionDateComparator implements Comparator<ClientTransaction> {
	
	
	@Override
	public int compare(ClientTransaction t1, ClientTransaction t2) {
		return t1.getTransactionDate().compareTo(t2.getTransactionDate());
	}
}
