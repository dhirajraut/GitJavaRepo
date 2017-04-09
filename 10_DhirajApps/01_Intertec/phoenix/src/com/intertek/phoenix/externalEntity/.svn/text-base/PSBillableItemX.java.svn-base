/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 */
package com.intertek.phoenix.externalEntity;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.intertek.phoenix.PhoenixException;
import com.intertek.phoenix.entity.BillingEventItem;
import com.intertek.phoenix.entity.BillingLineDistribution;
import com.intertek.phoenix.entity.CEJobOrder;
import com.intertek.phoenix.search.SearchService;

/**
 * @author Eric.Nguyen
 */
public class PSBillableItemX {
    private String jobNumber;
    private String businessUnit;
    private String temporaryInvoiceNumber;
    private String jobLineNumber;  
    private int lineSequenceNumber; //TODO: need map
    private String lineDescription;
    private double invoiceAmt; //Total invoice amount -- should be sum of all line amount -- sum(totalLineAmnt)
    private double quantity;
    private double unitAmt;
    private double totalLineAmt; //Only use for validation -- invoiceAmt should equal to quantity*unitAmount
    private String billingCurrencyCd;
    
    private List<BillingLineDistributionX> billingLineDistribution;

    public String getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public String getTemporaryInvoiceNumber() {
        return temporaryInvoiceNumber;
    }

    public void setTemporaryInvoiceNumber(String temporaryInvoiceNumber) {
        this.temporaryInvoiceNumber = temporaryInvoiceNumber;
    }

    public int getLineSequenceNumber() {
        return lineSequenceNumber;
    }

    public void setLineSequenceNumber(int lineSequenceNumber) {
        this.lineSequenceNumber = lineSequenceNumber;
    }

    public String getLineDescription() {
        return lineDescription;
    }

    public void setLineDescription(String lineDescription) {
        this.lineDescription = lineDescription;
    }

    public String getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(String jobNumber) {
        this.jobNumber = jobNumber;
    }

    public String getJobLineNumber() {
        return jobLineNumber;
    }

    public void setJobLineNumber(String jobLineNumber) {
        this.jobLineNumber = jobLineNumber;
    }

    public double getInvoiceAmt() {
        return invoiceAmt;
    }

    public void setInvoiceAmt(double invoiceAmt) {
        this.invoiceAmt = invoiceAmt;
    }

    public double getQuantity() {
        return quantity;
    }

    public void setQuantity(double quantity) {
        this.quantity = quantity;
    }

    public double getUnitAmt() {
        return unitAmt;
    }

    public void setUnitAmt(double unitAmt) {
        this.unitAmt = unitAmt;
    }

    public double getTotalLineAmt() {
        return totalLineAmt;
    }

    public void setTotalLineAmt(double totalLineAmt) {
        this.totalLineAmt = totalLineAmt;
    }

    public String getBillingCurrencyCd() {
        return billingCurrencyCd;
    }

    public void setBillingCurrencyCd(String billingCurrencyCd) {
        this.billingCurrencyCd = billingCurrencyCd;
    }

    public List<BillingLineDistributionX> getBillingLineDistribution() {
        return billingLineDistribution;
    }

    public void setBillingLineDistribution(List<BillingLineDistributionX> billingLineDistribution) {
        this.billingLineDistribution = billingLineDistribution;
    }
}
