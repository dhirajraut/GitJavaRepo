package com.intertek.web.controller.job;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.domain.AddJobContract;
import com.intertek.domain.AddJobEmail;
import com.intertek.domain.AddJobOrder;
import com.intertek.domain.JobEmail;
import com.intertek.entity.Contact;
import com.intertek.entity.JobContract;
import com.intertek.entity.JobContractNote;
import com.intertek.entity.JobLog;
import com.intertek.entity.JobOrder;
import com.intertek.entity.User;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.mail.VelocityMimeMessagePreparator;
import com.intertek.service.JobService;
import com.intertek.service.UserService;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;
import com.intertek.util.PropertyConfig;
import com.intertek.util.SecurityUtil;

public class JobLogMailPopupController extends SimpleFormController
{
  public JobLogMailPopupController() {
    super();
    setCommandClass(AddJobEmail.class);
    setSessionForm(true);
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
   */
  protected ModelAndView onSubmit(HttpServletRequest request,HttpServletResponse response, Object command, BindException errors) throws Exception
  {
	  AddJobEmail addJobEmail = (AddJobEmail)command;
	  
	  String refreshing = request.getParameter("refreshing");
	  int emailIndex = addJobEmail.getEmailIndex();
	  
	    if((refreshing != null) && "true".equals(refreshing))
	    {
	    	//Copy any of the notes to notesToDisplay field
	    	JobEmail[] jobEmails = addJobEmail.getJobEmails();
	    	
	    	if(jobEmails != null && jobEmails.length > 0)
	    	{
	    		for(int i=0;i<jobEmails.length;i++)
	    		{
	    			JobEmail jobEmail = jobEmails[i];
	    			
	    			String introduction = jobEmail.getIntroduction();
	    			if(introduction != null && (!introduction.trim().equals("")))
	    			{
	    				String introToDisplay = jobEmail.getIntroduction().replaceAll(System.getProperty("line.separator"), "<br>");
	    				jobEmail.setIntroductionToDisplay(introToDisplay);
	    			}
	    			AddJobOrder[] addJobOrders = jobEmail.getAddJobOrders();
	    			if(addJobOrders != null && addJobOrders.length > 0)
	    			{
	    				for(int j=0;j<addJobOrders.length;j++)
	    				{
	    					AddJobOrder addJobOrder = addJobOrders[j];
	    					String emailNote = addJobOrder.getEmailNote();
	    					String emailNoteToDisplay = emailNote.replaceAll(System.getProperty("line.separator"), "<br>");
	    					addJobOrder.setEmailNoteDisplay(emailNoteToDisplay);
	    				}
	    			}
	    		}
	    	}
	      return showForm(request, response, errors);
	    }
	    if((refreshing != null) && "sendEmail".equals(refreshing))
	    {
	    	
	    		JobEmail jobEmail = addJobEmail.getJobEmails()[emailIndex];
	    		if(jobEmail != null)
	    		{
	    			 JavaMailSender sender = (JavaMailSender) ServiceLocator.getInstance().getBean("sender");
	    			 VelocityMimeMessagePreparator preparator =(VelocityMimeMessagePreparator) ServiceLocator.getInstance().getBean("passwordMailPreparator");
	    			 PropertyConfig propertyConfig = (PropertyConfig)ServiceLocator.getInstance().getBean("propertyConfig");
	    			 Map data = new HashMap();
	    			 String randomPassword="";
	    			 data.put("jobEmail",jobEmail);
	    			 preparator.setTo(jobEmail.getTo());
	    			 preparator.setCc(jobEmail.getCc());
	    			 preparator.setSubject(jobEmail.getSubject());
	    			 preparator.setData(data);
	    		     sender.send(preparator);
	    		     jobEmail.setEmailSentFlag(true);
	    		}
	    	
	    	
	    }
	    
       
    return showForm(request, response, errors);
 }

/*excel download ends here*/

   

 
  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest, org.springframework.web.bind.ServletRequestDataBinder)
   */

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
   */
  protected Object formBackingObject(HttpServletRequest request) throws Exception
  {
	  AddJobEmail addJobEmail=new AddJobEmail();
	  String jobContracts=request.getParameter("jobContractId");
	  
	  JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
	  UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
	  User user = userService.getUserByName(SecurityUtil.getUser().getLoginName());
	  
	  addJobEmail.setUser(user);
	  addJobEmail.setTimeFormat("HH:mm");
	  String dateFormat = "dd-MMM-yyyy";
	  /*if(user.getTimeFormat() == null || user.getTimeFormat().trim().equals(""))
	  {
		  addJobEmail.setTimeFormat("hh:mm a");
	  }
	  else
	  {
		  if(user.getTimeFormat().trim().equals(Constants.USER_TIME_FPORMAT))
		  {
			  addJobEmail.setTimeFormat("hh:mm a");
		  }
		  else if(user.getTimeFormat().trim().equals(Constants.TWENTY_FOURHOUR))
		  {
			  addJobEmail.setTimeFormat("HH:mm");
		  }
		  else
		  {
			  addJobEmail.setTimeFormat("hh:mm a");
		  }
	  }*/
	  
	  //JobContract jobContract = null;
	  try {
	  if(jobContracts != null)
	    {
	      String[] jobContractList=jobContracts.split(";");
	      
	      //Identify number of distinct schedulers for the select contracts
		  List schedulers = jobService.getSchedulersByJobContractIds(jobContractList);
		  
		  JobEmail[] jobEmails = null;
		  if(schedulers != null)
			  jobEmails = new JobEmail[schedulers.size()];
		  
	      for(int i=0;i<schedulers.size();i++)
	      {
	    	  
	    	  JobEmail jobEmail = new JobEmail();
	    	  
	    	  Contact scheduler = (Contact) schedulers.get(i);
	    	  
	    	  List jobOrders = jobService.getJobOrdersByJobContractsAndScheduler(jobContractList,scheduler.getId());
	    	  
	    	  if(jobOrders != null && jobOrders.size() > 0)
	    	  {
	    		  AddJobOrder[] addJobOrders = new AddJobOrder[jobOrders.size()];
		    	  for(int j=0;j<jobOrders.size();j++)
		    	  {
		    		  AddJobOrder addJobOrder = new AddJobOrder();
		    		  
		    		  JobOrder jobOrder = (JobOrder) jobOrders.get(j);
		    		  if(jobOrder.getEtaTimeTz() == null)
		    		      jobOrder.setEtaTimeTz((userService.getUserByNameWithOrgHierarchy(SecurityUtil.getUser().getLoginName())).getPreferredTz());
		    		  if(jobOrder.getEtaTime() != null)
		    		  {
		    		      Calendar cal = new GregorianCalendar();
		    		      cal.setTime(jobOrder.getEtaTime());
		    		      int hour24 = cal.get(Calendar.HOUR_OF_DAY);
		    		      int min = cal.get(Calendar.MINUTE);

		    		      String etaTime=String.valueOf(hour24)+":"+String.valueOf(min);    		      
		    		      jobOrder.setEtaTime(DateUtil.getConvertedDateTime(jobOrder.getEtaTime(),etaTime,Constants.TIME_ZONE,jobOrder.getEtaTimeTz()));
		    		      
		    		      addJobOrder.setUiEtaTime(DateUtil.formatDate(jobOrder.getEtaTime(),addJobEmail.getTimeFormat()));
		    		      if(jobOrder.getEtaDate() != null)
		    		    	  addJobOrder.setUiEtaDate(DateUtil.formatDate(jobOrder.getEtaDate(), dateFormat));
		    		    }
		    		  addJobOrder.setJobOrder(jobOrder);
		    		  
		    		  Set jobContractSet = jobOrder.getJobContracts();
		    		  String emailNote = "";
		    		  String emailNoteDisplay = "";
		    		  if(jobContractSet != null && jobContractSet.size() > 0)
		    		  {
		    			  AddJobContract[] addJobContracts = new AddJobContract[jobContractSet.size()];
		    			  int jcCount = 0;
		    			  Iterator iter = jobContractSet.iterator();
		    			  while(iter.hasNext())
		    			  {
		    				  AddJobContract addJobContract = new AddJobContract();
		    				  JobContract jobContract = (JobContract) iter.next();
		    				  addJobContract.setJobContract(jobContract);
		    				  //Get all job contract notes to be displayed on email UI & to send in email
		    				  List jobContractNotes = jobService.getJobContractNotesByJobContract(jobContract);
		    				  if(jobContractNotes != null && jobContractNotes.size() > 0)
		    				  {
		    					  
		    					  for(int k=0;k<jobContractNotes.size();k++)
		    					  {
		    						  JobContractNote jobContractNote = (JobContractNote) jobContractNotes.get(k);
		    						  if(jobContractNote.getNoteCategory() != null && !jobContractNote.getNoteCategory().trim().equals("") 
		    								  && jobContractNote.getNoteCategory().trim().equals(Constants.LOG_STR))
		    						  {
			    						  if(jobContractNote.getNote() != null && !jobContractNote.getNote().trim().equals(""))
			    						  {
			    							  if(emailNote.trim().equals(""))
			    							  {
			    								  emailNote = jobContractNote.getAddedByUserName() + ": " + DateUtil.formatDate(jobContractNote.getDateTimeAdded(), "dd/MM/yyyy")+ ": "+ jobContractNote.getNote();
			    								  emailNoteDisplay = emailNote;
			    							  }
			    							  else
			    							  {
			    								  emailNote = emailNote + System.getProperty("line.separator") + jobContractNote.getAddedByUserName() + ": " + DateUtil.formatDate(jobContractNote.getDateTimeAdded(), "dd/MM/yyyy")+ ": "+ jobContractNote.getNote();
			    								  emailNoteDisplay = emailNoteDisplay + "<br>" + jobContractNote.getAddedByUserName() + ": " + DateUtil.formatDate(jobContractNote.getDateTimeAdded(), "dd/MM/yyyy")+ ": "+ jobContractNote.getNote();
			    							  }
			    							  
			    						  }
		    						  }
		    					  }
		    				  }
		    				  //Get job log entity associated with job contract
		    				  JobLog jobLog = jobService.getJobLogByJobContracts(addJobContract.getJobContract());
		    				  addJobContract.getJobContract().setJobLog(jobLog);
		    				  if(jobLog != null)
		    				  {
		    					  
		    					  //Set Date & Time strings related to Job Log
		    					  if(jobLog.getArriveDt() != null)
		    						  addJobContract.setArriveDt(DateUtil.formatDate(jobLog.getArriveDt(), dateFormat));
		    					  if(jobLog.getArriveTime() != null)
		    						  addJobContract.setArriveTime(DateUtil.formatDate(jobLog.getArriveTime(), addJobEmail.getTimeFormat()));
		    					  if(jobLog.getDockDt() != null)
		    						  addJobContract.setDockDt(DateUtil.formatDate(jobLog.getDockDt(), dateFormat));
		    					  if(jobLog.getDockTime() != null)
		    						  addJobContract.setDockTime(DateUtil.formatDate(jobLog.getDockTime(), addJobEmail.getTimeFormat()));
		    					  if(jobLog.getHoseOnDt() != null)
		    						  addJobContract.setHoseOnDt(DateUtil.formatDate(jobLog.getHoseOnDt(), dateFormat));
		    					  if(jobLog.getHoseOnTime() != null)
		    						  addJobContract.setHoseOnTime(DateUtil.formatDate(jobLog.getHoseOnTime(), addJobEmail.getTimeFormat()));   
		    					  if(jobLog.getEstStartDt() != null)
		    						  addJobContract.setEstStartDt(DateUtil.formatDate(jobLog.getEstStartDt(), dateFormat));
		    					  if(jobLog.getEstStartTime() != null)
		    						  addJobContract.setEstStartTime(DateUtil.formatDate(jobLog.getEstStartTime(), addJobEmail.getTimeFormat()));  
		    					  if(jobLog.getCommenceDt() != null)
		    						  addJobContract.setCommenceDt(DateUtil.formatDate(jobLog.getCommenceDt(), dateFormat));
		    					  if(jobLog.getCommenceTime() != null)
		    						  addJobContract.setCommenceTime(DateUtil.formatDate(jobLog.getCommenceTime(), addJobEmail.getTimeFormat()));
		    					  if(jobLog.getDelayDt() != null)
		    						  addJobContract.setDelayDt(DateUtil.formatDate(jobLog.getDelayDt(), dateFormat));
		    					  if(jobLog.getDelayTime() != null)
		    						  addJobContract.setDelayTime(DateUtil.formatDate(jobLog.getDelayTime(), addJobEmail.getTimeFormat()));
		    					  if(jobLog.getDelayEndDt() != null)
		    						  addJobContract.setDelayEndDt(DateUtil.formatDate(jobLog.getDelayEndDt(), dateFormat));
		    					  if(jobLog.getDelayEndTime() != null)
		    						  addJobContract.setDelayEndTime(DateUtil.formatDate(jobLog.getDelayEndTime(), addJobEmail.getTimeFormat()));
		    					  if(jobLog.getEstCompleteDt() != null)
		    						  addJobContract.setEstCompleteDt(DateUtil.formatDate(jobLog.getEstCompleteDt(), dateFormat));
		    					  if(jobLog.getEstCompleteTime() != null)
		    						  addJobContract.setEstCompleteTime(DateUtil.formatDate(jobLog.getEstCompleteTime(), addJobEmail.getTimeFormat()));
		    					  if(jobLog.getCompleteDt() != null)
		    						  addJobContract.setCompleteDt(DateUtil.formatDate(jobLog.getCompleteDt(), dateFormat));
		    					  if(jobLog.getCompleteTime() != null)
		    						  addJobContract.setCompleteTime(DateUtil.formatDate(jobLog.getCompleteTime(), addJobEmail.getTimeFormat()));
		    					  if(jobLog.getHoseOffDt() != null)
		    						  addJobContract.setHoseOffDt(DateUtil.formatDate(jobLog.getHoseOffDt(), dateFormat));
		    					  if(jobLog.getHoseOffTime() != null)
		    						  addJobContract.setHoseOffTime(DateUtil.formatDate(jobLog.getHoseOffTime(), addJobEmail.getTimeFormat()));
		    					  if(jobLog.getReleaseDt() != null)
		    						  addJobContract.setReleaseDt(DateUtil.formatDate(jobLog.getReleaseDt(), dateFormat));
		    					  if(jobLog.getReleaseTime() != null)
		    						  addJobContract.setReleaseTime(DateUtil.formatDate(jobLog.getReleaseTime(), addJobEmail.getTimeFormat()));
		    					  if(jobLog.getSampleReceiveDt() != null)
		    						  addJobContract.setSampleReceiveDt(DateUtil.formatDate(jobLog.getSampleReceiveDt(), dateFormat));
		    					  if(jobLog.getSampleReceiveTime() != null)
		    						  addJobContract.setSampleReceiveTime(DateUtil.formatDate(jobLog.getSampleReceiveTime(), addJobEmail.getTimeFormat()));
		    					  if(jobLog.getPrelimReportDt() != null)
		    						  addJobContract.setPrelimReportDt(DateUtil.formatDate(jobLog.getPrelimReportDt(), dateFormat));
		    					  if(jobLog.getPrelimReportTime() != null)
		    						  addJobContract.setPrelimReportTime(DateUtil.formatDate(jobLog.getPrelimReportTime(), addJobEmail.getTimeFormat()));
		    					  if(jobLog.getSampleShipDt() != null)
		    						  addJobContract.setSampleShipDt(DateUtil.formatDate(jobLog.getSampleShipDt(), dateFormat));
		    					  if(jobLog.getSampleShipTime() != null)
		    						  addJobContract.setSampleShipTime(DateUtil.formatDate(jobLog.getSampleShipTime(), addJobEmail.getTimeFormat()));
		    					  if(jobLog.getFinalReportDt() != null)
		    						  addJobContract.setFinalReportDt(DateUtil.formatDate(jobLog.getFinalReportDt(), dateFormat));
		    					  if(jobLog.getFinalReportTime() != null)
		    						  addJobContract.setFinalReportTime(DateUtil.formatDate(jobLog.getFinalReportTime(), addJobEmail.getTimeFormat()));
		    					  if(jobLog.getDistributeDt() != null)
		    						  addJobContract.setDistributeDt(DateUtil.formatDate(jobLog.getDistributeDt(), dateFormat));
		    					  if(jobLog.getDistributeTime() != null)
		    						  addJobContract.setDistributeTime(DateUtil.formatDate(jobLog.getDistributeTime(), addJobEmail.getTimeFormat()));
		    					  if(jobLog.getSummaryDt() != null)
		    						  addJobContract.setSummaryDt(DateUtil.formatDate(jobLog.getSummaryDt(), dateFormat));
		    					  if(jobLog.getCoordinator()!=null)
		    						  addJobContract.setCoordinator(jobLog.getCoordinator());
		    					  
		    					  //addJobContract = jobService.getJobLogDateandTime(addJobContract, jobLog);
		    				  }
		    				  addJobContracts[jcCount] = addJobContract;
		    				  jcCount++;
		    			  }
		    			  addJobOrder.setAddJobContracts(addJobContracts);
		    			  
		    		  }
		    		  addJobOrder.setEmailNote(emailNote);
		    		  addJobOrder.setEmailNoteDisplay(emailNoteDisplay);
		    		  addJobOrders[j] = addJobOrder;
		    	  }
		    	  jobEmail.setAddJobOrders(addJobOrders);
	    	  }
	    	  
	    	  
	    	  if(scheduler.getWorkEmail() != null)
	    		  jobEmail.setTo(scheduler.getWorkEmail());
	    	  else
	    		  jobEmail.setTo("");
	    	  
	    	  
	    	  jobEmail.setCc(user.getEmail());
	    	  //jobEmail.setCoordinator(user.getFirstName() + " " + user.getLastName());
	    	  jobEmail.setCoordinator(jobEmail.getAddJobOrders()[0].getAddJobContracts()[0].getCoordinator());
	    	  jobEmail.setSubject(Constants.DEFAULT_EMAIL_SUB);
	    	  jobEmail.setCustCode(jobEmail.getAddJobOrders()[0].getAddJobContracts()[0].getJobContract().getCustCode());
	    	  if(jobEmail.getAddJobOrders()[0].getAddJobContracts()[0].getJobContract().getBillingContact() != null)
	    		  jobEmail.setContactId(jobEmail.getAddJobOrders()[0].getAddJobContracts()[0].getJobContract().getBillingContact().getId());
	    	  
	    	  StringBuffer sb = new StringBuffer();
	    	  sb.append("Dear ");
	    	  sb.append(scheduler.getFirstName() + " " + scheduler.getLastName() + " / ");
	    	  sb.append(jobEmail.getAddJobOrders()[0].getAddJobContracts()[0].getJobContract().getCustomer().getName() + ",");
	    	  sb.append(System.getProperty("line.separator"));
	    	  sb.append(System.getProperty("line.separator"));
	    	  sb.append("Please find below the status of "+ jobEmail.getAddJobOrders().length +" job(s) you have nominated Intertek Caleb Brett as the independent inspector.\n");
	    	  
	    	  jobEmail.setIntroduction(sb.toString());
	    	  String introToDisplay = jobEmail.getIntroduction().replaceAll(System.getProperty("line.separator"), "<br>");
	    	  jobEmail.setIntroductionToDisplay(introToDisplay);
	    	  jobEmails[i] = jobEmail;
	      }
	      addJobEmail.setJobEmails(jobEmails);
	      if(jobEmails != null)
	    	  addJobEmail.setEmailCount(jobEmails.length);
	      	  
	      
	    }
	  addJobEmail.setEmailIndex(0);
	  } catch(ServiceException e)
	  {
	    	throw new ServiceException(e.getMessage(), e.getParams(), e);
	  }catch(Throwable t)
	  {
	        t.printStackTrace();
	        throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
	  }
	  return addJobEmail;
  }
}
