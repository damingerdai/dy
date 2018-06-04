package org.aming.dy.logger;

import org.slf4j.Logger;

import java.util.function.Supplier;

/**
 * Description: 动态数据源日志
 * User: daming
 * Date: 2018-06-02
 * Time: 20:36
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

	public void trace(Supplier<String> supplier) {
		if (logger.isTraceEnabled()) {
			logger.trace(supplier.get());
		}
	}

	public void trace(Supplier<String> supplier, Throwable cause) {
		if (logger.isTraceEnabled()) {
			logger.trace(supplier.get(), cause);
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

	public void debug(Supplier<String> supplier) {
		if (logger.isDebugEnabled()) {
			logger.debug(supplier.get());
		}
	}

	public void debug(Supplier<String> supplier, Throwable cause) {
		if (logger.isDebugEnabled()) {
			logger.debug(supplier.get(), cause);
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

	public void info(Supplier<String> supplier) {
		if (logger.isInfoEnabled()) {
			logger.info(supplier.get());
		}
	}

	public void info(Supplier<String> supplier, Throwable cause) {
		if (logger.isInfoEnabled()) {
			logger.info(supplier.get(), cause);
		}
	}

	public void warn(String message) {
		if (logger.isWarnEnabled()) {
			logger.warn(message);
		}
	}

	public void warn(String message, Object...params) {
		if (logger.isWarnEnabled()) {
			logger.warn(message, params);
		}
	}

	public void warn(String message, Throwable cause) {
		if (logger.isWarnEnabled()) {
			logger.warn(message, cause);
		}
	}

	public void warn(Supplier<String> supplier) {
		if (logger.isWarnEnabled()) {
			logger.warn(supplier.get());
		}
	}

	public void warn(Supplier<String> supplier, Throwable cause) {
		if (logger.isWarnEnabled()) {
			logger.warn(supplier.get(), cause);
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

	public void error(Supplier<String> supplier) {
		if (logger.isErrorEnabled()) {
			logger.error(supplier.get());
		}
	}

	public void error(Supplier<String> supplier, Throwable cause) {
		if (logger.isErrorEnabled()) {
			logger.error(supplier.get(), cause);
		}
	}

	public java.util.logging.Logger getLogger() {
		return java.util.logging.LogManager.getLogManager().getLogger(logger.getName());
	}

	DynamicLogger(Logger logger) {
		this.logger = logger;
	}
}
