package org.aming.dy.core;

import org.aming.dy.support.DynamicDataSourceCacheManager;
import org.aming.dy.support.DynamicDataSourceLookupKeyHolder;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description: 动态数据源
 * User: daming
 * Date: 2018-06-02
 * Time: 21:38
 */
public class DynamicDataSource extends AbstractDynamicDataSource implements DataSource {

	private DataSource masterDataSource;

	private ConcurrentHashMap<String, DataSource> slaveDataSources;

	@Override
	protected String determineCurrentLookupKey() {
		return DynamicDataSourceLookupKeyHolder.getLookupKey();
	}

	@Override
	public Connection getConnection() throws SQLException {
		String lookupKey = determineCurrentLookupKey();
		return doGetConnection(lookupKey);
	}

	private Connection doGetConnection(String lookupKey) throws SQLException {
		Optional<Connection> optional = DynamicDataSourceCacheManager.getConnection(lookupKey);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			Connection connection = newConnection(lookupKey);
			DynamicDataSourceCacheManager.cache(lookupKey, connection);
			return connection;
		}
	}

	private Connection newConnection(String lookupKey) throws SQLException {
		if (slaveDataSources.containsKey(lookupKey)) {
			return slaveDataSources.get(lookupKey).getConnection();
		} else {
			return masterDataSource.getConnection();
		}
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		String lookupKey = determineCurrentLookupKey();
		return doGetConnection(lookupKey, username, password);
	}

	private Connection doGetConnection(String lookupKey, String username, String password) throws  SQLException {
		Optional<Connection> optional = DynamicDataSourceCacheManager.getConnection(lookupKey);
		if (optional.isPresent()) {
			return optional.get();
		} else {
			Connection connection = newConnection(lookupKey, username, password);
			DynamicDataSourceCacheManager.cache(lookupKey, connection);
			return connection;
		}
	}

	private Connection newConnection(String lookupKey, String username, String password) throws SQLException {
		if (slaveDataSources.containsKey(lookupKey)) {
			return slaveDataSources.get(lookupKey).getConnection(username, password);
		} else {
			return masterDataSource.getConnection(username, password);
		}
	}

	public DataSource getMasterDataSource() {
		return masterDataSource;
	}

	public DynamicDataSource setMasterDataSource(DataSource masterDataSource) {
		this.masterDataSource = masterDataSource;
		return this;
	}

	public ConcurrentHashMap<String, DataSource> getSlaveDataSources() {
		return slaveDataSources;
	}

	public DynamicDataSource setSlaveDataSources(ConcurrentHashMap<String, DataSource> slaveDataSources) {
		this.slaveDataSources = slaveDataSources;
		return this;
	}

	public void addSlaveDataSource(String lookupKey, DataSource slaveDataSource) {
		if (Objects.isNull(slaveDataSources)) {
			slaveDataSources = new ConcurrentHashMap<>();
		}
		slaveDataSources.put(lookupKey, slaveDataSource);
	}

	public DynamicDataSource() {
		super();
	}
}
