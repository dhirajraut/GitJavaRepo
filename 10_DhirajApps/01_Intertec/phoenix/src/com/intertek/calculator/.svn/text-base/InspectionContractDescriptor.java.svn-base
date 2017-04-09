package com.intertek.calculator;

import java.text.MessageFormat;

import com.intertek.util.Constants;

/**
 * The Inspection Contract Descriptor
 *
 * LINE ITEM DESCRIPTION
 * concatenate(rate_BasePrice, "^",
 *             Quantity (default to 1), "^",
 *             rate_unitPrice, "^",
 *             rate_untsIncld, "^",
 *             if(PRICE = rate_minPrice, rate_minPrice, 0), "^",
 *             if(PRICE = rate_maxPrice, rate_maxPrice, 0), "^",
 *             rate_fltData0, "^",
 *             rate_intData2, "^",
 *             rate_intData3, "^",
 *             rate_ContRefNo
 * )
 *
 * As for account code: productGroup: ‘MasterGroupId’ got picked at the lot level.  Since there is no product at the vessel level, the product from the lot with the biggest quantity needs to be chosen and assigned at the job max line item.
 *
 **/

public class InspectionContractDescriptor implements Descriptor
{
  /**
   * Using input <code>CalculatorRequest</code> to format the line description.
   *
   * @param cr the <code>CalculatorRequest</code> contains the parameters used in the descriptor.
   * @return the formatted line description as a String.
   */
  public String formatLineDescription(CalculatorResult cr)
  {
    if(cr == null) return null;

    ContractExpressionExt cee = cr.getContractExpressionExt();
    if(cee == null) return null;
    if(cr.getTotalPrice() == null) return null;

    Object[] objs = new Object[] {
      cr.getParameter(Constants.BASE_PRICE),
      new Integer(1),  // quantity (default to 1)
      cr.getParameter(Constants.UNIT_PRICE),
      cr.getParameter(Constants.UNITS_INCLUDED),
      cr.getParameter(Constants.MIN_PRICE),
      cr.getParameter(Constants.MAX_PRICE),
      cr.getParameter(Constants.FLOAT_DATA0),
      cr.getParameter(Constants.INT_DATA2),
      cr.getParameter(Constants.INT_DATA3)
    };

    String rbValue = cee.getContractExpression().getVisibility();

    MessageFormat format = new MessageFormat(rbValue);
    String result = format.format(objs);

    String contractRefNo = (String)cr.getParameter(Constants.CONTRACT_REF_NO);
    if((contractRefNo != null) && !"0".equals(contractRefNo))
    {
      return contractRefNo + " - " + result;
    }

    return result;
  }
}

