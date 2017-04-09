package com.intertek.service;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.intertek.domain.AddJobContract;
import com.intertek.domain.AddJobOrder;
import com.intertek.domain.AddWeeklyReport;
import com.intertek.domain.BankSearch;
import com.intertek.domain.BatchReprintHelper;
import com.intertek.domain.CancelledJobsSearch;
import com.intertek.domain.ContractSearch;
import com.intertek.domain.JobSearch;
import com.intertek.domain.SlateSearch;
import com.intertek.domain.TemplateSearch;
import com.intertek.domain.TestSearch;
import com.intertek.domain.VesselSearch;
import com.intertek.entity.BatchReprint;
import com.intertek.entity.Branch;
import com.intertek.entity.CfgContract;
import com.intertek.entity.ContactCust;
import com.intertek.entity.ContractCustContact;
import com.intertek.entity.Event;
import com.intertek.entity.InspectionEventTbl;
import com.intertek.entity.JobContract;
import com.intertek.entity.JobContractAttach;
import com.intertek.entity.JobContractAttachType;
import com.intertek.entity.JobContractInvoice;
import com.intertek.entity.JobContractNote;
import com.intertek.entity.JobContractSlate;
import com.intertek.entity.JobContractTest;
import com.intertek.entity.JobEvent;
import com.intertek.entity.JobLog;
import com.intertek.entity.JobManualTest;
import com.intertek.entity.JobOrder;
import com.intertek.entity.JobProd;
import com.intertek.entity.JobProdQty;
import com.intertek.entity.JobProdSample;
import com.intertek.entity.JobSlate;
import com.intertek.entity.JobTest;
import com.intertek.entity.JobVessel;
import com.intertek.entity.LogConfigDetail;
import com.intertek.entity.LogConfigList;
import com.intertek.entity.OpenPeriod;
import com.intertek.entity.Operation;
import com.intertek.entity.Prebill;
import com.intertek.entity.PriceBook;
import com.intertek.entity.RB;
import com.intertek.entity.RevisionNotes;
import com.intertek.entity.Slate;
import com.intertek.entity.Test;
import com.intertek.entity.User;

public interface JobService
{
  String getJobNumber(String branchName);
  String getTemplateNumber(String branchName);//Template
  //JobOrder getTemplateByBranch(String branchName);//Template
  List  getTemplateByNameAndBranch(String tmpName, String branchName);// Template

  String getJobNumberByBranch(Branch branch);
  JobOrder getJobOrderByJobNumber(String jobNumber);
  JobOrder getJobOrderByJobNumberWithDetail(String jobNumber);
  JobOrder getJobOrderByJobNumberWithInvoiceInfo(String jobNumber);
  JobOrder getJobOrderByJobNumberWithJobContracts(String jobNumber);
  JobOrder getJobOrderByJobNumberWithPrebills(String jobNumber);
  JobOrder addJobOrder(JobOrder jobOrder);
  JobOrder saveJobOrder(JobOrder jobOrder);
  BatchReprint saveBatchReprint(BatchReprint batchReprint);
  JobOrder updateJobOrder(JobOrder jobOrder);
  JobContract getJobContractWithInvoiceDetails(long jobContractId);

  void searchJobOrder(JobSearch jobSearch,int pageNumber,String reqForm,String sortFlag);
  void searchVessel(VesselSearch search);
  void searchContract(ContractSearch search);
  JobContractNote addJobContractNote(JobContractNote jobContractNote);
  JobContractAttach addJobContractAttach(JobContractAttach jobContractAttach, String path, MultipartFile file);
  void deleteJobContractAttach(long id, String path);
  void deleteJobLogId(long id);
  
  void updateJobContract(JobContract jobContract);
  ContractCustContact getContractDetailsByCode(String contractCode);
  ContractCustContact getContractDetailsByCode(String contractCode,String custCode,long contactId);

  List getContractDetailsBycontractCode(String contractCode);

