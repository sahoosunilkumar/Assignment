package test.thoughtworks.merchantsguide.expression;

import static org.junit.Assert.*;

import org.junit.Test;

import com.thoughtworks.merchantsguide.database.Data;
import com.thoughtworks.merchantsguide.expression.ExpressionEvaluator;

public class RomanNumberParserTest {
	@Test()
	public void ValidationSuccessFor_AssetGlobIsI() {
		// given
		ExpressionEvaluator inputDataParser = ExpressionEvaluator.getInstance();
		inputDataParser.evaluate("glob is I");
		assertTrue((Data.getValue("glob")==Data.getValue("I")));
	}
	
	@Test()
	public void ValidationFailureFor_AssetGlobWithV() {
		ExpressionEvaluator inputDataParser = ExpressionEvaluator.getInstance();
		inputDataParser.evaluate("glob is I");
		assertFalse((Data.getValue("glob")==Data.getValue("V")));
	}
	
	@Test()
	public void ValidationSuccessFor_AssetProkIsV() {
		// given
		ExpressionEvaluator inputDataParser = ExpressionEvaluator.getInstance();
		inputDataParser.evaluate("prok is V");
		assertTrue((Data.getValue("prok")==Data.getValue("V")));
	}
	
	@Test()
	public void ValidationSuccessFor_AssetPish() {
		// given
		ExpressionEvaluator inputDataParser = ExpressionEvaluator.getInstance();
		inputDataParser.evaluate("pish is X");
		assertTrue((Data.getValue("pish")==Data.getValue("X")));
	}
	
	@Test()
	public void ValidationSuccessFor_AssetTegj() {
		// given
		ExpressionEvaluator inputDataParser = ExpressionEvaluator.getInstance();
		inputDataParser.evaluate("tegj is L");
		assertTrue((Data.getValue("tegj")==Data.getValue("L")));
	}
	
	@Test()
	public void ValidationFailureFor_AssetTegjWithX() {
		// given
		ExpressionEvaluator inputDataParser = ExpressionEvaluator.getInstance();
		inputDataParser.evaluate("tegj is L");
		assertFalse((Data.getValue("tegj")==Data.getValue("X")));
	}
	
}
