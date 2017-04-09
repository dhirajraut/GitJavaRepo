package com.intertek.service;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;


import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperPrint;

import com.intertek.entity.BusinessUnit;
import com.intertek.entity.CustAddress;
import com.intertek.entity.Customer;
import com.intertek.entity.InvoiceFile;
import com.intertek.entity.InvoiceFileType;
import com.intertek.entity.InvoiceLine;
import com.intertek.entity.JobContract;
import com.intertek.entity.JobContractInvoice;
import com.intertek.entity.Prebill;
import com.intertek.entity.PrebillSplit;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;
import com.intertek.report.JasperFillReport;
import com.intertek.report.JasperRunner;
import com.intertek.util.NumberUtil;

public class PrebillServiceImpl extends BaseService implements PrebillService
{
  public List getPrebillsByJobContractId(
    Long jobContractId
  )
  {
    return getDao().find(
      "select p from Prebill p left join fetch p.prebillSplits "
      + "where p.jobContract.id = ? order by p.vesselSortNum, p.lotSortNum, p.chargeSortNum",
      new Object[] { jobContractId }
    );
  }
  public JobContract getJobContractById(Long jobContractId)
  {
    List jcs = getDao().find(
      "from JobContract jc left join fetch jc.customer left join fetch jc.vatRegCountry left join fetch jc.jobOrder jo left join fetch jo.businessUnit left join fetch jo.branch left join fetch jc.jobContractInvoices left join fetch jc.prebills p left join fetch p.prebillSplits where jc.id = ?",
      new Object [] { jobContractId }
    );

    if(jcs.size() > 0) return (JobContract)jcs.get(0);

    return null;
  }
  private JobContract getJobContractHeaderById(Long jobContractId)
  {
    List jcs = getDao().find(
      "from JobContract jc left join fetch jc.jobOrder j left join fetch j.businessUnit left join fetch j.serviceLocation  where jc.id = ?",
      new Object [] { jobContractId }
    );

    if(jcs.size() > 0) return (JobContract)jcs.get(0);

    return null;
  }

/*
  public void generateInvoice(Long jobContractId, String dirName, String invoiceId,String creditReasonNote,String creditReasonUser)
  {
    JobContract jobContract = getJobContractById(jobContractId);
    if(jobContract == null) return;

    checkInvoiceGenerationPreRequisite(jobContract);

    AutoNumberService autoNumberService = (AutoNumberService)ServiceLocator.getInstance().getBean("autoNumberService");
    if(invoiceId == null)
    {
      invoiceId = autoNumberService.getSequenceNumber(
        jobContract.getJobOrder().getBuName(),
        jobContract.getJobOrder().getBranch().getNumberCategory(),
        Constants.INV
      );
      if(invoiceId == null)
      {
        throw new ServiceException(
          "AUTO_NUMBER_RETURN_NULL",
          new Object[] {
            jobContract.getJobOrder().getBuName(),
            jobContract.getJobOrder().getBranch().getNumberCategory(),
            Constants.INV
          }
        );
      }

      String creditInd = null;
      if(creditReasonNote != null)
      {
        creditInd = Constants.CREDIT_INDICATOR_C;
      }
      else
      {
        creditInd = Constants.CREDIT_INDICATOR_I;
      }

      jobContract.setInvoice(invoiceId);
      if(jobContract.getJobContractStatus() != null && (!jobContract.getJobContractStatus().trim().equals("")))
      {
        if(jobContract.getJobContractStatus().trim().equals(Constants.JOBCON_OPEN_STATUS))
          jobContract.setJobContractStatus(Constants.JOBCON_INVOICED_STATUS);
        //If Rebill without credit, set contract status to invoice else leave contract status to rebilled
        if(jobContract.getJobContractStatus().trim().equals(Constants.JOBCON_REBILLED_STATUS))
        {
          if(creditInd.equals(Constants.CREDIT_INDICATOR_I))
            jobContract.setJobContractStatus(Constants.JOBCON_INVOICED_STATUS);
        }
      }

      jobContract.setCreditInd(creditInd);
      jobContract.setSentToFinFlag(Constants.NEW);

      JobContractInvoice jobContractInvoice = new JobContractInvoice();
      jobContractInvoice.setInvoice(invoiceId);
      jobContractInvoice.setRuncntlid(invoiceId);
      jobContractInvoice.setJobContract(jobContract);
      jobContractInvoice.setCreditReasonNote(creditReasonNote);
      jobContractInvoice.setCreditReasonUserName(creditReasonUser);
      jobContractInvoice.setCreditInd(creditInd);

      //InvoiceUtil.copyPrebillsToInvoiceLines(jobContract, jobContractInvoice);

      if(jobContract.getInvoiceType() != null && (!jobContract.getInvoiceType().trim().equals("")))
      {
        if(jobContract.getInvoiceType().trim().equals(Constants.INV_TYPE_CON))
        {
          jobContractInvoice.setBillType(Constants.INV_TYPE_CON);
            jobContractInvoice.setBillStatus(Constants.BILL_STATUS_RDY);
        }
          if(jobContract.getInvoiceType().trim().equals(Constants.INV_TYPE_REG))
        {
            if(jobContract.getTaxVatFlag())
            jobContractInvoice.setBillType(Constants.INV_TYPE_TAX);
            else
            jobContractInvoice.setBillType(Constants.INV_TYPE_REG);

            jobContractInvoice.setBillStatus(Constants.BILL_STATUS_INV);
        }
      }

      //jobContract.getJobContractInvoices().add(jobContractInvoice);
      getDao().save(jobContractInvoice);
    }

    //Print invoices only if invoice_type = "REG" : issue no: 34180
    if(jobContract.getInvoiceType() != null && (!jobContract.getInvoiceType().trim().equals("")) &&
        jobContract.getInvoiceType().trim().equals(Constants.INV_TYPE_REG))
    {
      JasperPrint jasperPrint;
      Map paramMap = new HashMap();
      paramMap.put("dirName", dirName);

      paramMap.put("Job_Number", jobContract.getJobNumber());
      paramMap.put("uid20", jobContract.getUid20());
      paramMap.put("Invoice", jobContract.getInvoice());

      System.out.println("dir:"+dirName);
      System.out.println("Job_Number:"+jobContract.getJobNumber());
      System.out.println("uid20:"+jobContract.getUid20());
      System.out.println("Invoice:"+jobContract.getInvoice());

      javax.sql.DataSource dataSource = (javax.sql.DataSource)ServiceLocator.getInstance().getBean("entityDataSource");
      java.sql.Connection connection = null;

      try
      {
        //java.io.InputStream is = CommonUtil.getClassPathResource("invoice/invoice.jasper");
        java.io.InputStream is = new FileInputStream(dirName + "invoice_master.jasper");
        connection = dataSource.getConnection();
        if(connection==null)
          System.out.println("connection null");
        jasperPrint = JasperFillManager.fillReport(
          is,
          paramMap,
          connection
          //new JRBeanCollectionDataSource(
          //  invoiceDataList
          //)
        );

        JasperExportManager.exportReportToPdfFile(
          jasperPrint,
          dirName + invoiceId + ".pdf"
        );
      }
      catch (Throwable e)
      {
        e.printStackTrace();
        throw new ServiceException("INVOICE_GENERATION_ERROR", new Object[] {e.getMessage()}, e);
      }
      finally
      {
        if(connection != null)
        {
          try { connection.close(); } catch(Exception e) {}
        }
      }
    }
  }

  public void generateInvoices(List jobContractIds, String dirName)
  {
    if(jobContractIds != null)
    {
      for(int i=0; i<jobContractIds.size(); i++)
      {
        Long jobContractId = (Long)jobContractIds.get(i);
        generateInvoice(jobContractId, dirName, null,null,null);
      }
    }
  }
*/



