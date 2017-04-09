package com.intertek.domain;

import java.util.ArrayList;
import java.util.List;

import org.springmodules.validation.bean.conf.loader.annotation.handler.CascadeValidation;

import com.intertek.entity.JobOrder;
import com.intertek.entity.Project;
import com.intertek.entity.RevisionNotes;
import com.intertek.util.Constants;

public class AddJobOrder
{
  @CascadeValidation

  private JobOrder jobOrder;
  private String inputFieldId;
  private String towingCompFlag;
  private String shippingAgentFlag;
  private String serviceLocationFlag;
  private String portLocationFlag;
  private String shipAgentPhoneFlag;
  private String towCoPhoneFlag;

  private String towingCo;
  private String tabName = "0";
  private String searchField;
  private String searchString;
  private String uiEtaTime;
  private String uiEtaDate;
  private String uiNominationTime;

  private AddJobContract[] addJobContracts;
  private AddJobVessel[] addJobVessels;

  //STAT: Issue # 75052
  private RevisionNotes[] arrRevisionNotes; 
  private String uiRevisionNote;  
  //END: Issue # 75052 

  private int vesselIndex;
  private int targetVesselIndex;
  private int vesselCount;
  private String addOrDeleteVessel;
  private int productIndex;
  private String addOrDeleteProduct;
  private int eventIndex;
  private String addOrDeleteEvent;
  private int sampleLocIndex;
  private String addOrDeleteSampleLoc;
  private int testIndex;
  private String addOrDeleteTest;
  private int slateIndex;
  private String addOrDeleteSlate;
  private int qtyIndex;
  private String addOrDeleteQty;
  private String saveHeaderFlag;
  private String nextPageFlag;
  private StringSearchField contractCode;
  private String jobFlag;
  private String addOrDeleteJob;
  private int   jobCount ;
  private int   jobContractCount ;
  private int jobIndex;
  private String copyFlag;

  private String inputFieldIdValue;
  private String deleteFlag;
  private String rowNum;
  private String jobValFlag;
  private int contactIndex;
  private String contactFlag;
  private String attachFilesFlag;
  private String attachedFileNames;

  private String chosenContracts;
  private String chosenTestIds;
  private String chosenManualTestIds;
  private String manualTestQty;
  private String manualTestPrice;
  private String chosenSlateIds;
  private String originateFromSearchJob;
  private String jobNumber;
  private String nextListFlag;
  private String prevListFlag;
  private String uniqueFlag;
  private String popFlag;
  private String applicableContractFlag;
  private String otApprovedFlag;
  private String labAnalysisFlag;
  private String operationFlag;
  private String addOrDeletePrebill;
  private long prebillId;
  private String instructionFlag;

  private int contractIndex;
  private String bankFlag;
  private int bankIndex;
  private String bankCode;
  private String rebillFlag;
  private String creditReasonNote;
  private String creditReasonUser;
  private String searchCode;
  private List jobVessels;
  private String defaultJobDesc;
  private String dateFormat;
  private String userTimeFormat;
  private String nomTimeFormat;
  private String etaTimeFormat;
  private String pageNumber;
  private String errorFlag;
  private String errorCode;
  private String massQtyFlag;
  private String massOTFlag;
  private int custCount;
  private String contrCode;
  private String contrFlag;
  private String jobDateFlag;
  private String navigationUrl;
  private String warnUserFlag;
  private String loadState;
  private int uniqueCount;
  private String rCode;
  private String displayProductNames;
 
 private String emailNote;
 private String emailNoteDisplay;
 private String tempName;
 private String templateFlag;
 private String tmpSearchFlag;
 private String searchTemplate;


  private String templateHideFlg = "NO";
  private String templateNum;
  
  private boolean viewOnly;
  
  private String regeneratePdfInvoice;
  private String monthlyJobFlag;
  private String monthlyCheck;
  private String sendToAribaFlag;

