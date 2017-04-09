package com.intertek.util;

import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.intertek.entity.Bank;
import com.intertek.entity.BankAccount;
import com.intertek.entity.Branch;
import com.intertek.entity.BusinessUnit;
import com.intertek.entity.ContactCust;
import com.intertek.entity.Country;
import com.intertek.entity.CountryVAT;
import com.intertek.entity.CustAddress;
import com.intertek.entity.Customer;
import com.intertek.entity.InvoiceLine;
import com.intertek.entity.InvoiceLineSplit;
import com.intertek.entity.JobContract;
import com.intertek.entity.JobContractInvoice;
import com.intertek.entity.JobOrder;
import com.intertek.entity.OpenPeriod;
import com.intertek.entity.PaymentTerm;
import com.intertek.entity.Prebill;
import com.intertek.entity.PrebillSplit;
import com.intertek.entity.ServiceLocation;
import com.intertek.entity.State;
import com.intertek.entity.TaxArticle;
import com.intertek.entity.TaxLabel;
import com.intertek.entity.User;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.AutoNumberService;
import com.intertek.service.BankAccountService;
import com.intertek.service.BankService;
import com.intertek.service.CountryService;
import com.intertek.service.CustomerService;
import com.intertek.service.JobService;
import com.intertek.service.PaymentTermService;
import com.intertek.service.PrebillService;
import com.intertek.service.ServiceLocationService;
import com.intertek.service.TaxService;
import com.intertek.service.UserService;

public class InvoiceUtil
{
  private static final Log logger = LogFactory.getLog(InvoiceUtil.class);

  public static String getInvoiceDir()
  {
    PropertyConfig propertyConfig = (PropertyConfig)ServiceLocator.getInstance().getBean("propertyConfig");

    return propertyConfig.getProperty(Constants.invoiceDir);
  }
  
  public static String getJasperDir()
  {
    PropertyConfig propertyConfig = (PropertyConfig)ServiceLocator.getInstance().getBean("propertyConfig");

    return propertyConfig.getProperty(Constants.jasperDir);
  }
  
  /**
 * Name :copyInvoiceLinesToAdjToInvoiceLines
 * Date :Jul 30, 2008
 * Purpose : In case of a credit , we may have not any prebills for migrated records, 
 * so we need to copy form the original (to adjust) invoice lines.
 * @param lastJobContractInvoice
 * @param jobContractInvoice
 */
public static void copyInvoiceLinesToAdjToInvoiceLines(JobContractInvoice lastJobContractInvoice, JobContractInvoice jobContractInvoice)
  {
	PrebillService prebillService = (PrebillService)ServiceLocator.getInstance().getBean("prebillService");
	List<InvoiceLine> invoiceLinelist = prebillService.getInvoiceLinesByJobContractInvoiceId(lastJobContractInvoice.getId());
	
	for(InvoiceLine invoiceLineToAdj : invoiceLinelist)
    {
      InvoiceLine invoiceLine = new InvoiceLine();

      copyInvoiceLineToInvoiceLine(invoiceLineToAdj,invoiceLine);
      jobContractInvoice.getInvoiceLines().add(invoiceLine);
      invoiceLine.setJobContractInvoice(jobContractInvoice);
    }
  }


  public static void copyPrebillsToInvoiceLines(JobContract jobContract, JobContractInvoice jobContractInvoice)
  {
    if((jobContract == null) || (jobContractInvoice == null)) return;

    Iterator it = jobContract.getPrebills().iterator();
    while(it.hasNext())
    {
      Prebill prebill = (Prebill)it.next();

      InvoiceLine invoiceLine = new InvoiceLine();

      copyPrebillToInvoiceLine(prebill, invoiceLine);
      jobContractInvoice.getInvoiceLines().add(invoiceLine);
      invoiceLine.setJobContractInvoice(jobContractInvoice);
    }
  }

  public static void copyPrebillToInvoiceLine(Prebill prebill, InvoiceLine invoiceLine)
  {
    if((prebill == null) || (invoiceLine == null)) return;

    BeanUtil.copySimpleProperties(invoiceLine, prebill, false);

    TaxArticle taxArticle = prebill.getTaxArticle();
    if(taxArticle != null)
    {
      invoiceLine.setTaxArticleCode(taxArticle.getTaxArticleCode());
    }

    Iterator it = prebill.getPrebillSplits().iterator();
    while(it.hasNext())
    {
      PrebillSplit prebillSplit = (PrebillSplit)it.next();

      InvoiceLineSplit invoiceLineSplit = new InvoiceLineSplit();

      copyPrebillSplitToInvoiceLineSplit(prebillSplit, invoiceLineSplit);

      invoiceLine.getInvoiceLineSplits().add(invoiceLineSplit);
      invoiceLineSplit.setInvoiceLine(invoiceLine);
    }
  }
  
