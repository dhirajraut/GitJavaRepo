package com.intertek.calculator;

import java.util.Map;

import com.intertek.util.Constants;

/**
 * The Inspection Calculator (used in model - 1 and lot level model - 2)
 *
 * The logic to calculate the price follows:
 * - if(UseMaximum, min(rate_maxPrice, MinAdjustedPrice), MinAdjustedPrice)
 * - In this case, UseMaximum will always be ‘false’.  We can revisit in the future to enhance the max condition.
 * - MinAdjustedPrice:
 * if(additionalVessel (PS_ITSC_CONTROL:OBJECT_NAME - BOOLEAN_VAL_1 – if present and if chosen to yes),
 *    rate_fltData1,
 *    if(additionalLot (PS_ITSC_CONTROL:OBJECT_NAME - ADDITIONAL_LOT),
 *       if( rate_intData0=1,
 *    rate_fltData0,
 *    max(rate_fltData0, addtnlUnadjustedPrice)),
 *       max(rate_minPrice, UnadjustedPrice)
 *    )
 * )
 *
 * - addtnlUnadjustedPrice:
 * rate_unitPrice * max(0, (selectedQty (entered by user) – GetUnitsIncluded(PS_ITSC_INSP_RATE:UNITS_INCLUDED)))
 *
 * - UnadjustedPrice:
 * if(rate_BasePrice > 0, rate_BasePrice, rate_unitPrice * max(0, (selectedQty - GetUnitsIncluded)) )
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
 *   (minPriceFromResult) * OPS_LAB_Factor
 * )
 *
 **/

public class InspectionCalculator implements Calculator
{
  /**
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
    Double qty = (Double)params.get(Constants.INSPECTION_QUANTITY);
    Boolean additionalVessel = (Boolean)params.get(Constants.BOOLEAN_VAL_1);
    if(additionalVessel == null) additionalVessel = (Boolean)params.get(Constants.ADDITIONAL_VESSEL);
    Boolean additionalLot = (Boolean)params.get(Constants.ADDITIONAL_LOT);
    Double floatData0 = (Double)params.get(Constants.FLOAT_DATA0);
    Double floatData1 = (Double)params.get(Constants.FLOAT_DATA1);
    Integer intData0 = (Integer)params.get(Constants.INT_DATA0);

    double basePriceValue = 0;
    double unitPriceValue = 0;
    double minPriceValue = 0;
    double maxPriceValue = 0;
    double unitsIncludedValue = 0;
    double qtyValue = 0;
    boolean additionalVesselValue = false;
    boolean additionalLotValue = false;
    double floatData0Value = 0;
    double floatData1Value = 0;
    int intData0Value = 0;

    if(basePrice != null) basePriceValue = basePrice.doubleValue();
    if(unitPrice != null) unitPriceValue = unitPrice.doubleValue();
    if(minPrice != null) minPriceValue = minPrice.doubleValue();
    if(maxPrice != null) maxPriceValue = maxPrice.doubleValue();
    if(unitsIncluded != null) unitsIncludedValue = unitsIncluded.doubleValue();
    if(qty != null) qtyValue = qty.doubleValue();
    if(additionalVessel != null) additionalVesselValue = additionalVessel.booleanValue();
    if(additionalLot != null) additionalLotValue = additionalLot.booleanValue();
    if(floatData0 != null) floatData0Value = floatData0.doubleValue();
    if(floatData1 != null) floatData1Value = floatData1.doubleValue();
    if(intData0 != null) intData0Value = intData0.intValue();

    double result = 0;

    double unadjustedPrice = 0;
    if(basePriceValue > 0) unadjustedPrice = basePriceValue;
    else unadjustedPrice = unitPriceValue * Math.max(0, qtyValue - unitsIncludedValue);

    double addtnlUnadjustedPrice = unitPriceValue * Math.max(0, qtyValue - unitsIncludedValue);

    double minAdjustedPrice = 0;
    if(additionalVesselValue)
    {
      minAdjustedPrice = floatData1Value;
    }
    else
    {
      if(additionalLotValue)
      {
        if(intData0Value == 1)
        {
          minAdjustedPrice = floatData0Value;
        }
        else
        {
          minAdjustedPrice = Math.max(floatData0Value, addtnlUnadjustedPrice);
        }
      }
      else
      {
        minAdjustedPrice = Math.max(minPriceValue, unadjustedPrice);
      }
    }

    if(maxPriceValue > 0) result = Math.min(maxPriceValue, minAdjustedPrice);
    else result = minAdjustedPrice;

    return new Double(result);
  }
}
