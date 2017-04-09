/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.pricing.model;

import java.text.MessageFormat;
import java.util.Date;
import java.util.List;

import com.intertek.entity.CfgContract;
import com.intertek.entity.ContractExpression;
import com.intertek.entity.Expression;
import com.intertek.entity.LocationDiscount;
import com.intertek.entity.ServiceRate;
import com.intertek.phoenix.entity.JobContractService;
import com.intertek.phoenix.entity.JobContractServiceControl;
import com.intertek.phoenix.entity.JobContractServiceExpression;
import com.intertek.phoenix.job.ContractJobOrder;
import com.intertek.phoenix.job.JobSrvcException;
import com.intertek.phoenix.job.ServiceLevel;
import com.intertek.phoenix.job.ServiceLevel.ServiceLevelType;
import com.intertek.phoenix.pricing.InputInfo;
import com.intertek.phoenix.pricing.Pricer;
import com.intertek.phoenix.pricing.PricingParameter;
import com.intertek.phoenix.util.CommonUtil;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;

/**
 * The pricer implementation for the Standard pricing model. 
 * <p>
 * This is refactored from the old phoenix pricing engine, which has been
 * overly complicated, highly repetitive and very difficult to understand.
 * <p>
 * StandardPricer can be extended to implement other pricing models.
 * 
 * @author richard.qin
 */
public class StandardPricer implements Pricer { 

    /**
     * Note, the only place that percentage is set is in SamplingIncludesPricer.
     * This implementation is coped from the old phoenix code, with some minor
     * changes and a new method signature.
     * @see com.intertek.phoenix.pricing.Pricer#calculatePrice(com.intertek.entity.ServiceRate, double, double)
     */
    @Override
    public double calculatePrice(ServiceRate serviceRate, InputInfo inputInfo) {
        double result = 0;
        
        double basePrice = CommonUtil.doubleValue(serviceRate.getBasePrice());
        double unitPrice = CommonUtil.doubleValue(serviceRate.getUnitPrice());
        double minPrice = CommonUtil.doubleValue(serviceRate.getMinimumPrice());
        double maxPrice = CommonUtil.doubleValue(serviceRate.getMaximumPrice());
        double unitsIncluded = CommonUtil.doubleValue(serviceRate.getUnitsIncluded());
        
        double quantity = inputInfo.getDoubleQuantity();
        double percentage = inputInfo.getPercentage();

        double unadjustedPrice = basePrice + unitPrice * Math.max(0, quantity - unitsIncluded);
        double minAdjustedPrice = minPrice > 0 ? Math.max(minPrice, unadjustedPrice) 
                                                    : unadjustedPrice;

        if(maxPrice > 0) {
            result = Math.min(maxPrice, minAdjustedPrice);
        }
        else {
            result = minAdjustedPrice;
        }

        if(percentage > 1) {
          result *= percentage/100;
        }

        return result;
    }

