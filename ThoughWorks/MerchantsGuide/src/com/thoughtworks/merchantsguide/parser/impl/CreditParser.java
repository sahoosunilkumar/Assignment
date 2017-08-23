package com.thoughtworks.merchantsguide.parser.impl;

import com.thoughtworks.merchantsguide.calculator.ICalculator;
import com.thoughtworks.merchantsguide.calculator.ValueCalculator;
import com.thoughtworks.merchantsguide.error.ParserException;
import com.thoughtworks.merchantsguide.expression.ExpressionEvaluator;
import com.thoughtworks.merchantsguide.parser.ParserAbs;
import com.thoughtworks.merchantsguide.rules.RulesValidator;

/**
 * Parses Credits
 * 
 * @author sunilkumarsahoo
 *
 */
public class CreditParser extends ParserAbs {

	private final String REGEX = ".*\\sis\\s\\d+\\sCredits";

	@Override
	public String parse(String inputData) throws ParserException {
		if (inputData.matches(REGEX)) {
			String expression = inputData.split(" is ")[0];
			String locale = expression
					.substring(expression.lastIndexOf(" ") + 1);
			String knownPart = expression.substring(0,
					expression.lastIndexOf(locale) - 1);
			RulesValidator validator = new RulesValidator();
			validator.validate(knownPart, " ");
			ICalculator calculator = new ValueCalculator(knownPart, " ");
			float value = getValue(inputData) / calculator.calculate();
			ExpressionEvaluator.getInstance().getDatabase()
					.addValueToLocale(locale, value);
			this.inputParser = null;
		}
		if (this.inputParser != null) {
			return inputParser.parse(inputData);
		} else {
			return "";
		}

	}

	private static float getValue(String inputData) {
		int position = inputData.split(" ").length - 2;
		return Integer.parseInt(inputData.split(" ")[position]);
	}
}
