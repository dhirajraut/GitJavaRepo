/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 */
package com.intertek.phoenix.externalEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.intertek.entity.BankAccount;
import com.intertek.entity.Contact;
import com.intertek.phoenix.entity.CEInvoice;
import com.intertek.phoenix.entity.CEInvoiceLineItem;
import com.intertek.phoenix.entity.CEJobContract;
import com.intertek.phoenix.entity.CEJobOrder;
import com.intertek.phoenix.entity.SourceOrigin;
import com.intertek.phoenix.esb.Logable;

/**
 * 
 * @author Eric.Nguyen
 */
public class CEInvoiceX implements Logable {
    private String buName; // " select="jobContract/jobOrder/buName"/>
    private String branchName; // " select="jobContract/jobOrder/branchName"/>
    private Date jobFinishDate; // " select="substring(jobFinishDate, 1, 10)"/>
    private String shipToCustId; // " select="shipToCustId"/>
    private String shipToAddrNum; // " select="shipToAddrNum"/>
    private String vesselNames; // " select="vesselNames"/>
    private String productNames; // " select="productNames"/>
    private String receivedByUserName; // " select="receivedByUserName"/>
    private String operation; // " select="operation"/>
    private String serviceLocationCode; // " select="serviceLocationCode"/>

    private String custSentBy; // " select="custSentBy"/>
    private String invoiceDescr; // " select="invoiceDescr"/>
    private String invoice; // " select="invoice"/>
    private String custCode; // " select="jobContract/custCode"/>
    private String jobNumber; // " select="jobContract/jobNumber"/>
    private String custRefNum; // " select="custRefNum"/>
    private String billingContactName; // " select="billingContactName"/>
    private String bankCode; // " select="bankCode"/>
    private String bankAcctCode; // " select="bankAcctCode"/>
    private String pymntTermsCd; // " select="pymntTermsCd"/>
    private String contractCode; // " select="jobContract/contractCode"/>
    private String contactId; // " select="contactId"/>
    private String monthlyFlag; // " select="monthlyFlag"/>
    private String monthlyJobNumber; // " select="monthlyJobNumber"/>
    private String custLocationNumber; // " select="custLocationNumber"/>
    private String invoiceType; // " select="invoiceType"/>
    private String transactionCurrencyCd; // " select="transactionCurrencyCd"/>
    private String taxVatFlag; // " select="taxVatFlag"/>
    private String branchPhoneNumber; // " select="branchPhoneNumber"/>

    // <!-- unique run control id to be sent to PS - use a guid and store in
    // JOB_CONTRACT_INVOICE -->
    // <!-- note that only bring back the current invoice row (last row
    // generated) from jobContractinvoice -->
    private String runcntlid; // " select="runcntlid"/>

    private Date invoiceDate; // " select="substring(invoiceDate, 1, 10)"/>
    private Date accountingDate; // " select="substring(accountingDate, 1,
    // 10)"/>

    // <!-- below field only used for credits - this is the original invoice id
    // of the bill to be credited -->
    private String invoiceToAdjust; // " select="invoiceToAdjust"/>

    private String customerName; // " select="customerName"/>
    private String serviceLocationName; // " select="serviceLocationName"/>
    private String invoiceLabel1; // " select="invoiceLabel1"/>
    private String invoiceLabel2; // " select="invoiceLabel2"/>
    private String invoiceLabel3; // " select="invoiceLabel3"/>
    private String invoiceLabel4; // " select="invoiceLabel4"/>
    private String invoiceLabel5; // " select="invoiceLabel5"/>
    private String invoiceValue1; // " select="invoiceValue1"/>
    private String invoiceValue2; // " select="invoiceValue2"/>
    private String invoiceValue3; // " select="invoiceValue3"/>
    private String invoiceValue4; // " select="invoiceValue4"/>
    private String invoiceValue5; // " select="invoiceValue5"/>
    private String vatRegCountryCode; // " select="vatRegCountryCode"/>

    private String billType;

    private List<CEInvoiceLineX> invoiceLine;

    public CEInvoiceX() {

    }

