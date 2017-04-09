package com.intertek.web.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.domain.ChangeUser;
import com.intertek.entity.User;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.UserService;

public class ChangeUserFormController extends SimpleFormController
{
  public ChangeUserFormController() {
    super();
	 setCommandClass(ChangeUser.class);
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
	  ChangeUser changeUser = (ChangeUser) command;
	  String oldUserName = changeUser.getOldUser();
	  String newUserName = changeUser.getNewUser();

    UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");

    if(oldUserName == null || oldUserName.trim().equals(""))
    {
    	errors.reject("change.user.error", new Object[] {"Please enter the old user name"}, null);
    	return showForm(request, response, errors);
    }
    if(newUserName == null || newUserName.trim().equals(""))
    {
    	errors.reject("change.user.error", new Object[] {"Please enter the new user name"}, null);
    	return showForm(request, response, errors);
    }
    
    //Check to see if old user already exists in the system
    User user = userService.getUserByNameWithRoles(oldUserName);
    if(user==null)
    {
       	errors.reject("change.user.error", new Object[] {"User :"+oldUserName+" does not exist in Phoenix"}, null);
    	return showForm(request, response, errors);
    }
    try{
    //Create a new User object with the new user name
    
    user = userService.changeUserLoginName(oldUserName, newUserName);
    
    }
    catch(Exception e)
    {
    	errors.reject("change.user.error", new Object[] {e.toString()}, null);
        return showForm(request, response, errors);
    }
    ModelAndView modelAndView = new ModelAndView("common-message-r");
    modelAndView.addObject("backUrl", "edit_user.htm?loginName=" + newUserName);
    modelAndView.addObject("backUrlDescription", "You can continue to edit this user.");
    modelAndView.addObject("description", "Your user has been successfully updated.");

    return modelAndView;
  }
  

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
   */
  protected Object formBackingObject(
    HttpServletRequest request
  ) throws Exception
  {
    ChangeUser changeUser = new ChangeUser();

			return changeUser;
  }
}
