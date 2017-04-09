package com.intertek.web.controller.job;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.support.ByteArrayMultipartFileEditor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.domain.AddJobContract;
import com.intertek.entity.ContractAttach;
import com.intertek.entity.JobContract;
import com.intertek.entity.JobContractAttach;
import com.intertek.entity.JobContractAttachType;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.JobService;
import com.intertek.util.StringUtil;

public class CreateJobAddCustomerAttachFilePopupFormController extends SimpleFormController
{
  public CreateJobAddCustomerAttachFilePopupFormController() {
    super();
    setCommandClass(AddJobContract.class);
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
	  try {
	  JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
	  AddJobContract  addJobContract = (AddJobContract)command;
	  List<JobContractAttach> jobContractAttachList = new ArrayList<JobContractAttach>();
	  JobContractAttach[] jobContractAttachs = addJobContract.getJobContractAttachs();
	  if(jobContractAttachs != null && jobContractAttachs.length>0)
		  jobContractAttachList = new ArrayList(Arrays.asList(jobContractAttachs));
	  
	  PropertyResourceBundle pRB = (PropertyResourceBundle) ResourceBundle.getBundle("config");
	  	String path ="";
		if(pRB != null)
			path = pRB.getString(com.intertek.util.Constants.jobcontractfilepath);
		
		  //Editing a JobContractAttach
		  String saveFlag = request.getParameter("saveFlag");
		  if(saveFlag!=null && saveFlag.trim().length()>0 && saveFlag.equals("true"))
		    {
			 for (JobContractAttach jobContractAttach: addJobContract.getJobContractAttachs()){
				 //Resetting Special codes with special chars
				 jobContractAttach.setSystemFileName(StringUtil.replaceSpecialCodes(jobContractAttach.getSystemFileName()));
		          //End
				 jobService.saveJobContractAttach(jobContractAttach);
			 }
			 // Replacing Special characters with special codes in filenames
			 JobContract jobContract = addJobContract.getJobContract();
			 List<JobContractAttach> jobContractattachedList = jobService.getJobContractAttachByJobContractId(jobContract.getId());
			 if(jobContractattachedList != null && !jobContractattachedList.isEmpty()){
				JobContractAttach[] jobContractAttacheds = new JobContractAttach[jobContractattachedList.size()];
				int counter =0;
			    Iterator iter = jobContractattachedList.iterator();
			    while(iter.hasNext())
			    {
			      JobContractAttach jobContractAttach = (JobContractAttach) iter.next();
			      String sysFileName = "";
			      if (jobContractAttach != null){
			    	  sysFileName = jobContractAttach.getSystemFileName(); 
			    	  sysFileName = StringUtil.replaceSpecialChars(sysFileName);
			    	  jobContractAttach.setSystemFileName(sysFileName);
			    	  jobContractAttacheds[counter] = jobContractAttach;
			      }
			      counter++;
			    }
				addJobContract.setJobContractAttachs(jobContractAttacheds);
			 }//End
		     return showForm(request, response, errors);
		  }
	  //Deleting a JobContractAttach
	  String deleteAttachIdstr = request.getParameter("deleteAttachId");
	  if(deleteAttachIdstr!=null && deleteAttachIdstr.trim().length()>0)
	    {
		  long deleteAttachId = Long.parseLong(deleteAttachIdstr);
	      jobService.deleteJobContractAttach(deleteAttachId, path);
	      addJobContract.setDeleteAttachId("");
	      if(jobContractAttachs != null && jobContractAttachs.length>0){
		      for(int i=0; i<jobContractAttachs.length;i++){
		    	  if(jobContractAttachs[i].getId() == deleteAttachId){
		    		    JobContractAttach[] jobContractAttachsNew = new JobContractAttach[jobContractAttachs.length-1];
		    			System.arraycopy( jobContractAttachs, 0, jobContractAttachsNew, 0, i );
		    			System.arraycopy( jobContractAttachs, i+1, jobContractAttachsNew, i, jobContractAttachsNew.length-i );
		    			addJobContract.setJobContractAttachs(jobContractAttachsNew);
		    	  }
		      }
	      }
	      return showForm(request, response, errors);
	    }
	  
	 //adding a jobContractattach
	 JobContract jobContract = null;
	 jobContract = addJobContract.getJobContract();
	 for(HashMap map : addJobContract.getFileInfoMapList()){
		 MultipartFile file = (MultipartFile)map.get("file");
		 String desc = (String)map.get("desc");
		 String type = (String)map.get("type");
		 if(jobContract!=null){
					 JobContractAttach jobContractAttach = new JobContractAttach();
					 jobContractAttach.setJobContractId(jobContract.getId());
					 jobContractAttach.setContractCode(jobContract.getContractCode());
					 jobContractAttach.setJobNumber(jobContract.getJobNumber());
					 if(file!=null && !file.isEmpty() && file.getSize()>0 && file.getOriginalFilename()!= null && !"".equals(file.getOriginalFilename())){
						jobContractAttach.setFileName(file.getOriginalFilename());
						if(addJobContract.getInputFieldId() == null || addJobContract.getInputFieldId().trim().equals(""))
							addJobContract.setInputFieldId(addJobContract.getInputFieldId() +  file.getOriginalFilename());
						else
							addJobContract.setInputFieldId(addJobContract.getInputFieldId() + ";" + file.getOriginalFilename());
						String systemFileName =  "_".concat(file.getOriginalFilename());
						jobContractAttach.setSystemFileName(systemFileName);
						jobContractAttach.setDescription(desc);
						jobContractAttach.setType(type);
						jobContractAttach = jobService.addJobContractAttach(jobContractAttach, path, file);
						if(jobContractAttach != null && jobContractAttach.getId()>0){
							// Replacing Special characters in filename 
							jobContractAttach.setSystemFileName(StringUtil.replaceSpecialChars(jobContractAttach.getSystemFileName()));
							//End
							jobContractAttachList.add(jobContractAttach);
						}
					 }
				  }//for HashMapList
			addJobContract.setJobContractAttachs(jobContractAttachList.toArray(new JobContractAttach[0]));
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
	     return showForm(request, response, errors);
    	//return new ModelAndView();
    }

 
  protected void initBinder(HttpServletRequest request, ServletRequestDataBinder binder)
        throws ServletException 
	{
        binder.registerCustomEditor(byte[].class, new ByteArrayMultipartFileEditor());
	}
   
 protected void onBind(HttpServletRequest req, Object command, BindException errors) 
 {
       AddJobContract  addJobContract = (AddJobContract)command;
	   MultipartHttpServletRequest multiReq = (MultipartHttpServletRequest) req;
       Iterator fileNames = multiReq.getFileNames();
       List<HashMap> fileInfoMapList= new ArrayList<HashMap>();
	   while(fileNames.hasNext())
	   {
	        String fileName = (String) fileNames.next();
	        if(fileName != null){
		        MultipartFile multipart = multiReq.getFile(fileName);
		        if(multipart !=null && !multipart.isEmpty()){
		        	HashMap hash = new HashMap();
		        	int counter = Integer.parseInt(fileName.substring(4));
			        String desc = req.getParameter("desc"+fileName.substring(4));
			        String type = req.getParameter("type"+fileName.substring(4));
					hash.put("file", multipart);
					hash.put("desc", desc);
					hash.put("type", type);
					fileInfoMapList.add(hash);
		        }
			   
	        }
	   }
	   addJobContract.setFileInfoMapList(fileInfoMapList);
 }
  
	  
	protected Object formBackingObject(HttpServletRequest request) throws Exception
	{
		AddJobContract addJobContract = new AddJobContract();
		JobContract jobContract = null;
	    String rowNum = request.getParameter("rowNum");
		String divName=request.getParameter("divName");
		
		String inputFieldId = request.getParameter("inputFieldId");
		String jobContractIdstr = request.getParameter("jobContractId");
		
		try {
		if(jobContractIdstr!=null && jobContractIdstr.trim().length()>0){
			addJobContract = new AddJobContract();
			JobContractAttach[] jobContractAttaches = null;
			long jobContractId = Long.parseLong(jobContractIdstr);
			int counter = 0;
			JobService jobService = (JobService) ServiceLocator.getInstance().getBean("jobService");
			jobContract = jobService.getJobContractById(jobContractId);
			List<JobContractAttach> jobContractattachedList = jobService.getJobContractAttachByJobContractId(jobContractId);
			if(jobContractattachedList != null && !jobContractattachedList.isEmpty()){
				//JobContractAttach[] jobContractAttacheds = jobContractattachedList.toArray(new JobContractAttach[0]);
				JobContractAttach[] jobContractAttacheds = new JobContractAttach[jobContractattachedList.size()];
				
				// Replacing Special characters in filenames
			    Iterator iter = jobContractattachedList.iterator();
			    while(iter.hasNext())
			    {
			      JobContractAttach jobContractAttach = (JobContractAttach) iter.next();
			      String sysFileName = "";
			      if (jobContractAttach != null){
			    	  sysFileName = jobContractAttach.getSystemFileName(); 
			    	  sysFileName = StringUtil.replaceSpecialChars(sysFileName);
			    	  jobContractAttach.setSystemFileName(sysFileName);
			    	  jobContractAttacheds[counter] = jobContractAttach;
			      }
			      counter++;
			    }
				//End
				addJobContract.setJobContractAttachs(jobContractAttacheds);
			}
			addJobContract.setRowNum(rowNum);
			addJobContract.setDivName(divName);	 
			addJobContract.setInputFieldId(inputFieldId);
			addJobContract.setJobContract(jobContract);
		}
		}catch(ServiceException e)
		{
		throw new ServiceException(e.getMessage(), e.getParams(), e);    	
		}
        catch(Throwable t)
        {
        t.printStackTrace();
        throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
        }
		return addJobContract;
	}
}
