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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.intertek.entity.Service;
import com.intertek.entity.Slate;
import com.intertek.entity.Test;
import com.intertek.phoenix.job.JobServiceLevelInt;
import com.intertek.phoenix.job.ServiceLevel;
import com.intertek.util.Constants;

/**
 * 
 * @author richard.qin
 */
@Entity
@Table(name="PHX_JOB_SERVICE_LEVEL")
public class JobServiceLevel implements JobServiceLevelInt {
    @Id
    @SequenceGenerator(name="PHX_JobServiceLevel_seq", sequenceName = "PHX_JOB_SERVICE_LEVEL_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "PHX_JobServiceLevel_seq" )
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "SERVICE_LEVEL_NAME", length = 50)
    private String serviceLevelName;
    
    @Column(name = "TYPE", length = 20)
    @Enumerated(EnumType.STRING)
    private ServiceLevelType serviceLevelType;
    
    @Column(name = "SORT_ORDER_NUM", precision = 4, scale = 0)
    private int sortOrderNum; 
    
    @Column(name = "JOB_ORDER_NUMBER", updatable = false, insertable = false)
    private String jobOrderNumber;

    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "JOB_ORDER_NUMBER", referencedColumnName="JOB_NUMBER")
    @Index(name="PHX_JOB_SRVC_LVL_IDX_JO_NUMBER")
    private CEJobOrder jobOrder;
    
    @Column(name = "PARENT_SERVICE_LEVEL_ID", updatable = false, insertable = false)
    private Long parentServiceLevelId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "PARENT_SERVICE_LEVEL_ID")
    @Index(name="PHX_JOB_SRVC_LVL_IDX_PARENT")
    private JobServiceLevel parentServiceLevel;
    
    //TODO: need to revisit the fetch Type 
    @OneToMany( mappedBy="parentServiceLevel", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    private Set<JobServiceLevel> childServiceLevels;
    
    @OneToMany( mappedBy="jobServiceLevel", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    private Set<JobService> jobServices;
    
    @OneToMany( mappedBy="jobServiceLevel", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    private Set<JobTest> jobTests;
    
    @OneToMany( mappedBy="jobServiceLevel", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    private Set<JobSlate> jobSlates;
    
    @Column(name = "DESCRIPTION", length = 50)
    private String description;
    
    public JobServiceLevel(){
        
    }

    /**
     * @see com.intertek.phoenix.job.JobServiceLevelInt#createJobService(com.intertek.entity.Service)
     */
    @Override
    public <T extends JobService> T createJobService(Class<T> type, Service service) {
        try {
            T jobService = type.newInstance();
            jobService.setServiceName(service.getServiceId().getServiceName());
            jobService.setParentServiceId(service.getServiceId().getParentServiceId());
            jobService.setRollup(service.getRollUp());
            
            // the caller must set the rest of the details, such as serviceType
            
            this.getJobServices().add(jobService);
            jobService.setJobServiceLevel(this);
            return jobService;
        }
        catch (Exception e) {
            // TODO need to handle error here
            return null;
        }
    }

    /**
     * @see com.intertek.phoenix.job.JobServiceLevelInt#createJobSlaet(com.intertek.entity.Slate)
     */
    @Override
    public <T extends JobSlate> T createJobSlate(Class<T> type, Slate slate) {
        try {
            T jobSlate = type.newInstance();
            jobSlate.setSlate(slate);
            jobSlate.setQuantity(Constants.DEFAULT_TEST_SLATE_QTY);
            jobSlate.setOt(false); 
            
            this.getJobSlates().add(jobSlate); 
            jobSlate.setJobServiceLevel(this);
            return jobSlate;
        }
        catch (Exception e) {
            // TODO need to handle error here
            return null;
        }
    }

    /**
     * @see com.intertek.phoenix.job.JobServiceLevelInt#createJobTest(com.intertek.entity.Test)
     */
    @Override
    public <T extends JobTest> T createJobTest(Class<T> type, Test test){
        try {
            T jobTest = type.newInstance();
            if(test!=null){
                jobTest.setTest(test); // if test is null, it is a manual test
            }
            jobTest.setBillingStatus(BillingStatus.OPEN);
            jobTest.setOperationalStatus(OperationalStatus.Unscheduled); 
            jobTest.setQuantity(Constants.DEFAULT_TEST_SLATE_QTY);
            // TODO how to really set this value? Or it's up to the caller?
            jobTest.setOt(false);
            // TODO this should be the formatted rb text
            if(test!=null){
                jobTest.setLineDescription(test.getTestDescription());
            }
            // otherwise, the description will be set by a uer manually.
            
            // TODO move this logic to a seperate method, because when
            // a user deletes a jobTest, the numbering needs to be rearranged
            // all all JobTests.
            // Code for Line # in JobInstruction Page.
            int max=0;
            for(JobTest jt: this.getJobTests()) {                
                if(max <jt.getLinenumber()) {                   
                    max = (int) jt.getLinenumber();
                    
                }               
            }  
            max++;
            jobTest.setLinenumber(max);
            
            // make it part of me
            this.addJobTest(jobTest);
            return jobTest;
        }
        catch (Exception e) {            
            // TODO need to handle error here
            return null;
        }
    }

    /**
     * @see com.intertek.phoenix.job.JobServiceLevelInt#removeJobService(com.intertek.phoenix.entity.JobService)
     */
    @Override
    public boolean removeJobService(JobService service) {
        return this.getJobServices().remove(service);
    }
    public boolean addJobService(JobService service){
        service.setJobServiceLevel(this);
        return this.getJobServices().add(service);
    }


    /**
     * @see com.intertek.phoenix.job.JobServiceLevelInt#removeJobSlate(com.intertek.phoenix.entity.JobSlate)
     */
    @Override
    public boolean removeJobSlate(JobSlate slate) {
        return this.getJobSlates().remove(slate);
    }

    public boolean addJobSlate(JobSlate slate){
        slate.setJobServiceLevel(this);
        return this.getJobSlates().add(slate);
    }
    /**
     * @see com.intertek.phoenix.job.JobServiceLevelInt#removeJobTest(com.intertek.phoenix.entity.JobTest)
     */
    @Override
    public boolean removeJobTest(JobTest test) {
        boolean b = this.getJobTests().remove(test);
        if(b){
            // re-order the remaining
            for(JobTest jobTest: getJobTests()){
                long lineNumber = jobTest.getLinenumber();
                if(lineNumber > test.getLinenumber()){
                    jobTest.setLinenumber(lineNumber-1);
                }
            }
        }
        return b;
    }
    
    public boolean addJobTest(JobTest test){
        test.setJobServiceLevel(this);
        return this.getJobTests().add(test);
    }

    /**
     * @see com.intertek.phoenix.job.ServiceLevel#addChildServiceLevel(com.intertek.phoenix.job.ServiceLevel)
     */
    @Override
    public JobServiceLevel createChildServiceLevel(ServiceLevelType type, String name) {
        JobServiceLevel child = new JobServiceLevel();
        child.setJobOrder(this.getJobOrder());
        child.setServiceLevelName(name);
        child.setServiceLevelType(type);        
        child.setParentServiceLevel(this);
        this.getChildServiceLevels().add(child);
        return child;
    }

    /**
     * @see com.intertek.phoenix.job.ServiceLevel#getChildServiceLevels()
     */
    @Override
    public Collection<JobServiceLevel> getChildServiceLevels() {
        if(this.childServiceLevels == null){
            this.childServiceLevels = new HashSet<JobServiceLevel>();
        }
        return childServiceLevels;
    }

    /**
     * @see com.intertek.phoenix.job.ServiceLevel#getParentServiceLevel()
     */
    @Override
    public JobServiceLevel getParentServiceLevel() {
        return this.parentServiceLevel;
    }

    /**
     * @see com.intertek.phoenix.job.ServiceLevel#getServiceLevelName()
     */
    @Override
    public String getServiceLevelName() {
        return this.serviceLevelName;
    }

    public void setServiceLevelName(String name) {
        this.serviceLevelName = name;
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @see com.intertek.phoenix.job.ServiceLevel#removeChildServiceLevel(com.intertek.phoenix.job.ServiceLevel)
     */
    @Override
    public boolean removeChildServiceLevel(ServiceLevel childServiceLevel) {
        return this.getChildServiceLevels().remove(childServiceLevel);
    }

    @Override
    public Set<JobService> getJobServices() {
        if(jobServices == null){
            jobServices = new HashSet<JobService>();
        }
        return jobServices;
    }
    
    @Override
    public Set<JobTest> getJobTests() {
        if(jobTests == null){
            jobTests = new HashSet<JobTest>();
        }
        return jobTests;
    }

    @Override
    public Set<JobSlate> getJobSlates() {
        if(jobSlates == null){
            jobSlates = new HashSet<JobSlate>();
        }
        return jobSlates;
    }

    public Long getParentServiceLevelId() {
        return parentServiceLevelId;
    }

    public void setParentServiceLevelId(Long parentServiceLevelId) {
        this.parentServiceLevelId = parentServiceLevelId;
    }

    public int getSortOrderNum() {
        return sortOrderNum;
    }

    public void setSortOrderNum(int sortOrderNum) {
        this.sortOrderNum = sortOrderNum;
    }

    public String getJobOrderNumber() {
        return jobOrderNumber;
    }

    public void setJobOrderNumber(String jobOrderNumber) {
        this.jobOrderNumber = jobOrderNumber;
    }

    public CEJobOrder getJobOrder() {
        return jobOrder;
    }

    public void setJobOrder(CEJobOrder jobOrder) {
        if (jobOrder != null){
            this.jobOrderNumber = jobOrder.getJobNumber();
        }
        this.jobOrder = jobOrder;
    }

    public void setParentServiceLevel(JobServiceLevel parentServiceLevel){
        if (parentServiceLevel != null){
            this.parentServiceLevelId = parentServiceLevel.getId();
        }
        this.parentServiceLevel=parentServiceLevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    
}
