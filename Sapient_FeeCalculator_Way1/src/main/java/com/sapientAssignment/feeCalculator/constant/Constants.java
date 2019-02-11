package com.sapientAssignment.feeCalculator.constant;

public class Constants {

	public enum TransactionType {
		 DEPOSIT, WITHDRAW, BUY, SELL
	}
	
	public enum TransactionPriorityType {
		 HIGH, NORMAL
	}
	
	public enum TransactionFees {
		 TEN(10),FIFTY(50), HUNDRED(100),  FIVE_HUNDRED(500);
		
		private final int fees;

		TransactionFees(final int fees) {
			this.fees = fees;
		}

		public int getFees() {
			return fees;
		}
	}
	
}
