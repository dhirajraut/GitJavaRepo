/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.pricing;

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

/**
 * 
 * @author richard.qin
 */
public interface Pricer {
    public final static int CONTRACT_ID = 0;
    public final static int EXPRESSION_ID = 1;
    public final static int VESSEL_TYPE = 2;
    public final static int PRODUCT_GROUP = 3;
    public final static int SERVICE_LEVEL = 4;
    public final static int LOCATION = 5;
    public final static int INT_QUANTITY = 6;
    public final static int FLOAT_QUANTITY = 7;
    public final static int NOMINATION_DATE_STR = 8;
    
    /**
     * Get the parameters for looking up ServiceRate. The parameter values
     * are pricing model specific.
     * @param contract
     * @param cjo
     * @param sl
     * @param ce
     * @param qinfo
     * @return the following parameters in an object array:
     *      String contractId         
     *      String expressionId      
     *      String vesselType         
     *      String productGroup       
     *      String serviceLevel       
     *      String location           
     *      Float floatQuantity      
     *      Integer intQuantity        
     *      String nominationDateStr  
     * @throws JobSrvcException 
     */
    @Deprecated
    Object[] getPricingParameters(CfgContract contract, ContractJobOrder cjo, ServiceLevel sl, 
                                  ContractExpression ce, InputInfo qinfo) throws JobSrvcException;

    PricingParameter getPricingParameter(CfgContract contract, ContractJobOrder cjo, ServiceLevel sl, 
                                         ContractExpression ce, InputInfo qinfo) throws JobSrvcException;

    /**
     * Calculate the total price for the given service rate and user input.
     * <p>
     * ServiceRate contains all the required rate information. Note, all rates are already
     * adjusted with multipliers and escalators.
     * 
     * @param serviceRate The serviceRate is already adjusted with multipliers and escalators
     * @param inputInfo
     * @param percentage TODO check where percentage is applicable
     * @return the total price
     */
    double calculatePrice(ServiceRate serviceRate, InputInfo inputInfo);

    double calculateDiscount(CfgContract contract, ContractExpression contractExpression, 
                             Expression expression, ServiceRate serviceRate, LocationDiscount ld);

    public String formatLineDescription(ContractExpression contractExpression, ServiceRate serviceRate, 
                                        InputInfo inputInfo) throws JobSrvcException;

    /**
     * Get user input information for the service expression from the selected 
     * controls in the service.
     * @param service
     * @param expression
     * @return
     */
    public InputInfo getInputInfo(JobContractService service, JobContractServiceExpression expression,
                                  List<JobContractServiceControl> relatedControls);
    
}
