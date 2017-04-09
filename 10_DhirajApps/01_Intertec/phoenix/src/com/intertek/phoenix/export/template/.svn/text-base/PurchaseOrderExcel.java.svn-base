/*
 * PurchaseOrderExcel.java
 * 
 * @version 1.0
 * 
 * Jul 1, 2009
 * 
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 */
package com.intertek.phoenix.export.template;

import java.util.ArrayList;
import java.util.List;

import com.intertek.export.template.ITemplate;
import com.intertek.export.template.IXCellTemplate;

/**
 * The Class PurchaseOrderExcel implements IXCellTemplate.
 */
public class PurchaseOrderExcel implements IXCellTemplate {

    ITemplate purchaseOrderSheet;

    @Override
    public String getFileName() {
        return "PurchaseOrder.xls";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intertek.export.template.IXCellTemplate#getSheets()
     */
    @Override
    public List<ITemplate> getSheets() {
        List<ITemplate> list = new ArrayList<ITemplate>();
        list.add(purchaseOrderSheet);
        return list;
    }

    /**
     * Instantiates a new purchase order excel.
     * 
     * @param purchaseOrderSheet
     *            the object PurchaseOrderSheet
     */
    public PurchaseOrderExcel(ITemplate purchaseOrderSheet) {
        this.purchaseOrderSheet = purchaseOrderSheet;
    }

}
