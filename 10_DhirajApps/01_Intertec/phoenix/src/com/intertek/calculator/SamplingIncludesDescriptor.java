package com.intertek.calculator;

import java.text.MessageFormat;

import com.intertek.util.Constants;

/**
 * The Sampling Includes Descriptor
 *
 * LINE ITEM DESCRIPTION
 * The variable ‘descriptionArgs’ is used as the input to create the line item description.
 * descriptionArgs
 *
 * concatenate(rate_BasePrice, "^",
 *             noOfQuarts, "^",
 *             rate_unitPrice, "^",
 *             if(rate_untsIncld > 0, rate_untsIncld, rate_intData0), "^",
 *             if(PRICE = rate_minPrice, rate_minPrice, 0), "^",
 *             if(PRICE = rate_maxPrice, rate_maxPrice, 0), "^",
 *             rate_intData2, "^",
 *             rate_intData3, "^",
 *             noOfContainers, "^",
 *             includedSamples, "^",
 *             rate_ContRefNo
 * )
 *
 * The values ‘stringVal1’, ‘stringVal2’, and ‘stringVal3’ are user-entered.
 *
 * The variable ‘descriptionArgs’ (input parameters) along with the format string is used to generate the line item description.  The format string is retrieved from PS_ITSC_RB table.  The query first searches for contact-specific value, if not PB, or the default choice with ‘NONE’ as the contract id.  The PS_ITSC_CONTR_EXPN:EXPRESSION_ID value is used as the RB_KEY.
 *
 **/

public class SamplingIncludesDescriptor implements Descriptor
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

    Object noOfContainers = cr.getParameter(Constants.noOfContainers);
    Object includedSamples = cr.getParameter(Constants.includedSamples);

    Object[] objs = new Object[] {
      cr.getParameter(Constants.BASE_PRICE),
      cr.getParameter(Constants.noOfQuarts),
      cr.getParameter(Constants.UNIT_PRICE),
      cr.getParameter(Constants.UNITS_INCLUDED),
      cr.getParameter(Constants.MIN_PRICE),
      cr.getParameter(Constants.MAX_PRICE),
      cr.getParameter(Constants.INT_DATA2),
      cr.getParameter(Constants.INT_DATA3),
      noOfContainers != null ? noOfContainers : new Integer(0),
      includedSamples != null ? includedSamples : new Integer(0)
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

