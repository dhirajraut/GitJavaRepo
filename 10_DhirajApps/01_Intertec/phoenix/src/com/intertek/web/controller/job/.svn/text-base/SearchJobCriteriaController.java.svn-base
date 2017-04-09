package com.intertek.web.controller.job;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;

import com.intertek.domain.JobSearch;
import com.intertek.entity.JobSearchCriteria;
import com.intertek.entity.UserSettings;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.CriteriaService;
import com.intertek.service.UserService;
import com.intertek.util.CommonUtil;
import com.intertek.util.SecurityUtil;

public class SearchJobCriteriaController extends SearchJobResultsController{
	
	public SearchJobCriteriaController() {
		super();
	}

	protected ModelAndView onSubmit(HttpServletRequest request,HttpServletResponse response, Object command, BindException errors) throws Exception{
		JobSearch jobSearch = (JobSearch)command;
		String criteriaAction=request.getParameter("criteriaAction");
		if(criteriaAction==null){
			criteriaAction="";
		}
		
		boolean needReload=false;
		CriteriaService criteriaService=(CriteriaService) ServiceLocator.getInstance().getBean("criteriaService");
		String loginName=SecurityUtil.getUser().getLoginName();
		
		if(criteriaAction.equalsIgnoreCase("save")){
			long id=jobSearch.getJobSearchCriteriaId();
			JobSearchCriteria jobSearchCriteria1=getJobSearchCriteriaById(request, id);
			JobSearchCriteria jobSearchCriteria=getJobSearchCriteria(jobSearch);
			jobSearchCriteria.setId(id);
			if(jobSearchCriteria1==null){
				jobSearchCriteria.setSearchName(jobSearch.getJobSearchCriteriaName());
			}
			else{
				jobSearchCriteria.setSearchName(jobSearchCriteria1.getSearchName());
			}
			jobSearchCriteria.setLoginName(loginName);
			
			criteriaService.saveJobSearchCriteria(jobSearchCriteria);
			needReload=true;
		}
		else if(criteriaAction.equalsIgnoreCase("saveAs")){
			JobSearchCriteria jobSearchCriteria=getJobSearchCriteria(jobSearch);
			jobSearchCriteria.setId(0);
			jobSearchCriteria.setSearchName(jobSearch.getJobSearchCriteriaName());
			jobSearchCriteria.setLoginName(loginName);
			jobSearchCriteria=criteriaService.saveJobSearchCriteria(jobSearchCriteria);
			jobSearch.setJobSearchCriteriaId(jobSearchCriteria.getId());
			needReload=true;
		}
		else if(criteriaAction.equalsIgnoreCase("load")){
			long id=jobSearch.getJobSearchCriteriaId();
			setJobSearch(jobSearch, getJobSearchCriteriaById(request, id));
		}
		else if(criteriaAction.equalsIgnoreCase("setAsDefault")){
			long id=jobSearch.getJobSearchCriteriaId();
			UserService userService = (UserService) ServiceLocator.getInstance().getBean("userService");
			UserSettings userSettings=userService.getUserSettings(loginName);
			if(userSettings==null){
				userSettings=new UserSettings();
				userSettings.setLoginName(loginName);
			}
			userSettings.setJobSearchCriteriaId(id);
			userService.saveUserSettings(userSettings);
		}
		
		if(needReload){
		    List<JobSearchCriteria> userJobSearchCriteria=criteriaService.getJobSearchCriteria(loginName);
		    request.getSession().setAttribute("mySearchJobCriteria", userJobSearchCriteria);
		}
		
		return showForm(request, response, errors);
	}
	

