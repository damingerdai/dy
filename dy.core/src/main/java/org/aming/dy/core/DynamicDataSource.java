package org.aming.dy.core;

import org.aming.dy.support.DynamicDataSourceContextHolder;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.util.Assert;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @author aming
 * @create 2018-04-03 8:47
 **/
public class DynamicDataSource extends AbstractDataSource implements InitializingBean {

	private ReadWriteLock lock = new ReentrantReadWriteLock();

	private DataSource masterDataSource;

	private ConcurrentHashMap<String, DataSource> resolvedDataSources;

	protected String determineCurrentLookupKey() {
		return DynamicDataSourceContextHolder.getDataSource();
	}

	@Override
	public Connection getConnection() throws SQLException {
		var lookupKey = determineCurrentLookupKey();
		var dataSource = getDataSource(lookupKey);
		try (var connection = doGetConnection(dataSource)) {
			return connection;
		}
	}

	private DataSource getDataSource(String lookupKey) {
		var dataSource = masterDataSource;

		if (StringUtils.isNotBlank(lookupKey)) {
			dataSource = resolvedDataSources.get(lookupKey);
		}
		if (Objects.isNull(dataSource)) {
			return masterDataSource;
		} else {
			return dataSource;
		}
	}

	protected Connection doGetConnection(DataSource dataSource) throws SQLException {
		var connection = dataSource.getConnection();
		connection.setAutoCommit(false);
		return connection;
	}

	@Override
	public Connection getConnection(String username, String password) throws SQLException {
		var dataSource = masterDataSource;
		String lookupKey = determineCurrentLookupKey();
		if (StringUtils.isBlank(lookupKey)) {
			dataSource = resolvedDataSources.get(lookupKey);
		}
		return dataSource.getConnection(username, password);
	}

	protected ConcurrentHashMap<String, DataSource> obtainResolvedDataSources() {
		if (Objects.isNull(this.resolvedDataSources)) {
			this.resolvedDataSources = new ConcurrentHashMap<>();
		}
		return this.resolvedDataSources;
	}

	public void setDefaultDataSource(DataSource defaultDataSource) {
		this.masterDataSource = defaultDataSource;
	}

	public void setResolvedDataSources(ConcurrentHashMap<String, DataSource> resolvedDataSources) {
		this.resolvedDataSources = resolvedDataSources;
	}

	public void addDataSource(String name, DataSource dataSource) {
		Assert.notNull(name, "param 'name' is required");
		Assert.notNull(dataSource, "param 'dataSource' is required");
		lock.writeLock().lock();
		try {
			obtainResolvedDataSources().put(name, dataSource);
		} finally {
			lock.writeLock().unlock();
		}
	}

	public void replaceDataSource(String name, DataSource dataSource) {
		Assert.notNull(name, "param 'name' is required");
		Assert.notNull(dataSource, "param 'dataSource' is required");
		lock.writeLock().lock();
		try {
			obtainResolvedDataSources().putIfAbsent(name, dataSource);
		} finally {
			lock.writeLock().unlock();
		}
	}

	public void removeDataSource(String name) {
		Assert.notNull(name, "param 'name' is required");
		obtainResolvedDataSources().remove(name);
	}

	public DataSource replaceDefaultDataSource(DataSource defaultDataSource) {
		Assert.notNull(defaultDataSource, "param 'defaultDataSource' is required");
		lock.writeLock().lock();
		try {
			var oldDataSource = this.masterDataSource;
			this.masterDataSource = defaultDataSource;
			return oldDataSource;
		} finally {
			lock.writeLock().unlock();
		}
	}

	public DynamicDataSource(DataSource defaultDataSource, Map<String, DataSource> dsMap) {
		super();
		this.masterDataSource = defaultDataSource;
		this.initDsMap(dsMap);
	}

	public DynamicDataSource(Map<String, DataSource> dsMap) {
		super();
		this.initDsMap(dsMap);
	}

	public DynamicDataSource(DataSource dataSource) {
		super();
	}

	private void initDynamicDataSource(DataSource dataSource) {
		if (dataSource instanceof DynamicDataSource) {
			var defaultDynamicDataSource = (DynamicDataSource) dataSource;
			setDefaultDataSource(defaultDynamicDataSource.masterDataSource);
			initDsMap(defaultDynamicDataSource.obtainResolvedDataSources());
		} else {
			setDefaultDataSource(dataSource);
		}
	}

	private void initDsMap(Map<String, DataSource> resolvedDataSources) {
		if (resolvedDataSources instanceof ConcurrentHashMap) {
			setResolvedDataSources((ConcurrentHashMap<String, DataSource>) resolvedDataSources);
		} else {
			resolvedDataSources.forEach((k, v) -> {
				obtainResolvedDataSources().put(k, v);
			});
		}
	}


	@Override
	public void afterPropertiesSet() throws Exception {
		if (Objects.isNull(masterDataSource)) {
			throw new IllegalStateException("'defaultDataSource' is required");
		}

		if (Objects.isNull(resolvedDataSources)) {
			resolvedDataSources = new ConcurrentHashMap<>();
		}
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}
}
