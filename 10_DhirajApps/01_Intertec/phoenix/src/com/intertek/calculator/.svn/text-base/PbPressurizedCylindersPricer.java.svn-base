package com.intertek.calculator;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.intertek.entity.Expression;
import com.intertek.entity.LocationDiscount;
import com.intertek.entity.ServiceRate;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.CalculatorService;
import com.intertek.util.Constants;
import com.intertek.util.NumberUtil;

/**
 * Pricing Component - PB_PRESSURIZED_CYLINDERS:
 *
 * A).  Inputs
 *
 * Two inputs are used to calculate the price
 * - PS_ITSC_CONTROL:OBJECT_NAME values - "numPressCylinders" and "pressCylinderDays"
 *
 * The calculation within the model kicks in after the right row data is picked from the SRVCE_RATE table.  The following parameters are fed into the java class ‘com.itscb.pscb.udf.getRate’ to fetch the correct data (1 row):
 * getRate(
 * contrExpnContrId,
 * "PressCylinders",
 * if(equals(selectedLocationInfo, "*"), "*", getTokenVal(selectedLocationInfo,1,true)),
 * if(useProductGroup > 0, getTokenVal(selectedProduct,1,true), "*"),
 * "*",
 * selectedServiceLevel,
 * pressCylinderDays,
 * nominationDate)
 *
 * B) The source data for each of the parameters follows:
 * 1.  contrExpnContrId – this is either contract id or pricebook id, depending on the following:
 *    contrExpnContrId = PS_ITSC_CONTR_EXPN:CFG_CONTRACT_ID);
 *    if(PS_ITSC_CONTR_EXPN:ITS_CNTR_COMPONENT.equals("PB"))       contractId = pricebookId;
 *    else if(PS_ITSC_CONTR_EXPN:CFG_DISCOUNT_PCT >= 0)
 *   contractId = pricebookId;
 *
 * 2.  selectedLocationInfo = This is the zone information.  The zone information is auto-selected upfront based on port (PS_ITSC_PORT_LOC) or branch (PS_ITSC_BRNCH_LOC) for a given contract or price book.
 *
 * 3.  useProductGroup = PS_ITSC_CONTR_EXPN:ITS_USE_GROUP_ID.  This value could be greater than 0 only for lot level items.  Because only then we will have a product id to use
 *   selectedProduct = this is the product id (PS_ITSC_PRDUCT_GRP) selected at the lot level.
 *
 * 4.  useVesselType = PS_ITSC_CONTR_EXPN:ITS_USE_VESSEL_ID.  This value could be greater than 0 only for vessel and lot level items.  Because only then, we will have a vessel id to use.
 *
 * 5.  selectedServiceLevel = PS_ITSC_CONTR_EXPN:ITS_SERVICE_LEVEL
 *
 * 6.  Quantity = entered by the user
 * 7.  nominationDate = job finish date from the entry page.
 *
 * B) PRICING LOGIC
 * Once the row is populated, the logic to calculate the final price follows:
 *
 *
 * Price:
 *
 *  (if(UseMaximum, min(rate_maxPrice, MinAdjustedPrice ), MinAdjustedPrice )
 *
 * MinAdjustedPrice
 * if(UseMinimum, max(rate_minPrice, UnadjustedPrice), UnadjustedPrice)
 *
 * UnadjustedPrice
 * rate_BasePrice + (rate_unitPrice * max(0, (pressCylinderDays - GetUnitsIncluded)) * numPressCylinders)
 *
 * UseMaximum
 * if(rate_maxPrice > 0, true, false)
 *
 * UseMinimum
 * if(rate_minPrice > 0, true, false)
 *
 * - Please note that quantity and percentage are entered by the users.  The percentage is used for only for a few components.
 * - Note that rate_XXX (ex: rate_minPrice maps to PS_ITSC_SRVCE_RATE:MINIMUM_PRICE) directly map to a data element from the retrieved result in section ‘A’
 * - The multipliers and escalators are applied on the individual rates.  Refer to section ‘Multipliers & Escalators’.  The ‘OPS_LAB_Factor’ are refered to as ‘multipliers’ in the section ‘Multipliers & Escalators’
 * - All prices should be rounded to four decimal places.
 * - The ‘CurrencyMultiplier’ is the conversion rate from one currency to the other currency.  The table ‘PS_RT_RATE_TBL’ holds the conversion rate.  Depending on the currency of the contract, the user will have choice upfront to choose the conversion currency.  Refer to the drop-down ‘Transaction Currency – Multiplier’ under ‘’Add Customers’ tab in the mock-up.  You need to add a customer to view the above.
 *
 * Here is an example for the value ‘rate_minPrice’:
 * rate_minPrice:
 * round(nativeRateMinPrice * CurrencyMultiplier, -4)
 *
 * nativeRateMinPrice:
 * if(isContractPrice > 0, (minPriceFromResult in ‘A’) * OPS_LAB_Factor) * annual_escalator,
 *   (minPriceFromResult in ‘A’) * OPS_LAB_Factor
 * )
 *
 *
 * C). LINE ITEM DESCRIPTION
 * The variable ‘descriptionArgs’ is used as the input to create the line item description.
 * descriptionArgs
 * concatenate(rate_BasePrice, "^",
 *             pressCylinderDays, "^",
 *             rate_unitPrice, "^",
 *             rate_untsIncld, "^",
 *             if(PRICE = rate_minPrice, rate_minPrice, 0), "^",
 *             if(PRICE = rate_maxPrice, rate_maxPrice, 0), "^",
 *             rate_fltData0, "^",
 *             rate_intData2, "^",
 *             rate_fltData3, "^",
 *             numPressCylinders, "^",
 *             rate_ContRefNo
 * )
 * The variable ‘descriptionArgs’ (input parameters) along with the format string is used to generate the line item description.  The format string is retrieved from PS_ITSC_RB table.  The query first searches for contact-specific value, if not PB, or the default choice with ‘NONE’ as the contract id.  The PS_ITSC_CONTR_EXPN:EXPRESSION_ID value is used as the RB_KEY.
 *
 * D.  Account Codes
 * For every line item we need to identify the following chart-field accounts:
 *
 * 1.  glCode:  Using the NOMINATION_TYPE and EXPRESSION_ID, GL_CODE can be retrieved from PS_ITSC_EXP_GLCODE.   NOMINATION_TYPE is the job type.  EXPRESSION_ID is the same as the PS_ITSC_SRVCE_RATE table
 *
 * 2. department:  The retrieved glcode in # 1 can be used to get the department code from the table PS_ITSC_DEPARTMENT
 *
 * 3. serviceType ?  if(equals(EXPRESSION:branchType, "ADM"), EXPRESSION:expnBranchType, EXPRESSION:branchType)
 *
 *
 * 4. branch: if(equals(EXPRESSION:expnBranchType, "OPS"), PS_ITSC_BRANCH_CODE:OPS_CODE, PS_ITSC_BRANCH_CODE:LAB_CODE)
 *
 * 5. productGroup:
 *
 * String useProdFlag =  PS_ITSC_EXP_GLCODE::USEPROD_FLAG;                if("0".equals(useProdFlag)){
 *   // Check what kind of noProdCode to use
 * // getJobType(), -? for the given ‘Product Type’ (in Add Customers tab) – the drop down value  should be populated from the list of codes from PS_ITSC_JOB_CODE
 * //
 *   prodGrp = ds.getProdAccntCode(getJobType(),PS_ITSC_EXP_GLCODE:NOPROD_CODE
 * );
 * //
 * //getProdAccntCode ? PS_ITSC_PRD_CODE where JOB_CODE=? and NOPROD_CODE=?"
 * //
 * }
 * else{
 *   prodGrp = use the lot level product code (the masterGroupId), if any present
 * }
 *
 *
 * Except for branch, the rest of the values are sent to PSFT Financials for sub-ledgers.
 **/

