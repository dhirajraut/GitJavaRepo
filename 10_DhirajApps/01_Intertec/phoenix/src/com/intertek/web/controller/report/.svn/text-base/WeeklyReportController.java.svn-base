package com.intertek.web.controller.report;

import java.io.OutputStream;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.domain.AddWeeklyReport;
import com.intertek.entity.User;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.JobService;
import com.intertek.service.UserService;
import com.intertek.util.Constants;
import com.intertek.util.InvoiceUtil;
import com.intertek.util.SecurityUtil;

public class WeeklyReportController extends SimpleFormController
{
  public WeeklyReportController() {
    super();
    setCommandClass(AddWeeklyReport.class);
	setSessionForm(true);
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
   */

  protected ModelAndView onSubmit(HttpServletRequest request,HttpServletResponse response, Object command, BindException errors) throws Exception

  {
	AddWeeklyReport addWeeklyReport = (AddWeeklyReport) command;
	if(addWeeklyReport.getAsOfDate() == null)
    {
      errors.rejectValue("addWeeklyReport.asOfDate", "not.blank");
      return showForm(request, response, errors);
    }
    JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
	try{
		byte[] pdfData = jobService.generateWeeklyReport(addWeeklyReport , InvoiceUtil.getInvoiceDir(), InvoiceUtil.getJasperDir());
		if(pdfData != null){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String formattedTime = sdf.format(addWeeklyReport.getAsOfDate().getValue());
			String fileName = "WeeklyReport_"+ formattedTime+ ".pdf";
		    response.setHeader("content-disposition","attachment; filename=\"" + fileName+"\"");
		    response.setContentType("application/x-download");
		    response.setHeader("Pragma", "public");
			response.setHeader("Cache-Control", "max-age=0");
		    OutputStream os = response.getOutputStream();
		    os.write(pdfData);
		    os.close();
		    return showForm(request, response, errors);
		}else{
			errors.reject("weekly.report.error", new Object[] {"no result found"}, null);
			return showForm(request, response, errors);
		}
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
  }

	protected boolean suppressValidation(HttpServletRequest request)
	{
	
	  return super.suppressValidation(request);
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
			binder.registerCustomEditor(java.util.Date.class, null, new CustomDateEditor(dateFormat, true));
	}


	protected Object formBackingObject(HttpServletRequest request)
	throws Exception {
		UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
		User user = userService.getUserByName(SecurityUtil.getUser().getLoginName());
		AddWeeklyReport addWeeklyReport = new AddWeeklyReport();
		String dateFormat = user.getDateFormat();
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
			  dateFormat = dateFormat = Constants.DD_MM_YYYY_DATE_FORMAT; 
		addWeeklyReport.setDateFormat(dateFormat);
		addWeeklyReport.getBusinessUnit().setValue(user.getBuName());
		addWeeklyReport.getBranch().setValue(user.getBranchName());
		if(user.getCurrencyCd()!=null)
			addWeeklyReport.getCurrency().setValue(user.getCurrencyCd());
		return addWeeklyReport;
	}
}
