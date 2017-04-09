package com.intertek.web.controller.job;

import java.util.Calendar;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.domain.JobSearch;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.JobService;
import com.intertek.util.DateUtil;

public class MonthlyJobLogDetailsFormController extends SimpleFormController
{
  public MonthlyJobLogDetailsFormController() {
    super();
    setCommandClass(JobSearch.class);
	setSessionForm(true);
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
   */
  /**
 * Name :onSubmit
 * Date :Dec 19, 2008
 * purpose :
 * @param request
 * @param response
 * @param command
 * @param errors
 * @return ModelAndView
 * @throws Exception
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
    JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
	  
    int pageNumber = 1;
    try
    {
      pageNumber = new Integer(pageNumberStr).intValue();
    }
    catch(Exception e)
    {
    }
    if(pageNumber <= 0) pageNumber = 1;

    JobSearch search = (JobSearch)command;
    Pagination pagination = new Pagination(1, 20, pageNumber, 10);
    
    
    search.setPagination(pagination);
	search.setSearchForm(searchForm);
	
	String dateFormat="MM/dd/yyyy";	
	Date fromDate = search.getFromDate(dateFormat);
	Date toDate = search.getToDate(dateFormat);

	try{
	jobService.searchMonthlyJobDetails(search,sortFlag,fromDate,toDate);
	}catch(ServiceException e)
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
 
}
