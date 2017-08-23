package test.thoughtworks.merchantsguide.expression;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import com.thoughtworks.merchantsguide.expression.ExpressionEvaluator;

public class RomanNumberParserTest {
	@Test()
	public void ValidationSuccessFor_AssetGlobIsI() {
		// given
		ExpressionEvaluator inputDataParser = ExpressionEvaluator.getInstance();
		inputDataParser.evaluate("glob is I");
		assertTrue((ExpressionEvaluator.getInstance().getDatabase()
				.getValue("glob") == ExpressionEvaluator.getInstance()
						.getDatabase().getValue("I")));
	}

	@Test()
	public void ValidationFailureFor_AssetGlobWithV() {
		ExpressionEvaluator inputDataParser = ExpressionEvaluator.getInstance();
		inputDataParser.evaluate("glob is I");
		assertFalse((ExpressionEvaluator.getInstance().getDatabase()
				.getValue("glob") == ExpressionEvaluator.getInstance()
						.getDatabase().getValue("V")));
	}

	@Test()
	public void ValidationSuccessFor_AssetProkIsV() {
		// given
		ExpressionEvaluator inputDataParser = ExpressionEvaluator.getInstance();
		inputDataParser.evaluate("prok is V");
		assertTrue((ExpressionEvaluator.getInstance().getDatabase()
				.getValue("prok") == ExpressionEvaluator.getInstance()
						.getDatabase().getValue("V")));
	}

	@Test()
	public void ValidationSuccessFor_AssetPish() {
		// given
		ExpressionEvaluator inputDataParser = ExpressionEvaluator.getInstance();
		inputDataParser.evaluate("pish is X");
		assertTrue((ExpressionEvaluator.getInstance().getDatabase()
				.getValue("pish") == ExpressionEvaluator.getInstance()
						.getDatabase().getValue("X")));
	}

	@Test()
	public void ValidationSuccessFor_AssetTegj() {
		// given
		ExpressionEvaluator inputDataParser = ExpressionEvaluator.getInstance();
		inputDataParser.evaluate("tegj is L");
		assertTrue((ExpressionEvaluator.getInstance().getDatabase()
				.getValue("tegj") == ExpressionEvaluator.getInstance()
						.getDatabase().getValue("L")));
	}

	@Test()
	public void ValidationFailureFor_AssetTegjWithX() {
		// given
		ExpressionEvaluator inputDataParser = ExpressionEvaluator.getInstance();
		inputDataParser.evaluate("tegj is L");
		assertFalse((ExpressionEvaluator.getInstance().getDatabase()
				.getValue("tegj") == ExpressionEvaluator.getInstance()
						.getDatabase().getValue("X")));
	}

}
