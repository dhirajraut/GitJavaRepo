package com.intertek.calculator;

import java.text.MessageFormat;

import com.intertek.util.Constants;

/**
 * The Inspection Vessel Descriptor
 *
 * LINE ITEM DESCRIPTION
 * The variable ‘descriptionArgs’ (input parameters) along with the format string is used to generate the line item description.  The format string is retrieved from PS_ITSC_RB table.  The query first searches for contact-specific value, if not PB, or the default choice with ‘NONE’ as the contract id.  The PS_ITSC_CONTR_EXPN:EXPRESSION_ID value is used as the RB_KEY.  Please note for the lot level CONTR_EXPN entry, use the EXPRESSION_ID ‘Vessel-Price’.  The EXPRESSION_ID ‘Vessel-Insp’ can be ignored.  We will most likely remove the entry ‘Vessel-Insp’.
 *
 *
 * The variable ‘descriptionArgs’ is used as the input to create the line item description.  Please note that the following is the same as what is used at the lot level:
 * descriptionArgs
 * concatenate(rate_BasePrice, "^",
 *             Quantity, "^",
 *             IsTowPriceNotApplied (not used), "^",
 *             Quantity1, "^",
 *             isLotVessPr (this one is not used in LI description ),  "^",
 *             if(PRICE = rate_maxPrice, rate_maxPrice, 0), "^",
 *             Quantity2, "^",
 *             ADDITIONAL_LOT:agrade( PS_ITSC_CONTROL:OBJECT_NAME - ADDITIONAL_LOT  --- ‘1’ if yes, else 0) , "^",
 *             ADDITIONAL_VESSEL:multiplier (PS_ITSC_CONTROL:OBJECT_NAME - BOOLEAN_VAL_1--- ‘1’ if yes, else 0), "^",
 *             maxApplied (should be 1 if vessel max price is kicked-in), "^",
 *             rate_ContRefNo
 * )
 *
 * {9,choice,0#Additional Vessel Fee| 1#Vessel Maximum Price} {1,choice, 0#|1#, '{1,number,###,###,##0.###}' Long Tons} {3,choice, 0#|1#,  '{3,number,###,###,##0.###}' Barrels} {6,choice, 0#|1#,  '{6,number,###,###,##0.###}' Metric Tons}
 *
 * As for account code: productGroup: ‘MasterGroupId’ got picked at the lot level.  Since there is no product at the vessel level, the product from the lot with the biggest quantity needs to be chosen and assigned at the vessel level line item.
 *
 **/

public class InspectionVesselDescriptor implements Descriptor
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
      cr.getParameter(Constants.MAX_PRICE)
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

