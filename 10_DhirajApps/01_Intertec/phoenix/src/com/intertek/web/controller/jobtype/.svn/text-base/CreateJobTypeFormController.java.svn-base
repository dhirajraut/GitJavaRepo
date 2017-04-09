package com.intertek.web.controller.jobtype;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.domain.AddJobType;
import com.intertek.entity.JobType;
import com.intertek.entity.Operation;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.JobTypeService;

public class CreateJobTypeFormController extends SimpleFormController
{
  public CreateJobTypeFormController() {
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
	JobTypeService jobTypeService = (JobTypeService)ServiceLocator.getInstance().getBean("jobTypeService");

if((addOrDeleteJobType != null) && ("add".equals(addOrDeleteJobType) || "delete".equals(addOrDeleteJobType)))
    {
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

		String operationFlag=request.getParameter("operationFlag");
		if("newval".equals(operationFlag)){
			String rowNo=request.getParameter("rowNum");
			int num=Integer.parseInt(rowNo);
			Operation[] operationsItems =addJobType.getOperations();
			Operation operation = null;
			operation=jobTypeService.getOperationsByName(operationsItems[num].getOperationCode());
			operationsItems[num] =operation;
			addJobType .setOperations(operationsItems);
			addJobType.setOperationFlag("none");
			return showForm(request, response, errors);
       }

	Operation operation;
	Operation[] operatioinItems = addJobType.getOperations();
        
	if(jobType.getJobTypeCode()!=null &&(!jobType.getJobTypeCode().trim().equals(""))&&jobType.getJobTypeDesc()!=null && (!jobType.getJobTypeDesc().trim().equals("")))
	  {
	      	if (operatioinItems   != null )
		{		
			
			for(int i=0; i< operatioinItems.length; i++)
			{
				 operation = operatioinItems[i];	
				if(operation.getOperationCode() != null && (!operation.getOperationCode().trim().equals(""))&&operation.getDescription()!=null && (!operation.getDescription().trim().equals("")))		
				{		
				operation=jobTypeService.addOperation(operation);			 
				jobType.getOperations().add(operation);
				}
				else
				{
				//errors.reject("invalid.Operation.error", new Object[] {"Invalid Operation : "+operation.getOperationCode()}, null);
					errors.reject("invalid.Operation.error", new Object[] {operation.getOperationCode()}, null);
				return showForm(request, response, errors);
				}
			}
		 }
	  }
	  else
	  {	  errors.reject("invalid.jobType.error", new Object[] {jobType.getJobTypeCode()}, null);
		 // errors.reject("create.jobtype.error", new Object[] {"Invalid job Type : "+jobType.getJobTypeCode()}, null);
		  return showForm(request, response, errors);
	  }
    
    try
    {
	 if(jobType.getJobTypeCode()!=null && (!jobType.getJobTypeCode().trim().equals("")))
      jobTypeService.addJobType(jobType);
    }
    catch(Exception e)
    {
      e.printStackTrace();

      errors.reject("create.jobtype.error", new Object[] {e.getMessage()}, null);
      return showForm(request, response, errors);
    }

    ModelAndView modelAndView = new ModelAndView("common-message-r");
   modelAndView.addObject("backUrl", "edit_job_type.htm?name="+ jobType.getJobTypeCode());
    modelAndView.addObject("backUrlDescription", "You can continue to edit this Job Type.");
    modelAndView.addObject("description", "Your job type has been successfully created.");

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
	JobType jobType=new JobType();
	
	Operation operation   = new Operation();

     String countStr = request.getParameter("jobTypeCount");


		if( countStr == null || countStr.trim().equals("") )
			  countStr = "0";

			int count = 0;
			try
			{
				count = new Integer(countStr).intValue();
			}
			catch(Exception e) { }

		 
			 addJobType.setJobTypeCount(count);
		     Operation[] operations = new Operation[count];
			 for(int i=0; i<count; i++)
			{
				operations[i] = operation;
			}
		
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
