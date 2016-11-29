package com.thoughtworks.merchantsguide.rules;

import com.thoughtworks.merchantsguide.error.ValidatorException;
import com.thoughtworks.merchantsguide.utility.ValidatorUtils;

public class CValidator implements IRulesValidator {
	IRulesValidator rulesValidator;

	@Override
	public void validate(String expression) {
		boolean validate = ValidatorUtils.validateSuccession(expression, 'C',
				3);
		if (validate) {
			validate = ValidatorUtils.validateSubtraction(expression, 'C',
					new char[] { 'C', 'D', 'M' });
		}
		if (!validate) {
			rulesValidator = null;
			throw new ValidatorException("C",expression);
		} else {
			rulesValidator.validate(expression);
		}

	}

	@Override
	public void setNextValidator(IRulesValidator rulesValidator) {
		this.rulesValidator = rulesValidator;
	}

}
