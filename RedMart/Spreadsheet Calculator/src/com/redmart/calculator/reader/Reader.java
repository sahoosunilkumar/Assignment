package com.redmart.calculator.reader;

/**
 * Base class for all readers
 * @author sunilkumarsahoo
 *
 * @param <T>
 */
abstract public class Reader<T> {
	abstract public void read(T t);
}
