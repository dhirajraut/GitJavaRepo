/*
 * JobLogSheet.java
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

import com.intertek.export.template.ExportColumn;
import com.intertek.export.template.ITemplate;
import com.intertek.phoenix.util.CommonUtil;
import com.intertek.phoenix.web.controller.joblog.JobLogCESearchResult;

// TODO: Auto-generated Javadoc
/**
 * The Class JobLogSheet.
 */
public class JobLogSheet implements ITemplate {

    /** The rows. */
    List<JobLogCESearchResult> rows;

    /** The current index. */
    int currentIndex = 0;

    /** The header. */
    List<ExportColumn> header = null;

    /** The name. */
    String name;

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
        return name;
    }

    /*
     * (non-Javadoc)
     * 
     * @see com.intertek.export.template.ITemplate#getNextRow()
     */
    @Override
    public Object getNextRow() {
        JobLogCESearchResult obj = rows.get(currentIndex++);
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
     * Instantiates a new purchase order sheet.
     * 
     * @param rows
     *            the list of PurchaseOrder
     * @param header
     *            the header
     */
    public JobLogSheet(List<JobLogCESearchResult> rows, String header) {
        this.rows = rows;
        setHeader(header);
    }

    /**
     * Sets the common header.
     * 
     * @param headerName
     *            the header name
     */
    public void setHeader(String headerName) {
        header = new ArrayList<ExportColumn>();
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("projectNumber"), "projectNumber"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("taskno"), "taskId"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("projectOperationalStatus"), "projectOperationalStatus"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("orderStatus"), "orderStatus"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("customerName"), "customerName"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("modelNumber"), "modelNumber"));
        if (headerName.equalsIgnoreCase("Main")) {
            setMainHdr(headerName);
        }
        else if (headerName.equalsIgnoreCase("Billing")) {
            setBillingHdr(headerName);
        }
        else {
            setProcessHdr(headerName);
        }

    }

    /**
     * Sets the process log hdr.
     * 
     * @param headerName
     *            the new process hdr
     */
    private void setProcessHdr(String headerName) {
        this.name = headerName;
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("taskName"), "taskName"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("taskOperationalStatus"), "taskOperationalStatus"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("currentMonthBilled"), "currentMonthBilled"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("currentMonth"), "currentMonth"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("currentMonth+1"), "currentMonthPlus1"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("rowTotalOfRev"), "rowTotalOfRev"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("taskManager"), "taskManager"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("reportToReviewerDate"), "reportReviewerDate"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("s"), "reviewerName"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("report/sentDate"), "reportSentDate"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("isfDate"), "isfDate"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("taskComments"), "taskComments"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("taskDescription"), "taskDescription"));
    }

    /**
     * Sets the billing hdr.
     * 
     * @param headerName
     *            the new billing hdr
     */
    private void setBillingHdr(String headerName) {
        this.name = headerName;
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("taskName"), "taskName"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("taskOperationalStatus"), "taskOperationalStatus"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("currentMonthBilled"), "currentMonthBilled"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("currentMonth"), "currentMonth"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("rowTotalOfRev"), "rowTotalOfRev"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("billStatus"), "billStatus"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("isfDate"), "isfDate"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("dateInvoiced"), "dateInvoiced"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("amtInvoiced"), "amountInvoiced"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("invoiceNumber"), "invoiceNumber"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("invoicedCredited"), "invoicedCredited"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("reopenDate"), "reOpenDate"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("taskManager"), "taskManager"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("taskComments"), "taskComments"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("taskDescription"), "taskDescription"));

    }

    /**
     * Sets the main hdr.
     * 
     * @param headerName
     *            the new main hdr
     */
    private void setMainHdr(String headerName) {
        this.name = headerName;
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("taskSampleDescription"), "taskSampleDescription"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("taskName"), "taskName"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("taskDescription"), "taskDescription"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("taskComments"), "taskComments"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("taskOperationalStatus"), "taskOperationalStatus"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("taskOwningOrg"), "taskOwningOrg"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("currentMonthBilled"), "currentMonthBilled"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("pastDue"), "pastDue"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("currentMonth"), "currentMonth"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("currentMonth+1"), "currentMonthPlus1"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("currentMonth+2"), "currentMonthPlus2"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("futureMonth"), "futureMonth"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("rowTotalOfRev"), "rowTotalOfRev"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("stream"), "stream"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("serviceTypeCode"), "serviceTypeCode"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("taskReadyDate"), "taskReadyDate"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("promisedCompletionDate"), "promissedComplaintDate"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("projectManager"), "projectManager"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("salesRep"), "salesRep"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("taskManager"), "taskManager"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("projectOwningOrg"), "projectOwningOrg"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("actualReadyDate"), "actualReadyDate"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("customerReadyDate"), "customerReadyDate"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("serviceLocation"), "serviceLocation"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("billStatus"), "billStatus"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("serviceOfferingParentName"), "serviceOfferingParentName"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("jobDescription"), "jobDescription"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("contractID"), "contractno"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("customerNumber"), "customerNumber"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("quoteNumber"), "quoteNumber"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("orderNumber"), "orderNumber"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("ecsOrderNumber"), "ecsOrderNumber"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("orderAmt"), "orderAmount"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("quoteDate"), "quoteDate"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("taskStartDate"), "taskStartDate"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("taskCompletionDate"), "taskCompletionDate"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("orderDate"), "orderDate"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("deliverableShipDate"), "deliveryShipDate"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("actualStartDate"), "actualStartDate"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("poNumber"), "poNumber"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("costBudgetHours"), "costBudgetHrs"));
        header.add(new ExportColumn(CommonUtil.getTrackerResValue("creditoverrideby"), "creditOverRide"));

    }
}
