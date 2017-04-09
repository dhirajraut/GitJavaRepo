/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.pricing;


/**
 * A simple class encapsulates pricing parameters for Services.
 * <p> This class is designed to replace Object[] returned by
 * Pricer.getPricingParameters().
 * 
 * @author richard.qin
 */
public class PricingParameter {
    private String contractId;
    private String expressionId;
    private String location;
    private String productGroup;
    private String vesselType;
    private String serviceLevel;
    private int intQuantity;
    private double floatQuantity;
    private String nominationDateStr;
    
    public PricingParameter(){
        
    }
    
    public PricingParameter(Object[] parameters){
        this.contractId = (String)parameters[Pricer.CONTRACT_ID];
        this.expressionId = (String)parameters[Pricer.EXPRESSION_ID];
        this.vesselType = (String)parameters[Pricer.VESSEL_TYPE];
        this.productGroup = (String)parameters[Pricer.PRODUCT_GROUP];
        this.serviceLevel = (String)parameters[Pricer.SERVICE_LEVEL];
        this.location = (String)parameters[Pricer.LOCATION];
        this.floatQuantity = (Double)parameters[Pricer.FLOAT_QUANTITY];
        this.intQuantity = (Integer)parameters[Pricer.INT_QUANTITY];
        this.nominationDateStr = (String)parameters[Pricer.NOMINATION_DATE_STR];
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public String getExpressionId() {
        return expressionId;
    }

    public void setExpressionId(String expressionId) {
        this.expressionId = expressionId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getProductGroup() {
        return productGroup;
    }

    public void setProductGroup(String productGroup) {
        this.productGroup = productGroup;
    }

    public String getVesselType() {
        return vesselType;
    }

    public void setVesselType(String vesselType) {
        this.vesselType = vesselType;
    }

    public String getServiceLevel() {
        return serviceLevel;
    }

    public void setServiceLevel(String serviceLevel) {
        this.serviceLevel = serviceLevel;
    }

    public int getIntQuantity() {
        return intQuantity;
    }

    public void setIntQuantity(int intQuantity) {
        this.intQuantity = intQuantity;
    }

    public double getFloatQuantity() {
        return floatQuantity;
    }

    public void setFloatQuantity(double floatQuantity) {
        this.floatQuantity = floatQuantity;
    }

    public String getNominationDateStr() {
        return nominationDateStr;
    }

    public void setNominationDateStr(String nominationDateStr) {
        this.nominationDateStr = nominationDateStr;
    }

    /**
     * Convert PricingParameter into an object list to be used by pricing
     * logic
     * 
     * @return
     */
    public Object[] getParameters(){
        Object[] parameters = new Object[9];
        
        parameters[Pricer.CONTRACT_ID] =this.contractId;
        parameters[Pricer.EXPRESSION_ID] = this.expressionId;
        parameters[Pricer.VESSEL_TYPE] = this.vesselType;
        parameters[Pricer.PRODUCT_GROUP] = this.productGroup;
        parameters[Pricer.SERVICE_LEVEL] = this.serviceLevel;
        parameters[Pricer.LOCATION] = this.location;
        parameters[Pricer.FLOAT_QUANTITY] = this.floatQuantity;
        parameters[Pricer.INT_QUANTITY] = this.intQuantity;
        parameters[Pricer.NOMINATION_DATE_STR] = this.nominationDateStr;
        
        return parameters;
    }
}
