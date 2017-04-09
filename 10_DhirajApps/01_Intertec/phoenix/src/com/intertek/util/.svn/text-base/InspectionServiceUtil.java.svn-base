package com.intertek.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.intertek.calculator.CalculatorManager;
import com.intertek.calculator.CalculatorRequest;
import com.intertek.calculator.CalculatorResult;
import com.intertek.calculator.CalculatorUtil;
import com.intertek.calculator.ContractExpressionExt;
import com.intertek.calculator.ControlExt;
import com.intertek.domain.AddJobContract;
import com.intertek.domain.AddJobContractProd;
import com.intertek.domain.AddJobContractVessel;
import com.intertek.entity.CfgContract;
import com.intertek.entity.Control;
import com.intertek.entity.JobContract;
import com.intertek.entity.JobContractInspectionResult;
import com.intertek.entity.JobContractProd;
import com.intertek.entity.JobContractProdControl;
import com.intertek.entity.JobContractProductInspectionResult;
import com.intertek.entity.JobContractVessel;
import com.intertek.entity.JobContractVesselControl;
import com.intertek.entity.JobContractVesselInspectionResult;
import com.intertek.entity.JobOrder;
import com.intertek.entity.Prebill;
import com.intertek.entity.PriceBook;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.CalculatorService;

public class InspectionServiceUtil
{
  public static int calculateInspectionService(
    List controls,
    AddJobContractProd addJobContractProd,
    AddJobContractVessel addJobContractVessel,
    AddJobContract addJobContract,
    CfgContract contract,
    Boolean additionalVessel,
    Integer uomCode,
    boolean notChargeInd
  )
  {
    if((controls == null) || (addJobContractProd == null) || (addJobContractVessel == null) || (addJobContract == null)) return 0;

    JobContractProd jobContractProd = addJobContractProd.getJobContractProd();
    if(jobContractProd == null) return 0;

    JobContractVessel jobContractVessel = addJobContractVessel.getJobContractVessel();
    if(jobContractVessel == null) return 0;

    JobContract jobContract = addJobContract.getJobContract();
    if(jobContract == null) return 0;

    JobOrder jobOrder = jobContract.getJobOrder();
    if(jobOrder == null) return 0;

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

    Map userInputs = new HashMap();
    // additionalVessel should use questions from vessel.
    userInputs.put(Constants.ADDITIONAL_VESSEL, additionalVessel);
    userInputs.put(Constants.UOM_CODE, uomCode);

    Iterator it = jobContractProd.getControls().iterator();
    while(it.hasNext())
    {
      JobContractProdControl jControl = (JobContractProdControl)it.next();

      String dataInput = jControl.getInputValue0();
      String objectName = jControl.getObjectName();
      if(Constants.noOfUOM.equals(objectName))
      {
        Double qty = null;
        try { qty = new Double(dataInput); } catch(Exception e) { }

        if((qty != null) && (qty.doubleValue() != 0))
        {
          userInputs.put(Constants.noOfUOM, qty);
        }
      }
      else if(Constants.noOfUOM1.equals(objectName))
      {
        Double qty = null;
        try { qty = new Double(dataInput); } catch(Exception e) { }

        if((qty != null) && (qty.doubleValue() != 0))
        {
          userInputs.put(Constants.noOfUOM1, qty);
        }
      }
      else if(Constants.noOfUOM2.equals(objectName))
      {
        Double qty = null;
        try { qty = new Double(dataInput); } catch(Exception e) { }

        if((qty != null) && (qty.doubleValue() != 0))
        {
          userInputs.put(Constants.noOfUOM2, qty);
        }
      }
      else if(Constants.ADDITIONAL_LOT.equals(objectName))
      {
        userInputs.put(Constants.ADDITIONAL_LOT, new Boolean(Constants.Yes.equals(dataInput)));
      }
      else if(Constants.BOOLEAN_VAL_1.equals(objectName))
      {
        userInputs.put(Constants.BOOLEAN_VAL_1, new Boolean(Constants.Yes.equals(dataInput)));
      }
    }

    Map qtyMap = CalculatorUtil.getQtyMap(userInputs, uomCode);
    userInputs.putAll(qtyMap);

    Control c = new Control();

    ControlExt cExt = new ControlExt(c, userInputs);
    List controlExtList = new ArrayList();
    controlExtList.add(cExt);

    int modelType = calculatorService.getInspectionModelType(
      jobContract.getContractCode(),
      jobOrder.getJobFinishDate()
    );

    if(modelType == 0)
    {
      CalculatorUtil.checkVesselType(
        addJobContract,
        addJobContractVessel
      );

      CalculatorUtil.checkProductGroup(
        addJobContractVessel,
        addJobContractProd
      );
    }
    else
    {
      CalculatorUtil.checkVesselTypeForAddJobContract(addJobContract);
    }

    if(modelType == 2)
    {
      return InspectionServiceShellUtil.calculateInspectionServiceForShell(
        addJobContract,
        contract,
        addJobContractVessel,
        addJobContractProd,
        controlExtList
      );
    }

    List validResults = new ArrayList();

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
    //cr.setParameter(Constants.SERVICE_LEVEL, "*"); not used
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
    cr.setParameter(Constants.LANGUAGE_CD, jobContract.getLanguage()); //hardcoded
    cr.setParameter(Constants.JOB_TYPE, jobOrder.getJobType());
    cr.setParameter(Constants.BRANCH_CODE, jobOrder.getBranchName());
    cr.setParameter(Constants.JOB_CODE, jobContract.getProductType());
    cr.setParameter(Constants.CURRENCY_CD, jobContract.getTransactionCurrencyCd());
    cr.setParameter(Constants.OVERRIDE_CURRENCY_RATE, jobContract.getOverrideCurrRate());

    cr.setContract(contract);

    List contractExpressions = calculatorService.getContractExpressions(
      Constants.L_INSP,
      jobContract.getContractCode(),
      cr.getContract().getPriceBookId(),
      location != null ? location : "*",
      (String)cr.getParameter(Constants.NOMINATION_DATE_STR),
      (String)cr.getParameter(Constants.LANGUAGE_CD)
    );

    // calculate LOT level
    if((contractExpressions.size() > 0) && (!notChargeInd))
    {
      List results = CalculatorManager.calculateInspection(
        cr,
        controlExtList,
        contractExpressions,
        modelType
      );

      for(int i=0; i<results.size(); i++)
      {
        CalculatorResult calculatorResult = (CalculatorResult)results.get(i);
        if(calculatorResult != null)
        {
          validResults.add(calculatorResult);
        }
      }
    }

    deleteUnusedJobContractProductInspectionResults(
      validResults,
      addJobContractProd
    );

    String descriptor = Constants.CALCULATOR_INSPECTION_DESCRIPTOR;
    if(modelType == 1)
    {
      descriptor = Constants.CALCULATOR_INSPECTION_MODEL_2_DESCRIPTOR;
    }

    for(int i=0; i<validResults.size(); i++)
    {
      CalculatorResult calculatorResult = (CalculatorResult)validResults.get(i);
      populateJobContractProductInspectionResult(
        calculatorResult,
        addJobContractProd,
        descriptor
      );
    }

    recalculateInspectionForVesselLevel(addJobContractVessel, contract, addJobContractProd, modelType);
    recalculateInspectionForContractLevel(addJobContract, contract, addJobContractVessel, addJobContractProd, modelType);

    return validResults.size();
  }

