package com.redmart.calculator.writer;

import com.redmart.calculator.spreadsheet.SpreadSheet;
/**
 * Writer class to write information on console
 * @author sunilkumarsahoo
 *
 */
public class ConsoleWriter extends Writer{

	@Override
	public void write(SpreadSheet spreadSheet) {
		System.out.println(spreadSheet.getNoOfColumns()+" "+spreadSheet.getNoOfColumns());
		for (int i = 0; i < spreadSheet.getNoOfRows(); i++) {
			for (int j = 0; j < spreadSheet.getNoOfColumns(); j++) {
				System.out.printf("%.5f%n",
						spreadSheet.getCells()[i][j].getValue());
			}
		}
	}

}
