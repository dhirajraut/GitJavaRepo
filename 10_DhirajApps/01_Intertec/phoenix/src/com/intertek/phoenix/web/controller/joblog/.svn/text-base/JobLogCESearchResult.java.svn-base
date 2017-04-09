/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */

package com.intertek.phoenix.web.controller.joblog;

import java.text.SimpleDateFormat;
import java.sql.Timestamp;
import com.intertek.domain.Search;
import com.intertek.phoenix.util.ArrayMapGrid;
import com.intertek.util.Constants;

/**
 * This class is to hold the search result and set the updated value from GUI
 * 
 * @version 1.0 May 25, 2009
 * @author Patni *
 */

public class JobLogCESearchResult extends Search {

    // form search fields

    private String projectNumber;

    private String taskId;

    private String projectOperationalStatus;

    private String orderStatus;

    private String customerName;

    private String modelNumber;

    private String taskSampleDescription;

    private String taskName;

    private String taskDescription;

    private String taskComments;

    private String taskOperationalStatus;

    private String taskOwningOrg;

    private String currentMonthBilled;

    private String pastDue;

    private String currentMonth;

    private String currentMonthPlus1;

    private String currentMonthPlus2;

    private String futureMonth;

    private String rowTotalOfRev;

    private String stream;

    private String serviceTypeCode;

    private String taskReadyDate;

    private String promissedComplaintDate;

    private String projectManager;

    private String salesRep;

    private String taskManager;

    private String projectOwningOrg;

    private String actualReadyDate;

    private String customerReadyDate;

    private String serviceLocation;

    private String billStatus;

    private String serviceOfferingParentName;

    private String jobDescription;

    private String contractno;

    private String customerNumber;

    private String contact;

    private String quoteNumber;

    private String orderNumber;

    private String ecsOrderNumber;

    private String orderAmount;

    private String quoteDate;

    private String taskStartDate;

    private String taskCompletionDate;

    private String orderDate;

    private String deliveryShipDate;

    private String actualStartDate;

    private String poNumber;

    private String costBudgetHrs;

    private String creditOverRide;

    private String lineNumber;

    private String invoiceNumber;

    private SimpleDateFormat dateFormat = new SimpleDateFormat(Constants.DD_MM_YYYY_DATE_FORMAT);

    // Billing

    private Timestamp isfDate;

    private Timestamp dateInvoiced;

    private double amountInvoiced;

    private String invoicedNo;

    private String invoicedCredited;

    private Timestamp reOpenDate;

    // process log

    private Timestamp reportReviewerDate;

    private Timestamp reviewerName;

    private Timestamp reportSentDate;

