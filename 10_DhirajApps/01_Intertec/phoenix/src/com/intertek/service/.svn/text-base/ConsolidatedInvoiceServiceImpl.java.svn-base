package com.intertek.service;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.intertek.domain.ConsolidatedInvoiceSearch;
import com.intertek.domain.InvoiceSearch;
import com.intertek.entity.ConsolInvoiceFile;
import com.intertek.entity.ConsolidatedInvoice;
import com.intertek.entity.ConsolidatedInvoiceId;
import com.intertek.entity.InvoiceFileType;
import com.intertek.entity.JobContract;
import com.intertek.entity.JobContractInvoice;
import com.intertek.entity.OpenPeriod;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;
import com.intertek.report.JasperRunner;
import com.intertek.util.NumberUtil;
import com.intertek.util.PDFUtil;
import com.intertek.util.SecurityUtil;

public class ConsolidatedInvoiceServiceImpl extends BaseService implements ConsolidatedInvoiceService
{
  public ConsolidatedInvoice saveConsolidatedInvoice(ConsolidatedInvoice consolidatedInvoice)
  {
     return getDao().save(consolidatedInvoice);
  }

  public InvoiceSearch searchInvoice(InvoiceSearch search,String sortFlag,String sortOrderFlag)
  {

      if(search == null) return null;

      StringBuffer sb = new StringBuffer(" where 1=1  ");
      List params = new ArrayList();

      String custCode = search.getCustCode();
      String currency = search.getCurrency();
      String buName = search.getBuName();
      String billStatus = search.getBillStatus();
      String invoiceType = search.getInvoiceType();
      Date fromDate = search.getFromDate();
      Date toDate = search.getToDate();
      String consolInvoiceNo = search.getInvoiceNo();
      boolean generated = search.isGenerated();
      // Vat Province code
      String vatProvince = search.getInvoiceVatProvince();
      if(vatProvince != null && !vatProvince.trim().equals(""))
      {
        sb.append(" and upper(jci.vatProvince) = '"+vatProvince+"' ");
        search.setInvoiceVatProvince("");
      }
      //End
      if((custCode!=null)&&!"".equals(custCode.trim()))
      {
        //sb.append("where upper(jc.custCode) = ? ");
        //params.add(custCode);
        sb.append(" and upper(jc.custCode) = '"+custCode+"' ");
      }

      if(currency !=null && !currency.trim().equals(""))
      {
          //sb.append(" jc.transactionCurrencyCd = ?");
          //params.add(currency);
          sb.append(" and jci.transactionCurrencyCd = '"+currency+"' ");
      }

      // buName is set only in edit consol. In create consol buname is not passed.
      //At edit time, if invoice is not generated yet, we want to bring in the job contract invoices with consol bu name or null bu name
      //But if it is generated just the ones with consolBuname
      if((buName != null) && !"".equals(buName.trim()))
      {
        if(generated && consolInvoiceNo!=null){
          sb.append(" and jci.consolBuName = '"+buName+"' ");
        }else
          sb.append(" and (jci.consolBuName = '"+buName+"' or jci.consolBuName is null)");
      }

      if((billStatus != null) && !"".equals(billStatus.trim()))
      {

        //sb.append("jc.billStatus = ?");
          //params.add(billStatus);
          sb.append(" and jci.billStatus = '"+billStatus+"' ");
      }
      if((invoiceType != null) && !"".equals(invoiceType.trim()))
      {

        //sb.append("jc.invoiceType = ?");
          //params.add(invoiceType);
          sb.append(" and jci.invoiceType = '"+invoiceType+"' ");
      }
      if((fromDate != null) && !"".equals(fromDate.toString().trim()) && (toDate != null) && !"".equals(toDate.toString().trim()))
      {

        Calendar tcal = Calendar.getInstance();
        tcal.setTime(toDate);
        tcal.add(Calendar.DATE, 1);
        toDate = tcal.getTime();

        //sb.append("jc.invoiceType = ?");
          //params.add(invoiceType);
          sb.append(" and jci.invoiceDate >= ? and jci.invoiceDate < ? " );
          params.add(fromDate);
          params.add(toDate);
      }
      else
      {
        if((fromDate != null) && !"".equals(fromDate.toString().trim()))
        {
          //sb.append("jc.invoiceType = ?");
            //params.add(invoiceType);
            sb.append(" and jci.invoiceDate >= ? " );
            params.add(fromDate);
        }
        else if((toDate != null) && !"".equals(toDate.toString().trim()))
        {

          //sb.append("jc.invoiceType = ?");
            //params.add(invoiceType);
            sb.append(" and jci.invoiceDate <= ? " );
            params.add(toDate);
        }
      }

      //if(generated && consolInvoiceNo!=null && !consolInvoiceNo.isEmpty()){
        if(generated && consolInvoiceNo!=null){
          sb.append(" and jci.billType = '"+Constants.INV_TYPE_CON+ "' and jci.billStatus = '"+ Constants.INV+"' and  jci.consolInvoiceNo = ?");
          params.add(consolInvoiceNo);
      }else
          sb.append(" and jci.billType = '"+Constants.INV_TYPE_CON+ "' and jci.billStatus = '"+ Constants.BILL_STATUS_RDY+"'");

             //restriction to not return job contracts of cancelled jobs in job contract search : iTrak: 40677
        sb.append(" and jc.jobOrder.jobStatus not in ( '"+  Constants.CANCELLED_STATUS+ "')  " );


      Pagination pagination = search.getPagination();
      List results = null;
      if(sortFlag == null || sortFlag.trim().equals("")){

        if(pagination != null)
        {
          if(pagination.getTotalRecord() > 0)
          {
            List counts = getDao().find(
            "select count(jci.id) from JobContractInvoice jci left join fetch jci.jobContract jc " + sb.toString(),
            params.toArray()
            );

            if(counts.size() > 0)
            {
              Number count = (Number)counts.get(0);
              pagination.setTotalRecord(count.intValue());
            }
          }

          results = getDao().find(
          "select distinct jci from JobContractInvoice jci left join fetch jci.jobContract jc left join fetch jc.jobOrder job left join fetch job.businessUnit " + sb.toString()+" order by jci.id",
          params.toArray(),
          pagination
          );

          pagination.calculatePages();
        }
        else
        {
          results = getDao().find(
            "select distinct jci from JobContractInvoice jci left join fetch jci.jobContract jc left join fetch jc.jobOrder job left join fetch job.businessUnit " + sb.toString()+" order by jci.id",
            params.toArray()
          );
        }
      } else {
        // Attach/Detach tab header sort starts here
        if(pagination != null)
        {
          if(pagination.getTotalRecord() > 0)
          {
            List counts = getDao().find(
            "select count(jci.id) from JobContractInvoice jci left join fetch jci.jobContract jc " + sb.toString(),
            params.toArray()
            );

            if(counts.size() > 0)
            {
              Number count = (Number)counts.get(0);
              pagination.setTotalRecord(count.intValue());
            }
          }
          String orderBy = "";
          if(sortFlag != null && !sortFlag.trim().equals("")){
            if(sortOrderFlag != null && !sortOrderFlag.trim().equals(""))
              orderBy = " order by "+ sortFlag +" "+sortOrderFlag;
          }
          results = getDao().find(
          "select distinct jci from JobContractInvoice jci left join fetch jci.jobContract jc left join fetch jc.jobOrder job left join fetch job.businessUnit " + sb.toString()+orderBy,
          params.toArray(),
          pagination
          );

          pagination.calculatePages();
        } else {
          String orderBy = "";
          if(sortFlag != null && !sortFlag.trim().equals("")){
            if(sortOrderFlag != null && !sortOrderFlag.trim().equals(""))
              orderBy = " order by "+ sortFlag +" "+sortOrderFlag;
          }
          results = getDao().find(
              "select distinct jci from JobContractInvoice jci left join fetch jci.jobContract jc left join fetch jc.jobOrder job left join fetch job.businessUnit " + sb.toString()+orderBy,
              params.toArray()
            );
        }//End
      }
      search.setResults(results);
      search.setPagination(pagination);
        return search;
    }

