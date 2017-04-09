package com.intertek.web.controller.job;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.intertek.domain.AddJobContract;
import com.intertek.domain.AddJobOrder;
import com.intertek.domain.ContractSearch;
import com.intertek.domain.JobSearch;
import com.intertek.domain.StringSearchField;
import com.intertek.entity.Bank;
import com.intertek.entity.BankAccount;
import com.intertek.entity.Branch;
import com.intertek.entity.BusinessUnit;
import com.intertek.entity.CfgContract;
import com.intertek.entity.Contact;
import com.intertek.entity.ContactCust;
import com.intertek.entity.ContractCustContact;
import com.intertek.entity.Country;
import com.intertek.entity.CountryVAT;
import com.intertek.entity.CustAddress;
import com.intertek.entity.JobContract;
import com.intertek.entity.JobContractAttach;
import com.intertek.entity.JobContractInvoice;
import com.intertek.entity.JobOrder;
import com.intertek.entity.JobProd;
import com.intertek.entity.JobVessel;
import com.intertek.entity.Prebill;
import com.intertek.entity.PriceBook;
import com.intertek.entity.ReferenceField;
import com.intertek.entity.ServiceLocation;
import com.intertek.entity.ShippingAgent;
import com.intertek.entity.TowingCompany;
import com.intertek.entity.User;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.CountryService;
import com.intertek.service.CustomerService;
import com.intertek.service.JobService;
import com.intertek.service.PrebillService;
import com.intertek.service.ServiceLocationService;
import com.intertek.service.ShippingAgentService;
import com.intertek.service.TowingCompanyService;
import com.intertek.service.UserService;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;
import com.intertek.util.InvoiceUtil;
import com.intertek.util.JobUtil;
import com.intertek.util.SecurityUtil;
import com.intertek.util.StringUtil;

