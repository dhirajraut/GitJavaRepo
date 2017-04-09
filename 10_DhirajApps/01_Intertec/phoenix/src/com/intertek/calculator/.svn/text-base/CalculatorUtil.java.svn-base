package com.intertek.calculator;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.intertek.domain.AddJobContract;
import com.intertek.domain.AddJobContractProd;
import com.intertek.domain.AddJobContractVessel;
import com.intertek.entity.BranchCode;
import com.intertek.entity.CfgContract;
import com.intertek.entity.Department;
import com.intertek.entity.Expression;
import com.intertek.entity.ExpressionGLCode;
import com.intertek.entity.JobContract;
import com.intertek.entity.JobContractManualTest;
import com.intertek.entity.JobContractProd;
import com.intertek.entity.JobContractProdControl;
import com.intertek.entity.JobContractProductInspectionResult;
import com.intertek.entity.JobContractSlate;
import com.intertek.entity.JobContractTest;
import com.intertek.entity.JobContractVessel;
import com.intertek.entity.JobContractVesselControl;
import com.intertek.entity.JobOrder;
import com.intertek.entity.LocationDiscount;
import com.intertek.entity.Prebill;
import com.intertek.entity.PrebillSplit;
import com.intertek.entity.PriceBook;
import com.intertek.entity.PricingModel;
import com.intertek.entity.ProductGroup;
import com.intertek.entity.RB;
import com.intertek.entity.Service;
import com.intertek.entity.Slate;
import com.intertek.entity.Test;
import com.intertek.entity.User;
import com.intertek.entity.VesselType;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.CalculatorService;
import com.intertek.service.PrebillService;
import com.intertek.service.PricingModelService;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;
import com.intertek.util.SecurityUtil;
import com.intertek.util.NumberUtil;

/**
 * This class consists of static util methods that are used to calculate the price.
 *
 **/

public class CalculatorUtil
{
  private static Log log = LogFactory.getLog(CalculatorUtil.class);
  private static PrebillService prebillService = (PrebillService)ServiceLocator.getInstance().getBean("prebillService");

  /**
   * Get the pricing model by name
   *
   * @param name the pricing model name
   * @return the price model
   **/
  public static String getPricingModel(String name)
  {
    PricingModelService pricingModelService = (PricingModelService)ServiceLocator.getInstance().getBean("pricingModelService");
    PricingModel pm = pricingModelService.getPricingModel(name);

    if(pm != null) return pm.getModel();

    return null;
  }

  /**
   * Calculate the multiplier and escalator value
   *
   * @param cr a CalculatorRequest
   * @param expression a Expression
   * @param rateContractId the contract id
   * @param rateLocation the rate location
   * @param ld takes a LocationDiscount
   * @return the multiplier and escalator value
   **/
  public static Double getMultiplierAndEscalator(
    CalculatorRequest cr,
    Expression expression,
    String rateContractId,
    String rateLocation,
    LocationDiscount ld
  )
  {
    Double result = new Double(1);

    if(expression == null) return result;

    // expenses --> no multiplier and escalator
    if(Constants.ADM.equals(expression.getExpressionId().getBranchType())) return result;

    String contractId = (String)cr.getParameter(Constants.CONTRACT_ID);
    if(!contractId.equals(rateContractId)
      || Constants.STAR.equals(rateLocation))
    {
      // not zone specific price
      if(ld != null)
      {
        if(Constants.LAB.equals(expression.getExpressionId().getBranchType()))
        {
          Double labFactor = ld.getLabFactor();
          if((labFactor != null) && (labFactor.doubleValue() > 0))
          {
            result = result * labFactor.doubleValue();
          }
        }
        else if(Constants.OPS.equals(expression.getExpressionId().getBranchType()))
        {
          Double opsFactor = ld.getOpsFactor();
          if((opsFactor != null) && (opsFactor.doubleValue() > 0))
          {
            result = result * opsFactor.doubleValue();
          }
        }
      }
    }

    if(contractId.equals(rateContractId))
    {
      Double escalator = cr.getContract().getAnnualEscalator();
      if((escalator != null) && (escalator.doubleValue() > 0))
      {
        Date jobFinishDate = (Date)cr.getParameter(Constants.NOMINATION_DATE);
        Date escalatorDate = cr.getContract().getEscalatorDate();
        if((jobFinishDate != null) && (escalatorDate != null))
        {
          int yearsBetween = DateUtil.getYears(escalatorDate, jobFinishDate);

          if(yearsBetween > 0)
          {
            result = result * Math.pow(1 + escalator.doubleValue() * 0.01, yearsBetween);
          }
        }
      }
    }

    return result;
  }

  /**
   * Calculate the currency multiplier value
   *
   * @param cr a CalculatorRequest
   * @return the currency multiplier value
   **/
  public static double getCurrencyMultiplier(
    CalculatorRequest cr
  )
  {
    if(cr == null) return 1.0;

    CfgContract contract = cr.getContract();
    if(contract == null) return 1.0;

    Boolean overridable = contract.getOverridable();
    Double overrideCurrRate = (Double)cr.getParameter(Constants.OVERRIDE_CURRENCY_RATE);


    return getCurrencyMultiplier(
      contract.getCurrencyCD(),
      (String)cr.getParameter(Constants.CURRENCY_CD),
      (Date)cr.getParameter(Constants.NOMINATION_DATE),
      overridable,
      overrideCurrRate
    );
  }

  /**
   * Calculate the currency multiplier value
   *
   * If the overridable is true, and the overrideCurrRate is more than 0, the overrideCurrRate will be the final currency multiplier.
   *
   * Otherwise, the currency multiplier value will be obtained from currency rate table in database based on contract currency, transaction currency, and date.
   *
   * @param contractCurrencyCD the contract currency CD
   * @param transactionCurrencyCD the transaction currency CD
   * @param nominationDate the nomination date
   * @param overridable a flag to tell if the override currency rate can be used.
   * @param overrideCurrRate the override currency rate
   * @return the currency multiplier value
   **/
  public static double getCurrencyMultiplier(
    String contractCurrencyCD,
    String transactionCurrencyCD,
    Date nominationDate,
    Boolean overridable,
    Double overrideCurrRate
  )
  {
    if((overridable != null) && (overridable.booleanValue() == true))
    {
      if((overrideCurrRate != null) && (overrideCurrRate.doubleValue() > 0)) return overrideCurrRate.doubleValue();
    }

    CalculatorService calculatorService = (CalculatorService)ServiceLocator.getInstance().getBean("calculatorService");

    return calculatorService.getCurrencyMultiplier(
      contractCurrencyCD,
      transactionCurrencyCD,
      nominationDate
    );
  }

