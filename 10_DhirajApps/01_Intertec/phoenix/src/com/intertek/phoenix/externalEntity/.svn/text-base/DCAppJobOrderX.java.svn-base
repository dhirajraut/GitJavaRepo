/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 */
package com.intertek.phoenix.externalEntity;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.intertek.phoenix.entity.OrderOrigin;
import com.intertek.phoenix.entity.Quote;
import com.intertek.phoenix.entity.QuoteLine;

/**
 * Object to send to ESB
 * 
 * @author Eric.Nguyen
 */
public class DCAppJobOrderX {
    private String projectNumber;
    private Date projectDate;
    private String customerId;
    private String contactId;
    private double orderAmount;
    private String currencyCode;
    /*
     * The payment term that will be printed in the invoice. If a payment term
     * is not sent from DC App., the payment terms from contract or customer
     * level will be utilized. Note – The code for payment terms need to be
     * manually maintained between the systems Phoenix and DC. App.
     */
    private String paymentTerms; // TODO: need map -- per Rafiq 8/11/09 will be removed

    private String modelNumber;
    /*
     * Evaluation description captured at header level. Equates to job
     * instructions at header level in Phoenix.
     */
    private String productionEvaluationDescription;

    private String billToCustomerId;
    private String billToContactId;
    private String shipToCustomerId;
    private String shipToContactId;
    private BusinessStreamX businessStream;
    private WarehouseX warehouse;
    private String primarySalePersonId;
    private String secondarySalePersonId;
    private String projectManagerId;
    private Date customerReadyDate;
    private Date promiseCompletionDate;
    private String contractId;

    private List<DCAppJobOrderLineItemX> lineItems;

    public Quote convert() {
        Quote q = new Quote();
        q.setOrigin(OrderOrigin.DC_APP);
        q.setQuoteNumber(getProjectNumber());// DC Project number is like a ECS quote
        q.setQuoteDate(new Timestamp(getProjectDate().getTime()));
        // q.setOrderNumber(getOrderNumber());
        // q.setOrderDate(new Timestamp(getOrderDate().getTime()));
        q.setCustomerId(getCustomerId());
        q.setContactId(getContactId());
        q.setOrderAmount(getOrderAmount());
        // q.setAgreementPONumber(getAgreementPONumber());
        q.setCurrencyCode(getCurrencyCode());
        q.setModelNumber(getModelNumber());
        q.setProductionEvaluationDescription(getProductionEvaluationDescription());
        q.setBillToCustomerId(getBillToCustomerId());
        q.setBillToContactId(getBillToContactId());
        q.setShipToCustomerId(getShipToCustomerId());
        q.setShipToContactId(getShipToContactId());
        q.setProductGroup(getBusinessStream().convert());
        q.setBranch(getWarehouse().convert());
        q.setPrimarySalePersonId(getPrimarySalePersonId());
        q.setSecondarySalePersonId(getSecondarySalePersonId());
        q.setProjectManagerId(getProjectManagerId());
        q.setCustomerReadyDate(new Timestamp(getCustomerReadyDate().getTime()));
        q.setPromiseCompletionDate(new Timestamp(getPromiseCompletionDate().getTime()));
        q.setContractId(getContractId());
        Set<QuoteLine> quoteLines = new HashSet<QuoteLine>();
        for (DCAppJobOrderLineItemX line : lineItems) {
            QuoteLine quoteLine = line.convert();
            quoteLine.setQuote(q);
            quoteLines.add(quoteLine);
        }
        

        q.setQuoteLines(quoteLines);
        return q;
    }

    public String getProjectNumber() {
        return projectNumber;
    }

    public void setProjectNumber(String projectNumber) {
        this.projectNumber = projectNumber;
    }

    public Date getProjectDate() {
        return projectDate;
    }

    public void setProjectDate(Date projectDate) {
        this.projectDate = projectDate;
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

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
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

    public BusinessStreamX getBusinessStream() {
        return businessStream;
    }

    public void setBusinessStream(BusinessStreamX businessStream) {
        this.businessStream = businessStream;
    }

    public WarehouseX getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(WarehouseX warehouse) {
        this.warehouse = warehouse;
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

    public Date getCustomerReadyDate() {
        return customerReadyDate;
    }

    public void setCustomerReadyDate(Date customerReadyDate) {
        this.customerReadyDate = customerReadyDate;
    }

    public List<DCAppJobOrderLineItemX> getLineItems() {
        return lineItems;
    }

    public void setLineItems(List<DCAppJobOrderLineItemX> lineItems) {
        this.lineItems = lineItems;
    }

    public String getBillToContactId() {
        return billToContactId;
    }

    public void setBillToContactId(String billToContactId) {
        this.billToContactId = billToContactId;
    }

    public Date getPromiseCompletionDate() {
        return promiseCompletionDate;
    }

    public void setPromiseCompletionDate(Date promiseCompletionDate) {
        this.promiseCompletionDate = promiseCompletionDate;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }
}
