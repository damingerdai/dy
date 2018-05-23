package org.aming.dy.ms.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Description: 字符串工具类
 * User: daming
 * Date: 2018-05-21
 * Time: 20:30
 * Description:
 */
public class StringUtils extends org.apache.commons.lang3.StringUtils {

	public static boolean isRegMatch(String str, String regEx) {
		if (isBlank(str) || isBlank(regEx)) {
			return false;
		}
		Pattern pattern = Pattern.compile(regEx);
		Matcher matcher = pattern.matcher(str);
		return matcher.matches();
	}
}
