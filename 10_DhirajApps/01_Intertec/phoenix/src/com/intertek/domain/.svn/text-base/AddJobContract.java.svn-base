package com.intertek.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;
import org.springmodules.validation.bean.conf.loader.annotation.handler.CascadeValidation;
import org.springmodules.validation.bean.conf.loader.annotation.handler.Length;

import com.intertek.entity.CfgContract;
import com.intertek.entity.ContractCustContact;
import com.intertek.entity.JobContract;
import com.intertek.entity.JobContractAttach;
import com.intertek.entity.JobContractInvoice;
import com.intertek.entity.JobLog;
import com.intertek.entity.JobOrder;

public class AddJobContract extends BaseSelects
{
  @CascadeValidation

  private JobContract jobContract;
  private JobLog jobLog;
  private JobOrder jobOrder;

  private JobContractAttach[] jobContractAttachs;
  private ContractCustContact contractCustContact;
  private MultipartFile[] files;
  List<HashMap> fileInfoMapList= new ArrayList<HashMap>();
  private String rowNum;
  private String divName;
  private String inputFieldId;
  private int   jobCount ;
   @Length(min=0, max=512) private String billingAddress;
   @Length(min=0, max=512) private String schedulerAddress;
  private List jobServices;
  private String appendNote;
  private CfgContract cfgContract;
  private AddJobContractVessel[] addJobContractVessels;
  private AddJobContractServices addJobContractServices;
  private String invoicePreviewTabSrc;
  private AddPrebill[] addPrebills;
  private String priceBookId;

  private String coordinator;
  private String addOrDeletePrebill;
  private String inspectorNotifyTime;
  private String inspectorArriveTime;
  private String arriveTime;
  private String dockTime;
  private String hoseOnTime;
  private String estStartTime;
  private String commenceTime;
  private String delayTime;
  private String delayEndTime;
  private String estCompleteTime;
  private String completeTime;
  private String hoseOffTime;
  private String releaseTime;
  private String sampleReceiveTime;
  private String prelimReportTime;
  private String sampleShipTime;
  private String finalReportTime;
  private String distributeTime;
  private String arriveDt;
  private String dockDt;
  private String hoseOnDt;
  private String estStartDt;
  private String commenceDt;
  private String delayDt;
  private String delayEndDt;
  private String estCompleteDt;
  private String completeDt;
  private String hoseOffDt;
  private String releaseDt;
  private String sampleReceiveDt;
  private String prelimReportDt;
  private String sampleShipDt;
  private String finalReportDt;
  private String distributeDt;
  private String summaryDt;
  private String jobStatus;
  private int   prebillIndex ;
  private String herfJobType;
  private String jobType;
  private String operation;
  private String etaTime;
  private String reqFrom;
  private double invoiceTotalAmt;
  private double vatTotalAmt;
  private double taxTotalAmt;
  private String contractLvlTaxCode;
  private String contractLvlVatCode;
  private String contractLvlProvince;
  private String chosenPrebillIds;
  private double contractLvlTaxPct;
  private double contractLvlVatPct;
  private String taxArticleCode;
  private String vatRegistrationId;
  private String contractDesc;
  private String companyName;
  private JobSearch jobSearch;
  private JobContractInvoice latestJobContractInvoice;
  private int jobContractIndex;


  private String displayStatus = "SHOW";
    private String vatRateCountry;
  private String addNote;
  private String addUpdatedNote;
  private String invoiceFlag;
  private String etaDateTimeFlag;
  /*For ITrack#58291- START */
  private String customerNote;
  private String customerNoteDetails;
  /*For ITrack#58291- END */
  private Double splitPct;

  private boolean checkAll;
  private String jobFinshDate;
  private String contractStatus;

  private double currencyMultiplier = 1.0;

  private String deleteAttachId;
  private boolean contractViewOnly = false;

  private List jobContractInvoiceList;

  private List vesselTypes;
  
  private String jobContractStatus;
  private boolean billingSameAsScheduler;
  private boolean contractInProgress;
  
  // START : #127441
  private String cancelNote;
  private String cancelNoteDetails;
  // END : #127441

  public double getInvoiceTotalAmt() {
  return invoiceTotalAmt;
}

