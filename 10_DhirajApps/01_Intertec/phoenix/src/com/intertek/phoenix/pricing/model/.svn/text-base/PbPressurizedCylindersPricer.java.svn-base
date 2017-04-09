/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.pricing.model;

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
import com.intertek.phoenix.pricing.InputInfo;
import com.intertek.phoenix.util.CommonUtil;
import com.intertek.util.Constants;

/**
 * 
 * @author richard.qin
 */
public class PbPressurizedCylindersPricer extends StandardPricer {

    @Override
    public double calculateDiscount(CfgContract contract, ContractExpression contractExpression, Expression expression, ServiceRate serviceRate,
                                    LocationDiscount ld) {
        // it uses the standard discounter
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
        double numPressCylinders = CommonUtil.doubleValue(inputInfo.getNumPressCylinders());
        double pressCylinderDays = CommonUtil.doubleValue(inputInfo.getPressCylinderDays());
        
        double percentage = inputInfo.getPercentage();

        double unadjustedPrice = basePrice + unitPrice * Math.max(0, pressCylinderDays - unitsIncluded) * numPressCylinders;
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

    @Override
    public String formatLineDescription(ContractExpression contractExpression, ServiceRate serviceRate, 
                                        InputInfo inputInfo) throws JobSrvcException {
        Object[] objs = new Object[] {
            serviceRate.getBasePrice(),
            inputInfo.getPressCylinderDays(),
            serviceRate.getUnitPrice(),
            serviceRate.getUnitsIncluded(),
            serviceRate.getMinimumPrice(),
            serviceRate.getMaximumPrice(),
            serviceRate.getFloatData0(),
            serviceRate.getServiceRateId().getFloatData2(),
            serviceRate.getFloatData3(),
            inputInfo.getNumPressCylinders()
        };

        String rbValue = contractExpression.getVisibility();

        return formatDescription(rbValue, objs, serviceRate.getContractRef());
    }

    @Override
    public Object[] getPricingParameters(CfgContract contract, ContractJobOrder contractJobOrder, 
                                         ServiceLevel serviceLevel, ContractExpression contractExpression, 
                                         InputInfo inputInfo) throws JobSrvcException {
        Object[] params = super.getPricingParameters(contract, contractJobOrder, serviceLevel, 
                                                     contractExpression, inputInfo);
        // overwrite some values
        // TODO: verify
        params[EXPRESSION_ID] = Constants.PressCylinders;
        params[VESSEL_TYPE] = "*";
        params[FLOAT_QUANTITY] = inputInfo.getPressCylinderDays();
        params[INT_QUANTITY] = inputInfo.getPressCylinderDays();
        return params;
    }

    @Override
    public InputInfo getInputInfo(JobContractService service, JobContractServiceExpression expression, 
                                        List<JobContractServiceControl> relatedControls) {
        InputInfo inputInfo = new InputInfo();
        for (JobContractServiceControl control : relatedControls) {
            String name = control.getObjectName();
            String value = control.getInputValue0(); // inputValue1 - 4 are never used
            double val = 0;
            if(value != null){
                val = Double.parseDouble(value);
            }
            if (Constants.numPressCylinders.equals(name)) {
                inputInfo.setNumPressCylinders(val);
            }
            else if (Constants.pressCylinderDays.equals(name)) {
                inputInfo.setPressCylinderDays(val);
            }
        }
        return inputInfo;
    }

}
