package com.sgm.transaction.management.model;

import java.util.Date;


public class ClientTransaction {

	private String externalTransactionID;
	private String clientID;
	private String securityID;
	private String transactionType;
	private Date transactionDate;
	private Double marketValue;
	private String priorityFlag;
	private Double processingFees;

	
	public String getExternalTransactionId() {
		return externalTransactionID;
	}

	public void setExternalTransactionId(String externalTransactionId) {
		this.externalTransactionID = externalTransactionId;
	}

	public String getClientId() {
		return clientID;
	}

	public void setClientId(String clientId) {
		this.clientID = clientId;
	}

	public String getSecurityId() {
		return securityID;
	}

	public void setSecurityId(String securityId) {
		this.securityID = securityId;
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

	public void setTransactionProcessingFees(Double ProcessingFees) {
		this.processingFees = ProcessingFees;
	}
	public Double getTransactionProcessingFees() {
		return processingFees;
	}
	
}