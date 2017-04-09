/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.pricing.model;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.intertek.phoenix.pricing.InputInfo;
import com.intertek.phoenix.util.CommonUtil;
import com.intertek.util.Constants;

/**
 * 
 * @author richard.qin
 */
public class SamplingIncludesPricer extends StandardPricer {
    private static Map<String, String> objectNameMap = new HashMap<String, String>();

    static {
      objectNameMap.put("sampTotNoOfBalTanks", Constants.noOfContainers);
      objectNameMap.put("pulledBallSamples", Constants.noOfQuarts);
      objectNameMap.put("noOfShipTankers", Constants.noOfContainers);
      objectNameMap.put("pulledTankerSamples", Constants.noOfQuarts);
      objectNameMap.put("sampTotNoOfSLPTanks", Constants.noOfContainers);
      objectNameMap.put("pulledSLPSamples", Constants.noOfQuarts);
      objectNameMap.put("sampTotNoShipTanks", Constants.noOfContainers);
      objectNameMap.put("pulledTankerSamples", Constants.noOfQuarts);
      objectNameMap.put("sampTotNoShipTanks", Constants.noOfContainers);
      objectNameMap.put("pulledTankerSamples", Constants.noOfQuarts);
      objectNameMap.put("samplTotNoOfTankcarTruck", Constants.noOfContainers);
      objectNameMap.put("pulledTankCarTruckSamples", Constants.noOfQuarts);
      objectNameMap.put("noOfShipTankers", Constants.noOfContainers);
      objectNameMap.put("pulledTankerSamples", Constants.noOfQuarts);
      objectNameMap.put("sampTotNoOfBargeTanks", Constants.noOfContainers);
      objectNameMap.put("pulledBrgeSamples", Constants.noOfQuarts);
      objectNameMap.put("samplTotNoOfTankcarTruck", Constants.noOfContainers);
      objectNameMap.put("pulledTankCarTruckSamples", Constants.noOfQuarts);
      objectNameMap.put("sampTotNoShipTanks", Constants.noOfContainers);
      objectNameMap.put("pulledTankerSamples", Constants.noOfQuarts);
      objectNameMap.put("sampTotNoOfBargeTanks", Constants.noOfContainers);
      objectNameMap.put("pulledBrgeSamples", Constants.noOfQuarts);
      objectNameMap.put("sampTotNoOfBargeTanks", Constants.noOfContainers);
      objectNameMap.put("pulledBrgeSamples", Constants.noOfQuarts);
      objectNameMap.put("sampNoBargeTanks", Constants.noOfContainers);
      objectNameMap.put("pulledBrgeSamples", Constants.noOfQuarts);
      objectNameMap.put("samplTotNoOfShoreTanks", Constants.noOfContainers);
      objectNameMap.put("samplTotNoOfSamples", Constants.noOfQuarts);
      objectNameMap.put("sampNoBargeTanks", Constants.noOfContainers);
      objectNameMap.put("pulledBrgeSamples", Constants.noOfQuarts);
      objectNameMap.put("inputVal1", Constants.noOfContainers);
      objectNameMap.put("inputVal2", Constants.noOfQuarts);
      objectNameMap.put("noOfShipTankers", Constants.noOfContainers);
      objectNameMap.put("pulledTankerSamples", Constants.noOfQuarts);
      objectNameMap.put("noOfShipTankers", Constants.noOfContainers);
      objectNameMap.put("pulledTankerSamples", Constants.noOfQuarts);
      objectNameMap.put("sampNoShoreTanks", Constants.noOfContainers);
      objectNameMap.put("inputVal2", Constants.noOfQuarts);
      objectNameMap.put("samplTotNoOfShoreTanks", Constants.noOfContainers);
      objectNameMap.put("samplTotNoOfSamples", Constants.noOfQuarts);
    }

    @Override
    public double calculateDiscount(CfgContract contract, ContractExpression contractExpression, 
                                    Expression expression, ServiceRate serviceRate, LocationDiscount ld) {
        // use the standard discounter
        return super.calculateDiscount(contract, contractExpression, expression, serviceRate, ld);
    }