  private String goFlag;
  private String dateFlag;
  private boolean previewFlag=false;
  private boolean instrDisplayFlag = true;
  private String openPeriodsFlag;
  private boolean samBranchFlag=false;
  private boolean limsBranchFlag=false;
  private String goForm;
  private String jobCancelReasonNote;
  private String jobCancelReasonUser;
  private String showWarn;
  private boolean deleteSampleFlag = false;
  private boolean deleteProductFlag = false;
  private boolean isDeleteVesselFlag = false;
  List<Integer> deletedSampleLoc = new ArrayList<Integer>();
  List<Integer> deletedProducts = new ArrayList<Integer>();
  List<Integer> deletedVessels = new ArrayList<Integer>();
  List<String> deletedVesselProducts = new ArrayList<String>();
 // private boolean operationViewOnly=false;
 // private boolean shipViewOnly=false;
 // private boolean towViewOnly=false;
 // private boolean servViewOnly=false;
  
 // private boolean nomDateViewOnly=false;
 // private boolean nomTzViewOnly=false;
 // private boolean etaDateViewOnly=false;
//  private boolean etaTzViewOnly=false;
 // private boolean jobFinishDateViewOnly=false;
  
  private String monthlyvalidFlag;
  
  private String massTestSlateDeleteFlag;
  private String otApproveCheckFlag;
  private String testIndexFlag;
  private String scrollFlag;
  private String sampleLocCount;
  private String searchForm;
  private String Div1;
  private String Div2;
  
  //101771 START
  private String testDesc;
  //101771 END
  
  //START : 53791
  private String signIdntfr;
  private String signIndex;
  
  // START : #119240
  private String currPageIdentifier;
  // END : #119240
  
  public String getSignIndex() {
	return signIndex;
  }

  public void setSignIndex(String signIndex) {
	this.signIndex = signIndex;
  }

  public String getSignIdntfr() {
	return signIdntfr;
  }

  public void setSignIdntfr(String signIdntfr) {
	this.signIdntfr = signIdntfr;
  }  
  //END : 53791  
  
