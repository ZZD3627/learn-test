package com.example.aspect;

import com.example.annotation.LogExecutionTime;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @ClassName LoggingAspect
 * @Description 记录方法执行时间，反向代理
 * @Author zhang zhengdong
 * @DATE 2024/12/27 16:07
 * @Version 1.0
 */
@Aspect
@Component
public class LoggingAspect {

	// 定义一个切点，匹配所有标注了 @LogExecutionTime 的方法
	@Pointcut("@annotation(com.example.annotation.LogExecutionTime)")
	public void logExecutionTimeMethods() {
	}


	@Around("logExecutionTimeMethods()")
	public Object logExecutionTime(ProceedingJoinPoint joinPoint) throws Throwable {
		long start = System.currentTimeMillis();

		// 执行目标方法
		Object proceed = joinPoint.proceed();

		long executionTime = System.currentTimeMillis() - start;
		System.out.println(joinPoint.getSignature() + " executed in " + executionTime + "ms");

		return proceed;
	}
}