  public static void recalculateInspectionForContractLevel(
    AddJobContract addJobContract,
    CfgContract contract,
    AddJobContractVessel addJobContractVessel,
    AddJobContractProd addJobContractProd,
    int modelType
  )
  {
    if(addJobContract == null) return;
    if(modelType == 0)
    {
      deleteUnusedJobContractInspectionResults(
        new ArrayList(),
        addJobContract
      );

      return;
    }

    JobContract jobContract = addJobContract.getJobContract();
    if(jobContract == null) return;

    JobOrder jobOrder = jobContract.getJobOrder();
    if(jobOrder == null) return;

    JobContractProd jobContractProd = addJobContractProd != null ? addJobContractProd.getJobContractProd() : null;

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
        return;
      }
    }

    String location = jobContract.getZone();

    CalculatorRequest cr = new CalculatorRequest();
    cr.setParameter(Constants.CONTRACT_ID, jobContract.getContractCode());
    cr.setParameter(Constants.SERVICE_NAME, "L-Insp");
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
    cr.setParameter(Constants.LANGUAGE_CD, jobContract.getLanguage()); //hardcoded
    cr.setParameter(Constants.JOB_TYPE, jobOrder.getJobType());
    cr.setParameter(Constants.BRANCH_CODE, jobOrder.getBranchName());
    cr.setParameter(Constants.JOB_CODE, jobContract.getProductType());
    cr.setParameter(Constants.CURRENCY_CD, jobContract.getTransactionCurrencyCd());
    cr.setParameter(Constants.OVERRIDE_CURRENCY_RATE, jobContract.getOverrideCurrRate());

    cr.setContract(contract);

    List  contractExpressionsForContract = calculatorService.getContractExpressions(
      Constants.TOW_MAX,
      jobContract.getContractCode(),
      contract.getPriceBookId(),
      location != null ? location : "*",
      (String)cr.getParameter(Constants.NOMINATION_DATE_STR),
      (String)cr.getParameter(Constants.LANGUAGE_CD)
    );

    // calculate JobContract level
    List validResults = new ArrayList();
    if((contractExpressionsForContract != null) && (contractExpressionsForContract.size() > 0))
    {
      AddJobContractVessel[] addJobContractVessels = addJobContract.getAddJobContractVessels();
      if(addJobContractVessels != null)
      {
        for(int i=0; i<addJobContractVessels.length; i++)
        {
          if((addJobContractVessel == null) || (addJobContractVessel != addJobContractVessels[i]))
          {
            recalculateInspectionForVesselLevel(addJobContractVessels[i], contract, null, modelType);
          }
        }
      }

      JobContractProd maxQtyJobContractProd = populateTotalPriceAndMaxQtyJobContractProd(
        jobContract,
        cr,
        addJobContractVessel,
        addJobContractProd
      );
      if(maxQtyJobContractProd != null)
      {
        List controlExtList = getControlExtListFromJobContractProd(
          maxQtyJobContractProd,
          addJobContractProd
        );
        cr.setParameter(
          Constants.VESSEL_TYPE,
          maxQtyJobContractProd.getJobContractVessel().getType()
        );
        cr.setParameter(
          Constants.PRODUCT_GROUP_ID,
          maxQtyJobContractProd.getGroup()
        );
        cr.setParameter(Constants.MASTER_GROUP_ID, maxQtyJobContractProd.getProductGroupMaster());

        List results = CalculatorManager.calculateInspectionForContract(
          cr,
          controlExtList,
          contractExpressionsForContract
        );

        for(int i=0; i<results.size(); i++)
        {
          CalculatorResult calculatorResult = (CalculatorResult)results.get(i);
          if(calculatorResult != null)
          {
            Double maxPrice = (Double)calculatorResult.getParameter(Constants.MAX_PRICE);
            if((maxPrice != null) && (maxPrice.doubleValue() >  0))
            {
              validResults.add(calculatorResult);
            }
          }
        }
      }
    }

    deleteUnusedJobContractInspectionResults(
      validResults,
      addJobContract
    );

    for(int i=0; i<validResults.size(); i++)
    {
      CalculatorResult calculatorResult = (CalculatorResult)validResults.get(i);
      populateJobContractInspectionResult(
        calculatorResult,
        addJobContract,
        jobContract,
        1,
        addJobContractVessel,
        addJobContractProd
      );
    }
  }

  public static void recalculateInspectionForVesselLevel(
    AddJobContractVessel addJobContractVessel,
    CfgContract contract,
    AddJobContractProd addJobContractProd,
    int modelType
  )
  {
    if(addJobContractVessel == null) return;
    if(modelType == 0)
    {
      deleteUnusedJobContractVesselInspectionResults(
        new ArrayList(),
        addJobContractVessel
      );

      return;
    }

    JobContractVessel jobContractVessel = addJobContractVessel.getJobContractVessel();
    if(jobContractVessel == null) return;

    JobContract jobContract = jobContractVessel.getJobContract();
    if(jobContract == null) return;

    JobOrder jobOrder = jobContract.getJobOrder();
    if(jobOrder == null) return;

    JobContractProd jobContractProd = addJobContractProd != null ? addJobContractProd.getJobContractProd() : null;

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
        return;
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
    cr.setParameter(Constants.LANGUAGE_CD, jobContract.getLanguage()); //hardcoded
    cr.setParameter(Constants.JOB_TYPE, jobOrder.getJobType());
    cr.setParameter(Constants.BRANCH_CODE, jobOrder.getBranchName());
    cr.setParameter(Constants.JOB_CODE, jobContract.getProductType());
    cr.setParameter(Constants.CURRENCY_CD, jobContract.getTransactionCurrencyCd());
    cr.setParameter(Constants.OVERRIDE_CURRENCY_RATE, jobContract.getOverrideCurrRate());

    cr.setContract(contract);

    List contractExpressionsForVessel = calculatorService.getContractExpressions(
      Constants.VESSEL_INSP,
      jobContract.getContractCode(),
      contract.getPriceBookId(),
      location != null ? location : "*",
      (String)cr.getParameter(Constants.NOMINATION_DATE_STR),
      (String)cr.getParameter(Constants.LANGUAGE_CD)
    );

    Iterator it = jobContractVessel.getJobContractProds().iterator();
    while(it.hasNext())
    {
      JobContractProd tmpJobContractProd = (JobContractProd)it.next();
      if((jobContractProd == null) || (tmpJobContractProd != jobContractProd))
      {
        recalculateInspectionForProd(tmpJobContractProd, contract, modelType);
      }
    }

    // calculate Vessel level
    List validResults = new ArrayList();
    if(contractExpressionsForVessel.size() > 0)
    {
      JobContractProd maxQtyJobContractProd = populateTotalPriceAndMaxQtyJobContractProd(
        jobContractVessel,
        cr,
        addJobContractProd
      );
      if(maxQtyJobContractProd != null)
      {
        List controlExtList = getControlExtListFromJobContractProd(maxQtyJobContractProd, addJobContractProd);
        cr.setParameter(
          Constants.VESSEL_TYPE,
          maxQtyJobContractProd.getJobContractVessel().getType()
        );
        cr.setParameter(
          Constants.PRODUCT_GROUP_ID,
          maxQtyJobContractProd.getGroup()
        );
        cr.setParameter(Constants.MASTER_GROUP_ID, maxQtyJobContractProd.getProductGroupMaster());

        List results = CalculatorManager.calculateInspectionForVessel(
          cr,
          controlExtList,
          contractExpressionsForVessel
        );

        for(int i=0; i<results.size(); i++)
        {
          CalculatorResult calculatorResult = (CalculatorResult)results.get(i);
          if(calculatorResult != null)
          {
            Double maxPrice = (Double)calculatorResult.getParameter(Constants.MAX_PRICE);
            if((maxPrice != null) && (maxPrice.doubleValue() >  0))
            {
              validResults.add(calculatorResult);
            }
          }
        }
      }
    }

    deleteUnusedJobContractVesselInspectionResults(
      validResults,
      addJobContractVessel
    );

    for(int i=0; i<validResults.size(); i++)
    {
      CalculatorResult calculatorResult = (CalculatorResult)validResults.get(i);
      populateJobContractVesselInspectionResult(
        calculatorResult,
        addJobContractVessel,
        jobContract,
        1,
        addJobContractProd
      );
    }
  }

  public static void recalculateInspectionForProd(
    JobContractProd jobContractProd,
    CfgContract contract,
    int modelType
  )
  {
    if(jobContractProd == null) return;
    int size = jobContractProd.getResults().size();
    if(size == 0) return;

    Boolean notChargeInd = jobContractProd.getNotChargeInd();
    if((notChargeInd != null) && notChargeInd.booleanValue()) return;

    JobContractVessel jobContractVessel = jobContractProd.getJobContractVessel();
    if(jobContractVessel == null) return;

    JobContract jobContract = jobContractVessel.getJobContract();
    if(jobContract == null) return;

    JobOrder jobOrder = jobContract.getJobOrder();
    if(jobOrder == null) return;

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
        return;
      }
    }

    List validResults = new ArrayList();

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

    List contractExpressions = calculatorService.getContractExpressions(
      Constants.L_INSP,
      jobContract.getContractCode(),
      cr.getContract().getPriceBookId(),
      location != null ? location : "*",
      (String)cr.getParameter(Constants.NOMINATION_DATE_STR),
      (String)cr.getParameter(Constants.LANGUAGE_CD)
    );

    if(contractExpressions.size() > 0)
    {
      List controlExtList = getControlExtListFromJobContractProd(
        jobContractProd,
        null
      );
      List results = CalculatorManager.calculateInspection(
        cr,
        controlExtList,
        contractExpressions,
        modelType
      );

      for(int i=0; i<results.size(); i++)
      {
        CalculatorResult calculatorResult = (CalculatorResult)results.get(i);
        if(calculatorResult != null)
        {
          validResults.add(calculatorResult);
        }
      }
    }

    if(validResults.size() > 0)
    {
      JobContractProductInspectionResult jResult = null;
      Prebill prebill = null;
      Iterator it = jobContractProd.getResults().iterator();
      if(it.hasNext())
      {
        jResult = (JobContractProductInspectionResult)it.next();
        Iterator it1 = jResult.getPrebills().iterator();
        if(it1.hasNext())
        {
          prebill = (Prebill)it1.next();
        }
      }

      for(int i=0; i<validResults.size(); i++)
      {
        CalculatorResult calculatorResult = (CalculatorResult)validResults.get(i);

        CalculatorUtil.populatePrebillFromCalculatorResult(
          calculatorResult,
          prebill,
          (modelType == 0) ? Constants.CALCULATOR_INSPECTION_DESCRIPTOR : Constants.CALCULATOR_INSPECTION_MODEL_2_DESCRIPTOR
        );
      }
    }
  }

  public static void removeJobContractProductInspectionResultByIndex(
    AddJobContractProd addJobContractProd,
    Integer selectedIndex
  )
  {
    if((addJobContractProd == null) || (selectedIndex == null)) return;
    int sIndex = selectedIndex.intValue();

    if(sIndex < 0) return;

    List results = addJobContractProd.getResults();
    if(results != null)
    {
      if(sIndex < results.size())
      {
        JobContractProductInspectionResult jResult = (JobContractProductInspectionResult)results.remove(
          sIndex
        );

        if(jResult != null)
        {
          addJobContractProd.getDeletedResults().add(jResult);
          addJobContractProd.getJobContractProd().getResults().remove(jResult);
        }
      }
    }
  }

  public static void deleteUnusedJobContractProductControls(
    List controlExts,
    AddJobContractProd addJobContractProd
  )
  {
    if((controlExts == null) || (addJobContractProd == null)) return;

    Set oldControls = addJobContractProd.getJobContractProd().getControls();
    if((oldControls != null) && (oldControls.size() > 0))
    {
      Map newControlMap = new HashMap();

      for(int i=0; i<controlExts.size(); i++)
      {
        ControlExt controlExt = (ControlExt)controlExts.get(i);
        newControlMap.put(controlExt.getControl().getControlId().getObjectName(), controlExt);
      }

      Iterator it = oldControls.iterator();
      while(it.hasNext())
      {
        JobContractProdControl jControl = (JobContractProdControl)it.next();
        String objectName = jControl.getObjectName();
        ControlExt controlExt = (ControlExt)newControlMap.get(objectName);
        if(controlExt == null)
        {
          addJobContractProd.getDeletedControls().add(jControl);
        }
      }

      oldControls.removeAll(addJobContractProd.getDeletedControls());
    }
  }

  public static void populateControlsFromJobContractVesselControls(
    ControlExt[] controlExts,
    List jobContractVesselControls
  )
  {
    if((controlExts == null) || (jobContractVesselControls == null)) return;

    for(int i=0; i<controlExts.length; i++)
    {
      ControlExt controlExt = controlExts[i];
      for(int j=0; j<jobContractVesselControls.size(); j++)
      {
        JobContractVesselControl jControl = (JobContractVesselControl)jobContractVesselControls.get(j);
        if(jControl.getObjectName().equals(
          controlExt.getControl().getControlId().getObjectName()
        ))
        {
          controlExt.setDataInput(jControl.getInputValue0());
        }
      }
    }
  }

  public static void populateJobContractProductControl(
    ControlExt controlExt,
    AddJobContractProd addJobContractProd
  )
  {
    if((controlExt == null) || (addJobContractProd == null)) return;

    Set jControls = addJobContractProd.getJobContractProd().getControls();

    JobContractProdControl jControl = null;
    String objectName = controlExt.getControl().getControlId().getObjectName();

    Iterator it = jControls.iterator();
    while(it.hasNext())
    {
      JobContractProdControl tmpControl = (JobContractProdControl)it.next();
      if(tmpControl.getObjectName().equals(objectName))
      {
        jControl = tmpControl;
        break;
      }
    }
    if(jControl == null)
    {
      for(int i=0; i<addJobContractProd.getDeletedControls().size(); i++)
      {
        JobContractProdControl tmpControl = (JobContractProdControl)addJobContractProd.getDeletedControls().get(i);

        if(tmpControl.getObjectName().equals(objectName))
        {
          jControl = tmpControl;
          addJobContractProd.getDeletedControls().remove(i);
          break;
        }
      }
    }

    if(jControl == null)
    {
      jControl = new JobContractProdControl();
      jControl.setQuestionId(controlExt.getControl().getControlId().getQuestionId());
      jControl.setObjectName(objectName);
      jControl.setJobContractProd(addJobContractProd.getJobContractProd());
      addJobContractProd.getJobContractProd().getControls().add(jControl);
    }

    jControl.setInputValue0(controlExt.getDataInput());
  }

  public static void deleteUnusedJobContractProductInspectionResults(
    List calculatorResults,
    AddJobContractProd addJobContractProd
  )
  {
    if((addJobContractProd == null) || (calculatorResults == null)) return;

    List oldResults = addJobContractProd.getResults();

    if((oldResults != null) && (oldResults.size() > 0))
    {
      Map newResultMap = new HashMap();

      for(int i=0; i<calculatorResults.size(); i++)
      {
        CalculatorResult calculatorResult = (CalculatorResult)calculatorResults.get(i);
        ContractExpressionExt ceExt = calculatorResult.getContractExpressionExt();
        String expressionId = ceExt.getContractExpression().getContractExpressionId().getExpressionId();

        newResultMap.put(expressionId, calculatorResult);
      }

      for(int i=0; i<oldResults.size(); i++)
      {
        JobContractProductInspectionResult jResult = (JobContractProductInspectionResult)oldResults.get(i);
        String expressionId = jResult.getExpressionId();
        CalculatorResult calculatorResult = (CalculatorResult)newResultMap.get(expressionId);
        if(calculatorResult == null)
        {
          addJobContractProd.getDeletedResults().add(jResult);
        }
      }
    }
  }

  public static void deleteUnusedJobContractVesselInspectionResults(
    List calculatorResults,
    AddJobContractVessel addJobContractVessel
  )
  {
    if((addJobContractVessel == null) || (calculatorResults == null)) return;

    List oldResults = addJobContractVessel.getResults();

    if((oldResults != null) && (oldResults.size() > 0))
    {
      Map newResultMap = new HashMap();

      for(int i=0; i<calculatorResults.size(); i++)
      {
        CalculatorResult calculatorResult = (CalculatorResult)calculatorResults.get(i);
        ContractExpressionExt ceExt = calculatorResult.getContractExpressionExt();
        String expressionId = ceExt.getContractExpression().getContractExpressionId().getExpressionId();

        newResultMap.put(expressionId, calculatorResult);
      }

      for(int i=0; i<oldResults.size(); i++)
      {
        JobContractVesselInspectionResult jResult = (JobContractVesselInspectionResult)oldResults.get(i);
        String expressionId = jResult.getExpressionId();
        CalculatorResult calculatorResult = (CalculatorResult)newResultMap.get(expressionId);
        if(calculatorResult == null)
        {
          addJobContractVessel.getDeletedResults().add(jResult);
        }
      }
    }
  }

  public static void deleteUnusedJobContractInspectionResults(
    List calculatorResults,
    AddJobContract addJobContract
  )
  {
    if((addJobContract == null) || (calculatorResults == null)) return;

    List oldResults = addJobContract.getResults();

    if((oldResults != null) && (oldResults.size() > 0))
    {
      Map newResultMap = new HashMap();

      for(int i=0; i<calculatorResults.size(); i++)
      {
        CalculatorResult calculatorResult = (CalculatorResult)calculatorResults.get(i);
        ContractExpressionExt ceExt = calculatorResult.getContractExpressionExt();
        String expressionId = ceExt.getContractExpression().getContractExpressionId().getExpressionId();

        newResultMap.put(expressionId, calculatorResult);
      }

      for(int i=0; i<oldResults.size(); i++)
      {
        JobContractInspectionResult jResult = (JobContractInspectionResult)oldResults.get(i);
        String expressionId = jResult.getExpressionId();
        CalculatorResult calculatorResult = (CalculatorResult)newResultMap.get(expressionId);
        if(calculatorResult == null)
        {
          addJobContract.getDeletedResults().add(jResult);
        }
      }
    }
  }

  public static void populateJobContractProductInspectionResult(
    CalculatorResult calculatorResult,
    AddJobContractProd addJobContractProd,
    String descriptor
  )
  {
    if((calculatorResult == null) || (addJobContractProd == null)) return;

    JobContractProd jobContractProd = addJobContractProd.getJobContractProd();
    if(jobContractProd == null) return;

    JobContractVessel jobContractVessel = jobContractProd.getJobContractVessel();
    if(jobContractVessel == null) return;

    JobContract jobContract = jobContractVessel.getJobContract();
    if(jobContract == null) return;

    List jResults = addJobContractProd.getResults();
    if(jResults == null) return ;
    List deletedResults = addJobContractProd.getDeletedResults();

    ContractExpressionExt ceExt = calculatorResult.getContractExpressionExt();
    String expressionId = ceExt.getContractExpression().getContractExpressionId().getExpressionId();

    JobContractProductInspectionResult jResult = null;
    Prebill prebill = null;

    for(int i=0; i<jResults.size(); i++)
    {
      JobContractProductInspectionResult tmpResult = (JobContractProductInspectionResult)jResults.get(i);

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
        JobContractProductInspectionResult tmpResult = (JobContractProductInspectionResult)deletedResults.get(i);

        if(tmpResult.getExpressionId().equals(expressionId))
        {
          jResult = tmpResult;
          deletedResults.remove(i);
          addJobContractProd.getAddedResults().add(jResult);
          break;
        }
      }
    }

    if(jResult == null)
    {
      jResult = new JobContractProductInspectionResult();
      jResult.setExpressionId(expressionId);
      jResult.setJobContractProd(addJobContractProd.getJobContractProd());

      prebill = new Prebill();
      prebill.setJobContract(jobContract);
      if(jobContract != null){
      	prebill.setBaseCurrencyCd(jobContract.getBaseCurrencyCd());
      	prebill.setCurrencyCd(jobContract.getTransactionCurrencyCd());
      	prebill.setRateMult(jobContract.getRateMult());
      	prebill.setRateDiv(jobContract.getRateDiv());
      }
      prebill.setJobContractProductInspectionResult(jResult);
      jResult.getPrebills().add(prebill);

      addJobContractProd.getAddedResults().add(jResult);
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
        prebill.setJobContractProductInspectionResult(jResult);
        jResult.getPrebills().add(prebill);
      }
    }

    jResult.setLocation(ceExt.getContractExpression().getContractExpressionId().getLocation());
    Integer uomCode = (Integer)calculatorResult.getParameter(Constants.UOM_CODE);
    jResult.setUomFlag(uomCode);

    CalculatorUtil.populatePrebillFromCalculatorResult(
      calculatorResult,
      prebill,
      descriptor
    );
  }

  public static void populateJobContractVesselInspectionResult(
    CalculatorResult calculatorResult,
    AddJobContractVessel addJobContractVessel,
    JobContract jobContract,
    Integer uomCode,
    AddJobContractProd addJobContractProd
  )
  {
    if((calculatorResult == null) || (addJobContractVessel == null) || (jobContract == null)) return;

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
    jResult.setUomFlag(uomCode);

    CalculatorUtil.populatePrebillFromCalculatorResult(
      calculatorResult,
      prebill,
      Constants.CALCULATOR_INSPECTION_VESSEL_DESCRIPTOR
    );

    Double maxPrice = (Double)calculatorResult.getParameter(Constants.MAX_PRICE);
    if((maxPrice != null) && (maxPrice.doubleValue() >  0))
    {
      zeroLotLevelInspectionResults(
        addJobContractVessel.getJobContractVessel(),
        addJobContractProd
      );
    }
  }

  public static void populateJobContractInspectionResult(
    CalculatorResult calculatorResult,
    AddJobContract addJobContract,
    JobContract jobContract,
    Integer uomCode,
    AddJobContractVessel addJobContractVessel,
    AddJobContractProd addJobContractProd
  )
  {
    if((calculatorResult == null) || (addJobContract == null) || (jobContract == null)) return;

    List jResults = addJobContract.getResults();
    if(jResults == null) return ;
    List deletedResults = addJobContract.getDeletedResults();

    ContractExpressionExt ceExt = calculatorResult.getContractExpressionExt();
    String expressionId = ceExt.getContractExpression().getContractExpressionId().getExpressionId();

    JobContractInspectionResult jResult = null;
    Prebill prebill = null;

    for(int i=0; i<jResults.size(); i++)
    {
      JobContractInspectionResult tmpResult = (JobContractInspectionResult)jResults.get(i);

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
        JobContractInspectionResult tmpResult = (JobContractInspectionResult)deletedResults.get(i);

        if(tmpResult.getExpressionId().equals(expressionId))
        {
          jResult = tmpResult;
          deletedResults.remove(i);
          addJobContract.getAddedResults().add(jResult);
          break;
        }
      }
    }

    if(jResult == null)
    {
      jResult = new JobContractInspectionResult();
      jResult.setExpressionId(expressionId);
      jResult.setJobContract(addJobContract.getJobContract());

      prebill = new Prebill();
      prebill.setJobContract(jobContract);
      if(jobContract != null){
      	prebill.setBaseCurrencyCd(jobContract.getBaseCurrencyCd());
      	prebill.setCurrencyCd(jobContract.getTransactionCurrencyCd());
      	prebill.setRateMult(jobContract.getRateMult());
      	prebill.setRateDiv(jobContract.getRateDiv());
      }
      prebill.setJobContractInspectionResult(jResult);
      jResult.getPrebills().add(prebill);

      addJobContract.getAddedResults().add(jResult);
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
        prebill.setJobContractInspectionResult(jResult);
        jResult.getPrebills().add(prebill);
      }
    }

    jResult.setLocation(ceExt.getContractExpression().getContractExpressionId().getLocation());
    jResult.setUomFlag(uomCode);

    CalculatorUtil.populatePrebillFromCalculatorResult(
      calculatorResult,
      prebill,
      Constants.CALCULATOR_INSPECTION_CONTRACT_DESCRIPTOR
    );

    Double maxPrice = (Double)calculatorResult.getParameter(Constants.MAX_PRICE);
    if((maxPrice != null) && (maxPrice.doubleValue() >  0))
    {
      zeroVesselLevelInspectionResults(
        addJobContract.getJobContract(),
        addJobContractVessel,
        addJobContractProd
      );
    }
  }

  public static JobContractProd populateTotalPriceAndMaxQtyJobContractProd(
    JobContractVessel jobContractVessel,
    CalculatorRequest cr,
    AddJobContractProd addJobContractProd
  )
  {
    if((jobContractVessel == null) || (cr == null)) return null;

    double totalLotInspPrice = 0.0;
    double maxQty = 0.0;
    JobContractProd maxQtyJobContractProd = null;
    Iterator it = jobContractVessel.getJobContractProds().iterator();
    while(it.hasNext())
    {
      JobContractProd jobContractProd = (JobContractProd)it.next();

      Boolean notChargeInd = jobContractProd.getNotChargeInd();
      if((notChargeInd != null) && !notChargeInd.booleanValue())
      {
        JobContractProductInspectionResult jResult = null;

        if((addJobContractProd != null)&& (jobContractProd == addJobContractProd.getJobContractProd()))
        {
          if(addJobContractProd.getDeletedResults().size() == 0)
          {
            Iterator it1 = addJobContractProd.getAddedResults().iterator();
            if(it1.hasNext())
            {
              jResult = (JobContractProductInspectionResult)it1.next();
            }

            if(jResult == null)
            {
              Iterator it2 = jobContractProd.getResults().iterator();
              if(it2.hasNext())
              {
                jResult = (JobContractProductInspectionResult)it2.next();
              }
            }
          }
        }
        else
        {
          Iterator it1 = jobContractProd.getResults().iterator();
          if(it1.hasNext())
          {
            jResult = (JobContractProductInspectionResult)it1.next();
          }
        }

        if(jResult != null)
        {
          double totalPrice = getPriceFromPrebillSet(jResult.getPrebills(), true);
          totalLotInspPrice += totalPrice;

          double qty = getQtyFromJobContractProdControlSet(jResult.getUomFlag(), jobContractProd.getControls());
          if(qty > maxQty)
          {
            maxQty = qty;
            maxQtyJobContractProd = jobContractProd;
          }
        }
      }
    }

    cr.setParameter(Constants.TOTAL_LOT_INSP_PRICE, totalLotInspPrice);
    cr.setParameter(Constants.MAX_LOT_INSP_QTY, maxQty);

    return maxQtyJobContractProd;
  }

  public static JobContractProd populateTotalPriceAndMaxQtyJobContractProd(
    JobContract jobContract,
    CalculatorRequest cr,
    AddJobContractVessel addJobContractVessel,
    AddJobContractProd addJobContractProd
  )
  {
    if((jobContract == null) || (cr == null)) return null;

    double totalLotInspPrice = 0.0;
    double maxQty = 0.0;
    JobContractProd maxQtyJobContractProd = null;
    Iterator it = jobContract.getJobContractVessels().iterator();
    while(it.hasNext())
    {
      JobContractVessel jobContractVessel = (JobContractVessel)it.next();

      JobContractVesselInspectionResult jResult = null;
      if((addJobContractVessel != null)&& (jobContractVessel == addJobContractVessel.getJobContractVessel()))
      {
        if(addJobContractVessel.getDeletedResults().size() == 0)
        {
          Iterator it1 = addJobContractVessel.getAddedResults().iterator();
          if(it1.hasNext())
          {
            jResult = (JobContractVesselInspectionResult)it1.next();
          }

          if(jResult == null)
          {
            Iterator it2 = jobContractVessel.getResults().iterator();
            if(it2.hasNext())
            {
              jResult = (JobContractVesselInspectionResult)it2.next();
            }
          }
        }
      }
      else
      {
        Iterator it1 = jobContractVessel.getResults().iterator();
        if(it1.hasNext())
        {
          jResult = (JobContractVesselInspectionResult)it1.next();
        }
      }

      JobContractProd jobContractProd = populateTotalPriceAndMaxQtyJobContractProd(
        jobContractVessel,
        cr,
        addJobContractProd
      );

      if(jobContractProd != null)
      {
        Double qty = (Double)cr.getParameter(Constants.MAX_LOT_INSP_QTY);
        if(qty != null)
        {
          if(qty.doubleValue() > maxQty)
          {
            maxQty = qty.doubleValue();
            maxQtyJobContractProd = jobContractProd;
          }
        }

        if(jResult != null)
        {
          double totalPrice = getPriceFromPrebillSet(jResult.getPrebills(), true);
          totalLotInspPrice += totalPrice;
        }
        else
        {
          Double totalPriceObj = (Double)cr.getParameter(Constants.TOTAL_LOT_INSP_PRICE);
          if(totalPriceObj != null)
          {
            totalLotInspPrice += totalPriceObj.doubleValue();
          }
        }
      }
    }

    cr.setParameter(Constants.TOTAL_VESSEL_INSP_PRICE, totalLotInspPrice);

    return maxQtyJobContractProd;
  }

  public static double getPriceFromPrebillSet(Set prebills, boolean ignoreSplit)
  {
    double price = 0.0;

    if(prebills == null) return price;

    Iterator it = prebills.iterator();
    while(it.hasNext())
    {
      Prebill prebill = (Prebill)it.next();

      if(ignoreSplit)
      {
        price = prebill.getNetPrice();
      }
      else
      {
        double splitPct = prebill.getSplitPct();
        if(splitPct != 0)
        {
          price = prebill.getNetPrice() * 100 / splitPct;
        }
      }

      double discountPct = prebill.getDiscountPct();
      if(discountPct == 100) price = 0.0;
      else if(discountPct > 0) price = price / (1.0 - discountPct * 0.01);
    }

    return price;
  }

  public static double getQtyFromJobContractProdControlSet(Integer uomFlag, Set controls)
  {
    double qty = 0.0;
    int uomCode = 1;
    if(uomFlag != null) uomCode = uomFlag.intValue();

    if(controls == null) return qty;

    Iterator it = controls.iterator();
    while(it.hasNext())
    {
      JobContractProdControl jobContractProdControl = (JobContractProdControl)it.next();

      String objectName = jobContractProdControl.getObjectName();
      String qtyStr = null;
      if(Constants.noOfUOM.equals(objectName))
      {
        if(uomCode == 1) qtyStr = jobContractProdControl.getInputValue0();
      }
      else if(Constants.noOfUOM1.equals(objectName))
      {
        if(uomCode == 2) qtyStr = jobContractProdControl.getInputValue0();
      }
      else if(Constants.noOfUOM2.equals(objectName))
      {
        if(uomCode == 3) qtyStr = jobContractProdControl.getInputValue0();
      }

      if(qtyStr != null)
      {
        try { qty = new Double(qtyStr); } catch(Exception e) { }
        break;
      }
    }

    return qty;
  }

  public static List getControlExtListFromJobContractProd(
    JobContractProd jobContractProd,
    AddJobContractProd addJobContractProd
  )
  {
    List controlExtList = new ArrayList();

    if(jobContractProd == null) return controlExtList;

    Boolean notChargeInd = jobContractProd.getNotChargeInd();
    if((notChargeInd != null) && !notChargeInd.booleanValue())
    {
      JobContractProductInspectionResult jResult = null;
      if((addJobContractProd != null)&& (jobContractProd == addJobContractProd.getJobContractProd()))
      {
        if(addJobContractProd.getDeletedResults().size() == 0)
        {
          Iterator it1 = addJobContractProd.getAddedResults().iterator();
          if(it1.hasNext())
          {
            jResult = (JobContractProductInspectionResult)it1.next();
          }

          if(jResult == null)
          {
            Iterator it2 = jobContractProd.getResults().iterator();
            if(it2.hasNext())
            {
              jResult = (JobContractProductInspectionResult)it2.next();
            }
          }
        }
      }
      else
      {
        Iterator it1 = jobContractProd.getResults().iterator();
        if(it1.hasNext())
        {
          jResult = (JobContractProductInspectionResult)it1.next();
        }
      }

      if(jResult != null)
      {
        ControlExt controlExt = new ControlExt();
        int uomCode = 1;
        if(jResult.getUomFlag() != null) uomCode = jResult.getUomFlag().intValue();
        controlExt.setParameter(Constants.UOM_CODE, uomCode);

        Iterator it = jobContractProd.getControls().iterator();
        while(it.hasNext())
        {
          JobContractProdControl jobContractProdControl = (JobContractProdControl)it.next();

          String objectName = jobContractProdControl.getObjectName();
          if(Constants.noOfUOM.equals(objectName))
          {
            Double qty = null;
            try { qty = new Double(jobContractProdControl.getInputValue0()); } catch(Exception e) { }
            controlExt.setParameter(Constants.QUANTITY, qty);
          }
          else if(Constants.noOfUOM1.equals(objectName))
          {
            Double qty = null;
            try { qty = new Double(jobContractProdControl.getInputValue0()); } catch(Exception e) { }
            controlExt.setParameter(Constants.QUANTITY1, qty);
          }
          else if(Constants.noOfUOM2.equals(objectName))
          {
            Double qty = null;
            try { qty = new Double(jobContractProdControl.getInputValue0()); } catch(Exception e) { }
            controlExt.setParameter(Constants.QUANTITY2, qty);
          }
          else if(Constants.ADDITIONAL_LOT.equals(objectName))
          {
            boolean tmp = false;
            try { tmp = Constants.Yes.equals(jobContractProdControl.getInputValue0()); } catch(Exception e) { }
            controlExt.setParameter(Constants.ADDITIONAL_LOT, tmp);
          }
          else if(Constants.BOOLEAN_VAL_1.equals(objectName))
          {
            boolean tmp = false;
            try { tmp = Constants.Yes.equals(jobContractProdControl.getInputValue0()); } catch(Exception e) { }
            controlExt.setParameter(Constants.BOOLEAN_VAL_1, tmp);
          }
          else if(Constants.ADDITIONAL_VESSEL.equals(objectName))
          {
            boolean tmp = false;
            try { tmp = Constants.Yes.equals(jobContractProdControl.getInputValue0()); } catch(Exception e) { }
            controlExt.setParameter(Constants.ADDITIONAL_VESSEL, tmp);
          }
        }

        controlExtList.add(controlExt);
      }
    }

    return controlExtList;
  }

  public static void zeroLotLevelInspectionResults(
    JobContractVessel jobContractVessel,
    AddJobContractProd addJobContractProd
  )
  {
    if(jobContractVessel == null) return;

    Iterator it = jobContractVessel.getJobContractProds().iterator();
    while(it.hasNext())
    {
      JobContractProd jobContractProd = (JobContractProd)it.next();

      JobContractProductInspectionResult jResult = null;
      if((addJobContractProd != null)&& (jobContractProd == addJobContractProd.getJobContractProd()))
      {
        if(addJobContractProd.getDeletedResults().size() == 0)
        {
          Iterator it1 = addJobContractProd.getAddedResults().iterator();
          if(it1.hasNext())
          {
            jResult = (JobContractProductInspectionResult)it1.next();
          }

          if(jResult == null)
          {
            Iterator it2 = jobContractProd.getResults().iterator();
            if(it2.hasNext())
            {
              jResult = (JobContractProductInspectionResult)it2.next();
            }
          }
        }
      }
      else
      {
        Iterator it1 = jobContractProd.getResults().iterator();
        if(it1.hasNext())
        {
          jResult = (JobContractProductInspectionResult)it1.next();
        }
      }

      if(jResult != null)
      {
        zeroPriceForPrebillSet(jResult.getPrebills());
      }
    }
  }

  public static void zeroVesselLevelInspectionResults(
    JobContract jobContract,
    AddJobContractVessel addJobContractVessel,
    AddJobContractProd addJobContractProd
  )
  {
    if(jobContract == null) return;

    Iterator it = jobContract.getJobContractVessels().iterator();
    while(it.hasNext())
    {
      JobContractVessel jobContractVessel = (JobContractVessel)it.next();

      zeroLotLevelInspectionResults(jobContractVessel, addJobContractProd);

      JobContractVesselInspectionResult jResult = null;
      if((addJobContractVessel != null)&& (jobContractVessel == addJobContractVessel.getJobContractVessel()))
      {
        if(addJobContractVessel.getDeletedResults().size() == 0)
        {
          Iterator it1 = addJobContractVessel.getAddedResults().iterator();
          if(it1.hasNext())
          {
            jResult = (JobContractVesselInspectionResult)it1.next();
          }

          if(jResult == null)
          {
            Iterator it2 = jobContractVessel.getResults().iterator();
            if(it2.hasNext())
            {
              jResult = (JobContractVesselInspectionResult)it2.next();
            }
          }
        }
      }
      else
      {
        Iterator it1 = jobContractVessel.getResults().iterator();
        if(it1.hasNext())
        {
          jResult = (JobContractVesselInspectionResult)it1.next();
        }
      }

      if(jResult != null)
      {
        zeroPriceForPrebillSet(jResult.getPrebills());
      }
    }
  }

  public static void zeroPriceForPrebillSet(Set prebills)
  {
    if(prebills == null) return;

    Iterator it = prebills.iterator();
    while(it.hasNext())
    {
      Prebill prebill = (Prebill)it.next();

      prebill.setNetPrice(0.0);
      prebill.setUnitPrice(0.0);
   	  prebill.setBaseUnitPrice(0.0);
      prebill.setBaseNetPrice(0.0);
    }
  }
}

