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
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
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
import javax.persistence.Transient;

import org.hibernate.annotations.Index;

import com.intertek.entity.Bank;
import com.intertek.entity.Contact;
import com.intertek.entity.CustAddress;

/**
 * CEInvoice encapsulates Invoice for CE.
 * 
 * @author richard.qin
 * @author lily.sun
 */
@Entity
@Table(name="PHX_INVOICE")
public class CEInvoice implements Collectable {
    @Id
    @SequenceGenerator(name="CEInvoice_seq_gen", sequenceName = "PHX_INVOICE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "CEInvoice_seq_gen" )
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "PS_INVOICE_NUMBER", length = 22)
    private String psInvoiceNumber;

    @Column(name = "TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    private InvoiceType type;

    @Column(name = "GENERATION_TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    private InvoiceGenerationType generationType;

    @Column(name = "PROJECT_NUMBER", length = 128, updatable = false, insertable = false)
    private String projectNumber;
    @ManyToOne
    @JoinColumn(name="PROJECT_NUMBER")
    @Index(name="PHX_INVOICE_INDEX_PROJECT")
    @org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Project project;

    @Column(name = "JOB_ORDER_CONTRACT_ID", updatable = false, insertable = false)
    private Long jobOrderContractId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "JOB_ORDER_CONTRACT_ID")
    @Index(name="PHX_INVOICE_IDX_CONTRACT")
    private CEJobContract jobContract;

    @Embedded  
    private Period invoicePeriod;

    @Column(name = "INV_AMT_PRE_TAX", precision = 38, scale = 4 )
    private double invAmtPreTax;
    
    @Column(name="INV_AMT_POST_TAX", precision = 38, scale = 4)
    private double invAmtPostTax;

    @Column (name = "TOTAL_TAX", precision = 38, scale = 4 )
    private double totalTax;

    @Column (name = "TOTAL_VAT", precision = 38, scale = 4 )
    private double totalVat;
    
    @Column(name = "BALANCE", precision = 38, scale = 4 )
    private double balance; // TODO
    
    @Column(name = "SALES_TAX_LABEL", length = 10)
    private String salesTaxLabel;
    
    @Column(name = "VAT_PROVINCE", length = 20)
    private String vatProvince;
    
    @Column(name = "BU_VAT_REGISTRATION_ID", length = 12)
    private String buVatRegId;
    
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
    @Index(name = "BILLING_CON")
    private Contact billingContact;

    @Column(name = "BILLING_ADDRESS_ID", updatable = false, insertable = false)
    private Long billingAddressId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.MERGE)
    @JoinColumn(name = "BILLING_ADDRESS_ID")
    @Index(name = "BILLING_ADDR")
    private CustAddress billingAddress;

    @Column(name = "REPORT_RECEIVING_CONTACT_ID", updatable = false, insertable = false)
    private Long reportReceivingContactId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "REPORT_RECEIVING_CONTACT_ID")
    @Index(name = "REP_REC_CON")
    private Contact reportReceivingContact;

    @Column(name = "REPORT_RECEIVING_ADDRESS_ID", updatable = false, insertable = false)
    private Long reportReceivingAddressId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.MERGE)
    @JoinColumn(name = "REPORT_RECEIVING_ADDRESS_ID")
    @Index(name = "REP_REC_ADDR")
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
    
    @OneToOne(mappedBy = "invoice")
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
       org.hibernate.annotations.CascadeType.MERGE})
