package org.aming.dy.ms.jdbc.core;

import org.aming.dy.ms.jdbc.support.DynamicDataSourceLookupKeyHolder;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.jdbc.datasource.AbstractDataSource;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description: 动态数据源
 * User: daming
 * Date: 2018-05-21
 * Time: 20:02
 * Description:
 */
public class DynamicDataSource  extends AbstractDataSource implements InitializingBean {

    private DataSource masterDataSource;

    private ConcurrentHashMap<String, DataSource> slaveDataSources;

    protected String determineCurrentLookupKey() {
        return DynamicDataSourceLookupKeyHolder.getDataSource();
    }

    @Override
    public Connection getConnection() throws SQLException {
        String lookupKey = determineCurrentLookupKey();
        return !StringUtils.isEmpty(lookupKey) && slaveDataSources.containsKey(lookupKey) ? slaveDataSources.get(lookupKey).getConnection() : masterDataSource.getConnection();
    }

    @Override
    public Connection getConnection(String username, String password) throws SQLException {
        String lookupKey = determineCurrentLookupKey();
        if (!StringUtils.isEmpty(lookupKey) && slaveDataSources.contains(lookupKey)) {
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

	public DynamicDataSource() {
    	super();
	}

	public DynamicDataSource(DataSource masterDataSource) {
		super();
		this.masterDataSource = masterDataSource;
	}

	public DynamicDataSource(DataSource masterDataSource, ConcurrentHashMap<String, DataSource> slaveDataSources) {
		super();
		this.masterDataSource = masterDataSource;
		this.slaveDataSources = slaveDataSources;
	}

	@Override
    public void afterPropertiesSet() throws Exception {
        Assert.notNull(masterDataSource, "'masterDataSource' is required");

        if (Objects.isNull(slaveDataSources)) {
            slaveDataSources = new ConcurrentHashMap<>();
        }
    }
}
