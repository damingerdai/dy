package org.aming.dy.support;

/**
 *
 * @author aming
 * @create 2018-04-04 13:29
 **/
public class DynamicDataSourceContextHolder {

	private static final String DEFAULT_DATA_SOURCE = "main";

	private static final ThreadLocal<String> contextHolder = ThreadLocal.withInitial(() -> DEFAULT_DATA_SOURCE);

	public static void clearDataSource() {
		contextHolder.remove();
	}

	public static void setDataSource(String dataSource) {
		contextHolder.set(dataSource);
	}

	public static String getDataSource() {
		return contextHolder.get();
	}

}
