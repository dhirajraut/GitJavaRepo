/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.web.controller.invoice;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.phoenix.ServiceManager;
import com.intertek.phoenix.entity.CEInvoiceLineItem;
import com.intertek.phoenix.entity.DepositInvoice;
import com.intertek.phoenix.entity.DepositInvoiceAmount;
import com.intertek.phoenix.invoice.InvalidInvoiceOperationException;
import com.intertek.phoenix.invoice.InvoiceService;
import com.intertek.phoenix.web.controller.ControllerUtil;

/**
 * Purpose: Controller for 'Apply Deposit' popup in CE Invoice Preview
 * 
 * @version 1.0 May 25, 2009
 * @author Patni
 */
public class ApplyDepositPopupController extends SimpleFormController {

    /**
     * Instantiates a new ApplyDepositPopupController.
     */
    public ApplyDepositPopupController() {
        super();
        setSessionForm(true);
        setCommandClass(ApplyDepositForm.class);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax
     * .servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
     * java.lang.Object, org.springframework.validation.BindException)
     */
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {

        ApplyDepositForm applyDepform = (ApplyDepositForm) command;
        CEInvoiceLineItem invLineItem = applyDepform.getCeInvoiceLineItemForm().getCeInvoiceLineItem();
        DepositInvoiceForm[] depositForms = applyDepform.getDepositInvoiceForms();
        InvoiceService invoiceService = ServiceManager.getInvoiceService();

        for (DepositInvoiceForm depositForm : depositForms) {
            if (depositForm.isSelected()) {
                DepositInvoice depositInvoice = ControllerUtil.findById(DepositInvoice.class, depositForm.getDepositInvoice().getId());
                try {
                    CEInvoiceLineItem managedIli = ControllerUtil.loadAndUpdate(invLineItem, CEInvoiceLineItem.class);
                    invoiceService.applyDepositInvoice(managedIli, depositInvoice, depositForm.getAmountToApply());
                }
                catch (InvalidInvoiceOperationException e) {
                    errors.reject("generic.error", new Object[] {e.getMessage()}, null);
                    return showForm(request, response, errors);
                }
                depositForm.setDepositInvoice(depositInvoice);
            }
        }

        return showForm(request, response, errors);
    }

    /*
     * (non-Javadoc)
     * 
     * @see
     * org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject
     * (javax.servlet.http.HttpServletRequest)
     */
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        int lineItemNumber = Integer.valueOf(request.getParameter("lineItemNumber"));
        HttpSession session = request.getSession();
        CEJobInvoicePreviewForm cePreviewInvoiceForm = (CEJobInvoicePreviewForm) session
                .getAttribute("com.intertek.phoenix.web.controller.invoice.CEJobInvoicePreviewFormController.FORM.command");
        CEInvoiceLineItemForm invoiceLineItemForm = cePreviewInvoiceForm.getCeInvLineItemForms()[lineItemNumber];
        
        DepositInvoiceForm[] depositInvoiceForms = cePreviewInvoiceForm.getDepositInvoiceForms();
        //Using iliForm from session requires all the data being refreshed from DB
        for (DepositInvoiceForm depositInvoiceForm : depositInvoiceForms) {
            DepositInvoice depositInvoice = depositInvoiceForm.getDepositInvoice();
            DepositInvoice managedDI = ControllerUtil.loadAndUpdate(depositInvoice, DepositInvoice.class);
            depositInvoiceForm.setDepositInvoice(managedDI);
            Set<DepositInvoiceAmount> alreadyAppliedAmt = managedDI.getAppliedDepositInvoiceAmounts();
            for (DepositInvoiceAmount depositInvoiceAmount : alreadyAppliedAmt) {
                if (depositInvoiceAmount.getInvoiceLineItemId() != null && depositInvoiceAmount.getInvoiceLineItemId().equals(invoiceLineItemForm.getCeInvoiceLineItem().getId())){
                    depositInvoiceForm.setAmountToApply(depositInvoiceAmount.getAmount());
                }
            }
        }
        ApplyDepositForm applyDepositForm = new ApplyDepositForm(depositInvoiceForms, invoiceLineItemForm);
//        applyDepositForm.reset();
        return applyDepositForm;
    }

}
