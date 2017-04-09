package com.intertek.web.controller.user;

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

import com.intertek.domain.AddRole;
import com.intertek.entity.Permission;
import com.intertek.entity.Role;
import com.intertek.entity.User;
import com.intertek.locator.ServiceLocator;
import com.intertek.phoenix.search.entity.RoleSearch;
import com.intertek.phoenix.search.entity.UserSearch;
import com.intertek.service.AdminService;
import com.intertek.web.controller.BaseSimpleFormController;
import com.intertek.exception.ServiceException;
import com.intertek.util.SecurityUtil;
import com.intertek.util.Constants;


public class EditRoleFormController extends BaseSimpleFormController


//public class EditRoleFormController extends SimpleFormController
{
  public EditRoleFormController() {
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
		System.out.println("vatRateflag is "+roleFlag);

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
			addRole.setPermissions(permissionsItems);
			addRole.setRoleFlag("none");
			return showForm(request, response, errors);
       }

	Permission[] permissionItems = addRole.getPermissions();
		
		if (permissionItems   != null )
		{
			
			role.getPermissions().clear();
			for(int i=0; i< permissionItems.length; i++)
			{
				Permission permission = permissionItems[i];	
				
				 if(permission.getName() != null && (!permission.getName().trim().equals("")))		
						
					{
					//	Permission existingPerms = adminService.getPermissionByName(permission.getName());
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
    System.out.println("Role permisssions" + role.getPermissions().size());

    try
    {
		 if(role.getName()!=null && (!role.getName().trim().equals("")))
         adminService.saveRole(role);
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

			
	// START : #119240 : 23/06/09		
    /*ModelAndView modelAndView = new ModelAndView("common-message-r");
    modelAndView.addObject("backUrl", "edit_role.htm?role.name=" + role.getName());
    modelAndView.addObject("backUrlDescription", "You can continue to edit this Role.");
    modelAndView.addObject("description", "Your role has been successfully updated.");*/

			String operationType = request.getParameter("operationType");
			String roleName = null;
			ModelAndView modelAndView = null;
			if(operationType!=null && ("prevRole".equals(operationType)
					|| "nextRole".equals(operationType)
				    || "searchRole".equals(operationType) 
				    || "saveRole".equals(operationType))   )
			{
				
				String viewName = null;
				if("searchRole".equals(operationType))
			    {
					viewName = "search-role-r";
			    }
				
			    if(viewName == null)
			    {
			    	viewName = "edit-role-r"; 
			    }

			    modelAndView = new ModelAndView(viewName);
				
			    if ("prevRole".equals(operationType) || "nextRole".equals(operationType)) 
			    {
			    	roleName = this.getPrevOrNextRole(role.getName(), addRole.getRoleSearch(),
							"prevRole".equals(operationType));
			    	
					if (roleName == null) {
						if ("prevRole".equals(operationType)) {
							modelAndView.addObject("saved_message",	"No Previous Role!");
						} else {
							modelAndView.addObject("saved_message",	"No Next Role!");
						}
					}
				}
			    
			    if("saveRole".equals(operationType))
				{
			    	modelAndView.addObject("saved_message", "Saved successfully");		    	
				}		    
			 }

		//	System.out.println("Session in OS : ----- " + request.getSession().getAttribute("roleSearch"));
		 
		 	if(roleName == null)
		 		roleName = role.getName();
		 	modelAndView.addObject("role.name", roleName);		
		 	modelAndView.addObject("fromEdit", "true");
	// END : #119240 : 23/06/09
			
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
    AddRole addRole = new AddRole();
	AdminService adminService = (AdminService)ServiceLocator.getInstance().getBean("adminService");
       String countStr = request.getParameter("roleCount");


	  Permission permission    = new Permission();
		Role role = null;
		String roleName= request.getParameter("role.name");
		  
		  //Checking viewRole permission and disable
		  boolean disable = SecurityUtil.isAnyGranted(new String[] {Constants.VIEW_ROLE});
		  addRole.setViewOnly(disable == true);
		  if(addRole.getViewOnly() == true)
		  addRole.setStyleVisibility("visibility:hidden");
		  //End
			try
			{
			role = adminService.getRoleByName(roleName);
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
		if(role != null)
	    {
			Permission[] permissions = new Permission[(role.getPermissions()).size()];
			int i=0;
		   
			
			
			for(Iterator itr=(role.getPermissions()).iterator();itr.hasNext();)
			{
			
			 permissions[i] =(Permission)itr.next();
		     i++;
			}
			addRole.setPermissions(permissions);
		
		
		
		if( countStr == null || countStr.trim().equals("") )
			  countStr = "0";

			int count = 0;
			try
			{
				count = new Integer(countStr).intValue();
			}
			catch(Exception e) { }

			System.out.println("Count = " + count);
			 
			 addRole.setRoleCount(count);
		    /* Permission[] permissions = new Permission[count];
			 for(int i=0; i<count; i++)
			{
				permissions[i] = permission;
			} */
		
		addRole.setPermissions(permissions); 
		addRole.setRole(role);
	    }
		
		// START : #119240 : 23/06/09
		RoleSearch roleSearch = null;
	    HttpSession session = request.getSession();
	    if(session != null)
	    {
	    	if(session.getAttribute("roleSearch")!=null){
	    		roleSearch = (RoleSearch)session.getAttribute("roleSearch");
	    	}	    	
	    }
	 //   System.out.println("Session in FB : ----- " + session.getAttribute("roleSearch"));
	    addRole.setRoleSearch(roleSearch);	
		// END : #119240 : 23/06/09
		
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

  
//START : #119240 : 23/06/09
  public String getPrevOrNextRole(String currentRole, RoleSearch roleSearch, boolean prev){
    	  
    	  if(currentRole == null || roleSearch == null) 
    		  return null;
    	  
    	  AdminService adminService = (AdminService) ServiceLocator.getInstance().getBean("adminService2");
    	  
    	  List<Role> results =  (List<Role>) roleSearch.getResults();
    	  
    	  if(results == null) return null;

    	    for(int i=0; i<results.size(); i++)
    	    {
    	    	Role role = (Role)results.get(i);
    	    	if(role.getName().equals(currentRole))
    	    	{
    	    		if(prev)
    	    		{
    	    			if(i == 0)
    	    			{
    	    				  int currentPageNum = roleSearch.getPagination().getCurrentPageNum();
    	    		            if(currentPageNum > 1)
    	    		            {
    	    		            	roleSearch.getPagination().setCurrentPageNum(currentPageNum - 1);
    	    		            	adminService.searchAdminObject(roleSearch);
    	    		            	
    		   		              	List newResults = roleSearch.getResults();
    		   		              	if(newResults == null) return null;
    	
    		   		              	if(newResults.size() == 0) return null;
    	
    		    		            return ((Role)newResults.get(newResults.size() - 1)).getName();
    	    		            }
    	    		            else
    	    		            {
    	    		              return null;
    	    		            }
    	    			}
    	    			return ((Role)results.get(i-1)).getName();
    	    		}else{
    	    			
    	    			if(i == results.size() - 1)
    	    			{
    	   				  	int totalPages = roleSearch.getPagination().getTotalPages();
    	    		        int currentPageNum = roleSearch.getPagination().getCurrentPageNum();
    	    		        if(currentPageNum < totalPages)
    	    		        {
    	    		        	roleSearch.getPagination().setCurrentPageNum(currentPageNum + 1);
    	    		        	adminService.searchAdminObject(roleSearch);
    	    		        	List newResults = roleSearch.getResults();
    	    		        	if(newResults == null) return null;
    	
        		              	if(newResults.size() == 0) return null;

        		              	return ((Role)newResults.get(0)).getName();
    	    		        }else
    	    		        {
    	    		        	return null;
    	    		        }
    	    			}
    	    			
    	    			return ((Role)results.get(i+1)).getName();	    			
    	    		}
    	    	}	    	
    	    }
    	  
    	  return null;
      }
//END : #119240 : 23/06/09
}
