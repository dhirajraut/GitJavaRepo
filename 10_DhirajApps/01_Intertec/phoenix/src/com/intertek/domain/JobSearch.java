package com.intertek.domain;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.intertek.entity.JobContract;
import com.intertek.entity.LogConfigList;
import com.intertek.util.DateUtil;

public class JobSearch extends Search
{

  private StringSearchField businessUnit;
  private StringSearchField branch;
  private StringSearchField status;
  private StringSearchField jobType;
  private StringSearchField fromJobId;
  private StringSearchField toJobId;
  private DateSearchField fromJobFinshDate;
  private DateSearchField toJobFinshDate;
  private DateSearchField etaFrom;
  private DateSearchField etaTo;
  private DateSearchField nomDateFrom;
  private DateSearchField nomDateTo;
  private DateSearchField jobFinshDate;
  private StringSearchField vessel;
  private StringSearchField product;
  private StringSearchField custRefNum;
  private StringSearchField icbRefNum;
  private StringSearchField invoice;
  private StringSearchField dispatchStatus;
  private StringSearchField port;
  private StringSearchField svcLocation;
  private StringSearchField companyId;
  private StringSearchField company;
  private StringSearchField contractId;
  private StringSearchField billingContact;
  private LongSearchField schedulerId;
  //private LongSearchField invoice;
  private StringSearchField scheduler;
  private String sortFlag;
  private String tabFlag="criteria";
  private String dateFormat;
  private StringSearchField contractDescription;
  private StringSearchField contractStatus;
  private StringSearchField modifiedBy;
  private StringSearchField createdBy;
  private StringSearchField invoiceStatus;
  private StringSearchField billingContactId;
  private StringSearchField searchResults;
  private StringSearchField monthlyJobId;
  private LongSearchField confListId;
  private StringSearchField confListName;
  private String fromDate;
  private String toDate;
  

 private String inputFieldId;
 private String pCustCode;
  private String div1;
  private String div2;
  private String pageName;
  private String submitFlag;
  private String rowNum;
  private String reqFormFlag;
 private AddJobContract addJobContract;
private JobContract jobContract;
  private String refreshing="false";
  private String jxcel="false";
    private String jmail="false";
	private String searchForm;
	private String columnFlag;
	private String hrefJobNumber;
	private String hrefValue;
	private String hrefForm;
	private String sessionFlag;
	private String emailCol;

    
    private String chosenJobContracts;
    private List jobNumbers;
    private String showAllFlag="false";
    private int noOfJobs;
	private List hideAndSort;
	private List resultSortOrder;
	private List freezedList;
	private List displayOrder;
	private List defaultHideandSort;
    private List freezedList1;
	private List freezedList2;
	private List freezedList3;
	private List freezedList4;
	private List sessionFreezedList1;
	private List sessionFreezedList2;
	private List sessionFreezedList3;
	private List sessionFreezedList4;
	private List sessionHideColumns;
	private List sessionHideAndSort;
	private List sessionResultSortOrder;
	
	
	
	private String saveFlag;
	private String searchFlag="false";
	private String confName;
	private String userName;
	private String configFlag;
	private String firstTab;
	private String secondTab;
	private String thirdTab;
	private String fourthTab;
	private long confId;


  private LogConfigList logConfigList;
	
    private String mainTabFlag;
    private String dispatchTabFlag;
    private String billingTabFlag;
    private String processLogTabFlag;
	//newly added
	private String showAllTabFlag;
    private String noteFlag;
	//up to here
    private List mainColumns;
    private List dispatchColumns;
    private List billingColumns;
    private List processColumns;
    private List mainHeaderColumns;
    private List dispatchHeaderColumns;
    private List billingHeaderColumns;
    private List processHeaderColumns;
    private List mainFreezeHeaderColumns;
    private List dispatchFreezeHeaderColumns;
    private List billingFreezeHeaderColumns;
    private List processFreezeHeaderColumns;
    
