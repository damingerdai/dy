package org.aming.dy.ms.jdbc.support;

import java.util.Map;

/**
 * 数据源的JbdcUrl创建工具
 *
 * @athur aming
 * @date 2018-05-23 11:34
 */
@FunctionalInterface
public interface DataSourceJbdcUrlBuilder {

	String buildJdbcUrl(String host, int port, String datasourceName, Map<String, String> map);

	default String buildJdbcUrl(String host, int port, String dataSourceName) {
		return buildJdbcUrl(host, port, dataSourceName, null);
	}


}
