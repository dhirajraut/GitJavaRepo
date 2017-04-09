package com.intertek.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.intertek.calculator.CalculatorResult;
import com.intertek.calculator.CalculatorUtil;
import com.intertek.calculator.ContractExpressionExt;
import com.intertek.calculator.ControlExt;
import com.intertek.domain.AddJobContractProd;
import com.intertek.domain.AddJobContractProductServices;
import com.intertek.domain.JobContractProductServiceExt;
import com.intertek.entity.JobContract;
import com.intertek.entity.JobContractProductService;
import com.intertek.entity.JobContractProductServiceControl;
import com.intertek.entity.JobContractProductServiceResult;
import com.intertek.entity.Prebill;

public class JobContractProductServiceUtil
{
  public static void removeJobContractProductServiceByIndex(
    AddJobContractProd addJobContractProd,
    Integer selectedServiceIndex
  )
  {
    if((addJobContractProd == null) || (selectedServiceIndex == null)) return;
    int sIndex = selectedServiceIndex.intValue();

    if(sIndex < 0) return;

    AddJobContractProductServices addJobContractProductServices = addJobContractProd.getAddJobContractProductServices();
    if(addJobContractProductServices != null)
    {
      List addedJobContractProductServices = addJobContractProductServices.getAddedJobContractProductServices();
      if(addedJobContractProductServices != null)
      {
        if(sIndex < addedJobContractProductServices.size())
        {
          JobContractProductServiceExt jobContractProductServiceExt = (JobContractProductServiceExt)addedJobContractProductServices.remove(
            sIndex
          );

          if(jobContractProductServiceExt != null)
          {
            JobContractProductService jobContractProductService = jobContractProductServiceExt.getService();
            addJobContractProd.getJobContractProd().getJobContractProductServices().remove(jobContractProductService);
          }
        }
      }
    }
  }

  public static JobContractProductServiceExt getJobContractProductServiceExtByIndex(
    AddJobContractProd addJobContractProd,
    Integer index
  )
  {
    if((addJobContractProd == null) || (index == null)) return null;

    if(index < 0) return null;

    AddJobContractProductServices addJobContractProductServices = addJobContractProd.getAddJobContractProductServices();
    if(addJobContractProductServices != null)
    {
      List addedJobContractProductServices = addJobContractProductServices.getAddedJobContractProductServices();
      if(addedJobContractProductServices != null)
      {
        if(index < addedJobContractProductServices.size())
        {
          JobContractProductServiceExt jobContractProductServiceExt = (JobContractProductServiceExt)addedJobContractProductServices.get(
            index.intValue()
          );

          return jobContractProductServiceExt;
        }
      }
    }

    return null;
  }

  public static void populateControlsFromJobContractProductServiceControls(
    ControlExt[] controlExts,
    List jobContractProductServiceControls
  )
  {
    if((controlExts == null) || (jobContractProductServiceControls == null)) return;

    for(int i=0; i<controlExts.length; i++)
    {
      ControlExt controlExt = controlExts[i];
      for(int j=0; j<jobContractProductServiceControls.size(); j++)
      {
        JobContractProductServiceControl jControl = (JobContractProductServiceControl)jobContractProductServiceControls.get(j);
        if(jControl.getObjectName().equals(
          controlExt.getControl().getControlId().getObjectName()
        ))
        {
          controlExt.setDataInput(jControl.getInputValue0());
        }
      }
    }
  }


  public static void populateJobContractProductServiceControl(
    ControlExt controlExt,
    JobContractProductServiceExt jobContractProductServiceExt
  )
  {
    if((controlExt == null) || (jobContractProductServiceExt == null)) return;

    Set jControls = jobContractProductServiceExt.getService().getControls();

    JobContractProductServiceControl jControl = null;
    String objectName = controlExt.getControl().getControlId().getObjectName();

    Iterator it = jControls.iterator();
    while(it.hasNext())
    {
      JobContractProductServiceControl tmpControl = (JobContractProductServiceControl)it.next();
      if(tmpControl.getObjectName().equals(objectName))
      {
        jControl = tmpControl;
        break;
      }
    }
    if(jControl == null)
    {
      for(int i=0; i<jobContractProductServiceExt.getDeletedControls().size(); i++)
      {
        JobContractProductServiceControl tmpControl = (JobContractProductServiceControl)jobContractProductServiceExt.getDeletedControls().get(i);

        if(tmpControl.getObjectName().equals(objectName))
        {
          jControl = tmpControl;
          jobContractProductServiceExt.getDeletedControls().remove(i);
          break;
        }
      }
    }

    if(jControl == null)
    {
      jControl = new JobContractProductServiceControl();
      jControl.setQuestionId(controlExt.getControl().getControlId().getQuestionId());
      jControl.setObjectName(objectName);
      jControl.setJobContractProductService(jobContractProductServiceExt.getService());
      jobContractProductServiceExt.getService().getControls().add(jControl);
    }

    jControl.setInputValue0(controlExt.getDataInput());
  }