    private String etaDateColumn;
	private String showAllExcel;
	private String sortOrderFlag;
	private String currentSortFlag;
	private String prevSortFlag;
	private long jobSearchCriteriaId=-1; 
	private String jobSearchCriteriaName="";

public String getJobSearchCriteriaName() {
		return jobSearchCriteriaName;
	}

	public void setJobSearchCriteriaName(String jobSearchCriteriaName) {
		this.jobSearchCriteriaName = jobSearchCriteriaName;
	}

public JobSearch()
  {
    businessUnit = new StringSearchField();
    branch = new StringSearchField();
    status = new StringSearchField();
    jobType = new StringSearchField();
    fromJobId = new StringSearchField();
    toJobId = new StringSearchField();
    fromJobFinshDate = new DateSearchField();
    toJobFinshDate = new DateSearchField();
    etaFrom = new DateSearchField();
    etaTo = new DateSearchField();
    nomDateFrom = new DateSearchField();
    nomDateTo = new DateSearchField();
    jobFinshDate = new DateSearchField();
    vessel = new StringSearchField();
    product = new StringSearchField();
    custRefNum = new StringSearchField();
    icbRefNum = new StringSearchField();
    invoice = new StringSearchField();
    dispatchStatus = new StringSearchField();
    port = new StringSearchField();
    svcLocation = new StringSearchField();
    companyId = new StringSearchField();
    company = new StringSearchField();
    contractId = new StringSearchField();
	billingContactId=new StringSearchField();
    billingContact = new StringSearchField();
    schedulerId = new LongSearchField();
    scheduler = new StringSearchField();
    contractDescription = new StringSearchField();
    contractStatus = new StringSearchField();
    modifiedBy = new StringSearchField();
    createdBy = new StringSearchField(); 
	invoiceStatus=new StringSearchField();
	searchResults=new StringSearchField();
   monthlyJobId = new StringSearchField();
   confListId=new LongSearchField();
  confListName=new StringSearchField();
  }

  public StringSearchField getBusinessUnit()
  {
    return businessUnit;
  }

  public void setBusinessUnit(StringSearchField businessUnit)
  {
    this.businessUnit = businessUnit;
  }

  public StringSearchField getBranch()
  {
    return branch;
  }

  public void setBranch(StringSearchField branch)
  {
    this.branch = branch;
  }

  public StringSearchField getStatus()
  {
    return status;
  }

  public void setStatus(StringSearchField status)
  {
    this.status = status;
  }

  public StringSearchField getJobType()
  {
    return jobType;
  }

  public void setJobType(StringSearchField jobType)
  {
    this.jobType = jobType;
  }

  public StringSearchField getFromJobId()
  {
    return fromJobId;
  }

  public void setFromJobId(StringSearchField fromJobId)
  {
    this.fromJobId = fromJobId;
  }

  public StringSearchField getToJobId()
  {
    return toJobId;
  }

  public void setToJobId(StringSearchField toJobId)
  {
    this.toJobId = toJobId;
  }

  public DateSearchField getEtaFrom()
  {
    return etaFrom;
  }

  public void setEtaFrom(DateSearchField etaFrom)
  {
    this.etaFrom = etaFrom;
  }

  public DateSearchField getEtaTo()
  {
    return etaTo;
  }

  public void setEtaTo(DateSearchField etaTo)
  {
    this.etaTo = etaTo;
  }

  public DateSearchField getNomDateFrom()
  {
    return nomDateFrom;
  }

  public void setNomDateFrom(DateSearchField nomDateFrom)
  {
    this.nomDateFrom = nomDateFrom;
  }

  public DateSearchField getNomDateTo()
  {
    return nomDateTo;
  }

  public void setNomDateTo(DateSearchField nomDateTo)
  {
    this.nomDateTo = nomDateTo;
  }

  public StringSearchField getVessel()
  {
    return vessel;
  }