  public void setInvoiceTotalAmt(double invoiceTotalAmt) {
    this.invoiceTotalAmt = invoiceTotalAmt;
  }

  public double getVatTotalAmt() {
	  return vatTotalAmt;
	}

	  public void setVatTotalAmt(double vatTotalAmt) {
	    this.vatTotalAmt = vatTotalAmt;
	}
	  
  public double getTaxTotalAmt() {
		return taxTotalAmt;
	}

	public void setTaxTotalAmt(double taxTotalAmt) {
		this.taxTotalAmt = taxTotalAmt;
	}

public JobContract getJobContract() {
      return jobContract;
  }

  public void setJobContract(JobContract jobContract) {
    this.jobContract = jobContract;
  }

  public JobContractAttach[] getJobContractAttachs() {
    return jobContractAttachs;
  }

  public void setJobContractAttachs(JobContractAttach[] jobContractAttachs) {
    this.jobContractAttachs = jobContractAttachs;
  }

  public ContractCustContact getContractCustContact(){
    return contractCustContact;
  }

  public void setContractCustContact(ContractCustContact contratCustContact){
    this.contractCustContact=contractCustContact;
  }


  public void setFiles(MultipartFile[] files) {
    this.files = files;
  }

  public MultipartFile[] getFiles() {
    return files;
  }

  public String getRowNum() {
  return rowNum;
  }

  public void setRowNum(String rowNum)  {
  this.rowNum = rowNum;
  }

  public String getDivName() {
  return divName;
  }

  public void setDivName(String divName)  {
  this.divName = divName;
  }

  public int getJobCount(){
  return jobCount;
  }

  public void setJobCount(int jobCount){
  this.jobCount = jobCount;
  }

  public String getBillingAddress(){
  return billingAddress;
  }

  public void setBillingAddress(String billingAddress){
  this.billingAddress = billingAddress;
  }

  public String getInputFieldId() {
    return inputFieldId;
  }

  public void setInputFieldId(String inputFieldId) {
    this.inputFieldId = inputFieldId;
  }

  public CfgContract getCfgContract() {
    return cfgContract;
  }

  public void setCfgContract(CfgContract cfgContract) {
    this.cfgContract = cfgContract;
  }

    public AddJobContractVessel[] getAddJobContractVessels() {
      return addJobContractVessels;
    }

    public void setAddJobContractVessels(AddJobContractVessel[] addJobContractVessels) {
      this.addJobContractVessels = addJobContractVessels;
    }

    public void setAddJobContractServices(AddJobContractServices addJobContractServices)
    {
      this.addJobContractServices = addJobContractServices;
    }

    public AddJobContractServices getAddJobContractServices()
    {
      return addJobContractServices;
    }

    public String getInvoicePreviewTabSrc()
    {
      return invoicePreviewTabSrc;
    }

    public void setInvoicePreviewTabSrc(String invoicePreviewTabSrc)
    {
      this.invoicePreviewTabSrc = invoicePreviewTabSrc;
    }
  public AddPrebill[] getAddPrebills() {
    return addPrebills;
  }

  public void setAddPrebills(AddPrebill[] addPrebills) {
    this.addPrebills = addPrebills;
  }

  public String getAddOrDeletePrebill() {
    return addOrDeletePrebill;
  }

  public void setAddOrDeletePrebill(String addOrDeletePrebill) {
    this.addOrDeletePrebill = addOrDeletePrebill;
  }

  public int getPrebillIndex() {
    return prebillIndex;
  }

  public void setPrebillIndex(int prebillIndex) {
    this.prebillIndex = prebillIndex;
  }

  public String getAppendNote() {
    return appendNote;
  }

  public void setAppendNote(String appendNote) {
    this.appendNote = appendNote;
  }

  public String getArriveTime() {
    return arriveTime;
  }

  public void setArriveTime(String arriveTime) {
    this.arriveTime = arriveTime;
  }

  public String getCommenceTime() {
    return commenceTime;
  }

  public void setCommenceTime(String commenceTime) {
    this.commenceTime = commenceTime;
  }

  public String getCompleteTime() {
    return completeTime;
  }

  public void setCompleteTime(String completeTime) {
    this.completeTime = completeTime;
  }

