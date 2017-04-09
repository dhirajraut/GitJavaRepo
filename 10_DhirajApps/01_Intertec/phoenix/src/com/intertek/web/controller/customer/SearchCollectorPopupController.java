package com.intertek.web.controller.customer;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.domain.CollectorSearch;
import com.intertek.entity.Collector;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.CustomerService;

public class SearchCollectorPopupController extends SimpleFormController {
	public SearchCollectorPopupController() {
		super();
		setCommandClass(CollectorSearch.class);
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

		CollectorSearch search = (CollectorSearch) command;
		Pagination pagination = new Pagination(1, 20, pageNumber, 10);
		search.setPagination(pagination);
		search.setSearchForm(searchForm);

		CustomerService customerService = (CustomerService) ServiceLocator	.getInstance().getBean("customerService");
		Collector collector = new Collector();
	    List results = new ArrayList();
	    if(sortFlag != null && !sortFlag.trim().equals(""))
	    {
	    	List finalResults = new ArrayList();
	    	
	    	//if(pageNumberStr.equals("1"))
	    //	{
	    		try
		        {
	    			customerService.searchCollector(search,sortFlag);
	        } catch(ServiceException e)
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
					errors.reject("search.collector.error",new Object[] { e.getMessage() }, null);
					return showForm(request, response, errors);
		        }*/
	    	//}
	    	/*results = search.getTotalResults();
	    	
	    	Pagination collectorPagination = new Pagination(1, 20, pageNumber,10);
	    	
	    	search.setPagination(collectorPagination);
	    	if(collectorPagination != null)
			{
			  if(results.size() > 0){
				  collectorPagination.setTotalRecord(results.size());
			  }
			  collectorPagination.calculatePages();
			}
	    	int startRecord=Integer.valueOf(pageNumberStr).intValue();
	    	int sortedResultsSize = results.size();
	    	
	    	if(startRecord == 1 )
	    	{
	    		if(sortedResultsSize >= Constants.RECORDS_PAGE)
	    		{
			    	for(int i=0;i<Constants.RECORDS_PAGE;i++)
			    	{
			    		collector = (Collector) results.get(i);
			    		finalResults.add(collector);
			    	}
	    		}else
	    		{
	    			for(int i=0;i<sortedResultsSize;i++)
			    	{
	    				collector = (Collector) results.get(i);
			    		finalResults.add(collector);
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
		    			collector = (Collector) results.get(i);
			    		finalResults.add(collector);
			    	}
	    		}else
	    		{
	    			start=(startRecord -1)*Constants.RECORDS_PAGE;
	    			end=sortedResultsSize;
	    			for(int i=start;i<end;i++)
			    	{
	    				collector = (Collector) results.get(i);
			    		finalResults.add(collector);
			    	}
	    		}
	    		
	    	}
	    	search.setResults(finalResults);*/
	    	request.setAttribute("command", search);
	       
	        return showForm(request, response, errors);
	    }
	   // search.setTotalResults(results);

		try {
			customerService.searchCollector(search,sortFlag);
		} catch(ServiceException e)
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
	    }/* catch (Exception e) {
			e.printStackTrace();
			errors.reject("search.collector.error",new Object[] { e.getMessage() }, null);
			return showForm(request, response, errors);
		}*/

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

		String inputFieldId = request.getParameter("inputFieldId");
		String collectortType = request.getParameter("collectorType");

		CollectorSearch collectorSearch = new CollectorSearch();
		collectorSearch.setSearchType(collectortType);
		collectorSearch.setInputFieldId(inputFieldId);
		return collectorSearch;
	}
}
