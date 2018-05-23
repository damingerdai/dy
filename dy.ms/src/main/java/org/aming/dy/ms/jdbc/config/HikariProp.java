package org.aming.dy.ms.jdbc.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * Description: Hikari配置属性
 * User: daming
 * Date: 2018-05-21
 * Time: 22:31
 * Description:
 */
@ConfigurationProperties(prefix = "spring.hikari.datasource")
@Component
public class HikariProp {

	private String  dataSourceClassName;

	private String  jdbcUrl;

	private String username;

	private String password;

	public String getDataSourceClassName() {
		return dataSourceClassName;
	}

	public HikariProp setDataSourceClassName(String dataSourceClassName) {
		this.dataSourceClassName = dataSourceClassName;
		return this;
	}

	public String getJdbcUrl() {
		return jdbcUrl;
	}

	public HikariProp setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
		return this;
	}

	public String getUsername() {
		return username;
	}

	public HikariProp setUsername(String username) {
		this.username = username;
		return this;
	}

	public String getPassword() {
		return password;
	}

	public HikariProp setPassword(String password) {
		this.password = password;
		return this;
	}

	public HikariProp() {
		super();
	}
}
