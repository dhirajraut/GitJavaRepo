package com.intertek.phoenix.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.intertek.entity.BusinessUnit;
import com.intertek.entity.Contact;
import com.intertek.entity.ContactCust;
import com.intertek.entity.CustAddrSeq;
import com.intertek.entity.Customer;
import com.intertek.entity.PaymentTerm;
import com.intertek.entity.User;

/**
 * 
 * 
 * @author richard.qin
 */

@Entity
@Table(name = "PHX_CONSOL_INVOICE")
public class ConsolidatedInvoice {

    @Id
    private ConsolidatedInvoiceId id;
    
    @Column(name = "BILL_STATUS", length = 3)
    private String billStatus;
    
    @Column(name = "BILL_TYPE", length = 3)
    private String billType;
    
    @Column(name = "CURRENCY_CD", length = 3)
    private String currencyCd;
    
    @Column(name = "INVOICE_TYPE", length = 3)
    private String invoiceType;
    
    @Column(name = "BILL_ADD_DT")
    private Date billAddDt;
    
    @Column(name = "FROM_DT")
    private Date fromDate;
    
    @Column(name = "TO_DT")
    private Date toDate;
    
    @Column(name = "INVOICE_DT")
    private Date invoiceDate;
    
    @Column(name = "BANK_CD", length = 5)
    private String bankCode;
    
    @Column(name = "BANK_ACCT_KEY", length = 5)
    private String bankAcctKey;
    
    @Column(name = "INVOICE_AMT_PRE_TAX")
    private Double invoiceAmountPreTax;
    
    @Column(name = "INVOICE_AMT_POST_TAX")
    private Double invoiceAmountPostTax;
    
    @Column(name = "INVOICE", length = 22)
    private String invoice;
    
    @Column(name = "LOCATION_NUMBER", updatable = false, insertable = false)
    private Integer locationNumber;
    
    @Column(name = "PYMNT_TERMS_CD", length = 3)
    private String paymentTermsCode;

    @Column(name = "SENT_TO_FIN_FLAG", length = 8)
    private String sentToFinFlag;
    
    @Column(name = "GENERATE_TIME")
    private Date generateTime;
    
    @Column(name = "INVOICE_FILE_NAME", length = 80)
    private String invoiceFileName;
    
    @Column(name = "PDF_GENERATED_FLAG", length = 1)
    private Boolean pdfGeneratedFlag;
    
    @Column(name = "VAT_PROVINCE", length = 20)
    private String vatProvince;
    
    @Column(name = "GENERATED_BY", length = 128)
    private String generatedByUserName;
    
    private User generatedBy;
    
    @Column(name = "CUST_CODE", length = 15, updatable = false, insertable = false)
    private String custCode;
    @ManyToOne
    @JoinColumn(name = "CUST_CODE")
    private Customer customer;
    
