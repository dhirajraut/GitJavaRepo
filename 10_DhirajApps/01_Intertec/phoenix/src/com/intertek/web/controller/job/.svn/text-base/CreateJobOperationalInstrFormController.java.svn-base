package com.intertek.web.controller.job;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.StringTokenizer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.intertek.domain.AddJobContract;
import com.intertek.domain.AddJobOrder;
import com.intertek.domain.AddJobProd;
import com.intertek.domain.AddJobProdSample;
import com.intertek.domain.AddJobVessel;
import com.intertek.domain.SelectedTest;
import com.intertek.entity.Branch;
import com.intertek.entity.BranchCode;
import com.intertek.entity.BranchInt;
import com.intertek.entity.Country;
import com.intertek.entity.DropDowns;
import com.intertek.entity.Event;
import com.intertek.entity.JobContract;
import com.intertek.entity.JobEvent;
import com.intertek.entity.JobEventId;
import com.intertek.entity.JobManualTest;
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
import com.intertek.entity.RevisionNoteId;
import com.intertek.entity.RevisionNotes;
import com.intertek.entity.ServiceLocation;
import com.intertek.entity.Slate;
import com.intertek.entity.State;
import com.intertek.entity.Test;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.CountryService;
import com.intertek.service.DropdownService;
import com.intertek.service.JobService;
import com.intertek.service.ServiceLocationService;
import com.intertek.service.ServiceLocationServiceImpl;
import com.intertek.service.UserService;
import com.intertek.util.Constants;
import com.intertek.util.JobUtil;
import com.intertek.util.NumberUtil;
import com.intertek.util.SecurityUtil;
import com.intertek.util.I18nUtil;
import com.intertek.util.CommonUtil;
import com.intertek.web.controller.BaseSimpleFormController;
import com.intertek.web.propertyeditors.CustomPrimitiveNumberEditor;
import com.intertek.service.TestService;
public class CreateJobOperationalInstrFormController extends BaseSimpleFormController
{
  private static Log log = LogFactory.getLog(CreateJobOperationalInstrFormController.class);