  public String getTestDesc() {
	return testDesc;
}

public void setTestDesc(String testDesc) {
	this.testDesc = testDesc;
}

public String getOtApproveCheckFlag() {
	return otApproveCheckFlag;
}

public void setOtApproveCheckFlag(String otApproveCheckFlag) {
	this.otApproveCheckFlag = otApproveCheckFlag;
}

public boolean isViewOnly() {
	return viewOnly;
}

public void setViewOnly(boolean viewOnly) {
	this.viewOnly = viewOnly;
}
public String getTmpSearchFlag() {
	return tmpSearchFlag;
}

public void setTmpSearchFlag(String tmpSearchFlag) {
	this.tmpSearchFlag = tmpSearchFlag;
}

public String getWarnUserFlag() {
	return warnUserFlag;
}

public void setWarnUserFlag(String warnUserFlag) {
	this.warnUserFlag = warnUserFlag;
}

public AddJobOrder() {
 contractCode = new StringSearchField(Constants.CONTAINS);
 }

public String getInputFieldId() {
  return inputFieldId;
}

public void setInputFieldId(String inputFieldId) {
  this.inputFieldId = inputFieldId;
}

public String getShippingAgentFlag() {
  return shippingAgentFlag;
}

public void setShippingAgentFlag(String shippingAgentFlag) {
  this.shippingAgentFlag = shippingAgentFlag;
}

public String getTowingCompFlag() {
  return towingCompFlag;
}

public void setTowingCompFlag(String towingCompFlag) {
  this.towingCompFlag = towingCompFlag;
}

public String getTowingCo() {
  return towingCo;
}

public void setTowingCo(String towingCo) {
  this.towingCo = towingCo;
}
public StringSearchField getContractCode() {
return contractCode;
}

public void setContractCode(StringSearchField contractCode){
this.contractCode = contractCode;
}

public AddJobContract[] getAddJobContracts() {
  return addJobContracts;
}

public void setAddJobContracts(AddJobContract[] addJobContracts) {
  this.addJobContracts = addJobContracts;
}

public JobOrder getJobOrder() {
  return jobOrder;
}

public void setJobOrder(JobOrder jobOrder) {
  this.jobOrder = jobOrder;
}

public AddJobVessel[] getAddJobVessels() {
  return addJobVessels;
}

public void setAddJobVessels(AddJobVessel[] addJobVessels) {
  this.addJobVessels = addJobVessels;
}

public String getAddOrDeleteVessel() {
  return addOrDeleteVessel;
}

public void setAddOrDeleteVessel(String addOrDeleteVessel) {
  this.addOrDeleteVessel = addOrDeleteVessel;
}

public int getVesselCount() {
  return vesselCount;
}

public void setVesselCount(int vesselCount) {
  this.vesselCount = vesselCount;
}

public int getVesselIndex() {
  return vesselIndex;
}

public void setVesselIndex(int vesselIndex) {
  this.vesselIndex = vesselIndex;
}

public String getAddOrDeleteProduct() {
  return addOrDeleteProduct;
}

public void setAddOrDeleteProduct(String addOrDeleteProduct) {
  this.addOrDeleteProduct = addOrDeleteProduct;
}

public int getProductIndex() {
  return productIndex;
}

public void setProductIndex(int productIndex) {
  this.productIndex = productIndex;
}

public String getAddOrDeleteEvent() {
  return addOrDeleteEvent;
}

public void setAddOrDeleteEvent(String addOrDeleteEvent) {
  this.addOrDeleteEvent = addOrDeleteEvent;
}

public int getEventIndex() {
  return eventIndex;
}

public void setEventIndex(int eventIndex) {
  this.eventIndex = eventIndex;
}

public String getAddOrDeleteSampleLoc() {
  return addOrDeleteSampleLoc;
}

public void setAddOrDeleteSampleLoc(String addOrDeleteSampleLoc) {
  this.addOrDeleteSampleLoc = addOrDeleteSampleLoc;
}

public int getSampleLocIndex() {
  return sampleLocIndex;
}

public void setSampleLocIndex(int sampleLocIndex) {
  this.sampleLocIndex = sampleLocIndex;
}

public String getAddOrDeleteQty() {
  return addOrDeleteQty;
}

public void setAddOrDeleteQty(String addOrDeleteQty) {
  this.addOrDeleteQty = addOrDeleteQty;
}

public int getQtyIndex() {
  return qtyIndex;
}

public void setQtyIndex(int qtyIndex) {
  this.qtyIndex = qtyIndex;
}

public String getSaveHeaderFlag() {
  return saveHeaderFlag;
}

public void setSaveHeaderFlag(String saveHeaderFlag) {
  this.saveHeaderFlag = saveHeaderFlag;
}
public String getServiceLocationFlag() {
  return serviceLocationFlag;
}

public void setServiceLocationFlag(String serviceLocationFlag) {
  this.serviceLocationFlag = serviceLocationFlag;
}

public String getTabName() {
  return tabName;
}

public void setTabName(String tabName) {
  this.tabName = tabName;
}

public String getSearchField() {

  return searchField;
}

public void setSearchField(String searchField) {
  this.searchField = searchField;
}

public String getSearchString() {

  return searchString;
}

public void setSearchString(String searchString) {
  this.searchString = searchString;
}

public String getNextPageFlag() {
  return nextPageFlag;
}

public void setNextPageFlag(String nextPageFlag) {
  this.nextPageFlag = nextPageFlag;
}

public String getJobFlag(){
return jobFlag;
}

public void setJobFlag(String jobFlag){
this.jobFlag = jobFlag;
}

public String getAddOrDeleteJob(){
return addOrDeleteJob;
}

public void setAddOrDeleteJob(String addOrDeleteJob){
this.addOrDeleteJob = addOrDeleteJob;
}

public int getJobCount(){
return jobCount;
}

public void setJobCount(int jobCount){
this.jobCount = jobCount;
}

public int getJobIndex(){
return jobIndex;
}

public void setJobIndex(int jobIndex){
this.jobIndex = jobIndex;
}
public String getInputFieldIdValue(){
return inputFieldIdValue;
}

public void setInputFieldIdValue(String inputFieldIdValue){
this.inputFieldIdValue=inputFieldIdValue;
}

public String getDeleteFlag(){
return deleteFlag;
}

public void setDeleteFlag(String deleteFlag){
this.deleteFlag = deleteFlag;
}

public String getRowNum() {
return rowNum;
}

public void setRowNum(String rowNum)  {
this.rowNum = rowNum;
}


public String getJobValFlag(){
return jobValFlag;
}

public void setJobValFlag(String jobValFlag){
this.jobValFlag = jobValFlag;
}


public int getContactIndex(){
return contactIndex;
}

public void setContactIndex(int contactIndex){
this.contactIndex = contactIndex;
}
public String getContactFlag(){
return contactFlag;
}

public void setContactFlag(String contactFlag){
this.contactFlag = contactFlag;
}

public String getAttachedFileNames() {
  return attachedFileNames;
}

public void setAttachedFileNames(String attachedFileNames) {
  this.attachedFileNames = attachedFileNames;
}

public String getAttachFilesFlag() {
  return attachFilesFlag;
}

public void setAttachFilesFlag(String attachFilesFlag) {
  this.attachFilesFlag = attachFilesFlag;
}



public String getCopyFlag() {
  return copyFlag;
}

public void setCopyFlag(String copyFlag) {
  this.copyFlag = copyFlag;
}

public int getTargetVesselIndex() {
  return targetVesselIndex;
}

public void setTargetVesselIndex(int targetVesselIndex) {
  this.targetVesselIndex = targetVesselIndex;
}

public String getChosenContracts() {
  return chosenContracts;
}

public void setChosenContracts(String chosenContracts) {
  this.chosenContracts = chosenContracts;
}

public String getChosenTestIds() {
  return chosenTestIds;
}

public void setChosenTestIds(String chosenTestIds) {
  this.chosenTestIds = chosenTestIds;
}

public String getChosenSlateIds() {
  return chosenSlateIds;
}

public void setChosenSlateIds(String chosenSlateIds) {
  this.chosenSlateIds = chosenSlateIds;
}

public String getAddOrDeleteSlate() {
  return addOrDeleteSlate;
}

public void setAddOrDeleteSlate(String addOrDeleteSlate) {
  this.addOrDeleteSlate = addOrDeleteSlate;
}

public String getAddOrDeleteTest() {
  return addOrDeleteTest;
}

public void setAddOrDeleteTest(String addOrDeleteTest) {
  this.addOrDeleteTest = addOrDeleteTest;
}

public int getSlateIndex() {
  return slateIndex;
}

public void setSlateIndex(int slateIndex) {
  this.slateIndex = slateIndex;
}

public int getTestIndex() {
  return testIndex;
}

public void setTestIndex(int testIndex) {
  this.testIndex = testIndex;
}

public String getUiEtaTime() {
  return uiEtaTime;
}

public void setUiEtaTime(String uiEtaTime) {
  this.uiEtaTime = uiEtaTime;
}

public String getUiNominationTime() {
  return uiNominationTime;
}

public void setUiNominationTime(String uiNominationTime) {
  this.uiNominationTime = uiNominationTime;
}

public String getOriginateFromSearchJob() {
  return originateFromSearchJob;
}

public void setOriginateFromSearchJob(String originateFromSearchJob) {
  this.originateFromSearchJob = originateFromSearchJob;
}

public String getJobNumber() {
  return jobNumber;
}

public void setJobNumber(String jobNumber) {
  this.jobNumber = jobNumber;
}
public String getNextListFlag() {
  return nextListFlag;
}

public void setNextListFlag(String nextListFlag) {
  this.nextListFlag = nextListFlag;
}

public String getPrevListFlag() {
  return prevListFlag;
}

public void setPrevListFlag(String prevListFlag) {
  this.prevListFlag = prevListFlag;
}
public String getUniqueFlag(){
return uniqueFlag;
}

public void setUniqueFlag(String uniqueFlag){
this.uniqueFlag = uniqueFlag;
}
public String getpopFlag(){
return popFlag;
}

public void setPopFlag(String popFlag){
this.popFlag = popFlag;
}
public String getShipAgentPhoneFlag() {
  return shipAgentPhoneFlag;
}

public void setShipAgentPhoneFlag(String shipAgentPhoneFlag) {
  this.shipAgentPhoneFlag = shipAgentPhoneFlag;
}

public String getTowCoPhoneFlag() {
  return towCoPhoneFlag;
}

public void setTowCoPhoneFlag(String towCoPhoneFlag) {
  this.towCoPhoneFlag = towCoPhoneFlag;
}

public String getApplicableContractFlag() {
  return applicableContractFlag;
}

public void setApplicableContractFlag(String applicableContractFlag) {
  this.applicableContractFlag = applicableContractFlag;
}

public String getLabAnalysisFlag() {
  return labAnalysisFlag;
}

public void setLabAnalysisFlag(String labAnalysisFlag) {
  this.labAnalysisFlag = labAnalysisFlag;
}

public String getOtApprovedFlag() {
  return otApprovedFlag;
}

public void setOtApprovedFlag(String otApprovedFlag) {
  this.otApprovedFlag = otApprovedFlag;
}

public String getAddOrDeletePrebill() {
  return addOrDeletePrebill;
}

public void setAddOrDeletePrebill(String addOrDeletePrebill) {
  this.addOrDeletePrebill = addOrDeletePrebill;
}

public long getPrebillId() {
  return prebillId;
}

public void setPrebillId(long prebillId) {
  this.prebillId = prebillId;
}