  public void searchConsolidatedInvoice(ConsolidatedInvoiceSearch search,String sortFlag)
  {
      if(search == null) return;
      StringBuffer sb = new StringBuffer();
      List params = new ArrayList();
      boolean hasWhere = false;
      String customerId= search.getCustomerId().getValue();
      int customerVal=search.getCustomerId().getOperator();

      if((customerId!=null)&&(customerVal==Constants.CONTAINS))
      {
          String cId='%'+String.valueOf(customerId)+'%';
          sb.append("where upper(c.custCode) like ?");
          params.add(cId.toUpperCase());
          hasWhere = true;
      }
       else if((customerId!=null)&&(customerVal==Constants.BEGINS_WITH))
      {
        String cId=String.valueOf(customerId)+'%';
        sb.append("where upper(c.custCode) like ?");
        params.add(cId.toUpperCase());
        hasWhere = true;
      }
      else if((customerId!=null)&&(customerVal==Constants.EQUALS))
        {
        sb.append("where upper(c.custCode) = ?");
          params.add(customerId.toUpperCase());
          hasWhere = true;
        }
       else if((customerId!=null)&&(customerVal==Constants.NOT_EQUALS))
      {
          sb.append("where upper(c.custCode) !=?");
          params.add(customerId.toUpperCase());
          hasWhere = true;
      }

        String customerName = search.getCustomerName().getValue();
       int customerValue=search.getCustomerName().getOperator();
       if((customerName != null) && !"".equals(customerName.trim()))
        {
          if(hasWhere)
            sb.append(" and ");
          else
           {
            hasWhere = true;
            sb.append(" where ");
           }
          if((customerName!=null)&&(customerValue==Constants.CONTAINS))
            {
             String cName='%'+customerName.toUpperCase()+'%';
             sb.append(" upper(c.customer.name) like ? ");
             params.add(cName);
            }
          if((customerName!=null)&&(customerValue==Constants.BEGINS_WITH))
              {
               String cName=customerName.toUpperCase()+'%';
               sb.append(" upper(c.customer.name) like ? ");
               params.add(cName);
              }
            if((customerName!=null)&&(customerValue==Constants.EQUALS))
            {
              String cName=customerName.toUpperCase();
              sb.append(" upper(c.customer.name)  = ? ");
              params.add(cName);
            }
           if((customerName!=null)&&(customerValue==Constants.NOT_EQUALS))
           {
             String cName=customerName.toUpperCase();
             sb.append(" upper(c.customer.name) !=? ");
             params.add(cName);
           }
        }
       String buName = search.getBuName().getValue();
       int buValue=search.getBuName().getOperator();
       if((buName != null) && !"".equals(buName.trim()))
        {
          if(hasWhere)
            sb.append(" and ");
          else
           {
            hasWhere = true;
            sb.append(" where ");
           }
          if((buName!=null)&&(buValue==Constants.CONTAINS))
            {
             String bName='%'+buName.toUpperCase()+'%';
             sb.append(" upper(c.consolidatedInvoiceId.buName) like ? ");
             params.add(bName);
            }
          if((buName!=null)&&(buValue==Constants.BEGINS_WITH))
              {
               String bName=buName.toUpperCase()+'%';
               sb.append(" upper(c.consolidatedInvoiceId.buName) like ? ");
               params.add(bName);
              }
            if((buName!=null)&&(buValue==Constants.EQUALS))
            {
              String bName=buName.toUpperCase();
              sb.append(" upper(c.consolidatedInvoiceId.buName)   = ? ");
              params.add(bName);
            }
           if((buName!=null)&&(buValue==Constants.NOT_EQUALS))
           {
             String bName=buName.toUpperCase();
             sb.append(" upper(c.consolidatedInvoiceId.buName)  !=? ");
             params.add(bName);
           }
        }

       String invoiceNo = search.getInvoiceNumber().getValue();
       int invoiceValue=search.getInvoiceNumber().getOperator();
       if((invoiceNo != null) && !"".equals(invoiceNo.trim()))
        {
          if(hasWhere)
            sb.append(" and ");
          else
           {
            hasWhere = true;
            sb.append(" where ");
           }
          if((invoiceNo!=null)&&(invoiceValue==Constants.CONTAINS))
            {
             String iName='%'+invoiceNo.toUpperCase()+'%';
             sb.append(" upper(c.consolidatedInvoiceId.consolInvoiceNo) like ? ");
             params.add(iName);
            }
          if((invoiceNo!=null)&&(invoiceValue==Constants.BEGINS_WITH))
              {
               String iName=invoiceNo.toUpperCase()+'%';
               sb.append(" upper(c.consolidatedInvoiceId.consolInvoiceNo) like ? ");
               params.add(iName);
              }
            if((invoiceNo!=null)&&(invoiceValue==Constants.EQUALS))
            {
              String iName=invoiceNo.toUpperCase();
              sb.append(" upper(c.consolidatedInvoiceId.consolInvoiceNo)   = ? ");
              params.add(iName);
            }
           if((invoiceNo!=null)&&(invoiceValue==Constants.NOT_EQUALS))
           {
             String iName=invoiceNo.toUpperCase();
             sb.append(" upper(c.consolidatedInvoiceId.consolInvoiceNo)  !=? ");
             params.add(iName);
           }
        }


      Pagination pagination = search.getPagination();
        List results = null;

       if(pagination != null)
        {
          if(pagination.getTotalRecord() > 0)
          {
            List counts = getDao().find(
              "select count(c.consolidatedInvoiceId.consolInvoiceNo) from ConsolidatedInvoice c  " + sb.toString(),
              params.toArray()
            );

            if(counts.size() > 0)
            {
              Number count = (Number)counts.get(0);
              pagination.setTotalRecord(count.intValue());
            }
            pagination.calculatePages();
          }
          // column header sort starts here
          if(sortFlag != null && sortFlag.equals(""))
            {
            results = getDao().find(
              "select distinct c from ConsolidatedInvoice c left join fetch c.customer cust left join fetch c.consolInvoiceFiles i left join fetch i.invoiceFileType " + sb.toString(),
              params.toArray(),
              pagination
            );
            }else
            {
              String orderByValue=" order by c."+sortFlag;
              results = getDao().find(
                    "select distinct c from ConsolidatedInvoice c left join fetch c.customer cust  left join fetch c.consolInvoiceFiles  i left join fetch i.invoiceFileType   " + sb.toString()+ orderByValue,
                    params.toArray(),
                    pagination
                  );
            }
        }
        else
        {
          results = getDao().find(
          //  "select distinct c from ConsolidatedInvoice c left join fetch c.contactCust ct left join fetch ct.customer cust " + sb.toString(),
                 "select distinct c from ConsolidatedInvoice c left join fetch c.customer cust left join fetch c.consolInvoiceFiles  i left join fetch i.invoiceFileType   " + sb.toString(),

            params.toArray()
          );
        }
        search.setResults(results);


  }
  public ConsolidatedInvoice getConsolidatedInvoiceByInvoiceNumberBuName(String conslInvNumber, String buName)
  {
      List conslInvs = getDao().find(
              "from ConsolidatedInvoice c  left join fetch c.customer left join fetch c.contact left join fetch c.custAddrSeq left join fetch c.jobContractInvoices left join fetch c.consolInvoiceFiles  i left join fetch i.invoiceFileType  where c.consolidatedInvoiceId.consolInvoiceNo = ? " +
              "and c.consolidatedInvoiceId.buName = ?",
              new Object[] { conslInvNumber , buName}
            );
            if(conslInvs.size() > 0) return (ConsolidatedInvoice)conslInvs.get(0);

            return null;
  }
public List getAllConsolidatedInvoices()
{
    List conslInvs = getDao().find(
          "from ConsolidatedInvoice c ",
          null
        );
        return conslInvs;


}

/**
 * Name :findConsolidatedInvoiceByNoBuCust
 * Date :May 7, 2008
 * Purpose : Finding Consolidated invioce by invoice number custcode and buName
 * @param invoiceNo
 * @param buName
 * @param custCode
 * @return
 */
public ConsolidatedInvoice findConsolidatedInvoiceByNoBuCust(String invoiceNo, String custCode)
{
      List conslInvs = getDao().find(
              "from ConsolidatedInvoice c  left join fetch c.customer left join fetch c.contact left join fetch c.custAddrSeq left join fetch c.jobContractInvoices left join fetch c.consolInvoiceFiles  i left join fetch i.invoiceFileType " +
              " where c.consolidatedInvoiceId.consolInvoiceNo = ? and " +
              "  c.custCode=? ",
              new Object[] { invoiceNo, custCode }
            );
            if(conslInvs.size() > 0) return (ConsolidatedInvoice)conslInvs.get(0);

            return null;
}

public void generateConsolInvoice(String consolInvoiceNo, String buName){
  ConsolidatedInvoice consolidatedInvoice = this.getConsolidatedInvoiceByInvoiceNumberBuName(consolInvoiceNo, buName);
  List<JobContractInvoice> jobContractInvoiceAttachedList = getJobContractInvoiceByConsol(consolidatedInvoice.getConsolidatedInvoiceId().getConsolInvoiceNo(), consolidatedInvoice.getConsolidatedInvoiceId().getBuName(), "");
  PrebillService prebillService = (PrebillService)ServiceLocator.getInstance().getBean("prebillService");
  JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
  if(!jobContractInvoiceAttachedList.isEmpty() && jobContractInvoiceAttachedList.size()>0){
    consolidatedInvoice.setGeneratedBy(SecurityUtil.getUser());
    consolidatedInvoice.setGeneratedByUserName(SecurityUtil.getUser().getLoginName());
    consolidatedInvoice.setGenerateTime(new Date());
    consolidatedInvoice.setBillStatus(Constants.INV);
    consolidatedInvoice.setSentToFinFlag(Constants.NEW);
    double totalAmtPreTax = 0;
    double totalAmtPostTax = 0;
    for(JobContractInvoice jobContractInvoice : jobContractInvoiceAttachedList){
        OpenPeriod openPeriod = jobService.findOpenPeriodByBuName(jobContractInvoice.getJobContract().getJobOrder().getBuName());
        Date firstDate = openPeriod.getOpenFromDate();
        if(jobContractInvoice.getAccountingDate()==null || (jobContractInvoice.getAccountingDate()!=null && jobContractInvoice.getAccountingDate().before(firstDate)))
          jobContractInvoice.setAccountingDate(firstDate);
        //if it is not a rebill, we change the invoice date. Otherwise, we leave it as was.
        if(jobContractInvoice.getInvoiceDate()==null || (jobContractInvoice.getInvoiceDate()!=null && jobContractInvoice.getInvoiceDate().before(firstDate) && (jobContractInvoice.getInvoiceToAdjust()==null || jobContractInvoice.getInvoiceToAdjust().trim().length()==0)))
          jobContractInvoice.setInvoiceDate(firstDate);
          //jobContractInvoice.setInvoiceDate(consolidatedInvoice.getInvoiceDt());
          jobContractInvoice.setBillStatus(Constants.INV);
          //jobContractInvoice.setConsolidatedInvoice(consolidatedInvoice);
          prebillService.saveJobContractInvoice(jobContractInvoice);
          JobContract jobContract = prebillService.getJobContractByJobContractInvoiceId(jobContractInvoice.getId());
          jobContract.setConsolInvoiceNo(consolidatedInvoice.getConsolidatedInvoiceId().getConsolInvoiceNo());
          prebillService.saveJobContract(jobContract);
          totalAmtPreTax += jobContractInvoice.getInvAmtPreTax();
          totalAmtPostTax += jobContractInvoice.getInvAmtPostTax();
      }

    Integer roundingDigits = prebillService.getDecimalDigitsByCurrency(consolidatedInvoice.getCurrencyCd());
      if(roundingDigits == null)
        roundingDigits = 2;
    consolidatedInvoice.setInvoiceAmtPreTax(NumberUtil.roundHalfUp(totalAmtPreTax, roundingDigits));
    consolidatedInvoice.setInvoiceAmtPostTax(NumberUtil.roundHalfUp(totalAmtPostTax, roundingDigits));
    consolidatedInvoice= getDao().merge(consolidatedInvoice);
    }


}

/**
 * Name :generateInvoicePDF
 * Date :May 6, 2008
 * Purpose :
 * @param dirName
 * @param ConsolidatedInvoice
 */
public void generateInvoicePDF(String dirName, String jasperDirName, String consoleInvoiceNo, String buName, String custCode, int locationNumber)
{
  if(consoleInvoiceNo == null || buName == null)
    return;
  List<JobContractInvoice> jobContractinvoices = getJobContractInvoiceByConsol(consoleInvoiceNo, buName, "jc.jobNumber asc, jci.invoice asc");
  if(jobContractinvoices.isEmpty())
    return;

  PrebillService prebillService = (PrebillService)ServiceLocator.getInstance().getBean("prebillService");

    javax.sql.DataSource dataSource = (javax.sql.DataSource)ServiceLocator.getInstance().getBean("entityDataSource");
    java.sql.Connection connection = null;
    try
    {
      connection = dataSource.getConnection();

      //Generating individual invoices
      String fileNames[]=new String[jobContractinvoices.size()+1];
      int i=1;
      for(JobContractInvoice jobContractInvoice : jobContractinvoices){
         List<String> names=prebillService.generateInvoicePDF(jobContractInvoice.getId(), dirName, jasperDirName, true);
         fileNames[i++]=names.get(0);
      }

      //Generating consolidated invoice
    Map paramMap = new HashMap();

    System.out.println("BuName"+ buName);
      System.out.println("CustCode"+ custCode);
      System.out.println("ConsolInvoice"+ consoleInvoiceNo);

    paramMap.put("BuName", buName);
    paramMap.put("CustCode", custCode);
    paramMap.put("ConsolInvoice", consoleInvoiceNo);
      paramMap.put("dirName", jasperDirName);

      java.io.InputStream is2 = new FileInputStream(jasperDirName + "consol_summary.jasper");
      String reportName2 = "consol_summary";
      String consolPdfName =  consoleInvoiceNo + "_summary.pdf";
      String dateFolder = DateUtil.formatDate(new Date(), "yyyyMMdd");
      String consolFolderWithDate = dirName + "CONSOLS" + File.separator + dateFolder + File.separator;
      fileNames[0]="CONSOLS" + File.separator + dateFolder  +File.separator +  consolPdfName;
      File f=new File(consolFolderWithDate);
      if(!f.exists())
        f.mkdirs();

  if(JasperRunner.generateReport(reportName2, is2, paramMap, connection, consolFolderWithDate , consolPdfName)){
        OutputStream out=new FileOutputStream(consolFolderWithDate + consoleInvoiceNo + ".pdf");
        PDFUtil.concatPDFs(dirName, fileNames, out);
        updatePdfGeneratedFlag(consoleInvoiceNo, buName, true, dateFolder + File.separator + consoleInvoiceNo+".pdf");
      }

      //generating extra file types
      List<InvoiceFileType> types = prebillService.findInvoiceFileTypeByBuAndType(buName, "CON");
      for(InvoiceFileType invoiceFileType: types){
        String type = invoiceFileType.getInvoiceFileType();
        System.out.println("type "+type);
        boolean taxVatFlag = jobContractinvoices.get(0).getTaxVatFlag();
        String vatProvince=jobContractinvoices.get(0).getVatProvince();
        if(type.equals("KOR") && taxVatFlag){
          LangService langService = (LangService)ServiceLocator.getInstance().getBean("langService");
          if(langService.checkLangPrerequisits(buName, type, custCode, locationNumber)){
            is2 = new FileInputStream(jasperDirName + "consol_invoice_tax_kor.jasper");
                  reportName2 = "consol_invoice_tax_kor";
            consolPdfName  = consoleInvoiceNo + type + ".pdf";
              System.out.println("in generate korea pdfName:"+consolPdfName);

              ConsolInvoiceFile consolInvoiceFile = getConsolInvoiceFile(consoleInvoiceNo, buName, consolPdfName, type);
              if(consolInvoiceFile==null){
                consolInvoiceFile=saveInvoiceAndSerialNum(consolPdfName, true, new ConsolidatedInvoice(new ConsolidatedInvoiceId(consoleInvoiceNo, buName)), invoiceFileType, vatProvince, dateFolder);
              }
              String serialNumber=consolInvoiceFile.getSerialNumber();
              paramMap.put("serialNumber", serialNumber);
              JasperRunner.generateReport(/*reportName2, */is2, paramMap, connection, consolFolderWithDate, consolPdfName);
              /*
              if(JasperRunner.generateReport(is2, paramMap, connection, dirName + "CONSOLS\\", consolPdfName)){
                if(getConsolInvoiceFile(consoleInvoiceNo, buName, consolPdfName, type) == null){
                    ConsolInvoiceFile consolInvoiceFile = new ConsolInvoiceFile(consolPdfName, true, new ConsolidatedInvoice(new ConsolidatedInvoiceId(consoleInvoiceNo, buName)), invoiceFileType);
                    getDao().save(consolInvoiceFile);
                  }
              }
              */
          }
        }
      }
    }
    catch (Throwable e)
    {
      e.printStackTrace();
      throw new ServiceException(e.getMessage(), new Object[] {e.getMessage()}, e);
    }
    finally
    {
      if(connection != null)
      {
        try { connection.close(); } catch(Exception e) {}
      }
    }
}



private ConsolInvoiceFile saveInvoiceAndSerialNum(String invoiceFileName, Boolean countable, ConsolidatedInvoice consolidatedInvoice, InvoiceFileType invoiceFileType, String vatProvince, String dateFolder){
  ConsolInvoiceFile conInvoiceFile = new ConsolInvoiceFile();
  conInvoiceFile.setInvoiceFileName( dateFolder + File.separator + invoiceFileName);
  conInvoiceFile.setCountable(countable);
  conInvoiceFile.setConsolidatedInvoice(consolidatedInvoice);
  conInvoiceFile.setInvoiceFileType(invoiceFileType);

  conInvoiceFile=getDao().save(conInvoiceFile);
  String serNum=conInvoiceFile.getSerialNumber();
  if(serNum==null || serNum.trim().isEmpty()){
    String buName = consolidatedInvoice.getConsolidatedInvoiceId().getBuName();
    if(serNum==null){
        AutoNumberService autoNumberService = (AutoNumberService)ServiceLocator.getInstance().getBean("autoNumberService");
        String numberType="C";
        String type = invoiceFileType.getInvoiceFileType();
        if(type==null || type.equals(Constants.INV_TYPE_REG)){
          numberType="I";
        }
        serNum = autoNumberService.getSeqNumber(
            buName,
            vatProvince,
                numberType
        );
    }

    conInvoiceFile.setSerialNumber(serNum);
  }
  return getDao().save(conInvoiceFile);
}

