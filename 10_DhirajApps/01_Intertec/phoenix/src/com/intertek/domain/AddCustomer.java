	package com.intertek.domain;

	import org.springmodules.validation.bean.conf.loader.annotation.handler.CascadeValidation;

import com.intertek.entity.ContactCust;
import com.intertek.entity.ContractCust;
import com.intertek.entity.CreditAnalyst;
import com.intertek.entity.CustAddressLanguage;
import com.intertek.entity.CustVatRegistration;
import com.intertek.entity.Customer;
import com.intertek.entity.CustomerLanguage;
//import com.intertek.entity.CustomerNote;
import com.intertek.entity.Notes;

import java.util.*;

	public class AddCustomer 
	{
	  @CascadeValidation
	  private Customer customer;
	  private CreditAnalyst creditAnalyst;

	  private AddCustomerAddress[] addCustomerAddresses;
	  private ContactCust[] contactCusts;
	  private ContractCust[] contractCusts;
	  private CustVatRegistration[] custVatRegistrations;
	  //private CustomerNote[] customerNotes;
	  private List <Notes>delNotesList;
	  
	  private List <Notes>customerNotesList;
	  
	  private int custAddrSeqCount;
	  private long noteId;
	  private String addOrDeleteCustAddrSeq;
	  private String addOrDeleteCustAddrDtl;
	  private String addOrDeleteCustNote;
	  private int custAddrSeqIndex;
	  private int custAddrDtlIndex;
	  private String note;
	  private String noteSummary;
	  private String noteAddDateTime;
	  private String noteAddBy;
	  private int custNoteEditFlag;
	  private String custAddDateTime;
	  private String custAddBy;
	  private String custModiDateTime;
	  private String custModiBy;
	  
	  private int custVatRegCount;
	  private int custNoteCount;
	  private String addOrDeleteCustVatReg;
	  

	  private int custVatRegIndex;
	  

	  private int contactCustCount;
	  private String addCustContact;
	  private int contactCustIndex;
	  private String tabName = "0";

	  private int contractCustCount;
	  private String addOrDeleteCustContract;
	  private int contractCustIndex;
 private String busFlag;

	private String[] contactNames;
private String inputFieldId;
	  private String contactFlag;
	  private String rowNum;
private String addSeqFlag;
private String custCode;
private String vatCodeId;
private String contractFlag;
private String vesselFlag;
 private String divFlag;
	  private String sortFlag;
	  private String navFlag;
	  private String pageNumber;
	  
	  private CustomerAddresses customerAddresses;
	  private CustomerContacts customerContacts;
	  // newly added regd multilingual
	  private CustomerLanguage customerLanguage;
	  private CustAddressLanguage custAddressLanguage;
	  
	  private String searchForm;
	  private String divName;
	  private String divBody;
	 // private String custAddrId;
	 // private String contactAddrId;
	  private long custAddrId;
	  private long contactAddrId;
	  private String custAddress;
	  private String contactAddress;
	  private String lcode;
	  private String languageFlag;  
	  private boolean notesViewOnly;
	  
	//  private List custAddrLanguage;	

	  private Map custAddrLangmap;
private String custLanguageFlag;
	  private String contactLanguageFlag;

		 
	  //end
	  
	  private String contactEnable;
	  private String contactEnableFlag;
	  
	  private String parentCompanyName;
	  /* For iTrack#135193-START*/
	  private String reqForm;
	  /* For iTrack#135193-END*/
	  public void setCustomer(Customer customer)
	  {
		this.customer = customer;
	  }

	  public Customer getCustomer()
	  {
		return customer;
	  }

	    public void setCreditAnalyst(CreditAnalyst creditAnalyst)
	  {
		this.creditAnalyst = creditAnalyst;
	  }

	  public CreditAnalyst getCreditAnalyst()
	  {
		return creditAnalyst;
	  }

	  public AddCustomerAddress[] getAddCustomerAddresses()
	  {
		return addCustomerAddresses;
	  }

	  public void setAddCustomerAddresses(AddCustomerAddress[] addCustomerAddresses)
	  {
		this.addCustomerAddresses = addCustomerAddresses;
	  }


	 public ContactCust[] getContactCusts()
	  {
		return contactCusts;
	  }

	  public void setContactCusts(ContactCust[] contactCusts)
	  {
		this.contactCusts = contactCusts;
	  }

	 public String[] getContactNames()
	  {
		return contactNames;
	  }

	  public void setContactNames(String[] contactNames)
	  {
		this.contactNames = contactNames;
	  }

	   public ContractCust[]  getContractCusts()
	  {
		return contractCusts;
	  }

	  public void  setContractCusts(ContractCust[]  contractCusts)
	  {
		this.contractCusts = contractCusts;
	  }


	 public int getCustAddrSeqCount()
	  {
		return custAddrSeqCount;
	  }

	  public void setCustAddrSeqCount(int custAddrSeqCount)
	  {
		this.custAddrSeqCount = custAddrSeqCount;
	  }

	  public String getAddOrDeleteCustAddrSeq()
	  {
			return addOrDeleteCustAddrSeq;
		}

		public void setAddOrDeleteCustAddrSeq(String addOrDeleteCustAddrSeq)
		{
			this.addOrDeleteCustAddrSeq = addOrDeleteCustAddrSeq;
		}

	  public String getAddOrDeleteCustAddrDtl()
	  {
			return addOrDeleteCustAddrDtl;
		}

		public void setAddOrDeleteCustAddrDtl(String addOrDeleteCustAddrDtl)
		{
			this.addOrDeleteCustAddrDtl = addOrDeleteCustAddrDtl;
		}

		public int getCustAddrSeqIndex()
		{
			return custAddrSeqIndex;
		}

		public void setCustAddrSeqIndex(int custAddrSeqIndex)
		{
			this.custAddrSeqIndex = custAddrSeqIndex;
		}

		public int getCustAddrDtlIndex()
		{
			return custAddrDtlIndex;
		}

		public void setCustAddrDtlIndex(int custAddrDtlIndex)
		{
			this.custAddrDtlIndex = custAddrDtlIndex;
		}

	 public int getContactCustCount()
	  {
		return contactCustCount;
	  }

	  public void setContactCustCount(int contactCustCount)
	  {
		this.contactCustCount = contactCustCount;
	  }


		public int getContactCustIndex()
		{
			return contactCustIndex;
		}

		public void setContactCustIndex(int contactCustIndex)
		{
			this.contactCustIndex = contactCustIndex;
		}




	  public int getContractCustCount()
	  {
		return contractCustCount;
	  }

	  public void setContractCustCount(int contractCustCount)
	  {
		this.contractCustCount = contractCustCount;
	  }

	  public String getAddOrDeleteCustContract()
	  {
			return addOrDeleteCustContract;
		}

		public void setAddOrDeleteCustContract(String addOrDeleteCustContract)
		{
			this.addOrDeleteCustContract = addOrDeleteCustContract;
		}

		public int  getContractCustIndex()
		{
			return contractCustIndex;
		}

		public void setContractCustIndex(int contractCustIndex)
		{
			this.contractCustIndex = contractCustIndex;
		}
     public String getTabName()
     {
		return tabName;
 	}

	public void setTabName(String tabName)
	{
		this.tabName = tabName;
	}

 public String getContactFlag()
	  {
			return contactFlag;
	  }

		public void setContactFlag(String contactFlag)
	 {
		this.contactFlag = contactFlag;
	 }
	 public String getRowNum()
	 {
	 	return rowNum;
	 }

	public void setRowNum(String rowNum)
	{
		this.rowNum = rowNum;
	}
public String getInputFieldId()
{
return inputFieldId;
}

public void setInputFieldId(String inputFieldId)
{
this.inputFieldId = inputFieldId;
}

public String getAddSeqFlag()
{
return addSeqFlag;
}

public void setAddSeqFlag(String addSeqFlag)
{
this.addSeqFlag = addSeqFlag;
}

public void setCustCode(String custCode)
{ 
this.custCode=custCode;
}

public String getCustCode()
{
return custCode;
}

public String getVatCodeId()
{
return vatCodeId;
}

public void setVatCodeId(String vatCodeId)
{
this.vatCodeId = vatCodeId;
}
public String getContractFlag()
{
return contractFlag;
}

public void setContractFlag(String contractFlag)
{
this.contractFlag = contractFlag;
}

public String getDivFlag()
	{  return divFlag;	}

  public void setDivFlag(String divFlag)
	{	this.divFlag = divFlag;  }

public String getVesselFlag()
{
return vesselFlag;
}

public void setVesselFlag(String vesselFlag)
{
this.vesselFlag = vesselFlag;
}
public String getAddCustContact() {
	return addCustContact;
}

public void setAddCustContact(String addCustContact) {
	this.addCustContact = addCustContact;
}

public String getSortFlag() {
	return sortFlag;
}

public void setSortFlag(String sortFlag) {
	this.sortFlag = sortFlag;
}
public String getBusFlag()
{
	return busFlag;
}
public void setBusFlag(String busFlag)
{
this.busFlag=busFlag;
}

public String getNavFlag() {
	return navFlag;
}

public void setNavFlag(String navFlag) {
	this.navFlag = navFlag;
}

public CustomerAddresses getCustomerAddresses() {
	return customerAddresses;
}

public void setCustomerAddresses(CustomerAddresses customerAddresses) {
	this.customerAddresses = customerAddresses;
}

public String getPageNumber() {
	return pageNumber;
}

public void setPageNumber(String pageNumber) {
	this.pageNumber = pageNumber;
}

public CustomerContacts getCustomerContacts() {
	return customerContacts;
}

public void setCustomerContacts(CustomerContacts customerContacts) {
	this.customerContacts = customerContacts;
}

public String getAddOrDeleteCustVatReg() {
	return addOrDeleteCustVatReg;
}

public void setAddOrDeleteCustVatReg(String addOrDeleteCustVatReg) {
	this.addOrDeleteCustVatReg = addOrDeleteCustVatReg;
}

public int getCustVatRegCount() {
	return custVatRegCount;
}

public void setCustVatRegCount(int custVatRegCount) {
	this.custVatRegCount = custVatRegCount;
}

public int getCustVatRegIndex() {
	return custVatRegIndex;
}

public void setCustVatRegIndex(int custVatRegIndex) {
	this.custVatRegIndex = custVatRegIndex;
}

public CustVatRegistration[] getCustVatRegistrations() {
	return custVatRegistrations;
}

public void setCustVatRegistrations(CustVatRegistration[] custVatRegistrations) {
	this.custVatRegistrations = custVatRegistrations;
}


public String getParentCompanyName() {
	return parentCompanyName;
}

public void setParentCompanyName(String parentCompanyName) {
	this.parentCompanyName = parentCompanyName;
}

public CustomerLanguage getCustomerLanguage() {
	return customerLanguage;
}

public void setCustomerLanguage(CustomerLanguage customerLanguage) {
	this.customerLanguage = customerLanguage;
}

public CustAddressLanguage getCustAddressLanguage() {
	return custAddressLanguage;
}

public void setCustAddressLanguage(CustAddressLanguage custAddressLanguage) {
	this.custAddressLanguage = custAddressLanguage;
}

public String getDivBody() {
	return divBody;
}

public void setDivBody(String divBody) {
	this.divBody = divBody;
}

public String getDivName() {
	return divName;
}

public void setDivName(String divName) {
	this.divName = divName;
}

public String getSearchForm() {
	return searchForm;
}

public void setSearchForm(String searchForm) {
	this.searchForm = searchForm;
}

public long getContactAddrId() {
	return contactAddrId;
}

public void setContactAddrId(long contactAddrId) {
	this.contactAddrId = contactAddrId;
}

public long getCustAddrId() {
	return custAddrId;
}

public void setCustAddrId(long custAddrId) {
	this.custAddrId = custAddrId;
}

public String getContactAddress() {
	return contactAddress;
}

public void setContactAddress(String contactAddress) {
	this.contactAddress = contactAddress;
}

public String getCustAddress() {
	return custAddress;
}

public void setCustAddress(String custAddress) {
	this.custAddress = custAddress;
}

public String getLanguageFlag() {
	return languageFlag;
}

public void setLanguageFlag(String languageFlag) {
	this.languageFlag = languageFlag;
}

public String getLcode() {
	return lcode;
}

public void setLcode(String lcode) {
	this.lcode = lcode;
}

public String getContactLanguageFlag() {
	return contactLanguageFlag;
}

public void setContactLanguageFlag(String contactLanguageFlag) {
	this.contactLanguageFlag = contactLanguageFlag;
}

public String getCustLanguageFlag() {
	return custLanguageFlag;
}

public void setCustLanguageFlag(String custLanguageFlag) {
	this.custLanguageFlag = custLanguageFlag;
}
/*public List getCustAddrLanguage() {
	return custAddrLanguage;
}

public void setCustAddrLanguage(List custAddrLanguage) {
	this.custAddrLanguage = custAddrLanguage;
}*/

public Map getCustAddrLangmap() {
	return custAddrLangmap;
}

public void setCustAddrLangmap(Map custAddrLangmap) {
	this.custAddrLangmap = custAddrLangmap;
}

public String getContactEnable() {
	return contactEnable;
}

public void setContactEnable(String contactEnable) {
	this.contactEnable = contactEnable;
}

public String getContactEnableFlag() {
	return contactEnableFlag;
}

public void setContactEnableFlag(String contactEnableFlag) {
	this.contactEnableFlag = contactEnableFlag;
}

/*public CustomerNote[] getCustomerNotes() {
	return customerNotes;
}

public void setCustomerNotes(CustomerNote[] customerNotes) {
	this.customerNotes = customerNotes;
}*/

public int getCustNoteCount() {
	return custNoteCount;
}

public void setCustNoteCount(int custNoteCount) {
	this.custNoteCount = custNoteCount;
}

public String getAddOrDeleteCustNote() {
	return addOrDeleteCustNote;
}

public void setAddOrDeleteCustNote(String addOrDeleteCustNote) {
	this.addOrDeleteCustNote = addOrDeleteCustNote;
}

/*public List<CustomerNote> getCustomerNotesList() {
	return customerNotesList;
}

public void setCustomerNotesList(List<CustomerNote> customerNotesList) {
	this.customerNotesList = customerNotesList;
}*/

public String getNoteAddDateTime() {
	return noteAddDateTime;
}

public void setNoteAddDateTime(String noteAddDateTime) {
	this.noteAddDateTime = noteAddDateTime;
}

public String getNoteAddBy() {
	return noteAddBy;
}

public void setNoteAddBy(String noteAddBy) {
	this.noteAddBy = noteAddBy;
}

public String getCustAddDateTime() {
	return custAddDateTime;
}

public void setCustAddDateTime(String custAddDateTime) {
	this.custAddDateTime = custAddDateTime;
}

public String getCustAddBy() {
	return custAddBy;
}

public void setCustAddBy(String custAddBy) {
	this.custAddBy = custAddBy;
}

public String getCustModiDateTime() {
	return custModiDateTime;
}

public void setCustModiDateTime(String custModiDateTime) {
	this.custModiDateTime = custModiDateTime;
}

public String getCustModiBy() {
	return custModiBy;
}

public void setCustModiBy(String custModiBy) {
	this.custModiBy = custModiBy;
}

public String getNote() {
	return note;
}

public void setNote(String note) {
	this.note = note;
}

public String getNoteSummary() {
	return noteSummary;
}

public void setNoteSummary(String noteSummary) {
	this.noteSummary = noteSummary;
}

public int getCustNoteEditFlag() {
	return custNoteEditFlag;
}

public void setCustNoteEditFlag(int custNoteEditFlag) {
	this.custNoteEditFlag = custNoteEditFlag;
}

public List<Notes> getCustomerNotesList() {
	return customerNotesList;
}

public void setCustomerNotesList(List<Notes> customerNotesList) {
	this.customerNotesList = customerNotesList;
}

public List<Notes> getDelNotesList() {
	return delNotesList;
}

public void setDelNotesList(List<Notes> delNotesList) {
	this.delNotesList = delNotesList;
}

public long getNoteId() {
	return noteId;
}

public void setNoteId(long noteId) {
	this.noteId = noteId;
}

public boolean isNotesViewOnly() {
	return notesViewOnly;
}

public void setNotesViewOnly(boolean notesViewOnly) {
	this.notesViewOnly = notesViewOnly;
}
public String getReqForm() {
	return reqForm;
}

public void setReqForm(String reqForm) {
	this.reqForm = reqForm;
}
}
