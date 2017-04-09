package com.intertek.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import com.intertek.calculator.CalculatorManager;
import com.intertek.calculator.CalculatorRequest;
import com.intertek.calculator.CalculatorResult;
import com.intertek.calculator.CalculatorUtil;
import com.intertek.calculator.ContractExpressionExt;
import com.intertek.domain.AddJobContract;
import com.intertek.domain.AddJobContractProd;
import com.intertek.domain.AddJobContractVessel;
import com.intertek.entity.CfgContract;
import com.intertek.entity.ContractExpression;
import com.intertek.entity.JobContract;
import com.intertek.entity.JobContractProd;
import com.intertek.entity.JobContractVessel;
import com.intertek.entity.JobContractVesselControl;
import com.intertek.entity.JobContractVesselInspectionResult;
import com.intertek.entity.JobOrder;
import com.intertek.entity.Prebill;
import com.intertek.entity.PriceBook;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.CalculatorService;

public class InspectionServiceShellUtil
{
  public static int calculateInspectionServiceForShell(
    AddJobContract addJobContract,
    CfgContract contract,
    AddJobContractVessel selectedAddJobContractVessel,
    AddJobContractProd selectedAddJobContractProd,
    List selectedControlExtList
  )
  {
    if(addJobContract == null) return 0;

    JobContract jobContract = addJobContract.getJobContract();
    if(jobContract == null) return 0;

    JobOrder jobOrder = jobContract.getJobOrder();
    if(jobOrder == null) return 0;

    // group by tow
    AddJobContractVessel[] addJobContractVessels = addJobContract.getAddJobContractVessels();
    if(addJobContractVessels == null) return 0;

    Map addJobContractVesselMapByTow = groupAddJobContractVesselsByTow(addJobContractVessels);

    CalculatorService calculatorService = (CalculatorService)ServiceLocator.getInstance().getBean("calculatorService");
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
      else
      {
        return 0;
      }
    }

    String location = jobContract.getZone();
    String nominationDateStr = DateUtil.formatDate(jobOrder.getJobFinishDate(), "yyyyMMdd");

    List contractExpressionsForLot = calculatorService.getContractExpressions(
      Constants.L_INSP,
      jobContract.getContractCode(),
      contract.getPriceBookId(),
      location != null ? location : "*",
      nominationDateStr,
      jobContract.getLanguage()
    );
    contractExpressionsForLot = getShellLotContractExpressions(contractExpressionsForLot);

    List contractExpressionsForPrimaryVessel = calculatorService.getContractExpressions(
      Constants.SHELL_PRIMARY_VESSEL_INSP,
      jobContract.getContractCode(),
      contract.getPriceBookId(),
      location != null ? location : "*",
      nominationDateStr,
      jobContract.getLanguage()
    );

    List contractExpressionsForAdditionalVessel = calculatorService.getContractExpressions(
      Constants.SHELL_ADDITIONAL_VESSEL_INSP,
      jobContract.getContractCode(),
      contract.getPriceBookId(),
      location != null ? location : "*",
      nominationDateStr,
      jobContract.getLanguage()
    );

    int totalCalculatorResultCount = 0;

    Iterator it = addJobContractVesselMapByTow.keySet().iterator();
    while(it.hasNext())
    {
      String key = (String)it.next();
      List addJobContractVesselList = (List)addJobContractVesselMapByTow.get(key);

      int calculatorResultCount = calculateInspectionServiceByJobContractVesselListForShell(
        addJobContractVesselList,
        contract,
        selectedAddJobContractVessel,
        selectedAddJobContractProd,
        selectedControlExtList,
        contractExpressionsForLot,
        contractExpressionsForPrimaryVessel,
        contractExpressionsForAdditionalVessel
      );

      totalCalculatorResultCount += calculatorResultCount;
    }

