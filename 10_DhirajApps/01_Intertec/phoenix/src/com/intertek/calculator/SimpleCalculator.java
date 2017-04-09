package com.intertek.calculator;

import java.util.Map;

import com.intertek.util.Constants;

/**
 * The Simple Calculator
 *
 * Price: total price = unit price * quantity
 *
 **/
public class SimpleCalculator implements Calculator
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

    Double unitPrice = (Double)params.get(Constants.UNIT_PRICE);
    Integer qty = (Integer)params.get(Constants.QUANTITY);

    double unitPriceValue = 0;
    int qtyValue = 0;

    if(unitPrice != null) unitPriceValue = unitPrice.doubleValue();
    if(qty != null) qtyValue = qty.intValue();

    return new Double(unitPriceValue * qtyValue);
  }
}
