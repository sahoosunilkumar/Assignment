package com.thoughtworks.merchantsguide.utility;

import java.util.Iterator;
import java.util.Set;

public class StringUtils {
	private StringUtils() {

	}

	/**
	 * converts set of string to delimiter separated string
	 * 
	 * @param set
	 * @param delimiter
	 * @return
	 */
	public static String convertToString(Set<String> set, String delimiter) {
		if ((set == null) || set.isEmpty()) {
			return "";
		}
		Iterator<String> iterator = set.iterator();
		StringBuffer sb = new StringBuffer();
		boolean isFirstElement = true;
		while (iterator.hasNext()) {
			if (isFirstElement) {
				sb.append(iterator.next());
			} else {
				sb.append(delimiter).append(iterator.next());
			}
			isFirstElement = false;
		}

		return sb.toString();
	}

	/**
	 * returns word from at specified index
	 * 
	 * @param string
	 * @param delimeter
	 * @param index
	 * @return
	 */
	public static String stringAt(String string, String delimeter, int index) {
		return string.split(delimeter)[index];
	}

	/**
	 * checks whether the specified array contains the match char
	 * 
	 * @param charArr
	 * @param match
	 * @return
	 */
	public static boolean contains(char[] charArr, char match) {
		for (char ch : charArr) {
			if (ch == match) {
				return true;
			}
		}
		return false;
	}
}
