package com.thoughtworks.merchantsguide.error;

public class IllegalLocaleException extends IllegalArgumentException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4439322005742562534L;

	public IllegalLocaleException() {
		super("Invalid Locale");
	}
}