    public int getContractIndex()
    {
      return contractIndex;
    }

    public void setContractIndex(int contractIndex)
    {
      this.contractIndex = contractIndex;
    }
public String getInstructionFlag() {
  return instructionFlag;
}

public void setInstructionFlag(String instructionFlag) {
  this.instructionFlag = instructionFlag;
}

public int getJobContractCount() {
  return jobContractCount;
}

public void setJobContractCount(int jobContractCount) {
  this.jobContractCount = jobContractCount;

}
public String getBankFlag(){
return bankFlag;
}

public void setBankFlag(String bankFlag){
this.bankFlag = bankFlag;
}

public int getBankIndex(){
return bankIndex;
}

public void setBankIndex(int bankIndex){
this.bankIndex = bankIndex;
}

public String getBankCode(){
return bankCode;
}

public void setBankCode(String bankCode){
this.bankCode = bankCode;
}

public String getRebillFlag() {
  return rebillFlag;
}

public void setRebillFlag(String rebillFlag) {
  this.rebillFlag = rebillFlag;
}

public String getCreditReasonNote() {
  return creditReasonNote;
}

public void setCreditReasonNote(String creditReasonNote) {
  this.creditReasonNote = creditReasonNote;
}

public String getCreditReasonUser() {
  return creditReasonUser;
}

public void setCreditReasonUser(String creditReasonUser) {
  this.creditReasonUser = creditReasonUser;
}

