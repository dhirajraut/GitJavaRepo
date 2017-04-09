package com.intertek.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.intertek.entity.Contract;
import com.intertek.entity.ContractAttach;
import com.intertek.entity.Notes;

public class AddContract extends BaseEditContract
{
  private AddContractCust[] addContractCusts;
  private String status;
  private Long id;
  private String tabName = "0";
  private String addOrDelete;
  private int index;
  private ContractAttach[] contractAttaches;

  private int contractAttachCount;
  private int contractAttachIndex;
  private String uploadedFileName ="";
  private String deleteAttach;
  private int contractCustCount;
  private int contactCustCount;
  private int   contractCustContactIndex;
  private int contractCustIndex;
  private String addOrDeleteContractContact;

  private String inputFieldId;
  private String rowNum;
  private String rowNumContact;
  private String searchForm;
  private String contSeqFlag;
  private String custCode;
  private String contractCode;
  private String contactSearchFlag;
  private String userFlag;
  private String div1;
  private String div2;
  private String showAttachFile;

  private boolean isNewFlag;
  private List cfgContractExtList;

  private int activeCfgContractIndex;

  private List referenceFieldList;
  private List pbReferenceFieldList;

  private String ctrCode;
  private String pageNum;
  private String pageNavigateFlag;  
  private String searchContactId;
  private String searchFirstName;
  private String searchLastName;
  private String searchCustomerId;
  private String searchCustomerName;
  private String searchContactName;
  private String loadCustContflag; 
  
  private int contactSearchOperator;
  private int customerSearchOperator;
  //START For iTrack #103082	
  private String[] customerNotes;
  private String note;
  private String noteSummary;
  private String noteAddDateTime;
  private String noteAddBy;
  private int contractNoteEditFlag;
  private String contractAddDateTime;
  private String contractAddBy;
  private String contractModiDateTime;
  private String contractModiBy;
  private int contractNoteCount;
  private long noteId;
  private List <Notes>delNotesList;
  private List <Notes>contractNotesList;
  private String addOrDeleteContractNote;
  //End
  public String getStatus()
  {
    return this.status;
  }

  public void setStatus(String status)
  {
    this.status = status;
  }

  public Long getId()
  {
    return this.id;
  }

  public void setId(Long id)
  {
    this.id = id;
  }

  public String getTabName()
  {
    return tabName;
  }

  public void setTabName(String tabName)
  {
    this.tabName = tabName;
  }

  public String getDeleteAttach()
  {
    return deleteAttach;
  }

  public void setDeleteAttach(String deleteAttach)
  {
    this.deleteAttach = deleteAttach;
  }

  public ContractAttach[] getContractAttaches()
  {
    return contractAttaches;
  }

  public void setContractAttaches(ContractAttach[] contractAttaches)
  {
    this.contractAttaches = contractAttaches;
  }

  public int getContractAttachCount()
  {
    return contractAttachCount;
  }

  public void setContractAttachCount(int contractAttachCount)
  {
    this.contractAttachCount = contractAttachCount;
  }

  public int getContractAttachIndex()
  {
    return contractAttachIndex;
  }

  public void setContractAttachIndex(int contractAttachIndex)
  {
    this.contractAttachIndex = contractAttachIndex;
  }


  public String getUploadedFileName()
  {
    return uploadedFileName;
  }

  public void setUploadedFileName(String uploadedFileName)
  {
    this.uploadedFileName = uploadedFileName;
  }

  public String getAddOrDelete()
  {
    return addOrDelete;
  }

  public void setAddOrDelete(String addOrDelete)
  {
    this.addOrDelete = addOrDelete;
  }

  public String getAddOrDeleteContractContact()
  {
    return addOrDeleteContractContact;
  }

  public void setAddOrDeleteContractContact(String addOrDeleteContractContact)
  {
    this.addOrDeleteContractContact = addOrDeleteContractContact;
  }

  public int getIndex()
  {
    return index;
  }

  public void setIndex(int index)
  {
    this.index = index;
  }

  public int getContractCustCount()
  {
    return contractCustCount;
  }

