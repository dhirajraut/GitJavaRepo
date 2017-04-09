/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.web.controller.job;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

/**
 * The class TravelExpensePopupController for 'Add Travel Expenses' 
 * popup on 'Select Charges' screen
 * 
 * @version 1.0 May 22, 2009
 * @author Patni
 */
public class TravelExpensePopupController extends SimpleFormController {

    /**
     * Instantiates a new TravelExpensePopupController.
     */
    public TravelExpensePopupController() {
        super();
        setCommandClass(EditPricePopupForm.class);
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
         return showForm(request, response, errors);
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
     */
    protected Object formBackingObject(HttpServletRequest request) throws Exception {
        try {
            //TODO: we dont need expense line item functionality
/*          ExpenseLineItem newTravelExpenseItem = new ExpenseLineItem();
            EditPricePopupForm editPriceForm = new EditPricePopupForm(newTravelExpenseItem);
            return editPriceForm;
*/
            
            return null;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

}
