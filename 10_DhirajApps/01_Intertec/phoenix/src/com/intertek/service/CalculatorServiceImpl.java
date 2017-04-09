package com.intertek.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

import com.intertek.entity.BranchCode;
import com.intertek.entity.CfgContract;
import com.intertek.entity.CurrencyRate;
import com.intertek.entity.Department;
import com.intertek.entity.Expression;
import com.intertek.entity.ExpressionGLCode;
import com.intertek.entity.InspectionRate;
import com.intertek.entity.LocationDiscount;
import com.intertek.entity.PriceBook;
import com.intertek.entity.ProductCode;
import com.intertek.entity.RB;
import com.intertek.entity.ServiceRate;
import com.intertek.util.Constants;

public class CalculatorServiceImpl extends BaseService implements CalculatorService
{
  public CfgContract getContractByContractId(String contractId, Date date)
  {
    if(date == null)
      date = new Date();
      List contracts = getDao().find(
      "from CfgContract c where c.cfgContractId.contractId = ? "
      + " and ? between c.cfgContractId.beginDate and c.endDate",
      new Object[] { contractId, date }
    );

    if(contracts != null && contracts.size() > 0) return (CfgContract)contracts.get(0);

    return null;
  }

  public PriceBook getCurrentPriceBook(
    String pbSeries,
    String currencyCD,
    Date nominationDate
  )
  {
    List pbs = getDao().find(
      "from PriceBook pb where pb.priceBookId.pbSeries = ? and pb.priceBookId.currencyCD = ? and ? between pb.priceBookId.beginDate and pb.endDate",
      new Object[] { pbSeries, currencyCD, nominationDate }
    );

    if(pbs != null && pbs.size() > 0) return (PriceBook)pbs.get(0);

    return null;
  }

  public ServiceRate getServiceRate(
    String contractId,
    String expressionId,
    String vesselType,
    String productGroupId,
    String serviceLevel,
    String location,
    int intQuantity, // not used
    double floatQuantity,
    String nominationDateStr
  )
  {
    List results = getDao().findByNamedSqlQuery(
      "getServiceRate_FN",
      new Object[] {
        contractId,
        expressionId,
        vesselType,
        productGroupId,
        serviceLevel,
        location,
        floatQuantity,
        1,
        nominationDateStr
      }
    );

    if(results.size() > 0) return (ServiceRate)results.get(0);

    return null;
  }

  public List getContractExpressions(
    String serviceName,
    String contractId,
    String priceBookId,
    String location,
    String nominationDateStr,
    String languageCD
  )
  {
    System.out.println("========= location = " + location);
    return getDao().findByNamedSqlQuery(
      "getContractExpressions_FN",
      new Object[] {
        serviceName,
        contractId,
        priceBookId,
        location,
        nominationDateStr,
        languageCD
      }
    );
  }

  public List getControls(
    String serviceName,
    String contractId,
    String priceBookId,
    String nominationDateStr,
    String languageCD
  )
  {
    return getDao().findByNamedSqlQuery(
      "getControlRBs_FN",
      new Object[] {
        serviceName,
        contractId,
        priceBookId,
        nominationDateStr,
        languageCD
      }
    );
  }

  public List getServices(
    String contractId,
    String priceBookId,
    String nominationDateStr,
    String languageCD
  )
  {
    return getDao().findByNamedSqlQuery(
      "getServices_FN",
      new Object[] {
        contractId,
        priceBookId,
        nominationDateStr,
        languageCD
      }
    );
  }


  public InspectionRate getInspectionRate(
    String contractId,
    String priceBookId,
    String expressionId,
    String vesselType,
    String productGroupId,
    String masterGroupId,
    String location,
    int intQuantity, // not used.
    double floatQuantity,
    int uomCode,
    String nominationDateStr
  )
  {
    List results = getDao().findByNamedSqlQuery(
      "getInspectionRate_FN",
      new Object[] {
        contractId,
        priceBookId,
        expressionId,
        vesselType,
        productGroupId,
        masterGroupId,
        location,
        floatQuantity,
        1,
        uomCode,
        nominationDateStr
      }
    );

    if(results.size() > 0) return (InspectionRate)results.get(0);

    return null;
  }

  public List getVesselTypes(
    String vesselTypeSet,
    String contractId,
    String priceBookId,
    String location,
    String nominationDateStr,
    String languageCD
  )
  {
    return getDao().findByNamedSqlQuery(
      "getVesselType_FN",
      new Object[] {
        vesselTypeSet,
        contractId,
        priceBookId,
        location,
        nominationDateStr,
        languageCD
      }
    );
  }

  public List getProductGroups(
    String productGroupSet,
    String contractId,
    String priceBookId,
    String vesselType,
    Integer uomCode,
    String location,
    String nominationDateStr,
    String languageCD
  )
  {
    return getDao().findByNamedSqlQuery(
      "getProductGroup_FN",
      new Object[] {
        productGroupSet,
        contractId,
        priceBookId,
        vesselType,
        uomCode,
        location,
        nominationDateStr,
        languageCD
      }
    );
  }

