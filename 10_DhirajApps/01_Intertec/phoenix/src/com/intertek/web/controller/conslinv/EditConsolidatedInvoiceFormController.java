package com.intertek.web.controller.conslinv;

import java.text.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.intertek.entity.*;
import com.intertek.service.*;
import com.intertek.util.*;
import com.intertek.web.controller.BaseSimpleFormController;
import com.intertek.locator.*;
import com.intertek.domain.*;

//public class EditConsolidatedInvoiceFormController extends SimpleFormController
public class EditConsolidatedInvoiceFormController extends BaseSimpleFormController
{
  public EditConsolidatedInvoiceFormController() {
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
	  
	  List<JobContractInvoice> jobContractInvoiceAttachedList =  consolidatedInvoiceService.getJobContractInvoiceByConsol(consolidatedInvoice.getConsolidatedInvoiceId().getConsolInvoiceNo(), consolidatedInvoice.getConsolidatedInvoiceId().getBuName(), null);
	  if(!jobContractInvoiceAttachedList.isEmpty() && jobContractInvoiceAttachedList.size()>0)
		  request.setAttribute("attached", true);
	  else
		  request.setAttribute("attached", false);
	  //Prepare invoice search object
	  InvoiceSearch invoiceSearch = new InvoiceSearch();
	  //invoiceSearch.setBillStatus(consolidatedInvoice.getBillStatus());
	  //invoiceSearch.setBuName(consolidatedInvoice.getConsolidatedInvoiceId().getBuName());
	  invoiceSearch.setCurrency(consolidatedInvoice.getCurrencyCd());
	  invoiceSearch.setCustCode(consolidatedInvoice.getCustCode());
	  
	  invoiceSearch.setInvoiceVatProvince(consolidatedInvoice.getVatProvince());
	  invoiceSearch.setInvoiceType("CON");
	  
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
	  String attachFlag = addConsolidatedInvoice.getAttachFlag();
	  String detachFlag = addConsolidatedInvoice.getDetachFlag();
	  String generateInvFlag = addConsolidatedInvoice.getGenerateInvFlag();
	  String regeneratePdfInvoice = request.getParameter("regeneratePdfInvoice");
	 
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
	  if(regeneratePdfInvoice != null && (!regeneratePdfInvoice.trim().equals("")) && (regeneratePdfInvoice.trim().equals("true")) && addConsolidatedInvoice.getConsolidatedInvoice().getConsolidatedInvoiceId() != null && addConsolidatedInvoice.getConsolidatedInvoice().getConsolidatedInvoiceId().getBuName() != null && addConsolidatedInvoice.getConsolidatedInvoice().getConsolidatedInvoiceId().getConsolInvoiceNo() != null && addConsolidatedInvoice.getConsolidatedInvoice().getCustCode() != null)
	  {
		  try{
		        consolidatedInvoiceService.generateInvoicePDF(InvoiceUtil.getInvoiceDir(), InvoiceUtil.getJasperDir(),addConsolidatedInvoice.getConsolidatedInvoice().getConsolidatedInvoiceId().getConsolInvoiceNo(),addConsolidatedInvoice.getConsolidatedInvoice().getConsolidatedInvoiceId().getBuName(), addConsolidatedInvoice.getConsolidatedInvoice().getCustCode(), addConsolidatedInvoice.getConsolidatedInvoice().getLocationNumber() );
		      }
		      catch(Exception e){
		        errors.reject("INVOICE_GENERATION_ERROR", new Object[] { e.getMessage() }, null);
		        return showForm(request, response, errors);
		      }
	  }		  
			
	  String invoiceFileIdStr = request.getParameter("invoiceFileId");
	    String countableStr = request.getParameter("invoiceFileCountable");
	    
	    if(invoiceFileIdStr!=null && invoiceFileIdStr.trim().length()>0 && countableStr!=null && countableStr.trim().length()>0  ){
	       boolean countable = true;
	       if(countableStr.equals("N"))
	    	   countable = false;
	      long invoiceFileId = Long.parseLong(invoiceFileIdStr);
	      consolidatedInvoiceService.setInvoiceFileCountable(invoiceFileId, countable);
		  addConsolidatedInvoice.setTabName("3");
		  return showForm(request, response, errors);
	    }
			  
			  if(busFlag != null && (!busFlag.trim().equals("")) && (busFlag.trim().equals("selectBU")))
			  {
				  String buName = addConsolidatedInvoice.getConsolidatedInvoice().getConsolidatedInvoiceId().getBuName();
				  String curencyCd = addConsolidatedInvoice.getConsolidatedInvoice().getCurrencyCd();
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
				  //invoiceSearch = consolidatedInvoiceService.searchInvoice(invoiceSearch);
				  addConsolidatedInvoice.setInvoiceSearchCriteria(invoiceSearch);
				  invoiceSearch = consolidatedInvoiceService.searchInvoice(invoiceSearch,sortFlag,sortOrderFlag);
				  addConsolidatedInvoice.setInvoiceSearch(invoiceSearch);
				  addConsolidatedInvoice.setTabName("0");
				  return showForm(request, response, errors);
			  }
			  if(custFlag != null && (!custFlag.trim().equals("")) && (custFlag.trim().equals("selectCustomer")))
			  {
				  String custCode = addConsolidatedInvoice.getConsolidatedInvoice().getCustCode();
				  Customer customer = customerService.getCustomerByCustCode(custCode);
				  addConsolidatedInvoice.setCustomer(customer);
				  if(customer != null)
				  {
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
				 // invoiceSearch = consolidatedInvoiceService.searchInvoice(invoiceSearch);
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
				  List contactCusts = customerService.getContactCustsByCustCode(addConsolidatedInvoice.getConsolidatedInvoice().getCustCode());
				  
				  if(contactCusts != null && contactCusts.size() > 0)
				  {
					  for(int i=0;i<contactCusts.size();i++)
					  {
						  ContactCust contactCust = (ContactCust) contactCusts.get(i);
						  
						  if(new Long(contactCust.getContactCustId().getContactId()).toString().trim().equals(contactId))
						  {
							  addConsolidatedInvoice.getConsolidatedInvoice().setCustomer(customerService.getCustomerByCustCode(addConsolidatedInvoice.getConsolidatedInvoice().getCustCode()));
							  
							  Contact existingContact = customerService.getContactById(new Long(contactId));
							  
							  if(existingContact!=null)
								  addConsolidatedInvoice.getConsolidatedInvoice().setContact(existingContact);
							  else
								  addConsolidatedInvoice.getConsolidatedInvoice().setContact(null);
							  
							  
							  addConsolidatedInvoice.getConsolidatedInvoice().setCustAddrSeq(customerService.getCustAddrSeqByLocationNumber(contactCust.getContactCustId().getLocationNumber(), addConsolidatedInvoice.getConsolidatedInvoice().getCustCode()));
							  addConsolidatedInvoice.getConsolidatedInvoice().setLocationNumber(contactCust.getContactCustId().getLocationNumber());
							  addConsolidatedInvoice.setContact(addConsolidatedInvoice.getConsolidatedInvoice().getContact());
							  
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
				 // invoiceSearch = consolidatedInvoiceService.searchInvoice(invoiceSearch);
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
				 // invoiceSearch = consolidatedInvoiceService.searchInvoice(invoiceSearch);
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
				  
				 // invoiceSearch = consolidatedInvoiceService.searchInvoice(invoiceSearch);
				  addConsolidatedInvoice.setInvoiceSearchCriteria(invoiceSearch);
				  invoiceSearch = consolidatedInvoiceService.searchInvoice(invoiceSearch,sortFlag,sortOrderFlag);
				  addConsolidatedInvoice.setInvoiceSearch(invoiceSearch);
				  addConsolidatedInvoice.setTabName("0");
				  addConsolidatedInvoice.setDateFlag("none");
				  return showForm(request, response, errors);
			  }
			  if(generateInvFlag != null && (!generateInvFlag.trim().equals("")) && (generateInvFlag.trim().equals("generate")))
			  {
				  addConsolidatedInvoice.setGenerateInvFlag("none");
				  
				  if(!addConsolidatedInvoice.getConsolidatedInvoice().getBillStatus().equals(Constants.BILL_STATUS_RDY)){
					  errors.reject("create.consl.inv.error", new Object[] {"Please set the bill status to Ready!"}, null);
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
				  
				  
			     if(jobContractInvoiceAttachedList==null || jobContractInvoiceAttachedList.isEmpty() || jobContractInvoiceAttachedList.size()==0){
					  errors.reject("create.consl.inv.error", new Object[] {"Please attach some invoices before generating consolidated invoice"}, null);
					  addConsolidatedInvoice.setTabName("2");
					  return showForm(request, response, errors);
				  } else{
					  OpenPeriod openPeriodHeader = jobService.findOpenPeriodByBuName(addConsolidatedInvoice.getConsolidatedInvoice().getConsolidatedInvoiceId().getBuName());
					  Date firstDateHeader = openPeriodHeader.getOpenFromDate();
					  Calendar from_cal_header = Calendar.getInstance();
					  from_cal_header.setTime(firstDateHeader);
					  Calendar to_cal_header = from_cal_header;
					  to_cal_header.add(Calendar.MONTH, 1);
					  
					  if(addConsolidatedInvoice.getConsolidatedInvoice().getInvoiceDt()!=null && (addConsolidatedInvoice.getConsolidatedInvoice().getInvoiceDt().compareTo(firstDateHeader)<0 || addConsolidatedInvoice.getConsolidatedInvoice().getInvoiceDt().compareTo(to_cal_header.getTime())>=0)){
						  errors.reject("create.consl.inv.error", new Object[] {"Invoice header date must be within the 1st month of the current open period!"}, null);
						  addConsolidatedInvoice.setTabName("0");
						  return showForm(request, response, errors);
					  }
					  
					//All attached invoices must have their Accounting date  within the first month of the current open period. not later than that.
					  for(JobContractInvoice jobContractInvoice : jobContractInvoiceAttachedList){
						  OpenPeriod openPeriod = jobService.findOpenPeriodByBuName(jobContractInvoice.getJobContract().getJobOrder().getBuName());
						  Date firstDate = openPeriod.getOpenFromDate();
						  Calendar from_cal = Calendar.getInstance();
						  from_cal.setTime(firstDate);
						  Calendar to_cal = from_cal;
						  to_cal.add(Calendar.MONTH, 1);
						  
						  if(jobContractInvoice.getInvoiceDate()!=null && jobContractInvoice.getInvoiceDate().compareTo(to_cal_header.getTime())>=0){
							  errors.reject("create.consl.inv.error", new Object[] {"All attached invoices must have their Invoice dates within the first month of the current open period"}, null);
							  addConsolidatedInvoice.setTabName("2");
							  return showForm(request, response, errors);
						  }
						  
						  if(jobContractInvoice.getAccountingDate()!=null && jobContractInvoice.getAccountingDate().compareTo(to_cal_header.getTime())>=0 ){
							  errors.reject("create.consl.inv.error", new Object[] {"All attached invoices must have their Accounting dates within the first month of the current open period"}, null);
							  addConsolidatedInvoice.setTabName("2");
							  return showForm(request, response, errors);
						  }
					  }
					  
					  
					  if(addConsolidatedInvoice.getConsolidatedInvoice()!=null && addConsolidatedInvoice.getConsolidatedInvoice().getConsolidatedInvoiceId()!=null){
						  consolidatedInvoice = consolidatedInvoiceService.saveConsolidatedInvoice(addConsolidatedInvoice.getConsolidatedInvoice());
						  consolidatedInvoiceService.generateConsolInvoice(addConsolidatedInvoice.getConsolidatedInvoice().getConsolidatedInvoiceId().getConsolInvoiceNo(),addConsolidatedInvoice.getConsolidatedInvoice().getConsolidatedInvoiceId().getBuName());
						  consolidatedInvoiceService.generateInvoicePDF(InvoiceUtil.getInvoiceDir(), InvoiceUtil.getJasperDir(), addConsolidatedInvoice.getConsolidatedInvoice().getConsolidatedInvoiceId().getConsolInvoiceNo(), addConsolidatedInvoice.getConsolidatedInvoice().getConsolidatedInvoiceId().getBuName(), addConsolidatedInvoice.getConsolidatedInvoice().getCustCode(), addConsolidatedInvoice.getConsolidatedInvoice().getLocationNumber());
						  addConsolidatedInvoice.setTabName("3");
					  }
			     }
				 return new ModelAndView(new RedirectView("edit_consl_inv.htm?conslInvNumber="+consolidatedInvoice.getConsolidatedInvoiceId().getConsolInvoiceNo()+"&buName="+consolidatedInvoice.getConsolidatedInvoiceId().getBuName()+"&tabName=3")); 
			  }
			  
			  if(attachFlag != null && (!attachFlag.trim().equals("")) && (attachFlag.trim().equals("attach")))
			  {
				  addConsolidatedInvoice.setAttachFlag("none");
				  if(chosenInvoices != null && (!chosenInvoices.trim().equals("")))
			  	  {
					  consolidatedInvoice = consolidatedInvoiceService.saveConsolidatedInvoice(addConsolidatedInvoice.getConsolidatedInvoice());
			  		  String[] jobContractInvoiceIds=chosenInvoices.split("\\;");
			  		  
			  		  
			  		  
			  		if(jobContractInvoiceIds != null && addConsolidatedInvoice.getConsolidatedInvoice().getConsolidatedInvoiceId()!=null && addConsolidatedInvoice.getConsolidatedInvoice().getConsolidatedInvoiceId().getConsolInvoiceNo()!=null && addConsolidatedInvoice.getConsolidatedInvoice().getConsolidatedInvoiceId().getBuName()!=null)
			  		  {
			  			//consolidatedInvoice.getJobContracts().clear();
			  			  for(int i=0;i<jobContractInvoiceIds.length;i++)
			  			  {
			  				  JobContractInvoice jobContractInvoice = prebillService.getJobContractInvoiceById(new Long(jobContractInvoiceIds[i]));
			  				  //jobContract.setBuName(consolidatedInvoice.getConsolidatedInvoiceId().getBuName());
			  				  jobContractInvoice.setConsolInvoiceNo(addConsolidatedInvoice.getConsolidatedInvoice().getConsolidatedInvoiceId().getConsolInvoiceNo());
			  				  jobContractInvoice.setConsolBuName(addConsolidatedInvoice.getConsolidatedInvoice().getConsolidatedInvoiceId().getBuName());
			  				
			  				  prebillService.saveJobContractInvoice(jobContractInvoice);
			  			  }
			  		  }
			  					  		return new ModelAndView(new RedirectView("edit_consl_inv.htm?conslInvNumber="+consolidatedInvoice.getConsolidatedInvoiceId().getConsolInvoiceNo()+"&buName="+consolidatedInvoice.getConsolidatedInvoiceId().getBuName()+"&tabName=2"));
			  	  }
				  else{
					  consolidatedInvoice = consolidatedInvoiceService.saveConsolidatedInvoice(addConsolidatedInvoice.getConsolidatedInvoice());
					  return showForm(request, response, errors);
				  }
			  }
			  if(detachFlag != null && (!detachFlag.trim().equals("")) && (detachFlag.trim().equals("detach")))
			  {
				  addConsolidatedInvoice.setDetachFlag("none");
				  if(chosenInvoices != null && (!chosenInvoices.trim().equals("")))
			  	  {
					  consolidatedInvoice = consolidatedInvoiceService.saveConsolidatedInvoice(addConsolidatedInvoice.getConsolidatedInvoice());

			  		  String[] jobContractInvoiceIds=chosenInvoices.split("\\;");
			  		  if(jobContractInvoiceIds != null)
			  		  {
			  			//consolidatedInvoice.getJobContracts().clear();
			  			  for(int i=0;i<jobContractInvoiceIds.length;i++)
			  			  {
			  				  JobContractInvoice jobContractInvoice = prebillService.getJobContractInvoiceById(new Long(jobContractInvoiceIds[i]));
			  				  //jobContract.setBuName(consolidatedInvoice.getConsolidatedInvoiceId().getBuName());
			  				  jobContractInvoice.setConsolInvoiceNo("");
			  				  jobContractInvoice.setConsolBuName("");
			  				  prebillService.saveJobContractInvoice(jobContractInvoice);
			  			  }
			  		  }
			  					  					  		
			  		return new ModelAndView(new RedirectView("edit_consl_inv.htm?conslInvNumber="+consolidatedInvoice.getConsolidatedInvoiceId().getConsolInvoiceNo()+"&buName="+consolidatedInvoice.getConsolidatedInvoiceId().getBuName()+"&tabName=2"));
			  	  }
				  else{
					  consolidatedInvoice = consolidatedInvoiceService.saveConsolidatedInvoice(addConsolidatedInvoice.getConsolidatedInvoice());
					  return showForm(request, response, errors);
				  }
				  
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
				  Double pretaxAmount = 0.0;
				  if(invoiceSearch.getResults() != null && invoiceSearch.getResults().size() > 0)
				  {
					  for(int i=0;i<invoiceSearch.getResults().size();i++)
					  {
						  JobContractInvoice jobContractInvoice = (JobContractInvoice) invoiceSearch.getResults().get(i);
						  JobContract jobContract = ((JobContractInvoice) invoiceSearch.getResults().get(i)).getJobContract();
						  if(jobContract.getInvoiceAmt() != null)
						  {
							  if(jobContractInvoice.getConsolInvoiceNo() != null && (!jobContractInvoice.getConsolInvoiceNo().trim().equals("")))
							  {
								  if(jobContractInvoice.getConsolInvoiceNo().trim().equals(consolidatedInvoice.getConsolidatedInvoiceId().getConsolInvoiceNo()) && jobContractInvoice.getConsolBuName().trim().equals(consolidatedInvoice.getConsolidatedInvoiceId().getBuName().trim()))
									  pretaxAmount = pretaxAmount + jobContract.getInvoiceAmt();
							  }
								
						  }
					  }
					  addConsolidatedInvoice.setPreTaxAmount(pretaxAmount.toString());
				  }
				  else
					  addConsolidatedInvoice.setPreTaxAmount("0.0"); 
				  
				  addConsolidatedInvoice.setAttachedInvoices(updateAttachedInvoices(invoiceSearch,invoiceSearch.getInvoiceNo()));
				  
				  return showForm(request, response, errors);
			  }
			  //End
			  if(saveFlag != null && (!saveFlag.trim().equals("")) && (saveFlag.trim().equals("save")))
			  {
				addConsolidatedInvoice.setSaveFlag("none");
			  	String invoiceNo = "";
			    try
			    {
			    	/*
			    	List conslInvs = consolidatedInvoiceService.getAllConsolidatedInvoices();
			    	if(conslInvs != null && conslInvs.size() > 0)
			    	{
			    		
			    		int invSize = conslInvs.size() + 1;
			    		Integer invs = new Integer(invSize);
			    		String invStr = invs.toString();
			    		invoiceNo = "CON";
			    		for(int i=0;i<(7-invStr.length());i++)
			    		{
			    			invoiceNo = invoiceNo + "0";
			    		}
			    		invoiceNo = invoiceNo + invStr;
			    		
			    		addConsolidatedInvoice.getConsolidatedInvoice().getConsolidatedInvoiceId().setConsolInvoiceNo(invoiceNo);
			    	}
			    	*/
			    	
				  	  //Save Consolidated Invoice
				  	  if(chosenInvoices != null && (!chosenInvoices.trim().equals("")))
				  	  {
				  		  String[] jobContractInvoiceIds=chosenInvoices.split("\\;");
				  		  
				  		consolidatedInvoice = consolidatedInvoiceService.saveConsolidatedInvoice(addConsolidatedInvoice.getConsolidatedInvoice());
				  		if(jobContractInvoiceIds != null)
				  		  {
				  			//consolidatedInvoice.getJobContracts().clear();
				  			  for(int i=0;i<jobContractInvoiceIds.length;i++)
				  			  {
				  				  JobContractInvoice jobContractInvoice = prebillService.getJobContractInvoiceById(new Long(jobContractInvoiceIds[i]));
				  				  //jobContract.setBuName(consolidatedInvoice.getConsolidatedInvoiceId().getBuName());
				  				  //jobContract.setConsolInvoiceNo(consolidatedInvoice.getConsolidatedInvoiceId().getConsolInvoiceNo());
				  				  //jobContract.setConsolidatedInvoice(consolidatedInvoice);
				  				  //consolidatedInvoice.getJobContracts().add(jobContract);
				  				  prebillService.saveJobContractInvoice(jobContractInvoice);
				  				  //jobService.saveJobContractInsp(jobContract);
				  			 }
				  		  }
				  	  }else 	
				  		  consolidatedInvoice = consolidatedInvoiceService.saveConsolidatedInvoice(addConsolidatedInvoice.getConsolidatedInvoice());
				    }

			    catch(Exception e)
			    {
			      e.printStackTrace();

			      errors.reject("edit.consl.inv.error", new Object[] {e.getMessage()}, null);
			      return showForm(request, response, errors);
			    }


			    ModelAndView modelAndView = new ModelAndView("common-message-r");
			    //modelAndView.addObject("backUrl", "edit_contact.htm?contact.id=" + contact.getId());
				modelAndView.addObject("backUrl", "edit_consl_inv.htm?conslInvNumber="+consolidatedInvoice.getConsolidatedInvoiceId().getConsolInvoiceNo()+"&buName="+consolidatedInvoice.getConsolidatedInvoiceId().getBuName());
			    modelAndView.addObject("backUrlDescription", "You can continue to edit this invoice.");
			    modelAndView.addObject("description", "Your invoice has been successfully updated.");

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
     * 
     */
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
	Double pretaxAmount = 0.0;
	String conslInvNumber = request.getParameter("conslInvNumber");
	String buName = request.getParameter("buName");
	String tabName = request.getParameter("tabName");
	//Setting Attach/detach tab header sort order
	String sortFlag = addConsolidatedInvoice.getSortFlag();
	String sortOrderFlag = "";
	//End
	if(tabName != null && (!tabName.trim().equals("")))
	{
		addConsolidatedInvoice.setTabName(tabName);
	}
	else
		addConsolidatedInvoice.setTabName("0");
	UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
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
	
	InvoiceSearch invoiceSearch = new InvoiceSearch();
	

	if(conslInvNumber != null && (!conslInvNumber.trim().equals("")))
	{
		invoiceSearch.setInvoiceNo(conslInvNumber);
		if(buName!=null && buName.trim().length()>0)
			invoiceSearch.setBuName(buName);
		ConsolidatedInvoiceService consolidatedInvoiceService = (ConsolidatedInvoiceService)ServiceLocator.getInstance().getBean("consolidatedInvoiceService");
		CustomerService customerService = (CustomerService)ServiceLocator.getInstance().getBean("customerService");
		ConsolidatedInvoice consolidatedInvoice = consolidatedInvoiceService.getConsolidatedInvoiceByInvoiceNumberBuName(conslInvNumber, buName);
		if(consolidatedInvoice!=null && consolidatedInvoice.getGenerateTime()!=null)
			invoiceSearch.setGenerated(true);
		
		
		addConsolidatedInvoice.setConsolidatedInvoice(consolidatedInvoice);
		if(consolidatedInvoice != null){
			List<JobContractInvoice> jobContractInvoiceAttachedList =  consolidatedInvoiceService.getJobContractInvoiceByConsol(consolidatedInvoice.getConsolidatedInvoiceId().getConsolInvoiceNo(), consolidatedInvoice.getConsolidatedInvoiceId().getBuName(), null);
			if(!jobContractInvoiceAttachedList.isEmpty() && jobContractInvoiceAttachedList.size()>0)
				request.setAttribute("attached", true);
			else
				request.setAttribute("attached", false);
			  
			if(consolidatedInvoice.getContact() != null)
				addConsolidatedInvoice.setContact(consolidatedInvoice.getContact());
			else
				addConsolidatedInvoice.setContact(new Contact());
			
			addConsolidatedInvoice.setCustomer(consolidatedInvoice.getCustomer());
			addConsolidatedInvoice.setAttentionLocationNumber(consolidatedInvoice.getLocationNumber().toString());
			
			List custAddresses = customerService.getCustAddressDetailsBySeqNumber(consolidatedInvoice.getLocationNumber(), consolidatedInvoice.getCustCode());
			if(custAddresses != null && custAddresses.size() > 0)
			{
				CustAddress custAddress = (CustAddress) custAddresses.get(0);
				addConsolidatedInvoice.setCustAddress(custAddress);
			}
			  invoiceSearch.setCurrency(consolidatedInvoice.getCurrencyCd());
			  invoiceSearch.setCustCode(consolidatedInvoice.getCustCode());
			  invoiceSearch.setInvoiceVatProvince(consolidatedInvoice.getVatProvince());
			  invoiceSearch.setInvoiceType("CON");
			  if(consolidatedInvoice.getFromDt() != null && (!consolidatedInvoice.getFromDt().toString().trim().equals("")))
			  {
				  invoiceSearch.setFromDate(consolidatedInvoice.getFromDt());
			  }
			  if(consolidatedInvoice.getToDt() != null && (!consolidatedInvoice.getToDt().toString().trim().equals("")))
			  {
				  invoiceSearch.setToDate(consolidatedInvoice.getToDt());
			  }
			 // invoiceSearch = consolidatedInvoiceService.searchInvoice(invoiceSearch);
			  addConsolidatedInvoice.setInvoiceSearchCriteria(invoiceSearch);
			  invoiceSearch = consolidatedInvoiceService.searchInvoice(invoiceSearch,sortFlag,sortOrderFlag);
			  addConsolidatedInvoice.setInvoiceSearch(invoiceSearch);
			  
			  if(invoiceSearch.getResults() != null && invoiceSearch.getResults().size() > 0)
			  {
				  for(int i=0;i<invoiceSearch.getResults().size();i++)
				  {
					  JobContractInvoice jobContractInvoice = (JobContractInvoice) invoiceSearch.getResults().get(i);
					  JobContract jobContract = ((JobContractInvoice) invoiceSearch.getResults().get(i)).getJobContract();
					  if(jobContract.getInvoiceAmt() != null)
					  {
						  if(jobContractInvoice.getConsolInvoiceNo() != null && (!jobContractInvoice.getConsolInvoiceNo().trim().equals("")))
						  {
							  if(jobContractInvoice.getConsolInvoiceNo().trim().equals(consolidatedInvoice.getConsolidatedInvoiceId().getConsolInvoiceNo()) && jobContractInvoice.getConsolBuName().trim().equals(consolidatedInvoice.getConsolidatedInvoiceId().getBuName().trim()))
								  pretaxAmount = pretaxAmount + jobContract.getInvoiceAmt();
						  }
							
					  }
				  }
				  addConsolidatedInvoice.setPreTaxAmount(pretaxAmount.toString());
			  }
			  else
				  addConsolidatedInvoice.setPreTaxAmount("0.0"); 
			  
		}else{
			consolidatedInvoice = new ConsolidatedInvoice();
			addConsolidatedInvoice.setConsolidatedInvoice(consolidatedInvoice);
		}
		//Vat Province code
		BusinessUnit businessUnit = userService.getBusinessUnitByName(consolidatedInvoice.getConsolidatedInvoiceId().getBuName());
		if(businessUnit != null && businessUnit.getCountry() != null && businessUnit.getCountry().getSameProvinceValidation() != null && businessUnit.getCountry().getSameProvinceValidation()!= false)
		{
				addConsolidatedInvoice.setVatCountryFlag("true");
		} else
		{
			addConsolidatedInvoice.setVatCountryFlag("false");
		}
		//end 
	}	
	else
	{
		ConsolidatedInvoice consolidatedInvoice = new ConsolidatedInvoice();
		addConsolidatedInvoice.setConsolidatedInvoice(consolidatedInvoice);
	}

	addConsolidatedInvoice.setAttachedInvoices(updateAttachedInvoices(invoiceSearch,conslInvNumber));

	 return addConsolidatedInvoice;
  }

private String updateAttachedInvoices(InvoiceSearch search,String consolInvNo)
{
	if(search == null)
		return null;
	
	if(search.getResults() == null || search.getResults().size() == 0)
		return null;
	
	String attachedInvoices = "";
	for(int i=0;i<search.getResults().size();i++)
	{
		JobContractInvoice jobContractInvoice = (JobContractInvoice) search.getResults().get(i);
		if(jobContractInvoice.getConsolInvoiceNo() != null && (!jobContractInvoice.getConsolInvoiceNo().trim().equals("")))
		{
			if(jobContractInvoice.getConsolInvoiceNo().equalsIgnoreCase(consolInvNo))
				if(attachedInvoices.equals(""))
					attachedInvoices = new Long(jobContractInvoice.getId()).toString();
				else
					attachedInvoices = attachedInvoices + ";" + new Long(jobContractInvoice.getId()).toString();
		}
	}
	return attachedInvoices;
}

}
