/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.pricing;

import com.intertek.phoenix.pricing.model.BooleanBasePricer;
import com.intertek.phoenix.pricing.model.PbLighterageDaysPricer;
import com.intertek.phoenix.pricing.model.PbPressurizedCylindersPricer;
import com.intertek.phoenix.pricing.model.PbSampleRetentionDisposalPricer;
import com.intertek.phoenix.pricing.model.SamplingIncludesPricer;
import com.intertek.phoenix.pricing.model.SlatePricer;
import com.intertek.phoenix.pricing.model.StandardPricer;
import com.intertek.phoenix.pricing.model.TestPricer;

/**
 * This class can be refactored to use ServiceLocator, or DI
 * 
 * @author richard.qin
 */
public class PricerManager {
    
    private static Pricer standardPricer = new StandardPricer();
    private static Pricer booleanPricer = new BooleanBasePricer();
    private static Pricer testPricer = new TestPricer();
    private static Pricer slatePricer = new SlatePricer();
    private static Pricer pbLighterageDaysPricer = new PbLighterageDaysPricer();
    private static Pricer pbPressurizedCylinderPricer = new PbPressurizedCylindersPricer();
    private static Pricer pbSampleRetentionDisposalPricer = new PbSampleRetentionDisposalPricer();
    private static Pricer samplingIncludesPricer = new SamplingIncludesPricer();
    
    
    public static Pricer getPricer(PricingModelType type){
        switch(type){
            case STANDARD_FORMULA:
                return standardPricer;
            case BOOLEAN_BASE_PRICE:
                return booleanPricer;
            case TEST_SERVICE:
                return testPricer;
            case SLATE:
                return slatePricer;
            case PB_LIGHTERAGE_DAYS:
                return pbLighterageDaysPricer;
            case PB_PRESSURIZED_CYLINDERS:
                return pbPressurizedCylinderPricer;
            case PB_SAMPLE_RETENTION_DISPOSAL:
                return pbSampleRetentionDisposalPricer;
            case SAMPLING_INCLUDES:
                return samplingIncludesPricer;
                
            case CONTRACT_INSPECTION:
            case CONTRACT_PRICE_GATE:
            case CONTRACT_TOW_MAX:
            case JOB:
            case JOB_SERVICE:
            case LINEITEM:
            case LOT_SERVICE:
            case PB_LIGHTERAGE_HRS:
            case SHELL_INSPECTION:
            case SHELL_VESSEL_QTY:
            case VESS_MAX_PRICE:
            case VESSEL:
            case VESSEL_SERVICE:
                
            default:
                return null;
        }
    }
}
