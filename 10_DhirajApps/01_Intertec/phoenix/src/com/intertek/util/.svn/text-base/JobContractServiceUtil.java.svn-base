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
import com.intertek.domain.AddJobContract;
import com.intertek.domain.AddJobContractServices;
import com.intertek.domain.JobContractServiceExt;
import com.intertek.entity.JobContract;
import com.intertek.entity.JobContractService;
import com.intertek.entity.JobContractServiceControl;
import com.intertek.entity.JobContractServiceResult;
import com.intertek.entity.Prebill;

public class JobContractServiceUtil
{
  public static void removeJobContractServiceByIndex(
    AddJobContract addJobContract,
    Integer selectedServiceIndex
  )
  {
    if((addJobContract == null) || (selectedServiceIndex == null)) return;
    int sIndex = selectedServiceIndex.intValue();

    if(sIndex < 0) return;

    AddJobContractServices addJobContractServices = addJobContract.getAddJobContractServices();
    if(addJobContractServices != null)
    {
      List addedJobContractServices = addJobContractServices.getAddedJobContractServices();
      if(addedJobContractServices != null)
      {
        if(sIndex < addedJobContractServices.size())
        {
          JobContractServiceExt jobContractServiceExt = (JobContractServiceExt)addedJobContractServices.remove(sIndex);

          if(jobContractServiceExt != null)
          {
            JobContractService jobContractService = jobContractServiceExt.getService();
            addJobContract.getJobContract().getJobContractServices().remove(jobContractService);
          }
        }
      }
    }
  }

  public static JobContractServiceExt getJobContractServiceExtByIndex(
    AddJobContract addJobContract,
    Integer index
  )
  {
    if((addJobContract == null) || (index == null)) return null;

    if(index < 0) return null;

    AddJobContractServices addJobContractServices = addJobContract.getAddJobContractServices();
    if(addJobContractServices != null)
    {
      List addedJobContractServices = addJobContractServices.getAddedJobContractServices();
      if(addedJobContractServices != null)
      {
        if(index < addedJobContractServices.size())
        {
          JobContractServiceExt jobContractServiceExt = (JobContractServiceExt)addedJobContractServices.get(
            index.intValue()
          );

          return jobContractServiceExt;
        }
      }
    }

    return null;
  }

  public static void populateControlsFromJobContractServiceControls(
    ControlExt[] controlExts,
    List jobContractServiceControls
  )
  {
    if((controlExts == null) || (jobContractServiceControls == null)) return;

    for(int i=0; i<controlExts.length; i++)
    {
      ControlExt controlExt = controlExts[i];
      for(int j=0; j<jobContractServiceControls.size(); j++)
      {
        JobContractServiceControl jControl = (JobContractServiceControl)jobContractServiceControls.get(j);
        if(jControl.getObjectName().equals(
          controlExt.getControl().getControlId().getObjectName()
        ))
        {
          controlExt.setDataInput(jControl.getInputValue0());
        }
      }
    }
  }


  public static void populateJobContractServiceControl(
    ControlExt controlExt,
    JobContractServiceExt jobContractServiceExt
  )
  {
    if((controlExt == null) || (jobContractServiceExt == null)) return;

    Set jControls = jobContractServiceExt.getService().getControls();

    JobContractServiceControl jControl = null;
    String objectName = controlExt.getControl().getControlId().getObjectName();

    Iterator it = jControls.iterator();
    while(it.hasNext())
    {
      JobContractServiceControl tmpControl = (JobContractServiceControl)it.next();
      if(tmpControl.getObjectName().equals(objectName))
      {
        jControl = tmpControl;
        break;
      }
    }
    if(jControl == null)
    {
      for(int i=0; i<jobContractServiceExt.getDeletedControls().size(); i++)
      {
        JobContractServiceControl tmpControl = (JobContractServiceControl)jobContractServiceExt.getDeletedControls().get(i);

        if(tmpControl.getObjectName().equals(objectName))
        {
          jControl = tmpControl;
          jobContractServiceExt.getDeletedControls().remove(i);
          break;
        }
      }
    }

    if(jControl == null)
    {
      jControl = new JobContractServiceControl();
      jControl.setQuestionId(controlExt.getControl().getControlId().getQuestionId());
      jControl.setObjectName(objectName);
      jControl.setJobContractService(jobContractServiceExt.getService());
      jobContractServiceExt.getService().getControls().add(jControl);
    }

    jControl.setInputValue0(controlExt.getDataInput());
  }

