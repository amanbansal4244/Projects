package com.sapient.global.test.Thread;

import com.sapient.global.test.utility.TransactionRecordUtility;

public class TransactionRecordThread implements Runnable{
	
	private String[] transactionRecordTokens;
	private TransactionRecordUtility transactionRecordUtility ;
	
	public TransactionRecordThread(String[ ] transactionRecordTokens, TransactionRecordUtility transactionRecordUtility) {
		this.transactionRecordTokens = transactionRecordTokens;
		this.transactionRecordUtility= transactionRecordUtility;
	}

	@Override
	public void run() {
		try{
			transactionRecordUtility.executeTransaction(transactionRecordTokens);
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}