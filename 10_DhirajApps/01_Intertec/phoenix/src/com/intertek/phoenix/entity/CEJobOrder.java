/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 *
 * @Date : 4/22/2009
 * @Author : The Phoenix Team
 */

package com.intertek.phoenix.entity;

import java.sql.Timestamp;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.intertek.entity.Branch;
import com.intertek.entity.BusinessUnit;
import com.intertek.entity.ServiceLocation;
import com.intertek.entity.User;
import com.intertek.phoenix.job.Order;
import com.intertek.phoenix.util.CommonUtil;
import com.intertek.util.StringUtil;

/**
 * @author richard.qin
 */

@Entity
@Table(name = "PHX_JOB_ORDER")
public class CEJobOrder implements Order{
    @Id
    @Column(name = "JOB_NUMBER", length = 128)
    private String jobNumber;
    
    @Column(name = "JOB_TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    private JobOrderType jobType ;

    @Column(name = "PROJECT_NUMBER", updatable = false, insertable = false)
    private String projectNumber;
    @OneToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "PROJECT_NUMBER")
    @Index(name="PHX_JO_IDX_PROJECT")
    private Project project;
    
    @Column(name="PROJECT_TYPE", length=10)
    @Enumerated(EnumType.STRING)
    private ProjectType projectType; //should look at project Object first

