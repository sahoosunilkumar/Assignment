package com.thoughtworks.merchantsguide.parser;

import com.thoughtworks.merchantsguide.error.ParserException;

public interface IParser {
	String parse(String inputData) throws ParserException;

	void setNextParser(IParser inputParser);
}
