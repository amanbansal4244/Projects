package com.sapient.global.test.model;

import java.util.Comparator;

import com.sapient.global.test.constants.TransactionRecordPriorityBasedTypeConstant;

public class TransactionRecordPrioritySorting implements Comparator<TransactionRecord> {
	@Override
	public int compare(TransactionRecord t1, TransactionRecord t2) {
		String p1 = t1.getPriorityFlag().equals(TransactionRecordPriorityBasedTypeConstant.HIGH_PRIORITY) ? "High" : "Normal";
		String p2 = t2.getPriorityFlag().equals(TransactionRecordPriorityBasedTypeConstant.HIGH_PRIORITY) ? "High" : "Normal";
		return p1.compareTo(p2);
	}
}