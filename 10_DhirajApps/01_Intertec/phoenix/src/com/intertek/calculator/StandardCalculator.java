package com.intertek.calculator;

import java.util.Map;

import com.intertek.util.Constants;

/**
 * The Standard Calculator
 *
 * Price:
 * if(percentage > 1,
 *    (if(UseMaximum, min(rate_maxPrice, MinAdjustedPrice ), MinAdjustedPrice )) * (percentage/100),
 *    (if(UseMaximum, min(rate_maxPrice, MinAdjustedPrice ), MinAdjustedPrice )))
 *
 * MinAdjustedPrice
 * if(UseMinimum, max(rate_minPrice, UnadjustedPrice), UnadjustedPrice)
 *
 * UnadjustedPrice
 * rate_BasePrice +  rate_unitPrice * max(0, (Quantity - GetUnitsIncluded))
 *
 * UseMaximum
 * if(rate_maxPrice > 0, true, false)
 *
 * UseMinimum
 * if(rate_minPrice > 0, true, false)
 *
 * PriceWithOutPercentage
 * if(UseMaximum, min(rate_maxPrice, MinAdjustedPrice ), MinAdjustedPrice )
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
 *
 * annual_escalator
 * if(PS_ITSC_CONTRACT:ITS_ANNUAL_ESC) > 0, (1 + (PS_ITSC_CONTRACT:ITS_ANNUAL_ESC/100))** noOfYears, 1)
 * noOfYears
 * if(PS_ITSC_CONTRACT:ITS_ANNUAL_ESC) > 0, toInteger(yearsBetween(jobFinishDate, PS_ITSC_CONTRACT: ITS_ESCALATOR_DT)), 0)
 *
 **/

public class StandardCalculator implements Calculator
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
    Double qty = (Double)params.get(Constants.QUANTITY);
    Double percentage = (Double)params.get(Constants.PERCENTAGE);

    double basePriceValue = 0;
    double unitPriceValue = 0;
    double minPriceValue = 0;
    double maxPriceValue = 0;
    double unitsIncludedValue = 0;
    double qtyValue = 0;
    double percentageValue = 0;

    if(basePrice != null) basePriceValue = basePrice.doubleValue();
    if(unitPrice != null) unitPriceValue = unitPrice.doubleValue();
    if(minPrice != null) minPriceValue = minPrice.doubleValue();
    if(maxPrice != null) maxPriceValue = maxPrice.doubleValue();
    if(unitsIncluded != null) unitsIncludedValue = unitsIncluded.doubleValue();
    if(qty != null) qtyValue = qty.doubleValue();
    if(percentage != null) percentageValue = percentage.doubleValue();

    double result = 0;
    double unadjustedPrice = basePriceValue + unitPriceValue * Math.max(0, qtyValue - unitsIncludedValue);
    double minAdjustedPrice = minPriceValue > 0 ? Math.max(minPriceValue, unadjustedPrice) : unadjustedPrice;

    if(maxPriceValue > 0) result = Math.min(maxPriceValue, minAdjustedPrice);
    else result = minAdjustedPrice;

    if(percentageValue > 1)
    {
      result *= percentageValue/100;
    }

    return new Double(result);
  }
}
