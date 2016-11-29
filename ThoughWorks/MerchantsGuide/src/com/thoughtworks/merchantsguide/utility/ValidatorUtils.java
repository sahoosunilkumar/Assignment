package com.thoughtworks.merchantsguide.utility;

import com.thoughtworks.merchantsguide.database.Data;

public class ValidatorUtils {
	private ValidatorUtils(){
		
	}
	public static boolean validateSuccession(String expression,
			char romanNumber, int maxOccurence) {
		int counter = 0;
		for (int i = 0; i < expression.length(); i++) {
			if (romanNumber == expression.charAt(i)) {
				counter++;
			} else {
				if(counter > maxOccurence){
					if(Data.getValue(String.valueOf(romanNumber))> Data.getValue(String.valueOf(expression.charAt(i-1)))){
						return true;
					}else{
						return false;
					}
				}
				counter = 0;
			}
		}
		return counter <= maxOccurence;
	}

	public static boolean validateSubtraction(String expression, char romanChar,
			char[] validSubtractor) {
		char prevChar;
		boolean isValid = true;
		if (expression.length() <= 1) {
			return isValid;
		}
		int startIndex = expression.indexOf(romanChar);

		prevChar = expression.charAt(startIndex <= 0 ? 0 : startIndex);
		if (startIndex == -1) {
			return isValid;
		}
		float value = Data.getValue(String.valueOf(romanChar));
		if (startIndex > 0) {
			char currChar = expression.charAt(startIndex - 1);
			isValid = (Data.getValue(String.valueOf(currChar)) >= value);
		}
		for (int i = startIndex + 1; i < expression.length(); i++) {
			char currChar = expression.charAt(i);
			if (prevChar == romanChar) {
				isValid = (Data.getValue(String.valueOf(currChar)) <= value)
						|| TextUtils.contains(validSubtractor, currChar);
				if (!isValid) {
					break;
				}
			}
			prevChar = currChar;
		}
		return isValid;
	}

}
