package com.sapientAssignment.feeCalculator.utility.sorters;

import java.util.Comparator;

import com.sapientAssignment.feeCalculator.constant.Constants.TransactionPriorityType;
import com.sapientAssignment.feeCalculator.model.Transaction;

public class PriorityBasedSorter implements Comparator<Transaction> {
	@Override
	public int compare(Transaction t1, Transaction t2) {
		String priority1 = t1.getPriorityFlag().equals(TransactionPriorityType.HIGH) ? "High" : "Normal";
		String priority2 = t2.getPriorityFlag().equals(TransactionPriorityType.HIGH) ? "High" : "Normal";
		return priority1.compareTo(priority2);
	}
}