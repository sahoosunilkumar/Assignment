package com.thoughtworks.merchantsguide.database;

import java.util.HashMap;
import java.util.Set;

import com.thoughtworks.merchantsguide.error.IllegalLocaleException;
import com.thoughtworks.merchantsguide.error.IllegalRomanNumberException;

public class Data {

	private static final HashMap<String, Integer> romanNumberValueMapping = new HashMap<>();
	private static final HashMap<String, String> localeRomanNumberMapping = new HashMap<>();
	private static final HashMap<String, Float> localeValueMapping = new HashMap<>();

	static {
		romanNumberValueMapping.put("I", 1);
		romanNumberValueMapping.put("V", 5);
		romanNumberValueMapping.put("X", 10);
		romanNumberValueMapping.put("L", 50);
		romanNumberValueMapping.put("C", 100);
		romanNumberValueMapping.put("D", 500);
		romanNumberValueMapping.put("M", 1000);
	}

	public static void addLocaleToRomanNumber(String locale, String roman)
			throws IllegalRomanNumberException {
		if (!isValidRomanNumber(roman)) {
			throw new IllegalRomanNumberException();
		}
		localeRomanNumberMapping.put(locale, roman);
	}

	public static boolean isValidRomanNumber(String romanNumber) {
		return romanNumberValueMapping.containsKey(romanNumber);
	}

	public static Set<String> getRomanNumbers() {
		return romanNumberValueMapping.keySet();
	}

	public static Set<String> getLocale() {
		return localeRomanNumberMapping.keySet();
	}

	public static Set<String> getLocaleFromValueMapping() {
		return localeValueMapping.keySet();
	}

	public static void addValueToLocale(String locale, float value)
			throws IllegalLocaleException {
		localeValueMapping.put(locale, value);
	}

	public static float getValue(String inputData) {
		if (localeValueMapping.containsKey(inputData)) {
			return localeValueMapping.get(inputData);
		} else if (romanNumberValueMapping.containsKey(inputData)) {
			return romanNumberValueMapping.get(inputData);
		} else {
			return romanNumberValueMapping
					.get(localeRomanNumberMapping.get(inputData));
		}
	}

	public static boolean isLocale(String inputData) {
		return (localeValueMapping.containsKey(inputData));
	}

	public static String getRomanNumber(String locale) {
		if (localeRomanNumberMapping.containsKey(locale)) {
			return localeRomanNumberMapping.get(locale);
		} else {
			return "";
		}
	}
}
