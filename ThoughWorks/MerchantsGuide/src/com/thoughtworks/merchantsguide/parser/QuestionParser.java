package com.thoughtworks.merchantsguide.parser;

import com.thoughtworks.merchantsguide.calculator.ValueCalculator;
import com.thoughtworks.merchantsguide.database.Data;
import com.thoughtworks.merchantsguide.error.ParserException;
import com.thoughtworks.merchantsguide.rules.RulesValidator;
import com.thoughtworks.merchantsguide.utility.TextUtils;

public class QuestionParser implements IParser {
	IParser inputParser;

	@Override
	public String parse(String inputData) throws ParserException {
		if (inputData.matches("how\\s(much|many\\sCredits)\\sis\\s(["
				+ getLocale() + "]+\\s)*([" + getLocaleFromValueMapping()
				+ "]+\\s)*\\?")) {
			String expression = inputData.split(" is ")[1];
			int lastPosition = expression.lastIndexOf(" ?");
			if (lastPosition != -1) {
				expression = expression.substring(0, lastPosition);
			}
			validate(expression);
			ValueCalculator valueCalculator = new ValueCalculator(expression,
					" ");
			this.inputParser = null;
			return expression + " is " + valueCalculator.evaluate()
					+ (valueCalculator.isCreditApplied() ? " Credits" : "");
		} else {
			throw new ParserException(inputData);
		}

	}

	@Override
	public void setNextParser(IParser inputParser) {
		this.inputParser = inputParser;
	}

	private String getLocale() {
		return TextUtils.convertToString(Data.getLocale(), "|");
	}

	private String getLocaleFromValueMapping() {
		return TextUtils.convertToString(Data.getLocaleFromValueMapping(), "|");
	}
	
	private void validate(String expression){
		RulesValidator validator = new RulesValidator();
		String[] expressionSegmentArr = expression.split(" ");
		StringBuffer sb = new StringBuffer();
		for(String segment : expressionSegmentArr){
			if(Data.getRomanNumber(segment) != null){
				if(sb.length()==0){
					sb.append(segment);
				}else{
					sb.append(" ").append(segment);
				}
			}
		}
		validator.validate(sb.toString(), " ");
	}

}
