package com.thoughtworks.merchantsguide.parser;


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

	public String evaluate(String inputData) {
		initParser();
		return this.parser.parse(inputData);
	}
}
