package com.intertek.web.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import com.intertek.exception.ServiceException;
import com.intertek.domain.RoleSearch;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.AdminService;

public class SearchUserRolesPopupController extends SimpleFormController
{
		    public SearchUserRolesPopupController() {
			super();
			setCommandClass(RoleSearch.class);
			//setSessionForm(true);
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
			String pageNumberStr = request.getParameter("pageNumber");
			String sortFlag = request.getParameter("sortFlag");
			String searchForm = request.getParameter("searchForm");
	  	    String inputFieldId = request.getParameter("inputFieldId");
		    String rowNum = request.getParameter("rowNum");
		  
		  int pageNumber = 1;
				try
				{
				  pageNumber = new Integer(pageNumberStr).intValue();
				}
				catch(Exception e)
				{
				}
   
	if(pageNumber <= 0) pageNumber = 1;

    RoleSearch search = (RoleSearch)command;
    Pagination pagination = new Pagination(1, 20, pageNumber, 10);
    search.setPagination(pagination);
	search.setSearchForm(searchForm);

	// START : #119240 : 22/06/09
	String pageSort = request.getParameter("checkPageSort");
	    
    boolean changeSortOrder = false; 
    if(pageNumberStr != null && Integer.valueOf(pageNumberStr).intValue() == 1){
    	if(sortFlag != null && !sortFlag.trim().equals("") && pageSort.equals("")){
    		changeSortOrder = true;
    	}else if(sortFlag == null || sortFlag.trim().equals("")){
    		  search.setSortOrderFlag("");
  			  search.setCurrentSortFlag("");
  			  search.setPrevSortFlag("");
    	}
    }
	
	if(pageNumberStr != null && Integer.valueOf(pageNumberStr).intValue() == 1 && changeSortOrder){
		  
		  if(sortFlag != null && !sortFlag.trim().equals("")){
			  search.setCurrentSortFlag(sortFlag);
			  if(search.getCurrentSortFlag()!= null && search.getCurrentSortFlag().trim().equals(search.getPrevSortFlag())){
				  search.setSortOrderFlag("desc");
				  search.setPrevSortFlag("");
			  } else {
				  search.setPrevSortFlag(sortFlag);
				  search.setSortOrderFlag("asc");
			  }
		  } else {
			  search.setSortOrderFlag("");
			  search.setCurrentSortFlag("");
			  search.setPrevSortFlag("");
		  }
	 }
	// END : #119240 : 22/06/09
	
    AdminService adminService = (AdminService)ServiceLocator.getInstance().getBean("adminService");	
   // Role role = new Role();
   // List results = new ArrayList();
	if(sortFlag != null && !sortFlag.trim().equals(""))
    {
    	/*List finalResults = new ArrayList();
    	System.out.println("pageNumberStr="+pageNumberStr);
    	if(pageNumberStr.equals("1"))
    	{*/
    		try
	        {
    			adminService.searchRole(search,sortFlag);
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
	       /* catch(Exception e)
	        {
	          e.printStackTrace();	
	          errors.reject("search.userrole.error", new Object[] {e.getMessage()}, null);
	          return showForm(request, response, errors);
	        }*/



    	
    	request.setAttribute("command", search);
       
        return showForm(request, response, errors);
    }

				try
				{
					adminService.searchRole(search,sortFlag);
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
				/*catch(Exception e)
				{
				  e.printStackTrace();
				  errors.reject("search.userrole.error", new Object[] {e.getMessage()}, null);
				  return showForm(request, response, errors);
				}*/

			request.setAttribute("command", search);

			return showForm(request, response, errors);
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
/*  protected boolean suppressValidation(HttpServletRequest request)
  {
  String searchType = request.getParameter("searchType");
    if((searchType != null) && ("opsBranch".equals(searchType) || "labBranch".equals(searchType) || searchType.startsWith("assoc")))
    {
      return true;
    }

    return super.suppressValidation(request);
  }	*/

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
   */
  protected Object formBackingObject(
    HttpServletRequest request
  ) throws Exception
  {
	
	  String inputFieldId = request.getParameter("inputFieldId");
	  String rowNum = request.getParameter("rowNum");
	  String searchForm=request.getParameter("searchForm");
	  RoleSearch roleSearch = new RoleSearch();
	  roleSearch.setInputFieldId(inputFieldId);
	  roleSearch.setRowNum(rowNum);
	  roleSearch.setSearchForm(searchForm);
	
	  return roleSearch;
  }
}
