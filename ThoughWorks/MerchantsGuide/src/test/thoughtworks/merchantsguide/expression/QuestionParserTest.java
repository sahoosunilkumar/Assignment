package test.thoughtworks.merchantsguide.expression;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.thoughtworks.merchantsguide.expression.ExpressionEvaluator;

public class QuestionParserTest {
	@Test()
	public void ValidationSuccesFor_QuestionPishTegjGlobGlob() {
		String actualResult = getResult("how much is pish tegj glob glob ?");
		String expectedResult = "pish tegj glob glob is 42";
		assertTrue(actualResult.equals(expectedResult));
	}

	@Test()
	public void ValidationFailureFor_QuestionPishTegjGlobGlob() {
		String actualResult = getResult(
				"how much is pish tegj glob glob glob glob ?");
		String expectedResult = "pish tegj glob glob glob glob is";
		assertFalse(actualResult.contains(expectedResult));
	}

	@Test()
	public void ValidationSuccesFor_QuestionGlobProkSilver() {
		String actualResult = getResult(
				"how many Credits is glob prok Silver ?");
		String expectedResult = "glob prok Silver is 68 Credits";
		assertTrue(actualResult.equals(expectedResult));
	}

	@Test()
	public void ValidationSuccesFor_QuestionGlobProkGold() {
		String actualResult = getResult("how many Credits is glob prok Gold ?");
		String expectedResult = "glob prok Gold is 57800 Credits";
		assertTrue(actualResult.equals(expectedResult));
	}

	@Test()
	public void ValidationSuccesFor_QuestionGlobProkIron() {
		String actualResult = getResult("how many Credits is glob prok Iron ?");
		String expectedResult = "glob prok Iron is 782 Credits";
		assertTrue(actualResult.equals(expectedResult));
	}

	@Test()
	public void ValidationSuccesFor_QuestionInvalid() {
		String actualResult = getResult(
				"how much wood could a woodchuck chuck if a woodchuck could chuck wood ?");
		String expectedResult = "I have no idea what you are talking about";
		assertTrue(actualResult.equals(expectedResult));
	}

	private String getResult(String question) {
		ExpressionEvaluator parser = ExpressionEvaluator.getInstance();
		parser.evaluate("glob is I");
		parser.evaluate("prok is V");
		parser.evaluate("pish is X");
		parser.evaluate("tegj is L");
		parser.evaluate("glob glob Silver is 34 Credits");
		parser.evaluate("glob prok Gold is 57800 Credits");
		parser.evaluate("pish pish Iron is 3910 Credits");

		return parser.evaluate(question);
	}
}
