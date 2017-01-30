package com.redmart.calculator;

import com.redmart.calculator.evaluator.Evaluator;
import com.redmart.calculator.evaluator.PostFixEvaluator;
import com.redmart.calculator.exception.CircularDependencyException;
import com.redmart.calculator.exception.InvalidNumberException;
import com.redmart.calculator.reader.Reader;
import com.redmart.calculator.reader.SpreadsheetReader;
import com.redmart.calculator.spreadsheet.SpreadSheet;
import com.redmart.calculator.writer.ConsoleWriter;
import com.redmart.calculator.writer.Writer;

public class Calculator {

	// reader to read data from
	private Reader<SpreadSheet> reader = null;
	// spreadsheet instance
	private SpreadSheet spreadSheet = null;
	// evaluator to evaluate value of each cell
	private Evaluator evaluator = null;
	// writer to write the result
	private Writer writer = null;

	public static void main(String[] args) {
		Calculator calculator = new Calculator();
		calculator.execute();
	}

	private void execute() {
		init();
		try {
			reader.read(spreadSheet);
			System.out.println("Evaluating");
			for (int i = 0; i < spreadSheet.getNoOfRows(); i++) {
				for (int j = 0; j < spreadSheet.getNoOfColumns(); j++) {
					evaluator.evaluate(spreadSheet,
							spreadSheet.getCells()[i][j], null);
				}
			}
			System.out.println("Printing");

			writer.write(spreadSheet);
		} catch (InvalidNumberException e) {
			System.out.println("Error occurrend in main:" + e.getMessage());
			System.exit(1);
		} catch (CircularDependencyException circularException) {
			System.out.println(circularException.getMessage());
			System.exit(1);
		} catch(Exception ex){
			System.out.println("Other unhandled exceptions : "+ex.getMessage());
			System.exit(1);
		}
	}

	/**
	 * initialize readers, writers
	 */
	private void init() {
		// initialize reader
		reader = new SpreadsheetReader();
		// initialize spreadsheet
		spreadSheet = new SpreadSheet();
		// initialize evaluator
		evaluator = new PostFixEvaluator();
		// initialize writer
		writer = new ConsoleWriter();
	}

}
