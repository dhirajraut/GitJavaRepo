package com.intertek.calculator;

import com.intertek.entity.LocationDiscount;
import com.intertek.util.Constants;

/**
 * The Test Discounter
 *
 * Calculate the discount percentage for Test Charge.
 *
 */

public class TestDiscounter implements Discounter
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
    Boolean isContractPriceObj = (Boolean)cr.getParameter(Constants.IS_CONTRACT_PRICE);
    Boolean newTestObj = (Boolean)cr.getParameter(Constants.NEW_TEST);

    Double zoneDiscnt = null;
    Double overallZoneDiscnt = null;
    boolean newTest  = newTestObj != null ? newTestObj.booleanValue() : false;
    boolean isContractPrice  = isContractPriceObj != null ? isContractPriceObj.booleanValue() : false;

    if(ld != null) zoneDiscnt = ld.getTestDiscountPercent();
    overallZoneDiscnt = cr.getContract().getTestDiscount();

    Double overallDiscnt = null;
    if(ld != null) overallDiscnt = ld.getCfgDiscountPercent();

    Double tempDiscountPct = null;

    if(newTest) return new Double(0);

    //Apply only additional discount for contract-specific prices
    if(isContractPrice)
    {
      totalDiscount = CalculatorUtil.addUpDiscount(overallDiscnt, totalDiscount);

      return totalDiscount;
    }

    //if any LI discount found, sum up the LI discount and overall discount
    if(ld != null && zoneDiscnt != null && zoneDiscnt.doubleValue() >= 0)
    {
      totalDiscount = CalculatorUtil.addUpDiscount(overallDiscnt, totalDiscount);
      totalDiscount = CalculatorUtil.addUpDiscount(zoneDiscnt, totalDiscount);

      return totalDiscount;
    }

    //if any zone discount present, sum up the zone discount and overall discount
    if(ld != null && ld.getZoneDiscountPercent() != null && ld.getZoneDiscountPercent().doubleValue() >= 0)
    {
      totalDiscount = CalculatorUtil.addUpDiscount(overallDiscnt, totalDiscount);
      totalDiscount = CalculatorUtil.addUpDiscount(ld.getZoneDiscountPercent(), totalDiscount);

      return totalDiscount;
    }

    //if the test/slate discount found, sum up the test/slate discount and overall discount
    if((overallZoneDiscnt != null) && (overallZoneDiscnt.doubleValue() >= 0))
    {
      totalDiscount = CalculatorUtil.addUpDiscount(overallDiscnt, totalDiscount);
      totalDiscount = CalculatorUtil.addUpDiscount(overallZoneDiscnt, totalDiscount);

      return totalDiscount;
    }

    //Return overall contract discount and overall discount
    totalDiscount = CalculatorUtil.addUpDiscount(overallDiscnt, totalDiscount);
    totalDiscount = CalculatorUtil.addUpDiscount(cr.getContract().getCfgDiscountPercent(), totalDiscount);

    if( ((overallDiscnt != null) && (overallDiscnt >= 0))
      || ((cr.getContract().getCfgDiscountPercent() != null) && (cr.getContract().getCfgDiscountPercent() >= 0)))
    {
      return totalDiscount;
    }

    return null;
  }
}
