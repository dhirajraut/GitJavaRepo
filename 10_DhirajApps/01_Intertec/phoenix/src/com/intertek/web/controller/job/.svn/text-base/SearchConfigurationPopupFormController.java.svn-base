package com.intertek.web.controller.job;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.domain.JobSearch;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.JobService;
import com.intertek.util.SecurityUtil;

public class SearchConfigurationPopupFormController extends SimpleFormController{
	
	public SearchConfigurationPopupFormController() {
		super();
		setCommandClass(JobSearch.class);
		setSessionForm(true);
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
	    if(sortFlag != null && !sortFlag.trim().equals(""))
	    {
	    	try
		        {
					jobService.searchConfiguration(search,sortFlag);
		         }
		        catch(Exception e)
		        {
		        	e.printStackTrace();
					errors.reject("search.jobid.error", new Object[] { e.getMessage() }, null);
					return showForm(request, response, errors);
		        }
	    	
	    	request.setAttribute("command", search);
	       
	        return showForm(request, response, errors);
	    }


		try {
			jobService.searchConfiguration(search,sortFlag);
		} catch (Exception e) {
			e.printStackTrace();
			errors.reject("search.configuration.error", new Object[] { e.getMessage() }, null);
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

	
	protected Object formBackingObject(HttpServletRequest request) throws Exception {
		
		int pageNumber = 1;
		String sortFlag="";
		String inputFieldId = request.getParameter("inputFieldId");
		String div1 = request.getParameter("div1");
		String div2 = request.getParameter("div2");		
		String searchValue=request.getParameter("searchValue");

		JobService jobService = (JobService) ServiceLocator.getInstance().getBean("jobService");
		
		JobSearch jobSearch = new JobSearch();
		
		
		jobSearch.setInputFieldId(inputFieldId);
		if(searchValue!=null&& !searchValue.trim().equals("")){
        jobSearch.getConfListName().setValue(searchValue);}
		jobSearch.setDiv1(div1);
		jobSearch.setDiv2(div2);
		jobSearch.setUserName(SecurityUtil.getUser().getLoginName());
		


		return jobSearch;
	}
}
