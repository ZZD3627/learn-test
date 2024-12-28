package com.example.aspect;


import com.example.annotation.MyCacheable;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;
import org.springframework.cache.Cache;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName CacheAspect
 * @Description 缓存切面
 * @Author zhang zhengdong
 * @DATE 2024/12/27 16:43
 * @Version 1.0
 */
@Aspect
@Component
public class CacheAspect {

	private CacheManager cacheManager;

	public CacheAspect(CacheManager cacheManager) {
		this.cacheManager = cacheManager;
	}

	@Pointcut("@annotation(com.example.annotation.MyCacheable)")
	public void cacheableMethods() {
	}

	@Around("cacheableMethods()")
	public Object cacheMethod(ProceedingJoinPoint joinPoint) throws Throwable {
		MyCacheable cacheable = getCacheableAnnotation(joinPoint);

		String cacheName = cacheable.value();
		String key = cacheable.key();

		// 获取 CacheManager 中的缓存
		Cache cache = cacheManager.getCache(cacheName);

		if (cache == null) {
			throw new IllegalArgumentException("Cache '" + cacheName + "' not found in CacheManager");
		}

		Cache.ValueWrapper cacheValue = cache.get(key);
		if (cacheValue != null) {
			System.out.println("Returning cached value for key: " + key);
			return cacheValue.get();
		}

		//执行目标方法并缓存结果
		Object result = joinPoint.proceed();
		cache.put(key, result);

		System.out.println(String.format("Caching value for key :%s ", key));
		return result;
	}

	private MyCacheable getCacheableAnnotation(ProceedingJoinPoint joinPoint) {
		// 获取方法签名
		MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();

		// 获取目标方法所属的类
		Class<?> targetClass = joinPoint.getTarget().getClass();

		Method method = null;
		try {
			// 获取目标方法
			method = targetClass.getMethod(methodSignature.getName(), methodSignature.getParameterTypes());
		} catch (NoSuchMethodException e) {
			// 处理方法未找到的异常
			e.printStackTrace();
		}

		if (method != null) {
			// 获取并返回方法上的 MyCacheable 注解
			return method.getAnnotation(MyCacheable.class);
		} else {
			return null;
		}
	}

}
