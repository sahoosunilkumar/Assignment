package com.redmart.calculator.exception;

public class InvalidNumberException extends Exception{
	/**
	 * Class to throw invalid input type exception
	 */
	private static final long serialVersionUID = -4972888584397019274L;

	public InvalidNumberException(String message){
		super(message);
	}
}
