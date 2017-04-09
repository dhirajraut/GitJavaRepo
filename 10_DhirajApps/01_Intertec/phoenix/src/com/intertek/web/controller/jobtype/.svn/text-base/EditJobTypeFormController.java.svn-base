package com.intertek.web.controller.jobtype;

import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.intertek.exception.ServiceException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.intertek.domain.AddJobType;
import com.intertek.domain.JobTypeSearch;
import com.intertek.entity.JobType;
import com.intertek.entity.Operation;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.JobTypeService;
import com.intertek.util.Constants;
import com.intertek.web.controller.BaseSimpleFormController;

//public class EditJobTypeFormController extends SimpleFormController
public class EditJobTypeFormController extends BaseSimpleFormController
{
  public EditJobTypeFormController() {
    super();
	setSessionForm(true);
       setCommandClass(AddJobType.class);
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
     AddJobType addJobType= (AddJobType)command;
   
	String addOrDeleteJobType = request.getParameter("addOrDeleteJobType");
	String indexs = request.getParameter("jobTypeIndex");
	JobType jobType = addJobType.getJobType();
	String oxcel=request.getParameter("oxcel");
	String jobTypeCode= request.getParameter("name");
	JobTypeService jobTypeService = (JobTypeService)ServiceLocator.getInstance().getBean("jobTypeService");

	
	if((addOrDeleteJobType != null) && ("add".equals(addOrDeleteJobType) || "delete".equals(addOrDeleteJobType)))
    {
		System.out.println(" Adding rows");
			if("add".equals(addOrDeleteJobType))
			{
				addJobType.setOperations(addOperations(addJobType.getOperations()));
			}
			else
			{
				addJobType.setOperations(deleteOperations(addJobType.getOperations(),addJobType.getJobTypeIndex()));
			}
		  addJobType.setJobTypeCount(addJobType.getOperations().length);

		  addJobType.setAddOrDeleteJobType("none");
	  
      return showForm(request, response, errors);
    } 

    
	 
    if((oxcel!=null) && "true".equals(oxcel))
	{ 
    List  jobtypes=new ArrayList();
	
	System.out.println("jobtypecode is "+ jobTypeCode);
	JobTypeSearch search = new JobTypeSearch();
	search.setPagination(null);
	
	Operation[] operationsItems =addJobType .getOperations();
	
	if(operationsItems!=null&&operationsItems.length!=0)
	{
	try
		{
		response.setContentType("application/download");
	 	response.setHeader("Content-Disposition", "attachment; filename=\"jobtypesByOperations.xls\"");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0");

		
		HSSFWorkbook workBook = new HSSFWorkbook();
		HSSFSheet main=null;
		
		main=workBook.createSheet("jobtypes");
		HSSFRow   row ;
		HSSFCell  cell;
		HSSFRow headerRow;
		HSSFCell headerCell ;
		PropertyResourceBundle pRB = (PropertyResourceBundle) ResourceBundle.getBundle("TrackerRes");
		String path="";
			if(pRB!= null)
			{
				int  l=0;
				for(int k=0;k<=Constants.UColumnSize;k++)
				{
				path=pRB.getString("JTHeading"+k);
				headerRow = main.createRow((short) 0); headerCell =   headerRow.createCell((short) l); headerCell.setCellValue(path);
				l++;
				}
			}
        int j=1;
		for(int i=0;i<operationsItems.length;i++)
		{ 
		Operation operation = operationsItems[i];	

		row = main.createRow(j);cell = row.createCell((short) 0);cell.setCellValue(addJobType.getJobType().getJobTypeCode());
		
		row = main.createRow(j);cell = row.createCell((short) 1);cell.setCellValue(operation.getOperationCode());

		row = main.createRow(j);cell = row.createCell((short) 2);
		if(operation.getDescription()!=null && !(operation.getDescription().trim().equals("")))
		{cell.setCellValue(operation.getDescription());}
		else
		{cell.setCellValue("");}	
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
     search.setOxcel("false");
	return showForm(request, response, errors);
	}


	String operationFlag=request.getParameter("operationFlag");
		if("newval".equals(operationFlag)){

			String rowNo=request.getParameter("rowNum");
			int num=Integer.parseInt(rowNo);
			Operation[] operationsItems =addJobType .getOperations();
			Operation operation = null;
			try
		    {
			 operation=jobTypeService.getOperationsByName(operationsItems[num].getOperationCode());
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
			operationsItems[num]=operation;
			addJobType.setOperations(operationsItems);
			addJobType.setOperationFlag("none");
			return showForm(request, response, errors);
       }

	Operation[] operationItems = addJobType.getOperations();
		
    if(jobType.getJobTypeCode()!=null &&(!jobType.getJobTypeCode().trim().equals("")))
	  {
		if (operationItems   != null )
		 {			
			jobType.getOperations().clear();
			for(int i=0; i< operationItems.length; i++)
			{
				Operation operation = operationItems[i];	
				
				 if(operation.getOperationCode() != null && (!operation.getOperationCode().trim().equals(""))&&operation.getDescription()!=null && (!operation.getDescription().trim().equals("")))		
					{				
						try
						{
					operation=jobTypeService.saveOperation(operation);
					jobType.getOperations().add(operation);
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
					} else
						{
						errors.reject("invalid.Operation.error", new Object[] {operation.getOperationCode()}, null);
						return showForm(request, response, errors);
						}
					}
			   }
		 }
	else
	{
	errors.reject("invalid.jobType.error", new Object[] {jobType.getJobTypeCode()}, null);
	return showForm(request, response, errors);
	}

    try
    {
	 if(jobType.getJobTypeCode()!=null && (!jobType.getJobTypeCode().trim().equals("")))
       jobTypeService.saveJobType(jobType);

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
    ModelAndView modelAndView = new ModelAndView("common-message-r");
  modelAndView.addObject("backUrl", "edit_job_type.htm?name="+ jobType.getJobTypeCode());
  modelAndView.addObject("backUrlDescription", "You can continue to edit this Job Type.");
    modelAndView.addObject("description", "Your job type has been successfully updated.");

    return modelAndView;
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
  protected boolean suppressValidation(HttpServletRequest request)
  {
      String addOrDeleteJobType = request.getParameter("addOrDeleteJobType");
  
    if((addOrDeleteJobType != null) && ("add".equals(addOrDeleteJobType) || "delete".equals(addOrDeleteJobType)))
    {
      return true;
    }

 String operationFlag=request.getParameter("operationFlag");
  if((operationFlag != null) && ("newval".equals(operationFlag)))
    {
      return true;
    }
    return super.suppressValidation(request);
  }
  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
   */
  protected Object formBackingObject(
    HttpServletRequest request
  ) throws Exception
  {
    AddJobType addJobType = new AddJobType();
	JobType jobType= null;

JobTypeService jobTypeService = (JobTypeService)ServiceLocator.getInstance().getBean("jobTypeService");
       String countStr = request.getParameter("jobTypeCount");
	  Operation operation    = new Operation();
		String jobTypeCode= request.getParameter("name");
	try{
		jobType = jobTypeService.getJobTypeByName(jobTypeCode);
	}
		catch(ServiceException e)
    {
    	throw new ServiceException(e.getMessage(), e.getParams(), e);    	
    }
    catch(Throwable t)
    {
        t.printStackTrace();
        throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
    }
		Operation[] operations = new Operation[(jobType.getOperations()).size()];
		if(jobType != null)
	    {
			int i=0;	
			
			for(Iterator itr=(jobType.getOperations()).iterator();itr.hasNext();)
			{
			 operations[i] =(Operation)itr.next();
		     i++;
			}
			addJobType.setOperations(operations);
		}
		
		
		
		if( countStr == null || countStr.trim().equals("") )
			  countStr = "0";

			int count = 0;
			try
			{
				count = new Integer(countStr).intValue();
			}
			catch(Exception e) { }

		 
		addJobType.setJobTypeCount(count);		
		addJobType.setOperations(operations); 
		addJobType.setJobType(jobType);

    return addJobType;
  }

    private Operation[] addOperations(Operation[] operations){		
		Operation operation = new Operation();	
		Operation[] results = null;		
		if(operations == null) results = new Operation[1];
		else results = new Operation[operations.length + 1];
		if(operations != null)
		{
		for(int i=0; i<operations.length; i++)
		{
			results[i] = operations[i];
		}
		}
		results[results.length - 1] = operation;
		return results;
	}

  private Operation[] deleteOperations(Operation[] operations, int index){
		if(operations == null) return null;
		if(operations.length <= 0) return operations;
		Operation[] results = new Operation[operations.length - 1];
		int count = 0;
		for(int i=0; i<operations.length; i++)
		{
			if(i == index) continue;
			results[count] = operations[i];
			count ++;
		}
		return results;
	}
}