  public List getControlMaps(
    String contractId,
    String priceBookId,
    String serviceName,
    String expressionId
  )
  {
    List results = getDao().find(
      "from ControlMap cm where (cm.controlMapId.contractId = ? or cm.controlMapId.contractId = ? ) and cm.controlMapId.serviceName = ? and cm.controlMapId.expressionId = ?",
      new Object[] { priceBookId, Constants.MASTER, serviceName, expressionId }
    );

    //if(results.size() > 0) return results;

    //results = getDao().find(
    //  "from ControlMap cm where cm.controlMapId.contractId = ? and cm.controlMapId.serviceName = ? and cm.controlMapId.expressionId = ?",
    //  new Object[] { contractId, serviceName, expressionId }
    //);

    return results;

    //return getDao().find(
    //  "from ControlMap cm where cm.controlMapId.contractId = ? and cm.controlMapId.serviceName = ? and cm.controlMapId.expressionId = ?",
    //  new Object[] { Constants.MASTER, serviceName, expressionId }
    //);
  }

  public Expression getExpressionByExpressionId(String expressionId)
  {
    List expressions = getDao().find(
      "from Expression e where e.expressionId.expressionId = ?",
      new Object[] { expressionId }
    );

    if(expressions.size() > 0) return (Expression)expressions.get(0);

    return null;
  }

  public LocationDiscount getLocationDiscount(String contractId, String location, Date date)
  {
    List lds = getDao().find(
      "from LocationDiscount ld where ld.locationDiscountId.contractId = ? and ld.locationDiscountId.location = ? "
      + "and ? between ld.locationDiscountId.beginDate and ld.endDate",
      new Object[] { contractId, location, date }
    );

    if(lds.size() > 0) return (LocationDiscount)lds.get(0);

    lds = getDao().find(
      "from LocationDiscount ld where ld.locationDiscountId.contractId = ? and ld.locationDiscountId.location = ? "
      + "and ? between ld.locationDiscountId.beginDate and ld.endDate",
      new Object[] { contractId, "*", date }
    );

    if(lds.size() > 0) return (LocationDiscount)lds.get(0);

    return null;
  }

  public ExpressionGLCode getExpressionGLCode(String expressionId, String nominationType)
  {
    List glCodes = getDao().find(
      "from ExpressionGLCode eGLCode where eGLCode.expressionGLCodeId.expressionId = ? and eGLCode.expressionGLCodeId.nominationType = ?",
      new Object[] { expressionId, nominationType }
    );

    if(glCodes.size() > 0) return (ExpressionGLCode)glCodes.get(0);

    return null;
  }

  public Department getDepartment(String glCode)
  {
    List depts = getDao().find(
      "from Department d where d.GLCode = ?",
      new Object[] { glCode }
    );

    if(depts.size() > 0) return (Department)depts.get(0);

    return null;
  }

  public BranchCode getBranchCodeByBranchCode(String branchCode)
  {
    List bcs = getDao().find(
      "from BranchCode bc where bc.branchCode = ?",
      new Object[] { branchCode }
    );

    if(bcs.size() > 0) return (BranchCode)bcs.get(0);

    return null;
  }

public CurrencyRate getCurrencyRate(String fromCurrencyCD, String toCurrencyCD, Date date)
  {    
    List rates = getDao().find(
      "select cr from CurrencyRate cr where cr.currencyRateId.rateIndex = 'MODEL' "
      + " and cr.currencyRateId.term = 0 "
      + " and cr.currencyRateId.fromCurrency = ? "
      + " and cr.currencyRateId.toCurrency = ? "
      + " and cr.currencyRateId.type = 'CRRNT' "
      + " and cr.currencyRateId.effectiveDate = ("
      + "   select max(cr1.currencyRateId.effectiveDate) from CurrencyRate cr1 "
      + "   where cr.currencyRateId.rateIndex = cr1.currencyRateId.rateIndex"
      + "     and cr.currencyRateId.term = cr1.currencyRateId.term"
      + "     and cr.currencyRateId.fromCurrency = cr1.currencyRateId.fromCurrency"
      + "     and cr.currencyRateId.toCurrency = cr1.currencyRateId.toCurrency"
      + "     and cr.currencyRateId.type = cr1.currencyRateId.type"
      + "     and cr1.currencyRateId.effectiveDate <= ?)",
      new Object[] {fromCurrencyCD, toCurrencyCD, date}
    );

    if(rates.size() > 0) return (CurrencyRate)rates.get(0);

    return null;
  }
  /*for iTrack issue#114242-START on-03-07-09*/ 
 /* public CurrencyRate getCurrencyRate(String fromCurrencyCD, String toCurrencyCD, Date date)
  {
    List rates = getDao().find(
      "select cr from CurrencyRate cr where cr.currencyRateId.rateIndex = 'MODEL' "
      + " and cr.currencyRateId.term = 0 "
      + " and cr.currencyRateId.fromCurrency = ? "
      + " and cr.currencyRateId.toCurrency = ? "
      + " and cr.currencyRateId.effectiveDate = ("
      + "   select max(cr1.currencyRateId.effectiveDate) from CurrencyRate cr1 "
      + "   where cr.currencyRateId.rateIndex = cr1.currencyRateId.rateIndex"
      + "     and cr.currencyRateId.term = cr1.currencyRateId.term"
      + "     and cr.currencyRateId.fromCurrency = cr1.currencyRateId.fromCurrency"
      + "     and cr.currencyRateId.toCurrency = cr1.currencyRateId.toCurrency"
      + "     and cr1.currencyRateId.effectiveDate <= ?)",
      new Object[] {fromCurrencyCD, toCurrencyCD, date}
    );
    
    if(rates.size() > 0) return (CurrencyRate)rates.get(0);

    return null;
  }*/
  /*for iTrack issue#114242-END on-03-07-09*/ 
  public String getProductGroup(
    Double useGroupId,
    ExpressionGLCode eGLCode,
    String jobCode,
    String masterGroup
  )
  {
    if(eGLCode != null)
    {
      Integer useProdFlag = eGLCode.getUseProdFlag();
      if((useProdFlag != null) && (useProdFlag.intValue() == 0))
      {
        ProductCode productCode = getProductCode(jobCode, eGLCode.getNoProdCode());

        if(productCode != null) return productCode.getJobTypeNoProductCode();
      }
      else
      {
        return masterGroup;
      }
    }

    return null;
  }

