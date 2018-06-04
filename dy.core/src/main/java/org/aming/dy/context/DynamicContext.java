package org.aming.dy.context;

import com.google.common.collect.Maps;

import java.sql.Connection;
import java.util.Map;
import java.util.Optional;

/**
 * Description: 动态数据源上下文
 * User: daming
 * Date: 2018-06-02
 * Time: 21:23
 */
public class DynamicContext extends ThreadLocal<Map<String, Connection>> {

	@Override
	protected Map<String, Connection> initialValue() {
		return Maps.newHashMap();
	}

	public Optional<Connection> getConnection(String lookupKey) {
		Map<String, Connection> cache = get();
		if (cache.containsKey(lookupKey)) {
			return Optional.of(cache.get(lookupKey));
		} else {
			return Optional.empty();
		}
	}

	public Connection save(String lookupKey, Connection connection) {
		Map<String, Connection> cache = get();
		return get().put(lookupKey, connection);
	}

	public void delete(String lookupKey) {
		Map<String, Connection> cache = get();
		cache.remove(lookupKey);
	}

	@Override
	public void remove() {
		get().clear();
		super.remove();
	}
}
