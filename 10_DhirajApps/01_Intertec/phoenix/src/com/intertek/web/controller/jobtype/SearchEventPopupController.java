package com.intertek.web.controller.jobtype;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import com.intertek.exception.ServiceException;
import com.intertek.domain.JobTypeSearch;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.JobTypeService;

public class SearchEventPopupController extends SimpleFormController {
	public SearchEventPopupController() {
		super();
		setCommandClass(JobTypeSearch.class);
		//setSessionForm(true);
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors) throws Exception {
		String pageNumberStr = request.getParameter("pageNumber");
		String sortFlag = request.getParameter("sortFlag");

		int pageNumber = 1;
		try {
			pageNumber = new Integer(pageNumberStr).intValue();
		} catch (Exception e) {
		}
		if (pageNumber <= 0)
			pageNumber = 1;

		JobTypeSearch search = (JobTypeSearch) command;
		Pagination pagination = new Pagination(1, 20, pageNumber, 10);
		search.setPagination(pagination);

JobTypeService jobTypeService = (JobTypeService)ServiceLocator.getInstance().getBean("jobTypeService");

		/*Event event = new Event();
		List results = new ArrayList();*/
	  if(sortFlag != null && !sortFlag.trim().equals(""))
	  {
	  	/*List finalResults = new ArrayList();
	  	
	  	if(pageNumberStr.equals("1"))
	  	{*/
	  		try	        {
	  			jobTypeService.searchEvent(search,sortFlag);
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
		            errors.reject("search.event.error", new Object[] {e.getMessage()}, null);
		            return showForm(request, response, errors);
		        }*/
	  	request.setAttribute("command", search);
	     
	      return showForm(request, response, errors);
	  }
	 // search.setTotalResults(results);

		try {
         jobTypeService.searchEvent(search,sortFlag);
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
			errors.reject("search.event.error",new Object[] { e.getMessage() }, null);
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
	

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		String inputFieldId = request.getParameter("inputFieldId");
		String rowNum = request.getParameter("rowNum");
		String searchForm=request.getParameter("searchForm");
		JobTypeSearch jobTypeSearch = new JobTypeSearch();

		jobTypeSearch.setInputFieldId(inputFieldId);
		jobTypeSearch.setRowNum(rowNum);
		jobTypeSearch.setSearchForm(searchForm);
		return jobTypeSearch;
	}
}
