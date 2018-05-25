package org.aming.dy.ms.jdbc.support;

import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Mysql的jdbc-url创建者
 *
 * @athur aming
 * @date 2018-05-23 11:39
 */
@Component
public class MysqlJdbcUrlBuilder implements DataSourceJbdcUrlBuilder {

	@Override
	public String buildJdbcUrl(String host, int port, String datasourceName, Map<String, String> map) {
		String JDBC_Url_TEMPLATE = "jdbc:mysql://%s:%d/%s";
		String jdbcUrl = String.format(JDBC_Url_TEMPLATE, host, port, datasourceName);
		if (Objects.nonNull(map) && map.size() > 0) {
			jdbcUrl += map.entrySet().stream().map(entry -> entry.getKey() + "=" + entry.getValue()).collect(Collectors.joining("&", "?", ""));
		}
		return jdbcUrl;
	}
}
