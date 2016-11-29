package com.thoughtworks.merchantsguide.expression;
import com.thoughtworks.merchantsguide.error.ParserException;
import com.thoughtworks.merchantsguide.error.ValidatorException;
import com.thoughtworks.merchantsguide.parser.ParserManager;

public class ExpressionEvaluator {

	/**
	 * Private constructor.
	 */
	private ExpressionEvaluator() {
		manager = new ParserManager();
	}
	/**
	 * @return Singleton instance
	 */
	public static ExpressionEvaluator getInstance() {
		return ExpressionHolder.INSTANCE;
	}

	/**
	 * Provides the lazy-loaded Singleton instance.
	 */
	private static class ExpressionHolder {
		private static final ExpressionEvaluator INSTANCE = new ExpressionEvaluator();
	}
	private String value;
	private ParserManager manager;

	public String evaluate(String inputData) {
		try {
			value = manager.evaluate(inputData);
		} catch (ParserException pe) {
			value = pe.getMessage();
		} catch (ValidatorException ve) {
			value = ve.getMessage()+" "+inputData;
		}
		return value;
	}

}
