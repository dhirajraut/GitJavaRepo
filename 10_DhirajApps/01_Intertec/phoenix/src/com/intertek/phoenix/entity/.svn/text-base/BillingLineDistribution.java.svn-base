/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 *
 * @Date : 4/22/2009
 * @Author : The Phoenix Team
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

/**
 *
 * @author eric.nguyen
 */
@Entity
@Table(name = "PHX_BILLING_LINE_DISTRIBUTION")
public class BillingLineDistribution {
    @Id
    @SequenceGenerator(name = "PHX_BillingEventDist_seq", sequenceName = "PHX_BILLING_EVENT_DIST_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "PHX_BillingEventDist_seq")
    @Column(name = "ID")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "BILLING_EVENT_ITEM_ID", nullable=false)
    @org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private BillingEventItem billingEventItem;

    @Column(name = "BUSINESS_UNIT", length = 5)
    private String businessUnit;
    
    @Column(name = "INVOICE_NUMBER", length = 255)
    private String invoiceNumber;
    
    @Column(name = "LINE_SEQ_NUMBER", precision = 10, scale = 0)
    private int lineSeqNumber;
    
    @Column(name = "LINE_DIST_SEQ_NUMBER", precision = 10, scale = 0)
    private int lineDistSeqNumber;
    
    @Column(name = "JOB_NUMBER", length = 128)
    private String jobNumber;
    
    @Column(name = "ACCOUNT_CODE", length = 128)
    private String accountCode;
    
    @Column(name = "WAREHOUSE_ID", length = 8)
    private String warehouseId;
    
    @Column(name = "DEPARTMENT_ID", length = 128)
    private String departmentId;
    
    @Column(name = "BUSINESS_STREAM", length = 128)
    private String businessStream;
    
    @Column(name = "SERVICE_OFFERING", length = 128)
    private String serviceOffering;
    
    @Column(name = "DISTRIBUTION_AMT", precision = 38, scale = 4)
    private double distributionAmt;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BillingEventItem getBillingEventItem() {
        return billingEventItem;
    }

    public void setBillingEventItem(BillingEventItem billingEventItem) {
        this.billingEventItem = billingEventItem;
    }

    public String getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public int getLineSeqNumber() {
        return lineSeqNumber;
    }

    public void setLineSeqNumber(int lineSeqNumber) {
        this.lineSeqNumber = lineSeqNumber;
    }

    public int getLineDistSeqNumber() {
        return lineDistSeqNumber;
    }

    public void setLineDistSeqNumber(int lineDistSeqNumber) {
        this.lineDistSeqNumber = lineDistSeqNumber;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getAccountCode() {
        return accountCode;
    }

    public void setAccountCode(String accountCode) {
        this.accountCode = accountCode;
    }

    public String getWarehouseId() {
        return warehouseId;
    }

    public void setWarehouseId(String warehouseId) {
        this.warehouseId = warehouseId;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
    }

    public String getBusinessStream() {
        return businessStream;
    }

    public void setBusinessStream(String businessStream) {
        this.businessStream = businessStream;
    }

    public String getServiceOffering() {
        return serviceOffering;
    }

    public void setServiceOffering(String serviceOffering) {
        this.serviceOffering = serviceOffering;
    }

    public double getDistributionAmt() {
        return distributionAmt;
    }

    public void setDistributionAmt(double distributionAmt) {
        this.distributionAmt = distributionAmt;
    }    
}
