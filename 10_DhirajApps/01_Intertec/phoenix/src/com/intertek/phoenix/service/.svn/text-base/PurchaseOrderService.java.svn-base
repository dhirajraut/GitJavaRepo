/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.service;

import java.util.List;

import com.intertek.phoenix.BaseService;
import com.intertek.phoenix.dao.DaoException;
import com.intertek.phoenix.entity.CEJobOrderLineItem;
import com.intertek.phoenix.entity.PurchaseOrder;

/**
 * Purpose:.Interface defining methods to be implemented by Purchase Order service classes
 * 
 * @version 1.0  Apr 15, 2009
 * @author Patni
 */
public interface PurchaseOrderService extends BaseService {

    /**
     * Name :addPurchaseOrder
     * Purpose : Add given PurchaseOrder to DB.
     * 
     * @param po the po
     * 
     * @return the PurchaseOrder
     */
    public PurchaseOrder addPurchaseOrder(PurchaseOrder po) throws DaoException;

    /**
     * Name :savePurchaseOrder
     * Purpose :Save given PurchaseOrder to DB.
     * 
     * @param po the po
     * 
     * @return the PurchaseOrder
     */
    public PurchaseOrder savePurchaseOrder(PurchaseOrder po) throws DaoException;

    /**
     * Name :deletePurchaseOrder
     * Purpose :Delete given PurchaseOrder from DB.
     * 
     * @param po the po
     */
    public void deletePurchaseOrder(PurchaseOrder po) throws DaoException;

    /**
     * Name :loadPOByPONumber
     * Purpose :Load PurchaseOrders with given poNumber.
     * 
     * @param poNumber the po number
     * 
     * @return the PurchaseOrder
     */
    public PurchaseOrder loadPOByPONumber(String poNumber) throws PONotFoundException;

    /**
     * Name :loadPODetails
     * Purpose : loads PODetails for given po number in given order 
     * 
     * @param poNumber the po number
     * @param sortField the sort field
     * 
     * @return the List<PurchaseOrderDetails>
     */
    public List<CEJobOrderLineItem> loadJobOrderLineItems(String poNumber, String sortField) throws PONotFoundException;
}
