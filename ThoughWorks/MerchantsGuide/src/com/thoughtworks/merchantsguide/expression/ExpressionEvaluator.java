package com.thoughtworks.merchantsguide.expression;

import com.thoughtworks.merchantsguide.database.IDatabase;
import com.thoughtworks.merchantsguide.database.LocalStorageDatabase;
import com.thoughtworks.merchantsguide.error.ParserException;
import com.thoughtworks.merchantsguide.error.ValidatorException;
import com.thoughtworks.merchantsguide.parser.ParserManager;

/**
 * Entry point of the calculator program. It is initialized only once
 * 
 * @author sunilkumarsahoo
 *
 */
public class ExpressionEvaluator {

	private IDatabase database;
	private String value;
	private ParserManager manager;

	/**
	 * Private constructor.
	 */
	private ExpressionEvaluator() {
		manager = new ParserManager();
		setDatabase(new LocalStorageDatabase());
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

	public String evaluate(String inputData) {
		try {
			value = manager.evaluate(inputData);
		} catch (ParserException pe) {
			value = pe.getMessage();
		} catch (ValidatorException ve) {
			value = ve.getMessage() + " " + inputData;
		}
		return value;
	}

	public IDatabase getDatabase() {
		return database;
	}

	private void setDatabase(IDatabase database) {
		this.database = database;
	}

}
