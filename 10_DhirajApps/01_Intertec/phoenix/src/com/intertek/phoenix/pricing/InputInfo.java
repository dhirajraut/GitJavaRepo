/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.pricing;


/**
 * 
 * @author richard.qin
 */
public class InputInfo {
    private double doubleValue;
    private int intValue;
    private boolean booleanValue;
    private double percentage;

    private double noOfContainers;
    private double noOfQuarts;
    private double includedSamples;
    
    private double numPressCylinders;
    private double pressCylinderDays;
    
    private String lighterageType;
    
    private String productType;
    private String productType1;
    
    private String strVal1;
    private String strVal2;
    private String strVal3;
    
    private String testOrSlateId;
    private String description;
    private String rbValue;

    public InputInfo() {
    }
    
    public InputInfo(double value) {
        doubleValue = value;
    }
    
    public double getDoubleQuantity() {
        return doubleValue;
    }

    public void setDoubleQuantity(double doubleValue) {
        this.doubleValue = doubleValue;
    }

    public int getIntQuantity() {
        return intValue;
    }

    public void setIntQuantity(int intValue) {
        this.intValue = intValue;
    }

    public boolean getBooleanValue() {
        return booleanValue;
    }

    public void setBooleanValue(boolean booleanValue) {
        this.booleanValue = booleanValue;
    }

    public double getPercentage() {
        return percentage;
    }

    public void setPercentage(double percentage) {
        this.percentage = percentage;
    }

    public double getNoOfContainers() {
        return noOfContainers;
    }

    public void setNoOfContainers(double noOfContainers) {
        this.noOfContainers = noOfContainers;
    }

    public double getNoOfQuarts() {
        return noOfQuarts;
    }

    public void setNoOfQuarts(double noOfQuarts) {
        this.noOfQuarts = noOfQuarts;
    }

    public double getIncludedSamples() {
        return includedSamples;
    }

    public void setIncludedSamples(double includedSamples) {
        this.includedSamples = includedSamples;
    }

    public String getStrVal1() {
        return strVal1;
    }

    public void setStrVal1(String strVal1) {
        this.strVal1 = strVal1;
    }

    public String getStrVal2() {
        return strVal2;
    }

    public void setStrVal2(String strVal2) {
        this.strVal2 = strVal2;
    }

    public String getStrVal3() {
        return strVal3;
    }

    public void setStrVal3(String strVal3) {
        this.strVal3 = strVal3;
    }

    public String getTestOrSlateId() {
        return testOrSlateId;
    }

    public void setTestOrSlateId(String testOrSlateId) {
        this.testOrSlateId = testOrSlateId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRbValue() {
        return rbValue;
    }

    public void setRbValue(String rbValue) {
        this.rbValue = rbValue;
    }

    public double getNumPressCylinders() {
        return numPressCylinders;
    }

    public void setNumPressCylinders(double numPressCylinders) {
        this.numPressCylinders = numPressCylinders;
    }

    public double getPressCylinderDays() {
        return pressCylinderDays;
    }

    public void setPressCylinderDays(double pressCylinderDays) {
        this.pressCylinderDays = pressCylinderDays;
    }

    public String getLighterageType() {
        return lighterageType;
    }

    public void setLighterageType(String lighterageType) {
        this.lighterageType = lighterageType;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductType1() {
        return productType1;
    }

    public void setProductType1(String productType1) {
        this.productType1 = productType1;
    }

}
