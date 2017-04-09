/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 *
 */
package com.intertek.phoenix.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.intertek.entity.Bank;
import com.intertek.entity.BankAccount;
import com.intertek.entity.Contact;
import com.intertek.entity.Contract;
import com.intertek.entity.ContractCust;
import com.intertek.entity.CustAddress;
import com.intertek.entity.Customer;
import com.intertek.phoenix.job.ContractJobOrder;

/**
 * A convenient class that holds all the customer related information.
 * <p>
 * Note, the final relationship between this class and CEJobOrder will be
 * determined later.
 * 
 * @author richard.qin
 * @author lily.sun
 */
@Entity
@Table(name = "PHX_JOB_CONTRACT")
public class CEJobContract implements ContractJobOrder {
    @Id
    @SequenceGenerator(name = "PHX_JobContract_seq", sequenceName = "PHX_JOB_CONTRACT_SEQ",
                       allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="PHX_JobContract_seq")  
    @Column(name = "ID")
    private Long id;

    @Column(name = "JOB_ORDER_NUMBER", updatable = false, insertable = false)
    private String jobOrderNumber;
    @ManyToOne()
    @org.hibernate.annotations.Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE })
    @JoinColumn(name = "JOB_ORDER_NUMBER", referencedColumnName = "JOB_NUMBER")
    private CEJobOrder jobOrder;

    @Column(name = "CUSTOMER_CODE", updatable = false, insertable = false)
    private String customerCode;
    @ManyToOne()
    @org.hibernate.annotations.Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE })
    @JoinColumn(name = "CUSTOMER_CODE")
    private Customer customer;

    @Column(name = "CONTRACT_CODE", updatable = false, insertable = false)
    private String contractCode;
    @ManyToOne()
    @org.hibernate.annotations.Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE })
    @JoinColumn(name = "CONTRACT_CODE")
    @Index(name = "PHX_JOC_IDX_CONTRACT")
    private Contract contract;

    @Column(name = "BILLING_CUST_CODE", updatable = false, insertable = false)
    private String billingCustCode;
    @ManyToOne()
    @org.hibernate.annotations.Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE })
    @JoinColumn(name = "BILLING_CUST_CODE")
    private Customer billingCustomer;

    @Column(name = "REPORT_RECEIVING_CUST_CODE", updatable = false, insertable = false)
    private String reportReceivingCustCode;
    @ManyToOne()
    @org.hibernate.annotations.Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE })
    @JoinColumn(name = "REPORT_RECEIVING_CUST_CODE")
    private Customer reportReceivingCustomer;

    @Column(name = "SUPPLIER_CUST_CODE", updatable = false, insertable = false)
    private String supplierCustCode;
    @ManyToOne()
    @org.hibernate.annotations.Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE })
    @JoinColumn(name = "SUPPLIER_CUST_CODE")
    private Customer supplierCustomer;

    @Column(name = "MANF_CUST_CODE", updatable = false, insertable = false)
    private String manfCustCode;
    @ManyToOne()
    @org.hibernate.annotations.Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE })
    @JoinColumn(name = "MANF_CUST_CODE")
    private Customer manufacturerCustomer;

    @Column(name = "SOURCE_ORIGIN")
    @Enumerated(EnumType.STRING)
    private SourceOrigin sourceOrigin;

    @Column(name = "CONTACT_ID", updatable = false, insertable = false)
    private Long contactId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE })
    @JoinColumn(name = "CONTACT_ID")
    @Index(name = "PHX_JOC_IDX_CONTACT")
    private Contact contact;

    @Column(name = "CONTACT_ADDRESS_ID", updatable = false, insertable = false)
    private Long contactAddressId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.MERGE)
    @JoinColumn(name = "CONTACT_ADDRESS_ID")
    @Index(name = "PHX_JOC_IDX_CONTACT_ADDRESS")
    private CustAddress contactAddress;

    @Column(name = "REPORT_RECEIVING_CONTACT_ID", updatable = false, insertable = false)
    private Long reportReceivingContactId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE })
    @JoinColumn(name = "REPORT_RECEIVING_CONTACT_ID")
    @Index(name = "PHX_JOC_IDX_REP_REC_CONTACT")
    private Contact reportReceivingContact;

    @Column(name = "REPORT_RECEIVING_ADDRESS_ID", updatable = false, insertable = false)
    private Long reportReceivingAddressId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.MERGE)
    @JoinColumn(name = "REPORT_RECEIVING_ADDRESS_ID")
    @Index(name = "PHX_JOC_IDX_REP_REC_ADDRESS")
    private CustAddress reportReceivingAddress;

    @Column(name = "BILLING_CONTACT_ID", updatable = false, insertable = false)
    private Long billingContactId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE })
    @JoinColumn(name = "BILLING_CONTACT_ID")
    @Index(name = "PHX_JOC_IDX_BILLING_CONTACT")
    private Contact billingContact;

    @Column(name = "BILLING_ADDRESS_ID", updatable = false, insertable = false)
    private Long billingAddressId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.MERGE)
    @JoinColumn(name = "BILLING_ADDRESS_ID")
    @Index(name = "PHX_JOC_IDX_BILLING_ADDRESS")
    private CustAddress billingAddress;

    @Column(name = "SUPPLIER_CONTACT_ID", updatable = false, insertable = false)
    private Long supplierContactId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE })
    @JoinColumn(name = "SUPPLIER_CONTACT_ID")
    @Index(name = "PHX_JOC_IDX_SUPPLIER_CONTACT")
    private Contact supplierContact;

    @Column(name = "SUPPLIER_ADDRESS_ID", updatable = false, insertable = false)
    private Long supplierAddressId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.MERGE)
    @JoinColumn(name = "SUPPLIER_ADDRESS_ID")
    @Index(name = "PHX_JOC_IDX_SUPPLIER_ADDRESS")
    private CustAddress supplierAddress;

    @Column(name = "MANUFACTURER_CONTACT_ID", updatable = false, insertable = false)
    private Long manufacturerContactId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE })
    @JoinColumn(name = "MANUFACTURER_CONTACT_ID")
    @Index(name = "PHX_JOC_INDEX_MNF_CONTACT")
    private Contact manufacturerContact;

    @Column(name = "MANF_ADDRESS_ID", updatable = false, insertable = false)
    private Long manfAddressId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE })
    @JoinColumn(name = "MANF_ADDRESS_ID")
    @Index(name = "PHX_JOC_IDX_MNF_ADDRESS")
    private CustAddress manufacturerAddress;

    @Column(name = "PARENT_CUST_CODE", updatable = false, insertable = false)
    private String parentCustCode;
    
    @Column(name = "PARENT_CONTRACT_ID", updatable = false, insertable = false)
    private String parentContractCode;

    @ManyToOne()
    @org.hibernate.annotations.Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE })
    @JoinColumns( {@JoinColumn(name = "PARENT_CUST_CODE", referencedColumnName = "CUST_CODE"),
        @JoinColumn(name = "PARENT_CONTRACT_ID", referencedColumnName = "CONTRACT_CODE")})
    @Index(name = "PHX_JOC_IDX_CONTRACT_CODE")
    private ContractCust parentContractCust;

    @Column(name = "VAT_REG_ID", length = 128)
    private String vatRegId;

    @Column(name = "PO_NUMBER", length = 128)
    private String poNumber;

    @Column(name = "ZONE_ID", length = 128)
    private String zoneId;

    @Column(name = "ZONE_DESCRIPTION", columnDefinition = "NVARCHAR2(1024)")
    private String zoneDescription;

    @Column(name = "LANGUAGE", length = 64)
    private String language;

    @Column(name = "REMIT_TO_CODE", updatable = false, insertable = false)
    private String remitToCode;
    @ManyToOne()
    @org.hibernate.annotations.Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE })
    @JoinColumn(name = "REMIT_TO_CODE")
    private Bank remitTo;

    @Column(name = "REMIT_TO_BANK_ACCOUNT_BU_NAME", updatable = false, insertable = false)
    private String remitToBankAccountBuName;

    @Column(name = "REMIT_TO_BANK_CODE", updatable = false, insertable = false)
    private String remitToBankCode;

    @Column(name = "REMIT_TO_BANK_ACCOUNT_NUM", updatable = false, insertable = false)
    private String remitToBankAccountNum;

    @ManyToOne()
    @org.hibernate.annotations.Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE })
    @JoinColumns( { @JoinColumn(name = "REMIT_TO_BANK_ACCOUNT_BU_NAME", referencedColumnName = "BUSINESS_UNIT_NAME"),
                   @JoinColumn(name = "REMIT_TO_BANK_CODE", referencedColumnName = "BANK_CODE"),
                   @JoinColumn(name = "REMIT_TO_BANK_ACCOUNT_NUM", referencedColumnName = "BANK_ACCT_CODE") })
    private BankAccount remitToBankAccount;

    @Column(name = "PAYMENT_REFERENCE_NUM", length = 128)
    private String paymentReferenceNum;

    @Column(name = "PAYMENT_TYPE")
    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Column(name = "PAYMENT_TERMS", length = 128)
    private String paymentTerms;

    @Column(name = "PREPAYMENT_REQUIRED", length = 1)
    @org.hibernate.annotations.Type(type = "yes_no")
    private boolean prepaymentRequired;

    @Column(name = "CONTRACT_CURRENCY", length = 128)
    private String contractCurrency;
    
    @Column(name = "TRANSACTION_CURRENCY", length = 128)
    private String transactionCurrency;

    @Column(name = "INVOICE_LANGUAGE", length = 128)
    private String invoiceLanguage;
    
    @Column(name = "INVOICE_TYPE", length = 128)
    private String invoiceType;
    
    @Column(name = "INVOICE_DESCRIPTION", length = 180)
    private String invoiceDescription;

    @Column(name = "CUST_REF_NUM", length = 40)
    private String custRefNum;

    @Column(name = "INVOICE_LABEL1", length = 64)
    private String invoiceLabel1;

    @Column(name = "INVOICE_VALUE1", length = 64)
    private String invoiceValue1;

    @Column(name = "INVOICE_LABEL2", length = 64)
    private String invoiceLabel2;

    @Column(name = "INVOICE_VALUE2", length = 64)
    private String invoiceValue2;

    @Column(name = "INVOICE_LABEL3", length = 64)
    private String invoiceLabel3;

    @Column(name = "INVOICE_VALUE3", length = 64)
    private String invoiceValue3;

    @Column(name = "INVOICE_LABEL4", length = 64)
    private String invoiceLabel4;

    @Column(name = "INVOICE_VALUE4", length = 64)
    private String invoiceValue4;

    @Column(name = "INVOICE_LABEL5", length = 64)
    private String invoiceLabel5;

    @Column(name = "INVOICE_VALUE5", length = 64)
    private String invoiceValue5;

    @Column(name = "PRODUCT_TYPE", length = 20)
    private String productType;

    @Column(name = "PARENT_JOB_NUMBER", length = 128)
    private String parentJobNumber;

    @Column(name = "ROOT_SERVICE_LEVEL_ID", updatable = false, insertable = false)
    private Long rootServiceLevelId;
    @OneToOne()
    @org.hibernate.annotations.Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE,
                                         org.hibernate.annotations.CascadeType.DELETE_ORPHAN })
    @JoinColumn(name = "ROOT_SERVICE_LEVEL_ID")
    @Index(name = "PHX_JOC_IDX_ROOT_SRVC_LVL")
    private ContractServiceLevel rootServiceLevel;

    @OneToMany(mappedBy = "jobContract", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE })
    private Set<CEJobOrderLineItem> jobOrderLineItems;

    @OneToMany(mappedBy = "jobContract", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE })
    private Set<BillingEvent> billingEvents;

    @OneToMany(mappedBy = "jobContract", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE })
    private Set<JobOrderNote> notes;

    @OneToMany(mappedBy = "jobContract", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE })
    private Set<JobOrderAttachment> attachments;

    @OneToMany(mappedBy = "jobContract", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE })
    private Set<CEInvoice> ceInvoices;

    @OneToMany(mappedBy = "jobContract", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE })
    private Set<DepositInvoice> depositInvoices;
    
    @OneToMany(mappedBy = "jobContract", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE })
    private Set<SampleTracking> sampleTrackings;

    @Column(name = "VAT_REGISTRATION_COUNTRY", length = 3)
    private String vatRegCountry;

    public CEJobContract() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CEJobOrder getJobOrder() {
        return jobOrder;
    }

    public void setJobOrder(CEJobOrder jobOrder) {
        if (jobOrder != null) {
            this.jobOrderNumber = jobOrder.getJobNumber();
        }
        this.jobOrder = jobOrder;
    }

    public String getJobOrderNumber() {
        return jobOrderNumber;
    }

    public void setJobOrderNumber(String jobOrderNumber) {
        this.jobOrderNumber = jobOrderNumber;
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        if (customer != null) {
            this.customerCode = customer.getCustCode();
        }
        this.customer = customer;
    }

    public String getContractCode() {
        return contractCode;
    }

    public void setContractCode(String contractCode) {
        this.contractCode = contractCode;
    }

    public Contract getContract() {
        return contract;
    }

    public void setContract(Contract contract) {
        if (contract != null) {
            this.contractCode = contract.getContractCode();
        }
        this.contract = contract;
    }

    public SourceOrigin getSourceOrigin() {
        return sourceOrigin;
    }

    public void setSourceOrigin(SourceOrigin sourceOrigin) {
        this.sourceOrigin = sourceOrigin;
    }

    public Long getContactId() {
        return contactId;
    }

    public void setContactId(Long contactId) {
        this.contactId = contactId;
    }

    public Contact getContact() {
        return contact;
    }

    public void setContact(Contact contact) {
        if (contact != null) {
            this.contactId = contact.getId();
        }
        this.contact = contact;
    }

    public CustAddress getContactAddress() {
        return contactAddress;
    }

    public void setContactAddress(CustAddress contactAddress) {
        if (contactAddress != null) {
            this.contactAddressId = contactAddress.getId();
        }
        this.contactAddress = contactAddress;
    }

    public boolean isreportReceivingCustomerDifferent() {
        return isCustomerDifferent(getCustomer(), getReportReceivingCustomer());
    }

    private boolean isCustomerDifferent(Customer cust1, Customer cust2) {
        if (cust1 == null || cust2 == null) {
            return false;
        }
        return !cust1.getCustCode().trim().equalsIgnoreCase(cust2.getCustCode().trim());
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public boolean isBillingCustomerDifferent() {
        return isCustomerDifferent(getCustomer(), getBillingCustomer());
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
        if (billingContact != null) {
            this.billingContactId = billingContact.getId();
        }
        this.billingContact = billingContact;
    }

    public CustAddress getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(CustAddress billingAddress) {
        if (billingAddress != null) {
            this.billingAddressId = billingAddress.getId();
        }
        this.billingAddress = billingAddress;
    }

    public Long getManufacturerContactId() {
        return manufacturerContactId;
    }

    public void setManufacturerContactId(Long manufacturerContactId) {
        this.manufacturerContactId = manufacturerContactId;
    }

    public Contact getManufacturerContact() {
        return manufacturerContact;
    }

    public void setManufacturerContact(Contact manufacturerContact) {
        if (manufacturerContact != null) {
            this.manufacturerContactId = manufacturerContact.getId();
        }
        this.manufacturerContact = manufacturerContact;
    }

    public CustAddress getManufacturerAddress() {
        return manufacturerAddress;
    }

    public void setManufacturerAddress(CustAddress manufacturerAddress) {
        if (manufacturerAddress != null) {
            this.manfAddressId = manufacturerAddress.getId();
        }
        this.manufacturerAddress = manufacturerAddress;
    }

    public String getVatRegId() {
        return vatRegId;
    }

    public void setVatRegId(String vatRegId) {
        this.vatRegId = vatRegId;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
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
        if (remitTo != null) {
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

    public String getRemitToBankAccountBuName() {
        return remitToBankAccountBuName;
    }

    public void setRemitToBankAccountBuName(String remitToBankAccountBuName) {
        this.remitToBankAccountBuName = remitToBankAccountBuName;
    }

    public String getRemitToBankCode() {
        return remitToBankCode;
    }

    public void setRemitToBankCode(String remitToBankCode) {
        this.remitToBankCode = remitToBankCode;
    }

    public BankAccount getRemitToBankAccount() {
        return remitToBankAccount;
    }

    public void setRemitToBankAccount(BankAccount remitToBankAccount) {
        if (remitToBankAccount != null) {
            this.remitToBankAccountBuName = remitToBankAccount.getBankAccountId().getBusinessUnitName();
            this.remitToBankAccountNum = remitToBankAccount.getBankAccountId().getBankAcctCode();
            this.remitToBankCode = remitToBankAccount.getBankAccountId().getBankCode();
        }
        this.remitToBankAccount = remitToBankAccount;
    }

    public String getPaymentReferenceNum() {
        return paymentReferenceNum;
    }

    public void setPaymentReferenceNum(String paymentReferenceNum) {
        this.paymentReferenceNum = paymentReferenceNum;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public boolean isPrepaymentRequired() {
        return prepaymentRequired;
    }

    public void setPrepaymentRequired(boolean prepaymentRequired) {
        this.prepaymentRequired = prepaymentRequired;
    }

    public String getContractCurrency() {
        return contractCurrency;
    }

    public void setContractCurrency(String contractCurrency) {
        this.contractCurrency = contractCurrency;
    }

    public String getTransactionCurrency() {
        return transactionCurrency;
    }

    public void setTransactionCurrency(String transactionCurrency) {
        this.transactionCurrency = transactionCurrency;
    }

    public String getInvoiceLanguage() {
        return invoiceLanguage;
    }

    public void setInvoiceLanguage(String invoiceLanguage) {
        this.invoiceLanguage = invoiceLanguage;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public String getInvoiceDescription() {
        return invoiceDescription;
    }

    public void setInvoiceDescription(String invoiceDescription) {
        this.invoiceDescription = invoiceDescription;
    }

    public Set<JobOrderNote> getNotes() {
        if (notes == null) {
            notes = new HashSet<JobOrderNote>();
        }
        return notes;
    }

    public boolean addNote(JobOrderNote note) {
        return getNotes().add(note);
    }

    public boolean removeNote(Note note) {
        return getNotes().remove(note);
    }

    //    public void setNotes(Set<JobOrderNote> notes) {
    //        this.notes = notes;
    //    }
    //
    public Set<JobOrderAttachment> getAttachments() {
        if (attachments == null) {
            attachments = new HashSet<JobOrderAttachment>();
        }
        return attachments;
    }

    //    public void setAttachments(Set<JobOrderAttachment> attachments) {
    //        this.attachments = attachments;
    //    }
    //
    public boolean addAttachment(JobOrderAttachment attachment) {
        return getAttachments().add(attachment);
    }

    public boolean removeAttachment(Attachment attachment) {
        return getAttachments().remove(attachment);
    }

    public String getZoneId() {
        return zoneId;
    }

    public Long getContactAddressId() {
        return contactAddressId;
    }

    public void setContactAddressId(Long contactAddressId) {
        this.contactAddressId = contactAddressId;
    }

    public Long getBillingAddressId() {
        return billingAddressId;
    }

    public void setBillingAddressId(Long billingAddressId) {
        this.billingAddressId = billingAddressId;
    }

    public Long getManfAddressId() {
        return manfAddressId;
    }

    public void setManfAddressId(Long manfAddressId) {
        this.manfAddressId = manfAddressId;
    }

    public void setZoneId(String zoneId) {
        this.zoneId = zoneId;
    }

    public String getZoneDescription() {
        return zoneDescription;
    }

    public void setZoneDescription(String zoneDescription) {
        this.zoneDescription = zoneDescription;
    }

    public String getBillingCustCode() {
        return billingCustCode;
    }

    public void setBillingCustCode(String billingCustCode) {
        this.billingCustCode = billingCustCode;
    }

    public Customer getBillingCustomer() {
        return billingCustomer;
    }

    public void setBillingCustomer(Customer billingCustomer) {
        if (billingCustomer != null) {
            this.billingCustCode = billingCustomer.getCustCode();
        }
        this.billingCustomer = billingCustomer;
    }

    public String getManfCustCode() {
        return manfCustCode;
    }

    public void setManfCustCode(String manufacturerCustCode) {
        this.manfCustCode = manufacturerCustCode;
    }

    public Customer getManufacturerCustomer() {
        return manufacturerCustomer;
    }

    public void setManufacturerCustomer(Customer manufacturerCustomer) {
        if (manufacturerCustomer != null) {
            this.manfCustCode = manufacturerCustomer.getCustCode();
        }
        this.manufacturerCustomer = manufacturerCustomer;
    }

    public Set<JobOrderNote> removeNotes(JobOrderNote... notes) {
        Set<JobOrderNote> removed = new HashSet<JobOrderNote>();
        if (notes == null) {
            return removed;
        }

        Set<JobOrderNote> myNotes = this.getNotes();
        // removing all the notes
        for (JobOrderNote note : this.getNotes()) {
            if (myNotes.remove(note)) {
                removed.add(note);
                note.setJobContract(null);
            }
        }
        return removed;
    }

    public Set<CEJobOrderLineItem> removeCEJobOrderLineItem(CEJobOrderLineItem... lineItems) {
        Set<CEJobOrderLineItem> removed = new HashSet<CEJobOrderLineItem>();
        if (lineItems == null) {
            return removed;
        }

        if (lineItems != null) {
            Set<CEJobOrderLineItem> lis = this.getJobOrderLineItems();
            // removing line items
            for (CEJobOrderLineItem li : lineItems) {
                if (lis.remove(li)) {
                    li.setJobContract(null);
                    removed.add(li);
                }
            }
        }
        return removed;
    }

    /**
     * Add a list of new line items to the job order. If the line items is
     * already associated with some othe job order, some undesirable side
     * effects may happen. <p/> There is a danger here, if this.lineItems is not
     * populated at this point, but later hibernate populates it, the new items
     * not yet saved to the database will not be part of the list and become
     * "invisible"! Therefore, the caller is responsible for making sure that
     * the lineItem list is populated before calling this method. This
     * "out-of-synch" problem can be common to many other entity objects.
     * 
     * @param lineItems
     */
    public void addLineItems(List<CEJobOrderLineItem> lineItems) {
        // when lazy loading is enabled, the next line will have the joli list
        // populated
        Set<CEJobOrderLineItem> list = this.getJobOrderLineItems();
        for (CEJobOrderLineItem joli : lineItems) {
            joli.setJobContract(this);
            list.add(joli);
        }
    }

    public void addLineItems(CEJobOrderLineItem... lineItems) {
        // when lazy loading is enabled, the next line will have the joli list
        // populated
        Set<CEJobOrderLineItem> list = this.getJobOrderLineItems();
        for (CEJobOrderLineItem joli : lineItems) {
            joli.setJobContract(this);
            list.add(joli);
        }
    }

    /**
     * Retrieve a list of job order items that are currently billable
     */
    public List<CEJobOrderLineItem> getBillableJobOrderLineItems() {
        List<CEJobOrderLineItem> billableItems = new ArrayList<CEJobOrderLineItem>();
        for (CEJobOrderLineItem joli : this.getJobOrderLineItems()) {
            if (joli.isBillable()) {
                billableItems.add(joli);
            }
        }
        return billableItems;
    }

    /** 
     * Get the JOLIs related to the given billingEvent
     * 
     * @param billingEvent
     * @return
     */
    public Set<CEJobOrderLineItem> getJobOrderLineItems(BillingEvent billingEvent) {
        Set<CEJobOrderLineItem> result = new HashSet<CEJobOrderLineItem>();
        String batchNumber = billingEvent.getPsInvoiceNumber();
        for (CEJobOrderLineItem joli : getJobOrderLineItems()) {
            if (joli.getBatchNumber().equals(batchNumber)) {
                result.add(joli);
            }
        }
        return result;
    }

    public Set<CEJobOrderLineItem> getJobOrderLineItems() {
        if (jobOrderLineItems == null) {
            jobOrderLineItems = new HashSet<CEJobOrderLineItem>();
        }
        return jobOrderLineItems;
    }

    public boolean addJobOrderLineItem(CEJobOrderLineItem jobOrderLineItem) {
        if (jobOrderLineItem != null) {
            jobOrderLineItem.setJobContract(this);
        }
        return getJobOrderLineItems().add(jobOrderLineItem);
    }

    public boolean removeJobOrderLineItem(CEJobOrderLineItem lineItem) {
        return getJobOrderLineItems().remove(lineItem);
    }

    // Important:
    // This treatment is needed here, because rootServiceLevel is created
    // at the same time as CEJobContract itself, but rootServiceLevel is not
    // assigned at this time. This practice is safe, because 1) once the id
    // value is generated, it will never change. 2) for the two ways to 
    // change object association changing object association by setting object
    // will updates the foreign key field automatically; changing association
    // by setting foreign key id will have the object reference updated
    // "automatically" by ControllerUtil. Need a wiki page to explain the
    // details. -- RQ
    public Long getRootServiceLevelId() {
        if (rootServiceLevelId == null && rootServiceLevel != null) {
            rootServiceLevelId = rootServiceLevel.getId();
        }
        return rootServiceLevelId;
    }

    public void setRootServiceLevelId(Long rootServiceLevelId) {
        this.rootServiceLevelId = rootServiceLevelId;
    }

    public ContractServiceLevel getRootServiceLevel() {
        return rootServiceLevel;
    }

    public void setRootServiceLevel(ContractServiceLevel rootServiceLevel) {
        if (rootServiceLevel != null) {
            this.rootServiceLevelId = rootServiceLevel.getId();
            rootServiceLevel.setJobContract(this);
        }
        this.rootServiceLevel = rootServiceLevel;
    }

    /**
     * @see com.intertek.phoenix.job.ContractJobOrder#getContractCurrencyCode()
     */
    @Override
    public String getContractCurrencyCode() {
        return this.getContractCurrency();
    }

    /**
     * @see com.intertek.phoenix.job.ContractJobOrder#getLanguage()
     */
    @Override
    public String getLanguage() {
        return this.language;
    }

    /**
     * @see com.intertek.phoenix.job.ContractJobOrder#getOverrideCurrencyRate()
     */
    @Override
    public Double getOverrideCurrencyRate() {
        // TODO where to get this?
        return null;
    }

    /**
     * @see com.intertek.phoenix.job.ContractJobOrder#getTransactionCurrencyCode()
     */
    @Override
    public String getTransactionCurrencyCode() {
        return this.getTransactionCurrency();
    }

    /**
     * @see com.intertek.phoenix.job.ContractJobOrder#getZone()
     */
    @Override
    public String getZone() {
        if (this.getZoneId() == null) {
            return "*"; // this is not good TODO
        }
        else {
            return this.getZoneId(); // TODO check this
        }
    }

    public String getCustRefNum() {
        return this.custRefNum;
    }

    public void setCustRefNum(String custRefNum) {
        this.custRefNum = custRefNum;
    }

    public String getProductType() {
        return this.productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getInvoiceLabel1() {
        return this.invoiceLabel1;
    }

    public void setInvoiceLabel1(String invoiceLabel1) {
        this.invoiceLabel1 = invoiceLabel1;
    }

    public String getInvoiceValue1() {
        return this.invoiceValue1;
    }

    public void setInvoiceValue1(String invoiceValue1) {
        this.invoiceValue1 = invoiceValue1;
    }

    public String getInvoiceLabel2() {
        return this.invoiceLabel2;
    }

    public void setInvoiceLabel2(String invoiceLabel2) {
        this.invoiceLabel2 = invoiceLabel2;
    }

    public String getInvoiceValue2() {
        return this.invoiceValue2;
    }

    public void setInvoiceValue2(String invoiceValue2) {
        this.invoiceValue2 = invoiceValue2;
    }

    public String getInvoiceLabel3() {
        return this.invoiceLabel3;
    }

    public void setInvoiceLabel3(String invoiceLabel3) {
        this.invoiceLabel3 = invoiceLabel3;
    }

    public String getInvoiceValue3() {
        return this.invoiceValue3;
    }

    public void setInvoiceValue3(String invoiceValue3) {
        this.invoiceValue3 = invoiceValue3;
    }

    public String getInvoiceLabel4() {
        return this.invoiceLabel4;
    }

    public void setInvoiceLabel4(String invoiceLabel4) {
        this.invoiceLabel4 = invoiceLabel4;
    }

    public String getInvoiceValue4() {
        return this.invoiceValue4;
    }

    public void setInvoiceValue4(String invoiceValue4) {
        this.invoiceValue4 = invoiceValue4;
    }

    public String getInvoiceLabel5() {
        return this.invoiceLabel5;
    }

    public void setInvoiceLabel5(String invoiceLabel5) {
        this.invoiceLabel5 = invoiceLabel5;
    }

    public String getInvoiceValue5() {
        return this.invoiceValue5;
    }

    public void setInvoiceValue5(String invoiceValue5) {
        this.invoiceValue5 = invoiceValue5;
    }

    public String getParentJobNumber() {
        return parentJobNumber;
    }

    public void setParentJobNumber(String parentJobNumber) {
        this.parentJobNumber = parentJobNumber;
    }

    public Long getSupplierContactId() {
        return supplierContactId;
    }

    public void setSupplierContactId(Long supplierContactId) {
        this.supplierContactId = supplierContactId;
    }

    public Contact getSupplierContact() {
        return supplierContact;
    }

    public void setSupplierContact(Contact supplierContact) {
        if (supplierContact != null) {
            this.supplierContactId = supplierContact.getId();
        }
        this.supplierContact = supplierContact;
    }

    public Long getSupplierAddressId() {
        return supplierAddressId;
    }

    public void setSupplierAddressId(Long supplierAddressId) {
        this.supplierAddressId = supplierAddressId;
    }

    public CustAddress getSupplierAddress() {
        return supplierAddress;
    }

    public void setSupplierAddress(CustAddress supplierAddress) {
        if (supplierAddress != null) {
            this.supplierAddressId = supplierAddress.getId();
        }
        this.supplierAddress = supplierAddress;
    }

    public String getSupplierCustCode() {
        return supplierCustCode;
    }

    public void setSupplierCustCode(String supplierCustCode) {
        this.supplierCustCode = supplierCustCode;
    }

    public Customer getSupplierCustomer() {
        return supplierCustomer;
    }

    public void setSupplierCustomer(Customer supplierCustomer) {
        if (supplierCustomer != null) {
            this.supplierCustCode = supplierCustomer.getCustCode();
        }
        this.supplierCustomer = supplierCustomer;
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
        if (reportReceivingContact != null) {
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
        if (reportReceivingAddress != null) {
            reportReceivingAddressId = reportReceivingAddress.getId();
        }
        this.reportReceivingAddress = reportReceivingAddress;
    }

    public String getReportReceivingCustCode() {
        return reportReceivingCustCode;
    }

    public void setReportReceivingCustCode(String reportReceivingCustCode) {
        this.reportReceivingCustCode = reportReceivingCustCode;
    }

    public Customer getReportReceivingCustomer() {
        return reportReceivingCustomer;
    }

    public void setReportReceivingCustomer(Customer reportReceivingCustomer) {
        if (reportReceivingCustomer != null) {
            this.reportReceivingCustCode = reportReceivingCustomer.getCustCode();
        }
        this.reportReceivingCustomer = reportReceivingCustomer;
    }

    public Set<CEInvoice> getCeInvoices() {
        if (ceInvoices == null) {
            ceInvoices = new HashSet<CEInvoice>();
        }
        return ceInvoices;
    }

    public boolean addCEInvoice(CEInvoice invoice) {
        return getCeInvoices().add(invoice);
    }

    public boolean removeCEInvoice(CEInvoice invoice) {
        return getCeInvoices().remove(invoice);
    }

    public List<ServiceOffering> getServiceOfferings() {
        Set<ServiceOffering> result = new HashSet<ServiceOffering>();
        for (CEJobOrderLineItem jobOrderLineItem : getJobOrderLineItems()) {
            ServiceOffering serviceOffering = jobOrderLineItem.getServiceOffering();
            if (serviceOffering != null) {
                result.add(serviceOffering);
            }
        }
        ArrayList<ServiceOffering> arrayList = new ArrayList<ServiceOffering>();
        arrayList.addAll(result);
        return arrayList;
    }

    public Set<BillingEvent> getBillingEvents() {
        if (billingEvents == null) {
            billingEvents = new HashSet<BillingEvent>();
        }
        return billingEvents;
    }

    public void addBillingEvents(BillingEvent billingEvent) {
        if (billingEvent != null) {
            billingEvent.setJobContract(this);
            this.getBillingEvents().add(billingEvent);
        }
    }

    /**
     * @param temporaryInvoiceNumber
     * @return
     */
    public BillingEvent getBillingEvent(String batchNumber) {
        for (BillingEvent be : this.getBillingEvents()) {
            if (be.getPsInvoiceNumber().equals(batchNumber)) {
                return be;
            }
        }
        return null;
    }

    public Set<DepositInvoice> getDepositInvoices() {
        if (depositInvoices == null) {
            depositInvoices = new HashSet<DepositInvoice>();
        }
        return depositInvoices;
    }

    public boolean addDepositInvoice(DepositInvoice depositInvoice) {
        if (depositInvoice != null) {
            depositInvoice.setJobContract(this);
            return getDepositInvoices().add(depositInvoice);
        }
        return false;
    }

    public boolean removeDepositInvoice(DepositInvoice depositInvoice) {
        return getDepositInvoices().remove(depositInvoice);
    }

    public String getVatRegCountry() {
        return vatRegCountry;
    }

    public void setVatRegCountry(String vatRegCountry) {
        this.vatRegCountry = vatRegCountry;
    }

    public String getParentContractCode() {
        return parentContractCode;
    }

    public String getParentCustCode() {
        return parentCustCode;
    }

    public void setParentCustCode(String parentCustCode) {
        this.parentCustCode = parentCustCode;
    }

    public void setParentContractCode(String parentContractCode) {
        this.parentContractCode = parentContractCode;
    }

    public ContractCust getParentContractCust() {
        return parentContractCust;
    }

    public void setParentContractCust(ContractCust parentContractCust) {
        this.parentContractCust = parentContractCust;
    }

    public Set<SampleTracking> getSampleTrackings() {
        if(sampleTrackings == null){
            sampleTrackings = new HashSet<SampleTracking>();
        }
        return sampleTrackings;
    }

    public boolean addSampleTracking(SampleTracking sampleTracking){
        return getSampleTrackings().add(sampleTracking);
    }
    
    public boolean removeSampleTracking(SampleTracking sampleTracking){
        return getSampleTrackings().remove(sampleTracking);
    }
}