  /**
 * Name :copyInvoiceLineToInvoiceLine
 * Date :Jul 30, 2008
 * Purpose :In case of a credit , we may have not any prebills for migrated records, so we need to copy form the original (to adjust) invoice lines
 * @param InvoiceLineToAdj
 * @param invoiceLine
 */
public static void copyInvoiceLineToInvoiceLine(InvoiceLine InvoiceLineToAdj, InvoiceLine invoiceLine)
  {
    if((InvoiceLineToAdj == null) || (invoiceLine == null)) return;

    BeanUtil.copySimpleProperties(invoiceLine, InvoiceLineToAdj, false);

    /*TaxArticle taxArticle = InvoiceLineToAdj.get;
    if(taxArticle != null)
    {
      invoiceLine.setTaxArticleCode(taxArticle.getTaxArticleCode());
    }*/
   
    Iterator it = InvoiceLineToAdj.getInvoiceLineSplits().iterator();
    while(it.hasNext())
    {
      InvoiceLineSplit invoiceLineSplitToAdj = (InvoiceLineSplit)it.next();

      InvoiceLineSplit invoiceLineSplit = new InvoiceLineSplit();

      copyInvoiceLineSplitToInvoiceLineSplit(invoiceLineSplitToAdj, invoiceLineSplit);
      
      invoiceLine.getInvoiceLineSplits().add(invoiceLineSplit);
      invoiceLineSplit.setInvoiceLine(invoiceLine);
    }
  }

  public static void copyPrebillSplitToInvoiceLineSplit(
    PrebillSplit prebillSplit,
    InvoiceLineSplit invoiceLineSplit
  )
  {
    if((prebillSplit == null) || (invoiceLineSplit == null)) return;

    BeanUtil.copySimpleProperties(invoiceLineSplit, prebillSplit, false);
  }
  
  /**
 * Name :copyInvoiceLineSplitToInvoiceLineSplit
 * Date :Jul 30, 2008
 * Purpose :In case of a credit , we may have not any prebill split for migrated records, so we need to copy form the 
 * original (to adjust) invoiceline split
 * @param invoiceLineSplitToAdj
 * @param invoiceLineSplit
 */
public static void copyInvoiceLineSplitToInvoiceLineSplit(
		  InvoiceLineSplit invoiceLineSplitToAdj,
		    InvoiceLineSplit invoiceLineSplit
		  )
  {
    if((invoiceLineSplitToAdj == null) || (invoiceLineSplit == null)) return;

    BeanUtil.copySimpleProperties(invoiceLineSplit, invoiceLineSplitToAdj, false);
  }
		  

