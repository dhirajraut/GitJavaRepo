/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.intertek.entity.Test;
import com.intertek.phoenix.util.CommonUtil;

/**
 * 
 * @author richard.qin
 */
@Entity(name="com.intertek.phoenix.entity.JobContractTest")
@Table(name="PHX_JOB_CONTRACT_TEST")
public class JobContractTest {
    @Id
    @SequenceGenerator(name = "PHX_JobContractTest_seq", 
                       sequenceName = "PHX_JOB_CONTRACT_TEST_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "PHX_JobContractTest_seq")
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "CONTRACT_SERVICE_LEVEL_ID", updatable = false, insertable = false)
    private Long contractServiceLevelId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "CONTRACT_SERVICE_LEVEL_ID")
    @Index(name="PHX_JCT_IDX_CONT_SRVC_LVL")
    private ContractServiceLevel contractServiceLevel;
    
    @Column(name = "JOB_TEST_ID", updatable = false, insertable = false)
    private Long jobTestId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "JOB_TEST_ID", nullable=true) // not every JobContractTest has a JobTest
    @Index(name="PHX_JCT_IDX_JOB_TEST")
    private JobTest jobTest;
    
    @Column(name = "TEST_ID", updatable = false, insertable = false)
    private String testId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "TEST_ID")
    @Index(name="PHX_JCT_IDX_TEST")
    private Test test;
    
    /* 2009-8-14:
     * The relationship between JobContractTest and JOLI is changed to 
     * OneToMany, in order to support multiple BillingEvents from PeopleSoft.
     * On the other hand, JOLI needs to support JobContractTest, 
     * JobContractSlate and JobContractServiceExpression, new entity types 
     * will be created for Test, Slate and Service respectively.
     * There is "default" JOLI which is the current "snapshot". The default
     * used to be the only JOLI before, now it is the one to be billed next.
     * All the other already billed JOLI has batch number great than 0.
     */
    @Column(name = "JOB_ORDER_LINE_ITEM_ID", updatable = false, insertable = false)
    private Long jobOrderLineItemId;
    @OneToMany(mappedBy = "jobContractTask", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    private Set<TestJobOrderLineItem> jobOrderLineItems;
    
    //for split line items
    @Column(name = "MASTER_ID", updatable = false, insertable = false)
    private Long masterId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    private JobContractTest master;
    @OneToMany(mappedBy="master", fetch = FetchType.LAZY)
    @JoinColumn(name = "MASTER_ID")
    private Set<JobContractTest> related;
    
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
    
    @Column(name = "SERVICE_OFFERING_ID", updatable = false, insertable = false)
    private Long serviceOfferingId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "SERVICE_OFFERING_ID")
    @Index(name="PHX_CE_JOBTEST_IDX_SERV_OFR")
    private ServiceOffering serviceOffering;

    // Constructors

    /** default constructor */
    public JobContractTest() {
    }

    // Property accessors
    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Test getTest() {
        return this.test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Long getJobTestId() {
        return this.jobTestId;
    }

    public void setJobTestId(Long jobTestId) {
        this.jobTestId = jobTestId;
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

    public Long getContractServiceLevelId() {
        return contractServiceLevelId;
    }

    public void setContractServiceLevelId(Long serviceLevelId) {
        this.contractServiceLevelId = serviceLevelId;
    }

    public Long getJobOrderLineItemId() {
        return jobOrderLineItemId;
    }

    public void setJobOrderLineItemId(Long jobOrderLineItemId) {
        this.jobOrderLineItemId = jobOrderLineItemId;
    }

    public Set<TestJobOrderLineItem> getJobOrderLineItems() {
        if(jobOrderLineItems == null){
            jobOrderLineItems = new HashSet<TestJobOrderLineItem>();
        }
        return jobOrderLineItems;
    }

    public boolean addJobOrderLineItem(TestJobOrderLineItem jobOrderLineItem) {
        if(jobOrderLineItem != null) {
            jobOrderLineItem.setJobContractTask(this);
            return this.getJobOrderLineItems().add(jobOrderLineItem);
        }
        return false;
    }

    public ContractServiceLevel getContractServiceLevel() {
        return contractServiceLevel;
    }

    public void setContractServiceLevel(ContractServiceLevel serviceLevel) {
        if(serviceLevel != null){
            this.contractServiceLevelId = serviceLevel.getId();
        }
        this.contractServiceLevel = serviceLevel;
    }

    public Long getMasterId() {
        return masterId;
    }

    public void setMasterId(Long masterId) {
        this.masterId = masterId;
    }

    public JobContractTest getMaster() {
        return master;
    }

    public void setMaster(JobContractTest master) {
        this.masterId = master.getId();
        this.master = master;
    }

    public Set<JobContractTest> getRelated() {
        if(this.related == null){
            this.related = new HashSet<JobContractTest>();
        }
        return related;
    }
    
    public boolean addRelated(JobContractTest jobContractTest){
        if(jobContractTest != null){
            jobContractTest.setMaster(this);
            return this.getRelated().add(jobContractTest);
        }
        return false;
    }
    
    public boolean removeRelated(JobContractTest jobContractTest){
        return this.getRelated().remove(jobContractTest);
    }

    public JobTest getJobTest() {
        return jobTest;
    }

    public void setJobTest(JobTest jobTest) {
        if(jobTest != null){
            this.jobTestId = jobTest.getId();
        }
        this.jobTest = jobTest;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public boolean isManualTest() {
        return this.getTest() == null;
    }

    public BillingStatus getBillingStatus() {
        return billingStatus;
    }

    public void setBillingStatus(BillingStatus billingStatus) {
        this.billingStatus = billingStatus;
    }

    /**
     * Get the JOLI that matches the batch number
     * @param batchNumber
     * @return
     */
    public TestJobOrderLineItem getJobOrderLineItem(String batchNumber) {
        for(TestJobOrderLineItem joli: this.getJobOrderLineItems()){
            if(CommonUtil.compare(joli.getBatchNumber(), batchNumber) == 0){
                return joli;
            }
        }
        return null;
    }

    // the "default" JOLI used to be the only JOLI for a JobContractTest,
    // however, since we need to support billing multiple times,
    // JobContractTest now supports multiple JOLIs. The default is the
    // JOLI that has a null batch number, it is not sent from PeopleSofe
    // (BillingEvent) and will be billed next
    public TestJobOrderLineItem getJobOrderLineItem() {
        // if this is completely billed, return the last billed JOLI
        for(TestJobOrderLineItem joli: this.getJobOrderLineItems()){
            if(joli.getBilledAmount() == joli.getTotalAmount()){
                return joli;
            }
        }
        // only gets here when this is not completely billed.
        return getJobOrderLineItem(null);
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
        this.serviceOffering = serviceOffering;
    }
}