  public JobContractInvoice createJobContractInvoice(JobContractInvoice jobContractInvoice)
  {
    if(jobContractInvoice == null) return null;

    JobContract jobContract = jobContractInvoice.getJobContract();
    if(jobContract == null) return null;

    JobContract existedJobContract = getJobContractHeaderById(jobContract.getId());
    if(existedJobContract == null) return null;

    existedJobContract.setJobContractStatus(jobContract.getJobContractStatus());
    existedJobContract.setCreditInd(jobContract.getCreditInd());

    AutoNumberService autoNumberService = (AutoNumberService)ServiceLocator.getInstance().getBean("autoNumberService");
System.out.println("here after auto service");
    String numberType = Constants.INV;
    String invoiceToAdjust = null;
    if(Constants.CREDIT_INDICATOR_C.equals(jobContract.getCreditInd()))
    {
      numberType = Constants.CR;
      invoiceToAdjust = jobContractInvoice.getInvoiceToAdjust();
      if(invoiceToAdjust == null) return null;
    }

    String invoiceId = autoNumberService.getSequenceNumber(
      jobContract.getJobOrder().getBuName(),
      jobContract.getJobOrder().getBranch().getNumberCategory(),
      numberType
    );

    System.out.println("here after invoice number"+invoiceId );
    if(Constants.CREDIT_INDICATOR_C.equals(jobContract.getCreditInd()))
    {
      if(invoiceId == null)
      {
        invoiceId = invoiceToAdjust + Constants.CR;
      }
      else
      {
        invoiceId = invoiceId + Constants.CR;
      }
    }

    if(invoiceId == null)
    {
      throw new ServiceException(
        "AUTO_NUMBER_RETURN_NULL",
        new Object[] {
          jobContract.getJobOrder().getBuName(),
          jobContract.getJobOrder().getBranch().getNumberCategory(),
          Constants.INV
        }
      );
    }

    //setting BuVatRegistrationId
    jobContractInvoice.setBuVatRegId(getBusVatLocRegistrationId(existedJobContract.getJobOrder().getBusinessUnit(), existedJobContract.getJobOrder().getServiceLocation().getCountryCode(), jobContractInvoice.getVatProvince()));

    jobContractInvoice.setInvoice(invoiceId);
    jobContractInvoice.setRuncntlid(invoiceId);
    existedJobContract.setInvoice(invoiceId);
    jobContractInvoice.setJobContract(existedJobContract);
    //existedJobContract.getJobContractInvoices().add(jobContractInvoice);

//    jobContractInvoice = calculateTotalAmounts(jobContractInvoice, jobContract.getJobOrder().getBuName());
    return getDao().save(jobContractInvoice);
  }

