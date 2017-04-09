/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.pricing;

import com.intertek.entity.ServiceRate;

/**
 * 
 * @author richard.qin
 */
public class PricingInfo {
    private InputInfo inputInfo;
    private ServiceRate serviceRate;
    private AccountInfo accountInfo;
    private double priceBeforeDiscount;
    private double totalPrice;
    private double discountPct;
    private double minPrice;
    private double maxprice;
    private boolean isContractPrice;
    private double rateMult;
    private double rateDiv;
    private boolean isEditable;
    
    public PricingInfo(){
    }

    public InputInfo getInputInfo() {
        return inputInfo;
    }

    public void setInputInfo(InputInfo inputInfo) {
        this.inputInfo = inputInfo;
    }

    // ServiceRate is write only, its memebers can be referenced
    // individual getters.
    public void setServiceRate(ServiceRate serviceRate) {
        this.serviceRate = serviceRate;
    }

    public AccountInfo getAccountInfo() {
        return accountInfo;
    }

    public void setAccountInfo(AccountInfo accountInfo) {
        this.accountInfo = accountInfo;
    }

    public double getPriceBeforeDiscount() {
        return priceBeforeDiscount;
    }

    public void setPriceBeforeDiscount(double priceBeforeDiscount) {
        this.priceBeforeDiscount = priceBeforeDiscount;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getDiscountPct() {
        return discountPct;
    }

    public void setDiscountPct(double discountPct) {
        this.discountPct = discountPct;
    }

    public double getMinPrice() {
        return minPrice;
    }

    public void setMinPrice(double minPrice) {
        this.minPrice = minPrice;
    }

    public double getMaxprice() {
        return maxprice;
    }

    public void setMaxprice(double maxprice) {
        this.maxprice = maxprice;
    }

    public double getBasePrice(){
        return this.serviceRate.getBasePrice();
    }
    
    public double getUnitPrice(){
        return this.serviceRate.getUnitPrice();
    }
    
    public double getUnitsIncluded(){
        return this.serviceRate.getUnitsIncluded();
    }
    
    public String getContractRef(){
        return this.serviceRate.getContractRef();
    }
    
    public double getFloatData0(){
        return this.serviceRate.getFloatData0();
    }
    
    public int getIntData2(){
        return this.serviceRate.getServiceRateId().getIntData2();
    }
    
    public long getIntData3(){
        return this.serviceRate.getIntData3();
    }
    
    public boolean isContractPrice(){
        return this.isContractPrice;
    }

    public void setIsContractPrice(boolean isContractPrice) {
        this.isContractPrice = isContractPrice;
        
    }
    
    public double getRateMult() {
        return rateMult;
    }

    public void setRateMult(double rateMult) {
        this.rateMult = rateMult;
    }

    public double getRateDiv() {
        return rateDiv;
    }

    public void setRateDiv(double rateDiv) {
        this.rateDiv = rateDiv;
    }

    public boolean isEditable() {
        return isEditable;
    }

    public void setEditable(boolean isEditable) {
        this.isEditable = isEditable;
    }

    /**
     * @return
     */
    public double getBaseUnitPrice() {
        return this.getUnitPrice() * this.getRateMult() / this.getRateDiv();
    }
    
    public double getBaseNetPrice() {
        return this.getTotalPrice() * this.getRateMult() / this.getRateDiv();
    }

}