    @Column(name = "QUOTE_ID", updatable = false, insertable = false)
    private Long quoteId;
    @OneToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "QUOTE_ID")
    @Index(name="PHX_JO_IDX_QUOTE")
    private Quote quote;

    @Column(name = "BU_NAME", updatable = false, insertable = false)
    private String buName;
    @ManyToOne()
    @JoinColumn(name = "BU_NAME", referencedColumnName = "BU_NAME")
    @Index(name="PHX_JO_INX_BU")
    private BusinessUnit bu;

    @Column(name = "BRANCH_NAME", updatable = false, insertable = false)
    private String branchName;
    @ManyToOne()
    @JoinColumn(name = "BRANCH_NAME")
    @Index(name="PHX_JO_IDX_BRANCH")
    private Branch branch;

    /* 
     * TODO: remove businessStream
     */
    @Column(name = "BUSINESS_STREAM_CODE", updatable = false, insertable = false)
    private String businessStreamCode;
    @ManyToOne()
    @JoinColumn(name = "BUSINESS_STREAM_CODE", referencedColumnName = "CODE")
    @Index(name="PHX_JO_IDX_STREAM")
    private BusinessStream businessStream;

    @Column(name = "SERVICE_LOCATION_CODE", updatable = false, insertable = false)
    private String serviceLocationCode;
    @ManyToOne()
    @JoinColumn(name = "SERVICE_LOCATION_CODE")
    @Index(name="PHX_JO_IDX_LOCATION")
    private ServiceLocation serviceLocation;

    @Column(name = "SALES_PERSON_NAME", updatable = false, insertable = false)
    private String salesPersonName;
    @ManyToOne()
    @JoinColumn(name = "SALES_PERSON_NAME")
    private User salesPerson;

    @Column(name = "SECONDARY_SALES_PERSON_NAME", updatable = false, insertable = false)
    private String secondarySalesPersonName;
    @ManyToOne()
    @JoinColumn(name = "SECONDARY_SALES_PERSON_NAME")
    @Index(name="PHX_JO_IDX_SALE2")
    private User secondarySalesPerson;

    @Column(name = "PROJECT_MANAGER_NAME", updatable = false, insertable = false)
    private String projectManagerName;
    @ManyToOne()
    @JoinColumn(name = "PROJECT_MANAGER_NAME")
    @Index(name="PHX_JO_IDX_PM")
    private Employee projectManager;
    
    @Column(name = "CREATED_BY", updatable = false, insertable = false)
    private String createdByUserName;
    @ManyToOne()
    @JoinColumn(name = "CREATED_BY")
    @Index(name="PHX_JO_IDX_CRTBY")
    private User createdBy;
    
    @Column(name = "CREATE_DATE")
    private Timestamp createDate;

    @Column(name = "UPDATE_DATE")
    private Timestamp updateDate;

    @Column(name = "UPDATED_BY", updatable = false, insertable = false)
    private String updatedByUserName;
    @ManyToOne()
    @JoinColumn(name = "UPDATED_BY")
    @Index(name="PHX_JO_IDX_UPDTBY")
    private User updatedBy;
    
    @Column(name = "OPERATION", length = 10)
    private String operation;    

    @Column(name = "PROMISE_COMPLETION_DATE")
    private Timestamp promiseCompletionDate;

    @Column(name = "CUSTOMER_READY_DATE")
    private Timestamp customerReadyDate;

    @Column(name = "ACTUAL_READY_DATE")
    private Timestamp actualReadyDate;

    @Column(name = "TURNAROUND_DAYS")
    private long turnaroundDays;

    @Column(name = "FOLLOW_UP")
    @org.hibernate.annotations.Type(type = "yes_no")
    private boolean followUp;

    @Column(name = "MODEL_NUMBER", length = 128)
    private String modelNumber;

    @Column(name = "DESCRIPTION", columnDefinition = "NVARCHAR2(1024)")
    private String description;

    @Column(name = "ORIGIN", length = 20)
    @Enumerated(EnumType.STRING)
    private OrderOrigin origin;

    @Column(name = "OPERATIONAL_STATUS", length = 20)
    @Enumerated(EnumType.STRING)
    private OperationalStatus operationalStatus;

    @Column(name = "ORDER_STATUS", length = 20)
    @Enumerated(EnumType.STRING)
    private OrderStatus status; // ??
    
    @Column(name = "PAGE_NUMBER", length = 2)
    private Integer pageNumber;

    @Column(name = "BILLING_STATUS", length = 20)
    @Enumerated(EnumType.STRING)
    private BillingStatus billingStatus; // ??

    @Column(name = "ROOT_SERVICE_LEVEL_ID", updatable = false, insertable = false)
    private Long rootServiceLevelId;
    @OneToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "ROOT_SERVICE_LEVEL_ID")
    @Index(name="PHX_JO_IDX_ROOT_SRVC_LVL")
    private JobServiceLevel rootServiceLevel; // this is the root service level
    
    @OneToMany( mappedBy="jobOrder", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    private Set<CEJobContract> jobContracts;
    
    @OneToMany(mappedBy = "jobOrder", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    private Set<Instruction> instructions;
    

    public CEJobOrder(){
        
    }


    /**
     * For type 3 project, update the job order with billable line item
     * information from PeoppleSoft
     */
    public void syncFromPS(List<CEJobOrderLineItem> lineItems) {
        // TODO
        throw new UnsupportedOperationException();
    }

    /**
     * For type 2 project, send billable line item information of the job order
     * to PeoppleSoft
     */
    public void syncToPS() {
        // TODO
        throw new UnsupportedOperationException();
    }

    /**
     * Complete this job order
     */
    public void close() {
        this.status = OrderStatus.CANCELED;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public JobOrderType getJobType() {
        return jobType;
    }

    public void setJobType(JobOrderType jobType) {
        this.jobType = jobType;
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        if(project != null){
            this.projectNumber = project.getProjectNumber();
        }
        this.project = project;
    }

    public Long getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(Long quoteId) {
        this.quoteId = quoteId;
    }

    public Quote getQuote() {
        return quote;
    }

    public void setQuote(Quote quote) {
        if(quote != null){
            this.quoteId = quote.getId();
        }
        this.quote = quote;
    }

    public String getBuName() {
        return buName;
    }

    public void setBuName(String buName) {
        this.buName = buName;
    }

    public BusinessUnit getBu() {
        return bu;
    }

    public void setBu(BusinessUnit bu) {
        if(bu != null){
            this.buName = bu.getName();
        }
        this.bu = bu;
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

    public String getBusinessStreamCode() {
        return businessStreamCode;
    }

    public void setBusinessStreamCode(String businessStreamCode) {
        this.businessStreamCode = businessStreamCode;
    }

    public BusinessStream getBusinessStream() {
        return businessStream;
    }

    public void setBusinessStream(BusinessStream businessStream) {
        if(businessStream != null){
            this.businessStreamCode = businessStream.getCode();
        }
        this.businessStream = businessStream;
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
        this.serviceLocation = serviceLocation;
        if (serviceLocation != null) {
            this.serviceLocationCode = serviceLocation.getServiceLocationCode();
        } else {
            this.serviceLocationCode = null;
        }
    }

    public String getSalesPersonName() {
        return salesPersonName;
    }

    public void setSalesPersonName(String salesPersonName) {
        this.salesPersonName = salesPersonName;
    }

    public User getSalesPerson() {
        return salesPerson;
    }

    public void setSalesPerson(User salesPerson) {
        if(salesPerson != null){
            this.salesPersonName = salesPerson.getLoginName();
        }
        this.salesPerson = salesPerson;
    }

    public String getSecondarySalesPersonName() {
        return secondarySalesPersonName;
    }

    public void setSecondarySalesPersonName(String secondarySalesPersonName) {
        this.secondarySalesPersonName = secondarySalesPersonName;
    }

    public User getSecondarySalesPerson() {
        return secondarySalesPerson;
    }

    public void setSecondarySalesPerson(User secondarySalesPerson) {
        if(secondarySalesPerson != null){
            this.secondarySalesPersonName = secondarySalesPerson.getLoginName();
        }
        this.secondarySalesPerson = secondarySalesPerson;
    }

    public String getProjectManagerFullName() {
        if(projectManager!=null){
            return projectManager.getFullName();
        }
        return null;
    }
    
    public String getProjectManagerName() {
        return projectManagerName;
    }

    public void setProjectManagerName(String projectManagerName) {
        this.projectManagerName = projectManagerName;
    }

    public Employee getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(Employee projectManager) {
        if(projectManager != null){
            this.projectManagerName = projectManager.getEmployeeId();
        }
        this.projectManager = projectManager;
    }

    public Timestamp getPromiseCompletionDate() {
        return promiseCompletionDate;
    }

    public void setPromiseCompletionDate(Timestamp promiseCompletionDate) {
        this.promiseCompletionDate = promiseCompletionDate;
    }

    public Timestamp getCustomerReadyDate() {
        return customerReadyDate;
    }

    public void setCustomerReadyDate(Timestamp customerReadyDate) {
        this.customerReadyDate = customerReadyDate;
    }

    public Timestamp getActualReadyDate() {
        return actualReadyDate;
    }

    public void setActualReadyDate(Timestamp actualReadyDate) {
        this.actualReadyDate = actualReadyDate;
    }

    public long getTurnaroundDays() {
        return turnaroundDays;
    }

    public void setTurnaroundDays(long turnaroundDays) {
        this.turnaroundDays = turnaroundDays;
    }

    public boolean isFollowUp() {
        return followUp;
    }

    public void setFollowUp(boolean followUp) {
        this.followUp = followUp;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

    public Set<CEJobContract> getJobContracts(){
        if (jobContracts == null ){
            jobContracts = new HashSet<CEJobContract>();
        }
        return jobContracts;
    }
    
    public boolean addJobContract(CEJobContract jobContract){
        if(getJobContracts().size() == 0){
            jobContract.setJobOrder(this);
            return getJobContracts().add(jobContract);
        }
        // CE JobOrder only has one contract
        return false;
    }
    
    public boolean removeJobContract(CEJobContract jobContract){
        return getJobContracts().remove(jobContract);
    }

    public CEJobContract getJobContract(){
        CEJobContract jobContract = null;
        for (CEJobContract jc : getJobContracts()) {
            jobContract = jc;
            break;
        }
        return jobContract;
    }
    
    public void setJobContract(CEJobContract jobContract){
        if (jobContract != null) {
            jobContract.setJobOrderNumber(this.jobNumber);
            addJobContract(jobContract);
        }
    }

    public OrderOrigin getOrigin() {
        return origin;
    }

    public void setOrigin(OrderOrigin origin) {
        this.origin = origin;
    }

    public OperationalStatus getOperationalStatus() {
        return operationalStatus;
    }

    public void setOperationalStatus(OperationalStatus operationalStatus) {
        this.operationalStatus = operationalStatus;
    }

    public Instruction getInstruction(InstructionType type) {
        // Note: if multiple instruction of the same type 
        // in the set then the first one found is returned.
        Set<Instruction> ins = getInstructions();
        for (Instruction i : ins) {
            if (i.getInstructionType() == type) {
                return i;
            }
        }
        return null;
    }

    public Set<Instruction> getInstructions() {
        if (instructions == null) {
            instructions = new HashSet<Instruction>();
        }
        return instructions;
    }

    public boolean addInstruction(Instruction instruction) {
        instruction.setJobOrder(this);
        return getInstructions().add(instruction);
    }

    public boolean removeInstructions(Instruction instruction) {
        return getInstructions().remove(instruction);
    }

    public BillingStatus getBillingStatus() {
        return billingStatus;
    }

    public void setBillingStatus(BillingStatus billingStatus) {
        this.billingStatus = billingStatus;
    }

    // see notes @ CEJobContract.getRootServiceLevelId()
    public Long getRootServiceLevelId() {
        if(rootServiceLevelId == null && rootServiceLevel != null){
            rootServiceLevelId = rootServiceLevel.getId();
        }
        return rootServiceLevelId;
    }

    public void setRootServiceLevelId(Long rootServiceLevelId) {
        this.rootServiceLevelId = rootServiceLevelId;
    }

    public JobServiceLevel getRootServiceLevel() {
        return rootServiceLevel;
    }

    public void setRootServiceLevel(JobServiceLevel rootServiceLevel) {
        this.rootServiceLevelId = rootServiceLevel.getId();
        this.rootServiceLevel = rootServiceLevel;
        rootServiceLevel.setJobOrder(this);
    }

    public void setProjectType(ProjectType projectType) {
        if(project!=null){
            project.setType(projectType);
        }
        this.projectType = projectType;
    }

    public ProjectType getProjectType() {
        if(project!=null){
            return project.getType();
        }
        return this.projectType;
    }

    /**
     * 
     * @see com.intertek.phoenix.job.Order#getContractJobOrder()
     */
    @Override
    public Collection<CEJobContract> getContractJobOrder() {
        return this.getJobContracts();
    }

    /**
     * 
     * @see com.intertek.phoenix.job.Order#getJobOrderName()
     */
    @Override
    public String getJobOrderName() {
        return null; // does this make sense?
    }

    /**
     * 
     * @see com.intertek.phoenix.job.Order#getNominationDate()
     */
    @Override
    public Date getNominationDate() {
        if(this.getJobFinishDate() != null){
            return this.getJobFinishDate();
        }
        else if(this.getActualReadyDate() != null){
            return this.getActualReadyDate();
        }
        else if(this.getPromiseCompletionDate() != null){
            return this.getPromiseCompletionDate();
        }
        return new Date();
    }

    /**
     * 
     * @see com.intertek.phoenix.job.Order#getJobFinishDate()
     */
    @Override
    public Date getJobFinishDate() {
        return this.getActualReadyDate(); // TODO verify this
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public Integer getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(Integer pageNumber) {
        this.pageNumber = pageNumber;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public String getCreatedByUserName() {
        return createdByUserName;
    }

    public void setCreatedByUserName(String createdByName) {
        this.createdByUserName = createdByName;
    }

    public Timestamp getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Timestamp createdDate) {
        this.createDate = createdDate;
    }

    public String getUpdatedByUserName() {
        return updatedByUserName;
    }

    public void setUpdatedByUserName(String updatedByUserName) {
        this.updatedByUserName = updatedByUserName;
    }

    public User getUpdatedBy() {
        return updatedBy;
    }

    public void setUpdatedBy(User updatedBy) {
        this.updatedBy = updatedBy;
    }

    public Timestamp getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Timestamp updatedDate) {
        this.updateDate = updatedDate;
    }

    public String getQuoteNumber() {
        if (quote != null){
            return quote.getQuoteNumber();
        }
        return "";
    }
    
    public boolean isTestSplittable(){
        return ProjectType.TYPE_2 == getProjectType() ||
            (ProjectType.TYPE_3 == getProjectType() && OrderStatus.NEW == getStatus());
    }
    
    public boolean isCustomerExist() {
        if (getJobContract() != null &&  !CommonUtil.isNullOrEmpty(getJobContract().getCustomerCode())) {
            return true;
        }
        else {
            return false;
        }
    }

}
