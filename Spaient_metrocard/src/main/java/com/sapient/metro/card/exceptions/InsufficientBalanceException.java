package com.sapient.metro.card.exceptions;

public class InsufficientBalanceException extends Exception {

	private static final long serialVersionUID = -2838601244302852088L;

	public InsufficientBalanceException(String msg) {
		super(msg);
	}
}
