package org.aming.dy.test;

import com.zaxxer.hikari.HikariDataSource;
import org.aming.dy.core.DynamicDataSource;
import org.aming.dy.test.task.DyTestTask;
import org.junit.Test;

/**
 * Description: 动态数据源测试
 * User: daming
 * Date: 2018-06-04
 * Time: 20:31
 */
public class DynamicDataSourceThreadApplication {
	@Test
	public void test() {
		System.out.println("test");
	}

	@Test
	public  void test1( ) {
		DynamicDataSource dynamicDataSource = getDynamicDataSource();
		String[] lookupKeys = new String[] { "dy1", "dy2", "dy3"};
		Thread[] dy1Threads = new Thread[1000];
		Thread[] dy2Threads = new Thread[1000];
		Thread[] dy3Threads = new Thread[1000];

		for (int i=0; i<dy1Threads.length; i++) {
			Runnable runnable = new DyTestTask(dynamicDataSource, "dy1");
			dy1Threads[i] = new Thread(runnable);
			dy1Threads[i].start();
		}
		System.out.println("dy1 is finished");
		for (int i=0; i<dy2Threads.length; i++) {
			Runnable runnable = new DyTestTask(dynamicDataSource, "dy2");
			dy2Threads[i] = new Thread(runnable);
			dy2Threads[i].start();
		}
		System.out.println("dy2 is finished");
		for (int i=0; i<dy3Threads.length; i++) {
			Runnable runnable = new DyTestTask(dynamicDataSource, "dy3");
			dy3Threads[i] = new Thread(runnable);
			dy3Threads[i].start();
		}
		System.out.println("dy3 is finished");
		for(Thread thread : dy1Threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		for(Thread thread : dy2Threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		for(Thread thread : dy3Threads) {
			try {
				thread.join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}

		System.out.println("Test is finished");
	}

	private  DynamicDataSource getDynamicDataSource() {
		DynamicDataSource dynamicDataSource = new DynamicDataSource();

		HikariDataSource masterDataSource = new HikariDataSource();
		masterDataSource.setPoolName("master");
		masterDataSource.setUsername("root");
		masterDataSource.setPassword("267552");
		masterDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/greatwall?serverTimezone=GMT%2B8");
		dynamicDataSource.setMasterDataSource(masterDataSource);

		HikariDataSource dy1 = new HikariDataSource();
		dy1.setPoolName("dy1");
		dy1.setUsername("root");
		dy1.setPassword("267552");
		dy1.setJdbcUrl("jdbc:mysql://localhost:3306/dy1?serverTimezone=GMT%2B8");
		dynamicDataSource.addSlaveDataSource("dy1", dy1);

		HikariDataSource dy2 = new HikariDataSource();
		dy2.setPoolName("dy2");
		dy2.setUsername("root");
		dy2.setPassword("267552");
		dy2.setJdbcUrl("jdbc:mysql://localhost:3306/dy2?serverTimezone=GMT%2B8");
		dynamicDataSource.addSlaveDataSource("dy2", dy2);

		HikariDataSource dy3 = new HikariDataSource();
		dy3.setPoolName("dy3");
		dy3.setUsername("root");
		dy3.setPassword("267552");
		dy3.setJdbcUrl("jdbc:mysql://localhost:3306/dy3?serverTimezone=GMT%2B8");
		dynamicDataSource.addSlaveDataSource("dy3", dy3);
		return dynamicDataSource;
	}


}
