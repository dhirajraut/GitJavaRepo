package com.intertek.calculator;

import java.util.HashMap;
import java.util.Map;

import com.intertek.util.Constants;

/**
 * A Factory to return different kind of Pricer implementation.
 *
 * @version 1.0, 11/12/08
 *
 * @see Pricer
 * @see StandardPricer
 * @see BooleanBasePricePricer
 * @see PbSampleRetentionDisposalPricer
 * @see PbLighterageDaysPricer
 * @see PbPressurizedCylindersPricer
 * @see SamplingIncludesPricer
 * @see InspectionPricer
 * @see InspectionVesselPricer
 * @see InspectionContractPricer
 * @see InspectionShellPricer
 * @see TestPricer
 * @see SlatePricer
 *
 * @since 1.0
 */

public class PricerFactory
{
  private static Map pricerMap = new HashMap();

  static
  {
    pricerMap.put(Constants.CALCULATOR_STANDARD_FORMULA_PRICER, new StandardPricer());
    pricerMap.put(Constants.CALCULATOR_BOOLEAN_BASE_PRICE_PRICER, new BooleanBasePricePricer());
    pricerMap.put(Constants.CALCULATOR_PB_SAMPLE_RETENTION_DISPOSAL_PRICER, new PbSampleRetentionDisposalPricer());
    pricerMap.put(Constants.CALCULATOR_PB_LIGHTERAGE_DAYS_PRICER, new PbLighterageDaysPricer());
    pricerMap.put(Constants.CALCULATOR_PB_PRESSURIZED_CYLINDERS_PRICER, new PbPressurizedCylindersPricer());
    pricerMap.put(Constants.CALCULATOR_SAMPLING_INCLUDES_PRICER, new SamplingIncludesPricer());

    pricerMap.put(Constants.CALCULATOR_INSPECTION_PRICER, new InspectionPricer());
    pricerMap.put(Constants.CALCULATOR_INSPECTION_VESSEL_PRICER, new InspectionVesselPricer());
    pricerMap.put(Constants.CALCULATOR_INSPECTION_CONTRACT_PRICER, new InspectionContractPricer());
    pricerMap.put(Constants.CALCULATOR_INSPECTION_SHELL_PRICER, new InspectionShellPricer());

    pricerMap.put(Constants.CALCULATOR_TEST_PRICER, new TestPricer());
    pricerMap.put(Constants.CALCULATOR_SLATE_PRICER, new SlatePricer());
  };

  /**
   * Returns the different Pricer implementation based on the input name.
   *
   * @param name the pricer name.
   * @return a <code>Pricer</code> implementation which implements certain calculation logic.
   */
  public static Pricer getPricer(String name)
  {
    Pricer pricer = (Pricer)pricerMap.get(name);
    if(pricer != null) return pricer;

    return (Pricer)pricerMap.get(Constants.CALCULATOR_STANDARD_FORMULA_PRICER);
  }
}
