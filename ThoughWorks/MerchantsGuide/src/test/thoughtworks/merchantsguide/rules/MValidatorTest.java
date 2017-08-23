package test.thoughtworks.merchantsguide.rules;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.thoughtworks.merchantsguide.error.ValidatorException;
import com.thoughtworks.merchantsguide.rules.RulesValidator;

public class MValidatorTest {

	@Test()
	public void ValidationSuccessFor_MRomanCharacterThreeSuccession() {
		// given
		RulesValidator rulesValidator = new RulesValidator();
		assertTrue(rulesValidator.validate("MMM"));
		;
	}

	@Test()
	public void ValidationFailureFor_MRomanCharacterFourSuccession() {
		// given
		RulesValidator rulesValidator = new RulesValidator();
		assertFalse(validate(rulesValidator, "MMMM"));
	}

	@Test()
	public void ValidationFailureFor_MRomanCharacterFourSuccessionWithSmallerCharacterInBetween() {
		// given
		RulesValidator rulesValidator = new RulesValidator();
		assertFalse(validate(rulesValidator, "MMMXM"));
	}

	@Test()
	public void ValidationSuccessFor_MRomanCharacterSubtractedAnyChar() {
		// given
		RulesValidator rulesValidator = new RulesValidator();
		assertTrue(validate(rulesValidator, "MMI"));
		assertTrue(validate(rulesValidator, "MMX"));
		assertTrue(validate(rulesValidator, "MMC"));
		assertTrue(validate(rulesValidator, "MMV"));
		assertTrue(validate(rulesValidator, "MMD"));
	}

	private boolean validate(RulesValidator rulesValidator, String expression) {
		try {
			return rulesValidator.validate(expression);
		} catch (ValidatorException ve) {
			return false;
		}
	}
}