  public static void copyInvoiceHeader(JobContract jobContract, JobContractInvoice jobContractInvoice,  JobContractInvoice lastJobContractInvoice)
  {
    if((jobContract == null) || (jobContractInvoice == null)) return;
    TaxService taxService = (TaxService)ServiceLocator.getInstance().getBean("taxService");
    // copy JobOrder part
    JobOrder jobOrder = jobContract.getJobOrder();
    BusinessUnit bu = null;
    Branch branch = null;
    if(jobOrder != null)
    {
      jobContractInvoice.setJobFinishDate(jobOrder.getJobFinishDate());
      jobContractInvoice.setNominationDate(jobOrder.getNominationDate());
      jobContractInvoice.setVesselNames(jobOrder.getVesselNames());
      jobContractInvoice.setProductNames(jobOrder.getProductNames());
      jobContractInvoice.setJobDescription(jobOrder.getJobDescription());
      jobContractInvoice.setServiceLocationCode(jobOrder.getServiceLocationCode());

      jobContractInvoice.setShipToCustId(jobOrder.getShipToCustId());
      jobContractInvoice.setShipToAddrNum(jobOrder.getShipToAddrNum());
      jobContractInvoice.setReceivedByUserName(jobOrder.getReceivedByUserName());
      jobContractInvoice.setOperation(jobOrder.getOperation());

      // copy ServiceLocation part
      ServiceLocation serviceLocation = null;
      if(jobOrder.getServiceLocationCode() != null)
      {
        ServiceLocationService serviceLocationService = (ServiceLocationService)ServiceLocator.getInstance().getBean("serviceLocationService");
        serviceLocation = serviceLocationService.getServiceLocationByServiceLocationCode(
          jobOrder.getServiceLocationCode()
        );
        if(serviceLocation != null)
        {
          jobContractInvoice.setServiceLocationName(serviceLocation.getName());
        }
      }
      
      //Get TaxLabel object based on Bu Country and service location state
      String serviceLocationState = null;
      if(serviceLocation != null && serviceLocation.getCountryCode().equals(jobOrder.getBusinessUnit().getCountryCode()) && serviceLocation.getStateCode() != null)
    	  serviceLocationState = serviceLocation.getStateCode();
	  TaxLabel taxLabel = taxService.getTaxLabelByCtryCodeAndStateCode(jobOrder.getBusinessUnit().getCountryCode(), serviceLocationState);
      if(taxLabel != null)
      {
    	  jobContractInvoice.setVatLabel(taxLabel.getVatLabel());
          jobContractInvoice.setSalesTaxLabel(taxLabel.getSalesTaxLabel());
          jobContractInvoice.setVatRegLabel(taxLabel.getVatRegLabel());
      }

      bu = jobOrder.getBusinessUnit();
      branch = jobOrder.getBranch();
    }

    // copy JobContract part
    jobContractInvoice.setPymntTermsCd(jobContract.getPymntTermsCd());
    jobContractInvoice.setBillingContactName(jobContract.getBillingContactName());
    jobContractInvoice.setInvoiceLabel1(jobContract.getInvoiceLabel1());
    jobContractInvoice.setInvoiceValue1(jobContract.getInvoiceValue1());
    jobContractInvoice.setInvoiceLabel2(jobContract.getInvoiceLabel2());
    jobContractInvoice.setInvoiceValue2(jobContract.getInvoiceValue2());

    jobContractInvoice.setInvoiceLabel3(jobContract.getInvoiceLabel3());
    jobContractInvoice.setInvoiceValue3(jobContract.getInvoiceValue3());
    jobContractInvoice.setInvoiceLabel4(jobContract.getInvoiceLabel4());
    jobContractInvoice.setInvoiceValue4(jobContract.getInvoiceValue4());
    jobContractInvoice.setInvoiceLabel5(jobContract.getInvoiceLabel5());

    jobContractInvoice.setInvoiceValue5(jobContract.getInvoiceValue5());
    jobContractInvoice.setCustRefNum(jobContract.getCustRefNum());
    jobContractInvoice.setReasonCode(jobContract.getReasonCode());
    jobContractInvoice.setReasonDescr(jobContract.getReasonDescr());
    jobContractInvoice.setTransactionCurrencyCd(jobContract.getTransactionCurrencyCd());

    jobContractInvoice.setVatRegId(jobContract.getVatRegId());
    jobContractInvoice.setCustSentBy(jobContract.getCustSentBy());
    jobContractInvoice.setInvoiceDescr(jobContract.getInvoiceDescr());
    jobContractInvoice.setContactId(jobContract.getContactId());
    jobContractInvoice.setMonthlyFlag(jobContract.getMonthlyFlag());

    jobContractInvoice.setMonthlyJobNumber(jobContract.getMonthlyJobNumber());
    jobContractInvoice.setCustLocationNumber(jobContract.getCustLocationNumber());
    jobContractInvoice.setInvoiceType(jobContract.getInvoiceType());
    
    //because our job contract was not mapped properly for tax vat flag in migration from ps 
    //we better get that flag from original invoice if it is a credit or rebill
    if(lastJobContractInvoice !=null && lastJobContractInvoice.getTaxVatFlag()!=null)
    	jobContractInvoice.setTaxVatFlag(lastJobContractInvoice.getTaxVatFlag());
    else
    	jobContractInvoice.setTaxVatFlag(jobContract.getTaxVatFlag());
    Country vatRegCountry = jobContract.getVatRegCountry();
    if(vatRegCountry != null)
    {
      jobContractInvoice.setVatRegCountryCode(vatRegCountry.getCountryCode());
    }

    // copy PaymentTerm part
    PaymentTermService paymentTermService = (PaymentTermService)ServiceLocator.getInstance().getBean("paymentTermService");
    PaymentTerm paymentTerm = paymentTermService.getPaymentTermByCode(
      jobContract.getPymntTermsCd()
    );
    if(paymentTerm != null)
    {
      jobContractInvoice.setPaymentTermsDesc(paymentTerm.getPaymentTermsDesc());
    }

    // copy CustAddress part
    CustomerService customerService = (CustomerService)ServiceLocator.getInstance().getBean("customerService");
    CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");
    System.out.println("jobContract.getBillingContact().getId()"+jobContract.getBillingContact().getId());
    List contactCusts = customerService.getContactCustsByContactIdAndCustCode(
      jobContract.getBillingContact().getId(),
      jobContract.getCustCode()
    );
    if(contactCusts.size() > 0)
    {
      ContactCust contactCust = (ContactCust)contactCusts.get(0);

      List custAddressList = customerService.getCustAddressDetailsBySeqNumber(
        contactCust.getContactCustId().getLocationNumber(),
        contactCust.getContactCustId().getCustCode()
      );
      if((custAddressList != null) && (custAddressList.size() > 0))
      {
        CustAddress custAddress = (CustAddress)custAddressList.get(0);

        jobContractInvoice.setCustAddress1(custAddress.getAddress1());
        jobContractInvoice.setCustAddress2(custAddress.getAddress2());
        jobContractInvoice.setCustAddress3(custAddress.getAddress3());
        jobContractInvoice.setCustAddress4(custAddress.getAddress4());
        jobContractInvoice.setCustCity(custAddress.getCity());
        jobContractInvoice.setCustCounty(custAddress.getCounty());

        jobContractInvoice.setCustCountry(custAddress.getCountry());
        jobContractInvoice.setCustState(custAddress.getState());
        jobContractInvoice.setCustPostal(custAddress.getPostal());

        if(custAddress.getCountry() != null)
        {
          Country country = countryService.getCountryByCode(custAddress.getCountry());
          if(country != null)
          {
            jobContractInvoice.setCustCountryName(country.getName());

            if(custAddress.getState() != null)
            {
              State state = countryService.getStateByCodeAndCountryCode(
                custAddress.getState(),
                custAddress.getCountry()
              );
              if(state != null)
              {
                jobContractInvoice.setCustStateName(state.getName());
              }
            }
          }
        }
      }
    }

    // copy Customer part
    Customer customer = jobContract.getCustomer();
    if(customer != null)
    {
      jobContractInvoice.setCustomerName(customer.getName());
    }

    // copy BusinessUnit part
    if(bu != null)
    {
      jobContractInvoice.setBuDescription(bu.getDescription());

      jobContractInvoice.setBuCompanyDesc(bu.getCompanyDesc());
      jobContractInvoice.setBuAddress1(bu.getAddress1());
      jobContractInvoice.setBuAddress2(bu.getAddress2());
      jobContractInvoice.setBuAddress3(bu.getAddress3());
      jobContractInvoice.setBuAddress4(bu.getAddress4());

      jobContractInvoice.setBuCity(bu.getCity());
      jobContractInvoice.setBuPostal(bu.getPostal());
      jobContractInvoice.setBuPhonePrefix(bu.getPhonePrefix());
      jobContractInvoice.setBuPhoneNumber(bu.getPhoneNumber());
      jobContractInvoice.setBuPhoneExtension(bu.getPhoneExtension());

      jobContractInvoice.setBuCountryCode(bu.getCountryCode());
      jobContractInvoice.setBuStateCode(bu.getStateCode());

      Country country = countryService.getCountryByCode(bu.getCountryCode());
      if(country != null)
      {
        jobContractInvoice.setBuCountryName(country.getName());
      }
    }

    // copy Branch part
    if(branch != null)
    {
      jobContractInvoice.setBranchCompanyDesc(branch.getCompanyDesc());

      jobContractInvoice.setBranchPhonePrefix(branch.getPhonePrefix());
      jobContractInvoice.setBranchPhoneNumber(branch.getPhoneNumber());
      jobContractInvoice.setBranchPhoneExtension(branch.getPhoneExtension());

      // copy CountryVAT part
     /* if(branch.getStateCode() != null)
      {
        CountryVAT countryVAT = countryService.getCountryVATByCode(
          branch.getStateCode()
        );
        if(countryVAT != null)
        {
          jobContractInvoice.setVatLabel(countryVAT.getVatLabel());
          jobContractInvoice.setSalesTaxLabel(countryVAT.getSalesTaxLabel());
          jobContractInvoice.setVatRegLabel(countryVAT.getVatRegLabel());
        }
      }*/

    }

    // copy BankAccount part
    if(bu != null)
    {
      BankAccountService bankAccountService = (BankAccountService)ServiceLocator.getInstance().getBean("bankAccountService");
      BankAccount bankAccount = bankAccountService.getBankAccountByCompositeId(
        bu.getName(),
        jobContract.getBankCd(),
        jobContract.getBankAcctKey()
      );
      if(bankAccount != null)
      {
        jobContractInvoice.setBankAcctDesc(bankAccount.getBankAcctDesc());
        jobContractInvoice.setDfIdNum(bankAccount.getDfIdNum());
        jobContractInvoice.setBankCode(jobContract.getBankCd());
        jobContractInvoice.setBankAcctCode(jobContract.getBankAcctKey());
      }
    }

    // copy Bank part
    BankService bankService = (BankService)ServiceLocator.getInstance().getBean("bankService");
    Bank bank = bankService.getBankByBankCode(jobContract.getBankCd());
    if(bank != null)
    {
      jobContractInvoice.setBankDesc(bank.getBankDesc());

      jobContractInvoice.setBankDesc(bank.getBankDesc());
      jobContractInvoice.setBankAddress1(bank.getAddress1());
      jobContractInvoice.setBankAddress2(bank.getAddress2());
      jobContractInvoice.setBankAddress3(bank.getAddress3());
      jobContractInvoice.setBankAddress4(bank.getAddress4());

      jobContractInvoice.setBankIdNumber(bank.getBankIdNumber());
      jobContractInvoice.setBankStateCode(bank.getStateCode());
      jobContractInvoice.setBankCountryCode(bank.getCountryCode());
    }
  }