  /**
   * Add up the discountPercent to totalDiscount.
   *
   * If the discountPercent is null, or less than 0, the totalDiscount will be returned.
   *
   * If the discountPercent is more than 0, it will be added to totalDiscount.
   *
   * If the result is more than 100.0, the 100.0 will be returned.
   *
   * @param discountPercent takes a Double
   * @param totalDiscount takes a Double
   * @return returns a Double
   **/
  public static Double addUpDiscount(Double discountPercent, Double totalDiscount)
  {
    Double result = totalDiscount;

    if((discountPercent != null) && (discountPercent > 0))
    {
      if(totalDiscount <= 0) result = discountPercent;
      else result = totalDiscount + discountPercent;
    }

    if(result > 100.0) result = new Double(100.0);

    return result;
  }

  /**
   * Get the service type based on input Expression.
   *
   * @param expression an Expression
   * @return the service type
   **/
  public static String getServiceType(Expression expression)
  {
    if(expression == null) return null;

    if(Constants.ADM.equals(expression.getExpressionId().getBranchType()))
    {
      return expression.getExpenseBranchType();
    }

    return expression.getExpressionId().getBranchType();
  }

  /**
   * Get the branch code value based on expression object and branchCode object.
   *
   * @param expression an Expression
   * @param branchCode a BranchCode
   * @return the branch code value
   **/
  public static String getBranch(Expression expression, BranchCode branchCode)
  {
    if((expression == null) || (branchCode == null)) return null;

    if(Constants.OPS.equals(expression.getExpenseBranchType()))
    {
      return branchCode.getOpsCode();
    }

    return branchCode.getLabCode();
  }

  /**
   * Contruct the line description based on rbValue and an array of objects
   *
   * @param rbValue the resource bundle string.
   * @param objs an array of Objects
   * @return the formatted line description
   **/
  public static String constructDescription(String rbValue, Object[] objs)
  {
    if(rbValue == null) return null;

    MessageFormat format = new MessageFormat(rbValue);
    return format.format(objs);
  }

  /**
   * Get a list of services based on parent service id and input component type.
   *
   * @param services a list of services to be selected.
   * @param parentServiceId the parent service id.
   * @param inputComponentType the input component type.
   * @param serviceLevel the service level.
   * @return a list of selected services.
   **/
  public static List getServicesByParentServiceIdAndInputComponentType(
    List services,
    String parentServiceId,
    String inputComponentType,
    String serviceLevel
  )
  {
    List results = new ArrayList();

    if((parentServiceId == null) || (services == null))
    {
      return results;
    }

    for(int i=0; i<services.size(); i++)
    {
      Service service = (Service)services.get(i);

      if(parentServiceId.equals(service.getServiceId().getParentServiceId()))
      {
        if((inputComponentType == null) || inputComponentType.equals(service.getInputComponentType()))
        {
          if((serviceLevel == null) || serviceLevel.equals(service.getServiceLevel()))
          {
            results.add(service);
          }
        }
      }
    }

    return results;
  }

  /**
   * Calculate the price for a job contract test.
   *
   * @param jobContractTest the JobContractTest
   * @return the jobContractTest with the right price.
   **/
  public static JobContractTest calculateTest(
    JobContractTest jobContractTest
  )
  {
    return calculateTest(jobContractTest, null, null);
  }

  /**
   * Calculate the price for a job contract test.
   *
   * @param jobContractTest the JobContractTest.
   * @param rb the RB object.
   * @param contract the CfgContract object.
   * @return the jobContractTest with the right price.
  **/
  public static JobContractTest calculateTest(
    JobContractTest jobContractTest,
    RB rb,
    CfgContract contract
  )
  {
    if(jobContractTest == null) return null;

    Test test = jobContractTest.getTest();
    if(test == null) return null;

    JobContractProd jobContractProd = jobContractTest.getJobContractProd();
    if(jobContractProd == null) return null;

    JobContractVessel jobContractVessel = jobContractProd.getJobContractVessel();
    if(jobContractVessel == null) return null;

    JobContract jobContract = jobContractVessel.getJobContract();
    if(jobContract == null) return null;

    JobOrder jobOrder = jobContract.getJobOrder();
    if(jobOrder == null) return null;

    String location = jobContract.getZone();

    CalculatorService calculatorService = (CalculatorService)ServiceLocator.getInstance().getBean("calculatorService");
    if(rb == null)
    {
      rb = calculatorService.getRbByRbKey(Constants.Test);
    }

    if(contract == null)
    {
      contract = calculatorService.getContractByContractId(
        jobContract.getContractCode(),
        jobOrder.getJobFinishDate()
      );
      if(contract != null)
      {
        String priceBookId = contract.getPriceBookId();
        if(Constants.CURRENT.equals(priceBookId))
        {
          PriceBook pb = calculatorService.getCurrentPriceBook(
            contract.getPbSeries(),
            contract.getCurrencyCD(),
            jobOrder.getJobFinishDate()
          );
          if(pb != null) contract.setPriceBookId(pb.getPriceBookId().getPriceBookId());
        }
      }
    }

    Double qty = jobContractTest.getQuantity();
    if((qty != null) && (qty > 0))
    {
      CalculatorRequest cr = new CalculatorRequest();
      cr.setParameter(Constants.CONTRACT_ID, jobContract.getContractCode());
      cr.setParameter(Constants.TEST_ID, test.getTestId());
      cr.setParameter(
        Constants.LOCATION,
        location != null ? location : "*"
      );
      cr.setParameter(
        Constants.NOMINATION_DATE_STR,
        DateUtil.formatDate(jobOrder.getJobFinishDate(), "yyyyMMdd")
      );
      cr.setParameter(
        Constants.NOMINATION_DATE,
        jobOrder.getJobFinishDate()
      );
      cr.setParameter(Constants.QUANTITY, qty);
      cr.setParameter(Constants.DESCRIPTION, test.getTestDescription());
      cr.setParameter(Constants.RB, rb);

      cr.setContract(contract);

      String masterGroupId = jobContractProd.getProductGroupMaster();
      cr.setParameter(
        Constants.MASTER_GROUP_ID,
        masterGroupId != null ? masterGroupId : "*"
      );
      cr.setParameter(Constants.JOB_TYPE, jobOrder.getJobType());
      cr.setParameter(Constants.BRANCH_CODE, jobOrder.getBranchName());
      cr.setParameter(Constants.JOB_CODE, jobContract.getProductType());
      cr.setParameter(Constants.CURRENCY_CD, jobContract.getTransactionCurrencyCd());
      cr.setParameter(Constants.OVERRIDE_CURRENCY_RATE, jobContract.getOverrideCurrRate());

      List results = CalculatorManager.calculateTest(cr);

      for(int j=0; j<results.size(); j++)
      {
        CalculatorResult calculatorResult = (CalculatorResult)results.get(j);
        if(calculatorResult != null)
        {
          jobContractTest.setContractRefNo(
            (String)calculatorResult.getParameter(Constants.CONTRACT_REF_NO)
          );

          Prebill prebill = null;
          Iterator it = jobContractTest.getPrebills().iterator();
          if(it.hasNext())
          {
            prebill = (Prebill)it.next();
          }

          if(prebill == null)
          {
            prebill = new Prebill();
            prebill.setJobContract(jobContract);
            if(jobContract != null){
                prebill.setRateMult(jobContract.getRateMult());
                prebill.setRateDiv(jobContract.getRateDiv());
              prebill.setBaseCurrencyCd(jobContract.getBaseCurrencyCd());
              prebill.setCurrencyCd(jobContract.getTransactionCurrencyCd());
            }
            prebill.setJobContractTest(jobContractTest);
            jobContractTest.getPrebills().add(prebill);
          }

          populatePrebillFromCalculatorResult(
            calculatorResult,
            prebill,
            Constants.CALCULATOR_TEST_DESCRIPTOR
          );

          return jobContractTest;
        }
      }
    }

    return null;
  }