  public String getDelayEndTime() {
    return delayEndTime;
  }

  public void setDelayEndTime(String delayEndTime) {
    this.delayEndTime = delayEndTime;
  }

  public String getDelayTime() {
    return delayTime;
  }

  public void setDelayTime(String delayTime) {
    this.delayTime = delayTime;
  }

  public String getDistributeTime() {
    return distributeTime;
  }

  public void setDistributeTime(String distributeTime) {
    this.distributeTime = distributeTime;
  }

  public String getDockTime() {
    return dockTime;
  }

  public void setDockTime(String dockTime) {
    this.dockTime = dockTime;
  }

  public String getEstCompleteTime() {
    return estCompleteTime;
  }

  public void setEstCompleteTime(String estCompleteTime) {
    this.estCompleteTime = estCompleteTime;
  }

  public String getEstStartTime() {
    return estStartTime;
  }

  public void setEstStartTime(String estStartTime) {
    this.estStartTime = estStartTime;
  }

  public String getEtaTime() {
    return etaTime;
  }

  public void setEtaTime(String etaTime) {
    this.etaTime = etaTime;
  }

  public String getFinalReportTime() {
    return finalReportTime;
  }

  public void setFinalReportTime(String finalReportTime) {
    this.finalReportTime = finalReportTime;
  }

  public String getHerfJobType() {
    return herfJobType;
  }

  public void setHerfJobType(String herfJobType) {
    this.herfJobType = herfJobType;
  }

  public String getHoseOffTime() {
    return hoseOffTime;
  }

  public void setHoseOffTime(String hoseOffTime) {
    this.hoseOffTime = hoseOffTime;
  }

  public String getHoseOnTime() {
    return hoseOnTime;
  }

  public void setHoseOnTime(String hoseOnTime) {
    this.hoseOnTime = hoseOnTime;
  }

  public String getInspectorArriveTime() {
    return inspectorArriveTime;
  }

  public void setInspectorArriveTime(String inspectorArriveTime) {
    this.inspectorArriveTime = inspectorArriveTime;
  }

  public String getInspectorNotifyTime() {
    return inspectorNotifyTime;
  }

  public void setInspectorNotifyTime(String inspectorNotifyTime) {
    this.inspectorNotifyTime = inspectorNotifyTime;
  }

  public JobLog getJobLog() {
    return jobLog;
  }

  public void setJobLog(JobLog jobLog) {
    this.jobLog = jobLog;
  }

  public JobOrder getJobOrder() {
    return jobOrder;
  }

  public void setJobOrder(JobOrder jobOrder) {
    this.jobOrder = jobOrder;
  }

  public List getJobServices() {
    return jobServices;
  }

  public void setJobServices(List jobServices) {
    this.jobServices = jobServices;
  }

  public String getJobStatus() {
    return jobStatus;
  }

  public void setJobStatus(String jobStatus) {
    this.jobStatus = jobStatus;
  }

  public String getJobType() {
    return jobType;
  }

  public void setJobType(String jobType) {
    this.jobType = jobType;
  }

  public String getOperation() {
    return operation;
  }

  public void setOperation(String operation) {
    this.operation = operation;
  }

  public String getPrelimReportTime() {
    return prelimReportTime;
  }

  public void setPrelimReportTime(String prelimReportTime) {
    this.prelimReportTime = prelimReportTime;
  }

  public String getReleaseTime() {
    return releaseTime;
  }

  public void setReleaseTime(String releaseTime) {
    this.releaseTime = releaseTime;
  }

  public String getSampleReceiveTime() {
    return sampleReceiveTime;
  }

  public void setSampleReceiveTime(String sampleReceiveTime) {
    this.sampleReceiveTime = sampleReceiveTime;
  }

  public String getSampleShipTime() {
    return sampleShipTime;
  }

  public void setSampleShipTime(String sampleShipTime) {
    this.sampleShipTime = sampleShipTime;
  }

  public String getSchedulerAddress() {
    return schedulerAddress;
  }

  public void setSchedulerAddress(String schedulerAddress) {
    this.schedulerAddress = schedulerAddress;
  }

  public String getReqFrom() {
    return reqFrom;
  }

