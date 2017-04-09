package com.intertek.web.controller.job;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

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
import com.intertek.domain.StringSearchField;
import com.intertek.entity.Bank;
import com.intertek.entity.BankAccount;
import com.intertek.entity.Branch;
import com.intertek.entity.BusinessUnit;
import com.intertek.entity.CfgContract;
import com.intertek.entity.Contact;
import com.intertek.entity.ContactCust;
import com.intertek.entity.ContractCustContact;
import com.intertek.entity.JobContract;
import com.intertek.entity.JobOrder;
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
import com.intertek.service.ServiceLocationService;
import com.intertek.service.ShippingAgentService;
import com.intertek.service.TowingCompanyService;
import com.intertek.service.UserService;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;
import com.intertek.util.InvoiceUtil;
import com.intertek.util.JobUtil;
import com.intertek.util.SecurityUtil;

public class CreateJobOrderAnalyticalServiceFormController extends CommonJobOrder {
	public CreateJobOrderAnalyticalServiceFormController() {
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
		CustomerService customerService = (CustomerService) ServiceLocator.getInstance().getBean("customerService");
		TowingCompanyService towingCompanyService = (TowingCompanyService) ServiceLocator.getInstance().getBean("towingCompanyService");
		ShippingAgentService shippingAgentService = (ShippingAgentService) ServiceLocator.getInstance().getBean("shippingAgentService");
		ServiceLocationService serviceLocationService = (ServiceLocationService) ServiceLocator.getInstance().getBean("serviceLocationService");
		CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");
		UserService userService = (UserService) ServiceLocator.getInstance().getBean("userService");

		//JobOrder jobOrder = addJobOrder.getJobOrder();
		TowingCompany towingCompany = jobOrder.getTowingCompany();
		ShippingAgent shippingAgent = jobOrder.getShippingAgent();
		ServiceLocation serviceLocation = jobOrder.getServiceLocation();

		String searchFiled = addJobOrder.getSearchField();
		String searchString = addJobOrder.getSearchString();		
		String goFlag=request.getParameter("goFlag");

		//Validating branch
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
		if (searchString != null && !searchString.trim().equals("")&&goFlag!=null && goFlag.trim().equals("true"))
		{
			if(searchFiled.equals("1"))
			{
				try{
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
						}
						else
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
				}/*else {
			errors.reject("create.job.order.error",new Object[] { "no job records exists for this job number : "	+ searchString, }, null);
			addJobOrder.setSearchString("");
			addJobOrder.setGoFlag("false");
			return showForm(request, response, errors);
			}*/
			}
		}
		else
		{addJobOrder.setGoFlag("false");}


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
							try {
								contractCustContact=jobService.getContractDetailsByCode(cCode);
								//ContactCust contactCust=new ContactCust();
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

							if(contractCustContact.getContractCust().getCustomer().getInterunitInd()!=null && contractCustContact.getContractCust().getCustomer().getInterunitInd()==true
									&&contractCustContact.getContractCust().getCustomer().getInterunitBusUnitName()!=null && !contractCustContact.getContractCust().getCustomer().getInterunitBusUnitName().trim().equals("")	
									&&jobOrder.getBuName().equals(contractCustContact.getContractCust().getCustomer().getInterunitBusUnitName()))	
								//ContactCust contactCust=new ContactCust();
							{		
							}
							else
							{	

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
								else if(jobOrder.getJobFinishDate()==null &&	jobOrder.getEtaDate()==null )
								{
									asOfDate=new Date();
								}

								String zonedes="";
								String pricebookId="";
								CfgContract cfgContract=null;
								try{
									//CfgContract cfgContract =jobService.getPriceBookByContractCode(contractCustContact.getContractCust().getContract().getContractCode(),asOfDate); 
									cfgContract =jobService.getPriceBookByContractCode(contractCustContact.getContractCust().getContract().getContractCode(),asOfDate); 
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
									//sheidacfgContract=jobService.getCfgContractByContractCode(contractCustContact.getContractCust().getContract().getContractCode());
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
						addJobOrder.setContrFlag("true");
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

				}//for


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

				// }//if

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
			ContractCustContact contractCustContact=new ContractCustContact();
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
			else if(jobOrder.getJobFinishDate()==null &&	jobOrder.getEtaDate()==null )
			{
				asOfDate=new Date();
			}

			String zonedes="";
			String pricebookId="";
			CfgContract cfgContract=null;
			try{
				//CfgContract cfgContract =jobService.getPriceBookByContractCode(contractCustContact.getContractCust().getContract().getContractCode(),asOfDate); 
				cfgContract =jobService.getPriceBookByContractCode(contractCustContact.getContractCust().getContract().getContractCode(),asOfDate); 
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
				//sheidacfgContract=jobService.getCfgContractByContractCode(contractCustContact.getContractCust().getContract().getContractCode());
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
			}//end for try
			catch(ServiceException e)
			{
				addJobOrder.setUniqueFlag("none");
				addJobOrder.setPopFlag("none");
				addJobOrder.setJobFlag("none");		
				addJobOrder.setTabName("1");
				addJobOrder.setContrFlag("true");
				addJobOrder.setPopFlag("none");
				e.printStackTrace();
				errors.reject(e.getMessage(), e.getParams(), null);
				return showForm(request, response, errors);
			}
			catch(Throwable t)
			{
				addJobOrder.setUniqueFlag("none");
				addJobOrder.setPopFlag("none");
				addJobOrder.setJobFlag("none");		
				addJobOrder.setTabName("1");
				addJobOrder.setContrFlag("true");
				addJobOrder.setPopFlag("none");
				t.printStackTrace();
				errors.reject("generic.error", new Object[] {t.getMessage()}, null);
				return showForm(request, response, errors);
			}
			jobContract.setJobOrder(jobOrder);
			jobContract.setCustCode(contractCustContact.getContractCust().getCustomer().getCustCode());
			jobContract.setContractCode(contractCustContact.getContractCust().getContract().getContractCode());
			jobContract.setContactId(contractCustContact.getContact().getId());

			//		addJobOrder.setAddJobContracts(addJobContract(addJobOrder.getAddJobContracts(),contractCustContact,jobContract));
			addJobOrder.setAddJobContracts(addJobContract(addJobOrder.getAddJobContracts(),contractCustContact,jobContract,cfgContract,contactCust,pricebookId));
			addJobOrder.setCustCount(addJobOrder.getAddJobContracts().length);
			addJobOrder.setUniqueFlag("none");
			addJobOrder.setPopFlag("none");
			addJobOrder.setJobFlag("none");		addJobOrder.setTabName("1");
			addJobOrder.setContrFlag("true");
			addJobOrder.setPopFlag("none");
			return showForm(request, response, errors);
		}

		String deleteFlag=request.getParameter("deleteFlag");
		if("delval".equals(deleteFlag))
		{
			addJobOrder.setAddJobContracts(deleteJobContract(addJobOrder.getAddJobContracts(),addJobOrder.getJobIndex()));
			addJobOrder.setCustCount(addJobOrder.getAddJobContracts().length);
			addJobOrder.setDeleteFlag("none");
			addJobOrder.setPopFlag("none");
			addJobOrder.setTabName("1");
			return showForm(request, response, errors);
		}
		String jobDateFlag=request.getParameter("jobDateFlag");
		if("jobDate".equals(jobDateFlag))
		{      
			Date asOfDate=new Date();
			String branchName = jobOrder.getBranchName();
			Branch existedBranch = userService.getBranchByName(branchName);
			if (existedBranch == null) {
				errors.reject("branch.not.exist.error",new Object[] {branchName},null );
				return showForm(request, response, errors);	
			}
			try{
				if(jobOrder.getJobFinishDate()!=null )
				{
					asOfDate=jobOrder.getJobFinishDate();
				}
				else if(jobOrder.getJobFinishDate()==null && jobOrder.getEtaDate()!=null )
				{
					asOfDate=jobOrder.getEtaDate();
				}
				else if(jobOrder.getJobFinishDate()==null &&	jobOrder.getEtaDate()==null )
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
						//CfgContract cfgContract=new CfgContract();
						String zonedes="";
						String pricebookId="";
						CfgContract cfgContract =jobService.getPriceBookByContractCode(jobContract.getContractCode(),asOfDate); 
						//PriceBook priceBook=new PriceBook();
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
						zonedes= jobService.getZoneByBranchCodeandContractCode(jobOrder.getBranchName(),jobContract.getContractCode(),pricebookId,asOfDate);
						if(zonedes!=null&&!zonedes.trim().equals(""))
						{jobContract.setZone(zonedes);}else{jobContract.setZone("NONE");}
						String dateFlag=request.getParameter("dateFlag");
						if(dateFlag!=null && dateFlag.trim().equals("true"))
						{
							if(jobContract.getId()==0)
							{
								List referenceFields=jobService.getReferenceFieldsByContractCode(jobContract.getContractCode());

								if(referenceFields.size()==0)
								{
									if(pricebookId!=null&& !pricebookId.trim().equals(""))
									{
										referenceFields=jobService.getReferenceFieldsByContractCode(pricebookId);
									}
								}

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
			}
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
			} catch(ServiceException e)
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

		//Code for 'Add Customers' tab ends

		//Check if next page is requested before saving the record
		String nextFlag = addJobOrder.getNextPageFlag();
		if(nextFlag != null && nextFlag.trim().equals("1"))
		{
			//Check if a Job Contract record is chosen before moving to the next page
			if(addJobOrder.getAddJobContracts() == null || addJobOrder.getAddJobContracts().length == 0)
			{
				addJobOrder.setTabName("1");
				addJobOrder.setJobOrder(jobOrder);
				addJobOrder.setNextPageFlag("");
				//errors.reject("create.job.order.error",new Object[] {"Please select at least one customer before proceeding to operational instructions page"}, null);
				errors.reject("select.customer.operational.proceed.error",new Object[] {}, null);
				addJobOrder.setTabName("1");
				return showForm(request, response, errors);	 
			}

		}		
		//START: 118717 Fix for new requirement - 01 June 2009
		boolean blnJobFinishDtExts = false;		
		if(null != jobOrder.getJobFinishDate()){
			blnJobFinishDtExts = true;		
		}
		//END: 118717 Fix for new requirement - 01 June 2009		
		if(addJobOrder.getUiEtaTime()!= null && !addJobOrder.getUiEtaTime().equals(""))
		{
			//START: 118717 Fix for new requirement - 01 June 2009
			if(!blnJobFinishDtExts && (jobOrder.getEtaDate() == null || jobOrder.getEtaDate().equals("")))
			//END: 118717 Fix for new requirement - 01 June 2009	
			{
				//errors.reject("create.job.order.error",new Object[] {"Please Select ETA Date : "},null);
				errors.reject("select.eta.date.error",new Object[] {},null);
				return showForm(request, response, errors);	
			}
		}
		if(addJobOrder.getUiNominationTime()!= null && !addJobOrder.getUiNominationTime().equals(""))
		{
			if(jobOrder.getNominationDate() == null || jobOrder.getNominationDate().equals(""))
			{
				//	errors.reject("create.job.order.error",new Object[] {"Please Select Nomination Date : "},null);
				errors.reject("select.nomination.date.error",new Object[] {},null);
				return showForm(request, response, errors);	
			}
		}

		AddJobContract[] jobContractItem = addJobOrder.getAddJobContracts();
		if(jobContractItem!=null)
		{
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

		try    {
			
			//for the issue 106438 ---- for validating, wheather the ETA Date is greater than or equals to todays date
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

			//Do time conversions 
			String uiNominationTime = addJobOrder.getUiNominationTime();
			if(uiNominationTime != null && (!uiNominationTime.trim().equals("")))
			{
				if(jobOrder.getNominationTimeTz() == null || jobOrder.getNominationTimeTz().trim().equals(""))
				{
					//errors.reject("create.job.order.error",new Object[] {"Please enter a valid nomination time zone"}, null);
					errors.reject("invalid.timezone.error",new Object[] {}, null);
					return showForm(request, response, errors);					
				}
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

				} else {
					nominationTime = DateUtil.getConvertedDateTime(jobOrder.getNominationDate(),uiNominationTime,jobOrder.getNominationTimeTz(),Constants.TIME_ZONE);
				}
				//end
				jobOrder.setNominationTime(nominationTime);

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
				//END: 118717 Fix for new requirement - 04 June 2009				
				
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
				/*	errors.reject("ETADate.error", new Object[] {}, null);
		        	addJobOrder.setTabName("0");
		        	return showForm(request, response, errors);*/
					jobOrder.setEtaDate(todayDate);
					//END: For Issue 106438 21 May 2009					
		        }
			}
			//END: For Issue 106438 20 May 2009
			
			//For breadcrumbs

			jobOrder.setPageNumber(Integer.valueOf(1));

			jobOrder = jobService.addJobOrder(jobOrder);
		} 
		/*catch (Exception e)
				{
				e.printStackTrace();
				errors.reject("create.job.order.error", new Object[] { e.getMessage() }, null);
				return showForm(request, response, errors);
				}*/
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

		try{
			AddJobContract[] jobContractItems = addJobOrder.getAddJobContracts();
			if(jobContractItems!=null)
			{
				for(int i=0; i< jobContractItems.length; i++)
				{
					AddJobContract addJobContract=jobContractItems[i];
					JobContract jobContract=addJobContract.getJobContract();
					
					//           	      Validate bank details
					boolean bankValidateFlag = InvoiceUtil.validateBankDetails(jobContract);
					if(!bankValidateFlag)
					{
						errors.reject("invalid.bank.error", null, null);
						jobContract.setBankCd("");
						jobContract.setBankAcctKey("");
						addJobOrder.setTabName("1");
						return showForm(request, response, errors);
					}
					if(jobContract.getInvoiceDescr()=="" && jobOrder.getJobDescription()!=null)
					{
						jobContract.setInvoiceDescr(Constants.DESCR+" "+jobOrder.getJobDescription()+".");
					}
					jobContract.setJobNumber(jobOrder.getJobNumber());
					//jobContract.setUid20(jobOrder.getJobNumber()+i);
					//String s=jobOrder.getJobNumber();
					//int index_val = s.lastIndexOf("-");
					//String bname= s.substring(0, index_val);
					// String jcode=s.substring(index_val+1);          
					jobContract.setUid20(DateUtil.getUniquetimeString()+i);

					if(jobContract.getNominationFlag()==null || (!jobContract.getNominationFlag()))
					{
						jobContract.setNominationFlag(false);
					}
					jobContract = jobService.addJobContractInsp(jobContract);

				}
			}
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
		/*catch(Exception e)
			{
			e.printStackTrace();
			errors.reject("create.job.order.error",new Object[] {e.getMessage()}, null);
			return showForm(request, response, errors);
			}*/


		if(nextFlag != null && nextFlag.trim().equals("1"))
		{
			return new ModelAndView(new RedirectView("create_job_operational_info_insp.htm"), "jobNumber", jobOrder.getJobNumber());
		}			
		addJobOrder.setSearchField("");
		addJobOrder.setSearchString("");		
		return new ModelAndView(new RedirectView("edit_job_entry_analytical_service.htm?jobNumber="+jobOrder.getJobNumber()+"&tabName="+addJobOrder.getTabName()));


	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest,
	 *      org.springframework.web.bind.ServletRequestDataBinder)
	 */
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		//String dateFormate = SecurityUtil.getUser() != null ? SecurityUtil.getUser().getDateFormat():"dd/mm/yyyy";
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
		binder.registerCustomEditor(java.util.Date.class, null,new CustomDateEditor(dateFormat, true));
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
		if((jobFlag != null) &&("jobval".equals(jobFlag)))
		{
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
		if((bankFlag != null) &&("bankFlag".equals(bankFlag)))
		{
			return true;
		}


		String jobDateFlag=request.getParameter("jobDateFlag");
		if((jobDateFlag != null) &&("jobDate".equals(jobDateFlag)))
		{
			return true;
		}

		return super.suppressValidation(request);
	}

	protected Object formBackingObject(HttpServletRequest request)
	throws Exception {
		// START : #119240
		if(request.getSession().getAttribute("orginFromJobSearch")!=null){
			request.getSession().removeAttribute("orginFromJobSearch");
		}
		// END : #119240
		
		AddJobOrder addJobOrder = new AddJobOrder();
		UserService userService = (UserService) ServiceLocator
		.getInstance().getBean("userService");

		JobOrder jobOrder = new JobOrder();
		Date dtUpdate = new Date();
		Date dtCreate = new Date();

		Calendar mycal = Calendar.getInstance();
		mycal.setTime(dtCreate);
		String mytime = String.valueOf(mycal.get(Calendar.HOUR_OF_DAY)).concat(":").concat(String.valueOf(mycal.get(Calendar.MINUTE)));


		Branch branch = new Branch();
		BusinessUnit bu = new BusinessUnit();
		branch.setBusinessUnit(bu);
		jobOrder.setBranch(branch);
		jobOrder.setReceivedByUserName(SecurityUtil.getUser().getLoginName());
		jobOrder.setAdminContactUserName(SecurityUtil.getUser().getLoginName());
		jobOrder.setJobStatus(Constants.OPEN_STATUS);
		// Date Formate code start

		User loginUser =userService.getUserByName(SecurityUtil.getUser().getLoginName());
		String dateFormat = loginUser.getDateFormat();
		String timeFormat = loginUser.getTimeFormat();
		if(dateFormat != null && !dateFormat.trim().equals("")){
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
		if(timeFormat != null && !timeFormat.trim().equals(""))
		{
			timeFormat = timeFormat.toUpperCase();
		}else{
			timeFormat = Constants.TWENTY_FOURHOUR;
		}
		addJobOrder.setUserTimeFormat(timeFormat);
		//shDate nomDate = DateUtil.getFormateDateFromCurrentDate(dtCreate,dateFormat);

		//shjobOrder.setNominationDate(nomDate);
		try{
			if(userService.getUserByNameWithOrgHierarchy(SecurityUtil.getUser().getLoginName()).getPreferredTz() == null)
			{
				jobOrder.setNominationTimeTz(Constants.DEFAULT_TIMEZONE);
				jobOrder.setEtaTimeTz(Constants.DEFAULT_TIMEZONE);
			} else if(userService.getUserByNameWithOrgHierarchy(SecurityUtil.getUser().getLoginName()).getPreferredTz() != null && userService.getUserByNameWithOrgHierarchy(SecurityUtil.getUser().getLoginName()).getPreferredTz().trim().equals(""))
			{
				jobOrder.setNominationTimeTz(Constants.DEFAULT_TIMEZONE);
				jobOrder.setEtaTimeTz(Constants.DEFAULT_TIMEZONE);
			} else 
			{
				jobOrder.setNominationTimeTz(userService.getUserByNameWithOrgHierarchy(SecurityUtil.getUser().getLoginName()).getPreferredTz());
				jobOrder.setEtaTimeTz(userService.getUserByNameWithOrgHierarchy(SecurityUtil.getUser().getLoginName()).getPreferredTz());
			}
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
		Date defaultedDate = DateUtil.getConvertedDateTime(dtCreate,mytime,Constants.PHOENIX_TIMEZONE,jobOrder.getNominationTimeTz());
		jobOrder.setNominationDate(DateUtil.getFormateDateFromCurrentDate(defaultedDate,dateFormat));
		SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
		String defaultedTime = sdf.format(defaultedDate);

		if(timeFormat != null && timeFormat.equals(Constants.USER_TIME_FPORMAT))
		{
			String uiNomTime=defaultedTime;
			String nomTime[]=uiNomTime.split(":");
			int uiHourTime = 0;
			String uiMinuteTime = "";
			if(nomTime.length >=2)
			{
				uiHourTime = Integer.valueOf(nomTime[0]).intValue();
				uiMinuteTime = nomTime[1];
			}else
				uiHourTime = Integer.valueOf(nomTime[0]).intValue();

			if(uiHourTime == Constants.TWELVE_HOUR && uiMinuteTime.trim().equals("00"))
			{
				addJobOrder.setNomTimeFormat(Constants.ANTEMERIDIAN);
				addJobOrder.setEtaTimeFormat(Constants.ANTEMERIDIAN);
				addJobOrder.setUiNominationTime(defaultedTime);
			}
			else if(uiHourTime >= Constants.TWELVE_HOUR && !uiMinuteTime.trim().equals("00"))
			{
				uiHourTime = uiHourTime - Constants.TWELVE_HOUR;
				uiNomTime=String.valueOf(uiHourTime)+":"+uiMinuteTime;
				addJobOrder.setUiNominationTime(uiNomTime);
				addJobOrder.setNomTimeFormat(Constants.POSTMERIDIAN);
				addJobOrder.setEtaTimeFormat(Constants.POSTMERIDIAN);
			}else
			{
				addJobOrder.setNomTimeFormat(Constants.ANTEMERIDIAN);
				addJobOrder.setEtaTimeFormat(Constants.ANTEMERIDIAN);
				addJobOrder.setUiNominationTime(defaultedTime);
			}
		}
		else
			addJobOrder.setUiNominationTime(defaultedTime);
		//		 Date Formate code end
		try{
			if (!"POST".equals(request.getMethod())) {


				bu.setName(userService.getUserByNameWithOrgHierarchy(SecurityUtil.getUser().getLoginName()).getBuName());
				jobOrder.setBuName(userService.getUserByNameWithOrgHierarchy(SecurityUtil.getUser().getLoginName()).getBuName());
				jobOrder.setBranchName(userService.getUserByNameWithOrgHierarchy(SecurityUtil.getUser().getLoginName()).getBranchName());
				jobOrder.setBranch(userService.getUserByNameWithOrgHierarchy(SecurityUtil.getUser().getLoginName()).getBranch());
				jobOrder.setBusinessUnit(userService.getUserByNameWithOrgHierarchy(SecurityUtil.getUser().getLoginName()).getBusinessUnit());

			}
			//jobOrder.setNominationTime(DateUtil.getCurrentTime());

			jobOrder.setJobType(Constants.ANALYTICAL_SERVICE_JOBTYPE);
			jobOrder.setRevisionNumber(Constants.REVISION_NUMBER);
			jobOrder.setCreateTime(dtUpdate);
			jobOrder.setUpdateTime(dtCreate);
			addJobOrder.setJobOrder(jobOrder);
			addJobOrder.setTabName("0");
			/*if(userService.getUserByNameWithOrgHierarchy(SecurityUtil.getUser().getLoginName()).getPreferredTz() == null)
		{
			jobOrder.setNominationTimeTz(Constants.DEFAULT_TIMEZONE);
			jobOrder.setEtaTimeTz(Constants.DEFAULT_TIMEZONE);
		} else if(userService.getUserByNameWithOrgHierarchy(SecurityUtil.getUser().getLoginName()).getPreferredTz() != null && userService.getUserByNameWithOrgHierarchy(SecurityUtil.getUser().getLoginName()).getPreferredTz().trim().equals(""))
		{
			jobOrder.setNominationTimeTz(Constants.DEFAULT_TIMEZONE);
			jobOrder.setEtaTimeTz(Constants.DEFAULT_TIMEZONE);
		} else 
		{
			jobOrder.setNominationTimeTz(userService.getUserByNameWithOrgHierarchy(SecurityUtil.getUser().getLoginName()).getPreferredTz());
			jobOrder.setEtaTimeTz(userService.getUserByNameWithOrgHierarchy(SecurityUtil.getUser().getLoginName()).getPreferredTz());
		}*/
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
		//addJobOrder.setUiNominationTime(DateUtil.getCurrentTime().toString());
		//addJobOrder.setUiNominationTime(DateUtil.getParsedCurrentTime());
		//Code for 'Add Customers' tab

		String jobCountStr=request.getParameter("jobCount"); 

		if( jobCountStr== null ||jobCountStr.trim().equals("") ) 
			jobCountStr = "0";
		int jobCount=0;


		try {jobCount= new Integer(jobCountStr).intValue();	}

		catch(Exception e) { }

		addJobOrder.setJobCount(jobCount);

		AddJobContract[] addJobContracts= new AddJobContract[jobCount];


		for(int i=0; i<jobCount; i++)
		{
			AddJobContract addJobContract = new AddJobContract();
			JobContract jobContract=new JobContract();
			jobContract.setJobContractStatus(Constants.JOBCON_OPEN_STATUS);
			Contact contact=new Contact();
			ContractCustContact contractCustContact=new ContractCustContact();
			Contact billingContact=new Contact();
			jobContract.setBillingContact(billingContact);
			jobContract.setContractCustContact(contractCustContact);
			addJobContract.setJobContract(jobContract);
			addJobContracts[i] = addJobContract;
		}
		addJobOrder.setAddJobContracts(addJobContracts);
		//	   Code for 'Add Customers' tab	ends  

		return addJobOrder;
	}
	
}