  /**
   * Calculate the price for a job contract slate.
   *
   * @param jobContractSlate the JobContractSlate
   * @return the jobContractSlate with the right price.
   **/
  public static JobContractSlate calculateSlate(
    JobContractSlate jobContractSlate
  )
  {
    return calculateSlate(jobContractSlate, null, null);
  }

  /**
   * Calculate the price for a job contract slate.
   *
   * @param jobContractSlate the JobContractSlate.
   * @param rb the RB object.
   * @param contract the CfgContract object.
   * @return the jobContractSlate with the right price.
  **/
  public static JobContractSlate calculateSlate(
    JobContractSlate jobContractSlate,
    RB rb,
    CfgContract contract
  )
  {
    if(jobContractSlate == null) return null;

    Slate slate = jobContractSlate.getSlate();
    if(slate == null) return null;

    JobContractProd jobContractProd = jobContractSlate.getJobContractProd();
    if(jobContractProd == null) return null;

    JobContractVessel jobContractVessel = jobContractProd.getJobContractVessel();
    if(jobContractVessel == null) return null;

    JobContract jobContract = jobContractVessel.getJobContract();
    if(jobContract == null) return null;

    JobOrder jobOrder = jobContract.getJobOrder();
    if(jobOrder == null) return null;

    String location = jobContract.getZone();

    CalculatorService calculatorService = (CalculatorService)ServiceLocator.getInstance().getBean("calculatorService");
    if(rb == null)
    {
      rb = calculatorService.getRbByRbKey(Constants.Slate);
    }

    if(contract == null)
    {
      contract = calculatorService.getContractByContractId(
        jobContract.getContractCode(),
        jobOrder.getJobFinishDate()
      );
      if(contract != null)
      {
        String priceBookId = contract.getPriceBookId();
        if(Constants.CURRENT.equals(priceBookId))
        {
          PriceBook pb = calculatorService.getCurrentPriceBook(
            contract.getPbSeries(),
            contract.getCurrencyCD(),
            jobOrder.getJobFinishDate()
          );
          if(pb != null) contract.setPriceBookId(pb.getPriceBookId().getPriceBookId());
        }
      }
    }

    Double qty = jobContractSlate.getQuantity();
    if((qty != null) && (qty > 0))
    {
      CalculatorRequest cr = new CalculatorRequest();
      cr.setParameter(Constants.CONTRACT_ID, jobContract.getContractCode());
      cr.setParameter(Constants.SLATE_ID, slate.getSlateId());
      cr.setParameter(
        Constants.LOCATION,
        location != null ? location : "*"
      );
      cr.setParameter(
        Constants.NOMINATION_DATE_STR,
        DateUtil.formatDate(jobOrder.getJobFinishDate(), "yyyyMMdd")
      );
      cr.setParameter(
        Constants.NOMINATION_DATE,
        jobOrder.getJobFinishDate()
      );
      cr.setParameter(Constants.QUANTITY, qty);
      cr.setParameter(Constants.DESCRIPTION, slate.getSlateDescription());
      cr.setParameter(Constants.RB, rb);

      cr.setContract(contract);

      String masterGroupId = jobContractProd.getProductGroupMaster();
      cr.setParameter(
        Constants.MASTER_GROUP_ID,
        masterGroupId != null ? masterGroupId : "*"
      );
      cr.setParameter(Constants.JOB_TYPE, jobOrder.getJobType());
      cr.setParameter(Constants.BRANCH_CODE, jobOrder.getBranchName());
      cr.setParameter(Constants.JOB_CODE, jobContract.getProductType());
      cr.setParameter(Constants.CURRENCY_CD, jobContract.getTransactionCurrencyCd());
      cr.setParameter(Constants.OVERRIDE_CURRENCY_RATE, jobContract.getOverrideCurrRate());

      List results = CalculatorManager.calculateSlate(cr);

      for(int j=0; j<results.size(); j++)
      {
        CalculatorResult calculatorResult = (CalculatorResult)results.get(j);
        if(calculatorResult != null)
        {
          jobContractSlate.setContractRefNo(
            (String)calculatorResult.getParameter(Constants.CONTRACT_REF_NO)
          );

          Prebill prebill = null;
          Iterator it = jobContractSlate.getPrebills().iterator();
          if(it.hasNext())
          {
            prebill = (Prebill)it.next();
          }

          if(prebill == null)
          {
            prebill = new Prebill();
            prebill.setJobContract(jobContract);
            if(jobContract != null){
              prebill.setBaseCurrencyCd(jobContract.getBaseCurrencyCd());
              prebill.setCurrencyCd(jobContract.getTransactionCurrencyCd());
              prebill.setRateMult(jobContract.getRateMult());
                prebill.setRateDiv(jobContract.getRateDiv());
            }
            prebill.setJobContractSlate(jobContractSlate);
            jobContractSlate.getPrebills().add(prebill);
          }

          populatePrebillFromCalculatorResult(
            calculatorResult,
            prebill,
            Constants.CALCULATOR_SLATE_DESCRIPTOR
          );

          return jobContractSlate;
        }
      }
    }

    return null;
  }

