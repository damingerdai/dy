package org.aming.dy.ms.service;

import org.aming.dy.ms.base.pojo.Car;
import org.aming.dy.ms.dao.DyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Description: 动态数据源服务
 * User: daming
 * Date: 2018-05-23
 * Time: 20:52
 * Description:
 */
@Service
public class DyService {

	@Autowired
	private DyDao dyDao;

	public List<Car> get() {
		return dyDao.get();
	}

	public List<Car> getDy2() {
		return dyDao.get("dy2");
	}

}
