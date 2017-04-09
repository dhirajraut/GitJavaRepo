/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.intertek.entity.ContractExpression;

/**
 * 
 * @author richard.qin
 */
@Entity(name="com.intertek.phoenix.entity.JobServiceExpression")
@Table(name="PHX_JOB_SERVICE_EXPRESSION")
public class JobServiceExpression {
    @Id
    @SequenceGenerator(name="PHX_JobServiceCtl_seq", sequenceName = "PHX_JOB_SERVICE_CTL_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "PHX_JobServiceCtl_seq" )
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "JOB_SERVICE_ID", updatable = false, insertable = false)
    private Long jobServiceId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "JOB_SERVICE_ID", referencedColumnName="ID")
    @Index(name="PHX_JSE_IDX_JOB_SRVC")
    private JobService jobService;
    
    @Column(name="CONTRACT_ID", length=45, updatable = false, insertable = false)
    private String contractId;
    @Column(name = "SERVICE_NAME", length = 60, updatable = false, insertable = false)
    // TODO why the column size is 375 in PS_ITSC_CONTROL?
    private String serviceName;
    @Column(name = "EXPRESSION_ID", length = 105, nullable=false, updatable = false, insertable = false)
    private String expressionId;
    @Column(name = "LOCATION", length = 105, nullable=false, updatable = false, insertable = false)
    private String location;
    @Column(name="SERVICE_DATE", nullable=false, updatable = false, insertable = false)
    private Date serviceDate;
    
    @OneToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumns({
            @JoinColumn(name = "CONTRACT_ID", referencedColumnName = "CFG_CONTRACT_ID"),
            @JoinColumn(name = "SERVICE_NAME", referencedColumnName = "ITSC_SERVICE"),
            @JoinColumn(name = "EXPRESSION_ID", referencedColumnName = "EXPRESSION_ID"),
            @JoinColumn(name = "LOCATION", referencedColumnName = "CFG_LOCATION"),
            @JoinColumn(name = "SERVICE_DATE", referencedColumnName = "BEGIN_DATE")
    })
    private ContractExpression contractExpression;
    
    @Column(name = "SORT_ORDER_NUMBER")
    private int sortOrderNum;
    
    @Column(name = "IS_MAIN_EXPRESSION", precision = 1, scale = 0)
    private boolean isMainExpression;

    
    public JobServiceExpression(){
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JobService getJobService() {
        return jobService;
    }

    public void setJobService(JobService jobService) {
        if(jobService != null){
            this.jobServiceId = jobService.getId();
        }
        this.jobService = jobService;
    }

    public ContractExpression getContractExpression() {
        return contractExpression;
    }

    public void setContractExpression(ContractExpression contractExpression) {
        if(contractExpression != null){
            this.contractId = contractExpression.getContractExpressionId().getContractId();
            this.serviceName = contractExpression.getContractExpressionId().getServiceName();
            this.expressionId = contractExpression.getContractExpressionId().getExpressionId();
            this.location = contractExpression.getContractExpressionId().getLocation();
            this.serviceDate = contractExpression.getContractExpressionId().getBeginDate();
        }
        this.contractExpression = contractExpression;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getSortOrderNum() {
        return sortOrderNum;
    }

    public void setSortOrderNum(int sortOrderNum) {
        this.sortOrderNum = sortOrderNum;
    }

    public boolean isMainExpression() {
        return isMainExpression;
    }

    public void setMainExpression(boolean isMainExpression) {
        this.isMainExpression = isMainExpression;
    }

    public Long getJobServiceId() {
        return jobServiceId;
    }

    public void setJobServiceId(Long jobServiceId) {
        this.jobServiceId = jobServiceId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getExpressionId() {
        return expressionId;
    }

    public void setExpressionId(String expressionId) {
        this.expressionId = expressionId;
    }

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }
    
}
