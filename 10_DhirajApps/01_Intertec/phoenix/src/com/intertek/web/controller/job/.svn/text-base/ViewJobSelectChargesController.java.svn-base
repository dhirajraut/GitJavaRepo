package com.intertek.web.controller.job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.RedirectView;

import com.intertek.domain.AddJobOrder;
import com.intertek.domain.JobSearch;
import com.intertek.entity.JobContract;
import com.intertek.entity.JobOrder;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.JobContractSrc;
import com.intertek.service.JobService;
import com.intertek.util.Constants;
import com.intertek.util.JobUtil;

public class ViewJobSelectChargesController extends AbstractController
{
  public ViewJobSelectChargesController() {
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
   */
  protected ModelAndView handleRequestInternal(
    HttpServletRequest request,
    HttpServletResponse response
  ) throws Exception
  {
    String jobNumber = request.getParameter("jobNumber");

    JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
    JobContractSrc jobContractSrc = (JobContractSrc)ServiceLocator.getInstance().getBean("jobContractSrc");

    JobOrder jobOrder = jobService.getJobOrderByJobNumberWithJobContracts(jobNumber);
    if(jobOrder == null) jobOrder = new JobOrder();

    List jobContracts = new ArrayList();
    Iterator contractItr = jobOrder.getJobContracts().iterator();
    while(contractItr.hasNext())
    {
      JobContract oldJobContract = (JobContract)contractItr.next();
      JobContract jobContract = jobContractSrc.getJobContractByJobContractIdWithDetail(oldJobContract.getId());
      if(jobContract == null) continue;
      jobContract.setJobOrder(jobOrder);
      jobContracts.add(jobContract);
    }

    jobOrder.getJobContracts().clear();
    jobOrder.getJobContracts().addAll(jobContracts);

    Map command = new HashMap();
    command.put("jobOrder", jobOrder);

    // START : #119240
    AddJobOrder addJobOrder = new AddJobOrder();
    if(request.getSession() != null)
	{
		JobSearch jobSearch=(JobSearch)request.getSession().getAttribute("MySearchJobOrder");
		String orginFromJobSearch =  (String)request.getSession().getAttribute("orginFromJobSearch");		
		addJobOrder.setCurrPageIdentifier(Constants.JOB_SELECT_CHARGES);
		JobUtil.setPrevNextJobFlags(addJobOrder, jobSearch, jobOrder, orginFromJobSearch);
		//command.put("originateFromSearchJob", addJobOrder.getOriginateFromSearchJob());
		command.put("addJobOrder", addJobOrder);
	}	 
    
    String noPrev = request.getParameter("noPrevJob");
	if(noPrev !=null && noPrev.equals("true")){
		request.setAttribute("noJobMessage", "No Previous Job!");
		return new ModelAndView("view-job-select-charges", "command", command);
	}
	String noNext = request.getParameter("noNextJob");
	if(noNext !=null && noNext.equals("true")){
		request.setAttribute("noJobMessage", "No Next Job!");
		return new ModelAndView("view-job-select-charges", "command", command);
	}	 
 
	 // String nextList=request.getParameter("nextListFlag");
   String nextList=request.getParameter("showNextListFlag");

	if (nextList != null && nextList.trim().equals("next")) {
		jobNumber = jobOrder.getJobNumber();
		try{
			if (request.getSession() != null) {
				JobSearch jobSearch = (JobSearch) request.getSession().getAttribute("MySearchJobOrder");
				jobOrder = jobService.getNextOrPreviousJobNumber(jobSearch,	jobNumber, nextList);
			}
		}catch(Exception e){
			return new ModelAndView("view-job-select-charges", "command", command);
		}
		
		String url = jobService.getUrlByCurrPageIdentifier(jobOrder, addJobOrder.getCurrPageIdentifier()) + "?jobNumber=";
		return new ModelAndView(new RedirectView(url + jobOrder.getJobNumber() + "&reqFrom=" + addJobOrder.getOriginateFromSearchJob()));

	}	
	
	
	//String prevList=request.getParameter("prevListFlag");
	String prevList=request.getParameter("showPrevListFlag");
	
	if (prevList != null && prevList.trim().equals("prev")) {
		jobNumber = jobOrder.getJobNumber();
		try{
			if (request.getSession() != null) {
				JobSearch jobSearch = (JobSearch) request.getSession().getAttribute("MySearchJobOrder");
				jobOrder = jobService.getNextOrPreviousJobNumber(jobSearch,	jobNumber, prevList);
			}
		}catch(Exception e){
			
			new ModelAndView("view-job-select-charges", "command", command);
		}	

		String url = jobService.getUrlByCurrPageIdentifier(jobOrder, addJobOrder.getCurrPageIdentifier())
				+ "?jobNumber=";
		return new ModelAndView(new RedirectView(url + jobOrder.getJobNumber() + "&reqFrom=" + addJobOrder.getOriginateFromSearchJob()));
	}

	/*if (jobOrder.getTowingCompany() == null
			|| jobOrder.getTowingCompany().getName() == null
			|| jobOrder.getTowingCompany().getName().trim().equals(""))
		jobOrder.setTowingCompany(null);

	if (jobOrder.getShippingAgent() == null
			|| jobOrder.getShippingAgent().getName() == null
			|| jobOrder.getShippingAgent().getName().trim().equals(""))
		jobOrder.setShippingAgent(null);

	if (jobOrder.getServiceLocation() == null
			|| jobOrder.getServiceLocation().getName() == null
			|| jobOrder.getServiceLocation().getName().trim().equals(""))
		jobOrder.setServiceLocation(null);

	jobService.updateJobOrder(jobOrder);*/
    
    
    // END : #119240
	
    return new ModelAndView("view-job-select-charges", "command", command);
  }
}
