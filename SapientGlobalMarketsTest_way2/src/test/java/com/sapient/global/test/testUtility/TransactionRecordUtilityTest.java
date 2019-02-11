package com.sapient.global.test.testUtility;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.sapient.global.test.constants.TransactionRecordPriorityBasedTypeConstant;
import com.sapient.global.test.constants.TransactionRecordTypeConstant;
import com.sapient.global.test.model.TransactionRecord;
import com.sapient.global.test.utility.TransactionRecordUtility;

public class TransactionRecordUtilityTest {
	TransactionRecordUtility transactionRecordUtility;

	@Before
	public void setup() {
		transactionRecordUtility = TransactionRecordUtility.getObject();
	}

	@After
	public void tearDown() {
		transactionRecordUtility = null;
	}

	@Test
	public void testNormalTransactions() {
		TransactionRecord t1 = TestUtility.createNormalTransaction("HJ", TransactionRecordTypeConstant.WITHDRAW, TransactionRecordPriorityBasedTypeConstant.NORMAL_PRIORITY);
		TransactionRecord t2 = TestUtility.createNormalTransaction("AP", TransactionRecordTypeConstant.DEPOSIT,TransactionRecordPriorityBasedTypeConstant.HIGH_PRIORITY);
		transactionRecordUtility.applyProcessingRules(t1);
		transactionRecordUtility.applyProcessingRules(t2);
		assertEquals(new Double(100), t1.getProcessingFees());
		assertEquals(new Double(500), t2.getProcessingFees());

	}
	

}
