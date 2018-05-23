package org.aming.dy.ms.jdbc.support;

import com.zaxxer.hikari.HikariDataSource;
import org.aming.dy.ms.jdbc.config.HikariProp;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 默认动态数据源创建
 *
 * @athur aming
 * @date 2018-05-22 12:04
 */
@Component
public class DefaultDynamicDataSourceBuilder extends AbstractDynamicDataSourceBuilder {

	private HikariProp hikariProp;

	@Override
	public DataSource buildMasterDataSource() {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setDriverClassName(hikariProp.getDataSourceClassName());
		dataSource.setJdbcUrl(hikariProp.getJdbcUrl());
		dataSource.setUsername(hikariProp.getUsername());
		dataSource.setPassword(hikariProp.getPassword());
		dataSource.setPoolName("master");
		dataSource.setMaximumPoolSize(Runtime.getRuntime().availableProcessors());
		return dataSource;
	}

	@Override
	public ConcurrentHashMap<String, DataSource> buildSlaveDataSource() {
		return buildSlaveDataSource(buildMasterDataSource());
	}

	@Override
	public ConcurrentHashMap<String, DataSource> buildSlaveDataSource(DataSource masterDataSource) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(masterDataSource);
		return jdbcTemplate.query("select * from dy_datasource", this::mapRow).stream().collect(ConcurrentHashMap::new, (result, value) -> result.put(value.getPoolName(), value), ConcurrentHashMap::putAll);
	}

	private HikariDataSource mapRow(ResultSet rs, int rowNum) throws SQLException {
		HikariDataSource dataSource = new HikariDataSource();
		dataSource.setDriverClassName(rs.getString("driver"));
		dataSource.setJdbcUrl(rs.getString("url"));
		dataSource.setUsername(rs.getString("username"));
		dataSource.setPassword(rs.getString("password"));
		dataSource.setPoolName(rs.getString("name"));
		dataSource.setMaximumPoolSize(Runtime.getRuntime().availableProcessors());
		return dataSource;
	}

	public DefaultDynamicDataSourceBuilder(HikariProp hikariProp) {
		this.hikariProp = hikariProp;
	}
}