  /**
 * Name :generateInvoicePDF
 * Date :Nov 5, 2008
 * Purpose :
 * @param jobContractInvoiceId
 * @param dirName
 * @param jasperDirName
 * @param generateConsol: If this method is getting called from consolidated header generation it is equal to true. It means generate the pdf for individual consolidated invoice
 */
public List<String> generateInvoicePDF(
    Long jobContractInvoiceId,
    String dirName,
    String jasperDirName,
    boolean generateConsol
  )
  {
  ArrayList<String> fileNames=new ArrayList<String>();
    System.out.println("here in generateInvoicePDF id is"+jobContractInvoiceId );
    JobContractInvoice jobContractInvoice = getJobContractInvoiceById(jobContractInvoiceId);
    if(jobContractInvoice == null) return null;

    JobContract jobContract = jobContractInvoice.getJobContract();
    if(jobContract == null) return null;

    String invoiceId = jobContractInvoice.getInvoice();
    if(invoiceId == null) return null;

    //Print invoices only if invoice_type = "REG" : issue no: 34180
    if( jobContract.getInvoiceType() != null && (!jobContract.getInvoiceType().trim().equals("")) &&
        ( jobContract.getInvoiceType().trim().equals(Constants.INV_TYPE_REG)  || ( jobContract.getInvoiceType().trim().equals(Constants.INV_TYPE_CON) && jobContractInvoice.getCreditInd().equals(Constants.CREDIT_INDICATOR_C) ) || ( jobContract.getInvoiceType().trim().equals(Constants.INV_TYPE_CON) && generateConsol )   ))
    {
      JasperPrint jasperPrint;
      Map paramMap = new HashMap();
      paramMap.put("dirName", jasperDirName);
      paramMap.put("Invoice_Id", String.valueOf(jobContractInvoice.getId()));

    //If there is no prebill for that job contract, it is a people soft job and we need to send this parameter as true to jasper file
    //The reason is: PS jobs do not have vessel or lot sort_num.  They utilize level0 and level1 to sort
      if(hasPrebills(jobContract.getId())){
        System.out.println("NoPrebillFlag:F");
        paramMap.put("NoPrebillFlag", "F");
      }else{
        System.out.println("NoPrebillFlag:T");
        paramMap.put("NoPrebillFlag", "T");
      }

      System.out.println("dir:"+jasperDirName);
      System.out.println("Invoice_Id:"+ jobContractInvoice.getId());

      javax.sql.DataSource dataSource = (javax.sql.DataSource)ServiceLocator.getInstance().getBean("entityDataSource");
      java.sql.Connection connection = null;

      try
      {
    String buName = jobContractInvoice.getJobContract().getJobOrder().getBuName();

    java.io.InputStream is=null;
        String reportName = null;
    if(!jobContractInvoice.getTaxVatFlag() || ";USA01;USA02;USA03;".indexOf(";"+buName+";")>=0){
      is = new FileInputStream(jasperDirName + "invoice_master_nontaxfooter.jasper");
            reportName = "invoice_master_nontaxfooter";
    }
    else{
      is= new FileInputStream(jasperDirName + "invoice_master_taxfooter.jasper");
            reportName = "invoice_master_taxfooter";
    }

        //java.io.InputStream is = new FileInputStream(jasperDirName + "invoice_master.jasper");

        connection = dataSource.getConnection();
        String dateFolder = DateUtil.formatDate(new Date(), "yyyyMMdd");
        File f=new File(dirName.concat(dateFolder));
        if(!f.exists())
          f.mkdir();
        String pdfName  = dateFolder + "/" +  jobContract.getJobNumber()+ "_" + invoiceId + ".pdf";
        if(JasperRunner.generateReport(reportName, is, paramMap, connection, dirName, pdfName) ){
          updateJobContractInvoicePdfGeneratedFlag(jobContractInvoice.getId(), true, pdfName);
        }
        fileNames.add(pdfName);

        //generating extra file types
        //String buName = jobContractInvoice.getJobContract().getJobOrder().getBuName();
        String invoiceType = jobContractInvoice.getInvoiceType();

        List<InvoiceFileType> types = findInvoiceFileTypeByBuAndType(buName, invoiceType);
        for(InvoiceFileType invoiceFileType: types){
          String type = invoiceFileType.getInvoiceFileType();
          System.out.println("invoiceFileType"+type);
        //If jobContractInvoice creditInd is C dont generate korean invoice
        //If this method is being called from a consol header to generate individual consol invoices, dont generate korean invoice
         if(type.equals("KOR") && jobContractInvoice.getTaxVatFlag()  && !generateConsol){
            boolean printKoreanTax = false;
            //if VatTreatment='domestic' print korean tax invoice
            //if vatTreatment='export'  look into invoice lines. If any line has a vat , print it
            if(jobContract.getVatTreament()!=null && jobContract.getVatTreament().trim().length()>0 && jobContract.getVatTreament().equals("export") ){
              List<InvoiceLine> invoiceLines = getInvoiceLinesByJobContractInvoiceId(jobContractInvoice.getId());
              for(InvoiceLine line : invoiceLines){
                if(line.getVatAmt()>0)
                  printKoreanTax = true;
              }
            }else
              printKoreanTax = true;
            if(printKoreanTax){
              LangService langService = (LangService)ServiceLocator.getInstance().getBean("langService");
              if(langService.checkLangPrerequisits(buName, type, jobContractInvoice.getJobContract().getCustCode(), jobContractInvoice.getJobContract().getCustLocationNumber())){
                is = new FileInputStream(jasperDirName + "invoice_master_kor.jasper");
                pdfName  = dateFolder + "/" +  jobContract.getJobNumber()+ "_" + invoiceId + "_"+ type + ".pdf";
                InvoiceFile invoiceFile=getInvoiceFile(pdfName, jobContractInvoice.getId(), type);
                  if(invoiceFile==null){
                    //InvoiceFile invoiceFile = new InvoiceFile(pdfName, true, serNum, jobContractInvoice, invoiceFileType);
                    //getDao().save(invoiceFile);
                    invoiceFile=saveInvoiceAndSerialNum(pdfName, true, jobContractInvoice, invoiceFileType);
                  }
                  paramMap.put("serialNumber", invoiceFile.getSerialNumber());
                  //java.sql.Connection connection2 = dataSource.getConnection();;
                  //java.sql.Connection connection2 = dataSource.getConnection();;
                  // no change to _kor reports
                  JasperRunner.generateReport(is, paramMap, connection, dirName, pdfName);
                  fileNames.add(pdfName);
                  //try{
                  //  connection2.close();
                  //}
                  //catch(Exception j){
                  //}

              }
            }
            }
        }
      }
      catch (Throwable e)
      {
        e.printStackTrace();
        throw new ServiceException("INVOICE_GENERATION_ERROR", new Object[] {e.getMessage()}, e);
      }
      finally
      {
        if(connection != null)
        {
          try { connection.close(); } catch(Exception e) {}
        }
      }
    }
    return fileNames;
  }