  public static void generateInvoice(
    Long jobContractId,
    String dirName,
    String jasperDirName,
    String creditReasonNote,
    String creditReasonUser,
    String province
  )
  {
	  try {//For Itrack issue # 122836 - START 
	  boolean invValidateFlag = validatePrebillLineItems(jobContractId);
	  if(invValidateFlag)
	  {
		  JobContractInvoice jobContractInvoice = constructJobContractInvoice(
	      jobContractId,
	      creditReasonNote,
	      creditReasonUser
	    );
	
	    if(jobContractInvoice != null)
	    {
	    	System.out.println("province in invoice util is"+province );
	      if(province != null && !province.trim().equals(""))
	    	  jobContractInvoice.setVatProvince(province);
	      else
	    	  jobContractInvoice.setVatProvince("");
	      
	      JobContractInvoice createdJobContractInvoice = createJobContractInvoiceInDB(jobContractInvoice);
	
	      if(createdJobContractInvoice != null)
	      {
	        generateInvoicePDF(createdJobContractInvoice.getId(), dirName, jasperDirName);
	      }
	    }else{
	    	if(creditReasonNote != null){
	    		PrebillService prebillService = (PrebillService)ServiceLocator.getInstance().getBean("prebillService");
	    		prebillService.updateJobContractStatus(jobContractId, Constants.JOBCON_INVOICED_STATUS);
	    	}
	    }
	  }
	  } catch(Exception e){
		  PrebillService prebillService = (PrebillService) ServiceLocator.getInstance().getBean("prebillService");
		  prebillService.checkInvoiceAndPdfGenerated(jobContractId);
	  }//For Itrack issue # 122836 - END 
  }

