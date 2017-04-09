package com.intertek.calculator;

import java.util.Map;

import com.intertek.util.Constants;

/**
 * The Pb Pressurized Cylinders Calculator
 *
 * Price:
 *
 *  (if(UseMaximum, min(rate_maxPrice, MinAdjustedPrice ), MinAdjustedPrice )
 *
 * MinAdjustedPrice
 * if(UseMinimum, max(rate_minPrice, UnadjustedPrice), UnadjustedPrice)
 *
 * UnadjustedPrice
 * rate_BasePrice + (rate_unitPrice * max(0, (pressCylinderDays - GetUnitsIncluded)) * numPressCylinders)
 *
 * UseMaximum
 * if(rate_maxPrice > 0, true, false)
 *
 * UseMinimum
 * if(rate_minPrice > 0, true, false)
 *
 * - Please note that quantity and percentage are entered by the users.  The percentage is used for only for a few components.
 * - Note that rate_XXX (ex: rate_minPrice maps to PS_ITSC_SRVCE_RATE:MINIMUM_PRICE) directly map to a data element from the retrieved result in section ‘A’
 * - The multipliers and escalators are applied on the individual rates.  Refer to section ‘Multipliers & Escalators’.  The ‘OPS_LAB_Factor’ are refered to as ‘multipliers’ in the section ‘Multipliers & Escalators’
 * - All prices should be rounded to four decimal places.
 * - The ‘CurrencyMultiplier’ is the conversion rate from one currency to the other currency.  The table ‘PS_RT_RATE_TBL’ holds the conversion rate.  Depending on the currency of the contract, the user will have choice upfront to choose the conversion currency.  Refer to the drop-down ‘Transaction Currency – Multiplier’ under ‘’Add Customers’ tab in the mock-up.  You need to add a customer to view the above.
 *
 * Here is an example for the value ‘rate_minPrice’:
 * rate_minPrice:
 * round(nativeRateMinPrice * CurrencyMultiplier, -4)
 *
 * nativeRateMinPrice:
 * if(isContractPrice > 0, (minPriceFromResult in ‘A’) * OPS_LAB_Factor) * annual_escalator,
 *   (minPriceFromResult in ‘A’) * OPS_LAB_Factor
 * )
 *
 **/

public class PbPressurizedCylindersCalculator implements Calculator
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
    Double unitPrice = (Double)params.get(Constants.UNIT_PRICE);
    Double minPrice = (Double)params.get(Constants.MIN_PRICE);
    Double maxPrice = (Double)params.get(Constants.MAX_PRICE);
    Double unitsIncluded = (Double)params.get(Constants.UNITS_INCLUDED);
    Double numPressCylinders = (Double)params.get(Constants.numPressCylinders);
    Double pressCylinderDays = (Double)params.get(Constants.pressCylinderDays);

    double basePriceValue = 0;
    double unitPriceValue = 0;
    double minPriceValue = 0;
    double maxPriceValue = 0;
    double unitsIncludedValue = 0;
    double numPressCylindersValue = 0;
    double pressCylinderDaysValue = 0;

    if(basePrice != null) basePriceValue = basePrice.doubleValue();
    if(unitPrice != null) unitPriceValue = unitPrice.doubleValue();
    if(minPrice != null) minPriceValue = minPrice.doubleValue();
    if(maxPrice != null) maxPriceValue = maxPrice.doubleValue();
    if(unitsIncluded != null) unitsIncludedValue = unitsIncluded.doubleValue();
    if(numPressCylinders != null) numPressCylindersValue = numPressCylinders.doubleValue();
    if(pressCylinderDays != null) pressCylinderDaysValue = pressCylinderDays.doubleValue();

    double result = 0;
    double unadjustedPrice = basePriceValue + unitPriceValue * Math.max(0, pressCylinderDaysValue - unitsIncludedValue) * numPressCylinders;

    double minAdjustedPrice = minPriceValue > 0 ? Math.max(minPriceValue, unadjustedPrice) : unadjustedPrice;

    if(maxPriceValue > 0) result = Math.min(maxPriceValue, minAdjustedPrice);
    else result = minAdjustedPrice;

    return new Double(result);
  }
}
