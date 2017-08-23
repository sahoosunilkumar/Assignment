package com.thoughtworks.merchantsguide.rules;

import com.thoughtworks.merchantsguide.expression.ExpressionEvaluator;
import com.thoughtworks.merchantsguide.rules.romancharrules.CValidator;
import com.thoughtworks.merchantsguide.rules.romancharrules.DValidator;
import com.thoughtworks.merchantsguide.rules.romancharrules.IValidator;
import com.thoughtworks.merchantsguide.rules.romancharrules.LValidator;
import com.thoughtworks.merchantsguide.rules.romancharrules.MValidator;
import com.thoughtworks.merchantsguide.rules.romancharrules.VValidator;
import com.thoughtworks.merchantsguide.rules.romancharrules.XValidator;

/**
 * initializes all rules validators and sets next validator uses Chain Of
 * Responsibility pattern
 * 
 * @author sunilkumarsahoo
 *
 */
public class RulesValidator {
	IRulesValidator rulesValidator;

	private void initValidators() {
		IRulesValidator iValidator = new IValidator();
		IRulesValidator xValidator = new XValidator();
		IRulesValidator cValidator = new CValidator();
		IRulesValidator mValidator = new MValidator();
		IRulesValidator dValidator = new DValidator();
		IRulesValidator lValidator = new LValidator();
		IRulesValidator vValidator = new VValidator();
		iValidator.setNextValidator(xValidator);
		xValidator.setNextValidator(cValidator);
		cValidator.setNextValidator(mValidator);
		mValidator.setNextValidator(dValidator);
		dValidator.setNextValidator(lValidator);
		lValidator.setNextValidator(vValidator);
		rulesValidator = iValidator;
	}

	/**
	 * checks whether the input data is valid
	 * 
	 * @param inputData
	 * @return
	 */
	public boolean validate(String inputData) {
		initValidators();
		rulesValidator.validate(inputData);
		return true;
	}

	/**
	 * validates expression against its delimeter
	 * 
	 * @param expression
	 * @param delimeter
	 */
	public void validate(String expression, String delimeter) {
		initValidators();
		String[] dataArr = expression.split(delimeter);
		StringBuffer sb = new StringBuffer();
		for (String data : dataArr) {
			sb.append(ExpressionEvaluator.getInstance().getDatabase()
					.getRomanNumber(data));
		}
		if (sb.length() > 0) {
			rulesValidator.validate(sb.toString());
		}
	}

}
