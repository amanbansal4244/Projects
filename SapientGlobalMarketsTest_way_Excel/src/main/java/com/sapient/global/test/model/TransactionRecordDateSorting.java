package com.sapient.global.test.model;

import java.util.Comparator;

public class TransactionRecordDateSorting implements Comparator<TransactionRecord> {
	
	
	@Override
	public int compare(TransactionRecord t1, TransactionRecord t2) {
		return t1.getTransactionDate().compareTo(t2.getTransactionDate());
	}
}
