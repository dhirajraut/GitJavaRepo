package com.intertek.web.servlet;

import java.util.Properties;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.intertek.entity.ApplicationLevelLock;
import com.intertek.entity.User;
import com.intertek.exception.LockedByOtherUserException;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.ApplicationLevelLockService;
import com.intertek.util.Constants;
import com.intertek.util.SecurityUtil;

public class LockingInterceptor implements HandlerInterceptor{
	private Properties jobLockRequiredUrls=new Properties();
	
	public void setJobLockRequiredUrls(Properties jobLockRequiredUrls){
		this.jobLockRequiredUrls=jobLockRequiredUrls;
	}
	
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object obj, Exception exception)throws Exception {
	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object obj, ModelAndView modelAndView)throws Exception {
	}

	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object obj)throws Exception {
		try{
			HttpSession session=request.getSession(false);
			if(session==null){
				return true;
			}
			
			String actionUrl=request.getRequestURI();
			String contextPath=request.getContextPath();
			if(actionUrl.startsWith(contextPath)){
				actionUrl=actionUrl.substring(contextPath.length());
			}
			
			ApplicationLevelLockService lockingService = (ApplicationLevelLockService) ServiceLocator.getInstance().getBean("lockingService");			
			String targetUrl=jobLockRequiredUrls.getProperty(actionUrl);
			if(targetUrl==null){
				ApplicationLevelLock myJobLock=(ApplicationLevelLock)session.getAttribute(Constants.MY_JOB_LOCK);				
				lockingService.releaseLock(myJobLock);
				session.removeAttribute(Constants.MY_JOB_LOCK);
			}
			else{
				ApplicationLevelLock myJobLock=(ApplicationLevelLock)session.getAttribute(Constants.MY_JOB_LOCK);				
				String jobNumber=request.getParameter("jobNumber");
				if((jobNumber==null || jobNumber.trim().isEmpty()) && myJobLock!=null){
					jobNumber=myJobLock.getApplicationLevelLockId().getKey();
				}
				
				if(jobNumber!=null && !jobNumber.trim().isEmpty()){
					User user = SecurityUtil.getUser();
					String userId=user.getLoginName();
					try{
						ApplicationLevelLock newLock=lockingService.getLock("JOB", jobNumber, userId);
						if(myJobLock!=null && !myJobLock.equals(newLock)){
							lockingService.releaseLock(myJobLock);
						}
				    	session.setAttribute(Constants.MY_JOB_LOCK, newLock);
					}
					catch(LockedByOtherUserException locked){
						String queryString=request.getQueryString();
						response.sendRedirect(contextPath + targetUrl+"?"+queryString+"&jobLockedBy="+locked.getMessage());
						return false;
					}
				}
			}
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		return true;
	}
}
