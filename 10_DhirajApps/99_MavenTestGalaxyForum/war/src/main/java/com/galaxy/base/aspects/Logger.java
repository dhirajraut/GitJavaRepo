package com.galaxy.base.aspects;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.MethodBeforeAdvice;
import org.springframework.aop.ThrowsAdvice;

public class Logger implements MethodBeforeAdvice, AfterReturningAdvice, ThrowsAdvice {

	public void before(Method method, Object[] args, Object target) throws Throwable {
		StringBuffer logString = new StringBuffer();
		logString.append("Entering Method: ");
		logString.append(getMethodLog(method));
		logString.append("Sending param:");
		logString.append(getArrayLog(args));
		logString.append("Target: ");
		logString.append(target.toString());
		System.out.println(logString);
	}

	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		StringBuffer logString = new StringBuffer();
		logString.append("Returning From Method: ");
		logString.append(getMethodLog(method));
		logString.append("Sending param:");
		logString.append(getArrayLog(args));
		logString.append("ReturnValue: ");
		logString.append(returnValue.toString());
		logString.append("Target: ");
		logString.append(target.toString());
		System.out.println(logString);
	}

	public void afterThrowing (Method method, Object[] args, Object target, Exception ex) {
		StringBuffer logString = new StringBuffer();
		logString.append("Exception From Method: ");
		logString.append(getMethodLog(method));
		logString.append("Sending param:");
		logString.append(getArrayLog(args));
		logString.append("Target: ");
		logString.append(target.toString());
		logString.append("Exception: ");
		logString.append(getExceptionLog(ex));
		System.out.println(logString);
	}

	private String getMethodLog (Method method) {
		StringBuffer logString = new StringBuffer();
		logString.append(method.getName());
		return logString.toString();
	}

	private String getArrayLog (Object[] array) {
		StringBuffer logString = new StringBuffer();
		if (0 == array.length) {
			logString.append(" None.");
		}
		else {
			for (int ctr = 0; ctr < array.length; ctr ++) {
				logString.append(" ");
				logString.append(array[ctr].toString());
			}
		}
		return logString.toString();
	}
	
	private String getExceptionLog (Throwable ex) {
		StringBuffer logString = new StringBuffer();
		logString.append(getArrayLog(ex.getStackTrace()));
		return logString.toString();
	}
}
