/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 *
 * @Date : 4/22/2009
 * @Author : The Phoenix Team
 */

package com.intertek.phoenix.entity;

import javax.persistence.*;

import com.intertek.entity.Branch;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.Index;

/**
 * @author richard.qin
 */
@Entity
@Table(name = "PHX_INVOICE_LINE_ITEM")
public class CEInvoiceLineItem {
    @Id
    @SequenceGenerator(name="CEInvoiceLineItem_seq", sequenceName = "PHX_ILI_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "CEInvoiceLineItem_seq" )
    private Long id;

    @Column(name="INVOICE_ID", updatable = false, insertable = false)
    private Long invoiceId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "INVOICE_ID")
    @Index(name="PHX_ILI_IDX_INVOICE")
    private CEInvoice invoice;

    @Column(name="PHX_JOB_ORDER_LINE_ITEM_ID", updatable = false, insertable = false)
    private Long cEJobOrderLineItemId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "PHX_JOB_ORDER_LINE_ITEM_ID")
    @Index(name="PHX_ILI_IDX_OLI")
    private CEJobOrderLineItem cEJobOrderLineItem;

    @Column(name = "ACCOUNT_CODE", length=45)
    private String accountCode;

    @Column(name = "UNIT_PRICE", precision=38, scale= 4)
    private double unitPrice;

    @Column(name = "QUANTITY", precision=8, scale=2)
    private double quantity;

    @Column(name = "NET_PRICE", precision=38, scale=4 )
    private double netPrice;

    @Column(name = "TAX_CODE", length=8 )
    private String taxCode;

    @Column(name = "TAX_PCT", precision=38, scale=4)
    private double taxPct;

    @Column(name = "TAX", precision=38, scale=5)
    private double tax;

    @Column(name = "TAX_AMT", precision = 38, scale=5)
    private Double taxAmt;

    @Column(name = "VAT_CODE", length = 8)
    private String vatCode;

    @Column(name = "VAT_PCT", precision = 38, scale = 4)
    private double vatPct;

    @Column(name = "VAT", precision = 38, scale = 5)
    private double vat;

    @Column(name = "VAT_AMT", precision = 38, scale = 5)
    private Double vatAmt;
    // do we need this?
    // private double totalAmount;
    // the portion that has been paid for by deposit invoices
