package com.intertek.web.controller.user; 

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.entity.User;
import com.intertek.util.SecurityUtil;


 public class PhoenixDashBoardController extends SimpleFormController
{
	 public PhoenixDashBoardController() {
			super();
			setSessionForm(true);
			setCommandClass(User.class);
	  }
 private ServletConfig config;

  
  protected Object formBackingObject(
		    HttpServletRequest request
		  ) throws Exception
		  {
	  		User user = SecurityUtil.getUser();
	  		return user;
		  }
		  
}
