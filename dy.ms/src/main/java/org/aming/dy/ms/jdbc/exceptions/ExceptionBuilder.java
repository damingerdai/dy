package org.aming.dy.ms.jdbc.exceptions;

import org.aming.dy.ms.jdbc.logger.DynamicLogger;
import org.aming.dy.ms.jdbc.logger.LoggerManager;

/**
 * Description: 异常处理
 * User: daming
 * Date: 2018-05-31
 * Time: 21:05
 */
public class ExceptionBuilder {

	private static final DynamicLogger logger = LoggerManager.getDynamicLogger(ExceptionBuilder.class);

	public static DynamicDataSourceException buildDynamicException(String message, Throwable cause) {
		logger.error(message, cause);
		return new DynamicDataSourceException(message, cause);
	}
}
