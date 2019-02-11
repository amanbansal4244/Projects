package com.sapient.global.test.model;

import java.util.Comparator;

public class TransactionRecordClientIdSorting implements Comparator<TransactionRecord> {
	
	@Override
	public int compare(TransactionRecord t1, TransactionRecord t2) {
		return t1.getClientId().compareTo(t2.getClientId());
	}
}
