package org.aming.dy.ms.jdbc.logger;

import com.google.common.collect.Maps;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * Description: 日志管理器
 * User: daming
 * Date: 2018-05-31
 * Time: 20:31
 */
public class LoggerManager {

	private static Map<String, DynamicLogger> cache = Maps.newHashMap();

	public static DynamicLogger getDynamicLogger(Class<?> clazz) {
		return getDynamicLogger(clazz.getName());
	}

	public static DynamicLogger getDynamicLogger(String loggerName) {
		if (cache.containsKey(loggerName)) {
			return cache.get(loggerName);
		} else {
			return doGetDynamicLogger(loggerName);
		}
	}

	private synchronized static DynamicLogger doGetDynamicLogger(String loggerName) {
		DynamicLogger dynamicLogger = new DynamicLogger(LoggerFactory.getLogger(loggerName));
		cache.put(loggerName, dynamicLogger);
		return dynamicLogger;
	}


}
