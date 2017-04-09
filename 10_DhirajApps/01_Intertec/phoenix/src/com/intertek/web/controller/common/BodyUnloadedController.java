package com.intertek.web.controller.common;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.entity.ApplicationLevelLock;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.ApplicationLevelLockService;
import com.intertek.util.Constants;

public class BodyUnloadedController extends SimpleFormController{

  public BodyUnloadedController() {
  }

  protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception{
	  
	  HttpSession session=request.getSession(false);
	  if(session!=null){
		  ApplicationLevelLockService lockingService = (ApplicationLevelLockService) ServiceLocator.getInstance().getBean("lockingService");			
		  ApplicationLevelLock myJobLock=(ApplicationLevelLock)session.getAttribute(Constants.MY_JOB_LOCK);				
		  lockingService.releaseLock(myJobLock);
	  }	  
	  return showForm(request, response, errors);
  }
  
  protected Object formBackingObject(HttpServletRequest request) throws Exception{
	  return "";
  }
  
}
