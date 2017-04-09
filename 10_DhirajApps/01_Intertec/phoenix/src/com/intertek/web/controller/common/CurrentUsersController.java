package com.intertek.web.controller.common;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.web.servlet.PhoenixSystem;

public class CurrentUsersController extends SimpleFormController{

  public CurrentUsersController() {
  }

  protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception{
	  Map map=new HashMap();
	  String hang=request.getParameter("hang");
	  if(hang!=null && hang.equals("true")){
		  map.put("users", PhoenixSystem.logOffUsers);
	  }
	  else{
		  map.put("users", PhoenixSystem.getUsers());
	  }
	  String body=request.getParameter("body");
	  String view="current_users";
	  if(body!=null && body.equals("true")){
		  view="current_users_body";
	  }
	  else if(body!=null && body.equals("all")){
		  view="current_users_all";
	  }
	  ModelAndView modelAndView = new ModelAndView(view, map);
	  return modelAndView;
  }
}