  private InvoiceFile saveInvoiceAndSerialNum(String invoiceFileName, Boolean countable, JobContractInvoice jobContractInvoice, InvoiceFileType invoiceFileType){
    InvoiceFile invoiceFile = new InvoiceFile();
    invoiceFile.setInvoiceFileName(invoiceFileName);
    invoiceFile.setCountable(countable);
    invoiceFile.setJobContractInvoice(jobContractInvoice);
    invoiceFile.setInvoiceFileType(invoiceFileType);

    invoiceFile=getDao().save(invoiceFile);
    String serNum=invoiceFile.getSerialNumber();
    if(serNum==null || serNum.trim().isEmpty()){
      String buName = jobContractInvoice.getJobContract().getJobOrder().getBuName();
      if(serNum==null){
          AutoNumberService autoNumberService = (AutoNumberService)ServiceLocator.getInstance().getBean("autoNumberService");
          String numberType="I";
          String type = invoiceFileType.getInvoiceFileType();
          if(type==null || type.equals(Constants.INV_TYPE_CON)){
            numberType="C";
          }
          serNum = autoNumberService.getSeqNumber(
              buName,
              jobContractInvoice.getVatProvince(),
                  numberType
          );
      }

      invoiceFile.setSerialNumber(serNum);
    }
    return getDao().save(invoiceFile);
  }


  public List<InvoiceFileType> findInvoiceFileTypeByBuAndType(String buName, String invoiceType){
    return getDao().find(
        "from InvoiceFileType where buName = ? and invoiceType = ? ",
      new Object[] { buName, invoiceType});
  }

  public Customer getCustomerByCustCode(String custCode)
  {
    List results = getDao().find(
      "from Customer c where c.custCode = ?",
      new Object[] { custCode }
    );

    if(results.size() > 0) return (Customer)results.get(0);

    return null;
  }

  public CustAddress getCustAddressByCustCode(String custCode)
  {
    List results = getDao().find(
      "from CustAddress c where c.custCode = ?",
      new Object[] { custCode }
    );

    if(results.size() > 0) return (CustAddress)results.get(0);

    return null;
  }

  public   JobContractInvoice getJobContractInvoiceById(long id)
  {
      List results = getDao().find(
              "from JobContractInvoice jci left join fetch jci.jobContract where jci.id = ?",
              new Object[] { id }
            );

            if(results.size() > 0) return (JobContractInvoice)results.get(0);

            return null;
  }

  public void saveJobContractInvoice(JobContractInvoice jobContractInvoice)
  {
    getDao().save(jobContractInvoice);
  }

  public byte[] previewInvoice(long jobContractId, String jobNumber, String uid20, String dirName, String jasperDirName)
  {

  //String jaspersDirName =   dirName + "JASPERS\\";
    byte[] result = null;
    JasperPrint jasperPrint = null;
    Map paramMap = new HashMap();

    paramMap.put("Job_Number", jobNumber);
    paramMap.put("uid20", uid20);
    javax.sql.DataSource dataSource = (javax.sql.DataSource)ServiceLocator.getInstance().getBean("entityDataSource");
    java.sql.Connection connection = null;

    try
    {
      java.io.InputStream is = null;
      String reportName = null;
      if(hasPrebills(jobContractId)){
        is = new FileInputStream(jasperDirName + "invoice_preview.jasper");
        reportName = "invoice_preview";
      }else{
        JobContractInvoice jobContractInvoice = getLastJobContractInvoice(jobContractId);
        if(jobContractInvoice != null && jobContractInvoice.getId()>0){
          is = new FileInputStream(jasperDirName + "invoice_preview_rebill.jasper");
          reportName = "invoice_preview_rebill";
          paramMap.put("jci_id", String.valueOf(jobContractInvoice.getId()));
        }
      }
      //java.io.InputStream is = CommonUtil.getClassPathResource("invoice/invoice_preview.jasper");

    if(is == null)
      return null;
      connection = dataSource.getConnection();

      jasperPrint=JasperFillReport.fillReport(reportName, is, paramMap, connection);
      if(jasperPrint==null){
          if(connection != null){
            try { connection.close(); } catch(Exception e) {}
          }
          if(is != null){
              is.close();
          }
        throw new ServiceException("INVOICE_GENERATION_ERROR", new Object[] {"Fail to Fill Report"}, null);
      }
      /*
      jasperPrint = JasperFillManager.fillReport(
        is,
        paramMap,
        connection
      );
       */
      result = JasperExportManager.exportReportToPdf(
        jasperPrint
      );
    }
    catch (Throwable e)
    {
      e.printStackTrace();
      throw new ServiceException(e.getMessage(), e);
    }
    finally
    {
      if(connection != null)
      {
        try { connection.close(); } catch(Exception e) {}
      }
    }

    return result;
  }

