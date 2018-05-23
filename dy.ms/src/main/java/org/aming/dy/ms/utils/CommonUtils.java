package org.aming.dy.ms.utils;

import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Random;
import java.util.Set;

/**
 * @athur aming
 * @date 2018-05-23 18:13
 */
public class CommonUtils {

	private static Random random = new Random();

	/**
	 * checks empty.
	 * @param  obj
	 * @return Boolean
	 **/
	public final static Boolean isBlank(Object obj) {
		if (null == obj) {
			return true;
		}
		boolean flag = false;
		if (obj instanceof String) {
			flag = obj.toString().trim().length() <= 0;
		} else if (obj instanceof Object[]) {
			flag = ((Object[]) obj).length == 0;
		} else if (obj instanceof Collection) {
			flag = ((Collection<?>) obj).isEmpty();
		} else if (obj instanceof Map) {
			flag = ((Map<?, ?>) obj).isEmpty();
		} else if (obj instanceof Enumeration) {
			flag = !((Enumeration<?>) obj).hasMoreElements();
		} else if (obj instanceof Iterator) {
			flag = !((Iterator<?>) obj).hasNext();
		} else if (obj instanceof List) {
			flag = ((List<?>) obj).size() <= 0;
		} else if (obj instanceof Set) {
			flag = ((Set<?>) obj).size() <= 0;
		}
		return flag;
	}

	public final static Boolean isNotBlank(Object obj) {
		return !isBlank(obj);
	}

	public static boolean isEmpty(String str) {
		return str == null || "".equals(str.trim());
	}

	public static boolean isNotEmpty(String str) {
		return !isEmpty(str);
	}

	public static boolean isEmpty(List<?> list) {
		return list == null || list.size() == 0;
	}

	public static boolean isNotEmpty(List<?> list) {
		return !isEmpty(list);
	}

	public static boolean isEmpty(Object[] array) {
		return array == null || array.length == 0;
	}

	public static <T> boolean isEmpty2(T[] array) {
		return array == null || array.length == 0;
	}

	public static <T> boolean isNotEmpty(T[] array) {
		return !isEmpty2(array);
	}

	public static boolean isEmpty(Set<?> set) {
		return Objects.isNull(set) || set.isEmpty();
	}

	public static boolean isNotEmpty(Set<?> set) {
		return !isEmpty(set);
	}

	public static boolean isEmpty(Map<?, ?> map) {
		return Objects.isNull(map) || map.isEmpty();
	}

	public static boolean isNotEmpty(Map<?, ?> map) {
		return !isEmpty(map);
	}

	public static String getIpAddr(HttpServletRequest request) {
		String ipAddress = request.getHeader("x-forwarded-for");
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
			ipAddress = request.getRemoteAddr();
			if (ipAddress.equals("127.0.0.1") || ipAddress.equals("0:0:0:0:0:0:0:1")) {
				InetAddress inet = null;
				try {
					inet = InetAddress.getLocalHost();
				} catch (UnknownHostException e) {
					e.printStackTrace();
				}
				ipAddress = inet.getHostAddress();
			}
		}

		if (ipAddress != null && ipAddress.length() > 15) {
			if (ipAddress.indexOf(",") > 0) {
				ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
			}
		}
		return ipAddress;
	}

	public static String randomNumbers(int length) {
		StringBuffer sBuffer = new StringBuffer();
		for (int i = 0; i < length; i++) {
			sBuffer.append(random.nextInt(10));
		}
		return sBuffer.toString();
	}

	public static boolean isNumeric(String str) {
		for (int i = 0; i < str.length(); i++) {
			if (!Character.isDigit(str.charAt(i))) {
				return false;
			}
		}
		return true;
	}
}