  public void setReqFrom(String reqFrom) {
    this.reqFrom = reqFrom;
  }

  public String getContractLvlTaxCode() {
    return contractLvlTaxCode;
  }

  public void setContractLvlTaxCode(String contractLvlTaxCode) {
    this.contractLvlTaxCode = contractLvlTaxCode;
  }

  public String getContractLvlVatCode() {
    return contractLvlVatCode;
  }

  public void setContractLvlVatCode(String contractLvlVatCode) {
    this.contractLvlVatCode = contractLvlVatCode;
  }

  public String getChosenPrebillIds() {
    return chosenPrebillIds;
  }

  public void setChosenPrebillIds(String chosenPrebillIds) {
    this.chosenPrebillIds = chosenPrebillIds;
  }

  public double getContractLvlTaxPct() {
    return contractLvlTaxPct;
  }

  public void setContractLvlTaxPct(double contractLvlTaxPct) {
    this.contractLvlTaxPct = contractLvlTaxPct;
  }

  public double getContractLvlVatPct() {
    return contractLvlVatPct;
  }

  public void setContractLvlVatPct(double contractLvlVatPct) {
    this.contractLvlVatPct = contractLvlVatPct;
  }

  public String getContractDesc() {
    return contractDesc;
  }

  public void setContractDesc(String contractDesc) {
    this.contractDesc = contractDesc;
  }

  public String getCompanyName() {
    return companyName;
  }

  public void setCompanyName(String companyName) {
    this.companyName = companyName;
  }

  public void setSplitPct(Double splitPct)
  {
    this.splitPct = splitPct;
  }

  public Double getSplitPct()
  {
    return splitPct;
  }

  public JobContractInvoice getLatestJobContractInvoice() {
    return latestJobContractInvoice;
  }

  public void setLatestJobContractInvoice(
      JobContractInvoice latestJobContractInvoice) {
    this.latestJobContractInvoice = latestJobContractInvoice;
  }

  public void setCheckAll(boolean checkAll)
  {
    this.checkAll = checkAll;
  }

  public boolean getCheckAll()
  {
    return checkAll;
  }

  public String getTaxArticleCode() {
    return taxArticleCode;
  }

  public void setTaxArticleCode(String taxArticleCode) {
    this.taxArticleCode = taxArticleCode;
  }

  public String getVatRegistrationId() {
    return vatRegistrationId;
  }

  public void setVatRegistrationId(String vatRegistrationId) {
    this.vatRegistrationId = vatRegistrationId;
  }

  public String getDisplayStatus() {
    return displayStatus;
  }

  public void setDisplayStatus(String displayStatus) {
    this.displayStatus = displayStatus;
  }
  public JobSearch getJobSearch() {
    return jobSearch;
  }

  public void setJobSearch(JobSearch jobSearch) {
    this.jobSearch = jobSearch;
  }

  public String getJobFinshDate() {
    return jobFinshDate;
  }

  public void setJobFinshDate(String jobFinshDate) {
    this.jobFinshDate = jobFinshDate;
  }
  public String getContractStatus() {
    return contractStatus;
  }

  public void setContractStatus(String contractStatus) {
    this.contractStatus = contractStatus;
  }

  public double getCurrencyMultiplier()
  {
    return currencyMultiplier;
  }

  public void setCurrencyMultiplier(double currencyMultiplier)
  {
    this.currencyMultiplier = currencyMultiplier;
  }

  public String getDeleteAttachId() {
    return deleteAttachId;
  }

  public void setDeleteAttachId(String deleteAttachId) {
    this.deleteAttachId = deleteAttachId;
  }

  public String getPriceBookId() {
        return this.priceBookId;
    }

    public void setPriceBookId(String priceBookId) {
        this.priceBookId = priceBookId;
    }
  public boolean isContractViewOnly() {
    return contractViewOnly;
  }

  public void setContractViewOnly(boolean contractViewOnly) {
    this.contractViewOnly = contractViewOnly;
  }

public int getJobContractIndex() {
  return jobContractIndex;
}

public void setJobContractIndex(int jobContractIndex) {
  this.jobContractIndex = jobContractIndex;
}

public String getVatRateCountry(){
    return vatRateCountry;
  }

