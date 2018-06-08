package org.aming.dy.support;

import org.aming.dy.core.DynamicDataSource;
import org.aming.dy.exceptions.DynamicDataSourceException;
import org.aming.dy.logger.DynamicLogger;
import org.aming.dy.logger.LoggerManager;

/**
 * Description: 动态数据源创建器
 * User: daming
 * Date: 2018-06-06
 * Time: 23:16
 */
public abstract class DynamicDataSourceBuilder {

	protected final DynamicLogger logger = LoggerManager.getDynamicLogger(getClass());

	protected DynamicDataSource dynamicDataSource;

	public abstract DynamicDataSource build() throws DynamicDataSourceException;
}
