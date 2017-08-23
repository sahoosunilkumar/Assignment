package com.thoughtworks.merchantsguide.rules.romancharrules;

import com.thoughtworks.merchantsguide.error.ValidatorException;
import com.thoughtworks.merchantsguide.rules.RulesRomanLetterValidator;

final public class VValidator extends RulesRomanLetterValidator {

	@Override
	public void validate(String inputData) {
		boolean validate = validateSuccession(inputData, 'V', 1);
		if (!validate) {
			rulesValidator = null;
			throw new ValidatorException("V", inputData);
		}

		if (rulesValidator != null) {
			rulesValidator.validate(inputData);
		}

	}

}
