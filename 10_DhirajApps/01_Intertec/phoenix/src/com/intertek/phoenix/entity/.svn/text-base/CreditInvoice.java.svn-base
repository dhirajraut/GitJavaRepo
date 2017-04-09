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

import org.hibernate.annotations.Index;

import com.intertek.entity.Contact;
import com.intertek.entity.CustAddress;

import javax.persistence.*;

/**
 * 
 * @author richard.qin
 * @author lily.sun
 */
@Entity
@Table(name="PHX_CREDIT_INVOICE")
public class CreditInvoice {
    @Id
    @SequenceGenerator(name="CInvoice_seq_gen", sequenceName = "CRD_INVOICE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "CInvoice_seq_gen" )
    @Column(name = "ID")
    private Long id;
    
  //  @Column(name = "RELATED_INVOICE_NUMBER", updatable = false, insertable = false)
    @Column(name = "RELATED_INVOICE_NUMBER")
    private String relatedInvoiceNumber;
//    @ManyToOne()
//    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
//        org.hibernate.annotations.CascadeType.MERGE})
//    @JoinColumn(name="RELATED_INVOICE_NUMBER", referencedColumnName = "INVOICE_NUMBER")
//    @Index(name="CREDIT_INVOICE_INDEX_INVOICE")
//    private Invoice relatedInvoice;
    @Column(name="RELATED_INVOICE_TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    private InvoiceType type;
    
//    @Column (name = "NOTE_ID", updatable = false, insertable = false)
//    private Long noteId;
//    @ManyToOne()
//    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
//        org.hibernate.annotations.CascadeType.MERGE})
//    @JoinColumn(name="NOTE_ID")
//    @Index(name="CREDIT_INVOICE_INDEX_NOTE")
//    private Note creditInvoiceNote;

    @Column(name="CREDIT_REASON_NOTE", length=254)
    private String creditReasonNote; 
    @Column(name="CREDIT_REASON_USER_NAME", length=128)
    private String creditReasonUserName;

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
    @Index(name = "CR_BILLING_CON")
    private Contact billingContact;

    @Column(name = "BILLING_ADDRESS_ID", updatable = false, insertable = false)
    private Long billingAddressId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.MERGE)
    @JoinColumn(name = "BILLING_ADDRESS_ID")
    @Index(name = "CR_BILLING_ADDR")
    private CustAddress billingAddress;

    @Column(name = "REPORT_RECEIVING_CONTACT_ID", updatable = false, insertable = false)
    private Long reportReceivingContactId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "REPORT_RECEIVING_CONTACT_ID")
    @Index(name = "CR_REP_REC_CON")
    private Contact reportReceivingContact;

    @Column(name = "REPORT_RECEIVING_ADDRESS_ID", updatable = false, insertable = false)
    private Long reportReceivingAddressId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.MERGE)
    @JoinColumn(name = "REPORT_RECEIVING_ADDRESS_ID")
    @Index(name = "CR_REP_REC_ADDR")
    private CustAddress reportReceivingAddress;
    
    
    @Column(name = "INVOICE_FILE_NAME", length = 80)
    private String invoiceFileName;
    
    @OneToOne(mappedBy = "creditInvoice")
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
       org.hibernate.annotations.CascadeType.MERGE})
//    @Index(name="INVOICE_INDEX_FILE")
    private InvoiceFile invoiceFile;

    @Column(name = "NEW_FLAG", length = 8)
    private String newFlag;
    
    @Column(name = "UPDATE_FLAG", length = 8)
    private String updateFlag;
      
    
    public CreditInvoice(){
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
    
    /**
     * Create a CreditInvoice.
     * @param invoiceNumber The number of the invoice to be credited,
     * Can be either a normal invoice or a DepositInvoice.
     */
    public CreditInvoice(String invoiceNumber) {
        this.relatedInvoiceNumber =invoiceNumber;
    }

    /**
     * @param invoiceNumber
     * @param noteString
     */
//    public CreditInvoice(String invoiceNumber, String noteString) {
//        this(invoiceNumber);
//        if(noteString != null){
//            Note note = new Note(noteString, NoteType.COMMENT);
//            this.setCreditInvoiceNote(note);
//        }
//    }

    /**
     * @return the relatedInvoiceId
     */
    public String getRelatedInvoiceNumber() {
        return relatedInvoiceNumber;
    }

    /**
     * @param relatedInvoiceNumber the relatedInvoiceId to set
     */
    public void setRelatedInvoiceNumber(String relatedInvoiceNumber) {
        this.relatedInvoiceNumber = relatedInvoiceNumber;
    }

    /**
     * @return the creditInvoiceNote
     */
//    public Note getCreditInvoiceNote() {
//		return creditInvoiceNote;
//	}
//
//	/**
//	 * @param creditInvoiceNote the creditInvoiceNote to set
//	 */
//	public void setCreditInvoiceNote(Note creditInvoiceNote) {
//		this.creditInvoiceNote = creditInvoiceNote;
//	}
//
//    public Long getNoteId() {
//        return noteId;
//    }
//
//    public void setNoteId(Long noteId) {
//        this.noteId = noteId;
//    }

    public InvoiceType getType() {
        return this.type;
    }

//    public Invoice getRelatedInvoice() {
//        return relatedInvoice;
//    }
//
//    public void setRelatedInvoice(Invoice relatedInvoice) {
//        if(relatedInvoice != null){
//            this.relatedInvoiceNumber = relatedInvoice.getInvoiceNumber();
//        }
//        this.relatedInvoice = relatedInvoice;
//    }

    public void setType(InvoiceType type) {
        this.type = type;
    }

    public String getCreditReasonNote() {
        return creditReasonNote;
    }

    public void setCreditReasonNote(String creditReasonNote) {
        this.creditReasonNote = creditReasonNote;
    }

    public String getCreditReasonUserName() {
        return creditReasonUserName;
    }

    public void setCreditReasonUserName(String creditReasonUserName) {
        this.creditReasonUserName = creditReasonUserName;
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
}
