package com.thoughtworks.merchantsguide.error;

public class ValidatorException extends IllegalArgumentException {
	/**
	 * 
	 */
	private static final long serialVersionUID = -3106532825017222909L;

	public ValidatorException(String where, String expression) {
		super("Validator Exception while validating " + where + " for : "
				+ expression);
	}
}
