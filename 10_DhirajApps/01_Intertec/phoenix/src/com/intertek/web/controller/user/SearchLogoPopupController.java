package com.intertek.web.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import com.intertek.exception.ServiceException;
import com.intertek.domain.BranchSearch;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.UserService;

public class SearchLogoPopupController extends SimpleFormController
{
  public SearchLogoPopupController() {
    super();
    setCommandClass(BranchSearch.class);
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
	String searchForm = request.getParameter("formName");
	String sortFlag = request.getParameter("sortFlag");

    int pageNumber = 1;
    try
    {
      pageNumber = new Integer(pageNumberStr).intValue();
    }
    catch(Exception e)
    {
    }
    if(pageNumber <= 0) pageNumber = 1;

    BranchSearch search = (BranchSearch)command;
    Pagination pagination = new Pagination(1, 20, pageNumber, 10);
    search.setPagination(pagination);
	search.setSearchForm(searchForm);
	search.setPageNo(pageNumberStr);

    UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
   // Logo logo = new Logo();
	//List results = new ArrayList();

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

	if(sortFlag != null && !sortFlag.trim().equals(""))
		    {
		    	//List finalResults = new ArrayList();
		    	
		    	//if(pageNumberStr.equals("1"))
		    	//{
		    		try
			        {
		    			userService.searchLogo(search,sortFlag);
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
						errors.reject("search.logo.error", new Object[] { e.getMessage() }, null);
						return showForm(request, response, errors);
			        }*/
		    	//}
		    /*	results = search.getTotalResults();
		    	
		    	Pagination logoPagination = new Pagination(1, 20, pageNumber,10);
		    	
		    	search.setPagination(logoPagination);
		    	if(logoPagination != null)
				{
				  if(results.size() > 0){
					  logoPagination.setTotalRecord(results.size());
				  }
				  logoPagination.calculatePages();
				}
		    	int startRecord=Integer.valueOf(pageNumberStr).intValue();
		    	int sortedResultsSize = results.size();
		    	
		    	if(startRecord == 1 )
		    	{
		    		if(sortedResultsSize >= Constants.RECORDS_PAGE)
		    		{
				    	for(int i=0;i<Constants.RECORDS_PAGE;i++)
				    	{
				    		logo = (Logo) results.get(i);
				    		finalResults.add(logo);
				    	}
		    		}else
		    		{
		    			for(int i=0;i<sortedResultsSize;i++)
				    	{
		    				logo = (Logo) results.get(i);
				    		finalResults.add(logo);
				    	}
		    		}
		    	}else
		    	{
		    		
		    		int end=startRecord * Constants.RECORDS_PAGE;
		    		int start=end - Constants.RECORDS_PAGE;
		    		if(end<=sortedResultsSize)
		    		{
			    		for(int i=start;i<end;i++)
				    	{
			    			logo = (Logo) results.get(i);
				    		finalResults.add(logo);
				    	}
		    		}else
		    		{
		    			start=(startRecord -1)*Constants.RECORDS_PAGE;
		    			end=sortedResultsSize;
		    			for(int i=start;i<end;i++)
				    	{
		    				logo = (Logo) results.get(i);
				    		finalResults.add(logo);
				    	}
		    		}
		    		
		    	}
		    	search.setResults(finalResults);*/
		    	request.setAttribute("command", search);
		       
		        return showForm(request, response, errors);
		    }
		   // search.setTotalResults(results);
		try {
			userService.searchLogo(search,sortFlag);
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
		/*catch (Exception e) {
			e.printStackTrace();
			errors.reject("search.logo.error", new Object[] { e.getMessage() }, null);
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
  protected boolean suppressValidation(HttpServletRequest request)
  {
   return super.suppressValidation(request);
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
   */
  protected Object formBackingObject(
    HttpServletRequest request
  ) throws Exception
  {
	  int pageNumber = 1;
	  String sortFlag ="";
		String pageNumberStr = request.getParameter("pageNumber");
	  String inputFieldId = request.getParameter("inputFieldId");
	  String divName1 = request.getParameter("div1");
	  String divName2 = request.getParameter("div2");
	  String searchForm = request.getParameter("formName");
	  BranchSearch branchSearch = new BranchSearch();
	  branchSearch.setPageNo("1");
	  branchSearch.setInputFieldId(inputFieldId);
  	  branchSearch.setDivName1(divName1);
  	  branchSearch.setDivName2(divName2);
  	  branchSearch.setSearchForm(searchForm);
	 	  return branchSearch;
  }
}
