package com.example.demo.exception;

public class InvalidFileException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public InvalidFileException(String message) {
		super(message);
	}
	

}
