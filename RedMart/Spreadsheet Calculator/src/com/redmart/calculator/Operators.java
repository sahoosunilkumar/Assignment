package com.redmart.calculator;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;
import com.redmart.calculator.exception.InvalidNumberException;

public enum Operators {

	ADDITION("+"), SUBTRACTION("-"), MULTIPLICATION("*"), DIVISION("/");

	private static final Map<String, Operators> operatorsMap = new HashMap<String, Operators>();

	static {
		for (Operators op : Operators.values())
			operatorsMap.put(op.getOperator(), op);
	}

	private final String operator;

	private Operators(String op) {
		operator = op;
	}

	public static Operators get(String op) {
		return operatorsMap.get(op);
	}

	/**
	 * checks whether provided string is valid operator
	 * @param token
	 * @return
	 */
	public static boolean isValidOperator(String op) {
		return get(op) != null;
	}

	public String getOperator() {
		return operator;
	}
	
	/**
	 * evaluate result after applying operator
	 * @param argumentStack
	 * @throws InvalidNumberException
	 */
	public double evaluate(Stack<Double> argumentStack) throws InvalidNumberException {
		double op1, op2;
		op1 = argumentStack.pop();
		op2 = argumentStack.pop();
		switch (this) {
			case ADDITION:
				return op2 + op1;
			case SUBTRACTION:
				return op2 - op1;
			case MULTIPLICATION:
				return op2 * op1;
			case DIVISION:
				return op2 / op1;
			default:
				throw new InvalidNumberException(
						"Unhandled Operator : " + operator);
			}
	}
}