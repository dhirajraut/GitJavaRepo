package com.galaxy.base.aspects;

import java.lang.reflect.Method;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;

public class LoggerAdvice implements MethodBeforeAdvice, AfterReturningAdvice {

//	public Object invoke(MethodInvocation arg0) throws Throwable {
//		System.out.println("Before" + arg0.getMethod().getName());
//		arg0.proceed();
//		System.out.println("After" + arg0.getMethod().getName());
//		return true;
//	}

	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		StringBuffer sb = new StringBuffer();
		sb.append("Starting ");
		sb.append(target.getClass().getName());
		sb.append(": ");
		sb.append(method.getName());
		sb.append(". Arguments - ");
		for (int ctr = 0; ctr < args.length; ctr++) {
			sb.append(args[ctr] + " ");
		}
		System.out.println(sb.toString());
	}

	public void afterReturning(Object returnValue, Method method, Object[] args,
			Object target) throws Throwable {

		StringBuffer sb = new StringBuffer();
		sb.append("Exiting ");
		sb.append(target.getClass().getName());
		sb.append(": ");
		sb.append(method.getName());
		sb.append(". Returning - ");
		sb.append(returnValue);
		System.out.println(sb.toString());
	}

}
