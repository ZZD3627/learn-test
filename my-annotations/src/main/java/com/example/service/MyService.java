package com.example.service;

import com.example.annotation.LogExecutionTime;
import com.example.annotation.MyCacheable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Service;

/**
 * @ClassName MyService
 * @Description Service实体
 * @Author zhang zhengdong
 * @DATE 2024/12/27 16:20
 * @Version 1.0
 */
@Service
public class MyService {

	@Autowired
	private CacheManager cacheManager;

	@LogExecutionTime
	public void performTask() {

		try {
			System.out.println("执行开始");
			Thread.sleep(1000);
			System.out.println("执行结束");
		} catch (InterruptedException e) {
			throw new RuntimeException(e);
		}
	}


	@MyCacheable(value = "myCache", key = "userList")
	public String getUserList() {
		System.out.println("Fetching user list from database...");
		return "User list";
	}

	public Object getCachedData(String cacheName, String key) {
		Cache cache = cacheManager.getCache(cacheName);
		if (cache != null) {
			return cache.get(key, Object.class);
		}
		return null;
	}


	public Object getEmail(String email) {

		System.out.println(email);
		return email;
	}
}
