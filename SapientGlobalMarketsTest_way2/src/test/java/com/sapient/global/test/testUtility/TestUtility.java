package com.sapient.global.test.testUtility;

import com.sapient.global.test.model.TransactionRecord;
import com.sapient.global.test.utility.TransactionRecordUtility;

public class TestUtility {


	protected static TransactionRecord createNormalTransaction(String clientId, String transactionType, String priorityFlag) {
		TransactionRecord transaction = new TransactionRecord("NormalTransactionRecordID", clientId, "REL",
				transactionType, TransactionRecordUtility.getObject().parseDate("01-11-2013"), 121.1, priorityFlag, 0d);
		
		return transaction;
	}

}