  private void updatePdfGeneratedFlag(String consoleInvoiceNo, String buName, boolean pdfGeneratedFlag, String consolPdfName)
  {
    if(consoleInvoiceNo == null || buName ==null ) return;
    getDao().bulkUpdate(
      "update ConsolidatedInvoice set pdfGeneratedFlag = ?, invoiceFileName=? where consolidatedInvoiceId.consolInvoiceNo = ? and consolidatedInvoiceId.buName =?",
      new Object[] { pdfGeneratedFlag, consolPdfName, consoleInvoiceNo, buName}
    );
  }

  /**
   * Name :getJobContractInvoices
   * Date :May 7, 2008
   * Purpose : Returns all JobContractInvoices related to a Consolidated Invoice
   * @param consolidatedInvoice
   * @return
   */
  public List getJobContractInvoiceByConsol(String consolInvoiceNo, String consolBuName, String orderBy)
  {
    String myOrderBy=orderBy;
    if(myOrderBy==null || orderBy.trim().isEmpty()){
      myOrderBy="";
    }
    else{
      myOrderBy="order by "+ myOrderBy;
    }
      return getDao().find("from JobContractInvoice jci left join fetch jci.jobContract jc left join fetch jc.jobOrder where jci.consolInvoiceNo = ? and jci.consolBuName = ? "+myOrderBy,
        new Object[] { consolInvoiceNo, consolBuName}
      );
   }

