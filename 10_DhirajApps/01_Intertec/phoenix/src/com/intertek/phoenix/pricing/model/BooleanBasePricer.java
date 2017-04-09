/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.pricing.model;

import java.text.MessageFormat;
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
public class BooleanBasePricer extends StandardPricer {

    @Override
    public double calculateDiscount(CfgContract contract, ContractExpression contractExpression, 
                                    Expression expression, ServiceRate serviceRate, LocationDiscount ld) {
        // the boolean model does not support discount.
        return 0;
    }

    @Override
    public double calculatePrice(ServiceRate serviceRate, InputInfo inputInfo) {
        if(inputInfo.getBooleanValue()) {
            return serviceRate.getBasePrice();
        }
        return 0;
    }

    @Override
    public String formatLineDescription(ContractExpression contractExpression, ServiceRate serviceRate, 
                                        InputInfo inputInfo) throws JobSrvcException {
        Object[] objs = new Object[] {
            serviceRate.getBasePrice()                          
        };
        String rbValue = contractExpression.getVisibility();

        MessageFormat format = new MessageFormat(rbValue);
        String result = format.format(objs);

        String contractRefNo = serviceRate.getContractRef();
        if ((contractRefNo != null) && !"0".equals(contractRefNo)) {
            return contractRefNo + " - " + result;
        }

        return result;
    }

    @Override
    public Object[] getPricingParameters(CfgContract contract, ContractJobOrder contractJobOrder, 
                                         ServiceLevel serviceLevel, ContractExpression contractExpression, 
                                         InputInfo inputInfo) throws JobSrvcException {
        Object[] parameters = super.getPricingParameters(contract, contractJobOrder, serviceLevel, 
                                                         contractExpression, inputInfo);
        // just need to override the quantity parameter
        parameters[FLOAT_QUANTITY] = 1.0;
        
        return parameters;
    }

    @Override
    public InputInfo getInputInfo(JobContractService service, JobContractServiceExpression expression, 
                                        List<JobContractServiceControl> relatedControls) {
        InputInfo inputInfo = new InputInfo();
        for (JobContractServiceControl control : relatedControls) {
            String name = control.getObjectName();
            String value = control.getInputValue0(); // inputValue1-4 are never used
            if(Constants.GROUP.equals(control.getControlType())
              && name.startsWith("BOOLEAN_VAL") // TODO specified in doc, but not in old code. Verify.
              // these checks may be redundant, as the control_expression map table should
              // resolve this. But doing it anyway.
              && !Constants.LIGHTERAGE_TYPE.equals(name)
              && !Constants.PRODUCT_TYPE.equals(name)
              && !Constants.PRODUCT_TYPE1.equals(name)){
                if(Constants.Yes.equals(value)){
                    inputInfo.setBooleanValue(true);
                }
                else{
                    inputInfo.setBooleanValue(false);
                }
            }
        }
        return inputInfo;
    }
}
