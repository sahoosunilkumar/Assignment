package test.thoughtworks.merchantsguide.expression;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.thoughtworks.merchantsguide.expression.ExpressionEvaluator;

public class CreditParserTest {
	@Test()
	public void ValidationSuccesFor_CreditGlobGlobSilver() {
		// given
		ExpressionEvaluator parser = ExpressionEvaluator.getInstance();
		parser.evaluate("glob is I");
		parser.evaluate("prok is V");
		parser.evaluate("pish is X");
		parser.evaluate("tegj is L");
		assertTrue(parser.evaluate("glob glob Silver is 34 Credits").equals(""));
	}
	@Test()
	public void ValidationSuccesFor_CreditGlobGlobGlobGlobSilver() {
		// given
		ExpressionEvaluator parser = ExpressionEvaluator.getInstance();
		parser.evaluate("glob is I");
		parser.evaluate("prok is V");
		parser.evaluate("pish is X");
		parser.evaluate("tegj is L");
		assertFalse(parser.evaluate("glob glob glob glob Silver is 34 Credits").equals(""));
	}
	
	@Test()
	public void ValidationSuccesFor_CreditGlobProkGold() {
		// given
		ExpressionEvaluator parser = ExpressionEvaluator.getInstance();
		parser.evaluate("glob is I");
		parser.evaluate("prok is V");
		parser.evaluate("pish is X");
		parser.evaluate("tegj is L");
		assertTrue(parser.evaluate("glob prok Gold is 57800 Credits").equals(""));
	}
	
	@Test()
	public void ValidationSuccesFor_CreditPishPishIron() {
		// given
		ExpressionEvaluator parser = ExpressionEvaluator.getInstance();
		parser.evaluate("glob is I");
		parser.evaluate("prok is V");
		parser.evaluate("pish is X");
		parser.evaluate("tegj is L");
		assertTrue(parser.evaluate("pish pish Iron is 3910 Credits").equals(""));
	}	
}
