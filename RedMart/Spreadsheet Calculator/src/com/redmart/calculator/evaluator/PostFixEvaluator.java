package com.redmart.calculator.evaluator;

import java.util.LinkedHashSet;
import java.util.Set;
import java.util.Stack;

import com.redmart.calculator.Operators;
import com.redmart.calculator.exception.CircularDependencyException;
import com.redmart.calculator.exception.InvalidNumberException;
import com.redmart.calculator.spreadsheet.Cell;
import com.redmart.calculator.spreadsheet.SpreadSheet;

/**
 * evaluates postfix notation
 * @author sunilkumarsahoo
 *
 */
public class PostFixEvaluator extends Evaluator{

    @Override
    public double evaluate(SpreadSheet spreadSheet, Cell cell,
			Set<Cell> stack) throws CircularDependencyException, InvalidNumberException {
		if (stack == null) {
			stack = new LinkedHashSet<Cell>();
		}

		if (cell.isEvaluated()) {
			// no need to do anything. only return the value
		} else if (!cell.isEvaluated()
				&& !stack.contains(cell)) {
			stack.add(cell);

			String[] fields = cell.getContent().split(" ");

			Stack<Double> argumentStack = new Stack<Double>();

			for (int i = 0; i < fields.length; i++) {
				String fieldInfo = fields[i];

				if(Operators.isValidOperator(fieldInfo)){
					processToken(fieldInfo, argumentStack);
				}else if (isNumeric(fieldInfo))
					argumentStack.push(Double.parseDouble(fieldInfo));
				else {
					Cell anotherCell = spreadSheet.getCell(fieldInfo);
					argumentStack.push(
							evaluate(spreadSheet, anotherCell, stack));
				}
			}

			cell.setValue(argumentStack.pop());
			cell.setEvaluated(true);

		} else {
			throw new CircularDependencyException("Loop Found while evaluating : "+cell.getContent());
		}

		return cell.getValue();
	}
	
	/**
	 * evaluates value of statement from stack
	 * @param token
	 * @param argumentStack
	 * @throws InvalidNumberException
	 */
    private void processToken(String token, Stack<Double> argumentStack) throws InvalidNumberException {
    	double result;
        if(Operators.isValidOperator(token)){
        	checkArgumentsSize(argumentStack);
        	result = Operators.get(token).evaluate(argumentStack);
        }else{
        	result = Double.parseDouble(token);
        	argumentStack.push(Double.parseDouble(token));
        }
        argumentStack.push(result);
        
    }
 
    /**
     * checks whether the operation have atleast two operands
     * @param argumentStack
     * @throws InvalidNumberException
     */
    private void checkArgumentsSize(Stack<Double> argumentStack) throws InvalidNumberException {
        if (argumentStack.size() < 2) {
            throw new InvalidNumberException("Not enough parameters for operation");
        }
    }
}
