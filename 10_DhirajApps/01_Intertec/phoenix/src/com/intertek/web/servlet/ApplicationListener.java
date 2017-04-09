package com.intertek.web.servlet;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import com.intertek.entity.ApplicationLevelLock;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.ApplicationLevelLockService;
import com.intertek.util.Constants;

public class ApplicationListener implements ServletContextListener, HttpSessionListener
{
  public void contextInitialized(ServletContextEvent event)
  {
  }

  public void contextDestroyed(ServletContextEvent event)
  {
  }

  public void sessionCreated(HttpSessionEvent se){
  }

  public void sessionDestroyed(HttpSessionEvent se){
	  HttpSession session=se.getSession();
	  if(session!=null){
		  PhoenixSystem.userLoggedOut((String)session.getAttribute("userLoginName"));
		  ApplicationLevelLockService lockingService = (ApplicationLevelLockService) ServiceLocator.getInstance().getBean("lockingService");
		  lockingService.releaseLock((ApplicationLevelLock)session.getAttribute(Constants.MY_JOB_LOCK));
	  }
  }
}
