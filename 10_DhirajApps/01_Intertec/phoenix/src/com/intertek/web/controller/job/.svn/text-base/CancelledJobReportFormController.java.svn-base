package com.intertek.web.controller.job;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.domain.CancelledJobsSearch;
import com.intertek.entity.JobOrder;
import com.intertek.entity.User;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.JobService;
import com.intertek.service.UserService;
import com.intertek.util.Constants;
import com.intertek.util.SecurityUtil;

public class CancelledJobReportFormController extends SimpleFormController
{
  public CancelledJobReportFormController() {
    super();
    setCommandClass(CancelledJobsSearch.class);
	setSessionForm(true);
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
   */
  /**
 * Name :onSubmit
 * Date :Dec 19, 2008
 * purpose :
 * @param request
 * @param response
 * @param command
 * @param errors
 * @return ModelAndView
 * @throws Exception
 */
protected ModelAndView onSubmit(
    HttpServletRequest request,
    HttpServletResponse response,
    Object command,
    BindException errors
  ) throws Exception
  {
	
	CancelledJobsSearch search = (CancelledJobsSearch)command;
	
	String pageNumberStr = request.getParameter("pageNumber");
	String sortFlag = request.getParameter("sortFlag");	
	String jxcel = request.getParameter("jxcel");	
	//String searchForm = request.getParameter("searchForm");
    JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
	  
    int pageNumber = 1;
    try
    {
      pageNumber = new Integer(pageNumberStr).intValue();
    }
    catch(Exception e)
    {
    }
    if(pageNumber <= 0) pageNumber = 1;

    
    Pagination pagination = new Pagination(1, 20, pageNumber, 10);
    
    String pageSort = request.getParameter("checkPageSort");
	 
	 boolean changeSortOrder = false; 
	 if(pageNumberStr != null && Integer.valueOf(pageNumberStr).intValue() == 1){
	 	if(sortFlag != null && !sortFlag.trim().equals("") && pageSort.equals("")){
	 		changeSortOrder = true;
	 	}else if(sortFlag == null || sortFlag.trim().equals("")){
	 		  search.setSortOrderFlag("");
	  			  search.setCurrentSortFlag("");
	  			  search.setPrevSortFlag("");
	 	}
	 }
	
	if(pageNumberStr != null && Integer.valueOf(pageNumberStr).intValue() == 1 && changeSortOrder){
		  
		  if(sortFlag != null && !sortFlag.trim().equals("")){
			  search.setCurrentSortFlag(sortFlag);
			  if(search.getCurrentSortFlag()!= null && search.getCurrentSortFlag().trim().equals(search.getPrevSortFlag())){
				  search.setSortOrderFlag("desc");
				  search.setPrevSortFlag("");
			  } else {
				  search.setPrevSortFlag(sortFlag);
				  search.setSortOrderFlag("asc");
			  }
		  } else {
			  search.setSortOrderFlag("");
			  search.setCurrentSortFlag("");
			  search.setPrevSortFlag("");
		  }
	 }
    search.setPagination(pagination);
	//search.setSearchForm(searchForm);
	
	try{
		jobService.searchCancelledJobDetails(search, sortFlag);
	}catch(ServiceException e)
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
	
	if(jxcel!=null && jxcel.equals("true"))
	{
	    List cancelledJobList = new ArrayList();
	    search.setPagination(null);
	    try
		{
	    	jobService.searchCancelledJobDetails(search, sortFlag);
		}
		catch(Exception e)
		{
			e.printStackTrace();
			errors.reject("search.job.error", new Object[] {e.getMessage()}, null);
			return showForm(request, response, errors);
		}
		cancelledJobList = search.getResults();
		if(cancelledJobList != null && cancelledJobList.size() != 0)
		{
			try
			{
				response.setContentType("application/download");
			 	response.setHeader("Content-Disposition", "attachment; filename=\"Reasons for Cancelled Jobs by BU/OU.xls\"");
				response.setHeader("Pragma", "public");
				response.setHeader("Cache-Control", "max-age=0");

				HSSFWorkbook workBook = new HSSFWorkbook();
				HSSFSheet main=null;
			
				main=workBook.createSheet("Reasons for Cancelled Jobs");
				HSSFRow   row ;
				HSSFCell  cell;
				HSSFRow headerRow;
				HSSFCell headerCell ;
				PropertyResourceBundle pRB = (PropertyResourceBundle) ResourceBundle.getBundle("TrackerRes");
				
				HSSFFont font = workBook.createFont();
			    font.setBoldweight(font.BOLDWEIGHT_BOLD);
			    HSSFCellStyle cellStyle = workBook.createCellStyle();
			    cellStyle.setFont(font);
				
				String path="";
				if(pRB!= null)
				{
					int  l=0;
					for(int k=0;k<=Constants.CJColumnSize;k++)
					{
						path = pRB.getString("CJHeading"+k);//added constants in TrackerRes.properties for status
						headerRow = main.createRow((short) 0);//for row 
						headerCell =   headerRow.createCell((short) l);//for column
						headerCell.setCellValue(path);//setting the value
						headerCell.setCellStyle(cellStyle);
						l++;
					}
				}


				int j=1;
				for(int i=0;i<cancelledJobList.size();i++)
				{ 
					JobOrder jobOrder = (JobOrder)cancelledJobList.get(i);
					row = main.createRow(j);cell = row.createCell((short) 0);
					if(jobOrder.getBuName()!=null && !jobOrder.getBuName().trim().equals("")){
						cell.setCellValue(jobOrder.getBuName());
					}else{
						cell.setCellValue("");
					}
					row = main.createRow(j);cell = row.createCell((short) 1);
					if(jobOrder.getBranchName()!=null && !jobOrder.getBranchName().trim().equals("")){
						cell.setCellValue(jobOrder.getBranchName());
					}else{
						cell.setCellValue("");
					}
					row = main.createRow(j);cell = row.createCell((short) 2);
					if(jobOrder.getJobNumber()!=null && !jobOrder.getJobNumber().trim().equals("")){
						cell.setCellValue(jobOrder.getJobNumber());
					}else{
						cell.setCellValue("");
					}
					row = main.createRow(j);cell = row.createCell((short) 3);
					if(search.getCancelNoteDetails()!=null && search.getCancelNoteDetails()[i]!=null && !search.getCancelNoteDetails()[i].trim().equals("")){
						cell.setCellValue(search.getCancelNoteDetails()[i]);
					}else{
						cell.setCellValue("");
					}
					j++;
					
				}
			
				OutputStream out = response.getOutputStream(); 
				workBook.write(out);
				out.flush();
				out.close();
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
		}
		search.setJxcel("false");
		return showForm(request, response, errors);
	}
	
	
   	request.setAttribute("command", search); 
    return showForm(request, response, errors);
  
    
  }


/* (non-Javadoc)
 * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest, org.springframework.web.bind.ServletRequestDataBinder)
 */
protected void initBinder(HttpServletRequest request,ServletRequestDataBinder binder) throws Exception
{ 
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
	binder.registerCustomEditor(java.util.Date.class, null,	new CustomDateEditor(dateFormat, true));
}


protected Object formBackingObject(HttpServletRequest request) throws Exception {
	
	CancelledJobsSearch cancelledJobs = new CancelledJobsSearch();
	UserService userService = (UserService) ServiceLocator.getInstance().getBean("userService");
    User loginUser = userService.getUserByName(SecurityUtil.getUser().getLoginName());
	
    String dateFormat = loginUser.getDateFormat();
	  if(dateFormat != null && !dateFormat.trim().equals(""))
	  {
		  System.out.println("2");
			String[] pattern = dateFormat.split("/");
		    String month = pattern[1];
		    if(pattern[0].trim().equalsIgnoreCase("mm"))
			    dateFormat = Constants.MM_DD_YYYY_DATE_FORMAT;  
		    if(month.trim().equalsIgnoreCase("mmm"))
		    	dateFormat = Constants.DD_MMM_YYYY_DATE_FORMAT; 
		    if(month.trim().equalsIgnoreCase("mm"))
			    dateFormat = Constants.DD_MM_YYYY_DATE_FORMAT; 
	  }else
		    dateFormat = Constants.DD_MM_YYYY_DATE_FORMAT; 
	  
	  cancelledJobs.setDateFormat(dateFormat);
	  cancelledJobs.getStatus().setValue("1");
	  
	
    if(!"POST".equals(request.getMethod()))
	{
    	  if(cancelledJobs.getBusinessUnit().getValue() == null)
	      {
	        String buName=SecurityUtil.getUser().getBusinessUnit().getName();
	        cancelledJobs.getBusinessUnit().setValue(buName);
	    	String branchName=SecurityUtil.getUser().getBranch().getName();
			cancelledJobs.getBranch().setValue(branchName);
		  }
	}
    
	return cancelledJobs;
}

}

 

