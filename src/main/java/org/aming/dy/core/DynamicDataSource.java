package org.aming.dy.core;

import org.aming.dy.support.DynamicDataSourceContextHolder;
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
    private ConcurrentHashMap<String, DataSource> dsMap;

//    protected String determineCurrentLookupKey() {
//
//	}

    @Override
    public Connection getConnection() throws SQLException {

        return null;
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        return null;
    }

    protected ConcurrentHashMap<String, DataSource> obtainDsMap() {
        if (Objects.isNull(this.dsMap)) {
            this.dsMap = new ConcurrentHashMap<>();
        }
        return this.dsMap;
    }

    public void setDefaultDataSource(DataSource defaultDataSource) {
        this.defaultDataSource = defaultDataSource;
    }

    public void setDsMap(ConcurrentHashMap<String, DataSource> dsMap) {
        this.dsMap = dsMap;
    }

    public void addDataSource(String name, DataSource dataSource) {
        Assert.notNull(name, "param 'name' is required");
        Assert.notNull(dataSource, "param 'dataSource' is required");
        obtainDsMap().put(name, dataSource);
    }

    public void replaceDataSource(String name, DataSource dataSource) {
        Assert.notNull(name, "param 'name' is required");
        Assert.notNull(dataSource, "param 'dataSource' is required");
        obtainDsMap().putIfAbsent(name, dataSource);
    }

    public void removeDataSource(String name) {
        Assert.notNull(name, "param 'name' is required");
        obtainDsMap().remove(name);
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
            initDsMap(defaultDynamicDataSource.obtainDsMap());
        } else {
            setDefaultDataSource(dataSource);
        }
    }

    private void initDsMap(Map<String, DataSource> dsMap) {
        if (dsMap instanceof ConcurrentHashMap) {
            setDsMap((ConcurrentHashMap<String, DataSource>)dsMap);
        } else {
            dsMap.forEach((k, v) -> {
                obtainDsMap().put(k, v);
            });
        }
    }


    @Override
    public void afterPropertiesSet() throws Exception {
        if (Objects.isNull(defaultDataSource)) {
            throw new IllegalStateException("'defaultDataSource' is required");
        }

        if (Objects.isNull(dsMap)) {
            dsMap = new ConcurrentHashMap<>();
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
