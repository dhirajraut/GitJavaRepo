package com.intertek.web.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.intertek.entity.User;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.AdminService;

public class PermissionTag extends SimpleTagSupport
{
  private String action;

  public String getAction()
  {
    return action;
  }

  public void setAction(String action)
  {
    this.action = action;
  }

  public void doTag() throws JspException, IOException
  {
    User user = (User)getJspContext().getAttribute("user", PageContext.SESSION_SCOPE);

    AdminService adminService = (AdminService)ServiceLocator.getInstance().getBean("adminService");
    if(adminService.isAuthorized(action, user))
    {
      getJspBody().invoke(null);
    }
  }
}
