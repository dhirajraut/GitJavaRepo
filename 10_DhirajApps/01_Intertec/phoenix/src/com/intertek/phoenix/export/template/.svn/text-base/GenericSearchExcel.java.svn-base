/*
 * GenericSearchExcel.java
 * 
 * @version 1.0
 * 
 * Jul 2, 2009
 * 
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 */
package com.intertek.phoenix.export.template;

import java.util.ArrayList;
import java.util.List;

import com.intertek.export.template.ITemplate;
import com.intertek.export.template.IXCellTemplate;
import com.intertek.phoenix.search.AbstractRow;
import com.intertek.phoenix.search.Row;

/**
 * create the excel sheet, as per the entity type.
 * 
 * The Class GenericSearchExcel.
 */
public class GenericSearchExcel implements IXCellTemplate {

    AbstractRow row;
    /** The class name. */
    String className;

    /**
     * Instantiates a new generic search excel.
     * 
     * @param className
     *            the class name
     * @param results
     *            the results
     */
    public GenericSearchExcel(Row row, List<?> results) {
        this.row = (AbstractRow) row;
        this.row.setRows(results);
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intertek.export.template.IXCellTemplate#getFileName()
     */
    @Override
    public String getFileName() {
        return row.getName() + ".xls";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intertek.export.template.IXCellTemplate#getSheets()
     */
    @Override
    public List<ITemplate> getSheets() {
        List<ITemplate> list = new ArrayList<ITemplate>();
        ITemplate template = row;
        list.add(template);
        return list;
    }

}