  public PrebillSplit getPrebillSplitByPrebillIdAndBranchCode(long prebillId, String branchCode)
  {
    List splits = getDao().find(
      "from PrebillSplit ps  where ps.branchName = ? and ps.prebill.id = ? ",
      new Object [] { branchCode, prebillId }
    );

    if(splits.size() > 0) return (PrebillSplit)splits.get(0);

    return null;
  }

  public void checkInvoiceGenerationPreRequisite(JobContract jobContract, boolean isCredit)
  {
    if(jobContract == null) return;

    List conditions = new ArrayList();
    if(jobContract.getBillingContact() == null) conditions.add("BILLING_CONTACT_IS_NULL");

    if(jobContract.getBankCd() == null) conditions.add("REMIT_TO_IS_NULL");

    if(jobContract.getPymntType() == null) conditions.add("PAYMENT_TYPE_IS_NULL");

    if(jobContract.getPymntTermsCd() == null) conditions.add("PAYMENT_TERM_IS_NULL");

    if(jobContract.getTransactionCurrencyCd() == null) conditions.add("CURRENCY_CODE_IS_NULL");

    if(jobContract.getCustRefNum() == null) conditions.add("CUST_REF_NUM_IS_NULL");

    if(jobContract.getBankAcctKey() == null) conditions.add("BANK_ACCT_KEY_IS_NULL");

    if(jobContract.getJobOrder().getServiceLocationCode() == null) conditions.add("SERVICE_LOCATION_IS_NULL");

    if(!isCredit)
      if(jobContract.getPrebills().size() <= 0) conditions.add("NO_PREBILL");

    if(conditions.size() > 0)
    {
      throw new ServiceException(
        "INVOICE_GENERATION_PRE_REQUISITE_NOT_MEET",
        new Object[] { conditions.toString() }
      );
    }

    //in case of credit for migrated invoices from ps we may not have prebills
    if(jobContract.getPrebills()!=null && !jobContract.getPrebills().isEmpty()){
      List accountList = new ArrayList();
      List deptidList = new ArrayList();
      List productGroupList = new ArrayList();
      Iterator it = jobContract.getPrebills().iterator();
      while(it.hasNext())
      {
        Prebill prebill = (Prebill)it.next();
        String account = prebill.getAccount();
        if(account == null) conditions.add("ACCOUNT_CODE_IS_NULL");
        else accountList.add(account);

        String deptid = prebill.getDeptid();
        if(deptid == null) conditions.add("DEPARTMENT_ID_IS_NULL");
        else deptidList.add(deptid);

        String productGroup = prebill.getProductGroup();
        if(productGroup == null)
        {
          conditions.add("PRODUCT_GROUP_IS_NULL");
        }
        else productGroupList.add(productGroup);

        if(conditions.size() > 0) break;
      }

      if(conditions.size() > 0)
      {
        throw new ServiceException(
          "INVOICE_GENERATION_PRE_REQUISITE_NOT_MEET",
          new Object[] { conditions.toString() }
        );
      }

      checkAccountCodes(new HashSet(accountList));
      checkDeptids(new HashSet(deptidList));
      checkProductGroups(new HashSet(productGroupList));
    }
   }

  private void checkAccountCodes(Set set)
  {
    if((set == null) || (set.size() <= 0)) return;

    StringBuffer sb = new StringBuffer();
    sb.append("select a.account from GLAccount a where a.account in (");
    int count = 0;
    Iterator it = set.iterator();
    while(it.hasNext())
    {
      if(count > 0) sb.append(",?");
      else sb.append("?");

      it.next();
      count ++;
    }
    sb.append(")");

    List list = getDao().find(
      sb.toString(),
      (Object[])set.toArray(new Object[0])
    );

    Set newSet = new HashSet(list);
    if(newSet.size() != set.size())
    {
      set.removeAll(newSet);
      throw new ServiceException("INVALID_ACCOUNT_CODE", new Object[] {set.toString()});
    }
  }

  private void checkDeptids(Set set)
  {
    if((set == null) || (set.size() <= 0)) return;

    StringBuffer sb = new StringBuffer();
    sb.append("select d.glDepartmentId.deptId from GLDepartment d where d.glDepartmentId.deptId in (");
    int count = 0;
    Iterator it = set.iterator();
    while(it.hasNext())
    {
      if(count > 0) sb.append(",?");
      else sb.append("?");

      it.next();
      count ++;
    }
    sb.append(")");

    List list = getDao().find(
      sb.toString(),
      (Object[])set.toArray(new Object[0])
    );

    Set newSet = new HashSet(list);
    if(newSet.size() != set.size())
    {
      set.removeAll(newSet);
      throw new ServiceException("INVALID_DEPARTMENT_ID", new Object[] {set.toString()});
    }
  }