  public void setVatRateCountry(String vatRateCountry){
    this.vatRateCountry=vatRateCountry;
  }



 public String getAddNote() {
        return this.addNote;
    }

    public void setAddNote(String addNote) {
        this.addNote = addNote;
    }


  public void setJobContractInvoiceList(List jobContractInvoiceList)
  {
    this.jobContractInvoiceList = jobContractInvoiceList;
  }

  public List getJobContractInvoiceList()
  {
    return jobContractInvoiceList;
  }

  public List getVesselTypes()
  {
    return vesselTypes;
  }

  public void setVesselTypes(List vesselTypes)
  {
    this.vesselTypes = vesselTypes;
  }

  public String getAddUpdatedNote() {
        return this.addUpdatedNote;
    }

    public void setAddUpdatedNote(String addUpdatedNote) {
        this.addUpdatedNote = addUpdatedNote;
    }

	/**
	 * @return the arriveDt
	 */
	public String getArriveDt() {
		return arriveDt;
	}

	/**
	 * @param arriveDt the arriveDt to set
	 */
	public void setArriveDt(String arriveDt) {
		this.arriveDt = arriveDt;
	}

	/**
	 * @return the commenceDt
	 */
	public String getCommenceDt() {
		return commenceDt;
	}

	/**
	 * @param commenceDt the commenceDt to set
	 */
	public void setCommenceDt(String commenceDt) {
		this.commenceDt = commenceDt;
	}

	/**
	 * @return the completeDt
	 */
	public String getCompleteDt() {
		return completeDt;
	}

	/**
	 * @param completeDt the completeDt to set
	 */
	public void setCompleteDt(String completeDt) {
		this.completeDt = completeDt;
	}

	/**
	 * @return the delayDt
	 */
	public String getDelayDt() {
		return delayDt;
	}

	/**
	 * @param delayDt the delayDt to set
	 */
	public void setDelayDt(String delayDt) {
		this.delayDt = delayDt;
	}

	/**
	 * @return the delayEndDt
	 */
	public String getDelayEndDt() {
		return delayEndDt;
	}

	/**
	 * @param delayEndDt the delayEndDt to set
	 */
	public void setDelayEndDt(String delayEndDt) {
		this.delayEndDt = delayEndDt;
	}

	/**
	 * @return the distributeDt
	 */
	public String getDistributeDt() {
		return distributeDt;
	}

	/**
	 * @param distributeDt the distributeDt to set
	 */
	public void setDistributeDt(String distributeDt) {
		this.distributeDt = distributeDt;
	}

	/**
	 * @return the dockDt
	 */
	public String getDockDt() {
		return dockDt;
	}

	/**
	 * @param dockDt the dockDt to set
	 */
	public void setDockDt(String dockDt) {
		this.dockDt = dockDt;
	}

	/**
	 * @return the estCompleteDt
	 */
	public String getEstCompleteDt() {
		return estCompleteDt;
	}

	/**
	 * @param estCompleteDt the estCompleteDt to set
	 */
	public void setEstCompleteDt(String estCompleteDt) {
		this.estCompleteDt = estCompleteDt;
	}

	/**
	 * @return the estStartDt
	 */
	public String getEstStartDt() {
		return estStartDt;
	}

	/**
	 * @param estStartDt the estStartDt to set
	 */
	public void setEstStartDt(String estStartDt) {
		this.estStartDt = estStartDt;
	}

	/**
	 * @return the finalReportDt
	 */
	public String getFinalReportDt() {
		return finalReportDt;
	}

	/**
	 * @param finalReportDt the finalReportDt to set
	 */
	public void setFinalReportDt(String finalReportDt) {
		this.finalReportDt = finalReportDt;
	}

	/**
	 * @return the hoseOffDt
	 */
	public String getHoseOffDt() {
		return hoseOffDt;
	}

	/**
	 * @param hoseOffDt the hoseOffDt to set
	 */
	public void setHoseOffDt(String hoseOffDt) {
		this.hoseOffDt = hoseOffDt;
	}

	/**
	 * @return the hoseOnDt
	 */
	public String getHoseOnDt() {
		return hoseOnDt;
	}

