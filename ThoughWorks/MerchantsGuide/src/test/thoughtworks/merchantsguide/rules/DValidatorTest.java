package test.thoughtworks.merchantsguide.rules;

import org.junit.Test;

import com.thoughtworks.merchantsguide.error.ValidatorException;
import com.thoughtworks.merchantsguide.rules.DValidator;
import com.thoughtworks.merchantsguide.rules.IRulesValidator;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class DValidatorTest {
	
	@Test()
	public void ValidationSuccessFor_DRomanCharacterSingle() {
		// given
		IRulesValidator rulesValidator = new DValidator();
		assertTrue(validate(rulesValidator, "D"));
	}

	@Test()
	public void ValidationFailureFor_DRomanCharacterRepetition() {
		// given
		IRulesValidator rulesValidator = new DValidator();
		assertFalse(validate(rulesValidator, "IXDD"));
	}
	
	@Test()
	public void ValidationFailureFor_DRomanCharacterRepetitionOtherCharacterInBetween() {
		// given
		IRulesValidator rulesValidator = new DValidator();
		assertTrue(validate(rulesValidator, "DCD"));
	}

	
	@Test()
	public void ValidationFailureFor_DRomanCharacterSubtractedByOther() {
		// given
		IRulesValidator rulesValidator = new DValidator();
		assertFalse(validate(rulesValidator, "DM"));
	}

	private boolean validate(IRulesValidator rulesValidator, String expression) {
		try {
			rulesValidator.validate(expression);
			return true;
		} catch (ValidatorException ve) {
			return false;
		}
	}
}