    public CEInvoiceX(CEInvoice invoice) {
        CEJobContract jc=invoice.getJobContract();
        CEJobOrder jo=jc.getJobOrder();
        this.buName=jo.getBuName(); // " select="jobContract/jobOrder/buName"/>
        this.branchName=jo.getBranchName(); // " select="jobContract/jobOrder/branchName"/>
        this.jobFinishDate=jo.getJobFinishDate(); // " select="substring(jobFinishDate, 1, 10)"/>
        this.shipToCustId=invoice.getReportReceivingAddress().getCustCode(); // " select="shipToCustId"/>
        this.shipToAddrNum=invoice.getReportReceivingAddress().getLocationNumber()+""; // " select="shipToAddrNum"/>
        this.vesselNames=""; // " select="vesselNames"/>
//TODO        this.productNames=; // " select="productNames"/> -- comma seperated
//TODO        this.receivedByUserName=; // " select="receivedByUserName"/>
        this.operation=jo.getOperation(); // " select="operation"/>
        this.serviceLocationCode=jo.getServiceLocationCode(); // " select="serviceLocationCode"/>

        SourceOrigin so=jc.getSourceOrigin();
        if(so!=null){
            this.custSentBy=so.getValue(); // " select="custSentBy"/>
        }
        this.invoiceDescr=invoice.getDescription(); // " select="invoiceDescr"/>
        this.invoice=invoice.getInvoiceNumber(); // " select="invoice"/>
        this.custCode=jc.getCustomerCode(); // " select="jobContract/custCode"/>
        this.jobNumber=jo.getJobNumber(); // " select="jobContract/jobNumber"/>
        this.custRefNum=jc.getCustRefNum(); // " select="custRefNum"/>
        
        Contact bc=jc.getBillingContact();
        if(bc!=null){
            //TODO this.billingContactName=how to get the name from bc? //    invoice.getBillingContact().; // " select="billingContactName"/>
        }

        this.bankCode=jc.getRemitToBankCode(); // " select="bankCode"/>
        
        BankAccount ba=jc.getRemitToBankAccount();
        if(ba!=null){
            this.bankAcctCode=ba.getBankAccountId().getBankAcctCode(); // " select="bankAcctCode"/>
        }
        this.pymntTermsCd=jc.getPaymentTerms(); // " select="pymntTermsCd"/>
        this.contractCode=jc.getContractCode(); // " select="jobContract/contractCode"/>
        this.contactId=jc.getContactId()+""; // " select="contactId"/>
//TODO        this.monthlyFlag=; // " select="monthlyFlag"/>
//TODO        this.monthlyJobNumber; // " select="monthlyJobNumber"/>
//TODO        this.custLocationNumber=jc.getCustomer().getl; // " select="custLocationNumber"/>
        this.invoiceType=invoice.getType().getValue(); // " select="invoiceType"/>
        this.transactionCurrencyCd=invoice.getTransactionCurrencyCd(); // " select="transactionCurrencyCd"/>
//        this.taxVatFlag=; // " select="taxVatFlag"/>
//TODO        this.branchPhoneNumber=; // " select="branchPhoneNumber"/>

        // <!-- unique run control id to be sent to PS - use a guid and store in
        // JOB_CONTRACT_INVOICE -->
        // <!-- note that only bring back the current invoice row (last row
        // generated) from jobContractinvoice -->
//TODO        this.runcntlid; // " select="runcntlid"/>

        this.invoiceDate=invoice.getInvoiceDate(); // " select="substring(invoiceDate, 1, 10)"/>
        this.accountingDate=invoice.getAccountingDate(); // " select="substring(accountingDate, 1, 10)"/>

        // <!-- below field only used for credits - this is the original invoice id
        // of the bill to be credited -->

//TODO        this.invoiceToAdjust=invoice.geti; // " select="invoiceToAdjust"/>

//TODO        this.customerName; // " select="customerName"/>
        this.serviceLocationName=jo.getServiceLocationCode(); // " select="serviceLocationName"/>
        this.invoiceLabel1=jc.getInvoiceLabel1(); // " select="invoiceLabel1"/>
        this.invoiceLabel2=jc.getInvoiceLabel2(); // " select="invoiceLabel2"/>
        this.invoiceLabel3=jc.getInvoiceLabel3(); // " select="invoiceLabel3"/>
        this.invoiceLabel4=jc.getInvoiceLabel4(); // " select="invoiceLabel4"/>
        this.invoiceLabel5=jc.getInvoiceLabel5(); // " select="invoiceLabel5"/>
        this.invoiceValue1=jc.getInvoiceValue1(); // " select="invoiceValue1"/>
        this.invoiceValue2=jc.getInvoiceValue2(); // " select="invoiceValue2"/>
        this.invoiceValue3=jc.getInvoiceValue3(); // " select="invoiceValue3"/>
        this.invoiceValue4=jc.getInvoiceValue4(); // " select="invoiceValue4"/>
        this.invoiceValue5=jc.getInvoiceValue5(); // " select="invoiceValue5"/>
        this.vatRegCountryCode=jc.getVatRegCountry(); // " select="vatRegCountryCode"/>

//TODO        this.billType=;
        
        this.invoiceLine=new ArrayList<CEInvoiceLineX>();
        Set<CEInvoiceLineItem> items=invoice.getInvoiceLineItems();;
        for(CEInvoiceLineItem item:items){
            this.invoiceLine.add(new CEInvoiceLineX(item));
        }
    }
    