  public void setVessel(StringSearchField vessel)
  {
    this.vessel = vessel;
  }

  public StringSearchField getProduct()
  {
    return product;
  }

  public void setProduct(StringSearchField product)
  {
    this.product = product;
  }

  public StringSearchField getCustRefNum()
  {
    return custRefNum;
  }

  public void setCustRefNum(StringSearchField custRefNum)
  {
    this.custRefNum = custRefNum;
  }

  public StringSearchField getIcbRefNum()
  {
    return icbRefNum;
  }

  public void setIcbRefNum(StringSearchField icbRefNum)
  {
    this.icbRefNum = icbRefNum;
  }

  public StringSearchField getInvoice()
  {
    return invoice;
  }

  public void setInvoice(StringSearchField invoice)
  {
    this.invoice = invoice;
  }

  public StringSearchField getDispatchStatus()
  {
    return dispatchStatus;
  }

  public void setDispatchStatus(StringSearchField dispatchStatus)
  {
    this.dispatchStatus = dispatchStatus;
  }

  public StringSearchField getPort()
  {
    return port;
  }

  public void setPort(StringSearchField port)
  {
    this.port = port;
  }

  public StringSearchField getSvcLocation()
  {
    return svcLocation;
  }

  public void setSvcLocation(StringSearchField svcLocation)
  {
    this.svcLocation = svcLocation;
  }

  public StringSearchField getCompanyId()
  {
    return companyId;
  }

  public void setCompanyId(StringSearchField companyId)
  {
    this.companyId = companyId;
  }

  public StringSearchField getCompany()
  {
    return company;
  }

  public void setCompany(StringSearchField company)
  {
    this.company = company;
  }

  public StringSearchField getContractId()
  {
    return contractId;
  }

  public void setContractId(StringSearchField contractId)
  {
    this.contractId = contractId;
  }

  public StringSearchField getBillingContact()
  {
    return billingContact;
  }

  public void setBillingContact(StringSearchField billingContact)
  {
    this.billingContact = billingContact;
  }

  public LongSearchField getSchedulerId()
  {
    return schedulerId;
  }

  public void setSchedulerId(LongSearchField schedulerId)
  {
    this.schedulerId = schedulerId;
  }

  public StringSearchField getScheduler()
  {
    return scheduler;
  }

