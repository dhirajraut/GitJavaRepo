/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.entity;

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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * BillingEvent encapsulates the billing request initiated from the PeopleSoft
 * system for Type3 CE Projects.
 * <p/>
 * TODO Details needs to be completed
 * What's the relationship between BillingEvent and JobOrderLineItem?
 *
 * @author richard.qin/eric.nguyen
 */
@Entity
@Table(name = "PHX_BILLING_EVENT")
public class BillingEvent {
    
    @Id
    @SequenceGenerator(name = "PHX_BillingEvent_seq", sequenceName = "PHX_BILLING_EVENT_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "PHX_BillingEvent_seq")
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "IS_APPLIED", length = 1)
    @org.hibernate.annotations.Type(type = "yes_no")
    private boolean isApplied;

    @Column(name = "JOB_CONTRACT_ID", insertable = false, updatable = false)
    private Long jobContractId;
    @ManyToOne
    @JoinColumn(name = "JOB_CONTRACT_ID")
    @org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private CEJobContract jobContract;

    @Column(name = "PS_INVOICE_NUMBER", length = 22)
    private String psInvoiceNumber;

    @OneToMany(mappedBy = "billingEvent", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    private Set<BillingEventItem> billingEventItems;
    
    @Column(name = "BILLING_STATUS", length = 20)
    @Enumerated(EnumType.STRING)
    private BillingStatus billingStatus;
    
    public BillingEvent(){
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public boolean isApplied() {
        return isApplied;
    }

    public void setApplied(boolean isApplied) {
        this.isApplied = isApplied;
    }

    public Set<BillingEventItem> getBillingEventItems() {
        if(billingEventItems == null){
            billingEventItems = new HashSet<BillingEventItem>();
        }
        return billingEventItems;
    }

    public boolean addBillingEventItem(BillingEventItem billingEventItem) {
        return this.getBillingEventItems().add(billingEventItem);
    }

    // make sure that removal is not support
    public boolean removeBillingEventItem(BillingEventItem billingEventItem) {
        throw new UnsupportedOperationException();
    }

    public Long getJobContractId() {
        return jobContractId;
    }

    public void setJobContractId(Long jobContractId) {
        this.jobContractId = jobContractId;
    }

    public CEJobContract getJobContract() {
        return jobContract;
    }

    public void setJobContract(CEJobContract jobContract) {
        if(jobContract != null){
            this.jobContractId = jobContract.getId();
        }
        this.jobContract = jobContract;
    }

    public String getPsInvoiceNumber() {
        return psInvoiceNumber;
    }

    public void setPsInvoiceNumber(String psInvoiceNumber) {
        this.psInvoiceNumber = psInvoiceNumber;
    }

    public BillingStatus getBillingStatus() {
        return billingStatus;
    }

    public void setBillingStatus(BillingStatus billingStatus) {
        this.billingStatus = billingStatus;
    }

}
