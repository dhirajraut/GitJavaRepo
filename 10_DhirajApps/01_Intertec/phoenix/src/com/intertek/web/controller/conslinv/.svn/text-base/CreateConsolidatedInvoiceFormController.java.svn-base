package com.intertek.web.controller.conslinv;

import java.text.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.intertek.entity.*;
import com.intertek.service.*;
import com.intertek.util.*;
import com.intertek.web.controller.BaseSimpleFormController;
import com.intertek.locator.*;
import com.intertek.domain.*;

//public class CreateConsolidatedInvoiceFormController extends SimpleFormController
public class CreateConsolidatedInvoiceFormController extends BaseSimpleFormController
{
  public CreateConsolidatedInvoiceFormController() {
		super();
		setSessionForm(true);
		setCommandClass(AddConsolidatedInvoice.class);
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

	  AddConsolidatedInvoice addConsolidatedInvoice = (AddConsolidatedInvoice) command;
	  ConsolidatedInvoice consolidatedInvoice = addConsolidatedInvoice.getConsolidatedInvoice();
	  
	  JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");	
	  PrebillService prebillService = (PrebillService)ServiceLocator.getInstance().getBean("prebillService");
	  CustomerService customerService = (CustomerService)ServiceLocator.getInstance().getBean("customerService");	
	  ConsolidatedInvoiceService consolidatedInvoiceService = (ConsolidatedInvoiceService)ServiceLocator.getInstance().getBean("consolidatedInvoiceService");
	  UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
	  
	  
	  //Prepare invoice search object
	  InvoiceSearch invoiceSearch = new InvoiceSearch();
	  //invoiceSearch.setBillStatus(consolidatedInvoice.getBillStatus());
	  //invoiceSearch.setBuName(consolidatedInvoice.getConsolidatedInvoiceId().getBuName());
	  invoiceSearch.setCurrency(consolidatedInvoice.getCurrencyCd());
	  invoiceSearch.setCustCode(consolidatedInvoice.getCustCode());
	  invoiceSearch.setInvoiceType("CON");
	  
	  invoiceSearch.setInvoiceVatProvince(consolidatedInvoice.getVatProvince());
	  if(addConsolidatedInvoice.getConsolidatedInvoice().getFromDt() != null && (!addConsolidatedInvoice.getConsolidatedInvoice().getFromDt().toString().trim().equals("")))
	  {
		  invoiceSearch.setFromDate(addConsolidatedInvoice.getConsolidatedInvoice().getFromDt());
	  }
	  if(addConsolidatedInvoice.getConsolidatedInvoice().getToDt() != null && (!addConsolidatedInvoice.getConsolidatedInvoice().getToDt().toString().trim().equals("")))
	  {
		  invoiceSearch.setToDate(addConsolidatedInvoice.getConsolidatedInvoice().getToDt());
	  }
	  

	  
	  String busFlag = addConsolidatedInvoice.getBusFlag();
	  String custFlag = addConsolidatedInvoice.getCustFlag();
	  String attentionFlag = addConsolidatedInvoice.getAttentionFlag();
	  String currencyFlag = addConsolidatedInvoice.getCurrencyFlag();
	  String billStatusFlag = addConsolidatedInvoice.getBillStatusFlag();
	  String bankFlag = addConsolidatedInvoice.getBankFlag();
	  String chosenInvoices = addConsolidatedInvoice.getChosenInvoices();
	  String tabName = addConsolidatedInvoice.getTabName();
	  String dateFlag = addConsolidatedInvoice.getDateFlag();
	  String saveFlag = addConsolidatedInvoice.getSaveFlag();
	  String generateInvFlag = addConsolidatedInvoice.getGenerateInvFlag();
	 
	  //Setting Attach/detach tab header sort order
	  String sortFlag = addConsolidatedInvoice.getSortFlag();
	  String sortOrderFlag = "";
	  if(sortFlag != null && !sortFlag.trim().equals("")){
		  addConsolidatedInvoice.setCurrentSortFlag(sortFlag);
		  if(addConsolidatedInvoice.getCurrentSortFlag()!= null 
				  && addConsolidatedInvoice.getCurrentSortFlag().trim().equals(addConsolidatedInvoice.getPrevSortFlag())){
			  sortOrderFlag = "desc";
			  addConsolidatedInvoice.setPrevSortFlag("");
		  } else {
			  addConsolidatedInvoice.setPrevSortFlag(sortFlag);
			  sortOrderFlag = "asc";
		  }
	 } else {
		  addConsolidatedInvoice.setCurrentSortFlag("");
		  addConsolidatedInvoice.setPrevSortFlag("");
	 }
	//End
	  if(busFlag != null && (!busFlag.trim().equals("")) && (busFlag.trim().equals("selectBU")))
	  {
		  String buName = addConsolidatedInvoice.getConsolidatedInvoice().getConsolidatedInvoiceId().getBuName();
		  BusinessUnit bu = userService.getBusinessUnitByName(buName);
		 
		  // vat province
		  if((bu != null && bu.getCountry()!= null) && (bu.getCountry().getSameProvinceValidation()!= null && 
				  bu.getCountry().getSameProvinceValidation()!= false))
		  { 
			  addConsolidatedInvoice.setVatCountryFlag("true");
		  } else
		  {
			  addConsolidatedInvoice.setVatCountryFlag("false");
		  }
		  //end
		  if(bu == null)
		  {
			  errors.reject("search.businessunit.error", new Object[] {"BU does not exist :"+buName}, null);
		      return showForm(request, response, errors);
		  }
		  
		  String curencyCd = bu.getCurrencyBase();
		  addConsolidatedInvoice.getConsolidatedInvoice().setCurrencyCd(curencyCd);
		  invoiceSearch.setCurrency(curencyCd);
		  addConsolidatedInvoice.getConsolidatedInvoice().setBankCd("");
		  addConsolidatedInvoice.getConsolidatedInvoice().setBankAcctKey("");
		  		  
		  List banks = jobService.getBankCodebyBunameandCurrency(buName,curencyCd);
		  if(banks != null && banks.size() > 0)
		  {
			  if(banks.size() == 1)
			  {
				  Bank bank = (Bank) banks.get(0);
				  if(bank != null)
				  {
					  addConsolidatedInvoice.getConsolidatedInvoice().setBankCd(bank.getBankCode());
					  
					  //Get the bank accounts for this bank
					  List bankAccts = jobService.getBankAccountByBankCodeAndCurrency(buName, curencyCd, bank.getBankCode());
					  if(bankAccts != null && bankAccts.size() > 0)
					  {
						  if(bankAccts.size() == 1)
						  {
							  BankAccount bankAccount = (BankAccount) bankAccts.get(0);
							  if(bankAccount != null)
							  {
								  addConsolidatedInvoice.getConsolidatedInvoice().setBankAcctKey(bankAccount.getBankAccountId().getBankAcctCode());
							  }
						  }
					  }
				  }
			  }
		  }
		  addConsolidatedInvoice.setBusFlag("none");
		  // invoiceSearch = consolidatedInvoiceService.searchInvoice(invoiceSearch);
		  addConsolidatedInvoice.setInvoiceSearchCriteria(invoiceSearch);
		  invoiceSearch = consolidatedInvoiceService.searchInvoice(invoiceSearch,sortFlag,sortOrderFlag);
		  addConsolidatedInvoice.setInvoiceSearch(invoiceSearch);
		  return showForm(request, response, errors);
	  }
	  if(custFlag != null && (!custFlag.trim().equals("")) && (custFlag.trim().equals("selectCustomer")))
	  {
		  String custCode = addConsolidatedInvoice.getConsolidatedInvoice().getCustCode();
		  Customer customer = customerService.getCustomerByCustCode(custCode);
		  System.out.println("after getting the customer");
		  addConsolidatedInvoice.setCustomer(customer);
		  if(customer != null)
		  {
			  System.out.println("tring to get cust addr seq");
			  CustAddrSeq custAddrSeq = customerService.getCustAddrSeqByLocationNumber(customer.getPrimaryBillToAddress(),custCode);
			  addConsolidatedInvoice.setCustAddrSeq(custAddrSeq);
			  addConsolidatedInvoice.getConsolidatedInvoice().setLocationNumber(customer.getPrimaryBillToAddress());
			  if(custAddrSeq != null)
			  {
				  List custAddresses = customerService.getCustAddressDetailsBySeqNumber(custAddrSeq.getCustAddrSeqId().getLocationNumber(), custCode);
				  if(custAddresses != null && custAddresses.size()>0)
				  {
					  CustAddress custAddress = (CustAddress) custAddresses.get(0);
					  addConsolidatedInvoice.setCustAddress(custAddress);
					  
				  }
			  }
		  }
		  else
		  {
			     errors.reject("search.customer.error", new Object[] {"Customer does not exist :"+custCode}, null);
			      return showForm(request, response, errors);
		  }
				
		  addConsolidatedInvoice.setCustFlag("none");
		  //invoiceSearch = consolidatedInvoiceService.searchInvoice(invoiceSearch);
		  addConsolidatedInvoice.setInvoiceSearchCriteria(invoiceSearch);
		  invoiceSearch = consolidatedInvoiceService.searchInvoice(invoiceSearch,sortFlag,sortOrderFlag);
		  addConsolidatedInvoice.setInvoiceSearch(invoiceSearch);
		  addConsolidatedInvoice.setTabName("0");
		  return showForm(request, response, errors);		  
	  }
	  if(attentionFlag != null && (!attentionFlag.trim().equals("")) && (attentionFlag.trim().equals("selectContact")))
	  {
		  String locationNumber = addConsolidatedInvoice.getAttentionLocationNumber();
		  String contactId = addConsolidatedInvoice.getContactId();
		  System.out.println("location number :"+locationNumber);
		  System.out.println("contactId :"+contactId);
		  List contactCusts = customerService.getContactCustsByCustCode(addConsolidatedInvoice.getConsolidatedInvoice().getCustCode());
		  
		  if(contactCusts != null && contactCusts.size() > 0)
		  {
			  for(int i=0;i<contactCusts.size();i++)
			  {
				  ContactCust contactCust = (ContactCust) contactCusts.get(i);
				  
					  if(new Long(contactCust.getContactCustId().getContactId()).toString().trim().equals(contactId))
					  {
						  System.out.println("match found :"+contactId);
						  
						  addConsolidatedInvoice.getConsolidatedInvoice().setCustomer(customerService.getCustomerByCustCode(addConsolidatedInvoice.getConsolidatedInvoice().getCustCode()));
						  Contact existingContact = customerService.getContactById(new Long(contactId));
						  
						  if(existingContact!=null)
							  addConsolidatedInvoice.getConsolidatedInvoice().setContact(existingContact);
						  else
							  addConsolidatedInvoice.getConsolidatedInvoice().setContact(null);
						  
						  addConsolidatedInvoice.getConsolidatedInvoice().setCustAddrSeq(customerService.getCustAddrSeqByLocationNumber(contactCust.getContactCustId().getLocationNumber(), addConsolidatedInvoice.getConsolidatedInvoice().getCustCode()));
						  addConsolidatedInvoice.setContact(addConsolidatedInvoice.getConsolidatedInvoice().getContact());
						  addConsolidatedInvoice.getConsolidatedInvoice().setLocationNumber(contactCust.getContactCustId().getLocationNumber());
						  
						  addConsolidatedInvoice.setAttentionLocationNumber(contactCust.getContactCustId().getLocationNumber().toString());
						  List custAddresses = customerService.getCustAddressDetailsBySeqNumber(contactCust.getContactCustId().getLocationNumber(), addConsolidatedInvoice.getConsolidatedInvoice().getCustCode());
						  if(custAddresses != null && custAddresses.size()>0)
						  {
							  CustAddress custAddress = (CustAddress) custAddresses.get(0);
							  addConsolidatedInvoice.setCustAddress(custAddress);						  
						  }
						  break;
					  }
				  
			  }
		  }
		  addConsolidatedInvoice.setAttentionFlag("none");
		  //invoiceSearch = consolidatedInvoiceService.searchInvoice(invoiceSearch);
		  addConsolidatedInvoice.setInvoiceSearchCriteria(invoiceSearch);
		  invoiceSearch = consolidatedInvoiceService.searchInvoice(invoiceSearch,sortFlag,sortOrderFlag);
		  addConsolidatedInvoice.setInvoiceSearch(invoiceSearch);
		  addConsolidatedInvoice.setTabName("1");
		  return showForm(request, response, errors);	
	  }
	  if(currencyFlag != null && (!currencyFlag.trim().equals("")) && (currencyFlag.trim().equals("selectCurrency")))
	  {
		  addConsolidatedInvoice.getConsolidatedInvoice().setBankCd("");
		  addConsolidatedInvoice.getConsolidatedInvoice().setBankAcctKey("");
		  List banks = jobService.getBankCodebyBunameandCurrency(consolidatedInvoice.getConsolidatedInvoiceId().getBuName(),consolidatedInvoice.getCurrencyCd());
		  if(banks != null && banks.size() > 0)
		  {
			  if(banks.size() == 1)
			  {
				  Bank bank = (Bank) banks.get(0);
				  if(bank != null)
				  {
					  addConsolidatedInvoice.getConsolidatedInvoice().setBankCd(bank.getBankCode());
					  
					  //Get the bank accounts for this bank
					  List bankAccts = jobService.getBankAccountByBankCodeAndCurrency(consolidatedInvoice.getConsolidatedInvoiceId().getBuName(),consolidatedInvoice.getCurrencyCd(), bank.getBankCode());
					  if(bankAccts != null && bankAccts.size() > 0)
					  {
						  if(bankAccts.size() == 1)
						  {
							  BankAccount bankAccount = (BankAccount) bankAccts.get(0);
							  if(bankAccount != null)
							  {
								  addConsolidatedInvoice.getConsolidatedInvoice().setBankAcctKey(bankAccount.getBankAccountId().getBankAcctCode());
							  }
						  }
					  }
				  }
			  }
		  }
		  
		  addConsolidatedInvoice.setCurrencyFlag("none");
		  //invoiceSearch = consolidatedInvoiceService.searchInvoice(invoiceSearch);
		  addConsolidatedInvoice.setInvoiceSearchCriteria(invoiceSearch);
		  invoiceSearch = consolidatedInvoiceService.searchInvoice(invoiceSearch,sortFlag,sortOrderFlag);
		  addConsolidatedInvoice.setInvoiceSearch(invoiceSearch);
		  addConsolidatedInvoice.setTabName("0");
		  return showForm(request, response, errors);	
	  }
	  if(bankFlag != null && (!bankFlag.trim().equals("")) && (bankFlag.trim().equals("selectBank")))
	  {
		  //Get the bank accounts for this bank
		  List bankAccts = jobService.getBankAccountByBankCodeAndCurrency(consolidatedInvoice.getConsolidatedInvoiceId().getBuName(),consolidatedInvoice.getCurrencyCd(), consolidatedInvoice.getBankCd());
		  if(bankAccts != null && bankAccts.size() > 0)
		  {
			  if(bankAccts.size() == 1)
			  {
				  BankAccount bankAccount = (BankAccount) bankAccts.get(0);
				  if(bankAccount != null)
				  {
					  addConsolidatedInvoice.getConsolidatedInvoice().setBankAcctKey(bankAccount.getBankAccountId().getBankAcctCode());
				  }
			  }
			  else
				  addConsolidatedInvoice.getConsolidatedInvoice().setBankAcctKey("");
		  }
		  else
		  {
			  addConsolidatedInvoice.getConsolidatedInvoice().setBankAcctKey("");
		  }
		  addConsolidatedInvoice.setBankFlag("none");
		  addConsolidatedInvoice.setTabName("0");
		  return showForm(request, response, errors);	
	  }
	  if(billStatusFlag != null && (!billStatusFlag.trim().equals("")) && (billStatusFlag.trim().equals("selectBillStatus")))
	  {
		  addConsolidatedInvoice.setBillStatusFlag("none");
		  //invoiceSearch = consolidatedInvoiceService.searchInvoice(invoiceSearch);
		  addConsolidatedInvoice.setInvoiceSearchCriteria(invoiceSearch);
		  invoiceSearch = consolidatedInvoiceService.searchInvoice(invoiceSearch,sortFlag,sortOrderFlag);
		  addConsolidatedInvoice.setInvoiceSearch(invoiceSearch);
		  addConsolidatedInvoice.setTabName("0");
		  return showForm(request, response, errors);	
	  }
	  if(dateFlag != null && (!dateFlag.trim().equals("")) && (dateFlag.trim().equals("selectDate")))
	  {
		  if(addConsolidatedInvoice.getConsolidatedInvoice().getFromDt() != null && (!addConsolidatedInvoice.getConsolidatedInvoice().getFromDt().toString().trim().equals("")))
		  {
			  invoiceSearch.setFromDate(addConsolidatedInvoice.getConsolidatedInvoice().getFromDt());
		  }
		  if(addConsolidatedInvoice.getConsolidatedInvoice().getToDt() != null && (!addConsolidatedInvoice.getConsolidatedInvoice().getToDt().toString().trim().equals("")))
		  {
			  invoiceSearch.setToDate(addConsolidatedInvoice.getConsolidatedInvoice().getToDt());
		  }
		  
		  //invoiceSearch = consolidatedInvoiceService.searchInvoice(invoiceSearch);
		  addConsolidatedInvoice.setInvoiceSearchCriteria(invoiceSearch);
		  invoiceSearch = consolidatedInvoiceService.searchInvoice(invoiceSearch,sortFlag,sortOrderFlag);
		  addConsolidatedInvoice.setInvoiceSearch(invoiceSearch);
		  addConsolidatedInvoice.setTabName("0");
		  addConsolidatedInvoice.setDateFlag("none");
		  return showForm(request, response, errors);
	  }
	  if(generateInvFlag != null && (!generateInvFlag.trim().equals("")) && (generateInvFlag.trim().equals("generate")))
	  {
		  errors.reject("create.consl.inv.error", new Object[] {"Please save before generating consolidated invoice"}, null);
		  addConsolidatedInvoice.setTabName("0");
		  addConsolidatedInvoice.setGenerateInvFlag("none");
			 
		  return showForm(request, response, errors);
	  }
	  //Attach/Detach tab Header sort starts 
	  if(sortFlag != null && !sortFlag.trim().equals(""))
	  {
		  InvoiceSearch updatedSearchCriteria = addConsolidatedInvoice.getInvoiceSearchCriteria();
		
		  if(updatedSearchCriteria.getCurrency() == null || updatedSearchCriteria.getCurrency().trim().equals("")){
			  updatedSearchCriteria.setCurrency(invoiceSearch.getCurrency());
		  }
		  if(updatedSearchCriteria.getCustCode() == null || updatedSearchCriteria.getCustCode().trim().equals("")){
			  updatedSearchCriteria.setCustCode(invoiceSearch.getCustCode());
		  }
		  if(updatedSearchCriteria.getInvoiceType() == null || updatedSearchCriteria.getInvoiceType().trim().equals("")){
			  updatedSearchCriteria.setInvoiceType(invoiceSearch.getInvoiceType());
		  }
		  if(updatedSearchCriteria.getInvoiceVatProvince() == null || updatedSearchCriteria.getInvoiceVatProvince().trim().equals("")){
			  updatedSearchCriteria.setInvoiceVatProvince(invoiceSearch.getInvoiceVatProvince());
		  }
		  if(updatedSearchCriteria.getFromDate() == null ){
			  updatedSearchCriteria.setFromDate(invoiceSearch.getFromDate());
		  }
		  if(updatedSearchCriteria.getToDate() == null ){
			  updatedSearchCriteria.setToDate(invoiceSearch.getToDate());
		  }
		  
		  invoiceSearch = consolidatedInvoiceService.searchInvoice(updatedSearchCriteria,sortFlag,sortOrderFlag);
		  addConsolidatedInvoice.setInvoiceSearch(invoiceSearch);
		  addConsolidatedInvoice.setTabName("2");
		  addConsolidatedInvoice.setSortFlag("");
		  return showForm(request, response, errors);
	  }
	  //End
	  if(saveFlag != null && (!saveFlag.trim().equals("")) && (saveFlag.trim().equals("save")))
	  {
		  addConsolidatedInvoice.setSaveFlag("none");
		 /* if(addConsolidatedInvoice.getAttentionLocationNumber() == null || addConsolidatedInvoice.getAttentionLocationNumber().trim().equals(""))
		  {
			  errors.reject("create.consl.inv.error", new Object[] {"Please select Attention To before saving consolidated invoice"}, null);
			  addConsolidatedInvoice.setSaveFlag("none");
			  addConsolidatedInvoice.setTabName("1");
		      return showForm(request, response, errors);
		  }
		  try{
			  int attnNumber = Integer.parseInt(addConsolidatedInvoice.getAttentionLocationNumber());
		  }
		  catch(Exception e)
		  {
			  errors.reject("create.consl.inv.error", new Object[] {"Invalid Attention To location number"}, null);
			  addConsolidatedInvoice.setSaveFlag("none");
			  addConsolidatedInvoice.setTabName("1");
		      return showForm(request, response, errors);
		  }
		  
		  List contactCusts = customerService.getContactCustsByCustCode(addConsolidatedInvoice.getConsolidatedInvoice().getConsolidatedInvoiceId().getCustCode());
		  boolean contactCustFlag = false;
		  if(contactCusts != null && contactCusts.size() > 0)
		  {
			  for(int i=0;i<contactCusts.size();i++)
			  {
				  ContactCust contactCust = (ContactCust) contactCusts.get(i);
				  
				  if(contactCust.getContactCustId().getLocationNumber() == Integer.parseInt(addConsolidatedInvoice.getAttentionLocationNumber()))
				  {
					  if(new Long(contactCust.getContactCustId().getContactId()).toString().trim().equals(addConsolidatedInvoice.getContactId()))
					  {
						  contactCustFlag = true;
						  break;
					  }
				  }
			  }
			  if(!contactCustFlag)
			  {
				  errors.reject("create.consl.inv.error", new Object[] {"Invalid Attention To location number Contact Id combination"}, null);
				  addConsolidatedInvoice.setSaveFlag("none");
				  addConsolidatedInvoice.setTabName("1");
			      return showForm(request, response, errors);
			  }
		  }		  
		  else
		  {
			  errors.reject("create.consl.inv.error", new Object[] {"Invalid Attention To location number"}, null);
			  addConsolidatedInvoice.setSaveFlag("none");
			  addConsolidatedInvoice.setTabName("1");
		      return showForm(request, response, errors);
		  }
		  */
		  
		  if(addConsolidatedInvoice.getConsolidatedInvoice().getCustCode() == null || addConsolidatedInvoice.getConsolidatedInvoice().getCustCode().trim().equals(""))
		  {
			  errors.reject("create.consl.inv.error", new Object[] {"Please select Customer before saving consolidated invoice"}, null);
			  addConsolidatedInvoice.setTabName("0");
		      return showForm(request, response, errors);
		  }
		  
		  if(addConsolidatedInvoice.getConsolidatedInvoice().getBankAcctKey() == null || addConsolidatedInvoice.getConsolidatedInvoice().getBankAcctKey().trim().equals(""))
		  {
			  errors.reject("create.consl.inv.error", new Object[] {"Please select Bank Account before saving consolidated invoice"}, null);
			  addConsolidatedInvoice.setTabName("0");
		      return showForm(request, response, errors);
		  }
		  
		  if(addConsolidatedInvoice.getConsolidatedInvoice().getBankCd() == null || addConsolidatedInvoice.getConsolidatedInvoice().getBankCd().trim().equals(""))
		  {
			  errors.reject("create.consl.inv.error", new Object[] {"Please select Remit to before saving consolidated invoice"}, null);
			  addConsolidatedInvoice.setTabName("0");
		      return showForm(request, response, errors);
		  }
		  
		  if(addConsolidatedInvoice.getConsolidatedInvoice().getContact() == null){
			  errors.reject("create.consl.inv.error", new Object[] {"Please select Attention To and save before generating consolidated invoice"}, null);
			  addConsolidatedInvoice.setTabName("1");
			  return showForm(request, response, errors);
		  }
		  
		  ContactCust contactCust = customerService.getContactCustByPK(addConsolidatedInvoice.getConsolidatedInvoice().getContact().getId(), addConsolidatedInvoice.getConsolidatedInvoice().getCustCode(), addConsolidatedInvoice.getConsolidatedInvoice().getLocationNumber() );
		  if(contactCust == null){
			  errors.reject("create.consl.inv.error", new Object[] {"The contact id or location number is not valid for the customer"}, null);
			  addConsolidatedInvoice.setTabName("1");
		      return showForm(request, response, errors);
		  }
		  
		  OpenPeriod openPeriodHeader = jobService.findOpenPeriodByBuName(addConsolidatedInvoice.getConsolidatedInvoice().getConsolidatedInvoiceId().getBuName());
		  Date firstDateHeader = openPeriodHeader.getOpenFromDate();
		  Calendar from_cal_header = Calendar.getInstance();
		  from_cal_header.setTime(firstDateHeader);
		  System.out.println("from open period header is "+ from_cal_header.getTime());
		  Calendar to_cal_header = from_cal_header;
		  to_cal_header.add(Calendar.MONTH, 1);
		  
		  System.out.println("open period end of month header is "+ to_cal_header.getTime());
		  if(addConsolidatedInvoice.getConsolidatedInvoice().getInvoiceDt()!=null &&  (addConsolidatedInvoice.getConsolidatedInvoice().getInvoiceDt().compareTo(firstDateHeader)<0 || addConsolidatedInvoice.getConsolidatedInvoice().getInvoiceDt().compareTo(to_cal_header.getTime())>=0)){
			  errors.reject("create.consl.inv.error", new Object[] {"Invoice header date must be within the 1st month of the current open period!"}, null);
			  addConsolidatedInvoice.setTabName("0");
			  return showForm(request, response, errors);
		  }
		  
		  try{
			  consolidatedInvoice = consolidatedInvoiceService.createConsolidatedInvoice(addConsolidatedInvoice.getConsolidatedInvoice());
			  System.out.println("after create");  
			  addConsolidatedInvoice.setConsolidatedInvoice(consolidatedInvoice);
		  	  
			  if(addConsolidatedInvoice.getConsolidatedInvoice().getConsolidatedInvoiceId()!=null && chosenInvoices != null && (!chosenInvoices.trim().equals("")))
		  	  {
		  		  String[] jobContractInvoiceIds=chosenInvoices.split("\\;");
		  		  if(jobContractInvoiceIds != null && addConsolidatedInvoice.getConsolidatedInvoice().getConsolidatedInvoiceId()!=null && addConsolidatedInvoice.getConsolidatedInvoice().getConsolidatedInvoiceId().getConsolInvoiceNo()!=null && addConsolidatedInvoice.getConsolidatedInvoice().getConsolidatedInvoiceId().getBuName()!=null)
		  		  {
		  			  for(int i=0;i<jobContractInvoiceIds.length;i++)
		  			  {
		  				  JobContractInvoice jobContractInvoice = prebillService.getJobContractInvoiceById(new Long(jobContractInvoiceIds[i]));
		  				  //jobContract.setBuName(consolidatedInvoice.getConsolidatedInvoiceId().getBuName());
		  				  jobContractInvoice.setConsolInvoiceNo(addConsolidatedInvoice.getConsolidatedInvoice().getConsolidatedInvoiceId().getConsolInvoiceNo());
		  				  jobContractInvoice.setConsolBuName(addConsolidatedInvoice.getConsolidatedInvoice().getConsolidatedInvoiceId().getBuName());
		  				  prebillService.saveJobContractInvoice(jobContractInvoice);
		  			 }
		  		  }
		  	  }		  

	    }
	    catch(Exception e)
	    {
	      e.printStackTrace();

	      errors.reject("create.consl.inv.error", new Object[] {e.getMessage()}, null);
	      return showForm(request, response, errors);
	    }
	  

	    ModelAndView modelAndView = new ModelAndView("common-message-r");
	    //modelAndView.addObject("backUrl", "edit_contact.htm?contact.id=" + contact.getId());
		modelAndView.addObject("backUrl", "edit_consl_inv.htm?conslInvNumber="+addConsolidatedInvoice.getConsolidatedInvoice().getConsolidatedInvoiceId().getConsolInvoiceNo()+"&buName="+addConsolidatedInvoice.getConsolidatedInvoice().getConsolidatedInvoiceId().getBuName());
	    modelAndView.addObject("backUrlDescription", "You can continue to edit this invoice.");
	    modelAndView.addObject("description", "Your invoice has been successfully created.");

	    return modelAndView;
	  }

		  return showForm(request, response, errors);
	 

  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest, org.springframework.web.bind.ServletRequestDataBinder)
   */
  protected void initBinder(
    HttpServletRequest request,
    ServletRequestDataBinder binder
  ) throws Exception
  {
	  
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
    /*SimpleDateFormat dateFormat = new SimpleDateFormat("mm/dd/yyyy");
    dateFormat.setLenient(false);
    binder.registerCustomEditor(java.util.Date.class, null, new CustomDateEditor(dateFormat, true));*/

  }