    /**
     * This implementation is coped from the old phoenix code, with some minor
     * changes and a new method signature.
     * @see com.intertek.phoenix.pricing.Pricer#calculateDiscount(com.intertek.entity.CfgContract, com.intertek.entity.ContractExpression, com.intertek.entity.Expression, com.intertek.entity.ServiceRate, com.intertek.entity.LocationDiscount)
     */
    @Override
    public double calculateDiscount(CfgContract contract, ContractExpression contractExpression, 
                                    Expression expression, ServiceRate serviceRate, LocationDiscount ld) {
        double totalDiscount = 0;

        String branchType = "";
        if (expression != null) {
            branchType = expression.getExpressionId().getBranchType();
        }

        if (Constants.ADM.equals(branchType)) {
            // expenses, apply no discount
            return totalDiscount;
        }

        if (ld != null) {
            // get additional discount first
            totalDiscount = ld.getCfgDiscountPercent();
        }
        
        boolean isContractPrice = false;
        if(serviceRate.getServiceRateId().getContractId().equals(contract.getCfgContractId().getContractId())){
            isContractPrice = true;
        }

        // Apply only additional discount for contract-specific prices
        if (isContractPrice) {
            return totalDiscount;
        }

        // if any LI discount found, sum up the LI discount and overall discount
        if (contract.getCfgContractId().getContractId().equals(contractExpression.getContractExpressionId().getContractId())) {
            Double zoneSpecificDiscount = contractExpression.getCfgDiscountPercent();
            // LI overall discount >= 0
            if ((zoneSpecificDiscount != null) && (zoneSpecificDiscount >= 0)) {
                // apply li overall discount
                totalDiscount += zoneSpecificDiscount;

                return totalDiscount;
            }
        }

        if (Constants.OPS.equals(branchType) && ld != null) {
            Double opsZoneDiscount = ld.getOpsDiscountPercent();
            if ((opsZoneDiscount != null) && (opsZoneDiscount >= 0)) {
                // apply li overall discount
                totalDiscount += opsZoneDiscount;

                return totalDiscount;
            }
        }
        else if (Constants.LAB.equals(branchType) && ld != null) {
            Double labZoneDiscount = ld.getLabDiscountPercent();
            if ((labZoneDiscount != null) && (labZoneDiscount >= 0)) {
                // apply li overall discount
                totalDiscount += labZoneDiscount;

                return totalDiscount;
            }
        }

        // overall zone discount
        Double overallZoneDiscount = null;
        if (ld != null){
            overallZoneDiscount = ld.getZoneDiscountPercent();
        }
        if ((overallZoneDiscount != null) && (overallZoneDiscount >= 0)) {
            // apply li overall discount
            totalDiscount += overallZoneDiscount;

            return totalDiscount;
        }

        if (Constants.OPS.equals(branchType)) {
            Double opsOverallDiscount = contract.getOpsDiscountPercent();
            if ((opsOverallDiscount != null) && (opsOverallDiscount >= 0)) {
                // apply li overall discount
                totalDiscount += opsOverallDiscount;

                return totalDiscount;
            }
        }
        else if (Constants.LAB.equals(branchType)) {
            Double labOverallDiscount = contract.getLabDiscountPercent();
            if ((labOverallDiscount != null) && (labOverallDiscount >= 0)) {
                // apply li overall discount
                totalDiscount += labOverallDiscount;

                return totalDiscount;
            }
        }

        if ((contract.getCfgDiscountPercent() != null) && (contract.getCfgDiscountPercent() >= 0)) {
            // apply li overall discount
            totalDiscount += contract.getCfgDiscountPercent();

            return totalDiscount;
        }

        if ((ld != null) && (ld.getCfgDiscountPercent() != null) && (ld.getCfgDiscountPercent() >= 0)) {
            return totalDiscount;
        }

        return 0;
    }

    /**
     * This implementation is coped from the old phoenix code, with some minor
     * changes and a new method signature.
     * @see com.intertek.phoenix.pricing.Pricer#formatLineDescription(com.intertek.entity.ContractExpression, com.intertek.entity.ServiceRate, double, double)
     */
    @Override
    // TODO make sure that the ServiceRate is properly adjusted
    public String formatLineDescription(ContractExpression contractExpression, ServiceRate serviceRate, 
                                        InputInfo inputInfo) throws JobSrvcException {
        Object[] objs = new Object[] {
            serviceRate.getBasePrice(),
            inputInfo.getDoubleQuantity(),
            serviceRate.getUnitPrice(),
            serviceRate.getUnitsIncluded(),
            serviceRate.getMinimumPrice(),
            serviceRate.getMaximumPrice(),
            serviceRate.getFloatData0(),
            serviceRate.getServiceRateId().getIntData2(),
            serviceRate.getIntData3(),
            inputInfo.getStrVal1(),
            inputInfo.getStrVal2(),
            inputInfo.getStrVal3(),
            inputInfo.getPercentage()
        };

        String rbValue = contractExpression.getVisibility();

        return formatDescription(rbValue, objs, serviceRate.getContractRef());
    }

    /**
     * @param rbValue
     * @param objs
     * @param contractRef
     * @return
     */
    protected String formatDescription(String rbValue, Object[] objs, String contractRefNo) {
        MessageFormat format = new MessageFormat(rbValue);
        String result = format.format(objs);

        if ((contractRefNo != null) && !"0".equals(contractRefNo)) {
            return contractRefNo + " - " + result;
        }

        return result;
    }

    // Note, this method uses the old getPricingParameters() implementation,
    // it should be replaced by an implementation asap. 
    // getPricingParameters should be retired. TODO
    @Override
    public PricingParameter getPricingParameter(CfgContract contract, ContractJobOrder cjo, ServiceLevel sl, 
                                                ContractExpression ce, InputInfo qinfo) throws JobSrvcException{
        Object[] parameters = getPricingParameters(contract, cjo, sl, ce, qinfo);
        return new PricingParameter(parameters);
    }
    
