package com.example.config;

import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @ClassName CacheConfig
 * @Description CacheManager配置类
 * @Author zhang zhengdong
 * @DATE 2024/12/27 18:52
 * @Version 1.0
 */
@Configuration

public class CacheConfig {
	@Bean
	public CacheManager cacheManager() {
		// 创建一个内存缓存管理器
		return new ConcurrentMapCacheManager("myCache");
	}
}
