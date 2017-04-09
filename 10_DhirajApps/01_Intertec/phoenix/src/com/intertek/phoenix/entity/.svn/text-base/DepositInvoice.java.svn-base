/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 *
 * @Date : 4/22/2009
 * @Author : The Phoenix Team
 */

package com.intertek.phoenix.entity;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.intertek.entity.Bank;
import com.intertek.entity.Contact;
import com.intertek.entity.CustAddress;

/**
 * @author richard.qin
 * @author lily.sun
 */
@Entity
@Table(name="PHX_DEPOSIT_INVOICE")
public class DepositInvoice {
    @Id
    @SequenceGenerator(name="DInvoice_seq_gen", sequenceName = "DEP_INVOICE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "DInvoice_seq_gen" )
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    private InvoiceType type;

    @Column(name = "JOB_CONTRACT_ID", length = 128, insertable = false, updatable = false)
    private Long jobContractId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "JOB_CONTRACT_ID")
    @Index(name = "PHX_DEPOSIT_INV_IDX_JC")
    private CEJobContract jobContract;

    @Column(name = "DEPOSIT_AMOUNT", precision = 38, scale = 4)
    private double depositAmount;
    // this deposit invoice may have been used, the availableAmount is the
    // balance
    @Column(name = "AVAILABLE_AMOUNT", precision = 38, scale = 4)
    private double availableAmount;

    @Column(name = "DEPOSIT_TYPE", length = 20)
    private DepositType depositType;
    @Column(name = "DEPOSIT_REFERENCE", length = 128)
    //TODO: length, type nvarchar?
    private String depositReference;

    @Column(name = "COMMENTS", columnDefinition = "NVARCHAR2(1024)")
    private String comment;

    // the Account and DeptId fields mentioned in the functional spec
    // Business Stream and Branch can be retrieved from the CEInvoiceLineItem object
    @Column(name = "ACCOUNT", length = 45)
    private String account;

    @Column(name = "DEP_ID", length = 10)
    private String deptId;

    @Column(name = "PAYMENT_TYPE", length = 20)
    private PaymentType paymentType;
    @Column(name = "DEPOSIT_PAID", length = 1)
    @org.hibernate.annotations.Type(type="yes_no")
    private boolean depositPaid;
    
    @Column(name = "INVOICE_NUMBER")
    private String invoiceNumber;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private InvoiceStatus status;

    @Column(name = "GENERATED_ON")
    private Timestamp generatedOn;
 
    @Column(name = "INVOICE_DATE")
    private Timestamp invoiceDate;

    @Column(name = "ACCOUNTING_DATE")
    private Timestamp accountingDate;

    @Column(name = "GENERATED_BY")
    private String generatedBy;

    @Column(name = "DESCRIPTION", columnDefinition = "NVARCHAR2(1024)")
    private String description;

    @Column(name = "PDF_GENERATED_FLAG", length = 1)
    @org.hibernate.annotations.Type(type = "yes_no")
    private boolean pdfGeneratedFlag;;

    @Column(name = "PYMT_TERMS_DESC", columnDefinition = "NVARCHAR2(50)") 
    private String paymentTermsDesc;
    
    @Column(name = "SENT_TO_FIN_FLAG", length = 1)
    @org.hibernate.annotations.Type(type = "yes_no")
    private boolean sentToFinFlag;

    @Column(name = "TRANSACT_CURRENCY_CD", length = 3)
    private String transactionCurrencyCd;
    
    @Column(name = "VAT_REGISTRATION_ID", length = 12)
    private String vatRegId;
    
    @Column(name="VAT_REGISTRATION_COUNTRY", length= 3)
    private String vatRegCountryCode;
    
    @Column(name = "TAX_VAT_FLAG", length = 1)
    @org.hibernate.annotations.Type(type = "yes_no")
    private boolean taxVatFlag;
    
    @Column(name="VAT_LABEL", length=10)
    private String vatLabel;

    @Column(name="VAT_REG_LABEL", length=10)
    private String vatRegLabel;
    
    @Column(name = "BILLING_CONTACT_ID", updatable = false, insertable = false)
    private Long billingContactId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "BILLING_CONTACT_ID")
    @Index(name = "DI_BILLING_CON")
    private Contact billingContact;

    @Column(name = "BILLING_ADDRESS_ID", updatable = false, insertable = false)
    private Long billingAddressId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.MERGE)
    @JoinColumn(name = "BILLING_ADDRESS_ID")
    @Index(name = "DI_BILLING_ADDR")
    private CustAddress billingAddress;

    @Column(name = "REPORT_RECEIVING_CONTACT_ID", updatable = false, insertable = false)
    private Long reportReceivingContactId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "REPORT_RECEIVING_CONTACT_ID")
    @Index(name = "DI_REP_REC_CON")
    private Contact reportReceivingContact;

    @Column(name = "REPORT_RECEIVING_ADDRESS_ID", updatable = false, insertable = false)
    private Long reportReceivingAddressId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.MERGE)
    @JoinColumn(name = "REPORT_RECEIVING_ADDRESS_ID")
    @Index(name = "DI_REP_REC_ADDR")
    private CustAddress reportReceivingAddress;
    
    @Column(name = "REMIT_TO_CODE", updatable = false, insertable = false)
    private String remitToCode;
    @ManyToOne()
    @org.hibernate.annotations.Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE })
    @JoinColumn(name = "REMIT_TO_CODE")
    private Bank remitTo;

    @Column(name = "REMIT_TO_BANK_ACCOUNT_NUM")
    private String remitToBankAccountNum;

    @Column(name = "INVOICE_FILE_NAME", length = 80)
    private String invoiceFileName;
    
    @OneToOne(mappedBy = "depositInvoice")
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
       org.hibernate.annotations.CascadeType.MERGE})
