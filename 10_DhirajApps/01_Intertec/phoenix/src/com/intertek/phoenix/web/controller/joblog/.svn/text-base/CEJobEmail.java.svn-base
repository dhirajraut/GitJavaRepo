package com.intertek.phoenix.web.controller.joblog;

import java.util.ArrayList;

import org.springmodules.validation.bean.conf.loader.annotation.handler.CascadeValidation;

import com.intertek.phoenix.entity.CEJobContract;
import com.intertek.phoenix.entity.CEJobOrder;

public class CEJobEmail {
    @CascadeValidation
    private ArrayList<JobLogCEMailForm> jobLogCEMailFormList;
    private String to;
    private String cc;
    private String subject;
    private String coordinator;
    private String introduction;
    private String introductionToDisplay;
    private String custCode;
    private String emailNoteDisplay;
    private String emailNote;
    private long contactId;
    private boolean emailSentFlag = false;
    private CEJobOrder ceJobOrder;
    private CEJobContract contract;
    
    
    public boolean isOrderExit(String jobNumber) {        
        if (jobLogCEMailFormList != null){
            for(JobLogCEMailForm form:jobLogCEMailFormList)
            {
                if(form.getCeJobOrder().getJobNumber().equals(jobNumber)){
                    return true;
                }
            }
            return false;
        }
        else{
            return false;
        }
    }
    
    
    public CEJobOrder getCeJobOrder() {
        if (jobLogCEMailFormList != null){
            return jobLogCEMailFormList.get(0).getCeJobOrder();
        }
        else{
            return ceJobOrder;
        }
    }

    public void setCeJobOrder(CEJobOrder ceJobOrder) {
        this.ceJobOrder = ceJobOrder;
    }

    public String getEmailNoteDisplay() {
        return emailNoteDisplay;
    }

    public void setEmailNoteDisplay(String emailNoteDisplay) {
        this.emailNoteDisplay = emailNoteDisplay;
    }

    public String getEmailNote() {
        return emailNote;
    }

    public void setEmailNote(String emailNote) {
        this.emailNote = emailNote;
    }

    public String getCc() {
        return cc;
    }

    public void setCc(String cc) {
        this.cc = cc;
    }

    public String getCoordinator() {
        return coordinator;
    }

    public void setCoordinator(String coordinator) {
        this.coordinator = coordinator;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public ArrayList<JobLogCEMailForm> getjobLogCEMailFormList() {
        return jobLogCEMailFormList;
    }

    public void setjobLogCEMailFormList(ArrayList<JobLogCEMailForm> jobLogCEMailFormList) {
        this.jobLogCEMailFormList = jobLogCEMailFormList;
    }

    public String getCustCode() {
        return custCode;
    }

    public void setCustCode(String custCode) {
        this.custCode = custCode;
    }

    public long getContactId() {
        return contactId;
    }

    public void setContactId(long contactId) {
        this.contactId = contactId;
    }

    public boolean isEmailSentFlag() {
        return emailSentFlag;
    }

    public void setEmailSentFlag(boolean emailSentFlag) {
        this.emailSentFlag = emailSentFlag;
    }

    /**
     * @return the introductionToDisplay
     */
    public String getIntroductionToDisplay() {
        return introductionToDisplay;
    }

    /**
     * @param introductionToDisplay
     *            the introductionToDisplay to set
     */
    public void setIntroductionToDisplay(String introductionToDisplay) {
        this.introductionToDisplay = introductionToDisplay;
    }

    public void addJobLogMailForm(JobLogCEMailForm jobLogMailForm) {
        if (jobLogCEMailFormList == null) {
            jobLogCEMailFormList = new ArrayList<JobLogCEMailForm>();
        }
        jobLogCEMailFormList.add(jobLogMailForm);
    }

    public CEJobContract getContract() {
        return contract;
    }

    public void setContract(CEJobContract contract) {
        this.contract = contract;
    }
    
    
}