  public CreateJobOperationalInstrFormController() {
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
      JobOrder jobOrder = addJobOrder.getJobOrder();

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
      String otApproveCheckFlag = addJobOrder.getOtApproveCheckFlag();

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
      String qty=addJobOrder.getManualTestQty();
      String price=addJobOrder.getManualTestPrice();
      String chosenManualTestIds = addJobOrder.getChosenManualTestIds();
      String testDesc = addJobOrder.getTestDesc();//for the issue 101771 
      String chosenSlateIds = addJobOrder.getChosenSlateIds();
      String instructionFlag = addJobOrder.getInstructionFlag();
      String massQtyFlag = addJobOrder.getMassQtyFlag();
      String massOTFlag = addJobOrder.getMassOTFlag();
      String massTestSlateDeleteFlag = addJobOrder.getMassTestSlateDeleteFlag();

      String warnUserFlag = addJobOrder.getWarnUserFlag();
      String templateFlag = addJobOrder.getTemplateFlag(); // Template
      String tmpName = addJobOrder.getTempName(); // Template
      String tmpSearchFlag = addJobOrder.getTmpSearchFlag(); // Template
      String jobNumber = addJobOrder.getJobNumber(); // Template
      String tmpNumber = ""; // Template

      if(otApprovedFlag != null && otApprovedFlag.trim().equals("N"))
        addJobOrder.getJobOrder().setOtApproved(false);

      String scrollflag=request.getParameter("scrollFlag");
      if(scrollflag!=null  && !scrollflag.trim().equals(""))
      {addJobOrder.setScrollFlag(scrollflag);}

      //Start - Template
      if ((tmpSearchFlag != null) && "tmpSearchFlag".equalsIgnoreCase(tmpSearchFlag)){
      addJobOrder.setTmpSearchFlag("none");
      return new ModelAndView(new RedirectView("create_job_operational_info_insp.htm?jobNumber="+jobNumber+"&templateName="+addJobOrder.getTemplateNum()+"&searchTemplate="+addJobOrder.getSearchTemplate()));
      }
      //End - Template


      if((warnUserFlag != null) && "warn".equals(warnUserFlag))
      {
        addJobOrder.setWarnUserFlag("warned");
        errors.reject("unsaved.changes.save.error", new Object[] {}, null);
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



      JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
      DropdownService dropdownService = (DropdownService)ServiceLocator.getInstance().getBean("dropdownService");

      //Validate quantity fields
      if(addJobOrder.getAddJobVessels() != null && addJobOrder.getAddJobVessels().length > 0)
      {
           for(int i=0;i<addJobOrder.getAddJobVessels().length;i++)
      {
          AddJobVessel addJobVessel = addJobOrder.getAddJobVessels()[i];
          if(addJobVessel.getAddJobProds() != null && addJobVessel.getAddJobProds().length > 0)
          {
            for(int j=0;j<addJobVessel.getAddJobProds().length;j++)
            {
              AddJobProd addJobProd = addJobVessel.getAddJobProds()[j];
              JobProd jobProd = addJobProd.getJobProd();
              if(!(new Double(jobProd.getProductQty()).toString()).trim().equals(""))
              try{
                double prodQty = Double.parseDouble(new Double(jobProd.getProductQty()).toString());
              }
              catch(Exception e)
              {
                errors.reject("ordered.quantity.number.error", new Object[] {}, null);
                  return showForm(request, response, errors);
              }

              if(addJobProd.getJobProdQtys() != null && addJobProd.getJobProdQtys().length >0)
              {
                for(int k=0;k<addJobProd.getJobProdQtys().length;k++)
                {
                  JobProdQty jobProdQty = addJobProd.getJobProdQtys()[k];
                  if(!(new Double(jobProdQty.getProductQty()).toString()).trim().equals(""))
                      try{
                        double jobprodQty = Double.parseDouble(new Double(jobProdQty.getProductQty()).toString());
                      }
                      catch(Exception e)
                      {
                        errors.reject("ordered.quantity.number.error", new Object[] {}, null);
                          return showForm(request, response, errors);
                      }
                }
              }
            }
          }
      }
      }

     if((addOrDeleteVessel != null) && ("add".equals(addOrDeleteVessel) || "delete".equals(addOrDeleteVessel)))
       {
        if("add".equals(addOrDeleteVessel))
        {
          addJobOrder.setAddJobVessels(addJobVessel(addJobOrder.getAddJobVessels(),null,addJobOrder.getAddJobContracts()));

        }
        else
        {
          addJobOrder.setAddJobVessels(deleteJobVessel(addJobOrder.getAddJobVessels(), addJobOrder.getVesselIndex()));
        }
        addJobOrder.setVesselCount(addJobOrder.getAddJobVessels().length);

        addJobOrder.setAddOrDeleteVessel("none");

        return showForm(request, response, errors);

       }

     if((addOrDeleteProduct != null) && ("add".equals(addOrDeleteProduct) || "delete".equals(addOrDeleteProduct)))
       {
       AddJobVessel addJobVessel = addJobOrder.getAddJobVessels()[vesselIndex];
        if("add".equals(addOrDeleteProduct))
        {

          addJobVessel.setAddJobProds(addJobProducts(addJobVessel.getAddJobProds(),null,addJobOrder.getAddJobContracts()));
        }
        else
        {
          addJobVessel.setAddJobProds(deleteJobProducts(addJobVessel.getAddJobProds(), productIndex));
        }
        addJobOrder.getAddJobVessels()[vesselIndex] = addJobVessel;
        addJobOrder.setAddOrDeleteProduct("none");
        return showForm(request, response, errors);
        //return new ModelAndView("create-job-operational-info-insp", "command", addJobOrder);
        //return showPage(request,  errors, 1);
       }

     if((addOrDeleteEvent != null) && ("add".equals(addOrDeleteEvent) || "delete".equals(addOrDeleteEvent)))
       {
       AddJobVessel addJobVessel = addJobOrder.getAddJobVessels()[vesselIndex];
       AddJobProd addJobProd = addJobVessel.getAddJobProds()[productIndex];

        if("add".equals(addOrDeleteEvent))
        {
          addJobProd.setJobEvents(addInspectionEvents(addJobProd.getJobEvents()));
        }
        else
        {
          addJobProd.setJobEvents(deleteInspectionEvents(addJobProd.getJobEvents(),eventIndex));
        }
        addJobVessel.getAddJobProds()[productIndex] = addJobProd;
        addJobOrder.getAddJobVessels()[vesselIndex] = addJobVessel;
        addJobOrder.setAddOrDeleteEvent("none");
        //return showPage(request,  errors, 1);
        return showForm(request, response, errors);
       }
     if((addOrDeleteSampleLoc != null) && ("add".equals(addOrDeleteSampleLoc) || "delete".equals(addOrDeleteSampleLoc)))
       {
       AddJobVessel addJobVessel = addJobOrder.getAddJobVessels()[vesselIndex];
       AddJobProd addJobProd = addJobVessel.getAddJobProds()[productIndex];

        if("add".equals(addOrDeleteSampleLoc))
        {
    	  if(addJobOrder.getSampleLocCount()!=null && !addJobOrder.getSampleLocCount().trim().equals("")){
           int sampCount=Integer.parseInt(addJobOrder.getSampleLocCount());
           for(int i=0;i<sampCount;i++){
           addJobProd.setAddJobProdSamples(addProdSamples(addJobProd.getAddJobProdSamples(),null,addJobOrder.getJobOrder().getJobType()));
           String rtnPeriod = addJobOrder.getJobOrder().getRetainPeriod();
           if ( rtnPeriod == null || rtnPeriod.equals("")){
           addJobOrder.getJobOrder().setRetainPeriod(addJobOrder.getJobOrder().getBusinessUnit().getRetainPeriod());
           }
    	  }
    	 }
        }
        else
        {
            addJobProd.setAddJobProdSamples(deleteProdSamples(addJobProd.getAddJobProdSamples(),sampleLocIndex));

            // Once the Sample is Deleted update the Retain Period, Lab Analysis and OT Approved.
        	int testCount = 0;
        	int overTimeCount = 0;
        	int samplesCount = 0;

            if(addJobOrder.getAddJobVessels() != null)
            {
              for(int i=0;i<addJobOrder.getAddJobVessels().length;i++)
              {
                AddJobVessel addJobVessels = addJobOrder.getAddJobVessels()[i];
                if(addJobVessels.getAddJobProds() != null && addJobVessels.getAddJobProds().length > 0)
                {
                  for(int j=0;j<addJobVessels.getAddJobProds().length;j++)
                  {
                    AddJobProd addJobProds = addJobVessels.getAddJobProds()[j];
                    if(addJobProds.getAddJobProdSamples() != null && addJobProds.getAddJobProdSamples().length >0)
                    {
                    	AddJobProdSample[] addJobProdSamples = addJobProds.getAddJobProdSamples();
   			    		for(int k=0;k<addJobProdSamples.length;k++){
   			        		AddJobProdSample addJobProdSample = addJobProdSamples[k];
   			        		/*String priority = addJobProdSample.getJobProdSample().getPriority();

   			        		if(priority != null && (priority.trim().equals(Constants.OVERTIME)|| priority.trim().equals(Constants.RUSH)))
   			        			overTimeCount++;
   			        		if(addJobProdSample.getJobTests() != null && addJobProdSample.getJobTests().length >0)
   		                    {
   		                     for(int l=0;l<addJobProdSample.getJobTests().length;l++)
   		                     {
   		                       JobTest jobTest = addJobProdSample.getJobTests()[l];
	                           if(jobTest.getOt())
	                           {
	                        	   overTimeCount++;
	                           }
   		                     }
   		                   }
   			        	   if(addJobProdSample.getJobSlates() != null && addJobProdSample.getJobSlates().length >0)
   		                   {
   		                     for(int l=0;l<addJobProdSample.getJobSlates().length;l++)
   		                     {
   		                       JobSlate jobSlate = addJobProdSample.getJobSlates()[l];
	                           if(jobSlate.getOt())
	                           {
	                        	overTimeCount++;
	                           }
   		                     }
   		                 }
   			        	 if(addJobProdSample.getJobManualTests() != null && addJobProdSample.getJobManualTests().length >0)
  	                     {
  	                     for(int l=0;l<addJobProdSample.getJobManualTests().length;l++)
  	                     {
  	                       JobManualTest jobManualTest = addJobProdSample.getJobManualTests()[l];
                           if(jobManualTest.getOt())
                           {
                        	 overTimeCount++;
                           }
  	                     }
  	                     }*/
   			        		String test = addJobProdSample.getJobProdSample().getRetainTested();

   			        		if(test != null && test.trim().equals("T"))
   			        			testCount++;
   			        		samplesCount++;
   			        	}
   			         }
       	           }
                  }
                }
                if(testCount == 0){
		        	addJobOrder.getJobOrder().setLabAnalysis(false);
                } else {
                	addJobOrder.getJobOrder().setLabAnalysis(true);
                }
	        	/*if(overTimeCount == 0){
	        		addJobOrder.getJobOrder().setOtApproved(false);
	        	} else {
	        		addJobOrder.getJobOrder().setOtApproved(true);
	        	}*/
                boolean otApproveCheck = CommonUtil.checkOtApproved(addJobOrder.getAddJobVessels());
         		if(otApproveCheck){
         	    	   addJobOrder.getJobOrder().setOtApproved(true);
         		} else {
         		   		addJobOrder.getJobOrder().setOtApproved(false);
         		}
	        	if(samplesCount == 0){
	        		addJobOrder.getJobOrder().setRetainPeriod("");
	            	addJobOrder.getJobOrder().setLabAnalysis(false);
	            	addJobOrder.getJobOrder().setOtApproved(false);
	        	}
             }
        //End
        }
        addJobVessel.getAddJobProds()[productIndex] = addJobProd;
        addJobOrder.getAddJobVessels()[vesselIndex] = addJobVessel;
        addJobOrder.setAddOrDeleteSampleLoc("none");
        //return showPage(request,  errors, 1);
        return showForm(request, response, errors);
       }
     // issue 71778
    /* if((otApproveCheckFlag != null) && ("Y".equals(otApproveCheckFlag) ))
     {
     	 int otApprovedCheckCount = 0;
    	 if(addJobOrder.getAddJobVessels() != null)
         {
           for(int i=0;i<addJobOrder.getAddJobVessels().length;i++)
           {
             AddJobVessel addJobVessels = addJobOrder.getAddJobVessels()[i];
             if(addJobVessels.getAddJobProds() != null && addJobVessels.getAddJobProds().length > 0)
             {
               for(int j=0;j<addJobVessels.getAddJobProds().length;j++)
               {
                 AddJobProd addJobProds = addJobVessels.getAddJobProds()[j];
                 if(addJobProds.getAddJobProdSamples() != null && addJobProds.getAddJobProdSamples().length >0)
                 {
                 	AddJobProdSample[] addJobProdSamples = addJobProds.getAddJobProdSamples();
			    		for(int k=0;k<addJobProdSamples.length;k++){
			        		AddJobProdSample addJobProdSample = addJobProdSamples[k];
			        		String priority = addJobProdSample.getJobProdSample().getPriority();
			        		if(priority != null && (priority.trim().equals(Constants.OVERTIME)|| priority.trim().equals(Constants.RUSH)))
			        			otApprovedCheckCount++;
			        		if(addJobProdSample.getJobTests() != null && addJobProdSample.getJobTests().length >0)
   		                    {
   		                     for(int l=0;l<addJobProdSample.getJobTests().length;l++)
   		                     {
   		                       JobTest jobTest = addJobProdSample.getJobTests()[l];

	                           if(jobTest.getOt())
	                           {
	                        	   otApprovedCheckCount++;
	                           }
   		                     }
   		                   }
   			        	   if(addJobProdSample.getJobSlates() != null && addJobProdSample.getJobSlates().length >0)
   		                   {
   		                     for(int l=0;l<addJobProdSample.getJobSlates().length;l++)
   		                     {
   		                       JobSlate jobSlate = addJobProdSample.getJobSlates()[l];
	                           if(jobSlate.getOt())
	                           {
	                        	otApprovedCheckCount++;
	                           }
   		                     }
   		                 }
   			        	 if(addJobProdSample.getJobManualTests() != null && addJobProdSample.getJobManualTests().length >0)
  	                     {
  	                     for(int l=0;l<addJobProdSample.getJobManualTests().length;l++)
  	                     {
  	                       JobManualTest jobManualTest = addJobProdSample.getJobManualTests()[l];
                           if(jobManualTest.getOt())
                           {
                        	 otApprovedCheckCount++;
                           }
  	                     }
  	                     }
			        	}
			         }
    	          }
               }
            }
        	if(otApprovedCheckCount == 0){
        		addJobOrder.getJobOrder().setOtApproved(false);
        	} else {
        		 addJobOrder.getJobOrder().setOtApproved(true);
        	}
         }
    	 addJobOrder.setOtApproveCheckFlag("none");

         return showForm(request, response, errors);

     }*/
     //End
     //Added on 03-03-09 regarding sample priority functionality
     if((otApproveCheckFlag != null) && ("Y".equals(otApproveCheckFlag) ))
     {
    	 if(addJobOrder.getAddJobVessels() != null)
         {
           for(int i=0;i<addJobOrder.getAddJobVessels().length;i++)
           {
             AddJobVessel addJobVessels = addJobOrder.getAddJobVessels()[i];
             if(addJobVessels.getAddJobProds() != null && addJobVessels.getAddJobProds().length > 0)
             {
               for(int j=0;j<addJobVessels.getAddJobProds().length;j++)
               {
                 AddJobProd addJobProds = addJobVessels.getAddJobProds()[j];
                 if(addJobProds.getAddJobProdSamples() != null && addJobProds.getAddJobProdSamples().length >0)
                 {
                 	AddJobProdSample[] addJobProdSamples = addJobProds.getAddJobProdSamples();
			    		for(int k=0;k<addJobProdSamples.length;k++){
			        		AddJobProdSample addJobProdSample = addJobProdSamples[k];
			        		String priority = addJobProdSample.getJobProdSample().getPriority();

			        		if(addJobProdSample.getJobTests() != null && addJobProdSample.getJobTests().length >0)
   		                    {
			        	     int testOtYesValCount = 0;
   		                     for(int l=0;l<addJobProdSample.getJobTests().length;l++)
   		                     {
   		                       JobTest jobTest = addJobProdSample.getJobTests()[l];
   		                       if(jobTest.getOt())
	                           {
   		                    	   testOtYesValCount++;
	                           }
   		                     }
	   		                 if(testOtYesValCount >0){
	   		                	  if(priority != null && priority.trim().equals(Constants.STANDARD)) {

	   		                		    boolean otApproveCheck = CommonUtil.checkOtApproved(addJobOrder.getAddJobVessels());
		   		                	    if(otApproveCheck){
		   		                	    	   addJobOrder.getJobOrder().setOtApproved(true);
		   		                	    } else {
		   		                		   		addJobOrder.getJobOrder().setOtApproved(false);
		   		                	    }
			                    		errors.reject("sample.priority.change.otorrush.error");
			                    		addJobOrder.setOtApproveCheckFlag("none");
			                    		return showForm(request, response, errors);
			                    	}
	 				         } else {
	 				        	boolean otApproveCheck = CommonUtil.checkOtApproved(addJobOrder.getAddJobVessels());
   		                	    if(otApproveCheck){
   		                	    	   addJobOrder.getJobOrder().setOtApproved(true);
   		                	    } else {
   		                		   		addJobOrder.getJobOrder().setOtApproved(false);
   		                	    }
	 				         }
 		                   } else {
 		                	   boolean otApproveCheck = CommonUtil.checkOtApproved(addJobOrder.getAddJobVessels());
		                	   if(otApproveCheck){
		                	    	addJobOrder.getJobOrder().setOtApproved(true);
		                	   } else {
		                		   	addJobOrder.getJobOrder().setOtApproved(false);
		                	   }
 		                   }


   			        	   if(addJobProdSample.getJobSlates() != null && addJobProdSample.getJobSlates().length >0)
   		                   {
   			        		 int slateOtYesValCount = 0;
   		                     for(int l=0;l<addJobProdSample.getJobSlates().length;l++)
   		                     {
   		                       JobSlate jobSlate = addJobProdSample.getJobSlates()[l];
   		                       if(jobSlate.getOt())
	                           {
   		                    	slateOtYesValCount++;
	                           }
   		                     }
   		                      if(slateOtYesValCount >0){
	   		                	  if(priority != null && priority.trim().equals(Constants.STANDARD)) {
			                    		boolean otApproveCheck = CommonUtil.checkOtApproved(addJobOrder.getAddJobVessels());
			                 		    if(otApproveCheck){
			                 			   addJobOrder.getJobOrder().setOtApproved(true);
			                 		    } else {
			                 			   addJobOrder.getJobOrder().setOtApproved(false);
			                 		    }
			                 		    errors.reject("sample.priority.change.otorrush.error");
			                    		addJobOrder.setOtApproveCheckFlag("none");
			                    		return showForm(request, response, errors);
			                      }
   		                      } else {
	 				        	boolean otApproveCheck = CommonUtil.checkOtApproved(addJobOrder.getAddJobVessels());
   		                	    if(otApproveCheck){
   		                	    	   addJobOrder.getJobOrder().setOtApproved(true);
   		                	    } else {
   		                		   		addJobOrder.getJobOrder().setOtApproved(false);
   		                	    }
	 				         }
   		                 } else {
   		                	 boolean otApproveCheck = CommonUtil.checkOtApproved(addJobOrder.getAddJobVessels());
	            		     if(otApproveCheck){
	            			   addJobOrder.getJobOrder().setOtApproved(true);
	            		     } else {
	            			   addJobOrder.getJobOrder().setOtApproved(false);
	            		     }
                        }

   			        	 if(addJobProdSample.getJobManualTests() != null && addJobProdSample.getJobManualTests().length >0)
  	                     {
   			        		int manualTestOtYesValCount = 0;
  	                     for(int l=0;l<addJobProdSample.getJobManualTests().length;l++)
  	                     {
  	                       JobManualTest jobManualTest = addJobProdSample.getJobManualTests()[l];
                           if(jobManualTest.getOt())
                           {
                        	 //101771 START
                        	 manualTestOtYesValCount++;
                        	//101771 END
                           }
  	                     }
  	                     //101771 START
  	                   if(manualTestOtYesValCount >0){
		                	  if(priority != null && priority.trim().equals(Constants.STANDARD)) {
	   		                		
		                		    boolean otApproveCheck = CommonUtil.checkOtApproved(addJobOrder.getAddJobVessels());
	   		                	    if(otApproveCheck){
	   		                	    	   addJobOrder.getJobOrder().setOtApproved(true);
	   		                	    } else {
	   		                		   		addJobOrder.getJobOrder().setOtApproved(false);
	   		                	    }
		                    		errors.reject("sample.priority.change.otorrush.error");
		                    		addJobOrder.setOtApproveCheckFlag("none");
		                    		return showForm(request, response, errors);
		                    	}
				         } else {
				        	boolean otApproveCheck = CommonUtil.checkOtApproved(addJobOrder.getAddJobVessels());
	                	    if(otApproveCheck){
	                	    	   addJobOrder.getJobOrder().setOtApproved(true);
	                	    } else {
	                		   		addJobOrder.getJobOrder().setOtApproved(false);
	                	    }
				         }
	                   } else {
	                	   boolean otApproveCheck = CommonUtil.checkOtApproved(addJobOrder.getAddJobVessels());
	                	   if(otApproveCheck){
	                	    	addJobOrder.getJobOrder().setOtApproved(true);
	                	   } else {
	                		   	addJobOrder.getJobOrder().setOtApproved(false);
	                	   }
	                   }
   			          //101771 END
			         }
    	          }
               }
            }
         }
    	 addJobOrder.setOtApproveCheckFlag("none");

         return showForm(request, response, errors);
     }
     }//End
     if((addOrDeleteQty != null) && ("add".equals(addOrDeleteQty) || "delete".equals(addOrDeleteQty) || "update".equals(addOrDeleteQty)))
       {
       try {
       AddJobVessel addJobVessel = addJobOrder.getAddJobVessels()[vesselIndex];
       AddJobProd addJobProd = addJobVessel.getAddJobProds()[productIndex];

        if("add".equals(addOrDeleteQty))
        {
          if(addJobProd.getJobProd().getOption() != null && (!addJobProd.getJobProd().getOption().trim().equals("")))
          {
            DropDowns dd = dropdownService.getDropdownByDropdownCodeAndType(addJobProd.getJobProd().getOption(), Constants.OPTION);
            if(dd == null)
              System.out.println("dd is null");
            else
            {
              System.out.println("dd option value: "+dd.getFieldValue());
              addJobProd.setOptionDesc(dd.getFieldValue());
            }
          }
          addJobProd.setJobProdQtys(addProdQtys(addJobProd.getJobProdQtys(),addJobProd.getJobProd()));
        }
        else if("update".equals(addOrDeleteQty))
        {
          addJobProd.setJobProdQtys(updateProdQtys(addJobProd.getJobProdQtys(),addJobProd.getJobProd()));
        }
        else
        {
          addJobProd.setJobProdQtys(deleteProdQtys(addJobProd.getJobProdQtys(),qtyIndex,addJobProd.getJobProd()));
        }
        addJobVessel.getAddJobProds()[productIndex] = addJobProd;
        addJobOrder.getAddJobVessels()[vesselIndex] = addJobVessel;
        addJobOrder.setAddOrDeleteQty("none");
        //return showPage(request,  errors, 1);
        return showForm(request, response, errors);
        } catch(Throwable t)
        {
           addJobOrder.setAddOrDeleteQty("none");
         t.printStackTrace();
         errors.reject("generic.error", new Object[] {t.getMessage()}, null);
         return showForm(request, response, errors);
        }
       }
     if((addOrDeleteTest != null) && ("add".equals(addOrDeleteTest) || "delete".equals(addOrDeleteTest)))
       {
       AddJobVessel addJobVessel = addJobOrder.getAddJobVessels()[vesselIndex];
       AddJobProd addJobProd = addJobVessel.getAddJobProds()[productIndex];
       AddJobProdSample addJobProdSample = addJobProd.getAddJobProdSamples()[sampleLocIndex];

        if("delete".equals(addOrDeleteTest))
        {
          int[] indices = new int[1];
          indices[0] = testIndex;
          addJobProdSample.setJobTests(deleteJobTest(addJobProdSample.getJobTests(),indices));
        }
        addJobProd.getAddJobProdSamples()[sampleLocIndex] = addJobProdSample;
        addJobVessel.getAddJobProds()[productIndex] = addJobProd;
        addJobOrder.getAddJobVessels()[vesselIndex] = addJobVessel;
        addJobOrder.setAddOrDeleteTest("none");
        //return showPage(request,  errors, 1);
        return showForm(request, response, errors);
       }
     //101771 START
     if((addOrDeleteTest != null) && ("add".equals(addOrDeleteTest) || "deleteManual".equals(addOrDeleteTest)))
     {
     AddJobVessel addJobVessel = addJobOrder.getAddJobVessels()[vesselIndex];
     AddJobProd addJobProd = addJobVessel.getAddJobProds()[productIndex];
     AddJobProdSample addJobProdSample = addJobProd.getAddJobProdSamples()[sampleLocIndex];

      if("deleteManual".equals(addOrDeleteTest))
      {
        int[] indices = new int[1];
        indices[0] = testIndex;
        addJobProdSample.setJobManualTests(deleteJobManualTest(addJobProdSample.getJobManualTests(),indices));
      }
      addJobProd.getAddJobProdSamples()[sampleLocIndex] = addJobProdSample;
      addJobVessel.getAddJobProds()[productIndex] = addJobProd;
      addJobOrder.getAddJobVessels()[vesselIndex] = addJobVessel;
      addJobOrder.setAddOrDeleteTest("none");
      //return showPage(request,  errors, 1);
      return showForm(request, response, errors);
     }
     
     //101771 END
     if((addOrDeleteSlate != null) && ("add".equals(addOrDeleteSlate) || "delete".equals(addOrDeleteSlate)))
       {
       AddJobVessel addJobVessel = addJobOrder.getAddJobVessels()[vesselIndex];
       AddJobProd addJobProd = addJobVessel.getAddJobProds()[productIndex];
       AddJobProdSample addJobProdSample = addJobProd.getAddJobProdSamples()[sampleLocIndex];

        if("delete".equals(addOrDeleteSlate))
        {
          int[] indices = new int[1];
          indices[0] = slateIndex;
          addJobProdSample.setJobSlates(deleteJobSlate(addJobProdSample.getJobSlates(),indices));
        }
        addJobProd.getAddJobProdSamples()[sampleLocIndex] = addJobProdSample;
        addJobVessel.getAddJobProds()[productIndex] = addJobProd;
        addJobOrder.getAddJobVessels()[vesselIndex] = addJobVessel;
        addJobOrder.setAddOrDeleteSlate("none");
        //return showPage(request,  errors, 1);
        return showForm(request, response, errors);
       }


     //For mass test/slate delete
     if((massTestSlateDeleteFlag != null) && ("massDelete".equals(massTestSlateDeleteFlag)))
     {
       AddJobVessel addJobVessel = addJobOrder.getAddJobVessels()[vesselIndex];
       AddJobProd addJobProd = addJobVessel.getAddJobProds()[productIndex];
       AddJobProdSample addJobProdSample = addJobProd.getAddJobProdSamples()[sampleLocIndex];
       if(chosenTestIds != null && (!chosenTestIds.trim().equals("")))
       {
         String[] testIds = chosenTestIds.split("\\,");
         if(testIds != null && testIds.length > 0)
         {
           JobTest[] jobTests = addJobProdSample.getJobTests();
           int[] indices = new int[testIds.length] ;
           for(int i=0;i<testIds.length;i++)
           {
             indices[i] = Integer.parseInt(testIds[i]);
             System.out.println("indice:"+indices[i]);
           }
           jobTests = deleteJobTest(jobTests,indices);
           addJobProdSample.setJobTests(jobTests);
         }

       }
       
       if(chosenManualTestIds != null && (!chosenManualTestIds.trim().equals("")))
       {
         String[] testIds = chosenManualTestIds.split("\\,");
         if(testIds != null && testIds.length > 0)
         {
          
           int[] indices = new int[testIds.length] ;
           for(int i=0;i<testIds.length;i++)
           {
             indices[i] = Integer.parseInt(testIds[i]);
           }
           JobManualTest[] jobManualTest = addJobProdSample.getJobManualTests();
           jobManualTest = deleteJobManualTest(jobManualTest,indices);
           addJobProdSample.setJobManualTests(deleteJobManualTest(addJobProdSample.getJobManualTests(),indices));

         }

       }
       
         if(chosenSlateIds != null && (!chosenSlateIds.trim().equals("")))
       {
         String[] slateIds = chosenSlateIds.split("\\,");
         if(slateIds != null && slateIds.length > 0)
         {
           JobSlate[] jobSlates = addJobProdSample.getJobSlates();
           int[] indices = new int[slateIds.length] ;
           for(int i=0;i<slateIds.length;i++)
           {
             indices[i] = Integer.parseInt(slateIds[i]);

           }
           jobSlates = deleteJobSlate(jobSlates,indices);
           addJobProdSample.setJobSlates(jobSlates);
         }
       }


      addJobProd.getAddJobProdSamples()[sampleLocIndex] = addJobProdSample;
      addJobVessel.getAddJobProds()[productIndex] = addJobProd;
      addJobOrder.getAddJobVessels()[vesselIndex] = addJobVessel;
      addJobOrder.setMassTestSlateDeleteFlag("none");
      addJobOrder.setChosenSlateIds("");
      addJobOrder.setChosenTestIds("");
      addJobOrder.setChosenManualTestIds("");
      //return showPage(request,  errors, 1);
	   boolean otApproveCheck = CommonUtil.checkOtApproved(addJobOrder.getAddJobVessels());
	   if(otApproveCheck){
		    addJobOrder.getJobOrder().setOtApproved(true);
	   } else {
		  	addJobOrder.getJobOrder().setOtApproved(false);
	   }
      return showForm(request, response, errors);
     }

     if((copyFlag != null) && ("copyVessel".equals(copyFlag) || "copyProduct".equals(copyFlag) || "copySample".equals(copyFlag)))
       {
       if("copyVessel".equals(copyFlag))
       {
         //AddJobVessel addJobVessel = new AddJobVessel();
         //addJobVessel = addJobOrder.getAddJobVessels()[vesselIndex];
        /* for(int i=0;i<addJobVessel.getAddJobProds().length;i++)
         {
           AddJobProd addJobProd = new AddJobProd();
           addJobProd = addJobVessel.getAddJobProds()[i];
           addJobProd.getJobProd().setProductQty(0);
           addJobProd.getJobProd().setUom("");
           addJobProd.getJobProd().setOption("");
           addJobProd.getJobProd().setPlusMinus("");
           addJobProd.getJobProd().setPlusMinusPct(0);
           addJobProd.getJobProd().setDrafts("");
           addJobProd.getJobProd().setTanks("");
           addJobProd.setJobProdQtys(null);
           addJobVessel.getAddJobProds()[i] = addJobProd;
         }*/
         addJobOrder.setAddJobVessels(addJobVessel(addJobOrder.getAddJobVessels(),addJobOrder.getAddJobVessels()[vesselIndex],addJobOrder.getAddJobContracts()));
       }
       else if("copyProduct".equals(copyFlag))
       {
         AddJobVessel targetAddJobVessel  = addJobOrder.getAddJobVessels()[targetVesselIndex];
         AddJobVessel sourceAddJobVessel  = addJobOrder.getAddJobVessels()[vesselIndex];
         AddJobProd sourceAddJobProd = sourceAddJobVessel.getAddJobProds()[productIndex];

         targetAddJobVessel.setAddJobProds(addJobProducts(targetAddJobVessel.getAddJobProds(),sourceAddJobProd,addJobOrder.getAddJobContracts()));
         addJobOrder.getAddJobVessels()[targetVesselIndex] = targetAddJobVessel;
         addJobOrder.setScrollFlag("copyProduct"+targetVesselIndex+targetAddJobVessel.getAddJobProds().length);

       }
       else //copy sample location
       {
         AddJobVessel addJobVessel  = addJobOrder.getAddJobVessels()[vesselIndex];
         AddJobProd addJobProd = addJobVessel.getAddJobProds()[productIndex];
         AddJobProdSample sourceAddJobProdSample = addJobProd.getAddJobProdSamples()[sampleLocIndex];

         addJobProd.setAddJobProdSamples(addProdSamples(addJobProd.getAddJobProdSamples(),sourceAddJobProdSample,addJobOrder.getJobOrder().getJobType()));
         addJobVessel.getAddJobProds()[productIndex] = addJobProd;
         addJobOrder.getAddJobVessels()[vesselIndex] = addJobVessel;

       }
       addJobOrder.setCopyFlag("none");
       return showForm(request, response, errors);
       }
     if((chosenTestIds != null) && (!chosenTestIds.trim().equals("")))
     {
	       try {
	       AddJobVessel addJobVessel  = addJobOrder.getAddJobVessels()[vesselIndex];
	       AddJobProd addJobProd = addJobVessel.getAddJobProds()[productIndex];
	       AddJobProdSample addJobProdSample = addJobProd.getAddJobProdSamples()[sampleLocIndex];
	
	       if(chosenTestIds.indexOf(";") > -1) //More than 1 TestIds to be added
	       {
	        StringTokenizer st = new StringTokenizer(chosenTestIds,";");
	          while(st.hasMoreTokens())
	          {
	           	  String nextToken = st.nextToken();
	           	  Test test=null;
	           	  test  = jobService.getTestByTestId(nextToken);
	//           	  if(null == qty || null == price || "".equals(qty) || "".equals(price))
	           	if(null != test)
		              	addJobProdSample.setJobTests(addJobTest(addJobProdSample.getJobTests(),test,null,addJobProdSample.getJobProdSample()));
	          }
	       }
	       else{
	    	   Test test=null;
	    		   test = jobService.getTestByTestId(chosenTestIds);
	    		   if(null != test)
	    	         	addJobProdSample.setJobTests(addJobTest(addJobProdSample.getJobTests(),test,null,addJobProdSample.getJobProdSample()));
	
	       }
       
	       addJobOrder.setChosenTestIds("");
	       addJobOrder.setChosenContracts("");
	       //addJobOrder.getJobOrder().setOtApproved(true);
	       //Setting OtApproved
	       if(addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.OVERTIME)|| addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.RUSH))
	    	   addJobOrder.getJobOrder().setOtApproved(true);
	      //End
	       return showForm(request, response, errors);
	       } catch(Throwable t)
	       {
	         addJobOrder.setChosenTestIds("");
	           addJobOrder.setChosenContracts("");
	         t.printStackTrace();
	         errors.reject("generic.error", new Object[] {t.getMessage()}, null);
	         return showForm(request, response, errors);
	       }
	     }
     if(chosenManualTestIds != null && (!chosenManualTestIds.trim().equals("")))
     {
	       try {
	       AddJobVessel addJobVessel  = addJobOrder.getAddJobVessels()[vesselIndex];
	       AddJobProd addJobProd = addJobVessel.getAddJobProds()[productIndex];
	       AddJobProdSample addJobProdSample = addJobProd.getAddJobProdSamples()[sampleLocIndex];
	
	       if(chosenManualTestIds.indexOf(";") > -1) //More than 1 TestIds to be added
	       {
	        StringTokenizer st = new StringTokenizer(chosenManualTestIds,";");
	        int p=0;
	          while(st.hasMoreTokens())
	          {
	           	  String nextToken = st.nextToken();
	               JobManualTest jobManualTest = null;
	               //101771 START
	               String[] qtys=null;
	               String[] prices = null;
	               String[] descs=null;
	               	if(qty.indexOf(";")>-1){
	               		qtys = qty.split(";");
	               	}
	               	if(price.indexOf(";")>-1){
	               		prices = price.split(";");
	               	}
	               	if(testDesc.indexOf(";")>-1){
	               		descs = testDesc.split(";");
	               	}
	               	jobManualTest = new JobManualTest();
	            	jobManualTest.setTestId(nextToken);
	            	if(descs[p]=="@")
	            		descs[p]="";
	            	jobManualTest.setTestDescription(descs[p]);
	            	jobManualTest.setQuantity(Double.valueOf(qtys[p]));
	            	jobManualTest.setTotalPrice(Double.valueOf(prices[p]));
	            	
	               	if(null != jobManualTest)
	         			  addJobProdSample.setJobManualTests(addJobManualTest(addJobProdSample.getJobManualTests(),jobManualTest.getTestId(),jobManualTest.getTestDescription(),jobManualTest.getQuantity(),jobManualTest.getTotalPrice(),null,addJobProdSample.getJobProdSample()));
	              
	                	              //	            101771 END
	               p++;
	             }
	       }
	       else //single Test id to be added
	       {
	    	   
	          //101771 START
	          JobManualTest jobManualTest = null;
	        	  jobManualTest = new JobManualTest();
	        	  jobManualTest.setTestId(chosenManualTestIds);
	        	  if(null != testDesc)
	        		  jobManualTest.setTestDescription(testDesc);
	        	  if(null != qty)
	        		  jobManualTest.setQuantity(Double.valueOf(qty));
	        	  if(null != price)
	        		  jobManualTest.setTotalPrice(Double.valueOf(price));
	          
	              if(null != jobManualTest)
	         	addJobProdSample.setJobManualTests(addJobManualTest(addJobProdSample.getJobManualTests(),jobManualTest.getTestId(),jobManualTest.getTestDescription(),jobManualTest.getQuantity(),jobManualTest.getTotalPrice(),null,addJobProdSample.getJobProdSample()));
	       }
	          //        101771 END       
	       addJobOrder.setChosenManualTestIds("");
	       addJobOrder.setChosenContracts("");
	       //addJobOrder.getJobOrder().setOtApproved(true);
	       //Setting OtApproved
	       if(addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.OVERTIME)|| addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.RUSH))
	    	   addJobOrder.getJobOrder().setOtApproved(true);
	      //End
	       return showForm(request, response, errors);
	       } catch(Throwable t)
	       {
	         addJobOrder.setChosenManualTestIds("");
	           addJobOrder.setChosenContracts("");
	         t.printStackTrace();
	         errors.reject("generic.error", new Object[] {t.getMessage()}, null);
	         return showForm(request, response, errors);
	       }
     }
     if((chosenSlateIds != null) && (!chosenSlateIds.trim().equals("")))
     {
       try {
       AddJobVessel addJobVessel  = addJobOrder.getAddJobVessels()[vesselIndex];
       AddJobProd addJobProd = addJobVessel.getAddJobProds()[productIndex];
       AddJobProdSample addJobProdSample = addJobProd.getAddJobProdSamples()[sampleLocIndex];

       if(chosenSlateIds.indexOf(";") > -1) //More than 1 SlateIds to be added
       {
        StringTokenizer st = new StringTokenizer(chosenSlateIds,";");
        while(st.hasMoreTokens())
        {

          Slate slate = jobService.getSlateBySlateId(st.nextToken());
          addJobProdSample.setJobSlates(addJobSlate(addJobProdSample.getJobSlates(),slate,null,addJobProdSample.getJobProdSample()));
        }
       }
       else //single slate id to be added
       {
          Slate slate = jobService.getSlateBySlateId(chosenSlateIds);
          addJobProdSample.setJobSlates(addJobSlate(addJobProdSample.getJobSlates(),slate,null,addJobProdSample.getJobProdSample()));
       }
       addJobOrder.setChosenSlateIds("");
       addJobOrder.setChosenContracts("");
       //addJobOrder.getJobOrder().setOtApproved(true);
     //Setting OtApproved
       if(addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.OVERTIME)|| addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.RUSH))
    	   addJobOrder.getJobOrder().setOtApproved(true);
      //End
       return showForm(request, response, errors);
       } catch(Throwable t)
       {
         addJobOrder.setChosenSlateIds("");
           addJobOrder.setChosenContracts("");
         t.printStackTrace();
         errors.reject("generic.error", new Object[] {t.getMessage()}, null);
         return showForm(request, response, errors);
       }
     }

     if((applicableContractFlag != null) && ("add".equals(applicableContractFlag) || "delete".equals(applicableContractFlag)))
     {
      try {
     AddJobVessel addJobVessel = addJobOrder.getAddJobVessels()[vesselIndex];
     AddJobProd addJobProd = addJobVessel.getAddJobProds()[productIndex];

      if("delete".equals(applicableContractFlag))
      {
        String jobContractToDelete = addJobOrder.getChosenContracts();
        long jobContractToDel = new Long(jobContractToDelete).longValue();

        AddJobContract addJobContract = JobUtil.getAddJobContractByJobContractId(addJobOrder, jobContractToDel);
        addJobProd.setApplicableContracts(deleteApplicableContract(addJobProd.getApplicableContracts(),addJobContract));
        addJobProd.setNotApplicableContracts(addJobContract(addJobProd.getNotApplicableContracts(),addJobContract));

      }
      else if("add".equals(applicableContractFlag))
      {
        String jobContractToAdd = addJobOrder.getChosenContracts();
        long jobContractToAd = new Long(jobContractToAdd).longValue();

        AddJobContract addJobContract = JobUtil.getAddJobContractByJobContractId(addJobOrder, jobContractToAd);

          addJobProd.setApplicableContracts(addApplicableContract(addJobProd.getApplicableContracts(),addJobContract));
          addJobProd.setNotApplicableContracts(deleteJobContract(addJobProd.getNotApplicableContracts(),addJobContract));

      }

      addJobVessel.getAddJobProds()[productIndex] = addJobProd;
      addJobOrder.getAddJobVessels()[vesselIndex] = addJobVessel;
      addJobOrder.setApplicableContractFlag("none");
      addJobOrder.setChosenContracts("");
      //return showPage(request,  errors, 1);
      return showForm(request, response, errors);
    } catch(Throwable t)
      {
       addJobOrder.setChosenTestIds("");
       addJobOrder.setChosenManualTestIds("");
         addJobOrder.setChosenContracts("");
       t.printStackTrace();
       errors.reject("generic.error", new Object[] {t.getMessage()}, null);
       return showForm(request, response, errors);
     }
     }


     if((labAnalysisFlag != null) && ("Y".equals(labAnalysisFlag) ))
     {
       addJobOrder.getJobOrder().setLabAnalysis(false);
       if(addJobOrder.getAddJobVessels() != null)
       {
         for(int i=0;i<addJobOrder.getAddJobVessels().length;i++)
         {
           AddJobVessel addJobVessel = addJobOrder.getAddJobVessels()[i];
           if(addJobVessel.getAddJobProds() != null && addJobVessel.getAddJobProds().length > 0)
           {
             for(int j=0;j<addJobVessel.getAddJobProds().length;j++)
             {
               AddJobProd addJobProd = addJobVessel.getAddJobProds()[j];
               if(addJobProd.getAddJobProdSamples() != null && addJobProd.getAddJobProdSamples().length >0)
               {
                 for(int k=0;k<addJobProd.getAddJobProdSamples().length;k++)
                 {
                   AddJobProdSample addJobProdSample = addJobProd.getAddJobProdSamples()[k];
                   if(!addJobOrder.getJobOrder().getLabAnalysis())
                   {
                     if(addJobProdSample.getJobProdSample().getRetainTested() != null &&
                         addJobProdSample.getJobProdSample().getRetainTested().trim().equals("T"))
                     {
                       addJobOrder.getJobOrder().setLabAnalysis(true);
                     }
                   }
                 }
               }
             }
           }
         }
       }

      addJobOrder.setLabAnalysisFlag("none");
      return showForm(request, response, errors);
     }

  /*if((otApprovedFlag != null) && ("Y".equals(otApprovedFlag) ))
     {
       addJobOrder.getJobOrder().setOtApproved(false);
       if(addJobOrder.getAddJobVessels() != null)
       {
         for(int i=0;i<addJobOrder.getAddJobVessels().length;i++)
         {
           AddJobVessel addJobVessel = addJobOrder.getAddJobVessels()[i];
           if(addJobVessel.getAddJobProds() != null && addJobVessel.getAddJobProds().length > 0)
           {
             for(int j=0;j<addJobVessel.getAddJobProds().length;j++)
             {
               AddJobProd addJobProd = addJobVessel.getAddJobProds()[j];
               if(addJobProd.getAddJobProdSamples() != null && addJobProd.getAddJobProdSamples().length >0)
               {
                 for(int k=0;k<addJobProd.getAddJobProdSamples().length;k++)
                 {
                   AddJobProdSample addJobProdSample = addJobProd.getAddJobProdSamples()[k];

                   if(addJobProdSample.getJobTests() != null && addJobProdSample.getJobTests().length >0)
                   {
                     for(int l=0;l<addJobProdSample.getJobTests().length;l++)
                     {
                       JobTest jobTest = addJobProdSample.getJobTests()[l];
                       if(!addJobOrder.getJobOrder().getOtApproved())
                         {
                           if(jobTest.getOt())
                           {
                             addJobOrder.getJobOrder().setOtApproved(true);
                           }
                         }
                     }
                   }

                   if(addJobProdSample.getJobSlates() != null && addJobProdSample.getJobSlates().length >0)
                   {
                     for(int l=0;l<addJobProdSample.getJobSlates().length;l++)
                     {
                       JobSlate jobSlate = addJobProdSample.getJobSlates()[l];
                       if(!addJobOrder.getJobOrder().getOtApproved())
                         {
                           if(jobSlate.getOt())
                           {
                             addJobOrder.getJobOrder().setOtApproved(true);
                           }
                         }
                     }
                   }

                   if(addJobProdSample.getJobManualTests() != null && addJobProdSample.getJobManualTests().length >0)
                   {
                     for(int l=0;l<addJobProdSample.getJobManualTests().length;l++)
                     {
                       JobManualTest jobManualTest = addJobProdSample.getJobManualTests()[l];
                       if(!addJobOrder.getJobOrder().getOtApproved())
                         {
                           if(jobManualTest.getOt())
                           {
                             addJobOrder.getJobOrder().setOtApproved(true);
                           }
                         }
                     }
                   }
                   if(addJobProdSample.getJobProdSample().getPriority() != null && (!addJobProdSample.getJobProdSample().getPriority().trim().equals("")))
                   {
                     if(addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.OVERTIME)|| addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.RUSH))
                       addJobOrder.getJobOrder().setOtApproved(true);
                   }

                 }
               }
             }
           }
         }
       }

      addJobOrder.setOtApprovedFlag("none");
      return showForm(request, response, errors);
     }*/
     //Added on 03-03-09 regarding sample priority functionality
     if((otApprovedFlag != null) && ("Y".equals(otApprovedFlag) ))
     {
       if(addJobOrder.getAddJobVessels() != null)
       {
         for(int i=0;i<addJobOrder.getAddJobVessels().length;i++)
         {
           AddJobVessel addJobVessel = addJobOrder.getAddJobVessels()[i];
           if(addJobVessel.getAddJobProds() != null && addJobVessel.getAddJobProds().length > 0)
           {
             for(int j=0;j<addJobVessel.getAddJobProds().length;j++)
             {
               AddJobProd addJobProd = addJobVessel.getAddJobProds()[j];
               if(addJobProd.getAddJobProdSamples() != null && addJobProd.getAddJobProdSamples().length >0)
               {
                 for(int k=0;k<addJobProd.getAddJobProdSamples().length;k++)
                 {
                   AddJobProdSample addJobProdSample = addJobProd.getAddJobProdSamples()[k];

                   if(addJobProdSample.getJobTests() != null && addJobProdSample.getJobTests().length >0)
                   {
                	 int testOtNoValCount = 0;
                	 int testOtYesValCount = 0;
                     for(int l=0;l<addJobProdSample.getJobTests().length;l++)
                     {
                       JobTest jobTest = addJobProdSample.getJobTests()[l];
                       if(jobTest.getOt())
                       {
                    	    testOtYesValCount++;
                       } else {
                    	    testOtNoValCount++;
                       }
                     }
                     if(testOtNoValCount == addJobProdSample.getJobTests().length){

                    	if(addJobProdSample.getJobProdSample() != null
                    			&& (addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.OVERTIME)
                    					|| addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.RUSH))){
                    		for(int l=0;l<addJobProdSample.getJobTests().length;l++)
                            {
                                JobTest jobTest = addJobProdSample.getJobTests()[l];

                                String testIndexs = String.valueOf(i)+ String.valueOf(j)+String.valueOf(k)+String.valueOf(l);
                                if(addJobOrder.getTestIndexFlag() != null && !addJobOrder.getTestIndexFlag().equals("no") &&
                                		addJobOrder.getTestIndexFlag().equals(testIndexs)){
	                                if(!jobTest.getOt())
	                                {
	                                	jobTest.setOt(true);
	                                }
                                }
                            }
                    		 boolean otApproveCheck = CommonUtil.checkOtApproved(addJobOrder.getAddJobVessels());
	                     		if(otApproveCheck){
	                     	    	   addJobOrder.getJobOrder().setOtApproved(true);
	                     		} else {
	                     		   		addJobOrder.getJobOrder().setOtApproved(false);
	                     	 }
	                    	 errors.reject("test.ot.yes.change.error");
	                    	 addJobOrder.setOtApprovedFlag("none");
                    		 return showForm(request, response, errors);
		           	    }
                     }
                     if(testOtYesValCount != 0){

                     	if(addJobProdSample.getJobProdSample() != null
                     			&& addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.STANDARD)){
                     		for(int l=0;l<addJobProdSample.getJobTests().length;l++)
                            {
                                JobTest jobTest = addJobProdSample.getJobTests()[l];
                                String testIndexs = String.valueOf(i)+ String.valueOf(j)+String.valueOf(k)+String.valueOf(l);

                                if(addJobOrder.getTestIndexFlag() != null && !addJobOrder.getTestIndexFlag().equals("no") &&
                                		addJobOrder.getTestIndexFlag().equals(testIndexs)){

	                                if(jobTest.getOt())
	                                {
	                                	jobTest.setOt(false);
	                                }
                                }
                            }
                     		boolean otApproveCheck = CommonUtil.checkOtApproved(addJobOrder.getAddJobVessels());
                     		if(otApproveCheck){
                     	    	   addJobOrder.getJobOrder().setOtApproved(true);
                     		} else {
                     		   		addJobOrder.getJobOrder().setOtApproved(false);
                     		}
                     		errors.reject("test.ot.no.change.error");
                     		addJobOrder.setOtApprovedFlag("none");
                     		return showForm(request, response, errors);
 		           	    } else {
	 		           	    boolean otApproveCheck = CommonUtil.checkOtApproved(addJobOrder.getAddJobVessels());
	                 		if(otApproveCheck){
	                 	    	   addJobOrder.getJobOrder().setOtApproved(true);
	                 		} else {
	                 		   		addJobOrder.getJobOrder().setOtApproved(false);
	                 		}
 		           	    }
                     }
                   } else {
                	   boolean otApproveCheck = CommonUtil.checkOtApproved(addJobOrder.getAddJobVessels());
            		   if(otApproveCheck){
            			   addJobOrder.getJobOrder().setOtApproved(true);
            		   } else {
            			   addJobOrder.getJobOrder().setOtApproved(false);
            		   }
                   }

                   if(addJobProdSample.getJobSlates() != null && addJobProdSample.getJobSlates().length >0)
                   {
                	 int slateOtNoValCount = 0;
                  	 int slateOtYesValCount = 0;
                     for(int l=0;l<addJobProdSample.getJobSlates().length;l++)
                     {
                       JobSlate jobSlate = addJobProdSample.getJobSlates()[l];
                       if(jobSlate.getOt())
                       {
                    	    slateOtYesValCount++;
                       } else {
                    	   slateOtNoValCount++;
                       }
                     }
                     if(slateOtNoValCount == addJobProdSample.getJobSlates().length){
                     	if(addJobProdSample.getJobProdSample() != null
                     			&& (addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.OVERTIME)
                     					|| addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.RUSH))){

                     		for(int l=0;l<addJobProdSample.getJobSlates().length;l++)
                            {
                                JobSlate jobSlate = addJobProdSample.getJobSlates()[l];
                                String slateIndexs = String.valueOf(i)+ String.valueOf(j)+String.valueOf(k)+String.valueOf(l);
                                if(addJobOrder.getTestIndexFlag() != null && !addJobOrder.getTestIndexFlag().equals("no") &&
                                		addJobOrder.getTestIndexFlag().equals(slateIndexs)){
	                                if(!jobSlate.getOt())
	                                {
	                                	jobSlate.setOt(true);
	                             	   	addJobOrder.getJobOrder().setOtApproved(true);
	                                }
                                }
                            }
                     		boolean otApproveCheck = CommonUtil.checkOtApproved(addJobOrder.getAddJobVessels());
                 		    if(otApproveCheck){
                 			   addJobOrder.getJobOrder().setOtApproved(true);
                 		    } else {
                 			   addJobOrder.getJobOrder().setOtApproved(false);
                 		    }
                     		errors.reject("test.ot.yes.change.error");
                     		addJobOrder.setOtApprovedFlag("none");
                     		return showForm(request, response, errors);
 		           	    }
                      }
                      if(slateOtYesValCount != 0){
                      	if(addJobProdSample.getJobProdSample() != null
                      			&& addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.STANDARD)){

                      		for(int l=0;l<addJobProdSample.getJobSlates().length;l++)
                            {
                                JobSlate jobSlate = addJobProdSample.getJobSlates()[l];
                                String slateIndexs = String.valueOf(i)+ String.valueOf(j)+String.valueOf(k)+String.valueOf(l);

                                if(addJobOrder.getTestIndexFlag() != null && !addJobOrder.getTestIndexFlag().equals("no") &&
                                		addJobOrder.getTestIndexFlag().equals(slateIndexs)){

	                                if(jobSlate.getOt())
	                                {
	                                	jobSlate.setOt(false);
	                                }
                                }
                            }
                      		 boolean otApproveCheck = CommonUtil.checkOtApproved(addJobOrder.getAddJobVessels());
      	        		     if(otApproveCheck){
      	        			   addJobOrder.getJobOrder().setOtApproved(true);
      	        		     } else {
      	        			   addJobOrder.getJobOrder().setOtApproved(false);
      	        		     }
                      		 errors.reject("test.ot.no.change.error");
                      		 addJobOrder.setOtApprovedFlag("none");
                      		return showForm(request, response, errors);
  		           	    }
                      }
                   } else {
                	   boolean otApproveCheck = CommonUtil.checkOtApproved(addJobOrder.getAddJobVessels());
	        		   if(otApproveCheck){
	        			   addJobOrder.getJobOrder().setOtApproved(true);
	        		   } else {
	        			   addJobOrder.getJobOrder().setOtApproved(false);
	        		   }
                   }

                   if(addJobProdSample.getJobManualTests() != null && addJobProdSample.getJobManualTests().length >0)
                   {
                	 int testOtNoValCount = 0;
                	 int testOtYesValCount = 0;
                     for(int l=0;l<addJobProdSample.getJobManualTests().length;l++)
                     {
                       JobManualTest jobTest = addJobProdSample.getJobManualTests()[l];
                       if(jobTest.getOt())
                       {
                    	    testOtYesValCount++;
                       } else {
                    	    testOtNoValCount++;
                       }
                     }
                     if(testOtNoValCount == addJobProdSample.getJobManualTests().length){

                    	if(addJobProdSample.getJobProdSample() != null
                    			&& (addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.OVERTIME)
                    					|| addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.RUSH))){
                    		for(int l=0;l<addJobProdSample.getJobManualTests().length;l++)
                            {
                    			JobManualTest jobTest = addJobProdSample.getJobManualTests()[l];

                                String testIndexs = String.valueOf(i)+ String.valueOf(j)+String.valueOf(k)+String.valueOf(l);
                                if(addJobOrder.getTestIndexFlag() != null && !addJobOrder.getTestIndexFlag().equals("no") &&
                                		addJobOrder.getTestIndexFlag().equals(testIndexs)){
	                                if(!jobTest.getOt())
	                                {
	                                	jobTest.setOt(true);
	                                }
                                }
                            }
                    		 boolean otApproveCheck = CommonUtil.checkOtApproved(addJobOrder.getAddJobVessels());
	                     		if(otApproveCheck){
	                     	    	   addJobOrder.getJobOrder().setOtApproved(true);
	                     		} else {
	                     		   		addJobOrder.getJobOrder().setOtApproved(false);
	                     	 }
	                    	 errors.reject("test.ot.yes.change.error");
	                    	 addJobOrder.setOtApprovedFlag("none");
                    		 return showForm(request, response, errors);
		           	    }
                     }
                     if(testOtYesValCount != 0){

                     	if(addJobProdSample.getJobProdSample() != null
                     			&& addJobProdSample.getJobProdSample().getPriority().trim().equals(Constants.STANDARD)){
                     		for(int l=0;l<addJobProdSample.getJobManualTests().length;l++)
                            {
                     			JobManualTest jobTest = addJobProdSample.getJobManualTests()[l];
                                String testIndexs = String.valueOf(i)+ String.valueOf(j)+String.valueOf(k)+String.valueOf(l);

                                if(addJobOrder.getTestIndexFlag() != null && !addJobOrder.getTestIndexFlag().equals("no") &&
                                		addJobOrder.getTestIndexFlag().equals(testIndexs)){

	                                if(jobTest.getOt())
	                                {
	                                	jobTest.setOt(false);
	                                }
                                }
                            }
                     		boolean otApproveCheck = CommonUtil.checkOtApproved(addJobOrder.getAddJobVessels());
                     		if(otApproveCheck){
                     	    	   addJobOrder.getJobOrder().setOtApproved(true);
                     		} else {
                     		   		addJobOrder.getJobOrder().setOtApproved(false);
                     		}
                     		errors.reject("test.ot.no.change.error");
                     		addJobOrder.setOtApprovedFlag("none");
                     		return showForm(request, response, errors);
 		           	    } else {
	 		           	    boolean otApproveCheck = CommonUtil.checkOtApproved(addJobOrder.getAddJobVessels());
	                 		if(otApproveCheck){
	                 	    	   addJobOrder.getJobOrder().setOtApproved(true);
	                 		} else {
	                 		   		addJobOrder.getJobOrder().setOtApproved(false);
	                 		}
 		           	    }
                     }
                   }//mtest
                 }
               }
             }
           }
         }
       }
      addJobOrder.setOtApprovedFlag("none");
      return showForm(request, response, errors);
     }//End
     if(instructionFlag != null && (!instructionFlag.trim().equals("")) && (!instructionFlag.trim().equals("none")))
     {
       try {
         AddJobVessel addJobVessel = addJobOrder.getAddJobVessels()[vesselIndex];
         AddJobProd addJobProd = addJobVessel.getAddJobProds()[productIndex];
         JobEvent jobEvent = addJobProd.getJobEvents()[eventIndex];

         Event event = jobService.getEventByEventCode(jobEvent.getEvent().getEventCode());
         if(event != null)
           jobEvent.setEventInstructions(event.getEventInstruction());
         addJobProd.getJobEvents()[eventIndex] = jobEvent;
         addJobVessel.getAddJobProds()[productIndex] = addJobProd;
         addJobOrder.getAddJobVessels()[vesselIndex] = addJobVessel;
       addJobOrder.setInstructionFlag("none");
         return showForm(request, response, errors);
       } catch(Throwable t)
         {
        addJobOrder.setInstructionFlag("none");
          t.printStackTrace();
          errors.reject("generic.error", new Object[] {t.getMessage()}, null);
          return showForm(request, response, errors);
        }
     }

     String portLocationFlag = request.getParameter("portLocationFlag");

     // For itrack issue 88661 null error while jobInstrutions save
    // String myPortLocation = addJobOrder.getAddJobVessels()[vesselIndex].getAddJobProds()[productIndex].getServiceLocation();
    /* if ("portlocation".equals(portLocationFlag)) {

    	 String myPortLocation = addJobOrder.getAddJobVessels()[vesselIndex].getAddJobProds()[productIndex].getServiceLocation();

    	 try{
			ServiceLocationService serviceLocationService = (ServiceLocationService) ServiceLocator.getInstance().getBean("serviceLocationService");
			ServiceLocation serviceLocation = serviceLocationService.getServiceLocationByServiceLocationCode(myPortLocation);
			CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");
			if (serviceLocation != null) {
				 String servCountry = "";
				 String servState = "";
				 String servCity = "";
				 String servName = "";
				 Country country =null;
					if(serviceLocation.getCountryCode()!= null)
					{
						country = countryService.getCountryByCode(serviceLocation.getCountryCode());
					}
				 if(serviceLocation.getStateCode()!= null && serviceLocation.getCountryCode()!= null)
					{
						State state = countryService.getStateByCodeAndCountryCode(serviceLocation.getStateCode(),serviceLocation.getCountryCode());
						if(state != null && (country==null || country.getShowState())){
							servState =","+ state.getName();
						}
					}
					if(serviceLocation.getCountryCode()!= null)
					{
						if(country != null)
						servCountry = ","+country.getName();
					}
					if(serviceLocation.getCity()!= null && !serviceLocation.getCity().trim().equals(""))
						servCity = serviceLocation.getCity();
				String portValue = servCity + servState + servCountry;
				String servValue = servName +servCity+ servState + servCountry;
			    addJobOrder.getAddJobVessels()[vesselIndex].getAddJobProds()[productIndex].getJobProd().setDestination(portValue);
			    addJobOrder.getAddJobVessels()[vesselIndex].getAddJobProds()[productIndex].setServiceLocation(servValue);
			}
			return showForm(request, response, errors);
		}
			catch(ServiceException e)
			{
			  addJobOrder.setPortLocationFlag("none");
			  e.printStackTrace();
			  errors.reject(e.getMessage(), e.getParams(), null);
			  return showForm(request, response, errors);
			}
			catch(Throwable t)
			{
			  addJobOrder.setPortLocationFlag("none");
			  t.printStackTrace();
			  errors.reject("generic.error", new Object[] {t.getMessage()}, null);
			  return showForm(request, response, errors);
			}
		}*/