//    @Index(name="INVOICE_INDEX_FILE")
    private InvoiceFile invoiceFile;

    @Column(name = "NEW_FLAG", length = 8)
    private String newFlag;
    
    @Column(name = "UPDATE_FLAG", length = 8)
    private String updateFlag;
      
    
    @OneToMany(mappedBy = "depositInvoice", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    private Set<DepositInvoiceAmount> appliedDepositInvoiceAmounts;

    @Column(name = "SORT_NUMBER")
    private long sortNumber;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    
    public boolean isDepositPaid() {        
    	return depositPaid;
    }

    public void setDepositPaid(boolean depositPaid) {
        this.depositPaid = depositPaid;
    }

    /**
     * @param amount
     */
    public boolean useFund(double amount) {
        if (amount > this.availableAmount) {
            // can't do it, not enough fund
            return false;
        } else {
            availableAmount -= amount;
            return true;
        }
    }

    public CreditInvoice creditInvoice(Note note) {
        CreditInvoice ci = new CreditInvoice(this.getInvoiceNumber());
//        if (note != null) {
//            ci.setCreditInvoiceNote(note);
//        }
//        ci.setRelatedInvoice(this);
        ci.setType(InvoiceType.DEPOSIT);
        this.setStatus(InvoiceStatus.CREDITED);
        return ci;
    }

    /**
     * Determine if a deposit invoice can be credited.
     * <p/>
     * Only a deposit invoice that has been paid in full and has not been used
     * can be refunded / credited.
     *
     * @return
     */
    public boolean canCredit() {
        return this.getStatus() == InvoiceStatus.PAID
                && this.depositAmount == this.availableAmount;
    }


    public double getDepositAmount() {
        return depositAmount;
    }

    public void setDepositAmount(double depositAmount) {
        this.depositAmount = depositAmount;
    }

    public DepositType getDepositType() {
        return depositType;
    }

    public void setDepositType(DepositType depositType) {
        this.depositType = depositType;
    }

    public String getDepositReference() {
        return depositReference;
    }

    public void setDepositReference(String depositReference) {
        this.depositReference = depositReference;
    }

    public Set<DepositInvoiceAmount> getAppliedDepositInvoiceAmounts() {
        if (appliedDepositInvoiceAmounts == null) {
            appliedDepositInvoiceAmounts = new HashSet<DepositInvoiceAmount>();
        }
        return appliedDepositInvoiceAmounts;
    }

    public void addAppliedDepositInvoiceAmount(DepositInvoiceAmount amount) {
        if(amount != null){
            amount.setDepositInvoice(this);
        }
        getAppliedDepositInvoiceAmounts().add(amount);
    }

    public Long getJobContractId() {
        return this.jobContractId;
    }

    public void setJobNumber(Long jobContractId) {
        this.jobContractId = jobContractId;
    }

    public CEJobContract getJobContract() {
        return jobContract;
    }

    public void setJobContract(CEJobContract jobContract) {
        if(jobContract != null){
            this.jobContractId = jobContract.getId();
            this.jobContract = jobContract;
        }
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public double getAvailableAmount() {
        return availableAmount;
    }

    public void setAvailableAmount(double availableAmount) {
        this.availableAmount = availableAmount;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public InvoiceType getType() {
        return this.type;
    }

    public void setType(InvoiceType type) {
        this.type = type;
    }

    public long getSortNumber() {
        return sortNumber;
    }

    public void setSortNumber(long sortNumber) {
        this.sortNumber = sortNumber;
    }
    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public InvoiceFile getInvoiceFile() {
        return invoiceFile;
    }

    public void setInvoiceFile(InvoiceFile invoiceFile) {
        if(invoiceFile != null){
            this.invoiceFileName = invoiceFile.getInvoiceFileName();
            invoiceFile.setDepositInvoice(this);
        }
        this.invoiceFile = invoiceFile;
    }

    public InvoiceStatus getStatus() {
        return status;
    }


    public void setStatus(InvoiceStatus status) {
        this.status = status;
    }

    public Timestamp getGeneratedOn() {
        return generatedOn;
    }

    public void setGeneratedOn(Timestamp generatedOn) {
        this.generatedOn = generatedOn;
    }

    public Timestamp getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(Timestamp invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getGeneratedBy() {
        return generatedBy;
    }

    public void setGeneratedBy(String generatedBy) {
        this.generatedBy = generatedBy;
    }

    public Timestamp getAccountingDate() {
        return accountingDate;
    }

    public void setAccountingDate(Timestamp accountingDate) {
        this.accountingDate = accountingDate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isPdfGeneratedFlag() {
        return pdfGeneratedFlag;
    }

    public void setPdfGeneratedFlag(boolean pdfGeneratedFlag) {
        this.pdfGeneratedFlag = pdfGeneratedFlag;
    }

    public String getPaymentTermsDesc() {
        return paymentTermsDesc;
    }

    public void setPaymentTermsDesc(String paymentTermsDesc) {
        this.paymentTermsDesc = paymentTermsDesc;
    }

    public boolean isSentToFinFlag() {
        return sentToFinFlag;
    }

    public void setSentToFinFlag(boolean sentToFinFlag) {
        this.sentToFinFlag = sentToFinFlag;
    }

    public String getTransactionCurrencyCd() {
        return transactionCurrencyCd;
    }

    public void setTransactionCurrencyCd(String transactionCurrencyCd) {
        this.transactionCurrencyCd = transactionCurrencyCd;
    }

    public String getVatRegId() {
        return vatRegId;
    }

    public void setVatRegId(String vatRegId) {
        this.vatRegId = vatRegId;
    }

    public String getVatRegCountryCode() {
        return vatRegCountryCode;
    }

    public void setVatRegCountryCode(String vatRegCountryCode) {
        this.vatRegCountryCode = vatRegCountryCode;
    }

    public boolean isTaxVatFlag() {
        return taxVatFlag;
    }

    public void setTaxVatFlag(boolean taxVatFlag) {
        this.taxVatFlag = taxVatFlag;
    }

    public String getVatLabel() {
        return vatLabel;
    }

    public void setVatLabel(String vatLabel) {
        this.vatLabel = vatLabel;
    }

    public String getVatRegLabel() {
        return vatRegLabel;
    }

    public void setVatRegLabel(String vatRegLabel) {
        this.vatRegLabel = vatRegLabel;
    }

    public String getInvoiceFileName() {
        return invoiceFileName;
    }

    public void setInvoiceFileName(String invoiceFileName) {
        this.invoiceFileName = invoiceFileName;
    }

    public Long getBillingContactId() {
        return billingContactId;
    }

    public void setBillingContactId(Long billingContactId) {
        this.billingContactId = billingContactId;
    }

    public Contact getBillingContact() {
        return billingContact;
    }

    public void setBillingContact(Contact billingContact) {
        if (billingContact != null){
            this.billingContactId = billingContact.getId();
        }
        this.billingContact = billingContact;
    }

    public Long getBillingAddressId() {
        return billingAddressId;
    }

    public void setBillingAddressId(Long billingAddressId) {
        this.billingAddressId = billingAddressId;
    }

    public CustAddress getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(CustAddress billingAddress) {
        if (billingAddress != null){
            this.billingAddressId = billingAddress.getId();
        }
        this.billingAddress = billingAddress;
    }

    public Long getReportReceivingContactId() {
        return reportReceivingContactId;
    }

    public void setReportReceivingContactId(Long reportReceivingContactId) {
        this.reportReceivingContactId = reportReceivingContactId;
    }

    public Contact getReportReceivingContact() {
        return reportReceivingContact;
    }

    public void setReportReceivingContact(Contact reportReceivingContact) {
        if (reportReceivingContact != null){
            this.reportReceivingContactId = reportReceivingContact.getId();
        }
        this.reportReceivingContact = reportReceivingContact;
    }

    public Long getReportReceivingAddressId() {
        return reportReceivingAddressId;
    }

    public void setReportReceivingAddressId(Long reportReceivingAddressId) {
        this.reportReceivingAddressId = reportReceivingAddressId;
    }

    public CustAddress getReportReceivingAddress() {
        return reportReceivingAddress;
    }

    public void setReportReceivingAddress(CustAddress reportReceivingAddress) {
        if (reportReceivingAddress != null){
            this.reportReceivingAddressId = reportReceivingAddress.getId();
        }
        this.reportReceivingAddress = reportReceivingAddress;
    }

    public String getNewFlag() {
        return newFlag;
    }

    public void setNewFlag(String newFlag) {
        this.newFlag = newFlag;
    }

    public String getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(String updateFlag) {
        this.updateFlag = updateFlag;
    }
    
    public String getRemitToCode() {
        return remitToCode;
    }

    public void setRemitToCode(String remitToCode) {
        this.remitToCode = remitToCode;
    }

    public Bank getRemitTo() {
        return remitTo;
    }

    public void setRemitTo(Bank remitTo) {
        if (remitTo != null){
            this.remitToCode = remitTo.getBankCode();
        }
        this.remitTo = remitTo;
    }

    public String getRemitToBankAccountNum() {
        return remitToBankAccountNum;
    }

    public void setRemitToBankAccountNum(String remitToBankAccountNum) {
        this.remitToBankAccountNum = remitToBankAccountNum;
    }

}