  // List getBankByBankCode(String bankCode,String buName);
  List getBankByBankCode(String bankDesc,String buName,String currency);
  //List getBankAccountByAccount(String bankAccount,String buName);
  List getBankAccountByAccount(String bankAccountDesc,String buName,String currency);
  List getBankAccountByBankCode(String bankAccountDesc,String buName,String currency,String bankCode);

  void searchBankCode(BankSearch search,String sortFlag);
  void searchBankAccount(BankSearch search,String sortFlag);
  JobContract addJobContractInsp(JobContract jobContract);
  ContactCust getBillingContactByContactId(long contactId,String custCode);
  List getBillingContactByCustCode(String custCode);
  void searchTest(TestSearch search);
  void searchSlate(SlateSearch search);
  List getReferenceFieldsByContractCode(String contractCode);
  List getProductGroups(List<String> productTypes);
  List getVesselTypes();
  List getTestProductGroups();
  List getJObIdsById(String jobId);

  Prebill getPrebillById(Long prebillId);

  JobManualTest addManualTest(JobManualTest jobManualTest);
  Test getTestByTestId(String testId);
//  JobManualTest getJobManualTestByTestId(String jobManualTest, String nextQty, String nextPrice);
  List getJobTestsByJobNumberVesselRowProductAndSampleLoc(String jobNumber,Integer vesselRow,Integer prodSeqNum,Integer sampSeqId);
  List getJobManualTestsByJobNumberVesselRowProductAndSampleLoc(String jobNumber,Integer vesselRow,Integer prodSeqNum,Integer sampSeqId);
  JobContractTest saveJobContractTest(JobContractTest jobContractTest);
  JobTest saveJobTest(JobTest jobTest);
  Slate getSlateBySlateId(String slateId);
  List getJobSlatesByJobNumberVesselRowProductAndSampleLoc(String jobNumber,Integer vesselRow,Integer prodSeqNum,Integer sampSeqId);
  JobContractSlate saveJobContractSlate(JobContractSlate jobContractSlate);
  JobSlate saveJobSlate(JobSlate jobSlate);
  List getJobContractsByJobNumber(String jobNumber);
  JobVessel saveJobVessel(JobVessel jobVessel);
  List getJobVesselsByJobNumber(String jobNumber);
  JobProd saveJobProduct(JobProd jobProd);
  List getJobProductsByJobNumberAndVesselRow(String jobNumber,Integer vesselRow);
  JobProdQty saveJobProdQty(JobProdQty jobProdQty);
  List getJobProdQtysByJobNumberVesselRowAndProductName(String jobNumber,Integer vesselRow,Integer prodSeqNum);
  InspectionEventTbl saveInspectionEventTbl(InspectionEventTbl inspectionEventTbl);
  JobEvent saveJobEvent(JobEvent jobEvent);
  List getJobEventsByJobNumberVesselRowAndProductName(String jobNumber,Integer vesselRow,Integer prodSeqNum);
  JobProdSample saveJobProdSample(JobProdSample jobProdSample);
  List getJobProdSamplesByJobNumberVesselRowAndProductName(String jobNumber,Integer vesselRow,Integer prodSeqNum);
  JobContract getJobContractByJobNumberAndContractCode(String jobNumber,String contractCode);
  String getLocationByContractCode(String contractCode);
// String getZoneByBranchCodeandContractCode(String branchCode,String contractCode);
  String getZoneByBranchCodeandContractCode(String branchCode,String contractCode,String priceBook,Date asOfDate);
 // List getPortCodeByContractCode(String contractCode);
  List getPortCodeByContractCode(String contractCode,String priceBookId, String asOfDate);
  //List getZoneByPortCodeandContractCode(String portCode,String contractCode);
  List getZoneByPortCodeandContractCode(String portCode,String contractCode,String priceBookId,String asOfDate);
  List getZoneByContractCode(String contractCode,String priceBookId, String asOfDate);



