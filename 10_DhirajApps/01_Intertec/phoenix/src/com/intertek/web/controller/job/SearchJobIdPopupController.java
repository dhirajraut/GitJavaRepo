package com.intertek.web.controller.job;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.domain.JobSearch;
import com.intertek.domain.StringSearchField;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.JobService;

public class SearchJobIdPopupController extends SimpleFormController{
	
	public SearchJobIdPopupController() {
		super();
		setCommandClass(JobSearch.class);
		//setSessionForm(true);
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		String pageNumberStr = request.getParameter("pageNumber");
		String sortFlag = request.getParameter("sortFlag");
		
		
		int pageNumber = 1;
		try {
			pageNumber = new Integer(pageNumberStr).intValue();
		} catch (Exception e) {
		}
		if (pageNumber <= 0)
			pageNumber = 1;

		JobSearch search = (JobSearch) command;
		Pagination pagination = new Pagination(1, 20, pageNumber, 10);
		search.setPagination(pagination);
	
		// START : #119240 
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
		// END : #119240 
		
		JobService jobService = (JobService) ServiceLocator.getInstance().getBean("jobService");
		/*JobOrder jobOrder = new JobOrder();
	    List results = new ArrayList();*/
	    if(sortFlag != null && !sortFlag.trim().equals(""))
	    {
	    	/*List finalResults = new ArrayList();
	    	
	    	if(pageNumberStr.equals("1"))
	    	{*/
	    		try
		        {
	    			jobService.searchJobId(search,sortFlag);
		        } catch(ServiceException e)
				{
			        e.printStackTrace();
			        errors.reject(e.getMessage(), e.getParams(), null);
			        return showForm(request, response, errors);
			    } catch(Throwable t)
			    {
			        t.printStackTrace();
			        errors.reject("generic.error", new Object[] {t.getMessage()}, null);
			        return showForm(request, response, errors);
			    }
		      /*  }
		        catch(Exception e)
		        {
		        	e.printStackTrace();
					errors.reject("search.jobid.error", new Object[] { e.getMessage() }, null);
					return showForm(request, response, errors);
		        }*/
	    	/*}
	    	results = search.getTotalResults();
	    	
	    	Pagination jobidPagination = new Pagination(1, 20, pageNumber,10);
	    	
	    	search.setPagination(jobidPagination);
	    	if(jobidPagination != null)
			{
			  if(results.size() > 0){
				  jobidPagination.setTotalRecord(results.size());
			  }
			  jobidPagination.calculatePages();
			}
	    	int startRecord=Integer.valueOf(pageNumberStr).intValue();
	    	int sortedResultsSize = results.size();
	    	
	    	if(startRecord == 1 )
	    	{
	    		if(sortedResultsSize >= Constants.RECORDS_PAGE)
	    		{
			    	for(int i=0;i<Constants.RECORDS_PAGE;i++)
			    	{
			    		jobOrder = (JobOrder) results.get(i);
			    		finalResults.add(jobOrder);
			    	}
	    		}else
	    		{
	    			for(int i=0;i<sortedResultsSize;i++)
			    	{
	    				jobOrder = (JobOrder) results.get(i);
			    		finalResults.add(jobOrder);
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
		    			jobOrder = (JobOrder) results.get(i);
			    		finalResults.add(jobOrder);
			    	}
	    		}else
	    		{
	    			start=(startRecord -1)*Constants.RECORDS_PAGE;
	    			end=sortedResultsSize;
	    			for(int i=start;i<end;i++)
			    	{
	    				jobOrder = (JobOrder) results.get(i);
			    		finalResults.add(jobOrder);
			    	}
	    		}
	    		
	    	}
	    	search.setResults(finalResults);*/
	    	request.setAttribute("command", search);
	       
	        return showForm(request, response, errors);
	    }
	  //  search.setTotalResults(results);


		try {
			jobService.searchJobId(search,sortFlag);
		} catch(ServiceException e)
		   {
	        e.printStackTrace();
	        errors.reject(e.getMessage(), e.getParams(), null);
	        return showForm(request, response, errors);
		 } catch(Throwable t)
		 {
		     t.printStackTrace();
		     errors.reject("generic.error", new Object[] {t.getMessage()}, null);
		     return showForm(request, response, errors);
		 }
		/*} catch (Exception e) {
			e.printStackTrace();
			errors.reject("search.jobid.error", new Object[] { e.getMessage() }, null);
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

	
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		
		int pageNumber = 1;
		String sortFlag="";
		String inputFieldId = request.getParameter("inputFieldId");
		String div1 = request.getParameter("div1");
		String div2 = request.getParameter("div2");
		
		
		String buName=request.getParameter("buName");
		String branchName=request.getParameter("branchName");
		String jobId=request.getParameter("jobId");
		
		
		JobService jobService = (JobService) ServiceLocator.getInstance().getBean("jobService");
		
		JobSearch jobSearch = new JobSearch();
		
		
		Pagination pagination = new Pagination(1,20, pageNumber, 10);
		jobSearch.setPagination(pagination);
		StringSearchField stringSearchField1 = new StringSearchField();
		StringSearchField stringSearchField2 = new StringSearchField();
		jobSearch.setPageName(null);
		
		if((buName!= null) && !"".equals(buName.trim())||(branchName!=null && !"".equals(branchName.trim())) ||(jobId!= null) && !"".equals(jobId.trim()) ) {
			stringSearchField1.setValue(buName);
			stringSearchField2.setValue(jobId);
			jobSearch.setBusinessUnit(stringSearchField1);
			jobSearch.setFromJobId(stringSearchField2);
			jobSearch.getBranch().setValue(branchName);
	
		   try
		    {
			   jobService.searchJobId(jobSearch,sortFlag);
		    } catch(ServiceException e)
	        {
	         	throw new ServiceException(e.getMessage(), e.getParams(), e);
	        } catch(Throwable t)
	        {
	             t.printStackTrace();
	             throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
	        }
		  /*  catch(Exception e)
		    {
		        e.printStackTrace();
		    }*/
		}
		
		jobSearch.setInputFieldId(inputFieldId);
		jobSearch.setDiv1(div1);
		jobSearch.setDiv2(div2);

		return jobSearch;
	}
}
