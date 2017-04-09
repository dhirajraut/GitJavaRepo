package com.intertek.web.controller.user;

import java.util.Iterator;
import java.util.Set;

import javax.servlet.ServletConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.RedirectView;

import com.intertek.entity.Permission;
import com.intertek.entity.Role;
import com.intertek.entity.User;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.UserService;
import com.intertek.util.Constants;
import com.intertek.util.SecurityUtil;


 public class RedirectFormController extends AbstractController
{

 private ServletConfig config;
//  private ServletContext servletContext;


  public RedirectFormController() {
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
   */
  protected ModelAndView handleRequestInternal(
    HttpServletRequest request,
    HttpServletResponse response
  ) throws Exception
  {
    User user = SecurityUtil.getUser();
    String jobType = user.getJobType();
    String url = user.getUrl();
    UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
    user = userService.getUserByNameWithPermissions(user.getLoginName());
    boolean createJobPermFlag = false;

    if(user != null)
    {
    	Set roles = user.getRoles();
    	if(roles != null)
    	{
    		Iterator iter = roles.iterator();
    		while(iter.hasNext())
    		{
    			Role role = (Role) iter.next();
    			Set permissions = role.getPermissions();
    			if(permissions != null)
    			{
    				Iterator permIter = permissions.iterator();
    				while(permIter.hasNext())
    				{
    					Permission permission = (Permission) permIter.next();
    					if(permission != null)
    					{
    						if(!createJobPermFlag && permission.getName().equals("CreateJob"))
    							createJobPermFlag = true;
    					}
    				}
    			}
    		}
    	}
    }

    String defaultURL = Constants.DEFAULT_URL;
    
    if(url == null || url.trim().equals(""))
    {
    	if(createJobPermFlag)
    	{
	      if(jobType != null)
	      {
	        if(jobType.equals("FST"))
	        {
	          url = "create_job_entry_analytical_service.htm";
	        }
	        else if(jobType.equals("OUT"))
	        {
	          url = "create_job_entry_outsourcing.htm";
	        }
	        else if(jobType.equals("MAR"))
	        {
	          url = "create_job_entry_marine.htm";
	        }
	        else
	        {
	          url = "create_job_entry_insp.htm";
	        }
	      }
	      else
	      {
	    	  url = "create_job_entry_insp.htm";
	      }
    	}
    	else
    	{
    		url = defaultURL;
    	}
    }
    try
    {
	    if(url.startsWith("/")) url = request.getContextPath() + url;
	    return new ModelAndView(new RedirectView(url));
    }
    catch(Exception e)
    {
    	return new ModelAndView(new RedirectView(defaultURL));
    }
  }
}
