package com.intertek.calculator;

import java.util.Map;

import com.intertek.util.Constants;

/**
 * The Inspection Shell Calculator (used in model - 3)
 *
 * PRICE:
 * - PRICE = if(UseMaximum, min(rate_maxPrice, MinAdjustedPrice), MinAdjustedPrice)
 * - UseMaximum = if(rate_maxPrice > 0, true, false)
 * - MinAdjustedPrice = if(UseMinimum, max(rate_minPrice, UnadjustedPrice), UnadjustedPrice)
 * - UseMinimum = if(rate_minPrice > 0, true, false)
 * - UnadjustedPrice = if(additionalGrade > 0, rate_fltData0, rate_BasePrice + rate_unitPrice * max(0, Quantity))
 *
 **/

public class InspectionShellCalculator implements Calculator
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
    Double qty = (Double)params.get(Constants.INSPECTION_QUANTITY);
    Double floatData0 = (Double)params.get(Constants.FLOAT_DATA0);
    Double floatData1 = (Double)params.get(Constants.FLOAT_DATA1);
    Integer additionalGrade = (Integer)params.get(Constants.ADDITIONAL_GRADE);

    double basePriceValue = 0;
    double unitPriceValue = 0;
    double minPriceValue = 0;
    double maxPriceValue = 0;
    double qtyValue = 0;
    double floatData0Value = 0;
    double floatData1Value = 0;
    int additionalGradeValue = 0;

    if(basePrice != null) basePriceValue = basePrice.doubleValue();
    if(unitPrice != null) unitPriceValue = unitPrice.doubleValue();
    if(minPrice != null) minPriceValue = minPrice.doubleValue();
    if(maxPrice != null) maxPriceValue = maxPrice.doubleValue();
    if(qty != null) qtyValue = qty.doubleValue();
    if(floatData0 != null) floatData0Value = floatData0.doubleValue();
    if(floatData1 != null) floatData1Value = floatData1.doubleValue();
    if(additionalGrade != null) additionalGradeValue = additionalGrade.intValue();

    if(additionalGradeValue == 2) return new Double(floatData1);

    double unadjustedPrice = 0;
    if(additionalGradeValue > 0) unadjustedPrice = floatData0;
    else unadjustedPrice = basePriceValue + unitPriceValue * Math.max(0, qtyValue);

    double minAdjustedPrice = 0;
    if(minPriceValue > 0) minAdjustedPrice = Math.max(minPriceValue, unadjustedPrice);
    else minAdjustedPrice = unadjustedPrice;

    double result = 0;
    if(maxPriceValue > 0) result = Math.min(maxPriceValue, minAdjustedPrice);
    else result = minAdjustedPrice;

    return new Double(result);
  }
}
