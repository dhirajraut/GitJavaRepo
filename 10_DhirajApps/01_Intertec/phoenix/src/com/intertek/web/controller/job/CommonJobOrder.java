package com.intertek.web.controller.job;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.validation.BindException;

import com.intertek.domain.AddJobContract;
import com.intertek.domain.AddJobOrder;
import com.intertek.entity.CfgContract;
import com.intertek.entity.Contact;
import com.intertek.entity.ContactCust;
import com.intertek.entity.ContractCustContact;
import com.intertek.entity.Country;
import com.intertek.entity.CustAddress;
import com.intertek.entity.CustVatRegistration;
import com.intertek.entity.JobContract;
import com.intertek.entity.User;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.CountryService;
import com.intertek.service.CustomerService;
import com.intertek.service.JobService;
import com.intertek.service.UserService;
import com.intertek.util.InvoiceUtil;
import com.intertek.util.SecurityUtil;
import com.intertek.util.StringUtil;
import com.intertek.web.controller.BaseSimpleFormController;

public class CommonJobOrder extends BaseSimpleFormController {

	protected void populateBillingContact(AddJobOrder addJobOrder, JobService jobService,
			CountryService countryService, AddJobContract[] addJobContracts, String rowNo) {
		int num=Integer.parseInt(rowNo);
		AddJobContract[] jobContractItems = addJobOrder.getAddJobContracts();
		AddJobContract addJobContract=jobContractItems[num];
		JobContract jobContract=addJobContract.getJobContract();
		long contactId=jobContractItems[num].getJobContract().getBillingContact().getId();
		String custCode=jobContractItems[num].getJobContract().getCustCode();
		//ContactCust contactCust=new ContactCust();
		Contact billingContact=new Contact();
		ContactCust contactCust=jobService.getBillingContactByContactId(contactId,custCode);
		if(contactCust!=null)
		{
			String contactName=contactCust.getContact().getFirstName()+" "+contactCust.getContact().getLastName();
			Set st =contactCust.getCustAddrSeq().getCustAddresses();
			Iterator itr =st.iterator();
			CustAddress  custAddress=( CustAddress)itr.next();
			String address = getContactAddress(custAddress);

			jobContract.setCustLocationNumber(custAddress.getLocationNumber());
			jobContract.setBillingContactName(contactName);
			jobContract.setBillingContact(contactCust.getContact());
			addJobContracts[num].setBillingAddress(address);

			CustVatRegistration custVatRegistration=countryService.getCustomerVatRegistrationByCustCodeandCountryCode(custCode,custAddress.getCountry(), custAddress.getState());
			if(custVatRegistration!=null)
			{
				jobContract.setVatRegId(custVatRegistration.getCustVatRegistrationId().getRegistrationId());
				Country country = countryService.getCountryByCode(custAddress.getCountry());
				/*if(custVatRegistration.getRegistrationId()!=null && !custVatRegistration.getRegistrationId().trim().equals(""))
				{addJobContracts[num].setVatRateCountry(country.getCountry2()+" : "+custVatRegistration.getRegistrationId());}
				else{addJobContracts[num].setVatRateCountry(country.getCountry2());}*/
				if(custVatRegistration.getCustVatRegistrationId().getRegistrationId()!=null && !custVatRegistration.getCustVatRegistrationId().getRegistrationId().trim().equals(""))
				{

					if(country.getCountry2()!=null && !country.getCountry2().trim().equals(""))
					{
						addJobContracts[num].setVatRateCountry(country.getCountry2()+" : "+custVatRegistration.getCustVatRegistrationId().getRegistrationId());	
					}
					else
					{addJobContracts[num].setVatRateCountry(" : "+custVatRegistration.getCustVatRegistrationId().getRegistrationId());}
				}
				else
				{
					addJobContracts[num].setVatRateCountry(country.getCountry2()+" : ");
				}

				jobContract.setVatRegCountry(country);
			}  
			else{
				jobContract.setVatRegId("");
				jobContract.setVatRegCountry(null);
				addJobContracts[num].setVatRateCountry("");
			}
		}
		else
		{jobContract.setBillingContact(billingContact);
		}

		addJobContracts[num].setJobContract(jobContract);
	}

