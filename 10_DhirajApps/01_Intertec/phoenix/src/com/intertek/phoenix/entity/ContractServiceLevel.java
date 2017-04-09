/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.entity;

import java.util.Collection;
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

import org.hibernate.annotations.Index;

import com.intertek.entity.Service;
import com.intertek.entity.Slate;
import com.intertek.entity.Test;
import com.intertek.phoenix.job.ContractServiceLevelInt;
import com.intertek.phoenix.job.ServiceLevel;
import com.intertek.util.Constants;

/**
 * 
 * @author richard.qin
 */
@Entity
@Table(name="PHX_CONTRACT_SERVICE_LEVEL")
public class ContractServiceLevel implements ContractServiceLevelInt{
    @Id
    @SequenceGenerator(name="PHX_ContractServiceLevel_seq", 
                       sequenceName = "PHX_CONTRACT_SERVICE_LEVEL_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "PHX_ContractServiceLevel_seq" )
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "SERVICE_LEVEL_NAME", length = 50)
    private String serviceLevelName;
    
    @Column(name = "TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    private ServiceLevelType serviceLevelType;
    
    @Column(name = "SORT_ORDER_NUM", precision = 4, scale = 0)
    private int sortOrderNum; 
    
    @Column(name = "JOB_CONTRACT_ID", updatable = false, insertable = false)
    private Long jobContractId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "JOB_CONTRACT_ID", referencedColumnName="ID")
    @Index(name="PHX_CONT_SRVC_LVL_IDX_JC_ID")
    private CEJobContract jobContract;
    
