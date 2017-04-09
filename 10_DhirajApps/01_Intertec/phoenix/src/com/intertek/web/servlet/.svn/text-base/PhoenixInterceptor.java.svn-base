package com.intertek.web.servlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.intertek.entity.User;
import com.intertek.util.SecurityUtil;
import com.intertek.web.servlet.PhoenixSystem.ActionUrl;

public class PhoenixInterceptor implements HandlerInterceptor{
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception exception)throws Exception {
		userUrlCompletion(request, response, obj, exception);
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView exception)throws Exception {
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj)throws Exception {
		collectUserUrl(request, response, obj);
		return true;
	}
	
	private void userUrlCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception exception){
	    try{
	    	ActionUrl aUrl=(ActionUrl)request.getAttribute("myActionUrl");
	    	if(aUrl!=null){
	    		aUrl.setEndTime(System.currentTimeMillis());
	    	}
	    }
	    catch(Exception e){	
	    }
	}
	
	private void collectUserUrl(HttpServletRequest request, HttpServletResponse response, Object obj){
		try{
			userUrlCompletion(request, response, obj, null);
			
	    	User user = SecurityUtil.getUser();
	    	String actionUrl=request.getRequestURI();
	    	if(actionUrl.toLowerCase().indexOf("logout.htm")<0){
	    		String jxcel=request.getParameter("jxcel");
	    		if(jxcel!=null){
	    			actionUrl=actionUrl+"(jxcel="+jxcel+")";
	    		}
	    		ActionUrl aUrl=PhoenixSystem.addStartAction(user, actionUrl);
	    		request.setAttribute("myActionUrl", aUrl);
	    	}
	    	HttpSession session=request.getSession(false);
	    	if(session!=null){
	    		session.setAttribute("userLoginName", user.getLoginName());
	    	}
		}
		catch(Exception e){
			//e.printStackTrace();
		}
	}
}