	protected String getContactAddress(CustAddress custAddress) {
		String address="";
		if(custAddress.getAddress1()!=null &&!custAddress.getAddress1().trim().equals(""))
		{address=custAddress.getAddress1();}

		if(custAddress.getAddress2()!=null &&!custAddress.getAddress2().trim().equals(""))
		{if(custAddress.getAddress1()!=null &&!custAddress.getAddress1().trim().equals(""))
		{
			address=address + "," +custAddress.getAddress2();
		}
		else{address=custAddress.getAddress2();}
		}

		if(custAddress.getAddress3()!=null &&!custAddress.getAddress3().trim().equals(""))
		{if(address!=null && !address.trim().equals(""))
		{address=address+ "," +custAddress.getAddress3();}
		else
		{address=custAddress.getAddress3();}
		}

		if(custAddress.getAddress4()!=null &&!custAddress.getAddress4().trim().equals(""))
		{if(address!=null && !address.trim().equals(""))
		{address=address+ "," +custAddress.getAddress4();}
		else
		{address=custAddress.getAddress4();}
		}

		if(custAddress.getCity()!=null &&!custAddress.getCity().trim().equals(""))
		{
			if(address!=null && !address.trim().equals(""))
			{address=address+ "," +custAddress.getCity();}
			else
			{address=custAddress.getCity();}
		}


		Country country=null;
		CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");
		if(custAddress.getCountry()!=null && !custAddress.getCountry().trim().equals("")){
			country=countryService.getCountryByCode(custAddress.getCountry());
		}

		if(custAddress.getState()!=null && !custAddress.getState().trim().equals("") && (country==null || (country!=null && country.getShowState())))
		{if(address!=null && !address.trim().equals(""))
		{address=address+ "," +custAddress.getState();}
		else
		{address=custAddress.getState();}
		}

		if(custAddress.getPostal()!=null &&!custAddress.getPostal().trim().equals(""))
		{if(address!=null && !address.trim().equals(""))
		{address=address+ "," +custAddress.getPostal();}
		else
		{address=custAddress.getPostal();}
		}

		if(custAddress.getCountry()!=null && !custAddress.getCountry().trim().equals(""))
		{
			if(country != null)
			{
				if(address!=null && !address.trim().equals(""))
				{address=address+ "," +country.getName();}
				else
				{address=country.getName();}
			}
			else
			{
				if(address.trim().equals(""))
					address = custAddress.getCountry();
				else
					address = address + " , " + custAddress.getCountry();
			}
		}
		return address;
	}

