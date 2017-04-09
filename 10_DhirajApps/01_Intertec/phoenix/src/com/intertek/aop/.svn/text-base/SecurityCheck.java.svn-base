package com.intertek.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.ProceedingJoinPoint;

import com.intertek.entity.User;
import com.intertek.util.SecurityUtil;

/**
 * An AOP class to intercept the api calls to provide security checking, or loggings.
 *
 **/
public class SecurityCheck
{
  private static final Log logger = LogFactory.getLog(SecurityCheck.class);

  /**
   * Intercept the api calls to provide security checking, or loggings.
   *
   * @param pjp - a join point which contains the api information.
   * @return - the returns from the proceed method of the join point.
   * @throws Throwable
   *
   **/
  public Object doSecurityCheck(ProceedingJoinPoint pjp) throws Throwable
  {
    User user = SecurityUtil.getUser();
    long startTime = 0;
    long endTime = 0;
    long startMemory = 0;
    long endMemory = 0;
    String userName = null;
    String methodName = null;

    if(logger.isInfoEnabled())
    {
      startMemory = Runtime.getRuntime().freeMemory();
      startTime = System.currentTimeMillis();

      if(user != null) userName = user.getLoginName();
      methodName = pjp.getSignature().getName();

      logger.info("-- " + userName + ":" + methodName + ": max memory = " + Runtime.getRuntime().maxMemory());
      logger.info("-- " + userName + ":" + methodName + ": total memory = " + Runtime.getRuntime().totalMemory());
    }

    Object obj = pjp.proceed();

    if(logger.isInfoEnabled())
    {
      endMemory = Runtime.getRuntime().freeMemory();
      endTime = System.currentTimeMillis();

      logger.info("-- " + userName + ":" + methodName + ": timeUsed = " + (endTime - startTime));
      logger.info("-- " + userName + ":" + methodName + ": memoryUsed = " + (startMemory - endMemory));
      logger.info("-- " + userName + ":" + methodName + ": freeMemory = " + endMemory);
    }

    return obj;
  }
}
