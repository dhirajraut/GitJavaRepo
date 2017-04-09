package com.intertek.web.controller.job;

import java.text.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.validation.BindException;
import org.springframework.validation.Errors;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractWizardFormController;
import org.springframework.web.servlet.mvc.*;
import org.springframework.web.servlet.view.RedirectView;

import org.acegisecurity.Authentication;
import org.acegisecurity.context.SecurityContextHolder;

import com.intertek.entity.*;
import com.intertek.service.*;
import com.intertek.util.*;
import com.intertek.locator.*;
import com.intertek.domain.*;

public class CreateJobFormController extends SimpleFormController
{
  public CreateJobFormController() {
    super();
    System.out.println("inside constructor");
    setCommandClass(AddJobOrder.class);

   //setPages(new String[] { "create-job-entry", "create-job-operational-info-insp" });

  }

  protected ModelAndView onSubmit(
        HttpServletRequest request,
        HttpServletResponse response,
        Object command,
        BindException errors
      ) throws Exception
      {
        AddJobOrder addJobOrder = (AddJobOrder)command;
        System.out.println("inside onSubmit");
        String saveFlag = addJobOrder.getSaveHeaderFlag();
        if(saveFlag != null && saveFlag.trim().equals("1"))
        {
          addJobOrder.getJobOrder().setJobNumber("12313223");
          return new ModelAndView(new RedirectView("create_job_operational_info_insp.htm"), "jobNumber", "1");
          //return new ModelAndView("create-job-operational-info-insp", "jobNumber", "US100-41");
        }
        else
          return new ModelAndView("create_job.htm", "command", addJobOrder);
      }