  public static JobContractInvoice constructJobContractInvoice(
    Long jobContractId,
    String creditReasonNote,
    String creditReasonUser
  )
  {
    PrebillService prebillService = (PrebillService)ServiceLocator.getInstance().getBean("prebillService");
    JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
    JobContract jobContract = prebillService.getJobContractById(jobContractId);
    if(jobContract == null) return null;

    String creditInd = null;
    boolean isCredit = false;
    boolean isRebill = false;
    if(creditReasonNote != null)
    {
      creditInd = Constants.CREDIT_INDICATOR_C;
      isCredit = true;
    }
    else
    {
      creditInd = Constants.CREDIT_INDICATOR_I;
    }
    
    JobContractInvoice lastJobContractInvoice = null;
    lastJobContractInvoice = prebillService.getLastJobContractInvoice(jobContractId);
    
    //Check if the last jobContractInvoice was an Invoice not a credit and this one is an invoice too, Dont let generate happen
    if(!isCredit ){
    	String lastCreditInd = prebillService.getLastInvoiceCreditInd(jobContractId);
    	if(lastCreditInd != null && lastCreditInd.equals(Constants.CREDIT_INDICATOR_I))
    		throw new ServiceException("INVOICE_GENERATION_ERROR", new Object[] {"Invoice already generated!"});
    }

    prebillService.checkInvoiceGenerationPreRequisite(jobContract, isCredit);
    
    if(jobContract.getJobContractStatus() != null && (!jobContract.getJobContractStatus().trim().equals("")))
    {
      if(jobContract.getJobContractStatus().trim().equals(Constants.JOBCON_OPEN_STATUS))
        jobContract.setJobContractStatus(Constants.JOBCON_INVOICED_STATUS);
      //If Rebill without credit, set contract status to invoice else leave contract status to rebilled
      if(jobContract.getJobContractStatus().trim().equals(Constants.JOBCON_REBILLED_STATUS))
      {
    	  isRebill = true;
          if(creditInd.equals(Constants.CREDIT_INDICATOR_I))
             jobContract.setJobContractStatus(Constants.JOBCON_INVOICED_STATUS);
      }
    }

    jobContract.setCreditInd(creditInd);

    JobContractInvoice jobContractInvoice = new JobContractInvoice();
    jobContractInvoice.setJobContract(jobContract);

    jobContractInvoice.setSentToFinFlag(Constants.NEW);
    jobContractInvoice.setCreditReasonNote(creditReasonNote);
    jobContractInvoice.setCreditReasonUserName(creditReasonUser);
    jobContractInvoice.setCreditInd(creditInd);

	    Date jobFinishDate = jobContract.getJobOrder().getJobFinishDate();
	    OpenPeriod openPeriod = jobService.findOpenPeriodByBuName(jobContract.getJobOrder().getBuName());
	    if(openPeriod!= null && openPeriod.getOpenFromDate()!=null && openPeriod.getOpenToDate()!=null && jobFinishDate != null){
	    	//if job finish date is in the range of open period
	    	if(jobFinishDate.compareTo(openPeriod.getOpenFromDate())>=0 && jobFinishDate.compareTo(openPeriod.getOpenToDate())<=0){
	    		jobContractInvoice.setAccountingDate(jobFinishDate); 
	    		 if(!isCredit && !isRebill)
	    			 jobContractInvoice.setInvoiceDate(jobFinishDate);	 
	    	}else{
	    		jobContractInvoice.setAccountingDate(openPeriod.getOpenFromDate());
	    		if(!isCredit && !isRebill)
	    			jobContractInvoice.setInvoiceDate(openPeriod.getOpenFromDate());
	    			
	    	}
	    }
    jobContractInvoice.setGeneratedDateTime(new Date());

    User user = SecurityUtil.getUser();
    if(user != null)
    {
      jobContractInvoice.setCreationUserName(user.getLoginName());
    }

    //JobContractInvoice lastJobContractInvoice = null;

    if(Constants.CREDIT_INDICATOR_C.equals(creditInd) || isRebill)
    {
      //lastJobContractInvoice = prebillService.getLastJobContractInvoice(jobContractId);
      if(lastJobContractInvoice == null)
      {
        throw new ServiceException("INVOICE_GENERATION_ERROR_NO_INVOICE_FOR_CREDIT");
      }
      //for both credit and rebill it is going to update InvoiceToAdjust with the original invoiced id
      jobContractInvoice.setInvoiceToAdjust(lastJobContractInvoice.getInvoice());
      // if it is a credit or rebill, we want invoice date  equal to original invoice date
      jobContractInvoice.setInvoiceDate(lastJobContractInvoice.getInvoiceDate());
      
    }
    System.out.println("jobContractInvoice.getAccountingDate"+jobContractInvoice.getAccountingDate());
    System.out.println("jobContractInvoice.getInvoiceDate"+jobContractInvoice.getInvoiceDate());

    
    if(jobContract.getInvoiceType() != null && (!jobContract.getInvoiceType().trim().equals("")))
    {
      if(jobContract.getInvoiceType().trim().equals(Constants.INV_TYPE_CON))
      {
        jobContractInvoice.setBillType(Constants.INV_TYPE_CON);
        jobContractInvoice.setBillStatus(Constants.BILL_STATUS_RDY);
      }
      if(jobContract.getInvoiceType().trim().equals(Constants.INV_TYPE_REG))
      {
        if((jobContract.getTaxVatFlag() != null) && jobContract.getTaxVatFlag().booleanValue())
          jobContractInvoice.setBillType(Constants.INV_TYPE_TAX);
        else
          jobContractInvoice.setBillType(Constants.INV_TYPE_REG);

        jobContractInvoice.setBillStatus(Constants.BILL_STATUS_INV);
      }
    }

    if(isCredit)
    	copyInvoiceLinesToAdjToInvoiceLines(lastJobContractInvoice, jobContractInvoice);
    else
    	copyPrebillsToInvoiceLines(jobContract, jobContractInvoice);
    copyInvoiceHeader(jobContract, jobContractInvoice, lastJobContractInvoice);

    if(isCredit && lastJobContractInvoice != null)
    {
      Iterator it = jobContractInvoice.getInvoiceLines().iterator();
      while(it.hasNext())
      {
        InvoiceLine invoiceLine = (InvoiceLine)it.next();
        invoiceLine.setUnitPrice(0 - invoiceLine.getUnitPrice());
        invoiceLine.setNetPrice(0 - invoiceLine.getNetPrice());
        if(invoiceLine.getVatAmt()!=null)
        	invoiceLine.setVatAmt(0 - invoiceLine.getVatAmt());
        if(invoiceLine.getTaxAmt()!=null)
        	invoiceLine.setTaxAmt(0 - invoiceLine.getTaxAmt());

        Iterator it1 = invoiceLine.getInvoiceLineSplits().iterator();
        while(it1.hasNext())
        {
          InvoiceLineSplit invoiceLineSplit = (InvoiceLineSplit)it1.next();
          Double allocAmt = invoiceLineSplit.getAllocAmt();
          if(allocAmt != null)
          {
            invoiceLineSplit.setAllocAmt(0 - allocAmt);
          }
        }
      }
    }

    return jobContractInvoice;
  }

