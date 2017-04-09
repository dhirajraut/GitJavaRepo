package com.intertek.web.controller.job;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
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
import com.intertek.entity.CustAddress;
import com.intertek.entity.JobContract;
import com.intertek.entity.JobOrder;
import com.intertek.entity.PriceBook;
import com.intertek.entity.ReferenceField;
import com.intertek.entity.ServiceLocation;
import com.intertek.entity.ShippingAgent;
import com.intertek.entity.TowingCompany;
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
import com.intertek.util.JobUtil;
import com.intertek.util.SecurityUtil;
import com.intertek.util.StringUtil;

public class ViewJobOrderMarineFormController extends CommonJobOrder{
	public ViewJobOrderMarineFormController() {
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
		Date dtUpdate = new Date();
		String jobNumber = null;
		TowingCompany towingCompany = jobOrder.getTowingCompany();
		ShippingAgent shippingAgent = jobOrder.getShippingAgent();
		ServiceLocation serviceLocation = jobOrder.getServiceLocation();

		JobService jobService = (JobService) ServiceLocator.getInstance().getBean("jobService");
		CustomerService customerService = (CustomerService) ServiceLocator.getInstance().getBean("customerService");
		TowingCompanyService towingCompanyService = (TowingCompanyService) ServiceLocator.getInstance().getBean("towingCompanyService");
		ShippingAgentService shippingAgentService = (ShippingAgentService) ServiceLocator.getInstance().getBean("shippingAgentService");
		ServiceLocationService serviceLocationService = (ServiceLocationService) ServiceLocator.getInstance().getBean("serviceLocationService");
		
		if ("towingCompany".equals(addJobOrder.getTowingCompFlag())) {
			String towName=jobOrder.getTowingCompany().getName();
			try{
				long l=Long.parseLong(towName);
				Long id= Long.valueOf(l);
				towingCompany = towingCompanyService.getTowingCompanyById(id);
				if (towingCompany != null) {
					jobOrder.setTowingCompany(towingCompany);
					addJobOrder.setTowingCompFlag("none");
					addJobOrder.setTabName("0");
					return showForm(request, response, errors);
				}
			}catch(Exception e){
				e.printStackTrace();

			}
		}
		if ("shippingAgent".equals(addJobOrder.getShippingAgentFlag())) {
			String shipName=jobOrder.getShippingAgent().getName();
			try{
				long l=Long.parseLong(shipName);
				Long id= Long.valueOf(l);
				shippingAgent = shippingAgentService.getShippingAgentById(id);
				if (shippingAgent != null) {
					jobOrder.setShippingAgent(shippingAgent);
					addJobOrder.setShippingAgentFlag("none");
					addJobOrder.setTabName("0");
					return showForm(request, response, errors);
				}
			}catch(Exception e){
				e.printStackTrace();

			}
		}
		if ("servicelocation".equals(addJobOrder.getServiceLocationFlag())) {
			serviceLocation = serviceLocationService.getServiceLocationByServiceLocationCode(jobOrder.getServiceLocation().getName());
			if (serviceLocation != null) {

				jobOrder.setServiceLocation(serviceLocation);
				addJobOrder.setServiceLocationFlag("none");
				addJobOrder.setTabName("0");
				return showForm(request, response, errors);
			}
		}
		String sphone=request.getParameter("shipAgentPhoneFlag");
		String tphone=request.getParameter("towCoPhoneFlag");
		if(!tphone.equals(""))
			jobOrder.getTowingCompany().setPhone(tphone);
		if(!sphone.equals(""))
			jobOrder.getShippingAgent().setPhone(sphone);

		if(sphone.equals(""))
		{
			jobOrder.getShippingAgent().setPhone("");
		}
		if(tphone.equals(""))
		{
			jobOrder.getTowingCompany().setPhone("");
		}

		System.out.println("Updated Date" + dtUpdate);
		jobOrder.setUpdateTime(dtUpdate);
		String searchFiled = addJobOrder.getSearchField();
		String searchString = addJobOrder.getSearchString();
		if (searchString != null && !searchString.trim().equals(""))
		{
			if(  searchFiled.equals("1"))
			{
				jobOrder = jobService.getJobOrderByJobNumber(searchString);

				if (jobOrder != null) {
					if(jobOrder.getJobStatus().equals(Constants.OPEN_STATUS)||jobOrder.getJobStatus().equals(Constants.REOPENED_STATUS))
						return new ModelAndView(new RedirectView("edit_job_entry_marine.htm"), "jobNumber", searchString);
					else
						return new ModelAndView(new RedirectView("view_job_entry_marine.htm"), "jobNumber", searchString);
				} else {
					System.out.println("job number not found");
					errors.reject("search.job.record.error",
							new Object[] { "no job records exists for this job number : "+ searchString, }, null);
					return showForm(request, response, errors);
				}
			}
		}
		//Code for 'Add Customers' tab

		String indexs = request.getParameter("jobIndex");
		String uniqueFlag=request.getParameter("uniqueFlag");

		if("uniqueFlag".equals(uniqueFlag))
		{
			String pageNumberStr = request.getParameter("pageNumber");
			String inputFieldId = request.getParameter("inputFieldId");
			String concode=request.getParameter("contractCode.value");

			ContractSearch search =new ContractSearch();
			search.setInputFieldId(inputFieldId);
			StringSearchField stringSearchField=new StringSearchField();
			stringSearchField.setValue(concode);
			search.setContractCode(stringSearchField);

			int pageNumber = 1;
			try
			{  pageNumber = new Integer(pageNumberStr).intValue();   }
			catch(Exception e)
			{    }
			if(pageNumber <= 0) pageNumber = 1;
			Pagination pagination = new Pagination(1, 5, pageNumber, 10);
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
			if(search.getResults().size()!=0)
			{
				Iterator iter = search.getResults().iterator();
				ContractCustContact contractCustContact=(ContractCustContact) iter.next();
				for(int i=0;i<search.getResults().size();i++)
				{
					int count=0;
					int custcount=0;
					int contcount=0;
					for(int l=0;l<search.getResults().size();l++)
					{
						if(((ContractCustContact)search.getResults().get(i)).getContractCust().getContract().getContractCode().equals(((ContractCustContact)search.getResults().get(l)).getContractCust().getContract().getContractCode()))
						{
							count=count+1;
						}
						if(((ContractCustContact)search.getResults().get(i)).getContractCust().getCustomer().getCustCode().equals(((ContractCustContact)search.getResults().get(l)).getContractCust().getCustomer().getCustCode()))
						{
							custcount=custcount+1;
						}

						String fisstring=String.valueOf(((ContractCustContact)search.getResults().get(i)).getContact().getId());
						String secstring=String.valueOf(((ContractCustContact)search.getResults().get(l)).getContact().getId());
						if(fisstring.equals(secstring))
						{
							contcount=contcount+1;
						}

					}//second for
					if((count>1)||(custcount>1)||(contcount>1))
					{
						addJobOrder.setPopFlag("true");
						addJobOrder.setUniqueFlag("none");
						addJobOrder.setTabName("1");
						return showForm(request, response, errors);
					}
					else
					{
						String cCode=((ContractCustContact)search.getResults().get(i)).getContractCust().getContract().getContractCode()+","+((ContractCustContact)search.getResults().get(i)).getContractCust().getCustomer().getCustCode()+","+((ContractCustContact)search.getResults().get(i)).getContact().getId();
						JobContract jobContract=new JobContract();
						String rowNum = request.getParameter("rowNum");
						contractCustContact=jobService.getContractDetailsByCode(cCode);
						ContactCust contactCust=new ContactCust();
						contactCust=jobService.getBillingContactByContactId(contractCustContact.getContact().getId(),contractCustContact.getContractCust().getCustomer().getCustCode());

						jobContract.setLocationDesc(" ");
						jobContract.setJobContractStatus(Constants.JOBCON_OPEN_STATUS);
						CfgContract cfgContract=new CfgContract();
						Date asOfDate = null;
						if(jobOrder.getJobFinishDate()!=null )
						{
							asOfDate = jobOrder.getJobFinishDate();
						}
						else if(jobOrder.getEtaDate()!=null )
						{
							asOfDate = jobOrder.getEtaDate();
						}
						else
						{
							asOfDate = new Date();
						}

						String zonedes="";
						String pricebookId="";
						cfgContract =jobService.getPriceBookByContractCode(contractCustContact.getContractCust().getContract().getContractCode(),asOfDate); 
						PriceBook priceBook=new PriceBook();
						if(cfgContract!=null)
						{
							if(!cfgContract.getPriceBookId().equalsIgnoreCase("CURRENT"))
							{
								pricebookId=cfgContract.getPriceBookId();
								//   zonedes= jobService.getZoneByBranchCodeandContractCode(jobOrder.getBranchName(),contractCustContact.getContractCust().getContract().getContractCode(),cfgContract.getPriceBookId(),asOfDate);
								System.out.println("in if looop of current is "+cfgContract.getPriceBookId());
							}
							else
							{
								priceBook=jobService.getPriceBookIdByCurrencyandSeries(cfgContract.getCurrencyCD(),cfgContract.getPbSeries(),asOfDate);
								if(priceBook!=null)
								{ 
									pricebookId=priceBook.getPriceBookId().getPriceBookId();
									System.out.println("in else loop"+priceBook.getPriceBookId().getPbSeries());
									// zonedes= jobService.getZoneByBranchCodeandContractCode(jobOrder.getBranchName(),contractCustContact.getContractCust().getContract().getContractCode(),priceBook.getPriceBookId().getPriceBookId(),asOfDate);
								}
							}
						}

						zonedes= jobService.getZoneByBranchCodeandContractCode(jobOrder.getBranchName(),contractCustContact.getContractCust().getContract().getContractCode(),pricebookId,asOfDate);
						if(zonedes!=null&&!zonedes.trim().equals(""))
						{jobContract.setZone(zonedes);}else{jobContract.setZone("NONE");}
						List referenceFields=jobService.getReferenceFieldsByContractCode(contractCustContact.getContractCust().getContract().getContractCode());

						// CfgContract cfgContract=new CfgContract();
						//sheidacfgContract =jobService.getPriceBookByContractCode(contractCustContact.getContractCust().getContract().getContractCode(),asOfDate);
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
						jobContract.setJobOrder(jobOrder);
						jobContract.setCustCode(contractCustContact.getContractCust().getCustomer().getCustCode());
						jobContract.setContractCode(contractCustContact.getContractCust().getContract().getContractCode());
						jobContract.setContactId(contractCustContact.getContact().getId());
						addJobOrder.setAddJobContracts(addJobContractViewOnly(addJobOrder.getAddJobContracts(),contractCustContact,jobContract,cfgContract,contactCust));
					}//else

				}//first for(i);

				addJobOrder.setUniqueFlag("none");
				addJobOrder.setPopFlag("none");
				addJobOrder.setJobFlag("none");
				addJobOrder.setTabName("1");
				return showForm(request, response, errors);
			}
			else
			{
				addJobOrder.setUniqueFlag("none");
				addJobOrder.setPopFlag("none");
				addJobOrder.setJobFlag("none");
				addJobOrder.setTabName("1");
				errors.reject("search.string.is.not.available",new Object[] {}, null);
				return showForm(request, response, errors);
			}//if
		}//if


		String jobFlag=request.getParameter("jobFlag");
		System.out.println("job flag is "+jobFlag);
		if("jobval".equals(jobFlag))
		{
			String cCode=request.getParameter("inputFieldIdValue");
			JobContract jobContract=new JobContract();
			String rowNum = request.getParameter("rowNum");
			ContractCustContact contractCustContact=new ContractCustContact();
			contractCustContact=jobService.getContractDetailsByCode(cCode);
			ContactCust contactCust=new ContactCust();
			contactCust=jobService.getBillingContactByContactId(contractCustContact.getContact().getId(),contractCustContact.getContractCust().getCustomer().getCustCode());
			jobContract.setLocationDesc(" ");
			jobContract.setJobContractStatus(Constants.JOBCON_OPEN_STATUS);
			CfgContract cfgContract=new CfgContract();
			Date asOfDate = null;
			if(jobOrder.getJobFinishDate()!=null )
			{
				asOfDate = jobOrder.getJobFinishDate();
			}
			else if(jobOrder.getEtaDate()!=null )
			{
				asOfDate = jobOrder.getEtaDate();
			}
			else
			{
				asOfDate = new Date();
			}

			String zonedes="";
			String pricebookId="";
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

			//CfgContract cfgContract=new CfgContract();
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
			jobContract.setJobOrder(jobOrder);
			jobContract.setCustCode(contractCustContact.getContractCust().getCustomer().getCustCode());
			jobContract.setContractCode(contractCustContact.getContractCust().getContract().getContractCode());
			jobContract.setContactId(contractCustContact.getContact().getId());
			addJobOrder.setAddJobContracts(addJobContractViewOnly(addJobOrder.getAddJobContracts(),contractCustContact,jobContract,cfgContract,contactCust));
			addJobOrder.setJobFlag("none");
			addJobOrder.setTabName("1");
			addJobOrder.setUniqueFlag("none");
			addJobOrder.setPopFlag("none");

			return showForm(request, response, errors);
		}

		String deleteFlag=request.getParameter("deleteFlag");
		if("delval".equals(deleteFlag))
		{
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
				//jobService.deleteJobNoteInsp(jobContractNote);
				jobService.deleteJobContractNotesByJobContract(jobContract.getId());
				jobService.deleteJobInsp(jobContract);
				addJobOrder.setAddJobContracts(deleteJobContract(addJobOrder.getAddJobContracts(),addJobOrder.getJobIndex()));
			}
			else
			{
				addJobOrder.setAddJobContracts(deleteJobContract(addJobOrder.getAddJobContracts(),addJobOrder.getJobIndex()));
			}
			addJobOrder.setDeleteFlag("none");
			addJobOrder.setTabName("1");
			return showForm(request, response, errors);
		}


