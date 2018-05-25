package org.aming.dy.support;

import org.aming.dy.tx.DynamicDataSourceTransactionManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

import java.sql.SQLException;

@Aspect
public class DynamicTransactionAspect {

	@AfterReturning("@annotation(dt)")
	public void commitConnections(JoinPoint joinPoint, DynamicTransactional dt) throws SQLException {
		DynamicDataSourceTransactionManager.commitConnections();
		DynamicDataSourceTransactionManager.removeConnections();
	}

	@AfterThrowing("@annotation(dt)")
	public void rollbackConnections(JoinPoint joinPoint, DynamicTransactional dt) throws SQLException {
		DynamicDataSourceTransactionManager.rollbackConnections();
		DynamicDataSourceTransactionManager.removeConnections();
	}
}
