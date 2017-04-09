/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.web.controller.job;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.phoenix.entity.DepositInvoice;
import com.intertek.phoenix.web.controller.ControllerUtil;

/**
 * EditDepositAmountPopupController for Editing Deposit Invoice Amount on
 * 'Select Charges' screen
 * 
 * @version 1.0 May 22, 2009
 * @author Patni
 */
public class EditDepositAmountPopupController extends SimpleFormController {

    /**
     * Instantiates a new EditDepositAmountPopupController.
     */
    public EditDepositAmountPopupController() {
        super();
        setCommandClass(EditDepositAmountPopupForm.class);
        setSessionForm(true);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse, java.lang.Object,
     *      org.springframework.validation.BindException)
     */
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        String errorMsg = "";
        try {
            if (request.getParameter("operation").equals("updateAmount")) {
                errorMsg = "problem.edit.deposit.amount";
                EditDepositAmountPopupForm form = (EditDepositAmountPopupForm) command;
                DepositInvoice depInv = form.getDepositInvoice();
                // TODO Use ServiceMethod to take care of the available amount
                depInv.setAvailableAmount(depInv.getDepositAmount());
                depInv = ControllerUtil.loadAndUpdate(depInv, DepositInvoice.class);
                form.setOperation("refreshParent");
                // No need to call save
                // DaoManager.getDao(DepositInvoice.class).saveOrUpdate(depInv);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            errorMsg = errorMsg.equals("") ? "problem.general.page" : errorMsg;
            errors.reject(errorMsg, new Object[] { e.getMessage() }, null);
        }

        return showForm(request, response, errors);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
     */
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        try {
            int depInvIndex = Integer.valueOf(request.getParameter("depInvIndex"));

            HttpSession session = request.getSession();
            String tag = CEJobSelectChargesFormController.class.getName() + ".FORM.command";
            CESelectChargeForm ceSelectChargesForm = (CESelectChargeForm) session.getAttribute(tag);
            DepositInvoice depInvoice = (DepositInvoice) ceSelectChargesForm.getDepositInvoiceForms()[depInvIndex].getDepositInvoice();
            // On loading time, no need to refresh Deposit Invoice
            // depInvoice =
            // DaoManager.getDao(DepositInvoice.class).find(depInvoice.getId());
            return new EditDepositAmountPopupForm(depInvoice);
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
