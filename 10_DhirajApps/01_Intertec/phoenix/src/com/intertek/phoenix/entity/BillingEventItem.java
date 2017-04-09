/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 *
 * @Date : 4/22/2009
 * @Author : The Phoenix Team
 */

package com.intertek.phoenix.entity;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.intertek.entity.Contact;

/**
 * BillingEventItem maps to each billing event detail sent in a BillingEvent.
 *
 * @author richard.qin/eric.nguyen
 */
@Entity
@Table(name = "PHX_BILLING_EVENT_ITEM")
public class BillingEventItem {
    @Id
    @SequenceGenerator(name = "PHX_BillingEventItem_seq", sequenceName = "PHX_BILLING_EVENT_ITEM_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "PHX_BillingEventItem_seq")
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "PS_LINE_NUMBER", length = 22)
    private String psLineNumber;

    @Column(name = "DESCRIPTION", length = 1024)
    private String description;

    @Column(name = "ACTIVITY_ID", length = 128)
    private String activityId;

    @Column(name = "UNIT_AMOUNT", precision = 38, scale = 4)
    private double unitAmount;

    @Column(name = "GROSS_EXTENDED_AMOUNT", precision = 38, scale = 4)
    private double grossExtendedAmount;

    @Column(name = "ACCOUNT_CODE", length = 128)
    private String accountCode;

    @Column(name = "DEPARTMENT", length = 128)
    private String department;

    @Column(name = "SERVICE_OFFERING", length = 128)
    private String serviceOffering;

    @Column(name = "QUANTITY", precision = 38, scale = 4)
    private double quantity;

    @Column(name = "BILLING_CURRENCY_CD", length = 3)
    private String billingCurrencyCD;
    
    @Column(name = "INVOICE_AMOUNT", precision = 38, scale = 4)
    private double invoiceAmount;

    @Column(name = "BILLING_EVENT_ID", updatable = false, insertable = false)
    private Long billingEventId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "BILLING_EVENT_ID")
    @Index(name = "PHX_BEI_IDX_BILLING_EVENT_ID")
    private BillingEvent billingEvent;

    @OneToMany(mappedBy = "billingEventItem", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    private Set<BillingLineDistribution> billingLineDistributions;
    
    public BillingEventItem() {

    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    public String getPsLineNumber() {
        return psLineNumber;
    }

    public void setPsLineNumber(String psLineNumber) {
        this.psLineNumber = psLineNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActivityId() {
        return activityId;
    }

    public void setActivityId(String activityId) {
        this.activityId = activityId;
    }

    public double getUnitAmount() {
        return unitAmount;
    }

    public void setUnitAmount(double unitAmount) {
        this.unitAmount = unitAmount;
    }

    public double getGrossExtendedAmount() {
        return grossExtendedAmount;
    }

    public void setGrossExtendedAmount(double grossExtendedAmount) {
        this.grossExtendedAmount = grossExtendedAmount;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getDepartment() {
        return department;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public String getServiceOffering() {
        return serviceOffering;
    }

    public void setServiceOffering(String serviceOffering) {
        this.serviceOffering = serviceOffering;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public String getBillingCurrencyCD() {
        return billingCurrencyCD;
    }

    public void setBillingCurrencyCD(String billingCurrencyCD) {
        this.billingCurrencyCD = billingCurrencyCD;
    }

    public double getInvoiceAmount() {
        return invoiceAmount;
    }

    public void setInvoiceAmount(double invoiceAmount) {
        this.invoiceAmount = invoiceAmount;
    }

    public Set<BillingLineDistribution> getBillingLineDistributions() {
        return billingLineDistributions;
    }

    public void setBillingLineDistributions(Set<BillingLineDistribution> billingLineDistributions) {
        this.billingLineDistributions = billingLineDistributions;
    }

    public Long getBillingEventId() {
        return billingEventId;
    }

    public void setBillingEventId(Long billingEventId) {
        this.billingEventId = billingEventId;
    }

    public BillingEvent getBillingEvent() {
        return billingEvent;
    }

    public void setBillingEvent(BillingEvent billingEvent) {
        if(billingEvent != null){
            billingEventId = billingEvent.getId();
        }
        this.billingEvent = billingEvent;
    }
    
}
