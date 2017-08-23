package com.thoughtworks.merchantsguide.parser.impl;

import com.thoughtworks.merchantsguide.expression.ExpressionEvaluator;
import com.thoughtworks.merchantsguide.parser.ParserAbs;
import com.thoughtworks.merchantsguide.utility.StringUtils;

/**
 * Parses Roman numbers
 * 
 * @author sunilkumarsahoo
 *
 */
public class RomanNumberParser extends ParserAbs {

	private final String REGEX = "\\S+\\s(is)\\s\\S+";

	@Override
	public String parse(String inputData) {
		if (inputData.matches(REGEX)) {
			String locale = StringUtils.stringAt(inputData, " ", 0);
			String romanNumber = StringUtils.stringAt(inputData, " ", 2);
			ExpressionEvaluator.getInstance().getDatabase()
					.addLocaleToRomanNumber(locale, romanNumber);
			this.inputParser = null;
		}
		if (this.inputParser != null) {
			return inputParser.parse(inputData);
		} else {
			return "";
		}

	}

}
