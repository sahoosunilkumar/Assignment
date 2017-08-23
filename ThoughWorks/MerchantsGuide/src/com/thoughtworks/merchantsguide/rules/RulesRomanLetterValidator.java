package com.thoughtworks.merchantsguide.rules;

import com.thoughtworks.merchantsguide.expression.ExpressionEvaluator;
import com.thoughtworks.merchantsguide.utility.StringUtils;

/**
 * base class for all roman letter validators
 * 
 * @author sunilkumarsahoo
 *
 */
public abstract class RulesRomanLetterValidator implements IRulesValidator {
	protected IRulesValidator rulesValidator;

	@Override
	public void setNextValidator(IRulesValidator rulesValidator) {
		this.rulesValidator = rulesValidator;
	}

	/**
	 * validates succession
	 * 
	 * @param expression
	 * @param romanNumber
	 * @param maxOccurence
	 * @return
	 */
	protected boolean validateSuccession(String expression, char romanNumber,
			int maxOccurence) {
		int counter = 0;
		for (int i = 0; i < expression.length(); i++) {
			if (romanNumber == expression.charAt(i)) {
				counter++;
			} else {
				if (counter > maxOccurence) {
					if (ExpressionEvaluator.getInstance().getDatabase()
							.getValue(String
									.valueOf(romanNumber)) > ExpressionEvaluator
											.getInstance().getDatabase()
											.getValue(String.valueOf(expression
													.charAt(i - 1)))) {
						return true;
					} else {
						return false;
					}
				}
				counter = 0;
			}
		}
		return counter <= maxOccurence;
	}

	/**
	 * validates subtraction
	 * 
	 * @param expression
	 * @param romanChar
	 * @param validSubtractor
	 * @return
	 */
	protected boolean validateSubtraction(String expression, char romanChar,
			char[] validSubtractor) {
		char prevChar;
		boolean isValid = true;
		if (expression.length() <= 1) {
			return isValid;
		}
		int startIndex = expression.indexOf(romanChar);

		prevChar = expression.charAt(startIndex <= 0 ? 0 : startIndex);
		if (startIndex == -1) {
			return isValid;
		}
		float value = ExpressionEvaluator.getInstance().getDatabase()
				.getValue(String.valueOf(romanChar));
		if (startIndex > 0) {
			char currChar = expression.charAt(startIndex - 1);
			isValid = (ExpressionEvaluator.getInstance().getDatabase()
					.getValue(String.valueOf(currChar)) >= value);
		}
		for (int i = startIndex + 1; i < expression.length(); i++) {
			char currChar = expression.charAt(i);
			if (prevChar == romanChar) {
				isValid = (ExpressionEvaluator.getInstance().getDatabase()
						.getValue(String.valueOf(currChar)) <= value)
						|| StringUtils.contains(validSubtractor, currChar);
				if (!isValid) {
					break;
				}
			}
			prevChar = currChar;
		}
		return isValid;
	}
}
