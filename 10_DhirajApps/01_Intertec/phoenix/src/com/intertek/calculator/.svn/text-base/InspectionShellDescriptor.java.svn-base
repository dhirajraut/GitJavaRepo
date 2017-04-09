package com.intertek.calculator;

import java.text.MessageFormat;

import com.intertek.util.Constants;

/**
 * The Inspection Shell Descriptor
 *
 * LINE ITEMS:
 * In identifying the lot that makes up the primary vessel, if two or more lots in the same vessel or across vessels in a tow have the same price, choose the very first lot in the first vessel in the tow.  The calculated price of this lot (lets call this primary vessel lot) makes up for the price of the primary vessel.  The primary vessel LI carries all the product and account codes of the lot that makes up the primary vessel price (except for the quantity).  The line items for the vessel and lot follows:
 * A.  Primary vessel LI - Use the contractExpression:EXPRESSION_ID value of ‘shellPrimVessel’ as RB_KEY to fetch the LI mask (from PS_ITSC_RB where RB_KEY like '%shellPrimVessel%' and <jobFinishDate> between BEGIN_DATE and END_DATE).
 *
 * Please note that the price calculation variable additionalGrade = 0 so that the final PRICE variable will have the right value.
 *
 *
 * Use the following descriptionArgs to generate the primary vessel LI:
 * concatenate(rate_BasePrice, "^",
 *            <sum of all the lots in the given vessel> , "^",
 *             rate_unitPrice, "^",
 *             rate_untsIncld, "^",
 *             if(PRICE = rate_minPrice, rate_minPrice, 0), "^",
 *             if(PRICE = rate_maxPrice, rate_maxPrice, 0), "^",
 *             rate_fltData0, "^",
 *             PRICE, "^",
 *             isIncludedValue, "^",
 *             IS_VESS_PR, "^",
 *             rate_ContRefNo
 *            )
 *
 * B.  Primary vessel lot LI:  This lot corresponds to the lot that makes up the primary vessel price.   Use the contractExpression:EXPRESSION_ID value of ‘shellLot’ as RB_KEY to fetch the LI mask (from PS_ITSC_RB where RB_KEY = ‘shellLot' and <jobFinishDate> between BEGIN_DATE and END_DATE).
 *
 *
 * Use the following descriptionArgs to generate the LI for the lot that makes up the primary vessel price:
 * concatenate(rate_BasePrice, "^",
 *            <just the quantity that was entered at this lot> , "^",
 *             rate_unitPrice, "^",
 *             rate_untsIncld, "^",
 *             if(PRICE = rate_minPrice, rate_minPrice, 0), "^",
 *             if(PRICE = rate_maxPrice, rate_maxPrice, 0), "^",
 *             rate_fltData0, "^",
 *             PRICE, "^",
 *             isIncludedValue (default to 1), "^",
 *             IS_VESS_PR (default to 1), "^",
 *             rate_ContRefNo
 *            )
 * Please note that the lot will only have LI description and the prices will be zero for this lot.
 *
 *
 * C.  Included LI (Additional Included Grade):  Once we have identified the primary vessel lot (refer to ‘B. Primary vessel lot LI’), we can identify the included grades/lots with the following logic:
 *
 *
 * For the returned INSP_RATE row from ‘B’ above, fetch the following variables:
 * INSP_RATE:UNITS_INCLUDED and INSP_RATE:CONTRACT_REF
 * - if the UNITS_INCLUDED says 1, then only the lot that makes up the primary vessel (in other words ‘B’ above) is included, which is already accounted for.  No additional grade/lot to be included here.
 * - If the UNITS_INCLUDED says some number greater than 1, then the included grades/lots need to be identified.  The way to identify included additional included lots is to compare the CONTRACT_REF values from the primary vessel lot and other lots in the same vessel.  If the values match, then the lot is qualified to be included.  For example in the screen shot above with the whole price structure.  The primary vessel lot (product-1) had UNITS_INCLUDED of 3.  In comparison, the CONRACT_REF matched with lots: product-2, product-3, and product-4.  Since the UNITS_INCLUDED value was 3, only additional two lots (other than the primary lot) were included in the vessel price.
 * - If the UNITS_INCLUDED has a value of ‘0’, then all matching lots with the same CONTRACT_REF value within the same (primary) vessel are included.
 *
 * Use the contractExpression:EXPRESSION_ID value of ‘shellLot’ as RB_KEY to fetch the LI mask (from PS_ITSC_RB where RB_KEY = ‘shellLot' and <jobFinishDate> between BEGIN_DATE and END_DATE).
 * Use the following descriptionArgs to generate the LI for the lot that makes up for the included LI:
 * concatenate(rate_BasePrice, "^",
 *            <just the quantity that was entered at this lot> , "^",
 *             rate_unitPrice, "^",
 *             rate_untsIncld, "^",
 *             if(PRICE = rate_minPrice, rate_minPrice, 0), "^",
 *             if(PRICE = rate_maxPrice, rate_maxPrice, 0), "^",
 *             rate_fltData0 (default to 0), "^",
 *             PRICE, "^",
 *             isIncludedValue (default to 0), "^",
 *             IS_VESS_PR (default to 0), "^",
 *             rate_ContRefNo
 *            )
 *
 * Please note that lots will only have LI description and the prices will be zero for these lots.
 *
 * D.  Additional Grade Fee in Primary vessel:  The additional grade fee kicks in at both primary and additional vessels. Section C above covered the included grade/lot in the primary vessel.  Once the grades/lot do not satisfy the condition for both primary vessel LI (B above) and included LI (C above), the LI is qualified for additional grade fee.
 *
 * Please note that the price calculation variable additionalGrade = 1 so that the final PRICE variable will have the right value.
 *
 *
 * Use the contractExpression:EXPRESSION_ID value of ‘shellLot’ as RB_KEY to fetch the LI mask (from PS_ITSC_RB where RB_KEY = ‘shellLot' and <jobFinishDate> between BEGIN_DATE and END_DATE).
 * Use the following descriptionArgs to generate the LI for the lot that makes up the primary vessel price:
 * concatenate(rate_BasePrice, "^",
 *            <just the quantity that was entered at this lot> , "^",
 *             rate_unitPrice, "^",
 *             rate_untsIncld, "^",
 *             if(PRICE = rate_minPrice, rate_minPrice, 0), "^",
 *             if(PRICE = rate_maxPrice, rate_maxPrice, 0), "^",
 *             rate_fltData0 (real value), "^",
 *             PRICE, "^",
 *             isIncludedValue (default to 1), "^",
 *             IS_VESS_PR (default to 0), "^",
 *             rate_ContRefNo
 *            )
 *
 **/

public class InspectionShellDescriptor implements Descriptor
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

    Object qty = cr.getParameter(Constants.INSPECTION_QUANTITY);

    Object[] objs = new Object[] {
      cr.getParameter(Constants.BASE_PRICE),
      qty != null ? qty : new Integer(0),
      cr.getParameter(Constants.UNIT_PRICE),
      cr.getParameter(Constants.UNITS_INCLUDED),
      cr.getParameter(Constants.MIN_PRICE),
      cr.getParameter(Constants.MAX_PRICE),
      cr.getParameter(Constants.FLOAT_DATA0),
      cr.getParameter(Constants.PRICE_BEFORE_DISCOUNT),
      cr.getParameter(Constants.IS_INCLUDED_VALUE),
      cr.getParameter(Constants.IS_VESS_PR)
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

