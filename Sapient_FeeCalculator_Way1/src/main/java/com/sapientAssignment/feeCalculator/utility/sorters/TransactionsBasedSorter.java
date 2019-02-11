package com.sapientAssignment.feeCalculator.utility.sorters;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.sapientAssignment.feeCalculator.model.Transaction;

public class TransactionsBasedSorter implements Comparator<Transaction> {

	private List<Comparator<Transaction>> sorters;
	@SafeVarargs
	public TransactionsBasedSorter(Comparator<Transaction>... sorters) {
		this.sorters = Arrays.asList(sorters);
	}

	@Override
	public int compare(Transaction t1, Transaction t2) {

		for (Comparator<Transaction> sorter : sorters) {
			int result = sorter.compare(t1, t2);
			if (result != 0) {
				return result;
			}
		}
		return 0;
	}
}
