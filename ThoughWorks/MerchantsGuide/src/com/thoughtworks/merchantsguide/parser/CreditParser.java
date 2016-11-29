package com.thoughtworks.merchantsguide.parser;

import com.thoughtworks.merchantsguide.calculator.ValueCalculator;
import com.thoughtworks.merchantsguide.database.Data;
import com.thoughtworks.merchantsguide.error.ParserException;
import com.thoughtworks.merchantsguide.rules.RulesValidator;

public class CreditParser implements IParser {
	IParser inputParser;

	@Override
	public String parse(String inputData) throws ParserException {
		if (inputData.matches(".*\\sis\\s\\d+\\sCredits")) {
			String expression = inputData.split(" is ")[0];
			String locale = expression
					.substring(expression.lastIndexOf(" ") + 1);
			String knownPart = expression.substring(0,
					expression.lastIndexOf(locale) - 1);
			RulesValidator validator = new RulesValidator();
			validator.validate(knownPart, " ");
			ValueCalculator valueCalculator = new ValueCalculator(knownPart,
					" ");
			float value = getValue(inputData) / valueCalculator.evaluate();
			Data.addValueToLocale(locale, value);
			this.inputParser = null;
		}
		if (this.inputParser != null) {
			return inputParser.parse(inputData);
		} else {
			return "";
		}

	}

	@Override
	public void setNextParser(IParser inputParser) {
		this.inputParser = inputParser;

	}

	private static float getValue(String inputData) {
		int position = inputData.split(" ").length - 2;
		return Integer.parseInt(inputData.split(" ")[position]);
	}
}
