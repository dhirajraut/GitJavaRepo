/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 *
 * @Date : 4/22/2009
 * @Author : The Phoenix Team
 */

package com.intertek.phoenix.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

/**
 * CEJobOrderLineItem (aka JOLI) models individual line item information in
 * CEJobOrderContract objects. A JOLI captures billing
 * information includes pricing info (unit of measure, price and quantity),
 * billing data. There are three types of billing data:
 * <ul>
 * timeCommitted and amountCommitted, are the time and expenses quoted in the
 * original quote, or agreed between the two parties.
 * <ul>
 * timeBilled and amountBilled, are the time and expenses have been invoiced.
 * Time and amount can be billed incrementally, and the timeBilled and
 * expenseBilled are the accumulated value of all the time and expense that have
 * been invoiced so far.
 * <ul>
 * timeAccrued and amountAccrued are the accumulated the time and expense spent
 * on the task (line item), whether they have been invoiced or not. In other
 * words, timeAccrued and amountAccrued is all about revenues that have been
 * realized up to date.
 * <ul>
 * There is also a concept of time and expense to be billed. Expense-to-bill is
 * the difference between the amountAccrued and amountBilled. This is the amount
 * to show on the next invoice for this JOLI. In the case where expense-to-bill
 * is zero, then this JOLI is not in a billable state. Similar concept applies
 * to time-to-bill.
 * <p/>
 *
 * @author richard.qin
 */
@Entity
@Table(name = "PHX_JOB_ORDER_LINE_ITEM")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "TYPE",
        discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("JOLI")
public class CEJobOrderLineItem {

