/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.web.controller.invoice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.phoenix.entity.CEInvoice;
import com.intertek.phoenix.entity.CEInvoiceLineItem;
import com.intertek.phoenix.entity.CEJobOrder;
import com.intertek.phoenix.tax.PhxTaxUtil;
import com.intertek.phoenix.web.controller.ControllerUtil;
import com.intertek.util.Constants;

/**
 * Purpose: Controller for 'View All' tab in CE Invoice Preview
 * 
 * @version 1.0 May 25, 2009
 * @author Patni
 */
public class CEJobInvoicePreviewTab4FormController extends SimpleFormController {

    /**
     * Instantiates a new CEJobInvoicePreviewTab4FormController.
     */
    public CEJobInvoicePreviewTab4FormController() {
        super();
        setSessionForm(true);
        setCommandClass(CEJobInvoicePreviewForm.class);
    }

    /* (non-Javadoc)
     * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
     */
    protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception {
        CEJobInvoicePreviewForm invoicePreviewForm = (CEJobInvoicePreviewForm) command;
        String refreshing = invoicePreviewForm.getRefreshing();
        if (refreshing.equals(Constants.CALC)) {

            CEJobOrder ceJo = ControllerUtil.loadAndUpdate(invoicePreviewForm.getJobOrder(), CEJobOrder.class);
            CEInvoice ceInvoice = ControllerUtil.loadAndUpdate(invoicePreviewForm.getDraftInvoice(), CEInvoice.class);

            for (CEInvoiceLineItem invLineItem : invoicePreviewForm.getDraftInvoice().getInvoiceLineItems()) {
                invLineItem = PhxTaxUtil.calculateTaxAndVatCodes(ceJo, invoicePreviewForm, invLineItem);
            }

            ControllerUtil.updateCollection(ceInvoice, invoicePreviewForm.getDraftInvoice(), CEInvoice.class, "invoiceLineItems");

            for (CEInvoiceLineItem newLineItem : ceInvoice.getInvoiceLineItems()) {
                for (CEInvoiceLineItemForm ceInvForm : invoicePreviewForm.getCeInvLineItemForms()) {
                    if (ceInvForm.getCeInvoiceLineItem().getId() == newLineItem.getId().longValue()) {
                        ceInvForm.setCEInvoiceLineItem(newLineItem);
                        break;
                    }
                }
            }
            invoicePreviewForm.setDraftInvoice(ceInvoice);
        }
        return showForm(request, response, errors);

    }

    /* (non-Javadoc)
     * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
     */
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        HttpSession session = request.getSession();
        CEJobInvoicePreviewForm cePreviewInvoiceForm = (CEJobInvoicePreviewForm) session
                .getAttribute("com.intertek.phoenix.web.controller.invoice.CEJobInvoicePreviewFormController.FORM.command");
        cePreviewInvoiceForm.setTabSource(Constants.CE_JOB_INV_PREVIEW_TAB4);
        return cePreviewInvoiceForm;
    }

}