  public void setScheduler(StringSearchField scheduler)
  {
    this.scheduler = scheduler;
  }
public String getRefreshing()
{
return refreshing;
}

public void setRefreshing(String refreshing)
{
this.refreshing=refreshing;
}
public String getJxcel()
{
return jxcel;
}
public void setJxcel(String jxcel)
{
this.jxcel=jxcel;
}
public JobContract getJobContract()
{
return jobContract;
}

public void setJobContract(JobContract jobContract)
{
this.jobContract=jobContract;
}

public AddJobContract getAddJobContract() 
{
return addJobContract;
}

public void setAddJobContract(AddJobContract addJobContract) 
{
this.addJobContract = addJobContract;
}

public String getDiv1() {
	return div1;
}


public void setDiv1(String div1) {
	this.div1 = div1;
}


public String getDiv2() {
	return div2;
}


public void setDiv2(String div2) {
	this.div2 = div2;
}


public String getPageName() {
	return pageName;
}


public void setPageName(String pageName) {
	this.pageName = pageName;
}


public String getSubmitFlag() {
	return submitFlag;
}


public void setSubmitFlag(String submitFlag) {
	this.submitFlag = submitFlag;
}


public String getInputFieldId() {
	return inputFieldId;
}


public void setInputFieldId(String inputFieldId) {
	this.inputFieldId = inputFieldId;
}


public String getRowNum() {
	return rowNum;
}


public void setRowNum(String rowNum) {
	this.rowNum = rowNum;
}


public String getReqFormFlag() {
	return reqFormFlag;
}


public void setReqFormFlag(String reqFormFlag) {
	this.reqFormFlag = reqFormFlag;
}


public String getJmail()
{
return jmail;
}
public void setJmail(String jmail)
{
this.jmail=jmail;
}
public String getSortFlag() {
	return sortFlag;
}

public void setSortFlag(String sortFlag) {
	this.sortFlag = sortFlag;
}
public String getDateFormat() {
	return dateFormat;
}

public void setDateFormat(String dateFormat) {
	this.dateFormat = dateFormat;
}
public StringSearchField getContractDescription() {
	return contractDescription;
}

public void setContractDescription(StringSearchField contractDescription) {
	this.contractDescription = contractDescription;
}

public StringSearchField getContractStatus() {
	return contractStatus;
}

public void setContractStatus(StringSearchField contractStatus) {
	this.contractStatus = contractStatus;
}

public StringSearchField getCreatedBy() {
	return createdBy;
}

public void setCreatedBy(StringSearchField createdBy) {
	this.createdBy = createdBy;
}

public StringSearchField getModifiedBy() {
	return modifiedBy;
}

public void setModifiedBy(StringSearchField modifiedBy) {
	this.modifiedBy = modifiedBy;
}
public DateSearchField getJobFinshDate() {
	return jobFinshDate;
}

public void setJobFinshDate(DateSearchField jobFinshDate) {
	this.jobFinshDate = jobFinshDate;
}

public DateSearchField getFromJobFinshDate() {
	return fromJobFinshDate;
}

public void setFromJobFinshDate(DateSearchField fromJobFinshDate) {
	this.fromJobFinshDate = fromJobFinshDate;
}

public DateSearchField getToJobFinshDate() {
	return toJobFinshDate;
}

public void setToJobFinshDate(DateSearchField toJobFinshDate) {
	this.toJobFinshDate = toJobFinshDate;
  }
public StringSearchField getInvoiceStatus()
  {
    return invoiceStatus;
  }

  public void setInvoiceStatus(StringSearchField invoiceStatus)
  {
    this.invoiceStatus = invoiceStatus;
  }

public String getChosenJobContracts() {
	return chosenJobContracts;
}

public void setChosenJobContracts(String chosenJobContracts) {
	this.chosenJobContracts = chosenJobContracts;
}

public StringSearchField getBillingContactId() {
	return billingContactId;
}

public void setBillingContactId(StringSearchField billingContactId) {
	this.billingContactId= billingContactId;
}
public String getTabFlag() {
	return tabFlag;
}

public void setTabFlag(String tabFlag) {
	this.tabFlag = tabFlag;
}
public StringSearchField getSearchResults() {
	return searchResults;
}

public void setSearchResults(StringSearchField searchResults) {
	this.searchResults = searchResults;
}

public StringSearchField getMonthlyJobId()
  {
    return monthlyJobId;
  }

  public void setMonthlyJobId(StringSearchField monthlyJobId)
  {
    this.monthlyJobId = monthlyJobId;
  }

public void setSearchForm(String searchForm)
  {
    this.searchForm = searchForm;
  }