  /**
   * Calculate the price for a job contract manual test.
   *
   * @param jobContractManualTest the JobContractManualTest.
   * @return the jobContractManualTest with the right price.
  **/
  public static JobContractManualTest calculateManualTest(
    JobContractManualTest jobContractManualTest
  )
  {
    return calculateManualTest(jobContractManualTest, null, null);
  }

  /**
   * Calculate the price for a job contract manual test.
   *
   * @param jobContractManualTest the JobContractManualTest.
   * @param rb the RB object.
   * @param contract the CfgContract object.
   * @return the jobContractManualTest with the right price.
  **/
  public static JobContractManualTest calculateManualTest(
    JobContractManualTest jobContractManualTest,
    RB rb,
    CfgContract contract
  )
  {
    if(jobContractManualTest == null) return null;

    JobContractProd jobContractProd = jobContractManualTest.getJobContractProd();
    if(jobContractProd == null) return null;

    JobContractVessel jobContractVessel = jobContractProd.getJobContractVessel();
    if(jobContractVessel == null) return null;

    JobContract jobContract = jobContractVessel.getJobContract();
    if(jobContract == null) return null;

    JobOrder jobOrder = jobContract.getJobOrder();
    if(jobOrder == null) return null;

    CalculatorService calculatorService = (CalculatorService)ServiceLocator.getInstance().getBean("calculatorService");
    if(rb == null)
    {
      rb = calculatorService.getRbByRbKey(Constants.Test);
    }

    if(contract == null)
    {
      contract = calculatorService.getContractByContractId(
        jobContract.getContractCode(),
        jobOrder.getJobFinishDate()
      );
      if(contract != null)
      {
        String priceBookId = contract.getPriceBookId();
        if(Constants.CURRENT.equals(priceBookId))
        {
          PriceBook pb = calculatorService.getCurrentPriceBook(
            contract.getPbSeries(),
            contract.getCurrencyCD(),
            jobOrder.getJobFinishDate()
          );
          if(pb != null) contract.setPriceBookId(pb.getPriceBookId().getPriceBookId());
        }
      }
    }

    Double qty = jobContractManualTest.getQuantity();
    Double totalPrice = jobContractManualTest.getTotalPrice();
    if((totalPrice != null) && (totalPrice > 0) && (qty != null) && (qty > 0))
    {
      if(rb != null)
      {
        Prebill prebill = null;
        Iterator it = jobContractManualTest.getPrebills().iterator();
        if(it.hasNext())
        {
          prebill = (Prebill)it.next();
        }

        if(prebill == null)
        {
          prebill = new Prebill();
          prebill.setJobContract(jobContract);
          if(jobContract != null){
            prebill.setBaseCurrencyCd(jobContract.getBaseCurrencyCd());
            prebill.setCurrencyCd(jobContract.getTransactionCurrencyCd());
            prebill.setRateMult(jobContract.getRateMult());
              prebill.setRateDiv(jobContract.getRateDiv());
          }
          prebill.setJobContractManualTest(jobContractManualTest);
          jobContractManualTest.getPrebills().add(prebill);
        }

        double netPrice = totalPrice * qty;

        Expression expression = calculatorService.getExpressionByExpressionId(
          Constants.TEST
        );

        //String location = jobContract.getZone();
        //location = location != null ? location : "*";

        //LocationDiscount ld = calculatorService.getLocationDiscount(
        //  contract.getCfgContractId().getContractId(),
        //  location,
        //  jobOrder.getJobFinishDate()
        //);

        //Double multiplierEscalator = CalculatorUtil.getMultiplierAndEscalatorForTest(
        //  contract,
        //  expression,
        //  true,
        //  ld,
        //  jobOrder.getJobFinishDate()
        //);

        //netPrice = netPrice * multiplierEscalator.doubleValue();
        Integer roundingDigits = prebillService.getDecimalDigitsByCurrency(jobContract.getTransactionCurrencyCd());


        if(roundingDigits!=null){
          netPrice = NumberUtil.roundHalfUp(netPrice, roundingDigits);
          if(prebill.getRateMult()!=null && prebill.getRateDiv()!=null){
            prebill.setBaseUnitPrice(NumberUtil.roundHalfUp( netPrice * prebill.getRateMult() / prebill.getRateDiv(), roundingDigits));
            prebill.setBaseNetPrice(NumberUtil.roundHalfUp( netPrice * prebill.getRateMult() / prebill.getRateDiv(), roundingDigits));
          }
        }else{
          netPrice = NumberUtil.roundHalfUp(netPrice, 2);
          if(prebill.getRateMult()!=null && prebill.getRateDiv()!=null){
            prebill.setBaseUnitPrice(NumberUtil.roundHalfUp( netPrice * prebill.getRateMult() / prebill.getRateDiv(), 2));
            prebill.setBaseNetPrice(NumberUtil.roundHalfUp( netPrice * prebill.getRateMult() / prebill.getRateDiv(), 2));
          }
        }
        prebill.setUnitPrice(netPrice);
        prebill.setNetPrice(netPrice);
        
        //101771 START
        String testDescription = jobContractManualTest.getTestDescription();
        if(null == testDescription){
        	testDescription = "";
        }
        //101771 END

     // START : #130297 
        /*Object[] objs = new Object[] {
          jobContractManualTest.getTestId(),
          testDescription,//101771 changed
          jobContractManualTest.getQuantity(),
          totalPrice,
          ""
        };*/
        Object[] objs = new Object[] {
                jobContractManualTest.getTestId(),
                testDescription,//101771 changed
                jobContractManualTest.getQuantity(),
                String.valueOf(totalPrice),
                ""
              };
     // END : #130297
        
        String rbValue = rb.getRbValue();

        MessageFormat format = new MessageFormat(rbValue);

        jobContractManualTest.setLineDescription(format.format(objs));

        prebill.setLineDescription(jobContractManualTest.getLineDescription());        
        //START: ITrack #138225 - setting quantity        
        prebill.setQuantity(jobContractManualTest.getQuantity());
        //END: ITrack #138225 - setting quantity

        String masterGroupId = jobContractProd.getProductGroupMaster();
        Map accountInfoMap = CalculatorUtil.getAccountInfo(
          expression,
          jobOrder.getJobType(),
          jobContract.getProductType(),
          jobOrder.getBranchName(),
          masterGroupId != null ? masterGroupId : "*",
          null
        );

        prebill.setAccount((String)accountInfoMap.get(Constants.GL_CODE));
        prebill.setDeptid((String)accountInfoMap.get(Constants.DEPARTMENT));
        prebill.setServiceType((String)accountInfoMap.get(Constants.SERVICE_TYPE));
        prebill.setPrimaryBranchCd((String)accountInfoMap.get(Constants.BRANCH_CODE));
        prebill.setProductGroup((String)accountInfoMap.get(Constants.PRODUCT_GROUP));
        prebill.setEditable(true);

        createPrebillSplitForPrebill(prebill);

        return jobContractManualTest;
      }
    }

    return null;
  }

