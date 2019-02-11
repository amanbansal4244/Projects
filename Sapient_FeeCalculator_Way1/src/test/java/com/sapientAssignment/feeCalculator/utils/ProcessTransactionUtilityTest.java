package com.sapientAssignment.feeCalculator.utils;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sapientAssignment.feeCalculator.constant.Constants.TransactionPriorityType;
import com.sapientAssignment.feeCalculator.constant.Constants.TransactionType;
import com.sapientAssignment.feeCalculator.model.Transaction;
import com.sapientAssignment.feeCalculator.utility.TransactionAttributesUtility;
import com.sapientAssignment.feeCalculator.utility.ProcessTransactionUtility;

public class ProcessTransactionUtilityTest {
	ProcessTransactionUtility processTransactionUtility;

	@Before
	public void setup() {
		processTransactionUtility = ProcessTransactionUtility.getInstance();
	}

	@After
	public void tearDown() {
		processTransactionUtility = null;
	}

	@Test
	public void testIntraDayTransactions() {
		Transaction transaction1 = createIntraDayTransaction("TestSapient1", TransactionType.BUY);
		Transaction transaction2 = createIntraDayTransaction("TestSapient2", TransactionType.SELL);
		processTransactionUtility.processTransaction(transaction1);
		processTransactionUtility.processTransaction(transaction2);
		assertEquals(new Double(50), transaction1.getTransactionProcessingFees());
		assertEquals(new Double(110), transaction2.getTransactionProcessingFees());

	}

	@Test
	public void testNormalDayTransactions() {
		Transaction transaction1 = createNormalDayTransaction("GS", TransactionType.DEPOSIT, TransactionPriorityType.HIGH);
		Transaction transaction2 = createNormalDayTransaction("AS", TransactionType.WITHDRAW,TransactionPriorityType.NORMAL);
		processTransactionUtility.processTransaction(transaction1);
		processTransactionUtility.processTransaction(transaction2);
		assertEquals(new Double(500), transaction1.getTransactionProcessingFees());
		assertEquals(new Double(100), transaction2.getTransactionProcessingFees());

	}

	private Transaction createIntraDayTransaction(String externalTransactionID, TransactionType transactionType) {
		Transaction transaction = new Transaction();
		transaction.setExternalTransactionId(externalTransactionID);
		transaction.setPriorityFlag(TransactionPriorityType.NORMAL);
		transaction.setClientId("GS");
		transaction.setSecurityId("HDFC");
		transaction.setTransactionDate(TransactionAttributesUtility.getInstance().getDate("21-01-2017"));
		transaction.setTransactionType(transactionType);
		transaction.setTransactionProcessingFees(0d);
		return transaction;
	}

	private Transaction createNormalDayTransaction(String clientId, TransactionType transactionType, TransactionPriorityType transactionPriorityType) {
		Transaction transaction = new Transaction();
		transaction.setExternalTransactionId("TestExtID");
		transaction.setPriorityFlag(transactionPriorityType);
		transaction.setClientId(clientId);
		transaction.setSecurityId("HDFC");
		transaction.setTransactionDate(TransactionAttributesUtility.getInstance().getDate("21-01-2017"));
		transaction.setTransactionType(transactionType);
		transaction.setTransactionProcessingFees(0d);
		return transaction;
	}

}
