package com.intertek.calculator;

import java.text.MessageFormat;

import com.intertek.entity.RB;
import com.intertek.util.Constants;

/**
 * A Slate Descriptor
 *
 * LINE ITEM DESCRIPTION
 * The variable ‘descriptionArgs’ is used as the input to create the line item description.
 * descriptionArgs
 *
 * concatenate(METHODOLOGY,"^",DESCRIPTION,"^",quantity,"^",PRICE,"^",CONTRACT_REF)
 *
 **/

public class SlateDescriptor implements Descriptor
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

    if(cr.getTotalPrice() == null) return null;

    RB rb = (RB)cr.getParameter(Constants.RB);
    if(rb == null) return null;

 // START : #130297
    /*Object[] objs = new Object[] {
      cr.getParameter(Constants.QUANTITY),
      cr.getParameter(Constants.UNIT_PRICE),
      cr.getParameter(Constants.SLATE_ID),
      cr.getParameter(Constants.DESCRIPTION)
    };*/
    Object[] objs = new Object[] {
      cr.getParameter(Constants.QUANTITY),
      String.valueOf(cr.getParameter(Constants.UNIT_PRICE)),
      cr.getParameter(Constants.SLATE_ID),
      cr.getParameter(Constants.DESCRIPTION)
    };
 // END : #130297
    
    String rbValue = rb.getRbValue();

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