    @Column(name = "CONTACT_ID", updatable = false, insertable = false)
    private Long contactId;
    @ManyToOne
    @JoinColumn(name = "CONTACT_ID")
    private Contact contact;
    
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "LOCATION_NUMBER"), 
        @JoinColumn(name="CUST_CODE")})
    private CustAddrSeq custAddrSeq;
    
    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "CONTACT_ID"), 
        @JoinColumn(name="CUST_CODE")})
    private ContactCust contactCust;
    
    @ManyToOne
    @JoinColumn(name = "BU_NAME")
    private BusinessUnit businessUnit;
    @ManyToOne
    @JoinColumn(name = "PYMNT_TERMS_CD")
    private PaymentTerm paymentTerm;
    
    public ConsolidatedInvoice() {
    }

    public ConsolidatedInvoiceId getId() {
        return id;
    }

    public void setId(ConsolidatedInvoiceId id) {
        this.id = id;
    }

    public String getCustCode() {
        return this.custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public String getBillStatus() {
        return this.billStatus;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

    public String getBillType() {
        return this.billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getCurrencyCd() {
        return this.currencyCd;
    }

    public void setCurrencyCd(String currencyCd) {
        this.currencyCd = currencyCd;
    }

    public String getInvoiceType() {
        return this.invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public Date getBillAddDt() {
        return this.billAddDt;
    }

    public void setBillAddDt(Date billAddDt) {
        this.billAddDt = billAddDt;
    }

    public Date getFromDate() {
        return this.fromDate;
    }

    public void setFromDate(Date fromDt) {
        this.fromDate = fromDt;
    }

    public Date getToDate() {
        return this.toDate;
    }

    public void setToDate(Date toDt) {
        this.toDate = toDt;
    }

    public Date getInvoiceDate() {
        return this.invoiceDate;
    }

    public void setInvoiceDate(Date invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getPaymentTermsCode() {
        return this.paymentTermsCode;
    }

    public void setPaymentTermsCode(String paymentTermsCode) {
        this.paymentTermsCode = paymentTermsCode;
    }

    public String getBankCode() {
        return this.bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankAcctKey() {
        return this.bankAcctKey;
    }

    public void setBankAcctKey(String bankAcctKey) {
        this.bankAcctKey = bankAcctKey;
    }

    public Double getInvoiceAmountPreTax() {
        return this.invoiceAmountPreTax;
    }

    public void setInvoiceAmountPreTax(Double invoiceAmtPreTax) {
        this.invoiceAmountPreTax = invoiceAmtPreTax;
    }

    public Double getInvoiceAmountPostTax() {
        return this.invoiceAmountPostTax;
    }

    public void setInvoiceAmountPostTax(Double invoiceAmtPostTax) {
        this.invoiceAmountPostTax = invoiceAmtPostTax;
    }

    public String getInvoice() {
        return this.invoice;
    }

    public void setInvoice(String invoice) {
        this.invoice = invoice;
    }

    public Integer getLocationNumber() {
        return this.locationNumber;
    }

    public void setLocationNumber(Integer locationNumber) {
        this.locationNumber = locationNumber;
    }

    public String getSentToFinFlag() {
        return this.sentToFinFlag;
    }

    public void setSentToFinFlag(String sentToFinFlag) {
        this.sentToFinFlag = sentToFinFlag;
    }

    public Date getGenerateTime() {
        return this.generateTime;
    }

    public void setGenerateTime(Date generateTime) {
        this.generateTime = generateTime;
    }

    public String getInvoiceFileName() {
        return this.invoiceFileName;
    }

    public void setInvoiceFileName(String invoiceFileName) {
        this.invoiceFileName = invoiceFileName;
    }

    public Boolean getPdfGeneratedFlag() {
        return this.pdfGeneratedFlag;
    }

    public void setPdfGeneratedFlag(Boolean pdfGeneratedFlag) {
        this.pdfGeneratedFlag = pdfGeneratedFlag;
    }

    public String getVatProvince() {
        return this.vatProvince;
    }

    public void setVatProvince(String vatProvince) {
        this.vatProvince = vatProvince;
    }

    public String getGeneratedByUserName() {
        return this.generatedByUserName;
    }

    public void setGeneratedByUserName(String generatedByUserName) {
        this.generatedByUserName = generatedByUserName;
    }

    public User getGeneratedBy() {
        return this.generatedBy;
    }

    public void setGeneratedBy(User generatedBy) {
        this.generatedBy = generatedBy;
    }

    public Customer getCustomer() {
        return this.customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Contact getContact() {
        return this.contact;
    }

    public void setContact(Contact contact) {
        this.contact = contact;
    }

    public CustAddrSeq getCustAddrSeq() {
        return this.custAddrSeq;
    }

    public void setCustAddrSeq(CustAddrSeq custAddrSeq) {
        this.custAddrSeq = custAddrSeq;
    }

    public ContactCust getContactCust() {
        return this.contactCust;
    }

    public void setContactCust(ContactCust contactCust) {
        this.contactCust = contactCust;
    }

    public BusinessUnit getBusinessUnit() {
        return this.businessUnit;
    }

    public void setBusinessUnit(BusinessUnit businessUnit) {
        this.businessUnit = businessUnit;
    }

    public PaymentTerm getPaymentTerm() {
        return this.paymentTerm;
    }

    public void setPaymentTerm(PaymentTerm paymentTerm) {
        this.paymentTerm = paymentTerm;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

}
