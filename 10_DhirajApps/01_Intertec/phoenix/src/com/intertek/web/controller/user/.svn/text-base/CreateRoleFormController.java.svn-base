package com.intertek.web.controller.user;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.intertek.domain.AddRole;
import com.intertek.entity.Permission;
import com.intertek.entity.Role;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.AdminService;
import com.intertek.web.controller.BaseSimpleFormController;
import com.intertek.exception.ServiceException;


public class CreateRoleFormController extends BaseSimpleFormController



//public class CreateRoleFormController extends SimpleFormController
{
  public CreateRoleFormController() {
    super();
	setSessionForm(true);
    setCommandClass(AddRole.class);
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
    AddRole addRole= (AddRole)command;
   
   String addOrDeleteRole = request.getParameter("addOrDeleteRole");
	String indexs = request.getParameter("roleIndex");
    Role role = addRole.getRole();
	AdminService adminService = (AdminService)ServiceLocator.getInstance().getBean("adminService");
	
	if((addOrDeleteRole != null) && ("add".equals(addOrDeleteRole) || "delete".equals(addOrDeleteRole)))
    {
		System.out.println(" Adding rows");
			if("add".equals(addOrDeleteRole))
			{
				addRole.setPermissions(addPermissions(addRole.getPermissions()));
			}
			else
			{
				addRole.setPermissions(deletePermissions(addRole.getPermissions(), addRole.getRoleIndex()));
			}
		  addRole.setRoleCount(addRole.getPermissions().length);

		  addRole.setAddOrDeleteRole("none");
		  addRole.setTabName("0");
		
		  
      return showForm(request, response, errors);
    } 




		String roleFlag=request.getParameter("roleFlag");
		

		if("newval".equals(roleFlag)){

			String rowNo=request.getParameter("rowNum");
			int num=Integer.parseInt(rowNo);
			System.out.println("rownumber in onsubmit  "+rowNo);

			Permission[] permissionsItems =addRole .getPermissions();
			Permission permission = new Permission();
			 try 
					{ 
					 permission=adminService.getPermissionByName(permissionsItems[num].getName());
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

			permissionsItems[num] =permission;
			addRole .setPermissions(permissionsItems);
			addRole.setRoleFlag("none");
			return showForm(request, response, errors);
       }
	Permission permission;
	Permission[] permissionItems = addRole.getPermissions();
        
		if (permissionItems   != null )
		{
			
			
			for(int i=0; i< permissionItems.length; i++)
			{
				 permission = permissionItems[i];	
				 if(permission.getName() != null && (!permission.getName().trim().equals("")))		
				
				{
					//Permission existingPerms = adminService.getPermissionByName(permission.getName());
					Permission existingPerms=null;
					try 
					{ 
					 existingPerms = adminService.getAddPermissionByName(permission.getName());
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
					role.getPermissions().add(permission);
				}
			}
		}
    
    try
    {
	 if(role.getName()!=null && (!role.getName().trim().equals("")))
      adminService.addRole(role);
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

	// START : #119240 : 24/06/09		
    /*ModelAndView modelAndView = new ModelAndView("common-message-r");
    modelAndView.addObject("backUrl", "edit_role.htm?role.name=" + role.getName());
    modelAndView.addObject("backUrlDescription", "You can continue to edit this Role.");
    modelAndView.addObject("description", "Your role has been successfully created.");*/
	
	String viewName = "edit-role-r"; 		
	ModelAndView modelAndView = new ModelAndView(viewName);
	modelAndView.addObject("role.name", role.getName());
	modelAndView.addObject("saved_message", "Saved successfully");		

    // END : #119240 : 24/06/09
	
    return modelAndView;
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest, org.springframework.web.bind.ServletRequestDataBinder)
   */
  protected void initBinder(
    HttpServletRequest request,
    ServletRequestDataBinder binder
  ) throws Exception
  {
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    dateFormat.setLenient(false);
    binder.registerCustomEditor(java.util.Date.class, null, new CustomDateEditor(dateFormat, true));
  }
  protected boolean suppressValidation(HttpServletRequest request)
  {
    String addOrDelete = request.getParameter("addOrDeleteRole");
    String roleFlag=request.getParameter("roleFlag");
    if((addOrDelete != null) && ("add".equals(addOrDelete) || "delete".equals(addOrDelete)))
    {
      return true;
    }
   
	if((roleFlag != null) && ("newval".equals(roleFlag)))
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
	 // System.out.println("Session : roleSearch : ----- " + session.getAttribute("roleSearch"));
	 if(session.getAttribute("roleSearch")!=null)
		 session.removeAttribute("roleSearch");
	 // END : #119240 : 26/06/09
	  
    AddRole addRole = new AddRole();

       String countStr = request.getParameter("roleCount");


	  Permission permission    = new Permission();
		Role role          = new Role();
		
		
		if( countStr == null || countStr.trim().equals("") )
			  countStr = "0";

			int count = 0;
			try
			{
				count = new Integer(countStr).intValue();
			}
			catch(Exception e) { }

			 
			 addRole.setRoleCount(count);
		     Permission[] permissions = new Permission[count];
			 for(int i=0; i<count; i++)
			{
				permissions[i] = permission;
			}
		
		addRole.setPermissions(permissions); 
		addRole.setRole(role);

    return addRole;
  }

   private Permission[] addPermissions(Permission[] permissions){
		
		Permission permission = new Permission();
		
		Permission[] results = null;
		
		if(permissions == null) results = new Permission[1];
		else results = new Permission[permissions.length + 1];
		if(permissions != null)
		{
		for(int i=0; i<permissions.length; i++)
		{
			results[i] = permissions[i];
		}
		}

		results[results.length - 1] = permission;

		return results;
	}

  private Permission[] deletePermissions(Permission[] permissions, int index){
		if(permissions == null) return null;
		if(permissions.length <= 0) return permissions;

		Permission[] results = new Permission[permissions.length - 1];

		int count = 0;
		for(int i=0; i<permissions.length; i++)
		{
			if(i == index) continue;
			results[count] = permissions[i];

			count ++;
		}

		return results;
	}

}