   public List getJobVessels()
   {
     return jobVessels;
   }

   public void setJobVessels(List jobVessels)
   {
     this.jobVessels = jobVessels;
   }
public String getSearchCode(){
return searchCode;
}

public void setSearchCode(String searchCode){
this.searchCode = searchCode;
}

public String getDefaultJobDesc() {
	return defaultJobDesc;
}

public void setDefaultJobDesc(String defaultJobDesc) {
	this.defaultJobDesc = defaultJobDesc;
}

public String getPortLocationFlag() {
	return portLocationFlag;
}

public void setPortLocationFlag(String portLocationFlag) {
	this.portLocationFlag = portLocationFlag;
}
public String getDateFormat() {
	return dateFormat;
}

public void setDateFormat(String dateFormat) {
	this.dateFormat = dateFormat;
}
public String getEtaTimeFormat() {
	return etaTimeFormat;
}

public void setEtaTimeFormat(String etaTimeFormat) {
	this.etaTimeFormat = etaTimeFormat;
}

public String getNomTimeFormat() {
	return nomTimeFormat;
}

public void setNomTimeFormat(String nomTimeFormat) {
	this.nomTimeFormat = nomTimeFormat;
}

public String getUserTimeFormat() {
	return userTimeFormat;
}

public void setUserTimeFormat(String userTimeFormat) {
	this.userTimeFormat = userTimeFormat;
}

public String getPageNumber() {
	return pageNumber;
}

public void setPageNumber(String pageNumber) {
	this.pageNumber = pageNumber;
}

public String getOperationFlag() {
	return operationFlag;
}

public void setOperationFlag(String operationFlag) {
	this.operationFlag = operationFlag;
}

public String getErrorFlag() {
	return errorFlag;
}

public void setErrorFlag(String errorFlag) {
	this.errorFlag = errorFlag;
}

public String getErrorCode(){
return errorCode;
}

public void setErrorCode(String errorCode){
this.errorCode = errorCode;
}



public String getMassOTFlag() {
	return massOTFlag;
}

public void setMassOTFlag(String massOTFlag) {
	this.massOTFlag = massOTFlag;
}



public String getMassQtyFlag() {
	return massQtyFlag;
}

public void setMassQtyFlag(String massQtyFlag) {
	this.massQtyFlag = massQtyFlag;
}
public int getCustCount(){
return custCount;
}

public void setCustCount(int custCount){
this.custCount = custCount;
}




