package com.thoughtworks.merchantsguide.rules.romancharrules;

import com.thoughtworks.merchantsguide.error.ValidatorException;
import com.thoughtworks.merchantsguide.rules.RulesRomanLetterValidator;

final public class CValidator extends RulesRomanLetterValidator {

	@Override
	public void validate(String expression) {
		boolean validate = validateSuccession(expression, 'C', 3);
		if (validate) {
			validate = validateSubtraction(expression, 'C',
					new char[] { 'C', 'D', 'M' });
		}
		if (!validate) {
			rulesValidator = null;
			throw new ValidatorException("C", expression);
		} else {
			rulesValidator.validate(expression);
		}

	}

}
