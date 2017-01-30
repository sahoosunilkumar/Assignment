package com.redmart.calculator.evaluator;

import java.util.Set;

import com.redmart.calculator.exception.CircularDependencyException;
import com.redmart.calculator.exception.InvalidNumberException;
import com.redmart.calculator.spreadsheet.Cell;
import com.redmart.calculator.spreadsheet.SpreadSheet;
/**
 * Base class responsible for evaluating statement
 * 
 * @author sunilkumarsahoo
 *
 */
abstract public class Evaluator {
	/**
	 * evaluate particular cell
	 * @param spreadSheet
	 * @param cell
	 * @param currentEvaluationStack
	 * @return
	 * @throws CircularDependencyException
	 * @throws InvalidNumberException
	 */
	abstract public double evaluate(SpreadSheet spreadSheet, Cell cell,
			Set<Cell> currentEvaluationStack) throws CircularDependencyException, InvalidNumberException;

	/**
	 * checks whether the input string is number
	 * @param input
	 * @return
	 */
	protected boolean isNumeric(String input) {
		try {
			Double.parseDouble(input);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
}
