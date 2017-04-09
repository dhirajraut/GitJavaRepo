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
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.intertek.entity.Branch;
import com.intertek.entity.ProductGroup;

/**
 * @author richard.qin/eric.nguyen
 */
@Entity
@Table(name = "PHX_QUOTE")
public class Quote {
    @Id
    @SequenceGenerator(name = "PHX_QUOTE_SEQ", sequenceName = "PHX_QUOTE_SEQ", allocationSize = 1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator = "PHX_QUOTE_SEQ")
    @Column(name = "ID")
    private Long id;

    @Column(name = "FOLLOW_UP")
    @org.hibernate.annotations.Type(type = "yes_no")
    private boolean followUp;

    @Column(name = "QUOTE_NUMBER", length = 128)
    private String quoteNumber;

    @Column(name = "QUOTE_DATE")
    private Timestamp quoteDate;

    @Column(name = "ORIGIN")
    @Enumerated(EnumType.STRING)
    private OrderOrigin origin;

    @Column(name = "ORDER_NUMBER", length = 128)
    private String orderNumber;

    @Column(name = "ORDER_DATE")
    private Timestamp orderDate;

    @Column(name = "CUSTOMER_ID")
    private String customerId;

    @Column(name = "CONTRACT_ID")
    private String contractId;

    @Column(name = "CONTACT_ID")
    private String contactId;

    @Column(name = "ORDER_AMOUNT")
    private double orderAmount;

    @Column(name = "AGREEMENT_PO_NUMBER")
    private String agreementPONumber;

    @Column(name = "CURRENCY_CODE")
    private String currencyCode;

    @Column(name = "MODEL_NUMBER")
    private String modelNumber;

    @Column(name = "PRODUCTION_EVAL_DESC")
    private String productionEvaluationDescription;

    @Column(name = "BILL_TO_CUSTOMER_ID")
    private String billToCustomerId;

    @Column(name = "BILL_TO_CONTACT_ID")
    private String billToContactId;

    @Column(name = "SHIP_TO_CUSTOMER_ID")
    private String shipToCustomerId;

    @Column(name = "SHIP_TO_CONTACT_ID")
    private String shipToContactId;

    @ManyToOne
    @JoinColumns( { @JoinColumn(name = "PRODUCT_GROUP_SET", referencedColumnName = "PRODUCT_GROUP_SET"),
                   @JoinColumn(name = "GROUP_ID", referencedColumnName = "GROUP_ID"), @JoinColumn(name = "BEGIN_DATE", referencedColumnName = "BEGIN_DATE") })
    private ProductGroup productGroup;

    @ManyToOne
    @JoinColumn(name = "BRANCH_ID")
    private Branch branch;

    @Column(name = "PRIMARY_SALE_PERSION_ID")
    private String primarySalePersonId;

    @Column(name = "SECONDARY_SALE_PERSON_ID")
    private String secondarySalePersonId;

    @Column(name = "PROJECT_MANAGER_ID")
    private String projectManagerId;

    @Column(name = "CUSTOMER_READY_DATE")
    private Timestamp customerReadyDate;

    @Column(name = "PROMISE_COMPLETION_DATE")
    private Timestamp promiseCompletionDate;

    @OneToMany(mappedBy = "quote")
    @org.hibernate.annotations.Cascade( { org.hibernate.annotations.CascadeType.SAVE_UPDATE, org.hibernate.annotations.CascadeType.MERGE })
    private Set<QuoteLine> quoteLines;

    public Quote() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<QuoteLine> getQuoteLines() {
        if (quoteLines == null) {
            quoteLines = new HashSet<QuoteLine>();
        }
        return quoteLines;
    }

    public boolean addQuoteLine(QuoteLine quoteLine) {
        return getQuoteLines().add(quoteLine);
    }

    public boolean removeQuoteLine(QuoteLine quoteLine) {
        return getQuoteLines().remove(quoteLine);
    }

    public String getQuoteNumber() {
        return quoteNumber;
    }

    public void setQuoteNumber(String quoteNumber) {
        this.quoteNumber = quoteNumber;
    }

    public Timestamp getQuoteDate() {
        return quoteDate;
    }

    public void setQuoteDate(Timestamp quoteDate) {
        this.quoteDate = quoteDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public String getAgreementPONumber() {
        return agreementPONumber;
    }

    public void setAgreementPONumber(String agreementPONumber) {
        this.agreementPONumber = agreementPONumber;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getModelNumber() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber = modelNumber;
    }

    public String getProductionEvaluationDescription() {
        return productionEvaluationDescription;
    }

    public void setProductionEvaluationDescription(String productionEvaluationDescription) {
        this.productionEvaluationDescription = productionEvaluationDescription;
    }

    public String getBillToCustomerId() {
        return billToCustomerId;
    }

    public void setBillToCustomerId(String billToCustomerId) {
        this.billToCustomerId = billToCustomerId;
    }

    public String getBillToContactId() {
        return billToContactId;
    }

    public void setBillToContactId(String billToContactId) {
        this.billToContactId = billToContactId;
    }

    public String getShipToCustomerId() {
        return shipToCustomerId;
    }

    public void setShipToCustomerId(String shipToCustomerId) {
        this.shipToCustomerId = shipToCustomerId;
    }

    public String getShipToContactId() {
        return shipToContactId;
    }

    public void setShipToContactId(String shipToContactId) {
        this.shipToContactId = shipToContactId;
    }

    public ProductGroup getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(ProductGroup productGroup) {
        this.productGroup = productGroup;
    }

    public Branch getBranch() {
        return branch;
    }

    public void setBranch(Branch branch) {
        this.branch = branch;
    }

    public String getPrimarySalePersonId() {
        return primarySalePersonId;
    }

    public void setPrimarySalePersonId(String primarySalePersonId) {
        this.primarySalePersonId = primarySalePersonId;
    }

    public String getSecondarySalePersonId() {
        return secondarySalePersonId;
    }

    public void setSecondarySalePersonId(String secondarySalePersonId) {
        this.secondarySalePersonId = secondarySalePersonId;
    }

    public String getProjectManagerId() {
        return projectManagerId;
    }

    public void setProjectManagerId(String projectManagerId) {
        this.projectManagerId = projectManagerId;
    }

    public Timestamp getCustomerReadyDate() {
        return customerReadyDate;
    }

    public void setCustomerReadyDate(Timestamp customerReadyDate) {
        this.customerReadyDate = customerReadyDate;
    }

    public Timestamp getPromiseCompletionDate() {
        return promiseCompletionDate;
    }

    public void setPromiseCompletionDate(Timestamp promiseCompletionDate) {
        this.promiseCompletionDate = promiseCompletionDate;
    }

    public void setQuoteLines(Set<QuoteLine> quoteLines) {
        this.quoteLines = quoteLines;
    }

    public OrderOrigin getOrigin() {
        return origin;
    }

    public void setOrigin(OrderOrigin origin) {
        this.origin = origin;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Timestamp getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(Timestamp orderDate) {
        this.orderDate = orderDate;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public boolean isFollowUp() {
        return followUp;
    }

    public void setFollowUp(boolean followUp) {
        this.followUp = followUp;
    }
}
