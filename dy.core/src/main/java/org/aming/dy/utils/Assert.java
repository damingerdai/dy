package org.aming.dy.utils;

import java.util.Objects;

public class Assert {

	public static void nonNull(Object object, String message) {
		if (Objects.isNull(object)) {
			throwIllegalArgumentException(message);
		}
	}

	public static void nonNull(Object object, String message, Object[] params) {
		nonNull(object, message, params, String::format);
	}

	public static void nonNull(Object object, String message, Object[] params, StringFormatter formatter) {
		if (Objects.isNull(object)) {
			throwIllegalArgumentException(formatter.format(message, params));
		}
	}

	public static void isNull(Object object, String messge) {
		if (Objects.nonNull(object)) {
			throwIllegalArgumentException(messge);
		}
	}

	public static void isNull(Object object, String message, Object[] params) {
		isNull(object, message, params, String::format);
	}

	public static void isNull(Object object, String message, Object[] params, StringFormatter formatter) {
		if (Objects.nonNull(object)) {
			throwIllegalArgumentException(formatter.format(message, params));
		}
	}

	public static void throwIllegalArgumentException(String message) {
		throw new IllegalArgumentException(message);
	}

}
