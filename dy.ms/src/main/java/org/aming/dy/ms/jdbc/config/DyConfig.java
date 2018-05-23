package org.aming.dy.ms.jdbc.config;

import com.zaxxer.hikari.HikariDataSource;
import org.aming.dy.ms.jdbc.core.DynamicDataSource;
import org.aming.dy.ms.jdbc.support.AbstractDynamicDataSourceBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 * Description: 动态数据源的配置
 * User: daming
 * Date: 2018-05-21
 * Time: 21:41
 * Description:
 */
@Configuration
public class DyConfig {

	private HikariProp hikariProp;
	private AbstractDynamicDataSourceBuilder dynamicDataSourceBuilder;

	@Bean
	public HikariDataSource hikariDataSource() {
		HikariDataSource hikariDataSource = new HikariDataSource();
		hikariDataSource.setDriverClassName(hikariProp.getDataSourceClassName());
		hikariDataSource.setJdbcUrl(hikariProp.getJdbcUrl());
		hikariDataSource.setUsername(hikariProp.getUsername());
		hikariDataSource.setPassword(hikariProp.getPassword());
		hikariDataSource.setPoolName("master");
		return hikariDataSource;
	}

	@Bean
	public DynamicDataSource dynamicDataSource(HikariDataSource hikariDataSource) {
		return new DynamicDataSource(hikariDataSource, dynamicDataSourceBuilder.buildSlaveDataSource(hikariDataSource));
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DynamicDataSource dynamicDataSource) {
		return new JdbcTemplate(dynamicDataSource);
	}

	@Autowired
	public void setHikariProp(HikariProp hikariProp) {
		this.hikariProp = hikariProp;
	}

	@Autowired
	public void setDynamicDataSourceBuilder(AbstractDynamicDataSourceBuilder dynamicDataSourceBuilder) {
		this.dynamicDataSourceBuilder = dynamicDataSourceBuilder;
	}
}
