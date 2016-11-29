package com.thoughtworks.merchantsguide.error;

public class IllegalParserException extends IllegalArgumentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1545108534918666861L;

	public IllegalParserException() {
		super("Parser Data is not valid");
	}
}
