package com.thoughtworks.merchantsguide.rules.romancharrules;

import com.thoughtworks.merchantsguide.error.ValidatorException;
import com.thoughtworks.merchantsguide.rules.RulesRomanLetterValidator;

final public class XValidator extends RulesRomanLetterValidator {

	@Override
	public void validate(String expression) {
		boolean validate = validateSuccession(expression, 'X', 3);
		if (validate) {
			validate = validateSubtraction(expression, 'X',
					new char[] { 'X', 'L', 'C' });
		}
		if (!validate) {
			rulesValidator = null;
			throw new ValidatorException("X", expression);
		} else {
			rulesValidator.validate(expression);
		}

	}

}