  public String getSearchForm()
  {
    return searchForm;
  }

public List getJobNumbers() {
	return jobNumbers;
}

public void setJobNumbers(List jobNumbers) {
	this.jobNumbers = jobNumbers;
}

public String getSessionFlag() {
	return sessionFlag;
}

public void setSessionFlag(String sessionFlag) {
	this.sessionFlag = sessionFlag;
}

public String getHrefJobNumber() {
	return hrefJobNumber;
}

public void setHrefJobNumber(String hrefJobNumber) {
	this.hrefJobNumber = hrefJobNumber;
}

public String getHrefValue() {
	return hrefValue;
}

public void setHrefValue(String hrefValue) {
	this.hrefValue = hrefValue;
}

public String getHrefForm() {
	return hrefForm;
}

public void setHrefForm(String hrefForm) {
	this.hrefForm = hrefForm;
}

public String getColumnFlag() {
	return columnFlag;
}

public void setColumnFlag(String columnFlag) {
	this.columnFlag = columnFlag;
}

public String getShowAllFlag() {
	return showAllFlag;
}

public void setShowAllFlag(String showAllFlag) {
	this.showAllFlag = showAllFlag;
}

public int getNoOfJobs() {
	return noOfJobs;
}

public void setNoOfJobs(int noOfJobs) {
	this.noOfJobs = noOfJobs;
}

public String getBillingTabFlag() {
	return billingTabFlag;
}

public void setBillingTabFlag(String billingTabFlag) {
	this.billingTabFlag = billingTabFlag;
}

public String getDispatchTabFlag() {
	return dispatchTabFlag;
}

public void setDispatchTabFlag(String dispatchTabFlag) {
	this.dispatchTabFlag = dispatchTabFlag;
}

public String getMainTabFlag() {
	return mainTabFlag;
}

public void setMainTabFlag(String mainTabFlag) {
	this.mainTabFlag = mainTabFlag;
}

public String getProcessLogTabFlag() {
	return processLogTabFlag;
}

public void setProcessLogTabFlag(String processLogTabFlag) {
	this.processLogTabFlag = processLogTabFlag;
}

public List getHideAndSort() {
	return hideAndSort;
}

public void setHideAndSort(List hideAndSort) {
	this.hideAndSort = hideAndSort;
}

public List getResultSortOrder() {
	return resultSortOrder;
}

public void setResultSortOrder(List resultSortOrder) {
	this.resultSortOrder = resultSortOrder;
}

public List getMainColumns() {
	return mainColumns;
}

public void setMainColumns(List mainColumns) {
	this.mainColumns = mainColumns;
}

public List getBillingColumns() {
	return billingColumns;
}

public void setBillingColumns(List billingColumns) {
	this.billingColumns = billingColumns;
}

public List getDispatchColumns() {
	return dispatchColumns;
}

public void setDispatchColumns(List dispatchColumns) {
	this.dispatchColumns = dispatchColumns;
}

public List getProcessColumns() {
	return processColumns;
}

public void setProcessColumns(List processColumns) {
	this.processColumns = processColumns;
}

public String getEtaDateColumn() {
	return etaDateColumn;
}

public void setEtaDateColumn(String etaDateColumn) {
	this.etaDateColumn = etaDateColumn;
}
public List getFreezedList() {
	return freezedList;
}

public void setFreezedList(List freezedList) {
	this.freezedList = freezedList;
}

public List getBillingHeaderColumns() {
	return billingHeaderColumns;
}

public void setBillingHeaderColumns(List billingHeaderColumns) {
	this.billingHeaderColumns = billingHeaderColumns;
}

public List getDispatchHeaderColumns() {
	return dispatchHeaderColumns;
}

public void setDispatchHeaderColumns(List dispatchHeaderColumns) {
	this.dispatchHeaderColumns = dispatchHeaderColumns;
}

public List getMainHeaderColumns() {
	return mainHeaderColumns;
}

public void setMainHeaderColumns(List mainHeaderColumns) {
	this.mainHeaderColumns = mainHeaderColumns;
}

public List getProcessHeaderColumns() {
	return processHeaderColumns;
}

public void setProcessHeaderColumns(List processHeaderColumns) {
	this.processHeaderColumns = processHeaderColumns;
}
 public LongSearchField getConfListId()
  {
    return confListId;
  }

  public void setConfListId(LongSearchField confListId)
  {
    this.confListId = confListId;
  }

public StringSearchField getConfListName()
  {
    return confListName;
  }