    return totalCalculatorResultCount;
  }

  public static int calculateInspectionServiceByJobContractVesselListForShell(
    List addJobContractVesselList,
    CfgContract contract,
    AddJobContractVessel selectedAddJobContractVessel,
    AddJobContractProd selectedAddJobContractProd,
    List selectedControlExtList,
    List contractExpressionsForLot,
    List contractExpressionsForPrimaryVessel,
    List contractExpressionsForAdditionalVessel
  )
  {
    if((addJobContractVesselList == null) || (addJobContractVesselList.size() == 0)) return 0;

    AddJobContractVessel primaryAddJobContractVessel = null;
    AddJobContractProd primaryAddJobContractProd = null;
    CalculatorResult primaryCalculatorResult = null;

    double maxPrice = 0.0;
    int calculatorResultCount = 0;

    for(int i=0; i<addJobContractVesselList.size(); i++)
    {
      AddJobContractVessel addJobContractVessel = (AddJobContractVessel)addJobContractVesselList.get(i);

      AddJobContractProd[] addJobContractProds = addJobContractVessel.getAddJobContractProds();
      if( (addJobContractProds == null) || (addJobContractProds.length == 0))
      {
        InspectionServiceUtil.deleteUnusedJobContractVesselInspectionResults(
          new ArrayList(),
          addJobContractVessel
        );

        continue;
      }

      for(int j=0; j<addJobContractProds.length; j++)
      {
        List calculatorResults = calculateInspectionServiceByJobContractProdForShell(
          addJobContractProds[j],
          contract,
          selectedAddJobContractVessel,
          selectedAddJobContractProd,
          selectedControlExtList,
          0, // additionalGrade = 0
          contractExpressionsForLot
        );

        if((calculatorResults != null) && (calculatorResults.size() > 0))
        {
          calculatorResultCount = calculatorResults.size();

          CalculatorResult calculatorResult = (CalculatorResult)calculatorResults.get(0);
          addJobContractProds[j].setCalculatorResult(calculatorResult);

          Double totalPrice = calculatorResult.getTotalPrice();
          if(totalPrice != null)
          {
            if(totalPrice > maxPrice)
            {
              maxPrice = totalPrice;
              primaryAddJobContractVessel = addJobContractVessel;
              primaryAddJobContractProd = addJobContractProds[j];
              primaryCalculatorResult = calculatorResult;
            }
          }
        }
      }
    }

    if(primaryAddJobContractVessel != null)
    {
      double totalQtyInThisLot = calculatorTotalQtyInThisLotFromCalculatorResults(
        primaryAddJobContractVessel
      );

      // recalculate the primary vessel price based on new qty
      List newPrimaryCalculatorResults = calculateInspectionServiceByJobContractProdForShell(
        primaryAddJobContractProd,
        contract,
        selectedAddJobContractVessel,
        selectedAddJobContractProd,
        selectedControlExtList,
        0, // additionalGrade = 0
        contractExpressionsForLot,
        new Double(totalQtyInThisLot)
      );

      if((newPrimaryCalculatorResults != null) && (newPrimaryCalculatorResults.size() > 0))
      {
        CalculatorResult newPrimaryCalculatorResult = (CalculatorResult)newPrimaryCalculatorResults.get(0);

        newPrimaryCalculatorResult.setParameter(Constants.IS_INCLUDED_VALUE, new Integer(1));
        newPrimaryCalculatorResult.setParameter(Constants.IS_VESS_PR, new Integer(1));

        ContractExpression contractExpression = setContractExpressionToCalculatorResult(
          newPrimaryCalculatorResult,
          contractExpressionsForPrimaryVessel
        );

        InspectionServiceUtil.deleteUnusedJobContractVesselInspectionResults(
          newPrimaryCalculatorResults,
          primaryAddJobContractVessel
        );

        populateJobContractVesselInspectionResultForShell(
          newPrimaryCalculatorResult,
          primaryAddJobContractVessel,
          Constants.CALCULATOR_INSPECTION_SHELL_DESCRIPTOR
        );
      }

      // populate primary vessel lot line item
      List validResults = new ArrayList();
      validResults.add(primaryCalculatorResult);

      InspectionServiceUtil.deleteUnusedJobContractProductInspectionResults(
        validResults,
        primaryAddJobContractProd
      );
      primaryCalculatorResult.setParameter(Constants.IS_INCLUDED_VALUE, new Integer(1));
      primaryCalculatorResult.setParameter(Constants.IS_VESS_PR, new Integer(1));

      primaryCalculatorResult.setTotalPrice(0.0);
      primaryCalculatorResult.setParameter(Constants.PRICE_BEFORE_DISCOUNT, 0.0);
      InspectionServiceUtil.populateJobContractProductInspectionResult(
        primaryCalculatorResult,
        primaryAddJobContractProd,
        Constants.CALCULATOR_INSPECTION_SHELL_DESCRIPTOR
      );

      // populate included line item
      List includedJobContractProdList = populateIncludedJobContractProds(
        primaryCalculatorResult,
        primaryAddJobContractProd,
        primaryAddJobContractVessel
      );

      // calculate additional grade fee in primary vessel
      calculateAdditionalGradeFeeInVessel(
        primaryAddJobContractVessel,
        primaryAddJobContractProd,
        includedJobContractProdList,
        contract,
        selectedAddJobContractVessel,
        selectedAddJobContractProd,
        selectedControlExtList,
        contractExpressionsForLot
      );
    }

    calculateAdditionalVessels(
      addJobContractVesselList,
      contract,
      primaryAddJobContractVessel,
      selectedAddJobContractVessel,
      selectedAddJobContractProd,
      selectedControlExtList,
      contractExpressionsForLot,
      contractExpressionsForAdditionalVessel
    );

    return calculatorResultCount;
  }

  public static void calculateAdditionalVessels(
    List addJobContractVesselList,
    CfgContract contract,
    AddJobContractVessel primaryAddJobContractVessel,
    AddJobContractVessel selectedAddJobContractVessel,
    AddJobContractProd selectedAddJobContractProd,
    List selectedControlExtList,
    List contractExpressionsForLot,
    List contractExpressionsForAdditionalVessel
  )
  {
    if(addJobContractVesselList == null) return;

    for(int i=0; i<addJobContractVesselList.size(); i++)
    {
      AddJobContractVessel addJobContractVessel = (AddJobContractVessel)addJobContractVesselList.get(i);

      if(addJobContractVessel == primaryAddJobContractVessel) continue;

      calculateAdditionalVessel(
        addJobContractVessel,
        contract,
        selectedAddJobContractVessel,
        selectedAddJobContractProd,
        selectedControlExtList,
        contractExpressionsForLot,
        contractExpressionsForAdditionalVessel
      );
    }
  }

  public static void calculateAdditionalVessel(
    AddJobContractVessel addJobContractVessel,
    CfgContract contract,
    AddJobContractVessel selectedAddJobContractVessel,
    AddJobContractProd selectedAddJobContractProd,
    List selectedControlExtList,
    List contractExpressionsForLot,
    List contractExpressionsForAdditionalVessel
  )
  {
    if(addJobContractVessel == null) return;

    AddJobContractProd maxAddJobContractProd = null;
    CalculatorResult maxCalculatorResult = null;

    double maxPrice = 0.0;

    AddJobContractProd[] addJobContractProds = addJobContractVessel.getAddJobContractProds();
    if( (addJobContractProds == null) || (addJobContractProds.length == 0)) return;

    for(int j=0; j<addJobContractProds.length; j++)
    {
      // should change to use different contract expressions.
      List calculatorResults = calculateInspectionServiceByJobContractProdForShell(
        addJobContractProds[j],
        contract,
        selectedAddJobContractVessel,
        selectedAddJobContractProd,
        selectedControlExtList,
        2, // additionalGrade = 2
        contractExpressionsForLot
      );

      if((calculatorResults != null) && (calculatorResults.size() > 0))
      {
        CalculatorResult calculatorResult = (CalculatorResult)calculatorResults.get(0);
        addJobContractProds[j].setCalculatorResult(calculatorResult);

        Double totalPrice = calculatorResult.getTotalPrice();
        if(totalPrice != null)
        {
          if(totalPrice > maxPrice)
          {
            maxPrice = totalPrice;
            maxAddJobContractProd = addJobContractProds[j];
            maxCalculatorResult = calculatorResult;
          }
        }
      }
    }

    if(maxAddJobContractProd != null)
    {
      List validResults = new ArrayList();
      validResults.add(maxCalculatorResult);

      // polulate the additional vessel line item
      maxCalculatorResult.setParameter(Constants.IS_INCLUDED_VALUE, new Integer(0));
      maxCalculatorResult.setParameter(Constants.IS_VESS_PR, new Integer(0));

      ContractExpression contractExpression = setContractExpressionToCalculatorResult(
        maxCalculatorResult,
        contractExpressionsForAdditionalVessel
      );

      InspectionServiceUtil.deleteUnusedJobContractVesselInspectionResults(
        validResults,
        addJobContractVessel
      );

      populateJobContractVesselInspectionResultForShell(
        maxCalculatorResult,
        addJobContractVessel,
        Constants.CALCULATOR_INSPECTION_SHELL_DESCRIPTOR
      );
      setContractExpressionToCalculatorResult(
        maxCalculatorResult,
        contractExpression
      );

      // populate additional vessel lot line item
      InspectionServiceUtil.deleteUnusedJobContractProductInspectionResults(
        validResults,
        maxAddJobContractProd
      );
      maxCalculatorResult.setParameter(Constants.IS_INCLUDED_VALUE, new Integer(1));
      maxCalculatorResult.setParameter(Constants.IS_VESS_PR, new Integer(1));

      maxCalculatorResult.setTotalPrice(0.0);
      maxCalculatorResult.setParameter(Constants.PRICE_BEFORE_DISCOUNT, 0.0);

      InspectionServiceUtil.populateJobContractProductInspectionResult(
        maxCalculatorResult,
        maxAddJobContractProd,
        Constants.CALCULATOR_INSPECTION_SHELL_DESCRIPTOR
      );

      // calculate additional grade fee in secondary vessel
      calculateAdditionalGradeFeeInVessel(
        addJobContractVessel,
        maxAddJobContractProd,
        new ArrayList(),
        contract,
        selectedAddJobContractVessel,
        selectedAddJobContractProd,
        selectedControlExtList,
        contractExpressionsForLot
      );
    }
  }

  public static void calculateAdditionalGradeFeeInVessel(
    AddJobContractVessel primaryAddJobContractVessel,
    AddJobContractProd primaryAddJobContractProd,
    List includedJobContractProdList,
    CfgContract contract,
    AddJobContractVessel selectedAddJobContractVessel,
    AddJobContractProd selectedAddJobContractProd,
    List selectedControlExtList,
    List contractExpressions
  )
  {
    if((primaryAddJobContractVessel == null) || (primaryAddJobContractProd == null) || (includedJobContractProdList == null)) return;

    AddJobContractProd[] addJobContractProds = primaryAddJobContractVessel.getAddJobContractProds();
    if(addJobContractProds != null)
    {
      for(int i=0; i<addJobContractProds.length; i++)
      {
        if(primaryAddJobContractProd == addJobContractProds[i]) continue;
        if(includedJobContractProdList.contains(addJobContractProds[i])) continue;

        List calculatorResults = calculateInspectionServiceByJobContractProdForShell(
          addJobContractProds[i],
          contract,
          selectedAddJobContractVessel,
          selectedAddJobContractProd,
          selectedControlExtList,
          1, // additionalGrade = 1
          contractExpressions
        );

        InspectionServiceUtil.deleteUnusedJobContractProductInspectionResults(
          calculatorResults,
          addJobContractProds[i]
        );

        for(int j=0; j<calculatorResults.size(); j++)
        {
          CalculatorResult calculatorResult = (CalculatorResult)calculatorResults.get(j);
          calculatorResult.setParameter(Constants.IS_INCLUDED_VALUE, new Integer(1));
          calculatorResult.setParameter(Constants.IS_VESS_PR, new Integer(0));
          InspectionServiceUtil.populateJobContractProductInspectionResult(
            calculatorResult,
            addJobContractProds[i],
            Constants.CALCULATOR_INSPECTION_SHELL_DESCRIPTOR
          );
        }
      }
    }
  }

  public static List populateIncludedJobContractProds(
    CalculatorResult primaryCalculatorResult,
    AddJobContractProd primaryAddJobContractProd,
    AddJobContractVessel primaryAddJobContractVessel
  )
  {
    List results = new ArrayList();

    String primaryContractRefNo = (String)primaryCalculatorResult.getParameter(Constants.CONTRACT_REF_NO);
    Double unitsIncludedObj = (Double)primaryCalculatorResult.getParameter(Constants.UNITS_INCLUDED);
    int unitsIncluded = 1;
    if(unitsIncludedObj != null) unitsIncluded = unitsIncludedObj.intValue();
    if(primaryContractRefNo != null)
    {
      Boolean isContractPrice = (Boolean)primaryCalculatorResult.getParameter(Constants.IS_CONTRACT_PRICE);
      if((isContractPrice != null) && (isContractPrice.booleanValue() == false)) return results;
    }

    if(unitsIncluded == 1)
    {
      //results.add(primaryAddJobContractProd);
    }
    else if(unitsIncluded <= 0)
    {
      AddJobContractProd[] addJobContractProds = primaryAddJobContractVessel.getAddJobContractProds();
      int size = addJobContractProds != null ? addJobContractProds.length : 0;
      results.addAll(
        populateIncludedJobContractProdsInternal(
          primaryAddJobContractProd,
          primaryAddJobContractVessel,
          primaryContractRefNo,
          size
        )
      );
    }
    else
    {
      results.addAll(
        populateIncludedJobContractProdsInternal(
          primaryAddJobContractProd,
          primaryAddJobContractVessel,
          primaryContractRefNo,
          unitsIncluded
        )
      );
    }

    return results;
  }


  public static List populateIncludedJobContractProdsInternal(
    AddJobContractProd primaryAddJobContractProd,
    AddJobContractVessel primaryAddJobContractVessel,
    String primaryContractRefNo,
    int unitsIncluded
  )
  {
    List results = new ArrayList();

    if((primaryAddJobContractProd == null)
      || (primaryAddJobContractVessel == null)
      || (primaryContractRefNo == null)
      || (unitsIncluded <= 1)) return results;

    int count = 0;

    AddJobContractProd[] addJobContractProds = primaryAddJobContractVessel.getAddJobContractProds();
    if(addJobContractProds != null)
    {
      for(int i=0; i<addJobContractProds.length; i++)
      {
        if(primaryAddJobContractProd == addJobContractProds[i]) continue;
        if(count == unitsIncluded - 1) break;

        CalculatorResult calculatorResult = addJobContractProds[i].getCalculatorResult();
        if(calculatorResult != null)
        {
          String contractRefNo = (String)calculatorResult.getParameter(Constants.CONTRACT_REF_NO);
          if(primaryContractRefNo.equals(contractRefNo))
          {
            List validResults = new ArrayList();
            validResults.add(calculatorResult);

            InspectionServiceUtil.deleteUnusedJobContractProductInspectionResults(
              validResults,
              addJobContractProds[i]
            );

            calculatorResult.setTotalPrice(0.0);
            calculatorResult.setParameter(Constants.PRICE_BEFORE_DISCOUNT, 0.0);

            calculatorResult.setParameter(Constants.FLOAT_DATA0, new Integer(0));
            calculatorResult.setParameter(Constants.IS_INCLUDED_VALUE, new Integer(0));
            calculatorResult.setParameter(Constants.IS_VESS_PR, new Integer(0));

            InspectionServiceUtil.populateJobContractProductInspectionResult(
              calculatorResult,
              addJobContractProds[i],
              Constants.CALCULATOR_INSPECTION_SHELL_DESCRIPTOR
            );

            results.add(addJobContractProds[i]);
            count ++;
          }
        }
      }
    }

    return results;
  }

  public static void populateJobContractVesselInspectionResultForShell(
    CalculatorResult calculatorResult,
    AddJobContractVessel addJobContractVessel,
    String descriptor
  )
  {
    if((calculatorResult == null) || (addJobContractVessel == null)) return;

    JobContractVessel jobContractVessel = addJobContractVessel.getJobContractVessel();
    if(jobContractVessel == null) return;

    JobContract jobContract = jobContractVessel.getJobContract();
    if(jobContract == null) return;

    JobOrder jobOrder = jobContract.getJobOrder();
    if(jobOrder == null) return;

    List jResults = addJobContractVessel.getResults();
    if(jResults == null) return ;
    List deletedResults = addJobContractVessel.getDeletedResults();

    ContractExpressionExt ceExt = calculatorResult.getContractExpressionExt();
    String expressionId = ceExt.getContractExpression().getContractExpressionId().getExpressionId();

    JobContractVesselInspectionResult jResult = null;
    Prebill prebill = null;

    for(int i=0; i<jResults.size(); i++)
    {
      JobContractVesselInspectionResult tmpResult = (JobContractVesselInspectionResult)jResults.get(i);

      if(tmpResult.getExpressionId().equals(expressionId))
      {
        jResult = tmpResult;
        break;
      }
    }

    if(jResult == null)
    {
      for(int i=0; i<deletedResults.size(); i++)
      {
        JobContractVesselInspectionResult tmpResult = (JobContractVesselInspectionResult)deletedResults.get(i);

        if(tmpResult.getExpressionId().equals(expressionId))
        {
          jResult = tmpResult;
          deletedResults.remove(i);
          addJobContractVessel.getAddedResults().add(jResult);
          break;
        }
      }
    }

    if(jResult == null)
    {
      jResult = new JobContractVesselInspectionResult();
      jResult.setExpressionId(expressionId);
      jResult.setJobContractVessel(addJobContractVessel.getJobContractVessel());

      prebill = new Prebill();
      prebill.setJobContract(jobContract);
      if(jobContract != null){
        prebill.setBaseCurrencyCd(jobContract.getBaseCurrencyCd());
        prebill.setCurrencyCd(jobContract.getTransactionCurrencyCd());
        prebill.setRateMult(jobContract.getRateMult());
        prebill.setRateDiv(jobContract.getRateDiv());
      }
      prebill.setJobContractVesselInspectionResult(jResult);
      jResult.getPrebills().add(prebill);

      addJobContractVessel.getAddedResults().add(jResult);
    }
    else
    {
      Iterator it = jResult.getPrebills().iterator();
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
        prebill.setJobContractVesselInspectionResult(jResult);
        jResult.getPrebills().add(prebill);
      }
    }

    jResult.setLocation(ceExt.getContractExpression().getContractExpressionId().getLocation());
    //jResult.setUomFlag(uomCode);

    CalculatorUtil.populatePrebillFromCalculatorResult(
      calculatorResult,
      prebill,
      descriptor
    );
  }

  public static double calculatorTotalQtyInThisLotFromCalculatorResults(
    AddJobContractVessel addJobContractVessel
  )
  {
    double result = 0.0;

    if(addJobContractVessel == null) return result;

    AddJobContractProd[] addJobContractProds = addJobContractVessel.getAddJobContractProds();
    if( (addJobContractProds == null) || (addJobContractProds.length == 0)) return result;

    for(int j=0; j<addJobContractProds.length; j++)
    {
      CalculatorResult calculatorResult = addJobContractProds[j].getCalculatorResult();

      if(calculatorResult != null)
      {
        Double qty = (Double)calculatorResult.getParameter(Constants.INSPECTION_QUANTITY);
        if(qty != null)
        {
          result += qty;
        }
      }
    }

    return result;
  }

  public static List calculateInspectionServiceByJobContractProdForShell(
    AddJobContractProd addJobContractProd,
    CfgContract contract,
    AddJobContractVessel selectedAddJobContractVessel,
    AddJobContractProd selectedAddJobContractProd,
    List selectedControlExtList,
    int additionalGrade,
    List contractExpressions
  )
  {
    return calculateInspectionServiceByJobContractProdForShell(
      addJobContractProd,
      contract,
      selectedAddJobContractVessel,
      selectedAddJobContractProd,
      selectedControlExtList,
      additionalGrade,
      contractExpressions,
      null
    );
  }

  public static List calculateInspectionServiceByJobContractProdForShell(
    AddJobContractProd addJobContractProd,
    CfgContract contract,
    AddJobContractVessel selectedAddJobContractVessel,
    AddJobContractProd selectedAddJobContractProd,
    List selectedControlExtList,
    int additionalGrade,
    List contractExpressions,
    Double newQty
  )
  {
    List results = new ArrayList();

    if(addJobContractProd == null) return results;

    JobContractProd jobContractProd = addJobContractProd.getJobContractProd();
    if(jobContractProd == null) return results;

    Boolean notChargeInd = jobContractProd.getNotChargeInd();
    if((notChargeInd != null) && notChargeInd.booleanValue()) return results;

    JobContractVessel jobContractVessel = jobContractProd.getJobContractVessel();
    if(jobContractVessel == null) return results;

    JobContract jobContract = jobContractVessel.getJobContract();
    if(jobContract == null) return results;

    JobOrder jobOrder = jobContract.getJobOrder();
    if(jobOrder == null) return results;

    CalculatorService calculatorService = (CalculatorService)ServiceLocator.getInstance().getBean("calculatorService");
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
      else
      {
        return results;
      }
    }

    String location = jobContract.getZone();

    CalculatorRequest cr = new CalculatorRequest();
    cr.setParameter(Constants.CONTRACT_ID, jobContract.getContractCode());
    cr.setParameter(Constants.SERVICE_NAME, "L-Insp");
    cr.setParameter(
      Constants.VESSEL_TYPE,
      jobContractVessel.getType()
    );
    cr.setParameter(
      Constants.PRODUCT_GROUP_ID,
      jobContractProd.getGroup()
    );
    cr.setParameter(Constants.MASTER_GROUP_ID, jobContractProd.getProductGroupMaster());
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
    cr.setParameter(Constants.LANGUAGE_CD, jobContract.getLanguage());
    cr.setParameter(Constants.JOB_TYPE, jobOrder.getJobType());
    cr.setParameter(Constants.BRANCH_CODE, jobOrder.getBranchName());
    cr.setParameter(Constants.JOB_CODE, jobContract.getProductType());
    cr.setParameter(Constants.CURRENCY_CD, jobContract.getTransactionCurrencyCd());
    cr.setParameter(Constants.OVERRIDE_CURRENCY_RATE, jobContract.getOverrideCurrRate());

    cr.setContract(contract);
    cr.setParameter(Constants.ADDITIONAL_GRADE, additionalGrade);

    if(contractExpressions == null)
    {
      contractExpressions = calculatorService.getContractExpressions(
        Constants.L_INSP,
        jobContract.getContractCode(),
        cr.getContract().getPriceBookId(),
        location != null ? location : "*",
        (String)cr.getParameter(Constants.NOMINATION_DATE_STR),
        (String)cr.getParameter(Constants.LANGUAGE_CD)
      );
    }

    if(contractExpressions.size() > 0)
    {
      List controlExtList = null;
      if(selectedAddJobContractProd == addJobContractProd)
      {
        controlExtList = selectedControlExtList;
      }
      else
      {
        controlExtList = InspectionServiceUtil.getControlExtListFromJobContractProd(
          jobContractProd,
          null
        );
      }

      if(newQty != null)
      {
        CalculatorUtil.setInspectionQty(controlExtList, newQty.doubleValue());
      }

      List calculatorResults = CalculatorManager.calculateInspectionForShell(
        cr,
        controlExtList,
        contractExpressions
      );

      for(int i=0; i<calculatorResults.size(); i++)
      {
        CalculatorResult calculatorResult = (CalculatorResult)calculatorResults.get(i);
        if(calculatorResult != null)
        {
          results.add(calculatorResult);
        }
      }
    }

    return results;
  }

  public static Map groupAddJobContractVesselsByTow(AddJobContractVessel[] addJobContractVessels)
  {
    Map results = new HashMap();

    if(addJobContractVessels == null) return results;

    int count = 0;

    for(int i=0; i<addJobContractVessels.length; i++)
    {
      JobContractVessel jVessel = addJobContractVessels[i].getJobContractVessel();
      if(jVessel == null) continue;

      if(jVessel.getControls().size() > 0)
      {
        Iterator it = jVessel.getControls().iterator();
        while(it.hasNext())
        {
          JobContractVesselControl jControl = (JobContractVesselControl)it.next();
          if(Constants.Tow.equals(jControl.getObjectName()))
          {
            String dataInput = jControl.getInputValue0();
            if(dataInput != null)
            {
              String key = dataInput;

              if(dataInput.equals(Constants.NoTow))
              {
                key = key + count;
                count ++;
              }
              List groupedVessels = (List)results.get(key);
              if(groupedVessels == null)
              {
                groupedVessels = new ArrayList();
                results.put(key, groupedVessels);
              }
              groupedVessels.add(addJobContractVessels[i]);

              break;
            }
          }
        }
      }
      else
      {
        String key = Constants.NoTow + count;
        count ++;

        List groupedVessels = (List)results.get(key);
        if(groupedVessels == null)
        {
          groupedVessels = new ArrayList();
          results.put(key, groupedVessels);
        }
        groupedVessels.add(addJobContractVessels[i]);
      }
    }

    return results;
  }

  public static ContractExpression setContractExpressionToCalculatorResult(
    CalculatorResult calculatorResult,
    List contractExpressions
  )
  {
    ContractExpressionExt cee = calculatorResult.getContractExpressionExt();
    if(cee == null) return null;

    ContractExpression oldContractExpression = cee.getContractExpression();

    if((contractExpressions != null) && (contractExpressions.size() > 0))
    {
      cee.setContractExpression((ContractExpression)contractExpressions.get(0));
    }

    return oldContractExpression;
  }

  public static ContractExpression setContractExpressionToCalculatorResult(
    CalculatorResult calculatorResult,
    ContractExpression contractExpression
  )
  {
    ContractExpressionExt cee = calculatorResult.getContractExpressionExt();
    if(cee == null) return null;

    ContractExpression oldContractExpression = cee.getContractExpression();

    if(contractExpression != null)
    {
      cee.setContractExpression(contractExpression);
    }

    return oldContractExpression;
  }

  public static List getShellLotContractExpressions(List ceList)
  {
    List results = new ArrayList();

    if(ceList == null) return results;

    for(int i=0; i<ceList.size(); i++)
    {
      ContractExpression ce = (ContractExpression)ceList.get(i);
      if(Constants.SHELL_LOT.equals(ce.getContractExpressionId().getExpressionId()))
      {
        results.add(ce);

        break;
      }
    }

    if(results.size() > 0) return results;

    for(int i=0; i<ceList.size(); i++)
    {
      ContractExpression ce = (ContractExpression)ceList.get(i);
      if(Constants.INSPECTION_PRICE.equals(ce.getContractExpressionId().getExpressionId()))
      {
        results.add(ce);

        break;
      }
    }

    if(results.size() > 0) return results;

    if(ceList.size() > 0) results.add(ceList.get(0));

    return results;
  }
}