  /**
   * Get the JobContractProductInspectionResult from JobContractProd with the expression id.
   *
   * @param jobContractProd a JobContractProd
   * @param expressionId the expression id
   * @return the JobContractProductInspectionResult matched with the input expression id.
   **/
  public static JobContractProductInspectionResult getJobContractProductInspectionResultByExpressionId(
    JobContractProd jobContractProd,
    String expressionId
  )
  {
    if((jobContractProd == null) || (expressionId == null)) return null;

    Iterator it = jobContractProd.getResults().iterator();
    while(it.hasNext())
    {
      JobContractProductInspectionResult jResult = (JobContractProductInspectionResult)it.next();
      if(expressionId.equals(jResult.getExpressionId())) return jResult;
    }

    return null;
  }

  /**
   * Get the JobContractVesselControl from a list of JobContractVesselControls by matching the object name of the input controlExt.
   *
   * @param controlExt the ControlExt
   * @param jControls a List of JobContractVesselControls
   * @return the JobContractVesselControl matched with the controlExt by object name.
   **/
  public static JobContractVesselControl getJobContractVesselControl(
    ControlExt controlExt,
    List jControls
  )
  {
    if((controlExt == null) || (jControls == null)) return null;

    JobContractVesselControl jControl = null;
    String objectName = controlExt.getControl().getControlId().getObjectName();

    for(int i=0; i<jControls.size(); i++)
    {
      JobContractVesselControl tmpControl = (JobContractVesselControl)jControls.get(i);
      if(tmpControl.getObjectName().equals(objectName))
      {
        jControl = tmpControl;
        break;
      }
    }

    if(jControl == null)
    {
      jControl = new JobContractVesselControl();
      jControl.setQuestionId(controlExt.getControl().getControlId().getQuestionId());
      jControl.setObjectName(objectName);
    }

    jControl.setInputValue0(controlExt.getDataInput());

    return jControl;
  }

  /**
   * Delete unused JobContractVesselControls from the oldJobContractVesselControls set which doesn't match the object name in the list of controlExts.
   *
   * @param controlExts a list of controlExts
   * @param oldJobContractVesselControls a set of oldJobContractVesselControls to be cleaned.
   **/
  public static void deleteUnusedJobContractVesselControls(
    List controlExts,
    Set oldJobContractVesselControls
  )
  {
    if((oldJobContractVesselControls != null) && (oldJobContractVesselControls.size() > 0))
    {
      Map newControlMap = new HashMap();

      for(int i=0; i<controlExts.size(); i++)
      {
        ControlExt controlExt = (ControlExt)controlExts.get(i);
        newControlMap.put(controlExt.getControl().getControlId().getObjectName(), controlExt);
      }

      Iterator it = oldJobContractVesselControls.iterator();
      while(it.hasNext())
      {
        JobContractVesselControl jControl = (JobContractVesselControl)it.next();
        String objectName = jControl.getObjectName();
        ControlExt controlExt = (ControlExt)newControlMap.get(objectName);
        if(controlExt == null)
        {
          it.remove();
        }
      }
    }
  }

  /**
   * Get JobContractProdControl from a list of JobContractProdControls with matched object name in input controlExt.
   *
   * @param controlExt the ControlExt
   * @param jControls a list of JobContractProdControls
   * @return the JobContractProdControl with matched object name from the input controlExt.
   **/
  public static JobContractProdControl getJobContractProdControl(
    ControlExt controlExt,
    List jControls
  )
  {
    if((controlExt == null) || (jControls == null)) return null;

    JobContractProdControl jControl = null;
    String objectName = controlExt.getControl().getControlId().getObjectName();

    for(int i=0; i<jControls.size(); i++)
    {
      JobContractProdControl tmpControl = (JobContractProdControl)jControls.get(i);
      if(tmpControl.getObjectName().equals(objectName))
      {
        jControl = tmpControl;
        break;
      }
    }

    if(jControl == null)
    {
      jControl = new JobContractProdControl();
      jControl.setQuestionId(controlExt.getControl().getControlId().getQuestionId());
      jControl.setObjectName(objectName);
    }

    jControl.setInputValue0(controlExt.getDataInput());

    return jControl;
  }

  /**
   * Delete unused JobContractProdControls from the oldJobContractProdControls set which doesn't match the object name in the list of controlExts.
   *
   * @param controlExts a list of controlExts
   * @param oldJobContractProdControls a set of oldJobContractProdControls to be cleaned.
   **/
  public static void deleteUnusedJobContractProdControls(
    List controlExts,
    Set oldJobContractProdControls
  )
  {
    if((oldJobContractProdControls != null) && (oldJobContractProdControls.size() > 0))
    {
      Map newControlMap = new HashMap();

      for(int i=0; i<controlExts.size(); i++)
      {
        ControlExt controlExt = (ControlExt)controlExts.get(i);
        newControlMap.put(controlExt.getControl().getControlId().getObjectName(), controlExt);
      }

      Iterator it = oldJobContractProdControls.iterator();
      while(it.hasNext())
      {
        JobContractProdControl jControl = (JobContractProdControl)it.next();
        String objectName = jControl.getObjectName();
        ControlExt controlExt = (ControlExt)newControlMap.get(objectName);
        if(controlExt == null)
        {
          it.remove();
        }
      }
    }
  }

