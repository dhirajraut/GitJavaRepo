/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.entity;

import java.sql.Timestamp;
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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.intertek.entity.Branch;
import com.intertek.entity.ServiceLocation;
import com.intertek.entity.Test;
import com.intertek.entity.User;

/**
 * 
 * @author richard.qin
 */
@Entity(name="com.intertek.phoenix.entity.JobTest")
@Table(name="PHX_JOB_TEST")
//@Inheritance(strategy = InheritanceType.JOINED)
public class JobTest {
	
    @Id
    @SequenceGenerator(name="PHX_JobTest_seq", sequenceName = "PHX_JOB_TEST_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "PHX_JobTest_seq" )
    @Column(name = "ID")
    private Long id; 

    @Column(name = "UPDATE_FLAG", length=8)
    private String updateFlag;
    
    @Column(name = "JOB_SERVICE_LEVEL_ID", updatable = false, insertable = false)
    private Long jobServiceLevelId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "JOB_SERVICE_LEVEL_ID")
    @Index(name="PHX_JOB_TEST_IDX_JOB_SRVC_LVL")
    private JobServiceLevel jobServiceLevel;
    
    @Column(name = "TEST_ID", updatable = false, insertable = false)
    private String testId;
    @ManyToOne()
//    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
//        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "TEST_ID")
    @Index(name="PHX_JOB_TEST_IDX_TEST")
    private Test test;
    
    @Column(name = "QUANTITY", precision = 38, scale = 4)
    private double quantity;
    
    @Column(name = "QUOTED_AMOUNT", precision = 38, scale = 4)
    private double quotedAmount;
    
    @Column(name = "OT", precision = 1, scale = 0)
    @org.hibernate.annotations.Type(type = "yes_no")
    private boolean ot;
    
    @Column(name = "LINE_DESCRIPTION", length = 1024)
    private String lineDescription;
    
    @Column(name = "CONTRACT_REF_NO", length = 45)
    private String contractRefNo;
    
//    @Column(name = "IS_MANUAL_TEST")
//    @org.hibernate.annotations.Type(type = "yes_no")
//    private boolean isManualTest;
    
    @Column(name = "UNIT_PRICE", precision = 38, scale = 4)
    private double unitPrice;
    
    @Column(name = "SAMPLE_DESCRIPTION", columnDefinition = "NVARCHAR2(1024)")
    private String sampleDescription;

    @Column(name = "START_DATE")
    private Timestamp startDate;
    @Column(name = "END_DATE")
    private Timestamp endDate;
    @Column(name = "TASK_READY_DATE")
    private Timestamp taskReadyDate;

