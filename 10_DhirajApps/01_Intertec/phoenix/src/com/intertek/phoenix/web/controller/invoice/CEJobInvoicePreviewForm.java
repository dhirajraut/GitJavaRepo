/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.web.controller.invoice;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.intertek.entity.Bank;
import com.intertek.entity.BankAccount;
import com.intertek.entity.Contact;
import com.intertek.entity.CustAddress;
import com.intertek.meta.dropdown.Field;
import com.intertek.phoenix.ServiceManager;
import com.intertek.phoenix.common.EnumField;
import com.intertek.phoenix.common.Form;
import com.intertek.phoenix.entity.CEInvoice;
import com.intertek.phoenix.entity.CEJobOrder;
import com.intertek.phoenix.entity.InvoiceGenerationType;
import com.intertek.phoenix.tax.TaxSrvc;

/**
 * Purpose: Main form bean for CE Invoice Preview page. It holds rest of the
 * form beans required to display information on all the tabs
 * 
 * @version 1.0 May 25, 2009
 * @author Patni
 */
public class CEJobInvoicePreviewForm extends Form {

    private CEJobOrder jobOrder;
    private String refreshing;
    private String tabSource;
    private Integer invLineItemIndex;
    private Integer splitLineItemIndex;
    private CEInvoice draftInvoice;
    private CEInvoiceLineItemForm[] ceInvLineItemForms;
    private DepositInvoiceForm[] depositInvoiceForms;
    private List<Field> invGenerationTypes;
    private boolean depInvoiceGenerated;

    private static TaxSrvc taxSrvc = ServiceManager.getTaxSrvc();
    // Note: All tax and vat code implementation in this class are to support
    // header row in TAX tab
    private String taxCode;
    private String vatCode;
    private String taxArticleCode;

    public CEJobOrder getJobOrder() {
        return jobOrder;
    }

    public void setJobOrder(CEJobOrder jobOrder) {
        this.jobOrder = jobOrder;
    }

    public String getJobNumber() {
        return jobOrder.getJobNumber();
    }

    public String getBillingContactName() {
        Contact billingContact = jobOrder.getJobContract().getBillingContact();
        if (billingContact == null) {
            return "";
        }
        return billingContact.getFirstName() + " " + billingContact.getLastName();
    }

    public String getPaymentTerm() {
        return jobOrder.getJobContract().getPaymentTerms();
    }

    public String getRemitTo() {
        Bank r = jobOrder.getJobContract().getRemitTo();
        if (r == null) {
            return "";
        }
        return r.getBankDesc();
    }

    public String getRemitToAcct() {
        BankAccount bankAccount = jobOrder.getJobContract().getRemitToBankAccount();
        if (bankAccount == null) {
            return "";
        }
        return (bankAccount.getBankAcctDesc() + " " + bankAccount.getBankAccountId().getBankAcctCode());
    }

    public String getDescription() {
        return jobOrder.getDescription();
    }

    public void setDescription(String description) {
        jobOrder.setDescription(description);
    }

    public CEJobInvoicePreviewForm(CEJobOrder jobOrder) {
        this.jobOrder = jobOrder;
    }

    public String getRefreshing() {
        return refreshing;
    }

    public void setRefreshing(String refreshing) {
        this.refreshing = refreshing;
    }

    public Integer getInvLineItemIndex() {
        return invLineItemIndex;
    }

    public void setInvLineItemIndex(Integer invLineItemIndex) {
        this.invLineItemIndex = invLineItemIndex;
    }

    public Integer getSplitLineItemIndex() {
        return splitLineItemIndex;
    }

    public void setSplitLineItemIndex(Integer splitLineItemIndex) {
        this.splitLineItemIndex = splitLineItemIndex;
    }

    public String getTabSource() {
        return tabSource;
    }

    public void setTabSource(String tabSource) {
        this.tabSource = tabSource;
    }

    public String getFullAddress() {
        CustAddress a = jobOrder.getJobContract().getBillingAddress();
        if (a == null) {
            return "";
        }
        return a.getFullAddress();
    }