  protected ModelAndView processFinish(
    HttpServletRequest request,
    HttpServletResponse response,
    Object command,
    BindException errors
  ) throws Exception
  {
    System.out.println("=================== in process Finish");
    String refreshing = request.getParameter("refreshing");
    if((refreshing != null) && "true".equals(refreshing))
    {
      return showForm(request, response, errors);
    }

    AddJobOrder addJobOrder = (AddJobOrder)command;
    JobOrder jobOrder = addJobOrder.getJobOrder();

    String addOrDeleteVessel = addJobOrder.getAddOrDeleteVessel();
    String addOrDeleteProduct = addJobOrder.getAddOrDeleteProduct();
    String addOrDeleteEvent = addJobOrder.getAddOrDeleteEvent();
    String addOrDeleteSampleLoc = addJobOrder.getAddOrDeleteSampleLoc();
    String addOrDeleteQty = addJobOrder.getAddOrDeleteQty();
    int vesselIndex = addJobOrder.getVesselIndex();
    int productIndex = addJobOrder.getProductIndex();
    int eventIndex = addJobOrder.getEventIndex();
    int sampleLocIndex = addJobOrder.getSampleLocIndex();
    int qtyIndex = addJobOrder.getQtyIndex();

    System.out.println("addOrDeleteVessel :"+addOrDeleteVessel);
    System.out.println("addOrDeleteProduct :"+addOrDeleteProduct);
    System.out.println("addOrDeleteEvent :"+addOrDeleteEvent);
    System.out.println("addOrDeleteSampleLoc :"+addOrDeleteSampleLoc);
    System.out.println("addOrDeleteQty :"+addOrDeleteQty);

    System.out.println("vesselIndex :"+vesselIndex);
    System.out.println("productIndex :"+productIndex);
    System.out.println("eventIndex :"+eventIndex);
    System.out.println("sampleLocIndex :"+sampleLocIndex);
    System.out.println("qtyIndex :"+qtyIndex);

   if((addOrDeleteVessel != null) && ("add".equals(addOrDeleteVessel) || "delete".equals(addOrDeleteVessel)))
     {
      if("add".equals(addOrDeleteVessel))
      {
        System.out.println("in add or delete vessel ");
        addJobOrder.setAddJobVessels(addJobVessel(addJobOrder.getAddJobVessels()));
        System.out.println("size of vessel :"+addJobOrder.getAddJobVessels().length);
      }
      else
      {
        addJobOrder.setAddJobVessels(deleteJobVessel(addJobOrder.getAddJobVessels(), addJobOrder.getVesselIndex()));
      }
      addJobOrder.setVesselCount(addJobOrder.getAddJobVessels().length);

      addJobOrder.setAddOrDeleteVessel("none");
      System.out.println("command object :"+request.getAttribute("command"));
      return new ModelAndView("create-job-operational-info-insp", "command", addJobOrder);

      //return showPage(request,  errors, 1);
     }

   if((addOrDeleteProduct != null) && ("add".equals(addOrDeleteProduct) || "delete".equals(addOrDeleteProduct)))
     {
     AddJobVessel addJobVessel = addJobOrder.getAddJobVessels()[vesselIndex];
      if("add".equals(addOrDeleteProduct))
      {
        System.out.println("in add or delete product ");

        addJobVessel.setAddJobProds(addJobProducts(addJobVessel.getAddJobProds()));
      }
      else
      {
        addJobVessel.setAddJobProds(deleteJobProducts(addJobVessel.getAddJobProds(), productIndex));
      }
      addJobOrder.getAddJobVessels()[vesselIndex] = addJobVessel;
      addJobOrder.setAddOrDeleteProduct("none");
      return new ModelAndView("create-job-operational-info-insp", "command", addJobOrder);
      //return showPage(request,  errors, 1);
     }

   if((addOrDeleteEvent != null) && ("add".equals(addOrDeleteEvent) || "delete".equals(addOrDeleteEvent)))
     {
     AddJobVessel addJobVessel = addJobOrder.getAddJobVessels()[vesselIndex];
     AddJobProd addJobProd = addJobVessel.getAddJobProds()[productIndex];

      if("add".equals(addOrDeleteEvent))
      {
        System.out.println("in add or delete event ");
        addJobProd.setJobEvents(addEvents(addJobProd.getJobEvents()));
      }
      else
      {
        addJobProd.setJobEvents(deleteEvents(addJobProd.getJobEvents(),eventIndex));
      }
      addJobVessel.getAddJobProds()[productIndex] = addJobProd;
      addJobOrder.getAddJobVessels()[vesselIndex] = addJobVessel;
      addJobOrder.setAddOrDeleteEvent("none");
      //return showPage(request,  errors, 1);
     }
   if((addOrDeleteSampleLoc != null) && ("add".equals(addOrDeleteSampleLoc) || "delete".equals(addOrDeleteSampleLoc)))
     {
     AddJobVessel addJobVessel = addJobOrder.getAddJobVessels()[vesselIndex];
     AddJobProd addJobProd = addJobVessel.getAddJobProds()[productIndex];

      if("add".equals(addOrDeleteSampleLoc))
      {
        System.out.println("in add or delete sample loc ");
        //addJobProd.setJobProdSamples(addProdSamples(addJobProd.getAddJobProdSamples()));

      }
      else
      {
        //addJobProd.setJobProdSamples(deleteProdSamples(addJobProd.getJobProdSamples(),sampleLocIndex));
      }
      addJobVessel.getAddJobProds()[productIndex] = addJobProd;
      addJobOrder.getAddJobVessels()[vesselIndex] = addJobVessel;
      addJobOrder.setAddOrDeleteSampleLoc("none");
      //return showPage(request,  errors, 1);
     }
   if((addOrDeleteQty != null) && ("add".equals(addOrDeleteQty) || "delete".equals(addOrDeleteQty)))
     {
     AddJobVessel addJobVessel = addJobOrder.getAddJobVessels()[vesselIndex];
     AddJobProd addJobProd = addJobVessel.getAddJobProds()[productIndex];

      if("add".equals(addOrDeleteQty))
      {
        System.out.println("in add or delete product qty ");
        addJobProd.setJobProdQtys(addProdQtys(addJobProd.getJobProdQtys()));
      }
      else
      {
        addJobProd.setJobProdQtys(deleteProdQtys(addJobProd.getJobProdQtys(),qtyIndex));
      }
      addJobVessel.getAddJobProds()[productIndex] = addJobProd;
      addJobOrder.getAddJobVessels()[vesselIndex] = addJobVessel;
      addJobOrder.setAddOrDeleteQty("none");
      //return showPage(request,  errors, 1);
     }


    JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
    try
    {
      jobOrder.setBranchName(jobOrder.getBranch().getName());
      jobOrder.setBuName(jobOrder.getBranch().getBusinessUnit().getName());

      jobOrder = jobService.addJobOrder(jobOrder);
    }
    catch(Exception e)
    {
      e.printStackTrace();

      errors.reject("create.job.order.error", new Object[] {e.getMessage()}, null);
      return showForm(request, response, errors);
    }

    return new ModelAndView(new RedirectView("edit_job.htm"), "jobNumber", jobOrder.getJobNumber());
  }