//    @Column(name = "SECURED_AMOUNT", precision = 38, scale=3)
//    private double securedAmount;

    @Column(name = "BRANCH_NAME", length = 8, updatable = false, insertable = false)
    private String branchName;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "BRANCH_NAME")
    @Index(name="PHX_ILI_IDX_BRANCH")
    private Branch branch;

    @Column(name = "DEP_ID", length=10)
    private String depId; // part of GL account codes

    @Column(name = "ACCOUNT", length=45)
    private String account; // part of GL account codes

    @OneToMany(mappedBy = "invoiceLineItem", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    private Set<DepositInvoiceAmount> depositInvoiceAmounts;
    
    // This flag marks whether an invoice line item is selected to be invoiced.
    // If the isSelected flag is true, then when the invoice is generated,
    // this InvoiceLineItem will be included. If the isSelected flag is false,
    // then this InvoiceLineItem will not be included in the final invoice.
    // <p>
    // This flag gives the user an opportunity to play with invoice generation
    // on the preview page without the danger of losing information. Once
    // the invoice is finalized, all unselected line items will be removed, and
    // this flag serves no use.
    @Column(name = "IS_SELECTED", length = 1 )
    @org.hibernate.annotations.Type(type="yes_no")
    private boolean selected;

    @Column(name = "TAX_ARTICLE_CODE", length=10)
    private String taxArticleCode;
    
    
    @Column(name = "RATE_MULT", precision = 12, scale = 6)
    private double rateMult;
    
    @Column(name = "RATE_DIV", precision = 12, scale = 6)
    private double rateDiv;
    
    //Date for tax calculation
    private Timestamp taxDate;
    
    public Long getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(Long invoiceId) {
        this.invoiceId = invoiceId;
    }

    public CEInvoiceLineItem() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CEInvoice getInvoice() {
        return invoice;
    }

    public void setInvoice(CEInvoice invoice) {
        this.invoice = invoice;
        if (invoice != null ){ 
            this.invoiceId = invoice.getId();
        }
    }


    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(double netPrice) {
        this.netPrice = netPrice;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public double getTaxPct() {
        return taxPct;
    }

    public void setTaxPct(double taxPct) {
        this.taxPct = taxPct;
    }

    public Double getTaxAmt() {
        return taxAmt;
    }

    public void setTaxAmt(Double taxAmt) {
        this.taxAmt = taxAmt;
    }

    public Double getVatAmt() {
        return vatAmt;
    }

    public void setVatAmt(Double vatAmt) {
        this.vatAmt = vatAmt;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public String getVatCode() {
        return vatCode;
    }

    public void setVatCode(String vatCode) {
        this.vatCode = vatCode;
    }

    public double getVatPct() {
        return vatPct;
    }

    public void setVatPct(double vatPct) {
        this.vatPct = vatPct;
    }

    public double getVat() {
        return vat;
    }

    public void setVat(double vat) {
        this.vat = vat;
    }

    public String getBranchName() {
        return branchName;
    }

    public void setBranchName(String branchName) {
        this.branchName = branchName;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public String getDepId() {
        return depId;
    }

    public void setDepId(String depId) {
        this.depId = depId;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public Set<DepositInvoiceAmount> getDepositInvoiceAmounts() {
        if (depositInvoiceAmounts == null) {
            depositInvoiceAmounts = new HashSet<DepositInvoiceAmount>();
        }
        return depositInvoiceAmounts;
    }

//    public double getSecuredAmount() {
//        return securedAmount;
//    }
//
//    public void setSecuredAmount(double securedAmount) {
//        this.securedAmount = securedAmount;
//    }

    public boolean addDepositInvoiceAmount(DepositInvoiceAmount depositInvoiceAmount) {
        if(depositInvoiceAmount != null){
            depositInvoiceAmount.setInvoiceLineItem(this);
        }
        return getDepositInvoiceAmounts().add(depositInvoiceAmount);
    }

    public boolean removeDepositInvoiceAmount(DepositInvoiceAmount depositInvoiceAmount) {
        return getDepositInvoiceAmounts().remove(depositInvoiceAmount);
    }

    // TODO: check with Rafiq
    public double getTotalAmount() {
        return this.netPrice + this.tax + this.vat;
    }

//    public double getPreTaxAmount() {
//        return this.netPrice;
//    }

//    /**
//     * When an InvoiceLineItem is paid (may be partially) by a deposit invoice,
//     * the amount paid is noted as secured amount, which is different from
//     * the amount paid by other means. Every time when a deposit invoice is
//     * used for payment, the method should be called to increase the secured
//     * amount for this InvoiceLineItem.
//     *
//     * @param amt
//     */
//    public boolean secureAmount(double amt) {
//        if (this.securedAmount + amt > this.getTotalAmount()) {
//            // you cannot over pay this line item
//            return false;
//        } else {
//            this.securedAmount += amt;
//            return true;
//        }
//    }


//    /**
//     * The unpaid amount is the difference of the totalAmount and the securedAmount
//     *
//     * @return
//     */
//    public double getUnsecuredAmount() {
//        return this.getTotalAmount() - this.getSecuredAmount();
//    }

    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }

    public Timestamp getTaxDate() {
        return taxDate;
    }

    public void setTaxDate(Timestamp taxDate) {
        this.taxDate = taxDate;
    }

	public Long getCEJobOrderLineItemId() {
		return cEJobOrderLineItemId;
	}

	public void setCEJobOrderLineItemId(Long cEJobOrderLineItemId) {
		this.cEJobOrderLineItemId = cEJobOrderLineItemId;
	}

	public CEJobOrderLineItem getCEJobOrderLineItem() {
		return cEJobOrderLineItem;
	}

	public void setCEJobOrderLineItem(CEJobOrderLineItem cEJobOrderLineItem) {
		this.cEJobOrderLineItem = cEJobOrderLineItem;
        if(cEJobOrderLineItem != null)
            this.cEJobOrderLineItemId = cEJobOrderLineItem.getId();
	}

    public String getTaxArticleCode() {
        return taxArticleCode;
    }

    public void setTaxArticleCode(String taxArticleCode) {
        this.taxArticleCode = taxArticleCode;
    }

    public double getRateMult() {
        return rateMult;
    }

    public void setRateMult(double rateMult) {
        this.rateMult = rateMult;
    }

    public double getRateDiv() {
        return rateDiv;
    }

    public void setRateDiv(double rateDiv) {
        this.rateDiv = rateDiv;
    }
	
    
}
