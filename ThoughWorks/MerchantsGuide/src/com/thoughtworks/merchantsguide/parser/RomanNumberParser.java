package com.thoughtworks.merchantsguide.parser;

import com.thoughtworks.merchantsguide.database.Data;
import com.thoughtworks.merchantsguide.utility.TextUtils;

public class RomanNumberParser implements IParser {
	IParser inputParser;

	@Override
	public String parse(String inputData) {
		if (inputData.matches("\\S+\\s(is)\\s\\S+")) {
			String locale = TextUtils.stringAt(inputData, " ", 0);
			String romanNumber = TextUtils.stringAt(inputData, " ", 2);
			Data.addLocaleToRomanNumber(locale, romanNumber);
			this.inputParser = null;
		}
		if (this.inputParser != null) {
			return inputParser.parse(inputData);
		} else {
			return "";
		}

	}

	@Override
	public void setNextParser(IParser inputParser) {
		this.inputParser = inputParser;

	}

}
