package com.thoughtworks.merchantsguide.rules.romancharrules;

import com.thoughtworks.merchantsguide.error.ValidatorException;
import com.thoughtworks.merchantsguide.rules.RulesRomanLetterValidator;

public class MValidator extends RulesRomanLetterValidator {

	@Override
	public void validate(String inputData) {
		boolean validate = validateSuccession(inputData, 'M', 3);

		if (!validate) {
			rulesValidator = null;
			throw new ValidatorException("M", inputData);
		} else {
			rulesValidator.validate(inputData);
		}

	}
}
