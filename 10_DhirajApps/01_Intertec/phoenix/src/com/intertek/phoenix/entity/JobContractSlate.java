/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.intertek.entity.Slate;

/**
 * 
 * @author richard.qin
 */
@Entity(name="com.intertek.phoenix.entity.JobContractSlate")
@Table(name="PHX_JOB_CONTRACT_SLATE")
public class JobContractSlate {
    @Id
    @SequenceGenerator(name = "PHX_JobContractSlate_seq", 
                       sequenceName = "PHX_JOB_CONTRACT_SLATE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "PHX_JobContractSlate_seq")
    @Column(name = "ID")
    private Long id;
     
    @Column(name = "CONTRACT_SERVICE_LEVEL_ID", updatable = false, insertable = false)
    private Long contractServiceLevelId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "CONTRACT_SERVICE_LEVEL_ID")
    @Index(name="PHX_JCS_IDX_CONT_SRVC_LVL")
    private ContractServiceLevel contractServiceLevel;
     
    @Column(name = "JOB_SLATE_ID", updatable = false, insertable = false)
    private Long jobSlateId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "JOB_SLATE_ID", nullable=true) // not every JobContractSlate has a JobSlate
    @Index(name="PHX_JCS_IDX_JOB_SLATE")
    private JobSlate jobSlate;
     
    @Column(name = "SLATE_ID", updatable = false, insertable = false)
    private String slateId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "SLATE_ID")
    @Index(name="PHX_JCS_IDX_SLATE")
    private Slate slate;
     
    @Column(name = "JOB_ORDER_LINE_ITEM_ID", updatable = false, insertable = false)
    private Long jobOrderLineItemId;
    @OneToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "JOB_ORDER_LINE_ITEM_ID")
    @Index(name="PHX_JCS_IDX_JOLI")
    private CEJobOrderLineItem jobOrderLineItem;
     
    @Column(name = "QUANTITY", precision = 38, scale = 4)
    private double quantity;
    
    @Column(name = "OT", precision = 1, scale = 0)
    private boolean ot;
    
    @Column(name = "LINE_DESCRIPTION", length = 1024)
    private String lineDescription;
    
    @Column(name = "BILLING_STATUS", length = 20)
    @Enumerated(EnumType.STRING)
    private BillingStatus billingStatus;

    // TODO not sure is the pricing info belong here
    @Column(name = "UNIT_PRICE", precision = 38, scale = 4)
    private double unitPrice;
    
    @Column(name = "CONTRACT_REF_NO", length = 45)
    private String contractRefNo;
    
    @Column(name = "DISCOUNT", precision = 38, scale = 4)
    private double discount;
    
    @Column(name = "TOTAL_PRICE", precision = 38, scale = 4)
    private double totalPrice;


    /** default constructor */
    public JobContractSlate() {
    }

    // Property accessors
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Slate getSlate() {
        return this.slate;
    }

    public void setSlate(Slate slate) {
        this.slate = slate;
    }

    public Long getJobSlateId() {
        return this.jobSlateId;
    }

    public void setJobSlateId(Long jobSlateId) {
        this.jobSlateId = jobSlateId;
    }

    public double getQuantity() {
        return this.quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public boolean getOt() {
        return this.ot;
    }

    public void setOt(boolean ot) {
        this.ot = ot;
    }

    public double getUnitPrice() {
        return this.unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getLineDescription() {
        return this.lineDescription;
    }

    public void setLineDescription(String lineDescription) {
        this.lineDescription = lineDescription;
    }

    public String getContractRefNo() {
        return this.contractRefNo;
    }

    public void setContractRefNo(String contractRefNo) {
        this.contractRefNo = contractRefNo;
    }

    public double getDiscount() {
        return this.discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getTotalPrice() {
        return this.totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public CEJobOrderLineItem getJobOrderLineItem() {
        return jobOrderLineItem;
    }

    public void setJobOrderLineItem(CEJobOrderLineItem jobOrderLineItem) {
        this.jobOrderLineItem = jobOrderLineItem;
    }

    public ContractServiceLevel getContractServiceLevel() {
        return contractServiceLevel;
    }

    public void setContractServiceLevel(ContractServiceLevel contractServiceLevel) {
        this.contractServiceLevelId = contractServiceLevel.getId();
        this.contractServiceLevel = contractServiceLevel;
    }

    public JobSlate getJobSlate() {
        return jobSlate;
    }

    public void setJobSlate(JobSlate jobSlate) {
        this.jobSlate = jobSlate;
    }

    public Long getContractServiceLevelId() {
        return contractServiceLevelId;
    }

    public void setContractServiceLevelId(Long contractServiceLevelId) {
        this.contractServiceLevelId = contractServiceLevelId;
    }

    public String getSlateId() {
        return slateId;
    }

    public void setSlateId(String slateId) {
        this.slateId = slateId;
    }

    public Long getJobOrderLineItemId() {
        return jobOrderLineItemId;
    }

    public void setJobOrderLineItemId(Long jobOrderLineItemId) {
        this.jobOrderLineItemId = jobOrderLineItemId;
    }

    public BillingStatus getBillingStatus() {
        return billingStatus;
    }

    public void setBillingStatus(BillingStatus billingStatus) {
        this.billingStatus = billingStatus;
    }
}
