package com.intertek.web.controller.user;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.phoenix.search.entity.PermissionSearch;
import com.intertek.service.AdminService;
import com.intertek.service.UserService;
import com.intertek.web.controller.BaseSimpleFormController;
import com.intertek.exception.ServiceException;

//public class EditPermissionListFormController extends SimpleFormController
public class EditPermissionListFormController extends BaseSimpleFormController
{
         public EditPermissionListFormController() {
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
	   
   Permission permission=addPermissionList.getPermission();
	     Link[] links =	 addPermissionList.getLinks();

	    if((addOrDeletePermission != null) && ("add".equals(addOrDeletePermission) || "delete".equals(addOrDeletePermission)))
       {
			if("add".equals(addOrDeletePermission))
			{
				addPermissionList.setLinks(addPermissionList(addPermissionList.getLinks()));
			}
			else
			{
				addPermissionList.setLinks(deletePermissionList(addPermissionList.getLinks(), addPermissionList.getPermissionIndex(),permission));
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



                   try 
					{ 					
        	         adminService.savePermission(permission);
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
List existingLinks=adminService.getLinkByPermissionName(permission.getName());

if(existingLinks != null)
{
for(int j=0;j<existingLinks.size();j++)
{
Link existingLink = (Link) existingLinks.get(j);
                try 
					{ 
					 existingLink = adminService.getLinkByName(existingLink.getName());
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
Boolean deleteFlag = true;
for(int i=0;i<linkItems.length;i++)
{
 Link newLink = linkItems[i];
if(newLink.getName().trim().equals(existingLink.getName()) && deleteFlag)
{
		deleteFlag = false;
}
else if(deleteFlag && (!newLink.getName().trim().equals(existingLink.getName())))
deleteFlag = true;
}
if(deleteFlag)
	{
		existingLink.getPermissions().remove(permission);
		 try 
					{ 					
        	         adminService.saveLink(existingLink);
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

	   try
    {
   }

    catch(Exception e)
    {
      e.printStackTrace();

      errors.reject("create.permissionlist.error ", new Object[] {e.getMessage()}, null);
      return showForm(request, response, errors);
    }
	
    // START : #119240 : 22/06/09
    
    /* ModelAndView modelAndView = new ModelAndView("common-message-r");
    modelAndView.addObject("backUrl", "edit_permission_list.htm?name=" + permission.getName());
    modelAndView.addObject("backUrlDescription", "You can continue to edit this Permission.");
    modelAndView.addObject("description", "Your permission has been successfully created.");*/

    String operationType = request.getParameter("operationType");
	String name = null;
	ModelAndView modelAndView = null;
	if(operationType!=null && ("prevPermission".equals(operationType)
			|| "nextPermission".equals(operationType)
		    || "searchPermission".equals(operationType) 
		    || "savePermission".equals(operationType))   )
	{
		
		String viewName = null;
		if("searchPermission".equals(operationType))
	    {
			viewName = "search-permission-list-r";
	    }
		/*if("saveCountry".equals(operationType))
		{
			viewName = "";
		}*/

	    if(viewName == null)
	    {
	    	viewName = "edit-permission-list-r"; 
	    }

	    modelAndView = new ModelAndView(viewName);
		
	    if ("prevPermission".equals(operationType) || "nextPermission".equals(operationType)) 
	    {
	    	name = this.getPrevOrNextPermission(permission.getName(), addPermissionList.getPermissionSearch(),
					"prevPermission".equals(operationType));
	    	
			if (name == null) {
				if ("prevPermission".equals(operationType)) {
					modelAndView.addObject("saved_message",	"No Previous Permission!");
				} else {
					modelAndView.addObject("saved_message",	"No Next Permission!");
				}
			}
		}
	    
	    if("savePermission".equals(operationType))
		{
	    	modelAndView.addObject("saved_message", "Saved successfully");		    	
		}		    
	 }

	// System.out.println("Session in OS : ----- " + request.getSession().getAttribute("permissionSearch"));
 
 	if(name == null)
 		name = permission.getName();
 	modelAndView.addObject("name", name);
 	modelAndView.addObject("fromEdit", "true");
    // END : #119240 : 22/06/09
    
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
    String addOrDelete = request.getParameter("addOrDeletePermission");
    String controlFlag=request.getParameter("controlFlag");
    if((addOrDelete != null) && ("add".equals(addOrDelete) || "delete".equals(addOrDelete)))
    {
      return true;
    }
   if((controlFlag != null) && ("newval".equals(controlFlag)))
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
	   UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
		AdminService adminService=(AdminService)ServiceLocator.getInstance().getBean("adminService");
	    AddPermissionList addPermissionList = new AddPermissionList();
	    String permName= request.getParameter("name");
		Permission permission=adminService.getPermissionByName(permName);
		addPermissionList.setPermission(permission);
	   
        Link link=new Link();  
		List link1=new ArrayList();
		try{
  
		 link1=adminService.getLinkByPermissionName(permName);
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
	    
		if(link1 != null)
	    {
		int i=0;		
		Link[] links=new Link[link1.size()];
		for(Iterator itr=link1.iterator();itr.hasNext();)
    				{
				
		 	 links[i] =(Link)itr.next();
		     i++;
			}
			addPermissionList.setLinks(links);
		}
		
		// START : #119240 : 22/06/09
		PermissionSearch permissionSearch = null;
	    HttpSession session = request.getSession();
	    if(session != null)
	    {
	    	if(session.getAttribute("permissionSearch")!=null){
	    	//	System.out.println("Session : permissionSearch :--- "+ session.getAttribute("permissionSearch"));
	    		permissionSearch = (PermissionSearch)session.getAttribute("permissionSearch");
			}
	    }
	    addPermissionList.setPermissionSearch(permissionSearch);
		// END : #119240 : 22/06/09
		
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



		    
private Link[] deletePermissionList(Link[] links, int indexs, Permission perm)
{
  AdminService adminService = (AdminService) ServiceLocator.getInstance().getBean("adminService");
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

//START : #119240 : 22/06/09
public String getPrevOrNextPermission(String currentName, PermissionSearch permissionSearch, boolean prev){
  	  
  	  if(currentName == null || permissionSearch == null) 
  		  return null;
  	  
  	  AdminService adminService = (AdminService) ServiceLocator.getInstance().getBean("adminService2");
  	  
  	  List<Permission> results =  (List<Permission>) permissionSearch.getResults();
  	  
  	  if(results == null) return null;

  	    for(int i=0; i<results.size(); i++)
  	    {
  	    	Permission permission = (Permission)results.get(i);
  	    	if(permission.getName().equals(currentName))
  	    	{
  	    		if(prev)
  	    		{
  	    			if(i == 0)
  	    			{
  	    				  int currentPageNum = permissionSearch.getPagination().getCurrentPageNum();
  	    		            if(currentPageNum > 1)
  	    		            {
  	    		            	permissionSearch.getPagination().setCurrentPageNum(currentPageNum - 1);
  	    		            	adminService.searchAdminObject(permissionSearch);
  	    		            	
  		   		              	List newResults = permissionSearch.getResults();
  		   		              	if(newResults == null) return null;
  	
  		   		              	if(newResults.size() == 0) return null;
  	
  		    		            return ((Permission)newResults.get(newResults.size() - 1)).getName();
  	    		            }
  	    		            else
  	    		            {
  	    		              return null;
  	    		            }
  	    			}
  	    			return ((Permission)results.get(i-1)).getName();
  	    		}else{
  	    			
  	    			if(i == results.size() - 1)
  	    			{
  	   				  	int totalPages = permissionSearch.getPagination().getTotalPages();
  	    		        int currentPageNum = permissionSearch.getPagination().getCurrentPageNum();
  	    		        if(currentPageNum < totalPages)
  	    		        {
  	    		        	permissionSearch.getPagination().setCurrentPageNum(currentPageNum + 1);
  	    		        	adminService.searchAdminObject(permissionSearch);
  	    		        	List newResults = permissionSearch.getResults();
  	    		        	if(newResults == null) return null;
  	
      		              	if(newResults.size() == 0) return null;

      		              	return ((Permission)newResults.get(0)).getName();
  	    		        }else
  	    		        {
  	    		        	return null;
  	    		        }
  	    			}
  	    			
  	    			return ((Permission)results.get(i+1)).getName();	    			
  	    		}
  	    	}	    	
  	    }
  	  
  	  return null;
    }
//END : #119240 : 22/06/09


}


  
