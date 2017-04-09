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
import com.intertek.util.Constants;

/**
 * 
 * @author richard.qin
 */
public class PbLighterageDaysPricer extends StandardPricer {

    @Override
    public double calculateDiscount(CfgContract contract, ContractExpression contractExpression, 
                                    Expression expression, ServiceRate serviceRate, LocationDiscount ld) {
        // use the standard discount
        return super.calculateDiscount(contract, contractExpression, expression, serviceRate, ld);
    }

    @Override
    public double calculatePrice(ServiceRate serviceRate, InputInfo inputInfo) {
        // use the standard calculator
        return super.calculatePrice(serviceRate, inputInfo);
    }

    @Override
    public String formatLineDescription(ContractExpression contractExpression, ServiceRate serviceRate, 
                                        InputInfo inputInfo) throws JobSrvcException {
        Object[] objs = new Object[] {
              serviceRate.getBasePrice(),
              inputInfo.getDoubleQuantity(),
              serviceRate.getUnitPrice(),
              serviceRate.getUnitsIncluded(),
              serviceRate.getMinimumPrice(),
              serviceRate.getMaximumPrice(),
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
        // over write some values
        String lighterageType = inputInfo.getLighterageType();
        String expressionId = null;
        if(Constants.Harbor.equals(lighterageType)){
            expressionId = Constants.L_HarborDay;
        }
        if(Constants.Offshore.equals(lighterageType)){
            expressionId = Constants.L_OffshoreDay;
        }

        params[EXPRESSION_ID] = expressionId;
        params[VESSEL_TYPE] = "*";
        return params;
    }

    @Override
    public InputInfo getInputInfo(JobContractService service, JobContractServiceExpression expression, 
                                        List<JobContractServiceControl> relatedControls) {
        InputInfo inputInfo = new InputInfo();
        for (JobContractServiceControl control : relatedControls) {
            String name = control.getObjectName();
            String value = control.getInputValue0(); // inputValue1 - 4 are never used
            if (Constants.GROUP.equals(control.getControlType()) 
                    && Constants.LIGHTERAGE_TYPE.equals(name)) {
                inputInfo.setLighterageType(value);
            }
            else{
                inputInfo.setDoubleQuantity(Double.parseDouble(value));
            }
        }
        return inputInfo;
    }
}