public class EditJobOrderAnalyticalServiceFormController extends CommonJobOrder {
	public EditJobOrderAnalyticalServiceFormController() {
		super();
		setSessionForm(true);
		setCommandClass(AddJobOrder.class);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      org.springframework.validation.BindException)
	 */
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
	throws Exception {

		AddJobOrder addJobOrder = (AddJobOrder) command;
		JobOrder jobOrder = addJobOrder.getJobOrder();
		AddJobContract[] addJobContracts = addJobOrder.getAddJobContracts();


		JobService jobService = (JobService) ServiceLocator.getInstance().getBean("jobService");
		TowingCompanyService towingCompanyService = (TowingCompanyService) ServiceLocator.getInstance().getBean("towingCompanyService");
		CustomerService customerService = (CustomerService) ServiceLocator.getInstance().getBean("customerService");
		ShippingAgentService shippingAgentService = (ShippingAgentService) ServiceLocator.getInstance().getBean("shippingAgentService");
		ServiceLocationService serviceLocationService = (ServiceLocationService) ServiceLocator.getInstance().getBean("serviceLocationService");
		CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");
		UserService userService = (UserService) ServiceLocator.getInstance().getBean("userService");

		//	 Validating branch
		if(jobOrder != null && jobOrder.getBranchName()!= null)
		{
			Branch branch = userService.getBranchByName(jobOrder.getBranchName());
			if(branch != null && branch.getType()!= null && branch.getType().trim().equalsIgnoreCase(Constants.BRANCH_TYPE)){
				errors.reject("job.branch.error",
						new Object[] { jobOrder.getBranchName()}, null);
				return showForm(request, response, errors);
			}
		}
		//end
		String warnUserFlag = addJobOrder.getWarnUserFlag();

		if((warnUserFlag != null) && "warn".equals(warnUserFlag))
		{
			addJobOrder.setWarnUserFlag("warned");
			//errors.reject("edit.job.navigation.warning", new Object[] {"Unsaved changes if any will be lost.Please save the changes"}, null);
			errors.reject(" unsaved.changes.save.error", new Object[] {}, null);
			return showForm(request, response, errors);
		}

		if((warnUserFlag != null) && "navigate".equals(warnUserFlag))
		{
			String navigationUrl = addJobOrder.getNavigationUrl();
			if(navigationUrl != null)
			{
				return new ModelAndView(new RedirectView(navigationUrl));
			}
			else
				return showForm(request, response, errors);
		}




		Date dtUpdate = new Date();

		String jobNumber = null;
		TowingCompany towingCompany = jobOrder.getTowingCompany();
		ShippingAgent shippingAgent = jobOrder.getShippingAgent();
		ServiceLocation serviceLocation = jobOrder.getServiceLocation();

		jobOrder.setUpdateTime(dtUpdate);
		String searchFiled = addJobOrder.getSearchField();
		String searchString = addJobOrder.getSearchString();
		String goFlag=request.getParameter("goFlag");
		if (searchString != null && !searchString.trim().equals("")&&goFlag!=null && goFlag.trim().equals("true"))
		{
			if(searchFiled.equals("1"))
			{
				try {
					jobOrder = jobService.getJobOrderByJobNumber(searchString);

					if (jobOrder != null) {

						if(jobOrder.getJobStatus().equals(Constants.OPEN_STATUS)||jobOrder.getJobStatus().equals(Constants.REOPENED_STATUS))
						{
							if(jobOrder.getJobType().equalsIgnoreCase(Constants.INSPECTION_JOBTYPE))
								return new ModelAndView(new RedirectView("edit_job_entry_insp.htm"), "jobNumber", searchString);
							if(jobOrder.getJobType().equalsIgnoreCase(Constants.MARINE_JOBTYPE))
								return new ModelAndView(new RedirectView("edit_job_entry_marine.htm"), "jobNumber", searchString);
							if(jobOrder.getJobType().equalsIgnoreCase(Constants.ANALYTICAL_SERVICE_JOBTYPE))
								return new ModelAndView(new RedirectView("edit_job_entry_analytical_service.htm"), "jobNumber", searchString);
							if(jobOrder.getJobType().equalsIgnoreCase(Constants.OUTSOURCING_JOBTYPE))
								return new ModelAndView(new RedirectView("edit_job_entry_outsourcing.htm"), "jobNumber", searchString);
						}else
						{
							if(jobOrder.getJobType().equalsIgnoreCase(Constants.INSPECTION_JOBTYPE))
								return new ModelAndView(new RedirectView("view_job_entry_insp.htm"), "jobNumber", searchString);
							if(jobOrder.getJobType().equalsIgnoreCase(Constants.MARINE_JOBTYPE))
								return new ModelAndView(new RedirectView("view_job_entry_marine.htm"), "jobNumber", searchString);
							if(jobOrder.getJobType().equalsIgnoreCase(Constants.ANALYTICAL_SERVICE_JOBTYPE))
								return new ModelAndView(new RedirectView("view_job_entry_analytical_service.htm"), "jobNumber", searchString);
							if(jobOrder.getJobType().equalsIgnoreCase(Constants.OUTSOURCING_JOBTYPE))
								return new ModelAndView(new RedirectView("view_job_entry_outsourcing.htm"), "jobNumber", searchString);
						}
						addJobOrder.setGoFlag("false");
					}/* else {
        errors.reject("search.job.record.error",  new Object[] { "no job records exists for this job number : "+ searchString, }, null);
		addJobOrder.setSearchString("");
		addJobOrder.setGoFlag("false");
        return showForm(request, response, errors);
      }*/
				}catch(ServiceException e)
				{
					e.printStackTrace();
					addJobOrder.setSearchString("");
					addJobOrder.setGoFlag("false");
					errors.reject(e.getMessage(), e.getParams(), null);
					return showForm(request, response, errors);
				}
				catch(Throwable t)
				{
					t.printStackTrace();
					addJobOrder.setSearchString("");
					addJobOrder.setGoFlag("false");
					errors.reject("generic.error", new Object[] {t.getMessage()}, null);
					return showForm(request, response, errors);
				}
			}
		}else
		{addJobOrder.setGoFlag("false");}

		// START : #119240
		String noPrev = request.getParameter("noPrevJob");
		if(noPrev !=null && noPrev.equals("true")){
			request.setAttribute("noJobMessage", "No Previous Job!");
			return showForm(request, response, errors);
		}
		String noNext = request.getParameter("noNextJob");
		if(noNext !=null && noNext.equals("true")){
			request.setAttribute("noJobMessage", "No Next Job!");
			return showForm(request, response, errors);
		}
		 // String nextList=request.getParameter("nextListFlag");
	    String nextList=request.getParameter("showNextListFlag");
	 // END : #119240
		if(nextList != null && nextList.trim().equals("next"))
		{
			jobNumber=jobOrder.getJobNumber();
			try
			{
				if(request.getSession() != null)
				{
					JobSearch jobSearch=(JobSearch)request.getSession().getAttribute("MySearchJobOrder");
					jobOrder=jobService.getNextOrPreviousJobNumber(jobSearch,jobNumber,nextList);
				}
			}/*catch(Exception e){
	          errors.reject("search.job.order.error", new Object[] { e.getMessage() }, null);
	          return showForm(request, response, errors);
	        }*/
			catch(ServiceException e)
			{
				e.printStackTrace();
				addJobOrder.setSearchString("");
				addJobOrder.setGoFlag("false");
				errors.reject(e.getMessage(), e.getParams(), null);
				return showForm(request, response, errors);
			}
			catch(Throwable t)
			{
				t.printStackTrace();
				addJobOrder.setSearchString("");
				addJobOrder.setGoFlag("false");
				errors.reject("generic.error", new Object[] {t.getMessage()}, null);
				return showForm(request, response, errors);
			}
			
			// START : #119240
			//	String url=jobService.getUrlHrefJobTypeByJobOrder(jobOrder)+"?jobNumber=";
			String url=jobService.getUrlByCurrPageIdentifier(jobOrder, addJobOrder.getCurrPageIdentifier())+"?jobNumber=";
			// END : #119240
			return new ModelAndView(new RedirectView(url+jobOrder.getJobNumber()+"&reqFrom="+addJobOrder.getOriginateFromSearchJob()));

		}
		// START : #119240
		//String prevList=request.getParameter("prevListFlag");
		String prevList=request.getParameter("showPrevListFlag");
		// END : #119240
		if(prevList != null && prevList.trim().equals("prev"))
		{
			jobNumber=jobOrder.getJobNumber();
			try
			{
				if(request.getSession() != null)
				{
					JobSearch jobSearch=(JobSearch)request.getSession().getAttribute("MySearchJobOrder");
					jobOrder=jobService.getNextOrPreviousJobNumber(jobSearch,jobNumber,prevList);
				}
			}
			catch(ServiceException e)
			{
				e.printStackTrace();
				addJobOrder.setSearchString("");
				addJobOrder.setGoFlag("false");
				errors.reject(e.getMessage(), e.getParams(), null);
				return showForm(request, response, errors);
			}
			catch(Throwable t)
			{
				t.printStackTrace();
				addJobOrder.setSearchString("");
				addJobOrder.setGoFlag("false");
				errors.reject("generic.error", new Object[] {t.getMessage()}, null);
				return showForm(request, response, errors);
			}
			/*catch(Exception e){
	          errors.reject("search.job.order.error", new Object[] { e.getMessage() }, null);
	          return showForm(request, response, errors);
	        }*/
			
			// START : #119240
			// String url=jobService.getUrlHrefJobTypeByJobOrder(jobOrder)+"?jobNumber=";
			String url=jobService.getUrlByCurrPageIdentifier(jobOrder, addJobOrder.getCurrPageIdentifier())+"?jobNumber=";
			// END : #119240
			return new ModelAndView(new RedirectView(url+jobOrder.getJobNumber()+"&reqFrom="+addJobOrder.getOriginateFromSearchJob()));

		}
		//Code for 'Add Customers' tab

		String indexs = request.getParameter("jobIndex");
		String uniqueFlag=request.getParameter("uniqueFlag");
		if("uniqueFlag".equals(uniqueFlag))
		{
			String pageNumberStr = request.getParameter("pageNumber");
			String inputFieldId = request.getParameter("inputFieldId");
			String concode=request.getParameter("contractCode.value");
			
			String[] code= concode.split("\\;");
			ArrayList contCode=new ArrayList();
			ArrayList rcode=new ArrayList();
			int i=0;
			try {
				for (int j = 0; j<code.length;j++)
				{

					ContractSearch search =new ContractSearch();
					search.setInputFieldId(inputFieldId);
					StringSearchField stringSearchField=new StringSearchField();
					stringSearchField.setValue(code[j].trim());
					search.setContractCode(stringSearchField);


					int pageNumber = 1;
					try
					{  pageNumber = new Integer(pageNumberStr).intValue();   }
					catch(Exception e)
					{    }
					if(pageNumber <= 0) pageNumber = 1;
					Pagination pagination = new Pagination(1, 20, pageNumber, 10);
					search.setPagination(pagination);
					Date asOfDate2=new Date();
					if(jobOrder.getJobFinishDate()!=null ){
						asOfDate2=jobOrder.getJobFinishDate();
					}
					else if(jobOrder.getJobFinishDate()==null && jobOrder.getEtaDate()!=null ){
						asOfDate2=jobOrder.getEtaDate();
					}
					else if(jobOrder.getJobFinishDate()==null &&	jobOrder.getEtaDate()==null ){
						asOfDate2=new Date();
					}
					search.setAsOfDate(asOfDate2);

					jobService.searchContract(search);
					request.setAttribute("command", search);

					if(search.getResults().size()!=0 && search.getResults().size()==1)
					{
						Iterator iter = search.getResults().iterator();
						ContractCustContact contractCustContact=(ContractCustContact) iter.next();

						for(int l=0;l<search.getResults().size();l++)
						{
							String cCode=((ContractCustContact)search.getResults().get(l)).getContractCust().getContract().getContractCode()+","+((ContractCustContact)search.getResults().get(l)).getContractCust().getCustomer().getCustCode()+","+((ContractCustContact)search.getResults().get(l)).getContact().getId();;
							JobContract jobContract=new JobContract();

							String rowNum = request.getParameter("rowNum");
							ContactCust contactCust=null;
							contractCustContact=jobService.getContractDetailsByCode(cCode);
							if(contractCustContact.getContractCust().getCustomer().getInterunitInd()!=null && contractCustContact.getContractCust().getCustomer().getInterunitInd()==true
									&&contractCustContact.getContractCust().getCustomer().getInterunitBusUnitName()!=null && !contractCustContact.getContractCust().getCustomer().getInterunitBusUnitName().trim().equals("")	
									&&jobOrder.getBuName().equals(contractCustContact.getContractCust().getCustomer().getInterunitBusUnitName()))	
								//ContactCust contactCust=new ContactCust();
							{		
							}
							else
							{	
								// ContactCust contactCust=new ContactCust();
								contactCust=jobService.getBillingContactByContactId(contractCustContact.getContact().getId(),contractCustContact.getContractCust().getCustomer().getCustCode());
								jobContract.setJobContractStatus(Constants.JOBCON_OPEN_STATUS);
								//CfgContract cfgContract=new CfgContract();

								Date asOfDate=new Date();
								if(jobOrder.getJobFinishDate()!=null )
								{
									asOfDate=jobOrder.getJobFinishDate();
								}
								else if(jobOrder.getJobFinishDate()==null && jobOrder.getEtaDate()!=null )
								{
									asOfDate=jobOrder.getEtaDate();
								}
								else if(jobOrder.getJobFinishDate()==null &&  jobOrder.getEtaDate()==null )
								{
									asOfDate=new Date();
								}

								String zonedes="";
								String pricebookId="";
								CfgContract cfgContract=null;
								try {
									//CfgContract cfgContract =jobService.getPriceBookByContractCode(contractCustContact.getContractCust().getContract().getContractCode(),asOfDate);
									cfgContract =jobService.getPriceBookByContractCode(contractCustContact.getContractCust().getContract().getContractCode(),asOfDate);
									if(cfgContract!=null)
									{
										if(!cfgContract.getPriceBookId().equalsIgnoreCase("CURRENT"))
										{
											pricebookId=cfgContract.getPriceBookId();
											// zonedes= jobService.getZoneByBranchCodeandContractCode(jobOrder.getBranchName(),contractCustContact.getContractCust().getContract().getContractCode(),cfgContract.getPriceBookId(),asOfDate);
										}
										else
										{
											PriceBook priceBook=jobService.getPriceBookIdByCurrencyandSeries(cfgContract.getCurrencyCD(),cfgContract.getPbSeries(),asOfDate);
											if(priceBook!=null)
											{
												pricebookId=priceBook.getPriceBookId().getPriceBookId();
												// zonedes= jobService.getZoneByBranchCodeandContractCode(jobOrder.getBranchName(),contractCustContact.getContractCust().getContract().getContractCode(),priceBook.getPriceBookId().getPriceBookId(),asOfDate);
											}
										}
									}

									zonedes= jobService.getZoneByBranchCodeandContractCode(jobOrder.getBranchName(),contractCustContact.getContractCust().getContract().getContractCode(),pricebookId,asOfDate);
									if(zonedes!=null&&!zonedes.trim().equals(""))
									{jobContract.setZone(zonedes);}else{jobContract.setZone("NONE");}
									List referenceFields=jobService.getReferenceFieldsByContractCode(contractCustContact.getContractCust().getContract().getContractCode());

									if(referenceFields != null && referenceFields.size()==0)
									{
										if(pricebookId!=null&& !pricebookId.trim().equals(""))
										{
											referenceFields=jobService.getReferenceFieldsByContractCode(pricebookId);
										}
									}
									//cfgContract=jobService.getCfgContractByContractCode(contractCustContact.getContractCust().getContract().getContractCode());

									/*newly added*/
									if(jobOrder!=null && cfgContract!=null)
									{
										List banks=jobService.getPrimBankCodebyBunameandCurrency(jobOrder.getBuName(),cfgContract.getCurrencyCD());
										if(banks!=null && banks.size()==1)
										{
											Bank bank=(Bank)banks.get(0);
											jobContract.setBankCd(bank.getBankCode());
											List bankAccounts=jobService.getPrimBankAccountByBankCodeAndCurrency(jobOrder.getBuName(),cfgContract.getCurrencyCD(),jobContract.getBankCd());
											if(bankAccounts!=null && bankAccounts.size()==1)
											{
												BankAccount bankAccount=(BankAccount)bankAccounts.get(0);
												jobContract.setBankAcctKey(bankAccount.getBankAccountId().getBankAcctCode());
											}
										}
									}//up to here
									if(referenceFields != null)
									{
										for(int k=0;k<referenceFields.size();k++)
										{
											ReferenceField  referenceField=(ReferenceField)referenceFields.get(k);

											if(referenceField.getSortOrderNum()==1&& referenceField.getSortOrderNum()!=null)
											{
												jobContract.setInvoiceLabel1(referenceField.getReferenceFieldId().getReferenceFieldId());
											}

											if(referenceField.getSortOrderNum()==2 && referenceField.getSortOrderNum()!=null)
											{
												jobContract.setInvoiceLabel2(referenceField.getReferenceFieldId().getReferenceFieldId());
											}

											if(referenceField.getSortOrderNum()==3 && referenceField.getSortOrderNum()!=null)
											{
												jobContract.setInvoiceLabel3(referenceField.getReferenceFieldId().getReferenceFieldId());
											}

											if(referenceField.getSortOrderNum()==4 && referenceField.getSortOrderNum()!=null)
											{
												jobContract.setInvoiceLabel4(referenceField.getReferenceFieldId().getReferenceFieldId());
											}

											if(referenceField.getSortOrderNum()==5 && referenceField.getSortOrderNum()!=null)
											{
												jobContract.setInvoiceLabel5(referenceField.getReferenceFieldId().getReferenceFieldId());
											}

										}// end for(k)'
									}
								}//end for try
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
								jobContract.setJobOrder(jobOrder);
								jobContract.setCustCode(contractCustContact.getContractCust().getCustomer().getCustCode());
								jobContract.setContractCode(contractCustContact.getContractCust().getContract().getContractCode());
								jobContract.setContactId(contractCustContact.getContact().getId());
								addJobOrder.setAddJobContracts(addJobContract(addJobOrder.getAddJobContracts(),contractCustContact,jobContract,cfgContract,contactCust,pricebookId));
								addJobOrder.setCustCount(addJobOrder.getAddJobContracts().length);
							}   
						}//for(l)
						addJobOrder.setUniqueFlag("none");
						addJobOrder.setPopFlag("none");
						addJobOrder.setJobFlag("none");
						addJobOrder.setTabName("1");
						addJobOrder.setPageNumber("1");

					}//if
					else if(search.getResults().size()>1)
					{
						contCode.add(code[j].trim());
					}
					else if(search.getResults().size()==0)
					{
						rcode.add(code[j].trim());
					}

				}//for j

				if(rcode.size()>=1)
				{
					String c="";
					for(int n=0;n<rcode.size();n++)
					{
						if(c!=null && !c.trim().equals(""))
						{
							c=c+","+rcode.get(n).toString().trim();
						}
						else
						{c=c+rcode.get(n).toString().trim();
						}
					}
					String s="";
					if(contCode.size()>=1)
					{
						for(int m=0;m<contCode.size();m++)
						{
							s=s+contCode.get(m).toString().trim()+";";
						}
					}
					addJobOrder.setSearchCode(s);
					addJobOrder.setUniqueFlag("none");
					addJobOrder.setPopFlag("none");
					addJobOrder.setJobFlag("none");
					addJobOrder.setContrFlag("none");
					addJobOrder.setErrorFlag("true");
					addJobOrder.setTabName("1");
					addJobOrder.setPageNumber("1");
					addJobOrder.setErrorCode(c);
					return showForm(request, response, errors);
				}


				if(contCode.size()>=1)
				{
					String s="";
					for(int m=0;m<contCode.size();m++)
					{
						s=s+contCode.get(m).toString().trim()+";";
					}
					addJobOrder.setSearchCode(s.substring(0,s.indexOf(";")-0));
					addJobOrder.setContrFlag("none");
					addJobOrder.setUniqueFlag("none");
					if(contCode.size()!=1)
					{
						addJobOrder.setContrCode(s.substring(s.indexOf(";")+1));
						addJobOrder.setContrFlag("none");
					}
					if(addJobOrder.getContrCode().contains("'"))
					{
						String s1=addJobOrder.getContrCode().substring(0,addJobOrder.getContrCode().indexOf("'")-0);
						String s2=addJobOrder.getContrCode().substring(addJobOrder.getContrCode().indexOf("'")+1);
						String codec=s1+"/"+s2;
						addJobOrder.setContrCode(codec);
					}
					addJobOrder.setUniqueCount(contCode.size());
					addJobOrder.setPopFlag("true");
					addJobOrder.setJobFlag("none");
					addJobOrder.setTabName("1");
					addJobOrder.setPageNumber("1");
					return showForm(request, response, errors);
				}
				else{
					addJobOrder.setUniqueFlag("none");
					addJobOrder.setPopFlag("none");
					addJobOrder.setJobFlag("none");
					addJobOrder.setContrFlag("none");
					addJobOrder.setTabName("1");
					addJobOrder.setPageNumber("1");
					return showForm(request, response, errors);
				}
			} catch(ServiceException e)
			{
				addJobOrder.setUniqueFlag("none");
				addJobOrder.setPopFlag("none");
				addJobOrder.setJobFlag("none");
				addJobOrder.setContrFlag("none");
				addJobOrder.setTabName("1");
				e.printStackTrace();
				errors.reject(e.getMessage(), e.getParams(), null);
				return showForm(request, response, errors);
			} catch(Throwable t)
			{
				addJobOrder.setUniqueFlag("none");
				addJobOrder.setPopFlag("none");
				addJobOrder.setJobFlag("none");
				addJobOrder.setContrFlag("none");
				addJobOrder.setTabName("1");
				t.printStackTrace();
				errors.reject("generic.error", new Object[] {t.getMessage()}, null);
				return showForm(request, response, errors);
			}
		}//if

		String jobFlag=request.getParameter("jobFlag");
		if("jobval".equals(jobFlag))
		{
			String cCode=request.getParameter("inputFieldIdValue");
			JobContract jobContract=new JobContract();
			String rowNum = request.getParameter("rowNum");
			// ContractCustContact contractCustContact=new ContractCustContact();
			//ContractCustContact contractCustContact=jobService.getContractDetailsByCode(cCode);
			ContractCustContact contractCustContact=null;
			ContactCust contactCust=null;
			try{
				contractCustContact=jobService.getContractDetailsByCode(cCode);
				contactCust=jobService.getBillingContactByContactId(contractCustContact.getContact().getId(),contractCustContact.getContractCust().getCustomer().getCustCode());
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
			jobContract.setJobContractStatus(Constants.JOBCON_OPEN_STATUS);
			CfgContract cfgContract=new CfgContract();

			Date asOfDate=new Date();
			if(jobOrder.getJobFinishDate()!=null )
			{
				asOfDate=jobOrder.getJobFinishDate();
			}
			else if(jobOrder.getJobFinishDate()==null && jobOrder.getEtaDate()!=null )
			{
				asOfDate=jobOrder.getEtaDate();
			}
			else if(jobOrder.getJobFinishDate()==null &&  jobOrder.getEtaDate()==null )
			{
				asOfDate=new Date();
			}

			String zonedes="";
			String pricebookId="";
			try{
				cfgContract =jobService.getPriceBookByContractCode(contractCustContact.getContractCust().getContract().getContractCode(),asOfDate);
				PriceBook priceBook=new PriceBook();
				if(cfgContract!=null)
				{
					if(!cfgContract.getPriceBookId().equalsIgnoreCase("CURRENT"))
					{
						pricebookId=cfgContract.getPriceBookId();
					}
					else
					{
						priceBook=jobService.getPriceBookIdByCurrencyandSeries(cfgContract.getCurrencyCD(),cfgContract.getPbSeries(),asOfDate);
						if(priceBook!=null)
						{
							pricebookId=priceBook.getPriceBookId().getPriceBookId();
						}
					}
				}

				zonedes= jobService.getZoneByBranchCodeandContractCode(jobOrder.getBranchName(),contractCustContact.getContractCust().getContract().getContractCode(),pricebookId,asOfDate);
				if(zonedes!=null&&!zonedes.trim().equals(""))
				{jobContract.setZone(zonedes);}else{jobContract.setZone("NONE");}
				List referenceFields=jobService.getReferenceFieldsByContractCode(contractCustContact.getContractCust().getContract().getContractCode());

				if(referenceFields.size()==0)
				{
					if(pricebookId!=null&& !pricebookId.trim().equals(""))
					{
						referenceFields=jobService.getReferenceFieldsByContractCode(pricebookId);
					}
				}
				//cfgContract=jobService.getCfgContractByContractCode(contractCustContact.getContractCust().getContract().getContractCode());
				cfgContract =jobService.getPriceBookByContractCode(contractCustContact.getContractCust().getContract().getContractCode(),asOfDate);
				if(jobOrder!=null && cfgContract!=null)
				{
					List banks=jobService.getPrimBankCodebyBunameandCurrency(jobOrder.getBuName(),cfgContract.getCurrencyCD());
					if(banks!=null && banks.size()==1)
					{
						Bank bank=(Bank)banks.get(0);
						jobContract.setBankCd(bank.getBankCode());
						List bankAccounts=jobService.getPrimBankAccountByBankCodeAndCurrency(jobOrder.getBuName(),cfgContract.getCurrencyCD(),jobContract.getBankCd());
						if(bankAccounts!=null && bankAccounts.size()==1)
						{
							BankAccount bankAccount=(BankAccount)bankAccounts.get(0);
							jobContract.setBankAcctKey(bankAccount.getBankAccountId().getBankAcctCode());
						}
					}
				}
				if(referenceFields != null)
				{
					for(int i=0;i<referenceFields.size();i++)
					{
						ReferenceField  referenceField=(ReferenceField)referenceFields.get(i);

						if(referenceField.getSortOrderNum()==1&& referenceField.getSortOrderNum()!=null)
						{
							jobContract.setInvoiceLabel1(referenceField.getReferenceFieldId().getReferenceFieldId());
						}

						if(referenceField.getSortOrderNum()==2 && referenceField.getSortOrderNum()!=null)
						{
							jobContract.setInvoiceLabel2(referenceField.getReferenceFieldId().getReferenceFieldId());
						}

						if(referenceField.getSortOrderNum()==3 && referenceField.getSortOrderNum()!=null)
						{
							jobContract.setInvoiceLabel3(referenceField.getReferenceFieldId().getReferenceFieldId());
						}

						if(referenceField.getSortOrderNum()==4 && referenceField.getSortOrderNum()!=null)
						{
							jobContract.setInvoiceLabel4(referenceField.getReferenceFieldId().getReferenceFieldId());
						}

						if(referenceField.getSortOrderNum()==5 && referenceField.getSortOrderNum()!=null)
						{
							jobContract.setInvoiceLabel5(referenceField.getReferenceFieldId().getReferenceFieldId());
						}

					}
				}
			}//end for try
			catch(ServiceException e)
			{
				addJobOrder.setUniqueFlag("none");
				addJobOrder.setPopFlag("none");
				addJobOrder.setJobFlag("none");
				addJobOrder.setContrFlag("true");
				addJobOrder.setPopFlag("none");
				addJobOrder.setTabName("1");
				e.printStackTrace();
				errors.reject(e.getMessage(), e.getParams(), null);
				return showForm(request, response, errors);
			}
			catch(Throwable t)
			{
				addJobOrder.setUniqueFlag("none");
				addJobOrder.setPopFlag("none");
				addJobOrder.setJobFlag("none");
				addJobOrder.setContrFlag("true");
				addJobOrder.setPopFlag("none");
				addJobOrder.setTabName("1");
				t.printStackTrace();
				errors.reject("generic.error", new Object[] {t.getMessage()}, null);
				return showForm(request, response, errors);
			}
			jobContract.setJobOrder(jobOrder);
			jobContract.setCustCode(contractCustContact.getContractCust().getCustomer().getCustCode());
			jobContract.setContractCode(contractCustContact.getContractCust().getContract().getContractCode());
			jobContract.setContactId(contractCustContact.getContact().getId());
			addJobOrder.setAddJobContracts(addJobContract(addJobOrder.getAddJobContracts(),contractCustContact,jobContract,cfgContract,contactCust,pricebookId));
			addJobOrder.setCustCount(addJobOrder.getAddJobContracts().length);
			addJobOrder.setUniqueFlag("none");
			addJobOrder.setPopFlag("none");
			addJobOrder.setJobFlag("none");
			addJobOrder.setContrFlag("true");
			addJobOrder.setPopFlag("none");
			addJobOrder.setTabName("1");

			return showForm(request, response, errors);
		}

		String deleteFlag=request.getParameter("deleteFlag");
		if("delval".equals(deleteFlag))
		{
			try {
				String rowNo=request.getParameter("jobIndex");
				int num=Integer.parseInt(rowNo);
				AddJobContract[] jobContractItems = addJobOrder.getAddJobContracts();
				AddJobContract addJobContract=jobContractItems[num];
				JobContract jobContract=addJobContract.getJobContract();
				String jobNum=jobOrder.getJobNumber();
				long contactId=jobContractItems[num].getJobContract().getContactId();
				String custCode=jobContractItems[num].getJobContract().getCustCode();
				String contractCode=jobContractItems[num].getJobContract().getContractCode();
				boolean existingRecord=jobService.checkJobContract(jobNum,custCode,contractCode,contactId);
				if(existingRecord==true)
				{
					if(jobContract.getBillingContact().getId()<=0)
					{
						jobContract.setBillingContact(null);
					}
					jobService.deleteJobContractNotesByJobContract(jobContract.getId());
					List jobContractAttaches=jobService.getJobContractAttachByJobContractId(jobContract.getId());
					if(jobContractAttaches!=null && jobContractAttaches.size()>0){
						PropertyResourceBundle pRB = (PropertyResourceBundle) ResourceBundle.getBundle("config");
						String path ="";
						if(pRB != null)
							path = pRB.getString(com.intertek.util.Constants.jobcontractfilepath);	
						for(Iterator itr=jobContractAttaches.iterator();itr.hasNext();)
						{
							long id =((JobContractAttach)itr.next()).getId();
							jobService.deleteJobContractAttach(id, path);
						}
					}
					if(jobContract.getJobLog()!=null && jobContract.getJobLog().getId()!=0)
					{
						long logID=jobContract.getJobLog().getId();
						jobContract.setJobLog(null);
						jobService.updateJobContract(jobContract);
						jobService.deleteJobLogId(logID);
					}
					//jobService.deleteJobNoteInsp(jobContractNote);

					//remove jobProdContract from JobProd if it exists
					if(addJobOrder.getJobOrder().getJobVessels() != null && addJobOrder.getJobOrder().getJobVessels().size() > 0)
					{
						Set jobVessels = addJobOrder.getJobOrder().getJobVessels();
						Iterator iter = jobVessels.iterator();
						while(iter.hasNext())
						{
							JobVessel jobVessel = (JobVessel) iter.next();
							if(jobVessel.getJobProds() != null && jobVessel.getJobProds().size() > 0)
							{
								Set jobProds = jobVessel.getJobProds();
								Iterator prodIter = jobProds.iterator();
								while(prodIter.hasNext())
								{
									JobProd jobProd = (JobProd) prodIter.next();
									if(jobProd.getJobProdContracts() != null && jobProd.getJobProdContracts().size() > 0)
										jobProd.getJobProdContracts().remove(jobContract);
								}
							}
						}
					}

					jobService.deleteJobInsp(jobContract);
					addJobOrder.setAddJobContracts(deleteJobContract(addJobOrder.getAddJobContracts(),addJobOrder.getJobIndex()));
				}
				else
				{
					addJobOrder.setAddJobContracts(deleteJobContract(addJobOrder.getAddJobContracts(),addJobOrder.getJobIndex()));
				}

				AddJobContract[] jobContractItems1 = addJobOrder.getAddJobContracts();   
				for(int i=0;i<jobContractItems1.length;i++)
				{  
					AddJobContract addJobContract1=jobContractItems1[i];
					JobContract jobContract1=addJobContract1.getJobContract();

					if(jobContract1.getJobNumber()!=null && !jobContract1.getJobNumber().trim().equals("")){    	
						jobContract1.setJobNumber(jobOrder.getJobNumber());
						//jobContract1.setUid20(jobOrder.getJobNumber()+i);
						//String s=jobOrder.getJobNumber();
						//int index_val = s.lastIndexOf("-");
						//String bname= s.substring(0, index_val);
						//String jcode=s.substring(index_val+1);      
						jobContract1.setUid20(DateUtil.getUniquetimeString()+i);
						jobContract = jobService.saveJobContractInsp(jobContract1);
					}
				}
				addJobOrder.setCustCount(addJobOrder.getAddJobContracts().length);
				addJobOrder.setDeleteFlag("none");
				addJobOrder.setPopFlag("none");
				addJobOrder.setTabName("1");
				return showForm(request, response, errors);
			}catch(ServiceException e)
			{
				addJobOrder.setDeleteFlag("none");
				addJobOrder.setPopFlag("none");
				addJobOrder.setTabName("1");
				e.printStackTrace();
				errors.reject(e.getMessage(), e.getParams(), null);
				return showForm(request, response, errors);
			}
			catch(Throwable t)
			{
				addJobOrder.setDeleteFlag("none");
				addJobOrder.setPopFlag("none");
				addJobOrder.setTabName("1");
				t.printStackTrace();
				errors.reject("generic.error", new Object[] {t.getMessage()}, null);
				return showForm(request, response, errors);
			}
		}

		String jobDateFlag=request.getParameter("jobDateFlag");
		if("jobDate".equals(jobDateFlag))
		{      
			try{
				Date asOfDate=new Date();

				String branchName = jobOrder.getBranchName();
				Branch existedBranch = userService.getBranchByName(branchName);
				if (existedBranch == null) {
					errors.reject("create.job.order.error",new Object[] {"Branch can not be found:"+branchName},null );
					return showForm(request, response, errors);	
				}
				if(jobOrder.getJobFinishDate()!=null )
				{
					asOfDate=jobOrder.getJobFinishDate();
				}
				else if(jobOrder.getJobFinishDate()==null && jobOrder.getEtaDate()!=null )
				{
					asOfDate=jobOrder.getEtaDate();
				}
				else if(jobOrder.getJobFinishDate()==null &&  jobOrder.getEtaDate()==null )
				{
					asOfDate=new Date();
				}
				AddJobContract[] jobContractItem = addJobOrder.getAddJobContracts();
				if(jobContractItem!=null)
				{
					for(int i=0; i< jobContractItem.length; i++)
					{
						AddJobContract addJobContract=jobContractItem[i];
						JobContract jobContract=addJobContract.getJobContract();
						// CfgContract cfgContract=new CfgContract();
						String zonedes="";
						String pricebookId="";
						CfgContract cfgContract =jobService.getPriceBookByContractCode(jobContract.getContractCode(),asOfDate);
						// PriceBook priceBook=new PriceBook();
						if(cfgContract!=null)
						{
							if(!cfgContract.getPriceBookId().equalsIgnoreCase("CURRENT"))
							{
								pricebookId=cfgContract.getPriceBookId();
							}
							else
							{
								PriceBook priceBook=jobService.getPriceBookIdByCurrencyandSeries(cfgContract.getCurrencyCD(),cfgContract.getPbSeries(),asOfDate);
								if(priceBook!=null)
								{
									pricebookId=priceBook.getPriceBookId().getPriceBookId();
								}
							}
						}
						zonedes= jobService.getZoneByBranchCodeandContractCode(jobOrder.getBranchName(),jobContract.getContractCode(),pricebookId,asOfDate);    if(zonedes!=null&&!zonedes.trim().equals(""))
						{jobContract.setZone(zonedes);}else{jobContract.setZone("NONE");}
						String dateFlag=request.getParameter("dateFlag");
						if(dateFlag!=null && dateFlag.trim().equals("true"))
						{
							List referenceFields=jobService.getReferenceFieldsByContractCode(jobContract.getContractCode());

							if(referenceFields.size()==0)
							{
								if(pricebookId!=null&& !pricebookId.trim().equals(""))
								{
									referenceFields=jobService.getReferenceFieldsByContractCode(pricebookId);
								}
							}
							if(jobContract.getId()==0)
							{
								if(referenceFields.size()!=0)
								{
									for(int k=0;k<referenceFields.size();k++)
									{
										ReferenceField  referenceField=(ReferenceField)referenceFields.get(k);

										if(referenceField.getSortOrderNum()==1&& referenceField.getSortOrderNum()!=null)
										{
											jobContract.setInvoiceLabel1(referenceField.getReferenceFieldId().getReferenceFieldId());
										}

										if(referenceField.getSortOrderNum()==2 && referenceField.getSortOrderNum()!=null)
										{				
											jobContract.setInvoiceLabel2(referenceField.getReferenceFieldId().getReferenceFieldId());
										}

										if(referenceField.getSortOrderNum()==3 && referenceField.getSortOrderNum()!=null)
										{
											jobContract.setInvoiceLabel3(referenceField.getReferenceFieldId().getReferenceFieldId());
										}

										if(referenceField.getSortOrderNum()==4 && referenceField.getSortOrderNum()!=null)
										{
											jobContract.setInvoiceLabel4(referenceField.getReferenceFieldId().getReferenceFieldId());
										}
										if(referenceField.getSortOrderNum()==5 && referenceField.getSortOrderNum()!=null)
										{
											jobContract.setInvoiceLabel5(referenceField.getReferenceFieldId().getReferenceFieldId());
										}	

									}
								}
								else
								{
									jobContract.setInvoiceLabel1(null);
									jobContract.setInvoiceLabel2(null);
									jobContract.setInvoiceLabel3(null);
									jobContract.setInvoiceLabel4(null);
									jobContract.setInvoiceLabel5(null);
								}
							}
							else if(jobContract.getId()>0 && jobContract.getInvoiceLabel1()==null && jobContract.getInvoiceLabel2()==null && jobContract.getInvoiceLabel3()==null && jobContract.getInvoiceLabel4()==null && jobContract.getInvoiceLabel5()==null)
							{
								if(referenceFields.size()!=0)
								{
									for(int k=0;k<referenceFields.size();k++)
									{
										ReferenceField  referenceField=(ReferenceField)referenceFields.get(k);

										if(referenceField.getSortOrderNum()==1&& referenceField.getSortOrderNum()!=null)
										{
											jobContract.setInvoiceLabel1(referenceField.getReferenceFieldId().getReferenceFieldId());
										}

										if(referenceField.getSortOrderNum()==2 && referenceField.getSortOrderNum()!=null)
										{				
											jobContract.setInvoiceLabel2(referenceField.getReferenceFieldId().getReferenceFieldId());
										}

										if(referenceField.getSortOrderNum()==3 && referenceField.getSortOrderNum()!=null)
										{
											jobContract.setInvoiceLabel3(referenceField.getReferenceFieldId().getReferenceFieldId());
										}

										if(referenceField.getSortOrderNum()==4 && referenceField.getSortOrderNum()!=null)
										{
											jobContract.setInvoiceLabel4(referenceField.getReferenceFieldId().getReferenceFieldId());
										}
										if(referenceField.getSortOrderNum()==5 && referenceField.getSortOrderNum()!=null)
										{
											jobContract.setInvoiceLabel5(referenceField.getReferenceFieldId().getReferenceFieldId());
										}	

									}
								}
							}
						}
					}
				}	
				addJobOrder.setDateFlag("false");
				addJobOrder.setJobDateFlag("none");
				//setting warn message
				String openPeriodsFlag = request.getParameter("openPeriodsFlag");
				if(openPeriodsFlag != null && openPeriodsFlag.trim().equals("true"))
				{
					String buName = jobOrder.getBuName();
					Date jobFinishDate = jobOrder.getJobFinishDate();
					int openPeriods = 0;
					if(buName != null && !buName.trim().equals("") && jobFinishDate != null)
						openPeriods = jobService.getPeriods(buName,jobFinishDate);
					if(openPeriods == 0)
						addJobOrder.setShowWarn("true");
					addJobOrder.setOpenPeriodsFlag("false");
				}
				//end 
				return showForm(request,response,errors);	
			}
			catch(ServiceException e)
			{
				addJobOrder.setDateFlag("false");
				addJobOrder.setJobDateFlag("none");
				addJobOrder.setOpenPeriodsFlag("false");
				e.printStackTrace();
				errors.reject(e.getMessage(), e.getParams(), null);
				return showForm(request, response, errors);
			}
			catch(Throwable t)
			{
				addJobOrder.setDateFlag("false");
				addJobOrder.setJobDateFlag("none");
				addJobOrder.setOpenPeriodsFlag("false");
				t.printStackTrace();
				errors.reject("generic.error", new Object[] {t.getMessage()}, null);
				return showForm(request, response, errors);
			}

		}
		String monthlyJobFlag=request.getParameter("monthlyJobFlag");
		if("monthlyid".equals(monthlyJobFlag))
		{
			try {
				String rowNo=request.getParameter("rowNum");
				int num=Integer.parseInt(rowNo);
				AddJobContract[] jobContractItems = addJobOrder.getAddJobContracts();
				AddJobContract addJobContract=jobContractItems[num];
				JobContract jobContract=addJobContract.getJobContract();
				List jobs=jobService.checkMonthlyJob(jobOrder.getJobNumber());
				if(jobs!=null && jobs.size()!=0)
				{
					addJobOrder.setMonthlyCheck("true");
				}else
				{addJobOrder.setMonthlyCheck("false");}
				addJobOrder.setRowNum(rowNo);
				addJobOrder.setMonthlyJobFlag("none");
				addJobOrder.setTabName("1");
				return showForm(request,response,errors);
			}
			catch(ServiceException e)
			{
				addJobOrder.setMonthlyJobFlag("none");
				e.printStackTrace();
				errors.reject(e.getMessage(), e.getParams(), null);
				return showForm(request, response, errors);
			} catch(Throwable t)
			{
				addJobOrder.setMonthlyJobFlag("none");
				t.printStackTrace();
				errors.reject("generic.error", new Object[] {t.getMessage()}, null);
				return showForm(request, response, errors);
			}
		}


		String contactFlag=request.getParameter("contactFlag");
		if("contFlag".equals(contactFlag))
		{
			try{
				String rowNo=request.getParameter("contactIndex");
				populateBillingContact(addJobOrder, jobService, countryService,
						addJobContracts, rowNo);
				addJobOrder.setContactFlag("none");
				addJobOrder.setPopFlag("none");
				addJobOrder.setTabName("1");
				return showForm(request, response, errors);
			}//end for try
			catch(ServiceException e)
			{
				addJobOrder.setContactFlag("none");
				addJobOrder.setPopFlag("none");
				addJobOrder.setTabName("1");
				e.printStackTrace();
				errors.reject(e.getMessage(), e.getParams(), null);
				return showForm(request, response, errors);
			}
			catch(Throwable t)
			{
				addJobOrder.setContactFlag("none");
				addJobOrder.setPopFlag("none");
				addJobOrder.setTabName("1");
				t.printStackTrace();
				errors.reject("generic.error", new Object[] {t.getMessage()}, null);
				return showForm(request, response, errors);
			}

		}
		String bankFlag=request.getParameter("bankFlag");
		if("bankFlag".equals(bankFlag))
		{
			try {
				String rowNo=request.getParameter("bankIndex");
				int num=Integer.parseInt(rowNo);
				AddJobContract[] jobContractItems = addJobOrder.getAddJobContracts();
				AddJobContract addJobContract=jobContractItems[num];
				JobContract jobContract=addJobContract.getJobContract();
				String currency=jobContract.getTransactionCurrencyCd();
				if(jobOrder.getBuName()!=null && currency!=null)
				{
					List banks=jobService.getPrimBankCodebyBunameandCurrency(jobOrder.getBuName(),currency);
					if(banks!=null && banks.size()==1)
					{
						Bank bank=(Bank)banks.get(0);
						jobContract.setBankCd(bank.getBankCode());
						List bankAccounts=jobService.getPrimBankAccountByBankCodeAndCurrency(jobOrder.getBuName(),currency,jobContract.getBankCd());
						if(bankAccounts!=null && bankAccounts.size()==1)
						{
							BankAccount bankAccount=(BankAccount)bankAccounts.get(0);
							jobContract.setBankAcctKey(bankAccount.getBankAccountId().getBankAcctCode());
						}
					}
					else
					{
						jobContract.setBankCd(null);
						jobContract.setBankAcctKey(null);
					}
					addJobContracts[num].setJobContract(jobContract);
				}
				addJobOrder.setBankFlag("none");
				addJobOrder.setContactFlag("none");
				addJobOrder.setJobFlag("none");
				addJobOrder.setUniqueFlag("none");
				addJobOrder.setPopFlag("none");
				addJobOrder.setTabName("1");
				return showForm(request,response,errors);
			}//end for try
			catch(ServiceException e)
			{
				addJobOrder.setBankFlag("none");
				addJobOrder.setContactFlag("none");
				addJobOrder.setJobFlag("none");
				addJobOrder.setUniqueFlag("none");
				addJobOrder.setPopFlag("none");
				addJobOrder.setTabName("1");
				e.printStackTrace();
				errors.reject(e.getMessage(), e.getParams(), null);
				return showForm(request, response, errors);
			} catch(Throwable t)
			{
				addJobOrder.setBankFlag("none");
				addJobOrder.setContactFlag("none");
				addJobOrder.setJobFlag("none");
				addJobOrder.setUniqueFlag("none");
				addJobOrder.setPopFlag("none");
				addJobOrder.setTabName("1");
				t.printStackTrace();
				errors.reject("generic.error", new Object[] {t.getMessage()}, null);
				return showForm(request, response, errors);
			}
		}

		//START: 118717 Fix for new requirement - 01 June 2009
		boolean blnJobFinishDtExts = false;		
		if(null != jobOrder.getJobFinishDate()){
			blnJobFinishDtExts = true;		
		}
		//END: 118717 Fix for new requirement - 01 June 2009			
		
		//Code for 'Add Customers' tab ends
		if(addJobOrder.getUiEtaTime()!= null && !addJobOrder.getUiEtaTime().equals(""))
		{
			//START: 118717 Fix for new requirement - 01 June 2009
			if(!blnJobFinishDtExts && (jobOrder.getEtaDate()== null || jobOrder.getEtaDate().equals("")))
			//END: 118717 Fix for new requirement - 01 June 2009				
			{
				errors.reject("select.eta.date",new Object[] {},null);
				return showForm(request, response, errors);
			}
		}
		if(addJobOrder.getUiNominationTime()!= null && !addJobOrder.getUiNominationTime().equals(""))
		{
			if(jobOrder.getNominationDate() == null || jobOrder.getNominationDate().equals(""))
			{
				// errors.reject("create.job.order.error",new Object[] {"Please Select Nomination Date : "},null);
				errors.reject("select.nomination.date.error",new Object[] {},null);
				return showForm(request, response, errors);
			}
		}

		AddJobContract[] jobContractItem = addJobOrder.getAddJobContracts();
		if(jobContractItem!=null)
		{
			for(int i=0; i< jobContractItem.length; i++)
			{
				AddJobContract addJobContract=jobContractItem[i];
				JobContract jobContract=addJobContract.getJobContract();
				//new code added for the issue 81805
				if((jobContract.getInvoice()==null||jobContract.getInvoice().trim().equals(""))&&jobContract.getId()!=0){ 
					System.out.println("jobcontract id in looop is"+jobContract.getId());
					JobContract jobContract2=jobService.getJobContractById(jobContract.getId());
					System.out.println(" invoice before saving in the edit jobcontract insp"+jobContract2.getInvoice()); 
					if(jobContract2.getInvoice()!=null && !jobContract2.getInvoice().trim().equals("")
							&&jobContract2.getJobContractStatus()!=null && !jobContract2.getJobContractStatus().trim().equals("")
							&&jobContract2.getJobContractStatus().equals(Constants.JOBCON_INVOICED_STATUS)
							&&jobContract2.getCreditInd()!=null && !jobContract2.getCreditInd().trim().equals("")
							&& jobContract2.getCreditInd().equals(Constants.CREDIT_INDICATOR_I))
					{
						errors.reject("invoice.has.been.genererated.for.this.contract", null, null);
						return showForm(request, response, errors);
					}
				}
				//up to here
			}

			//Bind billingContact values
			for(int i=0; i< jobContractItem.length; i++)
			{
				AddJobContract addJobContract=jobContractItem[i];
				bindBillingContactInfo(addJobContract);
			}

			//Check for customer page errors and return to browser
			validateCustomerInfo(errors, addJobOrder, jobContractItem);
			if (errors.getErrorCount() > 0)
				return showForm(request, response, errors);
		}
		try {

			//Do time conversions
			String uiNominationTime = addJobOrder.getUiNominationTime();
			if(uiNominationTime != null && (!uiNominationTime.trim().equals("")))
			{
				if(jobOrder.getNominationTimeTz() == null || jobOrder.getNominationTimeTz().trim().equals(""))
				{
					// errors.reject("create.job.order.error",new Object[] {"Please enter a valid nomination time zone"}, null);
					errors.reject("invalid.timezone.error",new Object[] {}, null);
					return showForm(request, response, errors);
				}
				// user format date and time
				Date nominationTime;
				if(addJobOrder.getNomTimeFormat()!= null && !addJobOrder.getNomTimeFormat().equals(""))
				{
					if(addJobOrder.getNomTimeFormat().trim().equalsIgnoreCase(Constants.POSTMERIDIAN))
					{
						String nomTime[]=uiNominationTime.split(":");
						int uiHourTime = 0;
						int uiMinuteTime = 00;
						if(nomTime.length >= 2)
						{
							uiHourTime = Integer.valueOf(nomTime[0].trim()).intValue();
							uiMinuteTime = Integer.valueOf(nomTime[1].trim()).intValue();
						}else
							uiHourTime = Integer.valueOf(nomTime[0].trim()).intValue();
						uiHourTime = uiHourTime + Constants.TWELVE_HOUR;
						uiNominationTime = String.valueOf(uiHourTime)+":"+String.valueOf(uiMinuteTime);

						nominationTime = DateUtil.getConvertedDateTime(jobOrder.getNominationDate(),uiNominationTime,jobOrder.getNominationTimeTz(),Constants.TIME_ZONE);
					}else
					{
						nominationTime = DateUtil.getConvertedDateTime(jobOrder.getNominationDate(),uiNominationTime,jobOrder.getNominationTimeTz(),Constants.TIME_ZONE);
					}

				}else
				{
					nominationTime = DateUtil.getConvertedDateTime(jobOrder.getNominationDate(),uiNominationTime,jobOrder.getNominationTimeTz(),Constants.TIME_ZONE);
				}
				//end
				jobOrder.setNominationTime(nominationTime);

			}
			
			
//			for the issue 106438 ---- for validating, wheather the ETA Date is greater than or equals to todays date
			/*Date etaDate = jobOrder.getEtaDate();
			if(null != etaDate){
				DateFormat dateFormat1 = new SimpleDateFormat(Constants.YYYY_MM_DD_DATE_FORMAT);
		        java.util.Date date = new java.util.Date();
		        Date todayDate = dateFormat1.parse(dateFormat1.format(date));
				if(etaDate.before(todayDate)){
		        	errors.reject("ETADate.error", new Object[] {}, null);
		        	addJobOrder.setTabName("0");
		        	return showForm(request, response, errors);
		        }
			}*/
			//END 106438

			if(addJobOrder.getJobOrder().getOperation() == null || addJobOrder.getJobOrder().getOperation().trim().equals(""))
			{		

				errors.reject("select.operation.error",new Object[] {},null);
				addJobOrder.setTabName("0");
				return showForm(request, response, errors);	

			}

			String uiEtaTime = addJobOrder.getUiEtaTime();
			if(uiEtaTime != null && (!uiEtaTime.trim().equals("")))
			{
				if(jobOrder.getEtaTimeTz() == null || jobOrder.getEtaTimeTz().trim().equals(""))
				{
					//errors.reject("create.job.order.error",new Object[] {"Please enter a valid eta time zone"}, null);
					errors.reject("invalid.eta.timezone.error",new Object[] {}, null);
					return showForm(request, response, errors);
				}
				// user format date and time
				Date etaTime;

				//START: 118717 Fix for new requirement - 04 June 2009
				Date dateForETATime;
				if(null != jobOrder.getEtaDate()){
					dateForETATime = new Date(jobOrder.getEtaDate().getTime());
				}
				else{
					dateForETATime = new Date(jobOrder.getJobFinishDate().getTime());
				}				
				//END: 118717,119283 Fix for new requirement - 04 June 2009

				
				if(addJobOrder.getEtaTimeFormat()!= null && !addJobOrder.getEtaTimeFormat().equals(""))
				{
					if(addJobOrder.getEtaTimeFormat().trim().equalsIgnoreCase(Constants.POSTMERIDIAN))
					{
						String etaTimes[]=uiEtaTime.split(":");
						int uiHourTime = 0;
						int uiMinuteTime = 00;
						if(etaTimes.length >= 2)
						{
							uiHourTime = Integer.valueOf(etaTimes[0].trim()).intValue();
							uiMinuteTime = Integer.valueOf(etaTimes[1].trim()).intValue();
						}else
							uiHourTime = Integer.valueOf(etaTimes[0].trim()).intValue();
						uiHourTime = uiHourTime + Constants.TWELVE_HOUR;
						uiEtaTime = String.valueOf(uiHourTime)+":"+String.valueOf(uiMinuteTime);
						//START: 118717 Fix for new requirement - 01 June 2009
						etaTime = DateUtil.getConvertedDateTime(dateForETATime,uiEtaTime,jobOrder.getEtaTimeTz(),Constants.TIME_ZONE);
					}else
						etaTime = DateUtil.getConvertedDateTime(dateForETATime,uiEtaTime,jobOrder.getEtaTimeTz(),Constants.TIME_ZONE);
				}else
				{
					etaTime = DateUtil.getConvertedDateTime(dateForETATime,uiEtaTime,jobOrder.getEtaTimeTz(),Constants.TIME_ZONE);
					//END: 118717 Fix for new requirement - 01 June 2009
				}
				//end
				jobOrder.setEtaTime(etaTime);
			}
			//START: For Issue 106438 20 May 2009
			else if(uiEtaTime == null || uiEtaTime.trim().equals("")){
				jobOrder.setEtaTime(null);
			}
			
			if(null != addJobOrder.getJobOrder().getEtaDate() 
					&& JobUtil.restrictETA(addJobOrder)){
				DateFormat dateFormat1 = new SimpleDateFormat(Constants.YYYY_MM_DD_DATE_FORMAT);
		        java.util.Date date = new java.util.Date();
		        Date todayDate = dateFormat1.parse(dateFormat1.format(date));
				if(addJobOrder.getJobOrder().getEtaDate().before(todayDate)){
					//START: For Issue 106438 21 May 2009
		        	/*errors.reject("ETADate.error", new Object[] {}, null);
		        	addJobOrder.setTabName("0");
		        	return showForm(request, response, errors);*/
					jobOrder.setEtaDate(todayDate);
					//END: For Issue 106438 21 May 2009					
		        }
			}
			//END: For Issue 106438 20 May 2009
			
			
			AddJobContract[] jobContractItems = addJobOrder.getAddJobContracts();
			if(jobContractItems!=null)
			{
				Set jobContractsSet = new HashSet();
				if(jobOrder.getJobContracts() != null)
					jobOrder.getJobContracts().clear();

				for(int i=0; i< jobContractItems.length; i++)
				{
					AddJobContract addJobContract=jobContractItems[i];
					JobContract jobContract=addJobContract.getJobContract();

					//        Validate bank details
					boolean bankValidateFlag = InvoiceUtil.validateBankDetails(jobContract);
					if(!bankValidateFlag)
					{
						errors.reject("invalid.bank.error", null, null);
						addJobOrder.setTabName("1");
						jobContract.setBankCd("");
						jobContract.setBankAcctKey("");
						return showForm(request, response, errors);
					}

					if(jobContract.getInvoiceDescr()=="" && jobOrder.getJobDescription()!=null)
					{
						jobContract.setInvoiceDescr(Constants.DESCR+" "+jobOrder.getJobDescription()+".");
					}
					jobContract.setJobNumber(jobOrder.getJobNumber());
					/* if(jobContract.getUid20() == null || jobContract.getUid20().trim().equals(""))
          { jobContract.setUid20(jobOrder.getJobNumber()+i);}*/
					//String s=jobOrder.getJobNumber();
					//int index_val = s.lastIndexOf("-");
					//String bname= s.substring(0, index_val);
					//String jcode=s.substring(index_val+1);        
					jobContract.setUid20(DateUtil.getUniquetimeString()+i);

					if(jobContract.getNominationFlag()==null || (!jobContract.getNominationFlag()))
					{
						jobContract.setNominationFlag(false);
					}
					//jobContract = jobService.saveJobContractInsp(jobContract);
					jobContractsSet.add(jobContract);
				}
				jobOrder.setJobContracts(jobContractsSet);
			}


			//Save JobContractInvoice with CreditReason note & User if 'Credit' or 'Rebill' is selected for Contract Status
			//& redirect to invoice preview page

			String rebillFlag = addJobOrder.getRebillFlag();
			if(rebillFlag != null && rebillFlag.trim().equals("rebill"))
			{

				String creditReasonUser = addJobOrder.getCreditReasonUser();
				String creditReasonNote = addJobOrder.getCreditReasonNote();
				int contractIndex = addJobOrder.getContractIndex();

				User creditReasonUserObj = userService.getUserByFirstLastName(creditReasonUser);

				if(creditReasonUserObj == null)
					creditReasonUser = "";
				else
					creditReasonUser = creditReasonUserObj.getLoginName();


				AddJobContract addJobContract = addJobOrder.getAddJobContracts()[contractIndex];
				if(addJobContract != null)
				{
					JobContract jobContract = addJobContract.getJobContract();
					jobContract.setJobContractStatus(addJobContract.getJobContractStatus());
					PrebillService prebillService = (PrebillService) ServiceLocator.getInstance().getBean("prebillService");
					JobContractInvoice lastJobContractInvoice = prebillService.getLastJobContractInvoice(jobContract.getId());
					if(lastJobContractInvoice!=null && lastJobContractInvoice.getBillStatus() != null && !lastJobContractInvoice.getBillStatus().equals("INV"))
					{
						errors.reject("credit.reason.error", new Object[] {"Credit can not be processed. It is not invoiced yet!"}, null);
						jobContract.setJobContractStatus(Constants.JOBCON_INVOICED_STATUS);
						addJobOrder.setTabName("1");
						addJobOrder.setRebillFlag("none");
						return showForm(request, response, errors);
					}

					//new code added on 10112008 for saving the vatprovince
					if(addJobOrder.getJobOrder().getBuName() != null && (!addJobOrder.getJobOrder().getBuName().trim().equals("")))
					{
						BusinessUnit bu = userService.getBusinessUnitByName(addJobOrder.getJobOrder().getBuName()); 
						Country country = bu.getCountry();
						if(country.getSameProvinceValidation()!= null && country.getSameProvinceValidation()!= false)
						{
							//Set prebillss = addJobContract.getJobContract().getPrebills();
							List prebillss=prebillService.getPrebillsByJobContractId(jobContract.getId());
							if(prebillss!=null){
								Iterator prebillIterator1 = prebillss.iterator();
								CountryVAT countryVAT=null;
								while(prebillIterator1.hasNext())
								{
									Prebill prebill =(Prebill) prebillIterator1.next();
									countryVAT=countryService.getDefaultStateByCountryCodeandVatCode(country.getCountryCode(),prebill.getVatCode());	
								}

								if(countryVAT!=null)
								{addJobContract.setContractLvlProvince(countryVAT.getCountryVATId().getStateCode());}
							}
						}
						else
						{addJobContract.setContractLvlProvince("");	}
					}
					//up to here on 10112008

					//        validate the Credit
					boolean creditValidateFlag=InvoiceUtil.validateInvoiceCredit(jobContract);
					if(!creditValidateFlag){   
						errors.reject("invalid.rebill.credit.error", null, null);
						addJobContract.setJobContractStatus(Constants.JOBCON_INVOICED_STATUS);
						addJobOrder.setRebillFlag("none");
						return showForm(request, response, errors);
					}


					//        Validate bank details
					boolean bankValidateFlag = InvoiceUtil.validateBankDetails(jobContract);
					if(!bankValidateFlag)
					{
						errors.reject("invalid.bank.error", null, null);
						jobContract.setJobContractStatus(Constants.JOBCON_INVOICED_STATUS);
						addJobOrder.setTabName("1");
						addJobOrder.setRebillFlag("none");
						return showForm(request, response, errors);
					}
					//        Validate customer/contact location
					boolean locValidateFlag = InvoiceUtil.validateCustomerLocation(jobContract);
					if(!locValidateFlag)
					{
						errors.reject("cusomer.contact.combo.error", null, null);
						jobContract.setJobContractStatus(Constants.JOBCON_INVOICED_STATUS);
						addJobOrder.setTabName("1");
						addJobOrder.setRebillFlag("none");
						return showForm(request, response, errors);
					}
					// commented on 16/01/09 because without creditReasonNote saving jobContract with reBill Status
					//jobContract = jobService.saveJobContractInsp(jobContract);


					System.out.println("before generateInvoice : "+jobContract.getJobContractStatus());
					try {
						// Checking creditReasonNote & creditReasonUser is populated with data before reBill
						if(creditReasonNote != null && !creditReasonNote.trim().equals("") && creditReasonUser != null && !creditReasonUser.trim().equals("")){
							jobContract = jobService.saveJobContractInsp(jobContract);
							InvoiceUtil.generateInvoice(jobContract.getId(), InvoiceUtil.getInvoiceDir(), InvoiceUtil.getJasperDir(), creditReasonNote,creditReasonUser,addJobContract.getContractLvlProvince());
						} else {
							jobContract.setJobContractStatus(Constants.JOBCON_INVOICED_STATUS);
							jobContract = jobService.saveJobContractInsp(jobContract);
						}
						//changed on 10112008 for saving the vat province
						// commented on 16/01/09 because without creditReasonNote reBilling 
						// InvoiceUtil.generateInvoice(jobContract.getId(), InvoiceUtil.getInvoiceDir(), InvoiceUtil.getJasperDir(), creditReasonNote,creditReasonUser,addJobContract.getContractLvlProvince()); 
						//InvoiceUtil.generateInvoice(jobContract.getId(), InvoiceUtil.getInvoiceDir(), InvoiceUtil.getJasperDir(), creditReasonNote,creditReasonUser);
						//up to here
						addJobContract.setJobContractStatus(jobContract.getJobContractStatus());
					} catch(ServiceException e){
						// Setting jobContractStatus as invoiced if any exception occurs while rebill or credit invoice generation
						jobContract.setJobContractStatus(Constants.JOBCON_INVOICED_STATUS);
						jobContract = jobService.saveJobContractInsp(jobContract);
						addJobContract.setJobContractStatus(jobContract.getJobContractStatus());
						addJobOrder.setRebillFlag("none");
						errors.reject(e.getMessage(), e.getParams(), null);
						return showForm(request, response, errors);
					} catch(Throwable t)
					{
						// Setting jobContractStatus as invoiced if any exception occurs while rebill or credit invoice generation
						jobContract.setJobContractStatus(Constants.JOBCON_INVOICED_STATUS);
						jobContract = jobService.saveJobContractInsp(jobContract);
						addJobContract.setJobContractStatus(jobContract.getJobContractStatus());
						addJobOrder.setRebillFlag("none");
						t.printStackTrace();
						throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
					}


				}
				//Check if a Job Contract record is chosen before moving to the next page
				// commented on 16/01/09 because without creditReasonNote it goes to preview page 
				//return new ModelAndView(new RedirectView("edit_job_invoice_preview.htm"), "jobNumber", jobOrder.getJobNumber());

				// Checking creditReasonNote & creditReasonUser is populated with data 
				if(creditReasonNote != null && !creditReasonNote.trim().equals("") && creditReasonUser != null && !creditReasonUser.trim().equals("")){
					return new ModelAndView(new RedirectView("edit_job_invoice_preview.htm"), "jobNumber", jobOrder.getJobNumber());
				} 
			}

			//For breadcrumbs
			if((jobOrder.getPageNumber() == null) || (jobOrder.getPageNumber().intValue() < 1))
				jobOrder.setPageNumber(Integer.valueOf(1));

			jobService.saveJobOrder(jobOrder);
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

		String nextFlag = addJobOrder.getNextPageFlag();
		if(nextFlag != null && nextFlag.trim().equals("1"))
		{
			//Check if a Job Contract record is chosen before moving to the next page
			if(addJobOrder.getAddJobContracts() == null || addJobOrder.getAddJobContracts().length == 0)
			{
				//   errors.reject("edit.job.order.error",new Object[] {"Please select at least one customer before proceeding to operational instructions page"}, null);
				errors.reject("select.customer.operational.proceed.error",new Object[] {}, null);
				addJobOrder.setTabName("1");
				return showForm(request, response, errors);
			}
			return new ModelAndView(new RedirectView("edit_job_operational_info_insp.htm"), "jobNumber", jobOrder.getJobNumber());

		}

		if(nextFlag != null && nextFlag.trim().equals("1"))
		{
			if(jobOrder.getJobStatus().equals(Constants.OPEN_STATUS)||jobOrder.getJobStatus().equals(Constants.REOPENED_STATUS))
				return new ModelAndView(new RedirectView("edit_job_operational_info_insp.htm"), "jobNumber", jobOrder.getJobNumber());
			else
				return new ModelAndView(new RedirectView("view_job_operational_info_insp.htm"), "jobNumber", jobOrder.getJobNumber());
			// return new ModelAndView(new RedirectView("view_job_operational_info.htm"), "jobNumber", jobOrder.getJobNumber());

		}

		//Go to view page added 24-12-08
		String goForm = addJobOrder.getGoForm();
		System.out.println("GoFrom value in edit analytical="+goForm);
		if(goForm != null && goForm.trim().equals("jobCancelAndGo"))
		{
			jobOrder.setCancelReasonNote(addJobOrder.getJobCancelReasonNote());
			jobOrder.setCancelReasonUserName(addJobOrder.getJobCancelReasonUser());
			jobService.updateJobOrder(jobOrder);
			addJobOrder.setGoForm(null);
			return new ModelAndView(new RedirectView("view_job_entry_analytical_service.htm?jobNumber="+jobOrder.getJobNumber()));
		}
		//end
		if(jobOrder.getJobStatus().equals(Constants.OPEN_STATUS)||jobOrder.getJobStatus().equals(Constants.REOPENED_STATUS))
			return new ModelAndView(new RedirectView("edit_job_entry_analytical_service.htm?jobNumber="+jobOrder.getJobNumber()+"&reqFrom="+addJobOrder.getOriginateFromSearchJob()+"&tabName="+addJobOrder.getTabName()+"&warnUserFlag="+addJobOrder.getWarnUserFlag()));
		else
			return new ModelAndView(new RedirectView("view_job_entry_analytical_service.htm?jobNumber="+jobOrder.getJobNumber()+"&reqFrom="+addJobOrder.getOriginateFromSearchJob()+"&tabName="+addJobOrder.getTabName()));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest,
	 *      org.springframework.web.bind.ServletRequestDataBinder)
	 */
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		// String dateFormate = SecurityUtil.getUser() != null ? SecurityUtil.getUser().getDateFormat():"dd/mm/yyyy";
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
		binder.registerCustomEditor(java.util.Date.class, null,
				new CustomDateEditor(dateFormat, true));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */

	protected boolean suppressValidation(HttpServletRequest request,Object command) {
		String operationFlag =request.getParameter("operationFlag");
		if((operationFlag != null) &&("operation".equals(operationFlag)))
		{
			return true;
		}

		String jobFlag = request.getParameter("jobFlag");
		if ((jobFlag != null) && ("jobval".equals(jobFlag))) {
			return true;
		}

		String deleteFlag=request.getParameter("deleteFlag");
		if((deleteFlag != null) &&("delval".equals(deleteFlag)))
		{
			return true;
		}

		String contactFlag=request.getParameter("contactFlag");
		if((contactFlag != null) &&("contFlag".equals(contactFlag)))
		{
			return true;
		}

		String uniqueFlag=request.getParameter("uniqueFlag");
		if((uniqueFlag != null) &&("uniqueFlag".equals(uniqueFlag)))
		{
			return true;
		}
		String bankFlag=request.getParameter("bankFlag");
		if((bankFlag != null) &&("bankFlag".equals(bankFlag)))        {
			return true;
		}
		String jobDateFlag=request.getParameter("jobDateFlag");
		if((jobDateFlag != null) &&("jobDate".equals(jobDateFlag)))
		{
			return true;
		}
		
		// START : #119240
		String showNextListFlag = request.getParameter("showNextListFlag");
		String showPrevListFlag = request.getParameter("showPrevListFlag");
		if(showNextListFlag != null && showNextListFlag.equals("next")){
			return true;
		}
		if(showPrevListFlag != null && showPrevListFlag.equals("prev")){
			return true;
		}
		// END : #119240

		return super.suppressValidation(request);
	}

	protected Object formBackingObject(HttpServletRequest request)  throws Exception {

		boolean hasDate=false;
		AddJobOrder addJobOrder = new AddJobOrder();
		JobOrder jobOrder = new JobOrder();

		JobService jobService = (JobService) ServiceLocator.getInstance().getBean("jobService");
		UserService userService = (UserService) ServiceLocator.getInstance().getBean("userService");
		CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");

		String jobNumber = request.getParameter("jobNumber");
		String orginFromJobSearch=request.getParameter("reqFrom");
		String tabName=request.getParameter("tabName");
		// String jobNum = request.getParameter("jobNumber");
		String warnUserFlag = request.getParameter("warnUserFlag");

		if(warnUserFlag == null)
			warnUserFlag = "";

		addJobOrder.setWarnUserFlag(warnUserFlag);

		//addJobOrder.setJobNumber(jobNum);
		addJobOrder.setJobNumber(jobNumber);
		Date dtUpdate = new Date();
		Date dtCreate = new Date();
		Branch branch = new Branch();
		BusinessUnit bu = new BusinessUnit();
		branch.setBusinessUnit(bu);
		jobOrder.setBranch(branch);
		String jobDescription = "";

		try {
			if (jobNumber != null && !jobNumber.trim().equals("")) {

				jobOrder = jobService.getJobOrderByJobNumberWithDetail(jobNumber);
			}
			if(jobOrder.getServiceLocation()== null)
				jobOrder.setServiceLocation(new ServiceLocation());
			// String dateFormat = SecurityUtil.getUser() != null ? SecurityUtil.getUser().getDateFormat():"dd/mm/yyyy";
			//  String timeFormat = SecurityUtil.getUser() != null ? SecurityUtil.getUser().getTimeFormat():"24 Hour";

			User loginUser =userService.getUserByName(SecurityUtil.getUser().getLoginName());
			String dateFormat = loginUser.getDateFormat();
			String timeFormat = loginUser.getTimeFormat();
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
			}else{
				dateFormat = dateFormat = Constants.DD_MM_YYYY_DATE_FORMAT;
			}
			addJobOrder.setDateFormat(dateFormat);
			if(timeFormat != null && !timeFormat.trim().equals("")){
				timeFormat = timeFormat.toUpperCase();
			}else {
				timeFormat = Constants.TWENTY_FOURHOUR;
			}
			addJobOrder.setUserTimeFormat(timeFormat.toUpperCase());
			
			// START : #119240
			addJobOrder.setCurrPageIdentifier(Constants.JOB_ENTRY_FORM);
			// END : #119240
			
			// Setting next list and prev list values
			if(request.getSession() != null)
			{
				JobSearch jobSearch=(JobSearch)request.getSession().getAttribute("MySearchJobOrder");
				// START : #119240
				if(jobSearch != null && orginFromJobSearch == null && request.getSession().getAttribute("orginFromJobSearch")!=null){
					orginFromJobSearch = (String)request.getSession().getAttribute("orginFromJobSearch");
				}
				// END : #119240
				
				if(jobSearch != null && orginFromJobSearch != null && !orginFromJobSearch.trim().equals(""))
				{
					// START : #119240
					request.getSession().setAttribute("orginFromJobSearch", orginFromJobSearch);
					// END : #119240
					List jobSearchResults = jobSearch.getJobNumbers();
					if(jobSearchResults != null && jobSearchResults.size()>0)
					{
						String jobNum="";
						jobNum=(String)jobSearchResults.get((jobSearchResults.size()-1));
						if(jobOrder != null && jobOrder.getJobNumber()!= null && jobOrder.getJobNumber().trim().equals(jobNum.trim()))
						{
							addJobOrder.setNextListFlag("false") ;
						}else
							addJobOrder.setNextListFlag("true") ; 
						jobNum  =(String)jobSearchResults.get(0);
						if(jobOrder != null && jobOrder.getJobNumber()!= null && jobOrder.getJobNumber().trim().equals(jobNum.trim()))
						{
							addJobOrder.setPrevListFlag("false") ;
						}else
							addJobOrder.setPrevListFlag("true") ; 
					}
				}else
				{
					addJobOrder.setNextListFlag("false") ;
					addJobOrder.setPrevListFlag("false") ;
				}
			}
			//end
			if (jobOrder != null) {
				addJobOrder.setJobOrder(jobOrder);
			} else {
				jobOrder = new JobOrder();
				addJobOrder.setJobOrder(jobOrder);
			}



			//Nomination & ETA Time conversion related logic
			/* if(jobOrder.getNominationTimeTz() == null)
    jobOrder.setNominationTimeTz(userService.getUserByNameWithOrgHierarchy(SecurityUtil.getUser().getLoginName()).getPreferredTz());

    if(jobOrder.getEtaTimeTz() == null)
      jobOrder.setEtaTimeTz(userService.getUserByNameWithOrgHierarchy(SecurityUtil.getUser().getLoginName()).getPreferredTz());*/

			//Setting Nomination & ETA TimeZone values 
			if(jobOrder.getNominationTime() != null && (jobOrder.getNominationTimeTz() == null || jobOrder.getNominationTimeTz().trim().equals("")))
				jobOrder.setNominationTimeTz((userService.getUserByNameWithOrgHierarchy(SecurityUtil.getUser().getLoginName())).getPreferredTz());

			if(jobOrder.getNominationTime() != null && (jobOrder.getNominationTimeTz() == null || jobOrder.getNominationTimeTz().trim().equals("")))
				jobOrder.setNominationTimeTz(Constants.TIME_ZONE);

			if(jobOrder.getEtaTime() != null && (jobOrder.getEtaTimeTz() == null || jobOrder.getEtaTimeTz().trim().equals("")))
				jobOrder.setEtaTimeTz((userService.getUserByNameWithOrgHierarchy(SecurityUtil.getUser().getLoginName())).getPreferredTz());

			if(jobOrder.getEtaTime() != null && (jobOrder.getEtaTimeTz() == null || jobOrder.getEtaTimeTz().trim().equals("")))
				jobOrder.setEtaTimeTz(Constants.TIME_ZONE);
			//end

			if(jobOrder.getNominationTime() != null)
			{
				Calendar cal = new GregorianCalendar();
				cal.setTime(jobOrder.getNominationTime());
				int hour24 = cal.get(Calendar.HOUR_OF_DAY);
				int min = cal.get(Calendar.MINUTE);

				String nmTime=String.valueOf(hour24)+":"+String.valueOf(min);


				jobOrder.setNominationTime(DateUtil.getConvertedDateTime(jobOrder.getNominationTime(),nmTime,Constants.TIME_ZONE,jobOrder.getNominationTimeTz()));
				// new date format code
				if(timeFormat != null && timeFormat.equals(Constants.USER_TIME_FPORMAT))
				{
					int hours=jobOrder.getNominationTime().getHours();
					int minutes=jobOrder.getNominationTime().getMinutes();
					if(hours>=12 && minutes > 0 )
					{

						jobOrder.getNominationTime().setHours(jobOrder.getNominationTime().getHours() - Constants.TWELVE_HOUR);
						addJobOrder.setNomTimeFormat(Constants.POSTMERIDIAN);
						addJobOrder.setEtaTimeFormat(Constants.POSTMERIDIAN);
					}else
					{
						addJobOrder.setNomTimeFormat(Constants.ANTEMERIDIAN);
						addJobOrder.setEtaTimeFormat(Constants.ANTEMERIDIAN);
					}
				}
				// new date formatecode end
				if(jobOrder.getNominationTime().getMinutes() >=0 && jobOrder.getNominationTime().getMinutes() <=9)
					addJobOrder.setUiNominationTime(jobOrder.getNominationTime().getHours() + ":0"+ jobOrder.getNominationTime().getMinutes());
				else
					addJobOrder.setUiNominationTime(jobOrder.getNominationTime().getHours() + ":"+ jobOrder.getNominationTime().getMinutes());
			}
			if(jobOrder.getEtaTime() != null)
			{
				Calendar cal = new GregorianCalendar();
				cal.setTime(jobOrder.getEtaTime());
				int hour24 = cal.get(Calendar.HOUR_OF_DAY);
				int min = cal.get(Calendar.MINUTE);

				String etaTime=String.valueOf(hour24)+":"+String.valueOf(min);


				jobOrder.setEtaTime(DateUtil.getConvertedDateTime(jobOrder.getEtaTime(),etaTime,Constants.TIME_ZONE,jobOrder.getEtaTimeTz()));
				// new code
				if(timeFormat != null && timeFormat.equals(Constants.USER_TIME_FPORMAT))
				{
					int hours=jobOrder.getEtaTime().getHours();
					int minutes = jobOrder.getEtaTime().getMinutes();
					if(hours >= 12 && minutes > 0 )
					{

						jobOrder.getEtaTime().setHours(jobOrder.getEtaTime().getHours() - Constants.TWELVE_HOUR);
						addJobOrder.setEtaTimeFormat(Constants.POSTMERIDIAN);

					}else
						addJobOrder.setEtaTimeFormat(Constants.ANTEMERIDIAN);
				}
				// new code end
				if(jobOrder.getEtaTime().getMinutes() >=0 && jobOrder.getEtaTime().getMinutes() <=9)
					addJobOrder.setUiEtaTime(jobOrder.getEtaTime().getHours() + ":0"+ jobOrder.getEtaTime().getMinutes());
				else
					addJobOrder.setUiEtaTime(jobOrder.getEtaTime().getHours() + ":"+ jobOrder.getEtaTime().getMinutes());
			}

			//Issue 106438 20 May 2009
			Date etaDate = jobOrder.getEtaDate();
			if(null != etaDate){
				DateFormat dateFormat1 = new SimpleDateFormat(Constants.YYYY_MM_DD_DATE_FORMAT);
		        java.util.Date date = new java.util.Date();
		        Date todayDate = dateFormat1.parse(dateFormat1.format(date));
		        if(todayDate.after(etaDate) && null == jobOrder.getEtaTime() && jobOrder.getJobFinishDate()== null){
		        	jobOrder.setEtaDate(todayDate);
		        }
		        
			}
			//Issue 106438 20 May 2009							
			
			List jobContracts=jobService.getAllJobContractByJobNumber(jobNumber);
			AddJobContract[] addJobContracts= new AddJobContract[jobContracts.size()];
			int jobContractCount = 0;
			if(jobContracts != null)
				jobContractCount = jobContracts.size();
			addJobOrder.setJobContractCount(jobContractCount);
			addJobOrder.setCustCount(addJobOrder.getJobContractCount());
			boolean viewOnly = false;
			addJobOrder.setMonthlyvalidFlag("false"); 
			for(int i=0; i<jobContracts.size(); i++)
			{
				AddJobContract addJobContract = new AddJobContract();
				JobContract jobContract = (JobContract) jobContracts.get(i);

				if(jobContract.getJobContractStatus() == null || jobContract.getJobContractStatus().trim().equals(""))
					jobContract.setJobContractStatus(Constants.JOBCON_OPEN_STATUS);

				addJobContract.setJobContractStatus(jobContract.getJobContractStatus());

				if(!viewOnly && (jobContract.getJobContractStatus().trim().equals(Constants.JOBCON_INVOICED_STATUS) || jobContract.getJobContractStatus().trim().equals(Constants.JOBCON_CREDITED_STATUS) || jobContract.getJobContractStatus().trim().equals(Constants.JOBCON_REBILLED_STATUS)))
					viewOnly = true;

				if((jobContract.getJobContractStatus().trim().equals(Constants.JOBCON_INVOICED_STATUS)  ) || jobContract.getJobContractStatus().trim().equals(Constants.JOBCON_CREDITED_STATUS))
					addJobContract.setContractViewOnly(true);
				else
					addJobContract.setContractViewOnly(false);

				ContractCustContact contractCustContact=new ContractCustContact();
				ContactCust contactCust=new ContactCust();
				contractCustContact=jobService.getContractDetailsByCode(jobContract.getContractCode(),jobContract.getCustCode(),jobContract.getContactId());
				jobContract.setContractCustContact(contractCustContact);
				Contact billingContact=new Contact();
				if(jobContract.getBillingContact() != null)
				{
					long contactId=jobContract.getBillingContact().getId();
					billingContact.setId(contactId);
					contactCust=jobService.getBillingContactByContactId(contactId,jobContract.getCustCode());
					if(contactCust!=null)
					{
						String contactName=contactCust.getContact().getFirstName()+","+contactCust.getContact().getLastName();
						Set st =contactCust.getCustAddrSeq().getCustAddresses();
						Iterator itr =st.iterator();
						if(itr.hasNext())
						{
							CustAddress  custAddress=( CustAddress)itr.next();
							String address=getContactAddress(custAddress);
							addJobContract.setBillingAddress(address);
						}
					}
					jobContract.setBillingContact(billingContact);
				}
				else
				{
					jobContract.setBillingContact(billingContact);}

				if(jobContract.getVatRegCountry()!=null)
				{
					Country country = countryService.getCountryByCode(jobContract.getVatRegCountry().getCountryCode());
					if(jobContract.getVatRegId()!=null && !jobContract.getVatRegId().trim().equals(""))
					{
						if(country.getCountry2()!=null && !country.getCountry2().trim().equals(""))
						{
							addJobContract.setVatRateCountry(country.getCountry2()+" : "+jobContract.getVatRegId());
						}
						else
						{
							addJobContract.setVatRateCountry(" : "+jobContract.getVatRegId());
						}
					}
					else
					{
						if(country.getCountry2()!=null && !country.getCountry2().trim().equals(""))
						{addJobContract.setVatRateCountry(country.getCountry2()+" : ");}
						else
						{addJobContract.setVatRateCountry("");}
					}
				}
				else
				{addJobContract.setVatRateCountry("");}

				contactCust=jobService.getBillingContactByContactId(contractCustContact.getContact().getId(),contractCustContact.getContractCust().getCustomer().getCustCode());
				if(contactCust!=null)
				{
					Set st =contactCust.getCustAddrSeq().getCustAddresses();
					Iterator itr =st.iterator();
					CustAddress  custAddress=( CustAddress)itr.next();
					// addJobContract.setSchedulerAddress(address);
					addJobContract.setSchedulerAddress(StringUtil.forHTML(getContactAddress(custAddress)));
				}
				else{ addJobContract.setSchedulerAddress("");}

				String currency=jobContract.getTransactionCurrencyCd();

				jobContract.setJobOrder(jobOrder);
				//  addJobContract.setJobContract(jobContract);
				CfgContract cfgContract=new CfgContract();
				cfgContract.setCurrencyCD(currency);

//				for the issue 106438-------to auto change the ETA Date
/*				Date etaDate = jobOrder.getEtaDate();
				
				if(null != etaDate){
					DateFormat dateFormat1 = new SimpleDateFormat(Constants.YYYY_MM_DD_DATE_FORMAT);
			        java.util.Date date = new java.util.Date();
			        Date todayDate = dateFormat1.parse(dateFormat1.format(date));
			        if(todayDate.after(etaDate) && null == jobOrder.getEtaTime() && jobOrder.getJobFinishDate()== null){
			        	jobOrder.setEtaDate(todayDate);
			        }
			        
				}*/
				//END .... 106438	
				
				Date asOfDate=new Date();
				if(jobOrder.getJobFinishDate()!=null )
				{
					asOfDate=jobOrder.getJobFinishDate();
				}
				else if(jobOrder.getJobFinishDate()==null && jobOrder.getEtaDate()!=null )
				{
					asOfDate=jobOrder.getEtaDate();
				}
				else if(jobOrder.getJobFinishDate()==null &&  jobOrder.getEtaDate()==null )
				{
					asOfDate=new Date();
				}

				String pricebookId="";
				String zonedes="";

				cfgContract =jobService.getPriceBookByContractCode(jobContract.getContractCode(),asOfDate);
				PriceBook priceBook=new PriceBook();
				if(cfgContract!=null)
				{
					if(!cfgContract.getPriceBookId().equalsIgnoreCase("CURRENT"))
					{
						pricebookId=cfgContract.getPriceBookId();
					}
					else
					{
						priceBook=jobService.getPriceBookIdByCurrencyandSeries(cfgContract.getCurrencyCD(),cfgContract.getPbSeries(),asOfDate);
						if(priceBook!=null)
						{
							pricebookId=priceBook.getPriceBookId().getPriceBookId();
						}
					}
				}

				addJobContract.setPriceBookId(pricebookId);
				addJobContract.setCfgContract(cfgContract);
				if(jobContract.getJobOrder() != null && jobContract.getJobOrder().getBranch()!=null && jobContract.getJobOrder().getBranch().getBusinessUnit()!=null)
					jobContract.setBaseCurrencyCd(jobContract.getJobOrder().getBranch().getBusinessUnit().getCurrencyBase());

				if(jobContract.getZone()==null || jobContract.getZone().trim().equals("")){
					zonedes= jobService.getZoneByBranchCodeandContractCode(jobOrder.getBranchName(),jobContract.getContractCode(),pricebookId,asOfDate);
					if(zonedes!=null&&!zonedes.trim().equals(""))
					{jobContract.setZone(zonedes);}else{jobContract.setZone("NONE");}
				}
				addJobContract.setJobContractStatus(jobContract.getJobContractStatus());
				addJobContract.setJobContract(JobUtil.populateDefaultJobContractFields(jobContract, contractCustContact, jobOrder,SecurityUtil.getUser()));



				addJobContracts[i] = addJobContract;
			}
			addJobOrder.setAddJobContracts(addJobContracts);
			//     Code for 'Add Customers' tab ends
			if(tabName!=null)
			{
				addJobOrder.setTabName(tabName);
			}
			else
			{
				addJobOrder.setTabName("0");
			}

			addJobOrder.setViewOnly(viewOnly);
			
			if(orginFromJobSearch != null && orginFromJobSearch.equals("jobLog"))
				addJobOrder.setOriginateFromSearchJob(Constants.JOB_LOG);
			if(orginFromJobSearch != null && orginFromJobSearch.equals("jobSearch"))
				addJobOrder.setOriginateFromSearchJob(Constants.JOB_SEARCH);
			if(orginFromJobSearch != null && orginFromJobSearch.equals(Constants.JOB_LOG))
				addJobOrder.setOriginateFromSearchJob(orginFromJobSearch);
			if(orginFromJobSearch != null && orginFromJobSearch.equals(Constants.JOB_SEARCH))
				addJobOrder.setOriginateFromSearchJob(orginFromJobSearch);
		} catch(ServiceException e)
		{
			throw new ServiceException(e.getMessage(), e.getParams(), e);
		}
		catch(Throwable t)
		{
			t.printStackTrace();
			throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
		}      
		return addJobOrder;
	}
}
