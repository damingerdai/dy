package org.aming.dy.logger;

import com.google.common.collect.Maps;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Description: 日志管理器
 * User: daming
 * Date: 2018-06-02
 * Time: 20:37
 */
public class LoggerManager {

	private static Map<String, DynamicLogger> cache = Maps.newHashMap();

	public static DynamicLogger getDynamicLogger(Class<?> clazz) {
		return doGetDynamicLogger(clazz.getName());
	}

	public static DynamicLogger getDynamicLogger(String loggerName) {
		return doGetDynamicLogger(loggerName);
	}

	private static DynamicLogger doGetDynamicLogger(String loggerName) {
		if (cache.containsKey(loggerName)) {
			return cache.get(loggerName);
		} else {
			return fetchDynamicLogger(loggerName);
		}
	}

	private synchronized static DynamicLogger fetchDynamicLogger(String loggerName) {
		DynamicLogger dynamicLogger = new DynamicLogger(LoggerFactory.getLogger(loggerName));
		cache.put(loggerName, dynamicLogger);
		return dynamicLogger;
	}
}
