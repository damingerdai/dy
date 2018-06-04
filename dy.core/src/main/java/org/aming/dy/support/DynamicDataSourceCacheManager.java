package org.aming.dy.support;

import org.aming.dy.context.DynamicContext;

import java.sql.Connection;
import java.util.Optional;

/**
 * Description: 动态数据源缓存管理
 * User: daming
 * Date: 2018-06-03
 * Time: 21:41
 */
public class DynamicDataSourceCacheManager {

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

	public static void setLookupKey(String loopKey) {
		DynamicDataSourceLookupKeyHolder.setLookupKey(loopKey);
	}

	public static void clearLookupKey() {
		DynamicDataSourceLookupKeyHolder.clear();
	}
}
