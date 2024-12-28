package com.example.controller;

//import com.example.annotation.ValidEmail;

import com.example.entity.MyObject;
import com.example.service.MyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @ClassName TestController
 * @Description 页面测试
 * @Author zhang zhengdong
 * @DATE 2024/12/27 15:38
 * @Version 1.0
 */
@RestController
public class TestController {

	@Autowired
	private MyObject object;

	@Autowired
	private MyService service;

	@GetMapping("/hello")
	private String hello() {
		return object.display();
	}

	@GetMapping("/perform")
	private String performTest() {
		service.performTask();
		return "执行完成";
	}


	@GetMapping("/cache")
	private String cacheTest() {
		return service.getUserList();
	}


	@GetMapping("/cacheData")
	private Object cacheDataTest(@RequestParam String cacheName, String key) {
		return service.getCachedData(cacheName, key);
	}

}
