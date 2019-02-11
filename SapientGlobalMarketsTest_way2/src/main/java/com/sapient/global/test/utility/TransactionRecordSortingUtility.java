package com.sapient.global.test.utility;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

import com.sapient.global.test.model.TransactionRecord;

public class TransactionRecordSortingUtility implements Comparator<TransactionRecord> {

	private List<Comparator<TransactionRecord>> TransactionRecordSorters;

	public TransactionRecordSortingUtility(Comparator<TransactionRecord>... TransactionSorters) {
		this.TransactionRecordSorters = Arrays.asList(TransactionSorters);
	}

	@Override
	public int compare(TransactionRecord t1, TransactionRecord t2) {

		for (Comparator<TransactionRecord> transactionSorters : TransactionRecordSorters) {
			int result = transactionSorters.compare(t1, t2);
			if (result != 0) {
				return result;
			}
		}
		return 0;
	}
}