    @Column(name = "BRANCH_NAME", updatable = false, insertable = false)
    private String branchName;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "BRANCH_NAME")
    @Index(name="PHX_CE_JOBTEST_IDX_BRANCH")
    private Branch branch;

    @Column(name = "MODEL_NUMBER", columnDefinition = "NVARCHAR2(1024)")
    private String modelNumber;

    @Column(name = "TASK_MANAGER_ID", updatable = false, insertable = false)
    private String taskManagerId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "TASK_MANAGER_ID")
    @Index(name="PHX_CE_JOBTEST_IDX_TSK_MGR")
    private Employee taskManager;

    @Column(name = "CREDIT_OVERRIDE_BY_ID", updatable = false, insertable = false)
    private String creditOverrideById;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "CREDIT_OVERRIDE_BY_ID")
    @Index(name="PHX_CE_JOBTEST_IDX_CRDT")
    private User creditOverrideBy;

    @Column(name = "SERVICE_LOCATION_CODE", updatable = false, insertable = false)
    private String serviceLocationCode;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "SERVICE_LOCATION_CODE")
    @Index(name="PHX_CE_JOBTEST_IDX_LOCT")
    private ServiceLocation serviceLocation;
    
    @Column(name = "BILLING_STATUS", length = 20)
    @Enumerated(EnumType.STRING)
    private BillingStatus billingStatus;
    @Column(name = "OPERATIONAL_STATUS", length = 20)
    @Enumerated(EnumType.STRING)
    private OperationalStatus operationalStatus;
    
    @OneToMany(mappedBy = "jobTest", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    private Set<Estimation> estimations;

    @Column(name = "FORCASED_REVENUE")
    private double forcastedRevenue;
    @Column(name = "UOM")
    private String uom;
    
    @Column (name = "PO_ID", updatable = false, insertable = false)
    private Long poId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "PO_ID", referencedColumnName = "ID")
    @Index(name="PHX_JOBTEST_IDX_PO")
    private PurchaseOrder purchaseOrder;

    @Column(name = "QUOTE_LINE_ID", updatable = false, insertable = false)
    private Long quoteLineId;
    @OneToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "QUOTE_LINE_ID")
    @Index(name = "PHX_JOLI_IDX_QUOTE_LINE")
    private QuoteLine quoteLine;

    @Column(name = "SERVICE_OFFERING_ID", updatable = false, insertable = false)
    private Long serviceOfferingId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "SERVICE_OFFERING_ID")
    @Index(name="PHX_CE_JOBTEST_IDX_SERV_OFR")
    private ServiceOffering serviceOffering;

    @Column(name = "FUNDED_AMOUNT", precision = 38, scale = 4)
    private double fundedAmount;
    
    @Column(name = "MASTER_ID", updatable = false, insertable = false)
    private Long masterId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "MASTER_ID")
    @Index(name="PHX_JOB_TEST_IDX_MASTER") // invalid index, must be replaced
    private JobTest master;

    // Note, this field is for "split" line items
    @OneToMany(mappedBy="master", fetch = FetchType.LAZY)
    private Set<JobTest> related;
    
    @OneToMany( mappedBy="jobTest", fetch = FetchType.LAZY)
    private Set<JobContractTest> jobContractTests;

    @OneToMany(mappedBy = "jobTest", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    private Set<JobTestNote> notes;    

    @OneToMany(mappedBy = "jobTest", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    private Set<JobTestAttachment> attachments;

    @Column(name = "LINE_NUMBER")
    private long linenumber;
    
    public JobTest() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJobServiceLevelId() {
        return jobServiceLevelId;
    }

    public void setJobServiceLevelId(Long jobServiceLevelId) {
        this.jobServiceLevelId = jobServiceLevelId;
    }

    public String getTestId() {
        return testId;
    }

    public void setTestId(String testId) {
        this.testId = testId;
    }

    public JobServiceLevel getJobServiceLevel() {
        return jobServiceLevel;
    }

    public void setJobServiceLevel(JobServiceLevel jobServiceLevel) {
        if (jobServiceLevel != null ){
            this.jobServiceLevelId = jobServiceLevel.getId();
        }
        this.jobServiceLevel = jobServiceLevel;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        if(test != null){
            this.testId = test.getTestId();
        }
        this.test = test;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public boolean getOt() {
        return ot;
    }

    public void setOt(boolean ot) {
        this.ot = ot;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }

    public String getLineDescription() {
        return lineDescription;
    }

    public void setLineDescription(String lineDescription) {
        this.lineDescription = lineDescription;
    }

    public String getContractRefNo() {
        return contractRefNo;
    }

    public void setContractRefNo(String contractRefNo) {
        this.contractRefNo = contractRefNo;
    }

    public boolean isManualTest() {
        return this.getTest() == null;
    }

    public Set<JobContractTest> getJobContractTests() {
        if(jobContractTests == null){
            jobContractTests = new HashSet<JobContractTest>();
        }
        return jobContractTests;
    }

    public boolean addJobContractTest(JobContractTest jct){
        if(jct != null){
            jct.setJobTest(this);
            return this.getJobContractTests().add(jct);
        }
        return false;
    }

    public boolean removeJobContractTest(JobContractTest jct){
        return this.getJobContractTests().remove(jct);
    }
    
    public void setQuotedAmount(double amount){
        this.quotedAmount = amount;
    }

    public double getQuotedAmount() {
        return this.quotedAmount;
    }

    public String getSampleDescription() {
        return sampleDescription;
    }

    public void setSampleDescription(String sampleDescription) {
        this.sampleDescription = sampleDescription;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getEndDate() {
        return endDate;
    }

    public void setEndDate(Timestamp endDate) {
        this.endDate = endDate;
    }

    public Timestamp getTaskReadyDate() {
        return taskReadyDate;
    }

    public void setTaskReadyDate(Timestamp taskReadyDate) {
        this.taskReadyDate = taskReadyDate;
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
        if(branch != null){
            this.branchName = branch.getName();
        }
        this.branch = branch;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getTaskManagerId() {
        return taskManagerId;
    }

    public void setTaskManagerId(String taskManagerId) {
        this.taskManagerId = taskManagerId;
    }

    public Employee getTaskManager() {
        return taskManager;
    }

    public void setTaskManager(Employee taskManager) {
        if(taskManager != null){
            this.taskManagerId = taskManager.getEmployeeId();
        }
        this.taskManager = taskManager;
    }

    public String getCreditOverrideById() {
        return creditOverrideById;
    }

    public void setCreditOverrideById(String creditOverrideById) {
        this.creditOverrideById = creditOverrideById;
    }

    public User getCreditOverrideBy() {
        return creditOverrideBy;
    }

    public void setCreditOverrideBy(User creditOverrideBy) {
        if(creditOverrideBy != null){
            this.creditOverrideById = creditOverrideBy.getLoginName();
        }
        this.creditOverrideBy = creditOverrideBy;
    }

    public String getServiceLocationCode() {
        return serviceLocationCode;
    }

    public void setServiceLocationCode(String serviceLocationCode) {
        this.serviceLocationCode = serviceLocationCode;
    }

    public ServiceLocation getServiceLocation() {
        return serviceLocation;
    }

    public void setServiceLocation(ServiceLocation serviceLocation) {
        if(serviceLocation != null){
            this.serviceLocationCode = serviceLocation.getServiceLocationCode();
        }
        this.serviceLocation = serviceLocation;
    }

    public BillingStatus getBillingStatus() {
        return billingStatus;
    }

    public void setBillingStatus(BillingStatus billingStatus) {
        this.billingStatus = billingStatus;
    }

    public OperationalStatus getOperationalStatus() {
        return operationalStatus;
    }

    public void setOperationalStatus(OperationalStatus operationalStatus) {
        this.operationalStatus = operationalStatus;
    }

    public double getForcastedRevenue() {
        return forcastedRevenue;
    }

    public void setForcastedRevenue(double forcastedRevenue) {
        this.forcastedRevenue = forcastedRevenue;
    }

    public String getUom() {
        return uom;
    }

    public void setUom(String uom) {
        this.uom = uom;
    }

//    public String getPoNumber() {
//        return poNumber;
//    }
//
//    public void setPoNumber(String poNumber) {
//        this.poNumber = poNumber;
//    }

    public Long getPoId() {
        return poId;
    }

    public void setPoId(Long poId) {
        this.poId = poId;
    }

    public PurchaseOrder getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(PurchaseOrder purchaseOrder) {
        if(purchaseOrder != null){
            this.poId = purchaseOrder.getId();
        }
        this.purchaseOrder = purchaseOrder;
    }

    public Long getQuoteLineId() {
        return quoteLineId;
    }

    public void setQuoteLineId(Long quoteLineId) {
        this.quoteLineId = quoteLineId;
    }

    public QuoteLine getQuoteLine() {
        return quoteLine;
    }

    public void setQuoteLine(QuoteLine quoteLine) {
        if(quoteLine != null){
            this.quoteLineId = quoteLine.getId();
        }
        this.quoteLine = quoteLine;
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

    public double getFundedAmount() {
        return fundedAmount;
    }

    public void setFundedAmount(double fundedAmount) {
        this.fundedAmount = fundedAmount;
    }

    public Long getMasterId() {
        return masterId;
    }

    public void setMasterId(Long masterId) {
        this.masterId = masterId;
    }

    public JobTest getMaster() {
        return master;
    }

    public void setMaster(JobTest master) {
        this.masterId = master.getId();
        this.master = master;
    }

    public Set<Estimation> getEstimations() {
        if(this.estimations == null){
            this.estimations = new HashSet<Estimation>();
        }
        return estimations;
    }
    
    public boolean addEstimation(Estimation est){
        if(est != null) {
            est.setJobTest(this);
            est.setJobTestId(this.getId());
            return this.getEstimations().add(est);
        }
        else {
            return false;
        }
    }
    
    public boolean removeEstimation(Estimation est){
        return this.getEstimations().remove(est);
    }

    public Set<JobTest> getRelated() {
        if(this.related == null){
            this.related = new HashSet<JobTest>();
        }
        return related;
    }
    
    public boolean addRelated(JobTest jobTest){
        
        jobTest.setMaster(this);
        int max=0;
        for(JobTest jt:this.getRelated()) {             
            if(max <jt.getLinenumber()) {                   
                max = (int) jt.getLinenumber();
            }               
        }  
        max++;
        jobTest.setLinenumber(max);

        return this.getRelated().add(jobTest);
    }
    
    public boolean removeRelated(JobTest jobTest){
        return this.getRelated().remove(jobTest);
    }

    //this method convert Set to array, to make an iteration in JSP
    // RQ: this code is so not good. the jsp will repeatedly call this method,
    // which causes a new array being constructed everytime. A lot of CPU has
    // been wasted here. Must refactor, move it to a form object. TODO
//    public JobTest[] getSplit(){
//        if(related !=null && related.size()>0){
//             JobTest[] result= related.toArray(new JobTest[related.size()]);
//             //JobTest[] result = st.toArray(new JobTest[st.size()]);
//             JobTest tmpJobTest = null;
//             for (int i = result.length - 1; i >= 0; i--) {
//                 for (int k = i - 1; k >= 0; k--) {
//                     if (result[k].getLinenumber() > result[i].getLinenumber()) {
//                         tmpJobTest = result[i];
//                         result[i] = result[k];
//                         result[k] = tmpJobTest;
//                     }
//                 }
//             }
//             return result;
//
//        }
//        return null;
//    }

    public boolean isInvoiceable() {
        return this.billingStatus != BillingStatus.NOT_INVOICEABLE;
    }

    public void setInvoiceable(boolean invoiceable) {
        if(invoiceable) {
            this.setBillingStatus(BillingStatus.OPEN);
        }
        else{
            this.setBillingStatus(BillingStatus.NOT_INVOICEABLE);
        }
    }

    public Set<JobTestNote> getNotes() {
        if (notes == null) {
            notes = new HashSet<JobTestNote>();
        }
        return notes;
    }

    public boolean addNote(JobTestNote note) {
        return getNotes().add(note);
    }

    public boolean removeNote(JobTestNote note) {
        return getNotes().remove(note);
    }

    public Set<JobTestNote> removeNotes(JobTestNote... notes) {
        Set<JobTestNote> removed = new HashSet<JobTestNote>();
        if (notes == null) {
            return removed;
        }

        Set<JobTestNote> myNotes = this.getNotes();
        // removing all the notes
        for (JobTestNote note: this.getNotes()) {
            if (myNotes.remove(note)) {
                removed.add(note);
                note.setJobTest(null);
            }
        }
        return removed;
    }

    
    public void setNotes(Set<JobTestNote> notes) {
        this.notes = notes;
    }

    public Set<JobTestAttachment> getAttachments() {
        if (attachments == null) {
            attachments = new HashSet<JobTestAttachment>();
        }
        return attachments;
    }
    
    public void setAttachments(Set<JobTestAttachment> attachments) {
        this.attachments = attachments;
    }

    public boolean addAttachment(JobTestAttachment attachment) {
        return getAttachments().add(attachment);
    }

    public boolean removeAttachment(Attachment attachment) {
        return getAttachments().remove(attachment);
    }

    public long getLinenumber() {
        return linenumber;
    }

    public void setLinenumber(long linenumber) {
        this.linenumber = linenumber;
    }

    public String getUpdateFlag() {
        return updateFlag;
    }

    public void setUpdateFlag(String updateFlag) {
        this.updateFlag = updateFlag;
    }

    public boolean isNotRelated(){
        if(this.getMasterId()==null){
            return true;
        }
        else{
            return false;
        }
    }
    
    public boolean validatePrice(){
        if(this.getQuotedAmount() > 0){
            double sum = this.getForcastedRevenue();
            for(JobTest related: this.getRelated()){
                sum += related.getForcastedRevenue();
            }
            if(sum > this.getQuotedAmount()){
                return false;
            }
        }
        return true;
    }
}
