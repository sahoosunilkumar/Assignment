package com.redmart.calculator.reader;

import java.util.Scanner;

import com.redmart.calculator.spreadsheet.Cell;
import com.redmart.calculator.spreadsheet.SpreadSheet;

/**
 * Reader class to read Spreadsheet
 * @author sunilkumarsahoo
 *
 */
public class SpreadsheetReader extends Reader<SpreadSheet>{

	@Override
	public void read(SpreadSheet spreadsheet) {
		Scanner scanner = new Scanner(System.in);
		try{
			String[] inputMatrix = scanner.nextLine().split(" ");
			spreadsheet.initialize(Integer.valueOf(inputMatrix[0]), Integer.valueOf(inputMatrix[1]));
	        for(int rowCounter = 0; rowCounter<spreadsheet.getNoOfRows();rowCounter++){
	        	for(int columnCounter = 0; columnCounter<spreadsheet.getNoOfColumns();columnCounter++){
	        		spreadsheet.getCells()[rowCounter][columnCounter] = new Cell(scanner.nextLine());
	            }
	        }
		}finally{
			scanner.close();
		}
	}

}
