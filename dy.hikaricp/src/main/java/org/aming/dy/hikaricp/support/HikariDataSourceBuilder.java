package org.aming.dy.hikaricp.support;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.util.Properties;

/**
 * @date daming
 * @create 2018-06-10 13:36
 **/
public class HikariDataSourceBuilder {

	public static HikariDataSource buildHikariDataSourceFromConfig(HikariConfig config) {
		return new HikariDataSource(config);
	}

	public static HikariDataSource buildHikariDataSOurceFromPorperties(String propertyFile) {
		return buildHikariDataSourceFromConfig(new HikariConfig(propertyFile));
	}

	public static HikariDataSource buildHikariDataSOurceFromPorperties(Properties properties) {
		return buildHikariDataSourceFromConfig(new HikariConfig(properties));
	}
}
