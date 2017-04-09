package com.intertek.calculator;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.intertek.entity.Expression;
import com.intertek.entity.LocationDiscount;
import com.intertek.entity.SlatePrice;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.CalculatorService;
import com.intertek.service.SlateService;
import com.intertek.util.Constants;
import com.intertek.util.NumberUtil;

/**
 * A Pricing Component - Slate
 *
 * 1. Get TestPrice using stored procedure: itsc_slate_price_fn
 *
 * 2. Use Simple Calculator to calculate the total price
 *
 * 3. Apply Discount Logic
 *
 * 4. Generate LINE ITEM DESCRIPTION
 *   The variable ‘descriptionArgs’ is used as the input to create the line item description.
 *   descriptionArgs
 *
 *   concatenate(METHODOLOGY,"^",DESCRIPTION,"^",quantity,"^",PRICE,"^",CONTRACT_REF)
 **/

public class SlatePricer implements Pricer
{
  private static Log log = LogFactory.getLog(SlatePricer.class);

  /**
   * Using input <code>CalculatorRequest</code> to apply the pricing logic.
   *
   * @param cr the <code>CalculatorRequest</code> contains the parameters used in the pricer.
   * @return a <code>CalculatorResult</code> which contains the calculation result.
   */
  public CalculatorResult applyPricingLogic(CalculatorRequest cr)
  {
    int intQty = 0;

    Number tmpNumber = (Number)cr.getParameter(Constants.QUANTITY);
    if(tmpNumber != null)
    {
      intQty = tmpNumber.intValue();
    }

    if(intQty == 0) return null;

    String slateId = (String)cr.getParameter(Constants.SLATE_ID);
    String location = (String)cr.getParameter(Constants.LOCATION);

    SlateService slateService = (SlateService)ServiceLocator.getInstance().getBean("slateService");

    SlatePrice slatePrice = slateService.getSlatePrice(
      cr.getContract().getCfgContractId().getContractId(),
      cr.getContract().getPriceBookId(),
      slateId,
      location,
      new Integer(intQty),
      (String)cr.getParameter(Constants.NOMINATION_DATE_STR)
    );
    if(slatePrice != null)
    {
      CalculatorService calculatorService = (CalculatorService)ServiceLocator.getInstance().getBean("calculatorService");

      Expression expression = calculatorService.getExpressionByExpressionId(
        Constants.SLATE
      );

      LocationDiscount ld = calculatorService.getLocationDiscount(
        cr.getContract().getCfgContractId().getContractId(),
        (String)cr.getParameter(Constants.LOCATION),
        (Date)cr.getParameter(Constants.NOMINATION_DATE)
      );

      boolean isContractPrice = cr.getContract().getCfgContractId().getContractId().equals(slatePrice.getSlatePriceId().getContractId());
      cr.setParameter(Constants.IS_CONTRACT_PRICE, new Boolean(isContractPrice));

      Double basePrice = slatePrice.getUnitPrice();

      Double multiplierEscalator = CalculatorUtil.getMultiplierAndEscalator(
        cr,
        expression,
        slatePrice.getSlatePriceId().getContractId(),
        slatePrice.getSlatePriceId().getLocation(),
        ld
      );

      basePrice = basePrice * multiplierEscalator.doubleValue();

      double currencyMultiplier = CalculatorUtil.getCurrencyMultiplier(cr);

      basePrice = NumberUtil.roundHalfUp(basePrice * currencyMultiplier, Constants.DEFAULT_ROUNDING);

      Map params = new HashMap();
      params.put(Constants.UNIT_PRICE, basePrice);
      params.put(Constants.QUANTITY, new Integer(intQty));

      Calculator calculator = CalculatorFactory.getCalculator(Constants.CALCULATOR_SIMPLE);
      if(calculator != null)
      {
        Double totalPrice = (Double)calculator.calculate(params);
        if(totalPrice == null) return null;

        CalculatorResult calculatorResult = new CalculatorResult();

        calculatorResult.setParameter(Constants.QUANTITY, intQty);
        calculatorResult.setParameter(Constants.UNIT_PRICE, basePrice);
        calculatorResult.setParameter(Constants.CONTRACT_REF_NO, slatePrice.getContractRef());

        calculatorResult.setParameter(Constants.RB, cr.getParameter(Constants.RB));
        calculatorResult.setParameter(Constants.DESCRIPTION, cr.getParameter(Constants.DESCRIPTION));
        calculatorResult.setParameter(Constants.SLATE_ID, cr.getParameter(Constants.SLATE_ID));

        totalPrice = NumberUtil.roundHalfUp(totalPrice, Constants.DEFAULT_ROUNDING);
        calculatorResult.setParameter(Constants.PRICE_BEFORE_DISCOUNT, totalPrice);

        Discounter discounter = getDiscounter();
        Double discountPct = null;
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

        Map accountInfoMap = CalculatorUtil.getAccountInfo(
          expression,
          (String)cr.getParameter(Constants.JOB_TYPE),
          (String)cr.getParameter(Constants.JOB_CODE),
          (String)cr.getParameter(Constants.BRANCH_CODE),
          (String)cr.getParameter(Constants.MASTER_GROUP_ID),
          null
        );
        calculatorResult.getDataMap().putAll(accountInfoMap);

        boolean editable = CalculatorUtil.isEditable(
          isContractPrice,
          slatePrice.getContractRef(),
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
   * Returns a SlateDiscounter.
   *
   * @return a Discounter of instance SlateDiscounter.
   */
  private Discounter getDiscounter()
  {
    return DiscounterFactory.getDiscounter(Constants.CALCULATOR_SLATE_DISCOUNTER);
  }
}
