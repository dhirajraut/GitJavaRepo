package com.intertek.calculator;

import com.intertek.entity.LocationDiscount;
import com.intertek.util.Constants;

/**
 * The Inspepction Discounter
 *
 * Calculate the discount percentage for Inspection Charge.
 *
 */

public class InspectionDiscounter implements Discounter
{
  /**
   * Using input <code>CalculatorRequest</code> and and <code>LocationDiscount</code> to calculate the total discount percentage.
   *
   * @param cr the <code>CalculatorRequest</code> contains the parameters used in the discounter.
   * @param ld the <code>LocationDiscount</code> contains the discount information.
   * @return the total discount percentage.
   */
  public Double getDiscount(
    CalculatorRequest cr,
    LocationDiscount ld
  )
  {
    Double totalDiscount = new Double(0);

    ContractExpressionExt cee = (ContractExpressionExt)cr.getParameter(Constants.CONTRACT_EXPRESSION_EXT);
    if(cee == null) return null;

    Boolean isContractPriceObj = (Boolean)cr.getParameter(Constants.IS_CONTRACT_PRICE);
    boolean isContractPrice  = isContractPriceObj != null ? isContractPriceObj.booleanValue() : false;

    if(isContractPrice)
    {
      // apply additional discount
      if( (ld != null) && (ld.getCfgDiscountPercent() != null) && (ld.getCfgDiscountPercent() >= 0))
      {
        totalDiscount = CalculatorUtil.addUpDiscount(ld.getCfgDiscountPercent(), totalDiscount);
        return totalDiscount;
      }

      return null;
    }

    if(ld != null)
    {
      totalDiscount = CalculatorUtil.addUpDiscount(ld.getCfgDiscountPercent(), totalDiscount);

      Double zoneInspectionDiscount = ld.getInspectionDiscountPercent();
      if((zoneInspectionDiscount != null) && (zoneInspectionDiscount >= 0))
      {
        totalDiscount = CalculatorUtil.addUpDiscount(zoneInspectionDiscount, totalDiscount);

        return totalDiscount;
      }

      Double zoneDiscountPercent = ld.getZoneDiscountPercent();
      if((zoneDiscountPercent != null) && (zoneDiscountPercent >= 0))
      {
        totalDiscount = CalculatorUtil.addUpDiscount(zoneDiscountPercent, totalDiscount);

        return totalDiscount;
      }
    }

    if(cr.getContract().getCfgContractId().getContractId() != null)
    {
      Double overallInspectionDiscount = cr.getContract().getInspectionDiscountPercent();
      if((overallInspectionDiscount != null) && (overallInspectionDiscount >= 0))
      {
        totalDiscount = CalculatorUtil.addUpDiscount(overallInspectionDiscount, totalDiscount);

        return totalDiscount;
      }

      Double overallDiscount = cr.getContract().getCfgDiscountPercent();
      if((overallDiscount != null) && (overallDiscount >= 0))
      {
        totalDiscount = CalculatorUtil.addUpDiscount(overallDiscount, totalDiscount);

        return totalDiscount;
      }
    }

    if( (ld != null) && (ld.getCfgDiscountPercent() != null) && (ld.getCfgDiscountPercent() >= 0))
    {
      return totalDiscount;
    }

    return null;
  }
}