  public static void deleteUnusedJobContractProductServiceControls(
    List controlExts,
    JobContractProductServiceExt jobContractProductServiceExt
  )
  {
    if((controlExts == null) || (jobContractProductServiceExt == null)) return;

    Set oldControls = jobContractProductServiceExt.getService().getControls();
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
        JobContractProductServiceControl jControl = (JobContractProductServiceControl)it.next();
        String objectName = jControl.getObjectName();
        ControlExt controlExt = (ControlExt)newControlMap.get(objectName);
        if(controlExt == null)
        {
          jobContractProductServiceExt.getDeletedControls().add(jControl);
        }
      }

      oldControls.removeAll(jobContractProductServiceExt.getDeletedControls());
    }
  }

  public static void deleteUnusedJobContractProductServiceResults(
    List calculatorResults,
    JobContractProductServiceExt jobContractProductServiceExt
  )
  {
    if((jobContractProductServiceExt == null) || (calculatorResults == null)) return;

    List oldResults = jobContractProductServiceExt.getResults();

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
        JobContractProductServiceResult jResult = (JobContractProductServiceResult)oldResults.get(i);
        String expressionId = jResult.getExpressionId();
        CalculatorResult calculatorResult = (CalculatorResult)newResultMap.get(expressionId);
        if(calculatorResult == null)
        {
          jobContractProductServiceExt.getDeletedResults().add(jResult);
        }
      }
    }
  }

  public static void populateJobContractProductServiceResult(
    CalculatorResult calculatorResult,
    JobContractProductServiceExt jobContractProductServiceExt,
    JobContract jobContract
  )
  {
    if((calculatorResult == null) || (jobContractProductServiceExt == null) || (jobContract == null)) return;

    List jResults = jobContractProductServiceExt.getResults();
    if(jResults == null) return ;
    List deletedResults = jobContractProductServiceExt.getDeletedResults();

    ContractExpressionExt ceExt = calculatorResult.getContractExpressionExt();
    String expressionId = ceExt.getContractExpression().getContractExpressionId().getExpressionId();

    JobContractProductServiceResult jResult = null;
    Prebill prebill = null;

    for(int i=0; i<jResults.size(); i++)
    {
      JobContractProductServiceResult tmpResult = (JobContractProductServiceResult)jResults.get(i);

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
        JobContractProductServiceResult tmpResult = (JobContractProductServiceResult)deletedResults.get(i);

        if(tmpResult.getExpressionId().equals(expressionId))
        {
          jResult = tmpResult;
          deletedResults.remove(i);
          jobContractProductServiceExt.getAddedResults().add(jResult);
          break;
        }
      }
    }

    if(jResult == null)
    {
      jResult = new JobContractProductServiceResult();
      jResult.setExpressionId(expressionId);
      jResult.setJobContractProductService(jobContractProductServiceExt.getService());

      prebill = new Prebill();
      prebill.setJobContract(jobContract);
      if(jobContract != null){
      	prebill.setBaseCurrencyCd(jobContract.getBaseCurrencyCd());
      	prebill.setCurrencyCd(jobContract.getTransactionCurrencyCd());
      	prebill.setRateMult(jobContract.getRateMult());
      	prebill.setRateDiv(jobContract.getRateDiv());
      }
      prebill.setJobContractProductServiceResult(jResult);
      jResult.getPrebills().add(prebill);

      jobContractProductServiceExt.getAddedResults().add(jResult);
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
        prebill.setJobContractProductServiceResult(jResult);
        jResult.getPrebills().add(prebill);
      }
    }

    jResult.setLocation(ceExt.getContractExpression().getContractExpressionId().getLocation());

    CalculatorUtil.populatePrebillFromCalculatorResult(calculatorResult, prebill, null);
  }
}

