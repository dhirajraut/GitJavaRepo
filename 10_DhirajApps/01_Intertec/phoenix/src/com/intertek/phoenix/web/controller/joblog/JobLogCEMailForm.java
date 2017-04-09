package com.intertek.phoenix.web.controller.joblog;

import org.springmodules.validation.bean.conf.loader.annotation.handler.CascadeValidation;

import com.intertek.entity.Contract;
import com.intertek.entity.User;
import com.intertek.phoenix.common.Form;
import com.intertek.phoenix.entity.CEJobContract;
import com.intertek.phoenix.entity.CEJobOrder;

public class JobLogCEMailForm extends Form {
    @CascadeValidation
    private CEJobEmail[] jobEmails;
    private User user;
    private int emailIndex = 0;
    private int emailCount;
    private String timeFormat;
    private CEJobOrder ceJobOrder;
    private CEJobContract contract;
    private String emailNote;
    private String emailNoteDisplay;

    public CEJobContract getContract() {
        return contract;
    }

    public void setContract(CEJobContract contract) {
        this.contract = contract;
    }

    public CEJobOrder getCeJobOrder() {
        return ceJobOrder;
    }

    public void setCeJobOrder(CEJobOrder ceJobOrder) {
        this.ceJobOrder = ceJobOrder;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public CEJobEmail[] getJobEmails() {
        return jobEmails;
    }

    public void setJobEmails(CEJobEmail[] jobEmails) {
        this.jobEmails = jobEmails;
    }

    public int getEmailCount() {
        return emailCount;
    }

    public void setEmailCount(int emailCount) {
        this.emailCount = emailCount;
    }

    public int getEmailIndex() {
        return emailIndex;
    }

    public void setEmailIndex(int emailIndex) {
        this.emailIndex = emailIndex;
    }

    /**
     * @return the timeFormat
     */
    public String getTimeFormat() {
        return timeFormat;
    }

    /**
     * @param timeFormat
     *            the timeFormat to set
     */
    public void setTimeFormat(String timeFormat) {
        this.timeFormat = timeFormat;
    }

    public String getEmailNote() {
        return emailNote;
    }

    public void setEmailNote(String emailNote) {
        this.emailNote = emailNote;
    }

    public String getEmailNoteDisplay() {
        return emailNoteDisplay;
    }

    public void setEmailNoteDisplay(String emailNoteDisplay) {
        this.emailNoteDisplay = emailNoteDisplay;
    }

    public String getJobNumber() {
        return ceJobOrder.getJobNumber();
    }

    public String getContractCode() {
        return ceJobOrder.getJobContract().getContractCode();
    }

    public String getEtaTimeTz() {
        return "";
    }

    public String getInvoiceType() {
        return ceJobOrder.getJobContract().getInvoiceType();
    }

    public String getBranchName() {
        if (ceJobOrder.getBranch() != null) {
            return ceJobOrder.getBranch().getName();
        }
        else {
            return ceJobOrder.getBranchName();
        }
    }

    public String getTimeZone() {
        // TODO: set the time zone
        return "";
    }

    public String getOperation() {
        if (ceJobOrder.getOperationalStatus() != null) {
            return ceJobOrder.getOperationalStatus().getDescription();
        }
        else {
            return "";
        }
    }

    public String getServiceLocation() {
        if (ceJobOrder.getServiceLocation() != null) {
            return ceJobOrder.getServiceLocation().getName();
        }
        else {
            return "";
        }
    }

    public String getServiceLocationCity() {
        if (ceJobOrder.getServiceLocation() != null) {
            return ceJobOrder.getServiceLocation().getCity();
        }
        else {
            return "";
        }
    }

}