	protected AddJobContract[] addJobContract(AddJobContract[] addJobContracts, ContractCustContact contractCustContact, JobContract jobContract,
			CfgContract cfgContract, ContactCust contactCust, String pricebookId) {

		AddJobContract addJobContract = new AddJobContract();
		if(contractCustContact != null)
			jobContract.setContractCustContact(contractCustContact);
		if(contractCustContact != null)
			jobContract.setPymntTermsCd(contractCustContact.getContractCust().getContract().getPaymentTermsCD());
		jobContract.setBillingContact(new Contact());
		UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
		CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");
		User user=null;
		try{
			user=userService.getUserByName(SecurityUtil.getUser().getLoginName());
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
		if(user.getLanguage()!=null && !user.getLanguage().trim().equals(""))
		{jobContract.setLanguage(user.getLanguage());}
		else
		{jobContract.setLanguage("English");}

		if(user.getProductType()!=null && !user.getProductType().trim().equals(""))
		{jobContract.setProductType(user.getProductType());}
		else
		{jobContract.setProductType("Hydro");}

		if(contractCustContact != null && contractCustContact.getContractCust().getContract().getInvoiceType()!=null && !contractCustContact.getContractCust().getContract().getInvoiceType().trim().equals(""))
		{jobContract.setInvoiceType(contractCustContact.getContractCust().getContract().getInvoiceType());}
		else
		{jobContract.setInvoiceType("REG");}

		if(contractCustContact != null && contractCustContact.getContractCust().getCustomer().getPaymentType()!=null &&
				!contractCustContact.getContractCust().getCustomer().getPaymentType().trim().equals(""))
		{
			jobContract.setPymntType(contractCustContact.getContractCust().getCustomer().getPaymentType());}
		else
		{jobContract.setPymntType("CRE");}


		if(cfgContract != null)
		{
			addJobContract.setCfgContract(cfgContract);
			if(jobContract.getJobOrder() != null && jobContract.getJobOrder().getBranch()!=null && jobContract.getJobOrder().getBranch().getBusinessUnit()!=null)
				jobContract.setBaseCurrencyCd(jobContract.getJobOrder().getBranch().getBusinessUnit().getCurrencyBase());
			jobContract.setTransactionCurrencyCd(cfgContract.getCurrencyCD());
		}
		addJobContract.setPriceBookId(pricebookId);
		if(contactCust!=null)
		{
			Set st =contactCust.getCustAddrSeq().getCustAddresses();
			Iterator itr =st.iterator();
			CustAddress  custAddress=( CustAddress)itr.next();
			String address = getContactAddress(custAddress);	
			addJobContract.setSchedulerAddress(StringUtil.forHTML(address));
			
			String custCode = contractCustContact.getContractCust().getCustomer().getCustCode();
			Contact scheduler = contactCust.getContact();
			long schedulerId = scheduler.getId();
			defaultSchedulerAsBillingContact(jobContract, addJobContract,
					address, custCode, schedulerId);
			

			try{
				CustVatRegistration custVatRegistration=countryService.getCustomerVatRegistrationByCustCodeandCountryCode(custCode,custAddress.getCountry(), custAddress.getState());
				if(custVatRegistration!=null)
				{
					jobContract.setVatRegId(custVatRegistration.getCustVatRegistrationId().getRegistrationId());
					jobContract.setVatRegCountry(custVatRegistration.getCountry());
					Country country = countryService.getCountryByCode(custVatRegistration.getCustVatRegistrationId().getCountryCode());
					if(custVatRegistration.getCustVatRegistrationId().getRegistrationId()!=null && !custVatRegistration.getCustVatRegistrationId().getRegistrationId().trim().equals(""))
					{

						if(country.getCountry2()!=null && !country.getCountry2().trim().equals(""))
						{
							addJobContract.setVatRateCountry(country.getCountry2()+" : "+custVatRegistration.getCustVatRegistrationId().getRegistrationId());	
						}
						else
						{addJobContract.setVatRateCountry(" : "+custVatRegistration.getCustVatRegistrationId().getRegistrationId());}
					}
					else{addJobContract.setVatRateCountry(country.getCountry2()+" : ");}
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

		}
		if (jobContract.getJobContractStatus() != null){
		    addJobContract.setJobContractStatus(jobContract.getJobContractStatus());
		}
		addJobContract.setJobContract(jobContract); 

		AddJobContract[] results = null;

		if(addJobContracts == null) results = new AddJobContract[1];
		else results = new AddJobContract[addJobContracts.length + 1];
		if(addJobContracts != null)
		{
			for(int i=0; i<addJobContracts.length; i++)
			{
				results[i] = addJobContracts[i];
			}
			results[results.length - 1] = addJobContract;
		}
		return results;
	}

	private void defaultSchedulerAsBillingContact(JobContract jobContract,
			AddJobContract addJobContract, String address, String custCode,
			long schedulerId) {	
		JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");		
	//	List<Contact> contacts = jobService.getBillingContactByCustCode(custCode);		
		List<ContractCustContact> contacts = jobService.getBillingContactByCustCodeAndContractCode(custCode,jobContract.getContractCode());
		if (contacts.size()== 1){
			//Contact billingContact = contacts.get(0);
			ContractCustContact contractCustContact = contacts.get(0);
		    Contact billingContact=contractCustContact.getContact();
			jobContract.setBillingContact(billingContact);
			jobContract.setBillingContactName(billingContact.getFirstName() + " " + billingContact.getLastName());
			if (billingContact.getId() == schedulerId){
				addJobContract.setBillingSameAsScheduler(true);
				addJobContract.setBillingAddress(address);		
			}else{
				addJobContract.setBillingSameAsScheduler(false);
				ContactCust billingContactCust=jobService.getBillingContactByContactId(billingContact.getId(),custCode);
				if(billingContactCust!=null)
				{
					Iterator itr2 =billingContactCust.getCustAddrSeq().getCustAddresses().iterator();
					CustAddress  custAddress2=( CustAddress)itr2.next();
					String address2 = getContactAddress(custAddress2);
					addJobContract.setBillingAddress(address2);		
				}
			}
		}else if (contacts.size() > 1){
			for (Iterator iterator = contacts.iterator(); iterator.hasNext();) {
			//	Contact contact = (Contact) iterator.next();
				ContractCustContact contractCustContact = (ContractCustContact)iterator.next();
				//if (contact().getId()==schedulerId){
				if (contractCustContact.getContact().getId()==schedulerId){
					addJobContract.setBillingSameAsScheduler(true);
					break;
				}
			}
		}
	}

	protected AddJobContract[] deleteJobContract(AddJobContract[] addJobContracts, int indexs) {
		if(addJobContracts == null) return null;
		if(addJobContracts.length <= 0) return addJobContracts;
		AddJobContract[] results = new AddJobContract[addJobContracts.length - 1];
		int count = 0;
		for(int i=0; i<addJobContracts.length; i++)
		{
			if(i == indexs) continue;
			results[count] = addJobContracts[i];
			count ++;
		}
		return results;
	}

	protected void bindBillingContactInfo(AddJobContract addJobContract) {
		JobContract jobContract=addJobContract.getJobContract();
		if(jobContract.getBillingContact().getId() > 0)
		{
			CustomerService customerService = (CustomerService) ServiceLocator.getInstance().getBean("customerService");
			Contact existingContact = customerService.getContactByIdandCustCode(Long.valueOf(jobContract.getBillingContact().getId()),jobContract.getCustCode());
			if(existingContact != null)
			{
				jobContract.setBillingContact(existingContact);
				jobContract.setBillingContactName(existingContact.getFirstName() + " " + existingContact.getLastName());
				JobService jobService = (JobService) ServiceLocator.getInstance().getBean("jobService");
				ContactCust contactCust=jobService.getBillingContactByContactId(
						jobContract.getBillingContact().getId(), jobContract.getCustCode());
				if (contactCust != null){
					Set st =contactCust.getCustAddrSeq().getCustAddresses();
					Iterator itr =st.iterator();
					CustAddress  custAddress=( CustAddress)itr.next();
					addJobContract.setBillingAddress(getContactAddress(custAddress));
				}
			}
		}
	}

	protected void validateCustomerInfo(BindException errors, AddJobOrder addJobOrder, AddJobContract[] jobContractItem) {
		for(int i=0; i< jobContractItem.length; i++)
		{
			AddJobContract addJobContract=jobContractItem[i];
			JobContract jobContract=addJobContract.getJobContract();
	
			if(jobContract.getCustSentBy().trim().equals(""))
			{
				errors.reject("origin.error",new Object[] {jobContract.getContractCode()}, null);
			}
			if(jobContract.getCustRefNum().trim().equals(""))
			{
				errors.reject("curstrefnum.error",new Object[] {jobContract.getContractCode()}, null);
			}
			if(jobContract.getBillingContact().getId() <= 0)
			{
				errors.reject("billing.contact.contract.error",new Object[] {jobContract.getContractCode()}, null);
			}
			if(jobContract.getBillingContact().getId() > 0)
			{
				CustomerService customerService = (CustomerService) ServiceLocator.getInstance().getBean("customerService");
				Contact existingContact = customerService.getContactByIdandCustCode(Long.valueOf(jobContract.getBillingContact().getId()),jobContract.getCustCode());
				if(existingContact == null)
				{
					errors.reject("create.job.order.error",new Object[] {"Invalid Contact id : "+ jobContract.getBillingContact().getId()+" for selected customer code "+jobContract.getCustCode()}, null);
				}
			}
	
			if(jobContract.getBankCd().trim().equals("")&& jobContract.getBankAcctKey().trim().equals(""))
			{
				errors.reject("select.remit.accountkey.error",new Object[] {jobContract.getContractCode()}, null);
			}
	
			if(jobContract.getBankCd().trim().equals(""))
			{
				errors.reject("select.remitto.error",new Object[] {jobContract.getContractCode()}, null);
			}
			if(jobContract.getBankAcctKey().trim().equals(""))
			{
				errors.reject("select.bankaccount.key.error",new Object[] {jobContract.getContractCode()}, null);
			}
			//		      Validate bank details
			boolean bankValidateFlag = InvoiceUtil.validateBankDetails(jobContract);
			if(!bankValidateFlag)
			{
				errors.reject("invalid.bank.error", null, null);
				jobContract.setBankCd("");
				jobContract.setBankAcctKey("");
				addJobOrder.setTabName("1");
			}
		}
	}

	protected AddJobContract[] addJobContractViewOnly(AddJobContract[] addJobContracts, ContractCustContact contractCustContact,
			JobContract jobContract, CfgContract cfgContract, ContactCust contactCust) {

		AddJobContract addJobContract = new AddJobContract();
		if(contractCustContact != null)
			jobContract.setContractCustContact(contractCustContact);
		Contact billingContact=new Contact();
		jobContract.setBillingContact(billingContact);
		if(contractCustContact != null)
			jobContract.setPymntTermsCd(contractCustContact.getContractCust().getContract().getPaymentTermsCD());
		addJobContract.setJobContract(jobContract);
		if(cfgContract != null)
			addJobContract.setCfgContract(cfgContract);
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


		AddJobContract[] results = null;

		if(addJobContracts == null) results = new AddJobContract[1];
		else results = new AddJobContract[addJobContracts.length + 1];
		if(addJobContracts != null)
		{
			for(int i=0; i<addJobContracts.length; i++)
			{
				results[i] = addJobContracts[i];
			}
			results[results.length - 1] = addJobContract;
		}
		return results;
	}


}

