package com.intertek.calculator;

import java.util.Map;

import com.intertek.util.Constants;

/**
 * The Sampling Includes Calculator
 *
 * Price:
 * max(if(UseMaximum, min(rate_maxPrice, MinAdjustedPrice), MinAdjustedPrice), 0)
 *
 * UseMaximum
 * if(rate_maxPrice > 0, true, false)
 *
 * MinAdjustedPrice
 * if(UseMinimum, max(rate_minPrice, UnadjustedPrice), UnadjustedPrice)
 *
 * UnadjustedPrice
 * if(rate_intData1 = 1,
 *    rate_BasePrice + rate_unitPrice * ( TotNoOfContainers - max(0, includedSamples)),
 *    rate_BasePrice + rate_unitPrice * ( TotNoOfQuarts - max(0, includedSamples))
 *   )
 *
 * includedSamples
 * if(rate_intData0 > 0, rate_intData0, noOfContainers * rate_untsIncld))
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
 **/

public class SamplingIncludesCalculator implements Calculator
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
    Double includedSamples = (Double)params.get(Constants.includedSamples);
    Double noOfContainers = (Double)params.get(Constants.noOfContainers);
    Double noOfQuarts = (Double)params.get(Constants.noOfQuarts);
    Integer intData1 = (Integer)params.get(Constants.INT_DATA1);

    double basePriceValue = 0;
    double unitPriceValue = 0;
    double minPriceValue = 0;
    double maxPriceValue = 0;
    double includedSamplesValue = 0;
    double noOfContainersValue = 0;
    double noOfQuartsValue = 0;
    int intData1Value = 0;

    if(basePrice != null) basePriceValue = basePrice.doubleValue();
    if(unitPrice != null) unitPriceValue = unitPrice.doubleValue();
    if(minPrice != null) minPriceValue = minPrice.doubleValue();
    if(maxPrice != null) maxPriceValue = maxPrice.doubleValue();
    if(includedSamples != null) includedSamplesValue = includedSamples.doubleValue();
    if(noOfContainers != null) noOfContainersValue = noOfContainers.doubleValue();
    if(noOfQuarts != null) noOfQuartsValue = noOfQuarts.doubleValue();
    if(intData1 != null) intData1Value = intData1.intValue();

    double result = 0;
    double unadjustedPrice = 0;
    if(intData1 == 1)
      unadjustedPrice = basePriceValue + unitPriceValue * ( noOfContainersValue - Math.max(0, includedSamplesValue));
    else
      unadjustedPrice = basePriceValue + unitPriceValue * ( noOfQuartsValue - Math.max(0, includedSamplesValue));

    double minAdjustedPrice = minPriceValue > 0 ? Math.max(minPriceValue, unadjustedPrice) : unadjustedPrice;

    if(maxPriceValue > 0) result = Math.min(maxPriceValue, minAdjustedPrice);
    else result = minAdjustedPrice;

    result = Math.max(result, 0);

    return new Double(result);
  }
}
