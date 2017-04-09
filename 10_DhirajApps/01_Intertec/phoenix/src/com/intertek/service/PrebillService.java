package com.intertek.service;

import java.util.List;

import com.intertek.entity.BusinessUnit;
import com.intertek.entity.InvoiceFileType;
import com.intertek.entity.JobContract;
import com.intertek.entity.JobContractInvoice;
import com.intertek.entity.Prebill;
import com.intertek.entity.PrebillSplit;

public interface PrebillService
{
  List getPrebillsByJobContractId(
    Long jobContractId
  );

  JobContract getJobContractById(Long jobContractId);

  //void generateInvoice(Long jobContractId, String dirName, String invoiceId,String creditReasonNote,String creditReasonUser);
  //void generateInvoices(List jobContractIds, String dirName);

  JobContractInvoice createJobContractInvoice(JobContractInvoice jobContractInvoice);
  
  public JobContractInvoice calculateTotalAmounts(JobContractInvoice jobContractInvoice, String buName);
  
  List<String> generateInvoicePDF(
    Long jobContractInvoiceId,
    String dirName,
    String jasperDirName,
    boolean generateConsol
  );

  JobContractInvoice getJobContractInvoiceById(long id);
  void saveJobContractInvoice(JobContractInvoice jobContractInvoice);

  byte[] previewInvoice(long jobContractId, String jobNumber, String uid20, String dirName, String jasperDirName);

  PrebillSplit getPrebillSplitByPrebillIdAndBranchCode(long prebillId, String branchCode);

  void saveJobContract(JobContract jobContract);
  public JobContract getJobContractByJobContractInvoiceId(Long jobContractInvoiceId);

  void checkInvoiceGenerationPreRequisite(JobContract jobContract, boolean isCredit);
  JobContractInvoice getLastJobContractInvoice(Long jobContractId);
  String getLastInvoiceCreditInd(Long jobContractId);
  List getPrebillSplitsByUserName(String userName);
  void savePrebillSplit(PrebillSplit prebillSplit);
  void savePrebill(Prebill prebill);
  List getJobContractInvoicesByUserName(String name);
  void updateJobContractInvoicePdfGeneratedFlag(Long jobContractInvoiceId, boolean pdfGeneratedFlag, String pdfName);
  List<JobContractInvoice> getJobContractInvoicesByReceivedBy(String userName);
  boolean convertConToReg(long jobContractId, long jobContractInvoiceId, String dirName, String jasperDirName) throws Exception;
  List getInvoiceLinesByJobContractInvoiceId(long jobContractInvoiceId);
  boolean hasPrebills(long jobContractId);
  void updateJobContractStatus(long jobContractId, String status);
  List<InvoiceFileType> findInvoiceFileTypeByBuAndType(String buName, String invoiceType);
  Integer getDecimalDigitsByCurrency(String currency);
  void setInvoiceFileCountable(long id, boolean countable);
  String getBusVatLocRegistrationId(BusinessUnit businessUnit, String countryCode, String stateCode);
  void checkInvoiceAndPdfGenerated(long id);
  void updateInvoicePdfGeneratedFlag(Long jobContractInvoiceId, boolean pdfGeneratedFlag);
}