String servValue = "";
     String servCity = "";
     String servState = "";
     String servCountry = "";
     if ("portlocation".equals(portLocationFlag)) {
    	 /*Newly Added */
    	 String myPortLocation = addJobOrder.getAddJobVessels()[vesselIndex].getAddJobProds()[productIndex].getServiceLocation();
		//End
    	 try{

    		 System.out.println("in try 1252");
			ServiceLocationService serviceLocationService = (ServiceLocationService) ServiceLocator.getInstance().getBean("serviceLocationService");
			ServiceLocation serviceLocation = serviceLocationService.getServiceLocationByServiceLocationCode(myPortLocation);
			CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");
			if (serviceLocation != null) {

				 //String servName = "";
				 Country country =null;
					if(serviceLocation.getCountryCode()!= null)
					{
						country = countryService.getCountryByCode(serviceLocation.getCountryCode());
					}
				 if(serviceLocation.getStateCode()!= null && serviceLocation.getCountryCode()!= null)
					{
						State state = countryService.getStateByCodeAndCountryCode(serviceLocation.getStateCode(),serviceLocation.getCountryCode());
						if(state != null && (country==null || country.getShowState())){
							servState =","+ state.getName();
						}
					}
					if(serviceLocation.getCountryCode()!= null)
					{
						if(country != null)
						servCountry = ","+country.getName();
					}
					if(serviceLocation.getCity()!= null && !serviceLocation.getCity().trim().equals(""))
						servCity = serviceLocation.getCity();
				String portValue = servCity + servState + servCountry;

				servValue = servCity+ servState + servCountry;
			    addJobOrder.getAddJobVessels()[vesselIndex].getAddJobProds()[productIndex].getJobProd().setDestination(portValue);
			    addJobOrder.getAddJobVessels()[vesselIndex].getAddJobProds()[productIndex].setServiceLocation(servValue);
			}
			System.out.println("setting PortLocationFlag to none");
			addJobOrder.setPortLocationFlag("none");
			return showForm(request, response, errors);
		}
			catch(ServiceException e)
			{
				System.out.println("in catch 1");
			  addJobOrder.setPortLocationFlag("none");
			  e.printStackTrace();
			  errors.reject(e.getMessage(), e.getParams(), null);
			  return showForm(request, response, errors);
			}
			catch(Throwable t)
			{
				System.out.println("in catch 2");
			  addJobOrder.setPortLocationFlag("none");
			  t.printStackTrace();
			  errors.reject("generic.error", new Object[] {t.getMessage()}, null);
			  return showForm(request, response, errors);
			}
		}
     //end of if


      String vesselNames = "";
      String productNames = "";
      Set jobVesselSet = new HashSet();
      Set jobProdSet = new HashSet();
      Set jobProdSampleSet = new HashSet();
      Set jobTestSet = new HashSet();
      Set jobSlateSet = new HashSet();

      //Validate UI fields
      if(addJobOrder.getAddJobVessels() != null && addJobOrder.getAddJobVessels().length > 0)
      {
        String vesselLabel = "";
        if(addJobOrder.getJobOrder().getJobType() != null && (addJobOrder.getJobOrder().getJobType().trim().equals("INSP") ||
            addJobOrder.getJobOrder().getJobType().trim().equals("MAR"))    )
          vesselLabel = "Vessel";
        else
          vesselLabel = "Location";

      for(int i=0;i<addJobOrder.getAddJobVessels().length;i++)
      {
          AddJobVessel addJobVessel = addJobOrder.getAddJobVessels()[i];
          JobVessel jobVessel = addJobOrder.getAddJobVessels()[i].getJobVessel();
          if(jobVessel.getVesselName() == null || jobVessel.getVesselName().trim().equals(""))
          {
            errors.reject("create.job.order.error", new Object[] {"Please enter a "+vesselLabel+" name for "+vesselLabel+" "+(i+1)+ " under "+vesselLabel}, null);
              return showForm(request, response, errors);
          }
          if(addJobOrder.getJobOrder().getJobType() != null && (addJobOrder.getJobOrder().getJobType().trim().equals("INSP") ||
            addJobOrder.getJobOrder().getJobType().trim().equals("MAR"))    )
          {
            if(jobVessel.getType() == null || jobVessel.getType().trim().equals(""))
            {
              errors.reject("create.job.order.error", new Object[] {"Vessel Type for Vessel "+(i+1)+ " cannot be blank"}, null);
                return showForm(request, response, errors);
            }
          }
          if(addJobVessel.getAddJobProds() != null && addJobVessel.getAddJobProds().length > 0)
          {
            for(int j=0;j<addJobVessel.getAddJobProds().length;j++)
            {
              AddJobProd addJobProd = addJobVessel.getAddJobProds()[j];
              JobProd jobProd = addJobProd.getJobProd();
              // For itrack issue 24329
              String desTination = jobProd.getDestination();
              String servLocCity = "";
              String servLocState = "";
              String servLocCountry = "";
      	      String[] splittArray = null;
      	      if (desTination != null && !desTination.trim().equals("")){
	      	      if(desTination.indexOf(",")!= -1){
		      	        splittArray = desTination.split(",");
		      	        if(splittArray.length == 3){
		      	        	servLocCity = splittArray[0];
		      	        	servLocState = splittArray[1];
		      	        	servLocCountry = splittArray[2];
		      	        } else if(splittArray.length == 2) {
		      	        	servCity = splittArray[0];
		          	    	servCountry = splittArray[1];
		      	        } else {
		      	        	servCity = desTination;
		      	        }
	      	      } else {
	      	    	servLocCity = desTination;
	      	      }
	      	      ServiceLocationService serviceLocationService = (ServiceLocationService) ServiceLocator.getInstance().getBean("serviceLocationService");
	         	  List serViceLocations = serviceLocationService.getServiceLocationByCityAndStateAndCountry(servLocCity, servLocState, servLocCountry);
	     	      if(serViceLocations == null)
	     		  {
	     				errors.reject("destination.error", new Object[] {"For Product "+(j+1)+" under "+vesselLabel+" "+(i+1)}, desTination);
	     				return showForm(request, response, errors);
	     		  }
      	      }
              // For itrack issue 24329 End

              if(jobProd.getGroup() == null || jobProd.getGroup().trim().equals(""))
              {
                errors.reject("create.job.order.error", new Object[] {"Product Group for Product "+(j+1)+" under "+vesselLabel+" "+(i+1)+ " cannot be blank"}, null);
                  return showForm(request, response, errors);
              }
              if(jobProd.getProductQty() > 0)
              {
                if(jobProd.getUom() == null || jobProd.getUom().trim().equals(""))
                {
                  errors.reject("create.job.order.error", new Object[] {"Please select a UOM for Product "+(j+1)+" under "+vesselLabel+" "+(i+1)}, null);
                    return showForm(request, response, errors);
                }
              }
              if(addJobProd.getJobProdQtys() != null && addJobProd.getJobProdQtys().length >0)
              {
                for(int k=0;k<addJobProd.getJobProdQtys().length;k++)
                {
                  JobProdQty jobProdQty = addJobProd.getJobProdQtys()[k];
                  if(jobProdQty.getProductQty() > 0)
                  {
                    if(jobProdQty.getUom() == null || jobProdQty.getUom().trim().equals(""))
                    {
                      if(jobProd.getUom() == null || jobProd.getUom().trim().equals(""))
                      {
                        errors.reject("create.job.order.error", new Object[] {"Please select a UOM for Quantity "+(k+1)+" under Product "+(j+1)+" under "+vesselLabel+" "+(i+1)}, null);
                        return showForm(request, response, errors);
                      }
                    }
                  }
                }
              }
              if(addJobProd.getJobEvents() != null && addJobProd.getJobEvents().length >0)
              {
                for(int l=0;l<addJobProd.getJobEvents().length;l++)
                {
                  JobEvent jobEvent = addJobProd.getJobEvents()[l];
                  if(jobEvent.getEvent().getEventCode() == null || jobEvent.getEvent().getEventCode().trim().equals(""))
                  {
                    errors.reject("create.job.order.error", new Object[] {"Please select an  Event for Inspection "+(l+1)+" under Product "+(j+1)+" under "+vesselLabel+" "+(i+1)}, null);
                      return showForm(request, response, errors);
                  }
                }
              }
              if(addJobProd.getAddJobProdSamples() != null && addJobProd.getAddJobProdSamples().length >0)
              {
                for(int m=0;m<addJobProd.getAddJobProdSamples().length;m++)
                {
                  AddJobProdSample addJobProdSample = addJobProd.getAddJobProdSamples()[m];
                  JobProdSample jobProdSample = addJobProdSample.getJobProdSample();

                  if(jobProdSample.getJobSampleLocation() == null || jobProdSample.getJobSampleLocation().trim().equals(""))
                  {
                    errors.reject("create.job.order.error", new Object[] {"Please select a Sample Location for Sample "+(m+1)+" under Product "+(j+1)+" under "+vesselLabel+" "+(i+1)}, null);
                      return showForm(request, response, errors);
                  }
                }
                String retainPeriod = addJobOrder.getJobOrder().getRetainPeriod();
                if(retainPeriod == null || retainPeriod.trim().equals(""))
                {
                  errors.reject("retain.period.samples.error", new Object[] {}, null);
                    return showForm(request, response, errors);
                }
                try{
                  Double retainPeriodVal  = Double.parseDouble(retainPeriod) ;
                }
                catch(Exception e)
                {
                  errors.reject("invalid.retain.period.samples.error", new Object[] {}, null);
                    return showForm(request, response, errors);
                }

              }

            }
          }
      }
      }
      try
      {
      //Start Template
        if ((templateFlag != null) && "yes".equals(templateFlag)){
          List tmpJobOrderList = jobService.getTemplateByNameAndBranch(tmpName,addJobOrder.getJobOrder().getBranchName());
              if((tmpJobOrderList != null) && (tmpJobOrderList.size()>0)){
            	  JobOrder tmpJobOrder = (JobOrder)tmpJobOrderList.get(0);
              tmpNumber = tmpJobOrder.getJobNumber();
           }
          else{
        	  tmpNumber = jobService.getTemplateNumber(addJobOrder.getJobOrder().getBranchName());
             /* if (tmpJobOrder.getTempName().equalsIgnoreCase(tmpName)) {
                      errors.reject("create.job.order.error", new Object[] {"Template with name - "+tmpName+" , already Exists for the Branch - "+addJobOrder.getJobOrder().getBranchName()+". Please change the Template name to proceed..."}, null);
                      return showForm(request, response, errors);
            }*/
          }

          //tmpNumber = jobService.getTemplateNumber(addJobOrder.getJobOrder().getBranchName());
          jobNumber = addJobOrder.getJobOrder().getJobNumber();
          addJobOrder.getJobOrder().setJobNumber(tmpNumber);
          addJobOrder.getJobOrder().setTempName(tmpName);
          addJobOrder.getJobOrder().setIsTemplate(true);
          addJobOrder.getJobOrder().setIsDefault(false);
          addJobOrder.getJobOrder().setFirstName(SecurityUtil.getUser().getFirstName());
          addJobOrder.getJobOrder().setLastName(SecurityUtil.getUser().getLastName());
          addJobOrder.setApplicableContractFlag("none");

        }
      //End Template
      //Save Vessels first

        if(addJobOrder.getAddJobVessels() != null && addJobOrder.getAddJobVessels().length > 0)
        {
        for(int i=0;i<addJobOrder.getAddJobVessels().length;i++)
        {
          AddJobVessel addJobVessel = addJobOrder.getAddJobVessels()[i];
          JobVessel jobVessel = addJobOrder.getAddJobVessels()[i].getJobVessel();

          jobVessel.getJobVesselId().setJobNumber(addJobOrder.getJobOrder().getJobNumber());

          if(vesselNames == null || vesselNames.trim().equals(""))
            vesselNames = jobVessel.getVesselName();
          else
            vesselNames = vesselNames + ";" + jobVessel.getVesselName();

//        Set to default 'OTHER' vessel type if null : iTrak 54627
        if(jobVessel.getType() == null || jobVessel.getType().trim().equals(""))
          {
        jobVessel.setType(Constants.OTHER);
          }

          jobVessel.getJobVesselId().setLinkedVslRow(i+1);
         addJobOrder.getJobOrder().getJobVessels().add(jobVessel);
          //Save products inside jobVessel
          if(addJobVessel.getAddJobProds() != null && addJobVessel.getAddJobProds().length > 0)
          {
            for(int j=0;j<addJobVessel.getAddJobProds().length;j++)
            {
              AddJobProd addJobProd = addJobVessel.getAddJobProds()[j];
              JobProd jobProd = addJobProd.getJobProd();


              jobProd.getJobProdId().setLinkedVslRow(i+1);
              jobProd.getJobProdId().setProdSeqNum(j+1);
              jobProd.getJobProdId().setJobNumber(addJobOrder.getJobOrder().getJobNumber());

              //  Template
              if ((templateFlag == null) || (!"yes".equals(templateFlag))){       //  Template
                //Save JobProdContracts (if any)
              if(addJobProd.getApplicableContracts() != null && addJobProd.getApplicableContracts().length > 0 )
              {
                for(int v=0;v<addJobProd.getApplicableContracts().length;v++)
                {
                  AddJobContract addJobContract = addJobProd.getApplicableContracts()[v];
                  if(addJobContract != null)
                    jobProd.getJobProdContracts().add(addJobContract.getJobContract());
                }
              }
   } // Template
              jobProd.setJobVessel(jobVessel);
              jobVessel.getJobProds().add(jobProd);

              //Save individual product quantities within JobProd
              Set jobProdQtySet = new HashSet();

              double sumQty = 0;
              if(addJobProd.getJobProdQtys() != null && addJobProd.getJobProdQtys().length >0)
              {
                for(int k=0;k<addJobProd.getJobProdQtys().length;k++)
                {
                  JobProdQty jobProdQty = addJobProd.getJobProdQtys()[k];
                  if(jobProdQty.getProductQty() > 0)
                  {
                    jobProdQty.setJobNumber(addJobOrder.getJobOrder().getJobNumber());
                    //jobProdQty.setJobVesselName(jobVessel.getJobVesselId().getJobVesselName());
                    jobProdQty.setLinkedVslRow(i+1);
                    jobProdQty.setProdSeqNum(jobProd.getJobProdId().getProdSeqNum());
                    jobProdQty.setJobProd(jobProd);
                    jobProdQty.setGroup("dummy");


                    if(jobProdQty.getUom() == null || jobProdQty.getUom().trim().equals(""))
                      jobProdQty.setUom(jobProd.getUom());

                    if(jobProdQty.getOption() == null || jobProdQty.getOption().trim().equals(""))
                      jobProdQty.setOption(jobProd.getOption());

                    if(jobProdQty.getPlusMinus() == null || jobProdQty.getPlusMinus().trim().equals(""))
                      jobProdQty.setPlusMinus(jobProd.getPlusMinus());

                    if(jobProdQty.getPlusMinusPct() <= 0)
                      jobProdQty.setPlusMinusPct(jobProd.getPlusMinusPct());

                    sumQty = sumQty + jobProdQty.getProductQty();
                    if(jobProd.getUom() == null || jobProd.getUom().trim().equals(""))
                      jobProd.setUom(jobProdQty.getUom());
                    jobProdQtySet.add(jobProdQty);

                  }

                }
              }
              if(jobProdQtySet != null)
                System.out.println("size of jobprodqtys : "+jobProdQtySet.size());
              jobProd.setJobProdQtys(jobProdQtySet);

              //Save jobProd with updated summed qty
              if(sumQty > 0)
                jobProd.setProductQty(NumberUtil.roundHalfUp(sumQty,2));


              if(productNames == null || productNames.trim().equals(""))
              {
                productNames = jobProd.getJobProductName();
                /*if(jobOrder.getJobType().trim().equals(Constants.INSPECTION_JOBTYPE))
                {
                  if(jobProd.getProductQty() > 0)
                  {
                    productNames = productNames + "-" + new Double(jobProd.getProductQty()).intValue();
                  if(jobProd.getUom() != null && (!jobProd.getUom().trim().equals("")))
                    productNames = productNames + " " + jobProd.getUom();
                  }
                }*/
              }
              else
              {
                productNames = productNames + ", " + jobProd.getJobProductName();
                /*if(jobOrder.getJobType().trim().equals(Constants.INSPECTION_JOBTYPE))
                {
                  if(jobProd.getProductQty() > 0)
                  {
                    productNames = productNames + "-" + new Double(jobProd.getProductQty()).intValue();
                  if(jobProd.getUom() != null && (!jobProd.getUom().trim().equals("")))
                    productNames = productNames + " " + jobProd.getUom();
                  }
                }*/
              }

              //Save job Inspection Events for each product
              if(addJobProd.getJobEvents() != null && addJobProd.getJobEvents().length >0)
              {
                for(int l=0;l<addJobProd.getJobEvents().length;l++)
                {
                  JobEvent jobEvent = addJobProd.getJobEvents()[l];
                  jobEvent.getJobEventId().setJobNumber(addJobOrder.getJobOrder().getJobNumber());
                  //jobInspectionEvent.getJobInspectionEventId().setJobVesselName(jobVessel.getJobVesselId().getJobVesselName());
                  jobEvent.getJobEventId().setLinkedVslRow(jobVessel.getJobVesselId().getLinkedVslRow());
                  jobEvent.setJobProd(jobProd);
                  jobEvent.getJobEventId().setProdSeqNum(jobProd.getJobProdId().getProdSeqNum());
                  jobEvent.getJobEventId().setJobEventId(l+1);
                  jobEvent.setEvent(jobService.getEventByEventCode(jobEvent.getEvent().getEventCode()));
                  //jobInspectionEvent.getJobInspectionEventId().setJobProductName(jobProd.getJobProductName());

                  jobProd.getJobEvents().add(jobEvent);
                }
              }

              //Save Job prod Sample object for each product
              if(addJobProd.getAddJobProdSamples() != null && addJobProd.getAddJobProdSamples().length >0)
              {
                for(int m=0;m<addJobProd.getAddJobProdSamples().length;m++)
                {
                  AddJobProdSample addJobProdSample = addJobProd.getAddJobProdSamples()[m];
                  JobProdSample jobProdSample = addJobProdSample.getJobProdSample();
                  jobProdSample.getJobProdSampleId().setJobNumber(addJobOrder.getJobOrder().getJobNumber());
                  jobProdSample.setJobProd(jobProd);
                  //jobProdSample.getJobProdSampleId().setJobProductName(addJobProd.getJobProd().getJobProductName());
                  //jobProdSample.getJobProdSampleId().setJobVesselName(jobVessel.getJobVesselId().getJobVesselName());
                  jobProdSample.getJobProdSampleId().setLinkedVslRow(jobVessel.getJobVesselId().getLinkedVslRow());
                  jobProdSample.getJobProdSampleId().setProdSeqNum(jobProd.getJobProdId().getProdSeqNum());
                  jobProdSample.getJobProdSampleId().setSampSeqId(m+1);
                  jobProd.getJobProdSamples().add(jobProdSample);

                  StringBuffer sampleInstrSb = new StringBuffer();
                  StringBuffer testListSb = new StringBuffer();
                  StringBuffer testIdsSb = new StringBuffer();
                  //101771 START
                  StringBuffer manualTestListSb = new StringBuffer();
                  StringBuffer manualTestIdsSb = new StringBuffer();
                  //101771 END
                  StringBuffer slateListSb = new StringBuffer();
                  StringBuffer slateIdsSb = new StringBuffer();

                  if(addJobProdSample.getJobTests() != null)
                    System.out.println("trying to save JobTests not null");
                  else
                    System.out.println("trying to save JobTests  NULL");
                  //Save JobTests within JobProdSample
                  if(jobProdSample.getJobTests() != null)
                    jobProdSample.getJobTests().clear();
                  //101771 START
                  if(jobProdSample.getJobManualTests() != null)
                      jobProdSample.getJobManualTests().clear();
                  //101771 END
                 
                  if(addJobProdSample.getJobTests() != null && addJobProdSample.getJobTests().length > 0)
                  {
                   testListSb.append(I18nUtil.getString(I18nUtil.getLocale(request), "Following_tests_added_to_sample"));

                    for(int n=0;n<addJobProdSample.getJobTests().length;n++)
                    {
                      //Save JobTests for each contract
                      JobTest jobTest = addJobProdSample.getJobTests()[n];
                      jobTest.setJobProdSample(jobProdSample);
                      //jobTest=jobService.saveJobTest(jobTest);
                      jobProdSample.getJobTests().add(jobTest);
                      if(testIdsSb == null || testIdsSb.toString().length() <= 0)
                      {
                        testIdsSb.append(jobTest.getTest().getTestId());
                      }
                      else
                      {
                        testIdsSb.append(",");
                        testIdsSb.append(jobTest.getTest().getTestId());
                      }
                      //jobTestSet.add(jobTest);
                      //TODO: need to think how to do it
                      /*
                      if(addJobProd.getJobCodes() != null)
                      {
                        for(int p=0;p<addJobProd.getJobCodes().length;p++)
                        {
                          System.out.println("job contract code :"+addJobProd.getJobContractCodes()[p]);
                          JobContract jobContract = jobService.getJobContractByJobNumberAndContractCode(addJobOrder.getJobOrder().getJobNumber(), addJobProd.getJobContractCodes()[p]);
                          JobContractTest jobContractTestToAdd = new JobContractTest();
                          jobContractTestToAdd.setJobContract(jobContract);
                          jobContractTestToAdd.setJobProdSample(addJobProdSample.getJobProdSample());
                          jobContractTestToAdd.setOt(jobContractTest.getOt());
                          jobContractTestToAdd.setQuantity(jobContractTest.getQuantity());
                          jobContractTestToAdd.setTest(jobContractTest.getTest());
                          jobContractTestToAdd.setContractRefNo(jobContract.getContractCode());
                          jobContractTestToAdd = jobService.saveJobContractTest(jobContractTestToAdd);
                        }
                      }
                      else
                        System.out.println("job contract code is null");
                      */
                    }
                  }

                  //101771 START
                  
                  if(addJobProdSample.getJobManualTests() != null && addJobProdSample.getJobManualTests().length > 0)
                  {
                	  /* For iTrack#134877-START */
                   manualTestListSb.append(I18nUtil.getString(I18nUtil.getLocale(request), "Following_manual_tests_added_to_sample"));
                   /* For iTrack#134877-END */
                    for(int n=0;n<addJobProdSample.getJobManualTests().length;n++)
                    {
                      //Save JobManualTests for each contract
                      JobManualTest jobManualTest = addJobProdSample.getJobManualTests()[n];
                      jobManualTest.setJobProdSample(jobProdSample);
                      //jobTest=jobService.saveJobTest(jobTest);
                      jobProdSample.getJobManualTests().add(jobManualTest);
                      if(manualTestIdsSb == null || manualTestIdsSb.toString().length() <= 0)
                      {
                    	  manualTestIdsSb.append(jobManualTest.getTestId());
                      }
                      else
                      {
                    	  manualTestIdsSb.append(",");
                    	  manualTestIdsSb.append(jobManualTest.getTestId());
                      }
                      
                      //jobTestSet.add(jobTest);
                      //TODO: need to think how to do it
                      /*
                      if(addJobProd.getJobCodes() != null)
                      {
                        for(int p=0;p<addJobProd.getJobCodes().length;p++)
                        {
                          System.out.println("job contract code :"+addJobProd.getJobContractCodes()[p]);
                          JobContract jobContract = jobService.getJobContractByJobNumberAndContractCode(addJobOrder.getJobOrder().getJobNumber(), addJobProd.getJobContractCodes()[p]);
                          JobContractTest jobContractTestToAdd = new JobContractTest();
                          jobContractTestToAdd.setJobContract(jobContract);
                          jobContractTestToAdd.setJobProdSample(addJobProdSample.getJobProdSample());
                          jobContractTestToAdd.setOt(jobContractTest.getOt());
                          jobContractTestToAdd.setQuantity(jobContractTest.getQuantity());
                          jobContractTestToAdd.setTest(jobContractTest.getTest());
                          jobContractTestToAdd.setContractRefNo(jobContract.getContractCode());
                          jobContractTestToAdd = jobService.saveJobContractTest(jobContractTestToAdd);
                        }
                      }
                      else
                        System.out.println("job contract code is null");
                      */
                    }
                    //jobProdSample.setJobTests(jobTestSet);
                  }
                  //101771 END
                  
                  
                  
                  //Save Slates within JobProdSample
                  if(jobProdSample.getJobSlates() != null)
                    jobProdSample.getJobSlates().clear();
                  if(addJobProdSample.getJobSlates() != null && addJobProdSample.getJobSlates().length > 0)
                  {
                    slateListSb.append(I18nUtil.getString(I18nUtil.getLocale(request), "Following_slates_added_to_sample"));

                    for(int o=0;o<addJobProdSample.getJobSlates().length;o++)
                    {
                      JobSlate jobSlate = addJobProdSample.getJobSlates()[o];

                      jobSlate.setJobProdSample(jobProdSample);
                      //jobSlate = jobService.saveJobSlate(jobSlate);
                      //jobSlateSet.add(jobSlate);
                      jobProdSample.getJobSlates().add(jobSlate);

                      if(slateIdsSb == null || slateIdsSb.toString().length() <= 0)
                      {
                        slateIdsSb.append(jobSlate.getSlate().getSlateId());
                      }
                      else
                      {
                        slateIdsSb.append(",");
                        slateIdsSb.append(jobSlate.getSlate().getSlateId());
                      }

                      /*
                      for(int q=0;q<addJobProd.getJobContractCodes().length;q++)
                      {
                        System.out.println("job contract code :"+addJobProd.getJobContractCodes()[q]);
                        JobContract jobContract = jobService.getJobContractByJobNumberAndContractCode(addJobOrder.getJobOrder().getJobNumber(), addJobProd.getJobContractCodes()[q]);
                        JobContractSlate jobContractSlateToAdd = new JobContractSlate();
                        jobContractSlateToAdd.setJobContract(jobContract);
                        jobContractSlateToAdd.setJobProdSample(addJobProdSample.getJobProdSample());
                        jobContractSlateToAdd.setOt(jobContractSlate.getOt());
                        jobContractSlateToAdd.setQuantity(jobContractSlate.getQuantity());
                        jobContractSlateToAdd.setSlate(jobContractSlate.getSlate());
                        jobContractSlateToAdd.setContractRefNo(jobContract.getContractCode());


                        jobContractSlateToAdd = jobService.saveJobContractSlate(jobContractSlateToAdd);
                      }
                      */

                    }

                    //jobProdSample.setJobSlates(jobSlateSet);
                  }
                  if(testIdsSb != null &&  testIdsSb.toString().length() > 0)
                  {
                    testListSb.append(testIdsSb.toString());
                  }
                  //101771 START
                  if(manualTestIdsSb != null &&  manualTestIdsSb.toString().length() > 0)
                  {
                	  manualTestListSb.append(manualTestIdsSb.toString());
                  }
                  //101771 END
                  if(slateIdsSb != null &&  slateIdsSb.toString().length() > 0)
                  {
                    slateListSb.append(slateIdsSb.toString());
                  }
                  sampleInstrSb.append(testListSb.toString());
                  //101771 START
                sampleInstrSb.append(System.getProperty("line.separator"));
                  sampleInstrSb.append(manualTestListSb.toString());
                  //101771 END
                  sampleInstrSb.append(System.getProperty("line.separator"));
                  sampleInstrSb.append(slateListSb.toString());
                  /* Commented for iTrack #134877-START */
                  /* if(jobProdSample.getSampleInstructions() == null || jobProdSample.getSampleInstructions().trim().equals(""))
                  {
                    jobProdSample.setSampleInstructions(sampleInstrSb.toString());
                  }*/
                  /* Commented for iTrack #134877-END */
                  /* For iTrack #134877-START */
                  String autoPopulated = sampleInstrSb.toString();
                  String userFormed    = jobProdSample.getSampleInstructions();
                  if(userFormed == null || userFormed.trim().isEmpty()){
                	  if(autoPopulated != null){
                		  jobProdSample.setSampleInstructions(autoPopulated.trim() + System.getProperty("line.separator")+ Constants.sampleInstrSeparator);
                	  }
                  } else {
                	  String sampleInstrs = "";
                	  if(autoPopulated == null || autoPopulated.trim().isEmpty()){
                		  sampleInstrs = userFormed;
                	  } else {
                		  if(userFormed.indexOf(Constants.sampleInstrSeparator)!= -1){
    	                	  String[] sampleInstr = userFormed.split(Constants.sampleInstrSeparator); 
    	                	  String autoPopData = autoPopulated;
    	                	  String userFormedData = "";
    	                	  if(sampleInstr.length == 2){
    	                		  userFormedData = sampleInstr[1];
    	                	  }
    	                	  sampleInstrs = autoPopData.trim()+ System.getProperty("line.separator") + Constants.sampleInstrSeparator + userFormedData;
                		  } else {
                			  sampleInstrs = autoPopulated.trim() + Constants.sampleInstrSeparator + System.getProperty("line.separator") + userFormed;
                		  }
                	  }
                	  jobProdSample.setSampleInstructions(sampleInstrs);
                  }
                  /* For iTrack #134877-END */
                  
                  //jobProdSampleSet.add(jobProdSample);
                }
                //jobProd.setJobProdSamples(jobProdSampleSet);
              }
              //jobProdSet.add(jobProd);
            }
            //jobVessel.setJobProds(jobProdSet);
          }
          //jobVesselSet.add(jobVessel);
        }
       // addJobOrder.getJobOrder().setJobVessels(jobVesselSet);
        }

        //Save updated jobOrder object
        addJobOrder.getJobOrder().setVesselNames(vesselNames);
        addJobOrder.getJobOrder().setProductNames(productNames);

        //For breadcrumbs
    if((addJobOrder.getJobOrder().getPageNumber() == null) || (addJobOrder.getJobOrder().getPageNumber().intValue() < 2))
      jobOrder.setPageNumber(Integer.valueOf(2));

    	boolean validate = CommonUtil.validateSamplesData(addJobOrder.getAddJobVessels());
    	if(validate) {
    		errors.reject("sample.data.validate.error");
            return showForm(request, response, errors);
    	}
        jobOrder = jobService.updateJobOrder(addJobOrder.getJobOrder());
        //START: Issue # 75052 
        if(null != addJobOrder.getUiRevisionNote()
        		&& !"".equals(addJobOrder.getUiRevisionNote().trim())){
        	
        	if(addJobOrder.getUiRevisionNote().trim().length() > 254){
        		errors.reject("revisionNotes.maxLength.validate.error",new Object[] {"254"},null);
    	        return showForm(request, response, errors);
        	}        	
        	
        	RevisionNotes addRevisionNotes = new RevisionNotes();
        	addRevisionNotes.setAddedByUserName(SecurityUtil.getUser().getLoginName());
        	RevisionNoteId rvsnId = new RevisionNoteId();
        	rvsnId.setJobNumber(addJobOrder.getJobOrder().getJobNumber());
        	addRevisionNotes.setRevisionNoteId(rvsnId);
        	addRevisionNotes.setRevisionNote(addJobOrder.getUiRevisionNote());
        	jobService.saveRevisionNotes(addRevisionNotes);
        	addRevisionNotes = null;
        }
        //END: Issue # 75052 
      } catch(Throwable t)
      {
          t.printStackTrace();
          errors.reject("generic.error", new Object[] {t.getMessage()}, null);
          return showForm(request, response, errors);
      }

      String viewName = "edit_job_operational_info_insp.htm";
      /*String viewName = "";
      if(jobOrder.getJobType().trim().equals(Constants.ANALYTICAL_SERVICE_JOBTYPE))
        viewName = "edit_job_entry_analytical_service.htm";
      else if(jobOrder.getJobType().trim().equals(Constants.OUTSOURCING_JOBTYPE))
        viewName = "edit_job_entry_outsourcing.htm";
      else if(jobOrder.getJobType().trim().equals(Constants.INSPECTION_JOBTYPE))
        viewName = "edit_job_entry_insp.htm";
      else if(jobOrder.getJobType().trim().equals(Constants.MARINE_JOBTYPE))
        viewName = "edit_job_entry_marine.htm";*/

      //  Start Template
      if ((templateFlag != null) && "yes".equals(templateFlag)){
      addJobOrder.setTemplateFlag("none");
      return new ModelAndView(new RedirectView("create_job_operational_info_insp.htm?jobNumber="+jobNumber+"&templateName="+jobOrder.getJobNumber()+"&searchTemplate="+tmpName));
    }
      // End Template

      String nextFlag = addJobOrder.getNextPageFlag();
    if(nextFlag != null && nextFlag.trim().equals("1"))
      {
    	System.out.println("XXXXXXX SAVE AND GO JOBINSTRUCTIONS-XXXXXXX");
    	boolean validate = CommonUtil.validateSamplesData(addJobOrder.getAddJobVessels());
    	if(validate) {
    		errors.reject("sample.data.validate.error");
            return showForm(request, response, errors);
    	}
    	else {
    		 return new ModelAndView(new RedirectView("edit_job_select_charges.htm"), "jobNumber", jobOrder.getJobNumber());
    	}
       // return new ModelAndView(new RedirectView("edit_job_select_charges.htm"), "jobNumber", jobOrder.getJobNumber());

      }

      return new ModelAndView(new RedirectView(viewName), "jobNumber", jobOrder.getJobNumber());

  }

  protected boolean suppressValidation(HttpServletRequest request,Object command)
  {
    String refreshing = request.getParameter("refreshing");
    if((refreshing != null) && "true".equals(refreshing))
    {
      return true;
    }
 // For itrack issue 24329
    String portLocationFlag = request.getParameter("portLocationFlag");
    if(portLocationFlag != null  && "portlocation".equals(portLocationFlag))
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
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    dateFormat.setLenient(false);
    binder.registerCustomEditor(java.util.Date.class, null, new CustomDateEditor(dateFormat, true));

    NumberFormat nf = NumberFormat.getInstance();
    CustomPrimitiveNumberEditor longEditor = new CustomPrimitiveNumberEditor(Long.class, nf, Long.valueOf(0));
    binder.registerCustomEditor(Long.class, null, longEditor);
    binder.registerCustomEditor(Long.class, null, longEditor);
  }


  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
   */
  protected Object formBackingObject(
    HttpServletRequest request
  ) throws Exception
  {

    String jobNumber = request.getParameter("jobNumber");
    String tempNm = request.getParameter("templateName"); //Template
    String searchTemp = request.getParameter("searchTemplate"); //Template
    if (searchTemp == null) searchTemp = ""; // Template

    AddJobOrder addJobOrder = new AddJobOrder();
    JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
    UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
    try {
  // Start Template
  if ((tempNm != null) && (!"".equals(tempNm))) {
    JobOrder jobOrd = jobService.getJobOrderByJobNumberWithDetail(jobNumber);
      createJobOrderFromTemplate(request, jobService, addJobOrder, jobOrd, tempNm);
      addJobOrder.getJobOrder().setJobNumber(jobNumber);
    //addJobOrder.getJobOrder().setTempName("");
    addJobOrder.getJobOrder().setIsTemplate(false);
    addJobOrder.getJobOrder().setIsDefault(false);
    addJobOrder.getJobOrder().setFirstName("");
    addJobOrder.getJobOrder().setLastName("");
    //addJobOrder.setSearchTemplate(searchTemp);

    } else {

    createJobOrderWithoutTemplate(jobService,addJobOrder,jobNumber);
  }
  // End Template

  //Set the default LIMS Branch Id
  BranchCode branchCode = userService.getBranchCodeByBranchCode(addJobOrder.getJobOrder().getBranchName());
  if(branchCode != null)
    addJobOrder.getJobOrder().setLimsBranchId(branchCode.getLabCode());
  /* For Itrack Issue # 117812 - START 10 Jun 2009*/
     if(addJobOrder.getJobOrder().getLimsBranchId() != null && !(addJobOrder.getJobOrder().getLimsBranchId().trim().equals("")))
     {
            try {
    	      // List branches = userService.getLabBranchesByBUAndBranchName(addJobOrder.getJobOrder().getBuName(),addJobOrder.getJobOrder().getLimsBranchId());
      	  List branches = userService.getLabBranchesByBranchName(addJobOrder.getJobOrder().getLimsBranchId());
      	  boolean isValidLIMSBranch = false;
    	      if(branches != null){
    	          for (int i = 0; i< branches.size(); i++){
    	        	  Branch branchObj = (Branch) branches.get(i);
    	        	  if(branchObj.getLabBranch().getName().trim().equals(addJobOrder.getJobOrder().getLimsBranchId())){
    	        		  isValidLIMSBranch = true;
    	        		  break;
    	        	  }
    	       	  }
    	      } else {
    	    	 addJobOrder.getJobOrder().setLimsBranchId("");
    	      }
    	      if(!isValidLIMSBranch){
    	    	addJobOrder.getJobOrder().setLimsBranchId("");
    	      }
          } catch(Exception ex){}
     }
   /* For Itrack Issue # 117812 - END 10 Jun 2009*/

    if(addJobOrder.getAddJobVessels() == null || addJobOrder.getAddJobVessels().length <=0)
    {
      addJobOrder.setAddJobVessels(addJobVessel(addJobOrder.getAddJobVessels(),null,addJobOrder.getAddJobContracts()));
      AddJobVessel addJobVessel = addJobOrder.getAddJobVessels()[0];
      addJobVessel.setAddJobProds(addJobProducts(addJobVessel.getAddJobProds(),null,addJobOrder.getAddJobContracts()));
      addJobOrder.getAddJobVessels()[0] = addJobVessel;
    }

    addJobOrder.setJobNumber(jobNumber); //Template
    addJobOrder.getJobOrder().setJobNumber(jobNumber); //Template

    //Check for SAM/LIMS branch integration & set appropriate flag
    BranchInt branchInt = userService.getBranchIntByCode(addJobOrder.getJobOrder().getBranchName());
    if(branchInt != null)
    {
      if(branchInt.getSamInd())
        addJobOrder.setSamBranchFlag(true);
      if(branchInt.getLimsInd())
        addJobOrder.setLimsBranchFlag(true);
    }
  }
  catch(Throwable t)
  {
      t.printStackTrace();
      throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
  }
    return addJobOrder;
  }

  // Start Template
  private AddJobOrder createJobOrderFromTemplate(HttpServletRequest request,JobService jobService, AddJobOrder addJobOrder,JobOrder jobOrd_Org, String jobNumber) {


      DropdownService dropdownService = (DropdownService)ServiceLocator.getInstance().getBean("dropdownService");

      JobOrder jobOrder = jobService.getJobOrderByJobNumberWithDetail(jobNumber);

      if(jobOrder == null)
        jobOrder = new JobOrder();


        String jobType = request.getParameter("jobType");
        if((jobType != null) && !"".equals(jobType.trim()))
        {
          jobOrd_Org.setJobType(jobType);
        }
        jobOrd_Org.setInspInstructions(jobOrder.getInspInstructions());
        jobOrd_Org.setSampInstructions(jobOrder.getSampInstructions());
        jobOrd_Org.setRetainPeriod(jobOrder.getRetainPeriod());
        jobOrd_Org.setLabAnalysis(jobOrder.getLabAnalysis());
        jobOrd_Org.setOtApproved(jobOrder.getOtApproved());
        jobOrd_Org.setOtApprovedby(jobOrder.getOtApprovedby());
        jobOrd_Org.setLabInstructions(jobOrder.getLabInstructions());
        jobOrd_Org.setShipInstructions(jobOrder.getShipInstructions());
        jobOrd_Org.setReptInstructions(jobOrder.getReptInstructions());
        jobOrd_Org.setBillInstructions(jobOrder.getBillInstructions());
        jobOrd_Org.setOtherInstructions(jobOrder.getOtherInstructions());
        jobOrd_Org.setTempName(jobOrder.getTempName());

        addJobOrder.setJobOrder(jobOrd_Org);
        addJobOrder.setSearchTemplate(jobOrder.getTempName());
        boolean viewOnly = false;
      //Dummy contract codes for operational info testing; to be removed later
      //Issue 105711 start
      // commented below line
      // Set JobContractsSet = jobOrd_Org.getJobContracts();
      List JobContractsSet = new ArrayList(jobOrd_Org.getJobContracts());
	  JobUtil.sortJobContractsById(JobContractsSet);
	  //Issue 105711 End

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
            JobContract jobContract = new JobContract();
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
               /* if(jobProd.getJobProdContracts() != null && jobProd.getJobProdContracts().size() > 0)
                {
                  Iterator iter = jobProd.getJobProdContracts().iterator();
                  AddJobContract[] applicableContracts = new AddJobContract[jobProd.getJobProdContracts().size()];
                  int count = 0;
                  while(iter.hasNext())
                  {
                    //AddJobContract applicableAddJobContract = new AddJobContract();
                    JobContract jobContract = (JobContract) iter.next();
                    AddJobContract applicableAddJobContract = JobUtil.getAddJobContractByJobContractId(addJobOrder, jobContract.getId());
                    applicableAddJobContract.setJobContract(jobContract);

                    applicableContracts[count] = applicableAddJobContract;
                    count++;
                  }
                  addJobProd.setApplicableContracts(applicableContracts);
                }*/


                if(addJobOrder.getAddJobContracts() != null)
                  addJobProd.setApplicableContracts(addJobOrder.getAddJobContracts());

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
                      /* For iTrack#134877-START */
                      String sampleInstrs = jobProdSample.getSampleInstructions();
                      if(sampleInstrs != null && !sampleInstrs.trim().isEmpty()){
                    	  if(sampleInstrs.indexOf(I18nUtil.getString(I18nUtil.getLocale(request), "Following_tests_added_to_sample"))!= -1 
                				  || sampleInstrs.indexOf(I18nUtil.getString(I18nUtil.getLocale(request), "Following_slates_added_to_sample"))!= -1
                				  || sampleInstrs.indexOf(I18nUtil.getString(I18nUtil.getLocale(request), "Following_manual_tests_added_to_sample"))!= -1){
                    		  jobProdSample.setSampleInstructions(sampleInstrs.trim() + System.getProperty("line.separator")+ Constants.sampleInstrSeparator);
                		  }
                      } 
                      /* For iTrack#134877-END */
                      addJobProdSample.setJobProdSample(jobProdSample);

                      //Load Tests related to each sample location
                      //List jobTestsList = jobService.getJobTestsByJobNumberVesselRowProductAndSampleLoc(jobNumber,jobVessel.getJobVesselId().getLinkedVslRow(),jobProd.getJobProdId().getProdSeqNum(),jobProdSample.getJobProdSampleId().getSampSeqId());
                      List jobTestsList = new ArrayList(jobProdSample.getJobTests());

                      if(jobTestsList != null && jobTestsList.size() > 0)
                        {
                        JobTest[] jobTests = new JobTest[jobTestsList.size()];
                          for(int k=0;k<jobTestsList.size();k++)
                          {
                            //Copy JobTest from template to order
                            JobTest jobTest = new JobTest();
                            JobTest templateJobTest = (JobTest)jobTestsList.get(k);
                            jobTest.setContractRefNo(templateJobTest.getContractRefNo());
                            jobTest.setDiscount(templateJobTest.getDiscount());
                            jobTest.setJobProdSample(templateJobTest.getJobProdSample());
                            System.out.println("jobnumber in jobtest "+jobTest.getJobProdSample().getJobProdSampleId().getJobNumber());
                            jobTest.setLineDescription(templateJobTest.getLineDescription());
                            jobTest.setOt(templateJobTest.getOt());
                            jobTest.setQuantity(templateJobTest.getQuantity());
                            jobTest.setTest(templateJobTest.getTest());
                            jobTest.setTotalPrice(templateJobTest.getTotalPrice());
                            jobTest.setUnitPrice(templateJobTest.getUnitPrice());
                            jobTests[k] = jobTest;
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
                            //Copy JobSlate from template to order
                            JobSlate jobSlate = new JobSlate();
                            JobSlate templateJobSlate = (JobSlate)jobSlatesList.get(k);
                            jobSlate.setContractRefNo(templateJobSlate.getContractRefNo());
                            jobSlate.setDiscount(templateJobSlate.getDiscount());
                            jobSlate.setJobProdSample(templateJobSlate.getJobProdSample());
                            jobSlate.setLineDescription(templateJobSlate.getLineDescription());
                            jobSlate.setOt(templateJobSlate.getOt());
                            jobSlate.setQuantity(templateJobSlate.getQuantity());
                            jobSlate.setSlate(templateJobSlate.getSlate());
                            jobSlate.setTotalPrice(templateJobSlate.getTotalPrice());
                            jobSlate.setUnitPrice(templateJobSlate.getUnitPrice());

                            jobSlates[k] = jobSlate;
                          }
                          addJobProdSample.setJobSlates(jobSlates);
                        }
                      samplePos = jobProdSample.getSortOrder() - 1;
                      addJobProdSamples[samplePos] = addJobProdSample;
                    }
                    addJobProd.setAddJobProdSamples(addJobProdSamples);

                  }


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

    return addJobOrder;
  }


  // Start Template
  private AddJobOrder createJobOrderWithoutTemplate(JobService jobService, AddJobOrder addJobOrder, String jobNumber) {


      JobOrder jobOrder = jobService.getJobOrderByJobNumberWithDetail(jobNumber);

      if(jobOrder == null)
        jobOrder = new JobOrder();


      addJobOrder.setJobOrder(jobOrder);
      boolean viewOnly = false;
      //Dummy contract codes for operational info testing; to be removed later

      //Issue 105711 start
     // commented below line
     // Set JobContractsSet = jobOrd_Org.getJobContracts();
       List JobContractsSet = new ArrayList(jobOrder.getJobContracts());
 	  JobUtil.sortJobContractsById(JobContractsSet);
 	 //Issue 105711 End

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
            JobContract jobContract = (JobContract)iter.next();
            jobContractCodes[counter] = jobContract.getContractCode() + " " + (counter+1);
            addJobContract.setJobContract(jobContract);
            addJobContract.setJobContractIndex(counter+1);
            addJobContracts[counter] = addJobContract;
            if(!viewOnly && jobContract.getJobContractStatus().trim().equals(Constants.JOBCON_CREDITED_STATUS))
             viewOnly = true;
            counter++;
        }
        addJobOrder.setAddJobContracts(addJobContracts);
        addJobOrder.setViewOnly(viewOnly);
      }

    return addJobOrder;
  }
  // End Template

  private AddJobVessel[] addJobVessel(AddJobVessel[] addJobVessels, AddJobVessel addJobVesselToCopy,AddJobContract[] addJobContracts)
  {
    AddJobVessel addJobVessel = new AddJobVessel();

    JobVessel jobVessel = new JobVessel();
    JobVesselId jobVesselId = new JobVesselId();
    jobVessel.setJobVesselId(jobVesselId);

    if(addJobVesselToCopy != null)
    {
      jobVessel.setVesselName(addJobVesselToCopy.getJobVessel().getVesselName());
      jobVessel.setType(addJobVesselToCopy.getJobVessel().getType());
      jobVessel.getJobVesselId().setJobNumber(addJobVesselToCopy.getJobVessel().getJobVesselId().getJobNumber());
    }
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



         if(addJobVesselToCopy.getAddJobProds()[i].getAddJobProdSamples() != null && addJobVesselToCopy.getAddJobProds()[i].getAddJobProdSamples().length > 0)
         {
           for(int j=0;j<addJobVesselToCopy.getAddJobProds()[i].getAddJobProdSamples().length;j++)
           {
             AddJobProdSample addJobProdSampleToCopy = addJobVesselToCopy.getAddJobProds()[i].getAddJobProdSamples()[j];
             addJobProd.setAddJobProdSamples(addProdSamples(addJobProd.getAddJobProdSamples(),addJobProdSampleToCopy,null));
           }
         }
         //addJobProd.setAddJobProdSamples(addJobVesselToCopy.getAddJobProds()[i].getAddJobProdSamples());
          //commented regd issue 66400
          // addJobProd.setJobEvents(addJobVesselToCopy.getAddJobProds()[i].getJobEvents());
         //Added new code regd issue 66400
         if(addJobVesselToCopy.getAddJobProds()[i].getJobEvents() != null &&addJobVesselToCopy.getAddJobProds()[i].getJobEvents().length > 0)
         {
           for(int j=0;j<addJobVesselToCopy.getAddJobProds()[i].getJobEvents().length;j++)
           {
        	   JobEvent addJobProdEventToCopy = addJobVesselToCopy.getAddJobProds()[i].getJobEvents()[j];
        	   addJobProd.setJobEvents(addProdEvents(addJobProd.getJobEvents(),addJobProdEventToCopy,null));
        	// addJobProd.setJobEvents(addProdEvents(addJobVesselToCopy.getAddJobProds()[i].getJobEvents(),addJobProdEventToCopy,null,addJobProd.getJobProd()));
           }
         }
         //End

            if(addJobContracts != null && addJobContracts.length > 0)
            {
              /*String[] jobContractCodes = new String[addJobContracts.length];
              for(int j=0;j<addJobContracts.length;j++)
              {
                String jobContractCode = addJobContracts[j].getJobContract().getContractCode();
                jobContractCodes[j] = jobContractCode;
              }
              addJobProd.setJobContractCodes(jobContractCodes);*/
              addJobProd.setApplicableContracts(addJobContracts);
            }
         addJobProds[i] = addJobProd;

       }
       addJobVessel.setAddJobProds(addJobProds);
      // addJobVessel.setJobVessel(addJobVesselToCopy.getJobVessel().);
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
        /*String[] jobContractCodes = new String[addJobContracts.length];
        for(int i=0;i<addJobContracts.length;i++)
        {
          String jobContractCode = addJobContracts[i].getJobContract().getContractCode();
          jobContractCodes[i] = jobContractCode;
        }*/
        //addJobProd.setJobContractCodes(jobContractCodes);
        addJobProd.setApplicableContracts(addJobContracts);
      }
      if(addJobProdToCopy != null)

      {
             jobProd.setDestination(addJobProdToCopy.getJobProd().getDestination());
             jobProd.setGroup(addJobProdToCopy.getJobProd().getGroup());
             jobProd.setJobProductName(addJobProdToCopy.getJobProd().getJobProductName());
            // jobProd.getJobProdId().setJobProductName(addJobProdToCopy.getJobProd().getJobProdId().getJobProductName());
           jobProd.setProductQty(0);
           jobProd.setUom("");
           jobProd.setOption("");
           jobProd.setPlusMinus("");
           jobProd.setPlusMinusPct(0);
           jobProd.setDrafts("");
           jobProd.setTanks("");
           addJobProd.setJobProd(jobProd);
           addJobProd.setJobProdQtys(null);

           if(addJobProdToCopy.getAddJobProdSamples() != null && addJobProdToCopy.getAddJobProdSamples().length > 0)
           {
             for(int j=0;j<addJobProdToCopy.getAddJobProdSamples().length;j++)
             {
               AddJobProdSample addJobProdSampleToCopy = addJobProdToCopy.getAddJobProdSamples()[j];
               addJobProd.setAddJobProdSamples(addProdSamples(addJobProd.getAddJobProdSamples(),addJobProdSampleToCopy,null));
             }
           }
           //addJobProd.setAddJobProdSamples(addJobProdToCopy.getAddJobProdSamples());
           //         commented regd issue 66400
           //addJobProd.setJobEvents(addJobProdToCopy.getJobEvents());
           //Added new code regd issue 66400
           if(addJobProdToCopy.getJobEvents() != null &&addJobProdToCopy.getJobEvents().length > 0)
           {
             for(int j=0;j<addJobProdToCopy.getJobEvents().length;j++)
             {
          	 JobEvent addJobProdEventToCopy = addJobProdToCopy.getJobEvents()[j];
          	 //addJobProd.setJobEvents(addProdEvents(addJobProdToCopy.getJobEvents(),addJobProdEventToCopy,null,addJobProd.getJobProd()));
          	 addJobProd.setJobEvents(addProdEvents(addJobProd.getJobEvents(),addJobProdEventToCopy,null));
             }
           }
           //End


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
// newly added code regd issue 66400

  private JobEvent[] addProdEvents(JobEvent[] addJobProdEvents,JobEvent addJobProdEventToCopy,String jobType)
  {

	JobEvent jobEvent = new JobEvent();
	JobEventId jobEventId = new JobEventId();

	if(addJobProdEventToCopy != null)
    {
    	jobEvent.setJobEventId(jobEventId);
    	jobEvent.setEvent(addJobProdEventToCopy.getEvent());
    	jobEvent.setEventInstructions(addJobProdEventToCopy.getEventInstructions());
    	jobEvent.setJobProd(addJobProdEventToCopy.getJobProd());
    	jobEvent.setSortOrder(addJobProdEventToCopy.getSortOrder());
    }
    JobEvent[] results = null;
    if(addJobProdEvents == null)
    {
      results = new JobEvent[1];
      jobEvent.setSortOrder(1);
    }
    else
    {
      results = new JobEvent[addJobProdEvents.length+1];
      jobEvent.setSortOrder(addJobProdEvents.length+1);
    }
    if(addJobProdEvents != null)
    {
      for(int i=0; i<addJobProdEvents.length; i++)
      {
        results[i] = addJobProdEvents[i];
      }
    }
    results[results.length - 1] = jobEvent;

    return results;
  }
  //End
  private AddJobProdSample[] addProdSamples(AddJobProdSample[] addJobProdSamples,AddJobProdSample addJobProdSampleToCopy,String jobType)
  {
    AddJobProdSample addJobProdSample = new AddJobProdSample();
    JobProdSample jobProdSample = new JobProdSample();
    JobProdSampleId jobProdSampleId = new JobProdSampleId();
    jobProdSample.setJobProdSampleId(jobProdSampleId);
    jobProdSample.setSampleNumber(1);
    jobProdSample.setPriority(Constants.STANDARD);
    DropdownService dropdownService = (DropdownService)ServiceLocator.getInstance().getBean("dropdownService");

    if(jobType != null && (!jobType.trim().equals("")))
    {
      if(jobType.trim().equals(Constants.ANALYTICAL_SERVICE_JOBTYPE))
        jobProdSample.setJobSampleLocation((dropdownService.getDropdownByDropdownCodeAndType(Constants.ANALYTICAL_SERVICE_JOBTYPE, Constants.SAMPLE_LOCATION)).getDropDownId().getFieldName());
    }
    addJobProdSample.setJobProdSample(jobProdSample);


    if(addJobProdSampleToCopy != null)
    {
      JobProdSample jobProdSampleToCopy = addJobProdSampleToCopy.getJobProdSample();
      jobProdSample.setContainerType(jobProdSampleToCopy.getContainerType());
      jobProdSample.setJobProd(jobProdSampleToCopy.getJobProd());
      jobProdSample.setJobSampleLocation(jobProdSampleToCopy.getJobSampleLocation());
      jobProdSample.setRetainTested(jobProdSampleToCopy.getRetainTested());
      jobProdSample.setSampleInstructions(jobProdSampleToCopy.getSampleInstructions());
      jobProdSample.setSampleNumber(jobProdSampleToCopy.getSampleNumber());
      jobProdSample.setSampleTiming(jobProdSampleToCopy.getSampleTiming());
      jobProdSample.setSampleType(jobProdSampleToCopy.getSampleType());
      jobProdSample.setSampleVolume(jobProdSampleToCopy.getSampleVolume());
      jobProdSample.setTankNumber(jobProdSampleToCopy.getTankNumber());


      addJobProdSample.setDisplayStatus(addJobProdSampleToCopy.getDisplayStatus());
      addJobProdSample.setJobManualTests(addJobProdSampleToCopy.getJobManualTests());

      JobTest[] jobTests = addJobProdSampleToCopy.getJobTests();
      if(jobTests != null && jobTests.length > 0)
      {
        for(int i=0;i<jobTests.length;i++)
        {
          Test test = jobTests[i].getTest();
          addJobProdSample.setJobTests(addJobTest(addJobProdSample.getJobTests(),test,jobTests[i],addJobProdSample.getJobProdSample()));
        }
      }
      JobSlate[] jobSlates = addJobProdSampleToCopy.getJobSlates();
      if(jobSlates != null && jobSlates.length > 0)
      {
        for(int i=0;i<jobSlates.length;i++)
        {
          Slate slate = jobSlates[i].getSlate();
          addJobProdSample.setJobSlates(addJobSlate(addJobProdSample.getJobSlates(),slate,jobSlates[i],addJobProdSample.getJobProdSample()));
        }
      }
      //addJobProdSample.setJobSlates(addJobProdSampleToCopy.getJobSlates());
      //addJobProdSample.setJobTests(addJobProdSampleToCopy.getJobTests());
      addJobProdSample.setJobProdSample(jobProdSample);

    }
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
  private JobProdQty[] addProdQtys(JobProdQty[] jobProdQtys,JobProd jobProd)
  {
    JobProdQty jobProdQty = new JobProdQty();
    double sumQty = 0;
    jobProdQty.setUom(jobProd.getUom());
    jobProdQty.setOption(jobProd.getOption());
    jobProdQty.setPlusMinus(jobProd.getPlusMinus());
    jobProdQty.setPlusMinusPct(jobProd.getPlusMinusPct());

    JobProdQty[] results = null;
    if(jobProdQtys == null)
    {
      results = new JobProdQty[1];
      jobProdQty.setProductQty(jobProd.getProductQty());

    }
    else results = new JobProdQty[jobProdQtys.length + 1];

    if(jobProdQtys != null)
    {
      for(int i=0; i<jobProdQtys.length; i++)
      {
        results[i] = jobProdQtys[i];
        sumQty = sumQty + jobProdQtys[i].getProductQty();
      }
    }
    results[results.length - 1] = jobProdQty;
    sumQty = sumQty + jobProdQty.getProductQty();

    jobProd.setProductQty(NumberUtil.roundHalfUp(sumQty,2));

    return results;
  }
  private JobProdQty[] updateProdQtys(JobProdQty[] jobProdQtys,JobProd jobProd)
  {
    double sumQty = 0;


    JobProdQty[] results = null;
    if(jobProdQtys != null)
    {
    results = new JobProdQty[jobProdQtys.length];
      for(int i=0; i<jobProdQtys.length; i++)
      {
        results[i] = jobProdQtys[i];
        sumQty = sumQty + jobProdQtys[i].getProductQty();
      }
    }
    jobProd.setProductQty(NumberUtil.roundHalfUp(sumQty,2));

    return results;
  }

  private JobProdQty[] deleteProdQtys(JobProdQty[] jobProdQtys, int index,JobProd jobProd)
  {
    double minusQty = 0;
    if(jobProdQtys == null) return null;
    if(jobProdQtys.length <= 0) return jobProdQtys;

    JobProdQty[] results = new JobProdQty[jobProdQtys.length - 1];

    int count = 0;
    for(int i=0; i<jobProdQtys.length; i++)
    {
      if(i == index)
    {
        JobProdQty jobProdQtyToDelete = jobProdQtys[i];
        minusQty = jobProdQtyToDelete.getProductQty();
        continue;
    }
      results[count] = jobProdQtys[i];

      count ++;
    }
    jobProd.setProductQty(NumberUtil.roundHalfUp((jobProd.getProductQty() - minusQty),2));

    return results;
  }


  private JobTest[] addJobTest(JobTest[] jobTests, Test test,JobTest jobTestToCopy,JobProdSample jobProdSample)
  {
    JobTest jobTest = new JobTest();
    jobTest.setTest(test);

   /* if(jobProdSample.getPriority() != null && (!jobProdSample.getPriority().trim().equals("")))
    {
      if(jobProdSample.getPriority().trim().equals(Constants.OVERTIME) || jobProdSample.getPriority().trim().equals(Constants.RUSH))
        jobTest.setOt(true);
      else
        jobTest.setOt(false);
    }
    else
      jobTest.setOt(false);*/
    //Setting default testOt value according to selected priority
    if(jobProdSample.getPriority().trim().equals(Constants.OVERTIME)|| jobProdSample.getPriority().trim().equals(Constants.RUSH))
        jobTest.setOt(true);
    else
        jobTest.setOt(false);
    //End
    jobTest.setQuantity(Constants.DEFAULT_TEST_SLATE_QTY);

    if(jobTestToCopy != null)
    {
      jobTest.setOt(jobTestToCopy.getOt());
      jobTest.setQuantity(jobTestToCopy.getQuantity());
    }
    JobTest[] results = null;
    if(jobTests == null) results = new JobTest[1];
    else results = new JobTest[jobTests.length + 1];

    if(jobTests != null)
    {
      for(int i=0; i<jobTests.length; i++)
      {
    	  //Added on 03/03/09
    	  JobTest jobTst = jobTests[i];
	      if(jobProdSample.getPriority().trim().equals(Constants.OVERTIME)|| jobProdSample.getPriority().trim().equals(Constants.RUSH)){
	    	  jobTst.setOt(true);
	    	  results[i] = jobTst;
	      } else {
	    	  jobTst.setOt(false);
	    	  results[i] = jobTst;
	      } //end
    	  //results[i] = jobTests[i];
      }
    }
    results[results.length - 1] = jobTest;

    return results;
  }

//101771 START
  
  private JobManualTest[] addJobManualTest(JobManualTest[] jobManualTests, String id,String description,Double quantity, Double totalPrice,JobManualTest jobTestToCopy,JobProdSample jobProdSample)
  {
	  JobManualTest jobManualTest = new JobManualTest();
	  jobManualTest.setTestId(id);
	  jobManualTest.setTestDescription(description);
    if(jobProdSample.getPriority() != null && (!jobProdSample.getPriority().trim().equals("")))
    {
      if(jobProdSample.getPriority().trim().equals(Constants.OVERTIME) || jobProdSample.getPriority().trim().equals(Constants.RUSH))
    	  jobManualTest.setOt(true);
      else
    	  jobManualTest.setOt(false);
    }
    else
    	jobManualTest.setOt(false);
    //Setting default testOt value according to selected priority
    if(jobProdSample.getPriority().trim().equals(Constants.OVERTIME)|| jobProdSample.getPriority().trim().equals(Constants.RUSH))
    	jobManualTest.setOt(true);
    else
    	jobManualTest.setOt(false);
    //End
    jobManualTest.setQuantity(quantity);
    jobManualTest.setTotalPrice(totalPrice);

    if(jobTestToCopy != null)
    {
    	jobManualTest.setOt(jobTestToCopy.getOt());
    	jobManualTest.setQuantity(jobTestToCopy.getQuantity());
    }
    JobManualTest[] results = null;
    if(jobManualTests == null) results = new JobManualTest[1];
    else results = new JobManualTest[jobManualTests.length + 1];

    if(jobManualTests != null)
    {
      for(int i=0; i<jobManualTests.length; i++)
      {
    	  //Added on 03/03/09
    	  JobManualTest jobTst = jobManualTests[i];
	      if(jobProdSample.getPriority().trim().equals(Constants.OVERTIME)|| jobProdSample.getPriority().trim().equals(Constants.RUSH)){
	    	  jobTst.setOt(true);
	    	  results[i] = jobTst;
	      } else {
	    	  jobTst.setOt(false);
	    	  results[i] = jobTst;
	      } //end
    	  //results[i] = jobTests[i];
      }
    }
    results[results.length - 1] = jobManualTest;

    return results;
  }
  
  private JobManualTest[] deleteJobManualTest(JobManualTest[] jobManualTests, int[] indices)
  {
    if(jobManualTests == null) return null;
    if(jobManualTests.length <= 0) return jobManualTests;

    JobManualTest[] results = new JobManualTest[jobManualTests.length - indices.length];

    int count = 0;

    for(int i=0; i<jobManualTests.length; i++)
    {
      boolean testDelete = false;
      for(int j=0;j<indices.length;j++)
      {
        if(i == indices[j])
        {
          testDelete = true;
        }
      }
      if(testDelete)
        continue;
      else
      {
          results[count] = jobManualTests[i];
          count ++;
      }


    }

    return results;
  }
  
  
  
  
  
  
  //101771 END
 
  
  private JobTest[] deleteJobTest(JobTest[] jobTests, int[] indices)
  {
    if(jobTests == null) return null;
    if(jobTests.length <= 0) return jobTests;

    JobTest[] results = new JobTest[jobTests.length - indices.length];

    int count = 0;

    for(int i=0; i<jobTests.length; i++)
    {
      boolean testDelete = false;
      for(int j=0;j<indices.length;j++)
      {
        if(i == indices[j])
        {
          testDelete = true;
        }
      }
      if(testDelete)
        continue;
      else
      {
          results[count] = jobTests[i];
          count ++;
      }


    }

    return results;
  }
  private JobSlate[] addJobSlate(JobSlate[] jobSlates, Slate slate,JobSlate jobSlateToCopy,JobProdSample jobProdSample)
  {
    JobSlate jobSlate = new JobSlate();
    jobSlate.setSlate(slate);

    /*if(jobProdSample.getPriority() != null && (!jobProdSample.getPriority().trim().equals("")))
    {
      if(jobProdSample.getPriority().trim().equals(Constants.OVERTIME) || jobProdSample.getPriority().trim().equals(Constants.RUSH))
    	  jobSlate.setOt(true);
      else
    	  jobSlate.setOt(false);
    }
    else
    	jobSlate.setOt(false);*/
    //Setting default testOt value according to selected priority
    if(jobProdSample.getPriority().trim().equals(Constants.OVERTIME)|| jobProdSample.getPriority().trim().equals(Constants.RUSH))
    	jobSlate.setOt(true);
    else
    	jobSlate.setOt(false);
    //End
    jobSlate.setQuantity(Constants.DEFAULT_TEST_SLATE_QTY);

    if(jobSlateToCopy != null)
    {
      jobSlate.setOt(jobSlateToCopy.getOt());
      jobSlate.setQuantity(jobSlateToCopy.getQuantity());
    }

    JobSlate[] results = null;
    if(jobSlates == null) results = new JobSlate[1];
    else results = new JobSlate[jobSlates.length + 1];

    if(jobSlates != null)
    {
      for(int i=0; i<jobSlates.length; i++)
      {
    	  //Added on 03/03/09
    	  JobSlate jobSlt = jobSlates[i];
	      if(jobProdSample.getPriority().trim().equals(Constants.OVERTIME)|| jobProdSample.getPriority().trim().equals(Constants.RUSH)){
	    	  jobSlt.setOt(true);
	    	  results[i] = jobSlt;
	      } else {
	    	  jobSlt.setOt(false);
	    	  results[i] = jobSlt;
	      } //end
       // results[i] = jobSlates[i];
      }
    }
    results[results.length - 1] = jobSlate;

    return results;
  }

  private JobSlate[] deleteJobSlate(JobSlate[] jobSlates, int[] indices)
  {
    if(jobSlates == null) return null;
    if(jobSlates.length <= 0) return jobSlates;

    JobSlate[] results = new JobSlate[jobSlates.length - indices.length];

    int count = 0;
    for(int i=0; i<jobSlates.length; i++)
    {
      boolean slateDelete = false;
      for(int j=0;j<indices.length;j++)
      {
        if(i == indices[j])
        {
          slateDelete = true;
        }
      }
      if(slateDelete)
        continue;
      else
      {
          results[count] = jobSlates[i];
          count ++;
      }


    }

    return results;
  }

  private AddJobContract[] deleteApplicableContract(AddJobContract[] applicableContracts, AddJobContract jobContractToDelete)
  {
      if(applicableContracts == null) return null;
      if(applicableContracts.length <= 0) return applicableContracts;

      AddJobContract[] results = null;
      boolean contractExists = false;


      for(int i=0; i<applicableContracts.length; i++)
      {
        if(!contractExists && (applicableContracts[i].getJobContract().getId() == jobContractToDelete.getJobContract().getId()))
          contractExists = true;
      }

      if(contractExists)
      {
          results = new AddJobContract[applicableContracts.length - 1];

            int count = 0;
            for(int i=0; i<applicableContracts.length; i++)
            {
              if(applicableContracts[i].getJobContract().getId() ==  jobContractToDelete.getJobContract().getId()) continue;
              results[count] = applicableContracts[i];

              count ++;
            }
      }
      else
        return applicableContracts;



      return results;
  }

  private AddJobContract[] addApplicableContract(AddJobContract[] applicableContracts, AddJobContract jobContractToAdd)
  {


    AddJobContract[] results = null;
    boolean contractExists = false;

    if(applicableContracts == null) results = new AddJobContract[1];
    else
    {
      if(applicableContracts.length >0)
      {
        for(int i=0;i<applicableContracts.length;i++)
        {
          if(applicableContracts[i].getJobContract().getId()==jobContractToAdd.getJobContract().getId())
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
      results = new AddJobContract[applicableContracts.length + 1];

      if(applicableContracts != null)
      {
        for(int i=0; i<applicableContracts.length; i++)
        {
          results[i] = applicableContracts[i];
        }
      }
      results[results.length - 1] = jobContractToAdd;
      }
      return results;

  }
  }

  AddJobContract[] addJobContract(AddJobContract[] addJobContracts,AddJobContract addJobContractToAdd)
  {
    boolean contractExists = false;
    AddJobContract[] results = null;
    if(addJobContracts == null)
    {
      results = new AddJobContract[1];
      results[0] = addJobContractToAdd;
      return results;
    }

    for(int i=0;i<addJobContracts.length;i++)
    {
      if(addJobContracts[i].getJobContract().getId()==addJobContractToAdd.getJobContract().getId())
      {
        contractExists = true;
          break;
      }
    }
    if(contractExists)
      return addJobContracts;
    else
    {
      results = new AddJobContract[addJobContracts.length + 1];
      for(int i=0;i<addJobContracts.length;i++)
      {
        results[i] = addJobContracts[i];
      }
      results[results.length - 1] = addJobContractToAdd;

        return results;
    }

  }
  AddJobContract[] deleteJobContract(AddJobContract[] addJobContracts,AddJobContract addJobContractToDelete)
  {
    boolean contractExists = false;
    AddJobContract[] results = null;
    if(addJobContracts == null) return addJobContracts;
    for(int i=0;i<addJobContracts.length;i++)
    {
      if(addJobContracts[i].getJobContract().getId() == addJobContractToDelete.getJobContract().getId())
      {
        contractExists = true;
          break;
      }
    }
    if(!contractExists)
      return addJobContracts;
    else
    {
      results = new AddJobContract[addJobContracts.length - 1];
      int count = 0;
      for(int i=0;i<addJobContracts.length;i++)
      {
        if(addJobContracts[i].getJobContract().getId()==addJobContractToDelete.getJobContract().getId()) continue;
            results[count] = addJobContracts[i];

            count ++;
      }
        return results;
    }

  }

}
