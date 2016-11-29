package com.thoughtworks.merchantsguide.rules;

import com.thoughtworks.merchantsguide.error.ValidatorException;
import com.thoughtworks.merchantsguide.utility.ValidatorUtils;

public class XValidator implements IRulesValidator {
	IRulesValidator rulesValidator;

	@Override
	public void validate(String expression) {
		boolean validate = ValidatorUtils.validateSuccession(expression, 'X',
				3);
		if (validate) {
			validate = ValidatorUtils.validateSubtraction(expression, 'X',
					new char[] { 'X', 'L', 'C' });
		}
		if (!validate) {
			rulesValidator = null;
			throw new ValidatorException("X",expression);
		} else {
			rulesValidator.validate(expression);
		}

	}

	@Override
	public void setNextValidator(IRulesValidator rulesValidator) {
		this.rulesValidator = rulesValidator;
	}

}
