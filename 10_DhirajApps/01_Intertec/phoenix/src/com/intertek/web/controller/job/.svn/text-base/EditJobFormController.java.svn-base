package com.intertek.web.controller.job;

import java.text.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractWizardFormController;
import org.springframework.web.servlet.view.RedirectView;

import com.intertek.entity.*;
import com.intertek.service.*;
import com.intertek.util.*;
import com.intertek.locator.*;

public class EditJobFormController extends AbstractWizardFormController
{
  public EditJobFormController() {
    super();
    setCommandClass(JobOrder.class);
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
   */
  protected ModelAndView processFinish(
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

    JobOrder jobOrder = (JobOrder)command;

    JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
    try
    {
			jobOrder.setBranchName(jobOrder.getBranch().getName());
			jobOrder.setBuName(jobOrder.getBranch().getBusinessUnit().getName());
      jobOrder = jobService.saveJobOrder(jobOrder);
    }
    catch(Exception e)
    {
      e.printStackTrace();

      errors.reject("edit.job.order.error", new Object[] {e.getMessage()}, null);
      return showForm(request, response, errors);
    }

    ModelAndView modelAndView = new ModelAndView("common-message-r");
    modelAndView.addObject("backUrl", "edit_job.htm?jobNumber=" + jobOrder.getJobNumber());
    modelAndView.addObject("backUrlDescription", "You can continue to edit this job order.");
    modelAndView.addObject("description", "Your job order has been successfully saved.");
    modelAndView.addObject("tabName", "EditJob");

    return modelAndView;
  }

  protected ModelAndView processCancel(
    HttpServletRequest request,
    HttpServletResponse response,
    Object command,
    BindException errors
  ) throws Exception
  {
    System.out.println("=================== in process Cancel");
    super.processCancel(request, response, command, errors);
    return new ModelAndView(new RedirectView("search_job.htm"));
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

  protected Map referenceData(
    HttpServletRequest request,
    int page
  ) throws Exception
  {
    Map data = new HashMap();

    data.put("tabName", "EditJob");
    if(page == 0) data.put("tabName2", "Entry");
    else if(page == 1) data.put("tabName2", "OperationalInfo");
    else if(page == 2) data.put("tabName2", "Prebill");
    else if(page == 3) data.put("tabName2", "Notes");
    else if(page == 4) data.put("tabName2", "History");

    return data;
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest, org.springframework.web.bind.ServletRequestDataBinder)
   */
  protected void initBinder(
    HttpServletRequest request,
    ServletRequestDataBinder binder
  ) throws Exception
  {
    DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
    CustomDateEditor editor = new CustomDateEditor(df, true);
    binder.registerCustomEditor(Date.class, editor);
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
   */
  protected Object formBackingObject(
    HttpServletRequest request
  ) throws Exception
  {
    String jobNumber = request.getParameter("jobNumber");
    JobOrder jobOrder = null;

    JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
    try
    {
      jobOrder = jobService.getJobOrderByJobNumber(jobNumber);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

    if(jobOrder == null)
    {
      jobOrder = new JobOrder();
      Branch branch = new Branch();
      jobOrder.setBranch(branch);
      BusinessUnit bu = new BusinessUnit();
      branch.setBusinessUnit(bu);
    }

    if(jobOrder.getShippingAgent() == null)
    {
      ShippingAgent shippingAgent = new ShippingAgent();
      jobOrder.setShippingAgent(shippingAgent);
    }

    return jobOrder;
  }
}
