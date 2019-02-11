package com.sapientAssignment.feeCalculator.utility.sorters;

import java.util.Comparator;

import com.sapientAssignment.feeCalculator.model.Transaction;

public class TransactionTypeBasedSorter implements Comparator<Transaction> {
	@Override
	public int compare(Transaction t1, Transaction t2) {
		return t1.getTransactionType().compareTo(t2.getTransactionType());
	}
}