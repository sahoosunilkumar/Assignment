package com.redmart.calculator.spreadsheet;

import com.redmart.calculator.exception.InvalidNumberException;

/**
 * class contains all information related to spread sheet ie noOf rows, columns, cells
 * @author sunilkumarsahoo
 *
 */
public class SpreadSheet {
	private static final char COLUMN_NAME_START_FROM = 'A';
	private Cell[][] inputArr;
	private int noOfColumns;
	private int noOfRows;
	public void initialize(int noOfColumns, int noOfRows){
		this.noOfColumns = noOfColumns;
		this.noOfRows = noOfRows;
		inputArr = new Cell[this.noOfRows][this.noOfColumns];
	}
	/**
	 * total count
	 * @return
	 */
	public int readCount(){
		return noOfColumns*noOfRows;
	}
	/**
	 * retrieves number of rows
	 * @return
	 */
	public int getNoOfRows(){
		return noOfRows;
	}
	/**
	 * retrieves number of columns
	 * @return
	 */
	public int getNoOfColumns(){
		return noOfColumns;
	}
	/**
	 * retrieves all cells from spreadsheet
	 * @return
	 */
	public Cell[][] getCells(){
		return inputArr;
	}
	/**
	 * retrieves cell from info
	 * @param cellInfo
	 * @return
	 * @throws InvalidNumberException 
	 */
	public Cell getCell(String cellInfo) throws InvalidNumberException {
		try {
			int x = (int) cellInfo.charAt(0) % COLUMN_NAME_START_FROM;
			int y = Integer.parseInt(cellInfo.substring(1, cellInfo.length())) - 1;
			return getCells()[x][y];
		} catch (NumberFormatException e) {
			throw new InvalidNumberException("Data format error occurred while evaluating Cell" + cellInfo);
		}
	}
}
