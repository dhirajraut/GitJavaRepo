package com.intertek.calculator;

import com.intertek.entity.Expression;
import com.intertek.entity.LocationDiscount;
import com.intertek.util.Constants;

/**
 * The Standard Discounter
 *
 * Calculate the discount percentage for Service Charge.
 *
 */

public class StandardDiscounter implements Discounter
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

    String branchType = "";
    Expression expression = cee.getExpression();
    if(expression != null)
    {
      branchType = expression.getExpressionId().getBranchType();
    }

    if(Constants.ADM.equals(branchType))
    {
      // expenses, apply no discount
      return totalDiscount;
    }

    if(ld != null)
    {
      // get additional discount first
      totalDiscount = CalculatorUtil.addUpDiscount(ld.getCfgDiscountPercent(), totalDiscount);
    }

    Boolean isContractPriceObj = (Boolean)cr.getParameter(Constants.IS_CONTRACT_PRICE);
    boolean isContractPrice  = isContractPriceObj != null ? isContractPriceObj.booleanValue() : false;

    //Apply only additional discount for contract-specific prices
    if(isContractPrice)
    {
      return totalDiscount;
    }

    //if any LI discount found, sum up the LI discount and overall discount
    if(cr.getContract().getCfgContractId().getContractId().equals(
      cee.getContractExpression().getContractExpressionId().getContractId()
    ))
    {
      Double zoneSpecificDiscount = cee.getContractExpression().getCfgDiscountPercent();

      // LI overall discount >= 0
      if((zoneSpecificDiscount != null) && (zoneSpecificDiscount >= 0))
      {
        // apply li overall discount
        totalDiscount = CalculatorUtil.addUpDiscount(zoneSpecificDiscount, totalDiscount);

        return totalDiscount;
      }
    }

    if(Constants.OPS.equals(branchType))
    {
      if(ld != null)
      {
        Double opsZoneDiscount = ld.getOpsDiscountPercent();
        if((opsZoneDiscount != null) && (opsZoneDiscount >= 0))
        {
          // apply li overall discount
          totalDiscount = CalculatorUtil.addUpDiscount(opsZoneDiscount, totalDiscount);

          return totalDiscount;
        }
      }
    }
    else if(Constants.LAB.equals(branchType))
    {
      if(ld != null)
      {
        Double labZoneDiscount = ld.getLabDiscountPercent();
        if((labZoneDiscount != null) && (labZoneDiscount >= 0))
        {
          // apply li overall discount
          totalDiscount = CalculatorUtil.addUpDiscount(labZoneDiscount, totalDiscount);

          return totalDiscount;
        }
      }
    }

    // overall zone discount
    Double overallZoneDiscount = null;
    if(ld != null) overallZoneDiscount = ld.getZoneDiscountPercent();
    if((overallZoneDiscount != null) && (overallZoneDiscount >= 0))
    {
      // apply li overall discount
      totalDiscount = CalculatorUtil.addUpDiscount(overallZoneDiscount, totalDiscount);

      return totalDiscount;
    }

    if(Constants.OPS.equals(branchType))
    {
      Double opsOverallDiscount = cr.getContract().getOpsDiscountPercent();
      if((opsOverallDiscount != null) && (opsOverallDiscount >= 0))
      {
        // apply li overall discount
        totalDiscount = CalculatorUtil.addUpDiscount(opsOverallDiscount, totalDiscount);

        return totalDiscount;
      }
    }
    else if(Constants.LAB.equals(branchType))
    {
      Double labOverallDiscount = cr.getContract().getLabDiscountPercent();
      if((labOverallDiscount != null) && (labOverallDiscount >= 0))
      {
        // apply li overall discount
        totalDiscount = CalculatorUtil.addUpDiscount(labOverallDiscount, totalDiscount);

        return totalDiscount;
      }
    }

    if((cr.getContract().getCfgDiscountPercent() != null) && (cr.getContract().getCfgDiscountPercent() >= 0))
    {
      // apply li overall discount
      totalDiscount = CalculatorUtil.addUpDiscount(cr.getContract().getCfgDiscountPercent(), totalDiscount);

      return totalDiscount;
    }

    if( (ld != null) && (ld.getCfgDiscountPercent() != null) && (ld.getCfgDiscountPercent() >= 0))
    {
      return totalDiscount;
    }

    return null;
  }
}