    public String getProjectNumber() {
        return projectNumber;

    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getProjectOperationalStatus() {
        return projectOperationalStatus;
    }

    public void setProjectOperationalStatus(String projectOperationalStatus) {
        this.projectOperationalStatus = projectOperationalStatus;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getTaskSampleDescription() {
        return taskSampleDescription;
    }

    public void setTaskSampleDescription(String taskSampleDescription) {
        this.taskSampleDescription = taskSampleDescription;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskDescription() {
        return taskDescription;
    }

    public void setTaskDescription(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    public String getTaskComments() {
        return taskComments;
    }

    public void setTaskComments(String taskComments) {
        this.taskComments = taskComments;
    }

    public String getTaskOperationalStatus() {
        return taskOperationalStatus;
    }

    public void setTaskOperationalStatus(String taskOperationalStatus) {
        this.taskOperationalStatus = taskOperationalStatus;
    }

    public String getTaskOwningOrg() {
        return taskOwningOrg;
    }

    public void setTaskOwningOrg(String taskOwningOrg) {
        this.taskOwningOrg = taskOwningOrg;
    }

    public String getCurrentMonthBilled() {
        //
        return "$ " + currentMonthBilled;
    }

    public void setCurrentMonthBilled(double currentMonthBilled) {
        this.currentMonthBilled = Double.toString(currentMonthBilled);
    }

    public String getPastDue() {
        return "$ " + pastDue;
    }

    public void setPastDue(double pastDue) {
        this.pastDue = Double.toString(pastDue);
    }

    public String getCurrentMonth() {
        return currentMonth;
    }

    public void setCurrentMonth(double currentMonth) {
        this.currentMonth = Double.toString(currentMonth);
    }

    public String getCurrentMonthPlus1() {
        return "$ " + currentMonthPlus1;
    }

    public void setCurrentMonthPlus1(double currentMonthPlus1) {
        this.currentMonthPlus1 = Double.toString(currentMonthPlus1);
    }

    public String getCurrentMonthPlus2() {
        return "$ " + currentMonthPlus2;
    }

    public void setCurrentMonthPlus2(double currentMonthPlus2) {
        this.currentMonthPlus2 = Double.toString(currentMonthPlus2);
    }

    public String getFutureMonth() {
        return "$ " + futureMonth;
    }

    public void setFutureMonth(double futureMonth) {
        this.futureMonth = Double.toString(futureMonth);
    }

    public String getRowTotalOfRev() {
        return "$ " + rowTotalOfRev;
    }

    public void setRowTotalOfRev(double rowTotalOfRev) {
        this.rowTotalOfRev = Double.toString(rowTotalOfRev);
    }

    public String getStream() {
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public String getServiceTypeCode() {
        return serviceTypeCode;
    }

    public void setServiceTypeCode(String serviceTypeCode) {
        this.serviceTypeCode = serviceTypeCode;
    }

    public String getTaskReadyDate() {
        return taskReadyDate;
    }

    public void setTaskReadyDate(Timestamp taskReadyDate) {

        this.taskReadyDate = dateFormat.format(taskReadyDate);
    }

    public String getPromissedComplaintDate() {
        return promissedComplaintDate;
    }

    public void setPromissedComplaintDate(Timestamp promissedComplaintDate) {
        this.promissedComplaintDate = dateFormat.format(promissedComplaintDate);
    }

    public String getProjectManager() {
        return projectManager;
    }

    public void setProjectManager(String projectManager) {
        this.projectManager = projectManager;
    }

    public String getSalesRep() {
        return salesRep;
    }

    public void setSalesRep(String salesRep) {
        this.salesRep = salesRep;
    }

    public String getTaskManager() {
        return taskManager;
    }

    public void setTaskManager(String taskManager) {
        this.taskManager = taskManager;
    }

    public String getProjectOwningOrg() {
        return projectOwningOrg;
    }

    public void setProjectOwningOrg(String projectOwningOrg) {
        this.projectOwningOrg = projectOwningOrg;
    }

    public String getActualReadyDate() {
        return actualReadyDate;
    }

    public void setActualReadyDate(Timestamp actualReadyDate) {
        this.actualReadyDate = dateFormat.format(actualReadyDate);
    }

    public String getCustomerReadyDate() {
        return customerReadyDate;
    }

    public void setCustomerReadyDate(Timestamp customerReadyDate) {
        this.customerReadyDate = dateFormat.format(customerReadyDate);
    }

    public String getServiceLocation() {
        return serviceLocation;
    }

    public void setServiceLocation(String serviceLocation) {
        this.serviceLocation = serviceLocation;
    }

    public String getBillStatus() {
        return billStatus;
    }

    public void setBillStatus(String billStatus) {
        this.billStatus = billStatus;
    }

    public String getServiceOfferingParentName() {
        return serviceOfferingParentName;
    }

    public void setServiceOfferingParentName(String serviceOfferingParentName) {
        this.serviceOfferingParentName = serviceOfferingParentName;
    }

    public String getJobDescription() {
        return jobDescription;
    }

    public void setJobDescription(String jobDescription) {
        this.jobDescription = jobDescription;
    }

    public String getContractno() {
        return contractno;
    }

    public void setContractno(String contractno) {
        this.contractno = contractno;
    }

    public String getCustomerNumber() {
        return customerNumber;
    }

    public void setCustomerNumber(String customerNumber) {
        this.customerNumber = customerNumber;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public String getQuoteNumber() {
        return quoteNumber;
    }

    public void setQuoteNumber(String quoteNumber) {
        this.quoteNumber = quoteNumber;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getEcsOrderNumber() {
        return ecsOrderNumber;
    }

    public void setEcsOrderNumber(String ecsOrderNumber) {
        this.ecsOrderNumber = ecsOrderNumber;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(double orderAmount) {
        this.orderAmount = Double.toString(orderAmount);
    }

    public String getQuoteDate() {
        return quoteDate;
    }

    public void setQuoteDate(Timestamp quoteDate) {
        this.quoteDate = dateFormat.format(quoteDate);
    }

    public String getTaskStartDate() {
        return taskStartDate;
    }

    public void setTaskStartDate(Timestamp taskStartDate) {
        this.taskStartDate = dateFormat.format(taskStartDate);
        ;
    }

    public String getTaskCompletionDate() {
        return taskCompletionDate;
    }

    public void setTaskCompletionDate(Timestamp taskCompletionDate) {
        this.taskCompletionDate = dateFormat.format(taskCompletionDate);
    }

    public String getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = dateFormat.format(orderDate);
    }

    public String getDeliveryShipDate() {
        return deliveryShipDate;
    }

    public void setDeliveryShipDate(Timestamp deliveryShipDate) {
        this.deliveryShipDate = dateFormat.format(deliveryShipDate);
    }

    public String getActualStartDate() {
        return actualStartDate;
    }

    public void setActualStartDate(Timestamp actualStartDate) {
        this.actualStartDate = dateFormat.format(actualStartDate);
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(int poNumber) {
        this.poNumber = Integer.toString(poNumber);
    }

    public String getCostBudgetHrs() {
        return costBudgetHrs;
    }

    public void setCostBudgetHrs(String costBudgetHrs) {
        this.costBudgetHrs = costBudgetHrs;
    }

    public String getCreditOverRide() {
        return creditOverRide;
    }

    public void setCreditOverRide(String creditOverRide) {
        this.creditOverRide = creditOverRide;
    }

    public Timestamp getIsfDate() {
        return isfDate;
    }

    public void setIsfDate(Timestamp isfDate) {
        this.isfDate = isfDate;
    }

    public Timestamp getDateInvoiced() {
        return dateInvoiced;
    }

    public void setDateInvoiced(Timestamp dateInvoiced) {
        this.dateInvoiced = dateInvoiced;
    }

    public double getAmountInvoiced() {
        return amountInvoiced;
    }

    public void setAmountInvoiced(double amountInvoiced) {
        this.amountInvoiced = amountInvoiced;
    }

    public String getInvoicedNo() {
        return invoicedNo;
    }

    public void setInvoicedNo(String invoicedNo) {
        this.invoicedNo = invoicedNo;
    }

    public String getInvoicedCredited() {
        return invoicedCredited;
    }

    public void setInvoicedCredited(String invoicedCredited) {
        this.invoicedCredited = invoicedCredited;
    }

    public Timestamp getReOpenDate() {
        return reOpenDate;
    }

    public void setReOpenDate(Timestamp reOpenDate) {
        this.reOpenDate = reOpenDate;
    }

    public Timestamp getReportReviewerDate() {
        return reportReviewerDate;
    }

    public void setReportReviewerDate(Timestamp reportReviewerDate) {
        this.reportReviewerDate = reportReviewerDate;
    }

    public Timestamp getReviewerName() {
        return reviewerName;
    }

    public void setReviewerName(Timestamp reviewerName) {
        this.reviewerName = reviewerName;
    }

    public Timestamp getReportSentDate() {
        return reportSentDate;
    }

    public void setReportSentDate(Timestamp reportSentDate) {
        this.reportSentDate = reportSentDate;
    }

    public String getLineNumber() {
        return lineNumber;
    }

    public void setLineNumber(String lineNumber) {
        this.lineNumber = lineNumber;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    /**
     * Name :saveValues Date :May 25, 2009 purpose :to set the queried results
     * 
     * @return
     */

    public void setValues(ArrayMapGrid arrayMapGrid) {
        projectNumber = arrayMapGrid.getFieldValue("projectNumber").toString();

        taskId = arrayMapGrid.getFieldValue("taskNumber").toString();

        projectOperationalStatus = arrayMapGrid.getFieldValue("projectOperationalStatus").toString();

        orderStatus = arrayMapGrid.getFieldValue("orderStatus").toString();

        customerName = arrayMapGrid.getFieldValue("customerName").toString();
        modelNumber = arrayMapGrid.getFieldValue("modelNumber").toString();

        if (arrayMapGrid.getFieldValue("taskSampleDescription") != null) {
            taskSampleDescription = arrayMapGrid.getFieldValue("taskSampleDescription").toString();
        }
        if (arrayMapGrid.getFieldValue("taskName") != null) {
            taskName = arrayMapGrid.getFieldValue("taskName").toString();
        }
        if (arrayMapGrid.getFieldValue("taskDescription") != null) {
            taskDescription = arrayMapGrid.getFieldValue("taskDescription").toString();
        }
        if (arrayMapGrid.getFieldValue("taskComments") != null) {
            taskComments = arrayMapGrid.getFieldValue("taskComments").toString();
        }

        if (arrayMapGrid.getFieldValue("taskOperationalStatus") != null) {
            taskOperationalStatus = arrayMapGrid.getFieldValue("taskOperationalStatus").toString();
        }
        if (arrayMapGrid.getFieldValue("taskOwningOrg") != null) {
            taskOwningOrg = arrayMapGrid.getFieldValue("taskOwningOrg").toString();
        }
        if (arrayMapGrid.getFieldValue("currentMonthBilled") != null) {
            currentMonthBilled = arrayMapGrid.getFieldValue("currentMonthBilled").toString();
        }
        if (arrayMapGrid.getFieldValue("pastDue") != null) {
            pastDue = arrayMapGrid.getFieldValue("pastDue").toString();
        }
        if (arrayMapGrid.getFieldValue("currentMonth") != null) {
            currentMonth = arrayMapGrid.getFieldValue("currentMonth").toString();
        }
        if (arrayMapGrid.getFieldValue("currentMonthPlus1") != null) {
            currentMonthPlus1 = arrayMapGrid.getFieldValue("currentMonthPlus1").toString();
        }

        if (arrayMapGrid.getFieldValue("currentMonthPlus2") != null) {
            currentMonthPlus2 = arrayMapGrid.getFieldValue("currentMonthPlus2").toString();
        }
        if (arrayMapGrid.getFieldValue("futureMonths") != null) {
            futureMonth = arrayMapGrid.getFieldValue("futureMonths").toString();
        }
        if (arrayMapGrid.getFieldValue("rowTotalOfRev") != null) {
            rowTotalOfRev = arrayMapGrid.getFieldValue("rowTotalOfRev").toString();
        }
        if (arrayMapGrid.getFieldValue("businessStreamCode") != null) {
            stream = arrayMapGrid.getFieldValue("businessStreamCode").toString();
        }
        if (arrayMapGrid.getFieldValue("serviceTypeCode") != null) {
            serviceTypeCode = arrayMapGrid.getFieldValue("serviceTypeCode").toString();
        }
        if (arrayMapGrid.getFieldValue("taskReadyDate") != null) {
            taskReadyDate = arrayMapGrid.getFieldValue("taskReadyDate").toString();
        }

        if (arrayMapGrid.getFieldValue("promisedCompletionDate") != null) {
            promissedComplaintDate = arrayMapGrid.getFieldValue("promisedCompletionDate").toString();
        }

        if (arrayMapGrid.getFieldValue("projectManagerName") != null) {
            projectManager = arrayMapGrid.getFieldValue("projectManagerName").toString();
        }

        if (arrayMapGrid.getFieldValue("salesPersonName") != null) {
            salesRep = arrayMapGrid.getFieldValue("salesPersonName").toString();
        }

        if (arrayMapGrid.getFieldValue("taskManager") != null) {
            taskManager = arrayMapGrid.getFieldValue("taskManager").toString();
        }

        if (arrayMapGrid.getFieldValue("projectOwningOrg") != null) {
            projectOwningOrg = arrayMapGrid.getFieldValue("projectOwningOrg").toString();
        }
        if (arrayMapGrid.getFieldValue("actualReadyDate") != null) {
            actualReadyDate = arrayMapGrid.getFieldValue("actualReadyDate").toString();
        }
        if (arrayMapGrid.getFieldValue("customerReadyDate") != null) {
            customerReadyDate = arrayMapGrid.getFieldValue("customerReadyDate").toString();
        }
        if (arrayMapGrid.getFieldValue("serviceLocation") != null) {
            serviceLocation = arrayMapGrid.getFieldValue("serviceLocation").toString();
        }
        if (arrayMapGrid.getFieldValue("billStatus") != null) {
            billStatus = arrayMapGrid.getFieldValue("billStatus").toString();
        }
        if (arrayMapGrid.getFieldValue("serviceOfferingParentName") != null) {
            serviceOfferingParentName = arrayMapGrid.getFieldValue("serviceOfferingParentName").toString();
        }
        if (arrayMapGrid.getFieldValue("jobDescription") != null) {
            jobDescription = arrayMapGrid.getFieldValue("jobDescription").toString();
        }
        if (arrayMapGrid.getFieldValue("contractNumber") != null) {
            contractno = arrayMapGrid.getFieldValue("contractNumber").toString();
        }
        if (arrayMapGrid.getFieldValue("customerNumber") != null) {
            customerNumber = arrayMapGrid.getFieldValue("customerNumber").toString();
        }
        if (arrayMapGrid.getFieldValue("quoteNumber") != null) {
            quoteNumber = arrayMapGrid.getFieldValue("quoteNumber").toString();
        }

        if (arrayMapGrid.getFieldValue("orderNumber") != null) {
            orderNumber = arrayMapGrid.getFieldValue("orderNumber").toString();
        }
        if (arrayMapGrid.getFieldValue("ecsOrder#") != null) {
            ecsOrderNumber = arrayMapGrid.getFieldValue("ecsOrder#").toString();
        }
        if (arrayMapGrid.getFieldValue("orderAmt") != null) {
            orderAmount = arrayMapGrid.getFieldValue("orderAmt").toString();
        }
        if (arrayMapGrid.getFieldValue("quoteDate") != null) {
            quoteDate = arrayMapGrid.getFieldValue("quoteDate").toString();
        }
        if (arrayMapGrid.getFieldValue("taskStartDate") != null) {
            taskStartDate = arrayMapGrid.getFieldValue("taskStartDate").toString();
        }
        if (arrayMapGrid.getFieldValue("taskCompletionDate") != null) {
            taskCompletionDate = arrayMapGrid.getFieldValue("taskCompletionDate").toString();
        }
        if (arrayMapGrid.getFieldValue("orderDate") != null) {
            orderDate = arrayMapGrid.getFieldValue("orderDate").toString();
        }
        if (arrayMapGrid.getFieldValue("deliverableShipDate") != null) {
            deliveryShipDate = arrayMapGrid.getFieldValue("deliverableShipDate").toString();
        }
        if (arrayMapGrid.getFieldValue("actualStart") != null) {
            actualStartDate = arrayMapGrid.getFieldValue("actualStart").toString();
        }
        if (arrayMapGrid.getFieldValue("poNumber") != null) {
            poNumber = arrayMapGrid.getFieldValue("poNumber").toString();
        }
        if (arrayMapGrid.getFieldValue("costBudgetHours") != null) {
            costBudgetHrs = arrayMapGrid.getFieldValue("costBudgetHours").toString();
        }
        if (arrayMapGrid.getFieldValue("creditOverride") != null) {
            creditOverRide = arrayMapGrid.getFieldValue("creditOverride").toString();
        }

        if (arrayMapGrid.getFieldValue("lineNumber") != null) {
            lineNumber = arrayMapGrid.getFieldValue("lineNumber").toString();
        }

        if (arrayMapGrid.getFieldValue("invoiceNumber") != null) {
            invoiceNumber = arrayMapGrid.getFieldValue("invoiceNumber").toString();
        }

    }

}