  public void setConfListName(StringSearchField confListName)
  {
    this.confListName = confListName;
  }

public List getDisplayOrder() {
	return displayOrder;
}

public void setDisplayOrder(List displayOrder) {
	this.displayOrder = displayOrder;
}

public List getDefaultHideandSort() {
	return defaultHideandSort;
}

public void setDefaultHideandSort(List defaultHideandSort) {
	this.defaultHideandSort = defaultHideandSort;
}

public List getFreezedList1() {
	return freezedList1;
}

public void setFreezedList1(List freezedList1) {
	this.freezedList1 = freezedList1;
}

public List getFreezedList2() {
	return freezedList2;
}

public void setFreezedList2(List freezedList2) {
	this.freezedList2 = freezedList2;
}
public List getFreezedList3() {
	return freezedList3;
}

public void setFreezedList3(List freezedList3) {
	this.freezedList3 = freezedList3;
}

public List getFreezedList4() {
	return freezedList4;
}

public void setFreezedList4(List freezedList4) {
	this.freezedList4 = freezedList4;
}


public String getSaveFlag() {
	return saveFlag;
}

public void setSaveFlag(String saveFlag) {
	this.saveFlag = saveFlag;
}


public LogConfigList getLogConfigList() 
{
return logConfigList;
}

public void setLogConfigList(LogConfigList logConfigList) 
{
this.logConfigList = logConfigList;
}
public String getEmailCol() {
	return emailCol;
}

public void setEmailCol(String emailCol) {
	this.emailCol = emailCol;
}

public String getConfName()
  {
    return confName;
  }

  public void setConfName(String confName)
  {
    this.confName = confName;
  }


public String getUserName()
  {
    return userName;
  }

