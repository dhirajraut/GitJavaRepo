/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 */
package com.intertek.phoenix.externalEntity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

import com.intertek.entity.BusinessUnit;
import com.intertek.entity.CustAddrSeq;
import com.intertek.entity.Customer;
import com.intertek.phoenix.esb.Logable;

/**
 * Object to send to ESB
 * 
 * @author Eric.Nguyen
 */
public class CustomerX implements Logable {
    private String customerCode;
    private String customerName;
    private char customerStatus;
    private Date statusDate;;
    private String alternateName;
    private Date sinceDate;
    private Date addDate;
    private String industry;
    private String currencyCd;
    private String customerType;
    private String locationType;
    private String parentCustomerCode;
    private String creditAnalystName;
    private String collector;
    private boolean creditApplication=false;
    private boolean creditHold=false;
    private double creditLimit;
    private boolean interUnit=false;
    private String interUnitBusinessUnitName;
    private String interUnitBusinessUnit;
    private boolean interUnitPending=false;
    private String accountOwner;
    private String paymentType;
    private String paymentTerms;
    private String primaryIndustry;
    private String vip;
    private String customerStatusComment;
    private String dunsNumber;
    private String internalOriginDivision;
    private String internalOriginBusStrm;
    private String internalOrigin;
    private String invoiceType;
    private Date lastServiceDate;
    private String lastServiceLocation;
    private boolean legalEntitySubsidiary=false;
    private boolean referral=false;
    private String referralParentCust;
    private String referralChildCust;
    private String referralNotes;
    private String secondaryIndustry;
    private String taxpayerIdNumber;
    private String custType;
    private List<CustomerAddressSequenceX> customerAddressSequence;

    public CustomerX() {

    }

    public CustomerX(Customer c) {
        this.customerCode = c.getCustCode();
        this.customerName = c.getName();
        this.customerStatus = (c.getStatus() + "A").toUpperCase().charAt(0);
        this.statusDate = c.getStatusDate();
        this.alternateName = c.getAlternateName();
        this.sinceDate = c.getSinceDate();
        this.addDate = c.getAddDate();
        this.industry = c.getIndustry();
        this.currencyCd = c.getCurrencyCd();
        this.customerType = c.getType();
        this.locationType = c.getLocationType();
        this.parentCustomerCode = c.getParentCustCode();
        this.creditAnalystName = c.getCreditAnalystName();
        this.collector = c.getCollectorName();
        this.creditApplication = c.getCreditApproved();
        this.creditHold = (c.getCreditHoldInd() + "N").toUpperCase().charAt(0) == 'Y';
        this.creditLimit = c.getCreditLimit();
        this.interUnit = c.getInterunitInd();
        this.interUnitBusinessUnitName = c.getInterunitBusUnitName(); // maybe BU description?
        BusinessUnit bu=c.getInterunitBusUnit();
        this.interUnitBusinessUnit = bu==null?null:bu.getName();
        this.interUnitPending = c.getInterunitPendingInd();
        this.accountOwner = c.getAccountOwnerName();
        this.paymentType = c.getPaymentType();
        this.paymentTerms = c.getPaymentTerms();
        this.primaryIndustry = c.getPrimaryIndustry();
        this.vip = c.getVipPlatinumPreferred();
        // this.customerStatusComment=c.get;
        this.dunsNumber = c.getDunnAndBradstreetNumber();
        this.internalOriginDivision = c.getBusinessDivisions();
        this.internalOriginBusStrm = c.getBusinessStreams();
        this.internalOrigin = c.getOriginSource();
        this.invoiceType = c.getInvoiceType();
        this.lastServiceDate = c.getLastServiceDate();
        this.lastServiceLocation = c.getLastServiceLocation();
        // this.legalEntitySubsidiary=c.gete; --comment out for now
        // this.referral=c.getr; //need calculation
        this.referralParentCust = c.getParentCustomerName();
        this.referralChildCust = c.getChildCustomerName();
        this.referralNotes = c.getNotes();
        this.secondaryIndustry = c.getSecondaryIndustry();
        this.taxpayerIdNumber = c.getTaxpayerIDNumber();
        this.custType = c.getType();

        setCustAddrSeq(c.getCustAddrSeqs());
    }

    private void setCustAddrSeq(Set<CustAddrSeq> set) {
        if (set == null) {
            return;
        }
        this.customerAddressSequence = new ArrayList<CustomerAddressSequenceX>();
        for (CustAddrSeq ca : set) {
            this.customerAddressSequence.add(new CustomerAddressSequenceX(ca));
        }
    }

    @Override
    public String getId() {
        return getCustomerCode();
    }

    public String getCustomerCode() {
        return customerCode;
    }

