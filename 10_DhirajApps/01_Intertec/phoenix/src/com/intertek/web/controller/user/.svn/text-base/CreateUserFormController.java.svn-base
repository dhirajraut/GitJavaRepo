package com.intertek.web.controller.user;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.intertek.exception.ServiceException;
import com.intertek.domain.SignupUser;
import com.intertek.entity.Branch;
import com.intertek.entity.BusinessUnit;
import com.intertek.entity.JobCode;
import com.intertek.entity.Role;
import com.intertek.entity.User;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.AdminService;
import com.intertek.service.UserService;
import com.intertek.util.Constants;
import com.intertek.util.SecurityUtil;
import com.intertek.web.controller.BaseSimpleFormController;
import com.intertek.web.propertyeditors.CustomPrimitiveNumberEditor;

//public class CreateUserFormController extends SimpleFormController
public class CreateUserFormController extends BaseSimpleFormController
{
  public CreateUserFormController() {
    super();
	 setSessionForm(true);	 
    setCommandClass(SignupUser.class);
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
    SignupUser signupUser = (SignupUser)command;
    User user = signupUser.getUser();

    UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
    AdminService adminService = (AdminService)ServiceLocator.getInstance().getBean("adminService");
	 String addOrDeleteRole = request.getParameter("addOrDeleteRole");
	 String indexs = request.getParameter("roleIndex");
	 String addFlag=request.getParameter("addFlag");
	  
		
		 if((addOrDeleteRole != null) && (Constants.ADD.equals(addOrDeleteRole) || Constants.DELETE.equals(addOrDeleteRole)))
       {
			if(Constants.ADD.equals(addOrDeleteRole))
			{				
				signupUser.setRoles(addRole(signupUser.getRoles()));
			}
			else
			{
				signupUser.setRoles(deleteRole(signupUser.getRoles(), signupUser.getRoleIndex()));
			}
		         signupUser.setRoleCount(signupUser.getRoles().length);
								
		         signupUser.setAddOrDeleteRole(Constants.NONE_VALUE);

		         signupUser.setTabName("1");

      return showForm(request, response, errors);
    } 	
			
	String userRoleFlag=request.getParameter("userRoleFlag");
		  if(Constants.NEWVAL.equals(userRoleFlag))
		   {
			String rowNo=request.getParameter("rowNum");
			int num=Integer.parseInt(rowNo);
       
		   	Role[] roleItems = signupUser.getRoles();
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
			signupUser.setRoles(roleItems);
			signupUser.setUserRoleFlag(Constants.NONE_VALUE);
			return showForm(request, response, errors);
			}	
		  
    	String timeFlag=request.getParameter("timeFlag");
         if(Constants.BRANCH_CHANGE.equals(timeFlag))
		  {
			  if(signupUser.getUser().getBranchName() != null && (!signupUser.getUser().getBranchName().trim().equals("")))
			  {
				  Branch branch=new Branch();
				  BusinessUnit bu=new BusinessUnit();
					try 
					{ 
					  branch = userService.getBranchByName(signupUser.getUser().getBranchName());
					  bu = userService.getBusinessUnitByName(signupUser.getUser().getBuName());
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
					  signupUser.getUser().setBranchName(branch.getName());
				  	  signupUser.getUser().setPreferredTz(branch.getTimezone());
				  	  signupUser.getUser().setBusinessUnit(bu);
				  	  signupUser.getUser().setBranch(branch);
				  }
			  }
			  signupUser.setTimeFlag(Constants.NONE_VALUE);
  			  signupUser.setTabName("0");
			  return showForm(request, response, errors);
			  
		  } 

		   	Role[] roleItems = signupUser.getRoles();
	
		  if (roleItems != null )
		{
			for(int i=0; i< roleItems.length; i++)
			{
				Role role = roleItems[i];
				if(role.getName() != null && (!role.getName().trim().equals("")))
				{
					Role existingrole=null;
					try 
					{ 
					 existingrole = adminService.getRoleByName(role.getName());
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
	String strPwd = signupUser.getPassword();

	if(strPwd != null && !"".equals(strPwd.trim()) && (strPwd.length()<6 || strPwd.length()>128)) {
		
		errors.reject("password.not.long.enough", new Object[] {}, null);
		return showForm(request, response, errors);
	}
	 user.setPassword(signupUser.getPassword());
     user = userService.addUser(user);
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

 if((addFlag != null) && (Constants.ADD_NEW.equals(addFlag)))
 {
   signupUser.setAddFlag(Constants.NONE_VALUE);
   return new ModelAndView(new RedirectView("create_user.htm"));
 }
 else
  {
	 // START : #119240 : 24/06/09
    /*ModelAndView modelAndView = new ModelAndView(Constants.COMMON_MESSAGE_R);
    modelAndView.addObject("backUrl", "edit_user.htm?loginName=" + user.getLoginName());
    modelAndView.addObject("backUrlDescription", "You can continue to edit this user.");
    modelAndView.addObject("description", "Your user has been successfully created.");

    return modelAndView;
    */
	
	// return showForm(request, response, errors);
	 
	 ModelAndView modelAndView = new ModelAndView("edit-user-r");
	 modelAndView.addObject("loginName", user.getLoginName());
	 modelAndView.addObject("saved_message", "Saved successfully");
	 return modelAndView;
	// END : #119240 : 24/06/09
  }
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
    super.initBinder(request, binder);
    binder.registerCustomEditor(java.util.Date.class, null, new CustomDateEditor(dateFormat, true));
    NumberFormat nf = NumberFormat.getInstance();
    CustomPrimitiveNumberEditor longEditor = new CustomPrimitiveNumberEditor(Long.class, nf, Long.valueOf(0));
    binder.registerCustomEditor(Long.class, "user.employee.id", longEditor);


  }

  

		 protected boolean suppressValidation(HttpServletRequest request, Object command)
				  {
					String addOrDelete = request.getParameter("addOrDeleteRole");
						String userRoleFlag=request.getParameter("userRoleFlag");
				   
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
	  // START : #119240 : 26/06/09
	  HttpSession session = request.getSession();
	  if(session.getAttribute("userSearch")!=null)
		  session.removeAttribute("userSearch");
	  // END : #119240 : 26/06/09
	  
    SignupUser signupUser = new SignupUser();
    User user = new User();

		User supervisor=new User();user.setSupervisor(user);

		JobCode jobCode =new JobCode(); 
		user.setJobCode(jobCode);
	   Branch branch = new Branch();
	   BusinessUnit bu = new BusinessUnit();
		branch.setBusinessUnit(bu);user.setBranch(branch);
		user.setLanguage(Constants.ENGLISH);
		user.setProductType(Constants.HYDRO);

    if(!Constants.POST.equals(request.getMethod()))
    {
      //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      //User user = userService.getUserByNameWithOrgHierarchy(SecurityUtil.getUser().getLoginName());
/*      String buName = userService.getFirstBusinessUnitNameByDivisionName(
        SecurityUtil.getUser().getBranch().getBusinessUnit().getOrganization().getName());
      bu.setName(buName);
	  user.setBuName(buName);   */
       UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
   try 
    { 
        bu.setName(userService.getUserByNameWithOrgHierarchy(SecurityUtil.getUser().getLoginName()).getBuName());
			user.setBuName(userService.getUserByNameWithOrgHierarchy(SecurityUtil.getUser().getLoginName()).getBuName());
			user.setPreferredTz(userService.getUserByNameWithOrgHierarchy(SecurityUtil.getUser().getLoginName()).getPreferredTz());
			user.setBranchName(userService.getUserByNameWithOrgHierarchy(SecurityUtil.getUser().getLoginName()).getBranchName());
			user.setBranch(userService.getUserByNameWithOrgHierarchy(SecurityUtil.getUser().getLoginName()).getBranch());
			user.setBusinessUnit(userService.getUserByNameWithOrgHierarchy(SecurityUtil.getUser().getLoginName()).getBusinessUnit());
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
	}
    signupUser.setUser(user);

		  String roleCountStr=request.getParameter("roleCount"); 
	       
			   if( roleCountStr== null ||roleCountStr.trim().equals("") ) 
				roleCountStr = "0";
				 int roleCount=0;
				
						
							try {roleCount= new Integer(roleCountStr).intValue();	}
		            
									catch(Exception e) { }

									signupUser.setRoleCount(roleCount);
		            
									Role[] roles= new Role[roleCount];
				
													
									for(int i=0; i<roleCount; i++)
										{
											Role role = new Role();
											roles[i] = role;
										}

			 signupUser.setRoles(roles);
			return signupUser;
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
}
