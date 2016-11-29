package com.thoughtworks.merchantsguide.rules;

import com.thoughtworks.merchantsguide.database.Data;

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

	public boolean validate(String inputData){
		initValidators();
		rulesValidator.validate(inputData);
		return true;
	}

	public void validate(String expression, String delimeter) {
		initValidators();
		String[] dataArr = expression.split(delimeter);
		StringBuffer sb = new StringBuffer();
		for (String data : dataArr) {
			sb.append(Data.getRomanNumber(data));
		}
		if(sb.length()>0){
        rulesValidator.validate(sb.toString());
		}
	}

}
