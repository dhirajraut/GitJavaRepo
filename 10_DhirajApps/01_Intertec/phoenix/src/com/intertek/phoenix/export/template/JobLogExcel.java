/*
 * JobLogExcel.java
 * 
 * @version
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
import com.intertek.phoenix.web.controller.joblog.JobLogCESearchResult;

/**
 * The Class JobLogExcel.
 */
public class JobLogExcel implements IXCellTemplate {

    List<JobLogCESearchResult> jlResult = null;

    /*
     * (non-Javadoc)
     * 
     * @see com.intertek.export.template.IXCellTemplate#getFileName()
     */
    @Override
    public String getFileName() {
        return "Joblog.xls";
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intertek.export.template.IXCellTemplate#getSheets()
     */
    @Override
    public List<ITemplate> getSheets() {
        List<ITemplate> list = new ArrayList<ITemplate>();
        String sheetArr[] = new String[] { "Main", "Billing", "Process Log" };
        for (int i = 0; i < 3; i++) {
            ITemplate template = new JobLogSheet(jlResult, sheetArr[i]);
            list.add(template);
        }
        return list;
    }

    /**
     * Instantiates a new job log excel.
     * 
     * @param jlResult
     *            the jl result
     */
    public JobLogExcel(List<JobLogCESearchResult> jlResult) {
        this.jlResult = jlResult;
    }

}
