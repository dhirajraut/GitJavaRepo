package com.intertek.calculator;

import java.util.Map;

import com.intertek.util.Constants;

/**
 * The Boolean Base Price Calculator
 *
 * Price:
 *   rate_BasePrice * Condition:intValue; the Condition:intValue is 1 if the selected choice is ‘Yes’, else ‘0’ which will result in zero price
 *
 *
 * - Note that rate_XXX (ex: rate_BasePrice maps to PS_ITSC_SRVCE_RATE:CFG_BASE_PRICE) directly map to a data element from the retrieved result in section ‘A’
 * - The multipliers and escalators are applied on the individual rates.  Refer to section ‘Multipliers & Escalators’.  The ‘OPS_LAB_Factor’ are refered to as ‘multipliers’ in the section ‘Multipliers & Escalators’
 * - All prices should be rounded to four decimal places.
 * - The ‘CurrencyMultiplier’ is the conversion rate from one currency to the other currency.  The table ‘PS_RT_RATE_TBL’ holds the conversion rate.  Depending on the currency of the contract, the user will have choice upfront to choose the conversion currency.  Refer to the drop-down ‘Transaction Currency – Multiplier’ under ‘’Add Customers’ tab in the mock-up.  You need to add a customer to view the above.
 *
 * The following is same as in the standard formula but I have just shown the example for the only variable that we need here, that is ‘rate_BasePrice’
 * Here is an example for the value ‘rate_BasePrice’:
 * rate_BasePrice:
 * round(nativeRateBasePrice * CurrencyMultiplier, -4)
 *
 * if(isContractPrice > 0, (basePriceFromResult in ‘A’) * OPS_LAB_Factor) * annual_escalator,
 *   (basePriceFromResult in ‘A’) * OPS_LAB_Factor
 * )
 *
 **/

public class BooleanBasePriceCalculator implements Calculator
{

  /**
   * Using input parameters to calculate the total price.
   *
   * @param params a map which contains the input parameters.
   * @return the total price as a double value.
   */
  public Double calculate(Map params)
  {
    if(params == null) return null;

    Double basePrice = (Double)params.get(Constants.BASE_PRICE);
    Boolean condition = (Boolean)params.get(Constants.RADIO_INPUT);
    if((condition != null) && condition.booleanValue()) return basePrice;

    return new Double(0);
  }
}