  public static void deleteUnusedJobContractServiceControls(
    List controlExts,
    JobContractServiceExt jobContractServiceExt
  )
  {
    if((controlExts == null) || (jobContractServiceExt == null)) return;

    Set oldControls = jobContractServiceExt.getService().getControls();
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
        JobContractServiceControl jControl = (JobContractServiceControl)it.next();
        String objectName = jControl.getObjectName();
        ControlExt controlExt = (ControlExt)newControlMap.get(objectName);
        if(controlExt == null)
        {
          jobContractServiceExt.getDeletedControls().add(jControl);
        }
      }

      oldControls.removeAll(jobContractServiceExt.getDeletedControls());
    }
  }

  public static void deleteUnusedJobContractServiceResults(
    List calculatorResults,
    JobContractServiceExt jobContractServiceExt
  )
  {
    if((jobContractServiceExt == null) || (calculatorResults == null)) return;

    List oldResults = jobContractServiceExt.getResults();

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
        JobContractServiceResult jResult = (JobContractServiceResult)oldResults.get(i);
        String expressionId = jResult.getExpressionId();
        CalculatorResult calculatorResult = (CalculatorResult)newResultMap.get(expressionId);
        if(calculatorResult == null)
        {
          jobContractServiceExt.getDeletedResults().add(jResult);
        }
      }
    }
  }

  public static void populateJobContractServiceResult(
    CalculatorResult calculatorResult,
    JobContractServiceExt jobContractServiceExt,
    JobContract jobContract
  )
  {
    if((calculatorResult == null) || (jobContractServiceExt == null) || (jobContract == null)) return;

    List jResults = jobContractServiceExt.getResults();
    if(jResults == null) return ;
    List deletedResults = jobContractServiceExt.getDeletedResults();

    ContractExpressionExt ceExt = calculatorResult.getContractExpressionExt();
    String expressionId = ceExt.getContractExpression().getContractExpressionId().getExpressionId();

    JobContractServiceResult jResult = null;
    Prebill prebill = null;

    for(int i=0; i<jResults.size(); i++)
    {
      JobContractServiceResult tmpResult = (JobContractServiceResult)jResults.get(i);

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
        JobContractServiceResult tmpResult = (JobContractServiceResult)deletedResults.get(i);

        if(tmpResult.getExpressionId().equals(expressionId))
        {
          jResult = tmpResult;
          deletedResults.remove(i);
          jobContractServiceExt.getAddedResults().add(jResult);
          break;
        }
      }
    }

    if(jResult == null)
    {
      jResult = new JobContractServiceResult();
      jResult.setExpressionId(expressionId);
      jResult.setJobContractService(jobContractServiceExt.getService());

      prebill = new Prebill();
      prebill.setJobContract(jobContract);
      if(jobContract != null){
      	prebill.setBaseCurrencyCd(jobContract.getBaseCurrencyCd());
      	prebill.setCurrencyCd(jobContract.getTransactionCurrencyCd());
      	prebill.setRateMult(jobContract.getRateMult());
      	prebill.setRateDiv(jobContract.getRateDiv());
      }
      prebill.setJobContractServiceResult(jResult);
      jResult.getPrebills().add(prebill);

      jobContractServiceExt.getAddedResults().add(jResult);
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
        prebill.setJobContractServiceResult(jResult);
        jResult.getPrebills().add(prebill);
      }
    }

    jResult.setLocation(ceExt.getContractExpression().getContractExpressionId().getLocation());

    CalculatorUtil.populatePrebillFromCalculatorResult(calculatorResult, prebill, null);
  }
}

