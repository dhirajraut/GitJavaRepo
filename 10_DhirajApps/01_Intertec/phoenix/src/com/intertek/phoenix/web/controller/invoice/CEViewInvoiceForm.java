/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.web.controller.invoice;

import java.util.ArrayList;
import java.util.List;
import com.intertek.phoenix.common.Form;
import com.intertek.phoenix.entity.CEJobOrder;
import com.intertek.phoenix.entity.CEInvoice;
import com.intertek.entity.Customer;

/**
 * This class is to hold ceInvoice entity and all other fields needed on the
 * page.
 * 
 * @author patni
 * @author lily.sun
 */

public class CEViewInvoiceForm extends Form {

    private CEInvoice ceInvoice;
    private CEJobOrder jobOrder;
    private List<CEInvoiceForm> ceInvoiceFormList;
    private String creditFlag;
    private int index;
    private String readWriteFlag;
    private String creditReasonNote;
    private String creditReasonUser;
    private Customer customer;

    public CEJobOrder getJobOrder() {
        return jobOrder;
    }

    public void setJobOrder(CEJobOrder jobOrder) {
        this.jobOrder = jobOrder;
    }

    public CEInvoice getCeInvoice() {
        return ceInvoice;
    }

    public void setCeInvoice(CEInvoice ceInvoice) {
        this.ceInvoice = ceInvoice;
    }

    public String getCustomerName() {
        if (ceInvoice.getJobContract() == null || ceInvoice.getJobContract().getCustomer() == null) {
            return "";
        }
        return ceInvoice.getJobContract().getCustomer().getName();
    }

    public String getBillingContact() {

        if (ceInvoice.getJobContract() == null || ceInvoice.getJobContract().getBillingContact() == null) {
            return "";
        }
        return ceInvoice.getJobContract().getBillingContact().getFirstName() + " "
               + ceInvoice.getJobContract().getBillingContact().getLastName();
    }

    public String getCreditFlag() {
        return creditFlag;
    }

    public void setCreditFlag(String creditFlag) {
        this.creditFlag = creditFlag;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public String getReadWriteFlag() {
        return readWriteFlag;
    }

    public void setReadWriteFlag(String readWriteFlag) {
        this.readWriteFlag = readWriteFlag;
    }

    public String getCreditReasonNote() {
        return creditReasonNote;
    }

    public void setCreditReasonNote(String creditReasonNote) {
        this.creditReasonNote = creditReasonNote;
    }

    public String getCreditReasonUser() {
        return creditReasonUser;
    }

    public void setCreditReasonUser(String creditReasonUser) {
        this.creditReasonUser = creditReasonUser;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<CEInvoiceForm> getCeInvoiceFormList() {
        if (ceInvoiceFormList == null) {
            ceInvoiceFormList = new ArrayList<CEInvoiceForm>();
        }
        return ceInvoiceFormList;
    }

    public void setCeInvoiceFormList(List<CEInvoiceForm> ceInvoiceFormList) {
        this.ceInvoiceFormList = ceInvoiceFormList;
    }

    public String getJobNumber() {
        if (ceInvoice == null || ceInvoice.getJobContract() == null) {
            return "";
        }
        else {
            return ceInvoice.getJobContract().getJobOrderNumber();
        }
    }

}
