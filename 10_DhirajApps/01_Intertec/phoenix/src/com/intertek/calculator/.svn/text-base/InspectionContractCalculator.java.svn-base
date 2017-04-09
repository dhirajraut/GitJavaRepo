package com.intertek.calculator;

import java.util.Map;

import com.intertek.util.Constants;

/**
 * The Inspection Contract Calculator (contract level model - 2)
 *
 * The logic to calculate the price follows:
 * - if(MaxNotApplied > 0, 0, min(rate_maxPrice, TotalVesselPrices))
 * - MaxNotApplied = if (TotalVesselPrices > rate_maxPrice, 0, 1)
 * - TotalVesselPrices = sum of all applicable prices across all vessels and lots.  Lets say in an example, we have 2 vessels.  The vessel-1 has a vessel max price kicked in and all the lots underneath it are with 0 prices since vessel max price kicked-in.  The vessel-2 has two lots with appropriate prices.  In this case, the TotalVesselPrices would be the sum of vessel max price from vessel-1 and the two lots from vessel-2.
 *
 */

public class InspectionContractCalculator implements Calculator
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
    Double totalVesselInspPrice = (Double)params.get(Constants.TOTAL_VESSEL_INSP_PRICE);

    double maxPriceValue = 0;
    double totalVesselInspPriceValue = 0;

    if(maxPrice != null) maxPriceValue = maxPrice.doubleValue();
    if(totalVesselInspPrice != null) totalVesselInspPriceValue = totalVesselInspPrice.doubleValue();

    double result = 0;

    boolean maxNotApplied = false;
    if(totalVesselInspPriceValue > maxPriceValue) maxNotApplied = true;

    if(maxNotApplied) result = Math.min(maxPriceValue, totalVesselInspPriceValue);

    return new Double(result);
  }
}
