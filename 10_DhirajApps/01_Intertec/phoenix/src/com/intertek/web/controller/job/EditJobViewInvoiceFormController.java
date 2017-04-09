package com.intertek.web.controller.job;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.intertek.domain.AddJobContract;
import com.intertek.domain.AddJobOrder;
import com.intertek.domain.JobSearch;
import com.intertek.entity.Contract;
import com.intertek.entity.Customer;
import com.intertek.entity.JobContract;
import com.intertek.entity.JobContractInvoice;
import com.intertek.entity.JobOrder;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.CalculatorService;
import com.intertek.service.ContractService;
import com.intertek.service.CustomerService;
import com.intertek.service.JobContractResultService;
import com.intertek.service.JobService;
import com.intertek.service.PrebillService;
import com.intertek.util.Constants;
import com.intertek.util.InvoiceUtil;
import com.intertek.util.JobUtil;
import com.intertek.web.controller.BaseSimpleFormController;


import com.intertek.ariba.SendInvoiceToAribaManager;

public class EditJobViewInvoiceFormController extends BaseSimpleFormController
{
  public EditJobViewInvoiceFormController()
  {
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
      String warnUserFlag = addJobOrder.getWarnUserFlag();
      PrebillService prebillService = (PrebillService)ServiceLocator.getInstance().getBean("prebillService");

      // START : #119240
      String noPrev = request.getParameter("noPrevJob");
		if(noPrev !=null && noPrev.equals("true")){
			request.setAttribute("noJobMessage", "No Previous Job!");
			return showForm(request, response, errors);
		}
		String noNext = request.getParameter("noNextJob");
		if(noNext !=null && noNext.equals("true")){
			request.setAttribute("noJobMessage", "No Next Job!");
			return showForm(request, response, errors);
		}
  	 // String nextList=request.getParameter("nextListFlag");
     String nextList=request.getParameter("showNextListFlag");
     JobOrder jobOrder = addJobOrder.getJobOrder();
     String jobNumber = "";
	  	if(nextList != null && nextList.trim().equals("next"))
	  	{
	        JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
	  		jobNumber=jobOrder.getJobNumber();
	  		
	  		try
	  		{
	  			if(request.getSession() != null)
	  			{
	  				JobSearch jobSearch=(JobSearch)request.getSession().getAttribute("MySearchJobOrder");
	  				jobOrder=jobService.getNextOrPreviousJobNumber(jobSearch,jobNumber,nextList);
	  			}
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
	  		
	  		//String url=jobService.getUrlHrefJobTypeByJobOrder(jobOrder)+"?jobNumber=";
	  		String url=jobService.getUrlByCurrPageIdentifier(jobOrder, addJobOrder.getCurrPageIdentifier())+"?jobNumber=";
	  		
	  		return new ModelAndView(new RedirectView(url+jobOrder.getJobNumber()+"&reqFrom="+addJobOrder.getOriginateFromSearchJob()));
	  	}
	  	
	  	//String prevList=request.getParameter("prevListFlag");
	  	String prevList=request.getParameter("showPrevListFlag");
	  	if(prevList != null && prevList.trim().equals("prev"))
	  	{
	        JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
	  		jobNumber=jobOrder.getJobNumber();
	  		
	  		try
	  		{
	  			if(request.getSession() != null)
	  			{
	  				JobSearch jobSearch=(JobSearch)request.getSession().getAttribute("MySearchJobOrder");
	  				jobOrder=jobService.getNextOrPreviousJobNumber(jobSearch,jobNumber,prevList);
	  			}
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
	  		
	  		//String url=jobService.getUrlHrefJobTypeByJobOrder(jobOrder)+"?jobNumber=";
	  		String url=jobService.getUrlByCurrPageIdentifier(jobOrder, addJobOrder.getCurrPageIdentifier())+"?jobNumber=";
	  		
	  		return new ModelAndView(new RedirectView(url+jobOrder.getJobNumber()+"&reqFrom="+addJobOrder.getOriginateFromSearchJob()));
	
	  	}    
      // END : #119240
  	
      if((warnUserFlag != null) && "warn".equals(warnUserFlag))
      {
        addJobOrder.setWarnUserFlag("warned");
        errors.reject("edit.job.navigation.warning", new Object[] {"Unsaved changes if any will be lost.Please save the changes"}, null);
        return showForm(request, response, errors);
      }

      if((warnUserFlag != null) && "navigate".equals(warnUserFlag))
      {
        String navigationUrl = addJobOrder.getNavigationUrl();
        if(navigationUrl != null)
        {
          return new ModelAndView(new RedirectView(navigationUrl));
        }
        else
          return showForm(request, response, errors);
      }


        int contractIndex = 0;
        try
        {
          contractIndex = new Integer(request.getParameter("contractIndex")).intValue();
        }
        catch(Exception e)
        {
        }
        addJobOrder.setContractIndex(contractIndex);

    String refreshing = request.getParameter("refreshing");
    if((refreshing != null) && "true".equals(refreshing))
    {
      return showForm(request, response, errors);
    }

    //send Invoice to Ariba
    String sendToAribaFlag = request.getParameter("sendToAribaFlag");
    System.out.println("sendToAribaFlag"+sendToAribaFlag);
    if(sendToAribaFlag != null && sendToAribaFlag.trim().length()>0)
    {
    	SendInvoiceToAribaManager sendInvoiceToAribaManager = (SendInvoiceToAribaManager)ServiceLocator.getInstance().getBean("sendInvoiceToAribaManager");
    	String aribaResult = sendInvoiceToAribaManager.sendToAriba(sendToAribaFlag);
    	System.out.println("ariba result is "+ aribaResult);
    	request.setAttribute("aribaResult", aribaResult);
    	addJobOrder.setSendToAribaFlag(aribaResult);
    	return showForm(request, response, errors);
    }
   
    String conToRegFlag = request.getParameter("conToRegFlag");
    String jobContractIdstr = request.getParameter("jobContractId");
    if(conToRegFlag!=null && conToRegFlag.trim().length()>0 && jobContractIdstr!=null && jobContractIdstr.trim().length()>0){
       System.out.println("conToRegFlag"+conToRegFlag);
      long jobContractId = Long.parseLong(jobContractIdstr);
      long jobContractInvoiceId = Long.parseLong(conToRegFlag);
      JobContractInvoice jobContractInvoice = prebillService.getJobContractInvoiceById(jobContractInvoiceId);
      if(jobContractInvoice !=null && jobContractInvoice.getConsolInvoiceNo() != null && jobContractInvoice.getConsolInvoiceNo().trim().length()>0){
    	  errors.reject("invoice.convert.consol.regular.error", new Object[] {jobContractInvoice.getConsolInvoiceNo()}, null);
          return showForm(request, response, errors);
      }
      prebillService.convertConToReg( jobContractId, jobContractInvoiceId, InvoiceUtil.getInvoiceDir(), InvoiceUtil.getJasperDir());
    }

    String invoiceFileIdStr = request.getParameter("invoiceFileId");
    String countableStr = request.getParameter("invoiceFileCountable");
    
    if(invoiceFileIdStr!=null && invoiceFileIdStr.trim().length()>0 && countableStr!=null && countableStr.trim().length()>0  ){
       boolean countable = true;
       if(countableStr.equals("N"))
    	   countable = false;
      long invoiceFileId = Long.parseLong(invoiceFileIdStr);
      prebillService.setInvoiceFileCountable(invoiceFileId, countable);
    }

    //regenerate pdf
    String regeneratePdfInvoice = request.getParameter("regeneratePdfInvoice");
    System.out.println("regeneratePdfInvoice"+regeneratePdfInvoice);
    if(regeneratePdfInvoice != null && regeneratePdfInvoice.trim().length()>0)
    {
      try{
    	  prebillService.generateInvoicePDF(Long.parseLong(regeneratePdfInvoice), InvoiceUtil.getInvoiceDir(), InvoiceUtil.getJasperDir(), false);
      }
      catch(Exception e){
    	/* For Itrack issue # 122836 - START */		  
    	  prebillService.updateInvoicePdfGeneratedFlag(Long.parseLong(regeneratePdfInvoice), false);
	    /* For Itrack issue # 122836 - END */
        errors.reject("INVOICE_GENERATION_ERROR", new Object[] {"invoiceID="+regeneratePdfInvoice}, null);
        return showForm(request, response, errors);
      }
    }
    else
    {
      JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");

      jobService.updateJobOrderStatus(
        addJobOrder.getJobOrder().getJobNumber(),
        addJobOrder.getJobOrder().getJobStatus()
      );
    }
    
    

    ModelAndView modelAndView = new ModelAndView("edit-job-view-invoice-r");
    modelAndView.addObject("jobNumber", addJobOrder.getJobOrder().getJobNumber());
    modelAndView.addObject("contractIndex", addJobOrder.getContractIndex());

    return modelAndView;
  }

  protected boolean suppressValidation(HttpServletRequest request)
  {
    String refreshing = request.getParameter("refreshing");
    if((refreshing != null) && "true".equals(refreshing))
    {
      return true;
    }

    // START : #119240
	String showNextListFlag = request.getParameter("showNextListFlag");
	String showPrevListFlag = request.getParameter("showPrevListFlag");
	if(showNextListFlag != null && showNextListFlag.equals("next")){
		return true;
	}
	if(showPrevListFlag != null && showPrevListFlag.equals("prev")){
		return true;
	}
	// END : #119240
    
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
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    dateFormat.setLenient(false);
    binder.registerCustomEditor(java.util.Date.class, null, new CustomDateEditor(dateFormat, true));
  }


  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
   */
  protected Object formBackingObject(
    HttpServletRequest request
  ) throws Exception
  {
    JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
    ContractService contractService = (ContractService)ServiceLocator.getInstance().getBean("contractService");
    CustomerService customerService = (CustomerService)ServiceLocator.getInstance().getBean("customerService");
    CalculatorService calculatorService = (CalculatorService)ServiceLocator.getInstance().getBean("calculatorService");
    JobContractResultService jobContractResultService = (JobContractResultService)ServiceLocator.getInstance().getBean("jobContractResultService");

    AddJobOrder addJobOrder = new AddJobOrder();
    String jobNumber = request.getParameter("jobNumber");
    String contractIndex = request.getParameter("contractIndex");
    if(contractIndex != null && !contractIndex.trim().equals(""))
    {
      addJobOrder.setContractIndex(Integer.parseInt(contractIndex));
    }
    else
      addJobOrder.setContractIndex(0);

    try {
    JobOrder jobOrder = jobService.getJobOrderByJobNumberWithInvoiceInfo(jobNumber);

    if(jobOrder != null && jobOrder.getJobContracts() != null && jobOrder.getJobContracts().size()>0)
    {
      List jobContractList = new ArrayList(jobOrder.getJobContracts());
      JobUtil.sortJobContractsById(jobContractList);
      AddJobContract[] addJobContracts = new AddJobContract[jobContractList.size()];
      for(int i=0; i<jobContractList.size(); i++)
      {
        AddJobContract addJobContract = new AddJobContract();
        JobContract jobContract = (JobContract) jobContractList.get(i);

        List jobContractInvoiceList = new ArrayList();
        jobContractInvoiceList.addAll(jobContract.getJobContractInvoices());

        Collections.sort(
          jobContractInvoiceList,
          new Comparator()
          {
            public int compare(Object o1, Object o2)
            {
              JobContractInvoice p1 = (JobContractInvoice)o1;
              JobContractInvoice p2 = (JobContractInvoice)o2;

              String invoice1 = p1.getInvoice();
              String invoice2 = p2.getInvoice();

              if(invoice1 == null)
              {
                if(invoice2 == null) return 0;
                else return 1;
              }
              else if(invoice2 == null)
              {
                return -1;
              }

              return invoice1.compareTo(invoice2);
            }
          }
        );
        addJobContract.setJobContractInvoiceList(jobContractInvoiceList);

        addJobContract.setJobContract(jobContract);
        Contract contract = contractService.getContractByContractCode(jobContract.getContractCode());
        if(contract != null)
          addJobContract.setContractDesc(contract.getDescription());
        else
          addJobContract.setContractDesc("");
        Customer customer = customerService.getCustomerByCustCode(jobContract.getCustCode());
        if(customer != null)
          addJobContract.setCompanyName(customer.getName());
        else
          addJobContract.setCompanyName("");
        addJobContracts[i] = addJobContract;
      }
      addJobOrder.setAddJobContracts(addJobContracts);
    }
    if(jobOrder == null) jobOrder = new JobOrder();

    String jobType = request.getParameter("jobType");
    if((jobType != null) && !"".equals(jobType.trim()))
    {
      jobOrder.setJobType(jobType);
    }
	 
	 // START : #119240
    	addJobOrder.setJobNumber(jobNumber);
        addJobOrder.setCurrPageIdentifier(Constants.JOB_INVOICE);
	    // Setting next list and prev list values
		if(request.getSession() != null)
		{
			JobSearch jobSearch=(JobSearch)request.getSession().getAttribute("MySearchJobOrder");
			String orginFromJobSearch =  (String)request.getSession().getAttribute("orginFromJobSearch");
			JobUtil.setPrevNextJobFlags(addJobOrder, jobSearch, jobOrder, orginFromJobSearch);
		}
		//end
				
      // END : #119240  

    addJobOrder.setJobOrder(jobOrder);
    } catch(ServiceException e)
    {
      throw new ServiceException(e.getMessage(), e.getParams(), e);
    }
    catch(Throwable t)
    {
        t.printStackTrace();
        throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
    }
    return addJobOrder;
  }
}
