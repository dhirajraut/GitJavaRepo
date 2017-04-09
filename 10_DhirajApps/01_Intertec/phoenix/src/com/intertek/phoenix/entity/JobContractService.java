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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.intertek.entity.ContractExpression;
import com.intertek.entity.Control;

/**
 * 
 * @author richard.qin
 */
@Entity(name="com.intertek.phoenix.entity.JobContractService")
@Table(name="PHX_JOB_CONTRACT_SERVICE")
public class JobContractService {
    @Id
    @SequenceGenerator(name="PHX_JobContractService_seq", 
                       sequenceName = "PHX_JOB_CONTRACT_SERVICE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "PHX_JobContractService_seq" )
    @Column(name = "ID")
	private Long id;
    
    @Column(name = "CONTRACT_SERVICE_LEVEL_ID", updatable = false, insertable = false)
    private Long contractServiceLevelId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "CONTRACT_SERVICE_LEVEL_ID", referencedColumnName="ID")
    @Index(name="PHX_JC_SRVC_IDX_CONT_SRVC_LVL")
    private ContractServiceLevel contractServiceLevel;
    
    @Column(name = "JOB_SERVICE_ID", updatable = false, insertable = false)
    private Long jobServiceId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "JOB_SERVICE_ID", nullable=true) // not every JobContractService has a JobService
    @Index(name="PHX_JC_SRVC_IDX_JOB_SERVICE")
    private JobService jobService;
    
    @Column(name = "SERVICE_NAME", nullable=false, length = 60)
    private String serviceName;
    
    @Column(name = "PARENT_SERVICE_ID", length = 60)
    private String parentServiceId;
    
    @Column(name = "SERVICE_TYPE", length = 30)
    private String serviceType;
    
    @Column(name = "BILLING_STATUS", length = 20)
    @Enumerated(EnumType.STRING)
    private BillingStatus billingStatus;
    
    @OneToMany(mappedBy = "jobContractService", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    private Set<JobContractServiceControl> serviceControls;
    
    @OneToMany(mappedBy = "jobContractService", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    private Set<JobContractServiceExpression> serviceExpressions;
    
    // fields required to support revenue segregation
//    private JobContractServiceExpression mainServiceItem; 
    
    @Column(name = "IS_ROLL_UP", precision = 1, scale = 0)
    private boolean isRollup;
    
    public JobContractService(){
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getServiceType() {
        return serviceType;
    }

    public void setServiceType(String serviceType) {
        this.serviceType = serviceType;
    }

    public JobContractServiceExpression getMainServiceItem() {
        if(isRollup()){
            for(JobContractServiceExpression jcse: this.getJobContractServiceExpresions()){
                // TODO
            }
        }
        return null;
    }

    public boolean isRollup() {
        return isRollup;
    }

    public void setRollup(boolean isRollup) {
        this.isRollup = isRollup;
    }
    
    public Set<JobContractServiceControl> getControls() {
        if(this.serviceControls == null){
            this.serviceControls = new HashSet<JobContractServiceControl>();
        }
        return serviceControls;
    }
    
    public JobContractServiceControl createJobContractServiceControl(Control control){
        JobContractServiceControl jobServiceControl = new JobContractServiceControl();
        jobServiceControl.setJobContractService(this);
        jobServiceControl.setObjectName(control.getControlId().getObjectName());
        jobServiceControl.setQuestionId(control.getControlId().getQuestionId());
        // controlType is a new field, may need to introduce back to the old tables
        jobServiceControl.setControlType(control.getControlType());
        jobServiceControl.setControl(control);
        this.getControls().add(jobServiceControl);
        return jobServiceControl;
    }
    
    public boolean removeJobContractServiceControl(JobContractServiceControl control){
        // also removed related JobContractServiceExpression
        Set<JobContractServiceExpression> jcse = control.getJobContractServiceExpressions();
        this.getJobContractServiceExpresions().removeAll(jcse);
        
        return this.getControls().remove(control);
    }

    public Set<JobContractServiceExpression> getJobContractServiceExpresions() {
        if(this.serviceExpressions == null){
            this.serviceExpressions = new HashSet<JobContractServiceExpression>();
        }
        return serviceExpressions;
    }

    public JobContractServiceExpression createJobContractServiceExpression(ContractExpression expression){
        JobContractServiceExpression jse = new JobContractServiceExpression();
        jse.setContractExpression(expression);
        jse.setJobContractService(this);
        // other attribute must be filled by the caller
        
        this.getJobContractServiceExpresions().add(jse);
        return jse;
    }
    
    public boolean removeJobContractServiceExpression(JobContractServiceExpression jse){
        return this.getJobContractServiceExpresions().remove(jse);
    }

    public JobService getJobService() {
        return jobService;
    }

    public void setJobService(JobService jobService) {
        if(jobService != null){
            this.jobServiceId = jobService.getId();
            this.serviceName = jobService.getServiceName();
            this.serviceType = jobService.getServiceType();
        }
        this.jobService = jobService;
    }

    public Long getContractServiceLevelId() {
        return contractServiceLevelId;
    }

    public void setContractServiceLevelId(Long contractServiceLevelId) {
        this.contractServiceLevelId = contractServiceLevelId;
    }

    public Long getJobServiceId() {
        return jobServiceId;
    }

    public void setJobServiceId(Long jobServiceId) {
        this.jobServiceId = jobServiceId;
    }

    public String getParentServiceId() {
        return parentServiceId;
    }

    public void setParentServiceId(String parentServiceId) {
        this.parentServiceId = parentServiceId;
    }

    public BillingStatus getBillingStatus() {
        return billingStatus;
    }

    public void setBillingStatus(BillingStatus billingStatus) {
        this.billingStatus = billingStatus;
    }

    /**
     * @param control
     * @return
     */
    public JobContractServiceControl getJobContractServiceControl(Control control) {
        for(JobContractServiceControl jcsc: this.getControls()){
            if(control.equals(jcsc.getControl())){
                return jcsc;
            }
        }
        return null;
    }

}
