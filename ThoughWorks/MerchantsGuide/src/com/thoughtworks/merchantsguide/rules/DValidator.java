package com.thoughtworks.merchantsguide.rules;

import com.thoughtworks.merchantsguide.error.ValidatorException;
import com.thoughtworks.merchantsguide.utility.ValidatorUtils;

public class DValidator implements IRulesValidator {
	IRulesValidator rulesValidator;

	@Override
	public void validate(String expression) {
		boolean validate = ValidatorUtils.validateSuccession(expression, 'D',
				1);
		if (validate) {
			validate = ValidatorUtils.validateSubtraction(expression, 'D',
					new char[] {});
		}
		if (!validate) {
			rulesValidator = null;
			throw new ValidatorException("D",expression);
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
