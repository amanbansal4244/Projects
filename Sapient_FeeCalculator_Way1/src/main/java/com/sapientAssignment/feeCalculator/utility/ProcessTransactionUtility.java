package com.sapientAssignment.feeCalculator.utility;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.sapientAssignment.feeCalculator.constant.Constants.TransactionFees;
import com.sapientAssignment.feeCalculator.constant.Constants.TransactionPriorityType;
import com.sapientAssignment.feeCalculator.constant.Constants.TransactionType;
import com.sapientAssignment.feeCalculator.model.Transaction;

public class ProcessTransactionUtility {
	/**
	 * Create private constructor
	 */
	private ProcessTransactionUtility() {
	}

	private static ProcessTransactionUtility INSTANCE = null;
	private Map<String, Transaction> transactionsMap = new HashMap<String, Transaction>();

	/**
	 * Create a static method to get instance.
	 */
	public static ProcessTransactionUtility getInstance() {
		if(INSTANCE == null){
			synchronized (ProcessTransactionUtility.class) {
				if(INSTANCE == null){
					INSTANCE = new ProcessTransactionUtility();
				}
			}
		}
		return INSTANCE;
	}

	public void processTransaction(Transaction transaction) {
		applyProcessingRules(transaction);
		transactionsMap.put(transaction.getExternalTransactionId(), transaction);
	}

	public List<Transaction> getTransactionList() {
		return new ArrayList<>(transactionsMap.values());
	}

	private void applyProcessingRules(Transaction transaction) {

		if (isIntraDayTransaction(transaction)) {
			transaction.setTransactionProcessingFees(transaction.getTransactionProcessingFees() + TransactionFees.TEN.getFees());
		}

		if (transaction.getPriorityFlag().equals(TransactionPriorityType.HIGH)) {
			transaction.setTransactionProcessingFees(transaction.getTransactionProcessingFees() + TransactionFees.FIVE_HUNDRED.getFees());
		} else {
			if ((transaction.getTransactionType() == TransactionType.SELL) || (transaction.getTransactionType() == TransactionType.WITHDRAW)) {
				transaction.setTransactionProcessingFees(transaction.getTransactionProcessingFees() + TransactionFees.HUNDRED.getFees());
			}
			if ((transaction.getTransactionType() == TransactionType.BUY) || (transaction.getTransactionType() == TransactionType.DEPOSIT)) {
				transaction.setTransactionProcessingFees(transaction.getTransactionProcessingFees() + TransactionFees.FIFTY.getFees());
			}
		}

	}

	private boolean isIntraDayTransaction(Transaction transaction) {
		Collection<Transaction> transactionsList = transactionsMap.values();
		for ( Transaction trans : transactionsList) {
			if (trans.isSameTransactionType(transaction) && trans.isOppositeTransactionType(transaction)) {
				return true;
			}
		}
		return false;
	}
}
