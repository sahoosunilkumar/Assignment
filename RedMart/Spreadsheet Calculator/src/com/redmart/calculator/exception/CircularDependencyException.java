package com.redmart.calculator.exception;

public class CircularDependencyException extends Exception {
	/**
	 * class to throw Circular Dependency exception
	 */
	private static final long serialVersionUID = -969385379354463538L;

	public CircularDependencyException(String message) {
		super(message);
	}
}
