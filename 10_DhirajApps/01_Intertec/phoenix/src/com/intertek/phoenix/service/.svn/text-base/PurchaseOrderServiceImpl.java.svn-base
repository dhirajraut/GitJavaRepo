/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.service;

import java.util.List;

import com.intertek.phoenix.BaseServiceImpl;
import com.intertek.phoenix.dao.DaoException;
import com.intertek.phoenix.dao.DaoManager;
import com.intertek.phoenix.dao.QueryInfo;
import com.intertek.phoenix.entity.CEJobOrderLineItem;
import com.intertek.phoenix.entity.PurchaseOrder;

/**
 * Purpose: Purchase Order Service implementation
 * 
 * @version 1.0 Apr 15, 2009
 * @author Patni
 */
public class PurchaseOrderServiceImpl extends BaseServiceImpl implements PurchaseOrderService {

    /*
     * (non-Javadoc)
     * 
     * @see com.intertek.phoenix.service.PurchaseOrderService#addPurchaseOrder(com
     *      .intertek.phoenix.entity.PurchaseOrder)
     */
    public PurchaseOrder addPurchaseOrder(PurchaseOrder po) throws DaoException {
        return DaoManager.getDao(PurchaseOrder.class).saveOrUpdate(po);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intertek.phoenix.service.PurchaseOrderService#deletePurchaseOrder
     *      (com.intertek.phoenix.entity.PurchaseOrder)
     */
    public void deletePurchaseOrder(PurchaseOrder po) throws DaoException {
        DaoManager.getDao(PurchaseOrder.class).remove(po);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intertek.phoenix.service.PurchaseOrderService#savePurchaseOrder(com
     *      .intertek.phoenix.entity.PurchaseOrder)
     */

    public PurchaseOrder savePurchaseOrder(PurchaseOrder po) throws DaoException {
        return DaoManager.getDao(PurchaseOrder.class).saveOrUpdate(po);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intertek.phoenix.service.PurchaseOrderService#loadPOByPONumber(java
     *      .lang.String)
     */

    public PurchaseOrder loadPOByPONumber(String poNumber) throws PONotFoundException {
        List<PurchaseOrder> poList = null;

        try {
            QueryInfo query = new QueryInfo(PurchaseOrder.class);
            query.addFilter("poNumber", poNumber);
            poList = DaoManager.getDao(PurchaseOrder.class).search(query);

            if (poList != null && poList.size() > 0) {
                return poList.get(0);
            }

            throw new PONotFoundException("Purchase order not found: " + poNumber);
        }
        catch (DaoException e) {
            throw new PONotFoundException(e.getMessage(), e);
        }
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intertek.phoenix.service.PurchaseOrderService#loadJobOrderLineItems(java.lang.String,
     *      java.lang.String)
     */
    public List<CEJobOrderLineItem> loadJobOrderLineItems(String poNumber, String sortField) throws PONotFoundException {
        // TODO: provide implementation
        return null;
    }
}
