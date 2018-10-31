package com.huahua.saselomo.common.aspect;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.huahua.saselomo.common.util.LogApp;
/**
 * 日志横切面
 * @author Lin·Y
 *
 */
//@Order(0)
@Aspect
@Component
public class WebLogAspect {
	//切入点
	@Pointcut(value="execution(* com.huahua.saselomo.*.service.impl.*.*(..))")
//	@Pointcut(value="execution(* com.huahua.saselomo.client.service.impl.ClientServiceImpl.*(..))")
	public void aspectMethod(){
		
	}
//	//前置通知
	@Before("aspectMethod()")
	public void logBefore(JoinPoint joinPoint){
		//获取当前请求对象
		HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
		LogApp.getLogger().info("URL: "+request.getRequestURI());
		LogApp.getLogger().info("Http_Method: "+request.getMethod());
		LogApp.getLogger().info("IP: "+request.getRemoteAddr());
		String className = joinPoint.getTarget().toString();//执行的类名
		String methodName = joinPoint.getSignature().getName();//执行的方法名
		LogApp.getLogger().info(className+"类的"+methodName+"方法开始执行...");
	}
	//返回通知
	@AfterReturning(value="aspectMethod()", returning="result")
	public void logAfterReturning(JoinPoint joinPoint, Object result){
		String className = joinPoint.getTarget().toString();//执行的类名
		String methodName = joinPoint.getSignature().getName();//执行的方法名
		LogApp.getLogger().info(className+"类的"+methodName+"返回正常,返回值为: "+result);
	}
	//后置通知
	@After("aspectMethod()")
	public void logAfter(JoinPoint joinPoint){
		String className = joinPoint.getTarget().toString();//执行的类名
		String methodName = joinPoint.getSignature().getName();//执行的方法名
		LogApp.getLogger().info(className+"类的"+methodName+"方法执行结束...");
	}
	//异常通知
	@AfterThrowing(value="aspectMethod()", throwing="exception")
	public void logAfterThrowing(JoinPoint joinPoint, Exception exception){
		String className = joinPoint.getTarget().toString();//执行的类名
		String methodName = joinPoint.getSignature().getName();//执行的方法名
		LogApp.getLogger().error(className+"类的"+methodName+"方法执行异常, 异常为: "+exception.getMessage());
	}
	
}
