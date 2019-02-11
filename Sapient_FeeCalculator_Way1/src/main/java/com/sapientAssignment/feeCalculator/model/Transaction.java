package com.sapientAssignment.feeCalculator.model;

import java.util.Date;

import com.sapientAssignment.feeCalculator.constant.Constants.TransactionPriorityType;
import com.sapientAssignment.feeCalculator.constant.Constants.TransactionType;

public class Transaction {
	private String externalTransactionId;
	private String clientId;
	private String securityId;
	private TransactionType transactionType;
	private Date transactionDate;
	private Double marketValue;
	private TransactionPriorityType priorityFlag;
	private Double transactionProcessingFees;

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
	public TransactionType getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(TransactionType transactionType) {
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
	public TransactionPriorityType getPriorityFlag() {
		return priorityFlag;
	}
	public void setPriorityFlag(TransactionPriorityType transactionPriorityType) {
		this.priorityFlag = transactionPriorityType;
	}

	public Double getTransactionProcessingFees() {
		return transactionProcessingFees;
	}
	public void setTransactionProcessingFees(Double transactionProcessingFees) {
		this.transactionProcessingFees = transactionProcessingFees;
	}

	@Override
	public String toString() {
		return "Transaction {externalTransactionId=" + externalTransactionId + ", clientId=" + clientId + ", securityId=" + securityId + ","
				+ " transactionType=" + transactionType + ", transactionDate="+ transactionDate + ", marketValue=" + marketValue + ","
				+ " priority=" + priorityFlag + ", transactionFees=" + transactionProcessingFees + "}";
	}

	public boolean isSameTransactionType(Transaction transaction) {
		if ((transaction.getClientId().equals(this.getClientId())) && (transaction.getSecurityId().equals(this.getSecurityId())) 
				&& (transaction.getTransactionDate().equals(this.getTransactionDate()))) {
			return true;
		}
		return false;
	}

	public boolean isOppositeTransactionType(Transaction transaction) {
		if (((transaction.getTransactionType() == TransactionType.SELL) && (this.getTransactionType() == TransactionType.BUY))
				|| ((transaction.getTransactionType() == TransactionType.BUY) && (this.getTransactionType() == TransactionType.SELL))) {
			return true;
		}
		return false;
	}

}