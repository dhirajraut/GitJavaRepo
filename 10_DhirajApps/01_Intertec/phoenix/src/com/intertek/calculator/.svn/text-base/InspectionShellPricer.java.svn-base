package com.intertek.calculator;

import com.intertek.util.Constants;

/**
 * A Pricing Component of Inspection Model - 3 (SHELL)
 *
 * How to identify model-3
 * If the following from PS_ITSC_RB table where RB_KEY = ‘<contractId>.ContractOperations’ fetches the following value “com.itscb.pscb.contractoperations.ShellContractOperations”.
 *
 * Selecting contractExpressionEntries:
 * From PS_ITSC_CONTR_EXPN where CFG_CONTRACT_ID = 'SHELL29260001' and ITSC_SERVICE in ('L-Insp', 'shellAddtnlVessel','shellPrimVessel')
 *
 * Controls:
 * As usual for other inspection, we have questions at both vessel and lot levels.  The new variable for this model is the question ‘Select Appropriate Tow’ at vessel level.
 *
 * Pricing calculation
 * The following describes pricing logic at a higher level:
 * - The overall structure can be described as Tow consisting of 0 to many vessels.  One vessel consisting of 0 to many lots.
 * - For the vessels, we could have the following vessel type line items:
 *   Primary vessel LI: Of the many vessels in a tow, only one vessel will have primary vessel LI.  Suppose the user has chosen ‘No Tow’, then each vessel with no associated tow will have the primary vessel LI.
 *
 * Additional vessel LI: Once the primary vessel is identified, the remaining vessels in the tow will have additional vessel LI.  If the chosen vessel does not belong to any Tow, then additional vessel LI will not be applicable.
 *
 *
 * - For the lots, we could have the following lot-level line items:
 *   Included LI (for corresponding vessel prices):  This lot level LI is included in the price of the vessel.  For both primary vessel and additional vessels, corresponding lots (one from primary and one from additional) will be included in the vessel’s price.
 *
 *
 * Included LI (Additional Included Grade): Once we choose the vessel price, we can determine how many additional lots are included in the vessel price.  Please note that these included lots will only be applicable for primary vessel and not for additional.  Such LIs will be shown as follows:
 *
 *
 * Additional Grade fee: The system automatically generates the additional grade fee.  Please note that unlike other models, the user will not have a control at lot level to indicate if a given lot is additional grade or not.  This type of LI appears at both primary and additional vessels.  The system determines that.  Here is an example of additional grade fee:
 *
 * Pricing logic:
 * To fetch the price, invoke the SP - ITSC_INSPECT_RATE_SP .  The parameters to pass on to are
 * getInspectionRate(contracted,  PricebookId, ‘Inspection’,
 * if(equals(selectedLocationInfo, "*"), "*", getTokenVal(selectedLocationInfo,1,true)),
 * productGrpStr (this value is the selected ‘GroupId’),
 * masterGroupId(this value is the selected ‘MasterGroupId’),
 * selectedVesselType,
 * "Y",
 * selectedQty (the quantity entered by the user),
 * jobFinishDate,
 * UOMCode (),
 * Default to 1)
 * )
 *
 * 1.  Determine the primary vessel:
 * - Every time a lot is added, calculate the price for all the lots across all the vessels in a given tow and choose the one with the highest price.  For every lot, use the following logic to calculate the price, compare, and identify the one with the highest price.
 *
 * Assign the following variables when trying to determine the primary vessel and lot:
 * additionalGrade = 0
 *
 * PRICE:
 * - PRICE = if(UseMaximum, min(rate_maxPrice, MinAdjustedPrice), MinAdjustedPrice)
 * - UseMaximum = if(rate_maxPrice > 0, true, false)
 * - MinAdjustedPrice = if(UseMinimum, max(rate_minPrice, UnadjustedPrice), UnadjustedPrice)
 * - UseMinimum = if(rate_minPrice > 0, true, false)
 * - UnadjustedPrice = if(additionalGrade > 0, rate_fltData0, rate_BasePrice + rate_unitPrice * max(0, Quantity))
 *
 * LINE ITEMS:
 * In identifying the lot that makes up the primary vessel, if two or more lots in the same vessel or across vessels in a tow have the same price, choose the very first lot in the first vessel in the tow.  The calculated price of this lot (lets call this primary vessel lot) makes up for the price of the primary vessel.  The primary vessel LI carries all the product and account codes of the lot that makes up the primary vessel price (except for the quantity).  The line items for the vessel and lot follows:
 * A.  Primary vessel LI – Use the contractExpression:EXPRESSION_ID value of ‘shellPrimVessel’ as RB_KEY to fetch the LI mask (from PS_ITSC_RB where RB_KEY like '%shellPrimVessel%' and <jobFinishDate> between BEGIN_DATE and END_DATE).
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
 * 2.  Determine the additional vessel price:
 * - Please refer to the section ‘Additional vessel LI’ above.  Basically within a tow, any non primary vessel is an additional vessel.  For every additional vessel in the tow, within the additional vessel, we need to identify the lot with maximum additional vessel fee.  For every lot within the additional vessel, use the following logic to calculate the price, compare, and identify the one with the highest additional vessel price.
 *
 * For every lot within the additional vessel, compare the PRICE = rate_fltData1
 *
 * The lot with the biggest price will make up the additional vessel lot price.
 *
 *
 * E.  Additional Vessel Fee LI at vessel level:
 *
 * Use the contractExpression:EXPRESSION_ID value of ‘shellPrimVessel’ as RB_KEY to fetch the LI mask (from PS_ITSC_RB where RB_KEY like '%'shellAddtnlVessel'%' and <jobFinishDate> between BEGIN_DATE and END_DATE).
 *
 * The RB value reads as follows "Additional Vessel Cargo Inspection Fee".  We do not need any descriptionArgs for the this LI.
 *
 *
 * F.  Additional Vessel lot LI:  Please follow the same logic as in section "B.  Primary vessel lot LI"
 *
 * G.  Additional Grade Fee in secondary vessel - Unlike included grades in primary vessel, there is no such included items in the additional vessels.  Other than the lot that makes up additional vessel LI (section F above), every other lot is charged the additional grade price.  As for the logic, follow the same as in the section "D.  Additional Grade Fee in Primary vessel"
 *
 **/

public class InspectionShellPricer extends InspectionPricer
{
  /**
   * Returns an InspectionShellCalculator.
   *
   * @return a Calculator of instance InspectionShellCalculator.
   */
  public Calculator getCalculator()
  {
    return CalculatorFactory.getCalculator(Constants.CALCULATOR_INSPECTION_SHELL);
  }
}
