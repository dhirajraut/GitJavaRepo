package com.intertek.calculator;

import com.intertek.util.Constants;

/**
 * A Pricing Component of Inspection Model - 2 (Vessel Level)
 *
 * The Inspections Model 2
 *
 * Selecting the right CONTR_EXPN entry:
 * The following query will tell us as which model to use -? PS_ITSC_RB where (<jobFinishDate> between BEGIN_DATE and END_DATE and RB_VALUE = ‘<contractId>. ContractOperations’) for MODEL-1.
 * - If the above returns null, we can assume that MODEL-1 will be used.  In which case we look for only one entry in PS_ITSC_CONTR_EXPN where ITSC_SERVICE=’L-Insp’ and <date> between BEGIN_DATE and END_DATE with the given contract ID.  Id any found use it.  Else check for an entry with the pricebookId.
 * - If the above returns a value, we can assume that MODEL-2 will be used.  In which case, we look entries in PS_ITSC_CONTR_EXPN where ITSC_SERVICE in ('Vessel-Insp', 'L-Insp', ‘Tow-Max’) and <date> between BEGIN_DATE and END_DATE with the given contract ID.  If any found use it.  Else, try the same with pricebookId.
 *
 *
 * Overall, we have three different inspection models:
 * 1)  MODEL-1
 * - The above-mentioned inspections model can be loosely defined as model-1.
 * If the following query returns only one row with the COMPONENT_TYPE as either ‘CONTRACT_INSPECTION’ or ‘PB_L-INSPECTION’, then the model-1 is applicable:
 * - from PS_ITSC_CONTR_EXPN where ITSC_SERVICE in ('Vessel-Insp', 'L-Insp', ‘Tow-Max’) and <date> between BEGIN_DATE and END_DATE
 * - Alternatively, the following query will not return any row:
 * PS_ITSC_RB where (<jobFinishDate> between BEGIN_DATE and END_DATE and RB_VALUE = ‘<contractId>. ContractOperations’) for MODEL-1.
 * 2) MODEL-2
 * If the following query returns 4-5 rows, then the model-2 is applicable:
 * - from PS_ITSC_CONTR_EXPN where ITSC_SERVICE in ('Vessel-Insp', 'L-Insp', ‘Tow-Max’) and <date> between BEGIN_DATE and END_DATE
 * - As of today, 4 or 5  rows are returned.  But we can potentially reduce it to 2 - 3 – rows when implemented in PHNX.
 * - Please note that for those that use MODEL-2, ~95% of the times 4 rows are returned: 2 with ITSC_SERVICE= 'Vessel-Insp', 2 with ITSC_SERVICE= 'L-Insp'.  The rest ~5% of the time, we will have the fifth with ITSC_SERVICE= 'Tow-Max'.
 * - Alternatively, the following query will return one row.  If the query from S_ITSC_RB where (<jobFinishDate> between BEGIN_DATE and END_DATE and RB_VALUE = ‘<contractId>. ContractOperations’), model-2 is applicable.
 *
 * Overview:
 * For model-2, we can have line items appearing at three levels:
 * - Lot level:  The pricing logic at the lot level line item is the same as model-1, except for lineItemDescription and arguments which we are discussed later.
 * - Vessel level:   There could be 2 types of vessel level line items:
 * o 1.Vessel max – this line item appears when the vessel max price kicks-in.  That is when the sum of all the lot level price exceeds the vessel maximum price.  The details are discussed below.
 * - Job max: This line item would only kick-in if any row is found in  PS_ITSC_CONTR_EXPN where ITSC_SERVICE in (‘Tow-Max’).  If found, this line item will appear if sum of all inspection-related line items exceed the job max.  For example, there could be a couple of vessels with vessel maximum price.  If the sum of all vessel maximum prices exceed job max, then the job max line item should kick-in.
 *
 *
 * Pricing-logic:
 *
 * Lot-level:
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
 * )
 *
 * The variable ‘descriptionArgs’ (input parameters) along with the format string is used to generate the line item description.  The format string is retrieved from PS_ITSC_RB table.  The query first searches for contact-specific value, if not PB, or the default choice with ‘NONE’ as the contract id.  The PS_ITSC_CONTR_EXPN:EXPRESSION_ID value is used as the RB_KEY.  Please note for the lot level CONTR_EXPN entry, use the EXPRESSION_ID ‘Inspection-Price’.  The EXPRESSION_ID ‘L-Inspection’ can be ignored.  We will most likely remove the entry ‘L-Inspection’.
 *
 * Vessel-level:
 *
 * If the query from PS_ITSC_RB where (<jobFinishDate> between BEGIN_DATE and END_DATE and RB_VALUE = ‘<contractId>. ContractOperations’) RETURNS any value, we can safely assume that MODEL-2 will be used:
 *
 * Fetch the contractExpression value using the ITSC_SERVICE=’Vessel-Insp’.
 * To fetch the price, invoke the SP - ITSC_INSPECT_RATE_SP .  The parameters to pass on to are
 * getInspectionRate(contracted,  PricebookId, ‘Inspection’,
 *   if(equals(selectedLocationInfo, "*"), "*", getTokenVal(selectedLocationInfo,1,true)),
 *   productGrpStr (this value is the selected ‘GroupId’ for the lot with the biggest qty),
 *   masterGroupId(this value is the selected ‘MasterGroupId’),
 *   selectedVesselType,
 *   "Y",
 *   selectedQty (the quantity entered by the user.  Please note that the user could have entered three quantities in three different UOMs in the lot screen shot.  But we got to pass on the quantity that was ‘required’),
 *   jobFinishDate,
 *   UOMCode (),
 *   Default to 1)
 * )
 *
 * Once you retrieve the result (one row) from the SP, the logic to calculate the price follows:
 * - if(maxPrice > 0, maxPrice, TotalLotPrices)
 * - maxPrice = if (vesselApplied > 0, min(rate_maxPrice, TotalLotPrices + addtnlVesselPr), 0)
 * - vesselApplied =  if(rate_maxPrice > 0, if (TotalLotPrices> rate_maxPrice, 1, 0)
 * - TotalLotPrices = sum of all lot prices in a vessel.
 * - Please note that if the vessel maximum price kicks in, all the lot level prices should be 0.
 *
 * - Note that rate_XXX (ex: rate_minPrice maps to PS_ITSC_INSP_RATE:MINIMUM_PRICE) directly map to a data element from the retrieved from the INSP_RATe table.
 * - The multipliers and escalators are applied on the individual rates.  Refer to section ‘Multipliers & Escalators’.  The ‘OPS_LAB_Factor’ are refered to as ‘multipliers’ in the section ‘Multipliers & Escalators’
 * - All prices should be rounded to four decimal places.
 * - The ‘CurrencyMultiplier’ is the conversion rate from one currency to the other currency.  The table ‘PS_RT_RATE_TBL’ holds the conversion rate.  Depending on the currency of the contract, the user will have choice upfront to choose the conversion currency.  Refer to the drop-down ‘Transaction Currency – Multiplier’ under ‘’Add Customers’ tab in the mock-up.  You need to add a customer to view the above.
 *
 * Here is an example for the value ‘rate_minPrice’:
 * rate_minPrice:
 * round(nativeRateMinPrice * CurrencyMultiplier, -4)
 *
 * nativeRateMinPrice:
 * if(isContractPrice > 0, (minPriceFromResult) * OPS_LAB_Factor) * annual_escalator,
 *   (minPriceFromResult) * OPS_LAB_Factor
 * )
 *
**/

public class InspectionVesselPricer extends InspectionPricer
{
  /**
   * Returns an InspectionVesselCalculator.
   *
   * @return a Calculator of instance InspectionVesselCalculator.
   */
  public Calculator getCalculator()
  {
    return CalculatorFactory.getCalculator(Constants.CALCULATOR_INSPECTION_VESSEL);
  }

  /**
   * Returns an InspectionDiscounter.
   *
   * @param isContractPrice (not used here).
   * @return a Discounter of instance InspectionDiscounter.
   */
  public Discounter getDiscounter(boolean isContractPrice)
  {
    return DiscounterFactory.getDiscounter(Constants.CALCULATOR_INSPECTION_DISCOUNTER);
  }
}
