package com.intertek.calculator;

import java.text.MessageFormat;

import com.intertek.util.Constants;

/**
 * The Inspection Model-2 Descriptor
 *
 * LINE ITEM DESCRIPTION
 * The logic is the same as that in model-1 for lot level prices.  The only difference is in line item description.  Currently, the same variable ‘descriptionArgs’ is used as the input to create the line items for both lot level-level line item and vessel-level line item:
 *
 * concatenate(rate_BasePrice, "^",
 *             Quantity, "^",
 *             IsTowPriceNotApplied (not used), "^",
 *             Quantity1, "^",
 *             isLotVessPr (this one is not used in LI description ),  "^",
 *             if(PRICE = rate_maxPrice, rate_maxPrice, 0), "^",
 *             Quantity2, "^",
 *             ADDITIONAL_LOT:agrade( PS_ITSC_CONTROL:OBJECT_NAME - ADDITIONAL_LOT  --- ‘1’ if yes, else 0) , "^",
 *             ADDITIONAL_VESSEL:multiplier (PS_ITSC_CONTROL:OBJECT_NAME - BOOLEAN_VAL_1--- ‘1’ if yes, else 0), "^",
 *             maxApplied (this one is not used in LI description), "^",
 *             rate_ContRefNo
 *             min price 
 *             unit price
 * )
 *
 * The variable ‘descriptionArgs’ (input parameters) along with the format string is used to generate the line item description.  The format string is retrieved from PS_ITSC_RB table.  The query first searches for contact-specific value, if not PB, or the default choice with ‘NONE’ as the contract id.  The PS_ITSC_CONTR_EXPN:EXPRESSION_ID value is used as the RB_KEY.  Please note for the lot level CONTR_EXPN entry, use the EXPRESSION_ID ‘Inspection-Price’.  The EXPRESSION_ID ‘L-Inspection’ can be ignored.  We will most likely remove the entry ‘L-Inspection’.
 *
 **/

public class InspectionModel2Descriptor implements Descriptor
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

    Boolean additionalLot = (Boolean)cr.getParameter(Constants.ADDITIONAL_LOT);
    Boolean additionalVessel = (Boolean)cr.getParameter(Constants.ADDITIONAL_VESSEL);
    Object qty = cr.getParameter(Constants.QUANTITY);
    Object qty1 = cr.getParameter(Constants.QUANTITY1);
    Object qty2 = cr.getParameter(Constants.QUANTITY2);

    Object[] objs = new Object[] {
      cr.getParameter(Constants.BASE_PRICE),
      qty != null ? qty : new Integer(0),
      new Integer(0), // not used
      qty1 != null ? qty1 : new Integer(0),
      new Integer(0), // not used
      cr.getParameter(Constants.MAX_PRICE),
      qty2 != null ? qty2 : new Integer(0),
      additionalLot != null && additionalLot.equals(true) ? new Integer(1) : new Integer(0),
      additionalVessel != null && additionalVessel.equals(true) ? new Integer(1) : new Integer(0),
      new Integer(0), // not used
      cr.getParameter(Constants.MIN_PRICE),
      cr.getParameter(Constants.UNIT_PRICE)
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

