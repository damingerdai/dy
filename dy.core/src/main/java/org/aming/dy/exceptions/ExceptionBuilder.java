package org.aming.dy.exceptions;

import org.aming.dy.logger.DynamicLogger;
import org.aming.dy.logger.LoggerManager;

import java.text.MessageFormat;

/**
 * Description: 异常建设者
 * User: daming
 * Date: 2018-06-02
 * Time: 20:57
 */
public class ExceptionBuilder {

	private static final DynamicLogger logger = LoggerManager.getDynamicLogger(ExceptionBuilder.class);

	public static DynamicDataSourceException buildDynamicException(String message, Throwable cause) {
		logger.error(message, cause);
		return new DynamicDataSourceException(message, cause);
	}

	public static DynamicDataSourceException buildDynamicException(String message, Object[] params, Throwable cause) {
		message = MessageFormat.format(message, params);
		logger.error(message, cause);
		return new DynamicDataSourceException(message, cause);
	}

	public static DynamicDataSourceException buildDynamicException(String message, Object[] params) {
		message = MessageFormat.format(message, params);
		logger.error(message);
		return new DynamicDataSourceException(message);
	}

	public static UnsupportedOperationException buildUnsupportedOperationException(String operation) {
		return null;
	}

	private void error(String message) {
		logger.error(message);
	}

	private void error(String message, Throwable cause) {
		logger.error(message, cause);
	}

	private void error(String message, Object[] params) {
		logger.error(() -> MessageFormat.format(message, params));
	}

	private void error(String message, Object[] params, Throwable cause) {
		logger.error("");
	}
}