  public static JobContractInvoice createJobContractInvoiceInDB(JobContractInvoice jobContractInvoice)
  {
    PrebillService prebillService = (PrebillService)ServiceLocator.getInstance().getBean("prebillService");
    jobContractInvoice = prebillService.calculateTotalAmounts(jobContractInvoice, jobContractInvoice.getJobContract().getJobOrder().getBuName());
    JobContractInvoice savedJobContractInvoice = null;
    String message = null;
    for(int i=0; i<5; i++)
    {
      try
      {
        long startTime = System.currentTimeMillis();
        savedJobContractInvoice = prebillService.createJobContractInvoice(jobContractInvoice);
        long endTime = System.currentTimeMillis();
        System.out.println("in invoice util saveJobContract invoice is"+savedJobContractInvoice );

        logger.info(" Time used to createJobContractInvoice: " + (endTime - startTime));
      }
      catch(Throwable t)
      {
        t.printStackTrace();
        message = t.getMessage();
      }

      if(savedJobContractInvoice != null) break;
      logger.info("Failed to create JobContractInvoice: " + i);
      try
      {
        Thread.sleep(500);
      }
      catch(Throwable t)
      {
      }
    }

    if(savedJobContractInvoice == null)
    {
      throw new ServiceException("INVOICE_GENERATION_ERROR", new Object[] {message});
    }

    return savedJobContractInvoice;
  }

