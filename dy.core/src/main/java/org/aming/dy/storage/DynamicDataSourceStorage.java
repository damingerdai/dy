package org.aming.dy.storage;

import org.aming.dy.core.DynamicDataSource;
import org.aming.dy.exceptions.DynamicDataSourceException;

/**
 * 动态数据源存储
 */
public interface DynamicDataSourceStorage {

	void store(DynamicDataSource dynamicDataSource) throws DynamicDataSourceException;
}
