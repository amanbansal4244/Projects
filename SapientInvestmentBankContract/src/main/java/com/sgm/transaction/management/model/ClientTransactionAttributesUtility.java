package com.sgm.transaction.management.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.sgm.transaction.management.constants.ClientTransactionConstants;
public class ClientTransactionAttributesUtility {
	
	private ClientTransactionAttributesUtility() {
	}

	private volatile static ClientTransactionAttributesUtility INSTANCE = null;;

	public static ClientTransactionAttributesUtility getInstance() {
		if(INSTANCE == null){
			synchronized (ClientTransactionAttributesUtility.class) {
				if(INSTANCE == null){
					INSTANCE = new ClientTransactionAttributesUtility();
				}
			}
		}
		return INSTANCE;
	}

	ClientTransaction setTransactionRecord(String[] clientTransactionRecordTokens) {
		ClientTransaction clientTransaction = new ClientTransaction();
		clientTransaction.setExternalTransactionId(clientTransactionRecordTokens[0]);
		clientTransaction.setClientId(clientTransactionRecordTokens[1]);
		clientTransaction.setSecurityId(clientTransactionRecordTokens[2]);
		clientTransaction.setTransactionType(clientTransactionRecordTokens[3].toUpperCase());
		clientTransaction.setTransactionDate(getDate(clientTransactionRecordTokens[4]));
		clientTransaction.setMarketValue(Double.parseDouble(clientTransactionRecordTokens[5]));
		clientTransaction.setPriorityFlag("Y".equalsIgnoreCase(clientTransactionRecordTokens[6]) ? ClientTransactionConstants.HIGH_PRIORITY :  ClientTransactionConstants.NORMAL_PRIORITY);
		clientTransaction.setTransactionProcessingFees(new Double(0));
		return clientTransaction;
		
	}
	
	public Date getDate(String date) {
		final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		Date parsedDate = null;
		try {
			parsedDate = sdf.parse(date);
			return parsedDate;
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return parsedDate;
	}
}