	public String getContrCode() {
	return contrCode;
}

public void setContrCode(String contrCode) {
	this.contrCode = contrCode;
}



	public String getContrFlag() {
	return contrFlag;
}

public void setContrFlag(String contrFlag) {
	this.contrFlag = contrFlag;
}

public String getJobDateFlag(){
return jobDateFlag;
}

public void setJobDateFlag(String jobDateFlag){
this.jobDateFlag = jobDateFlag;
}

public String getNavigationUrl() {
	return navigationUrl;
}

public void setNavigationUrl(String navigationUrl) {
	this.navigationUrl = navigationUrl;
}

public String getLoadState() {
	return loadState;
}

public void setLoadState(String loadState) {
	this.loadState = loadState;
}


public int getUniqueCount(){
return uniqueCount;
}

public void setUniqueCount(int uniqueCount){
this.uniqueCount = uniqueCount;
}

public String getRCode() {
return rCode;
}

public void setRCode(String rCode) {
	this.rCode = rCode;
}

public String getDisplayProductNames() {
	return displayProductNames;
}

public void setDisplayProductNames(String displayProductNames) {
	this.displayProductNames = displayProductNames;
}

public String getEmailNote() {
	return emailNote;
}

public void setEmailNote(String emailNote) {
	this.emailNote = emailNote;
}

public String getRegeneratePdfInvoice() {
	return regeneratePdfInvoice;
}

public void setRegeneratePdfInvoice(String regeneratePdfInvoice) {
	this.regeneratePdfInvoice = regeneratePdfInvoice;
}



public String getTemplateFlag() {
	return templateFlag;
}

public void setTemplateFlag(String templateFlag) {
	this.templateFlag = templateFlag;
}

public String getTempName() {
	return tempName;
}

public void setTempName(String tempName) {
	this.tempName = tempName;
}



public String getSearchTemplate() {
	return searchTemplate;
}

public void setSearchTemplate(String searchTemplate) {
	this.searchTemplate = searchTemplate;
}

public String getTemplateHideFlg() {
	return templateHideFlg;
}

public void setTemplateHideFlg(String templateHideFlg) {
	this.templateHideFlg = templateHideFlg;
}

public String getTemplateNum() {
	return templateNum;
}

public void setTemplateNum(String templateNum) {
	this.templateNum = templateNum;
}

public String getMonthlyJobFlag() {
	return monthlyJobFlag;
}

public void setMonthlyJobFlag(String monthlyJobFlag) {
	this.monthlyJobFlag = monthlyJobFlag;
}
public String getMonthlyCheck() {
	return monthlyCheck;
}

public void setMonthlyCheck(String monthlyCheck) {
	this.monthlyCheck = monthlyCheck;
}

public String getGoFlag(){
	return goFlag;
}
public void setGoFlag(String goFlag){
this.goFlag=goFlag;
}

public String getDateFlag(){
   return dateFlag;
}

public void setDateFlag(String dateFlag){
	this.dateFlag=dateFlag;
}

public boolean isPreviewFlag() {
	return previewFlag;
}

public void setPreviewFlag(boolean previewFlag) {
	this.previewFlag = previewFlag;
}

public boolean isInstrDisplayFlag() {
	return instrDisplayFlag;
}


public void setInstrDisplayFlag(boolean instrDisplayFlag) {
	this.instrDisplayFlag = instrDisplayFlag;
}

public String getOpenPeriodsFlag() {
	return openPeriodsFlag;
}

public void setOpenPeriodsFlag(String openPeriodsFlag) {
	this.openPeriodsFlag = openPeriodsFlag;
}
public String getShowWarn() {
	return showWarn;
}

public void setShowWarn(String showWarn) {
	this.showWarn = showWarn;
}

public boolean isLimsBranchFlag() {
	return limsBranchFlag;
}

public void setLimsBranchFlag(boolean limsBranchFlag) {
	this.limsBranchFlag = limsBranchFlag;
}

public boolean isSamBranchFlag() {
	return samBranchFlag;
}

public void setSamBranchFlag(boolean samBranchFlag) {
	this.samBranchFlag = samBranchFlag;
}

/**
 * @return the emailNoteDisplay
 */
public String getEmailNoteDisplay() {
	return emailNoteDisplay;
}

/**
 * @param emailNoteDisplay the emailNoteDisplay to set
 */
public void setEmailNoteDisplay(String emailNoteDisplay) {
	this.emailNoteDisplay = emailNoteDisplay;
}

/*


public boolean isOperationViewOnly() {
	return operationViewOnly;
}

public void setOperationViewOnly(boolean operationViewOnly) {
	this.operationViewOnly = operationViewOnly;
}


public boolean isServViewOnly() {
	return servViewOnly;
}

public void setServViewOnly(boolean servViewOnly) {
	this.servViewOnly = servViewOnly;
}

public boolean isShipViewOnly() {
	return shipViewOnly;
}

public void setShipViewOnly(boolean shipViewOnly) {
	this.shipViewOnly = shipViewOnly;
}

public boolean isTowViewOnly() {
	return towViewOnly;
}

public void setTowViewOnly(boolean towViewOnly) {
	this.towViewOnly = towViewOnly;
}
*/
/**
 * @return the massTestSlateDeleteFlag
 */
public String getMassTestSlateDeleteFlag() {
	return massTestSlateDeleteFlag;
}

/**
 * @param massTestSlateDeleteFlag the massTestSlateDeleteFlag to set
 */
public void setMassTestSlateDeleteFlag(String massTestSlateDeleteFlag) {
	this.massTestSlateDeleteFlag = massTestSlateDeleteFlag;
}

public String getUiEtaDate() {
	return uiEtaDate;
}

public void setUiEtaDate(String uiEtaDate) {
	this.uiEtaDate = uiEtaDate;
}
/*
public boolean isNomDateViewOnly() {
	return nomDateViewOnly;
}

public void setNomDateViewOnly(boolean nomDateViewOnly) {
	this.nomDateViewOnly = nomDateViewOnly;
}

public boolean isNomTzViewOnly() {
	return nomTzViewOnly;
}

public void setNomTzViewOnly(boolean nomTzViewOnly) {
	this.nomTzViewOnly = nomTzViewOnly;
}

public boolean isEtaDateViewOnly() {
	return etaDateViewOnly;
}

public void setEtaDateViewOnly(boolean etaDateViewOnly) {
	this.etaDateViewOnly = etaDateViewOnly;
}

public boolean isEtaTzViewOnly() {
	return etaTzViewOnly;
}

public void setEtaTzViewOnly(boolean etaTzViewOnly) {
	this.etaTzViewOnly = etaTzViewOnly;
}

public boolean isJobFinishDateViewOnly() {
	return jobFinishDateViewOnly;
}

public void setJobFinishDateViewOnly(boolean jobFinishDateViewOnly) {
	this.jobFinishDateViewOnly = jobFinishDateViewOnly;
}
*/
public String getPopFlag() {
	return popFlag;
}

public String getSendToAribaFlag() {
	return sendToAribaFlag;
}

public void setSendToAribaFlag(String sendToAribaFlag) {
	this.sendToAribaFlag = sendToAribaFlag;
}

public String getMonthlyvalidFlag() {
	return monthlyvalidFlag;
}

public void setMonthlyvalidFlag(String monthlyvalidFlag) {
	this.monthlyvalidFlag = monthlyvalidFlag;
}

public String getGoForm() {
	return goForm;
}

public void setGoForm(String goForm) {
	this.goForm = goForm;
}



public String getJobCancelReasonNote() {
	return jobCancelReasonNote;
}

public void setJobCancelReasonNote(String jobCancelReasonNote) {
	this.jobCancelReasonNote = jobCancelReasonNote;
}

public String getJobCancelReasonUser() {
	return jobCancelReasonUser;
}

public void setJobCancelReasonUser(String jobCancelReasonUser) {
	this.jobCancelReasonUser = jobCancelReasonUser;
}

public boolean isDeleteSampleFlag() {
	return deleteSampleFlag;
}

public void setDeleteSampleFlag(boolean deleteSampleFlag) {
	this.deleteSampleFlag = deleteSampleFlag;
}

public List<Integer> getDeletedSampleLoc() {
	return deletedSampleLoc;
}

public void setDeletedSampleLoc(List<Integer> deletedSampleLoc) {
	this.deletedSampleLoc = deletedSampleLoc;
}

public boolean isDeleteProductFlag() {
	return deleteProductFlag;
}

public void setDeleteProductFlag(boolean deleteProductFlag) {
	this.deleteProductFlag = deleteProductFlag;
}
public List<Integer> getDeletedProducts() {
	return deletedProducts;
}

public void setDeletedProducts(List<Integer> deletedProducts) {
	this.deletedProducts = deletedProducts;
}

public String getTestIndexFlag() {
	return testIndexFlag;
}

public void setTestIndexFlag(String testIndexFlag) {
	this.testIndexFlag = testIndexFlag;
}

public String getScrollFlag() {
	return scrollFlag;
}

public void setScrollFlag(String scrollFlag) {
	this.scrollFlag = scrollFlag;
}
public String getSampleLocCount() {
	return sampleLocCount;
}

public void setSampleLocCount(String sampleLocCount) {
	this.sampleLocCount = sampleLocCount;
}

public String getSearchForm() {
	return searchForm;
}

public void setSearchForm(String searchForm) {
	this.searchForm = searchForm;
}

public String getDiv1() {
	return Div1;
}

public void setDiv1(String div1) {
	Div1 = div1;
}

public String getDiv2() {
	return Div2;
}

public void setDiv2(String div2) {
	Div2 = div2;
}

public boolean isDeleteVesselFlag() {
	return isDeleteVesselFlag;
}

public void setDeleteVesselFlag(boolean isDeleteVesselFlag) {
	this.isDeleteVesselFlag = isDeleteVesselFlag;
}

public List<Integer> getDeletedVessels() {
	return deletedVessels;
}

public void setDeletedVessels(List<Integer> deletedVessels) {
	this.deletedVessels = deletedVessels;
}

public List<String> getDeletedVesselProducts() {
	return deletedVesselProducts;
}

public void setDeletedVesselProducts(List<String> deletedVesselProducts) {
	this.deletedVesselProducts = deletedVesselProducts;
}

public String getChosenManualTestIds() {
	return chosenManualTestIds;
}

public void setChosenManualTestIds(String chosenManualTestIds) {
	this.chosenManualTestIds = chosenManualTestIds;
}

public String getManualTestPrice() {
	return manualTestPrice;
}

public void setManualTestPrice(String manualTestPrice) {
	this.manualTestPrice = manualTestPrice;
}

public String getManualTestQty() {
	return manualTestQty;
}

public void setManualTestQty(String manualTestQty) {
	this.manualTestQty = manualTestQty;
}
//STAT: Issue # 75052
public RevisionNotes[] getArrRevisionNotes() {
	return arrRevisionNotes;
}

public void setArrRevisionNotes(RevisionNotes[] arrRevisionNotes) {
	this.arrRevisionNotes = arrRevisionNotes;
}
public String getUiRevisionNote() {
	return uiRevisionNote;
}

public void setUiRevisionNote(String uiRevisionNote) {
	this.uiRevisionNote = uiRevisionNote;
}
//END: Issue # 75052

// START : #119240
public String getCurrPageIdentifier() {
	return currPageIdentifier;
}

public void setCurrPageIdentifier(String currPageIdentifier) {
	this.currPageIdentifier = currPageIdentifier;
}
// END : #119240
public String getProjectId() {
    Project p = jobOrder.getProject();
    if (p != null) {
        return p.getProjectNumber();
    }
    return "";
}

public void setProjectId(String projectNumber) {
    jobOrder.setProjectNumber(projectNumber);
}

public String getProjectType() {
    Project p = jobOrder.getProject();
    if (p != null) {
         return p.getProjectType();
        }    
    return "";
}
}