  private void checkProductGroups(Set set)
  {
    if((set == null) || (set.size() <= 0)) return;

    StringBuffer sb = new StringBuffer();
    sb.append("select p.glProductGroupId.product from GLProductGroup p where p.glProductGroupId.product in (");
    int count = 0;
    Iterator it = set.iterator();
    while(it.hasNext())
    {
      if(count > 0) sb.append(",?");
      else sb.append("?");

      it.next();
      count ++;
    }
    sb.append(")");

    List list = getDao().find(
      sb.toString(),
      (Object[])set.toArray(new Object[0])
    );

    Set newSet = new HashSet(list);
    if(newSet.size() != set.size())
    {
      set.removeAll(newSet);
      throw new ServiceException("INVALID_PRODUCT_GROUP", new Object[] {set.toString()});
    }
  }

  public void saveJobContract(JobContract jobContract)
  {
    getDao().save(jobContract);
  }

  /**
   * Name :getJobContractByJobContractInvoiceId
   * Date :May 8, 2008
   * Purpose : Returns JobContract for the given JobContractInvoice
   * @param jobContractInvoiceId
   * @return
   */
  public JobContract getJobContractByJobContractInvoiceId(Long jobContractInvoiceId)
  {
    JobContract jc=null;

    List list =  getDao().find(
      "select jci.jobContract from JobContractInvoice jci where jci.id=?",
      new Object[] { jobContractInvoiceId }
    );

    if(!list.isEmpty() && list.size() > 0)
    {
      jc = (JobContract)list.get(0);
    }

    return jc;
  }

  public JobContractInvoice getLastJobContractInvoice(Long jobContractId)
  {
    List jcis = getDao().find(
      "from JobContractInvoice jci where jci.creditInd = ? and jci.jobContract.id = ? order by jci.generatedDateTime desc",
      new Object[] { Constants.CREDIT_INDICATOR_I, jobContractId }
    );

    if(jcis.size() > 0) return (JobContractInvoice)jcis.get(0);

    return null;
  }


  public String getLastInvoiceCreditInd(Long jobContractId)
  {
    List creditInds = getDao().find(
      "select creditInd from JobContractInvoice jci where jci.jobContract.id = ? order by jci.generatedDateTime desc",
      new Object[] { jobContractId }
    );

    if(creditInds!=null && !creditInds.isEmpty()) return (String)creditInds.get(0);

    return null;
  }

  public List getPrebillSplitsByUserName(String userName)
  {

      List list =  getDao().find(
        "select pbs from PrebillSplit pbs where pbs.updatedByUserName=?",
        new Object[] { userName }
      );

      if(!list.isEmpty() && list.size() > 0)
      {
        return list;
      }

      return null;
  }

  public void savePrebillSplit(PrebillSplit prebillSplit)
  {
    getDao().save(prebillSplit);
  }

  public void savePrebill(Prebill prebill)
  {
    getDao().save(prebill);
  }

  public List getJobContractInvoicesByUserName(String name)
  {
      List list =  getDao().find(
            "select jci from JobContractInvoice jci where jci.creditReasonUserName=?",
            new Object[] { name }
          );

          if(!list.isEmpty() && list.size() > 0)
          {
            return list;
          }

          return null;
  }

  public void updateJobContractInvoicePdfGeneratedFlag(Long jobContractInvoiceId, boolean pdfGeneratedFlag, String pdfName)
  {
    if(jobContractInvoiceId == null) return;
    getDao().bulkUpdate(
      "update JobContractInvoice set pdfGeneratedFlag = ?, invoiceFileName=? where id = ?",
      new Object[] { pdfGeneratedFlag, pdfName, jobContractInvoiceId }
    );
  }

  public List<JobContractInvoice> getJobContractInvoicesByReceivedBy(String userName)
  {
      List<JobContractInvoice> list =  getDao().find(
            "select jci from JobContractInvoice jci where jci.receivedByUserName=?",
            new Object[] { userName }
          );

          if(!list.isEmpty() && list.size() > 0)
          {
            return list;
          }

          return null;
  }

