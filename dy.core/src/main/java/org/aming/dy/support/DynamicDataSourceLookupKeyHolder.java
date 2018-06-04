package org.aming.dy.support;


/**
 * Description:
 * User: daming
 * Date: 2018-06-03
 * Time: 21:57
 */
public class DynamicDataSourceLookupKeyHolder {

	private static final ThreadLocal<String> contextHolder = ThreadLocal.withInitial(String::new);

	public static void setLookupKey(String loopKey) {
		contextHolder.set(loopKey);
	}

	public static String getLookupKey() {
		return contextHolder.get();
	}

	public static void clear() {
		contextHolder.remove();
	}

}
