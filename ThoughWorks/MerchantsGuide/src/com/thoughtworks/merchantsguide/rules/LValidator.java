package com.thoughtworks.merchantsguide.rules;

import com.thoughtworks.merchantsguide.error.ValidatorException;
import com.thoughtworks.merchantsguide.utility.ValidatorUtils;

public class LValidator implements IRulesValidator {
	IRulesValidator rulesValidator;

	@Override
	public void validate(String inputData) {
		boolean validate = ValidatorUtils.validateSuccession(inputData, 'L', 1);

		if (!validate) {
			rulesValidator = null;
			throw new ValidatorException("L",inputData);
		} else {
			rulesValidator.validate(inputData);
		}

	}

	@Override
	public void setNextValidator(IRulesValidator rulesValidator) {
		this.rulesValidator = rulesValidator;
	}

}
