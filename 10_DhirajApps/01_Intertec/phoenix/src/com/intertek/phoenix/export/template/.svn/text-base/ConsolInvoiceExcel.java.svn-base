/*
 * ConsolInvoiceExcel.java
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
 * The Class ConsolInvoiceExcel implements IXCellTemplate.
 */
public class ConsolInvoiceExcel implements IXCellTemplate {

    ITemplate consoldInvoiceSheet;

    @Override
    public String getFileName() {
        return "ConsoldInvoiceSheet.xls";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intertek.export.template.IXCellTemplate#getSheets()
     */
    @Override
    public List<ITemplate> getSheets() {
        List<ITemplate> list = new ArrayList<ITemplate>();
        list.add(consoldInvoiceSheet);
        return list;
    }

    /**
     * Instantiates a new consoldated invoice excel.
     * 
     * @param consoldInvoiceSheet
     *            the object ConsoldInvoiceSheet
     */
    public ConsolInvoiceExcel(ITemplate consoldInvoiceSheet) {
        this.consoldInvoiceSheet = consoldInvoiceSheet;
    }

}