  /**
   * Get selected ProductGroup from a list of productGroups with the group id the same as selectedGroup.
   *
   * @param productGroups a list of ProductGroups.
   * @param selectedGroup the group id to be matched.
   * @return the matched ProductGroup
   **/
  public static ProductGroup getSelectedProductGroup(
    List productGroups,
    String selectedGroup
  )
  {
    if((productGroups == null) || (selectedGroup == null)) return null;

    for(int i=0; i<productGroups.size(); i++)
    {
      ProductGroup pg = (ProductGroup)productGroups.get(i);
      if(selectedGroup.equals(pg.getProductGroupId().getGroupId()))
      {
        return pg;
      }
    }

    return null;
  }

  /**
   * Get selected VesselType from a list of VesselTypes with the selectedType.
   *
   * @param vesselTypes a list of VesselTypes.
   * @param selectedType the selected vessel type.
   * @return the matched VesselType
   **/
  public static VesselType getSelectedVesselType(
    List vesselTypes,
    String selectedType
  )
  {
    if((vesselTypes == null) || (selectedType == null)) return null;

    for(int i=0; i<vesselTypes.size(); i++)
    {
      VesselType vt = (VesselType)vesselTypes.get(i);
      if(selectedType.equals(vt.getVesselTypeId().getVesselType()))
      {
        return vt;
      }
    }

    return null;
  }

  /**
   * Get the additional vessel flag from JobContractVessel
   *
   * @param jVessel the JobContractVessel
   * @return the additional vessel flag
   **/
  public static boolean getAdditionalVesselFlag(JobContractVessel jVessel)
  {
    if(jVessel == null) return false;

    Iterator it = jVessel.getControls().iterator();
    while(it.hasNext())
    {
      JobContractVesselControl jControl = (JobContractVesselControl)it.next();
      if(Constants.ADDITIONAL_VESSEL.equals(jControl.getObjectName()))
      {
        String dataInput = jControl.getInputValue0();
        if((dataInput != null) && Constants.Yes.equals(dataInput)) return true;
      }
    }

    return false;
  }

  /**
   * Create a quantity map from a map of parameters and uom code.
   *
   * @param params a map of parameters
   * @param uomCode the uom code
   * @return a newly created Map.
   **/
  public static Map getQtyMap(Map params, int uomCode)
  {
    Map map = new HashMap();

    map.put(Constants.QUANTITY, params.get(Constants.noOfUOM));
    map.put(Constants.QUANTITY1, params.get(Constants.noOfUOM1));
    map.put(Constants.QUANTITY2, params.get(Constants.noOfUOM2));

    return map;
  }

  /**
   * Get account information.
   *
   * @param expressionId the expression id
   * @param jobType the job type
   * @param jobCode the job code
   * @param branchCode the branch code
   * @param masterGroupId the master group id
   * @param userGroupId the user group id
   * @return a Map of parameters with the account information
   **/
  public static Map getAccountInfo(
    String expressionId,
    String jobType,
    String jobCode,
    String branchCode,
    String masterGroupId,
    Double userGroupId
  )
  {
    CalculatorService calculatorService = (CalculatorService)ServiceLocator.getInstance().getBean("calculatorService");
    Expression expression = calculatorService.getExpressionByExpressionId(expressionId);
    return getAccountInfo(
      expression,
      jobType,
      jobCode,
      branchCode,
      masterGroupId,
      userGroupId
    );
  }

  /**
   * Get account information.
   *
   * @param expressionthe expression object
   * @param jobType the job type
   * @param jobCode the job code
   * @param branchCode the branch code
   * @param masterGroupId the master group id
   * @param userGroupId the user group id
   * @return a Map of parameters with the account information
   **/
  public static Map getAccountInfo(
    Expression expression,
    String jobType,
    String jobCode,
    String branchCode,
    String masterGroupId,
    Double userGroupId
  )
  {
    CalculatorService calculatorService = (CalculatorService)ServiceLocator.getInstance().getBean("calculatorService");
    Map results = new HashMap();

    if(expression != null)
    {
      ExpressionGLCode eGLCode = calculatorService.getExpressionGLCode(
        expression.getExpressionId().getExpressionId(),
        jobType
      );
      if(eGLCode != null)
      {
        results.put(Constants.GL_CODE, eGLCode.getGLCode());
        Department dept = calculatorService.getDepartment(eGLCode.getGLCode());
        if(dept != null)
        {
          results.put(Constants.DEPARTMENT, dept.getDepartmentCode());
        }
      }

      results.put(Constants.SERVICE_TYPE, CalculatorUtil.getServiceType(expression));

      results.put(
        Constants.PRODUCT_GROUP,
        calculatorService.getProductGroup(
          userGroupId,
          eGLCode,
          jobCode,
          masterGroupId
        )
      );
    }

    BranchCode branchCodeObj = calculatorService.getBranchCodeByBranchCode(branchCode);
    results.put(Constants.BRANCH_CODE, CalculatorUtil.getBranch(expression, branchCodeObj));

    return results;
  }

