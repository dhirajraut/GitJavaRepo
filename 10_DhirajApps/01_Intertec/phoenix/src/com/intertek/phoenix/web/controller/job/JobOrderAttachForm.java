/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.web.controller.job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springmodules.validation.bean.conf.loader.annotation.handler.CascadeValidation;

import com.intertek.phoenix.entity.JobOrderAttachment;
import com.intertek.phoenix.entity.CEJobContract;

/**
 * @author patni
 * 
 */
public class JobOrderAttachForm {

    @CascadeValidation
    private List<JobOrderAttachment> attachmentList;
    @SuppressWarnings("unchecked")
    List<HashMap> fileInfoMapList = new ArrayList<HashMap>();
    private JobOrderAttachment attachment;
    private String divName;
    private String inputFieldId;
    private String deleteAttachId;
    private String jobOrderNumber;
    private String requestAction;
    private CEJobContract jobContract;
    private String jobContractId;

    public List<JobOrderAttachment> getAttachmentList() {
        List<JobOrderAttachment> attachTmpList = new ArrayList<JobOrderAttachment>();
        if (jobContract.getAttachments() != null && jobContract.getAttachments().size() > 0) {
            for (JobOrderAttachment attachment : jobContract.getAttachments()) {
                attachTmpList.add(attachment);
            }
        }
        this.attachmentList = attachTmpList;
        return attachTmpList;
    }

    public void setAttachmentList(List<JobOrderAttachment> attachmentList) {
        this.attachmentList = attachmentList;
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

    public CEJobContract getJobContract() {
        return jobContract;
    }

    public void setJobContract(CEJobContract jobContract) {
        this.jobContract = jobContract;
    }

    public JobOrderAttachment getAttachment() {
        return attachment;
    }

    public void setAttachment(JobOrderAttachment attachment) {
        this.attachment = attachment;
    }

    public String getJobContractId() {
        return jobContractId;
    }

    public void setJobContractId(String jobContractId) {
        this.jobContractId = jobContractId;
    }

}