		String contactFlag=request.getParameter("contactFlag");
		if("contFlag".equals(contactFlag))
		{
			System.out.println("contact flag is"+contactFlag);
			String rowNo=request.getParameter("contactIndex");
			int num=Integer.parseInt(rowNo);
			System.out.println("row number in create job order insp form controller is"+num);
			AddJobContract[] jobContractItems = addJobOrder.getAddJobContracts();
			AddJobContract addJobContract=jobContractItems[num];
			JobContract jobContract=addJobContract.getJobContract();
			long contactId=jobContractItems[num].getJobContract().getBillingContact().getId();
			String custCode=jobContractItems[num].getJobContract().getCustCode();
			ContactCust contactCust=new ContactCust();
			contactCust=jobService.getBillingContactByContactId(contactId,custCode);
			if (contactCust != null){
				String contactName=contactCust.getContact().getFirstName()+","+contactCust.getContact().getLastName();
				Set st =contactCust.getCustAddrSeq().getCustAddresses();
				Iterator itr =st.iterator();
				CustAddress  custAddress=( CustAddress)itr.next();
				jobContract.setBillingContactName(contactName);
				jobContract.setBillingContact(contactCust.getContact());
				addJobContracts[num].setBillingAddress(getContactAddress(custAddress));
			}
			addJobContracts[num].setJobContract(jobContract);
			addJobOrder.setContactFlag("none");
			addJobOrder.setTabName("1");
			return showForm(request, response, errors);
		}