	@Id
    @SequenceGenerator(name="PHX_JobOrderLineItem_seq", sequenceName = "PHX_JOB_ORDER_LINE_ITEM_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "PHX_JobOrderLineItem_seq" )
    @Column(name = "ID")
    private Long id;
	
//    @Column(name = "QUOTE_LINE_ID", updatable = false, insertable = false)
//    private Long quoteLineId;
//    @OneToOne // was ManyToOne
//    @JoinColumn(name = "QUOTE_LINE_ID")
//    @Index(name="PHX_JOLI_IDX_QUOTE_LINE")
//    @org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
//    private QuoteLine quoteLine;
//    @Column(name = "QUOTED_PRICE", precision = 38, scale = 4)
//    private double quotedPrice;

    @Column(name = "ACTIVITY_ID")
    private String activityId;
    
    // a JobContractTest can have more than one JOLI, each belongs
    // to a different (unique) batch number. At billing time, all or some
    // of the JOLIs belong to a same batch number will go to one
    // invoice.
    // For JOLIs that are converted from BillingEvent, the temporary
    // invoice number is the batchNumber. For other JOLIs, the batchNumber
    // is a sequence number. 
    @Column(name = "BATCH_NUMBER")
    private String batchNumber;

    
    @Column(name = "BILLED_AMOUNT", precision = 38, scale = 4)
    private double billedAmount;
    @Column(name = "ACCRUED_AMOUNT", precision = 38, scale = 4)
    private double accruedAmount;
    @Column(name = "COMMITTED_AMOUNT", precision = 38, scale = 4)
    private double committedAmount; // funded amount

    @Column(name = "UOM", length = 20)
    @Enumerated(EnumType.STRING)
    private UOM uom;

    @Column(name = "QUANTITY", precision = 38, scale = 4)
    private double quantity;
    
    @Column(name = "UNIT_PRICE", precision = 38, scale = 4)
    private double unitPrice;
    @Column(name = "SPLIT_PCT", precision = 38, scale = 4)
    private double splitPct;
    @Column(name = "DISCOUNT_PCT", precision = 38, scale = 4)
    private double discountPct;
    @Column(name = "NET_PRICE", precision = 38, scale = 4)
    private double netPrice;
    @Column(name = "CURRENCY_CD", length = 3)
    private String currencyCd;
    @Column(name = "PRIMARY_BRANCH_CD", length = 10)
    private String primaryBranchCd;
    @Column(name = "PRODUCT_GROUP", length = 6)
    private String productGroup;
    @Column(name = "DEPT_ID", length = 10)
    private String deptid;
//    @Column(name = "BUS_STREAM_CODE", length = 35)
//    private String busStreamCode;
    @Column(name = "TAX_CODE", length = 8)
    private String taxCode;
    @Column(name = "VAT_CODE", length = 8)
    private String vatCode;
    @Column(name = "TAX_PCT", precision = 38, scale = 4)
    private double taxPct;
    @Column(name = "VAT_PCT", precision = 38, scale = 4)
    private double vatPct;
    @Column(name = "TAX_AMT", precision = 38, scale = 5)
    private double taxAmt;
    @Column(name = "VAT_AMT", precision = 38, scale = 5)
    private double vatAmt;
    @Column(name = "ACCOUNT", length = 45)
    private String account;
    @Column(name = "RATE_TYPE", length = 20)
    private String rateType; //??
    @Column(name = "RATE_MULT", precision = 12, scale = 6)
    private double rateMult;
    @Column(name = "RATE_DIV", precision = 12, scale = 6)
    private double rateDiv;
//    @Length(min=0, max=3) private String baseCurrencyCd; //??
    @Column(name = "BASE_UNIT_PRICE", precision = 38, scale = 4)
    private double baseUnitPrice;
    @Column(name = "BASE_NET_PRICE", precision = 38, scale = 4)
    private double baseNetPrice;
    @Column(name = "BASE_TAX_AMT", precision = 38, scale = 4)
    private double baseTaxAmt;
    @Column(name = "BASE_VAT_AMT", precision = 38, scale = 4)
    private double baseVatAmt;
    @Column(name = "EDITABLE", precision = 1, scale = 0)
    @org.hibernate.annotations.Type(type = "yes_no")
    private boolean editable;
    
    @Column(name = "TASK_OPERATIONAL_STATUS")
    @Enumerated(EnumType.STRING)
    private TaskOperationalStatus taskOperationalStatus;

    @OneToMany(mappedBy = "joli", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    private Set<JobOrderLineItemNote> notes;
    
    @OneToMany(mappedBy = "joli", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    private Set<JobOrderLineItemAttachment> attachments;
    
    @OneToMany(mappedBy = "cEJobOrderLineItem", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    private Set<RevenueSegregation> revenueSegregations;
    
    @Column(name = "SERVICE_OFFERING_ID", updatable = false, insertable = false)
    private Long serviceOfferingId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "SERVICE_OFFERING_ID")
    @Index(name="PHX_CE_JOLI_IDX_SERV_OFR")
    private ServiceOffering serviceOffering;

    @Column(name = "BILLING_STATUS", length = 20)
    @Enumerated(EnumType.STRING)
    private BillingStatus billingStatus;

    @Column(name = "SERVICE_TYPE", length = 128)
    private String serviceType;

//    @Column(name = "REVENUE", length = 128)
//    private String revenue;
    
    @Column(name = "LINE_NUMBER")
    private long lineNumber;
    @Column(name = "DESCRIPTION", columnDefinition = "NVARCHAR2(1024)")
    private String description;

    // TODO the following fields may not be needed
    @Column(name = "IS_NEW", length = 1)
    @org.hibernate.annotations.Type(type = "yes_no")
    private boolean isNew;
    @Column(name = "IS_CANCELED", length = 1)
    @org.hibernate.annotations.Type(type = "yes_no")
    private boolean canceled;
    
    @Column(name = "IS_RELATED_TO_TASK", length = 1)
    @org.hibernate.annotations.Type(type = "yes_no")
    private boolean relatedToTask;

    @Column(name = "ORIGIN", length = 20)
    @Enumerated(EnumType.STRING)
    private OrderOrigin origin;

    @Column(name = "JOB_ORDER_CONTRACT_ID", updatable = false, insertable = false)
    private Long jobOrderContractId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "JOB_ORDER_CONTRACT_ID")
    @Index(name="OLI_INDEX_JO_CONTRACT")
    private CEJobContract jobContract;
    
    //for split line items
    @Column(name = "MASTER_ID", updatable = false, insertable = false)
    private Long masterId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
        private CEJobOrderLineItem master;
    @JoinColumn(name = "MASTER_ID")
    @OneToMany(mappedBy="master", fetch = FetchType.LAZY)
    private Set<CEJobOrderLineItem> related;

    @Column(name = "COMMENTS", columnDefinition = "NVARCHAR2(1024)")
    private String comment;
    
    public CEJobOrderLineItem(){
        
    }

    /**
     * Validate the job order line item, required by spec
     */
    public boolean validate() {
        // TODO
        throw new UnsupportedOperationException();
    }

//    /**
//     * A type 2 job orde line item is splitable
//     */
//    public boolean isSplitable() {
//        if (this.getJobContract().getJobOrder().getStatus() == OrderStatus.OPEN) {
//            if (this.getJobContract().getJobOrder().getProjectType() == ProjectType.TYPE_2) {
//                return true;
//            }
//        }
//        return false;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public long getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(long lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean getIsNew() {
        return isNew;
    }

    public boolean isNew() {
        return isNew;
    }

    public void setIsNew(boolean isNew) {
        this.isNew = isNew;
    }

    public boolean isCanceled() {
        return canceled;
    }

    public void setCanceled(boolean isCanceled) {
        this.canceled = isCanceled;
    }

    public OrderOrigin getOrigin() {
        return origin;
    }

    public void setOrigin(OrderOrigin origin) {
        this.origin = origin;
    }

    public double getBilledAmount() {
        return billedAmount;
    }

    public void setBilledAmount(double billedAmount) {
        this.billedAmount = billedAmount;
    }

    public double getAccruedAmount() {
        return accruedAmount;
    }

    public void setAccruedAmount(double accruedAmount) {
        this.accruedAmount = accruedAmount;
    }

    public double getCommittedAmount() {
        return committedAmount;
    }

    public void setCommittedAmount(double committedAmount) {
        this.committedAmount = committedAmount;
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
    
    public BillingStatus getBillingStatus() {
        return billingStatus;
    }

    public void setBillingStatus(BillingStatus billingStatus) {
        this.billingStatus = billingStatus;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

//    public Long getQuoteLineId() {
//        return quoteLineId;
//    }
//
//    public void setQuoteLineId(Long quoteLineId) {
//        this.quoteLineId = quoteLineId;
//    }
//
//    public QuoteLine getQuoteLine() {
//        return quoteLine;
//    }
//
//    public void setQuoteLine(QuoteLine quoteLine) {
//        this.quoteLineId = quoteLine.getId();
//        this.quoteLine = quoteLine;
//    }
//
    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public UOM getUom() {
        return uom;
    }

    public void setUom(UOM uom) {
        this.uom = uom;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public TaskOperationalStatus getTaskOperationalStatus() {
        return taskOperationalStatus;
    }

    public void setTaskOperationalStatus(TaskOperationalStatus taskOperationalStatus) {
        this.taskOperationalStatus = taskOperationalStatus;
    }

    public Set<JobOrderLineItemNote> getNotes() {
        if (notes == null) {
            notes = new HashSet<JobOrderLineItemNote>();
        }
        return notes;
    }

    public boolean addNote(JobOrderLineItemNote note) {
        boolean ok = getNotes().add(note);
        if (ok) {
            note.setJobOrderLineItem(this);
        }
        return ok;
    }

    public boolean removeNote(JobOrderLineItemNote note) {
        return getNotes().remove(note);
    }

    public void addNotes(JobOrderLineItemNote... notes) {
        if (notes == null) {
            return;
        }

        for (JobOrderLineItemNote note: notes) {
            this.addNote(note);
        }
    }

    public void addAttachment(JobOrderLineItemAttachment attachment) {
        this.getAttachments().add(attachment);
        attachment.setJobOrderLineItem(this);
    }

    public Set<JobOrderLineItemAttachment> getAttachments() {
        if (attachments == null) {
            attachments = new HashSet<JobOrderLineItemAttachment>();
        }
        return attachments;
    }

    public boolean addAttachments(JobOrderLineItemAttachment attachment) {
        boolean ok = getAttachments().add(attachment);
        if (ok) {
            attachment.setJobOrderLineItem(this);
        }
        return ok;
    }

    public boolean removeAttachments(JobOrderLineItemAttachment attachment) {
        return getAttachments().remove(attachment);
    }

//    // TODO, please move this method somewhere else
//    public SplitLineItem[] getSplitLineItem() {
//        return getSplits().toArray(new SplitLineItem[0]);
//        
//        if (splits != null) {
//
//            SplitLineItem[] result = new SplitLineItem[splits.size()];
//            int i = 0;
//            for (SplitLineItem ce : splits) {
//                result[i] = ce;
//                i++;
//            }
//
//            return result;
//        } else {
//            return null;
//        }
//    }

//    public Set<SplitLineItem> getSplits() {
//        if (splits == null) {
//            splits = new HashSet<SplitLineItem>();
//        }
//        return splits;
//    }
//
//    public boolean addSplit(SplitLineItem split) {
//        return getSplits().add(split);
//    }
//
//    public boolean removeSplit(SplitLineItem split) {
//        return getSplits().remove(split);
//    }
//
    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public double getDiscountPct() {
        return discountPct;
    }

    public void setDiscountPct(double discountPct) {
        this.discountPct = discountPct;
    }

    /**
     * The net amount can be calculated by the pricing engine,
     * or the estimated revenue that a user put in. A user can
     * change the estimated revenue anytime, as long as the value
     * is no greater than the quoted amount and no less than the
     * billed amount.
     */
    public double getNetPrice() {
        return netPrice;
    }

    public void setNetPrice(double netPrice) {
        this.netPrice = netPrice;
    }

    public String getCurrencyCd() {
        return currencyCd;
    }

    public void setCurrencyCd(String currencyCd) {
        this.currencyCd = currencyCd;
    }

    public String getPrimaryBranchCd() {
        return primaryBranchCd;
    }

    public void setPrimaryBranchCd(String primaryBranchCd) {
        this.primaryBranchCd = primaryBranchCd;
    }

    public String getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(String productGroup) {
        this.productGroup = productGroup;
    }

    public String getDeptid() {
        return deptid;
    }

    public void setDeptid(String deptid) {
        this.deptid = deptid;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getVatCode() {
        return vatCode;
    }

    public void setVatCode(String vatCode) {
        this.vatCode = vatCode;
    }

    public double getTaxPct() {
        return taxPct;
    }

    public void setTaxPct(double taxPct) {
        this.taxPct = taxPct;
    }

    public double getVatPct() {
        return vatPct;
    }

    public void setVatPct(double vatPct) {
        this.vatPct = vatPct;
    }

    public double getTaxAmt() {
        return taxAmt;
    }

    public void setTaxAmt(double taxAmt) {
        this.taxAmt = taxAmt;
    }

    public double getVatAmt() {
        return vatAmt;
    }

    public void setVatAmt(double vatAmt) {
        this.vatAmt = vatAmt;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getRateType() {
        return rateType;
    }

    public void setRateType(String rateType) {
        this.rateType = rateType;
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

    public double getBaseUnitPrice() {
        return baseUnitPrice;
    }

    public void setBaseUnitPrice(double baseUnitPrice) {
        this.baseUnitPrice = baseUnitPrice;
    }

    public double getBaseNetPrice() {
        return baseNetPrice;
    }

    public void setBaseNetPrice(double baseNetPrice) {
        this.baseNetPrice = baseNetPrice;
    }

    public double getBaseTaxAmt() {
        return baseTaxAmt;
    }

    public void setBaseTaxAmt(double baseTaxAmt) {
        this.baseTaxAmt = baseTaxAmt;
    }

    public double getBaseVatAmt() {
        return baseVatAmt;
    }

    public void setBaseVatAmt(double baseVatAmt) {
        this.baseVatAmt = baseVatAmt;
    }

    public boolean getEditable() {
        return editable;
    }

    public void setEditable(boolean editable) {
        this.editable = editable;
    }


    public double getSplitPct() {
        return splitPct;
    }

    public void setSplitPct(double splitPct) {
        this.splitPct = splitPct;
    }

    // TODO remove this method. If sorting is required, do it 
    // properly with Comparator.
//    public SplitLineItem[] getSplitLineItems() {
//        if (splits != null) {                
//            SplitLineItem[] result = splits.toArray(new SplitLineItem[splits.size()]);
//            SplitLineItem tmpItem=null;
//            for(int i =result.length-1;i>=0 ;i--){
//                for(int k = i-1; k>=0;k--){
//                    if(result[k].getLineNumber()>result[i].getLineNumber())
//                    {
//                        tmpItem = result[i];
//                        result[i]=result[k];
//                        result[k]=tmpItem;
//                    }
//                }
//                
//            }
//            return result;
//        } else
//            return null;
//    }

    // TODO remove this Bad method
//    public void setSplitLineItems(SplitLineItem[] spl) {
//        if (splits != null) {
//            Object[] obj = splits.toArray();
//            SplitLineItem[] spl1 = new SplitLineItem[obj.length];
//
//            for (int i = 0; i < obj.length; i++) {
//                spl1[i] = (SplitLineItem) obj[i];
//            }
//            for (int i = 0; i < spl1.length; i++) {
//                spl1[i] = spl[i];
//            }
//        }
//    }
//
    /**
     * Find the revenue segregation that matches the given line description
     * @param lineDesc
     * @return
     */
    public RevenueSegregation getRevenueSegregation(String lineDesc) {
        for(RevenueSegregation revSeg: this.getRevenueSegregations()){
            if(revSeg.getDescription().equals(lineDesc)){
                return revSeg;
            }
        }
        return null;
    }

    /**
     * A billable Job Order Line Item has a OPEN status, and its accrued amount
     * is greater than the billed amount.
     * <p>A split line item is not billable.
     *
     * @return true if the line item is not a split, is OPEN and has accrued
     *         amount.
     */
    public boolean isBillable() {
        if ((this.getBillingStatus() == BillingStatus.OPEN 
                // being billable doesn't mean it can be invoiced directly
                || this.getBillingStatus() == BillingStatus.NOT_INVOICEABLE)
                && this.getBatchNumber() == null 
                // batch number other than null are either already billed 
                // or related to BillingEvents
                && this.getUnbilledAmount() > 0) {
            return true;
        } 
        else {
            return false;
        }
    }

    /**
     * Adjust the total bill amount after this line item is invoiced
     */
    public void adjustBilledAmount() {
        if (isBillable()) {
            this.billedAmount += this.getBillingAmount();
            this.billingStatus = BillingStatus.INVOICED;
        }
    }

    public Set<RevenueSegregation> getRevenueSegregations() {
        if (revenueSegregations == null) {
            revenueSegregations = new HashSet<RevenueSegregation>();
        }
        return revenueSegregations;
    }

    // TODO should the update trace be captured here or in the Form Controller?
    public boolean addRevenueSegregation(RevenueSegregation rs) {
        if(rs != null){
            rs.setCEJobOrderLineItem(this);
            return getRevenueSegregations().add(rs);
        }
        return false;
    }

    // TODO should the update trace be captured here or in the Form Controller?
    public boolean removeRevenueSegregation(RevenueSegregation rs) {
        boolean deleteResult = getRevenueSegregations().remove(rs);
        return deleteResult;
    }

    public Long getMasterId() {
        return masterId;
    }

    public void setMasterId(Long masterId) {
        this.masterId = masterId;
    }

    public CEJobOrderLineItem getMaster() {
        return master;
    }

    public void setMaster(CEJobOrderLineItem master) {
        if(master != null){
            this.masterId = master.getId();
        }
        this.master = master;
    }

    public Set<CEJobOrderLineItem> getRelated() {
        if(this.related == null){
            this.related = new HashSet<CEJobOrderLineItem>();
        }
        return this.related;
    }
    
    public boolean addRelated(CEJobOrderLineItem split) {
        if(split != null){
            split.setMaster(this);
            return this.getRelated().add(split);
        }
        return false;
    }
    
    public boolean removeRelated(CEJobOrderLineItem split){
        if(split != null){
            boolean result = this.getRelated().remove(split);
            // reorganize all the line items
            for(CEJobOrderLineItem joli: this.getRelated()){
                long line = joli.getLineNumber();
                if(line > split.getLineNumber()){
                    joli.setLineNumber(line -1);
                }
            }
            return result;
        }
        return false;
    }
    
    /**
     * @return
     */
    public boolean isRelatedToTask() {
        return this.relatedToTask;
    }
    
    public void setRelatedToTask(boolean isRelatedToTask){
        this.relatedToTask = isRelatedToTask;
    }

    public Long getServiceOfferingId() {
        return serviceOfferingId;
    }

    public void setServiceOfferingId(Long serviceOfferingId) {
        this.serviceOfferingId = serviceOfferingId;
    }

    public ServiceOffering getServiceOffering() {
        return serviceOffering;
    }

    public void setServiceOffering(ServiceOffering serviceOffering) {
        if(serviceOffering != null){
            this.serviceOfferingId = serviceOffering.getId();
        }
        this.serviceOffering = serviceOffering;
    }

    public String getBatchNumber() {
        return batchNumber;
    }

    public void setBatchNumber(String batchNumber) {
        this.batchNumber = batchNumber;
    }
    
    public double getBillingAmount(){
        double amount = 0;
        if(this.getAccruedAmount() > 0){
            amount = this.getAccruedAmount() - this.getBilledAmount();
        }
        else{
            amount = this.getUnbilledAmount();
        }
        // RQ: The following may need to be turned on, in order to support
        // split line items.
        // If someone uncomment the following code, please leave a comment
        // and put you name here.
        /*
        for(CEJobOrderLineItem related: this.getRelated()){
            if(related.getBillingStatus() == BillingStatus.NOT_INVOICEABLE){
                amount += related.getBillingAmount();
            }
        }
        */
        return amount;
    }
    
    /**
     * The unbilled amount is the net amount substract billedAmount, or the 
     * quoted amount substract billedAmount, or 0 .
     * 
     * The net amount can be calculated by the pricing engine,
     * or the estimated revenue that a user put in.
     * 
     * @return
     */
    public double getUnbilledAmount(){
        if(this.getNetPrice() > 0){
            return this.getNetPrice() - this.getBilledAmount();
        }
        else if(this.getCommittedAmount() > 0){
            return this.getCommittedAmount() - this.getBilledAmount();
        }
        else{
            return 0;
        }
    }
    
    public CEJobOrderLineItem clone(){
        CEJobOrderLineItem joli = new CEJobOrderLineItem();
        return copyTo(joli);
    }

    /**
     * @param joli
     * @param jobOrderLineItem
     */
    protected CEJobOrderLineItem copyTo(CEJobOrderLineItem joli) {
        joli.setAccount(this.getAccount());
        // do not carry over accruedAmount, because 0 accruedAmount
        // has a special meaning
        joli.setAccruedAmount(0);
        joli.setActivityId(this.getActivityId());
        joli.setBaseNetPrice(this.getBaseNetPrice());
        joli.setBaseTaxAmt(this.getBaseTaxAmt());
        joli.setBaseUnitPrice(this.getBaseUnitPrice());
        joli.setBaseVatAmt(this.getBaseVatAmt());
        joli.setBatchNumber(this.getBatchNumber());
        joli.setBilledAmount(this.getBilledAmount());
        joli.setBillingStatus(BillingStatus.OPEN);
        joli.setCommittedAmount(this.getCommittedAmount());
        joli.setCurrencyCd(this.getCurrencyCd());
        joli.setDeptid(this.getDeptid());
        joli.setDescription(this.getDescription());
        joli.setDiscountPct(this.getDiscountPct());
        joli.setEditable(this.getEditable());
        joli.setLineNumber(this.lineNumber);
        joli.setNetPrice(this.getNetPrice());
        joli.setOrigin(this.getOrigin());
        joli.setPrimaryBranchCd(this.getPrimaryBranchCd());
        joli.setProductGroup(this.getProductGroup());
        joli.setQuantity(this.getQuantity());
        joli.setRateDiv(this.getRateDiv());
        joli.setRateMult(this.getRateMult());
        joli.setRateType(this.getRateType());
        joli.setServiceOffering(this.getServiceOffering());
        joli.setServiceType(this.getServiceType());
        joli.setSplitPct(this.getSplitPct()); // how is this used?
        joli.setTaskOperationalStatus(this.getTaskOperationalStatus());
        joli.setTaxAmt(this.getTaxAmt());
        joli.setTaxCode(this.getTaxCode());
        joli.setTaxPct(this.getTaxPct());
        joli.setUnitPrice(this.getUnitPrice());
        joli.setUom(this.getUom());
        joli.setVatAmt(this.getVatAmt());
        joli.setVatCode(this.getVatCode());
        joli.setVatPct(this.getVatPct());

        this.getJobContract().addJobOrderLineItem(joli);
        return joli;
    }

    /**
     * The total amount is the net amount if not 0, or the 
     * quoted amount if not 0, or 0 in type-3 projects.
     * 
     * The net amount can be calculated by the pricing engine,
     * or the estimated revenue that a user put in.
     * 
     * @return
     */
    public double getTotalAmount() {
        if(this.getNetPrice() > 0){
            return this.getNetPrice();
        }
        else if(this.getCommittedAmount() > 0){
            return this.getCommittedAmount();
        }
        else {
            return 0;
        }
    }
    
    public boolean validatePrice(){
        if(this.getCommittedAmount() > 0){
            double sum = this.getNetPrice();
            for(CEJobOrderLineItem related: this.getRelated()){
                sum += related.getNetPrice();
            }
            if(sum > this.getCommittedAmount()){
                return false;
            }
        }
        return true;
    }
}
