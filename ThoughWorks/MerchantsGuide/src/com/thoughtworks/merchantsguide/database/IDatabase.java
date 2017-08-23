package com.thoughtworks.merchantsguide.database;

import java.util.Set;

import com.thoughtworks.merchantsguide.error.IllegalLocaleException;
import com.thoughtworks.merchantsguide.error.IllegalRomanNumberException;

public interface IDatabase {
	/**
	 * adds LocaleToRomanNumber
	 * 
	 * @param locale
	 * @param roman
	 * @throws IllegalRomanNumberException
	 */
	public void addLocaleToRomanNumber(String locale, String roman)
			throws IllegalRomanNumberException;

	/**
	 * checks whether input number is valid roman number
	 * 
	 * @param romanNumber
	 * @return
	 */
	public boolean isValidRomanNumber(String romanNumber);

	/**
	 * returns all roman numbers
	 * 
	 * @return
	 */
	public Set<String> getRomanNumbers();

	/**
	 * returns all locale
	 * 
	 * @return
	 */
	public Set<String> getLocale();

	/**
	 * returns store locale
	 * 
	 * @return
	 */
	public Set<String> getLocaleFromValueMapping();

	/**
	 * adds value to locale
	 * 
	 * @param locale
	 * @param value
	 * @throws IllegalLocaleException
	 */
	public void addValueToLocale(String locale, float value)
			throws IllegalLocaleException;

	/**
	 * returns the value of provided data
	 * 
	 * @param inputData
	 * @return
	 */
	public float getValue(String inputData);

	/**
	 * checks whether input string is locale
	 * 
	 * @param inputData
	 * @return
	 */
	public boolean isLocale(String inputData);

	/**
	 * returns roman number from locale
	 * 
	 * @param locale
	 * @return
	 */
	public String getRomanNumber(String locale);
}
