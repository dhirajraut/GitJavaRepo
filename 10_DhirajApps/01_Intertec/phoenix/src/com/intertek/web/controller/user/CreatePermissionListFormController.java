package com.intertek.web.controller.user;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.intertek.domain.AddPermissionList;
import com.intertek.entity.Link;
import com.intertek.entity.Permission;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.AdminService;
import com.intertek.service.UserService;
import com.intertek.web.controller.BaseSimpleFormController;
import com.intertek.exception.ServiceException;

//public class CreatePermissionListFormController extends SimpleFormController
public class CreatePermissionListFormController extends BaseSimpleFormController

{
  public CreatePermissionListFormController() {
    super();
		 setSessionForm(true);	 
setCommandClass(AddPermissionList.class);
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
	  AddPermissionList addPermissionList = (AddPermissionList)command;
      AdminService adminService = (AdminService) ServiceLocator.getInstance().getBean("adminService");
	   UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
	  String addOrDeletePermission = request.getParameter("addOrDeletePermission");
	   String indexs = request.getParameter("permissionIndex");
	  
	    if((addOrDeletePermission != null) && ("add".equals(addOrDeletePermission) || "delete".equals(addOrDeletePermission)))
       {
			if("add".equals(addOrDeletePermission))
			{
				addPermissionList.setLinks(addPermissionList(addPermissionList.getLinks()));
			}
			else
			{
				addPermissionList.setLinks(deletePermissionList(addPermissionList.getLinks(), addPermissionList.getPermissionIndex()));
			}
		  addPermissionList.setPermissionCount(addPermissionList.getLinks().length);

		  addPermissionList.setAddOrDeletePermission("none");
		
      return showForm(request, response, errors);
    } 



    String controlFlag=request.getParameter("controlFlag");
  	
			
			if("newval".equals(controlFlag)){

			String rowNo=request.getParameter("rowNum");
			int num=Integer.parseInt(rowNo);
	
		   Link[] linkItems = addPermissionList.getLinks();
		   Link link = new Link();
		          try 
					{ 
					link=adminService.getLinkByControl(linkItems[num].getName());
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
		   linkItems[num] = link;
		   addPermissionList.setLinks(linkItems);
		   addPermissionList.setControlFlag("none");
		   return showForm(request, response, errors);
			}


	Link[] linkItems = addPermissionList.getLinks();
	    if (linkItems != null )
		{
			for(int i=0; i< linkItems.length; i++)
			{
				Link  link = linkItems[i];
			    if(link.getName() != null && (!link.getName().trim().equals("")))
				{
                    try 
					{ 
					 Link existinglink = adminService.getLinkByName(link.getName());
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
			          }
               }	  
		}

	 Permission permission=addPermissionList.getPermission();
//	Link[] links =	 addPermissionList.getLinks();

 if(permission.getName()!=null && (!permission.getName().trim().equals("")))	
	  {
	               try 
					{ 
					adminService.addPermission(permission);
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
	  }
	  else
	  {
      errors.reject("create.permissionlist.error", new Object[] {}, null);
      return showForm(request, response, errors);
	  }
//	Link[] linkItems = addPermissionList.getLinks();
	    if (linkItems != null )
		{
			for(int i=0; i< linkItems.length; i++)
			{
				Link  link = linkItems[i];
			    if(link.getName() != null && (!link.getName().trim().equals("")))
				{
					 try 
					{ 
					 Link existinglink = adminService.getLinkByName(link.getName());
		    		 existinglink.getPermissions().add(permission);
        	         adminService.saveLink(existinglink);
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
					
			 	/*existinglink.getPermissions().add(permission);
				adminService.saveLink(existinglink);*/
			}
          }	  
		}
	// START : #119240 : 24/06/09	  
    /*ModelAndView modelAndView = new ModelAndView("common-message-r");
    modelAndView.addObject("backUrl", "edit_permission_list.htm?name=" + permission.getName());
    modelAndView.addObject("description", "The PermissionLists   has been successfully saved.");
    modelAndView.addObject("backUrlDescription", "You can continue to edit this Permission.");*/
	    
    String viewName = "edit-permission-list-r"; 		
	ModelAndView modelAndView = new ModelAndView(viewName);
	modelAndView.addObject("name", permission.getName());
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

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
   */
  protected Object formBackingObject(
    HttpServletRequest request
  ) throws Exception
  {
	  AddPermissionList addPermissionList = new AddPermissionList();
	  Permission permission=new Permission();
	  String permissionCountStr=request.getParameter("permissionCount"); 
		
		   if( permissionCountStr== null ||permissionCountStr.trim().equals("") ) 
		    permissionCountStr = "0";
	      	 int permissionCount=0;
				try {permissionCount= new Integer(permissionCountStr).intValue();	}
		            catch(Exception e) { }

					  addPermissionList.setPermissionCount(permissionCount);
		              Link[] links = new Link[permissionCount];
						for(int i=0; i<permissionCount; i++)
							{
						  Link link = new Link();
								links[i] = link;
							}
					addPermissionList.setLinks(links);
					addPermissionList.setPermission(permission);
				
					// START : #119240 : 26/06/09
					HttpSession session = request.getSession();
					//System.out.println("Session : permissionSearch : ----- " + session.getAttribute("permissionSearch"));
					if(session.getAttribute("permissionSearch")!=null)
						session.removeAttribute("permissionSearch");
					// END : #119240 : 26/06/09
   return addPermissionList;
  }
   private Link[] addPermissionList(Link[] links)
 {
	   Link link = new Link();
		Link[] results = null;
		if(links == null) results = new Link[1];
		else results = new Link[links.length + 1];
		if(links != null)
		{
		for(int i=0; i<links.length; i++)
		{
			results[i] = links[i];
		}
		}
		results[results.length - 1] = link;
		return results;
	}



		    
  private Link[] deletePermissionList(Link[] links, int indexs)
  {
		if(links == null) return null;
		if(links.length <= 0) return links;
		Link[] results = new Link[links.length - 1];
		int count = 0;
		for(int i=0; i<links.length; i++)
		{
			if(i == indexs) continue;
			results[count] = links[i];
			count ++;
		}
		return results;
	}
  }