  protected ModelAndView processCancel(
    HttpServletRequest request,
    HttpServletResponse response,
    Object command,
    BindException errors
  ) throws Exception
  {
    System.out.println("=================== in process Cancel");
    //super.processCancel(request, response, command, errors);
    return new ModelAndView(new RedirectView("search_job.htm"));
  }

  protected boolean suppressValidation(HttpServletRequest request)
  {
    System.out.println("inside suppressValidation");
    String refreshing = request.getParameter("refreshing");
    if((refreshing != null) && "true".equals(refreshing))
    {
      return true;
    }

    return super.suppressValidation(request);
  }

  protected Map referenceData(HttpServletRequest request,Object command, Errors errors,int page)
 throws Exception
  {
    System.out.println("inside referenceData");
    Map data = new HashMap();
    AddJobOrder addJobOrder = (AddJobOrder)command;
    //data.put("addJobOrder",errors.getModel());
    if(addJobOrder.getAddJobVessels() != null && addJobOrder.getAddJobVessels().length > 0)
    System.out.println("inside referenceData :"+ addJobOrder.getAddJobVessels()[0].getJobVessel().getJobVesselId().getLinkedVslRow());
    data.put("tabName", "CreateJob");
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
    System.out.println("inside formBacking Object");
    AddJobOrder addJobOrder = new AddJobOrder();
    JobOrder jobOrder = new JobOrder();
    jobOrder.setJobType("INSP");
    Branch branch = new Branch();
    jobOrder.setBranch(branch);
    BusinessUnit bu = new BusinessUnit();
    branch.setBusinessUnit(bu);
    ShippingAgent shippingAgent = new ShippingAgent();
    jobOrder.setShippingAgent(shippingAgent);

    if(!"POST".equals(request.getMethod()))
    {
      //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");

      //User user = userService.getUserByNameWithOrgHierarchy(SecurityUtil.getUser().getLoginName());
      String buName = userService.getFirstBusinessUnitNameByDivisionName(
        SecurityUtil.getUser().getBranch().getBusinessUnit().getOrganization().getName()
      );

      bu.setName(buName);

      String jobType = request.getParameter("jobType");
      if((jobType != null) && !"".equals(jobType.trim()))
      {
        jobOrder.setJobType(jobType);
      }
    }
    addJobOrder.setJobOrder(jobOrder);

    //Dummy contract codes for operational info testing; to be removed later
    AddJobContract[] addJobContracts = new AddJobContract[2];
    JobContract jobContract1 = new JobContract();
    JobContract jobContract2 = new JobContract();
    jobContract1.setContractCode("ABC123");
    jobContract2.setContractCode("DEF456");
    AddJobContract addJobContract1 = new AddJobContract();
    AddJobContract addJobContract2 = new AddJobContract();
    addJobContract1.setJobContract(jobContract1);
    addJobContract2.setJobContract(jobContract2);
    addJobContracts[0]=addJobContract1;
    addJobContracts[1]=addJobContract2;
    addJobOrder.setAddJobContracts(addJobContracts);
    return addJobOrder;
  }