  public List getConsolidatedInvoicesByUserName(String name)
  {
      return getDao().find("from ConsolidatedInvoice con where con.generatedByUserName = ? ",
            new Object[] { name}
          );
  }

   public ConsolidatedInvoice createConsolidatedInvoice(ConsolidatedInvoice consolidatedInvoice)
    {
     AutoNumberService autoNumberService = (AutoNumberService)ServiceLocator.getInstance().getBean("autoNumberService");
      String numberType = Constants.CON;

      String invoiceNo = autoNumberService.getSequenceNumber(
         consolidatedInvoice.getConsolidatedInvoiceId().getBuName(),
            null,
            numberType
          );

      if(invoiceNo == null)
        {
          throw new ServiceException(
            "AUTO_NUMBER_RETURN_NULL",
            new Object[] {
              consolidatedInvoice.getConsolidatedInvoiceId().getBuName(),
              Constants.CON
            }
          );
        }

      consolidatedInvoice.getConsolidatedInvoiceId().setConsolInvoiceNo(invoiceNo);
      if(consolidatedInvoice.getInvoiceDt() == null){
        JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
        OpenPeriod openPeriodHeader = jobService.findOpenPeriodByBuName(consolidatedInvoice.getConsolidatedInvoiceId().getBuName());
        Date firstDateHeader = openPeriodHeader.getOpenFromDate();
        Calendar from_cal_header = Calendar.getInstance();
        from_cal_header.setTime(firstDateHeader);
        System.out.println("from open period header is "+ from_cal_header.getTime());
        Calendar to_cal_header = from_cal_header;
        to_cal_header.add(Calendar.MONTH, 1);
        to_cal_header.add(Calendar.DATE, -1);
        System.out.println("open period end of month header is "+ to_cal_header.getTime());
        consolidatedInvoice.setInvoiceDt(to_cal_header.getTime());
      }
        getDao().save(consolidatedInvoice);

     return consolidatedInvoice;
    }

   private ConsolInvoiceFile getConsolInvoiceFile(String consolInvoiceNo, String buName , String consolPdfName, String invoiceFileType)
    {
        List<ConsolInvoiceFile> list = getDao().find("from ConsolInvoiceFile i where i.consolidatedInvoice.consolidatedInvoiceId.consolInvoiceNo = ? " +
            " and i.consolidatedInvoice.consolidatedInvoiceId.buName = ? and i.invoiceFileName=?  and i.invoiceFileType.invoiceFileType = ? ",
              new Object[] { consolInvoiceNo, buName, consolPdfName, invoiceFileType}
            );
        if(!list.isEmpty())
            return (ConsolInvoiceFile)list.get(0);
        return null;
    }

     /**
     * Name :setInvoiceFileCountable
     * Date :Nov 14, 2008
     * Purpose : One of korean requirement was the ability to count or not count their tax invoice files .
     * @param id
     * @param countable
     */
    public void setInvoiceFileCountable(long id, boolean countable){
      if(id <= 0) return;
        getDao().bulkUpdate(
          "update ConsolInvoiceFile set countable = ? where id = ?",
          new Object[] { countable, id }
        );
   }

}