  public ProductCode getProductCode(String jobCode, String noProdCode)
  {
    List results = getDao().find(
      "from ProductCode jc where jc.productCodeId.jobCode = ? and jc.productCodeId.noProdCode = ?",
      new Object[] { jobCode, noProdCode }
    );

    if(results.size() > 0) return (ProductCode)results.get(0);

    return null;
  }

  public double getCurrencyMultiplier(String contractCurrencyCD, String inputCurrencyCD, Date date)
  {
    HashMap rateMap = getCurrencyRateMap(contractCurrencyCD, inputCurrencyCD, date);

    Double rateMult = (Double)rateMap.get("RateMult");
    Double rateDiv = (Double)rateMap.get("RateDiv");

    if(rateMult != null && rateDiv != null)
      return rateMult/rateDiv;
    return 1.0;
  }

  /**
 * Name :getCurrencyRateMap
 * Date :Mar 5, 2009
 * Purpose : Returning a map containing RateMult and RateDiv as Double based on from currency and to currency and job finish date
 * @param fromCurrencyCD
 * @param toCurrencyCD
 * @param date
 * @return
 */
public HashMap<String, Double> getCurrencyRateMap(String fromCurrencyCD, String toCurrencyCD, Date date){
    HashMap rateMap = new HashMap();
    rateMap.put("RateMult", 1.0);
    rateMap.put("RateDiv", 1.0);

    if((fromCurrencyCD == null) || (toCurrencyCD == null) || (date == null)) return rateMap;

    if(fromCurrencyCD.equals(toCurrencyCD)) return rateMap;

    CurrencyRate rate = getCurrencyRate(fromCurrencyCD, toCurrencyCD, date);

    if(rate == null) return rateMap;

    Double rateMult = rate.getRateMult();
    Double rateDiv = rate.getRateDiv();
    if((rateMult == null) || (rateDiv == null)) return rateMap;
    if((rateMult.doubleValue() == 0) || (rateDiv.doubleValue() == 0)) return rateMap;

    rateMap.put("RateMult", rateMult.doubleValue());
    rateMap.put("RateDiv", rateDiv.doubleValue());
    return rateMap;
  }

  public RB getRbByRbKey(String rbKey)
  {
    List rbs = getDao().find(
      "from RB rb where rb.rbId.rbKey = ? ",
      new Object[] { rbKey }
    );

    if(rbs != null && rbs.size() > 0) return (RB)rbs.get(0);

    return null;
  }

  public List getCfgJobCodes()
  {
    return getDao().find("from CfgJobCode cjc", null);
  }

  public int getInspectionModelType(String contractCode, Date jobFinishDate)
  {
    if((contractCode == null) || (jobFinishDate == null)) return 0;  // model 1

    List results = getDao().find(
      "from RB rb where rb.rbId.rbKey = ? and ? between rb.rbId.beginDate and rb.rbId.endDate",
      new Object[] { contractCode + ".ContractOperations", jobFinishDate}
    );

    if(results.size() == 0) return 0; // model 1

    RB rb = (RB)results.get(0);
    if("com.itscb.pscb.contractoperations.ShellContractOperations".equals(rb.getRbValue())) return 2; // model 3

    return 1;  // model 2
  }
}