   protected boolean suppressValidation(HttpServletRequest request,Object command)
  {
  
    return super.suppressValidation(request);
  }

 
  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
   */
  protected Object formBackingObject(
    HttpServletRequest request
  ) throws Exception
  {

	AddConsolidatedInvoice addConsolidatedInvoice = new AddConsolidatedInvoice();
	UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
	JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
	
	User loginUser =userService.getUserByName(SecurityUtil.getUser().getLoginName());
	String dateFormat = loginUser.getDateFormat();
	
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
	addConsolidatedInvoice.setDateFormat(dateFormat);
	
	ConsolidatedInvoice consolidatedInvoice = new ConsolidatedInvoice();
	ConsolidatedInvoiceId consolidatedInvoiceId = new ConsolidatedInvoiceId();
	consolidatedInvoiceId.setBuName((userService.getUserByName(SecurityUtil.getUser().getLoginName())).getBuName());
	consolidatedInvoice.setCurrencyCd((userService.getUserByName(SecurityUtil.getUser().getLoginName())).getCurrencyCd());
	consolidatedInvoice.setConsolidatedInvoiceId(consolidatedInvoiceId);
	//Vat perovince code
	BusinessUnit businessUnit = userService.getBusinessUnitByName(consolidatedInvoiceId.getBuName());
	//addConsolidatedInvoice.setBusinessUnit(businessUnit);
	
	if(businessUnit != null && businessUnit.getCountry() != null && businessUnit.getCountry().getSameProvinceValidation() != null && businessUnit.getCountry().getSameProvinceValidation()!= false)
	{
		addConsolidatedInvoice.setVatCountryFlag("true");
	} else
	{
		addConsolidatedInvoice.setVatCountryFlag("false");
	}
	//end 
	  List banks = jobService.getBankCodebyBunameandCurrency(consolidatedInvoiceId.getBuName(),consolidatedInvoice.getCurrencyCd());
	  if(banks != null && banks.size() > 0)
	  {
		  if(banks.size() == 1)
		  {
			  Bank bank = (Bank) banks.get(0);
			  if(bank != null)
			  {
				  consolidatedInvoice.setBankCd(bank.getBankCode());
				  
				  //Get the bank accounts for this bank
				  List bankAccts = jobService.getBankAccountByBankCodeAndCurrency(consolidatedInvoiceId.getBuName(),consolidatedInvoice.getCurrencyCd(), bank.getBankCode());
				  if(bankAccts != null && bankAccts.size() > 0)
				  {
					  if(bankAccts.size() == 1)
					  {
						  BankAccount bankAccount = (BankAccount) bankAccts.get(0);
						  if(bankAccount != null)
						  {
							  consolidatedInvoice.setBankAcctKey(bankAccount.getBankAccountId().getBankAcctCode());
						  }
					  }
				  }
			  }
		  }
	  }

	consolidatedInvoice.setBillStatus("NEW");
	consolidatedInvoice.setInvoiceType("REG");
	consolidatedInvoice.setBillType("CON");
	consolidatedInvoice.setBillAddDt(new Date());
	addConsolidatedInvoice.setContact(new Contact());

	addConsolidatedInvoice.setConsolidatedInvoice(consolidatedInvoice);
	 return addConsolidatedInvoice;
  }



}
