/*
 * ConsolInvoiceSheet.java
 * 
 * @version 1.0
 * 
 * Jun 30, 2009
 * 
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 */
package com.intertek.phoenix.export.template;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.MessageSource;
import com.intertek.export.template.ExportColumn;
import com.intertek.export.template.ITemplate;
import com.intertek.phoenix.entity.ConsolidatedInvoice;
import com.intertek.phoenix.util.CommonUtil;

/**
 * The Class ConsolInvoiceSheet.
 */
public class ConsolInvoiceSheet implements ITemplate {

    /** The rows. */
    List<ConsolidatedInvoice> rows;

    /** The message source. */
    private MessageSource messageSource;

    /** The current index. */
    int currentIndex = 0;

    /** The header. */
    List<ExportColumn> header = null;

    /*
     * (non-Javadoc)
     * 
     * @see com.intertek.export.template.ITemplate#getHeader()
     */
    @Override
    public List<ExportColumn> getHeader() {

        return header;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intertek.export.template.ITemplate#getName()
     */
    @Override
    public String getName() {
        return "ConsolidateInvoice";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intertek.export.template.ITemplate#getNextRow()
     */
    @Override
    public Object getNextRow() {
        ConsolidatedInvoice obj = rows.get(currentIndex++);
        return obj;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intertek.export.template.ITemplate#hasMoreRow()
     */
    @Override
    public boolean hasMoreRow() {
        return rows != null && currentIndex < rows.size();
    }

    /**
     * Instantiates a new consolidate invoicet sheet.
     * 
     * @param <ConsolidatedInvoice>result
     *            the list of ConsolidatedInvoice
     */
    @SuppressWarnings("unchecked")
    public ConsolInvoiceSheet(List<?> result) {
        this.rows = (List<ConsolidatedInvoice>) result;
        setHeader();
    }

    /**
     * Sets the header.
     */
    public void setHeader() {
        header = new ArrayList<ExportColumn>();
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("buName"), "id.buName"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("customerCode"), "custCode"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("customerName"), "customer.name"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("Invoice"), "id.consolInvoiceNo"));
    }

}
