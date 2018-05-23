package org.aming.dy.ms.dao;

import org.aming.dy.ms.base.pojo.Car;
import org.aming.dy.ms.jdbc.annotation.TargetDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

/**
 * Description: 动态数据源示例Dao
 * User: daming
 * Date: 2018-05-23
 * Time: 20:54
 * Description:
 */
@Repository
public class DyDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@TargetDataSource(dataSource = "dy2")
	public List<Car> get() {
		String sql = "select * from car";
		return jdbcTemplate.query("select * from car", this::mapRow);
	}

	@TargetDataSource(dataSource = "#{dy2}")
	public List<Car> get(String dy2) {
		String sql = "select * from car";
		return jdbcTemplate.query("select * from car", this::mapRow);
	}

	private Car mapRow(ResultSet rs, int rowMun) throws SQLException {
		return new Car()
				.setCarid(rs.getString("carid"))
				.setCartype(rs.getString("cartype"))
				.setDirverid(rs.getString("dirverid"))
				.setUsagetype(rs.getString("usagetype"))
				.setBuytime(rs.getString("buytime"))
				.setCartype(rs.getString("capacity"))
				.setCapunit(rs.getString("capunit"));
	}
}
