/**  
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 * @Date : 4/22/2009
 * @Author : The Phoenix Team
 */

package com.intertek.phoenix.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.hibernate.annotations.Index;

/**
 * ECS Quote Line.
 * 
 * @author richard.qin/eric.nguyen
 */
@Entity
@Table(name = "PHX_QUOTE_LINE")
public class QuoteLine {
    @Id
    @SequenceGenerator(name="QuoteLine_seq", sequenceName = "QUOTE_LINE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "QuoteLine_seq" )
    @Column(name = "ID")
    private Long id;

    @Column(name = "LINE_ITEM_NUMBER", precision = 10, scale = 0)
    private int lineItemNumber;

    @Column(name = "ITEM_STANDARD_ID", length = 45)
    private String itemStandardId; //test ID

    @Column(name = "ITEM_STANDARD_DESC", columnDefinition = "NVARCHAR2(1024)")
    private String itemStandardDescription;

    @Column(name = "QUOTED_VALUE", precision = 10, scale = 4)
    private double quotedValue;

    @Column(name = "TASK_OWNER_ID", length = 125)
    private String taskOwnerId;

    @ManyToOne
    @JoinColumn(name = "SERVICE_OFFERING_ID")
    private ServiceOffering serviceOffering;

    @Column(name = "START_DATE")
    private Timestamp startDate;

    @Column(name = "COMPLETION_DATE")
    private Timestamp completionDate;

    @Column(name = "JOB_TITLE")
    @Enumerated(EnumType.STRING)
    private UserType jobTitle;    

    @Column(name = "BILLABLE_HOURS", precision = 10, scale = 0)
    private int billableHours;

    @Column(name = "QUOTE_ID", updatable = false, insertable = false)
    private Long quoteId;
    @ManyToOne()
    @org.hibernate.annotations.Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE })
    @JoinColumn(name = "QUOTE_ID")
    @Index(name = "PHX_QUOTE_LINE_INDEX_QUOTE")
    private Quote quote;

    public QuoteLine() {

    }

    public void setQuote(Quote quote) {
        this.quoteId = quote.getId();
        this.quote = quote;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getLineItemNumber() {
        return lineItemNumber;
    }

    public void setLineItemNumber(int lineItemNumber) {
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

    public ServiceOffering getServiceOffering() {
        return serviceOffering;
    }

    public void setServiceOffering(ServiceOffering serviceOffering) {
        this.serviceOffering = serviceOffering;
    }

    public Timestamp getStartDate() {
        return startDate;
    }

    public void setStartDate(Timestamp startDate) {
        this.startDate = startDate;
    }

    public Timestamp getCompletionDate() {
        return completionDate;
    }

    public void setCompletionDate(Timestamp completionDate) {
        this.completionDate = completionDate;
    }

    public int getBillableHours() {
        return billableHours;
    }

    public void setBillableHours(int billableHours) {
        this.billableHours = billableHours;
    }

    public Long getQuoteId() {
        return quoteId;
    }

    public void setQuoteId(Long quoteId) {
        this.quoteId = quoteId;
    }

    public Quote getQuote() {
        return quote;
    }

    public UserType getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(UserType jobTitle) {
        this.jobTitle = jobTitle;
    }

}
