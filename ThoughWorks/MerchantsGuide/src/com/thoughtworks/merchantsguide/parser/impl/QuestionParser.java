package com.thoughtworks.merchantsguide.parser.impl;

import com.thoughtworks.merchantsguide.calculator.ICalculator;
import com.thoughtworks.merchantsguide.calculator.ValueCalculator;
import com.thoughtworks.merchantsguide.error.ParserException;
import com.thoughtworks.merchantsguide.expression.ExpressionEvaluator;
import com.thoughtworks.merchantsguide.parser.ParserAbs;
import com.thoughtworks.merchantsguide.rules.RulesValidator;
import com.thoughtworks.merchantsguide.utility.StringUtils;

/**
 * Parses question
 * 
 * @author sunilkumarsahoo
 *
 */
public class QuestionParser extends ParserAbs {

	private final String REGEX = "how\\s(much|many\\sCredits)\\sis\\s(["
			+ getLocale() + "]+\\s)*([" + getLocaleFromValueMapping()
			+ "]+\\s)*\\?";
	private final String QUESTION_STATEMENT_SEPARATOR = " is ";
	private final String END_OF_QUESTION_IDENTIFIER = " ?";
	private final String CREDIT_EVALUATOR_STR = " Credits";

	@Override
	public String parse(String inputData) throws ParserException {
		if (inputData.matches(REGEX)) {
			String expression = inputData
					.split(QUESTION_STATEMENT_SEPARATOR)[1];
			int lastPosition = expression
					.lastIndexOf(END_OF_QUESTION_IDENTIFIER);
			if (lastPosition != -1) {
				expression = expression.substring(0, lastPosition);
			}
			validate(expression);
			ICalculator calculator = new ValueCalculator(expression, " ");
			this.inputParser = null;
			return expression + QUESTION_STATEMENT_SEPARATOR
					+ (int) calculator.calculate()
					+ (calculator.isCreditApplied() ? CREDIT_EVALUATOR_STR
							: "");
		} else {
			throw new ParserException(inputData);
		}

	}

	private String getLocale() {
		return StringUtils.convertToString(
				ExpressionEvaluator.getInstance().getDatabase().getLocale(),
				"|");
	}

	private String getLocaleFromValueMapping() {
		return StringUtils.convertToString(ExpressionEvaluator.getInstance()
				.getDatabase().getLocaleFromValueMapping(), "|");
	}

	private void validate(String expression) {
		RulesValidator validator = new RulesValidator();
		String[] expressionSegmentArr = expression.split(" ");
		StringBuffer sb = new StringBuffer();
		for (String segment : expressionSegmentArr) {
			if (ExpressionEvaluator.getInstance().getDatabase()
					.getRomanNumber(segment) != null) {
				if (sb.length() == 0) {
					sb.append(segment);
				} else {
					sb.append(" ").append(segment);
				}
			}
		}
		validator.validate(sb.toString(), " ");
	}

}
