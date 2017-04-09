package com.intertek.phoenix.web.controller.job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springmodules.validation.bean.conf.loader.annotation.handler.CascadeValidation;

import com.intertek.phoenix.common.Form;
import com.intertek.phoenix.entity.JobTest;
import com.intertek.phoenix.entity.JobTestAttachment;
import com.intertek.phoenix.entity.JobTestNote;

public class JobInstructionAttachFileForm extends Form{
    @CascadeValidation
    private List<JobTestAttachment> attachmentList;
    @SuppressWarnings("unchecked")
    List<HashMap> fileInfoMapList = new ArrayList<HashMap>();

    private JobTest jobTest;
    private String divName;
    private String inputFieldId;
    private String deleteAttachId;
    private String orderId;
    private String jobOrderNumber;
    private String requestAction;
    private long jobTestId;
    
    
    public long getJobTestId() {
        return jobTestId;
    }

    public void setJobTestId(long jobTestId) {
        this.jobTestId = jobTestId;
    }

    public List<JobTestAttachment> getAttachmentList() {
        List<JobTestAttachment> attachmentTmpList = new ArrayList<JobTestAttachment>();
        if(jobTest.getAttachments()!=null && jobTest.getAttachments().size()>0){
            for(JobTestAttachment attachment: jobTest.getAttachments()){
                attachmentTmpList.add(attachment);
            }
        }
        this.attachmentList=attachmentTmpList;
        return attachmentTmpList;
    }

    public void setAttachmentList(List<JobTestAttachment> attachmentList) {
        this.attachmentList = attachmentList;
    }

    public JobTest getJobTest() {
        return jobTest;
    }

    public void setJobTest(JobTest jobTest) {
        this.jobTest = jobTest;
    }


    @SuppressWarnings("unchecked")
    public List<HashMap> getFileInfoMapList() {
        return fileInfoMapList;
    }

    @SuppressWarnings("unchecked")
    public void setFileInfoMapList(List<HashMap> fileInfoMapList) {
        this.fileInfoMapList = fileInfoMapList;
    }

    public String getDivName() {
        return divName;
    }

    public void setDivName(String divName) {
        this.divName = divName;
    }

    public String getInputFieldId() {
        return inputFieldId;
    }

    public void setInputFieldId(String inputFieldId) {
        this.inputFieldId = inputFieldId;
    }

    public String getDeleteAttachId() {
        return deleteAttachId;
    }

    public void setDeleteAttachId(String deleteAttachId) {
        this.deleteAttachId = deleteAttachId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getJobOrderNumber() {
        return jobOrderNumber;
    }

    public void setJobOrderNumber(String jobOrderNumber) {
        this.jobOrderNumber = jobOrderNumber;
    }

    public String getRequestAction() {
        return requestAction;
    }

    public void setRequestAction(String requestAction) {
        this.requestAction = requestAction;
    }

}