    @Override
    public Object[] getPricingParameters(CfgContract contract, ContractJobOrder contractJobOrder, 
                                         ServiceLevel serviceLevel, ContractExpression contractExpression, 
                                         InputInfo inputInfo) throws JobSrvcException {
        Object[] parameters = new Object[9];
        /*
         * 1. contrExpnContrId – this is either contract id or pricebook id, 
         * depending on the following:
         * contrExpnContrId = PS_ITSC_CONTR_EXPN:CFG_CONTRACT_ID);
         * if(PS_ITSC_CONTR_EXPN:ITS_CNTR_COMPONENT.equals("PB"))
         *     contractId = pricebookId;
         * else if(PS_ITSC_CONTR_EXPN:CFG_DISCOUNT_PCT >= 0)
         *     contractId = pricebookId;
         */
        String contractId = contract.getCfgContractId().getContractId();
        if(contractExpression.getContractComponent().equals("PB")){
            contractId = contract.getPriceBookId();
        }
        else if(contract.getCfgDiscountPercent() >= 0){ // should this be > or >=
            contractId = contract.getPriceBookId();
        }
        parameters[CONTRACT_ID] = contractId;
        /*
         * 2. expressionId = PS_ITSC_CONTR_EXPN:ITS_SRVC_RT_EXP_ID  
         * Please note that this ID will be present for all the components that are 
         * using ‘Standard Formula’ model         
         */
        parameters[EXPRESSION_ID] = contractExpression.getServiceRateExpressionId();
        /*
         * 3. selectedLocationInfo = This is the zone information.  
         * The zone information is auto-selected upfront based on port (PS_ITSC_PORT_LOC)
         * or branch (PS_ITSC_BRNCH_LOC) for a given contract or price book
         */
        parameters[LOCATION] = contractJobOrder.getZone();
        /*
         * 4. useProductGroup = PS_ITSC_CONTR_EXPN:ITS_USE_GROUP_ID.  
         * This value could be greater than 0 only for lot level items.  
         * Because only then we will have a product id to use 
         * selectedProduct = this is the product id (PS_ITSC_PRDUCT_GRP) 
         * selected at the lot level.
         */ 
        String selectedProductGroup = "*";
        if(contractExpression.getUseGroupId() > 0){
            // TODO verify this
            if(serviceLevel.getServiceLevelType() == ServiceLevelType.LOT){
                selectedProductGroup = serviceLevel.getServiceLevelName();
            }
        }
        parameters[PRODUCT_GROUP] = selectedProductGroup;
        /*
         * 5. useVesselType = PS_ITSC_CONTR_EXPN:ITS_USE_VESSEL_ID.  
         * This value could be greater than 0 only for vessel and lot level items.  
         * Because only then, we will have a vessel id to use.
         */
        String selectedVesselType = "*";
        if(contractExpression.getUseVesselId() > 0){
            // TODO verify this
            if(serviceLevel.getServiceLevelType() == ServiceLevelType.LOT){
                // this logic is too restrictive, and may even be hacky.
                ServiceLevel vesselServiceLevel = serviceLevel.getParentServiceLevel();
                selectedVesselType = vesselServiceLevel.getServiceLevelName();
            }
        }
        parameters[VESSEL_TYPE] = selectedVesselType;
        /* 
         * 6. selectedServiceLevel = PS_ITSC_CONTR_EXPN:ITS_SERVICE_LEVEL
         */
        parameters[SERVICE_LEVEL] = contractExpression.getServiceLevel();
        /*
         * 7. Quantity = entered by the user
         * RQ: Quantity is entered into the Controls related to the expression.
         */
        parameters[FLOAT_QUANTITY] = inputInfo.getDoubleQuantity();
        //TODO: hardcoded to make it work. Replace
        parameters[FLOAT_QUANTITY] = 1.0;
        
        
        /*
         * Hardcoded value
         */
        parameters[INT_QUANTITY] = 1;
        /*
         * 9. nominationDate = job finish date from the entry page.
         */
        Date date = contractJobOrder.getJobOrder().getNominationDate();
        parameters[NOMINATION_DATE_STR] = DateUtil.formatDate(date, "yyyyMMdd"); 
        // or should it be jobFinishDate?
        
        return parameters;
    }
    
    /**
     * @see com.intertek.phoenix.pricing.Pricer#getInputInfo(com.intertek.phoenix.entity.JobContractService, com.intertek.phoenix.entity.JobContractServiceExpression, java.util.List)
     */
    @Override
    public InputInfo getInputInfo(JobContractService service, JobContractServiceExpression expression, 
                                        List<JobContractServiceControl> relatedControls) {
        InputInfo inputInfo = new InputInfo();
        for (JobContractServiceControl control : relatedControls) {
            String name = control.getObjectName();
            String value = control.getInputValue0(); // inputValue1 - 4 are never used
            if (Constants.STRING_VAL1.equals(name)) {
                inputInfo.setStrVal1(value);
            }
            else if (Constants.STRING_VAL2.equals(name)) {
                inputInfo.setStrVal1(value);
            }
            else if (Constants.STRING_VAL3.equals(name)) {
                inputInfo.setStrVal1(value);
            }
            else if ("inputVal1".equals(name) || ("inputVal2".equals(name))){
                inputInfo.setPercentage(Double.parseDouble(value));
            }
            else{
                inputInfo.setDoubleQuantity(Double.parseDouble(value));
            }
        }
        return inputInfo;
    }

}
