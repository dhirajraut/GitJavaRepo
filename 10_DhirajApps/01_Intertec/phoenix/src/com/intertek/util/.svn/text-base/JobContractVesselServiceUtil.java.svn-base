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
import com.intertek.domain.AddJobContractVessel;
import com.intertek.domain.AddJobContractVesselServices;
import com.intertek.domain.JobContractVesselServiceExt;
import com.intertek.entity.JobContract;
import com.intertek.entity.JobContractVesselService;
import com.intertek.entity.JobContractVesselServiceControl;
import com.intertek.entity.JobContractVesselServiceResult;
import com.intertek.entity.Prebill;

public class JobContractVesselServiceUtil
{

  public static void removeJobContractVesselServiceByIndex(
    AddJobContractVessel addJobContractVessel,
    Integer selectedServiceIndex
  )
  {
    if((addJobContractVessel == null) || (selectedServiceIndex == null)) return;
    int sIndex = selectedServiceIndex.intValue();

    if(sIndex < 0) return;

    AddJobContractVesselServices addJobContractVesselServices = addJobContractVessel.getAddJobContractVesselServices();
    if(addJobContractVesselServices != null)
    {
      List addedJobContractVesselServices = addJobContractVesselServices.getAddedJobContractVesselServices();
      if(addedJobContractVesselServices != null)
      {
        if(sIndex < addedJobContractVesselServices.size())
        {
          JobContractVesselServiceExt jobContractVesselServiceExt = (JobContractVesselServiceExt)addedJobContractVesselServices.remove(
            sIndex
          );

          if(jobContractVesselServiceExt != null)
          {
            JobContractVesselService jobContractVesselService = jobContractVesselServiceExt.getService();

            addJobContractVessel.getJobContractVessel().getJobContractVesselServices().remove(jobContractVesselService);
          }
        }
      }
    }
  }

  public static JobContractVesselServiceExt getJobContractVesselServiceExtByIndex(
    AddJobContractVessel addJobContractVessel,
    Integer index
  )
  {
    if((addJobContractVessel == null) || (index == null)) return null;

    if(index < 0) return null;

    AddJobContractVesselServices addJobContractVesselServices = addJobContractVessel.getAddJobContractVesselServices();
    if(addJobContractVesselServices != null)
    {
      List addedJobContractVesselServices = addJobContractVesselServices.getAddedJobContractVesselServices();
      if(addedJobContractVesselServices != null)
      {
        if(index < addedJobContractVesselServices.size())
        {
          JobContractVesselServiceExt jobContractVesselServiceExt = (JobContractVesselServiceExt)addedJobContractVesselServices.get(
            index.intValue()
          );

          return jobContractVesselServiceExt;
        }
      }
    }

    return null;
  }

  public static void populateControlsFromJobContractVesselServiceControls(
    ControlExt[] controlExts,
    List jobContractVesselServiceControls
  )
  {
    if((controlExts == null) || (jobContractVesselServiceControls == null)) return;

    for(int i=0; i<controlExts.length; i++)
    {
      ControlExt controlExt = controlExts[i];
      for(int j=0; j<jobContractVesselServiceControls.size(); j++)
      {
        JobContractVesselServiceControl jControl = (JobContractVesselServiceControl)jobContractVesselServiceControls.get(j);
        if(jControl.getObjectName().equals(
          controlExt.getControl().getControlId().getObjectName()
        ))
        {
          controlExt.setDataInput(jControl.getInputValue0());
        }
      }
    }
  }

  public static void populateJobContractVesselServiceControl(
    ControlExt controlExt,
    JobContractVesselServiceExt jobContractVesselServiceExt
  )
  {
    if((controlExt == null) || (jobContractVesselServiceExt == null)) return;

    Set jControls = jobContractVesselServiceExt.getService().getControls();

    JobContractVesselServiceControl jControl = null;
    String objectName = controlExt.getControl().getControlId().getObjectName();

    Iterator it = jControls.iterator();
    while(it.hasNext())
    {
      JobContractVesselServiceControl tmpControl = (JobContractVesselServiceControl)it.next();
      if(tmpControl.getObjectName().equals(objectName))
      {
        jControl = tmpControl;
        break;
      }
    }
    if(jControl == null)
    {
      for(int i=0; i<jobContractVesselServiceExt.getDeletedControls().size(); i++)
      {
        JobContractVesselServiceControl tmpControl = (JobContractVesselServiceControl)jobContractVesselServiceExt.getDeletedControls().get(i);

        if(tmpControl.getObjectName().equals(objectName))
        {
          jControl = tmpControl;
          jobContractVesselServiceExt.getDeletedControls().remove(i);
          break;
        }
      }
    }

    if(jControl == null)
    {
      jControl = new JobContractVesselServiceControl();
      jControl.setQuestionId(controlExt.getControl().getControlId().getQuestionId());
      jControl.setObjectName(objectName);
      jControl.setJobContractVesselService(jobContractVesselServiceExt.getService());
      jobContractVesselServiceExt.getService().getControls().add(jControl);
    }

    jControl.setInputValue0(controlExt.getDataInput());
  }

