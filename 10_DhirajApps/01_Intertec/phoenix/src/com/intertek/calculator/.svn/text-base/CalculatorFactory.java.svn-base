package com.intertek.calculator;

import java.util.HashMap;
import java.util.Map;

import com.intertek.util.Constants;

/**
 * A Factory to return different kind of Calculator implementation.
 *
 * @version 1.0, 11/12/08
 *
 * @see Calculator
 * @see StandardCalculator
 * @see BooleanBasePriceCalculator
 * @see PbPressurizedCylindersCalculator
 * @see SamplingIncludesCalculator
 * @see InspectionCalculator
 * @see InspectionVesselCalculator
 * @see InspectionContractCalculator
 * @see InspectionShellCalculator
 * @see SimpleCalculator
 *
 * @since 1.0
 */

public class CalculatorFactory
{
  private static Map calculatorMap = new HashMap();

  static
  {
    calculatorMap.put(Constants.CALCULATOR_STANDARD_FORMULA, new StandardCalculator());
    calculatorMap.put(Constants.CALCULATOR_BOOLEAN_BASE_PRICE, new BooleanBasePriceCalculator());
    calculatorMap.put(Constants.CALCULATOR_PB_PRESSURIZED_CYLINDERS, new PbPressurizedCylindersCalculator());
    calculatorMap.put(Constants.CALCULATOR_SAMPLING_INCLUDES, new SamplingIncludesCalculator());

    calculatorMap.put(Constants.CALCULATOR_INSPECTION, new InspectionCalculator());
    calculatorMap.put(Constants.CALCULATOR_INSPECTION_VESSEL, new InspectionVesselCalculator());
    calculatorMap.put(Constants.CALCULATOR_INSPECTION_CONTRACT, new InspectionContractCalculator());
    calculatorMap.put(Constants.CALCULATOR_INSPECTION_SHELL, new InspectionShellCalculator());

    calculatorMap.put(Constants.CALCULATOR_SIMPLE, new SimpleCalculator());
  };

  /**
   * Returns the different Calculator implementation based on the input name.
   *
   * @param name the calculator name.
   * @return a <code>Calculator</code> implementation which implements certain calculation logic.
   */
  public static Calculator getCalculator(String name)
  {
    return (Calculator)calculatorMap.get(name);
  }
}
