package org.aming.dy.ms.jdbc.support;

import org.aming.dy.ms.jdbc.context.DynamicContext;

import java.sql.Connection;
import java.util.Optional;

/**
 * Description: 动态数据源缓存的管理器
 * User: daming
 * Date: 2018-05-24
 * Time: 22:03
 * Description:
 */
public class DynamicCacheManager {

	private static DynamicContext dynamicContext = new DynamicContext();

	public static void cache(String lookupKey, Connection connection) {
		dynamicContext.save(lookupKey, connection);
	}

	public static Optional<Connection> getConnection(String lookupKey) {
		return dynamicContext.getConnection(lookupKey);
	}

	public static void remove() {
		dynamicContext.remove();
	}
}
