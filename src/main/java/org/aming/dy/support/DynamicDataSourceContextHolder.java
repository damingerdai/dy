package org.aming.dy.support;

import com.google.common.collect.Maps;

import java.sql.Connection;
import java.util.Map;

/**
 * 动态数据源上下文信息
 *
 * @author aming
 * @create 2018-04-04 13:29
 **/
public class DynamicDataSourceContextHolder {

    private static final ThreadLocal<Map<String, Connection>> contextHolder = ThreadLocal.withInitial(Maps::newHashMap);

    public static void clearDataSourceType() {
        contextHolder.remove();
    }
}