	/**
	 * @param hoseOnDt the hoseOnDt to set
	 */
	public void setHoseOnDt(String hoseOnDt) {
		this.hoseOnDt = hoseOnDt;
	}

	/**
	 * @return the prelimReportDt
	 */
	public String getPrelimReportDt() {
		return prelimReportDt;
	}

	/**
	 * @param prelimReportDt the prelimReportDt to set
	 */
	public void setPrelimReportDt(String prelimReportDt) {
		this.prelimReportDt = prelimReportDt;
	}

	/**
	 * @return the releaseDt
	 */
	public String getReleaseDt() {
		return releaseDt;
	}

	/**
	 * @param releaseDt the releaseDt to set
	 */
	public void setReleaseDt(String releaseDt) {
		this.releaseDt = releaseDt;
	}

	/**
	 * @return the sampleReceiveDt
	 */
	public String getSampleReceiveDt() {
		return sampleReceiveDt;
	}

	/**
	 * @param sampleReceiveDt the sampleReceiveDt to set
	 */
	public void setSampleReceiveDt(String sampleReceiveDt) {
		this.sampleReceiveDt = sampleReceiveDt;
	}

	/**
	 * @return the sampleShipDt
	 */
	public String getSampleShipDt() {
		return sampleShipDt;
	}

	/**
	 * @param sampleShipDt the sampleShipDt to set
	 */
	public void setSampleShipDt(String sampleShipDt) {
		this.sampleShipDt = sampleShipDt;
	}

	/**
	 * @return the summaryDt
	 */
	public String getSummaryDt() {
		return summaryDt;
	}

	/**
	 * @param summaryDt the summaryDt to set
	 */
	public void setSummaryDt(String summaryDt) {
		this.summaryDt = summaryDt;
	}

	public String getCoordinator() {
		return coordinator;
	}

	public void setCoordinator(String coordinator) {
		this.coordinator = coordinator;
	}
	public String getInvoiceFlag() {
		return invoiceFlag;
	}

	public void setInvoiceFlag(String invoiceFlag) {
		this.invoiceFlag = invoiceFlag;
	}

	public String getEtaDateTimeFlag() {
		return etaDateTimeFlag;
	}

	public void setEtaDateTimeFlag(String etaDateTimeFlag) {
		this.etaDateTimeFlag = etaDateTimeFlag;
	}

	public String getJobContractStatus() {
		return jobContractStatus;
	}

	public void setJobContractStatus(String jobContractStatus) {
		this.jobContractStatus = jobContractStatus;
	}

	public String getContractLvlProvince() {
		return contractLvlProvince;
	}

	public void setContractLvlProvince(String contractLvlProvince) {
		this.contractLvlProvince = contractLvlProvince;
	}

	public List<HashMap> getFileInfoMapList() {
		return fileInfoMapList;
	}

	public void setFileInfoMapList(List<HashMap> fileInfoMapList) {
		this.fileInfoMapList = fileInfoMapList;
	}

	public void setBillingSameAsScheduler(boolean b) {
		this.billingSameAsScheduler = b;
	}

	public boolean isBillingSameAsScheduler() {
		return billingSameAsScheduler;
	}

	public boolean isContractInProgress() {
		return contractInProgress;
	}

	public void setContractInProgress(boolean contractInProgress) {
		this.contractInProgress = contractInProgress;
	}
	/*For ITrack#58291- START */
	public String getCustomerNote() {
		return customerNote;
	}

	public void setCustomerNote(String customerNote) {
		this.customerNote = customerNote;
	}

	public String getCustomerNoteDetails() {
		return customerNoteDetails;
	}

	public void setCustomerNoteDetails(String customerNoteDetails) {
		this.customerNoteDetails = customerNoteDetails;
	}
	/*For ITrack#58291- END */

	// START : #127441
	public String getCancelNote() {
		return cancelNote;
	}

	public void setCancelNote(String cancelNote) {
		this.cancelNote = cancelNote;
	}

	public String getCancelNoteDetails() {
		return cancelNoteDetails;
	}

	public void setCancelNoteDetails(String cancelNoteDetails) {
		this.cancelNoteDetails = cancelNoteDetails;
	}
	// END : #127441
	
}

