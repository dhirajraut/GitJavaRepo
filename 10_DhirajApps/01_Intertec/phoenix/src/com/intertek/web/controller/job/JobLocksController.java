package com.intertek.web.controller.job;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.entity.ApplicationLevelLock;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.ApplicationLevelLockService;

public class JobLocksController extends SimpleFormController{

  public JobLocksController() {
  }

  protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception{
	  ApplicationLevelLockService lockingService = (ApplicationLevelLockService) ServiceLocator.getInstance().getBean("lockingService");
	  
	  String releaseAll=request.getParameter("releaseAll");
	  if(releaseAll!=null && releaseAll.equalsIgnoreCase("true")){
		  lockingService.releaseAll("JOB");
	  }
	  else{
		  String jobNumberToRelease=request.getParameter("jobNumberToRelease");
		  if(jobNumberToRelease!=null && !jobNumberToRelease.trim().isEmpty()){
			  lockingService.releaseLock("JOB", jobNumberToRelease);
		  }
	  }
	  
	  Map<String, List<ApplicationLevelLock>> map=new HashMap<String, List<ApplicationLevelLock>>();
	  List<ApplicationLevelLock> myLocks=lockingService.getCurrentLocks("JOB");
	  if(myLocks!=null){
		  map.put("myLocks", myLocks);
	  }
		
	  ModelAndView modelAndView = new ModelAndView("job-locks", map);
	  return modelAndView;
  }
}