    @Column(name = "PARENT_SERVICE_LEVEL_ID", updatable = false, insertable = false)
    private Long parentServiceLevelId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "PARENT_SERVICE_LEVEL_ID")
    @Index(name="PHX_CONT_SRVC_LVL_IDX_PARENT")
    private ContractServiceLevel parentServiceLevel;
    
    @OneToMany( mappedBy="parentServiceLevel", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    private Set<ContractServiceLevel> childServiceLevels;
    
    //TODO: If lazy loading is failing, fix the problem rather than put into workaround.
    @OneToMany( mappedBy="contractServiceLevel", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    private Set<JobContractService> jobContractServices;
    
    @OneToMany( mappedBy="contractServiceLevel", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    private Set<JobContractTest> jobContractTests;
    
    @OneToMany( mappedBy="contractServiceLevel", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    private Set<JobContractSlate> jobContractSlates;
    
    @Column(name = "JOB_SERVICE_LEVEL_ID", updatable = false, insertable = false)
    private Long jobServiceLevelId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "JOB_SERVICE_LEVEL_ID")
    private JobServiceLevel jobServiceLevel;
    
    @Column(name = "DESCRIPTION", length = 50)
    private String description;

    public ContractServiceLevel(){
        
    }

    /**
     * @see com.intertek.phoenix.job.ContractServiceLevelInt#createJobContractService(com.intertek.entity.Service)
     */
    @Override
    public <T extends JobContractService> T createJobContractService(Class<T> type, Service service) {
        T jobContractService = null;
        try {
            jobContractService = type.newInstance();
            jobContractService.setServiceName(service.getServiceId().getServiceName());
            jobContractService.setRollup(service.getRollUp());
            // the caller must set the rest of the details, including serviceType
            
            this.getJobContractServices().add(jobContractService);
            jobContractService.setContractServiceLevel(this);
            return jobContractService;
        }
        catch (Exception e) {
            return null;
        }
    }

    /**
     * @see com.intertek.phoenix.job.ContractServiceLevelInt#createJobContractSlate(com.intertek.entity.Slate)
     */
    @Override
    public <T extends JobContractSlate> T createJobContractSlate(Class<T> type, Slate slate) {
        T jobContractSlate = null;
        try {
            jobContractSlate = type.newInstance();
            jobContractSlate.setSlate(slate);
            jobContractSlate.setQuantity(Constants.DEFAULT_TEST_SLATE_QTY);
            jobContractSlate.setOt(false); // TODO how to really set this value? Or it's up to the caller?
            
            this.getJobContractSlates().add(jobContractSlate);
            jobContractSlate.setContractServiceLevel(this);
            return jobContractSlate;
        }
        catch (Exception e) {
            return null;
        }
    }

    /**
     * @see com.intertek.phoenix.job.ContractServiceLevelInt#createJobContractTest(com.intertek.entity.Test)
     */
    @Override
    public <T extends JobContractTest> T createJobContractTest(Class<T> type, Test test) {
        T jobContractTest = null;
        try {
            jobContractTest = type.newInstance();
            if(test!=null){  //manual Test
                jobContractTest.setTest(test);
            }
            jobContractTest.setContractRefNo(this.getJobContract().getContractCode());
            jobContractTest.setBillingStatus(BillingStatus.OPEN);
            jobContractTest.setQuantity(Constants.DEFAULT_TEST_SLATE_QTY);
            jobContractTest.setOt(false);
            
            if(test != null){  //manual Test
                jobContractTest.setLineDescription(test.getTestDescription());
            }
            
            // TODO check if there is anything else to set?

            this.getJobContractTests().add(jobContractTest);
            jobContractTest.setContractServiceLevel(this);
            return jobContractTest;
        }
        catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * @see com.intertek.phoenix.job.ContractServiceLevelInt#removeJobContractService(com.intertek.phoenix.entity.JobContractService)
     */
    @Override
    public boolean removeJobContractService(JobContractService service) {
        return this.getJobContractServices().remove(service);
    }

    /**
     * @see com.intertek.phoenix.job.ContractServiceLevelInt#removeJobContractSlate(com.intertek.phoenix.entity.JobContractSlate)
     */
    @Override
    public boolean removeJobContractSlate(JobContractSlate slate) {
        return this.getJobContractSlates().remove(slate);
    }

    /**
     * @see com.intertek.phoenix.job.ContractServiceLevelInt#removeJobContractTest(com.intertek.phoenix.entity.JobContractTest)
     */
    @Override
    public boolean removeJobContractTest(JobContractTest test) {
        return this.getJobContractTests().remove(test);
    }

    /**
     * @see com.intertek.phoenix.job.ServiceLevel#createChildServiceLevel(com.intertek.phoenix.job.ServiceLevel.ServiceLevelType, java.lang.String)
     */
    @Override
    public ContractServiceLevel createChildServiceLevel(ServiceLevelType type, String name) {
        ContractServiceLevel child = new ContractServiceLevel();
        child.setServiceLevelName(name);
        child.setServiceLevelType(type);
        child.setParentServiceLevel(this);
        child.setJobContract(this.getJobContract());
        this.getChildServiceLevels().add(child);
        return child;
    }

    /**
     * @see com.intertek.phoenix.job.ServiceLevel#getChildServiceLevels()
     */
    @Override
    public Collection<ContractServiceLevel> getChildServiceLevels() {
        if(this.childServiceLevels == null){
            this.childServiceLevels = new HashSet<ContractServiceLevel>();
        }
        return childServiceLevels;
    }

    /**
     * @see com.intertek.phoenix.job.ServiceLevel#getParentServiceLevel()
     */
    @Override
    public ContractServiceLevel getParentServiceLevel() {
        return this.parentServiceLevel;
    }

    /**
     * @see com.intertek.phoenix.job.ServiceLevel#getServiceLevelName()
     */
    @Override
    public String getServiceLevelName() {
        return this.serviceLevelName;
    }

    /**
     * @see com.intertek.phoenix.job.ServiceLevel#getServiceLevelType()
     */
    @Override
    public ServiceLevelType getServiceLevelType() {
        return this.serviceLevelType;
    }

    public void setServiceLevelType(ServiceLevelType type) {
        this.serviceLevelType = type;
    }

    /**
     * @see com.intertek.phoenix.job.ServiceLevel#removeChildServiceLevel(com.intertek.phoenix.job.ServiceLevel)
     */
    @Override
    public boolean removeChildServiceLevel(ServiceLevel childServiceLevel) {
        return this.getChildServiceLevels().remove(childServiceLevel);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getSortOrderNum() {
        return sortOrderNum;
    }

    public void setSortOrderNum(int sortOrderNum) {
        this.sortOrderNum = sortOrderNum;
    }

    public Long getParentServiceLevelId() {
        return parentServiceLevelId;
    }

    public void setParentServiceLevelId(Long parentServiceLevelId) {
        this.parentServiceLevelId = parentServiceLevelId;
    }

    public void setServiceLevelName(String serviceLevelName) {
        this.serviceLevelName = serviceLevelName;
    }

    public void setParentServiceLevel(ContractServiceLevel parentServiceLevel) {
        if(parentServiceLevel != null){
            this.parentServiceLevelId = parentServiceLevel.getId();
        }
        this.parentServiceLevel = parentServiceLevel;
    }
    
    public Set<JobContractService> getJobContractServices() {
        if(jobContractServices == null){
            jobContractServices = new HashSet<JobContractService>();
        }
        return jobContractServices;
    }

    public Set<JobContractTest> getJobContractTests() {
        if(jobContractTests == null){
            jobContractTests = new HashSet<JobContractTest>();
        }
        return jobContractTests;
    }

    public Set<JobContractSlate> getJobContractSlates() {
        if(jobContractSlates == null){
            jobContractSlates = new HashSet<JobContractSlate>();
        }
        return jobContractSlates;
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

    
    public Long getJobServiceLevelId() {
        return jobServiceLevelId;
    }

    public void setJobServiceLevelId(Long jobServiceLevelId) {
        this.jobServiceLevelId = jobServiceLevelId;
    }

    public JobServiceLevel getJobServiceLevel() {
        return jobServiceLevel;
    }

    public void setJobServiceLevel(JobServiceLevel jobServiceLevel) {
        if(jobServiceLevel != null) {
            this.jobServiceLevelId = jobServiceLevel.getId();
        }
        
        this.jobServiceLevel = jobServiceLevel;
    }

    /**
     * @param class1
     * @param contractCode
     * @param serviceName
     * @param parentServiceId
     * @return
     */
    public <T extends JobContractService> T createJobContractService(Class<T> type, 
                                                       JobService jobService) {
        T jobContractService = null;
        try {
            jobContractService = type.newInstance();
            jobContractService.setJobService(jobService);
            //jobContractService.setContractId(contractCode);
            
            this.getJobContractServices().add(jobContractService);
            jobContractService.setContractServiceLevel(this);
            return jobContractService;
        }
        catch (Exception e) {
            return null;
        }
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @param jobTest
     * @return
     */
    public JobContractTest findJobContractTest(JobTest jobTest) {
        for(JobContractTest jct: this.getJobContractTests()){
            if(jct.getJobTest()!=null && jct.getJobTest().equals(jobTest)){
                return jct;
            }
        }
        return null;
    }

    /**
     * @param jobSlate
     * @return
     */
    public JobContractSlate findJobContractSlate(JobSlate jobSlate) {
        for(JobContractSlate jcs: this.getJobContractSlates()){
            if(jcs.getJobSlate().equals(jobSlate)){
                return jcs;
            }
        }
        return null;
    }

    /**
     * @param jobService
     * @return
     */
    public JobContractService findJobService(JobService jobService) {
        for(JobContractService jcs: this.getJobContractServices()){
            if(jcs.getJobService().equals(jobService)){
                return jcs;
            }
        }
        return null;
    }
    
}
