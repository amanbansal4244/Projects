package com.sapient.metro.card.model;

public class SmartCard {

	private double smartCardBalance;
	private long smartCardId;
	private User smartCardUser;

	public double getSmartCardBalance() {
		return smartCardBalance;
	}

	public void setSmartCardBalance(final double smartCardBalance) {
		this.smartCardBalance = smartCardBalance;
	}

	public long getSmartCardId() {
		return smartCardId;
	}

	public void setSmartCardId(final long smartCardId) {
		this.smartCardId = smartCardId;
	}

	public User getSmartCardUser() {
		return smartCardUser;
	}

	public void setSmartCardUser(final User smartCardUser) {
		this.smartCardUser = smartCardUser;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = (prime * result) + (int) (smartCardId ^ (smartCardId >>> 32));
		return result;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final SmartCard other = (SmartCard) obj;
		if (smartCardId != other.smartCardId) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "SmartCard [smartCardBalance=" + smartCardBalance + ", smartCardId=" + smartCardId + ", smartCardUser="
				+ smartCardUser + "]";
	}

}
