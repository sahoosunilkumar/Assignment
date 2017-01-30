package com.redmart.calculator.spreadsheet;
/**
 * Class contains cell information
 * @author sunilkumarsahoo
 *
 */
public class Cell{
	//value of cell
	private Double value;
	//Evaluation status
	private boolean evaluated;
	//user entered content
	private String content;
	
	public Cell(String cellContent){
		this.setContent(cellContent);
		this.evaluated = false;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public boolean isEvaluated() {
		return evaluated;
	}

	public void setEvaluated(boolean evaluated) {
		this.evaluated = evaluated;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}