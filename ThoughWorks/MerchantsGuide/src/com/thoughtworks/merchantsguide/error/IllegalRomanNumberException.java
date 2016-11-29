package com.thoughtworks.merchantsguide.error;

public class IllegalRomanNumberException extends IllegalArgumentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -186976728157120482L;

	public IllegalRomanNumberException() {
		super("Invalid Roman Number");
	}
}
