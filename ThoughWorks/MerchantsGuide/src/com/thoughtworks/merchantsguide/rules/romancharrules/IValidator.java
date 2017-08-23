package com.thoughtworks.merchantsguide.rules.romancharrules;

import com.thoughtworks.merchantsguide.error.ValidatorException;
import com.thoughtworks.merchantsguide.rules.RulesRomanLetterValidator;

public class IValidator extends RulesRomanLetterValidator {

	@Override
	public void validate(String expression) {
		boolean validate = validateSuccession(expression, 'I', 3);
		if (validate) {
			validate = validateSubtraction(expression, 'I',
					new char[] { 'I', 'V', 'X' });
		}

		if (!validate) {
			rulesValidator = null;
			throw new ValidatorException("I", expression);
		}
		if (rulesValidator != null) {
			rulesValidator.validate(expression);
		}

	}

}