		String bankFlag=request.getParameter("bankFlag");
		if("bankFlag".equals(bankFlag))
		{
			System.out.println("bank flag is "+bankFlag);
			String rowNo=request.getParameter("bankIndex");
			int num=Integer.parseInt(rowNo);
			AddJobContract[] jobContractItems = addJobOrder.getAddJobContracts();
			AddJobContract addJobContract=jobContractItems[num];
			JobContract jobContract=addJobContract.getJobContract();
			//String currency=jobContractItems[num].getCfgContract().getCurrencyCD();
			String currency=jobContract.getTransactionCurrencyCd();
			System.out.println("currency in bank flag loop is "+currency);
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
			addJobOrder.setTabName("1");
			return showForm(request,response,errors);
		}

		//Code for 'Add Customers' tab ends
		String nextFlag = addJobOrder.getNextPageFlag();


		AddJobContract[] jobContractItem = addJobOrder.getAddJobContracts();
		if(jobContractItem!=null)
		{
			for(int i=0; i< jobContractItem.length; i++)
			{
				AddJobContract addJobContract=jobContractItem[i];
				JobContract jobContract=addJobContract.getJobContract();
				
				if(jobContract.getBillingContact().getId() > 0)
				{
					Contact existingContact = customerService.getContactByIdandCustCode(Long.valueOf(jobContract.getBillingContact().getId()),jobContract.getCustCode());
					if(existingContact == null)
					{
						errors.reject("edit.job.order.error",new Object[] {"Invalid Contact id : "+ jobContract.getBillingContact().getId()+" for selected customer code "+jobContract.getCustCode()}, null);
						addJobOrder.setTabName("1");
						return showForm(request, response, errors);
					}
					else
						jobContract.setBillingContact(existingContact);
				}
			}
		}
		try {
			System.out.println("joborder saving");
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
				System.out.println("MarinJobNumber="+jobNumber);

				try
				{
					if(request.getSession() != null)
					{
						JobSearch jobSearch=(JobSearch)request.getSession().getAttribute("MySearchJobOrder");
						jobOrder=jobService.getNextOrPreviousJobNumber(jobSearch,jobNumber,nextList);
					}
				}catch(Exception e){
					errors.reject("search.job.order.error", new Object[] { e.getMessage() }, null);
					return showForm(request, response, errors);
				}
				// START : #119240
				// String url=jobService.getUrlHrefJobTypeByJobOrder(jobOrder)+"?jobNumber=";
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
				System.out.println("MarinJobNumber="+jobNumber);
				try
				{
					if(request.getSession() != null)
					{
						JobSearch jobSearch=(JobSearch)request.getSession().getAttribute("MySearchJobOrder");
						jobOrder=jobService.getNextOrPreviousJobNumber(jobSearch,jobNumber,prevList);
					}
				}catch(Exception e){
					errors.reject("search.job.order.error", new Object[] { e.getMessage() }, null);
					return showForm(request, response, errors);
				}
				// START : #119240
				// String url=jobService.getUrlHrefJobTypeByJobOrder(jobOrder)+"?jobNumber=";
				String url=jobService.getUrlByCurrPageIdentifier(jobOrder, addJobOrder.getCurrPageIdentifier())+"?jobNumber=";
				// END : #119240
				
				return new ModelAndView(new RedirectView(url+jobOrder.getJobNumber()+"&reqFrom="+addJobOrder.getOriginateFromSearchJob()));

			}
			if(jobOrder.getTowingCompany() == null || jobOrder.getTowingCompany().getName() == null || jobOrder.getTowingCompany().getName().trim().equals(""))
				jobOrder.setTowingCompany(null);