    @Override
    public double calculatePrice(ServiceRate serviceRate, InputInfo inputInfo) {
        double result = 0;
        
        double basePrice = CommonUtil.doubleValue(serviceRate.getBasePrice());
        double unitPrice = CommonUtil.doubleValue(serviceRate.getUnitPrice());
        double minPrice = CommonUtil.doubleValue(serviceRate.getMinimumPrice());
        double maxPrice = CommonUtil.doubleValue(serviceRate.getMaximumPrice());
        double unitsIncluded = CommonUtil.doubleValue(serviceRate.getUnitsIncluded());
        double noOfContainers = inputInfo.getNoOfContainers();
        double noOfQuarts = inputInfo.getNoOfQuarts();
        double includedSamples = CommonUtil.intValue(serviceRate.getIntData0());
        if(includedSamples == 0){
            includedSamples = unitsIncluded * noOfContainers;
        }
        int intData1 = CommonUtil.intValue(serviceRate.getIntData1());
        
        double unadjustedPrice = 0;
        if(intData1 == 1){
            unadjustedPrice = basePrice + unitPrice * ( noOfContainers - Math.max(0, includedSamples));
        }
        else{
            unadjustedPrice = basePrice + unitPrice * ( noOfQuarts - Math.max(0, includedSamples));
        }
        double minAdjustedPrice = minPrice > 0 ? Math.max(minPrice, unadjustedPrice) 
                                               : unadjustedPrice;

        if(maxPrice > 0) {
            result = Math.min(maxPrice, minAdjustedPrice);
        }
        else {
            result = minAdjustedPrice;
        }
        result = Math.max(result, 0);
        
        return result;
    }

    @Override
    public String formatLineDescription(ContractExpression contractExpression, ServiceRate serviceRate, 
                                        InputInfo inputInfo) throws JobSrvcException {
        Object[] objs = new Object[] {
              serviceRate.getBasePrice(),
              inputInfo.getNoOfQuarts(),
              serviceRate.getUnitPrice(),
              serviceRate.getUnitsIncluded(),
              serviceRate.getMinimumPrice(),
              serviceRate.getMaximumPrice(),
              serviceRate.getServiceRateId().getIntData2(),
              serviceRate.getIntData3(),
              inputInfo.getNoOfContainers(),
              inputInfo.getIncludedSamples()
        };
        String rbValue = contractExpression.getVisibility();
        return super.formatDescription(rbValue, objs, serviceRate.getContractRef());
    }

    @Override
    public Object[] getPricingParameters(CfgContract contract, ContractJobOrder contractJobOrder, 
                                         ServiceLevel serviceLevel, ContractExpression contractExpression, 
                                         InputInfo inputInfo) throws JobSrvcException {
        Object[] params = super.getPricingParameters(contract, contractJobOrder, serviceLevel, 
                                                     contractExpression, inputInfo);
        // overwrite some values
        params[FLOAT_QUANTITY] = inputInfo.getNoOfQuarts();
        params[INT_QUANTITY] = inputInfo.getNoOfQuarts();
        return params;
    }

    @Override
    public InputInfo getInputInfo(JobContractService service, JobContractServiceExpression expression, List<JobContractServiceControl> relatedControls) {
        InputInfo inputInfo = new InputInfo();
        for (JobContractServiceControl control : relatedControls) {
            String name = control.getObjectName();
            String value = control.getInputValue0(); // inputValue1 - 4 are never used
            double val = Double.parseDouble(value);
            String mappedName = objectNameMap.get(name);
            if (Constants.noOfContainers.equals(mappedName)) {
                inputInfo.setNoOfContainers(val);
            }
            else if (Constants.noOfQuarts.equals(mappedName)) {
                inputInfo.setNoOfQuarts(val);
            }
            else{
                inputInfo.setDoubleQuantity(val);
            }
        }
        return inputInfo;
    }

}