  public void setContractCustCount(int contractCustCount)
  {
    this.contractCustCount = contractCustCount;
  }


  public AddContractCust[] getAddContractCusts()
  {
    return this.addContractCusts;
  }

  public void setAddContractCusts(AddContractCust[] addContractCusts)
  {
    this.addContractCusts=addContractCusts;
  }

  public int getContactCustCount()
  {
    return contactCustCount;
  }

  public void setContactCustCount(int contactCustCount)
  {
    this.contactCustCount = contactCustCount;
  }

  public int getContractCustContactIndex()
  {
    return contractCustContactIndex;
  }

  public void setContractCustContactIndex(int contractCustContactIndex)
  {
    this.contractCustContactIndex= contractCustContactIndex;
  }

  public int getContractCustIndex()
  {
    return contractCustIndex;
  }

  public void setContractCustIndex(int contractCustIndex)
  {
    this.contractCustIndex=contractCustIndex;
  }

  public String getInputFieldId()
  {
    return inputFieldId;
  }

  public void setInputFieldId(String inputFieldId)
  {
    this.inputFieldId = inputFieldId;
  }

  public String getRowNum()
  {
    return rowNum;
  }

  public void setRowNum(String rowNum)
  {
    this.rowNum = rowNum;
  }

  public String getSearchForm()
  {
    return searchForm;
  }

  public void setSearchForm(String searchForm)
  {
    this.searchForm = searchForm;
  }
  public String getContSeqFlag()
  {
    return contSeqFlag;
  }

  public void setContSeqFlag(String contSeqFlag)
  {
    this.contSeqFlag = contSeqFlag;
  }

  public void setCustCode(String custCode)
  {
    this.custCode=custCode;
  }

  public String getCustCode()
  {
    return custCode;
  }

  public void setContractCode(String contractCode)
  {
    this.contractCode=contractCode;
  }

  public String getContractCode()
  {
    return contractCode;
  }

  public String getContactSearchFlag()
  {
    return contactSearchFlag;
  }

  public void setContactSearchFlag(String contactSearchFlag)
  {
    this.contactSearchFlag = contactSearchFlag;
  }

  public String getRowNumContact()
  {
    return rowNumContact;
  }

  public void setRowNumContact(String rowNumContact)
  {
    this.rowNumContact = rowNumContact;
  }

  public String getUserFlag()
  {
    return userFlag;
  }

  public void setUserFlag(String userFlag)
  {
    this.userFlag = userFlag;
  }

  public String getDiv1()
  {
    return div1;
  }

  public void setDiv1(String div1)
  {
    this.div1 = div1;
  }

  public String getDiv2()
  {
    return div2;
  }

  public void setDiv2(String div2)
  {
    this.div2 = div2;
  }

  public boolean getIsNewFlag()
  {
    return isNewFlag;
  }

  public void setIsNewFlag(boolean isNewFlag)
  {
    this.isNewFlag = isNewFlag;
  }

  public String getShowAttachFile() {
    return showAttachFile;
  }

  public void setShowAttachFile(String showAttachFile) {
    this.showAttachFile = showAttachFile;
  }

  public List getCfgContractExtList()
  {
    return cfgContractExtList;
  }

  public void setCfgContractExtList(List cfgContractExtList)
  {
    this.cfgContractExtList = cfgContractExtList;
  }

  public void setActiveCfgContractIndex(int activeCfgContractIndex)
  {
    this.activeCfgContractIndex = activeCfgContractIndex;
  }

  public int getActiveCfgContractIndex()
  {
    return activeCfgContractIndex;
  }

  public void setReferenceFieldList(List referenceFieldList)
  {
    this.referenceFieldList = referenceFieldList;
  }

  public List getReferenceFieldList()
  {
    return referenceFieldList;
  }

  public void setPbReferenceFieldList(List pbReferenceFieldList)
  {
    this.pbReferenceFieldList = pbReferenceFieldList;
  }

