package com.intertek.web.controller.job;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.intertek.domain.AddJobContract;
import com.intertek.domain.AddJobOrder;
import com.intertek.domain.AddJobProd;
import com.intertek.domain.AddJobProdSample;
import com.intertek.domain.AddJobVessel;
import com.intertek.domain.JobSearch;
import com.intertek.entity.DropDowns;
import com.intertek.entity.Event;
import com.intertek.entity.JobContract;
import com.intertek.entity.JobEvent;
import com.intertek.entity.JobEventId;
import com.intertek.entity.JobOrder;
import com.intertek.entity.JobProd;
import com.intertek.entity.JobProdId;
import com.intertek.entity.JobProdQty;
import com.intertek.entity.JobProdSample;
import com.intertek.entity.JobProdSampleId;
import com.intertek.entity.JobSlate;
import com.intertek.entity.JobTest;
import com.intertek.entity.JobVessel;
import com.intertek.entity.JobVesselId;
import com.intertek.entity.RevisionNotes;
import com.intertek.entity.Slate;
import com.intertek.entity.Test;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.DropdownService;
import com.intertek.service.JobService;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;
import com.intertek.util.JobUtil;
import com.intertek.web.controller.BaseSimpleFormController;

public class ViewJobOperationalInstrFormController extends BaseSimpleFormController
{
  public ViewJobOperationalInstrFormController() {
    super();
    System.out.println("inside constructor");
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
    System.out.println("inside onSubmit of EditJobOperationalInstrFormController");
      AddJobOrder addJobOrder = (AddJobOrder)command;
      JobOrder jobOrder = addJobOrder.getJobOrder();
      String jobNumber = jobOrder.getJobNumber();
      String addOrDeleteVessel = addJobOrder.getAddOrDeleteVessel();
      String addOrDeleteProduct = addJobOrder.getAddOrDeleteProduct();
      String addOrDeleteEvent = addJobOrder.getAddOrDeleteEvent();
      String addOrDeleteSampleLoc = addJobOrder.getAddOrDeleteSampleLoc();
      String addOrDeleteQty = addJobOrder.getAddOrDeleteQty();
      String addOrDeleteTest = addJobOrder.getAddOrDeleteTest();
      String addOrDeleteSlate = addJobOrder.getAddOrDeleteSlate();
      String applicableContractFlag = addJobOrder.getApplicableContractFlag();
      String labAnalysisFlag = addJobOrder.getLabAnalysisFlag();
      String otApprovedFlag = addJobOrder.getOtApprovedFlag();
      int vesselIndex = addJobOrder.getVesselIndex();
      int targetVesselIndex = addJobOrder.getTargetVesselIndex();
      int productIndex = addJobOrder.getProductIndex();
      int eventIndex = addJobOrder.getEventIndex();
      int sampleLocIndex = addJobOrder.getSampleLocIndex();
      int qtyIndex = addJobOrder.getQtyIndex();
      int testIndex = addJobOrder.getTestIndex();
      int slateIndex = addJobOrder.getSlateIndex();
      String copyFlag = addJobOrder.getCopyFlag();
      String chosenContracts = addJobOrder.getChosenContracts();
      String chosenTestIds = addJobOrder.getChosenTestIds();
      String chosenSlateIds = addJobOrder.getChosenSlateIds();
      String instructionFlag = addJobOrder.getInstructionFlag();
      


      JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");

      jobOrder = jobService.saveJobOrder(jobOrder);
     
      // START : #119240
      try {
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
			if(nextList != null && nextList.trim().equals("next"))
			{
				jobNumber=jobOrder.getJobNumber();
				try
				{
					if(request.getSession() != null)
					{
						JobSearch jobSearch=(JobSearch)request.getSession().getAttribute("MySearchJobOrder");
						jobOrder=jobService.getNextOrPreviousJobNumber(jobSearch,jobNumber,nextList);
					}
				}catch(Exception e){
					errors.reject("search.job.order.error", new Object[] { e.getMessage() }, null);
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
				jobNumber=jobOrder.getJobNumber();

				try
				{
					if(request.getSession() != null)
					{
						JobSearch jobSearch=(JobSearch)request.getSession().getAttribute("MySearchJobOrder");
						jobOrder=jobService.getNextOrPreviousJobNumber(jobSearch,jobNumber,prevList);
					}
				}catch(Exception e){
					errors.reject("search.job.order.error", new Object[] { e.getMessage() }, null);
					return showForm(request, response, errors);
				}
				
				//String url=jobService.getUrlHrefJobTypeByJobOrder(jobOrder)+"?jobNumber=";
				String url=jobService.getUrlByCurrPageIdentifier(jobOrder, addJobOrder.getCurrPageIdentifier())+"?jobNumber=";
				return new ModelAndView(new RedirectView(url+jobOrder.getJobNumber()+"&reqFrom="+addJobOrder.getOriginateFromSearchJob()));

			}

			if(jobOrder.getTowingCompany() == null || jobOrder.getTowingCompany().getName() == null || jobOrder.getTowingCompany().getName().trim().equals(""))
				jobOrder.setTowingCompany(null);

			if(jobOrder.getShippingAgent() == null || jobOrder.getShippingAgent().getName() == null || jobOrder.getShippingAgent().getName().trim().equals(""))
				jobOrder.setShippingAgent(null);

			if(jobOrder.getServiceLocation() == null || jobOrder.getServiceLocation().getName() == null || jobOrder.getServiceLocation().getName().trim().equals(""))
				jobOrder.setServiceLocation(null);

			jobService.updateJobOrder(jobOrder);
		} catch (Exception e) {
			e.printStackTrace();
			errors.reject("edit.job.order.error", new Object[] { e.getMessage() }, null);
			return showForm(request, response, errors);
		}
      // END : #119240
      
      String editViewName = "edit_job_operational_info_insp.htm";
      String readOnlyViewName = "view_job_operational_info_insp.htm";
      /*String viewName = "";
      if(jobOrder.getJobType().trim().equals(Constants.ANALYTICAL_SERVICE_JOBTYPE))
        viewName = "edit_job_entry_analytical_service.htm";
      else if(jobOrder.getJobType().trim().equals(Constants.OUTSOURCING_JOBTYPE))
        viewName = "edit_job_entry_outsourcing.htm";
      else if(jobOrder.getJobType().trim().equals(Constants.INSPECTION_JOBTYPE))
        viewName = "edit_job_entry_insp.htm";
      else if(jobOrder.getJobType().trim().equals(Constants.MARINE_JOBTYPE))
        viewName = "edit_job_entry_marine.htm";*/
      String nextFlag = addJobOrder.getNextPageFlag();
		if(nextFlag != null && nextFlag.trim().equals("1"))
  		{

  			return new ModelAndView(new RedirectView("view_job_select_charges.htm"), "jobNumber", jobOrder.getJobNumber());
  			
  		}
		
		if(jobOrder.getJobStatus().trim().equals(Constants.CANCELLED_STATUS) ||
				jobOrder.getJobStatus().trim().equals(Constants.CLOSED_STATUS))
			return new ModelAndView(new RedirectView(readOnlyViewName), "jobNumber", jobOrder.getJobNumber());
		else
			return new ModelAndView(new RedirectView(editViewName), "jobNumber", jobOrder.getJobNumber());



  }

  protected boolean suppressValidation(HttpServletRequest request)
  {
    System.out.println("inside suppressValidation");
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
    System.out.println("inside initbinder");
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

    AddJobOrder addJobOrder = new AddJobOrder();

    JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
    DropdownService dropdownService = (DropdownService)ServiceLocator.getInstance().getBean("dropdownService");

    String jobNumber = request.getParameter("jobNumber");
    //JobOrder jobOrder = jobService.getJobOrderByJobNumberWithJobContracts(request.getParameter("jobNumber"));
    JobOrder jobOrder = jobService.getJobOrderByJobNumberWithDetail(request.getParameter("jobNumber"));
   // JobOrder jobOrder = null;
    if(jobOrder == null)
      jobOrder = new JobOrder();




      String jobType = request.getParameter("jobType");
      if((jobType != null) && !"".equals(jobType.trim()))
      {
        jobOrder.setJobType(jobType);
      }
     
      // START : #119240
      addJobOrder.setJobNumber(jobNumber);
      addJobOrder.setCurrPageIdentifier(Constants.JOB_INSTRUCTIONS);
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
    boolean viewOnly = true;
    //Dummy contract codes for operational info testing; to be removed later
    try {
    	
	//START: Issue # 75052
    List<RevisionNotes> lstNotes = jobService.getRevisionNotes(jobNumber);
    
    if(null != lstNotes && lstNotes.size() > 0){
    	RevisionNotes[] arrRevisionNotes = new RevisionNotes[lstNotes.size()]; 
        RevisionNotes revNotes = null;
    	for(int indx=0; indx< lstNotes.size(); indx++){
    		revNotes = (RevisionNotes)lstNotes.get(indx);
    		revNotes.setRevisionDateTime(DateUtil.parseDate(DateUtil.formatDate(revNotes.getRevisionDateTime(),"EEE, d MMM yyyy HH:mm:ss z"),"EEE, d MMM yyyy HH:mm:ss z"));
    		arrRevisionNotes[indx] = revNotes;
    		revNotes = null;
    	}
    	addJobOrder.setArrRevisionNotes(arrRevisionNotes);
    }
    lstNotes = null;
    //END: Issue # 75052
    	
    Set JobContractsSet = jobOrder.getJobContracts();
    String[] jobContractCodes = null;
    if(JobContractsSet != null && JobContractsSet.size() > 0)
    {
      AddJobContract[] addJobContracts = new AddJobContract[JobContractsSet.size()];
      jobContractCodes = new String[JobContractsSet.size()];
      int counter = 0;
      Iterator iter = JobContractsSet.iterator();

      while(iter.hasNext())
      {
          AddJobContract addJobContract = new AddJobContract();
          JobContract jobContract = null;
          jobContract = (JobContract)iter.next();
          jobContractCodes[counter] = jobContract.getContractCode();
          addJobContract.setJobContract(jobContract);
          addJobContract.setJobContractIndex(counter+1);
          addJobContracts[counter] = addJobContract;
          counter++;
      }

      addJobOrder.setAddJobContracts(addJobContracts);
      addJobOrder.setViewOnly(viewOnly);
    }
    //Load operational information for given job type
    //Load Vessels
    //List jobVessels = jobService.getJobVesselsByJobNumber(jobNumber);
    List jobVessels = new ArrayList(jobOrder.getJobVessels());

    if(jobVessels != null && jobVessels.size() > 0)
    {
      AddJobVessel[] addJobVessels = new AddJobVessel[jobVessels.size()];
      for(int i =0;i<jobVessels.size();i++)
      {
        AddJobVessel addJobVessel = new AddJobVessel();
        JobVessel jobVessel = (JobVessel)jobVessels.get(i);
        addJobVessel.setJobVessel(jobVessel);


        //        Load Products related to each vessel
        //List jobProds = jobService.getJobProductsByJobNumberAndVesselRow(jobNumber,jobVessel.getJobVesselId().getLinkedVslRow());
        List jobProds = new ArrayList(jobVessel.getJobProds());
          if(jobProds != null && jobProds.size() > 0)
          {
            AddJobProd[] addJobProds = new AddJobProd[jobProds.size()];
            for(int j=0;j<jobProds.size();j++)
            {
              AddJobProd addJobProd = new AddJobProd();
              JobProd jobProd = (JobProd)jobProds.get(j);
              addJobProd.setJobProd(jobProd);

              if(addJobProd.getJobProd().getOption() != null && (!addJobProd.getJobProd().getOption().trim().equals("")))
              {
                DropDowns dd = dropdownService.getDropdownByDropdownCodeAndType(addJobProd.getJobProd().getOption(), Constants.OPTION);


                if(dd != null)
                {
                  addJobProd.setOptionDesc(dd.getFieldValue());
                }
              }

              //Set Applicable Contracts
              if(jobProd.getJobProdContracts() != null && jobProd.getJobProdContracts().size() > 0)
              {
                Iterator iter = jobProd.getJobProdContracts().iterator();
                AddJobContract[] applicableContracts = new AddJobContract[jobProd.getJobProdContracts().size()];
                int count = 0;
                while(iter.hasNext())
                {
                	//AddJobContract addJobContract = new AddJobContract();
                    JobContract jobContract = (JobContract) iter.next();
                    AddJobContract addJobContract = JobUtil.getAddJobContractByJobContractId(addJobOrder, jobContract.getId());
                    addJobContract.setJobContract(jobContract);
                    applicableContracts[count] = addJobContract;
                    count++;
                }
                addJobProd.setApplicableContracts(applicableContracts);
              }

              //Load Quantity related to each product
              //List jobProdQtys = jobService.getJobProdQtysByJobNumberVesselRowAndProductName(jobNumber,jobVessel.getJobVesselId().getLinkedVslRow(),jobProd.getJobProdId().getProdSeqNum());
              List jobProdQtys = new ArrayList(jobProd.getJobProdQtys());
                if(jobProdQtys != null && jobProdQtys.size() > 0)
                {
                  JobProdQty[] jobProdQts = new JobProdQty[jobProdQtys.size()];
                  for(int k=0;k<jobProdQtys.size();k++)
                  {
                    jobProdQts[k] = (JobProdQty)jobProdQtys.get(k);
                  }
                  addJobProd.setJobProdQtys(jobProdQts);

                }
              //Load Inspection Events related to each product
              //List jobEventList = jobService.getJobEventsByJobNumberVesselRowAndProductName(jobNumber,jobVessel.getJobVesselId().getLinkedVslRow(),jobProd.getJobProdId().getProdSeqNum());
              List jobEventList = new ArrayList(jobProd.getJobEvents());
              int eventPos = 0;
              
              if(jobEventList != null && jobEventList.size() > 0)
                {
                  JobEvent[] jobEvents = new JobEvent[jobEventList.size()];
                  for(int k=0;k<jobEventList.size();k++)
                  {
                    JobEvent jobEvent = (JobEvent)jobEventList.get(k);
                    eventPos = jobEvent.getSortOrder() - 1;
                    jobEvents[eventPos] = jobEvent;
                  }
                  addJobProd.setJobEvents(jobEvents);

                }
              //Load Sample Locations related to each product
              //List jobProdSamples = jobService.getJobProdSamplesByJobNumberVesselRowAndProductName(jobNumber,jobVessel.getJobVesselId().getLinkedVslRow(),jobProd.getJobProdId().getProdSeqNum());
              List jobProdSamples = new ArrayList(jobProd.getJobProdSamples());
                if(jobProdSamples != null && jobProdSamples.size() > 0)
                {
                  AddJobProdSample[] addJobProdSamples = new AddJobProdSample[jobProdSamples.size()];
                  int samplePos = 0;
                  for(int l=0;l<jobProdSamples.size();l++)
                  {
                    AddJobProdSample addJobProdSample = new AddJobProdSample();
                    JobProdSample jobProdSample = (JobProdSample)jobProdSamples.get(l);
                    addJobProdSample.setJobProdSample(jobProdSample);

                    //Load Tests related to each sample location
                    //List jobTestsList = jobService.getJobTestsByJobNumberVesselRowProductAndSampleLoc(jobNumber,jobVessel.getJobVesselId().getLinkedVslRow(),jobProd.getJobProdId().getProdSeqNum(),jobProdSample.getJobProdSampleId().getSampSeqId());
                    List jobTestsList = new ArrayList(jobProdSample.getJobTests());

                    if(jobTestsList != null && jobTestsList.size() > 0)
                      {
                      JobTest[] jobTests = new JobTest[jobTestsList.size()];
                        for(int k=0;k<jobTestsList.size();k++)
                        {
                          jobTests[k] = (JobTest)jobTestsList.get(k);
                        }
                        addJobProdSample.setJobTests(jobTests);
                      }
                    //Load Slates related to each sample location
                    //List jobSlatesList = jobService.getJobSlatesByJobNumberVesselRowProductAndSampleLoc(jobNumber,jobVessel.getJobVesselId().getLinkedVslRow(),jobProd.getJobProdId().getProdSeqNum(),jobProdSample.getJobProdSampleId().getSampSeqId());
                    List jobSlatesList = new ArrayList(jobProdSample.getJobSlates());
                    if(jobSlatesList != null && jobSlatesList.size() > 0)
                      {
                      JobSlate[] jobSlates = new JobSlate[jobSlatesList.size()];
                        for(int k=0;k<jobSlatesList.size();k++)
                        {
                          jobSlates[k] = (JobSlate)jobSlatesList.get(k);
                        }
                        addJobProdSample.setJobSlates(jobSlates);
                      }
                    samplePos = jobProdSample.getSortOrder() - 1;
                    addJobProdSamples[samplePos] = addJobProdSample;
                  }
                  addJobProd.setAddJobProdSamples(addJobProdSamples);

                }
                //addJobProd.setJobContractCodes(jobContractCodes);
                if(addJobProd.getApplicableContracts() != null && addJobProd.getApplicableContracts().length > 0)
                {
                  AddJobContract[] nonAppJobContracts = new AddJobContract[addJobOrder.getAddJobContracts().length - addJobProd.getApplicableContracts().length];
                  int count = 0;
                  for(int m=0;m<addJobOrder.getAddJobContracts().length;m++)
                  {
                	  AddJobContract addJobContract = addJobOrder.getAddJobContracts()[m];
                    boolean appFlag = false;
                    for(int n=0;n<addJobProd.getApplicableContracts().length;n++)
                    {
                    	AddJobContract appAddJobContract = addJobProd.getApplicableContracts()[n];
                      if((!appFlag) && appAddJobContract.getJobContract().getId() == addJobContract.getJobContract().getId())
                      {
                        appFlag = true;
                      }
                    }
                    if(!appFlag)
                    {
                    	nonAppJobContracts[count] = addJobContract;
                      count++;
                    }
                  }
                  addJobProd.setNotApplicableContracts(nonAppJobContracts);
                }
                else
                  addJobProd.setNotApplicableContracts(addJobOrder.getAddJobContracts());
                //addJobProd.setApplicableContractCodes(jobContractCodes);
                addJobProds[j] = addJobProd;
                }
            addJobVessel.setAddJobProds(addJobProds);
          }
          addJobVessels[i] = addJobVessel;
      }
      if(addJobVessels != null)
      System.out.println("size of jobVeseels : "+addJobVessels.length);
      addJobOrder.setAddJobVessels(addJobVessels);
    }
    if(addJobOrder.getAddJobVessels() == null || addJobOrder.getAddJobVessels().length <=0)
    {
      addJobOrder.setAddJobVessels(addJobVessel(addJobOrder.getAddJobVessels(),null,addJobOrder.getAddJobContracts()));
      AddJobVessel addJobVessel = addJobOrder.getAddJobVessels()[0];
      addJobVessel.setAddJobProds(addJobProducts(addJobVessel.getAddJobProds(),null,addJobOrder.getAddJobContracts()));
      addJobOrder.getAddJobVessels()[0] = addJobVessel;
    }
    } catch(ServiceException e)
    {
     	throw new ServiceException(e.getMessage(), e.getParams(), e);
    } catch(Throwable t)
    {
        t.printStackTrace();
        throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
    } 
    return addJobOrder;

    }



  private AddJobVessel[] addJobVessel(AddJobVessel[] addJobVessels, AddJobVessel addJobVesselToCopy,AddJobContract[] addJobContracts)
  {
    AddJobVessel addJobVessel = new AddJobVessel();

    JobVessel jobVessel = new JobVessel();
    JobVesselId jobVesselId = new JobVesselId();
    jobVessel.setJobVesselId(jobVesselId);
    addJobVessel.setJobVessel(jobVessel);


    if(addJobVesselToCopy != null)

    {
      AddJobProd[] addJobProds = new AddJobProd[addJobVesselToCopy.getAddJobProds().length];
      //addJobVessel = addJobVesselToAdd;
       for(int i=0;i<addJobVesselToCopy.getAddJobProds().length;i++)
       {
         AddJobProd addJobProd = new AddJobProd();
         JobProd jobProd = new JobProd();
         jobProd.setJobProdId(new JobProdId());
         jobProd.setProductQty(0);
         jobProd.setUom("");
         jobProd.setOption("");
         jobProd.setPlusMinus("");
         jobProd.setPlusMinusPct(0);
         jobProd.setDrafts("");
         jobProd.setTanks("");
         addJobProd.setJobProd(jobProd);
         addJobProd.setJobProdQtys(null);
         addJobProd.setAddJobProdSamples(addJobVesselToCopy.getAddJobProds()[i].getAddJobProdSamples());
         addJobProd.setJobEvents(addJobVesselToCopy.getAddJobProds()[i].getJobEvents());


            if(addJobContracts != null && addJobContracts.length > 0)
            {
            
              addJobProd.setApplicableContracts(addJobContracts);
            }
         addJobProds[i] = addJobProd;

       }
       addJobVessel.setAddJobProds(addJobProds);
       addJobVessel.setJobVessel(addJobVesselToCopy.getJobVessel());
    }

    AddJobVessel[] results = null;
    if(addJobVessels == null) results = new AddJobVessel[1];
    else results = new AddJobVessel[addJobVessels.length + 1];
    if(addJobVessels != null)
    {

      for(int i=0; i<addJobVessels.length; i++)
      {

        results[i] = addJobVessels[i];

      }
    }

    results[results.length - 1] = addJobVessel;

    return results;
  }

  private AddJobVessel[] deleteJobVessel(AddJobVessel[] addJobVessels, int index)
  {
    if(addJobVessels == null) return null;
    if(addJobVessels.length <= 0) return addJobVessels;

    AddJobVessel[] results = new AddJobVessel[addJobVessels.length - 1];

    int count = 0;
    for(int i=0; i<addJobVessels.length; i++)
    {
      if(i == index) continue;
      results[count] = addJobVessels[i];

      count ++;
    }

    return results;
  }

  private AddJobProd[] addJobProducts(AddJobProd[] addJobProds,AddJobProd addJobProdToCopy,AddJobContract[] addJobContracts)
  {
      AddJobProd addJobProd = new AddJobProd();
      JobProd jobProd = new JobProd();
      jobProd.setJobProdId(new JobProdId());

      addJobProd.setJobProd(jobProd);

      if(addJobContracts != null && addJobContracts.length > 0)
      {
       
        addJobProd.setApplicableContracts(addJobContracts);
      }
      if(addJobProdToCopy != null)

      {
             jobProd.setDestination(addJobProdToCopy.getJobProd().getDestination());
             jobProd.setGroup(addJobProdToCopy.getJobProd().getGroup());
             jobProd.setJobProductName(addJobProdToCopy.getJobProd().getJobProductName());
             //jobProd.getJobProdId().setJobProductName(addJobProdToCopy.getJobProd().getJobProdId().getJobProductName());
           jobProd.setProductQty(0);
           jobProd.setUom("");
           jobProd.setOption("");
           jobProd.setPlusMinus("");
           jobProd.setPlusMinusPct(0);
           jobProd.setDrafts("");
           jobProd.setTanks("");
           addJobProd.setJobProd(jobProd);
           addJobProd.setJobProdQtys(null);
           addJobProd.setAddJobProdSamples(addJobProdToCopy.getAddJobProdSamples());
           addJobProd.setJobEvents(addJobProdToCopy.getJobEvents());


      }


      AddJobProd[] results = null;
    if(addJobProds == null) results = new AddJobProd[1];
    else results = new AddJobProd[addJobProds.length + 1];

    if(addJobProds != null)
    {
      for(int i=0; i<addJobProds.length; i++)
      {
        results[i] = addJobProds[i];
      }
    }
    results[results.length - 1] = addJobProd;

    return results;
  }

  private AddJobProd[] deleteJobProducts(AddJobProd[] addJobProds, int index)
  {
    if(addJobProds == null) return null;
    if(addJobProds.length <= 0) return addJobProds;

    AddJobProd[] results = new AddJobProd[addJobProds.length - 1];

    int count = 0;
    for(int i=0; i<addJobProds.length; i++)
    {
      if(i == index) continue;
      results[count] = addJobProds[i];

      count ++;
    }

    return results;
  }

  private JobEvent[] addInspectionEvents(JobEvent[] jobEvents)
  {
    JobEvent jobEvent = new JobEvent();
    JobEventId jobEventId = new JobEventId();
    Event event = new Event();
    
   
    jobEvent.setJobEventId(jobEventId);
    jobEvent.setEvent(event);

    JobEvent[] results = null;
    if(jobEvents == null)
    {
      results = new JobEvent[1];
      jobEvent.setSortOrder(1);
    }
    else
    {
      results = new JobEvent[jobEvents.length + 1];
      jobEvent.setSortOrder(jobEvents.length + 1);
    }

    if(jobEvents != null)
    {
      for(int i=0; i<jobEvents.length; i++)
      {
        results[i] = jobEvents[i];
      }
    }
    results[results.length - 1] = jobEvent;

    return results;
  }

  private JobEvent[] deleteInspectionEvents(JobEvent[] jobEvents, int index)
  {
    if(jobEvents == null) return null;
    if(jobEvents.length <= 0) return jobEvents;

    JobEvent[] results = new JobEvent[jobEvents.length - 1];

    int count = 0;
    for(int i=0; i<jobEvents.length; i++)
    {
      if(i == index) continue;
      results[count] = jobEvents[i];

      count ++;
    }

    return results;
  }

  private AddJobProdSample[] addProdSamples(AddJobProdSample[] addJobProdSamples)
  {
    AddJobProdSample addJobProdSample = new AddJobProdSample();
    JobProdSample jobProdSample = new JobProdSample();
    JobProdSampleId jobProdSampleId = new JobProdSampleId();
    jobProdSample.setJobProdSampleId(jobProdSampleId);
    addJobProdSample.setJobProdSample(jobProdSample);

    AddJobProdSample[] results = null;
    if(addJobProdSamples == null)
    {
      results = new AddJobProdSample[1];
      jobProdSample.setSortOrder(1);
    }
    else
    {
      results = new AddJobProdSample[addJobProdSamples.length + 1];
      jobProdSample.setSortOrder(addJobProdSamples.length + 1);
    }

    if(addJobProdSamples != null)
    {
      for(int i=0; i<addJobProdSamples.length; i++)
      {
        results[i] = addJobProdSamples[i];
      }
    }
    results[results.length - 1] = addJobProdSample;

    return results;
  }

  private AddJobProdSample[] deleteProdSamples(AddJobProdSample[] addJobProdSamples, int index)
  {
    if(addJobProdSamples == null) return null;
    if(addJobProdSamples.length <= 0) return addJobProdSamples;

    AddJobProdSample[] results = new AddJobProdSample[addJobProdSamples.length - 1];

    int count = 0;
    for(int i=0; i<addJobProdSamples.length; i++)
    {
      if(i == index) continue;
      results[count] = addJobProdSamples[i];

      count ++;
    }

    return results;
  }
  private JobProdQty[] addProdQtys(JobProdQty[] jobProdQtys)
  {
    JobProdQty jobProdQty = new JobProdQty();


    JobProdQty[] results = null;
    if(jobProdQtys == null) results = new JobProdQty[1];
    else results = new JobProdQty[jobProdQtys.length + 1];

    if(jobProdQtys != null)
    {
      for(int i=0; i<jobProdQtys.length; i++)
      {
        results[i] = jobProdQtys[i];
      }
    }
    results[results.length - 1] = jobProdQty;

    return results;
  }

  private JobProdQty[] deleteProdQtys(JobProdQty[] jobProdQtys, int index)
  {
    if(jobProdQtys == null) return null;
    if(jobProdQtys.length <= 0) return jobProdQtys;

    JobProdQty[] results = new JobProdQty[jobProdQtys.length - 1];

    int count = 0;
    for(int i=0; i<jobProdQtys.length; i++)
    {
      if(i == index) continue;
      results[count] = jobProdQtys[i];

      count ++;
    }

    return results;
  }


  private JobTest[] addJobTest(JobTest[] jobTests, Test test)
  {
    JobTest jobTest = new JobTest();
    jobTest.setTest(test);
    jobTest.setOt(true);

    JobTest[] results = null;
    if(jobTests == null) results = new JobTest[1];
    else results = new JobTest[jobTests.length + 1];

    if(jobTests != null)
    {
      for(int i=0; i<jobTests.length; i++)
      {
        results[i] = jobTests[i];
      }
    }
    results[results.length - 1] = jobTest;

    return results;
  }

  private JobTest[] deleteJobTest(JobTest[] jobTests, int index)
  {
    if(jobTests == null) return null;
    if(jobTests.length <= 0) return jobTests;

    JobTest[] results = new JobTest[jobTests.length - 1];

    int count = 0;
    for(int i=0; i<jobTests.length; i++)
    {
      if(i == index) continue;
      results[count] = jobTests[i];

      count ++;
    }

    return results;
  }
  private JobSlate[] addJobSlate(JobSlate[] jobSlates, Slate slate)
  {
    JobSlate jobSlate = new JobSlate();
    jobSlate.setSlate(slate);
    jobSlate.setOt(true);
    JobSlate[] results = null;
    if(jobSlates == null) results = new JobSlate[1];
    else results = new JobSlate[jobSlates.length + 1];

    if(jobSlates != null)
    {
      for(int i=0; i<jobSlates.length; i++)
      {
        results[i] = jobSlates[i];
      }
    }
    results[results.length - 1] = jobSlate;

    return results;
  }

  private JobSlate[] deleteJobSlate(JobSlate[] jobSlates, int index)
  {
    if(jobSlates == null) return null;
    if(jobSlates.length <= 0) return jobSlates;

    JobSlate[] results = new JobSlate[jobSlates.length - 1];

    int count = 0;
    for(int i=0; i<jobSlates.length; i++)
    {
      if(i == index) continue;
      results[count] = jobSlates[i];

      count ++;
    }

    return results;
  }

  private String[] deleteApplicableContract(String[] applicableContracts, String contractToDelete)
  {
	    if(applicableContracts == null) return null;
	    if(applicableContracts.length <= 0) return applicableContracts;

	    String[] results = null;
	    boolean contractExists = false;

	    
	    for(int i=0; i<applicableContracts.length; i++)
	    {
	      if(!contractExists && (applicableContracts[i].trim().equalsIgnoreCase(contractToDelete)))
	    	  contractExists = true;
	    }
	    
	    System.out.println("contractExists :"+contractExists);
	    if(contractExists)
	    {
	    	  results = new String[applicableContracts.length - 1];

	    	    int count = 0;
	    	    for(int i=0; i<applicableContracts.length; i++)
	    	    {
	    	      if(applicableContracts[i].trim().equalsIgnoreCase(contractToDelete)) continue;
	    	      results[count] = applicableContracts[i];

	    	      count ++;
	    	    }
	    }
	    else
	    	return applicableContracts;

	  

	    return results;
  }
  
  private String[] addApplicableContract(String[] applicableContracts, String contractToAdd)
  {
    

    String[] results = null;
    boolean contractExists = false;
    
    if(applicableContracts == null) results = new String[1];
    else
    {
    	if(applicableContracts.length >0)
    	{
    		for(int i=0;i<applicableContracts.length;i++)
    		{
    			if(applicableContracts[i].trim().equalsIgnoreCase(contractToAdd))
    			{
    				contractExists = true;
    				break;
    			}
    				
    		}
    	}
    }
    if(contractExists)
    	return applicableContracts;
    else
    {
    	if(applicableContracts != null)
    	{
    	results = new String[applicableContracts.length + 1];

    	if(applicableContracts != null)
	    {
	      for(int i=0; i<applicableContracts.length; i++)
	      {
	        results[i] = applicableContracts[i];
	      }
	    }
	    results[results.length - 1] = contractToAdd;
    	}
	    return results;
	    
  }
  }

  String[] addJobContractCode(String[] jobContractCodes,String contractCode)
  {
	  boolean contractExists = false;
	  String[] results = null;
	  if(jobContractCodes == null)
	  {
		  results = new String[1];
		  results[0] = contractCode;
		  return results;
	  }
		  
	  for(int i=0;i<jobContractCodes.length;i++)
	  {
		  if(jobContractCodes[i].trim().equals(contractCode))
		  {
			  contractExists = true;
		  	  break;
		  }
	  }
	  if(contractExists)
		  return jobContractCodes;
	  else
	  {
		  results = new String[jobContractCodes.length + 1];
		  for(int i=0;i<jobContractCodes.length;i++)
		  {
			  results[i] = jobContractCodes[i];
		  }
		  results[results.length - 1] = contractCode;

		    return results;
	  }
		  
  }
  String[] deleteJobContractCode(String[] jobContractCodes,String contractCode)
  {
	  boolean contractExists = false;
	  String[] results = null;
	  if(jobContractCodes == null) return jobContractCodes;
	  for(int i=0;i<jobContractCodes.length;i++)
	  {
		  if(jobContractCodes[i].trim().equals(contractCode))
		  {
			  contractExists = true;
		  	  break;
		  }
	  }
	  if(!contractExists)
		  return jobContractCodes;
	  else
	  {
		  results = new String[jobContractCodes.length - 1];
		  int count = 0;
		  for(int i=0;i<jobContractCodes.length;i++)
		  {
			  if(jobContractCodes[i].trim().equals(contractCode)) continue;
    	      results[count] = jobContractCodes[i];

    	      count ++;
		  }
		  

		    return results;
	  }
		  
  }  
}
