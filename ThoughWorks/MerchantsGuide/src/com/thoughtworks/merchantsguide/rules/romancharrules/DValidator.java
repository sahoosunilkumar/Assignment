package com.thoughtworks.merchantsguide.rules.romancharrules;

import com.thoughtworks.merchantsguide.error.ValidatorException;
import com.thoughtworks.merchantsguide.rules.RulesRomanLetterValidator;

final public class DValidator extends RulesRomanLetterValidator {

	@Override
	public void validate(String expression) {
		boolean validate = validateSuccession(expression, 'D', 1);
		if (validate) {
			validate = validateSubtraction(expression, 'D', new char[] {});
		}
		if (!validate) {
			rulesValidator = null;
			throw new ValidatorException("D", expression);
		}

		if (rulesValidator != null) {
			rulesValidator.validate(expression);
		}

	}

}
