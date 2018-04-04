package org.aming.dy.support;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.sql.Connection;
import java.util.List;
import java.util.Map;

/**
 * 动态数据源上下文
 *
 * @author aming
 * @create 2018-04-04 17:46
 **/
public class DynamicDataSourceContext {

	private List<String> dataSourceNames = Lists.newArrayList();
	private Map<String, Connection> dataSourceMap = Maps.newHashMap();

	public void addDataSourceName(String dataSourceName) {
		this.dataSourceNames.add(dataSourceName);
	}

	public void addDataSourceMap(String dataSourceName, Connection connection) {
		this.dataSourceMap.put(dataSourceName, connection);
	}

	public List<String> getDataSourceNames() {
		return dataSourceNames;
	}

	public DynamicDataSourceContext setDataSourceNames(List<String> dataSourceNames) {
		this.dataSourceNames = dataSourceNames;
		return this;
	}

	public Map<String, Connection> getDataSourceMap() {
		return dataSourceMap;
	}

	public DynamicDataSourceContext setDataSourceMap(Map<String, Connection> dataSourceMap) {
		this.dataSourceMap = dataSourceMap;
		return this;
	}

	public DynamicDataSourceContext() {
		super();
	}
}
