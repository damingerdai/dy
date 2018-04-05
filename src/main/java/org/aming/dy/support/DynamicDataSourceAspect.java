package org.aming.dy.support;

import org.aming.dy.annotation.TargetDataSource;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;

@Aspect
public class DynamicDataSourceAspect {

	@Before("@annotation(ds)")
	public void changeDataSource(JoinPoint joinpoint, TargetDataSource ds) {
		String name = ds.name();
		String access = ds.access().getValue();
		DynamicDataSourceContextHolder.setDataSource(name + '_' + access);
	}

	@After("@annotation(ds)")
	public void restoreDataSource(JoinPoint point, TargetDataSource ds) {
		DynamicDataSourceContextHolder.clearDataSource();
	}

}
