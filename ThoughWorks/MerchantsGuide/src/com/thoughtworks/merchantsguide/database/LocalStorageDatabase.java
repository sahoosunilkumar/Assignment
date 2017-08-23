package com.thoughtworks.merchantsguide.database;

import java.util.HashMap;
import java.util.Set;

import com.thoughtworks.merchantsguide.error.IllegalLocaleException;
import com.thoughtworks.merchantsguide.error.IllegalRomanNumberException;

/**
 * stores the value and numbers
 * 
 * @author sunilkumarsahoo
 *
 */
public class LocalStorageDatabase implements IDatabase {

	private final HashMap<String, Integer> romanNumberValueMapping = new HashMap<>();
	private final HashMap<String, String> localeRomanNumberMapping = new HashMap<>();
	private final HashMap<String, Float> localeValueMapping = new HashMap<>();

	public LocalStorageDatabase() {
		initialize();
	}

	private void initialize() {
		romanNumberValueMapping.put("I", 1);
		romanNumberValueMapping.put("V", 5);
		romanNumberValueMapping.put("X", 10);
		romanNumberValueMapping.put("L", 50);
		romanNumberValueMapping.put("C", 100);
		romanNumberValueMapping.put("D", 500);
		romanNumberValueMapping.put("M", 1000);
	}

	public void addLocaleToRomanNumber(String locale, String roman)
			throws IllegalRomanNumberException {
		if (!isValidRomanNumber(roman)) {
			throw new IllegalRomanNumberException();
		}
		localeRomanNumberMapping.put(locale, roman);
	}

	public boolean isValidRomanNumber(String romanNumber) {
		return romanNumberValueMapping.containsKey(romanNumber);
	}

	public Set<String> getRomanNumbers() {
		return romanNumberValueMapping.keySet();
	}

	public Set<String> getLocale() {
		return localeRomanNumberMapping.keySet();
	}

	public Set<String> getLocaleFromValueMapping() {
		return localeValueMapping.keySet();
	}

	public void addValueToLocale(String locale, float value)
			throws IllegalLocaleException {
		localeValueMapping.put(locale, value);
	}

	public float getValue(String inputData) {
		if (localeValueMapping.containsKey(inputData)) {
			return localeValueMapping.get(inputData);
		} else if (romanNumberValueMapping.containsKey(inputData)) {
			return romanNumberValueMapping.get(inputData);
		} else {
			return romanNumberValueMapping
					.get(localeRomanNumberMapping.get(inputData));
		}
	}

	public boolean isLocale(String inputData) {
		return (localeValueMapping.containsKey(inputData));
	}

	public String getRomanNumber(String locale) {
		if (localeRomanNumberMapping.containsKey(locale)) {
			return localeRomanNumberMapping.get(locale);
		} else {
			return "";
		}
	}
}
