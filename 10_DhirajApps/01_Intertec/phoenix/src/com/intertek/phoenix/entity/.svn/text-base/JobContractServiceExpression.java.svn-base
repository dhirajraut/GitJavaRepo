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
@Entity(name="com.intertek.phoenix.entity.JobContractServiceExpression")
@Table(name="PHX_JC_SERVICE_EXPRESSION")
public class JobContractServiceExpression {
    @Id
    @SequenceGenerator(name="PHX_JobContSrvcCtl_seq", 
                       sequenceName = "PHX_JOB_CONT_SRVC_CTL_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "PHX_JobContSrvcCtl_seq" )
    @Column(name = "ID")
    private Long id;
    
    @Column(name = "JOB_CONTRACT_SERVICE_ID", updatable = false, insertable = false)
    private Long jobContractServiceId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "JOB_CONTRACT_SERVICE_ID", referencedColumnName="ID")
    @Index(name="PHX_JCSE_IDX_JOB_SRVC")
    private JobContractService jobContractService;
    
    @Column(name = "JOB_ORDER_LINE_ITEM_ID", updatable = false, insertable = false)
    private Long jobOrderLineItemId;
    @OneToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "JOB_ORDER_LINE_ITEM_ID")
    @Index(name="PHX_JCSE_IDX_JOLI")
    private CEJobOrderLineItem jobOrderLineItem; 
    
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
    
    @ManyToOne()
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
    private Integer sortOrderNum;
    
    @Column(name = "IS_MAIN_EXPRESSION", precision = 1, scale = 0)
    private boolean isMainExpression;
    
    @Column(name="JOB_CNTR_SRVC_CTRL_ID", updatable = false, insertable = false)
    private Long JobContractServiceControlId;
    @ManyToOne
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "JOB_CNTR_SRVC_CTRL_ID")
    private JobContractServiceControl jobContractServiceControl;

    public JobContractServiceExpression(){
        
    }
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JobContractService getJobContractService() {
        return jobContractService;
    }

    public void setJobContractService(JobContractService value) {
        if(value != null){
            this.jobContractServiceId = value.getId();
        }
        this.jobContractService = value;
    }

    public ContractExpression getContractExpression() {
        return contractExpression;
    }

    public void setContractExpression(ContractExpression contractExpression) {
        if(contractExpression != null){
            contractId = contractExpression.getContractExpressionId().getContractId();
            serviceName = contractExpression.getContractExpressionId().getServiceName();
            expressionId = contractExpression.getContractExpressionId().getExpressionId();
            location = contractExpression.getContractExpressionId().getLocation();
            serviceDate = contractExpression.getContractExpressionId().getBeginDate();
        }
        this.contractExpression = contractExpression;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getSortOrderNum() {
        return sortOrderNum;
    }

    public void setSortOrderNum(Integer sortOrderNum) {
        this.sortOrderNum = sortOrderNum;
    }

    public CEJobOrderLineItem getJobOrderLineItem() {
        return jobOrderLineItem;
    }
    
    public void setJobOrderLineItem(CEJobOrderLineItem jobOrderLineItem){
        if(jobOrderLineItem != null){
            this.jobContractServiceId = jobOrderLineItem.getId();
        }
        this.jobOrderLineItem = jobOrderLineItem;
    }

    public boolean isMainExpression() {
        return isMainExpression;
    }

    public void setMainExpression(boolean isMain) {
        this.isMainExpression = isMain;
    }

    public Long getJobContractServiceId() {
        return jobContractServiceId;
    }

    public void setJobContractServiceId(Long jobContractServiceId) {
        this.jobContractServiceId = jobContractServiceId;
    }

    public Long getJobOrderLineItemId() {
        return jobOrderLineItemId;
    }

    public void setJobOrderLineItemId(Long jobOrderLineItemId) {
        this.jobOrderLineItemId = jobOrderLineItemId;
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

    public Long getJobContractServiceControlId() {
        return JobContractServiceControlId;
    }

    public void setJobContractServiceControlId(Long jobContractServiceControlId) {
        JobContractServiceControlId = jobContractServiceControlId;
    }

    public JobContractServiceControl getJobContractServiceControl() {
        return jobContractServiceControl;
    }

    public void setJobContractServiceControl(JobContractServiceControl jobContractServiceControl) {
        if(jobContractServiceControl != null){
            this.JobContractServiceControlId = jobContractServiceControl.getId();
        }
        this.jobContractServiceControl = jobContractServiceControl;
    }
}
