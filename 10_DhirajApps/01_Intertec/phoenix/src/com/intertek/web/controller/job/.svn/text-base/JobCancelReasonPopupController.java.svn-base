package com.intertek.web.controller.job;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import com.intertek.domain.AddJobContract;
import com.intertek.domain.AddJobOrder;
import com.intertek.domain.CreditReason;
import com.intertek.entity.JobContractInvoice;
import com.intertek.entity.JobOrder;
import com.intertek.entity.User;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.JobService;
import com.intertek.service.PrebillService;
import com.intertek.service.UserService;
import com.intertek.util.Constants;
import com.intertek.util.SecurityUtil;

public class JobCancelReasonPopupController extends SimpleFormController
{
  public JobCancelReasonPopupController() {
    super();
    setCommandClass(AddJobOrder.class);
    setSessionForm(true);
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
	  
	AddJobOrder addJobOrder = (AddJobOrder)command;
    return new ModelAndView("refresh-page");
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
	  AddJobOrder addJobOrder= new AddJobOrder();
	  String jobNumber = request.getParameter("jobNumber");
	  JobService jobService = (JobService) ServiceLocator.getInstance().getBean("jobService");
	  UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
	  
	  JobOrder jobOrder = jobService.getJobOrderByJobNumberWithDetail(jobNumber);
	  jobOrder.setJobStatus(Constants.CANCELLED_STATUS);
	  User cancelReasonUser = userService.getUserByName(SecurityUtil.getUser().getLoginName());
	  if(cancelReasonUser == null) {
	 	jobOrder.setCancelReasonUserName("");
	  } else {
		jobOrder.setCancelReasonUserName(cancelReasonUser.getFirstName()+" "+cancelReasonUser.getLastName());
	  }
	  addJobOrder.setJobOrder(jobOrder);
	  return addJobOrder;
  }
}
