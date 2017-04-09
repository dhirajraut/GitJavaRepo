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
 * Pricing Component - SAMPLING_INCLUDES
 *
 * A). Inputs
 *
 * Two inputs are used to calculate the price
 *
 * The variables ‘noOfQuarts’  and ‘noOfContainers’ are used within the pricing logic.
 *
 * The following parameters are fed into the java class ‘com.itscb.pscb.udf.getRate’ to fetch the correct data (1 row):
 *
 * getRate(
 * contrExpnContrId,
 * bnd(EXPRESSION:expressionId, "*"),
 * if(equals(selectedLocationInfo, "*"), "*", getTokenVal(selectedLocationInfo,1,true)),
 * if(useProductGrp > 0, getTokenVal(selectedProduct,1,true), "*"),
 * if(useVesselType > 0, selectedVesselType, "*"),
 * selectedServiceLevel,
 * noOfQuarts,
 * nominationDate
 * )
 *
 * B) The source data for each of the parameters follows:
 * 1.  contrExpnContrId – this is either contract id or pricebook id, depending on the following:
 *    contrExpnContrId = PS_ITSC_CONTR_EXPN:CFG_CONTRACT_ID);
 *    if(PS_ITSC_CONTR_EXPN:ITS_CNTR_COMPONENT.equals("PB"))       contractId = pricebookId;
 *    else if(PS_ITSC_CONTR_EXPN:CFG_DISCOUNT_PCT >= 0)
 *   contractId = pricebookId;
 *
 * 2.  expressionId = PS_ITSC_CONTR_EXPN:ITS_SRVC_RT_EXP_ID  Please note that this ID will be present for all the components that are using ‘Standard Formula’ model.  As for other components using other model, the expressionId may not be present.  To de discussed later on the source of the ids.
 *
 * 3.  selectedLocationInfo = This is the zone information.  The zone information is auto-selected upfront based on port (PS_ITSC_PORT_LOC) or branch (PS_ITSC_BRNCH_LOC) for a given contract or price book.
 *
 * 4.  useProductGroup = PS_ITSC_CONTR_EXPN:ITS_USE_GROUP_ID.  This value could be greater than 0 only for lot level items.  Because only then we will have a product id to use
 *   selectedProduct = this is the product id (PS_ITSC_PRDUCT_GRP) selected at the lot level.
 *
 * 5.  useVesselType = PS_ITSC_CONTR_EXPN:ITS_USE_VESSEL_ID.  This value could be greater than 0 only for vessel and lot level items.  Because only then, we will have a vessel id to use.
 *
 * 6.  selectedServiceLevel = PS_ITSC_CONTR_EXPN:ITS_SERVICE_LEVEL
 *
 * 7.  noOfQuarts = one of the quantities entered by the user
 * 8.  nominationDate = job finish date from the entry page.
 *
 * C) PRICING LOGIC
 * Once the row is populated, the logic to calculate the final price follows:
 * Price:
 * max(if(UseMaximum, min(rate_maxPrice, MinAdjustedPrice), MinAdjustedPrice), 0)
 *
 * UseMaximum
 * if(rate_maxPrice > 0, true, false)
 *
 * MinAdjustedPrice
 * if(UseMinimum, max(rate_minPrice, UnadjustedPrice), UnadjustedPrice)
 *
 * UnadjustedPrice
 * if(rate_intData1 = 1,
 *    rate_BasePrice + rate_unitPrice * ( TotNoOfContainers - max(0, includedSamples)),
 *    rate_BasePrice + rate_unitPrice * ( TotNoOfQuarts - max(0, includedSamples))
 *   )
 *
 * includedSamples
 * if(rate_intData0 > 0, rate_intData0, noOfContainers * rate_untsIncld))
 *
 * UseMaximum
 * if(rate_maxPrice > 0, true, false)
 *
 * UseMinimum
 * if(rate_minPrice > 0, true, false)
 *
 * PriceWithOutPercentage
 * if(UseMaximum, min(rate_maxPrice, MinAdjustedPrice ), MinAdjustedPrice )
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
 * D). LINE ITEM DESCRIPTION
 * The variable ‘descriptionArgs’ is used as the input to create the line item description.
 * descriptionArgs
 *
 * concatenate(rate_BasePrice, "^",
 *             noOfQuarts, "^",
 *             rate_unitPrice, "^",
 *             if(rate_untsIncld > 0, rate_untsIncld, rate_intData0), "^",
 *             if(PRICE = rate_minPrice, rate_minPrice, 0), "^",
 *             if(PRICE = rate_maxPrice, rate_maxPrice, 0), "^",
 *             rate_intData2, "^",
 *             rate_intData3, "^",
 *             noOfContainers, "^",
 *             includedSamples, "^",
 *             rate_ContRefNo
 * )
 *
 * The values ‘stringVal1’, ‘stringVal2’, and ‘stringVal3’ are user-entered.
 *
 * The variable ‘descriptionArgs’ (input parameters) along with the format string is used to generate the line item description.  The format string is retrieved from PS_ITSC_RB table.  The query first searches for contact-specific value, if not PB, or the default choice with ‘NONE’ as the contract id.  The PS_ITSC_CONTR_EXPN:EXPRESSION_ID value is used as the RB_KEY.
 *
 * E).  Account Codes
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
 *
 **/