			if(jobOrder.getShippingAgent() == null || jobOrder.getShippingAgent().getName() == null || jobOrder.getShippingAgent().getName().trim().equals(""))
				jobOrder.setShippingAgent(null);

			if(jobOrder.getServiceLocation() == null || jobOrder.getServiceLocation().getName() == null || jobOrder.getServiceLocation().getName().trim().equals(""))
				jobOrder.setServiceLocation(null);

			jobService.updateJobOrder(jobOrder);
		} catch (Exception e) {
			e.printStackTrace();

			errors.reject("edit.job.order.error", new Object[] { e.getMessage() }, null);
			return showForm(request, response, errors);
		}



		AddJobContract[] jobContractItems = addJobOrder.getAddJobContracts();
		if(jobContractItems!=null)
		{
			for(int i=0; i< jobContractItems.length; i++)
			{
				AddJobContract addJobContract=jobContractItems[i];
				JobContract jobContract=addJobContract.getJobContract();
				
				if(jobContract.getBillingContact().getId() > 0)
				{
					Contact existingContact = customerService.getContactByIdandCustCode(Long.valueOf(jobContract.getBillingContact().getId()),jobContract.getCustCode());
					if(existingContact == null)
					{
						errors.reject("create.job.order.error",new Object[] {"Invalid Contact id : "+ jobContract.getBillingContact().getId()+" for selected customer code "+jobContract.getCustCode()}, null);
						return showForm(request, response, errors);
					}
					else
						jobContract.setBillingContact(existingContact);
				}
				if(jobContract.getInvoiceDescr()=="" && jobOrder.getJobDescription()!=null)
				{
					jobContract.setInvoiceDescr(Constants.DESCR+" "+jobOrder.getJobDescription());
				}
				jobContract.setJobNumber(jobOrder.getJobNumber());
				
				jobContract.setUid20(DateUtil.getUniquetimeString()+i);
				if(jobContract.getNominationFlag()==null || (!jobContract.getNominationFlag()))
				{
					jobContract.setNominationFlag(false);
				}
				jobContract = jobService.saveJobContractInsp(jobContract);

			}
		}

		if(nextFlag != null && nextFlag.trim().equals("1"))
		{
			//Check if a Job Contract record is chosen before moving to the next page
			if(jobOrder.getJobStatus().equals(Constants.OPEN_STATUS)||jobOrder.getJobStatus().equals(Constants.REOPENED_STATUS))
				return new ModelAndView(new RedirectView("edit_job_operational_info_insp.htm"), "jobNumber", jobOrder.getJobNumber());
			else
				return new ModelAndView(new RedirectView("view_job_operational_info_insp.htm"), "jobNumber", jobOrder.getJobNumber());
			// return new ModelAndView(new RedirectView("view_job_operational_info.htm"), "jobNumber", jobOrder.getJobNumber());

		}
		String rebillFlag = addJobOrder.getRebillFlag();
		if(rebillFlag != null && rebillFlag.trim().equals("rebill"))
		{
			//Check if a Job Contract record is chosen before moving to the next page

			return new ModelAndView(new RedirectView("edit_job_invoice_preview.htm"), "jobNumber", jobOrder.getJobNumber());

		}

		ModelAndView modelAndView = new ModelAndView("common-message-r");
		System.out.println("tab name in edit job order marine form controller is "+addJobOrder.getTabName());
		if(jobOrder.getJobStatus().equals(Constants.OPEN_STATUS)||jobOrder.getJobStatus().equals(Constants.REOPENED_STATUS))
			return new ModelAndView(new RedirectView("edit_job_entry_marine.htm?jobNumber="+jobOrder.getJobNumber()+"&reqFrom="+addJobOrder.getOriginateFromSearchJob()+"&tabName="+addJobOrder.getTabName()));
		else
			return new ModelAndView(new RedirectView("view_job_entry_marine.htm?jobNumber="+jobOrder.getJobNumber()+"&reqFrom="+addJobOrder.getOriginateFromSearchJob()+"&tabName="+addJobOrder.getTabName()));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest,
	 *      org.springframework.web.bind.ServletRequestDataBinder)
	 */
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		System.out.println("dateformat in controller is " + dateFormat);
		dateFormat.setLenient(false);
		binder.registerCustomEditor(java.util.Date.class, null,
				new CustomDateEditor(dateFormat, true));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */

	protected boolean suppressValidation(HttpServletRequest request) {

		String shippingAgentFlag = request.getParameter("shippingAgentFlag");
		String towingCoFlag = request.getParameter("towingCompFlag");
		String serviceLcoationFlag = request.getParameter("serviceLocationFlag");
		String sphone=request.getParameter("shipAgentPhoneFlag");
		String tphone=request.getParameter("towCoPhoneFlag");

		if ((sphone != null)) {
			return true;
		}
		if ((tphone != null)) {
			return true;
		}
		String jobFlag = request.getParameter("jobFlag");

		System.out.println("suppressvalidation shippingAgentFlag :"+ shippingAgentFlag);
		if ((shippingAgentFlag != null) && ("shippingAgent".equals(shippingAgentFlag))) {
			return true;
		}
		if ((towingCoFlag != null) && ("towingCompany".equals(towingCoFlag))) {
			return true;
		}

		if ((serviceLcoationFlag != null) && ("servicelocation".equals(serviceLcoationFlag))) {
			return true;
		}
		if ((jobFlag != null) && ("job".equals(jobFlag))) {
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

		if ((towingCoFlag != null) && ("towingCompany".equals(towingCoFlag))) {
			return true;
		}
		String bankFlag=request.getParameter("bankFlag");
		if((bankFlag != null) &&("bankFlag".equals(bankFlag)))        {
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

	protected Object formBackingObject(HttpServletRequest request)	throws Exception {
		String jobNum = null;

		AddJobOrder addJobOrder = new AddJobOrder();

		JobOrder jobOrder = new JobOrder();
		String orginFromJobSearch=request.getParameter("reqFrom");


		Branch branch = new Branch();
		BusinessUnit bu = new BusinessUnit();
		branch.setBusinessUnit(bu);
		jobOrder.setBranch(branch);
		//jobOrder.setJobType(Constants.INSPECTION_JOBTYPE);

		JobService jobService = (JobService) ServiceLocator.getInstance().getBean("jobService");
		UserService userService = (UserService) ServiceLocator.getInstance().getBean("userService");
		CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");


		jobNum = request.getParameter("jobNumber");
		addJobOrder.setJobNumber(jobNum);
		String tabName=request.getParameter("tabName");
		System.out.println("tab name is "+tabName);
		System.out.println("Edit Request JobNumber=" + jobNum );
		try {
			if (jobNum != null && !jobNum.trim().equals("")) {

				System.out.println("getiing job record");
				jobOrder = jobService.getJobOrderByJobNumber(jobNum);
			}
			
			if(jobOrder.getShippingAgent()==null)
				jobOrder.setShippingAgent(new ShippingAgent());
			if(jobOrder.getTowingCompany()==null)
				jobOrder.setTowingCompany(new TowingCompany());
			if(jobOrder.getServiceLocation()==null)
				jobOrder.setServiceLocation(new ServiceLocation());
			addJobOrder.setJobOrder(jobOrder);

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
				if(jobOrder.getEtaTime().getMinutes() >=0 && jobOrder.getEtaTime().getMinutes() <=9)
					addJobOrder.setUiEtaTime(jobOrder.getEtaTime().getHours() + ":0"+ jobOrder.getEtaTime().getMinutes());
				else
					addJobOrder.setUiEtaTime(jobOrder.getEtaTime().getHours() + ":"+ jobOrder.getEtaTime().getMinutes());
			}

			addJobOrder.setJobOrder(jobOrder);
			//  addJobOrder.setTabName("0");
			
			// START : #119240
			addJobOrder.setCurrPageIdentifier(Constants.JOB_ENTRY_FORM);
			// END : #119240
			
			//  Setting next list and prev list values
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
					
					String jobNumber="";

					List jobSearchResults = jobSearch.getJobNumbers();
					if(jobSearchResults != null && jobSearchResults.size()>0)
					{
						jobNumber=(String)jobSearchResults.get((jobSearchResults.size()-1));
						if(jobOrder != null && jobOrder.getJobNumber()!= null && jobOrder.getJobNumber().trim().equals(jobNumber.trim()))
						{
							addJobOrder.setNextListFlag("false") ;
						}else
							addJobOrder.setNextListFlag("true"); 
						jobNumber  =(String)jobSearchResults.get(0);
						if(jobOrder != null && jobOrder.getJobNumber()!= null && jobOrder.getJobNumber().trim().equals(jobNumber.trim()))
						{
							addJobOrder.setPrevListFlag("false") ;
						}else
							addJobOrder.setPrevListFlag("true"); 
					}
				}else
				{
					addJobOrder.setNextListFlag("false") ;
					addJobOrder.setPrevListFlag("false") ;
				}
			}
			//end

			//Code for 'Add Customers' tab


			List jobContracts=jobService.getAllJobContractByJobNumber(jobNum);
			AddJobContract[] addJobContracts= new AddJobContract[jobContracts.size()];

			int jobContractCount = 0;
			if(jobContracts != null)
				jobContractCount = jobContracts.size();
			addJobOrder.setJobContractCount(jobContractCount);

			for(int i=0; i<jobContracts.size(); i++)
			{
				AddJobContract addJobContract = new AddJobContract();
				JobContract jobContract = (JobContract) jobContracts.get(i);
				if(jobContract.getJobContractStatus() == null || jobContract.getJobContractStatus().trim().equals(""))
				{  jobContract.setJobContractStatus(Constants.JOBCON_OPEN_STATUS);}
				ContractCustContact contractCustContact=new ContractCustContact();
				ContactCust contactCust=new ContactCust();
				contractCustContact=jobService.getContractDetailsByCode(jobContract.getContractCode(),jobContract.getCustCode(),jobContract.getContactId());
				jobContract.setContractCustContact(contractCustContact);
				Contact billingContact=new Contact();
				if(jobContract.getBillingContact() != null)
				{
					long contactId=jobContract.getBillingContact().getId();
					billingContact.setId(contactId);
					//  ContactCust contactCust=new ContactCust();
					contactCust=jobService.getBillingContactByContactId(contactId,jobContract.getCustCode());
					if(contactCust!=null)
					{
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

				if(contractCustContact != null && contractCustContact.getContractCust() != null && contractCustContact.getContractCust().getCustomer() != null)
					contactCust=jobService.getBillingContactByContactId(contractCustContact.getContact().getId(),contractCustContact.getContractCust().getCustomer().getCustCode());
				if(contactCust!=null)
				{
					Set st =contactCust.getCustAddrSeq().getCustAddresses();
					Iterator itr =st.iterator();
					if(itr.hasNext())
					{
						CustAddress  custAddress=( CustAddress)itr.next();
						String address=getContactAddress(custAddress);
						addJobContract.setSchedulerAddress(StringUtil.forHTML(address));
					}
				}
				else{ addJobContract.setSchedulerAddress("");}

				String currency=jobContract.getTransactionCurrencyCd();
				jobContract.setJobOrder(jobOrder);
				addJobContract.setJobContract(jobContract);
				CfgContract cfgContract=new CfgContract();
				cfgContract.setCurrencyCD(currency);
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

				cfgContract =jobService.getPriceBookByContractCode(jobContract.getContractCode(),asOfDate);
				addJobContract.setCfgContract(cfgContract);
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
		} catch(Throwable t)
		{
			t.printStackTrace();
			throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
		} 
		return addJobOrder;
	}
	
}
