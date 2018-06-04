package org.aming.dy.core;

import org.aming.dy.exceptions.ExceptionBuilder;
import org.aming.dy.logger.DynamicLogger;
import org.aming.dy.logger.LoggerManager;

import javax.sql.DataSource;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.util.logging.Logger;

/**
 * Description: 动态数据源的抽象类
 * User: daming
 * Date: 2018-06-02
 * Time: 21:52
 */
public abstract class AbstractDynamicDataSource implements DataSource {

	protected final DynamicLogger logger = LoggerManager.getDynamicLogger(getClass());


	protected abstract String determineCurrentLookupKey();

	@Override
	public PrintWriter getLogWriter() throws SQLException {
		throw ExceptionBuilder.buildUnsupportedOperationException("getLogWriter");
	}

	@Override
	public void setLogWriter(PrintWriter out) throws SQLException {
		throw ExceptionBuilder.buildUnsupportedOperationException("setLogWriter");
	}

	@Override
	public void setLoginTimeout(int seconds) throws SQLException {
		throw ExceptionBuilder.buildUnsupportedOperationException("setLogWriter");
	}

	@Override
	public int getLoginTimeout() throws SQLException {
		return 0;
	}

	@Override
	public Logger getParentLogger() throws SQLFeatureNotSupportedException {
		return logger.getLogger();
	}

	@Override
	public <T> T unwrap(Class<T> iface) throws SQLException {
		if (iface.isInstance(this)) {
			return (T)this;
		}
		throw new SQLException("unwrap is unsupported");
	}

	@Override
	public boolean isWrapperFor(Class<?> iface) throws SQLException {
		return iface.isInstance(this);
	}
}