  public void setUserName(String userName)
  {
    this.userName = userName;
  }

public String getConfigFlag() {
	return configFlag;
}

public void setConfigFlag(String configFlag) {
	this.configFlag = configFlag;
}


/*public String getFirstTab() {
	return firstTab;
}

public void setFirstTab(String firstTab) {
	this.firstTab = firstTab;
}

public String getSecondTab() {
	return firstTab;
}

public void setSecondTab(String secondTab) {
	this.secondTab = secondTab;
}


public String getThirdTab() {
	return thirdTab;
}

public void setThirdTab(String thirdTab) {
	this.thirdTab = thirdTab;
}

public String getFourthTab() {
	return fourthTab;
}

public void setFourthTab(String fourthTab) {
	this.fourthTab = fourthTab;
}*/

public long getConfId(){
	return confId;
}

public void setConfId(long confId) {
	this.confId = confId;
}
public List getBillingFreezeHeaderColumns() {
	return billingFreezeHeaderColumns;
}

public void setBillingFreezeHeaderColumns(List billingFreezeHeaderColumns) {
	this.billingFreezeHeaderColumns = billingFreezeHeaderColumns;
}

public List getDispatchFreezeHeaderColumns() {
	return dispatchFreezeHeaderColumns;
}

public void setDispatchFreezeHeaderColumns(List dispatchFreezeHeaderColumns) {
	this.dispatchFreezeHeaderColumns = dispatchFreezeHeaderColumns;
}

public List getMainFreezeHeaderColumns() {
	return mainFreezeHeaderColumns;
}

public void setMainFreezeHeaderColumns(List mainFreezeHeaderColumns) {
	this.mainFreezeHeaderColumns = mainFreezeHeaderColumns;
}

public List getProcessFreezeHeaderColumns() {
	return processFreezeHeaderColumns;
}

public void setProcessFreezeHeaderColumns(List processFreezeHeaderColumns) {
	this.processFreezeHeaderColumns = processFreezeHeaderColumns;
}

public String getSearchFlag() {
	return searchFlag;
}

public void setSearchFlag(String searchFlag) {
	this.searchFlag = searchFlag;
}

public List getSessionFreezedList1() {
	return sessionFreezedList1;
}

public void setSessionFreezedList1(List sessionFreezedList1) {
	this.sessionFreezedList1 = sessionFreezedList1;
}

public List getSessionFreezedList2() {
	return sessionFreezedList2;
}

public void setSessionFreezedList2(List sessionFreezedList2) {
	this.sessionFreezedList2 = sessionFreezedList2;
}

public List getSessionFreezedList3() {
	return sessionFreezedList3;
}

public void setSessionFreezedList3(List sessionFreezedList3) {
	this.sessionFreezedList3 = sessionFreezedList3;
}

public List getSessionFreezedList4() {
	return sessionFreezedList4;
}

public void setSessionFreezedList4(List sessionFreezedList4) {
	this.sessionFreezedList4 = sessionFreezedList4;
}

public List getSessionHideAndSort() {
	return sessionHideAndSort;
}

public void setSessionHideAndSort(List sessionHideAndSort) {
	this.sessionHideAndSort = sessionHideAndSort;
}

public List getSessionHideColumns() {
	return sessionHideColumns;
}

public void setSessionHideColumns(List sessionHideColumns) {
	this.sessionHideColumns = sessionHideColumns;
}

public List getSessionResultSortOrder() {
	return sessionResultSortOrder;
}

public void setSessionResultSortOrder(List sessionResultSortOrder) {
	this.sessionResultSortOrder = sessionResultSortOrder;
}
public String getShowAllTabFlag() {
	return showAllTabFlag; 
}

public void setShowAllTabFlag(String showAllTabFlag) {
	this.showAllTabFlag = showAllTabFlag;
}


public String getNoteFlag() {
	return noteFlag; 
}

public void setNoteFlag(String noteFlag) {
	this.noteFlag = noteFlag;
}

public String getShowAllExcel() {
	return showAllExcel; 
}

public void setShowAllExcel(String showAllExcel) {
	this.showAllExcel = showAllExcel;
}

public String getPCustCode() {
	return pCustCode;
}

public void setPCustCode(String custCode) {
	pCustCode = custCode;
}

public String getFromDate() {
	return fromDate;
}

public void setFromDate(String fromDate) {
	this.fromDate = fromDate;
}

public String getToDate() {
	return toDate;
}

public void setToDate(String toDate) {
	this.toDate = toDate;
}


public Date getFromDate(String dateFormat){
	Date aDate = null;
	if(getFromDate() != null&& !"".equals(getFromDate()))
	aDate = DateUtil.parseDate(getFromDate(), dateFormat);
	if(aDate == null){
		aDate=Calendar.getInstance().getTime();
		setFromDate(DateUtil.formatDate(aDate, dateFormat));
		aDate=DateUtil.parseDate(getFromDate(), dateFormat);		
	}
	return aDate;
}

public Date getToDate(String dateFormat){
	Date aDate = null;	
	if(getToDate()!= null && !"".equals(getToDate()))
	aDate=DateUtil.parseDate(getToDate()+" 23:59:59", dateFormat+" HH:mm:ss");
	if(aDate==null){
		aDate=Calendar.getInstance().getTime();
		setToDate(DateUtil.formatDate(aDate, dateFormat));
		aDate=DateUtil.parseDate(getToDate()+" 23:59:59", dateFormat+" HH:mm:ss");
	}
	return aDate;
}

public String getSortOrderFlag() {
	return sortOrderFlag;
}

public void setSortOrderFlag(String sortOrderFlag) {
	this.sortOrderFlag = sortOrderFlag;
}

public String getCurrentSortFlag() {
	return currentSortFlag;
}

public void setCurrentSortFlag(String currentSortFlag) {
	this.currentSortFlag = currentSortFlag;
}

public String getPrevSortFlag() {
	return prevSortFlag;
}

public void setPrevSortFlag(String prevSortFlag) {
	this.prevSortFlag = prevSortFlag;
}
public long getJobSearchCriteriaId() {
	return jobSearchCriteriaId;
}

public void setJobSearchCriteriaId(long jobSearchCriteriaId) {
	this.jobSearchCriteriaId = jobSearchCriteriaId;
}
}
