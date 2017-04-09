package com.intertek.web.controller.job;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ajaxtags.helpers.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.AbstractController;

import com.intertek.entity.Bank;
import com.intertek.entity.BankAccount;
import com.intertek.entity.Event;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.AdminService;
import com.intertek.service.ContractService;
import com.intertek.service.JobService;
import com.intertek.service.JobTypeService;
import com.intertek.service.ProjectService;
import com.intertek.service.UserService;
import com.intertek.util.DateUtil;
import com.intertek.util.SecurityUtil;

public class JobOrderController extends AbstractController {
	private static Log log = LogFactory.getLog(JobOrderController.class);

	/**
	 * .ctor
	 */
	public JobOrderController() {
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 * <context dynamic="true|false">
	 <User Name="jaloia111" />
	 <Type Name="MANIFEST_FILE" />
	 </context>
	 */
	protected ModelAndView handleRequestInternal(HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String xml = "";
		//String bankCode = "";
		String bankDesc = "";
		String bankAccountDesc="";
		//String bankAccount = "";
		String currency="";
		String buName="";
		String branchCode="";
		String  portCode="";
		String contractCode="";
		List bankCodes=new ArrayList();  		   
		List bankAccounts = new ArrayList();
		List zonedes =new ArrayList();
		List portLocations=new ArrayList();
		String priceBookId="";

		

		JobService jobService = (JobService) ServiceLocator.getInstance().getBean("jobService");
		
		ProjectService projectService  = (ProjectService ) ServiceLocator.getInstance().getBean("projectService");

		UserService userService = (UserService) ServiceLocator.getInstance().getBean("userService");
		
		JobTypeService jobTypeService = (JobTypeService) ServiceLocator.getInstance().getBean("jobTypeService");

		AdminService adminService = (AdminService) ServiceLocator.getInstance().getBean("adminService");

		ContractService contractService=(ContractService)ServiceLocator.getInstance().getBean("contractService");

		log.info("======== in JobOrderController: JobOrderService = "+ jobService);

		String receivedBy = request.getParameter("recivedBy");
		String nominationTimeZone = request.getParameter("nominationTimeZone");
		String etaTimeZone = request.getParameter("etaTimeZone");
        String dTimeZone = request.getParameter("dTimeZone");
		String pTimeZone = request.getParameter("pTimeZone");
		String projectNumber = request.getParameter("projectNumber");
		log.info("======== in JobOrderController: ReceivedByName = "	+ receivedBy);

     	String fromJobId = request.getParameter("fromJobId");
		String toJobId = request.getParameter("toJobId");
		String confName = request.getParameter("confName");
		
		
		
		if(confName != null	&& (!confName.trim().equals("")))
		{
			System.out.println("Ajax ConfName="+confName);
			String uname=SecurityUtil.getUser().getLoginName();
			List confNames = jobService.getConfName(confName,uname );
			System.out.println("ConfNames size"+confNames.size());
			xml = new AjaxXmlBuilder().addItems(confNames , "confListName","confListName").toString();
		}
		if(fromJobId != null	&& (!fromJobId.trim().equals("")))
		{
		List fromJobIds = jobService.getJobIdById(fromJobId );		
		xml = new AjaxXmlBuilder().addItems(fromJobIds , "jobNumber","jobNumber").toString();
		}

		if(toJobId != null&& (!toJobId.trim().equals("")))
		{
		List toJobIds = jobService.getJobIdById(toJobId);			
		xml = new AjaxXmlBuilder().addItems(toJobIds , "jobNumber","jobNumber").toString();
		}

		if(receivedBy != null	&& (!receivedBy.trim().equals("")))
		{
		List recivedBys = userService.getUsersByName(receivedBy);				
		xml = new AjaxXmlBuilder().addItems(recivedBys , "loginName","loginName").toString();
		}

		if(nominationTimeZone != null	&& (!nominationTimeZone.trim().equals("")))
        {
		List timeZones = adminService.getTimeZonesByName(nominationTimeZone);
		xml = new AjaxXmlBuilder().addItems(timeZones, "timeZone",	"timeZone").toString();
		}

		if (etaTimeZone != null	&& (!etaTimeZone.trim().equals(""))) {			
		List timeZones = adminService.getTimeZonesByName(etaTimeZone);
		xml = new AjaxXmlBuilder().addItems(timeZones, "timeZone",	"timeZone").toString();
		}

		if(dTimeZone != null&& (!dTimeZone.trim().equals("")))
		{
		List timeZones = adminService.getTimeZonesByName(dTimeZone);
		xml = new AjaxXmlBuilder().addItems(timeZones, "timeZone",	"timeZone").toString();
		}

		if (pTimeZone != null 	&& (!pTimeZone.trim().equals(""))) 
		{
		List timeZones = adminService.getTimeZonesByName(pTimeZone);
		xml = new AjaxXmlBuilder().addItems(timeZones, "timeZone",	"timeZone").toString();
		}

				
		if(request.getParameter("projectNumber") != null && (!request.getParameter("projectNumber").trim().equals(""))&&
		request.getParameter("custCode")!=null && (!request.getParameter("custCode").trim().equals("")) )
		{
		projectNumber = request.getParameter("projectNumber");
		String custCode=request.getParameter("custCode");
		List projectNumbers = projectService.getProjectsByProjectNumber(projectNumber,custCode);
		xml = new AjaxXmlBuilder().addItems(projectNumbers, "projectNumber",	"projectNumber").toString();
		}

		if(request.getParameter("bankDesc") != null && (!request.getParameter("bankDesc").trim().equals(""))&&
		request.getParameter("buName")!=null && (!request.getParameter("buName").trim().equals("")) &&
		request.getParameter("currency")!=null && (!request.getParameter("currency").trim().equals(""))	)
		{
		currency=request.getParameter("currency");
		bankDesc = request.getParameter("bankDesc");
		buName=request.getParameter("buName");
		bankCodes = jobService.getBankByBankCode(bankDesc,buName,currency);
        AjaxXmlBuilder xmlBuilder =  new AjaxXmlBuilder();
			for(int i=0;i<bankCodes.size();i++)
			{
			Bank bank = (Bank) bankCodes.get(i);
			String bcode = bank.getBankCode()+"-"+bank.getBankDesc();
			String value = bank.getBankCode();
			xmlBuilder.addItem(bcode,value);
			}
			xml = xmlBuilder.toString();
		}


		if(request.getParameter("bankAccountDesc") != null && (!request.getParameter("bankAccountDesc").trim().equals(""))
		&&  request.getParameter("buName")!=null && (!request.getParameter("buName").trim().equals(""))&&
		request.getParameter("currency")!=null && (!request.getParameter("currency").trim().equals("")))
		{
		currency=request.getParameter("currency");
		bankAccountDesc = request.getParameter("bankAccountDesc");
		buName=request.getParameter("buName");
		if(request.getParameter("bankCode")!=null &&(!request.getParameter("bankCode").trim().equals("")))
		{
            String bankCode=request.getParameter("bankCode");
			bankAccounts=jobService.getBankAccountByBankCode(bankAccountDesc,buName,currency,bankCode);		
        }
		else
			{
			bankAccounts = jobService.getBankAccountByAccount(bankAccountDesc,buName,currency);
			}
            AjaxXmlBuilder xmlBuilder =  new AjaxXmlBuilder();
			for(int i=0;i<bankAccounts.size();i++)
			{
			BankAccount bankAccount = (BankAccount) bankAccounts.get(i);
			String bcode = bankAccount.getBankAccountId().getBankAcctCode()+"-"+bankAccount.getBankAcctDesc();
			String value =bankAccount.getBankAccountId().getBankAcctCode();
			xmlBuilder.addItem(bcode,value);
			}
			xml = xmlBuilder.toString();
		}


		if(request.getParameter("contractCode")!=null && (!request.getParameter("contractCode").trim().equals(""))
		&&request.getParameter("branchCode")!=null &&(!request.getParameter("branchCode").trim().equals("")))
		{
			if(request.getParameter("portCode") != null && (!request.getParameter("portCode").trim().equals(""))&&
				(!request.getParameter("portCode").trim().equals("NONE")))
			{
			portCode = request.getParameter("portCode");
			contractCode=request.getParameter("contractCode");
			String jdate=request.getParameter("jdate");
			String edate=request.getParameter("edate");
			Date asOfDate=new Date();
			priceBookId=request.getParameter("priceBookId");

			if(jdate!=null&&!jdate.trim().equals("")&&edate==null&&edate.trim().equals(""))
			{
			asOfDate=DateUtil.parseDate(jdate,"dd/MM/yyyy");	
			}
			else if(jdate==null&&jdate.trim().equals("") &&edate!=null&&!edate.trim().equals(""))
			{
			asOfDate=DateUtil.parseDate(edate,"dd/MM/yyyy");
			}
			else if(jdate==null&&jdate.trim().equals("") &&edate==null&&!edate.trim().equals(""))
			{ 
			asOfDate=new Date();
			}
		    String dt=DateUtil.formatDate(asOfDate,"dd-MMM-yyyy");
			portLocations = jobService.getZoneByPortCodeandContractCode(portCode,contractCode, priceBookId,dt);
			xml = new AjaxXmlBuilder().addItems(portLocations, "portLocationId.location", "portLocationId.location").toString();
			}
				else if(request.getParameter("portCode").trim().equals("NONE")||request.getParameter("portCode").trim().equals(""))
				{
				 branchCode=request.getParameter("branchCode");
				 contractCode=request.getParameter("contractCode");
				 String jdate=request.getParameter("jdate");
			     String edate=request.getParameter("edate");
			     Date asOfDate=new Date();
				 priceBookId=request.getParameter("priceBookId");

			if(jdate!=null&&!jdate.trim().equals("")&&edate==null&&edate.trim().equals(""))
			{
			asOfDate=DateUtil.parseDate(jdate,"dd/MM/yyyy");	
			}
			else if(jdate==null&&jdate.trim().equals("") &&edate!=null&&!edate.trim().equals(""))
			{
			asOfDate=DateUtil.parseDate(edate,"dd/MM/yyyy");
			}
			else if(jdate==null&&jdate.trim().equals("") &&edate==null&&!edate.trim().equals(""))
			{ 
			asOfDate=new Date();
			}
		    String dt=DateUtil.formatDate(asOfDate,"dd-MMM-yyyy");

				zonedes = jobService.getZoneIdByBranchCodeandContractCode(branchCode,contractCode,priceBookId,dt);
				if(zonedes!=null && zonedes.size()!=0)
				{
				xml = new AjaxXmlBuilder().addItems(zonedes, "branchLocationId.location", "branchLocationId.location").toString();
				}
				else
				{
				AjaxXmlBuilder xmlBuilder =  new AjaxXmlBuilder();
				String bcode = "NONE";
				String value = "NONE";
				xmlBuilder.addItem(bcode,value);
				xml = xmlBuilder.toString();
		  		}                 
			}
		}

		if(request.getParameter("jobTypeCode") != null && (!request.getParameter("jobTypeCode").trim().equals("")))
		{
		String jobTypeCode = request.getParameter("jobTypeCode");
		List jobTypeCodes = jobTypeService.getJobDescByJobType(jobTypeCode);
		xml = new AjaxXmlBuilder().addItems(jobTypeCodes, "jobTypeCode","jobTypeDesc").toString();
		}

		if(request.getParameter("operationName") != null && (!request.getParameter("operationName").trim().equals("")))
		{
		String operationName = request.getParameter("operationName");
		
		List operationNames = jobTypeService.getOperationDescByOperationType(operationName);
		xml = new AjaxXmlBuilder().addItems(operationNames, "operationCode","description").toString();
		}
		if(request.getParameter("eventCode") != null && (!request.getParameter("eventCode").trim().equals("")))
		{
		String eventCode = request.getParameter("eventCode");
		System.out.println("eventCode in ajax controller :"+eventCode);
		Event event = jobService.getEventByEventCode(eventCode);
		
		  xml = new AjaxXmlBuilder().addItem(event.getEventInstruction(),event.getEventInstruction()).toString();
		  xml = new AjaxXmlBuilder().addItemAsCData(event.getEventInstruction(),event.getEventInstruction()).toString();
		  Map model = new HashMap();
		  model.put("Content", xml);

		  View view = (View)getApplicationContext().getBean("xmlView");
		  return new ModelAndView(view, model);

		}
		if(request.getParameter("createdby")!=null && (!request.getParameter("createdby").trim().equals("")))
		{
			String createdby=request.getParameter("createdby");
			List users = userService.getUsersByName(createdby);				
			xml = new AjaxXmlBuilder().addItems(users , "loginName","loginName").toString();
		}

		if(request.getParameter("modifiedby")!=null && (!request.getParameter("modifiedby").trim().equals("")))
		{
			String modifiedby=request.getParameter("modifiedby");
			List users = userService.getUsersByName(modifiedby);				
			xml = new AjaxXmlBuilder().addItems(users , "loginName","loginName").toString();
		}


     if(request.getParameter("description")!=null && (!request.getParameter("description").trim().equals("")))
	   {
            String desc=request.getParameter("description");
			List descriptions = contractService.getDescriptionByContractDescription(desc);				
			xml = new AjaxXmlBuilder().addItems(descriptions , "description","description").toString();
	   }


      
	 
      if(request.getParameter("branchName") != null&& (!request.getParameter("branchName").trim().equals(""))
		  &&request.getParameter("monthlyJobId")!=null && (!request.getParameter("monthlyJobId").trim().equals(""))
		  &&request.getParameter("contractCode")!=null && (!request.getParameter("contractCode").trim().equals("")))
		{
    	String cCode=request.getParameter("contractCode");
		String monthlyJobId = request.getParameter("monthlyJobId");
        String branchName= request.getParameter("branchName");
        String parentCustCode=request.getParameter("parentCode");
        List monthlyJobIds = jobService.getMonthlyJobIdById(monthlyJobId,branchName,cCode,parentCustCode);			
		xml = new AjaxXmlBuilder().addItems(monthlyJobIds , "jobNumber","jobNumber").toString();
		}



	Map model = new HashMap();
	model.put("Content", xml);
	View view = (View) getApplicationContext().getBean("xmlView");
	return new ModelAndView(view, model);
	}
}
