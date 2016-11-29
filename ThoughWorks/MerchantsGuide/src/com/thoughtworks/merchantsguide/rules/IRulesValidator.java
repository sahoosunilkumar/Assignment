package com.thoughtworks.merchantsguide.rules;

import com.thoughtworks.merchantsguide.error.ValidatorException;

public interface IRulesValidator {

	void validate(String inputData) throws ValidatorException;

	void setNextValidator(IRulesValidator rulesValidator);
}
