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

import com.intertek.entity.Control;

/**
 * 
 * @author richard.qin
 */
@Entity(name="com.intertek.phoenix.entity.JobServiceControl")
@Table(name="PHX_JOB_SERVICE_CONTROL")
public class JobServiceControl {
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
    @Index(name="PHX_JSC_IDX_JOB_SRVC")
    private JobService jobService;
    
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
    
    public JobServiceControl(){
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getJobServiceId() {
        return jobServiceId;
    }

    public void setJobServiceId(Long jobServiceId) {
        this.jobServiceId = jobServiceId;
    }

    public JobService getJobService() {
        return jobService;
    }

    public void setJobService(JobService jobService) {
        this.jobService = jobService;
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

    public Control getControl() {
        return control;
    }

    public void setControl(Control control) {
        this.controlType = control.getControlType();
        this.objectName = control.getControlId().getObjectName();
        this.contractId = control.getControlId().getContractId();
        this.questionId = control.getControlId().getQuestionId();
        this.serviceName = control.getControlId().getServiceName();
        
        this.control = control;
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

}
