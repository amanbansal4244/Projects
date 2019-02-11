package com.example.demo.exception;

public class StructureCreationFailedException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public StructureCreationFailedException(String errorMessage) {
		super(errorMessage);
	}
	

}
