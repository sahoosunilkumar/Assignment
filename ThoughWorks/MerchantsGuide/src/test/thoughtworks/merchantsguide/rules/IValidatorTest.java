package test.thoughtworks.merchantsguide.rules;

import org.junit.Test;

import com.thoughtworks.merchantsguide.error.ValidatorException;
import com.thoughtworks.merchantsguide.rules.RulesValidator;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

public class IValidatorTest {

	@Test()
	public void ValidationSuccessFor_IRomanCharacterThreeSuccession() {
		// given
		RulesValidator rulesValidator = new RulesValidator();
		assertTrue(rulesValidator.validate("III"));
		;
	}

	@Test()
	public void ValidationFailureFor_IRomanCharacterFourSuccession() {
		// given
		RulesValidator rulesValidator = new RulesValidator();
		assertFalse(validate(rulesValidator, "IIII"));
	}

	@Test()
	public void ValidationSuccessFor_IRomanCharacterFourSuccessionWithOtherCharacterInBetween() {
		// given
		RulesValidator rulesValidator = new RulesValidator();
		assertTrue(validate(rulesValidator, "IIIXI"));
	}

	@Test()
	public void ValidationSuccessFor_IRomanCharacterSubtractedByV() {
		// given
		RulesValidator rulesValidator = new RulesValidator();
		assertTrue(validate(rulesValidator, "IIV"));
	}

	@Test()
	public void ValidationFailureFor_IRomanCharacterSubtractedByOther() {
		// given
		RulesValidator rulesValidator = new RulesValidator();
		assertFalse(validate(rulesValidator, "IID"));
	}

	private boolean validate(RulesValidator rulesValidator, String expression) {
		try {
			return rulesValidator.validate(expression);
		} catch (ValidatorException ve) {
			return false;
		}
	}
}
