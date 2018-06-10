package org.aming.dy.hikaricp;

import com.zaxxer.hikari.HikariDataSource;
import org.aming.dy.core.DynamicDataSource;
import org.aming.dy.exceptions.DynamicDataSourceException;
import org.aming.dy.support.DynamicDataSourceBuilder;
import org.aming.dy.utils.Assert;

import javax.sql.DataSource;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description: HikariCP动态数据源创建器
 * User: daming
 * Date: 2018-06-06
 * Time: 23:32
 */
public class HikariCPDynamicDataSourceBuilder extends DynamicDataSourceBuilder {

	private HikariDataSource masterDataSource;

	private ConcurrentHashMap<String, DataSource> slaveDataSources;

	@Override
	public DynamicDataSource build() throws DynamicDataSourceException {
		return null;
	}

	public void withMasterDataSource(HikariDataSource masterDataSource) {
		Assert.nonNull(masterDataSource, "master datasource is required");
		this.masterDataSource = masterDataSource;
	}

	public void withSlavesDataSources(ConcurrentHashMap<String, HikariDataSource> slavesDataSources) {
		Assert.nonNull(slavesDataSources, "salve datasource is required");
		if (Objects.isNull(this.slaveDataSources)) {
			this.slaveDataSources = new ConcurrentHashMap<>(slavesDataSources.size());
		}
		slavesDataSources.forEach((key, value) -> {
			this.slaveDataSources.put(key, value);
		});
	}

	public void addSlavesDataSources(String lookupKey, HikariDataSource slaveDataSource) {
		if (Objects.isNull(this.slaveDataSources)) {
			this.slaveDataSources = new ConcurrentHashMap<>();
		}
		this.slaveDataSources.put(lookupKey, slaveDataSource);

	}


	public HikariCPDynamicDataSourceBuilder() {
		super();
	}

}
