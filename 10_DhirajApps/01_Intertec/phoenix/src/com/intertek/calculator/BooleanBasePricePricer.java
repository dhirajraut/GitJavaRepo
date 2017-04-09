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
 * A Pricing Component - BOOLEAN_BASE_PRICE.
 *
 * For standard formula, the user entered value in an input box.  For this model (scenario),
 * the user will choose a value in the radio button with the option Yes or No.
 * The calculation within the model kicks in after the right row data is picked from the SRVCE_RATE table.
 * The following parameters are fed into the java class ‘com.itscb.pscb.udf.getRate’ to fetch
 * the correct data (1 row):
 * getRate(
 *   contrExpnContrId,
 *   expressionId,
 *   if(equals(selectedLocationInfo, "*"), "*", getTokenVal(selectedLocationInfo,1,true)),
 *   if(useProductGroup > 0, getTokenVal(selectedProduct,1,true), "*"),
 *   if(useVesselType > 0, selectedVesselType, "*"),
 *   selectedServiceLevel,
 *   1.0,
 *   nominationDate)
 *
 * A) The source data for each of the parameters follows:
 *  1.  contrExpnContrId - this is either contract id or pricebook id, depending on the following:
 *     contrExpnContrId = PS_ITSC_CONTR_EXPN:CFG_CONTRACT_ID);
 *     if(PS_ITSC_CONTR_EXPN:ITS_CNTR_COMPONENT.equals("PB"))       contractId = pricebookId;
 *     else if(PS_ITSC_CONTR_EXPN:CFG_DISCOUNT_PCT >= 0)
 *    contractId = pricebookId;
 *
 *  2.  expressionId = PS_ITSC_CONTR_EXPN:ITS_SRVC_RT_EXP_ID  Please note that this ID will be present for all the components that are using ‘Standard Formula’ model.  As for other components using other model, the expressionId may not be present.  To de discussed later on the source of the ids.
 *
 *  3.  selectedLocationInfo = This is the zone information.  The zone information is auto-selected upfront based on port (PS_ITSC_PORT_LOC) or branch (PS_ITSC_BRNCH_LOC) for a given contract or price book.
 *
 *  4.  useProductGroup = PS_ITSC_CONTR_EXPN:ITS_USE_GROUP_ID.  This value could be greater than 0 only for lot level items.  Because only then we will have a product id to use
 *    selectedProduct = this is the product id (PS_ITSC_PRDUCT_GRP) selected at the lot level.
 *
 *  5.  useVesselType = PS_ITSC_CONTR_EXPN:ITS_USE_VESSEL_ID.  This value could be greater than 0 only for vessel and lot level items.  Because only then, we will have a vessel id to use.
 *
 *  6.  selectedServiceLevel = PS_ITSC_CONTR_EXPN:ITS_SERVICE_LEVEL
 *
 *  7.  Quantity = hard-coded to “1” at the code level
 *  8.  nominationDate = job finish date from the entry page.
 *
 * B) PRICING LOGIC
 *  Once the row is populated, the logic to calculate the final price follows:
 *  Price:
 *
 *  rate_BasePrice * Condition:intValue; the Condition:intValue is 1 if the selected choice is ‘Yes’, else ‘0’ which will result in zero price
 *
 *
 *  - Note that rate_XXX (ex: rate_BasePrice maps to PS_ITSC_SRVCE_RATE:CFG_BASE_PRICE) directly map to a data element from the retrieved result in section ‘A’
 *  - The multipliers and escalators are applied on the individual rates.  Refer to section ‘Multipliers & Escalators’.  The ‘OPS_LAB_Factor’ are refered to as ‘multipliers’ in the section ‘Multipliers & Escalators’
 *  - All prices should be rounded to four decimal places.
 *  - The ‘CurrencyMultiplier’ is the conversion rate from one currency to the other currency.  The table ‘PS_RT_RATE_TBL’ holds the conversion rate.  Depending on the currency of the contract, the user will have choice upfront to choose the conversion currency.  Refer to the drop-down ‘Transaction Currency – Multiplier’ under ‘’Add Customers’ tab in the mock-up.  You need to add a customer to view the above.
 *
 *  The following is same as in the standard formula but I have just shown the example for the only variable that we need here, that is ‘rate_BasePrice’
 *  Here is an example for the value ‘rate_BasePrice’:
 *  rate_BasePrice:
 *  round(nativeRateBasePrice * CurrencyMultiplier, -4)
 *
 *  if(isContractPrice > 0, (basePriceFromResult in ‘A’) * OPS_LAB_Factor) * annual_escalator,
 *    (basePriceFromResult in ‘A’) * OPS_LAB_Factor
 *  )
 *
 *
 * C). LINE ITEM DESCRIPTION
 *  The variable ‘descriptionArgs’ is used as the input to create the line item description.
 *  descriptionArgs
 *
 *  concatenate(rate_BasePrice, "^",
 *              rate_ContRefNo)
 *
 *
 *  The variable ‘descriptionArgs’ (input parameters) along with the format string is used to generate the line item description.  The format string is retrieved from PS_ITSC_RB table.  The query first searches for contact-specific value, if not PB, or the default choice with ‘NONE’ as the contract id.  The PS_ITSC_CONTR_EXPN:EXPRESSION_ID value is used as the RB_KEY.
 *
 * D).  Account Codes
 *  For every line item we need to identify the following chart-field accounts:
 *
 *  1.  glCode:  Using the NOMINATION_TYPE and EXPRESSION_ID, GL_CODE can be retrieved from PS_ITSC_EXP_GLCODE.   NOMINATION_TYPE is the job type.  EXPRESSION_ID is the same as the PS_ITSC_SRVCE_RATE table
 *
 *  6.  department:  The retrieved glcode in # 1 can be used to get the department code from the table PS_ITSC_DEPARTMENT
 *
 *  7.  serviceType ?  if(equals(EXPRESSION:branchType, "ADM"), EXPRESSION:expnBranchType, EXPRESSION:branchType)
 *
 *
 *  8.  branch: if(equals(EXPRESSION:expnBranchType, "OPS"), PS_ITSC_BRANCH_CODE:OPS_CODE, PS_ITSC_BRANCH_CODE:LAB_CODE)
 *
 *  9.  productGroup:
 *
 *  String useProdFlag =  PS_ITSC_EXP_GLCODE::USEPROD_FLAG;                if("0".equals(useProdFlag)){
 *    // Check what kind of noProdCode to use
 *    // getJobType(), -? for the given ‘Product Type’ (in Add Customers tab) - the drop down value  should be populated from the list of codes from PS_ITSC_JOB_CODE
 *
 *    prodGrp = ds.getProdAccntCode(getJobType(),PS_ITSC_EXP_GLCODE:NOPROD_CODE
 *  );
 *  //
 *  //getProdAccntCode ? PS_ITSC_PRD_CODE where JOB_CODE=? and NOPROD_CODE=?"
 *  //
 *  }
 *  else{
 *    prodGrp = use the lot level product code (the masterGroupId), if any present
 *  }
 *
 *
 *
 *  Except for branch, the rest of the values are sent to PSFT Financials for sub-ledgers.
 *
 * E).  The radio button choices
 *
 *  For any question (control) with the  OBJECT_NAME like 'BOOLEAN_VAL%', the radio button choices are ‘Yes’ and ‘No’.
