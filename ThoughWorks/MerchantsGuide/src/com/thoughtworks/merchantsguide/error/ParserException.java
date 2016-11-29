package com.thoughtworks.merchantsguide.error;

public class ParserException extends IllegalArgumentException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3106532825017222909L;

	public ParserException(String expression) {
		super("I have no idea what you are talking about");
	}
}
