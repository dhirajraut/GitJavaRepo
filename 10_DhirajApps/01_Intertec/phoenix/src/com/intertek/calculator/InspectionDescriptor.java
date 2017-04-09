package com.intertek.calculator;

import java.text.MessageFormat;

import com.intertek.util.Constants;

/**
 * The Inspection Descriptor
 *
 * LINE ITEM DESCRIPTION
 * The variable ‘descriptionArgs’ is used as the input to create the line item description.
 * descriptionArgs
 * concatenate(if(PRICE = rate_BasePrice, rate_BasePrice, 0), "^",
 *             Quantity, "^",
 *             rate_unitPrice, "^",
 *             Quantity1, "^",
 *             if(PRICE = rate_minPrice, rate_minPrice, 0), "^",
 *             if(PRICE = rate_maxPrice, rate_maxPrice, 0), "^",
 *             if(PRICE = rate_fltData0, rate_fltData0, 0), "^",
 *             if(PRICE = rate_unitPrice * selectedQty, 1, 0), "^",
 *             Quantity2, "^",
 *             ADDITIONAL_LOT:agrade (‘1’ if yes, else 0), "^",
 *             ADDITIONAL_VESSEL:avessel (‘1’ if yes, else 0), "^",
 *             rate_ContRefNo
 * )
 *
 * The variable ‘descriptionArgs’ (input parameters) along with the format string is used to generate the line item description.  The format string is retrieved from PS_ITSC_RB table.  The query first searches for contact-specific value, if not PB, or the default choice with ‘NONE’ as the contract id.  The PS_ITSC_CONTR_EXPN:EXPRESSION_ID value is used as the RB_KEY.
 *
 **/

public class InspectionDescriptor implements Descriptor
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

    Boolean selectedQtyPrice = (Boolean)cr.getParameter(Constants.SELECTED_QTY_PRICE);
    Boolean additionalLot = (Boolean)cr.getParameter(Constants.ADDITIONAL_LOT);
    Boolean additionalVessel = (Boolean)cr.getParameter(Constants.ADDITIONAL_VESSEL);
    Object qty = cr.getParameter(Constants.QUANTITY);
    Object qty1 = cr.getParameter(Constants.QUANTITY1);
    Object qty2 = cr.getParameter(Constants.QUANTITY2);

    // START : #130297 
    /*Object[] objs = new Object[] {
      cr.getParameter(Constants.BASE_PRICE_0),
      qty != null ? qty : new Integer(0),
      cr.getParameter(Constants.UNIT_PRICE),
      qty1 != null ? qty1 : new Integer(0),
      cr.getParameter(Constants.MIN_PRICE),
      cr.getParameter(Constants.MAX_PRICE),
      cr.getParameter(Constants.FLOAT_DATA0),
      selectedQtyPrice != null && selectedQtyPrice.equals(true) ? new Integer(1) : new Integer(0),
      qty2 != null ? qty2 : new Integer(0),
      additionalLot != null && additionalLot.equals(true) ? new Integer(1) : new Integer(0),
      additionalVessel != null && additionalVessel.equals(true) ? new Integer(1) : new Integer(0)
    };*/
    
    Object[] objs = new Object[] {
  	      cr.getParameter(Constants.BASE_PRICE_0),
  	      qty != null ? qty : new Integer(0),
  	      String.valueOf(cr.getParameter(Constants.UNIT_PRICE)),
  	      qty1 != null ? qty1 : new Integer(0),
  	      cr.getParameter(Constants.MIN_PRICE),
  	      cr.getParameter(Constants.MAX_PRICE),
  	      cr.getParameter(Constants.FLOAT_DATA0),
  	      selectedQtyPrice != null && selectedQtyPrice.equals(true) ? new Integer(1) : new Integer(0),
  	      qty2 != null ? qty2 : new Integer(0),
  	      additionalLot != null && additionalLot.equals(true) ? new Integer(1) : new Integer(0),
  	      additionalVessel != null && additionalVessel.equals(true) ? new Integer(1) : new Integer(0)
  	    };
// END : #130297 

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

