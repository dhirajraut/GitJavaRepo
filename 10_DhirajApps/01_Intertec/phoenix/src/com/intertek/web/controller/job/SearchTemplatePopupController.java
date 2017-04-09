package com.intertek.web.controller.job;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.domain.TemplateSearch;
import com.intertek.entity.JobOrder;
import com.intertek.entity.User;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Page;
import com.intertek.pagination.Pagination;
import com.intertek.service.JobService;
import com.intertek.service.UserService;
import com.intertek.util.SecurityUtil;

public class SearchTemplatePopupController extends SimpleFormController {
	public SearchTemplatePopupController() {
		super();
		setCommandClass(TemplateSearch.class);
		//setSessionForm(true);
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		
		int count = 0;
		int pages = 0;
		int num = 0;
		
		String pageNumberStr = request.getParameter("pageNumber");
		String sortFlag = request.getParameter("sortFlag");
		String tmpNumber=request.getParameter("tempNumber");
		String tmpdelFlag=request.getParameter("tempdelFlag");
		

		int pageNumber = 1;
		try {
			pageNumber = new Integer(pageNumberStr).intValue();
		} catch (Exception e) {
		}
		if (pageNumber <= 0)
			pageNumber = 1;

		TemplateSearch search = (TemplateSearch) command;
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
		
		JobService jobService = (JobService) ServiceLocator	.getInstance().getBean("jobService");
		/*JobOrder jobOrder = new  JobOrder();
		
		List results = new ArrayList();*/
		if(sortFlag != null && !sortFlag.trim().equals(""))
	    {
	    	/*List finalResults = new ArrayList();
	    	
	    	if(pageNumberStr.equals("1"))
	    	{*/
			    try
			    {
			    	jobService.searchTemplate(search,sortFlag);
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
			   /* catch(Exception e)
			    {
			      e.printStackTrace();

					errors.reject("search.template.error", new Object[] {e.getMessage()}, null);
			      return showForm(request, response, errors);
			    }*/
	    	/*}
	    	results = search.getTotalResults();
	    	
	    	Pagination templateNamePagination = new Pagination(1, 20, pageNumber,10);
	    	search.setPagination(templateNamePagination);
	    	if(templateNamePagination != null)
			{
			  if(results.size() > 0){
				  templateNamePagination.setTotalRecord(results.size());
			  }
			  templateNamePagination.calculatePages();
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
	    	
		if(tmpdelFlag!=null && "true".equals(tmpdelFlag))
		{
			JobOrder tmpJobOrder=new JobOrder();
			tmpJobOrder=jobService.getJobOrderByJobNumber(tmpNumber);
			if(tmpJobOrder!=null)
			{
				try{
			      jobService.deleteTemplate(tmpJobOrder);
				  }
				 catch(ServiceException e)
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
			}
			search.setTempdelFlag("none");				
		}
	    		
		 //List results = new ArrayList();
		//search.setTotalResults(results);   
	    try
	    {
	    	jobService.searchTemplate(search,sortFlag);
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
	   /* catch(Exception e)
	    {
	      e.printStackTrace();

			errors.reject("search.template.error", new Object[] {e.getMessage()}, null);
	      return showForm(request, response, errors);
	    }*/

	    count=pagination.getTotalRecord();
	    pages=pagination.getPagesToDisplay();
		num=pagination.getNumInPage();
		List pagesList=pagination.getPages();
	
		if(pagesList.size()>0)
		{
			Page page =(Page)pagesList.get(0);
			String pageName=page.getName();
			if(pageName.equals("Prev"))
			search.setPageName(pageName);
			else if(count <= (num*pages))
			search.setPageName(pageName);
			else
			search.setPageName(null);
			
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

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		String inputFieldId = request.getParameter("inputFieldId");
		String sortFlag = "";
		String branchName = request.getParameter("branchName");
		String buName = request.getParameter("buName");
		String templateName = request.getParameter("templateName");
		TemplateSearch templateSearch = new TemplateSearch();
		templateSearch.setInputFieldId(inputFieldId);
		templateSearch.getBranchName().setValue(branchName);
		templateSearch.getTemplateName().setValue(templateName);
		templateSearch.getBuName().setValue(buName);
		String pageNumberStr = request.getParameter("pageNumber");
		int pageNumber = 0;
		try {
		UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
		User user = userService.getUserByName(SecurityUtil.getUser().getLoginName());
		
		if(user != null)
		{
			templateSearch.getFirstName().setValue(user.getFirstName());
			templateSearch.getLastName().setValue(user.getLastName());
		}

try
   {
      pageNumber = new Integer(pageNumberStr).intValue();
    }
    catch(Exception e)
    {
    }
    if(pageNumber <= 0) pageNumber = 1;
    Pagination pagination = new Pagination(1, 20, pageNumber, 10);
    templateSearch.setPagination(pagination);
    
    JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
    
    jobService.searchTemplate(templateSearch, sortFlag);
		} catch(ServiceException e)
		{
		   	throw new ServiceException(e.getMessage(), e.getParams(), e);
		}catch(Throwable t)
		{
		    t.printStackTrace();
		    throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
		}
    request.setAttribute("command", templateSearch);


return templateSearch;
}
}
