/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.web.controller.purchaseorder;

import java.util.ArrayList;

import java.util.List;

import com.intertek.exception.ServiceException;
import com.intertek.phoenix.entity.CEJobContract;
import com.intertek.phoenix.entity.CEJobOrderLineItem;
import com.intertek.phoenix.entity.CEInvoiceLineItem;
import com.intertek.phoenix.job.JobSrvcException;
import com.intertek.phoenix.web.controller.invoice.CEJobOrderLineItemForm;

import com.intertek.phoenix.dao.DaoException;
import com.intertek.phoenix.dao.DaoManager;
import com.intertek.phoenix.dao.Dao;
import com.intertek.phoenix.dao.QueryInfo;

/**
 * Purpose: Form bean to represent CEJobOrderLineItem entity in context of Purchase Order page
 * 
 * @version 1.0 May 25, 2009
 * @author Patni
 */
public class POJoliForm {

    private CEJobOrderLineItem ceJobOrderLineItem;
    private List<POIliForm> iliForms;
    CEJobContract jobContract;
    CEJobOrderLineItemForm lineItem;
    private int length;


    /**
     * Instantiates new POJoliForm
     * 
     * @param ceJobOrderLineItem
     * @throws JobSrvcException
     */
    public POJoliForm(CEJobOrderLineItem ceJobOrderLineItem) {
        try {
            this.ceJobOrderLineItem = ceJobOrderLineItem;
            Dao<CEInvoiceLineItem> dao = DaoManager.getDao(CEInvoiceLineItem.class);
            QueryInfo query = new QueryInfo(CEInvoiceLineItem.class);
            query.addFilter("cEJobOrderLineItemId", this.ceJobOrderLineItem.getId());
            List<CEInvoiceLineItem> iliList = dao.search(query);
            iliForms = new ArrayList<POIliForm>();

            for (CEInvoiceLineItem ceInvoiceLineItem : iliList) {
                POIliForm ceIliForm = new POIliForm(ceInvoiceLineItem);
                iliForms.add(ceIliForm);
            }

            this.length = iliForms.size()+1;
        }
        catch (DaoException e) {
            new ServiceException("Failed to construct PO form.", e);
        }
    }

    public long getLineNumber() {
        return ceJobOrderLineItem.getLineNumber();
    }

    public void setLineNumber(long lineNumber) {
        ceJobOrderLineItem.setLineNumber(lineNumber);
    }

    public String getDescription() {
        return ceJobOrderLineItem.getDescription();
    }

    public void setDescription(String description) {
        ceJobOrderLineItem.setDescription(description);
    }

    public List<POIliForm> getIliForms() {
        return iliForms;
    }

    public int getLength() {
        return length;
    }

}
