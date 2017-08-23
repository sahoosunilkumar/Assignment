package com.thoughtworks.merchantsguide.parser;

/**
 * base class of all parser
 * 
 * @author sunilkumarsahoo
 *
 */
public abstract class ParserAbs implements IParser {
	public IParser inputParser;

	@Override
	public void setNextParser(IParser inputParser) {
		this.inputParser = inputParser;
	}

}