    public void setCustomerCode(String customerCode) {
        this.customerCode = customerCode;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public char getCustomerStatus() {
        return customerStatus;
    }

    public void setCustomerStatus(char customerStatus) {
        this.customerStatus = customerStatus;
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    public String getAlternateName() {
        return alternateName;
    }

    public void setAlternateName(String alternateName) {
        this.alternateName = alternateName;
    }

    public Date getSinceDate() {
        return sinceDate;
    }

    public void setSinceDate(Date sinceDate) {
        this.sinceDate = sinceDate;
    }

    public Date getAddDate() {
        return addDate;
    }

    public void setAddDate(Date addDate) {
        this.addDate = addDate;
    }

    public String getIndustry() {
        return industry;
    }

    public void setIndustry(String industry) {
        this.industry = industry;
    }

    public String getCurrencyCd() {
        return currencyCd;
    }

    public void setCurrencyCd(String currencyCd) {
        this.currencyCd = currencyCd;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public String getLocationType() {
        return locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType;
    }

    public String getParentCustomerCode() {
        return parentCustomerCode;
    }

    public void setParentCustomerCode(String parentCustomerCode) {
        this.parentCustomerCode = parentCustomerCode;
    }

    public String getCreditAnalystName() {
        return creditAnalystName;
    }

    public void setCreditAnalystName(String creditAnalystName) {
        this.creditAnalystName = creditAnalystName;
    }

    public String getCollector() {
        return collector;
    }

    public void setCollector(String collector) {
        this.collector = collector;
    }

    public boolean isCreditApplication() {
        return creditApplication;
    }

    public void setCreditApplication(boolean creditApplication) {
        this.creditApplication = creditApplication;
    }

    public boolean isCreditHold() {
        return creditHold;
    }

    public void setCreditHold(boolean creditHold) {
        this.creditHold = creditHold;
    }

    public double getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(double creditLimit) {
        this.creditLimit = creditLimit;
    }

    public boolean isInterUnit() {
        return interUnit;
    }

    public void setInterUnit(boolean interUnit) {
        this.interUnit = interUnit;
    }

    public String getInterUnitBusinessUnitName() {
        return interUnitBusinessUnitName;
    }

    public void setInterUnitBusinessUnitName(String interUnitBusinessUnitName) {
        this.interUnitBusinessUnitName = interUnitBusinessUnitName;
    }

    public String getInterUnitBusinessUnit() {
        return interUnitBusinessUnit;
    }

    public void setInterUnitBusinessUnit(String interUnitBusinessUnit) {
        this.interUnitBusinessUnit = interUnitBusinessUnit;
    }

    public boolean isInterUnitPending() {
        return interUnitPending;
    }

    public void setInterUnitPending(boolean interUnitPending) {
        this.interUnitPending = interUnitPending;
    }

    public String getAccountOwner() {
        return accountOwner;
    }

    public void setAccountOwner(String accountOwner) {
        this.accountOwner = accountOwner;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getPaymentTerms() {
        return paymentTerms;
    }

    public void setPaymentTerms(String paymentTerms) {
        this.paymentTerms = paymentTerms;
    }

    public String getPrimaryIndustry() {
        return primaryIndustry;
    }

    public void setPrimaryIndustry(String primaryIndustry) {
        this.primaryIndustry = primaryIndustry;
    }

    public String getVip() {
        return vip;
    }

    public void setVip(String vip) {
        this.vip = vip;
    }

    public String getCustomerStatusComment() {
        return customerStatusComment;
    }

    public void setCustomerStatusComment(String customerStatusComment) {
        this.customerStatusComment = customerStatusComment;
    }

    public String getDunsNumber() {
        return dunsNumber;
    }

    public void setDunsNumber(String dunsNumber) {
        this.dunsNumber = dunsNumber;
    }

    public String getInternalOriginDivision() {
        return internalOriginDivision;
    }

    public void setInternalOriginDivision(String internalOriginDivision) {
        this.internalOriginDivision = internalOriginDivision;
    }

    public String getInternalOriginBusStrm() {
        return internalOriginBusStrm;
    }

    public void setInternalOriginBusStrm(String internalOriginBusStrm) {
        this.internalOriginBusStrm = internalOriginBusStrm;
    }

    public String getInternalOrigin() {
        return internalOrigin;
    }

    public void setInternalOrigin(String internalOrigin) {
        this.internalOrigin = internalOrigin;
    }

    public String getInvoiceType() {
        return invoiceType;
    }

    public void setInvoiceType(String invoiceType) {
        this.invoiceType = invoiceType;
    }

    public Date getLastServiceDate() {
        return lastServiceDate;
    }

    public void setLastServiceDate(Date lastServiceDate) {
        this.lastServiceDate = lastServiceDate;
    }

    public String getLastServiceLocation() {
        return lastServiceLocation;
    }

    public void setLastServiceLocation(String lastServiceLocation) {
        this.lastServiceLocation = lastServiceLocation;
    }

    public boolean isLegalEntitySubsidiary() {
        return legalEntitySubsidiary;
    }

    public void setLegalEntitySubsidiary(boolean legalEntitySubsidiary) {
        this.legalEntitySubsidiary = legalEntitySubsidiary;
    }

    public boolean isReferral() {
        return referral;
    }

    public void setReferral(boolean referral) {
        this.referral = referral;
    }

    public String getReferralParentCust() {
        return referralParentCust;
    }

    public void setReferralParentCust(String referralParentCust) {
        this.referralParentCust = referralParentCust;
    }

    public String getReferralChildCust() {
        return referralChildCust;
    }

    public void setReferralChildCust(String referralChildCust) {
        this.referralChildCust = referralChildCust;
    }

    public String getReferralNotes() {
        return referralNotes;
    }

    public void setReferralNotes(String referralNotes) {
        this.referralNotes = referralNotes;
    }

    public String getSecondaryIndustry() {
        return secondaryIndustry;
    }

    public void setSecondaryIndustry(String secondaryIndustry) {
        this.secondaryIndustry = secondaryIndustry;
    }

    public String getTaxpayerIdNumber() {
        return taxpayerIdNumber;
    }

    public void setTaxpayerIdNumber(String taxpayerIdNumber) {
        this.taxpayerIdNumber = taxpayerIdNumber;
    }

    public String getCustType() {
        return custType;
    }

    public void setCustType(String custType) {
        this.custType = custType;
    }

    public List<CustomerAddressSequenceX> getCustomerAddressSequence() {
        return customerAddressSequence;
    }

    public void setCustomerAddressSequence(List<CustomerAddressSequenceX> customerAddressSequence) {
        this.customerAddressSequence = customerAddressSequence;
    }

}