	public static JobSearchCriteria getJobSearchCriteria(JobSearch jobSearch){
		if(jobSearch==null){
			return null;
		}
		JobSearchCriteria jobSearchCriteria=new JobSearchCriteria();
		jobSearchCriteria.setBuName(jobSearch.getBusinessUnit().getValue());
		jobSearchCriteria.setBranchCode(jobSearch.getBranch().getValue());
		jobSearchCriteria.setJobLogStatus(jobSearch.getStatus().getValue());
		jobSearchCriteria.setJobType(jobSearch.getJobType().getValue());
		jobSearchCriteria.setFromJobId(jobSearch.getFromJobId().getValue());
		jobSearchCriteria.setToJobId(jobSearch.getToJobId().getValue());
		jobSearchCriteria.setFromJobFinishDate(jobSearch.getFromJobFinshDate().getValue());
		jobSearchCriteria.setToJobFinishDate(jobSearch.getToJobFinshDate().getValue());
		jobSearchCriteria.setFromETA(jobSearch.getEtaFrom().getValue());
		jobSearchCriteria.setToETA(jobSearch.getEtaTo().getValue());
		jobSearchCriteria.setFromNomDate(jobSearch.getNomDateFrom().getValue());
		jobSearchCriteria.setToNomDate(jobSearch.getNomDateTo().getValue());
		jobSearchCriteria.setVesselOperator(jobSearch.getVessel().getOperator()+"");
		jobSearchCriteria.setVessel(jobSearch.getVessel().getValue());
		jobSearchCriteria.setProductOperator(jobSearch.getProduct().getOperator()+"");
		jobSearchCriteria.setProduct(jobSearch.getProduct().getValue());
		jobSearchCriteria.setCustRefNum(jobSearch.getCustRefNum().getValue());
		jobSearchCriteria.setIcbRefNum(jobSearch.getIcbRefNum().getValue());
		jobSearchCriteria.setInvoice(jobSearch.getInvoice().getValue());
		jobSearchCriteria.setInvoiceStatus(jobSearch.getInvoiceStatus().getValue());
		jobSearchCriteria.setContractDescription(jobSearch.getContractDescription().getValue());
		jobSearchCriteria.setContractIdOperator(jobSearch.getContractId().getOperator()+"");
		jobSearchCriteria.setContractId(jobSearch.getContractId().getValue());
		jobSearchCriteria.setCreatedBy(jobSearch.getCreatedBy().getValue());
		jobSearchCriteria.setModifiedBy(jobSearch.getModifiedBy().getValue());
		jobSearchCriteria.setPortOperator(jobSearch.getPort().getOperator()+"");
		jobSearchCriteria.setPort(jobSearch.getPort().getValue());
		jobSearchCriteria.setServiceLocationOperator(jobSearch.getSvcLocation().getOperator()+"");
		jobSearchCriteria.setServiceLocation(jobSearch.getSvcLocation().getValue());
		jobSearchCriteria.setCompanyIdOperator(jobSearch.getCompanyId().getOperator()+"");
		jobSearchCriteria.setCompanyId(jobSearch.getCompanyId().getValue());
		jobSearchCriteria.setCompanyOperator(jobSearch.getCompany().getOperator()+"");
		jobSearchCriteria.setCompany(jobSearch.getCompany().getValue());
		jobSearchCriteria.setContactIdOperator(jobSearch.getBillingContactId().getOperator()+"");
		jobSearchCriteria.setContactId(jobSearch.getBillingContactId().getValue());
		jobSearchCriteria.setBillingContactOperator(jobSearch.getBillingContact().getOperator()+"");
		jobSearchCriteria.setBillingContact(jobSearch.getBillingContact().getValue());
		jobSearchCriteria.setSchedulerIdOperator(jobSearch.getSchedulerId().getOperator()+"");
		jobSearchCriteria.setSchedulerId(jobSearch.getSchedulerId().getValue()+"");
		jobSearchCriteria.setSchedulerOperator(jobSearch.getScheduler().getOperator()+"");
		jobSearchCriteria.setScheduler(jobSearch.getScheduler().getValue());
	
		return jobSearchCriteria;
	}
	