  //CfgContract getCfgContractByContractCode(String contractCode);
  List getCurrencyTransByCurrency(String currency, Date effectiveDate);
  //List getZoneIdByBranchCodeandContractCode(String branchCode,String contractCode);
  List getZoneIdByBranchCodeandContractCode(String branchCode,String contractCode,String priceBookId,String asOfDate);
  List getAllJobContractByJobNumber(String jobNumber);
  List<JobContractNote> getJobContractNotesByContractIdandJobNumber(String jobNumber,String contractCode,long jobId);
  User getUserByName(String name);
  boolean checkJobContract(String jobNumber,String custCode,String contractCode,long contactId);
  void deleteJobNoteInsp(JobContractNote jobContractNote);
  void deleteJobInsp(JobContract jobContract);
  JobContractNote saveJobContractNote(JobContractNote jobContractNote);
  JobContractAttach saveJobContractAttach(JobContractAttach jobContractAttach);
  public List<JobContractAttach> getJobContractAttachByJobContractId(long jobContractId);
  JobContract saveJobContractInsp(JobContract jobContract);

  void removeObject(Object obj);
  List<JobContractNote> getJobContractNotesByJobContract(JobContract jobcontract);
  List getJobIdById(String jobid);
  void updateJobContractNote(JobContractNote jobContractNote);
  JobLog addJobLog(JobLog jobLog);
  void searchJobId(JobSearch jobSearch,String sortFlag);
  RB getRBByRBKeyAndContractCode(String rbKey,String contractCode);
  JobOrder getNextOrPreviousJobNumber(JobSearch jobSearch,String jobNumber,String nextOrPrevFlag);

  String getUrlHrefJobTypeByJobOrder(JobOrder jobOrder);
  String getUiJobOperations(String operation);
  String getUiJobTypes(String jobType);
  String getUiJobStatus(String jobStatus);
  String getUiContractStatus(String contractStatus);
  //JobType getJobOperationByJobType(String jType);
  Operation getOperationByOperationCode(String operationCode);
  Event getEventByEventCode(String eventCode);

  List getBankCodebyBunameandCurrency(String buname,String currency);
  List getBankAccountByBankCodeAndCurrency(String buname,String currency,String bankCode);
  List getPrimBankCodebyBunameandCurrency(String buname,String currency);
  List getPrimBankAccountByBankCodeAndCurrency(String buname,String currency,String bankCode);

  void saveJobContractInvoice(JobContractInvoice jobContractInvoice);
  List  getJobOrdersByJobNumberWithJobContracts(String jobNumber);

  byte[] getJobOrderPdfData(String jobNumber, String dirName, String jasperDirName);

  CfgContract getPriceBookByContractCode(String contractCode,Date asOfDate);
  PriceBook getPriceBookIdByCurrencyandSeries(String currency, String pbSeries, Date asOfDate);

  void reopenJobOrder(JobOrder jobOrder, String toStateName);
  void cancelJobOrder(JobOrder jobOrder, String toStateName);
  void createJobOrder(JobOrder jobOrder, String toStateName);
  void updateJobOrderStatus(String jobNumber, String toStateName);

  void deleteJobContractNotesByJobContract(long jobContractId);
  List<JobContractNote> getJobContractNoteByJobContractId(long jobContractId);
  void deleteJobContractNote(long id);
  JobContract getJobContractById(long id);
  JobContractNote mergeJobContractNote(JobContractNote jobContractNote);
  JobContract getJobContractByIdWithDetails(long id);
  List getSchedulersByJobContractIds(String[] jobContractList);
  List getJobOrdersByJobContractsAndScheduler(String[] jobContractList,long id);

  void searchTemplate(TemplateSearch search,String sortFlag);
  List getMonthlyJobIdById(String jobid,String branchName,String contractCode,String parentCustCode);

  void searchMonthlyJob(JobSearch search,String sortFlag);

  List checkMonthlyJob(String jobNumber);
 List getConfName(String confName,String uname );
  JobContractNote getJobContractNoteByJobId(long id, long jobId);

  List getJobsByUserName(String userName);
  List getJobContractNotesByUserName(String userName);
  List getCloumnsByTabName(String tabName,long confListId);
  List getTabNames();
  List getColsandTabsByListId(long confListId);
  LogConfigList  saveConfig(LogConfigList logConfigList);

