package com.intertek.web.controller.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.domain.AddJobContractNote;
import com.intertek.entity.JobContract;
import com.intertek.entity.JobContractNote;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.JobService;
import com.intertek.util.SecurityUtil;
import com.intertek.util.Constants;

public class CreateJobAddCustomerNotePopupFormController extends SimpleFormController
{
  public CreateJobAddCustomerNotePopupFormController() {
    super();
    setCommandClass(AddJobContractNote.class);
	setSessionForm(false);
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
   */
  protected ModelAndView onSubmit(
    HttpServletRequest request,
    HttpServletResponse response,
    Object command,
    BindException errors
  )  throws Exception
  {
	  JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
	  AddJobContractNote  addJobContractNote = (AddJobContractNote)command;
	  try {
	  List<JobContractNote> jobContractNoteList = addJobContractNote.getJobContractNoteList();
	  JobContractNote jobContractNote = addJobContractNote.getJobContractNote();
	  JobContract jobContract = addJobContractNote.getJobContract();
	  
	//Selecting a JobContractNote
	  String selectedNoteIdstr = request.getParameter("selectedNoteId");
	  if(selectedNoteIdstr!=null && selectedNoteIdstr.trim().length()>0)
	    {
		  long selectedNoteId = Long.parseLong(selectedNoteIdstr);
		  for(JobContractNote n : jobContractNoteList){
			  if(n.getId()==selectedNoteId){
				  addJobContractNote.setJobContractNote(n);
			  }
		  }
	      
	      addJobContractNote.setSelectedNoteId("");
	      
	      //jobContractNoteList = jobService.getJobContractNoteByJobContractId(addJobContractNote.getJobContract().getId());
	      //addJobContractNote.setJobContractNoteList(jobContractNoteList);
	      return showForm(request, response, errors);
	    }
	  
	  
	  //Deleting a JobContractNote
	  String deleteNoteIdstr = request.getParameter("deleteNoteId");
	  if(deleteNoteIdstr!=null && deleteNoteIdstr.trim().length()>0)
	    {
		  long deleteNoteId = Long.parseLong(deleteNoteIdstr);
	      jobService.deleteJobContractNote(deleteNoteId);
	      addJobContractNote.setDeleteNoteId("");
	      
	      jobContractNoteList = jobService.getJobContractNoteByJobContractId(addJobContractNote.getJobContract().getId());
	      addJobContractNote.setJobContractNoteList(jobContractNoteList);
	      return showForm(request, response, errors);
	    }
	  
	  String reset = request.getParameter("reset");
	  if(reset!=null && reset.trim().length()>0)
	    {
	      addJobContractNote.setJobContractNote(new JobContractNote());
	      jobContractNoteList = jobService.getJobContractNoteByJobContractId(addJobContractNote.getJobContract().getId());
	      addJobContractNote.setJobContractNoteList(jobContractNoteList);
	      return showForm(request, response, errors);
	    }
	  
	  
	  //add or update
	 if(jobContract!=null && jobContractNote!=null){
		 jobContractNote.setJobContractId(jobContract.getId());
		 jobContractNote.setContractCode(jobContract.getContractCode());
		 jobContractNote.setJobNumber(jobContract.getJobNumber());
	     jobContractNote.setAddedBy(SecurityUtil.getUser());
	     jobContractNote.setAddedByUserName(SecurityUtil.getUser().getLoginName());
	     jobContractNote.setDateTimeAdded(new Date());
	     jobContractNote.setNoteOrigin("internal");
	     jobContractNote.setNoteCategory(Constants.CONTRACT_STR);
	      
		 jobContractNote = jobService.mergeJobContractNote(jobContractNote);
		 jobContractNoteList = jobService.getJobContractNoteByJobContractId(addJobContractNote.getJobContract().getId());
		 addJobContractNote.setJobContractNoteList(jobContractNoteList);
	 }//if
		
		addJobContractNote.setJobContractNoteList(jobContractNoteList);
		addJobContractNote.setJobContractNote(new JobContractNote());
		return showForm(request, response, errors);
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
    	//return new ModelAndView();
    }

 
  protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
        throws ServletException 
	{
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}
   
 protected void onBind(HttpServletRequest req, Object command, BindException errors) 
 {
 }
	  
	protected Object formBackingObject(HttpServletRequest request) throws Exception
	{
		AddJobContractNote addJobContractNote = new AddJobContractNote();
		List<JobContractNote> jobContractNoteList = new ArrayList<JobContractNote>();
		JobContractNote jobContractNote = new JobContractNote();
		JobContract jobContract = new JobContract();
		String jobContractIdstr = request.getParameter("jobContractId");
		try {
		if(jobContractIdstr!=null && jobContractIdstr.trim().length()>0){
			long jobContractId = Long.parseLong(jobContractIdstr);
			JobService jobService = (JobService) ServiceLocator.getInstance().getBean("jobService");
			jobContract = jobService.getJobContractById(jobContractId);
			jobContractNoteList = jobService.getJobContractNoteByJobContractId(jobContractId);
		}
		addJobContractNote.setJobContractNoteList(jobContractNoteList);
		addJobContractNote.setJobContract(jobContract);
		addJobContractNote.setJobContractNote(jobContractNote);
		} catch(ServiceException e)
		{
		throw new ServiceException(e.getMessage(), e.getParams(), e);    	
		}
        catch(Throwable t)
        {
        t.printStackTrace();
        throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
        }
		return addJobContractNote;
	}
}
