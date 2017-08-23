package com.thoughtworks.merchantsguide.rules.romancharrules;

import com.thoughtworks.merchantsguide.error.ValidatorException;
import com.thoughtworks.merchantsguide.rules.RulesRomanLetterValidator;

public class LValidator extends RulesRomanLetterValidator {

	@Override
	public void validate(String inputData) {
		boolean validate = validateSuccession(inputData, 'L', 1);

		if (!validate) {
			rulesValidator = null;
			throw new ValidatorException("L", inputData);
		} else {
			rulesValidator.validate(inputData);
		}

	}

}
