package com.intertek.web.controller.job;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.domain.AddJobContract;
import com.intertek.domain.AddJobOrder;
import com.intertek.domain.CreditReason;
import com.intertek.entity.JobContractInvoice;
import com.intertek.entity.User;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.JobService;
import com.intertek.service.PrebillService;
import com.intertek.service.UserService;
import com.intertek.util.SecurityUtil;

public class CreditReasonPopupController extends SimpleFormController
{
  public CreditReasonPopupController() {
    super();
    setCommandClass(CreditReason.class);
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
	  AddJobContract addJobContract = (AddJobContract)command;
	  String controllerName = request.getParameter("controllerName");
	  System.out.println("controllerName in credit reason popup: "+controllerName);
	  
	  String sessionAttribute = "";
	  if(controllerName != null && (!controllerName.trim().equals("")))
		  sessionAttribute = "com.intertek.web.controller.job."+controllerName+".FORM.command";
	  
    HttpSession session = request.getSession();
    
    AddJobOrder addJobOrder = (AddJobOrder)session.getAttribute(sessionAttribute);
    
    JobContractInvoice latestJobContractInvoice = addJobContract.getLatestJobContractInvoice();
    
    
    if(latestJobContractInvoice.getBillStatus() != null && !latestJobContractInvoice.getBillStatus().equals("INV"))
    {
        errors.reject("it.is.not.invoiced.yet", new Object[] {}, null);
        return showForm(request, response, errors);
    }
    
    if(latestJobContractInvoice.getCreditReasonNote() == null || latestJobContractInvoice.getCreditReasonNote().trim().equals(""))
    {
        errors.reject("please.enter.credit.reason.note", new Object[] {}, null);
        return showForm(request, response, errors);
    }
    if(latestJobContractInvoice.getCreditReasonUserName() == null || latestJobContractInvoice.getCreditReasonUserName().trim().equals(""))
    {
        errors.reject("please.select.approval.manager.for.the.credit.note", new Object[] {}, null);
        return showForm(request, response, errors);
    }
    
    JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
    jobService.saveJobContractInvoice(latestJobContractInvoice);
    System.out.println("after saving job contract invoice");
  
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
 /*   String jobContractId = request.getParameter("jobContractId");
    String contractCode = request.getParameter("contractCode");
    
    System.out.println("jobContractId : "+jobContractId);
    System.out.println("contractCode : "+contractCode);
    
    JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
    HttpSession session = request.getSession();
    AddJobOrder addJobOrder = (AddJobOrder)session.getAttribute("EditJobEntryFormController.AddJobOrder");

    AddJobContract addJobContract = JobUtil.getAddJobContractByContractCode(
      addJobOrder,
      contractCode 
    );

    
    if(jobContractId != null && (!jobContractId.trim().equals("")))
    {
    	JobContract jobContract = jobService.getJobContractWithInvoiceDetails(new Long(jobContractId).longValue());
    	if(jobContract != null)
    	{
    		Set jobContractInvoices = jobContract.getJobContractInvoices();
    		long invoiceId = 0;
    		JobContractInvoice latestJobContractInvoice = null;
    		if(jobContractInvoices != null && jobContractInvoices.size() > 0)
    		{
    			Iterator iter = jobContractInvoices.iterator();
    			while(iter.hasNext())
    			{
    				JobContractInvoice jobContractInvoice = (JobContractInvoice) iter.next();
    				if(jobContractInvoice.getId() > invoiceId)
    				{
    					invoiceId = jobContractInvoice.getId();
    					latestJobContractInvoice = jobContractInvoice;
    				}
    				addJobContract.setLatestJobContractInvoice(latestJobContractInvoice);
    			}
    		}
    	}
    }
    System.out.println("existing formbacking of CreditReasonPopupControlelr");
    return addJobContract;*/
	  
	  CreditReason creditReason = new CreditReason();
	  String contractIndex = request.getParameter("contractIndex");
	  String invoiceId = request.getParameter("invoiceId");
	  String viewFlag = request.getParameter("viewFlag");
	  String controllerName = request.getParameter("controllerName");
	  
	  UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
	  
	    System.out.println("contractIndex : "+contractIndex);
	    creditReason.setContractIndex(contractIndex);
	    creditReason.setReadWriteFlag(viewFlag);
	    creditReason.setControllerName(controllerName);
	    
	    if(invoiceId != null && (!invoiceId.trim().equals("")))
	    {
	    	PrebillService prebillService = (PrebillService)ServiceLocator.getInstance().getBean("prebillService");
	    	JobContractInvoice jobContractInvoice = null;
	    	try{
	    	jobContractInvoice = prebillService.getJobContractInvoiceById(new Long(invoiceId).longValue());
	    	} catch(ServiceException e)
	        {
    	   	throw new ServiceException(e.getMessage(), e.getParams(), e);
    	    }
    	    catch(Throwable t)
    	    {
    	      t.printStackTrace();
    	      throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
    	    }
	    	if(jobContractInvoice != null)
	    	{
	    		creditReason.setCreditReasonNote(jobContractInvoice.getCreditReasonNote());
	    		
	    		User creditReasonUser = userService.getUserByName(jobContractInvoice.getCreditReasonUserName());
	    		if(creditReasonUser == null)
	    		{
	    			creditReasonUser = userService.getUserByName(SecurityUtil.getUser().getLoginName());
	    			if(creditReasonUser == null)
	    				creditReason.setCreditReasonUser("");
	    			else
	    				creditReason.setCreditReasonUser(creditReasonUser.getFirstName()+" "+creditReasonUser.getLastName());
	    		}
	    		else
	    			creditReason.setCreditReasonUser(creditReasonUser.getFirstName()+" "+creditReasonUser.getLastName());
	    		
	    	}
	    }
	    else
	    {
	    	User creditReasonUser = userService.getUserByName(SecurityUtil.getUser().getLoginName());
			if(creditReasonUser == null)
				creditReason.setCreditReasonUser("");
			else
				creditReason.setCreditReasonUser(creditReasonUser.getFirstName()+" "+creditReasonUser.getLastName());
	    }
	  return creditReason;
  }
}
