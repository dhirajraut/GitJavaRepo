package com.intertek.web.controller.job;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.intertek.domain.AddJobContract;
import com.intertek.domain.JobSearch;
import com.intertek.entity.JobContract;
import com.intertek.entity.JobLog;
import com.intertek.entity.JobOrder;
import com.intertek.entity.JobSearchCriteria;
import com.intertek.entity.User;
import com.intertek.entity.UserSettings;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.CriteriaService;
import com.intertek.service.JobService;
import com.intertek.service.UserService;
import com.intertek.util.CommonUtil;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;
import com.intertek.util.JobUtil;
import com.intertek.util.SecurityUtil;
import com.intertek.web.controller.BaseSimpleFormController;

public class SearchJobResultsController extends BaseSimpleFormController
{
  public SearchJobResultsController() {
    super();
    setCommandClass(JobSearch.class);
    setSessionForm(true);
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
   */
  protected ModelAndView onSubmit(HttpServletRequest request,HttpServletResponse response, Object command, BindException errors) throws Exception
  {
    JobSearch jobSearch = (JobSearch)command;
    AddJobContract addJobContract=jobSearch.getAddJobContract();
    JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
    String refreshing = request.getParameter("refreshing");
    String sortFlag = request.getParameter("sortFlag");
    String sessionFlag = request.getParameter("sessionFlag");
	  String hrefValue = request.getParameter("hrefValue");
	  String hrefJobNumber = request.getParameter("hrefJobNumber");
	  String hrefForm = request.getParameter("hrefForm");
    String reqForm ="";
    
    // START : #119240
    // Setting Sort Order 
      String pageNumStr = request.getParameter("pageNumber");
      String pageSort = request.getParameter("checkPageSort");
      
      boolean changeSortOrder = false; 
      if(pageNumStr != null && Integer.valueOf(pageNumStr).intValue() == 1 && hrefJobNumber.equals("")){
      	if(sortFlag != null && !sortFlag.trim().equals("") && pageSort.equals("")){
      		changeSortOrder = true;
      	}else if(sortFlag == null || sortFlag.trim().equals("")){
      		jobSearch.setSortOrderFlag("");
      		jobSearch.setCurrentSortFlag("");
      		jobSearch.setPrevSortFlag("");
      	}
      }
		
	if(pageNumStr != null && Integer.valueOf(pageNumStr).intValue() == 1 && changeSortOrder){
    //if(pageNumStr != null && Integer.valueOf(pageNumStr).intValue() == 1){
		  if(sortFlag != null && !sortFlag.trim().equals("")){
			  jobSearch.setCurrentSortFlag(sortFlag);
			  if(jobSearch.getCurrentSortFlag()!= null && jobSearch.getCurrentSortFlag().trim().equals(jobSearch.getPrevSortFlag())){
			 	jobSearch.setSortOrderFlag("desc");
			  	jobSearch.setPrevSortFlag("");
			  } else {
				jobSearch.setPrevSortFlag(sortFlag);
			  	jobSearch.setSortOrderFlag("asc");
			  }
		  } else {
			  jobSearch.setSortOrderFlag("");
			  jobSearch.setCurrentSortFlag("");
			  jobSearch.setPrevSortFlag("");
		  }
	 }
	 //End
	// END : #119240
    
    List  jobs=new ArrayList();
    JobOrder jobOrder= new JobOrder();
    if((sessionFlag != null && sessionFlag.trim().equals("true"))&&
			  (hrefValue != null && !hrefValue.trim().equals(""))&&
			  (hrefJobNumber != null && !hrefJobNumber.trim().equals(""))&&
			  (hrefForm != null && !hrefForm.trim().equals(""))
			  )
	  {
		  if(jobSearch.getResults() != null)
		  {
		    List jobSearchresults=jobSearch.getResults();
		   
		    if(jobSearchresults != null && jobSearchresults.size() > 0)
		    {
		    	ArrayList al = new ArrayList();
			    for(int i=0;i<jobSearchresults.size();i++)
			    {
				    String jobNumber=((AddJobContract)jobSearchresults.get(i)).getJobOrder().getJobNumber();
				    al.add(jobNumber);
			    }
			   jobSearch.setJobNumbers(al);
		    }
		    jobSearch.getResults().clear();
			
	      }
		  request.getSession().setAttribute("MySearchJobOrder", jobSearch);
		  
		  String href=hrefValue+"?jobNumber="+hrefJobNumber+"&reqFrom="+hrefForm;
		  jobSearch.setHrefValue("");
		  jobSearch.setHrefJobNumber("");
		  jobSearch.setHrefForm("");
		  jobSearch.setSessionFlag("false");
		  return new ModelAndView(new RedirectView(href));
	  }
    if((refreshing != null) && "true".equals(refreshing))
    {
      try {
      jobs=jobSearch.getResults();
      for(int i=0;i<jobs.size();i++)
      {
        addJobContract=(AddJobContract)jobs.get(i);
/*        List<JobContractNote> jobContractNoteList = addJobContract.getJobContractNoteList();

        if(((AddJobContract)jobs.get(i)).getAppendNote()!=null && (!((AddJobContract)jobs.get(i)).getAppendNote().trim().equals("")))
        {
          jobContractNote=jobService.getJobContractNoteByContractIdandJobNumber(
            ((AddJobContract)jobs.get(i)).getJobContract().getJobNumber(),
            ((AddJobContract)jobs.get(i)).getJobContractNote().getContractCode(),
            ((AddJobContract)jobs.get(i)).getJobContractNote().getJobContractId()
          );
          String note=((AddJobContract)jobs.get(i)).getJobContractNote().getNote();
          if(note==null){note="";}
          String anote=((AddJobContract)jobs.get(i)).getAppendNote();
          String appendednote=anote+" "+ note;
          jobContractNote.setNote(appendednote);
          User addedBy=new User();
          Date date= new Date();
          jobContractNote.setAddedBy(SecurityUtil.getUser());
          jobContractNote.setAddedByUserName(SecurityUtil.getUser().getLoginName());
          jobContractNote.setDateTimeAdded(date);
          jobService.saveJobContractNote(jobContractNote);
        }
       
      
        if(((AddJobContract)jobs.get(i)).getAppendNote()!=null && (!((AddJobContract)jobs.get(i)).getAppendNote().trim().equals("")))
		{
          jobContractNoteList = jobService.getJobContractNotesByContractIdandJobNumber(((AddJobContract)jobs.get(i)).getJobOrder().getJobNumber(),((AddJobContract)jobs.get(i)).getJobContract().getContractCode(),((AddJobContract)jobs.get(i)).getJobContract().getId());
          for(JobContractNote jobContractNote : jobContractNoteList){
        		  
        		  jobContractNote.setAddedBy(SecurityUtil.getUser());
                  jobContractNote.setAddedByUserName(SecurityUtil.getUser().getLoginName());
                  jobContractNote.setDateTimeAdded(new Date());
                  
	              jobContractNote = jobService.addJobContractNote(jobContractNote);
        	  
          }
		}*/

        JobContract jobContract=addJobContract.getJobContract();
        jobOrder=jobContract.getJobOrder();
        JobLog jobLog=addJobContract.getJobLog();

        if(!((AddJobContract)jobs.get(i)).getInspectorNotifyTime().trim().equals(""))
        {
          jobLog.setInspectorNotifyTime(
            DateUtil.DateTime(
              ((AddJobContract)jobs.get(i)).getJobLog().getInspectorNotifyDt(),
              ((AddJobContract)jobs.get(i)).getInspectorNotifyTime()
            )
          );
        }

        if(!((AddJobContract)jobs.get(i)).getInspectorArriveTime().trim().equals(""))
        {
          jobLog.setInspectorArriveTime(
            DateUtil.DateTime(
              ((AddJobContract)jobs.get(i)).getJobLog().getInspectorArriveDt(),
              ((AddJobContract)jobs.get(i)).getInspectorArriveTime()
            )
          );
        }

        if(!((AddJobContract)jobs.get(i)).getArriveTime().trim().equals(""))
        {
          jobLog.setArriveTime(
            DateUtil.DateTime(
              ((AddJobContract)jobs.get(i)).getJobLog().getArriveDt(),
              ((AddJobContract)jobs.get(i)).getArriveTime()
            )
          );
        }

        if(!((AddJobContract)jobs.get(i)).getDockTime().trim().equals(""))
        {
          jobLog.setDockTime(
            DateUtil.DateTime(
              ((AddJobContract)jobs.get(i)).getJobLog().getDockDt(),
              ((AddJobContract)jobs.get(i)).getDockTime()
            )
          );
        }

        if(!((AddJobContract)jobs.get(i)).getHoseOnTime().trim().equals(""))
        {
          jobLog.setHoseOnTime(
            DateUtil.DateTime(
              ((AddJobContract)jobs.get(i)).getJobLog().getHoseOnDt(),
              ((AddJobContract)jobs.get(i)).getHoseOnTime()
            )
          );
        }

        if(!((AddJobContract)jobs.get(i)).getEstStartTime().trim().equals(""))
        {
          jobLog.setEstStartTime(
            DateUtil.DateTime(
              ((AddJobContract)jobs.get(i)).getJobLog().getEstStartDt(),
              ((AddJobContract)jobs.get(i)).getEstStartTime()
            )
          );
        }

        if(!((AddJobContract)jobs.get(i)).getCommenceTime().trim().equals(""))
        {
          jobLog.setCommenceTime(
            DateUtil.DateTime(
              ((AddJobContract)jobs.get(i)).getJobLog().getCommenceDt(),
              ((AddJobContract)jobs.get(i)).getCommenceTime()
            )
          );
        }

        if(!((AddJobContract)jobs.get(i)).getDelayTime().trim().equals(""))
        {jobLog.setDelayTime(DateUtil.DateTime(((AddJobContract)jobs.get(i)).getJobLog().getDelayDt(),((AddJobContract)jobs.get(i)).getDelayTime()));}

        if(!((AddJobContract)jobs.get(i)).getDelayEndTime().trim().equals(""))
        {jobLog.setDelayEndTime(DateUtil.DateTime(((AddJobContract)jobs.get(i)).getJobLog().getDelayEndDt(),((AddJobContract)jobs.get(i)).getDelayEndTime()));}

        if(!((AddJobContract)jobs.get(i)).getEstCompleteTime().trim().equals(""))
        {jobLog.setEstCompleteTime(DateUtil.DateTime(((AddJobContract)jobs.get(i)).getJobLog().getEstCompleteDt(),((AddJobContract)jobs.get(i)).getEstCompleteTime()));}

        if(!((AddJobContract)jobs.get(i)).getCompleteTime().trim().equals(""))
        {jobLog.setCompleteTime(DateUtil.DateTime(((AddJobContract)jobs.get(i)).getJobLog().getCompleteDt(),((AddJobContract)jobs.get(i)).getCompleteTime()));}

        if(!((AddJobContract)jobs.get(i)).getHoseOffTime().trim().equals(""))
        {jobLog.setHoseOffTime(DateUtil.DateTime(((AddJobContract)jobs.get(i)).getJobLog().getHoseOffDt(),((AddJobContract)jobs.get(i)).getHoseOffTime()));}

        if(!((AddJobContract)jobs.get(i)).getReleaseTime().trim().equals(""))
        {jobLog.setReleaseTime(DateUtil.DateTime(((AddJobContract)jobs.get(i)).getJobLog().getReleaseDt(),((AddJobContract)jobs.get(i)).getReleaseTime()));}

        if(!((AddJobContract)jobs.get(i)).getSampleReceiveTime().trim().equals(""))
        {jobLog.setSampleReceiveTime(DateUtil.DateTime(((AddJobContract)jobs.get(i)).getJobLog().getSampleReceiveDt(),((AddJobContract)jobs.get(i)).getSampleReceiveTime()));}

        if(!((AddJobContract)jobs.get(i)).getPrelimReportTime().trim().equals(""))
        {jobLog.setPrelimReportTime(DateUtil.DateTime(((AddJobContract)jobs.get(i)).getJobLog().getPrelimReportDt(),((AddJobContract)jobs.get(i)).getPrelimReportTime()));}

        if(!((AddJobContract)jobs.get(i)).getSampleShipTime().trim().equals(""))
        {jobLog.setSampleShipTime(DateUtil.DateTime(((AddJobContract)jobs.get(i)).getJobLog().getSampleShipDt(),((AddJobContract)jobs.get(i)).getSampleShipTime()));}

        if(!((AddJobContract)jobs.get(i)).getFinalReportTime().trim().equals(""))
        {jobLog.setFinalReportTime(DateUtil.DateTime(((AddJobContract)jobs.get(i)).getJobLog().getFinalReportDt(),((AddJobContract)jobs.get(i)).getFinalReportTime()));}

        if(!((AddJobContract)jobs.get(i)).getDistributeTime().trim().equals(""))
        {jobLog.setDistributeTime(DateUtil.DateTime(((AddJobContract)jobs.get(i)).getJobLog().getDistributeDt(),((AddJobContract)jobs.get(i)).getDistributeTime()));}

        if(!((AddJobContract)jobs.get(i)).getEtaTime().trim().equals(""))
        {
          String etatime=((AddJobContract)jobs.get(i)).getEtaTime();
          Date etaTime=DateUtil.getConvertedDateTime(((AddJobContract)jobs.get(i)).getJobContract().getJobOrder().getEtaDate(),etatime,((AddJobContract)jobs.get(i)).getJobContract().getJobOrder().getEtaTimeTz(),Constants.TIME_ZONE);
          jobOrder.setEtaTime(etaTime);
        }

        jobOrder=jobService.saveJobOrder(jobOrder);
        jobLog=jobService.addJobLog(jobLog);
        jobContract.setJobOrder(jobOrder);
        jobContract.setJobLog(jobLog);

        jobService.saveJobContractInsp(jobContract);
      }

      jobSearch.setRefreshing("false");
      return showForm(request, response, errors);
      } catch(ServiceException e)
	  {
    	    jobSearch.setRefreshing("false");
	        e.printStackTrace();
	        errors.reject(e.getMessage(), e.getParams(), null);
	        return showForm(request, response, errors);
	  } catch(Throwable t)
	  {
		  jobSearch.setRefreshing("false");
	      t.printStackTrace();
	      errors.reject("generic.error", new Object[] {t.getMessage()}, null);
	      return showForm(request, response, errors);
	  }
    }
  

    /* excel download code starts here*/
    reqForm = request.getParameter("reqFormFlag");
    String jxcel=request.getParameter("jxcel");
    if(reqForm == null)
    	reqForm = "jobSearch";
    if(reqForm != null && reqForm.trim().equals(""))
    	reqForm = "jobSearch";
    if((jxcel!=null) && "true".equals(jxcel))
    {
    try {
      int pageNumber=-1;
      jobSearch.setPagination(null);
      jobService.searchJobOrder(jobSearch,pageNumber,reqForm,sortFlag);
      
     // jobs=jobSearch.getResults();
     // jobs=jobSearch.getTotalResults();
      jobs=jobSearch.getResults();
 		 response.setContentType("application/download");
		 //response.setHeader("Content-disposition","attachment; filename=\"samplejoblog.xls\"");
	 	response.setHeader("Content-Disposition", "attachment; filename=\"samplejoblog.xls\"");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0");

        HSSFWorkbook workBook = new HSSFWorkbook();
        HSSFSheet main=null;

        main=workBook.createSheet("Main");
        HSSFRow   row ;
        HSSFCell  cell;
        HSSFRow headerRow;
        HSSFCell headerCell ;

        PropertyResourceBundle pRB = (PropertyResourceBundle) ResourceBundle.getBundle("TrackerRes");
        String path="";
        if(pRB!= null)
        {
          int  l=0;
          for(int k=0;k<=Constants.JMColumnSize;k++)
          {
            path=pRB.getString("JHeading"+k);
            headerRow = main.createRow((short) 0); headerCell =   headerRow.createCell((short) l); headerCell.setCellValue(path);
            l++;
          }
        }

        int j=1;
        for(int i=0;i<jobs.size();i++)
        {
 row = main.createRow(j);cell = row.createCell((short) 0);
 if(((AddJobContract)jobs.get(i)).getJobOrder()!=null)
	{ cell.setCellValue(((AddJobContract)jobs.get(i)).getJobOrder().getBuName());}
    else
    	{cell.setCellValue("");}
 row = main.createRow(j);cell = row.createCell((short) 1);
 cell.setCellValue(((AddJobContract)jobs.get(i)).getJobOrder().getJobNumber());

row = main.createRow(j);cell = row.createCell((short) 2);cell.setCellValue(((AddJobContract)jobs.get(i)).getOperation());

row = main.createRow(j);cell = row.createCell((short) 3);
cell.setCellValue(jobService.getUiJobTypes(((AddJobContract)jobs.get(i)).getJobOrder().getJobType()));

row = main.createRow(j);cell = row.createCell((short) 4);
//cell.setCellValue(((AddJobContract)jobs.get(i)).getJobOrder().getServiceLocation().getCity());
if(((AddJobContract)jobs.get(i)).getJobOrder().getServiceLocation()!=null){
cell.setCellValue(((AddJobContract)jobs.get(i)).getJobOrder().getServiceLocation().getCity());
}else{cell.setCellValue("");}

row = main.createRow(j);cell = row.createCell((short) 5);
if(((AddJobContract)jobs.get(i)).getJobOrder().getServiceLocationCode()!=null && !((AddJobContract)jobs.get(i)).getJobOrder().getServiceLocationCode().trim().equals(""))
	{
		if(((AddJobContract)jobs.get(i)).getJobOrder().getServiceLocation()!=null)
		{cell.setCellValue(((AddJobContract)jobs.get(i)).getJobOrder().getServiceLocation().getName());}
	}
    else
	{cell.setCellValue("");}

row = main.createRow(j);cell = row.createCell((short) 6);
if(((AddJobContract)jobs.get(i)).getJobOrder().getVesselNames()!=null && !((AddJobContract)jobs.get(i)).getJobOrder().getVesselNames().trim().equals(""))
	{cell.setCellValue(((AddJobContract)jobs.get(i)).getJobOrder().getVesselNames());}
else
	{cell.setCellValue("");}

row = main.createRow(j);cell = row.createCell((short) 7);
if(((AddJobContract)jobs.get(i)).getJobOrder().getProductNames()!=null && !((AddJobContract)jobs.get(i)).getJobOrder().getProductNames().trim().equals(""))
	{cell.setCellValue(((AddJobContract)jobs.get(i)).getJobOrder().getProductNames());}
else
	{cell.setCellValue("");}

row = main.createRow(j);cell = row.createCell((short) 8);
if(((AddJobContract)jobs.get(i)).getJobContract().getCustCode()!=null && !((AddJobContract)jobs.get(i)).getJobContract().getCustCode().trim().equals(""))
{cell.setCellValue(((AddJobContract)jobs.get(i)).getJobContract().getCustCode());}
else
	{cell.setCellValue("");}


row = main.createRow(j);cell = row.createCell((short) 9);
if(((AddJobContract)jobs.get(i)).getJobContract().getCustomer().getName()!=null && !((AddJobContract)jobs.get(i)).getJobContract().getCustomer().getName().trim().equals(""))
{cell.setCellValue(((AddJobContract)jobs.get(i)).getJobContract().getCustomer().getName());}
else
	{cell.setCellValue("");}
  
row = main.createRow(j);cell = row.createCell((short) 10);
if(((AddJobContract)jobs.get(i)).getJobContract()!=null)
{cell.setCellValue(((AddJobContract)jobs.get(i)).getJobContract().getContactId());}
else
	{cell.setCellValue("");}
 
row = main.createRow(j);cell = row.createCell((short) 11);
if(((AddJobContract)jobs.get(i)).getJobContract().getContact().getFirstName()!= null && ((AddJobContract)jobs.get(i)).getJobContract().getContact().getLastName() != null){
	cell.setCellValue((((AddJobContract)jobs.get(i)).getJobContract().getContact().getFirstName())+" "+(((AddJobContract)jobs.get(i)).getJobContract().getContact().getLastName()));  
}
else if(((AddJobContract)jobs.get(i)).getJobContract().getContact().getFirstName()!= null ){
    cell.setCellValue((((AddJobContract)jobs.get(i)).getJobContract().getContact().getFirstName())+"");
}
else if(((AddJobContract)jobs.get(i)).getJobContract().getContact().getLastName()!= null ){
	cell.setCellValue(" "+(((AddJobContract)jobs.get(i)).getJobContract().getContact().getLastName()));
}
else
	cell.setCellValue("");  

row = main.createRow(j);cell = row.createCell((short) 12);
if(((AddJobContract)jobs.get(i)).getJobContract().getCustRefNum()!=null && !((AddJobContract)jobs.get(i)).getJobContract().getCustRefNum().trim().equals(""))
{cell.setCellValue(((AddJobContract)jobs.get(i)).getJobContract().getCustRefNum());}
else
	{cell.setCellValue("");}

row = main.createRow(j);cell = row.createCell((short) 13);
if(((AddJobContract)jobs.get(i)).getJobContract().getInvoiceValue5()!=null && !((AddJobContract)jobs.get(i)).getJobContract().getInvoiceValue5().trim().equals(""))
{cell.setCellValue(((AddJobContract)jobs.get(i)).getJobContract().getInvoiceValue5());}
else
	{cell.setCellValue("");}
/* For itrack Issue 96935 starts 15-06-09*/
//Commented for itrack Issue 96935 starts 15-06-09
/* 
row = main.createRow(j);cell = row.createCell((short) 14);
if(((AddJobContract)jobs.get(i)).getJobOrder().getShippingAgent()!= null)
 {cell.setCellValue(((AddJobContract)jobs.get(i)).getJobOrder().getShippingAgent().getName());}
 else
	 {cell.setCellValue("");}

          row = main.createRow(j);cell = row.createCell((short) 15);
if(((AddJobContract)jobs.get(i)).getJobOrder().getShippingAgent()!=null)
{cell.setCellValue(((AddJobContract)jobs.get(i)).getJobOrder().getShippingAgent().getPhone());}
          else
          {cell.setCellValue("");
          }
row = main.createRow(j);cell = row.createCell((short) 16);
if(((AddJobContract)jobs.get(i)).getJobOrder().getTowingCompany()!= null)
{cell.setCellValue(((AddJobContract)jobs.get(i)).getJobOrder().getTowingCompany().getName());}
          else
          {cell.setCellValue("");}

          row = main.createRow(j);cell = row.createCell((short) 17);
if(((AddJobContract)jobs.get(i)).getJobOrder().getTowingCompany()!=null)
{cell.setCellValue(((AddJobContract)jobs.get(i)).getJobOrder().getTowingCompany().getPhone());
          }else
          {cell.setCellValue("");
          }*///Commented for itrack Issue 96935 Ends 15-06-09


          row = main.createRow(j);cell = row.createCell((short) 14);
if(((AddJobContract)jobs.get(i)).getJobOrder().getJobFinishDate()!=null)	
{Date date1=(((AddJobContract)jobs.get(i)).getJobOrder().getJobFinishDate());String k=DateUtil.formatDate(date1,"MM/dd/yyyy"); cell.setCellValue(k);}else{cell.setCellValue("");}

row = main.createRow(j);cell=row.createCell((short) 15);
if(((AddJobContract)jobs.get(i)).getJobOrder().getJobStatus()!=null && !((AddJobContract)jobs.get(i)).getJobOrder().getJobStatus().trim().equals(""))
{cell.setCellValue(jobService.getUiJobStatus(((AddJobContract)jobs.get(i)).getJobOrder().getJobStatus()));}
else
			{cell.setCellValue("");}

row = main.createRow(j);cell=row.createCell((short) 16);
//if(((AddJobContract)jobs.get(i)).getJobContract()!=null)
if(((AddJobContract)jobs.get(i)).getLatestJobContractInvoice()!= null && ((AddJobContract)jobs.get(i)).getLatestJobContractInvoice().getInvoice()!= null &&((AddJobContract)jobs.get(i)).getLatestJobContractInvoice().getInvoice().trim().equals(""))
	{ cell.setCellValue(((AddJobContract)jobs.get(i)).getLatestJobContractInvoice().getInvoice());}
  else
	{cell.setCellValue("");}

row = main.createRow(j);cell=row.createCell((short) 17);
if(((AddJobContract)jobs.get(i)).getJobContract().getContract()!= null)
{
	//if(((AddJobContract)jobs.get(i)).getJobContract().getContractCustContact().getContractCust()!= null)
	//{
		//if(((AddJobContract)jobs.get(i)).getJobContract().getContractCustContact().getContractCust().getContract()!= null)
			cell.setCellValue(((AddJobContract)jobs.get(i)).getJobContract().getContract().getDescription());
	//}
}
else
			{cell.setCellValue("");}

row = main.createRow(j);cell=row.createCell((short) 18);
if(((AddJobContract)jobs.get(i)).getJobContract().getContract()!= null)
{
	//if(((AddJobContract)jobs.get(i)).getJobContract().getContractCustContact().getContractCust()!= null)
	//{
		//if(((AddJobContract)jobs.get(i)).getJobContract().getContractCustContact().getContractCust().getContract()!= null)
			cell.setCellValue(jobService.getUiContractStatus(((AddJobContract)jobs.get(i)).getJobContract().getContract().getStatus()));
	//}
}
else
		{cell.setCellValue("");}

row = main.createRow(j);cell=row.createCell((short) 19);
if(((AddJobContract)jobs.get(i)).getJobOrder().getCreatedByUserName()!=null && !((AddJobContract)jobs.get(i)).getJobOrder().getCreatedByUserName().trim().equals(""))
			{cell.setCellValue(((AddJobContract)jobs.get(i)).getJobOrder().getCreatedByUserName());}
else
			{cell.setCellValue("");}

row = main.createRow(j);cell=row.createCell((short) 20);
if(((AddJobContract)jobs.get(i)).getJobOrder().getUpdatedByUserName()!=null && !((AddJobContract)jobs.get(i)).getJobOrder().getUpdatedByUserName().trim().equals(""))
			{cell.setCellValue(((AddJobContract)jobs.get(i)).getJobOrder().getUpdatedByUserName());}
			else
			{cell.setCellValue("");}
          j++;
        }
/* For itrack Issue 96935 Ends 15-06-09*/
        OutputStream out = response.getOutputStream();
        workBook.write(out);
        out.flush();
        out.close();
        response.reset();
        jobSearch.setJxcel("false");

      }
      catch (FileNotFoundException fne)
      {
        fne.printStackTrace();
      }
      catch (IOException ioe)
      {
        System.out.println("IOException..." +  ioe.toString());
      }

      return showForm(request, response, errors);
    }

    /*excel download ends here*/
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
    Pagination pagination = new Pagination(1,20, pageNumber, 10);
    jobSearch.setPagination(pagination);

    String fromJobid = jobSearch.getFromJobId().getValue();
    String toJobid = jobSearch.getToJobId().getValue();
    String branch = jobSearch.getBranch().getValue();
    if((fromJobid != null) && !"".equals(fromJobid.trim())&& ((toJobid != null) && !"".equals(toJobid.trim()))&& (branch != null) && !"".equals(branch.trim()))
    {
    	 try {
    	 String[] fromJob = fromJobid.trim().split("\\-");

         String fromStringPart= fromJob[0];
         
         String[] toJob = toJobid.trim().split("\\-");

         String toStringPart = toJob[0];
         if(!fromStringPart.equalsIgnoreCase(branch))
         {
        	 errors.reject("invalid.fromJob.id.select.branch.associated.fromJobIderror",new Object[] {fromJobid, }, null);
			return showForm(request, response, errors);
         }
         jobOrder = jobService.getJobOrderByJobNumber(fromJobid.trim().toUpperCase());
         if (jobOrder == null)
         {
        	 errors.reject("invalid.fromJob.id.select.valid.branch.associated.fromJobIderror",new Object[] {fromJobid, }, null);
 			return showForm(request, response, errors);
         }
         if(!toStringPart.equalsIgnoreCase(branch))
         {
        	 errors.reject("invalid.tojob.id.please.select.branch.associated.tojobid",new Object[] { toJobid, }, null);
			return showForm(request, response, errors);
         }
         jobOrder = jobService.getJobOrderByJobNumber(toJobid.trim().toUpperCase());
         if (jobOrder == null)
         {
        	 errors.reject("invalid.tojob.id.please.select.valid.branch.associated.tojobid",new Object[] {toJobid, }, null);
 			return showForm(request, response, errors);
         }
    	 } catch(ServiceException e)
	     {
	        e.printStackTrace();
	        errors.reject(e.getMessage(), e.getParams(), null);
	        return showForm(request, response, errors);
	     } catch(Throwable t)
	     {
	        t.printStackTrace();
	        errors.reject("generic.error", new Object[] {t.getMessage()}, null);
 			return showForm(request, response, errors);
         }

    }
    if(reqForm == null)
    	reqForm = "jobSearch";
    if(reqForm != null && reqForm.trim().equals(""))
    	reqForm = "jobSearch";
   // try
   // {
      //if(pageNumber == 1 && sortFlag != null && sortFlag.equals(""))
    try {
      jobService.searchJobOrder(jobSearch,pageNumber,reqForm,sortFlag);
    } catch(ServiceException e)
    {
        e.printStackTrace();
        errors.reject(e.getMessage(), e.getParams(), null);
        return showForm(request, response, errors);
    } catch(Throwable t)
    {
        t.printStackTrace();
        errors.reject("generic.error", new Object[] {t.getMessage()}, null);
        return showForm(request, response, errors);
    }
   /* catch(Exception e)
    {
      e.printStackTrace();
      errors.reject("search.job.order.error", new Object[] {e.getMessage()}, null);
      return showForm(request, response, errors);
    }*/
    	
    
    /* Ankur comment starts
    	List results = jobSearch.getTotalResults();
    	AddJobContract addJobContracts = new AddJobContract();
    	List finalResults = new ArrayList();
    	Pagination jobPagination = new Pagination(1, 10, pageNumber,10);
    	
    	jobSearch.setPagination(jobPagination);
    	if(results != null)
    	{
    	if(jobPagination != null)
		{
		  if(results.size() > 0){
			  jobPagination.setTotalRecord(results.size());
		  }
		  jobPagination.calculatePages();
		}
//	    	 sorting by column header
	    	List sortResults = new ArrayList();
	    	if(pageNumber == 1 && sortFlag != null && !sortFlag.equals("") && results != null)
	    	{
	    		sortResults = SortUtil.sortByJobLogColumnHeader(results, sortFlag);
	    		jobSearch.setResults(sortResults);
	    		jobSearch.setTotalResults(sortResults);
	    		results = jobSearch.getTotalResults();
	    	}
	        
	    	//end
    	int startRecord=pageNumber;
    	int sortedResultsSize = results.size();
    	
    	if(startRecord == 1 )
    	{
    		if(sortedResultsSize >= Constants.JOBSEARCH_RECORDS_PAGE)
    		{
		    	for(int i=0;i<Constants.JOBSEARCH_RECORDS_PAGE;i++)
		    	{
		    		addJobContracts = (AddJobContract) results.get(i);
		    		finalResults.add(addJobContracts);
		    	}
    		}else
    		{
    			for(int i=0;i<sortedResultsSize;i++)
		    	{
    				addJobContracts = (AddJobContract) results.get(i);
		    		finalResults.add(addJobContracts);
		    	}
    		}
    	}else
    	{
    		int end=startRecord * Constants.JOBSEARCH_RECORDS_PAGE;
    		int start=end - Constants.JOBSEARCH_RECORDS_PAGE;
    		if(end<=sortedResultsSize)
    		{
	    		for(int i=start;i<end;i++)
		    	{
	    			addJobContracts = (AddJobContract) results.get(i);
		    		finalResults.add(addJobContracts);
		    	}
    		}else
    		{
    			start=(startRecord -1)*Constants.JOBSEARCH_RECORDS_PAGE;
    			end=sortedResultsSize;
    			for(int i=start;i<end;i++)
		    	{
    				addJobContracts = (AddJobContract) results.get(i);
		    		finalResults.add(addJobContracts);
		    	}
		    	}
    		}
    	}
    	jobSearch.setResults(finalResults);
    	
    	Ankur comment ends */
    
    
    //end
	String columnflag=request.getParameter("columnFlag");
	jobSearch.setColumnFlag(columnflag);
    request.setAttribute("command", jobSearch);
    request.setAttribute("searchResult", "main");
    return showForm(request, response, errors);
  }

  protected boolean suppressValidation(HttpServletRequest request)
  {
    String refreshing = request.getParameter("refreshing");
    if((refreshing != null) && "true".equals(refreshing))
    {
      return true;
    }
    String jxcel=request.getParameter("jxcel");
    if((jxcel!=null) && "true".equals(jxcel))
    {
      return true;
    }
    String jmail=request.getParameter("jmail");
    if((jmail!=null) && "true".equals(jmail))
    {
      return true;
    }
    return super.suppressValidation(request);
  }

  protected Map referenceData(HttpServletRequest request) throws Exception
  {
    Map data = new HashMap();

    data.put("tabName", "SearchJob");

    return data;
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest, org.springframework.web.bind.ServletRequestDataBinder)
   */
  protected void initBinder(HttpServletRequest request,ServletRequestDataBinder binder) throws Exception
  {
    /*SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    dateFormat.setLenient(false);
    binder.registerCustomEditor(java.util.Date.class, null,new CustomDateEditor(dateFormat, true));*/
    
  //  String dateFormate = SecurityUtil.getUser() != null ? SecurityUtil.getUser().getDateFormat():"dd/mm/yyyy";
	UserService userService = (UserService) ServiceLocator.getInstance().getBean("userService");
	User loginUser = userService.getUserByName(SecurityUtil.getUser().getLoginName());
	String dateFormate = loginUser.getDateFormat();
    if(dateFormate != null && !dateFormate.trim().equals(""))
	{
	String[] pattern = dateFormate.split("/");
       String month = pattern[1];
       if(pattern[0].trim().equalsIgnoreCase("mm"))
    	   dateFormate = "MM/dd/yyyy";  
       if(month.trim().equalsIgnoreCase("mmm"))
    	   dateFormate = "dd/MMM/yyyy"; 
       if(month.trim().equalsIgnoreCase("mm"))
    	   dateFormate = "dd/MM/yyyy"; 
	}else
	{
		dateFormate =  Constants.DD_MM_YYYY_DATE_FORMAT; 
	}
	SimpleDateFormat dateFormat = new SimpleDateFormat(dateFormate);
	
	dateFormat.setLenient(false);
	binder.registerCustomEditor(java.util.Date.class, null,
			new CustomDateEditor(dateFormat, true));
  }

  protected Object formBackingObject(HttpServletRequest request) throws Exception
  {
	JobSearch jobSearch = new JobSearch();
	UserService userService = (UserService) ServiceLocator.getInstance().getBean("userService");
	JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");

    String rowNo=request.getParameter("rowNum");

    String jobNumber = request.getParameter("jobNum");
    String reqForm="jobSearch";
    jobSearch.setReqFormFlag(reqForm);
    AddJobContract addJobContract = new AddJobContract();
    if(jobSearch == null)
    {
      jobSearch = new JobSearch();
      jobSearch.setAddJobContract(addJobContract);
      jobSearch.setRowNum(rowNo);
    }
//  date formate code start
	//String dateFormat = SecurityUtil.getUser() != null ? SecurityUtil.getUser().getDateFormat():"dd/mm/yyyy";
    String loginName=SecurityUtil.getUser().getLoginName();
    
    User loginUser = userService.getUserByName(SecurityUtil.getUser().getLoginName());
	String dateFormat = loginUser.getDateFormat();
	try {
	if(dateFormat != null && !dateFormat.trim().equals(""))
	{
	String[] pattern = dateFormat.split("/");
    String month = pattern[1];
    if(pattern[0].trim().equalsIgnoreCase("mm"))
	   dateFormat = Constants.MM_DD_YYYY_DATE_FORMAT;  
    if(month.trim().equalsIgnoreCase("mmm"))
    dateFormat = Constants.DD_MMM_YYYY_DATE_FORMAT; 
    if(month.trim().equalsIgnoreCase("mm"))
	   dateFormat = Constants.DD_MM_YYYY_DATE_FORMAT; 
	}else
	{
		dateFormat = dateFormat = Constants.DD_MM_YYYY_DATE_FORMAT; 
	} 
    jobSearch.setDateFormat(dateFormat);

	
	// date formate code end
	jobSearch.getStatus().setValue("4");
	jobSearch.getInvoiceStatus().setValue("0");
	jobSearch.getPort().setOperator(2);
	jobSearch.getContractId().setOperator(2);
	jobSearch.getSvcLocation().setOperator(2);
	jobSearch.getCompanyId().setOperator(2);
	jobSearch.getCompany().setOperator(2);
	jobSearch.getSchedulerId().setOperator(2);
	jobSearch.getScheduler().setOperator(2);
	jobSearch.getBillingContactId().setOperator(2);
	jobSearch.getBillingContact().setOperator(2);

    if(!"POST".equals(request.getMethod()))
    {
      if(jobSearch.getBusinessUnit().getValue() == null)
      {
        String buName=SecurityUtil.getUser().getBusinessUnit().getName();
        jobSearch.getBusinessUnit().setValue(buName);
		String branchName=SecurityUtil.getUser().getBranch().getName();
		jobSearch.getBranch().setValue(branchName);		
      }

    }
   // jobSearch.getBranch().setValue(null);
    jobSearch.getJobType().setValue(Constants.INSPECTION_JOBTYPE);
    
    CriteriaService criteriaService=(CriteriaService) ServiceLocator.getInstance().getBean("criteriaService");
    List<JobSearchCriteria> userJobSearchCriteria=criteriaService.getJobSearchCriteria(loginName);
    request.getSession().setAttribute("mySearchJobCriteria", userJobSearchCriteria);
    UserSettings userSettings=userService.getUserSettings(loginName);
    String criteriaAction=request.getParameter("criteriaAction");
    if(userSettings!=null && !"refreshCriteria".equalsIgnoreCase(criteriaAction)){
    	Long jobSearchCriteriaId=userSettings.getJobSearchCriteriaId();
    	SearchJobCriteriaController.setJobSearch(jobSearch, SearchJobCriteriaController.getJobSearchCriteriaById(request, jobSearchCriteriaId));
    }
    
    // Return to search process
    if(jobNumber != null && !jobNumber.equals(""))
    {
    	JobSearch jobSrch =((JobSearch)request.getSession().getAttribute("MySearchJobOrder"));
    	if(jobSrch != null)
    	{
	      int pageNumber = 1;
	      if(jobSrch.getPagination()!= null && jobSrch.getPagination().getCurrentPageNum()!= 0)
	      {
	    	  pageNumber= jobSrch.getPagination().getCurrentPageNum();
	      }else
	      {
	    	  Pagination pagination = new Pagination(1, 20, pageNumber,10);
	    	  jobSrch.setPagination(pagination);
	      }
	      String sortFlag = jobSrch.getSortFlag();
	      jobService.searchJobOrder(jobSrch,pageNumber,reqForm,sortFlag);
	      request.setAttribute("command", jobSrch);
	      request.setAttribute("searchResult", "main");
	      //Setting Session null
		  request.getSession().setAttribute("MySearchJobOrder", null);
	      return jobSrch;
    }else
    {
    	return jobSearch;
    }
  }
	} catch(ServiceException e)
	{
	 	throw new ServiceException(e.getMessage(), e.getParams(), e);
	} catch(Throwable t)
	{
	     t.printStackTrace();
	     throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
	}
    //end
    return jobSearch;
  }
}