**/

public class BooleanBasePricePricer extends StandardPricer
{
  private static Log log = LogFactory.getLog(BooleanBasePricePricer.class);

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

    String contractId = getContractId(
      cee.getContractExpression(),
      cr.getContract().getPriceBookId()
    );

    Boolean radioInput = (Boolean)params.get(Constants.RADIO_INPUT);
    if((radioInput == null) || (radioInput.booleanValue() == false)) return null;

    CalculatorService calculatorService = (CalculatorService)ServiceLocator.getInstance().getBean("calculatorService");
    ServiceRate sr = calculatorService.getServiceRate(
      contractId,
      cee.getContractExpression().getServiceRateExpressionId(),
      CalculatorUtil.applyVesselTypeConversion(
        cee.getContractExpression().getUseVesselId(),
        (String)cr.getParameter(Constants.VESSEL_TYPE)
      ),
      CalculatorUtil.applyProductGroupConversion(
        cee.getContractExpression().getUseGroupId(),
        (String)cr.getParameter(Constants.PRODUCT_GROUP_ID)
      ),
      cee.getContractExpression().getServiceLevel(),
      (String)cr.getParameter(Constants.LOCATION),
      1,
      1,
      (String)cr.getParameter(Constants.NOMINATION_DATE_STR)
    );

    if(sr != null)
    {
      Expression expression = calculatorService.getExpressionByExpressionId(
        sr.getServiceRateId().getExpressionId()
      );
      cee.setExpression(expression);

      Double basePrice = sr.getBasePrice();

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

      double currencyMultiplier = CalculatorUtil.getCurrencyMultiplier(cr);

      basePrice = NumberUtil.roundHalfUp(basePrice * currencyMultiplier, Constants.DEFAULT_ROUNDING);

      params.put(Constants.BASE_PRICE, basePrice);

      Calculator calculator = CalculatorFactory.getCalculator(Constants.CALCULATOR_BOOLEAN_BASE_PRICE);
      if(calculator != null)
      {
        Double totalPrice = (Double)calculator.calculate(params);
        if(totalPrice == null) return null;

        CalculatorResult calculatorResult = new CalculatorResult();
        calculatorResult.setContractExpressionExt(cee);

        calculatorResult.getDataMap().putAll(params);

        calculatorResult.setParameter(Constants.BASE_PRICE, basePrice);
        calculatorResult.setParameter(Constants.CONTRACT_REF_NO, sr.getContractRef());

        totalPrice = NumberUtil.roundHalfUp(totalPrice, Constants.DEFAULT_ROUNDING);
        calculatorResult.setParameter(Constants.PRICE_BEFORE_DISCOUNT, totalPrice);

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
}
