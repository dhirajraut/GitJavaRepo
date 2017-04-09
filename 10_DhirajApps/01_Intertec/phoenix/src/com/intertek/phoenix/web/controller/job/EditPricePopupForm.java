/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.web.controller.job;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.intertek.phoenix.entity.RevenueSegregation;
import com.intertek.phoenix.entity.TestJobOrderLineItem;
import com.intertek.phoenix.web.controller.invoice.RevenueSegregationForm;

/**
 * Edit price Popup form for Select Charges functionality  
 * @author rautsmit
 *
 */
public class EditPricePopupForm {

    private TestJobOrderLineItem joli;
    private RevenueSegregationForm[] revenueSegregationForms;
    private Set<RevenueSegregation> revenueSegregations;
    private String operation;

    /**
     * Instantiates new CEJobOrderLineItemForm
     * 
     * @param ceJobOrderLineItem
     */
    public EditPricePopupForm(TestJobOrderLineItem joli, Set<RevenueSegregation> rsSet) {
        this.joli = joli;
        revenueSegregations = rsSet;
//        revenueSegregations = new HashSet<RevenueSegregation>();
//        this.revenueSegregations.addAll(rsSet);
        revenueSegregationForms = new RevenueSegregationForm[rsSet.size()];
        int rsCounter = 0;
        for (RevenueSegregation rs : rsSet) {
            RevenueSegregationForm rsForm = new RevenueSegregationForm(rs);
            revenueSegregationForms[rsCounter++] = rsForm;
        }
        
        Arrays.sort(revenueSegregationForms, new RevSegComparator());
    }

    public TestJobOrderLineItem getJobOrderLineItem() {
        return joli;
    }

    public RevenueSegregationForm[] getRevenueSegregationForms() {
        return revenueSegregationForms;
    }

    public Set<RevenueSegregation> getRevenueSegregations() {
        return revenueSegregations;
    }

    public void setRevenueSegregationForms(RevenueSegregationForm[] revenueSegregationForms) {
        this.revenueSegregationForms = revenueSegregationForms;
    }

    /**
     * Adds revenue segregation collection
     * @param revenueSegregations
     */
    public void addRevenueSegregations(Collection<RevenueSegregation> revenueSegregations) {
        this.revenueSegregations.addAll(revenueSegregations);
    }

    public String getDescription() {
        return joli.getDescription();
    }

    public void setDescription(String desc) {
        joli.setDescription(desc);
    }

    public String getOperation() {
        return operation;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }


    /**
     * To order/ sort Revenue Segregations 
     * @author rautsmit
     *
     */
    private class RevSegComparator implements Comparator<RevenueSegregationForm> {
        @Override
        public int compare(RevenueSegregationForm form1, RevenueSegregationForm form2) {
            
            if(form1.isPrimary()) {
                return -1;
            }
            
            if(form2.isPrimary()) {
                return 1;
            }
            
            return form1.getDescription().compareTo(form2.getDescription());
        }
    }

}
