/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

/**
 * 
 * @author richard.qin
 */
@Entity
@Table(name = "PHX_DEPOSIT_INVOICE_AMOUNT")
//@IdClass(com.intertek.phoenix.entity.DepositInvoiceAmount.DepositInvoiceAmountId.class)
public class DepositInvoiceAmount {
//    @SuppressWarnings("serial")
//    public static class DepositInvoiceAmountId  implements Serializable{
//        private Long depositInvoiceId; // was String
//        private Long invoiceLineItemId;
//        
//        public DepositInvoiceAmountId(){
//            
//        }
//        
//        public DepositInvoiceAmountId(Long depositInvoiceId, Long invoiceLineItemId){
//            this.depositInvoiceId = depositInvoiceId;
//            this.invoiceLineItemId = invoiceLineItemId;
//        }
//
//        public Long getDepositInvoiceId() {
//            return depositInvoiceId;
//        }
//
//        public Long getInvoiceLineItemId() {
//            return invoiceLineItemId;
//        }
//        
//        @Override
//        public boolean equals(Object obj){
//            if(obj != null){
//                DepositInvoiceAmountId other = (DepositInvoiceAmountId)obj;
//                return other.depositInvoiceId == this.depositInvoiceId && 
//                       other.invoiceLineItemId == this.invoiceLineItemId;                    
//            }
//            return false;
//        }
//        
//        @Override
//        public int hashCode(){
//            int hash = 19;
////            hash = 37 * hash + (int)(this.depositInvoiceId.hashCode());
//            hash = 37 * hash + (int)(this.depositInvoiceId ^ this.depositInvoiceId >>> 32);
//            hash = 37 * hash + (int)(this.invoiceLineItemId ^ this.invoiceLineItemId >>> 32);
//            return hash;
//        }
//    }
    @Id
    @SequenceGenerator(name="DInv_Amt_seq_gen", sequenceName = "DEP_INV_AMT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "DInv_Amt_seq_gen" )
    @Column(name = "ID")
    private Long id;

//    @Id
    @Column(name = "DEPOSIT_INVOICE_ID", updatable = false, insertable = false)
    private Long depositInvoiceId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "DEPOSIT_INVOICE_ID")
    @Index(name = "DPST_INV_AMT_IDX_DI")
    private DepositInvoice depositInvoice;

//    @Id
    @Column(name = "INVOICE_LINE_ITEM_ID", updatable = false, insertable = false)
    private Long invoiceLineItemId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "INVOICE_LINE_ITEM_ID")
    @Index(name = "DPST_INV_AMT_IDX_IDX_ILI")
    private CEInvoiceLineItem invoiceLineItem;

    @Column(name="AMOUNT", precision = 38, scale = 4)
    private double amount;

    public DepositInvoiceAmount(){
    }

    
    public Long getId() {
        return id;
    }



    public void setId(Long id) {
        this.id = id;
    }



    public DepositInvoiceAmount(Long depositInvoiceId, Long invoiceLineItemId){
        this.invoiceLineItemId = invoiceLineItemId;
        this.depositInvoiceId = depositInvoiceId;
    }

    public Long getDepositInvoiceId() {
        return depositInvoiceId;
    }

    public void setDepositInvoiceId(Long depositInvoiceId) {
        this.depositInvoiceId = depositInvoiceId;
    }

    public Long getInvoiceLineItemId() {
        return invoiceLineItemId;
    }

    public void setInvoiceLineItemId(Long invoiceLineItemId) {
        this.invoiceLineItemId = invoiceLineItemId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public DepositInvoice getDepositInvoice() {
        return depositInvoice;
    }

    public void setDepositInvoice(DepositInvoice depositInvoice) {
        if(depositInvoice != null){
            this.depositInvoiceId = depositInvoice.getId();
        }
        this.depositInvoice = depositInvoice;
    }

    public CEInvoiceLineItem getInvoideLineItem() {
        return invoiceLineItem;
    }

    public void setInvoiceLineItem(CEInvoiceLineItem invoiceLineItem) {
        this.invoiceLineItem = invoiceLineItem;
        if(invoiceLineItem != null){
            this.invoiceLineItemId = invoiceLineItem.getId();
        }
    }
}
