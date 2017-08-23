package com.thoughtworks.merchantsguide.calculator;

import java.util.Stack;

import com.thoughtworks.merchantsguide.expression.ExpressionEvaluator;

/**
 * calculates the expression
 * 
 * @author sunilkumarsahoo
 *
 */
public class ValueCalculator implements ICalculator {

	private Stack<String> stack = new Stack<>();
	private String expression;
	private String delimeter;

	private float currValue;
	private float prevValue = Float.MAX_VALUE;
	private float sum;
	private boolean isCreditApplied = false;

	public ValueCalculator(String expression, String delimeter) {
		this.expression = expression;
		this.delimeter = delimeter;
		String[] arr = this.expression.split(this.delimeter);
		for (String data : arr) {
			stack.push(data);
		}
	}

	/**
	 * calculates
	 * 
	 * @return
	 */
	@Override
	public float calculate() {
		float creditCarry = 1.0f;
		while (!stack.isEmpty()) {
			String data = stack.pop();
			if (!ExpressionEvaluator.getInstance().getDatabase()
					.isLocale(data)) {
				currValue = ExpressionEvaluator.getInstance().getDatabase()
						.getValue(data);
				if ((prevValue > currValue) && (prevValue != Float.MAX_VALUE)) {
					sum = sum - prevValue + (prevValue - currValue);
				} else {
					sum += currValue;
				}
				prevValue = currValue;
			} else {
				creditCarry = creditCarry * ExpressionEvaluator.getInstance()
						.getDatabase().getValue(data);
				isCreditApplied = true;
			}
		}
		return sum * creditCarry;
	}

	@Override
	public boolean isCreditApplied() {
		return isCreditApplied;
	}
}
