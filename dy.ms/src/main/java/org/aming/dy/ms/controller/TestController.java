package org.aming.dy.ms.controller;

import org.aming.dy.ms.base.pojo.Car;
import org.aming.dy.ms.service.DyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 测试Controller
 *
 * @athur aming
 * @date 2018-05-23 09:03
 */
@RestController
public class TestController {

	@Autowired
	private DyService dyService;

	@GetMapping(path = "dy")
	public List<Car> get() {
		return dyService.get();
	}

	@GetMapping(path = "dy2")
	public List<Car> get2() {
		return dyService.getDy2();
	}
}