public class SamplingIncludesPricer extends StandardPricer
{
  private static Log log = LogFactory.getLog(SamplingIncludesPricer.class);

  private static Map objectNameMap = new HashMap();

  static {
    init();
  }

  public SamplingIncludesPricer()
  {
  }

  private static void init()
  {
    objectNameMap.put("sampTotNoOfBalTanks", Constants.noOfContainers);
    objectNameMap.put("pulledBallSamples", Constants.noOfQuarts);
    objectNameMap.put("noOfShipTankers", Constants.noOfContainers);
    objectNameMap.put("pulledTankerSamples", Constants.noOfQuarts);
    objectNameMap.put("sampTotNoOfSLPTanks", Constants.noOfContainers);
    objectNameMap.put("pulledSLPSamples", Constants.noOfQuarts);
    objectNameMap.put("sampTotNoShipTanks", Constants.noOfContainers);
    objectNameMap.put("pulledTankerSamples", Constants.noOfQuarts);
    objectNameMap.put("sampTotNoShipTanks", Constants.noOfContainers);
    objectNameMap.put("pulledTankerSamples", Constants.noOfQuarts);
    objectNameMap.put("samplTotNoOfTankcarTruck", Constants.noOfContainers);
    objectNameMap.put("pulledTankCarTruckSamples", Constants.noOfQuarts);
    objectNameMap.put("noOfShipTankers", Constants.noOfContainers);
    objectNameMap.put("pulledTankerSamples", Constants.noOfQuarts);
    objectNameMap.put("sampTotNoOfBargeTanks", Constants.noOfContainers);
    objectNameMap.put("pulledBrgeSamples", Constants.noOfQuarts);
    objectNameMap.put("samplTotNoOfTankcarTruck", Constants.noOfContainers);
    objectNameMap.put("pulledTankCarTruckSamples", Constants.noOfQuarts);
    objectNameMap.put("sampTotNoShipTanks", Constants.noOfContainers);
    objectNameMap.put("pulledTankerSamples", Constants.noOfQuarts);
    objectNameMap.put("sampTotNoOfBargeTanks", Constants.noOfContainers);
    objectNameMap.put("pulledBrgeSamples", Constants.noOfQuarts);
    objectNameMap.put("sampTotNoOfBargeTanks", Constants.noOfContainers);
    objectNameMap.put("pulledBrgeSamples", Constants.noOfQuarts);
    objectNameMap.put("sampNoBargeTanks", Constants.noOfContainers);
    objectNameMap.put("pulledBrgeSamples", Constants.noOfQuarts);
    objectNameMap.put("samplTotNoOfShoreTanks", Constants.noOfContainers);
    objectNameMap.put("samplTotNoOfSamples", Constants.noOfQuarts);
    objectNameMap.put("sampNoBargeTanks", Constants.noOfContainers);
    objectNameMap.put("pulledBrgeSamples", Constants.noOfQuarts);
    objectNameMap.put("inputVal1", Constants.noOfContainers);
    objectNameMap.put("inputVal2", Constants.noOfQuarts);
    objectNameMap.put("noOfShipTankers", Constants.noOfContainers);
    objectNameMap.put("pulledTankerSamples", Constants.noOfQuarts);
    objectNameMap.put("noOfShipTankers", Constants.noOfContainers);
    objectNameMap.put("pulledTankerSamples", Constants.noOfQuarts);
    objectNameMap.put("sampNoShoreTanks", Constants.noOfContainers);
    objectNameMap.put("inputVal2", Constants.noOfQuarts);
    objectNameMap.put("samplTotNoOfShoreTanks", Constants.noOfContainers);
    objectNameMap.put("samplTotNoOfSamples", Constants.noOfQuarts);
  }