  public static void generateInvoicePDF(
    Long jobContractInvoiceId,
    String dirName, 
    String jasperDirName
  )
  {
    PrebillService prebillService = (PrebillService)ServiceLocator.getInstance().getBean("prebillService");
    prebillService.generateInvoicePDF(jobContractInvoiceId, dirName, jasperDirName, false);
  }

  public static void generateInvoices(List jobContractIds, String dirName, String jasperDirName)
  {
    if(jobContractIds != null)
    {
      for(int i=0; i<jobContractIds.size(); i++)
      {
        Long jobContractId = (Long)jobContractIds.get(i);
        //change on 10112008 for vatprovince
        //generateInvoice(jobContractId, dirName, jasperDirName, null, null);
        generateInvoice(jobContractId, dirName, jasperDirName, null, null,null);
        //up to here
      }
    }
  }
  
  public static boolean validateBankDetails(JobContract jobContract)
  {
	  boolean  bankValidateFlag = false;
	  boolean  bankAcctValidateFlag = false;
	  Bank validatedBank = null;
	  
	  JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
	  
	  //Validate bank code
	  List banks = jobService.getBankCodebyBunameandCurrency(jobContract.getJobOrder().getBuName(),jobContract.getTransactionCurrencyCd());
	  if(banks != null && banks.size() > 0)
	  {
		  for(int i=0;i<banks.size();i++)
		  {
			  Bank bank = (Bank) banks.get(i);
			  if(bank.getBankCode().trim().equals(jobContract.getBankCd()))
			  {
				  bankValidateFlag = true;
				  validatedBank = bank;
				  break;
			  }
		  }
	  }
	  if(!bankValidateFlag)
	  {
		  if(banks != null && banks.size() == 1)
		  {
			  //Check for the correponding bank account for the given bank
			  Bank bank = (Bank) banks.get(0);
			  List bankAccts = jobService.getBankAccountByBankCodeAndCurrency(jobContract.getJobOrder().getBuName(),jobContract.getTransactionCurrencyCd(),bank.getBankCode());
			  
			  
			  if(bankAccts != null && bankAccts.size() == 1)
			  {
				  BankAccount bankAcct = (BankAccount) bankAccts.get(0);
				  jobContract.setBankCd(bankAcct.getBankAccountId().getBankCode());
				  jobContract.setBankAcctKey(bankAcct.getBankAccountId().getBankAcctCode());
				  bankAcctValidateFlag = true;
				  return bankAcctValidateFlag;
			  }			 
			  else
			  {
				  bankAcctValidateFlag = false;
				  return bankAcctValidateFlag;
			  }
		  }
		  else
		  {
			  return bankValidateFlag;
		  }
	  }
	  else //Bank code is correct, check for validity of bank account code
	  {
		  List bankAccts = jobService.getBankAccountByBankCodeAndCurrency(jobContract.getJobOrder().getBuName(),jobContract.getTransactionCurrencyCd(),validatedBank.getBankCode());
		  if(bankAccts != null && bankAccts.size() > 0)
		  {
			  for(int j=0;j<bankAccts.size();j++)
			  {
				  BankAccount bankAcct = (BankAccount) bankAccts.get(j);
				  if(bankAcct.getBankAccountId().getBankAcctCode().trim().equals(jobContract.getBankAcctKey()))
				  {
					  bankAcctValidateFlag = true;
					  break;
				  }
			  }
		  }
		  if(!bankAcctValidateFlag)
		  {
			  if(bankAccts != null && bankAccts.size() == 1)
			  {
				  BankAccount bankAcct = (BankAccount) bankAccts.get(0);
				  jobContract.setBankCd(bankAcct.getBankAccountId().getBankCode());
				  jobContract.setBankAcctKey(bankAcct.getBankAccountId().getBankAcctCode());
				  bankAcctValidateFlag = true;
				  return bankAcctValidateFlag;
			  }
			  else
			  {
				  bankAcctValidateFlag = false;
				  return bankAcctValidateFlag;
			  }
		  }
		  else
		  {
			  return bankAcctValidateFlag;
		  }
	  }
	  
	  
  }
  