    @Override
    public String getId() {
        return invoice;
    }

    public String getBuName() {
        return buName;
    }

    public void setBuName(String buName) {
        this.buName = buName;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public String getShipToCustId() {
        return shipToCustId;
    }

    public void setShipToCustId(String shipToCustId) {
        this.shipToCustId = shipToCustId;
    }

    public String getShipToAddrNum() {
        return shipToAddrNum;
    }

    public void setShipToAddrNum(String shipToAddrNum) {
        this.shipToAddrNum = shipToAddrNum;
    }

    public String getVesselNames() {
        return vesselNames;
    }

    public void setVesselNames(String vesselNames) {
        this.vesselNames = vesselNames;
    }

    public String getProductNames() {
        return productNames;
    }

    public void setProductNames(String productNames) {
        this.productNames = productNames;
    }

    public String getReceivedByUserName() {
        return receivedByUserName;
    }

    public void setReceivedByUserName(String receivedByUserName) {
        this.receivedByUserName = receivedByUserName;
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public String getServiceLocationCode() {
        return serviceLocationCode;
    }

    public void setServiceLocationCode(String serviceLocationCode) {
        this.serviceLocationCode = serviceLocationCode;
    }

    public String getCustSentBy() {
        return custSentBy;
    }

    public void setCustSentBy(String custSentBy) {
        this.custSentBy = custSentBy;
    }

    public String getInvoiceDescr() {
        return invoiceDescr;
    }

    public void setInvoiceDescr(String invoiceDescr) {
        this.invoiceDescr = invoiceDescr;
    }

    public String getInvoice() {
        return invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getCustRefNum() {
        return custRefNum;
    }

    public void setCustRefNum(String custRefNum) {
        this.custRefNum = custRefNum;
    }

    public String getBillingContactName() {
        return billingContactName;
    }

    public void setBillingContactName(String billingContactName) {
        this.billingContactName = billingContactName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankAcctCode() {
        return bankAcctCode;
    }

    public void setBankAcctCode(String bankAcctCode) {
        this.bankAcctCode = bankAcctCode;
    }

    public String getPymntTermsCd() {
        return pymntTermsCd;
    }

    public void setPymntTermsCd(String pymntTermsCd) {
        this.pymntTermsCd = pymntTermsCd;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getMonthlyFlag() {
        return monthlyFlag;
    }

    public void setMonthlyFlag(String monthlyFlag) {
        this.monthlyFlag = monthlyFlag;
    }

    public String getMonthlyJobNumber() {
        return monthlyJobNumber;
    }

    public void setMonthlyJobNumber(String monthlyJobNumber) {
        this.monthlyJobNumber = monthlyJobNumber;
    }

    public String getCustLocationNumber() {
        return custLocationNumber;
    }

    public void setCustLocationNumber(String custLocationNumber) {
        this.custLocationNumber = custLocationNumber;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getTransactionCurrencyCd() {
        return transactionCurrencyCd;
    }

    public void setTransactionCurrencyCd(String transactionCurrencyCd) {
        this.transactionCurrencyCd = transactionCurrencyCd;
    }

    public String getTaxVatFlag() {
        return taxVatFlag;
    }

    public void setTaxVatFlag(String taxVatFlag) {
        this.taxVatFlag = taxVatFlag;
    }

    public String getBranchPhoneNumber() {
        return branchPhoneNumber;
    }

    public void setBranchPhoneNumber(String branchPhoneNumber) {
        this.branchPhoneNumber = branchPhoneNumber;
    }

    public String getRuncntlid() {
        return runcntlid;
    }

    public void setRuncntlid(String runcntlid) {
        this.runcntlid = runcntlid;
    }

    public Date getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public Date getAccountingDate() {
        return accountingDate;
    }

    public void setAccountingDate(Date accountingDate) {
        this.accountingDate = accountingDate;
    }

    public String getInvoiceToAdjust() {
        return invoiceToAdjust;
    }

    public void setInvoiceToAdjust(String invoiceToAdjust) {
        this.invoiceToAdjust = invoiceToAdjust;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getServiceLocationName() {
        return serviceLocationName;
    }

    public void setServiceLocationName(String serviceLocationName) {
        this.serviceLocationName = serviceLocationName;
    }

    public String getInvoiceLabel1() {
        return invoiceLabel1;
    }

    public void setInvoiceLabel1(String invoiceLabel1) {
        this.invoiceLabel1 = invoiceLabel1;
    }

    public String getInvoiceLabel2() {
        return invoiceLabel2;
    }

    public void setInvoiceLabel2(String invoiceLabel2) {
        this.invoiceLabel2 = invoiceLabel2;
    }

    public String getInvoiceLabel3() {
        return invoiceLabel3;
    }

    public void setInvoiceLabel3(String invoiceLabel3) {
        this.invoiceLabel3 = invoiceLabel3;
    }

    public String getInvoiceLabel4() {
        return invoiceLabel4;
    }

    public void setInvoiceLabel4(String invoiceLabel4) {
        this.invoiceLabel4 = invoiceLabel4;
    }

    public String getInvoiceLabel5() {
        return invoiceLabel5;
    }

    public void setInvoiceLabel5(String invoiceLabel5) {
        this.invoiceLabel5 = invoiceLabel5;
    }

    public String getInvoiceValue1() {
        return invoiceValue1;
    }

    public void setInvoiceValue1(String invoiceValue1) {
        this.invoiceValue1 = invoiceValue1;
    }

    public String getInvoiceValue2() {
        return invoiceValue2;
    }

    public void setInvoiceValue2(String invoiceValue2) {
        this.invoiceValue2 = invoiceValue2;
    }

    public String getInvoiceValue3() {
        return invoiceValue3;
    }

    public void setInvoiceValue3(String invoiceValue3) {
        this.invoiceValue3 = invoiceValue3;
    }

    public String getInvoiceValue4() {
        return invoiceValue4;
    }

    public void setInvoiceValue4(String invoiceValue4) {
        this.invoiceValue4 = invoiceValue4;
    }

    public String getInvoiceValue5() {
        return invoiceValue5;
    }

    public void setInvoiceValue5(String invoiceValue5) {
        this.invoiceValue5 = invoiceValue5;
    }

    public String getVatRegCountryCode() {
        return vatRegCountryCode;
    }

    public void setVatRegCountryCode(String vatRegCountryCode) {
        this.vatRegCountryCode = vatRegCountryCode;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public List<CEInvoiceLineX> getInvoiceLine() {
        return invoiceLine;
    }

    public void setInvoiceLine(List<CEInvoiceLineX> invoiceLine) {
        this.invoiceLine = invoiceLine;
    }

    public Date getJobFinishDate() {
        return jobFinishDate;
    }

    public void setJobFinishDate(Date jobFinishDate) {
        this.jobFinishDate = jobFinishDate;
    }

}
