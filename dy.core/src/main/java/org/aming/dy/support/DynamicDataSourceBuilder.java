package org.aming.dy.support;

import org.aming.dy.core.DynamicDataSource;
import org.aming.dy.exceptions.DynamicDataSourceException;

/**
 * Description: 动态数据源创建器
 * User: daming
 * Date: 2018-06-06
 * Time: 23:16
 */
public abstract class DynamicDataSourceBuilder {

	public abstract DynamicDataSource build() throws DynamicDataSourceException;
}
