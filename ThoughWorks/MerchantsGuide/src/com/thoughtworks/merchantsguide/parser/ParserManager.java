package com.thoughtworks.merchantsguide.parser;

import com.thoughtworks.merchantsguide.parser.impl.CreditParser;
import com.thoughtworks.merchantsguide.parser.impl.QuestionParser;
import com.thoughtworks.merchantsguide.parser.impl.RomanNumberParser;

/**
 * initializes all parsers and sets next parser implemented using Chain Of
 * Responsibility pattern
 * 
 * @author sunilkumarsahoo
 *
 */
public class ParserManager {
	private IParser parser;

	private void initParser() {
		IParser romanNumberParser = new RomanNumberParser();
		IParser questionParser = new QuestionParser();
		IParser creditParser = new CreditParser();
		romanNumberParser.setNextParser(creditParser);
		creditParser.setNextParser(questionParser);
		parser = romanNumberParser;
	}

	/**
	 * evaluates the input data and returns result
	 * 
	 * @param inputData
	 * @return
	 */
	public String evaluate(String inputData) {
		initParser();
		return this.parser.parse(inputData);
	}
}
