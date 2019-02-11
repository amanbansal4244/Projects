package com.sgm.transaction.management.model;

import java.util.Comparator;

public class ClientIdComparator implements Comparator<ClientTransaction> {
	
	@Override
	public int compare(ClientTransaction t1, ClientTransaction t2) {
		return t1.getClientId().compareTo(t2.getClientId());
	}
}