public class PbPressurizedCylindersPricer extends StandardPricer
{
  private static Log log = LogFactory.getLog(PbPressurizedCylindersPricer.class);

  /**
   * Using input <code>CalculatorRequest</code> to apply the pricing logic.
   *
   * @param cr the <code>CalculatorRequest</code> contains the parameters used in the pricer.
   * @return a <code>CalculatorResult</code> which contains the calculation result.
   */
  public CalculatorResult applyPricingLogic(CalculatorRequest cr)
  {
    ContractExpressionExt cee = (ContractExpressionExt)cr.getParameter(Constants.CONTRACT_EXPRESSION_EXT);
    if(cee == null) return null;

    Map params = new HashMap();
    for(int i=0; i<cee.getControlExts().size();i++)
    {
      ControlExt c = (ControlExt)cee.getControlExts().get(i);
      params.putAll(c.getDataMap());
    }

    int pressCylinderDays = 0;
    double floatQty = 0.0;
    Number tmpNumber = (Number)params.get(Constants.pressCylinderDays);
    if(tmpNumber != null)
    {
      pressCylinderDays = tmpNumber.intValue();
      floatQty = tmpNumber.doubleValue();
    }

    String contractId = getContractId(
      cee.getContractExpression(),
      cr.getContract().getPriceBookId()
    );

    CalculatorService calculatorService = (CalculatorService)ServiceLocator.getInstance().getBean("calculatorService");
    ServiceRate sr = calculatorService.getServiceRate(
      contractId,
      Constants.PressCylinders,
      "*", //(String)cr.getParameter(Constants.VESSEL_TYPE),
      CalculatorUtil.applyProductGroupConversion(
        cee.getContractExpression().getUseGroupId(),
        (String)cr.getParameter(Constants.PRODUCT_GROUP_ID)
      ),
      cee.getContractExpression().getServiceLevel(),
      (String)cr.getParameter(Constants.LOCATION),
      pressCylinderDays,
      floatQty,
      (String)cr.getParameter(Constants.NOMINATION_DATE_STR)
    );

    if(sr != null)
    {
      Expression expression = calculatorService.getExpressionByExpressionId(
        sr.getServiceRateId().getExpressionId()
      );
      cee.setExpression(expression);

      Double basePrice = sr.getBasePrice();
      Double unitPrice = sr.getUnitPrice();
      Double minPrice = sr.getMinimumPrice();
      Double maxPrice = sr.getMaximumPrice();

      LocationDiscount ld = calculatorService.getLocationDiscount(
        cr.getContract().getCfgContractId().getContractId(),
        (String)cr.getParameter(Constants.LOCATION),
        (Date)cr.getParameter(Constants.NOMINATION_DATE)
      );

      Double multiplierEscalator = CalculatorUtil.getMultiplierAndEscalator(
        cr,
        cee.getExpression(),
        sr.getServiceRateId().getContractId(),
        sr.getServiceRateId().getLocation(),
        ld
      );

      basePrice = basePrice * multiplierEscalator.doubleValue();
      unitPrice = unitPrice * multiplierEscalator.doubleValue();
      minPrice = minPrice * multiplierEscalator.doubleValue();
      maxPrice = maxPrice * multiplierEscalator.doubleValue();

      double currencyMultiplier = CalculatorUtil.getCurrencyMultiplier(cr);

      basePrice = NumberUtil.roundHalfUp(basePrice * currencyMultiplier, Constants.DEFAULT_ROUNDING);
      unitPrice = NumberUtil.roundHalfUp(unitPrice * currencyMultiplier, Constants.DEFAULT_ROUNDING);
      minPrice =  NumberUtil.roundHalfUp(minPrice * currencyMultiplier, Constants.DEFAULT_ROUNDING);
      maxPrice =  NumberUtil.roundHalfUp(maxPrice * currencyMultiplier, Constants.DEFAULT_ROUNDING);

      params.put(Constants.BASE_PRICE, basePrice);
      params.put(Constants.UNIT_PRICE, unitPrice);
      params.put(Constants.MIN_PRICE, minPrice);
      params.put(Constants.MAX_PRICE, maxPrice);
      params.put(Constants.UNITS_INCLUDED, sr.getUnitsIncluded());

      Calculator calculator = CalculatorFactory.getCalculator(Constants.CALCULATOR_PB_PRESSURIZED_CYLINDERS);
      if(calculator != null)
      {
        Double totalPrice = (Double)calculator.calculate(params);
        if(totalPrice == null) return null;

        CalculatorResult calculatorResult = new CalculatorResult();
        calculatorResult.setContractExpressionExt(cee);

        calculatorResult.getDataMap().putAll(params);

        calculatorResult.setParameter(Constants.BASE_PRICE, basePrice);
        calculatorResult.setParameter(Constants.UNIT_PRICE, unitPrice);
        calculatorResult.setParameter(Constants.UNITS_INCLUDED, sr.getUnitsIncluded());
        calculatorResult.setParameter(Constants.CONTRACT_REF_NO, sr.getContractRef());
        calculatorResult.setParameter(Constants.FLOAT_DATA0, sr.getFloatData0());
        calculatorResult.setParameter(Constants.FLOAT_DATA2, sr.getServiceRateId().getFloatData2());
        calculatorResult.setParameter(Constants.FLOAT_DATA3, sr.getFloatData3());

        totalPrice = NumberUtil.roundHalfUp(totalPrice, Constants.DEFAULT_ROUNDING);
        calculatorResult.setParameter(Constants.PRICE_BEFORE_DISCOUNT, totalPrice);

        if(totalPrice.equals(minPrice))
          calculatorResult.setParameter(Constants.MIN_PRICE, minPrice);
        else
          calculatorResult.setParameter(Constants.MIN_PRICE, new Double(0));

        if(totalPrice.equals(maxPrice))
          calculatorResult.setParameter(Constants.MAX_PRICE, maxPrice);
        else
          calculatorResult.setParameter(Constants.MAX_PRICE, new Double(0));

        Discounter discounter = getDiscounter();
        Double discountPct = null;
        boolean isContractPrice = cr.getContract().getCfgContractId().getContractId().equals(contractId);
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

        Map accountInfoMap = CalculatorUtil.getAccountInfo(
          cee.getExpression(),
          (String)cr.getParameter(Constants.JOB_TYPE),
          (String)cr.getParameter(Constants.JOB_CODE),
          (String)cr.getParameter(Constants.BRANCH_CODE),
          (String)cr.getParameter(Constants.MASTER_GROUP_ID),
          cee.getContractExpression().getUseGroupId()
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
   * Return the expression id by lighterage type
   *
   * @param lighterageType a String
   * @return the expression id as a String
   **/
  public String getExpressionId(String lighterageType)
  {
    if(Constants.Harbor.equals(lighterageType)) return Constants.L_HarborDay;
    if(Constants.Offshore.equals(lighterageType)) return Constants.L_OffshoreDay;

    return null;
  }
}