  public static boolean validateCustomerLocation(JobContract jobContract)
  {
	  boolean validateFlag = false;
	  CustomerService customerService = (CustomerService)ServiceLocator.getInstance().getBean("customerService");
	  List contactCusts =  customerService.getContactCustsByContactIdAndCustCode(jobContract.getBillingContact().getId(), jobContract.getCustCode());
	  if(contactCusts == null || contactCusts.size() == 0)
		  return false;
	  for(int i=0;i<contactCusts.size();i++)
	  {
		  ContactCust contactCust = (ContactCust) contactCusts.get(i);
		  if(contactCust.getContactCustId().getLocationNumber()==jobContract.getCustLocationNumber())
		  {
			  validateFlag = true;
			  break;
		  }
	  }
	  if(!validateFlag && contactCusts.size() > 0)
	  {
		  ContactCust contactCust = (ContactCust) contactCusts.get(0);
		  jobContract.setCustLocationNumber(contactCust.getContactCustId().getLocationNumber());
		  validateFlag = true;
	  }
	  return validateFlag;
  }
  
  public static boolean validatePrebillLineItems(Long jobContractId)
  {
	  PrebillService prebillService = (PrebillService)ServiceLocator.getInstance().getBean("prebillService");
	  JobContract jobContract = prebillService.getJobContractById(jobContractId);
	  if(jobContract == null)
		  return false;
	  Set prebills = jobContract.getPrebills();
	  if(prebills != null && prebills.size() > 0)
	  {
		  Iterator iter = prebills.iterator();
		  while(iter.hasNext())
		  {
			  Prebill prebill = (Prebill) iter.next();
			  if(prebill != null)
			  {
				  if(prebill.getPrimaryBranchCd() == null || prebill.getPrimaryBranchCd().trim().equals(""))
					  throw new ServiceException("INVOICE_GENERATION_ERROR_BRANCH_CANNOT_BE_BLANK");
				  if(prebill.getProductGroup() == null || prebill.getProductGroup().trim().equals(""))
					  throw new ServiceException("INVOICE_GENERATION_ERROR_PRODUCT_GROUP_CANNOT_BE_BLANK");
				  if(prebill.getDeptid() == null || prebill.getDeptid().trim().equals(""))
					  throw new ServiceException("INVOICE_GENERATION_ERROR_DEPT_ID_CANNOT_BE_BLANK");
				  if(prebill.getAccount() == null || prebill.getAccount().trim().equals(""))
					  throw new ServiceException("INVOICE_GENERATION_ERROR_ACCT_CODE_CANNOT_BE_BLANK");

			  }
		  }
	  }
	  return true;
  }
  
  
  public static boolean validateSplitPct(Double splitPct)
  {
	  int scale=2;
	  double pct=100;
	  if(!splitPct.toString().contains("-")){
		  if(NumberUtil.roundHalfUp(splitPct, scale)>pct){		  	  
		  return false;	  
		  }
		  else
		  { return true;}
	   }
	  else
	  { return false;}
   }
 
  public static boolean validateLineVatCodes(JobOrder jobOrder,Set prebills) 
  {
	  UserService userService=(UserService)ServiceLocator.getInstance().getBean("userService");
	  CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");
	  Country country=null;
	  if(jobOrder.getBuName() != null && (!jobOrder.getBuName().trim().equals("")))	 
      {
      BusinessUnit bu = userService.getBusinessUnitByName(jobOrder.getBuName()); 
       country = bu.getCountry();
      }
	  String s1="";
	  int c=0;
	  String[] s=new String[prebills.size()];
	  Iterator prebillIterator = prebills.iterator();
	  if(country.getSameProvinceValidation()!= null && country.getSameProvinceValidation()!= false)
	  {
      while(prebillIterator.hasNext())	 
      {
        Prebill prebill = (Prebill) prebillIterator.next();
  		//s[c]= prebill.getVatCode();
        CountryVAT countryVAT=null;
        countryVAT=countryService.getDefaultStateByCountryCodeandVatCode(country.getCountryCode(),prebill.getVatCode());
        if(countryVAT != null && countryVAT.getCountryVATId()!= null){
        s[c]= countryVAT.getCountryVATId().getStateCode();
        }
  		c++; 
      }
         if(s.length>1){
	      for(int j=0;j<(s.length-1);j++)
	      {
	    	  if(s[j]!=null && !s[j].equalsIgnoreCase(s[j+1]))
	    	  { 
	    		  System.out.println("first vatcode is "+ s[j]);
	    		  System.out.println("second vatcode is"+ s[j+1]);
	    		  System.out.println("inside the array loop");
	    		  return false;
	    	  }
	      }	        
         }
         return true;
	  }
	  else
	    {
		  return true;
		}
  }
  
 public static boolean validateInvoiceCredit(JobContract jobContract) 
  {
	JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");		
	JobContractInvoice lastJobContractInvoice = jobService.getLastJobContractInvoice(jobContract.getId());
	if(lastJobContractInvoice.getInvoice().contains(Constants.CR)||Constants.CREDIT_INDICATOR_C.equals(lastJobContractInvoice.getCreditInd()))
	 {	 
	  return false;
     }
	  else
	  {		 
       return true;
       }	
  }
  
}
