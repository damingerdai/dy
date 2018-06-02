package org.aming.dy.ms.jdbc.logger;

import org.slf4j.Logger;

/**
 * Description: 动态数据源日志
 * User: daming
 * Date: 2018-05-31
 * Time: 19:45
 */
public class DynamicLogger {

	private final Logger logger;

	public void trace(String msg) {
		if (logger.isTraceEnabled()) {
			logger.trace(msg);
		}
	}

	public void trace(String msg, Object... params) {
		if (logger.isTraceEnabled()) {
			logger.trace(msg, params);
		}
	}

	public void trace(String msg, Throwable cause) {
		if (logger.isTraceEnabled()) {
			logger.trace(msg, cause);
		}
	}

	public void debug(String msg) {
		if (logger.isDebugEnabled()) {
			logger.debug(msg);
		}
	}

	public void debug(String msg, Object...params) {
		if (logger.isDebugEnabled()) {
			logger.debug(msg, params);
		}
	}

	public void debug(String msg, Throwable cause) {
		if (logger.isDebugEnabled()) {
			logger.debug(msg, cause);
		}
	}

	public void info(String msg) {
		if (logger.isInfoEnabled()) {
			logger.info(msg);
		}
	}

	public void info(String msg, Object...params) {
		if (logger.isInfoEnabled()) {
			logger.info(msg, params);
		}
	}

	public void info(String msg, Throwable cause) {
		if (logger.isInfoEnabled()) {
			logger.info(msg, cause);
		}
	}

	public void error(String msg) {
		if (logger.isErrorEnabled()) {
			logger.error(msg);
		}
	}

	public void error(String msg, Object...params) {
		if (logger.isErrorEnabled()) {
			logger.error(msg, params);
		}
	}

	public void error(String msg, Throwable cause) {
		if (logger.isErrorEnabled()) {
			logger.error(msg, cause);
		}
	}


	DynamicLogger(Logger logger) {
		this.logger = logger;
	}
}
