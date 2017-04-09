package com.intertek.calculator;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.intertek.entity.Expression;
import com.intertek.entity.InspectionRate;
import com.intertek.entity.LocationDiscount;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.CalculatorService;
import com.intertek.util.Constants;
import com.intertek.util.NumberUtil;

/**
 * A Pricing Component of Inspection Model - 1 and Model -2 (Lot level)
 *
 * The pricing scenarios could range from simple to complex cases as follows:
 * If the query (<jobFinishDate> between BEGIN_DATE and END_DATE and RB_VALUE = ‘<contractId>. ContractOperations’) fails to return any value, proceed to ‘A’ below.
 * A)  Fetch the contractExpression value using the ITSC_SERVICE=’L-Insp’.
 * To fetch the price, invoke the SP - ITSC_INSPECT_RATE_SP .  The parameters to pass on to are
 * getInspectionRate(contracted,  PricebookId, ‘L-Insp’,
 *   if(equals(selectedLocationInfo, "*"), "*", getTokenVal(selectedLocationInfo,1,true)),
 *   productGrpStr (this value is the selected ‘GroupId’),
 *   masterGroupId(this value is the selected ‘MasterGroupId’),
 *   selectedVesselType,
 *   "Y",
 *   selectedQty (the quantity entered by the user.  Please note that the user could have entered three quantities in three different UOMs in the lot screen shot.  But we got to pass on the quantity that was ‘required’),
 *   jobFinishDate,
 *   UOMCode (),
 *   INPUT_VAL1 (this value is what was entered by the user in the lot page in the field ‘Enter total # of Grades…’.  If not present, default to 1)
 * )
 *
 * Once you retrieve the result (one row) from the SP, the logic to calculate the price follows:
 * - if(UseMaximum, min(rate_maxPrice, MinAdjustedPrice), MinAdjustedPrice)
 * - In this case, UseMaximum will always be ‘false’.  We can revisit in the future to enhance the max condition.
 * - MinAdjustedPrice:
 * if(additionalVessel (PS_ITSC_CONTROL:OBJECT_NAME - BOOLEAN_VAL_1 – if present and if chosen to yes),
 *    rate_fltData1,
 *    if(additionalLot (PS_ITSC_CONTROL:OBJECT_NAME - ADDITIONAL_LOT),
 *       if( rate_intData0=1,
 *    rate_fltData0,
 *    max(rate_fltData0, addtnlUnadjustedPrice)),
 *       max(rate_minPrice, UnadjustedPrice)
 *    )
 * )
 *
 * - addtnlUnadjustedPrice:
 * rate_unitPrice * max(0, (selectedQty (entered by user) – GetUnitsIncluded(PS_ITSC_INSP_RATE:UNITS_INCLUDED)))
 *
 * - UnadjustedPrice:
 * if(rate_BasePrice > 0, rate_BasePrice, rate_unitPrice * max(0, (selectedQty - GetUnitsIncluded)) )
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
 *  LINE ITEM DESCRIPTION
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
 * D.  Account Codes
 *   For every line item we need to identify the following chart-field accounts:
 *
 *   1.  glCode:  Using the NOMINATION_TYPE and EXPRESSION_ID, GL_CODE can be retrieved from PS_ITSC_EXP_GLCODE.   NOMINATION_TYPE is the job type.  EXPRESSION_ID is the same as the PS_ITSC_SRVCE_RATE table
 *
 *   2. department:  The retrieved glcode in # 1 can be used to get the department code from the table PS_ITSC_DEPARTMENT
 *
 *   3. serviceType ?  if(equals(EXPRESSION:branchType, "ADM"), EXPRESSION:expnBranchType, EXPRESSION:branchType)
 *
 *
 *   4. branch: if(equals(EXPRESSION:expnBranchType, "OPS"), PS_ITSC_BRANCH_CODE:OPS_CODE, PS_ITSC_BRANCH_CODE:LAB_CODE)
 *
 *   5. productGroup: ‘MasterGroupId’ got picked at the lot level
 *
 **/