  /**
   * Populate the prebill from the calculator result.
   *
   * @param calculatorResult the calculator result
   * @param prebill the Prebill
   * @param descriptorName the descriptor name.
  **/
  public static void populatePrebillFromCalculatorResult(
    CalculatorResult calculatorResult,
    Prebill prebill,
    String descriptorName
  )
  {
    if((calculatorResult == null) || (prebill == null)) return;

    Double unitPrice = (Double)calculatorResult.getParameter(Constants.UNIT_PRICE);
    if(unitPrice != null)
    {
      prebill.setUnitPrice(unitPrice.doubleValue());
      if(prebill.getRateMult()!=null && prebill.getRateDiv()!=null)
        prebill.setBaseUnitPrice(unitPrice.doubleValue() * prebill.getRateMult() / prebill.getRateDiv());
    }
    else
    {
      prebill.setUnitPrice(0.0);
      prebill.setBaseUnitPrice(0.0);
    }

    Double priceBeforeDiscount = (Double)calculatorResult.getParameter(Constants.PRICE_BEFORE_DISCOUNT);
    if(priceBeforeDiscount != null)
    {
      prebill.setUnitPrice(priceBeforeDiscount.doubleValue());
      if(prebill.getRateMult()!=null && prebill.getRateDiv()!=null)
        prebill.setBaseUnitPrice(prebill.getUnitPrice() * prebill.getRateMult() / prebill.getRateDiv());
    }

    Double totalPrice = calculatorResult.getTotalPrice();
    if(totalPrice != null)
    {
      Integer roundingDigits = prebillService.getDecimalDigitsByCurrency(prebill.getJobContract().getTransactionCurrencyCd());
      if(roundingDigits != null){
        prebill.setNetPrice(NumberUtil.roundHalfUp(totalPrice.doubleValue(), roundingDigits));
        if(prebill.getRateMult()!=null && prebill.getRateDiv()!=null)
          prebill.setBaseNetPrice(NumberUtil.roundHalfUp( totalPrice.doubleValue() * prebill.getRateMult() / prebill.getRateDiv(), roundingDigits));
      }else{
        prebill.setNetPrice(NumberUtil.roundHalfUp(totalPrice.doubleValue(), 2));
        if(prebill.getRateMult()!=null && prebill.getRateDiv()!=null)
          prebill.setBaseNetPrice(NumberUtil.roundHalfUp( totalPrice.doubleValue() * prebill.getRateMult() / prebill.getRateDiv(), 2));

      }
    }
    else
    {
      prebill.setNetPrice(0.0);
      prebill.setBaseNetPrice(0.0);
    }

    Double discount = calculatorResult.getDiscount();
    if(discount != null)
    {
      prebill.setDiscountPct(discount.doubleValue());
    }
    else
    {
      prebill.setDiscountPct(0.0);
    }

    Boolean editable = (Boolean)calculatorResult.getParameter(Constants.IS_EDITABLE);
    prebill.setEditable(editable);

    String lineDescription = calculatorResult.constructDescription(descriptorName);
    prebill.setLineDescription(lineDescription);    
    //START: ITrack #138225 - setting quantity
    if(null != calculatorResult.getParameter(Constants.QUANTITY) &&
    		calculatorResult.getParameter(Constants.QUANTITY).toString().trim().length() > 0 ){
    	prebill.setQuantity(new Double(calculatorResult.getParameter(Constants.QUANTITY).toString().trim()));
    }
    //END: ITrack #138225 - setting quantity


    prebill.setAccount((String)calculatorResult.getParameter(Constants.GL_CODE));
    prebill.setDeptid((String)calculatorResult.getParameter(Constants.DEPARTMENT));
    prebill.setServiceType((String)calculatorResult.getParameter(Constants.SERVICE_TYPE));
    prebill.setPrimaryBranchCd((String)calculatorResult.getParameter(Constants.BRANCH_CODE));
    prebill.setProductGroup((String)calculatorResult.getParameter(Constants.PRODUCT_GROUP));

    createPrebillSplitForPrebill(prebill);
  }

  /**
   * Create a PrebillSplit for the Prebill.
   *
   * @param prebill the Prebill
   **/
  public static void createPrebillSplitForPrebill(Prebill prebill)
  {
    if(prebill == null) return;

    if(prebill.getPrebillSplits().size() > 0) return;

    JobContract jobContract = prebill.getJobContract();
    if(jobContract != null)
    {
      JobOrder jobOrder = jobContract.getJobOrder();
      if(jobOrder != null)
      {
         PrebillSplit prebillSplit = new PrebillSplit();
         prebillSplit.setPrebill(prebill);
         //prebillSplit.setBranchName(jobOrder.getBranchName());
         //Changed for issue 42284
         prebillSplit.setBranchName(prebill.getPrimaryBranchCd());
         prebillSplit.setAllocPct(100.0);
         prebillSplit.setPrimaryInd(true);
         prebillSplit.setAllocAmt(prebill.getNetPrice());
         prebill.setSplitPct(100.0);

         User user = SecurityUtil.getUser();
         if(user != null)
         {
           prebillSplit.setUpdatedByUserName(user.getLoginName());
           prebillSplit.setUpdateTime(new Date());
         }

         prebill.getPrebillSplits().add(prebillSplit);
      }
    }
  }

  /**
   * Apply the product group conversion.
   *
   * if useGroupId is more than 0, the input productGroup will be returned, otherwise "*" will be returned.
   *
   * @param useGroupId
   * @param productGroup the product group.
   * @return the converted product group.
   **/
  public static String applyProductGroupConversion(
    Double useGroupId,
    String productGroup
  )
  {
    if((useGroupId != null) && (useGroupId > 0)) return productGroup;

    return "*";
  }

  /**
   * Apply vessel typ conversion
   *
   * if useVesselId is more than 0, the input vesselType will be returned, otherwise "*" will be returned.
   *
   * @param useVesselId
   * @param vesselType the input vessel type.
   * @return the converted vessel type.
   **/
  public static String applyVesselTypeConversion(
    Double useVesselId,
    String vesselType
  )
  {
    if((useVesselId != null) && (useVesselId > 0)) return vesselType;

    return "*";
  }

  /**
   * Get the quantity value from a map of parameter.
   *
   * @param params a Map of parameters
   * @return the quantity
   **/
  public static Number getQtyFromMap(Map params)
  {
    if(params == null) return null;

    Number tmpNumber = (Number)params.get(Constants.QUANTITY);
    if(tmpNumber == null)
    {
      tmpNumber = (Number)params.get("inputVal1");

      if(tmpNumber == null)
      {
        tmpNumber = (Number)params.get("inputVal2");
      }

      if(tmpNumber == null)
      {
        Iterator it = params.values().iterator();
        while(it.hasNext())
        {
          Object obj = it.next();
          if(obj instanceof Number)
          {
            tmpNumber = (Number)obj;
            break;
          }
        }
      }

      if(tmpNumber != null)
      {
        params.put(Constants.QUANTITY, tmpNumber);
      }
    }

    return tmpNumber;
  }

  /**
   * Get the percentage value from a map of parameter.
   *
   * @param params a Map of parameters
   * @return the percentage
   **/
  public static Number getPercentageFromMap(Map params)
  {
    if(params == null) return null;

    Number tmpNumber = (Number)params.get(Constants.PERCENTAGE);
    if(tmpNumber == null)
    {
      Number inputVal1 = (Number)params.get("inputVal1");
      Number inputVal2 = (Number)params.get("inputVal2");

      if((inputVal1 != null) && (inputVal2 != null))
      {
        tmpNumber = inputVal2;
      }

      if(tmpNumber != null)
      {
        params.put(Constants.PERCENTAGE, tmpNumber);
      }
    }

    return tmpNumber;
  }

