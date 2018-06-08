package org.aming.dy.hikaricp;

import com.zaxxer.hikari.HikariDataSource;
import org.aming.dy.core.DynamicDataSource;
import org.aming.dy.exceptions.DynamicDataSourceException;
import org.aming.dy.support.DynamicDataSourceBuilder;

import javax.sql.DataSource;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Description: HikariCP动态数据源创建器
 * User: daming
 * Date: 2018-06-06
 * Time: 23:32
 */
public class HikariCPDynamicDataSourceBuilder extends DynamicDataSourceBuilder {

	private DynamicDataSource dynamicDataSource;

	@Override
	public DynamicDataSource build() throws DynamicDataSourceException {
		return null;
	}


}
