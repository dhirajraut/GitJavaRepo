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

public class LogJobOrderController extends SimpleFormController
{
  public LogJobOrderController() {
    super();
    setCommandClass(JobSearch.class);
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
    String refreshing = request.getParameter("refreshing");
    if((refreshing != null) && "true".equals(refreshing))
    {
      return showForm(request, response, errors);
    }

    String pageNumberStr = request.getParameter("pageNumber");
    int pageNumber = 1;
    try
    {
      pageNumber = new Integer(pageNumberStr).intValue();
    }
    catch(Exception e)
    {
    }
    if(pageNumber <= 0) pageNumber = 1;

    JobSearch jobSearch = (JobSearch)command;

    Pagination pagination = new Pagination(1, 5, pageNumber, 10);
    jobSearch.setPagination(pagination);

    JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
    try
    {
      jobService.searchJobOrder(jobSearch, pageNumber, "jobSearch","job.jobNumber");
    }
    catch(Exception e)
    {
      e.printStackTrace();

      errors.reject("search.job.order.error", new Object[] {e.getMessage()}, null);
      return showForm(request, response, errors);
    }

    System.out.println("========== results: " + jobSearch.getResults());

    request.setAttribute("command", jobSearch);
    return showForm(request, response, errors);
  }

  protected boolean suppressValidation(HttpServletRequest request)
  {
    String refreshing = request.getParameter("refreshing");
    if((refreshing != null) && "true".equals(refreshing))
    {
      return true;
    }

    return super.suppressValidation(request);
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

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
   */
  protected Object formBackingObject(
    HttpServletRequest request
  ) throws Exception
  {
    JobSearch jobSearch = new JobSearch();

    //JobOrder jobOrder = new JobOrder();
    //jobSearch.setJobOrder(jobOrder);

    return jobSearch;
  }
}
