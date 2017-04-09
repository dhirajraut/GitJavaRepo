/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.entity;

import java.util.Date;
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
@Entity(name="com.intertek.phoenix.entity.JobService")
@Table(name="PHX_JOB_SERVICE")
public class JobService {
    @Id
    @SequenceGenerator(name="PHX_JobService_seq", sequenceName = "PHX_JOB_SERVICE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "PHX_JobService_seq" )
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "JOB_SERVICE_LEVEL_ID", updatable = false, insertable = false)
    private Long jobServiceLevelId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "JOB_SERVICE_LEVEL_ID", referencedColumnName="ID")
    @Index(name="PHX_JOB_SRVC_IDX_JOB_SRVC_LVL")
    private JobServiceLevel jobServiceLevel;
    
    @Column(name = "SERVICE_NAME", length = 60)
    private String serviceName;

    @Column(name="PARENT_SERVICE_ID", length = 96)
    private String parentServiceId;

    @Column(name="SERVICE_DATE")
    private Date serviceDate;
        
    /*
    @OneToOne
    @JoinColumns({
            @JoinColumn(name = "CONTRACT_ID", referencedColumnName = "CFG_CONTRACT_ID"),
            @JoinColumn(name = "SERVICE_NAME", referencedColumnName = "ITSC_SERVICE"),
            @JoinColumn(name = "PARENT_SERVICE_ID", referencedColumnName = "PARENT_SERVICE_ID"),
            @JoinColumn(name = "SERVICE_DATE", referencedColumnName = "BEGIN_DATE")
    })
    @org.hibernate.annotations.Cascade(org.hibernate.annotations.CascadeType.SAVE_UPDATE)
    private Service service;
    */
    
    @Column(name = "SERVICE_TYPE", length = 30)
    private String serviceType;
    
    @OneToMany(mappedBy = "jobService", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    private Set<JobServiceControl> serviceControls;
    
    @OneToMany(mappedBy = "jobService", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    private Set<JobServiceExpression> serviceExpressions;
    
//    // fields required to support revenue segregation
//    private JobContractServiceExpression mainServiceItem;
    
    @Column(name = "IS_ROLL_UP", precision = 1, scale = 0)
    private boolean isRollup;

    @Column(name = "BILLING_STATUS", length = 20)
    @Enumerated(EnumType.STRING)
    private BillingStatus billingStatus;
    @Column(name = "OPERATIONAL_STATUS", length = 20)
    @Enumerated(EnumType.STRING)
    private OperationalStatus operationalStatus;
    
    @OneToMany(mappedBy = "jobService", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    private Set<JobContractService> jobContractServices;
    
    public JobService() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JobServiceLevel getJobServiceLevel() {
        return jobServiceLevel;
    }

    public void setJobServiceLevel(JobServiceLevel serviceLevel) {
        if(serviceLevel != null){
            this.jobServiceLevelId = serviceLevel.getId();
        }
        this.jobServiceLevel = serviceLevel;
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

    public JobServiceExpression getMainServiceItem() {
        if(isRollup()){
            // TODO do a search in the list
            
        }
        return null;
    }

    public boolean isRollup() {
        return isRollup;
    }

    public void setRollup(boolean isRollup) {
        this.isRollup = isRollup;
    }

    public Set<JobServiceControl> getControls() {
        if(serviceControls == null){
            this.serviceControls = new HashSet<JobServiceControl>();
        }
        return serviceControls;
    }
    
    public JobServiceControl createJobServiceControl(Control control){
        JobServiceControl serviceControl = new JobServiceControl();
        serviceControl.setControl(control);
        serviceControl.setJobService(this);
        this.getControls().add(serviceControl);
        return serviceControl;
    }

    public void removeJobServiceControl(JobServiceControl control){
        this.getControls().remove(control);
    }

    public Set<JobServiceExpression> getServiceExpressions() {
        if(serviceExpressions == null){
            serviceExpressions = new HashSet<JobServiceExpression>();
        }
        return serviceExpressions;
    }

    public JobServiceExpression createJobServiceExpression(ContractExpression expression){
        JobServiceExpression exp = new JobServiceExpression();
        exp.setContractExpression(expression);
        exp.setJobService(this);
        this.getServiceExpressions().add(exp);
        return exp;
    }

    public void removeJobServiceExpression(JobServiceExpression expression){
        this.getServiceExpressions().remove(expression);
    }

    /*
    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }
    */

    public String getParentServiceId() {
        return parentServiceId;
    }

    public void setParentServiceId(String parentServiceId) {
        this.parentServiceId = parentServiceId;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public Long getJobServiceLevelId() {
        return jobServiceLevelId;
    }

    public void setJobServiceLevelId(Long jobServiceLevelId) {
        this.jobServiceLevelId = jobServiceLevelId;
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

    public Set<JobContractService> getJobContractServices() {
        if(jobContractServices == null){
            jobContractServices = new HashSet<JobContractService>();
        }
        return jobContractServices;
    }
    
//    public boolean addJobContractService(JobContractService jcs){
//        jcs.setJobService(this);
//        return getJobContractServices().add(jcs);
//    }
//    
    public boolean removeJobContractService(JobContractService jcs){
        return getJobContractServices().remove(jcs);
    }

    /**
     * @param control
     * @return
     */
    public JobServiceControl getJobServiceControl(Control control) {
        for(JobServiceControl jsc: this.getControls()){
            if(control.equals(jsc.getControl())){ 
                // make sure the equals() and hashcode() are there TODO
                return jsc;
            }
        }
        return null;
    }
}
