package com.sapient.global.test.model;

import java.util.Date;

import com.sapient.global.test.constants.TransactionRecordTypeConstant;


public class TransactionRecord {

	private String externalTransactionId;
	private String clientId;
	private String securityId;
	private String transactionType;
	private Date transactionDate;
	private Double marketValue;
	private String priorityFlag;
	private Double processingFees;

	public TransactionRecord() {
		
	}
	public TransactionRecord(String externalTransactionId, String clientId, String securityId, String transactionType,
			Date transactionDate, Double marketValue, String priorityFlag, Double processingFees) {
		super();
		this.externalTransactionId = externalTransactionId;
		this.clientId = clientId;
		this.securityId = securityId;
		this.transactionType = transactionType;
		this.transactionDate = transactionDate;
		this.marketValue = marketValue;
		this.priorityFlag = priorityFlag;
		this.processingFees = processingFees;
	}
	
	public String getExternalTransactionId() {
		return externalTransactionId;
	}

	public void setExternalTransactionId(String externalTransactionId) {
		this.externalTransactionId = externalTransactionId;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getSecurityId() {
		return securityId;
	}

	public void setSecurityId(String securityId) {
		this.securityId = securityId;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public Date getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(Date transactionDate) {
		this.transactionDate = transactionDate;
	}

	public Double getMarketValue() {
		return marketValue;
	}

	public void setMarketValue(Double marketValue) {
		this.marketValue = marketValue;
	}

	public String getPriorityFlag() {
		return priorityFlag;
	}

	public void setPriorityFlag(String priorityFlag) {
		this.priorityFlag = priorityFlag;
	}

	public Double getProcessingFees() {
		return processingFees;
	}

	public void setTransactionProcessingFees(Double transactionProcessingFees) {
		this.processingFees = transactionProcessingFees;
	}

	public boolean isOppositeTransactionRecordType(TransactionRecord transactionRecord) {
		if (((transactionRecord.getTransactionType() == TransactionRecordTypeConstant.SELL) && (this.getTransactionType() == TransactionRecordTypeConstant.BUY))
				|| ((transactionRecord.getTransactionType() == TransactionRecordTypeConstant.BUY) && (this.getTransactionType() == TransactionRecordTypeConstant.SELL))) {
			return true;
		}
		return false;
	}
	
	public boolean isSameTransactionRecordType(TransactionRecord transactionRecord) {
		if ((transactionRecord.getClientId().equals(this.getClientId())) && (transactionRecord.getSecurityId().equals(this.getSecurityId()))  && (transactionRecord.getTransactionDate().equals(this.getTransactionDate()))) {
			return true;
		}
		return false;
	}

	
	
}