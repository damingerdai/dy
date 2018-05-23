package org.aming.dy.ms.jdbc.support;

/**
 * Description: 动态数据源上下文
 * User: daming
 * Date: 2018-05-21
 * Time: 20:06
 * Description:
 */
public class DynamicDataSourceLookupKeyHolder {

    private static final String DEFAULT_DATA_SOURCE = "master";

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