  public List getPbReferenceFieldList()
  {
    return pbReferenceFieldList;
  }

public String getCtrCode() {
	return ctrCode;
}

public void setCtrCode(String ctrCode) {
	this.ctrCode = ctrCode;
}

public String getPageNum() {
	return pageNum;
}

public void setPageNum(String pageNum) {
	this.pageNum = pageNum;
}

public String getPageNavigateFlag() {
	return pageNavigateFlag;
}

public void setPageNavigateFlag(String pageNavigateFlag) {
	this.pageNavigateFlag = pageNavigateFlag;
}

public String getSearchContactId() {
	return searchContactId;
}

public void setSearchContactId(String searchContactId) {
	this.searchContactId = searchContactId;
}

public String getSearchFirstName() {
	return searchFirstName;
}

public void setSearchFirstName(String searchFirstName) {
	this.searchFirstName = searchFirstName;
}

public String getSearchLastName() {
	return searchLastName;
}

public void setSearchLastName(String searchLastName) {
	this.searchLastName = searchLastName;
}

public String getSearchCustomerId() {
	return searchCustomerId;
}

public void setSearchCustomerId(String searchCustomerId) {
	this.searchCustomerId = searchCustomerId;
}

public String getSearchCustomerName() {
	return searchCustomerName;
}

public void setSearchCustomerName(String searchCustomerName) {
	this.searchCustomerName = searchCustomerName;
}

public String getSearchContactName() {
	return searchContactName;
}

public void setSearchContactName(String searchContactName) {
	this.searchContactName = searchContactName;
}

public String getLoadCustContflag() {
	return loadCustContflag;
}

public void setLoadCustContflag(String loadCustContflag) {
	this.loadCustContflag = loadCustContflag;
}

public int getContactSearchOperator() {
	return contactSearchOperator;
}

public void setContactSearchOperator(int contactSearchOperator) {
	this.contactSearchOperator = contactSearchOperator;
}

public int getCustomerSearchOperator() {
	return customerSearchOperator;
}

public void setCustomerSearchOperator(int customerSearchOperator) {
	this.customerSearchOperator = customerSearchOperator;
}

public String[] getCustomerNotes() {
	return customerNotes;
}

public void setCustomerNotes(String[] customerNote) {
	this.customerNotes = customerNote;
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

public int getContractNoteEditFlag() {
	return contractNoteEditFlag;
}

public void setContractNoteEditFlag(int contractNoteEditFlag) {
	this.contractNoteEditFlag = contractNoteEditFlag;
}

public String getContractAddDateTime() {
	return contractAddDateTime;
}

public void setContractAddDateTime(String contractAddDateTime) {
	this.contractAddDateTime = contractAddDateTime;
}

public String getContractAddBy() {
	return contractAddBy;
}

public void setContractAddBy(String contractAddBy) {
	this.contractAddBy = contractAddBy;
}

public String getContractModiDateTime() {
	return contractModiDateTime;
}

public void setContractModiDateTime(String contractModiDateTime) {
	this.contractModiDateTime = contractModiDateTime;
}

public String getContractModiBy() {
	return contractModiBy;
}

public void setContractModiBy(String contractModiBy) {
	this.contractModiBy = contractModiBy;
}

public int getContractNoteCount() {
	return contractNoteCount;
}

public void setContractNoteCount(int contractNoteCount) {
	this.contractNoteCount = contractNoteCount;
}

public long getNoteId() {
	return noteId;
}

public void setNoteId(long noteId) {
	this.noteId = noteId;
}

public List<Notes> getDelNotesList() {
	return delNotesList;
}

public void setDelNotesList(List<Notes> delNotesList) {
	this.delNotesList = delNotesList;
}

public List<Notes> getContractNotesList() {
	return contractNotesList;
}

public void setContractNotesList(List<Notes> contractNotesList) {
	this.contractNotesList = contractNotesList;
}

public String getAddOrDeleteContractNote() {
	return addOrDeleteContractNote;
}

public void setAddOrDeleteContractNote(String addOrDeleteContractNote) {
	this.addOrDeleteContractNote = addOrDeleteContractNote;
}
}
