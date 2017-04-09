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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

import com.intertek.entity.Control;

/**
 * 
 * @author richard.qin
 */
@Entity(name="com.intertek.phoenix.entity.JobContractServiceControl")
@Table(name="PHX_JOB_CONTRACT_SRVC_CTL")
public class JobContractServiceControl {
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
    @Index(name="PHX_JC_SRVC_CTL_IDX_JC_SRVC")
    private JobContractService jobContractService;
    
    @Column(name = "JOB_SERVICE_CONTROL_ID", updatable = false, insertable = false)
    private Long jobServiceControlId;
    @OneToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumn(name = "JOB_SERVICE_CONTROL_ID", nullable=true) 
    @Index(name="PHX_JC_SRVC_CTL_IDX_J_SRVC_CTL")
    private JobServiceControl jobServiceControl;
    
    @Column(name="CONTRACT_ID", length=45, updatable = false, insertable = false)
    private String contractId;
    @Column(name = "SERVICE_NAME", length = 60, updatable = false, insertable = false)
    // TODO why the column size is 375 in PS_ITSC_CONTROL?
    private String serviceName;
    @Column(name = "QUESTION_ID", length = 96, nullable=false, updatable = false, insertable = false)
    private String questionId;
    @Column(name = "OBJECT_NAME", length = 96, nullable=false, updatable = false, insertable = false)
    private String objectName;
    @Column(name="SERVICE_DATE", nullable=false, updatable = false, insertable = false)
    private Date serviceDate;
    
    @ManyToOne()
    @org.hibernate.annotations.Cascade({org.hibernate.annotations.CascadeType.SAVE_UPDATE, 
        org.hibernate.annotations.CascadeType.MERGE})
    @JoinColumns({
            @JoinColumn(name = "CONTRACT_ID", referencedColumnName = "CFG_CONTRACT_ID"),
            @JoinColumn(name = "SERVICE_NAME", referencedColumnName = "ITSC_SERVICE"),
            @JoinColumn(name = "QUESTION_ID", referencedColumnName = "ITS_QUESTION_ID"),
            @JoinColumn(name = "OBJECT_NAME", referencedColumnName = "OBJECT_NAME"),
            @JoinColumn(name = "SERVICE_DATE", referencedColumnName = "BEGIN_DATE")
    })
    private Control control;
    
    @Column(name = "INPUT_VALUE_0", length = 256)
    private String inputValue0;
    @Column(name = "INPUT_VALUE_1", length = 256)
    private String inputValue1;
    @Column(name = "INPUT_VALUE_2", length = 256)
    private String inputValue2;
    @Column(name = "INPUT_VALUE_3", length = 256)
    private String inputValue3;
    @Column(name = "INPUT_VALUE_4", length = 256)
    private String inputValue4;
    
    // this field is not in phoenix, but it will make something much simpler to implement
    @Column(name = "CONTROL_TYPE", length = 30)
    private String controlType;
    
    @OneToMany(mappedBy = "jobContractServiceControl")
    private Set<JobContractServiceExpression> jobContractServiceExpressions;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public JobContractService getJobContractService() {
        return jobContractService;
    }

    public void setJobContractService(JobContractService jobContractService) {
        if(jobContractService != null){
            this.jobContractServiceId = jobContractService.getId();
        }
        this.jobContractService = jobContractService;
    }

    public String getQuestionId() {
        return questionId;
    }

    public void setQuestionId(String questionId) {
        this.questionId = questionId;
    }

    public String getObjectName() {
        return objectName;
    }

    public void setObjectName(String objectName) {
        this.objectName = objectName;
    }

    public String getInputValue0() {
        return inputValue0;
    }

    public void setInputValue0(String inputValue0) {
        this.inputValue0 = inputValue0;
    }

    public String getInputValue1() {
        return inputValue1;
    }

    public void setInputValue1(String inputValue1) {
        this.inputValue1 = inputValue1;
    }

    public String getInputValue2() {
        return inputValue2;
    }

    public void setInputValue2(String inputValue2) {
        this.inputValue2 = inputValue2;
    }

    public String getInputValue3() {
        return inputValue3;
    }

    public void setInputValue3(String inputValue3) {
        this.inputValue3 = inputValue3;
    }

    public String getInputValue4() {
        return inputValue4;
    }

    public void setInputValue4(String inputValue4) {
        this.inputValue4 = inputValue4;
    }

    public String getControlType() {
        return controlType;
    }

    public void setControlType(String controlType) {
        this.controlType = controlType;
    }

    public JobServiceControl getJobServiceControl() {
        return jobServiceControl;
    }

    public void setJobServiceControl(JobServiceControl jobServiceControl) {
        this.jobServiceControl = jobServiceControl;
    }

    public Long getJobContractServiceId() {
        return jobContractServiceId;
    }

    public void setJobContractServiceId(Long jobContractServiceId) {
        this.jobContractServiceId = jobContractServiceId;
    }

    public Long getJobServiceControlId() {
        return jobServiceControlId;
    }

    public void setJobServiceControlId(Long jobServiceControlId) {
        this.jobServiceControlId = jobServiceControlId;
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

    public Date getServiceDate() {
        return serviceDate;
    }

    public void setServiceDate(Date serviceDate) {
        this.serviceDate = serviceDate;
    }

    public Control getControl() {
        return control;
    }

    public void setControl(Control control) {
        if(control != null){
            this.contractId = control.getControlId().getContractId();
            this.serviceName = control.getControlId().getServiceName();
            this.serviceDate = control.getControlId().getBeginDate();
            this.questionId = control.getControlId().getQuestionId();
            this.objectName = control.getControlId().getObjectName();
        }
        this.control = control;
    }

    public Set<JobContractServiceExpression> getJobContractServiceExpressions() {
        if(jobContractServiceExpressions == null){
            jobContractServiceExpressions = new HashSet<JobContractServiceExpression>();
        }
        return jobContractServiceExpressions;
    }

    public boolean addJobContractServiceExpression(JobContractServiceExpression jcse){
        return this.getJobContractServiceExpressions().add(jcse);
    }

    public boolean removeJobContractServiceExpression(JobContractServiceExpression jcse){
        return this.getJobContractServiceExpressions().remove(jcse);
    }
}
