/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.pricing.model;

import java.text.MessageFormat;

import com.intertek.entity.ContractExpression;
import com.intertek.entity.ServiceRate;
import com.intertek.phoenix.job.JobSrvcException;
import com.intertek.phoenix.pricing.InputInfo;


/**
 * 
 * @author richard.qin
 */
public class TestPricer extends SlatePricer {

    @Override
    public String formatLineDescription(ContractExpression contractExpression, ServiceRate serviceRate, 
                                        InputInfo inputInfo) throws JobSrvcException {
        Object[] objs = new Object[] {
            inputInfo.getTestOrSlateId(),
            inputInfo.getDescription(),
            inputInfo.getDoubleQuantity(),
            serviceRate.getUnitPrice(),
        };

        String rbValue = inputInfo.getRbValue();
        if(rbValue == null) {
            return null;
        }

        MessageFormat format = new MessageFormat(rbValue);
        String result = format.format(objs);

        String contractRefNo = serviceRate.getContractRef();
        if((contractRefNo != null) && !"0".equals(contractRefNo)) {
            return contractRefNo + " - " + result;
        }

        return result;
    }
}
