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
 * A Pricing Component of Inspection Model - 2 (Contract Level)
 *
 * Job Max
 *
 * Within model-2, in order to identify if job max is applicable, please check if PS_ITSC_CONTR_EXPN where ITSC_SERVICE in (‘Tow-Max’) has returns a row.
 *
 * Fetch the contractExpression value using the ITSC_SERVICE=’Tow-Max’.
 *
 * To fetch the price, invoke the SP - ITSC_INSPECT_RATE_SP .  The parameters to pass on to are
 *
 * getInspectionRate(
 *   contracted,
 *   PricebookId,
 *   "InspectionTow",
 *   if(equals(selectedLocationInfo, "*"), "*", getTokenVal(selectedLocationInfo,1,true)),
 *   "*",
 *   "*",
 *   selectedVesselType,
 *   "Y",
 *   1,
 *   nominationDate,
 *   default to 1
 * )
 *
 *
 * Once you retrieve the result (one row) from the SP, the logic to calculate the price follows:
 * - if(MaxNotApplied > 0, 0, min(rate_maxPrice, TotalVesselPrices))
 * - MaxNotApplied = if (TotalVesselPrices > rate_maxPrice, 0, 1)
 * - TotalVesselPrices = sum of all applicable prices across all vessels and lots.  Lets say in an example, we have 2 vessels.  The vessel-1 has a vessel max price kicked in and all the lots underneath it are with 0 prices since vessel max price kicked-in.  The vessel-2 has two lots with appropriate prices.  In this case, the TotalVesselPrices would be the sum of vessel max price from vessel-1 and the two lots from vessel-2.
 *
**/

public class InspectionContractPricer implements Pricer
{
  private static Log log = LogFactory.getLog(InspectionContractPricer.class);

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

      tmpBoolean = (Boolean)c.getParameter(Constants.ADDITIONAL_VESSEL);
      if(tmpBoolean != null) additionalVessel = tmpBoolean.booleanValue();

      params.putAll(c.getDataMap());
    }

    CalculatorService calculatorService = (CalculatorService)ServiceLocator.getInstance().getBean("calculatorService");
    InspectionRate sr = calculatorService.getInspectionRate(
      cr.getContract().getCfgContractId().getContractId(),
      cr.getContract().getPriceBookId(),
      "InspectionTow",
      (String)cr.getParameter(Constants.VESSEL_TYPE),
      "*",
      "*",
      (String)cr.getParameter(Constants.LOCATION),
      1,
      1.0,
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

        if(totalPrice == basePrice) calculatorResult.setParameter(Constants.BASE_PRICE, basePrice);
        else calculatorResult.setParameter(Constants.BASE_PRICE, new Double(0));
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
        Discounter discounter = getDiscounter(isContractPrice);
        if(discounter != null)
        {
          cr.setParameter(Constants.IS_CONTRACT_PRICE, new Boolean(isContractPrice));
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
   * Returns an InspectionContractCalculator.
   *
   * @return a Calculator of instance InspectionContractCalculator.
   */
  public Calculator getCalculator()
  {
    return CalculatorFactory.getCalculator(Constants.CALCULATOR_INSPECTION_CONTRACT);
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
