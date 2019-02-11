package com.sgm.transaction.management.model;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;


public class ClientTransactionComparatorUtility implements Comparator<ClientTransaction> {

	private List<Comparator<ClientTransaction>> clientTransactionRecordSorters;

	@SafeVarargs
	public ClientTransactionComparatorUtility(Comparator<ClientTransaction>... clientTransactionSorters) {
		this.clientTransactionRecordSorters = Arrays.asList(clientTransactionSorters);
	}

	@Override
	public int compare(ClientTransaction ct1, ClientTransaction ct2) {

		for (Comparator<ClientTransaction> clientTransactionSorters : clientTransactionRecordSorters) {
			int result = clientTransactionSorters.compare(ct1, ct2);
			if (result != 0) {
				return result;
			}
		}
		return 0;
	}
}
