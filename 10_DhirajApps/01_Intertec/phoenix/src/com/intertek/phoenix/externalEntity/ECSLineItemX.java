/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 */
package com.intertek.phoenix.externalEntity;

import java.sql.Timestamp;
import java.util.Date;

import com.intertek.phoenix.entity.QuoteLine;
import com.intertek.phoenix.entity.UserType;

/**
 * Object to send to ESB
 * 
 * @author Eric.Nguyen
 */
public class ECSLineItemX {
    private String lineItemNumber;
    private String itemStandardId;
    private String itemStandardDescription;
    private double quotedValue;
    private String taskOwnerId;
    private ServiceOfferingX serviceOffering;
    private Date startDate;
    private Date completionDate;
    private String jobTitle;
    private int billableHours;

    public QuoteLine convert(){
        QuoteLine ql=new QuoteLine();
        ql.setLineItemNumber(Integer.parseInt(getLineItemNumber()));
        ql.setItemStandardId(getItemStandardId());
        ql.setItemStandardDescription(getItemStandardDescription());
        ql.setQuotedValue(getQuotedValue());
        ql.setTaskOwnerId(getTaskOwnerId());
        ql.setServiceOffering(getServiceOffering().convert());
        ql.setStartDate(new Timestamp(getStartDate().getTime()));
        ql.setCompletionDate(new Timestamp(getCompletionDate().getTime()));
        ql.setJobTitle(UserType.valueOf(getJobTitle()));
        ql.setBillableHours(getBillableHours());        
        return ql;
    }
    
    public String getLineItemNumber() {
        return lineItemNumber;
    }

    public void setLineItemNumber(String lineItemNumber) {
        this.lineItemNumber = lineItemNumber;
    }

    public String getItemStandardId() {
        return itemStandardId;
    }

    public void setItemStandardId(String itemStandardId) {
        this.itemStandardId = itemStandardId;
    }

    public String getItemStandardDescription() {
        return itemStandardDescription;
    }

    public void setItemStandardDescription(String itemStandardDescription) {
        this.itemStandardDescription = itemStandardDescription;
    }

    public double getQuotedValue() {
        return quotedValue;
    }

    public void setQuotedValue(double quotedValue) {
        this.quotedValue = quotedValue;
    }

    public String getTaskOwnerId() {
        return taskOwnerId;
    }

    public void setTaskOwnerId(String taskOwnerId) {
        this.taskOwnerId = taskOwnerId;
    }

    public ServiceOfferingX getServiceOffering() {
        return serviceOffering;
    }

    public void setServiceOffering(ServiceOfferingX serviceOffering) {
        this.serviceOffering = serviceOffering;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Date completionDate) {
        this.completionDate = completionDate;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    public int getBillableHours() {
        return billableHours;
    }

    public void setBillableHours(int billableHours) {
        this.billableHours = billableHours;
    }
}
