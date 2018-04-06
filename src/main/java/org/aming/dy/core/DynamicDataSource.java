package org.aming.dy.core;

import org.aming.dy.support.DynamicDataSourceContextHolder;
import org.aming.dy.tx.DynamicDataSourceTransactionManager;
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

/**
 * 动态数据源
 *
 * @author aming
 * @create 2018-04-03 8:47
 **/
public class DynamicDataSource extends AbstractDataSource implements InitializingBean {

    /**
     * 默认数据源
     */
    private DataSource defaultDataSource;
    /**
     * 其他数据源
     */
    private ConcurrentHashMap<String, DataSource> resolvedDataSources;

    protected String determineCurrentLookupKey() {
		return DynamicDataSourceContextHolder.getDataSource();
	}

    @Override
    public Connection getConnection() throws SQLException {
		var lookupKey = determineCurrentLookupKey();
		var connection = DynamicDataSourceTransactionManager.getConnection(lookupKey);
		if (Objects.isNull(connection)) {
			var dataSource = getDataSource(lookupKey);
			connection = doGetConnection(dataSource);
			DynamicDataSourceTransactionManager.addConnection(lookupKey, connection);
		}
    	return connection;
    }

    private DataSource getDataSource(String lookupKey) throws SQLException {
    	var dataSource = defaultDataSource;

    	if (StringUtils.isNotBlank(lookupKey)) {
    		dataSource = resolvedDataSources.get(lookupKey);
		}
		if (Objects.isNull(dataSource)) {
    		return defaultDataSource;
		} else {
    		return dataSource;
		}
	}

    protected  Connection doGetConnection(DataSource dataSource) throws SQLException {
    	var connection = dataSource.getConnection();
    	connection.setAutoCommit(false);
    	return connection;
	}

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
		var dataSource = defaultDataSource;
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
        this.defaultDataSource = defaultDataSource;
    }

    public void setResolvedDataSources(ConcurrentHashMap<String, DataSource> resolvedDataSources) {
        this.resolvedDataSources = resolvedDataSources;
    }

    public void addDataSource(String name, DataSource dataSource) {
        Assert.notNull(name, "param 'name' is required");
        Assert.notNull(dataSource, "param 'dataSource' is required");
		obtainResolvedDataSources().put(name, dataSource);
    }

    public void replaceDataSource(String name, DataSource dataSource) {
        Assert.notNull(name, "param 'name' is required");
        Assert.notNull(dataSource, "param 'dataSource' is required");
		obtainResolvedDataSources().putIfAbsent(name, dataSource);
    }

    public void removeDataSource(String name) {
        Assert.notNull(name, "param 'name' is required");
		obtainResolvedDataSources().remove(name);
    }

    public synchronized DataSource replaceDefaultDataSource(DataSource defaultDataSource) {
        Assert.notNull(defaultDataSource, "param 'defaultDataSource' is required");
        var oldDataSource = this.defaultDataSource;
        this.defaultDataSource = defaultDataSource;
        return oldDataSource;
    }

    public DynamicDataSource(DataSource defaultDataSource, Map<String, DataSource> dsMap) {
        super();
        this.defaultDataSource = defaultDataSource;
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
            setDefaultDataSource(defaultDynamicDataSource.defaultDataSource);
            initDsMap(defaultDynamicDataSource.obtainResolvedDataSources());
        } else {
            setDefaultDataSource(dataSource);
        }
    }

    private void initDsMap(Map<String, DataSource> resolvedDataSources) {
        if (resolvedDataSources instanceof ConcurrentHashMap) {
			setResolvedDataSources((ConcurrentHashMap<String, DataSource>)resolvedDataSources);
        } else {
			resolvedDataSources.forEach((k, v) -> {
				obtainResolvedDataSources().put(k, v);
            });
        }
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        if (Objects.isNull(defaultDataSource)) {
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
