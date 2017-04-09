/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 *
 */
package com.intertek.phoenix.entity;

import org.hibernate.annotations.Index;

import javax.persistence.*;

/**
 * InvoiceFile encapsulates a invoice file, typically a PDF file on a
 * file server.
 * <p/>
 * This InvoiceFile is a "lean" version of the old phoenix InvoiceFile.
 * At this time, it only contains fields that are most related to CEInvoice.
 * If other fields from the old phoenix InvoiceFile are needed, they will
 * be added accordingly.
 *
 * @author richard.qin
 * @author lily.sun
 */
@Entity(name = "invoiceFile_ce")
@Table(name = "PHX_INVOICE_FILE")
public class InvoiceFile {
    @Id
    @SequenceGenerator(name = "PHX_InvoiceFile_seq", sequenceName = "PHX_INVOICE_FILE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "PHX_InvoiceFile_seq")
    @Column(name = "ID")
    private Long id;

    @Column(name = "INVOICE_FILE_NAME", length = 80)
    private String invoiceFileName;

    @Column(name = "SERIAL_NUMBER", length = 80)
    private String serialNumber;

    //    @Column(name="INVOICE_TYPE", length = 20 )
    //    @Enumerated(EnumType.STRING)
    @Transient
    private InvoiceType invoiceType;

    @Column(name = "INVOICE_NUMBER", length = 80, updatable = false, insertable = false)
    private String invoiceNumber;
    @OneToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "INVOICE_NUMBER", referencedColumnName = "INVOICE_NUMBER", updatable = false, insertable = false)
    @Index(name = "PHX_INVOICE_FILE_IDX_INVOICE")
    private CEInvoice invoice;
    
    @OneToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "INVOICE_NUMBER", referencedColumnName = "INVOICE_NUMBER", updatable = false, insertable = false)
    @Index(name = "PHX_INVOICE_FILE_IDX_INVOICE")
    private DepositInvoice depositInvoice;
    
    @OneToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "INVOICE_NUMBER", referencedColumnName = "INVOICE_NUMBER", updatable = false, insertable = false)
    @Index(name = "PHX_INVOICE_FILE_IDX_INVOICE")
    private CreditInvoice creditInvoice;

    public InvoiceFile() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInvoiceFileName() {
        return invoiceFileName;
    }

    public void setInvoiceFileName(String invoiceFileName) {
        this.invoiceFileName = invoiceFileName;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

//    public Invoice getInvoice() {
//        return invoice;
//    }
//
//    public void setInvoice(Invoice invoice) {
//        if (invoice != null) {
//            this.invoiceNumber = invoice.getInvoiceNumber();
//            this.invoiceType = invoice.getType();
//        }
//        this.invoice = invoice;
//    }

    public InvoiceType getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(InvoiceType invoiceType) {
        this.invoiceType = invoiceType;
    }

    public CEInvoice getInvoice() {
        return invoice;
    }

    public void setInvoice(CEInvoice invoice) {
        this.invoice = invoice;
    }

    public DepositInvoice getDepositInvoice() {
        return depositInvoice;
    }

    public void setDepositInvoice(DepositInvoice depositInvoice) {
        if(depositInvoice != null){
            this.invoiceNumber = depositInvoice.getInvoiceNumber();
        }
        this.depositInvoice = depositInvoice;
    }

    public CreditInvoice getCreditInvoice() {
        return creditInvoice;
    }

    public void setCreditInvoice(CreditInvoice creditInvoice) {
        this.creditInvoice = creditInvoice;
    }

//    private InvoiceType getInvoiceType(Invoice invoice){
//        if(invoice instanceof CEInvoice){
//            CEInvoice ceInvoice = (CEInvoice)
//            return InvoiceType.NORMAL;
//        }
//        else if(invoice instanceof DepositInvoice){
//            return InvoiceType.DEPOSIT;
//        }
//        else if(invoice instanceof CreditInvoice){
//            return InvoiceType.CREDIT;
//        }
//        
//    }
//
}
