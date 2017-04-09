package com.intertek.web.controller.user;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import com.intertek.exception.ServiceException;
import com.intertek.domain.BusinessUnitSearch;
import com.intertek.entity.BusinessUnit;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.UserService;

public class SearchBusinessUnitPopupController extends SimpleFormController {
	public SearchBusinessUnitPopupController() {
		super();
		setCommandClass(BusinessUnitSearch.class);
		//setSessionForm(true);
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		String pageNumberStr = request.getParameter("pageNumber");
		String searchType = request.getParameter("searchType");
		String searchForm = request.getParameter("searchForm");
		String sortFlag = request.getParameter("sortFlag");
		int pageNumber = 1;
		try {
			pageNumber = new Integer(pageNumberStr).intValue();
		} catch (Exception e) {
		}
		if (pageNumber <= 0)
			pageNumber = 1;

		BusinessUnitSearch search = (BusinessUnitSearch) command;
		Pagination pagination = new Pagination(1, 20, pageNumber, 10);
		search.setPagination(pagination);
		search.setSearchForm(searchForm);

		UserService userService = (UserService) ServiceLocator.getInstance().getBean("userService");
		BusinessUnit businessUnit = new BusinessUnit();
		List results = new ArrayList();
		if(sortFlag != null && !sortFlag.trim().equals(""))
	   {
	   	List finalResults = new ArrayList();
	   	System.out.println("pageNumberStr="+pageNumberStr);
	   //	if(pageNumberStr.equals("1"))
	   //	{
	   		try
		        {
	   			userService.searchBusinessUnit(search,sortFlag);
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
	  // 	}
	   /*	results = search.getTotalResults();
	   	
	   	Pagination countryPagination = new Pagination(1, 20, pageNumber,10);
	   	
	   	search.setPagination(countryPagination);
	   	if(countryPagination != null)
			{
			  if(results.size() > 0){
				  countryPagination.setTotalRecord(results.size());
			  }
			  countryPagination.calculatePages();
			}
	   	int startRecord=Integer.valueOf(pageNumberStr).intValue();
	   	int sortedResultsSize = results.size();
	   	
	   	if(startRecord == 1 )
	   	{
	   		if(sortedResultsSize >= Constants.RECORDS_PAGE)
	   		{
			    	for(int i=0;i<Constants.RECORDS_PAGE;i++)
			    	{
			    		businessUnit = (BusinessUnit) results.get(i);
			    		finalResults.add(businessUnit);
			    	}
	   		}else
	   		{
	   			for(int i=0;i<sortedResultsSize;i++)
			    	{
	   				businessUnit = (BusinessUnit) results.get(i);
			    		finalResults.add(businessUnit);
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
		    			businessUnit = (BusinessUnit) results.get(i);
			    		finalResults.add(businessUnit);
			    	}
	   		}else
	   		{
	   			start=(startRecord -1)*Constants.RECORDS_PAGE;
	   			end=sortedResultsSize;
	   			for(int i=start;i<end;i++)
			    	{
	   				businessUnit = (BusinessUnit) results.get(i);
			    		finalResults.add(businessUnit);
			    	}
	   		}
	   	}
	   	search.setResults(finalResults);*/
	   	request.setAttribute("command", search);
	      
	       return showForm(request, response, errors);
	   }
		//search.setTotalResults(results);
		try {
			userService.searchBusinessUnit(search,sortFlag);
		} /*catch (Exception e) {
			e.printStackTrace();
			errors.reject("search.businessunit.error",new Object[] { e.getMessage() }, null);
			return showForm(request, response, errors);
		}*/
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

		request.setAttribute("command", search);

		return showForm(request, response, errors);
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest, org.springframework.web.bind.ServletRequestDataBinder)
	 */
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
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
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		String businessUnitSearchType = request.getParameter("creditAnalysttype");
		String inputFieldId = request.getParameter("inputFieldId");
		String searchForm=request.getParameter("searchForm");
		BusinessUnitSearch businessUnitSearch = new BusinessUnitSearch();
		businessUnitSearch.setSearchType(businessUnitSearchType);
		businessUnitSearch.setInputFieldId(inputFieldId);
    	businessUnitSearch.setSearchForm(searchForm);
		return businessUnitSearch;
	}
}