  int getPeriods(String buName,Date jobFinishDate);
  OpenPeriod findOpenPeriodByBuName(String buName);
  LogConfigList  addConfig(LogConfigList logConfigList);

  void addlogConfigDetail(LogConfigDetail logConfigDetail);

  void searchConfiguration(JobSearch search,String sortFlag);

  LogConfigDetail getDbColumnValue(String colHeader);
  List getColsandTabsByConfListId(long confListId);

  boolean getConfigListByName(String confListName);
  LogConfigList getDefaultConfigListByUserName(String userName);
  int updateDefaultConfigListByUserName(String userName);
  

 
  LogConfigList getConfListNameExists(String confListName);
  
  LogConfigList getConfigNameById(String confListName);
  boolean getJobContractNotesByNoteandId(long id,String note);
  byte[] generateBatchReprintPDF(String dirName, String jasperDirName, String[] invoiceIdArray);
  List<JobContractInvoice> searchInvoice(BatchReprintHelper batchReprint, int pageNumber, String sortFlag);
  List<BatchReprint> getLastFiveBatchReprint(String branchName);
  List<JobContractInvoice> getBatchReprint(Date toDate, BatchReprintHelper batchReprintHelper);
  BatchReprint getBatchReprintById(long id);
  AddJobContract getJobLogDateandTime(AddJobContract addJobContracts,JobLog jobLog);
  JobLog getJobLogByJobContracts(JobContract jobcontract);
  JobContract saveJobContract(JobContract jobContract);
  JobLog getJobLogById(long id);
  List getLatestInvoiceByJobContractId(long id);
  JobContract saveJobContracts(JobContract jobContract);
  void deleteTemplate(JobOrder jobOrder);
  
  List<JobContractInvoice> getJobContractInvoices(Long[] jobContractInvoiceIDs);
  public JobContract getJobContractHeaderById(Long jobContractId);
  
  
  void searchMonthlyJobDetails(JobSearch search,String sortFlag,Date fromDate,Date toDate);
  JobContractInvoice getLastJobContractInvoice(Long jobContractId);
  byte[] generateWeeklyReport(AddWeeklyReport addWeeklyReport ,String dirName, String jasperDirName);
 
  public List getJobProdSampleSeqId(int prodSeqNum,String jobNumber,int linkedVesselRow,String sampleLoc);
  public void updateJobProdSampleSeqId(JobProdSample jobProdSample);
  public List getJobProductsSeqId(int linkedVesselRow,String jobNumber,String productName);
  public void updateJobProdsSeqId(JobProd jobProd);
  public List<JobContractAttachType> getJobContractAttachTypes();
  public List getBillingContactByCustCodeAndContractCode(String customerCode,String  contractCode);
  public void updateJobVesselsSeqId(JobVessel jobVessel);
  public void updateOnlyJobOrder(JobOrder jobOrder);
  public List<JobOrder> getReceivedJobsByUserName(String userName);
  public List<JobOrder> getAdminContactJobsByUserName(String userName);
  public List<JobOrder> getCreatedByJobsByUserName(String userName);
  public List<JobOrder> getUpdatedByJobsByUserName(String userName);
  public List<LogConfigList> getLogConfigListsByCreatedByUser(String userName);
  public List<LogConfigList> getLogConfigListsByUpdatedByUser(String userName);
  
  //START: Issue # 75052
  public void saveRevisionNotes(RevisionNotes revisionNotes);
  public List<RevisionNotes> getRevisionNotes(String jobNumber);  
  //END: Issue # 75052
  
  //START 125185
  public boolean notifyAboutPrebillEntry(Long jobContractID);
  //END 125185
  
  // START : #119240
  public String getUrlByCurrPageIdentifier(JobOrder jobOrder, String currPageIdentifier);
  // END : #119240
  
  // START : #127441
  public void searchCancelledJobDetails(CancelledJobsSearch search, String sortFlag);
  // END : #127441
   
}

