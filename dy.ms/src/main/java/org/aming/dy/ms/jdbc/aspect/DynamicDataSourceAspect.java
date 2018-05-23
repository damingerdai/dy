package org.aming.dy.ms.jdbc.aspect;

import org.aming.dy.ms.jdbc.annotation.TargetDataSource;
import org.aming.dy.ms.jdbc.support.DynamicDataSourceLookupKeyHolder;
import org.aming.dy.ms.utils.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Description: 动态数据源拦截
 * User: daming
 * Date: 2018-05-21
 * Time: 20:17
 * Description:
 */
@Aspect
@Component
public class DynamicDataSourceAspect {

    @Before("@annotation(ds)")
    public void changeDataSource(JoinPoint joinpoint, TargetDataSource ds) {
        boolean isParse = ds.isParse();
        if (isParse) {
            String dataSource = ds.dataSource();
            if (StringUtils.isRegMatch(dataSource, "^#{\\w}$")) {
                changeDataSourceWithExpressionLookupKey(joinpoint, dataSource);
            } else if(StringUtils.isRegMatch(dataSource, "^#\\d$")) {
                changeDataSourceWithIndexExpressionLookupKey(dataSource, joinpoint.getArgs());
            } else {
                changeDataSourceWithCommonLookupKey(dataSource);
            }
        } else {
            changeDataSourceWithCommonLookupKey(ds.dataSource());
        }
    }

    private void changeDataSourceWithExpressionLookupKey(JoinPoint joinpoint, String dataSource) {
        String dataSourceParam = dataSource.substring(2, dataSource.length() -1);
        if (joinpoint.getSignature() instanceof MethodSignature) {
            MethodSignature methodSignature = (MethodSignature)joinpoint.getSignature();
            String[] parameterNames = methodSignature.getParameterNames();
            for (int i = 0; i < parameterNames.length; i++) {
                if (parameterNames[i].equals(dataSourceParam)) {
                    Object[] args = joinpoint.getArgs();
                    if (Objects.nonNull(args) && args.length > 0 && args.length < i +1) {
                        Object dataSourceArg = args[i];
						DynamicDataSourceLookupKeyHolder.setDataSource(String.valueOf(dataSourceArg));
                        return;
                    } else {
                        throw new RuntimeException("fail to find datasource lookupKey");
                    }
                }
            }
        }
    }

    private void changeDataSourceWithCommonLookupKey(String lookupKey) {
		DynamicDataSourceLookupKeyHolder.setDataSource(lookupKey);
    }

    private void changeDataSourceWithIndexExpressionLookupKey(String lookupKey, Object[] args) {
        int argIndex = Integer.parseInt(lookupKey.substring(1, lookupKey.length()));
        if (Objects.nonNull(args) && args.length > 0 && args.length < argIndex +1) {
            Object dataSourceArg = args[argIndex];
			DynamicDataSourceLookupKeyHolder.setDataSource(String.valueOf(dataSourceArg));
        } else {
            throw new RuntimeException("fail to find datasource lookupKey");
        }
    }


    @After("@annotation(ds)")
    public void restoreDataSource(JoinPoint point, TargetDataSource ds) {
		DynamicDataSourceLookupKeyHolder.clearDataSource();
    }
}
