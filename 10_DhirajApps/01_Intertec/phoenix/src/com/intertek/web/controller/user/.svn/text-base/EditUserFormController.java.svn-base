package com.intertek.web.controller.user;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.intertek.domain.EditUser;
import com.intertek.entity.Branch;
import com.intertek.entity.BusinessUnit;
import com.intertek.entity.Contract;
import com.intertek.entity.Role;
import com.intertek.entity.User;
import com.intertek.locator.ServiceLocator;
import com.intertek.phoenix.search.entity.UserSearch;
import com.intertek.service.AdminService;
import com.intertek.service.UserService;
import com.intertek.util.Constants;
import com.intertek.web.controller.BaseSimpleFormController;
import com.intertek.web.propertyeditors.CustomPrimitiveNumberEditor;
import com.intertek.exception.ServiceException;

//public class EditUserFormController extends SimpleFormController
public class EditUserFormController extends BaseSimpleFormController
{
  public EditUserFormController() {
    super();
  setSessionForm(true);
    setCommandClass(EditUser.class);
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
    EditUser editUser = (EditUser)command;
    User user = editUser.getUser();

    String password = editUser.getPassword();
	if(password != null && !"".equals(password.trim()) && (password.length()<6 || password.length()>128)) {
		
		errors.reject("password.not.long.enough", new Object[] {}, null);
		return showForm(request, response, errors);
	} else {
		  user.setPassword(password);
		  user.setSalt(null);
	}
    /*if((password != null) && !"".equals(password.trim()))
    {
      user.setPassword(password);
      user.setSalt(null);
    }*/

    UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
    AdminService adminService = (AdminService)ServiceLocator.getInstance().getBean("adminService");
   String addOrDeleteRole = request.getParameter("addOrDeleteRole");
   String indexs = request.getParameter("roleIndex");
    String userRoleFlag=request.getParameter("userRoleFlag");
      String addFlag=request.getParameter("addFlag");

     if((addOrDeleteRole != null) && (Constants.ADD.equals(addOrDeleteRole) || Constants.DELETE.equals(addOrDeleteRole)))
       {
      if(Constants.ADD.equals(addOrDeleteRole))
      {
               editUser.setRoles(addRole(editUser.getRoles()));
      }
      else
      {
        editUser.setRoles(deleteRole(editUser.getRoles(), editUser.getRoleIndex()));
      }
             editUser.setRoleCount(editUser.getRoles().length);

             editUser.setAddOrDeleteRole(Constants.NONE_VALUE);

             editUser.setTabName("1");

           return showForm(request, response, errors);
        }


    if(Constants.NEWVAL.equals(userRoleFlag))
      {

      String rowNo=request.getParameter("rowNum");

      int num=Integer.parseInt(rowNo);
        
      Role[] roleItems = editUser.getRoles();
      Role role=null;
	  try 
    	    { 
        	 role=adminService.getRoleByName( roleItems[num].getName());
    	     } 
				catch(ServiceException e)
				{
				  e.printStackTrace();
				  errors.reject(e.getMessage(), e.getParams(), null);
				  return showForm(request, response, errors);
				}
					catch(Throwable t)
					{
					  t.printStackTrace();
					  errors.reject("generic.error", new Object[] {t.getMessage()}, null);
					  return showForm(request, response, errors);
					}
        roleItems[num] = role;
      editUser.setRoles(roleItems);
      editUser.setUserRoleFlag(Constants.NONE_VALUE);
      return showForm(request, response, errors);
    }

String timeFlag=request.getParameter("timeFlag");
         if(Constants.BRANCH_CHANGE.equals(timeFlag))
      {
        if(editUser.getUser().getBranchName() != null && (!editUser.getUser().getBranchName().trim().equals("")))
        {
			      Branch branch=null;
				  BusinessUnit bu=null;
                 try 
					{ 
					   branch = userService.getBranchByName(editUser.getUser().getBranchName());
                       bu = userService.getBusinessUnitByName(editUser.getUser().getBuName());
					 } 
						catch(ServiceException e)
						{
						  e.printStackTrace();
						  errors.reject(e.getMessage(), e.getParams(), null);
						  return showForm(request, response, errors);
						}
							catch(Throwable t)
							{
							  t.printStackTrace();
							  errors.reject("generic.error", new Object[] {t.getMessage()}, null);
							  return showForm(request, response, errors);
							}
          if(branch != null)
          {
            editUser.getUser().setBranchName(branch.getName());
              editUser.getUser().setPreferredTz(branch.getTimezone());
              editUser.getUser().setBusinessUnit(bu);
              editUser.getUser().setBranch(branch);
          }
        }
        editUser.setTimeFlag(Constants.NONE_VALUE);
        return showForm(request, response, errors);
      }
        Role[] roleItems = editUser.getRoles();

       
      if (roleItems != null )
    {
        user.getRoles().clear();
      for(int i=0; i< roleItems.length; i++)
      {
        Role role = roleItems[i];
        if(role.getName() != null && (!role.getName().trim().equals("")))
        {
					try 
					{ 
					Role existingrole = adminService.getRoleByName(role.getName());
					 } 
						catch(ServiceException e)
						{
						  e.printStackTrace();
						  errors.reject(e.getMessage(), e.getParams(), null);
						  return showForm(request, response, errors);
						}
							catch(Throwable t)
							{
							  t.printStackTrace();
							  errors.reject("generic.error", new Object[] {t.getMessage()}, null);
							  return showForm(request, response, errors);
							}
          user.getRoles().add(role);
        }

      }

    }
    try
    {
      userService.saveUser(user);
	 user = userService.getUserByNameWithOrgHierarchy(user.getLoginName());
    }
	  catch(ServiceException e)
	  {
		e.printStackTrace();
		errors.reject(e.getMessage(), e.getParams(), null);
		return showForm(request, response, errors);
	  }
		  catch(Throwable t)
		  {
			t.printStackTrace();
			errors.reject("generic.error", new Object[] {t.getMessage()}, null);
			return showForm(request, response, errors);
		  }


   // user = userService.getUserByNameWithOrgHierarchy(user.getLoginName()); 

    request.getSession().setAttribute("user", user);

 // START : #119240 : 19/06/09
    
    /*if((addFlag != null) && (Constants.ADD_NEW.equals(addFlag)))
    {
      editUser.setAddFlag(Constants.NONE_VALUE);
      return new ModelAndView(new RedirectView("create_user.htm"));
    }
    else
     {
       ModelAndView modelAndView = new ModelAndView(Constants.COMMON_MESSAGE_R);
       modelAndView.addObject("backUrl", "edit_user.htm?loginName=" + user.getLoginName());
       modelAndView.addObject("backUrlDescription", "You can continue to edit this User.");
       modelAndView.addObject("description", "The User has been successfully updated.");

       return modelAndView;
     }*/
    
    if((addFlag != null) && (Constants.ADD_NEW.equals(addFlag)))
    {
      editUser.setAddFlag(Constants.NONE_VALUE);
      return new ModelAndView(new RedirectView("create_user.htm"));
    }
    
 	String operationType = request.getParameter("operationType");
	String loginName = null;
	ModelAndView modelAndView = null;
	if(operationType!=null && ("prevUser".equals(operationType)
			|| "nextUser".equals(operationType)
		    || "searchUser".equals(operationType) 
		    || "saveUser".equals(operationType))   )
	{
		
		String viewName = null;
		if("searchUser".equals(operationType))
	    {
			viewName = "search-user-r";
	    }
	
	    if(viewName == null)
	    {
	    	viewName = "edit-user-r"; 
	    }

	    modelAndView = new ModelAndView(viewName);
		
	    if ("prevUser".equals(operationType) || "nextUser".equals(operationType)) 
	    {
	    	loginName = this.getPrevOrNextUser(user.getLoginName(), editUser.getUserSearch(),
					"prevUser".equals(operationType));
	    	
			if (loginName == null) {
				if ("prevUser".equals(operationType)) {
					modelAndView.addObject("saved_message",	"No Previous User!");
				} else {
					modelAndView.addObject("saved_message",	"No Next User!");
				}
			}
		}
	    
	    if("saveUser".equals(operationType))
		{
	    	modelAndView.addObject("saved_message", "Saved successfully");		    	
		}		    
	 }

	
 	if(loginName == null)
 		loginName = user.getLoginName();
 	modelAndView.addObject("loginName", loginName);
 	modelAndView.addObject("fromEdit", "true");
 	
 	return modelAndView;
//END : #119240 : 19/06/09
 
  }


  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest, org.springframework.web.bind.ServletRequestDataBinder)
   */
  protected void initBinder(
    HttpServletRequest request,
    ServletRequestDataBinder binder
  ) throws Exception
  {
    SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.MM_DD_YYYY_DATE_FORMAT);
    dateFormat.setLenient(false);
    binder.registerCustomEditor(java.util.Date.class, null, new CustomDateEditor(dateFormat, true));
    NumberFormat nf = NumberFormat.getInstance();

    CustomPrimitiveNumberEditor longEditor = new CustomPrimitiveNumberEditor(Long.class, nf, Long.valueOf(0));
    binder.registerCustomEditor(Long.class, "user.supervisor.id", longEditor);
    binder.registerCustomEditor(Long.class, "user.employee.id", longEditor);


  }

 protected boolean suppressValidation(HttpServletRequest request, Object command)
  {
  String addOrDelete = request.getParameter("addOrDeleteRole");
  String userRoleFlag=    request.getParameter("userRoleFlag");

       if((addOrDelete != null) && (Constants.ADD.equals(addOrDelete) || Constants.DELETE.equals(addOrDelete)))

      {
        return true;
       }
        if((userRoleFlag != null) && (Constants.NEWVAL.equals(userRoleFlag)))
         {
         return true;
        }

        String timeFlag=request.getParameter("timeFlag");
          if((timeFlag != null) && (Constants.BRANCH_CHANGE.equals(timeFlag)))
          {
          return true;
          }

    return super.suppressValidation(request);
   }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
   */
  protected Object formBackingObject(
    HttpServletRequest request
  ) throws Exception
  {

   String loginName=request.getParameter("loginName");
   
    User user = null;
   EditUser editUser = new EditUser();
    UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
    try
    {
     user=userService.getUsersandRolesByName(loginName);
    }
   	catch(ServiceException e)
    {
    	throw new ServiceException(e.getMessage(), e.getParams(), e);    	
    }
    catch(Throwable t)
    {
        t.printStackTrace();
        throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
    }


     if(user != null)
      {
      int i=0;
        if(user.getLanguage()==null)
      {
        user.setLanguage(Constants.ENGLISH);
      }
        String dateFormate = user.getDateFormat();
		if(dateFormate != null && !dateFormate.trim().equals(""))
		{
		String[] pattern = dateFormate.split("/");
	       String month = pattern[1];
	       if(pattern[0].trim().equalsIgnoreCase(Constants.MONTH_MM_FORMATE))
	    	   dateFormate = Constants.MM_DD_YYYY_DATE_FORMAT;  
	       if(month.trim().equalsIgnoreCase(Constants.MONTH_MMM_FORMATE))
	    	   dateFormate = Constants.DD_MMM_YYYY_DATE_FORMAT; 
	       if(month.trim().equalsIgnoreCase(Constants.MONTH_MM_FORMATE))
	    	   dateFormate = Constants.DD_MM_YYYY_DATE_FORMAT; 
		}else
		{
			dateFormate =  Constants.DD_MM_YYYY_DATE_FORMAT; 
		}
		user.setDateFormat(dateFormate);
      Role[] roles = new Role[(user.getRoles()).size()];
      for(Iterator itr=(user.getRoles()).iterator();itr.hasNext();)
      {
       roles[i] =(Role)itr.next();
        i++;
        }
        editUser.setRoles(roles);
    }
     if(user != null)
     {
   editUser.setPassword(user.getPassword());
  editUser.setPasswordConfirmation(user.getPassword());
 /* if(user.getEmployee() == null)
    user.setEmployee(new Employee());*/
  //if(user.getSupervisor() == null)
  //  user.setSupervisor(new Employee());
//  if(user.getSupervisor() == null)
//    user.setSupervisor(new User());


    editUser.setUser(user);
     }

     
    // START : #119240 : 17/06/09
	UserSearch userSearch = null;
    HttpSession session = request.getSession();
    if(session != null)
    {
    	/*userSearch = (UserSearch)session.getAttribute(
    		"com.intertek.web.controller.country.SearchCountryFormController.FORM.command"
    		);*/
    	if(session.getAttribute("userSearch")!=null){
    		userSearch = (UserSearch)session.getAttribute("userSearch");
    	}
    	
    }
  
    editUser.setUserSearch(userSearch);	
	// END : #119240 : 17/06/09
    
  return editUser;
  }
   private Role[] addRole(Role[] roles)
       {
           Role role = new Role();
          Role[] results = null;
          if(roles == null) results = new Role[1];
          else results = new Role[roles.length + 1];
          if(roles != null)
          {
          for(int i=0; i<roles.length; i++)
          {
            results[i] = roles[i];
          }
          }
          results[results.length - 1] = role;
          return results;
        }

        private Role[] deleteRole(Role[] roles, int indexs)
        {
          if(roles == null) return null;
          if(roles.length <= 0) return roles;
          Role[] results = new Role[roles.length - 1];
          int count = 0;
          for(int i=0; i<roles.length; i++)
          {
            if(i == indexs) continue;
            results[count] = roles[i];
            count ++;
          }
          return results;
        }
        
    //START : #119240 : 22/06/09
    public String getPrevOrNextUser(String currentUser, UserSearch userSearch, boolean prev){
      	  
      	  if(currentUser == null || userSearch == null) 
      		  return null;
      	  
      	  AdminService adminService = (AdminService) ServiceLocator.getInstance().getBean("adminService2");
      	  
      	  List<User> results =  (List<User>) userSearch.getResults();
      	  
      	  if(results == null) return null;

      	    for(int i=0; i<results.size(); i++)
      	    {
      	    	User user = (User)results.get(i);
      	    	if(user.getLoginName().equals(currentUser))
      	    	{
      	    		if(prev)
      	    		{
      	    			if(i == 0)
      	    			{
      	    				  int currentPageNum = userSearch.getPagination().getCurrentPageNum();
      	    		            if(currentPageNum > 1)
      	    		            {
      	    		            	userSearch.getPagination().setCurrentPageNum(currentPageNum - 1);
      	    		            	adminService.searchAdminObject(userSearch);
      	    		            	
      		   		              	List newResults = userSearch.getResults();
      		   		              	if(newResults == null) return null;
      	
      		   		              	if(newResults.size() == 0) return null;
      	
      		    		            return ((User)newResults.get(newResults.size() - 1)).getLoginName();
      	    		            }
      	    		            else
      	    		            {
      	    		              return null;
      	    		            }
      	    			}
      	    			return ((User)results.get(i-1)).getLoginName();
      	    		}else{
      	    			
      	    			if(i == results.size() - 1)
      	    			{
      	   				  	int totalPages = userSearch.getPagination().getTotalPages();
      	    		        int currentPageNum = userSearch.getPagination().getCurrentPageNum();
      	    		        if(currentPageNum < totalPages)
      	    		        {
      	    		        	userSearch.getPagination().setCurrentPageNum(currentPageNum + 1);
      	    		        	adminService.searchAdminObject(userSearch);
      	    		        	List newResults = userSearch.getResults();
      	    		        	if(newResults == null) return null;
      	
          		              	if(newResults.size() == 0) return null;

          		              	return ((User)newResults.get(0)).getLoginName();
      	    		        }else
      	    		        {
      	    		        	return null;
      	    		        }
      	    			}
      	    			
      	    			return ((User)results.get(i+1)).getLoginName();	    			
      	    		}
      	    	}	    	
      	    }
      	  
      	  return null;
        }
  //END : #119240 : 22/06/09
}
