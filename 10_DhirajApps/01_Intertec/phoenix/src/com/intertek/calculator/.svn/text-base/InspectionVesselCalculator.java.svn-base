package com.intertek.calculator;

import java.util.Map;

import com.intertek.util.Constants;

/**
 * The Inspection Vessel Calculator (used in model - 2)
 *
 * The logic to calculate the price follows:
 * - if(maxPrice > 0, maxPrice, TotalLotPrices)
 * - maxPrice = if (vesselApplied > 0, min(rate_maxPrice, TotalLotPrices + addtnlVesselPr), 0)
 * - vesselApplied =  if(rate_maxPrice > 0, if (TotalLotPrices> rate_maxPrice, 1, 0)
 * - TotalLotPrices = sum of all lot prices in a vessel.
 * - Please note that if the vessel maximum price kicks in, all the lot level prices should be 0.
 *
 * - Note that rate_XXX (ex: rate_minPrice maps to PS_ITSC_INSP_RATE:MINIMUM_PRICE) directly map to a data element from the retrieved from the INSP_RATe table.
 * - The multipliers and escalators are applied on the individual rates.  Refer to section ‘Multipliers & Escalators’.  The ‘OPS_LAB_Factor’ are refered to as ‘multipliers’ in the section ‘Multipliers & Escalators’
 * - All prices should be rounded to four decimal places.
 * - The ‘CurrencyMultiplier’ is the conversion rate from one currency to the other currency.  The table ‘PS_RT_RATE_TBL’ holds the conversion rate.  Depending on the currency of the contract, the user will have choice upfront to choose the conversion currency.  Refer to the drop-down ‘Transaction Currency – Multiplier’ under ‘’Add Customers’ tab in the mock-up.  You need to add a customer to view the above.
 *
 * Here is an example for the value ‘rate_minPrice’:
 * rate_minPrice:
 * round(nativeRateMinPrice * CurrencyMultiplier, -4)
 *
 * nativeRateMinPrice:
 * if(isContractPrice > 0, (minPriceFromResult) * OPS_LAB_Factor) * annual_escalator,
 *   (mi
 *   nPriceFromResult) * OPS_LAB_Factor
 * )
 *
 **/

public class InspectionVesselCalculator implements Calculator
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

    Double maxPrice = (Double)params.get(Constants.MAX_PRICE);
    Double totalLotInspPrice = (Double)params.get(Constants.TOTAL_LOT_INSP_PRICE);

    double maxPriceValue = 0;
    double totalLotInspPriceValue = 0;

    if(maxPrice != null) maxPriceValue = maxPrice.doubleValue();
    if(totalLotInspPrice != null) totalLotInspPriceValue = totalLotInspPrice.doubleValue();

    double result = 0;

    boolean vesselApplied = false;
    if((maxPriceValue > 0) && (totalLotInspPriceValue > maxPriceValue)) vesselApplied = true;

    if(vesselApplied) result = Math.min(maxPriceValue, totalLotInspPriceValue);

    if(result == 0) result = totalLotInspPriceValue;

    return new Double(result);
  }
}
