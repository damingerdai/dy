package org.aming.dy.support;

import com.google.common.collect.Maps;

/**
 * 动态数据源上下文信息
 *
 * @author aming
 * @create 2018-04-04 13:29
 **/
public class DynamicDataSourceContextHolder {

    private static final ThreadLocal<DynamicDataSourceContext> contextHolder = ThreadLocal.withInitial(DynamicDataSourceContext::new);

    public static void clearDataSources() {
        contextHolder.remove();
    }

    public static void addDataSource(String dataSourceName) {
		DynamicDataSourceContext context = contextHolder.get();
		context.addDataSourceName(dataSourceName);
	}

	public static DynamicDataSourceContext getDataSourceContext() {
    	return contextHolder.get();
	}
}