  public static void deleteUnusedJobContractVesselServiceControls(
    List controlExts,
    JobContractVesselServiceExt jobContractVesselServiceExt
  )
  {
    if((controlExts == null) || (jobContractVesselServiceExt == null)) return;

    Set oldControls = jobContractVesselServiceExt.getService().getControls();
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
        JobContractVesselServiceControl jControl = (JobContractVesselServiceControl)it.next();
        String objectName = jControl.getObjectName();
        ControlExt controlExt = (ControlExt)newControlMap.get(objectName);
        if(controlExt == null)
        {
          jobContractVesselServiceExt.getDeletedControls().add(jControl);
        }
      }

      oldControls.removeAll(jobContractVesselServiceExt.getDeletedControls());
    }
  }

  public static void deleteUnusedJobContractVesselServiceResults(
    List calculatorResults,
    JobContractVesselServiceExt jobContractVesselServiceExt
  )
  {
    if((jobContractVesselServiceExt == null) || (calculatorResults == null)) return;

    List oldResults = jobContractVesselServiceExt.getResults();

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
        JobContractVesselServiceResult jResult = (JobContractVesselServiceResult)oldResults.get(i);
        String expressionId = jResult.getExpressionId();
        CalculatorResult calculatorResult = (CalculatorResult)newResultMap.get(expressionId);
        if(calculatorResult == null)
        {
          jobContractVesselServiceExt.getDeletedResults().add(jResult);
        }
      }
    }
  }

  public static void populateJobContractVesselServiceResult(
    CalculatorResult calculatorResult,
    JobContractVesselServiceExt jobContractVesselServiceExt,
    JobContract jobContract
  )
  {
    if((calculatorResult == null) || (jobContractVesselServiceExt == null) || (jobContract == null)) return;

    List jResults = jobContractVesselServiceExt.getResults();
    if(jResults == null) return ;
    List deletedResults = jobContractVesselServiceExt.getDeletedResults();

    ContractExpressionExt ceExt = calculatorResult.getContractExpressionExt();
    String expressionId = ceExt.getContractExpression().getContractExpressionId().getExpressionId();

    JobContractVesselServiceResult jResult = null;
    Prebill prebill = null;

    for(int i=0; i<jResults.size(); i++)
    {
      JobContractVesselServiceResult tmpResult = (JobContractVesselServiceResult)jResults.get(i);

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
        JobContractVesselServiceResult tmpResult = (JobContractVesselServiceResult)deletedResults.get(i);

        if(tmpResult.getExpressionId().equals(expressionId))
        {
          jResult = tmpResult;
          deletedResults.remove(i);
          jobContractVesselServiceExt.getAddedResults().add(jResult);
          break;
        }
      }
    }

    if(jResult == null)
    {
      jResult = new JobContractVesselServiceResult();
      jResult.setExpressionId(expressionId);
      jResult.setJobContractVesselService(jobContractVesselServiceExt.getService());

      prebill = new Prebill();
      prebill.setJobContract(jobContract);
      if(jobContract != null){
      	prebill.setBaseCurrencyCd(jobContract.getBaseCurrencyCd());
      	prebill.setCurrencyCd(jobContract.getTransactionCurrencyCd());
      	prebill.setRateMult(jobContract.getRateMult());
      	prebill.setRateDiv(jobContract.getRateDiv());
      }
      prebill.setJobContractVesselServiceResult(jResult);
      jResult.getPrebills().add(prebill);

      jobContractVesselServiceExt.getAddedResults().add(jResult);
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
        prebill.setJobContractVesselServiceResult(jResult);
        jResult.getPrebills().add(prebill);
      }
    }

    jResult.setLocation(ceExt.getContractExpression().getContractExpressionId().getLocation());

    CalculatorUtil.populatePrebillFromCalculatorResult(calculatorResult, prebill, null);
  }
}

