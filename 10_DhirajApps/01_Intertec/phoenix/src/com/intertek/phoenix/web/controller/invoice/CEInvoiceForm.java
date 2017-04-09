/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.web.controller.invoice;

import java.sql.Timestamp;

import com.intertek.phoenix.entity.CEInvoice;
import com.intertek.phoenix.entity.CEInvoiceLineItem;
import com.intertek.phoenix.entity.CreditInvoice;
import com.intertek.phoenix.entity.DepositInvoice;
import com.intertek.phoenix.entity.DepositInvoiceAmount;
import com.intertek.phoenix.entity.InvoiceStatus;

/**
 * This class is to hold CEInvoice and CreditInvoice entities and all other
 * fields needed on the page.
 * 
 * It is expanded to hold DepositInvoice as well.
 * 
 * @author patni
 * @author lily.sun
 */

public class CEInvoiceForm {
    enum FormInvoice {
        CEInvoice, CreditInvoice, DepositInvoice
    };

    private CEInvoice ceInvoice;
    private CreditInvoice crInvoice;
    private DepositInvoice depInvoice;
    private FormInvoice formInvoiceType;

    public String getDepositAccAmount() {

        StringBuffer depositAccAmountStr = new StringBuffer();
        if (ceInvoice != null) {
            for (CEInvoiceLineItem lineItem : ceInvoice.getInvoiceLineItems()) {
                for (DepositInvoiceAmount depAmount : lineItem.getDepositInvoiceAmounts()) {
                    depositAccAmountStr.append(depAmount.getDepositInvoice().getDepositReference());
                    depositAccAmountStr.append(" -$");
                    depositAccAmountStr.append(depAmount.getAmount());
                    depositAccAmountStr.append(" <BR>");
                }
            }
        }
        return depositAccAmountStr.toString();
    }

    public CEInvoiceForm() {
    }

    public CEInvoiceForm(CEInvoice ceInvoice) {
        this.ceInvoice = ceInvoice;
    }

    public CEInvoice getCeInvoice() {
        return ceInvoice;
    }

    public void setCeInvoice(CEInvoice ceInvoice) {
        this.ceInvoice = ceInvoice;
    }

    public DepositInvoice getDepInvoice() {
        return depInvoice;
    }

    public void setDepInvoice(DepositInvoice depInvoice) {
        this.depInvoice = depInvoice;
    }

    public String getInvoiceFile() {
        if (FormInvoice.CEInvoice == getFormInvoiceType()) {
            if (ceInvoice != null) {
                //TODO commented are the changes for the demo. dont delete it.
                //InvoiceFile f = ceInvoice.getInvoiceFile();
                //if (f != null) {
                //   return f.getInvoiceFileName();
                return ceInvoice.getInvoiceFileName();
                // }
            }
        }
        else if (FormInvoice.CreditInvoice == getFormInvoiceType()) {
            if (crInvoice != null) {
                return crInvoice.getInvoiceFileName();
            }
        }
        else if (FormInvoice.DepositInvoice == getFormInvoiceType()) {
            if (depInvoice != null) {
                return depInvoice.getInvoiceFileName();
            }
        }
        return "";
    }

    public Timestamp getGeneratedOn() {
        if (FormInvoice.CEInvoice == getFormInvoiceType()) {
            if (ceInvoice != null) {
                return ceInvoice.getGeneratedOn();
            }
        }
        else if (FormInvoice.CreditInvoice == getFormInvoiceType()) {
            if (crInvoice != null) {
                return crInvoice.getGeneratedOn();
            }
        }
        else if (FormInvoice.DepositInvoice == getFormInvoiceType()) {
            if (depInvoice != null) {
                return depInvoice.getGeneratedOn();
            }
        }

        return null;
    }

    public String getGeneratedBy() {
        if (FormInvoice.CEInvoice == getFormInvoiceType()) {
            if (ceInvoice != null) {
                return ceInvoice.getGeneratedBy();
            }
        }
        else if (FormInvoice.CreditInvoice == getFormInvoiceType()) {
            if (crInvoice != null) {
                return crInvoice.getGeneratedBy();
            }
        }
        else if (FormInvoice.DepositInvoice == getFormInvoiceType()) {
            if (depInvoice != null) {
                return depInvoice.getGeneratedBy();
            }
        }

        return "";
    }

    public String getInvoiceNumber() {
        if (FormInvoice.CEInvoice == getFormInvoiceType()) {
            if (ceInvoice != null) {
                return ceInvoice.getInvoiceNumber();
            }
        }
        else if (FormInvoice.CreditInvoice == getFormInvoiceType()) {
            if (crInvoice != null) {
                return crInvoice.getInvoiceNumber();
            }
        }
        else if (FormInvoice.DepositInvoice == getFormInvoiceType()) {
            if (depInvoice != null) {
                return depInvoice.getInvoiceNumber();
            }
        }

        return "";
    }

    public InvoiceStatus getStatus() {
        if (FormInvoice.CEInvoice == getFormInvoiceType()) {
            if (ceInvoice != null) {
                return ceInvoice.getStatus();
            }
        }
        else if (FormInvoice.CreditInvoice == getFormInvoiceType()) {
            if (crInvoice != null) {
                return crInvoice.getStatus();
            }
        }
        else if (FormInvoice.DepositInvoice == getFormInvoiceType()) {
            if (depInvoice != null) {
                return depInvoice.getStatus();
            }
        }

        return null;
    }

    public CreditInvoice getCrInvoice() {
        return crInvoice;
    }

    public void setCrInvoice(CreditInvoice crInvoice) {
        this.crInvoice = crInvoice;
    }

    public String getCreditInvoiceNumber() {
        if (crInvoice == null) {
            return "";
        }
        else {
            return crInvoice.getInvoiceNumber();
        }
    }

    public String getRelatedInvoiceNumber() {
        if (crInvoice == null) {
            return "";
        }
        else {
            return crInvoice.getRelatedInvoiceNumber();
        }
    }

    public String getRelatedInvoiceFile() {
        if (crInvoice != null) {           
            String invoiceFileName = crInvoice.getInvoiceFileName();
            if (invoiceFileName != null) {
                return invoiceFileName;
            }
        }
        return "";
    }

    public FormInvoice getFormInvoiceType() {
        return formInvoiceType;
    }

    public void setFormInvoiceType(FormInvoice formInvoiceType) {
        this.formInvoiceType = formInvoiceType;
    }

}