	public static void setJobSearch(JobSearch jobSearch, JobSearchCriteria jobSearchCriteria){
		if(jobSearchCriteria==null || jobSearch==null){
			return;
		}
		jobSearch.setJobSearchCriteriaId(jobSearchCriteria.getId());
		jobSearch.getBusinessUnit().setValue(jobSearchCriteria.getBuName());
		jobSearch.getBranch().setValue(jobSearchCriteria.getBranchCode());
		jobSearch.getStatus().setValue(jobSearchCriteria.getJobLogStatus());
		jobSearch.getJobType().setValue(jobSearchCriteria.getJobType());
		jobSearch.getFromJobId().setValue(jobSearchCriteria.getFromJobId());
		jobSearch.getToJobId().setValue(jobSearchCriteria.getToJobId());
		jobSearch.getFromJobFinshDate().setValue(jobSearchCriteria.getFromJobFinishDate());
		jobSearch.getToJobFinshDate().setValue(jobSearchCriteria.getToJobFinishDate());
		jobSearch.getEtaFrom().setValue(jobSearchCriteria.getFromETA());
		jobSearch.getEtaTo().setValue(jobSearchCriteria.getToETA());
		jobSearch.getNomDateFrom().setValue(jobSearchCriteria.getFromNomDate());
		jobSearch.getNomDateTo().setValue(jobSearchCriteria.getToNomDate());
		jobSearch.getVessel().setOperator(CommonUtil.parseInt(jobSearchCriteria.getVesselOperator()));
		jobSearch.getVessel().setValue(jobSearchCriteria.getVessel());
		jobSearch.getProduct().setOperator(CommonUtil.parseInt(jobSearchCriteria.getProductOperator()));
		jobSearch.getProduct().setValue(jobSearchCriteria.getProduct());
		jobSearch.getCustRefNum().setValue(jobSearchCriteria.getCustRefNum());
		jobSearch.getIcbRefNum().setValue(jobSearchCriteria.getIcbRefNum());
		jobSearch.getInvoice().setValue(jobSearchCriteria.getInvoice());
		jobSearch.getInvoiceStatus().setValue(jobSearchCriteria.getInvoiceStatus());
		jobSearch.getContractDescription().setValue(jobSearchCriteria.getContractDescription());
		jobSearch.getContractId().setOperator(CommonUtil.parseInt(jobSearchCriteria.getContractIdOperator()));
		jobSearch.getContractId().setValue(jobSearchCriteria.getContractId());
		jobSearch.getCreatedBy().setValue(jobSearchCriteria.getCreatedBy());
		jobSearch.getModifiedBy().setValue(jobSearchCriteria.getModifiedBy());
		jobSearch.getPort().setOperator(CommonUtil.parseInt(jobSearchCriteria.getPortOperator()));
		jobSearch.getPort().setValue(jobSearchCriteria.getPort());
		jobSearch.getSvcLocation().setOperator(CommonUtil.parseInt(jobSearchCriteria.getServiceLocationOperator()));
		jobSearch.getSvcLocation().setValue(jobSearchCriteria.getServiceLocation());
		jobSearch.getCompanyId().setOperator(CommonUtil.parseInt(jobSearchCriteria.getCompanyIdOperator()));
		jobSearch.getCompanyId().setValue(jobSearchCriteria.getCompanyId());
		jobSearch.getCompany().setOperator(CommonUtil.parseInt(jobSearchCriteria.getCompanyOperator()));
		jobSearch.getCompany().setValue(jobSearchCriteria.getCompany());
		
		jobSearch.getBillingContact().setOperator(CommonUtil.parseInt(jobSearchCriteria.getBillingContactOperator()));
		jobSearch.getBillingContact().setValue(jobSearchCriteria.getBillingContact());
		
		jobSearch.getBillingContactId().setOperator(CommonUtil.parseInt(jobSearchCriteria.getContactIdOperator()));
		jobSearch.getBillingContactId().setValue(jobSearchCriteria.getContactId());
		
		jobSearch.getSchedulerId().setOperator(CommonUtil.parseInt(jobSearchCriteria.getSchedulerIdOperator()));
		jobSearch.getSchedulerId().setValue(CommonUtil.parseLong(jobSearchCriteria.getSchedulerId()));
		jobSearch.getScheduler().setOperator(CommonUtil.parseInt(jobSearchCriteria.getSchedulerOperator()));
		jobSearch.getScheduler().setValue(jobSearchCriteria.getScheduler());
		
	}

	public static JobSearchCriteria getJobSearchCriteriaById(HttpServletRequest request, long id){
		List<JobSearchCriteria> userJobSearchCriteria=(List<JobSearchCriteria>)request.getSession().getAttribute("mySearchJobCriteria");
		for(JobSearchCriteria jsc:userJobSearchCriteria){
			if(jsc!=null && jsc.getId()==id){
				return jsc;
			}
		}
		return null;
	}		
}
