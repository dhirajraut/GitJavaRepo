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

/**
 * Purpose: Controller for Deposit invoice tab in CE Invoice Preview
 * 
 * @version 1.0 May 25, 2009
 * @author Patni
 */
public class CEJobInvoicePreviewTab0FormController extends SimpleFormController {

    /**
     * Instantiates a new CEJobInvoicePreviewTab0FormController.
     */
    public CEJobInvoicePreviewTab0FormController() {
        super();
        setSessionForm(true);
        setCommandClass(CEJobInvoicePreviewForm.class);
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
        HttpSession session = request.getSession();
        CEJobInvoicePreviewForm cePreviewInvoiceForm = (CEJobInvoicePreviewForm) session
                .getAttribute("com.intertek.phoenix.web.controller.invoice.CEJobInvoicePreviewFormController.FORM.command");
        return cePreviewInvoiceForm;
    }

}
