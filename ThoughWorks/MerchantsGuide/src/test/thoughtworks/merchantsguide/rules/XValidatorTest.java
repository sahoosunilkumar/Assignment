package test.thoughtworks.merchantsguide.rules;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.thoughtworks.merchantsguide.error.ValidatorException;
import com.thoughtworks.merchantsguide.rules.RulesValidator;

public class XValidatorTest {

	@Test()
	public void ValidationSuccessFor_XRomanCharacterThreeSuccession() {
		// given
		RulesValidator rulesValidator = new RulesValidator();
		assertTrue(rulesValidator.validate("XXX"));
		;
	}

	@Test()
	public void ValidationFailureFor_XRomanCharacterFourSuccession() {
		// given
		RulesValidator rulesValidator = new RulesValidator();
		assertFalse(validate(rulesValidator, "XXXX"));
	}

	@Test()
	public void ValidationSuccessFor_XRomanCharacterFourSuccessionWithOtherCharacterInBetween() {
		// given
		RulesValidator rulesValidator = new RulesValidator();
		assertTrue(validate(rulesValidator, "XXXIX"));
	}

	@Test()
	public void ValidationSuccessFor_XRomanCharacterFourSuccessionSmallerCharacterInBetween() {
		// given
		RulesValidator rulesValidator = new RulesValidator();
		assertFalse(validate(rulesValidator, "XXXMX"));
	}

	@Test()
	public void ValidationSuccessFor_XRomanCharacterSubtractedByL() {
		// given
		RulesValidator rulesValidator = new RulesValidator();
		assertTrue(validate(rulesValidator, "XXL"));
	}

	@Test()
	public void ValidationFailureFor_XRomanCharacterSubtractedByOther() {
		// given
		RulesValidator rulesValidator = new RulesValidator();
		assertFalse(validate(rulesValidator, "XXM"));
	}

	private boolean validate(RulesValidator rulesValidator, String expression) {
		try {
			return rulesValidator.validate(expression);
		} catch (ValidatorException ve) {
			return false;
		}
	}
}
