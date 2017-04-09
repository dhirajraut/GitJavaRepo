package com.intertek.calculator;

/**
 * A Pricer is used to apply pricing logic based on the input <code>CalculatorRequest</code>.
 * The result is an <code>CalculatorResult</code>
 *
 * @version 1.0, 11/12/08
 *
 * @see PricerFactory
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

public interface Pricer
{
  /**
   * Using input <code>CalculatorRequest</code> to apply the pricing logic.
   *
   * @param cr the <code>CalculatorRequest</code> contains the parameters used in the pricer.
   * @return a <code>CalculatorResult</code> which contains the calculation result.
   */
  CalculatorResult applyPricingLogic(CalculatorRequest cr);
}
