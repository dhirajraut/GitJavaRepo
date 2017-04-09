/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.web.controller.job;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.phoenix.ServiceManager;
import com.intertek.phoenix.dao.DaoManager;
import com.intertek.phoenix.entity.CEJobOrderLineItem;
import com.intertek.phoenix.entity.JobContractTest;
import com.intertek.phoenix.entity.RevenueSegregation;
import com.intertek.phoenix.entity.TestJobOrderLineItem;
import com.intertek.phoenix.job.JobOrderService;
import com.intertek.phoenix.web.controller.ControllerUtil;

/**
 * The class EditPricePopupController for Edit Price (RevenueSegregation) popup
 * on 'Select Charges' screen
 * 
 * @version 1.0 May 22, 2009
 * @author Patni
 */
public class EditPricePopupController extends SimpleFormController {

    /**
     * Instantiates a new EditPricePopupController.
     */
    public EditPricePopupController() {
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
        EditPricePopupForm form = (EditPricePopupForm) command;
        String errorMsg = "";

        try {
            if (request.getParameter("operation").equals("updateRS")) {
                errorMsg = "problem.edit.price";

                Set<RevenueSegregation> rsList = new HashSet<RevenueSegregation>();
                for (RevenueSegregation rs : form.getRevenueSegregations()) {
                    rsList.add(ControllerUtil.loadAndUpdate(rs, RevenueSegregation.class));
                }

                CEJobOrderLineItem joli = form.getJobOrderLineItem();
                joli = ControllerUtil.loadAndUpdate(joli, CEJobOrderLineItem.class);
                JobOrderService joService = ServiceManager.getJobOrderService();
                joService.updateRevenueSegregations(joli, rsList);
                form.setOperation("refreshParent");
                return showForm(request, response, errors);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
            errorMsg = errorMsg.equals("") ? "problem.general.page" : errorMsg;
            errors.reject(errorMsg, new Object[]{e.getMessage()}, null);
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
            JobOrderService joService = ServiceManager.getJobOrderService();
            Long joliId = Long.valueOf(request.getParameter("joliId"));

            TestJobOrderLineItem joli = DaoManager.getDao(TestJobOrderLineItem.class).find(joliId);
            JobContractTest jcTest = joli.getJobContractTask();

            List<RevenueSegregation> rsList = joService
                    .getRevenueSegregationForTest(joli.getJobContract(), joli.getJobContract().getRootServiceLevel(), jcTest);
            Set<RevenueSegregation> revSegs = joli.getRevenueSegregations();
            // merge rsList to revSegs
            mergeRevSegList(revSegs, rsList);

            EditPricePopupForm editPriceForm = new EditPricePopupForm(joli, revSegs);
            return editPriceForm;
        }
        catch (Exception e) {
            e.printStackTrace();
            throw e;
        }
    }

    /**
     * Merge two revenue segregation collections
     * @param revSegs
     * @param rsList
     */
    private void mergeRevSegList(Set<RevenueSegregation> revSegs, List<RevenueSegregation> rsList) {
        for (RevenueSegregation revSeg : rsList) {
            if (!revSegPresent(revSegs, revSeg))
                revSegs.add(revSeg);
        }
    }

    /**
     * Check if revenue segregation is present in the set
     * @param revSegs
     * @param rs
     * @return true/ false
     */
    private boolean revSegPresent(Set<RevenueSegregation> revSegs, RevenueSegregation rs) {
        for (RevenueSegregation revSeg : revSegs) {
            if (rs.getDescription().equals(revSeg.getDescription())) {
                return true;
            }
        }

        return false;
    }

}
