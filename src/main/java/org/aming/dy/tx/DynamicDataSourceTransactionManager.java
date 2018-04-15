package org.aming.dy.tx;

import com.google.common.collect.Maps;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

/**
 *
 * @author aming
 * @create 2018-04-03 8:51
 **/
public class DynamicDataSourceTransactionManager {

	private static Logger logger = LoggerFactory.getLogger(DynamicDataSourceTransactionManager.class);

	private static ThreadLocal<Map<String, Connection>> transactionHolder = ThreadLocal.withInitial(Maps::newHashMap);

	public static Connection getConnection(String name) throws SQLException {
		Assert.notNull(name, "param 'name' is required");
		return transactionHolder.get().getOrDefault(name, null);
	}

	public static void addConnection(String name, Connection connection) throws SQLException {
		Assert.notNull(name, "param 'name' is required");
		Assert.notNull(connection,  "param 'connection' is required");
		transactionHolder.get().put(name, connection);
	}

	public static void removeConnections() throws SQLException {
		transactionHolder.remove();
	}

	public static void commitConnections() throws SQLException {
		transactionHolder.get().forEach((k, v) -> {
			try {
				v.commit();
			} catch (SQLException e) {
				logger.debug("fail to commit connection : " + e.getMessage());
			}
		});
	}

	public static void rollbackConnections() throws SQLException {
		transactionHolder.get().forEach((k,v) -> {
			try {
				v.rollback();
			} catch (SQLException e) {
				logger.debug("fail to rollback connection : " + e.getMessage());
			}
		});
	}
}