  private AddJobVessel[] addJobVessel(AddJobVessel[] addJobVessels)
  {
    AddJobVessel addJobVessel = new AddJobVessel();

    JobVessel jobVessel = new JobVessel();
    JobVesselId jobVesselId = new JobVesselId();
    jobVessel.setJobVesselId(jobVesselId);



    addJobVessel.setJobVessel(jobVessel);



    AddJobVessel[] results = null;
    if(addJobVessels == null) results = new AddJobVessel[1];
    else results = new AddJobVessel[addJobVessels.length + 1];
    if(addJobVessels != null)
    {
          System.out.println("inside if ");

      for(int i=0; i<addJobVessels.length; i++)
      {
                  System.out.println("inside for ");

        results[i] = addJobVessels[i];
                  System.out.println("after assigning results object");

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

  private AddJobProd[] addJobProducts(AddJobProd[] addJobProds)
  {
      AddJobProd addJobProd = new AddJobProd();
      JobProd jobProd = new JobProd();
      jobProd.setJobProdId(new JobProdId());
      addJobProd.setJobProd(jobProd);

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

  private JobEvent[] addEvents(JobEvent[] jobEventEvents)
  {
    JobEvent jobEventEvent = new JobEvent();
    JobEventId jobEventEventId = new JobEventId();
    //InspectionEventTbl inspectionEventTbl = new InspectionEventTbl();
    //jobEventEvent.setInspectionEventTbl(inspectionEventTbl);
    jobEventEvent.setJobEventId(jobEventEventId);


    JobEvent[] results = null;
    if(jobEventEvents == null) results = new JobEvent[1];
    else results = new JobEvent[jobEventEvents.length + 1];

    if(jobEventEvents != null)
    {
      for(int i=0; i<jobEventEvents.length; i++)
      {
        results[i] = jobEventEvents[i];
      }
    }
    results[results.length - 1] = jobEventEvent;

    return results;
  }

  private JobEvent[] deleteEvents(JobEvent[] jobEventEvents, int index)
  {
    if(jobEventEvents == null) return null;
    if(jobEventEvents.length <= 0) return jobEventEvents;

    JobEvent[] results = new JobEvent[jobEventEvents.length - 1];

    int count = 0;
    for(int i=0; i<jobEventEvents.length; i++)
    {
      if(i == index) continue;
      results[count] = jobEventEvents[i];

      count ++;
    }

    return results;
  }

  private JobProdSample[] addProdSamples(JobProdSample[] jobProdSamples)
  {
    JobProdSample jobProdSample = new JobProdSample();
    JobProdSampleId jobProdSampleId = new JobProdSampleId();
    jobProdSample.setJobProdSampleId(jobProdSampleId);


    JobProdSample[] results = null;
    if(jobProdSamples == null) results = new JobProdSample[1];
    else results = new JobProdSample[jobProdSamples.length + 1];

    if(jobProdSamples != null)
    {
      for(int i=0; i<jobProdSamples.length; i++)
      {
        results[i] = jobProdSamples[i];
      }
    }
    results[results.length - 1] = jobProdSample;

    return results;
  }

  private JobProdSample[] deleteProdSamples(JobProdSample[] jobProdSamples, int index)
  {
    if(jobProdSamples == null) return null;
    if(jobProdSamples.length <= 0) return jobProdSamples;

    JobProdSample[] results = new JobProdSample[jobProdSamples.length - 1];

    int count = 0;
    for(int i=0; i<jobProdSamples.length; i++)
    {
      if(i == index) continue;
      results[count] = jobProdSamples[i];

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

}