  /**
   * Return a boolean to tell if the objectName is in the Map or not.
   *
   * @param objectName a object name
   * @return a boolean value to tell if the object name is in the Map or not.
   **/
  public static boolean containsObjectName(String objectName)
  {
    if(objectName == null) return false;

    return objectNameMap.get(objectName) != null;
  }

  /**
  * @param cr takes a CalculatorResult
  * @return returns a CalculatorResult
  **/

  public CalculatorResult applyPricingLogic(CalculatorRequest cr)
  {
    ContractExpressionExt cee = (ContractExpressionExt)cr.getParameter(Constants.CONTRACT_EXPRESSION_EXT);
    if(cee == null) return null;

    Map params = new HashMap();
    for(int i=0; i<cee.getControlExts().size();i++)
    {
      ControlExt c = (ControlExt)cee.getControlExts().get(i);
      String objectName = c.getControl().getControlId().getObjectName();
      String mappedName = (String)objectNameMap.get(objectName);

      params.put(mappedName, c.getDataMap().get(objectName));
      //params.putAll(c.getDataMap());
    }

    int intQty = 0;
    double percentage = 0;
    double floatQty = 0.0;

    Double noOfContainers = (Double)params.get(Constants.noOfContainers);
    Number tmpNumber = (Number)params.get(Constants.noOfQuarts);
    if(tmpNumber != null)
    {
      intQty = tmpNumber.intValue();
      floatQty = tmpNumber.doubleValue();
    }

    String contractId = getContractId(
      cee.getContractExpression(),
      cr.getContract().getPriceBookId()
    );

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
      intQty,
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
      params.put(Constants.INT_DATA1, sr.getIntData1());

      Double unitsIncluded = sr.getUnitsIncluded();

      Double includedSamples = null;
      Integer intData0 = sr.getIntData0();
      if((intData0 != null) && (intData0 > 0))
      {
        includedSamples = new Double(intData0.doubleValue());
      }
      else
      {
        if( (unitsIncluded != null) && (unitsIncluded > 0)
          && (noOfContainers != null) && (noOfContainers > 0) )
        {
          includedSamples = noOfContainers * unitsIncluded;
        }
        else
        {
          includedSamples = new Double(0.0);
        }
      }
      params.put(Constants.includedSamples, includedSamples);

      Calculator calculator = getCalculator();
      if(calculator != null)
      {
        Double totalPrice = (Double)calculator.calculate(params);
        if(totalPrice == null) return null;

        CalculatorResult calculatorResult = new CalculatorResult();
        calculatorResult.setContractExpressionExt(cee);

        calculatorResult.getDataMap().putAll(params);

        calculatorResult.setParameter(Constants.BASE_PRICE, basePrice);
        calculatorResult.setParameter(Constants.UNIT_PRICE, unitPrice);
        calculatorResult.setParameter(Constants.CONTRACT_REF_NO, sr.getContractRef());
        calculatorResult.setParameter(Constants.INT_DATA2, sr.getServiceRateId().getIntData2());
        calculatorResult.setParameter(Constants.INT_DATA3, sr.getIntData3());

        totalPrice = NumberUtil.roundHalfUp(totalPrice, Constants.DEFAULT_ROUNDING);
        calculatorResult.setParameter(Constants.PRICE_BEFORE_DISCOUNT, totalPrice);

        if((unitsIncluded != null) && (unitsIncluded > 0))
          calculatorResult.setParameter(Constants.UNITS_INCLUDED, sr.getUnitsIncluded());
        else
          calculatorResult.setParameter(Constants.UNITS_INCLUDED, sr.getIntData0());

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
   * Returns an SamplingIncludesCalculator.
   *
   * @return a Calculator of instance SamplingIncludesCalculator.
   */
  protected Calculator getCalculator()
  {
    return CalculatorFactory.getCalculator(Constants.CALCULATOR_SAMPLING_INCLUDES);
  }
}
