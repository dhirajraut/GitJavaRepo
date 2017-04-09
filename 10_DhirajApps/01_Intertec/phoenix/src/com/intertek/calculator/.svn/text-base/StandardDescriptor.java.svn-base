package com.intertek.calculator;

import java.text.MessageFormat;

import com.intertek.util.Constants;

/**
 * A Standard Descriptor
 *
 * LINE ITEM DESCRIPTION
 * The variable ‘descriptionArgs’ is used as the input to create the line item description.
 * descriptionArgs
 * concatenate(rate_BasePrice, "^",
 *             Quantity, "^",
 *             rate_unitPrice, "^",
 *             rate_untsIncld, "^",
 *             if(PriceWithOutPercentage = rate_minPrice, rate_minPrice, 0), "^",
 *             if(PriceWithOutPercentage = rate_maxPrice, rate_maxPrice, 0), "^",
 *             rate_fltData0, "^",
 *             rate_intData2, "^",
 *             rate_intData3, "^",
 *             stringVal1, "^",
 *             stringVal2, "^",
 *             stringVal3, "^",
 *             percentage, "^",
 *             rate_ContRefNo
 * )
 * The values ‘stringVal1’, ‘stringVal2’, and ‘stringVal3’ are user-entered.
 *
 * The variable ‘descriptionArgs’ (input parameters) along with the format string is used to generate the line item description.  The format string is retrieved from PS_ITSC_RB table.  The query first searches for contact-specific value, if not PB, or the default choice with ‘NONE’ as the contract id.  The PS_ITSC_CONTR_EXPN:EXPRESSION_ID value is used as the RB_KEY.
 *
 **/

public class StandardDescriptor implements Descriptor
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
      cr.getParameter(Constants.QUANTITY),
      cr.getParameter(Constants.UNIT_PRICE),
      cr.getParameter(Constants.UNITS_INCLUDED),
      cr.getParameter(Constants.MIN_PRICE),
      cr.getParameter(Constants.MAX_PRICE),
      cr.getParameter(Constants.FLOAT_DATA0),
      cr.getParameter(Constants.INT_DATA2),
      cr.getParameter(Constants.INT_DATA3),
      cr.getParameter(Constants.STRING_VAL1),
      cr.getParameter(Constants.STRING_VAL2),
      cr.getParameter(Constants.STRING_VAL3),
      cr.getParameter(Constants.PERCENTAGE)
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
