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
import com.intertek.phoenix.pricing.Pricer;
import com.intertek.phoenix.pricing.PricingParameter;
import com.intertek.phoenix.util.CommonUtil;

/**
 * 
 * @author richard.qin
 */
public class SlatePricer implements Pricer {

    /**
     * @see com.intertek.phoenix.pricing.Pricer#calculateDiscount(com.intertek.entity.CfgContract, com.intertek.entity.ContractExpression, com.intertek.entity.Expression, com.intertek.entity.ServiceRate, com.intertek.entity.LocationDiscount)
     */
    @Override
    public double calculateDiscount(CfgContract contract, ContractExpression contractExpression, 
                                    Expression expression, ServiceRate serviceRate, LocationDiscount ld) {
        double totalDiscount = 0;
        double zoneDiscount = 0;
        double zoneDiscountPct = 0;
        double overallDiscount = 0;
        double overallZoneDiscount = 0;
        double cfgDiscountPct = 0;
        
        if(ld != null){
        	zoneDiscount = CommonUtil.doubleValue(ld.getSlateDiscountPercent());
        	zoneDiscountPct = CommonUtil.doubleValue(ld.getZoneDiscountPercent());
        	overallDiscount = CommonUtil.doubleValue(ld.getCfgDiscountPercent());
        	totalDiscount = overallDiscount;
        }
        if(contract != null){
        	overallZoneDiscount = CommonUtil.doubleValue(contract.getSlateDiscount());
        	cfgDiscountPct = CommonUtil.doubleValue(contract.getCfgDiscountPercent());
        }
        
        
        boolean isContractPrice = false;
        if(serviceRate.getServiceRateId().getContractId().equals(contract.getCfgContractId().getContractId())){
            isContractPrice = true;
        }
        
        if(isContractPrice){
            // no other discount is applicable
        }
        else if(zoneDiscount > 0){
            totalDiscount += zoneDiscount;
        }
        else if(zoneDiscountPct > 0){
            totalDiscount += zoneDiscountPct;
        }
        else if(overallZoneDiscount > 0){
            totalDiscount += overallZoneDiscount;
        }
        else{
            totalDiscount += cfgDiscountPct;
        }
        
        if(overallDiscount > 0 || cfgDiscountPct > 0){
            if(totalDiscount > 100){
                return 100;
            }
            else{
                return totalDiscount;
            }
        }
        else{
            return 0;
        }
    }

    /**
     * @see com.intertek.phoenix.pricing.Pricer#calculatePrice(com.intertek.entity.ServiceRate, com.intertek.phoenix.pricing.InputInfo)
     */
    @Override
    public double calculatePrice(ServiceRate serviceRate, InputInfo inputInfo) {
        return serviceRate.getUnitPrice() * inputInfo.getDoubleQuantity();
    }

    /**
     * @see com.intertek.phoenix.pricing.Pricer#formatLineDescription(com.intertek.entity.ContractExpression, com.intertek.entity.ServiceRate, com.intertek.phoenix.pricing.InputInfo)
     */
    @Override
    public String formatLineDescription(ContractExpression contractExpression, ServiceRate serviceRate, 
                                        InputInfo inputInfo) throws JobSrvcException {
        Object[] objs = new Object[] {
            inputInfo.getDoubleQuantity(),
            serviceRate.getUnitPrice(),
            inputInfo.getTestOrSlateId(),
            inputInfo.getDescription(),
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

    /**
     * @see com.intertek.phoenix.pricing.Pricer#getPricingParameters(com.intertek.entity.CfgContract, com.intertek.phoenix.job.ContractJobOrder, com.intertek.phoenix.job.ServiceLevel, com.intertek.entity.ContractExpression, com.intertek.phoenix.pricing.InputInfo)
     */
    @Override
    public Object[] getPricingParameters(CfgContract contract, ContractJobOrder cjo, ServiceLevel sl, 
                                         ContractExpression ce, InputInfo qinfo)
            throws JobSrvcException {
        throw new UnsupportedOperationException();
    }

    /**
     * @see com.intertek.phoenix.pricing.Pricer#getInputInfo(com.intertek.phoenix.entity.JobContractService, com.intertek.phoenix.entity.JobContractServiceExpression, java.util.List)
     */
    @Override
    public InputInfo getInputInfo(JobContractService service, JobContractServiceExpression expression, 
                                        List<JobContractServiceControl> relatedControls) {
        throw new UnsupportedOperationException();
    }

    /**
     * @see com.intertek.phoenix.pricing.Pricer#getPricingParameter(com.intertek.entity.CfgContract, com.intertek.phoenix.job.ContractJobOrder, com.intertek.phoenix.job.ServiceLevel, com.intertek.entity.ContractExpression, com.intertek.phoenix.pricing.InputInfo)
     */
    @Override
    public PricingParameter getPricingParameter(CfgContract contract, ContractJobOrder cjo, ServiceLevel sl, ContractExpression ce, InputInfo qinfo)
            throws JobSrvcException {
        return null;
    }

}