public class InspectionPricer implements Pricer
{
  private static Log log = LogFactory.getLog(InspectionPricer.class);

  /**
   * Using input <code>CalculatorRequest</code> to apply the pricing logic.
   *
   * @param cr the <code>CalculatorRequest</code> contains the parameters used in the pricer.
   * @return a <code>CalculatorResult</code> which contains the calculation result.
   */
  public CalculatorResult applyPricingLogic(CalculatorRequest cr)
  {
    int intQty = 0;
    double floatQty = 0;
    boolean additionalVessel = false;
    boolean booleanVal1 = false;
    boolean additionalLot = false;
    int uomCode = 1;
    Double quantity = null;
    Double quantity1 = null;
    Double quantity2 = null;

    Map params = new HashMap();

    ContractExpressionExt cee = (ContractExpressionExt)cr.getParameter(Constants.CONTRACT_EXPRESSION_EXT);
    if(cee == null) return null;

    if(cee.getControlExts().size() > 0)
    {
      ControlExt c = (ControlExt)cee.getControlExts().get(0);

      Number tmpNumber = (Number)c.getParameter(Constants.UOM_CODE);
      if(tmpNumber != null) uomCode = tmpNumber.intValue();

      quantity = (Double)c.getParameter(Constants.QUANTITY);
      quantity1 = (Double)c.getParameter(Constants.QUANTITY1);
      quantity2 = (Double)c.getParameter(Constants.QUANTITY2);

      if(uomCode == 1)
      {
        if(quantity != null)
        {
          intQty = quantity.intValue();
          floatQty = quantity.doubleValue();
        }
      }
      else if(uomCode == 2)
      {
        if(quantity1 != null)
        {
          intQty = quantity1.intValue();
          floatQty = quantity1.doubleValue();
        }
      }
      else if(uomCode == 3)
      {
        if(quantity2 != null)
        {
          intQty = quantity2.intValue();
          floatQty = quantity2.doubleValue();
        }
      }

      Boolean tmpBoolean = (Boolean)c.getParameter(Constants.ADDITIONAL_LOT);
      if(tmpBoolean != null) additionalLot = tmpBoolean.booleanValue();

      tmpBoolean = (Boolean)c.getParameter(Constants.BOOLEAN_VAL_1);
      if(tmpBoolean != null) booleanVal1 = tmpBoolean.booleanValue();

      Boolean tmpBooleanAdditionalVessel = (Boolean)c.getParameter(Constants.ADDITIONAL_VESSEL);
      if(tmpBoolean != null) additionalVessel = tmpBoolean.booleanValue();
      else if(tmpBooleanAdditionalVessel != null) additionalVessel = tmpBooleanAdditionalVessel.booleanValue();
      //additionalVessel = booleanVal1;

      params.putAll(c.getDataMap());
    }

    CalculatorService calculatorService = (CalculatorService)ServiceLocator.getInstance().getBean("calculatorService");
    InspectionRate sr = calculatorService.getInspectionRate(
      cr.getContract().getCfgContractId().getContractId(),
      cr.getContract().getPriceBookId(),
      "Inspection",
      (String)cr.getParameter(Constants.VESSEL_TYPE),
      (String)cr.getParameter(Constants.PRODUCT_GROUP_ID),
      (String)cr.getParameter(Constants.MASTER_GROUP_ID),
      (String)cr.getParameter(Constants.LOCATION),
      intQty,
      floatQty,
      uomCode,
      (String)cr.getParameter(Constants.NOMINATION_DATE_STR)
    );

    if(sr != null)
    {
      Expression expression = calculatorService.getExpressionByExpressionId(
        sr.getInspectionRateId().getExpressionId()
      );
      cee.setExpression(expression);

      Double basePrice = sr.getBasePrice();
      Double unitPrice = sr.getUnitPrice();
      Double minPrice = sr.getMinimumPrice();
      Double maxPrice = sr.getMaximumPrice();
      Double floatData0 = sr.getFloatData0();
      Double floatData1 = sr.getFloatData1();

      LocationDiscount ld = calculatorService.getLocationDiscount(
        cr.getContract().getCfgContractId().getContractId(),
        (String)cr.getParameter(Constants.LOCATION),
        (Date)cr.getParameter(Constants.NOMINATION_DATE)
      );

      Double multiplierEscalator = CalculatorUtil.getMultiplierAndEscalator(
        cr,
        cee.getExpression(),
        sr.getInspectionRateId().getContractId(),
        sr.getInspectionRateId().getLocation(),
        ld
      );

      basePrice = basePrice * multiplierEscalator;
      unitPrice = unitPrice * multiplierEscalator;
      minPrice = minPrice * multiplierEscalator;
      maxPrice = maxPrice * multiplierEscalator;
      if(floatData0 != null) floatData0 = floatData0 * multiplierEscalator;
      if(floatData1 != null) floatData1 = floatData1 * multiplierEscalator;

      double currencyMultiplier = CalculatorUtil.getCurrencyMultiplier(cr);

      basePrice = NumberUtil.roundHalfUp(basePrice * currencyMultiplier, Constants.DEFAULT_ROUNDING);
      unitPrice = NumberUtil.roundHalfUp(unitPrice * currencyMultiplier, Constants.DEFAULT_ROUNDING);
      minPrice =  NumberUtil.roundHalfUp(minPrice * currencyMultiplier, Constants.DEFAULT_ROUNDING);
      maxPrice =  NumberUtil.roundHalfUp(maxPrice * currencyMultiplier, Constants.DEFAULT_ROUNDING);
      if(floatData0 != null) floatData0 = NumberUtil.roundHalfUp(floatData0 * currencyMultiplier, Constants.DEFAULT_ROUNDING);
      if(floatData1 != null) floatData1 = NumberUtil.roundHalfUp(floatData1 * currencyMultiplier, Constants.DEFAULT_ROUNDING);

      //params.put(Constants.ADDITIONAL_VESSEL, new Boolean(additionalVessel));
      //params.put(Constants.ADDITIONAL_LOT, new Boolean(additionalLot));
      params.put(Constants.BASE_PRICE, basePrice);
      params.put(Constants.UNIT_PRICE, unitPrice);
      params.put(Constants.MIN_PRICE, minPrice);
      params.put(Constants.MAX_PRICE, maxPrice);
      params.put(Constants.UNITS_INCLUDED, sr.getUnitsIncluded());
      params.put(Constants.INSPECTION_QUANTITY, new Double(floatQty));
      params.put(Constants.FLOAT_DATA0, floatData0);
      params.put(Constants.FLOAT_DATA1, floatData1);
      params.put(Constants.INT_DATA0, sr.getIntData0());
      params.put(Constants.TOTAL_LOT_INSP_PRICE,cr.getParameter(Constants.TOTAL_LOT_INSP_PRICE));
      params.put(Constants.MAX_LOT_INSP_QTY,cr.getParameter(Constants.MAX_LOT_INSP_QTY));
      params.put(Constants.TOTAL_VESSEL_INSP_PRICE,cr.getParameter(Constants.TOTAL_VESSEL_INSP_PRICE));
      params.put(Constants.ADDITIONAL_GRADE,cr.getParameter(Constants.ADDITIONAL_GRADE));

      Calculator calculator = getCalculator();
      if(calculator != null)
      {
        Double totalPrice = (Double)calculator.calculate(params);
        if(totalPrice == null) return null;
        //totalPrice = applyDiscount(cr, cee, ld, totalPrice);

        CalculatorResult calculatorResult = new CalculatorResult();
        calculatorResult.setContractExpressionExt(cee);

        calculatorResult.getDataMap().putAll(params);

        totalPrice = NumberUtil.roundHalfUp(totalPrice, Constants.DEFAULT_ROUNDING);
        calculatorResult.setParameter(Constants.PRICE_BEFORE_DISCOUNT, totalPrice);

        if(totalPrice == basePrice) calculatorResult.setParameter(Constants.BASE_PRICE_0, basePrice);
        else calculatorResult.setParameter(Constants.BASE_PRICE_0, new Double(0));
        calculatorResult.setParameter(Constants.QUANTITY, quantity);
        calculatorResult.setParameter(Constants.QUANTITY1, quantity1);
        calculatorResult.setParameter(Constants.QUANTITY2, quantity2);
        calculatorResult.setParameter(Constants.UNIT_PRICE, unitPrice);

        if(totalPrice.equals(minPrice)) calculatorResult.setParameter(Constants.MIN_PRICE, minPrice);
        else calculatorResult.setParameter(Constants.MIN_PRICE, new Double(0));

        if(totalPrice.equals(maxPrice)) calculatorResult.setParameter(Constants.MAX_PRICE, maxPrice);
        else calculatorResult.setParameter(Constants.MAX_PRICE, new Double(0));

        if(totalPrice.equals(floatData0)) calculatorResult.setParameter(Constants.FLOAT_DATA0, floatData0);
        else calculatorResult.setParameter(Constants.FLOAT_DATA0, new Double(0));

        if(totalPrice.equals(unitPrice * floatQty)) calculatorResult.setParameter(Constants.SELECTED_QTY_PRICE, true);
        else calculatorResult.setParameter(Constants.SELECTED_QTY_PRICE, false);

        if(additionalLot) calculatorResult.setParameter(Constants.ADDITIONAL_LOT, true);
        else calculatorResult.setParameter(Constants.ADDITIONAL_LOT, false);

        if(booleanVal1) calculatorResult.setParameter(Constants.BOOLEAN_VAL_1, true);
        else calculatorResult.setParameter(Constants.BOOLEAN_VAL_1, false);

        if(additionalVessel) calculatorResult.setParameter(Constants.ADDITIONAL_VESSEL, true);
        else calculatorResult.setParameter(Constants.ADDITIONAL_VESSEL, false);

        Double discountPct = null;
        boolean isContractPrice = cr.getContract().getCfgContractId().getContractId().equals(
          sr.getInspectionRateId().getContractId()
        );
        cr.setParameter(Constants.IS_CONTRACT_PRICE, new Boolean(isContractPrice));
        Discounter discounter = getDiscounter(isContractPrice);
        if(discounter != null)
        {
          discountPct = discounter.getDiscount(cr, ld);
          if((discountPct != null) && (discountPct > 0))
          {
            totalPrice *= (1.0 - discountPct * 0.01);
            calculatorResult.setDiscount(discountPct);
          }
        }

        totalPrice = NumberUtil.roundHalfUp(totalPrice, Constants.DEFAULT_ROUNDING);
        calculatorResult.setTotalPrice(totalPrice);

        calculatorResult.setParameter(Constants.CONTRACT_REF_NO, sr.getContractRef());

        Map accountInfoMap = CalculatorUtil.getAccountInfo(
          cee.getExpression(),
          (String)cr.getParameter(Constants.JOB_TYPE),
          (String)cr.getParameter(Constants.JOB_CODE),
          (String)cr.getParameter(Constants.BRANCH_CODE),
          (String)cr.getParameter(Constants.MASTER_GROUP_ID),
          null
        );
        calculatorResult.getDataMap().putAll(accountInfoMap);

        boolean editable = CalculatorUtil.isEditable(
          isContractPrice,
          sr.getContractRef(),
          discountPct,
          NumberUtil.roundHalfUp(totalPrice,2)
        );

        calculatorResult.setParameter(Constants.IS_EDITABLE, editable);

        return calculatorResult;
      }
    }

    return null;
  }

  /**
   * Returns an InspectionCalculator.
   *
   * @return a Calculator of instance InspectionCalculator.
   */
  public Calculator getCalculator()
  {
    return CalculatorFactory.getCalculator(Constants.CALCULATOR_INSPECTION);
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
