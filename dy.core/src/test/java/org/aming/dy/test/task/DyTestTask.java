package org.aming.dy.test.task;

import org.aming.dy.core.DynamicDataSource;
import org.aming.dy.support.DynamicDataSourceCacheManager;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Instant;
import java.util.concurrent.TimeUnit;

/**
 * Description:
 * User: daming
 * Date: 2018-06-04
 * Time: 21:05
 */
public class DyTestTask implements Runnable {

	private final DynamicDataSource dynamicDataSource;

	private final String lookupKey;

	@Override
	public void run() {
		DynamicDataSourceCacheManager.setLookupKey(lookupKey);
		try {
			execute();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	private void execute() throws SQLException {
		try (Connection connection = dynamicDataSource.getConnection();Statement st = connection.createStatement(); ) {
			st.execute("INSERT INTO `car` (`carid`, `cartype`, `dirverid`, `usagetype`, `buytime`, `capacity`, `capunit`) VALUE ('苏 " + System.currentTimeMillis() +"' , '大众', 'AD20060005', '公用', '2016-03-16', 5, '人')");
		} catch (SQLException ex) { 
			throw ex;
		}finally {
			DynamicDataSourceCacheManager.clearLookupKey();
		}
	}


	public DyTestTask(DynamicDataSource dynamicDataSource, String lookupKey) {
		super();
		this.dynamicDataSource = dynamicDataSource;
		this.lookupKey = lookupKey;
	}
}