    public CEInvoice getDraftInvoice() {
        return draftInvoice;
    }

    public void setDraftInvoice(CEInvoice draftInvoice) {
        this.draftInvoice = draftInvoice;
    }

    public String getVatRegId() {
        return jobOrder.getJobContract().getVatRegId();
    }

    public void setVatRegId(String vatRegId) {
        jobOrder.getJobContract().setVatRegId(vatRegId);
    }

    public List<EnumField> getTaxCodes() {
        List<EnumField> taxcodeList = new ArrayList<EnumField>();
        try {
            taxcodeList = taxSrvc.getSalesTaxCode(jobOrder.getJobContract());
        }
        catch (Exception e) {
            // TODO
        }
        return taxcodeList;
    }

    public List<EnumField> getVatCodes() {
        List<EnumField> vatcodeList = new ArrayList<EnumField>();
        try {
            vatcodeList = taxSrvc.getVatTaxCode(jobOrder.getJobContract());
        }
        catch (Exception e) {
            // TODO
        }
        return vatcodeList;
    }

    public List<EnumField> getTaxArticleCodes() {
        List<EnumField> taxArticleList = new ArrayList<EnumField>();
        try {
            taxArticleList = taxSrvc.getTaxArticles();
        }
        catch (Exception e) {
            //TODO
        }
        return taxArticleList;
    }

    public String getTaxCode() {
        return taxCode;
    }

    public void setTaxCode(String taxCode) {
        this.taxCode = taxCode;
    }

    public String getVatCode() {
        return vatCode;
    }

    public void setVatCode(String vatCode) {
        this.vatCode = vatCode;
    }

    public String getTaxArticleCode() {
        return taxArticleCode;
    }

    public void setTaxArticleCode(String taxArticleCode) {
        this.taxArticleCode = taxArticleCode;
    }

    public double getTotal() {
        return draftInvoice.getInvAmtPreTax();
    }

    public CEInvoiceLineItemForm[] getCeInvLineItemForms() {
        return ceInvLineItemForms;
    }

    public void setCeInvLineItemForms(CEInvoiceLineItemForm[] ceInvLineItemForms) {
        this.ceInvLineItemForms = ceInvLineItemForms;
    }

    public DepositInvoiceForm[] getDepositInvoiceForms() {
        return depositInvoiceForms;
    }

    public void setDepositInvoiceForms(DepositInvoiceForm[] depositInvoiceForms) {
        this.depositInvoiceForms = depositInvoiceForms;
    }

    public String getInvGenerationType() {
        InvoiceGenerationType t = draftInvoice.getGenerationType();
        if (t == null) {
            return "";
        }
        return t.getValue();
    }

    public void setInvGenerationType(String code) {
        draftInvoice.setGenerationType(InvoiceGenerationType.getIvoiceGenerationType(code));
    }

    public void setInvGenerationTypes(List<Field> invGenerationTypes) {
        this.invGenerationTypes = invGenerationTypes;
    }

    public List<Field> getInvGenerationTypes() {
        return invGenerationTypes;
    }

    /**
     * Name : removeInvoiceLineItemForm Purpose : Removed given
     * InvoiceLineItemForm from the array of forms
     * 
     * @param iliForm:
     *            form to be removed
     */
    public void removeInvoiceLineItemForm(CEInvoiceLineItemForm iliForm) {
        List<CEInvoiceLineItemForm> iliFormList = new ArrayList<CEInvoiceLineItemForm>();
        Collections.addAll(iliFormList, ceInvLineItemForms);
        iliFormList.remove(iliForm);
        ceInvLineItemForms = iliFormList.toArray(new CEInvoiceLineItemForm[iliFormList.size()]);
    }

    public boolean isDepInvoiceGenerated() {
        return depInvoiceGenerated;
    }

    public void setDepInvoiceGenerated(boolean depInvoiceGenerated) {
        this.depInvoiceGenerated = depInvoiceGenerated;
    }
}