  /**
   * Get the editable flag.
   *
   * @param isContractPrice a flag to tell if it is a contract price or pb price.
   * @param contractRef the contract ref value
   * @param discountPct the discount percentage
   * @param totalPrice the total price.
   * @return the editable flag.
   **/
  public static boolean isEditable(
    boolean isContractPrice,
    String contractRef,
    Double discountPct,
    Double totalPrice
  )
  {
      if(totalPrice == null){
        if(discountPct == null || (discountPct != null && discountPct != 100))
          return true;
      }
      if(totalPrice != null && totalPrice == 0.0 && discountPct != null && discountPct != 100)
        return true;
      if(isContractPrice)
      {
        if((contractRef == null) || !contractRef.startsWith("PB"))
        {
          return false;
        }
      }
      else
      {
        if((discountPct != null) && (discountPct >= 0)) return false;
      }

      return true;
  }

  /**
   * Check the vessel type to see if it is valid.
   *
   * If it is invalid, an unchecked ServiceException will be thrown.
   *
   * @param addJobContract the AddJobContract which contains a list of vessel types.
   * @param addJobContractVessel the AddJobContractVessel to check its vessel type with the list of vessel types in AddJobContract
   **/
  public static void checkVesselType(
    AddJobContract addJobContract,
    AddJobContractVessel addJobContractVessel
  )
  {
    if(addJobContract == null) return;

    if(addJobContractVessel != null)
    {
      VesselType matchedVesselType = CalculatorUtil.getSelectedVesselType(
        addJobContract.getVesselTypes(),
        addJobContractVessel.getJobContractVessel().getType()
      );
      if(matchedVesselType == null)
      {
        throw new ServiceException("NO_PRICE_WILL_BE_CALCULATED_DUE_TO_INVALIDATE_VESSEL_TYPE");
      }
    }
  }

  /**
   * Check the product group to see if it is valid.
   *
   * If it is invalid, an unchecked ServiceException will be thrown.
   *
   * @param addJobContract the AddJobContract which contains a list of product groups.
   * @param addJobContractProd the AddJobContractProd to check its product group with the list of product groups in AddJobContract
   **/
  public static void checkProductGroup(
    AddJobContractVessel addJobContractVessel,
    AddJobContractProd addJobContractProd
  )
  {
    if(addJobContractVessel == null) return;

    if(addJobContractProd != null)
    {
      ProductGroup matchedProductGroup = CalculatorUtil.getSelectedProductGroup(
        addJobContractVessel.getProductGroups(),
        addJobContractProd.getJobContractProd().getGroup()
      );
      if(matchedProductGroup == null)
      {
        throw new ServiceException("NO_PRICE_WILL_BE_CALCULATED_DUE_TO_INVALIDATE_PRODUCT_GROUP");
      }
    }
  }

  /**
   * Check all the vessel types for all the vessels in the AddJobContract
   *
   * If the AddJobContract contains invalid vessel types, an unchecked ServiceException will be thrown.
   *
   * @param addJobContract the AddJobContract
   **/
  public static void checkVesselTypeForAddJobContract(
    AddJobContract addJobContract
  )
  {
    if(addJobContract == null) return;

    AddJobContractVessel[] addJobContractVessels = addJobContract.getAddJobContractVessels();
    if(addJobContractVessels != null)
    {
      for(int i=0; i<addJobContractVessels.length; i++)
      {
        checkVesselType(addJobContract, addJobContractVessels[i]);

        checkProductGroupForAddJobContractVessel(addJobContractVessels[i]);
      }
    }
  }

  /**
   * Check all the product groups for all the lots in the AddJobContractVessel
   *
   * If the AddJobContractVessel contains invalid product groups, an unchecked ServiceException will be thrown.
   *
   * @param addJobContractVessel the AddJobContractVessel
   **/
  public static void checkProductGroupForAddJobContractVessel(
    AddJobContractVessel addJobContractVessel
  )
  {
    if(addJobContractVessel == null) return;

    AddJobContractProd[] addJobContractProds = addJobContractVessel.getAddJobContractProds();
    if(addJobContractProds != null)
    {
      for(int i=0; i<addJobContractProds.length; i++)
      {
        checkProductGroup(addJobContractVessel, addJobContractProds[i]);
      }
    }
  }

  /**
   * Get the inspection quantity from a list of controlExts.
   *
   * @param controlExts a list of controlExts
   * @return the inspection quantity
   **/
  public static double getInspectionQty(List controlExts)
  {
    double floatQty = 0.0;
    if(controlExts == null) return floatQty;

    if(controlExts.size() > 0)
    {
      ControlExt c = (ControlExt)controlExts.get(0);

      int uomCode = 1;
      Number tmpNumber = (Number)c.getParameter(Constants.UOM_CODE);
      if(tmpNumber != null) uomCode = tmpNumber.intValue();

      Double quantity = (Double)c.getParameter(Constants.QUANTITY);
      Double quantity1 = (Double)c.getParameter(Constants.QUANTITY1);
      Double quantity2 = (Double)c.getParameter(Constants.QUANTITY2);

      if(uomCode == 1)
      {
        if(quantity != null)
        {
          floatQty = quantity.doubleValue();
        }
      }
      else if(uomCode == 2)
      {
        if(quantity1 != null)
        {
          floatQty = quantity1.doubleValue();
        }
      }
      else if(uomCode == 3)
      {
        if(quantity2 != null)
        {
          floatQty = quantity2.doubleValue();
        }
      }
    }

    return floatQty;
  }

  /**
   * Set the inspection quantity to a list of controlExts.
   *
   * @param controlExts a list of controlExts
   * @param newQty the new inspection quantity
   **/
  public static void setInspectionQty(List controlExts, double newQty)
  {
    if(controlExts == null) return;

    if(controlExts.size() > 0)
    {
      ControlExt c = (ControlExt)controlExts.get(0);

      int uomCode = 1;
      Number tmpNumber = (Number)c.getParameter(Constants.UOM_CODE);
      if(tmpNumber != null) uomCode = tmpNumber.intValue();

      if(uomCode == 1)
      {
        c.setParameter(Constants.QUANTITY, newQty);
      }
      else if(uomCode == 2)
      {
        c.setParameter(Constants.QUANTITY1, newQty);
      }
      else if(uomCode == 3)
      {
        c.setParameter(Constants.QUANTITY2, newQty);
      }
    }
  }
}
