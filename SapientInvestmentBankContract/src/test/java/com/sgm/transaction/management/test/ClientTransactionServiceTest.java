package com.sgm.transaction.management.test;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sgm.transaction.management.constants.ClientTransactionConstants;
import com.sgm.transaction.management.model.ClientTransaction;
import com.sgm.transaction.management.model.ClientTransactionAttributesUtility;
import com.sgm.transaction.management.model.ClientTransactionService;


public class ClientTransactionServiceTest {
	ClientTransactionService clientTransactionService;

	@Before
	public void setup() {
		clientTransactionService = new ClientTransactionService();
	}

	@After
	public void tearDown() {
		clientTransactionService = null;
	}

	@Test
	public void testIntraDayClientTransactions() {
		ClientTransaction clientTransaction1 = createIntraDayTransaction("SGM1", ClientTransactionConstants.SELL);
		ClientTransaction clientTransaction2 = createIntraDayTransaction("SGM2", ClientTransactionConstants.BUY);
		clientTransactionService.applyProcessingRules(clientTransaction1);
		clientTransactionService.applyProcessingRules(clientTransaction2);
		
		assertEquals(new Double(500), clientTransaction1.getTransactionProcessingFees());
		assertEquals(new Double(510), clientTransaction2.getTransactionProcessingFees());

	}

	
	@Test
	public void testNormalDayClientTransactions() {
		ClientTransaction t1 = createNormalDayTransaction("GS", ClientTransactionConstants.SELL,  ClientTransactionConstants.NORMAL_PRIORITY);
		ClientTransaction t2 = createNormalDayTransaction("AS", ClientTransactionConstants.BUY, ClientTransactionConstants.HIGH_PRIORITY);
		clientTransactionService.applyProcessingRules(t1);
		clientTransactionService.applyProcessingRules(t2);
		
		assertEquals(new Double(100), t1.getProcessingFees());
		assertEquals(new Double(500), t2.getProcessingFees());

	}

	private ClientTransaction createIntraDayTransaction(String externalTransactionID, String transactionType) {
		ClientTransaction transaction = new ClientTransaction();
		transaction.setExternalTransactionId(externalTransactionID);
		transaction.setPriorityFlag(ClientTransactionConstants.HIGH_PRIORITY);
		transaction.setClientId("AP");
		transaction.setSecurityId("ICICI");
		transaction.setTransactionDate(ClientTransactionAttributesUtility.getInstance().getDate("12-11-2018"));
		transaction.setTransactionType(transactionType);
		transaction.setTransactionProcessingFees(0d);
		return transaction;
	}

	private ClientTransaction createNormalDayTransaction(String clientId, String transactionType, String transactionPriorityType) {
		ClientTransaction transaction = new ClientTransaction();
		transaction.setExternalTransactionId("SGM");
		transaction.setPriorityFlag(transactionPriorityType);
		transaction.setClientId(clientId);
		transaction.setSecurityId("HINDALCO	");
		transaction.setTransactionDate(ClientTransactionAttributesUtility.getInstance().getDate("23-10-1992"));
		transaction.setTransactionType(transactionType);
		transaction.setTransactionProcessingFees(0d);
		return transaction;
	}

	@Test
	public void testIsIntraDayClientTransaction() {
		ClientTransaction clientTransaction1 = createIntraDayTransaction("SGM3", ClientTransactionConstants.SELL);
		ClientTransaction clientTransaction2 = createIntraDayTransaction("SGM4", ClientTransactionConstants.BUY);
		clientTransactionService.applyProcessingRules(clientTransaction1);
		boolean isIntraDayClientTransaction = clientTransactionService.isIntraDayClientTransaction(clientTransaction2);
		
		assertEquals(true, isIntraDayClientTransaction);

	}
	
	@Test
	public void testGetAllTransactionRecord() {
		ClientTransaction clientTransaction1 = createIntraDayTransaction("SGM3", ClientTransactionConstants.SELL);
		ClientTransaction clientTransaction2 = createIntraDayTransaction("SGM4", ClientTransactionConstants.BUY);
		ClientTransaction t1 = createNormalDayTransaction("GS", ClientTransactionConstants.SELL,  ClientTransactionConstants.NORMAL_PRIORITY);
		ClientTransaction t2 = createNormalDayTransaction("AS", ClientTransactionConstants.BUY, ClientTransactionConstants.HIGH_PRIORITY);
		clientTransactionService.applyProcessingRules(clientTransaction1);
		clientTransactionService.applyProcessingRules(clientTransaction2);
		clientTransactionService.applyProcessingRules(t1);
		clientTransactionService.applyProcessingRules(t2);

		ArrayList<ClientTransaction> list  = (ArrayList<ClientTransaction>) clientTransactionService.getAllTransactionRecord();
		assertEquals(3, list.size());

	}
	
}
