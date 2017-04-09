package com.intertek.web.controller.user;

import java.text.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.entity.*;
import com.intertek.service.*;
import com.intertek.util.*;

public class LoginFormController extends SimpleFormController
{
  public LoginFormController() {
    super();
    setCommandClass(User.class);
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
   */
  protected ModelAndView onSubmit(
    HttpServletRequest request,
    HttpServletResponse response,
    Object command,
    BindException errors
  ) throws Exception
  {
    User user = (User)command;
    UserService userService = (UserService)getWebApplicationContext().getBean("userService");
    try
    {
      user = userService.login(user.getLoginName(), user.getPassword());
    }
    catch(Exception e)
    {
      e.printStackTrace();

      errors.reject("login.error", new Object[] {e.getMessage()}, null);
      return showForm(request, response, errors);
    }

    if(!Constants.STATUS_A.equals(user.getStatus()))
    {
      errors.reject("login.error.account.not.activated", null);
      return showForm(request, response, errors);
    }

    // put into session
    request.getSession().setAttribute("user", user);

    return new ModelAndView(getSuccessView());
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest, org.springframework.web.bind.ServletRequestDataBinder)
   */
  protected void initBinder(
    HttpServletRequest request,
    ServletRequestDataBinder binder
  ) throws Exception
  {
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
   */
  protected Object formBackingObject(
    HttpServletRequest request
  ) throws Exception
  {
    return new User();
  }
}
