package com.sapientAssignment.feeCalculator.utility.sorters;

import java.util.Comparator;

import com.sapientAssignment.feeCalculator.model.Transaction;

public class ClientIdBasedSorter implements Comparator<Transaction> {
	@Override
	public int compare(Transaction t1, Transaction t2) {
		return t1.getClientId().compareTo(t2.getClientId());
	}
}
