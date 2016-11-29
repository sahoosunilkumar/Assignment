package com.thoughtworks.merchantsguide.utility;

import java.util.Iterator;
import java.util.Set;

public class TextUtils {
	private TextUtils(){
		
	}

	public static String convertToString(Set<String> set, String delimeter) {
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
				sb.append(delimeter).append(iterator.next());
			}
			isFirstElement = false;
		}

		return sb.toString();
	}

	public static String stringAt(String string, String delimeter, int index) {
		return string.split(delimeter)[index];
	}

	public static boolean contains(char[] charArr, char match) {
		for (char ch : charArr) {
			if (ch == match) {
				return true;
			}
		}
		return false;
	}
}