//    @Index(name="INVOICE_INDEX_FILE")
    private InvoiceFile invoiceFile;

    @Column(name = "NEW_FLAG", length = 8)
    private String newFlag;
    
    @Column(name = "UPDATE_FLAG", length = 8)
    private String updateFlag;
    
    
    @OneToMany (mappedBy = "invoice", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    private Set<CEInvoiceLineItem> invoiceLineItems;

    @Transient
    private Set<DepositInvoice> depositInvoices;

    public CEInvoice() {
//        this.status = InvoiceStatus.NEW;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    public void calculateTax() {
        double temp = 0;
        for(CEInvoiceLineItem ili: this.getInvoiceLineItems()){
            temp += ili.getTax();
        }
        this.totalTax = temp;
    }

    public void calculateVat() {
        double temp = 0;
        for(CEInvoiceLineItem ili: this.getInvoiceLineItems()){
            temp += ili.getVat();
        }
        this.totalVat = temp;
    }

    public void calculatePreTaxAmount() {
        double temp = 0;
        double tempDepositAmt=0;
        for(CEInvoiceLineItem ili: this.getInvoiceLineItems()){
            temp += ili.getNetPrice();
        }       
        
        //logic for adjusting pre tax amount after applying deposit invoice
        for(CEInvoiceLineItem ili: this.getInvoiceLineItems()){
            if(ili.getDepositInvoiceAmounts()!=null&&!ili.getDepositInvoiceAmounts().isEmpty()){
                for(DepositInvoiceAmount dia:ili.getDepositInvoiceAmounts()){
                    tempDepositAmt+=dia.getAmount();
                }
            }
        }
        
        temp=temp-tempDepositAmt;
        this.invAmtPreTax = temp;
    }
    
    public void calculate(){
        calculatePreTaxAmount();
        calculateTax();
        calculateVat();
        //TODO verify the rounding issues
        invAmtPostTax = invAmtPreTax + totalTax + totalVat;
    }

    /**
     * Determine if a deposit invoice can be credited.
     * <p>
     * Only a paid invoice can be credited.
     * 
     * @return
     */
    public boolean canCredit() {
        // According to the functional spec, any generated invoice can be credited.
        return this.getType() != InvoiceType.DRAFT && this.getType() != InvoiceType.CREDIT;
    }

    /**
     * Credit this invoice
     * @param note
     * @return
     */
    public CreditInvoice creditInvoice(Note note){
        CreditInvoice ci = new CreditInvoice(this.getInvoiceNumber());
        //commented due to removing the relation ship in credit invoice for note.
//        if(note != null){
//            ci.setCreditInvoiceNote(note);
//        }
//        ci.setRelatedInvoice(this);
        ci.setType(InvoiceType.CREDIT);
        this.setStatus(InvoiceStatus.CREDITED);
        // TODO populate details here
        
        return ci;
    }

    /**
     * @return the type
     */
    public InvoiceType getType() {
        return type;
    }

    /**
     * @param type
     *            the type to set
     */
    public void setType(InvoiceType type) {
        this.type = type;
    }

    public Long getJobOrderContractId() {
        return jobOrderContractId;
    }

    public void setJobOrderContractId(Long jobOrderContractId) {
        this.jobOrderContractId = jobOrderContractId;
    }

    public CEJobContract getJobContract() {
        return jobContract;
    }

    public void setJobContract(CEJobContract jobContract) {
        if (jobContract != null){
            this.jobOrderContractId = jobContract.getId();
        }
        this.jobContract = jobContract;
    }

    /**
     * @return the projectId
     */
    public String getProjectNumber() {
        return projectNumber;
    }

    /**
     * @param projectNumber
     *            the projectId to set
     */
    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    /**
     * @return the project
     */
    public Project getProject() {
        return project;
    }

    /**
     * @param project
     *            the project to set
     */
    public void setProject(Project project) {
        if (project != null){
            this.projectNumber = project.getProjectNumber();
        }
        this.project = project;
    }

    /**
     * @return the invoicePeriod
     */
    public Period getInvoicePeriod() {
        return invoicePeriod;
    }

    /**
     * @param invoicePeriod
     *            the invoicePeriod to set
     */
    public void setInvoicePeriod(Period invoicePeriod) {
        this.invoicePeriod = invoicePeriod;
    }
    
    public double getInvAmtPreTax() {
        return invAmtPreTax;
    }

    public void setInvAmtPreTax(double invAmtPreTax) {
        this.invAmtPreTax = invAmtPreTax;
    }

    public double getInvAmtPostTax() {
        return invAmtPostTax;
    }

    public void setInvAmtPostTax(double invAmtPostTax) {
        this.invAmtPostTax = invAmtPostTax;
    }

    /**
     * @return the totalTax
     */
    public double getTotalTax() {
        return totalTax;
    }

    /**
     * @param totalTax
     *            the totalTax to set
     */
    public void setTotalTax(double totalTax) {
        this.totalTax = totalTax;
    }

    /**
     * @return the totalVat
     */
    public double getTotalVat() {
        return totalVat;
    }

    /**
     * @param totalVat
     *            the totalVat to set
     */
    public void setTotalVat(double totalVat) {
        this.totalVat = totalVat;
    }

    /**
     * @return the balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * @param balance
     *            the balance to set
     */
    public void setBalance(double balance) {
        this.balance = balance;
    }

    /**
     * @return the invoiceLineItems
     */
    public Set<CEInvoiceLineItem> getInvoiceLineItems() {
        if(invoiceLineItems == null){
            invoiceLineItems = new HashSet<CEInvoiceLineItem>();
        }
        return invoiceLineItems;
    }

    /**
     * @param invoiceLineItem
     *            the invoiceLineItem to set
     */
    public boolean addInvoiceLineItems(CEInvoiceLineItem invoiceLineItem) {
        invoiceLineItem.setInvoice(this);
        return getInvoiceLineItems().add(invoiceLineItem);
    }

    public boolean removeInvoiceLineItems(CEInvoiceLineItem invoiceLineItem) {
        return getInvoiceLineItems().remove(invoiceLineItem);
    }

    /**
     * @return the depositInvoices
     */
    public Set<DepositInvoice> getDepositInvoices() {
        if(depositInvoices == null){
            depositInvoices = new HashSet<DepositInvoice>();
        }
        return depositInvoices;
    }

    /**
     * @param depositInvoice
     *            the depositInvoices to set
     */
    public boolean addDepositInvoices(DepositInvoice depositInvoice) {
        return getDepositInvoices().add(depositInvoice);
    }

    public boolean removeDepositInvoices(DepositInvoice depositInvoice) {
        return getDepositInvoices().remove(depositInvoice);
    }

    /**
     * @param ili
     */
    public void addInvoiceLineItem(CEInvoiceLineItem ili) {
        if(ili != null){
            this.getInvoiceLineItems().add(ili);
            ili.setInvoice(this);
        }
    }

    /**
     * Remove all invoice line items from an invoice.
     * <p>It is up to the caller to determine if the invoice lines can be
     * removed, sucha as, only a newly created invoice can have its line 
     * items removed. Otherwise, an exception will be thrown.
     */
    public void removeInvoiceLines() {
        this.getInvoiceLineItems().clear();
    }

    public InvoiceGenerationType getGenerationType() {
        return generationType;
    }

    public void setGenerationType(InvoiceGenerationType generationType) {
        this.generationType = generationType;
    }

    /**
     * At the time of invoice generation, we need to remove all the invoice lines
     * that are not selected.
     * @return a list of removed InvoiceLineItem
     */
    public List<CEInvoiceLineItem> removeUnselectedLineItems() {
        List<CEInvoiceLineItem> removed = new ArrayList<CEInvoiceLineItem>();
        // To avoid the Concurrent Modification Exception, created new temporay
        // variable called temp.
        Set<CEInvoiceLineItem> temp = new HashSet<CEInvoiceLineItem>();
        temp.addAll(getInvoiceLineItems());
        for (CEInvoiceLineItem line : temp) {
            if (!line.isSelected()) {
                getInvoiceLineItems().remove(line);
                removed.add(line);
            }
        }
        return removed;
    }

	public String getPsInvoiceNumber() {
		return psInvoiceNumber;
	}

	public void setPsInvoiceNumber(String psInvoiceNumber) {
		this.psInvoiceNumber = psInvoiceNumber;
	}

    public String getSalesTaxLabel() {
        return salesTaxLabel;
    }

    public void setSalesTaxLabel(String salesTaxLabel) {
        this.salesTaxLabel = salesTaxLabel;
    }

    public String getVatProvince() {
        return vatProvince;
    }

    public void setVatProvince(String vatProvince) {
        this.vatProvince = vatProvince;
    }

    public String getBuVatRegId() {
        return buVatRegId;
    }

    public void setBuVatRegId(String buVatRegId) {
        this.buVatRegId = buVatRegId;
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