  /**
 * Name :convertConToReg
 * Date :Jul 28, 2008
 * Purpose : Converts a consolidated invoice to regular
 * step 1 : updates jobContractInvoice columns
 * step 2: Updates jobContract Columns
 * create the invoice pdf as a regular invoice
 * The condition was already met in vew: billstatus!='INV' and bill_type=='CON' in jobcontractinvoice
 * @param jobContractId
 * @param jobContractInvoiceId
 * @param dirName
 * @return
 * @throws Exception
 */
public boolean convertConToReg(long jobContractId, long jobContractInvoiceId, String dirName, String jasperDirName) throws Exception{
   if(jobContractId<=0 || jobContractInvoiceId<=0 )
     return false;
   getDao().bulkUpdate(
          "update JobContractInvoice set invoiceType='REG', consolInvoiceNo=null, consolBuName = null , billType='C2R', billStatus ='INV', sentToFinFlag='NEW'  where id = ?",
          new Object[] { jobContractInvoiceId }
        );

   getDao().bulkUpdate(
          "update JobContract set invoiceType='REG', consolInvoiceNo=null where id = ?",
          new Object[] { jobContractId }
        );
   generateInvoicePDF(jobContractInvoiceId, dirName, jasperDirName, false);
   return true;
  }

public List getInvoiceLinesByJobContractInvoiceId(long jobContractInvoiceId)
    {
  System.out.println("in getInvoiceLinesByJobContractInvoiceId id is"+jobContractInvoiceId);
      return getDao().find(
        "select distinct i from InvoiceLine i left join fetch i.invoiceLineSplits "
        + "where i.jobContractInvoice.id = ? order by i.vesselSortNum, i.lotSortNum, i.chargeSortNum",
        new Object[] { jobContractInvoiceId }
      );
    }

public boolean hasPrebills(long jobContractId){
  Number count = 0;
  List counts = getDao().find(
            "select count(p.id) from Prebill p where p.jobContract.id = ?" ,
            new Object[] { jobContractId }
          );
    if(counts.size() > 0)
      count = (Number)counts.get(0);
  if(count.intValue()>0) return true;
  return false;
}

public void updateJobContractStatus(long jobContractId, String status)
{
  if(jobContractId == 0) return;
  getDao().bulkUpdate(
    "update JobContract set jobContractStatus = ? where id = ?",
    new Object[] { status, jobContractId }
  );
}

private InvoiceFile getInvoiceFile(String pdfName, long jobContractInvoiceId, String invoiceFileType)
{
    List<InvoiceFile> list = getDao().find("from InvoiceFile i where i.invoiceFileName = ? " +
        " and i.jobContractInvoice.id = ? and i.invoiceFileType.invoiceFileType=? ",
          new Object[] { pdfName, jobContractInvoiceId, invoiceFileType}
        );
    if(!list.isEmpty())
        return (InvoiceFile)list.get(0);
    return null;
}


/**
 * Name :getDecimalDigitsByCurrency
 * Date :Nov 13, 2008
 * Purpose :Some currency like KRW need to round with scale=0. The rest: scale=2
 * @param currency
 * @return
 */
public Integer getDecimalDigitsByCurrency(String currency){
    List list = getDao().find(
        "select decimalDigits from Currency c where c.currencyCd = ?",
        new Object [] { currency }
      );
    if(list.size() > 0) return (Integer)list.get(0);
    return null;
}

public void setInvoiceFileCountable(long id, boolean countable){
  if(id <= 0) return;

    getDao().bulkUpdate(
      "update InvoiceFile set countable = ? where id = ?",
      new Object[] { countable, id }
    );
}

/**
 * Name :getBusinessUnitVatLocRegistration
 * Date :Nov 18, 2008
 * Purpose : based on the buName and country code (coming from business unit or service location country (if that bu has more than 1 country registration)) and state (may be or my not be there) pulls the registration id
 * If nothing found it returns the default one for that bu
 * @param buName
 * @param countryCode
 * @param stateCode
 * @return
 */
public String getBusVatLocRegistrationId( BusinessUnit businessUnit, String serviceLocationCountry, String stateCode)
{
  if(businessUnit == null)
    return null;
  List list = new ArrayList();
  if(stateCode != null && stateCode.trim().length()>0){
    list = getDao().find(
        "select v.busUnitVatLocId.vatRegistrationId from BusUnitVatLoc v where v.busUnitVatLocId.buName = ? and v.busUnitVatLocId.countryCode = ? and v.state = ? ",
        new Object[] { businessUnit.getName(), businessUnit.getCountryCode(), stateCode}
      );
  }else{
    list = getDao().find(
        "select v.busUnitVatLocId.vatRegistrationId from BusUnitVatLoc v where v.busUnitVatLocId.buName = ?  and (v.state is null or v.state = '' or v.state = ' ' ) ",
        new Object[] { businessUnit.getName()}
      );
    //if for that businessUnit more than 1 country exist , We need to look for the service location country
    if(list.size()>1 && serviceLocationCountry!=null){
      list = getDao().find(
          "select v.busUnitVatLocId.vatRegistrationId from BusUnitVatLoc v where v.busUnitVatLocId.buName = ? and v.busUnitVatLocId.countryCode = ?   and (v.state is null or v.state = '' or v.state = ' ' ) ",
          new Object[] { businessUnit.getName(), serviceLocationCountry}
        );
    }
  }

  //if service location country has no registration id or there is no registration for that state, we need to get the default one.
  if(list==null || list.isEmpty() || list.size()>1){
    list = getDao().find(
        "select v.busUnitVatLocId.vatRegistrationId from BusUnitVatLoc v where v.busUnitVatLocId.buName = ? and defaultLocInd='1'  ) ",
        new Object[] { businessUnit.getName()}
      );
  }
   if(list.size() > 0) return (String)list.get(0);

   return null;
}

/**
 * Name :calculateTotalAmounts
 * Date :Nov 25, 2008
 * Purpose :
 * @param invoiceLineSet
 * @return
 */
//START: 125191 - Adding new two fields to store VAT and Tax total
/* 
public JobContractInvoice calculateTotalAmounts(JobContractInvoice jobContractInvoice, String buName){
  if(jobContractInvoice == null)
    return null;
  Set<InvoiceLine> invoiceLineSet = jobContractInvoice.getInvoiceLines();
  double totalAmtPreTax = 0;
  double totalTaxAndVat = 0;
  for(InvoiceLine invoiceLine : invoiceLineSet){
    totalAmtPreTax += invoiceLine.getNetPrice();
    if(invoiceLine.getTaxAmt() != null)
      totalTaxAndVat += invoiceLine.getTaxAmt();
    if(invoiceLine.getVatAmt() != null)
      totalTaxAndVat += invoiceLine.getVatAmt();
  }
  //for KOR01 vat total should be rounded with scale=0 itrack 75244
  if(buName.equals("KOR01") && jobContractInvoice.getTransactionCurrencyCd().equals("KRW"))
    totalTaxAndVat = NumberUtil.roundDown(totalTaxAndVat, 0);
  else
    totalTaxAndVat = NumberUtil.roundHalfUp(totalTaxAndVat, Constants.TAX_VAT_SCALE);
  Integer roundingDigits = getDecimalDigitsByCurrency(jobContractInvoice.getTransactionCurrencyCd());
    if(roundingDigits == null)
      roundingDigits = 2;
    totalAmtPreTax = NumberUtil.roundHalfUp(totalAmtPreTax, roundingDigits);
  jobContractInvoice.setInvAmtPreTax(totalAmtPreTax);
  jobContractInvoice.setInvAmtPostTax(NumberUtil.roundHalfUp(totalAmtPreTax + totalTaxAndVat, roundingDigits));
  return jobContractInvoice;
}
*/
public JobContractInvoice calculateTotalAmounts(JobContractInvoice jobContractInvoice, String buName){
  if(jobContractInvoice == null)
    return null;
  Set<InvoiceLine> invoiceLineSet = jobContractInvoice.getInvoiceLines();
  double totalAmtPreTax = 0;
  double totalTax = 0;
  double totalVat = 0; 
  for(InvoiceLine invoiceLine : invoiceLineSet){
    totalAmtPreTax += invoiceLine.getNetPrice();
    if(invoiceLine.getTaxAmt() != null)
    	totalTax += invoiceLine.getTaxAmt();
    if(invoiceLine.getVatAmt() != null)
    	totalVat += invoiceLine.getVatAmt();
  }
  
  Integer roundingDigits = getDecimalDigitsByCurrency(jobContractInvoice.getTransactionCurrencyCd());
  if(roundingDigits == null)
    roundingDigits = 2;
  
  //for KOR01 vat total should be rounded with scale=0 itrack 75244
  if(buName.equals("KOR01") && jobContractInvoice.getTransactionCurrencyCd().equals("KRW")){
    totalTax = NumberUtil.roundDown(totalTax, 0);
    totalVat = NumberUtil.roundDown(totalVat, 0);
  }
  else{	
	totalTax = NumberUtil.roundHalfUp(totalTax, Constants.TAX_VAT_SCALE);
    totalVat = NumberUtil.roundHalfUp(totalVat, Constants.TAX_VAT_SCALE);
  }
  
  totalTax = NumberUtil.roundHalfUp(totalTax, roundingDigits);
  totalVat = NumberUtil.roundHalfUp(totalVat, roundingDigits);
  
  totalAmtPreTax = NumberUtil.roundHalfUp(totalAmtPreTax, roundingDigits);
  
  jobContractInvoice.setInvAmtPreTax(totalAmtPreTax);
  jobContractInvoice.setVatAmt(totalVat);
  jobContractInvoice.setTaxAmt(totalTax);
  jobContractInvoice.setInvAmtPostTax(NumberUtil.roundHalfUp(totalAmtPreTax , roundingDigits) + totalTax + totalVat);
  return jobContractInvoice;
}
//END: 125191 - Adding new two fields to store VAT and Tax total
/**
 * Name :checkInvoiceAndPdfGenerated
 * Date :Jul 21, 2009
 * Purpose : to check invoice and PDF generated successfully 
 * @param id
 */
public void checkInvoiceAndPdfGenerated(long id){
	List<JobContractInvoice> jobContractInv = getDao().find("from JobContractInvoice jci left join fetch jci.jobContract jc where jc.id = ? " ,
	          new Object[] {id});
    if(jobContractInv != null && jobContractInv.size()>0){
    	for(JobContractInvoice jobContractInvoice:jobContractInv){
	    	if(jobContractInvoice.getInvoiceFileName() == null || jobContractInvoice.getInvoiceFileName().trim().isEmpty()){
	    		updateJobContractInvoicePdfGeneratedFlag(jobContractInvoice.getId(), false, jobContractInvoice.getInvoiceFileName());
	    	} 
    	}
    }
}
/**
 * Name :updateInvoicePdfGeneratedFlag
 * Date :Jul 21, 2009
 * Purpose : to update pdfGeneratedFlag values for failure invoices
 * @param jobContractInvoiceId,pdfGeneratedFlag
 * @return
 */
public void updateInvoicePdfGeneratedFlag(Long jobContractInvoiceId, boolean pdfGeneratedFlag)
{
  if(jobContractInvoiceId == null) return;
  getDao().bulkUpdate(
    "update JobContractInvoice set pdfGeneratedFlag = ? where id = ?",
    new Object[] { pdfGeneratedFlag,jobContractInvoiceId }
  );
}
}

