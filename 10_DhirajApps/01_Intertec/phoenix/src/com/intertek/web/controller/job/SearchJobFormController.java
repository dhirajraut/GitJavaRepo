package com.intertek.web.controller.job;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.intertek.domain.AddJobContract;
import com.intertek.domain.JobSearch;
import com.intertek.entity.Contact;
import com.intertek.entity.Contract;
import com.intertek.entity.DropDowns;
import com.intertek.entity.JobContract;
import com.intertek.entity.JobContractInvoice;
import com.intertek.entity.JobContractNote;
import com.intertek.entity.JobLog;
import com.intertek.entity.JobOrder;
import com.intertek.entity.LogConfigDetail;
import com.intertek.entity.LogConfigDetailId;
import com.intertek.entity.LogConfigList;
import com.intertek.entity.Operation;
import com.intertek.entity.User;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.mail.VelocityMimeMessagePreparator;
import com.intertek.pagination.Pagination;
import com.intertek.service.DropdownService;
import com.intertek.service.JobService;
import com.intertek.service.SequenceNumberService;
import com.intertek.service.UserService;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;
import com.intertek.util.PropertyConfig;
import com.intertek.util.SecurityUtil;
import com.intertek.web.controller.BaseSimpleFormController;

public class SearchJobFormController extends BaseSimpleFormController
{
  public SearchJobFormController() {
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
	  String tabName = request.getParameter("tabFlag");
      String configFlag=request.getParameter("configFlag");
      String saveFlag=request.getParameter("saveFlag");
	  if(tabName.equalsIgnoreCase("criteria") && (saveFlag.equalsIgnoreCase("false"))&&(configFlag.equalsIgnoreCase("false"))){

		  request.setAttribute("searchResult", tabName);
		  return showForm(request, response, errors); 
		   // return new ModelAndView(new RedirectView("search_job.htm"));
	  }
	  
     	AddJobContract addJobContract=jobSearch.getAddJobContract();		
		JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
		DropdownService dropdownService = (DropdownService)ServiceLocator.getInstance().getBean("dropdownService");
		    SequenceNumberService sequenceNumberService = (SequenceNumberService)ServiceLocator.getInstance().getBean("sequenceNumberService");    


       String sortFlag = request.getParameter("sortFlag");
	  String refreshing = request.getParameter("refreshing");
	  String sessionFlag = request.getParameter("sessionFlag");
	  String hrefValue = request.getParameter("hrefValue");
	  String hrefJobNumber = request.getParameter("hrefJobNumber");
	  String hrefForm = request.getParameter("hrefForm");
	  String searchResults = jobSearch.getSearchResults().getValue();
	  String showAllFlag = request.getParameter("showAllFlag");
	  String reqForm ="";
	  String reload = request.getParameter("reload");
      String searchFlag=request.getParameter("searchFlag");
	  String jxcel=request.getParameter("jxcel");
	  
	  // Setting Sort Order on 03/02/09
	  String pageNumStr = request.getParameter("pageNumber");
	// START : #119240
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
	 // if(pageNumStr != null && Integer.valueOf(pageNumStr).intValue() == 1){
		
	// END : #119240		
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
if(jxcel.equalsIgnoreCase("false")){
	 jobSearch.setShowAllExcel("false");
 }



//    Getting Header sort value
      LogConfigDetail logConfigDet;
      if(sortFlag != null && !sortFlag.equals("")&& !sortFlag.equalsIgnoreCase("mh_col13") && !sortFlag.equalsIgnoreCase("mh_col14")&& !sortFlag.equalsIgnoreCase("mh_col32") && !sortFlag.equalsIgnoreCase("mh_col19") /*&& !sortFlag.equalsIgnoreCase("mh_col33")*/)
      {
      	//Aby
    	  try 
    	    { 
      	logConfigDet =jobService.getDbColumnValue(sortFlag);
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
      	sortFlag="";
  		if(logConfigDet != null)
  		{
  			sortFlag=logConfigDet.getDbColName();
  		}
      }  






if((saveFlag!=null) && "true".equals(saveFlag))
{

List mainBox=new ArrayList();
List sortOrder=new ArrayList();
List firstfreelist=new ArrayList();
List secondfreelist=new ArrayList();
List thirdfreelist=new ArrayList();
List fourthfreelist=new ArrayList();
List dispList=new ArrayList();

if(jobSearch.getHideAndSort()!=null)
{mainBox=jobSearch.getHideAndSort();}

if(jobSearch.getResultSortOrder()!=null)
{sortOrder=jobSearch.getResultSortOrder();}
if(jobSearch.getFreezedList1()!=null)
{firstfreelist=jobSearch.getFreezedList1();}


if(jobSearch.getFreezedList2()!=null)
{secondfreelist=jobSearch.getFreezedList2();}


if(jobSearch.getFreezedList3()!=null)
{thirdfreelist=jobSearch.getFreezedList3();}


if(jobSearch.getFreezedList4()!=null)
{fourthfreelist=jobSearch.getFreezedList4();}

if(jobSearch.getDisplayOrder()!=null)
{dispList=jobSearch.getDisplayOrder();}


List defaultList=jobSearch.getDefaultHideandSort();

LogConfigList logConfigList=jobSearch.getLogConfigList();
String name="";
if(logConfigList.getConfListName()!=null && !logConfigList.getConfListName().trim().equals("")){
 name=logConfigList.getConfListName();}
else{name="none";}

//List defaultList=new ArrayList();

LogConfigList existingLists=jobService.getConfListNameExists(name);
if(existingLists!=null)
{
//Aby
try 
  { 
defaultList=jobService.getColsandTabsByListId(existingLists.getConfListId());
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
logConfigList.setConfListId(existingLists.getConfListId());
}
else{
defaultList=jobSearch.getDefaultHideandSort();
logConfigList.setConfListId(sequenceNumberService.getSequenceNumber("CONFLIST_SEQ"));
}
logConfigList.setCreatedBy(SecurityUtil.getUser());
logConfigList.setCreatedByUserName(SecurityUtil.getUser().getLoginName());
logConfigList.setUpdatedBy(SecurityUtil.getUser());
logConfigList.setUpdatedByUserName(SecurityUtil.getUser().getLoginName());
logConfigList.setCreateTime(new Date());
logConfigList.setUpdateTime(new Date());

/*String[] mainBoxCol=new String[mainBox.size()];
String[] DispBoxCol=new String[mainBox.size()];
String[] BillBoxCol=new String[mainBox.size()];
String[] PLBoxCol=new String[mainBox.size()];*/



	//if(mainBox!=null && mainBox.size()!=0){
        
		int k1=0;int k2=0; int k3=0; int k4=0;

       for(int i=0;i<mainBox.size();i++){

		 if(mainBox.get(i).toString().equalsIgnoreCase("Main"))
	 	{k1=i;}
		if(mainBox.get(i).toString().equalsIgnoreCase("Dispatch"))
	 	{k2=i;}
         
		if(mainBox.get(i).toString().equalsIgnoreCase("Billing"))
	 	{k3=i;}
         
		if(mainBox.get(i).toString().equalsIgnoreCase("Process Log"))
	 	{k4=i;}
	   }
		

String[] mainBoxCol=new String[(k2-k1)-1];
String[] DispBoxCol=new String[(k3-k2)-1];
String[] BillBoxCol=new String[(k4-k3)-1];
String[] PLBoxCol=new String[(mainBox.size()-k4)-1];

int m=0;
for(int i=1;i<k2;i++){
String s="";
s=mainBox.get(i).toString();
mainBoxCol[m]=s;
m++;
}
         
	int d=0;
	for(int i=k2+1;i<k3;i++){
	String s1=""; 					
	s1=mainBox.get(i).toString();
	DispBoxCol[d]=s1;
	d++;
	}

		int b=0;
		for(int i=k3+1;i<k4;i++)
		{String s2="";
		s2=mainBox.get(i).toString();
		BillBoxCol[b]=s2;
		b++;
		}

			int pl=0;
			for(int i=k4+1;i<mainBox.size();i++)
			{String s3="";
			s3=mainBox.get(i).toString();
			PLBoxCol[pl]=s3;
			pl++;}



String[] sortCol=new String[sortOrder.size()];

	if(sortOrder!=null && sortOrder.size()!=0){
		for(int i=0;i<sortOrder.size();i++){
		String s="";
		s=sortOrder.get(i).toString();
		sortCol[i]=s;						
		}
	}

String[] dispCol=new String[dispList.size()];

if(dispList!=null && dispList.size()!=0){
		for(int i=0;i<dispList.size();i++){
		String s="";
		s=dispList.get(i).toString();
		dispCol[i]=s;						
		}
	}


String[] freeMain= new String[firstfreelist.size()];

if(firstfreelist!=null && firstfreelist.size()!=0){
		for(int i=0;i<firstfreelist.size();i++){
		String s="";
		s=firstfreelist.get(i).toString();
		freeMain[i]=s;						
		}
	}


String[] freeDispatch= new String[secondfreelist.size()];

if(secondfreelist!=null && secondfreelist.size()!=0){

		for(int i=0;i<secondfreelist.size();i++){
		String s="";
		s=secondfreelist.get(i).toString();
		freeDispatch[i]=s;						
		}
	}

String[] freeBilling= new String[thirdfreelist.size()];

if(thirdfreelist!=null && thirdfreelist.size()!=0){
		for(int i=0;i<thirdfreelist.size();i++){
		String s="";
		s=thirdfreelist.get(i).toString();
		freeBilling[i]=s;						
		}
	}

String[] freePL= new String[fourthfreelist.size()];

if(fourthfreelist!=null && fourthfreelist.size()!=0){
	
		for(int i=0;i<fourthfreelist.size();i++){
		String s="";
		s=fourthfreelist.get(i).toString();
		freePL[i]=s;						
		}
	}

logConfigList.getLogConfigDetails().clear();

for(int j=0;j<defaultList.size();j++)
{
LogConfigDetail logConfigDetails=(LogConfigDetail)defaultList.get(j);
LogConfigDetail logConfigDetail = new LogConfigDetail();
LogConfigDetailId logConfigDetailId =new LogConfigDetailId();
logConfigDetail.setLogConfigDetailId(logConfigDetailId);

String colheader=logConfigDetails.getLogConfigDetailId().getColHeader();

logConfigDetail.getLogConfigDetailId().setConfListId(logConfigList.getConfListId());
logConfigDetail.getLogConfigDetailId().setTabName(logConfigDetails.getLogConfigDetailId().getTabName());
logConfigDetail.getLogConfigDetailId().setColHeader(logConfigDetails.getLogConfigDetailId().getColHeader());
logConfigDetail.setColName(logConfigDetails.getColName());
logConfigDetail.setDbColName(logConfigDetails.getDbColName());
//logConfigDetail.setDisplayStatus(logConfigDetails.getDisplayStatus());
logConfigDetail.setDisplayStatus(true);
logConfigDetail.setDisplayOrder(logConfigDetails.getDisplayOrder());
//logConfigDetail.setSortOrder(logConfigDetails.getSortOrder());
logConfigDetail.setSortOrder(0);
//logConfigDetail.setFreezeOrder(logConfigDetails.getFreezeOrder());
logConfigDetail.setFreezeOrder(0);
logConfigDetail.setDisplayOrder(logConfigDetails.getDisplayOrder());


if(mainBox!=null && mainBox.size()!=0){
		for(int k=0;k<mainBoxCol.length;k++){	
			if(mainBoxCol[k]!=null){
				if(mainBoxCol[k].trim().equals(colheader))
				{logConfigDetail.setDisplayOrder(k+1);
				logConfigDetail.getLogConfigDetailId().setTabName("Main");}		  
			}
		}
   }

if(mainBox!=null && mainBox.size()!=0){
		for(int k=0;k<DispBoxCol.length;k++){
			if(DispBoxCol[k]!=null){
				if(DispBoxCol[k].trim().equals(colheader))
				{logConfigDetail.setDisplayOrder(k+1);
				logConfigDetail.getLogConfigDetailId().setTabName("Dispatch");}		  
			}
		}
   }

if(mainBox!=null && mainBox.size()!=0){
		for(int k=0;k<BillBoxCol.length;k++){
			if(BillBoxCol[k]!=null){
				if(BillBoxCol[k].trim().equals(colheader))
				{logConfigDetail.setDisplayOrder(k+1);
             logConfigDetail.getLogConfigDetailId().setTabName("Billing");}		  
			}
		}
   }

if(mainBox!=null && mainBox.size()!=0){
		for(int k=0;k<PLBoxCol.length;k++){
               if(PLBoxCol[k]!=null){
				if(PLBoxCol[k].trim().equals(colheader))
				{logConfigDetail.setDisplayOrder(k+1);
				logConfigDetail.getLogConfigDetailId().setTabName("Process Log");}		
			}
		}
   }



	if(sortOrder!=null && sortOrder.size()!=0){
		for(int k=0;k<sortCol.length;k++){

				if(sortCol[k].trim().equals(colheader))
				{logConfigDetail.setSortOrder(k+1);}		  
			}
		}

     if(dispList!=null && dispList.size()!=0){
		for(int k=0;k<dispCol.length;k++){

				if(dispCol[k].trim().equals(colheader))
				{logConfigDetail.setDisplayStatus(false);}		  
			}
	 }
		

      if(firstfreelist!=null && firstfreelist.size()!=0){
		for(int k=0;k<freeMain.length;k++){

				if(freeMain[k].trim().equals(colheader))
				{logConfigDetail.setFreezeOrder(k+1);} 
			}	
	  }


 if(secondfreelist!=null && secondfreelist.size()!=0){
		for(int k=0;k<freeDispatch.length;k++){

				if(freeDispatch[k].trim().equals(colheader))
				{logConfigDetail.setFreezeOrder(k+1);}  
			}	
	}


 if(thirdfreelist!=null && thirdfreelist.size()!=0){
		for(int k=0;k<freeBilling.length;k++){

				if(freeBilling[k].trim().equals(colheader))
				{logConfigDetail.setFreezeOrder(k+1);}  
			}	
	}


if(fourthfreelist!=null && fourthfreelist.size()!=0){
		for(int k=0;k<freePL.length;k++){

				if(freePL[k].trim().equals(colheader))
				{logConfigDetail.setFreezeOrder(k+1);}  
			}	
	}
	logConfigDetail.setLogConfigList(logConfigList);
logConfigList.getLogConfigDetails().add(logConfigDetail);
}
if(searchFlag !=null && searchFlag.equals("false"))
{
if(logConfigList.getConfListName()!=null && !logConfigList.getConfListName().trim().equals(""))
{
	LogConfigList existingname = jobService.getConfListNameExists(logConfigList.getConfListName());

	if(existingname!=null && existingname.getConfListName().equalsIgnoreCase("DefaultList"))
	{
	//errors.reject("search.job.error",new Object[] {"ConfigList Name Already Exist's : "+ logConfigList.getConfListName(), }, null);
		
	errors.reject("configlist.name.already.exist",new Object[] {logConfigList.getConfListName(), }, null);
	return showForm(request, response, errors);
	}

	if(logConfigList.getDefaultList()) {
		LogConfigList defaultConfigList = jobService.getDefaultConfigListByUserName(SecurityUtil.getUser().getLoginName());
		if(defaultConfigList!=null && !defaultConfigList.getConfListName().equalsIgnoreCase("")){
			jobService.updateDefaultConfigListByUserName(SecurityUtil.getUser().getLoginName());
		}
		
	}

     if(existingname!=null && !existingname.getConfListName().equalsIgnoreCase("DefaultList"))
	    {
		     logConfigList=jobService.saveConfig(logConfigList);
			 jobSearch.setLogConfigList(logConfigList);
		}
		else{
			  logConfigList=jobService.addConfig(logConfigList);
			  jobSearch.setConfId(logConfigList.getConfListId());
			  jobSearch.setLogConfigList(logConfigList);
		}
} else
	{
	errors.reject("configlist.name.is.required",new Object[] {}, null);
	return showForm(request, response, errors);
	}
}
jobSearch.setConfigFlag("false");

jobSearch.setSaveFlag("false");
if(searchFlag !=null && searchFlag.equals("false")){
	
return showForm(request, response, errors); }
}
jobSearch.setSearchFlag("false");  

 
if((configFlag!=null) && "true".equals(configFlag))
{
String id=jobSearch.getConfName();
LogConfigList logConfigLists= jobService.getConfigNameById(id);
LogConfigList logConfigList=jobSearch.getLogConfigList();
logConfigList.setConfListName(logConfigLists.getConfListName());
logConfigList.setAccessScope(logConfigLists.getAccessScope());
logConfigList.setDefaultList(logConfigLists.getDefaultList()); 
jobSearch.setConfId(logConfigLists.getConfListId());
jobSearch.setConfigFlag("false");



return showForm(request, response, errors); 
}
try {



	  if(reload != null && reload.equals("true"))
	  {
		  if(jobSearch.getPagination()== null)
		  {
			  int pageNumber = 1;
			  int resultCount = 20;
			  if(jobSearch.getSearchResults().getValue() != null && (!jobSearch.getSearchResults().getValue().trim().equals("")))
			  {
			    	resultCount = Integer.parseInt(jobSearch.getSearchResults().getValue());
			  }
			  Pagination pagination = new Pagination(1,resultCount, pageNumber, 10);
			  jobSearch.setPagination(pagination);
			  jobService.searchJobOrder(jobSearch,pageNumber,reqForm,sortFlag);
		  }
			 
		  if(tabName != null && !tabName.equals(""))
		  {
			  if(tabName.equals("main"))
			  jobSearch.setMainTabFlag("true");
			  if(tabName.equals("dispatch"))
				  jobSearch.setDispatchTabFlag("true");
			  if(tabName.equals("billing"))
				  jobSearch.setBillingTabFlag("true");
			  if(tabName.equals("processlog"))
				  jobSearch.setProcessLogTabFlag("true");
		    request.setAttribute("searchResult", tabName);
		  }
		    else
		    request.setAttribute("searchResult", "main");
		  jobSearch.setShowAllFlag("false");
		  
		  
		  return showForm(request, response, errors); 
	  }
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
	  if(showAllFlag != null && showAllFlag.equals("true") && jobSearch.getResults()!= null && jobSearch.getResults().size()>0)
	  {
		    String pageNumberStr = request.getParameter("pageNumber");
		    jobSearch.setShowAllExcel("true");
			if(reqForm == null)
				reqForm = "jobLog";
			if(reqForm != null && reqForm.trim().equals(""))
				reqForm = "jobLog";
			int pageNumber = 1;
			if(pageNumberStr != null)
			  pageNumber=Integer.parseInt(pageNumberStr);
			 
			  int resultCount = 20;
			  if(jobSearch.getSearchResults().getValue() != null && (!jobSearch.getSearchResults().getValue().trim().equals("")))
			  {
			    	resultCount = Integer.parseInt(jobSearch.getSearchResults().getValue());
			  }
			Pagination pagination = new Pagination(1,resultCount, pageNumber, 10);
			jobSearch.setPagination(pagination);
			jobService.searchJobOrder(jobSearch,pageNumber,reqForm,sortFlag);
		    request.setAttribute("command", jobSearch);
		    request.setAttribute("searchResult","showall");
		    return showForm(request, response, errors); 
	  }
} catch(ServiceException e)
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

String noteFlag=request.getParameter("noteFlag");
if(noteFlag!=null && "addToNote".equals(noteFlag))
{
try {
 if(jobSearch.getPagination()== null)
 {
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
			  int resultCount = 20;
			  if(jobSearch.getSearchResults().getValue() != null && (!jobSearch.getSearchResults().getValue().trim().equals("")))
			  {
			    	resultCount = Integer.parseInt(jobSearch.getSearchResults().getValue());
			  }
			  Pagination pagination = new Pagination(1,resultCount, pageNumber, 10);
			  jobSearch.setPagination(pagination);
			  jobService.searchJobOrder(jobSearch,pageNumber,reqForm,sortFlag);
		  }


		String tabFlag=request.getParameter("tabFlag");
		String rowNo=request.getParameter("rowNum");
		int num=Integer.parseInt(rowNo);
		List jobs=jobSearch.getResults();
		addJobContract=(AddJobContract)jobs.get(num);
		JobContractNote jobContractNote=new JobContractNote();
       Date date= new Date();

if(((AddJobContract)jobs.get(num)).getJobContract().getJobNumber()!=null)
	{     
	 if(((AddJobContract)jobs.get(num)).getAppendNote()!=null && (!((AddJobContract)jobs.get(num)).getAppendNote().trim().equals("")))
	  {
          if(((AddJobContract)jobs.get(num)).getJobOrder().getJobNumber()!=null && ((AddJobContract)jobs.get(num)).getJobContract().getContractCode()!=null &&((AddJobContract)jobs.get(num)).getJobContract().getId()!=0)
		 {	      
			  if(addJobContract.getAddNote()!=null && !addJobContract.getAddNote().trim().equals(""))
			  {
			  addJobContract.setAddNote(SecurityUtil.getUser().getLoginName()+", "+DateUtil.formateJobDescriptionDate(date)+", "+DateUtil.getTimeFromDate(date)+":"+((AddJobContract)jobs.get(num)).getAppendNote()+"<br>"+addJobContract.getAddNote());		
              addJobContract.setAddUpdatedNote(((AddJobContract)jobs.get(num)).getAppendNote()+","+addJobContract.getAddUpdatedNote());
			  }
			 else
			 { addJobContract.setAddNote(SecurityUtil.getUser().getLoginName()+", "+DateUtil.formateJobDescriptionDate(date)+", "+DateUtil.getTimeFromDate(date)+":" +((AddJobContract)jobs.get(num)).getAppendNote());
               addJobContract.setAddUpdatedNote(((AddJobContract)jobs.get(num)).getAppendNote());
			 }

			}
        }
	}
jobSearch.setNoteFlag("none");
if(tabFlag.equals("showall")){
jobSearch.setShowAllFlag("true");
jobSearch.setShowAllExcel("true");
}
request.setAttribute("searchResult", tabFlag);
jobSearch.setTabFlag(tabFlag);
String columnflag=request.getParameter("columnFlag");
jobSearch.setColumnFlag(columnflag);
jobSearch.setAddJobContract(addJobContract);
return showForm(request,response,errors);

} catch(ServiceException e)
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
}




     List  jobs=new ArrayList();
	if((refreshing != null) && "true".equals(refreshing))
	{
	jobs=jobSearch.getResults();
	JobLog invJobLog = null;
	Date invRecEtaTime = null; 
	String latestInvoice = null; 
	
		for(int i=0;i<jobs.size();i++)
		{
         addJobContract=(AddJobContract)jobs.get(i);
		//JobContractNote jobContractNote=new JobContractNote();
		 
	if(((AddJobContract)jobs.get(i)).getJobContract().getJobNumber()!=null)
	{     
	   try {
       if(((AddJobContract)jobs.get(i)).getJobOrder().getJobNumber()!=null && ((AddJobContract)jobs.get(i)).getJobContract().getContractCode()!=null &&((AddJobContract)jobs.get(i)).getJobContract().getId()!=0)
		 {	 
  		 /*  if( addJobContract.getAddNote()!=null && ! addJobContract.getAddNote().trim().equals(""))
  			 {*/
    	   
		   if(addJobContract.getAddUpdatedNote()!=null && addJobContract.getAddUpdatedNote().trim().length()>0  &&  addJobContract.getAddNote()!=null && ! addJobContract.getAddNote().trim().equals(""))
			 {
		System.out.println("addJobContract.getAddNote()!=null");

		
			// String[] result = addJobContract.getAddNote().split("\\,");
		
			String[] result =addJobContract.getAddUpdatedNote().split("\\,");
			  for (int x=0; x<result.length; x++){				
					
               boolean noteExists = jobService.getJobContractNotesByNoteandId(((AddJobContract)jobs.get(i)).getJobContract().getId(),result[x].trim());
               if(noteExists==false){
            	   JobContractNote jobContractNote=new JobContractNote();
					jobContractNote.setNote(result[x]);
					jobContractNote.setNoteSummary(result[x]);
					Date date= new Date();
					jobContractNote.setAddedBy(SecurityUtil.getUser()); 
					jobContractNote.setAddedByUserName(SecurityUtil.getUser().getLoginName());
					jobContractNote.setDateTimeAdded(date);
					jobContractNote.setRelatedLine(String.valueOf(i+1));
					jobContractNote.setJobNumber(((AddJobContract)jobs.get(i)).getJobOrder().getJobNumber());
					jobContractNote.setJobContractId(((AddJobContract)jobs.get(i)).getJobContract().getId());
					jobContractNote.setContractCode(((AddJobContract)jobs.get(i)).getJobContract().getContract().getContractCode());
					jobContractNote.setNoteCategory(Constants.LOG_STR);
					JobContract jobContract = addJobContract.getJobContract();
	//						jobService.saveJobContractNote(jobContractNote);
					if(jobContract != null)
					{
						Set jcn = jobContract.getJobContractNotes();
						jcn.add(jobContractNote);
						jobContract.setJobContractNotes(jcn);
						// Setting jobContractInvoices for saving invoices if invoices appear in different pages
						if(i==0 || i==(jobs.size()-1))
						{
							List jobContractInv = null;
							if(jobContract.getJobContractInvoices() != null && jobContract.getJobContractInvoices().size()!= 0)
							{
								JobContractInvoice jobContractInvoice;
								jobContractInv = jobService.getLatestInvoiceByJobContractId(jobContract.getId());
								System.out.println("Invoice size ...="+jobContractInv.size());
								if(jobContractInv != null)
								{
									Set jobcInvoiceSet = new HashSet();
									for(int j=0;j<jobContractInv.size();j++)
									{
									jobcInvoiceSet.add((JobContractInvoice)jobContractInv.get(j));
									}
									jobContract.setJobContractInvoices(jobcInvoiceSet);
								}
							}
						}
						//end
						jobService.saveJobContract(jobContract);
                 }
				}
			 }
		     }
		   }

	   
       JobLog jobLog=addJobContract.getJobLog();
	   JobOrder jobOrder=addJobContract.getJobOrder();
if(((AddJobContract)jobs.get(i)).getJobOrder().getNominationTimeTz()!=null && !((AddJobContract)jobs.get(i)).getJobOrder().getNominationTimeTz().equals(""))
{
jobLog.setDispatchTz(((AddJobContract)jobs.get(i)).getJobOrder().getNominationTimeTz());	
jobLog.setProcessTz(((AddJobContract)jobs.get(i)).getJobOrder().getNominationTimeTz())	;
}
if(((AddJobContract)jobs.get(i)).getInspectorNotifyTime()!=null&&!((AddJobContract)jobs.get(i)).getInspectorNotifyTime().trim().equals("")&&(((AddJobContract)jobs.get(i)).getJobLog().getInspectorNotifyDt()!=null))
{jobLog.setInspectorNotifyTime(DateUtil.DateTime(((AddJobContract)jobs.get(i)).getJobLog().getInspectorNotifyDt(),((AddJobContract)jobs.get(i)).getInspectorNotifyTime().trim()));}
else{jobLog.setInspectorNotifyTime(null);}

if(((AddJobContract)jobs.get(i)).getInspectorArriveTime()!=null&&!((AddJobContract)jobs.get(i)).getInspectorArriveTime().trim().equals("")&&(((AddJobContract)jobs.get(i)).getJobLog().getInspectorArriveDt()!=null))
{jobLog.setInspectorArriveTime(DateUtil.DateTime(((AddJobContract)jobs.get(i)).getJobLog().getInspectorArriveDt(),((AddJobContract)jobs.get(i)).getInspectorArriveTime().trim()));}
else{jobLog.setInspectorArriveTime(null);}

if(((AddJobContract)jobs.get(i)).getArriveTime()!=null&&!((AddJobContract)jobs.get(i)).getArriveTime().trim().equals("")&&(((AddJobContract)jobs.get(i)).getJobLog().getArriveDt()!=null))
{jobLog.setArriveTime(DateUtil.DateTime(((AddJobContract)jobs.get(i)).getJobLog().getArriveDt(),((AddJobContract)jobs.get(i)).getArriveTime().trim()));}
else{jobLog.setArriveTime(null);}

if(((AddJobContract)jobs.get(i)).getDockTime()!=null &&!((AddJobContract)jobs.get(i)).getDockTime().trim().equals("")&&(((AddJobContract)jobs.get(i)).getJobLog().getDockDt()!=null))
{jobLog.setDockTime(DateUtil.DateTime(((AddJobContract)jobs.get(i)).getJobLog().getDockDt(),((AddJobContract)jobs.get(i)).getDockTime().trim()));}
else{jobLog.setDockTime(null);}

if(((AddJobContract)jobs.get(i)).getHoseOnTime()!=null &&!((AddJobContract)jobs.get(i)).getHoseOnTime().trim().equals("")&&(((AddJobContract)jobs.get(i)).getJobLog().getHoseOnDt()!=null))
{jobLog.setHoseOnTime(DateUtil.DateTime(((AddJobContract)jobs.get(i)).getJobLog().getHoseOnDt(),((AddJobContract)jobs.get(i)).getHoseOnTime().trim()));}
else{jobLog.setHoseOnTime(null);}


if(((AddJobContract)jobs.get(i)).getEstStartTime()!=null && !((AddJobContract)jobs.get(i)).getEstStartTime().trim().equals("")&&(((AddJobContract)jobs.get(i)).getJobLog().getEstStartDt()!=null))
{jobLog.setEstStartTime(DateUtil.DateTime(((AddJobContract)jobs.get(i)).getJobLog().getEstStartDt(),((AddJobContract)jobs.get(i)).getEstStartTime().trim()));}
else{jobLog.setEstStartTime(null);}

if(((AddJobContract)jobs.get(i)).getCommenceTime()!=null && !((AddJobContract)jobs.get(i)).getCommenceTime().trim().equals("")&&(((AddJobContract)jobs.get(i)).getJobLog().getCommenceDt()!=null))
{jobLog.setCommenceTime(DateUtil.DateTime(((AddJobContract)jobs.get(i)).getJobLog().getCommenceDt(),((AddJobContract)jobs.get(i)).getCommenceTime().trim()));}
else{jobLog.setCommenceTime(null);}

if(((AddJobContract)jobs.get(i)).getDelayTime()!=null && !((AddJobContract)jobs.get(i)).getDelayTime().trim().equals("")&&(((AddJobContract)jobs.get(i)).getJobLog().getDelayDt()!=null))
{jobLog.setDelayTime(DateUtil.DateTime(((AddJobContract)jobs.get(i)).getJobLog().getDelayDt(),((AddJobContract)jobs.get(i)).getDelayTime().trim()));}
else{jobLog.setDelayTime(null);}

if(((AddJobContract)jobs.get(i)).getDelayEndTime()!=null && !((AddJobContract)jobs.get(i)).getDelayEndTime().trim().equals("")&&(((AddJobContract)jobs.get(i)).getJobLog().getDelayEndDt()!=null))
{jobLog.setDelayEndTime(DateUtil.DateTime(((AddJobContract)jobs.get(i)).getJobLog().getDelayEndDt(),((AddJobContract)jobs.get(i)).getDelayEndTime().trim()));}
else{jobLog.setDelayEndTime(null);}

if(((AddJobContract)jobs.get(i)).getEstCompleteTime()!=null && !((AddJobContract)jobs.get(i)).getEstCompleteTime().trim().equals("")&&(((AddJobContract)jobs.get(i)).getJobLog().getEstCompleteDt()!=null))
{jobLog.setEstCompleteTime(DateUtil.DateTime(((AddJobContract)jobs.get(i)).getJobLog().getEstCompleteDt(),((AddJobContract)jobs.get(i)).getEstCompleteTime().trim()));}
else{jobLog.setEstCompleteTime(null);}


if(((AddJobContract)jobs.get(i)).getCompleteTime()!=null &&!((AddJobContract)jobs.get(i)).getCompleteTime().trim().equals("")&&(((AddJobContract)jobs.get(i)).getJobLog().getCompleteDt()!=null))
{jobLog.setCompleteTime(DateUtil.DateTime(((AddJobContract)jobs.get(i)).getJobLog().getCompleteDt(),((AddJobContract)jobs.get(i)).getCompleteTime().trim()));}
else{jobLog.setCompleteTime(null);}

if(((AddJobContract)jobs.get(i)).getHoseOffTime()!=null &&!((AddJobContract)jobs.get(i)).getHoseOffTime().trim().equals("")&&(((AddJobContract)jobs.get(i)).getJobLog().getHoseOffDt()!=null))
{jobLog.setHoseOffTime(DateUtil.DateTime(((AddJobContract)jobs.get(i)).getJobLog().getHoseOffDt(),((AddJobContract)jobs.get(i)).getHoseOffTime().trim()));}
else{jobLog.setHoseOffTime(null);}

if(((AddJobContract)jobs.get(i)).getReleaseTime()!=null && !((AddJobContract)jobs.get(i)).getReleaseTime().trim().equals("")&&(((AddJobContract)jobs.get(i)).getJobLog().getReleaseDt()!=null))
{jobLog.setReleaseTime(DateUtil.DateTime(((AddJobContract)jobs.get(i)).getJobLog().getReleaseDt(),((AddJobContract)jobs.get(i)).getReleaseTime().trim()));}
else{jobLog.setReleaseTime(null);}

if(((AddJobContract)jobs.get(i)).getSampleReceiveTime()!=null &&!((AddJobContract)jobs.get(i)).getSampleReceiveTime().trim().equals("")&&(((AddJobContract)jobs.get(i)).getJobLog().getSampleReceiveDt()!=null))
{jobLog.setSampleReceiveTime(DateUtil.DateTime(((AddJobContract)jobs.get(i)).getJobLog().getSampleReceiveDt(),((AddJobContract)jobs.get(i)).getSampleReceiveTime().trim()));}
else{jobLog.setSampleReceiveTime(null);}

if(((AddJobContract)jobs.get(i)).getPrelimReportTime()!=null &&!((AddJobContract)jobs.get(i)).getPrelimReportTime().trim().equals("")&&(((AddJobContract)jobs.get(i)).getJobLog().getPrelimReportDt()!=null))
{jobLog.setPrelimReportTime(DateUtil.DateTime(((AddJobContract)jobs.get(i)).getJobLog().getPrelimReportDt(),((AddJobContract)jobs.get(i)).getPrelimReportTime().trim()));}
else{jobLog.setPrelimReportTime(null);}

if(((AddJobContract)jobs.get(i)).getSampleShipTime()!=null &&!((AddJobContract)jobs.get(i)).getSampleShipTime().trim().equals("")&&(((AddJobContract)jobs.get(i)).getJobLog().getSampleShipDt()!=null))
{jobLog.setSampleShipTime(DateUtil.DateTime(((AddJobContract)jobs.get(i)).getJobLog().getSampleShipDt(),((AddJobContract)jobs.get(i)).getSampleShipTime().trim()));}
else{jobLog.setSampleShipTime(null);}

if(((AddJobContract)jobs.get(i)).getFinalReportTime()!=null && !((AddJobContract)jobs.get(i)).getFinalReportTime().trim().equals("")&&(((AddJobContract)jobs.get(i)).getJobLog().getFinalReportDt()!=null))
{jobLog.setFinalReportTime(DateUtil.DateTime(((AddJobContract)jobs.get(i)).getJobLog().getFinalReportDt(),((AddJobContract)jobs.get(i)).getFinalReportTime().trim()));}
else{jobLog.setFinalReportTime(null);}

if(((AddJobContract)jobs.get(i)).getDistributeTime()!=null && !((AddJobContract)jobs.get(i)).getDistributeTime().trim().equals("")&&(((AddJobContract)jobs.get(i)).getJobLog().getDistributeDt()!=null))
{jobLog.setDistributeTime(DateUtil.DateTime(((AddJobContract)jobs.get(i)).getJobLog().getDistributeDt(),((AddJobContract)jobs.get(i)).getDistributeTime().trim()));}
else{jobLog.setDistributeTime(null);}

if(((AddJobContract)jobs.get(i)).getJobOrder().getEtaDate()!=null)
{
	if(((AddJobContract)jobs.get(i)).getEtaTime()!=null && !((AddJobContract)jobs.get(i)).getEtaTime().trim().equals(""))
	{
		String etatime=((AddJobContract)jobs.get(i)).getEtaTime().trim();
		Date etaTime=DateUtil.getConvertedDateTime(((AddJobContract)jobs.get(i)).getJobOrder().getEtaDate(),etatime,((AddJobContract)jobs.get(i)).getJobOrder().getEtaTimeTz(),Constants.TIME_ZONE);
		jobOrder.setEtaTime(etaTime);
	}
	else{jobOrder.setEtaTime(null);}
}
JobContract jobContract=addJobContract.getJobContract();
jobLog.setUid20(jobContract.getUid20());
jobLog.setJobContract(jobContract);
jobLog.setJobOrder(jobOrder);
//saving joblog if invoices prasent
if(jobContract.getJobContractInvoices() != null && jobContract.getJobContractInvoices().size()!= 0)
{
	Set jobcInvoiceSet = jobContract.getJobContractInvoices();
	List jobContractInv = null;
	if(jobcInvoiceSet != null && jobcInvoiceSet.size()!=0)
	{
		JobContractInvoice jobContractInvoice;
		jobContractInv = jobService.getLatestInvoiceByJobContractId(jobContract.getId());
	    if(jobContractInv != null && jobContractInv.size()>0)
	   	latestInvoice = ((JobContractInvoice)jobContractInv.get(0)).getInvoice();
	  
	}
	if(addJobContract.getLatestJobContractInvoice() != null && addJobContract.getLatestJobContractInvoice().getInvoice()!= null && 
			addJobContract.getLatestJobContractInvoice().getInvoice().trim().equals(latestInvoice))
	{
		//System.out.println("invoice equal");
		jobLog = jobService.addJobLog(jobLog);
		invJobLog = jobService.getJobLogById(jobLog.getId());
	} else
	{
		 if(i>0)
		 {
			 AddJobContract addJobCon1 = (AddJobContract)jobs.get(i);
			 AddJobContract addJobCon2 = (AddJobContract)jobs.get(i-1);
			 if(addJobCon1.getJobContract().getId()!= addJobCon2.getJobContract().getId())
			 {
				 invJobLog = null;
			 }
		 }
		if(invJobLog == null)
		{
			//setting JobContractInvoices for last record if latest invoice appear in different page
			if(i==(jobs.size()-1))
			{
				jobLog = jobService.addJobLog(jobLog);
			} else {
			jobLog = null;
			}
		}
		else
		{
			jobLog = invJobLog;
		}
	}
	if(jobContractInv != null)
	{
		Set jobcInvoiceSets = new HashSet();
		for(int j=0;j<jobContractInv.size();j++)
		jobcInvoiceSets.add((JobContractInvoice)jobContractInv.get(j));
		jobContract.setJobContractInvoices(jobcInvoiceSets);
	}
} //end
else
{
	invJobLog = null;
//	 test 
	if(jobLog != null)
	{
	System.out.println("JobLog IdBefore Save="+jobLog.getId());
	}
	jobLog = jobService.addJobLog(jobLog); 
}

System.out.println("JObCOntract invoice size before save jobContract="+jobContract.getJobContractInvoices().size());
//end
addJobContract.setJobLog(jobLog);
jobContract.setJobLog(jobLog);
//jobContract=jobService.saveJobContractInsp(jobContract);
jobContract=jobService.saveJobContracts(jobContract);

Date d=jobOrder.getEtaDate();
Date d1=jobOrder.getEtaTime();
// Setting etaTime for invoices records
if(addJobContract.getLatestJobContractInvoice() != null && jobContract.getJobContractInvoices().size()>1)
{
	Date etaTime = jobOrder.getEtaTime();
	if(etaTime != null)
	invRecEtaTime = etaTime;
	else
	jobOrder.setEtaTime(invRecEtaTime);
	d1=invRecEtaTime;
}
//end
List jC=jobService.getJobOrdersByJobNumberWithJobContracts(((AddJobContract)jobs.get(i)).getJobOrder().getJobNumber());
for(int k=0;k<jC.size();k++)
{
jobOrder=(JobOrder)jC.get(k);
Set st =jobOrder.getJobContracts();
if(st.size()>0)
{
   Iterator itr =st.iterator();
	if(itr.hasNext())
	{
	 jobContract=(JobContract)itr.next();
	jobOrder.getJobContracts().add(jobContract);
		}
	}
        
}

if(jobOrder.getEtaDate()!=null && !jobOrder.getEtaDate().equals(""))
{
 if(!jobOrder.getEtaDate().equals(d))
		{jobOrder.setEtaDate(d);}
}
else{jobOrder.setEtaDate(d);}
if(jobOrder.getEtaTime()!=null && !jobOrder.getEtaTime().equals(""))
{
		if(!jobOrder.getEtaTime().equals(d1))
		{jobOrder.setEtaTime(d1);}
}
else
{
	jobOrder.setEtaTime(d1);}
//commented on 27/01/09 because it's grayed out bread crumb link
//jobOrder.setPageNumber(Integer.valueOf(1));
jobService.updateJobOrder(jobOrder);
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
 }//check for job number not null
}//for loop
 
jobSearch.setRefreshing("false");
//return showForm(request, response, errors);
}




/*  mail sending code*/
String jmail=request.getParameter("jmail");
if((jmail!=null) && "true".equals(jmail))
{
jobs=jobSearch.getResults();
	for(int i=0;i<jobs.size();i++)
	 {
		if(request.getParameter("mailCheck"+i)!=null && request.getParameter("mailCheck"+i).equals("Y"))
		{
	 String to="sasidhar.gudivada@patni.com";
	 JavaMailSender sender = (JavaMailSender) ServiceLocator.getInstance().getBean("sender");
	 VelocityMimeMessagePreparator preparator =(VelocityMimeMessagePreparator) ServiceLocator.getInstance().getBean("passwordMailPreparator");
	 PropertyConfig propertyConfig = (PropertyConfig)ServiceLocator.getInstance().getBean("propertyConfig");
	 Map data = new HashMap();
	 String randomPassword="";
	 data.put("jobSearch",(AddJobContract)jobs.get(i));
	 data.put("password", randomPassword);
	 preparator.setTo(to);
	 preparator.setData(data);
     sender.send(preparator);
		}
	}
   jobSearch.setJmail("false");
   
	return showForm(request, response, errors);
}
/* mail sending code ends here*/



/* excel download code starts here*/
if(reqForm == null)
	reqForm = "jobLog";
if(reqForm != null && reqForm.trim().equals(""))
	reqForm = "jobLog";
//String jxcel=request.getParameter("jxcel");



   if((jxcel!=null) && "true".equals(jxcel))
   {
	    try {
		int pageNumber=-1;
		jobSearch.setPagination(null);
      jobService.searchJobOrder(jobSearch,pageNumber,reqForm,sortFlag);
		jobs=jobSearch.getResults();
		//jobs=jobSearch.getTotalResults();
		    
		try
		{
		 //response.setContentType("application/vnd.ms-excel");
		 response.setContentType("application/download");
		 //response.setHeader("Content-disposition","attachment; filename=\"samplejoblog.xls\"");
	 	response.setHeader("Content-Disposition", "attachment; filename=\"samplejoblog.xls\"");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0");

		
		HSSFWorkbook workBook = new HSSFWorkbook();
		HSSFSheet main=null;
		HSSFSheet dispatch=null;  
		HSSFSheet billing=null;  
		HSSFSheet processlog=null;  
	
		HSSFFont font = workBook.createFont();
	    font.setBoldweight(font.BOLDWEIGHT_BOLD);
	    HSSFCellStyle cellStyle = workBook.createCellStyle();
	    cellStyle.setFont(font);

		HSSFRow headerRow;
		HSSFCell headerCell ;

		PropertyResourceBundle pRB = (PropertyResourceBundle) ResourceBundle.getBundle("TrackerRes");
		String path="";
		
		ArrayList mList = null;
		ArrayList dList = null;
		ArrayList bList = null;
		ArrayList pList = null;	    
		//jobSearch.setShowAllExcel("true");

	 if (jobSearch.getShowAllExcel().equalsIgnoreCase("false")){
	    
		main=workBook.createSheet("Main");
		dispatch=workBook.createSheet("Dispatch");
		billing=workBook.createSheet("Billing");
		processlog=workBook.createSheet("ProcessLog");
		
		mList = (ArrayList)jobSearch.getMainColumns();
		dList = (ArrayList)jobSearch.getDispatchColumns();
		bList = (ArrayList)jobSearch.getBillingColumns();
		pList = (ArrayList)jobSearch.getProcessColumns();
		
		if ((mList != null) && (mList.size()>0)) {
			if (mList.contains("mH_Col13")) {
				mList.remove("mH_Col13");
			}
			if (mList.contains("mH_Col19")) {
				mList.remove("mH_Col19");
			}

		}

		if(pRB!= null)
		{
			path=pRB.getString("mH_Col1");
			headerRow = main.createRow((short) 0); headerCell =   headerRow.createCell((short) 0); headerCell.setCellValue(path); headerCell.setCellStyle(cellStyle);

			for(int k=0;k<mList.size();k++)
			{
				path=pRB.getString(mList.get(k).toString());
				headerRow = main.createRow((short) 0); headerCell =   headerRow.createCell((short) (k+1)); headerCell.setCellValue(path); headerCell.setCellStyle(cellStyle);
			}

			path=pRB.getString("mH_Col1");
			headerRow = dispatch.createRow((short) 0); headerCell =   headerRow.createCell((short) 0); headerCell.setCellValue(path); headerCell.setCellStyle(cellStyle);

			for(int d=0;d<dList.size();d++)
			{
			path=pRB.getString(dList.get(d).toString());
			headerRow = dispatch.createRow((short) 0); headerCell =   headerRow.createCell((short) (d+1)); headerCell.setCellValue(path); headerCell.setCellStyle(cellStyle);
			}

			path=pRB.getString("mH_Col1");
			headerRow = billing.createRow((short) 0); headerCell =   headerRow.createCell((short) 0); headerCell.setCellValue(path); headerCell.setCellStyle(cellStyle);
		
			for(int b=0;b<bList.size();b++)
			{
			path=pRB.getString(bList.get(b).toString());
			headerRow = billing.createRow((short) 0); headerCell =   headerRow.createCell((short) (b+1)); headerCell.setCellValue(path); headerCell.setCellStyle(cellStyle);
			}

			path=pRB.getString("mH_Col1");
			headerRow = processlog.createRow((short) 0); headerCell =   headerRow.createCell((short) 0); headerCell.setCellValue(path); headerCell.setCellStyle(cellStyle);

			for(int p=0;p<pList.size();p++)
			{
			path=pRB.getString(pList.get(p).toString());
			headerRow = processlog.createRow((short) 0); headerCell =   headerRow.createCell((short) (p+1)); headerCell.setCellValue(path); headerCell.setCellStyle(cellStyle);
			}
		}
	 } else {
			main=workBook.createSheet("Show All");
			mList = new ArrayList();
            //Commented for Itrack Issue#58291
			//for (int i=2;i<=30;i++) {
			//for Itrack Issue#58291
			//Commented for Itrack Issue#127441
			//for (int i=2;i<=32;i++) {
			// START : #127441
			for (int i=2;i<=33;i++) {	
			// END : #127441
				if (i != 13 && i != 19) {
					mList.add("mH_Col"+i);
				}
			}
			for (int i=2;i<=31;i++) {
				mList.add("dH_Col"+i);
			}
			for (int i=2;i<=10;i++) {
				mList.add("bH_Col"+i);
			}
			for (int i=2;i<=12;i++) {
				mList.add("pH_Col"+i);
			}

			
			if(pRB!= null)
			{
				path=pRB.getString("mH_Col1");
				headerRow = main.createRow((short) 0); headerCell =   headerRow.createCell((short) 0); headerCell.setCellValue(path); headerCell.setCellStyle(cellStyle);

				for(int k=0;k<mList.size();k++)
				{
					path=pRB.getString(mList.get(k).toString());
					headerRow = main.createRow((short) 0); headerCell =   headerRow.createCell((short) (k+1)); headerCell.setCellValue(path); headerCell.setCellStyle(cellStyle);
				}
				
			}

	 }
		
		int j=1;
		for(int i=0;i<jobs.size();i++){ 
		 if (jobSearch.getShowAllExcel().equalsIgnoreCase("true")){
			populateTab(workBook,main,mList,jobs,j,i,jobService,dropdownService);
		 } else {
			populateTab(workBook,main,mList,jobs,j,i,jobService,dropdownService);
			populateTab(workBook,dispatch,dList,jobs,j,i,jobService,dropdownService);
			populateTab(workBook,billing,bList,jobs,j,i,jobService,dropdownService);
			populateTab(workBook,processlog,pList,jobs,j,i,jobService,dropdownService);
		 }
			j++;
		}
		
		OutputStream out = response.getOutputStream(); 
		workBook.write(out);
		out.flush();
		out.close();
		//response.reset();
		jobSearch.setJxcel("false");
		
		   } catch (FileNotFoundException fne) {
			  fne.printStackTrace();
		   } catch (IOException ioe) {
		        
		   }
		   jobSearch.setJxcel("false");
		    return showForm(request, response, errors);
		    
   } catch(ServiceException e)
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
  }

/*excel download ends here*/
    String pageNumberStr = request.getParameter("pageNumber");
   int pageNumber = 1;
   try {
      pageNumber = new Integer(pageNumberStr).intValue();
    }
    catch(Exception e)
    {
    }
    if(pageNumber <= 0) pageNumber = 1;
    int resultCount = 20;
    if(jobSearch.getSearchResults().getValue() != null && (!jobSearch.getSearchResults().getValue().trim().equals("")))
    {
    	resultCount = Integer.parseInt(jobSearch.getSearchResults().getValue());
    }
    	Pagination pagination = new Pagination(1,resultCount, pageNumber, 10);
    	jobSearch.setPagination(pagination);
    
	
	
	String fromJobid = jobSearch.getFromJobId().getValue();
    String toJobid = jobSearch.getToJobId().getValue();
    String branch = jobSearch.getBranch().getValue();
 
    if((fromJobid != null) && !"".equals(fromJobid.trim())&& ((toJobid != null) && !"".equals(toJobid.trim()))&& (branch != null) && !"".equals(branch.trim()))
    {
    	 try {
    	 String[] fromJob=fromJobid.trim().split("\\-");

         String fromStringPart= fromJob[0];
         
         String[] toJob = toJobid.trim().split("\\-");

         String toStringPart= toJob[0];
         if(!fromStringPart.equalsIgnoreCase(branch))
         {
        	 jobSearch.setTabFlag("criteria");
        	 errors.reject("invalid.fromJob.id.select.branch.associated.fromJobIderror",new Object[] {fromJobid, }, null);
			return showForm(request, response, errors);
         }
       JobOrder jobOrder = jobService.getJobOrderByJobNumber(fromJobid.trim().toUpperCase());
         if (jobOrder == null)
         {
        	 jobSearch.setTabFlag("criteria");
        	 errors.reject("invalid.fromJob.id.select.valid.branch.associated.fromJobIderror",new Object[] {fromJobid, }, null);
 			return showForm(request, response, errors);
         }
         if(!toStringPart.equalsIgnoreCase(branch))
         {
        	 jobSearch.setTabFlag("criteria");
        	 errors.reject("invalid.tojob.id.please.select.branch.associated.tojobid",new Object[] {toJobid, }, null);
			return showForm(request, response, errors);
         }
       jobOrder = jobService.getJobOrderByJobNumber(toJobid.trim().toUpperCase());
         if (jobOrder == null)
         {
        	 jobSearch.setTabFlag("criteria");
        	 errors.reject("invalid.tojob.id.please.select.valid.branch.associated.tojobid",new Object[] { toJobid, }, null);
 			return showForm(request, response, errors);
         }
	     } catch(ServiceException e)
	     {
	    	jobSearch.setTabFlag("criteria");
	        e.printStackTrace();
	        errors.reject(e.getMessage(), e.getParams(), null);
	        return showForm(request, response, errors);
	     } catch(Throwable t)
	     {
	    	jobSearch.setTabFlag("criteria");
	        t.printStackTrace();
	        errors.reject("generic.error", new Object[] {t.getMessage()}, null);
	        return showForm(request, response, errors);
	     }
    }
    if(reqForm == null)
    	reqForm = "jobLog";
    if(reqForm != null && reqForm.trim().equals(""))
    	reqForm = "jobLog";
    List sortOrder = jobSearch.getResultSortOrder();
  
 
   // try
   // {
	 jobSearch.setSearchFlag(searchFlag);
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
    
    // joblog configuration code
    List mainColumns = new ArrayList();
	List disPatchColumns = new ArrayList();
	List billingColumns = new ArrayList();
	List processColumns = new ArrayList();
	
	
	List mainHeaderColumns = new ArrayList();
	List dispatchHeaderColumns = new ArrayList();
	List billingHeaderColumns = new ArrayList();
	List processHeaderColumns = new ArrayList();
	
	
	
	List mainFreeze = new ArrayList();
	List dispatchFreeze = new ArrayList();
	List billingFreeze = new ArrayList();
	List processFreeze = new ArrayList();
	List hideColumns = new ArrayList();
	List hideAndSort = new ArrayList();
	List sortList = new ArrayList();
	List allFreezeColumns = new ArrayList();
	List allHeaderColumns = new ArrayList();
	//getting values from UI
    if(pageNumber == 1 && searchFlag != null && searchFlag.equals("true"))
    {
    	mainFreeze = jobSearch.getFreezedList1();
	    dispatchFreeze = jobSearch.getFreezedList2();
	    billingFreeze = jobSearch.getFreezedList3();
	    processFreeze = jobSearch.getFreezedList4();
	    hideColumns = jobSearch.getDisplayOrder();
	    hideAndSort = jobSearch.getHideAndSort();
	    sortList =jobSearch.getResultSortOrder();
	    
    	if(mainFreeze != null)
    	{
    		if(jobSearch.getSessionFreezedList1() != null && jobSearch.getSessionFreezedList1().size()>0)
    			jobSearch.getSessionFreezedList1().clear();
    		jobSearch.setSessionFreezedList1(mainFreeze);
    	}else
    	{
    		if(jobSearch.getSessionFreezedList1() != null && jobSearch.getSessionFreezedList1().size()>0)
    			jobSearch.getSessionFreezedList1().clear();
    	}
    	if(dispatchFreeze != null)
    	{
    		if(jobSearch.getSessionFreezedList2() != null && jobSearch.getSessionFreezedList2().size()>0)
    			jobSearch.getSessionFreezedList2().clear();
    		jobSearch.setSessionFreezedList2(dispatchFreeze);
    	}else
    	{
    		if(jobSearch.getSessionFreezedList2() != null && jobSearch.getSessionFreezedList2().size()>0)
    			jobSearch.getSessionFreezedList2().clear();
    	}
    	if(billingFreeze != null)
    	{
    		if(jobSearch.getSessionFreezedList3() != null && jobSearch.getSessionFreezedList3().size()>0)
    			jobSearch.getSessionFreezedList3().clear();
    		jobSearch.setSessionFreezedList3(billingFreeze);
    	}else
    	{
    		if(jobSearch.getSessionFreezedList3() != null && jobSearch.getSessionFreezedList3().size()>0)
    			jobSearch.getSessionFreezedList3().clear();
    	}
    	if(processFreeze != null)
    	{
    		if(jobSearch.getSessionFreezedList4() != null && jobSearch.getSessionFreezedList4().size()>0)
    			jobSearch.getSessionFreezedList4().clear();
    		jobSearch.setSessionFreezedList4(processFreeze);
    	}else
    	{
    		if(jobSearch.getSessionFreezedList4() != null && jobSearch.getSessionFreezedList4().size()>0)
    			jobSearch.getSessionFreezedList4().clear();
    	}
    	if(hideColumns != null)
    	{
    		if(jobSearch.getSessionHideColumns() != null && jobSearch.getSessionHideColumns().size()>0)
    			jobSearch.getSessionHideColumns().clear();
    		jobSearch.setSessionHideColumns(hideColumns);
    	}else
    	{
    		if(jobSearch.getSessionHideColumns() != null && jobSearch.getSessionHideColumns().size()>0)
    			jobSearch.getSessionHideColumns().clear();
    	}
    	if(hideAndSort != null)
    	{
    		if(jobSearch.getSessionHideAndSort() != null && jobSearch.getSessionHideAndSort().size()>0)
    			jobSearch.getSessionHideAndSort().clear();
    		jobSearch.setSessionHideAndSort(hideAndSort);
    	}else
    	{
    		if(jobSearch.getSessionHideAndSort() != null && jobSearch.getSessionHideAndSort().size()>0)
    			jobSearch.getSessionHideAndSort().clear();
    	}
    	/*if(sortList != null)
    	{
    		if(jobSearch.getSessionResultSortOrder() != null && jobSearch.getSessionResultSortOrder().size()>0)
    			jobSearch.getSessionResultSortOrder().clear();
    		jobSearch.setSessionResultSortOrder(sortList);
    	}else
    	{
    		if(jobSearch.getSessionResultSortOrder() != null && jobSearch.getSessionResultSortOrder().size()>0)
    			jobSearch.getSessionResultSortOrder().clear();
    	}*/
    }
    mainFreeze=jobSearch.getSessionFreezedList1();
    dispatchFreeze =jobSearch.getSessionFreezedList2();
    billingFreeze =jobSearch.getSessionFreezedList3();
    processFreeze =jobSearch.getSessionFreezedList4();
    hideColumns = jobSearch.getSessionHideColumns();
    hideAndSort = jobSearch.getSessionHideAndSort();
    sortList =jobSearch.getSessionResultSortOrder();
  
   //Removing Hide columns 
    if(hideAndSort != null && hideColumns != null)
    hideAndSort = removeColumns(hideAndSort,hideColumns);
    //end
    if(mainFreeze != null && mainFreeze.size()>0)
	  {
	    	for(int i=0;i<mainFreeze.size();i++)
	    	{
	    		String freezeColumn=(String)mainFreeze.get(i);
	    		if(freezeColumn != null && !freezeColumn.toUpperCase().equals("MH_COL1"))
	    		{
		    		allFreezeColumns.add(freezeColumn.trim());
		    		allHeaderColumns.add(freezeColumn.trim().toUpperCase());
	    		}
	    	}
	  }
//  Removing main freeze columns 
   /* if(hideAndSort != null && mainColumns != null)
    	hideAndSort = removeColumns(hideAndSort,mainColumns);*/
    if(hideAndSort != null && allFreezeColumns != null && allFreezeColumns.size()>0)
        hideAndSort = removeColumns(hideAndSort,allFreezeColumns);
//end
    if(dispatchFreeze != null && dispatchFreeze.size()>0)
    {
    	for(int i=0;i<dispatchFreeze.size();i++)
    	{
    		String freezeColumn=(String)dispatchFreeze.get(i);
    		if(freezeColumn != null && !freezeColumn.toUpperCase().equals("DH_COL1"))
    		{
	    		allFreezeColumns.add(freezeColumn.trim());
	    		allHeaderColumns.add(freezeColumn.trim().toUpperCase());
    		}
    	}
    }
//  Removing disPatch freeze columns 
/*if(hideAndSort != null && disPatchColumns != null)
	hideAndSort = removeColumns(hideAndSort,disPatchColumns);  */
if(hideAndSort != null && allFreezeColumns != null && allFreezeColumns.size()>0)
	hideAndSort = removeColumns(hideAndSort,allFreezeColumns);
//end
    if(billingFreeze != null && billingFreeze.size()>0)
    {
    	for(int i=0;i<billingFreeze.size();i++)
    	{
    		String freezeColumn=(String)billingFreeze.get(i);
    		if(freezeColumn != null && !freezeColumn.toUpperCase().equals("BH_COL1"))
    		{
	    		allFreezeColumns.add(freezeColumn.trim());
	    		allHeaderColumns.add(freezeColumn.trim().toUpperCase());
    		}
    	}
    }
//  Removing billing freeze columns     
   /* if(hideAndSort != null && billingColumns != null)
    	hideAndSort = removeColumns(hideAndSort,billingColumns);*/
    if(hideAndSort != null && allFreezeColumns != null && allFreezeColumns.size()>0)
    	hideAndSort = removeColumns(hideAndSort,allFreezeColumns);
 //end   
	    if(processFreeze != null && processFreeze.size()>0)
	    {
	    	for(int i=0;i<processFreeze.size();i++)
	    	{
	    		String freezeColumn=(String)processFreeze.get(i);
	    		if(freezeColumn != null && !freezeColumn.toUpperCase().equals("PH_COL1"))
	    		{
	    			allFreezeColumns.add(freezeColumn.trim());
		    		allHeaderColumns.add(freezeColumn.trim().toUpperCase());
	    		}
	    	}
	    }
//	  Removing process freeze columns   
	   /* if(hideAndSort != null && processColumns != null)
		hideAndSort = removeColumns(hideAndSort,processColumns);*/
		
		 if(hideAndSort != null && allFreezeColumns != null && allFreezeColumns.size()>0)
		    	hideAndSort = removeColumns(hideAndSort,allFreezeColumns);
//end
	// freeze columns adding
	  if(allFreezeColumns != null && allFreezeColumns.size() >0 && allHeaderColumns != null &&allHeaderColumns.size()>0)
	  {
		  for(int i=0;i<allFreezeColumns.size();i++)
		  {
			  mainColumns.add(allFreezeColumns.get(i));
			  disPatchColumns.add(allFreezeColumns.get(i));
			  billingColumns.add(allFreezeColumns.get(i));
			  processColumns.add(allFreezeColumns.get(i));
		  }
		  for(int i=0;i<allHeaderColumns.size();i++)
		  {
			  mainHeaderColumns.add(allHeaderColumns.get(i));
			  dispatchHeaderColumns.add(allHeaderColumns.get(i));
			  billingHeaderColumns.add(allHeaderColumns.get(i));
			  processHeaderColumns.add(allHeaderColumns.get(i));
		  }
	  }
	//end
//end
if(hideAndSort != null)
    {
    	String tabNames="";
    	
    	for(int i=0;i<hideAndSort.size();i++)
	   {
	    String HsListValue =(String)hideAndSort.get(i);
    	
    	int tabNameCount=0;
    	if(HsListValue != null && HsListValue.trim().equals("Main"))
    	{
    		tabNameCount=1;
    		tabNames="Main";
    		jobSearch.setMainTabFlag("true");
    	}
    	if(HsListValue != null && HsListValue.trim().equals("Dispatch"))
    	{
    		tabNameCount=1;
    		tabNames="Dispatch";
    		jobSearch.setDispatchTabFlag("true");
    	}
    	if(HsListValue != null && HsListValue.trim().equals("Billing"))
    	{
    		tabNameCount=1;
    		tabNames="Billing";
    		jobSearch.setBillingTabFlag("true");
    	}
    	if(HsListValue != null && HsListValue.trim().equals("Process Log"))
    	{
    		tabNameCount=1;
    		tabNames="Process Log";
    		jobSearch.setProcessLogTabFlag("true");
    	}
    	if(tabNames.equals("Main") && tabNameCount==0 && 
    			!HsListValue.trim().toUpperCase().equals("MH_COL1") && 
    			!HsListValue.trim().toUpperCase().equals("DH_COL1")&&
    			!HsListValue.trim().toUpperCase().equals("BH_COL1")&&
    			!HsListValue.trim().toUpperCase().equals("PH_COL1"))
    	{
			mainColumns.add(HsListValue.trim());
			mainHeaderColumns.add(HsListValue.trim().toUpperCase());
    	}
    	
    	if(tabNames.equals("Dispatch") && tabNameCount==0 && 
    			!HsListValue.trim().toUpperCase().equals("MH_COL1") && 
    			!HsListValue.trim().toUpperCase().equals("DH_COL1")&&
    			!HsListValue.trim().toUpperCase().equals("BH_COL1")&&
    			!HsListValue.trim().toUpperCase().equals("PH_COL1"))
    	{
			disPatchColumns.add(HsListValue.trim());
			dispatchHeaderColumns.add(HsListValue.trim().toUpperCase());
    	}
    	if(tabNames.equals("Billing") && tabNameCount==0 && 
    			!HsListValue.trim().toUpperCase().equals("MH_COL1") && 
    			!HsListValue.trim().toUpperCase().equals("DH_COL1")&&
    			!HsListValue.trim().toUpperCase().equals("BH_COL1")&&
    			!HsListValue.trim().toUpperCase().equals("PH_COL1"))
    	{
			billingColumns.add(HsListValue.trim());
			billingHeaderColumns.add(HsListValue.trim().toUpperCase());
    	}
    	if(tabNames.equals("Process Log") && tabNameCount==0 && 
    			!HsListValue.trim().toUpperCase().equals("MH_COL1") && 
    			!HsListValue.trim().toUpperCase().equals("DH_COL1")&&
    			!HsListValue.trim().toUpperCase().equals("BH_COL1")&&
    			!HsListValue.trim().toUpperCase().equals("PH_COL1"))
    	{
			processColumns.add(HsListValue.trim());
			processHeaderColumns.add(HsListValue.trim().toUpperCase());
    	}
	   }
    }
		/*new code added here by sasi*/
		 jobSearch.setShowAllTabFlag("true");
		/*  up to here*/
  jobSearch.setMainColumns(mainColumns);
  jobSearch.setDispatchColumns(disPatchColumns);
  jobSearch.setBillingColumns(billingColumns);
  jobSearch.setProcessColumns(processColumns);
 
  jobSearch.setMainHeaderColumns(mainHeaderColumns);
  jobSearch.setDispatchHeaderColumns(dispatchHeaderColumns);
  jobSearch.setBillingHeaderColumns(billingHeaderColumns);
  jobSearch.setProcessHeaderColumns(processHeaderColumns);
 
	String columnflag=request.getParameter("columnFlag");
	jobSearch.setColumnFlag(columnflag);
    request.setAttribute("command", jobSearch);
    if(tabName != null && !tabName.equals(""))
    {
    	if(tabName.equals("showall"))
    		jobSearch.setShowAllFlag("true");
    request.setAttribute("searchResult", tabName);
    }
    else
    request.setAttribute("searchResult", "main");
    request.setAttribute("command", jobSearch);
    
    return showForm(request, response, errors);  
    }

  
  
  private void populateTab(HSSFWorkbook wkBook, HSSFSheet tabName, ArrayList mList,List  jobs, int j,int i, JobService jobService,DropdownService dropdownService) {
		HSSFRow   row ;
		HSSFCell  cell;
		AddJobContract addJobCon = (AddJobContract)jobs.get(i);
		JobOrder jobOrd = addJobCon.getJobOrder();
		JobLog jobLog = addJobCon.getJobLog();
		JobContract jobCon = addJobCon.getJobContract();
		Contract con = jobCon.getContract();
		JobContractInvoice jobContractInvoice = addJobCon.getLatestJobContractInvoice();
		row = tabName.createRow(j);cell = row.createCell((short) 0);cell.setCellValue(jobOrd.getJobNumber());
		
		HSSFCellStyle cellTimeStyle = wkBook.createCellStyle();
		cellTimeStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("h:mm:ss AM/PM"));
		//cellDateStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("m/d/yy"));
		

		
		String colName = "";
		for(int k=1;k<=mList.size();k++)
		{
			
			colName = mList.get(k-1).toString();	
			if ( colName.equalsIgnoreCase("mH_Col2")) { 
				row = tabName.createRow(j);cell = row.createCell((short) k);
				if(jobOrd.getEtaDate()!=null){Date date=(jobOrd.getEtaDate());
				String s=DateUtil.formatDate(date,"MM/dd/yyyy"); cell.setCellValue(s);}else{cell.setCellValue("");}
				//if(jobOrd.getEtaDate()!=null){cell.setCellValue(jobOrd.getEtaDate());cell.setCellStyle(cellDateStyle);}else{cell.setCellValue("");}


			} else if (colName.equalsIgnoreCase("mH_Col3")) {
				row = tabName.createRow(j);cell = row.createCell((short) k);
				if(jobOrd.getEtaTime()!=null){cell.setCellValue(jobOrd.getEtaTime());cell.setCellStyle(cellTimeStyle);}else{cell.setCellValue("");}
				
			} else if (colName.equalsIgnoreCase("mH_Col4")) {
				row = tabName.createRow(j);cell = row.createCell((short) k);
				if(jobOrd.getEtaTimeTz()!=null && !jobOrd.getEtaTimeTz().trim().equals(""))
					{cell.setCellValue(jobOrd.getEtaTimeTz());}
			
			} else if (colName.equalsIgnoreCase("mH_Col5")) {
				row = tabName.createRow(j);cell = row.createCell((short) k);
		       if(jobOrd.getOperation()!= null)
		       {
		    	   Operation operation = jobService.getOperationByOperationCode(jobOrd.getOperation());
		    	   if(operation != null)
		    		   cell.setCellValue(operation.getDescription());
		    	   else
		    		   cell.setCellValue("");
		    	  // cell.setCellValue(jobService.getUiJobOperations(jobOrd.getOperation()));
			   }
			   else
				{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("mH_Col6")) {
				row = tabName.createRow(j);cell = row.createCell((short) k);
				if(jobOrd.getJobDescription()!=null && !jobOrd.getJobDescription().trim().equals(""))
						{cell.setCellValue(jobOrd.getJobDescription());}

			} else if (colName.equalsIgnoreCase("mH_Col7")) {

				row = tabName.createRow(j);cell = row.createCell((short) k);
				String dropdowntype="dispatchStatus";
				DropDowns dropDown=new DropDowns();
				if(jobLog.getDispatchStatus()!=null){
				dropDown=dropdownService.getDropdownByDropdownCodeAndType(jobLog.getDispatchStatus(),dropdowntype);
				if(dropDown!=null)
				{cell.setCellValue(dropDown.getFieldValue());}else{cell.setCellValue("");}}
				else{cell.setCellValue("");}
				
			} else if (colName.equalsIgnoreCase("mH_Col8")) {
			
				row = tabName.createRow(j);cell = row.createCell((short) k);
				if(jobOrd.getJobType()!=null && !jobOrd.getJobType().trim().equals(""))
					{cell.setCellValue(jobService.getUiJobTypes(jobOrd.getJobType()));}
				else
					{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("mH_Col9")) {
				
				row = tabName.createRow(j);cell = row.createCell((short) k);
				if(jobOrd.getServiceLocation()!=null){
				cell.setCellValue(jobOrd.getServiceLocation().getCity());
				}else{cell.setCellValue("");}
				
			} else if (colName.equalsIgnoreCase("mH_Col10")) {
	
				row = tabName.createRow(j);cell = row.createCell((short) k);
				if(jobOrd.getServiceLocationCode()!=null && !jobOrd.getServiceLocationCode().trim().equals(""))
				{
					if(jobOrd.getServiceLocation()!=null)
					{cell.setCellValue(jobOrd.getServiceLocation().getName());}
				}
				else
					{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("mH_Col11")) {
			
				row = tabName.createRow(j);cell = row.createCell((short) k);
				if(jobOrd.getVesselNames()!=null && !jobOrd.getVesselNames().trim().equals(""))
					{cell.setCellValue(jobOrd.getVesselNames());}
				else
					{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("mH_Col12")) {

				row = tabName.createRow(j);cell = row.createCell((short) k);
				if(jobOrd.getProductNames()!=null && !jobOrd.getProductNames().trim().equals(""))
					{cell.setCellValue(jobOrd.getProductNames());}
				else
		           {cell.setCellValue("");}
				/*
				row = tabName.createRow(j);cell = row.createCell((short) k);
				if(addJobCon.getJobContractNote().getNote()!=null && !addJobCon.getJobContractNote().getNote().trim().equals(""))
					{cell.setCellValue(addJobCon.getJobContractNote().getNote());}
				else
					{cell.setCellValue("");}
					
				*/
			} else if (colName.equalsIgnoreCase("mH_Col14")) {
				
				row = tabName.createRow(j);cell = row.createCell((short) k);
				if(addJobCon.getAddNote()!=null && !addJobCon.getAddNote().trim().equals(""))
					{cell.setCellValue(addJobCon.getAddNote());}
				else
					{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("mH_Col15")) {
				
				row = tabName.createRow(j);cell = row.createCell((short) k);
				if(jobCon.getCustCode()!=null && !jobCon.getCustCode().trim().equals(""))
					{cell.setCellValue(jobCon.getCustCode());}
				else
					{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("mH_Col16")) {
			
				row = tabName.createRow(j);cell = row.createCell((short) k);
				if(jobCon.getCustomer().getName()!=null && !jobCon.getCustomer().getName().trim().equals(""))
					{cell.setCellValue(jobCon.getCustomer().getName());}
				else
					{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("mH_Col17")) {
			
				row = tabName.createRow(j);cell = row.createCell((short) k);
				if(jobCon!=null)
					{cell.setCellValue(jobCon.getContactId());}
				//else

			} else if (colName.equalsIgnoreCase("mH_Col18")) {
				Contact contact = jobCon.getContact();	
				row = tabName.createRow(j);cell = row.createCell((short) k);
				if(contact.getFirstName()!=null&& contact.getLastName() != null){
				cell.setCellValue((contact.getFirstName())+" "+(contact.getLastName()));
				}
				else if(contact.getFirstName()!= null ){
		        cell.setCellValue((contact.getFirstName())+"");
		        }
				else if(contact.getLastName()!= null ){
		        cell.setCellValue(""+(contact.getLastName()));
		        }
				else
					{cell.setCellValue("");}
				//row = tabName.createRow(j);cell = row.createCell((short) 16);cell.setCellValue((jobCon.getContractCustContact().getContact().getFirstName())+" "+(jobCon.getContractCustContact().getContact().getLastName()));

			} else if (colName.equalsIgnoreCase("mH_Col20")) {
				
				row = tabName.createRow(j);cell = row.createCell((short) k);
				if(jobCon.getCustRefNum()!=null && !jobCon.getCustRefNum().trim().equals(""))
					{cell.setCellValue(jobCon.getCustRefNum());}
				else
					{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("mH_Col21")) {
				
				row = tabName.createRow(j);cell = row.createCell((short) k);
				if(jobCon.getInvoiceValue5()!=null && !jobCon.getInvoiceValue5().trim().equals(""))
					{cell.setCellValue(jobCon.getInvoiceValue5());}
				else
					{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("mH_Col22")) {
				
				row = tabName.createRow(j);cell = row.createCell((short) k);
				if(jobOrd.getShippingAgent()!= null)
				{cell.setCellValue(jobOrd.getShippingAgent().getName());}
				else{cell.setCellValue("");}
				
			} else if (colName.equalsIgnoreCase("mH_Col23")) {
				
				row = tabName.createRow(j);cell = row.createCell((short) k);
				if(jobOrd.getShippingAgent()!=null)
				{cell.setCellValue(jobOrd.getShippingAgent().getPhone());
				}
				else
				{cell.setCellValue("");
				}
				
			} else if (colName.equalsIgnoreCase("mH_Col24")) {
				
				row = tabName.createRow(j);cell = row.createCell((short) k);
				if(jobOrd.getTowingCompany()!= null)
				{cell.setCellValue(jobOrd.getTowingCompany().getName());
				}
				else
				{cell.setCellValue("");
				}
				
			} else if (colName.equalsIgnoreCase("mH_Col25")) {
				
				row = tabName.createRow(j);cell = row.createCell((short) k);
				if(jobOrd.getTowingCompany()!=null)
				{cell.setCellValue(jobOrd.getTowingCompany().getPhone());
				}else
				{cell.setCellValue("");
				}

			} else if (colName.equalsIgnoreCase("mH_Col26")) {
				
				row = tabName.createRow(j);cell = row.createCell((short) k);
				if(jobOrd.getJobFinishDate()!=null)
				{Date date1=(jobOrd.getJobFinishDate());String kk=DateUtil.formatDate(date1,"MM/dd/yyyy"); cell.setCellValue(kk);}else{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("mH_Col27")) {
				
				row = tabName.createRow(j);cell=row.createCell((short) k);
				if(con!= null)
				{
					//if(jobCon.getContractCustContact().getContractCust()!= null)
					//{
						//if(jobCon.getContractCustContact().getContractCust().getContract()!= null)
							cell.setCellValue(con.getDescription());
					//}
				}
				else
					{cell.setCellValue("");}

				
			} else if (colName.equalsIgnoreCase("mH_Col28")) {
				
		         row = tabName.createRow(j);cell=row.createCell((short) k);
				if(con!= null)
				{
					//if(jobCon.getContractCustContact().getContractCust()!= null)
					//{
						//if(jobCon.getContractCustContact().getContractCust().getContract()!= null)
							cell.setCellValue(jobService.getUiContractStatus(con.getStatus()));
					//}
				}
				else
					{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("mH_Col29")) {
				
				row = tabName.createRow(j);cell=row.createCell((short) k);
				if(jobOrd.getCreatedByUserName()!=null && !jobOrd.getCreatedByUserName().trim().equals(""))
					{cell.setCellValue(jobOrd.getCreatedByUserName());}
				else
					{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("mH_Col30")) {
				
				row = tabName.createRow(j);cell=row.createCell((short) k);
				if(jobOrd.getUpdatedByUserName()!=null && !jobOrd.getUpdatedByUserName().trim().equals(""))
						{cell.setCellValue(jobOrd.getUpdatedByUserName());}
				else
						{cell.setCellValue("");}
				
			} else if (colName.equalsIgnoreCase("mH_Col31")) {
				
				row = tabName.createRow(j);cell=row.createCell((short) k);
				if(jobOrd.getJobStatus()!=null && !jobOrd.getJobStatus().trim().equals(""))
						{cell.setCellValue(jobService.getUiJobStatus(jobOrd.getJobStatus()));}
				else
						{cell.setCellValue("");}
				
			}/*For ITrack#58291- START */ else if (colName.equalsIgnoreCase("mH_Col32")) {
				System.out.println(" CustomerNoteDetails...."+addJobCon.getCustomerNoteDetails());
				row = tabName.createRow(j);cell=row.createCell((short) k);
				if(addJobCon.getCustomerNoteDetails()!=null && !addJobCon.getCustomerNoteDetails().trim().equals(""))
						{cell.setCellValue(addJobCon.getCustomerNoteDetails());}
				else
						{cell.setCellValue("");}
				
			} /*For ITrack#58291- END */
			// START : #127441
			else if (colName.equalsIgnoreCase("mH_Col33")) {
				row = tabName.createRow(j);cell=row.createCell((short) k);
				if(addJobCon.getCancelNoteDetails()!=null && !addJobCon.getCancelNoteDetails().trim().equals(""))
						{cell.setCellValue(addJobCon.getCancelNoteDetails());}
				else
						{cell.setCellValue("");}
				
			}
			// END : #127441			
			else if (colName.equalsIgnoreCase("dH_Col2")) { 
				row = tabName.createRow(j);cell=row.createCell((short) k);
				if(jobLog.getCoordinator()!=null && !jobLog.getCoordinator().trim().equals(""))
					{cell.setCellValue(jobLog.getCoordinator());}
				else
					{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("dH_Col3")) { 

				row = tabName.createRow(j);cell=row.createCell((short) k);		
				if(jobLog.getInspector()!=null && !jobLog.getInspector().trim().equals(""))
					{cell.setCellValue(jobLog.getInspector());}
				else
			        {cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("dH_Col4")) { 
				
				row = tabName.createRow(j);cell = row.createCell((short) k);
				/*if(jobLog.getDispatchTz()!=null && !jobLog.getDispatchTz().trim().equals(""))
					{cell.setCellValue(jobLog.getDispatchTz());}*/
				if(jobOrd.getNominationTimeTz().trim()!=null && !jobOrd.getNominationTimeTz().trim().equals(""))
				{cell.setCellValue(jobOrd.getNominationTimeTz().trim());}				
				else
					{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("dH_Col5")) { 
				
				row = tabName.createRow(j);cell = row.createCell((short) k);
				if(jobLog.getInspectorNotifyDt()!=null)	{Date date1=(jobLog.getInspectorNotifyDt());String kk=DateUtil.formatDate(date1,"MM/dd/yyyy"); cell.setCellValue(kk);}else{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("dH_Col6")) { 
				
				row = tabName.createRow(j);cell = row.createCell((short) k);
				if(jobLog.getInspectorNotifyTime()!=null){cell.setCellValue(jobLog.getInspectorNotifyTime());cell.setCellStyle(cellTimeStyle);}else{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("dH_Col7")) { 
			
				row = tabName.createRow(j);cell = row.createCell((short) k);
				if(jobLog.getInspectorArriveDt()!=null)	{Date date1=(jobLog.getInspectorArriveDt());String kk=DateUtil.formatDate(date1,"MM/dd/yyyy"); cell.setCellValue(kk);}else{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("dH_Col8")) { 
				
				row = tabName.createRow(j);cell = row.createCell((short) k);
				if(jobLog.getInspectorArriveTime()!=null){cell.setCellValue(jobLog.getInspectorArriveTime());cell.setCellStyle(cellTimeStyle);}else{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("dH_Col9")) { 
				
				row = tabName.createRow(j);cell = row.createCell((short) k);
				if(jobLog.getArriveDt()!=null)	{Date date1=(jobLog.getArriveDt());String kk=DateUtil.formatDate(date1,"MM/dd/yyyy"); cell.setCellValue(kk);}else{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("dH_Col10")) { 
				
				row = tabName.createRow(j);cell = row.createCell((short) k);
				if(jobLog.getArriveTime()!=null){cell.setCellValue(jobLog.getArriveTime());cell.setCellStyle(cellTimeStyle);}else{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("dH_Col11")) { 
				
				row = tabName.createRow(j);cell = row.createCell((short) k);
				if(jobLog.getDockDt()!=null)	{Date date1=(jobLog.getDockDt());String kk=DateUtil.formatDate(date1,"MM/dd/yyyy"); cell.setCellValue(kk);}else{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("dH_Col12")) { 
				
				row = tabName.createRow(j);cell = row.createCell((short) k);
				if(jobLog.getDockTime()!=null){cell.setCellValue(jobLog.getDockTime());cell.setCellStyle(cellTimeStyle);}else{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("dH_Col13")) { 
				
				row = tabName.createRow(j);cell = row.createCell((short) k);
				if(jobLog.getHoseOnDt()!=null)	{Date date1=(jobLog.getHoseOnDt());String kk=DateUtil.formatDate(date1,"MM/dd/yyyy"); cell.setCellValue(kk);}else{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("dH_Col14")) { 
			
				row = tabName.createRow(j);cell = row.createCell((short)k);
				if(jobLog.getHoseOnTime()!=null){cell.setCellValue(jobLog.getHoseOnTime());cell.setCellStyle(cellTimeStyle);}else{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("dH_Col15")) { 
				
				row = tabName.createRow(j);cell = row.createCell((short)k);
				if(jobLog.getEstStartDt()!=null)	{Date date1=(jobLog.getEstStartDt());String kk=DateUtil.formatDate(date1,"MM/dd/yyyy"); cell.setCellValue(kk);}else{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("dH_Col16")) { 
				
				row = tabName.createRow(j);cell = row.createCell((short)k);
				if(jobLog.getEstStartTime()!=null){cell.setCellValue(jobLog.getEstStartTime());cell.setCellStyle(cellTimeStyle);}else{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("dH_Col17")) { 
				
				row = tabName.createRow(j);cell = row.createCell((short)k);
				if(jobLog.getCommenceDt()!=null)	{Date date1=(jobLog.getCommenceDt());String kk=DateUtil.formatDate(date1,"MM/dd/yyyy"); cell.setCellValue(kk);}else{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("dH_Col18")) { 
				
				row = tabName.createRow(j);cell = row.createCell((short)k);
				if(jobLog.getCommenceTime()!=null){cell.setCellValue(jobLog.getCommenceTime());cell.setCellStyle(cellTimeStyle);}else{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("dH_Col19")) { 
				
				row = tabName.createRow(j);cell = row.createCell((short)k);
				if(jobLog.getDelayDt()!=null)	{Date date1=(jobLog.getDelayDt());String kk=DateUtil.formatDate(date1,"MM/dd/yyyy"); cell.setCellValue(kk);}else{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("dH_Col20")) { 
				
				row = tabName.createRow(j);cell = row.createCell((short)k);
				if(jobLog.getDelayTime()!=null){cell.setCellValue(jobLog.getDelayTime());cell.setCellStyle(cellTimeStyle);}else{cell.setCellValue("");}
			} else if (colName.equalsIgnoreCase("dH_Col21")) { 
				
				row = tabName.createRow(j);cell = row.createCell((short)k);
				if(jobLog.getDelayEndDt()!=null)	{Date date1=(jobLog.getDelayEndDt());String kk=DateUtil.formatDate(date1,"MM/dd/yyyy"); cell.setCellValue(kk);}else{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("dH_Col22")) { 
				
				row = tabName.createRow(j);cell = row.createCell((short)k);
				if(jobLog.getDelayEndTime()!=null){cell.setCellValue(jobLog.getDelayEndTime());cell.setCellStyle(cellTimeStyle);}else{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("dH_Col23")) { 
				
				row = tabName.createRow(j);cell = row.createCell((short)k);
				if(jobLog.getEstCompleteDt()!=null)	{Date date1=(jobLog.getEstCompleteDt());String kk=DateUtil.formatDate(date1,"MM/dd/yyyy"); cell.setCellValue(kk);}else{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("dH_Col24")) { 
				
				row = tabName.createRow(j);cell = row.createCell((short)k);
				if(jobLog.getEstCompleteTime()!=null){cell.setCellValue(jobLog.getEstCompleteTime());cell.setCellStyle(cellTimeStyle);}else{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("dH_Col25")) { 
				
				row = tabName.createRow(j);cell = row.createCell((short)k);
				if(jobLog.getCompleteDt()!=null)	{Date date1=(jobLog.getCompleteDt());String kk=DateUtil.formatDate(date1,"MM/dd/yyyy"); cell.setCellValue(kk);}else{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("dH_Col26")) { 
				
				row = tabName.createRow(j);cell = row.createCell((short)k);
				if(jobLog.getCompleteTime()!=null){cell.setCellValue(jobLog.getCompleteTime());cell.setCellStyle(cellTimeStyle);}else{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("dH_Col27")) { 
				
				row = tabName.createRow(j);cell = row.createCell((short)k);
				if(jobLog.getHoseOffDt()!=null)	{Date date1=(jobLog.getHoseOffDt());String kk=DateUtil.formatDate(date1,"MM/dd/yyyy"); cell.setCellValue(kk);}else{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("dH_Col28")) { 
				
				row = tabName.createRow(j);cell = row.createCell((short)k);
				if(jobLog.getHoseOffTime()!=null){cell.setCellValue(jobLog.getHoseOffTime());cell.setCellStyle(cellTimeStyle);}else{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("dH_Col29")) { 
				
				row = tabName.createRow(j);cell = row.createCell((short)k);
				if(jobLog.getReleaseDt()!=null)	{Date date1=(jobLog.getReleaseDt());String kk=DateUtil.formatDate(date1,"MM/dd/yyyy"); cell.setCellValue(kk);}else{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("dH_Col30")) { 

				row = tabName.createRow(j);cell = row.createCell((short)k);
				if(jobLog.getReleaseTime()!=null){cell.setCellValue(jobLog.getReleaseTime());cell.setCellStyle(cellTimeStyle);}else{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("dH_Col31")) { 

				row = tabName.createRow(j);cell = row.createCell((short)k);
				if(jobLog.getSummaryDt()!=null)	{Date date1=(jobLog.getSummaryDt());String kk=DateUtil.formatDate(date1,"MM/dd/yyyy"); cell.setCellValue(kk);}else{cell.setCellValue("");}
				
			} else if (colName.equalsIgnoreCase("bH_Col2")) { 

				row = tabName.createRow(j);cell=row.createCell((short) k);
				if(jobCon.getBillingContact()!=null){
				cell.setCellValue(jobCon.getBillingContact().getId());
				}else
				{cell.setCellValue("");}
		    
		    } else if (colName.equalsIgnoreCase("bH_Col3")) { 

				row = tabName.createRow(j);cell = row.createCell((short) k);
				if(jobCon.getBillingContact()!=null){
				cell.setCellValue((jobCon.getBillingContact().getFirstName())+" "+(jobCon.getBillingContact().getLastName()));
				}else
					{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("bH_Col4")) { 
			
				row = tabName.createRow(j);cell=row.createCell((short) k);
				if(jobOrd.getJobStatus()!=null && !jobOrd.getJobStatus().trim().equals(""))
					{cell.setCellValue(jobService.getUiJobStatus(jobOrd.getJobStatus()));}
				else
					{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("bH_Col5")) { 
				
				row = tabName.createRow(j);cell=row.createCell((short) k);
				if(addJobCon.getLatestJobContractInvoice()!= null && addJobCon.getLatestJobContractInvoice().getInvoice()!= null && !addJobCon.getLatestJobContractInvoice().getInvoice().trim().equals(""))
					{cell.setCellValue(addJobCon.getLatestJobContractInvoice().getInvoice());}
				else
					{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("bH_Col6")) { 
				row = tabName.createRow(j);cell=row.createCell((short) k);
				if(jobCon.getInvoiceAmt()!= null && !jobCon.getInvoiceAmt().equals(""))
					{cell.setCellValue(jobCon.getInvoiceAmt());}
				else
					{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("bH_Col7")) { 
				row = tabName.createRow(j);cell=row.createCell((short) k);
				if(jobCon.getInvoiceDate()!=null)	{Date date1=(jobCon.getInvoiceDate());String kk=DateUtil.formatDate(date1,"MM/dd/yyyy"); cell.setCellValue(kk);}else{cell.setCellValue("");}
			} else if (colName.equalsIgnoreCase("bH_Col8")) { 
				row = tabName.createRow(j);cell=row.createCell((short) k);
				if(jobContractInvoice != null && jobContractInvoice.getGeneratedDateTime()!= null)
					{Date date1=(jobContractInvoice.getGeneratedDateTime());String kk=DateUtil.formatDate(date1,"MM/dd/yyyy"); cell.setCellValue(kk);}else{cell.setCellValue("");}
				
			} else if (colName.equalsIgnoreCase("bH_Col9")) { 
				row = tabName.createRow(j);cell=row.createCell((short) k);
				if(jobCon.getCreditInd()!= null && !jobCon.getCreditInd().equals(""))
					{cell.setCellValue(jobCon.getCreditInd());}
				else
					{cell.setCellValue("");}
			} else if (colName.equalsIgnoreCase("bH_Col10")) { 
				row = tabName.createRow(j);cell=row.createCell((short) k);
				if(jobOrd != null && jobOrd.getReOpenDate()!= null)
				{Date date1=(jobOrd.getReOpenDate());String kk=DateUtil.formatDate(date1,"MM/dd/yyyy"); cell.setCellValue(kk);}else{cell.setCellValue("");}
			} else if (colName.equalsIgnoreCase("pH_Col2")) { 
				row = tabName.createRow(j);cell = row.createCell((short) k);
				/*if(jobLog.getProcessTz()!=null && !jobLog.getProcessTz().trim().equals(""))
					{cell.setCellValue(jobLog.getProcessTz());}*/
				if(jobOrd.getNominationTimeTz().trim()!=null && !jobOrd.getNominationTimeTz().trim().equals(""))
				{cell.setCellValue(jobOrd.getNominationTimeTz().trim());}
					else
					{cell.setCellValue("");}
			
			} else if (colName.equalsIgnoreCase("pH_Col3")) { 

				row = tabName.createRow(j);cell = row.createCell((short) k);
				if(jobLog.getSampleReceiveDt()!=null)	{Date date1=(jobLog.getSampleReceiveDt());String kk=DateUtil.formatDate(date1,"MM/dd/yyyy"); cell.setCellValue(kk);}else{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("pH_Col4")) { 
			
				row = tabName.createRow(j);cell = row.createCell((short) k);
				if(jobLog.getSampleReceiveTime()!=null){cell.setCellValue(jobLog.getSampleReceiveTime());cell.setCellStyle(cellTimeStyle);}else{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("pH_Col5")) { 
			
				row = tabName.createRow(j);cell = row.createCell((short) k);
				if(jobLog.getPrelimReportDt()!=null)	{Date date1=(jobLog.getPrelimReportDt());String kk=DateUtil.formatDate(date1,"MM/dd/yyyy"); cell.setCellValue(kk);}else{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("pH_Col6")) { 
			
				row = tabName.createRow(j);cell = row.createCell((short)k);
				if(jobLog.getPrelimReportTime()!=null){cell.setCellValue(jobLog.getPrelimReportTime());cell.setCellStyle(cellTimeStyle);}else{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("pH_Col7")) { 
			
				row = tabName.createRow(j);cell = row.createCell((short)k);
				if(jobLog.getSampleShipDt()!=null)	{Date date1=(jobLog.getSampleShipDt());String kk=DateUtil.formatDate(date1,"MM/dd/yyyy"); cell.setCellValue(kk);}else{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("pH_Col8")) { 
			
				row = tabName.createRow(j);cell = row.createCell((short)k);
				if(jobLog.getSampleShipTime()!=null){cell.setCellValue(jobLog.getSampleShipTime());cell.setCellStyle(cellTimeStyle);}else{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("pH_Col9")) { 
			
				row = tabName.createRow(j);cell = row.createCell((short)k);
				if(jobLog.getFinalReportDt()!=null)	{Date date1=(jobLog.getFinalReportDt());String kk=DateUtil.formatDate(date1,"MM/dd/yyyy"); cell.setCellValue(kk);}else{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("pH_Col10")) { 

				row = tabName.createRow(j);cell = row.createCell((short)k);
				if(jobLog.getFinalReportTime()!=null){cell.setCellValue(jobLog.getFinalReportTime());cell.setCellStyle(cellTimeStyle);}else{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("pH_Col11")) { 

				row = tabName.createRow(j);cell = row.createCell((short)k);
				if(jobLog.getDistributeDt()!=null)	{Date date1=(jobLog.getDistributeDt());String kk=DateUtil.formatDate(date1,"MM/dd/yyyy"); cell.setCellValue(kk);}else{cell.setCellValue("");}

			} else if (colName.equalsIgnoreCase("pH_Col12")) { 

				row = tabName.createRow(j);cell = row.createCell((short)k);
				if(jobLog.getDistributeTime()!=null){cell.setCellValue(jobLog.getDistributeTime());cell.setCellStyle(cellTimeStyle);}else{cell.setCellValue("");}
			}
			
			colName = "";
		
		}// End Main loop
		
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

  String saveFlag=request.getParameter("saveFlag");
 if((saveFlag!=null) && "true".equals(saveFlag))
	  {
	 return true;
	  }

  String configFlag=request.getParameter("configFlag");
 if((configFlag!=null) && "true".equals(configFlag))
	  {
	 return true;
	  }

String noteFlag=request.getParameter("noteFlag");
if(noteFlag!=null && "addToNote".equals(noteFlag))
	  {return true;}

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
	 
	  UserService userService = (UserService) ServiceLocator.getInstance().getBean("userService");
	  User loginUser = userService.getUserByName(SecurityUtil.getUser().getLoginName());
	  String dateFormate = loginUser.getDateFormat();
	  if(dateFormate != null && !dateFormate.trim().equals("")){
		String[] pattern = dateFormate.split("/");
	       String month = pattern[1];
	       if(pattern[0].trim().equalsIgnoreCase("mm"))
	    	   dateFormate = "MM/dd/yyyy";  
	       if(month.trim().equalsIgnoreCase("mmm"))
	    	   dateFormate = "dd/MMM/yyyy"; 
	       if(month.trim().equalsIgnoreCase("mm"))
	    	   dateFormate = "dd/MM/yyyy"; 
		}else{
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
    String reqForm="jobLog";

	    
    JobLog jobLog = new JobLog();
    
   AddJobContract addJobContract = new AddJobContract();
   if(jobSearch == null)
    {	   
	    addJobContract.setJobLog(jobLog);
	    jobSearch.setAddJobContract(addJobContract);
	    jobSearch.setRowNum(rowNo);
    }
	User loginUser = userService.getUserByName(SecurityUtil.getUser().getLoginName());
	String dateFormat = loginUser.getDateFormat();
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
		dateFormat = Constants.DD_MM_YYYY_DATE_FORMAT; 
	} 
   jobSearch.setDateFormat(dateFormat);
   jobSearch.getJobType().setValue(Constants.INSPECTION_JOBTYPE);
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

	LogConfigList logConfigList=new LogConfigList();
	//LogConfigDetails logConfigDetail=new LogConfigDetails();
	//logConfigList.setLogConfigDetails(logConfigDetail);
	jobSearch.setLogConfigList(logConfigList);
	
	try { 
	long confListId=1;
	LogConfigList dfltConfigList = jobService.getDefaultConfigListByUserName(SecurityUtil.getUser().getLoginName());
	if(dfltConfigList!=null && !dfltConfigList.getConfListName().equalsIgnoreCase("")){
		confListId = dfltConfigList.getConfListId();
	}

	
List TabsandColumns=jobService.getColsandTabsByListId(confListId);
//List TabsandColumns=jobService.getColsandTabsByConfListId(confListId);
jobSearch.setDefaultHideandSort(TabsandColumns);
jobSearch.setConfId(confListId);
	} catch(ServiceException e)
    {
    	throw new ServiceException(e.getMessage(), e.getParams(), e);
    }
    catch(Throwable t)
    {
        t.printStackTrace();
        throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
    }
/*jobSearch.setFirstTab("Main");
jobSearch.setSecondTab("Dispatch");
jobSearch.setThirdTab("Billing");
jobSearch.setFourthTab("Process Log");*/

//Set Default search results value
if(jobSearch.getSearchResults() == null || jobSearch.getSearchResults().getValue() == null || jobSearch.getSearchResults().getValue().trim().equals(""))
	jobSearch.getSearchResults().setValue("20");
	
	// date formate code end
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
		    	  int noOfRecords = 20;
		    	  if(jobSrch.getSearchResults()!= null && jobSrch.getSearchResults().getValue()!= null && !jobSrch.getSearchResults().getValue().trim().equals(""))
		    	  noOfRecords = Integer.parseInt(jobSrch.getSearchResults().getValue());
		    	  Pagination pagination = new Pagination(1, noOfRecords, pageNumber,10);
		    	  jobSrch.setPagination(pagination);
		      }
	    	String sortFlag = jobSrch.getSortFlag();
	        LogConfigDetail logConfigDet;
	        try {
	        if(sortFlag != null && !sortFlag.equals(""))
	        {
	        	logConfigDet =jobService.getDbColumnValue(sortFlag);
	        	sortFlag="";
	    		if(logConfigDet != null)
	    		{
	    			sortFlag=logConfigDet.getDbColName();
	    		}
	        }
		    jobService.searchJobOrder(jobSrch,pageNumber,reqForm,sortFlag);
	        }catch(ServiceException e)
	         {
	         	throw new ServiceException(e.getMessage(), e.getParams(), e);
	         }
	         catch(Throwable t)
	         {
	             t.printStackTrace();
	             throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
	         }
		    request.setAttribute("command", jobSrch);
		    request.setAttribute("searchResult", jobSrch.getTabFlag());
		  //Setting Session null
		  request.getSession().setAttribute("MySearchJobOrder", null);
		    return jobSrch;
	   }else
	   {
	 	return jobSearch;
	   }
   }
//end
	 return jobSearch;
  }
private List removeColumns(List mainColumns,List removeColumns)
{
  for(int i=0;i<removeColumns.size();i++){
		for(int j=0;j<mainColumns.size();j++)
		  {
			  if(((String)mainColumns.get(j)).trim().equals(((String)removeColumns.get(i)).trim()))
				  mainColumns.remove(j);
		  }
	  }
  return mainColumns;
}
}
