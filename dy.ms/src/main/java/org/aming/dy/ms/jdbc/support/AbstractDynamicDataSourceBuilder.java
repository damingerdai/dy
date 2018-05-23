package org.aming.dy.ms.jdbc.support;

import org.aming.dy.ms.jdbc.core.DynamicDataSource;

import javax.sql.DataSource;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 动态数据源创建辅助工具类
 *
 * @athur aming
 * @date 2018-05-22 09:09
 */
public abstract class AbstractDynamicDataSourceBuilder {

	public DynamicDataSource buildDynamicDataSource(DataSource masterDataSource) {
		return new DynamicDataSource(masterDataSource);
	}

	public DynamicDataSource buildDynamicDataSource(DataSource masterDataSource, ConcurrentHashMap<String, DataSource> slaveDataSource) {
		return new DynamicDataSource(masterDataSource, slaveDataSource);
	}

	public abstract DataSource buildMasterDataSource();

	public abstract  ConcurrentHashMap<String, DataSource> buildSlaveDataSource();

	public abstract  ConcurrentHashMap<String, DataSource> buildSlaveDataSource(DataSource masterDataSource);

}
