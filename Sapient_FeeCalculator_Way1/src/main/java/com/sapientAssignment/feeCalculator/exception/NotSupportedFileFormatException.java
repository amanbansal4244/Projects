package com.sapientAssignment.feeCalculator.exception;

import java.io.IOException;

public class NotSupportedFileFormatException extends Exception {
	private static final long serialVersionUID = 1L;

	public NotSupportedFileFormatException(String error) {
		super(error);
	}

	public NotSupportedFileFormatException(String message, final IOException ioe) {
		super(message, ioe);
	}
	
}
