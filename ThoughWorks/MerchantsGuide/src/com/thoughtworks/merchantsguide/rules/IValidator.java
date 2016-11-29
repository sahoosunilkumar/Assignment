package com.thoughtworks.merchantsguide.rules;

import com.thoughtworks.merchantsguide.error.ValidatorException;
import com.thoughtworks.merchantsguide.utility.ValidatorUtils;

public class IValidator implements IRulesValidator {
	IRulesValidator rulesValidator;

	@Override
	public void validate(String expression) {
		boolean validate = ValidatorUtils.validateSuccession(expression, 'I',
				3);
		if (validate) {
			validate = ValidatorUtils.validateSubtraction(expression, 'I',
					new char[] { 'I', 'V', 'X' });
		}

		if (!validate) {
			rulesValidator = null;
			throw new ValidatorException("I",expression);
		}
		if(rulesValidator != null){
			rulesValidator.validate(expression);
		}

	}

	@Override
	public void setNextValidator(IRulesValidator rulesValidator) {
		this.rulesValidator = rulesValidator;
	}

}
