package org.aming.dy.utils;

/**
 * @date daming
 * @create 2018-06-08 23:48
 **/
@FunctionalInterface
public interface StringFormatter {

	String format(String message, Object...params);

	default String format(String message) {
		return message;
	}

}
