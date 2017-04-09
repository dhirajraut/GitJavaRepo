package com.intertek.web.validators;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.intertek.domain.AddJobOrder;
import com.intertek.domain.AddJobContract;
import com.intertek.domain.AddJobContractProd;
import com.intertek.domain.AddJobContractVessel;
import com.intertek.domain.JobContractManualTestExt;
import com.intertek.domain.JobContractProductServiceExt;
import com.intertek.domain.JobContractServiceExt;
import com.intertek.domain.JobContractSlateExt;
import com.intertek.domain.JobContractTestExt;
import com.intertek.domain.JobContractVesselServiceExt;
import com.intertek.entity.JobContractInspectionResult;
import com.intertek.entity.JobContractProductInspectionResult;
import com.intertek.entity.JobContractProductServiceResult;
import com.intertek.entity.JobContractServiceResult;
import com.intertek.entity.JobContractVesselInspectionResult;
import com.intertek.entity.JobContractVesselServiceResult;
import com.intertek.entity.Prebill;
import com.intertek.domain.AddJobContractProductServices;
import com.intertek.domain.AddJobContractServices;
import com.intertek.domain.AddJobContractVesselServices;
import com.intertek.util.StringUtil;

public class AddJobOrderSelectChargeValidator implements Validator
{
  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#supports(java.lang.Class)
   */
  public boolean supports(Class clazz) {
    return AddJobOrder.class.isAssignableFrom(clazz);
  }

  /* (non-Javadoc)
   * @see org.springframework.validation.Validator#validate(java.lang.Object, org.springframework.validation.Errors)
   */
  public void validate(Object obj, Errors errors)
  {
    AddJobOrder addJobOrder = (AddJobOrder)obj;
    if(addJobOrder == null) return;

    AddJobContract[] addJobContracts = addJobOrder.getAddJobContracts();
    if(addJobContracts == null) return;

    for(int ii=0; ii<addJobContracts.length; ii++)
    {
      Double splitPct = addJobContracts[ii].getSplitPct();
      if(splitPct != null)
      {
        if((splitPct.doubleValue() < 0.0) || (splitPct.doubleValue() > 100.0))
        {
          errors.rejectValue("addJobContracts[" + ii + "].splitPct", "invalid", "");
        }
      }

      split(addJobContracts[ii], ii, errors);

      AddJobContractServices addJobContractServices = addJobContracts[ii].getAddJobContractServices();
      if(addJobContractServices != null)
      {
        List addedServiceExtList = addJobContractServices.getAddedJobContractServices();
        for(int j=0; j<addedServiceExtList.size(); j++)
        {
          JobContractServiceExt serviceExt = (JobContractServiceExt)addedServiceExtList.get(j);

          split(serviceExt, ii, j, errors);
        }
      }

      AddJobContractVessel[] addJobContractVessels = addJobContracts[ii].getAddJobContractVessels();
      if(addJobContractVessels != null)
      {
        for(int i=0; i<addJobContractVessels.length; i++)
        {
          AddJobContractVesselServices addJobContractVesselServices = addJobContractVessels[i].getAddJobContractVesselServices();
          if(addJobContractVesselServices != null)
          {
            List addedServiceExtList = addJobContractVesselServices.getAddedJobContractVesselServices();
            for(int j=0; j<addedServiceExtList.size(); j++)
            {
              JobContractVesselServiceExt serviceExt = (JobContractVesselServiceExt)addedServiceExtList.get(j);

              split(serviceExt, ii, i, j, errors);
            }
          }

          AddJobContractProd[] addJobContractProds = addJobContractVessels[i].getAddJobContractProds();
          if(addJobContractProds != null)
          {
            for(int k=0; k<addJobContractProds.length; k++)
            {
              AddJobContractProductServices addJobContractProductServices = addJobContractProds[k].getAddJobContractProductServices();
              if(addJobContractProductServices != null)
              {
                List addedServiceExtList = addJobContractProductServices.getAddedJobContractProductServices();
                for(int l=0; l<addedServiceExtList.size(); l++)
                {
                  JobContractProductServiceExt serviceExt = (JobContractProductServiceExt)addedServiceExtList.get(l);
                  split(serviceExt, ii, i, k, l, errors);
                }
              }

              split(addJobContractProds[k], ii, i, k, errors);
            }
          }

          split(addJobContractVessels[i], ii, i, errors);
        }
      }
    }
  }


  private void split(
    JobContractServiceExt serviceExt,
    int addJobContractIndex,
    int addedJobContractServiceExtIndex,
    Errors errors
  )
  {
    if(serviceExt == null) return;

    boolean[] selects = serviceExt.getSelects();
    List results = serviceExt.getResults();

    if((selects == null) || (results == null)) return;

    if(selects.length != results.size()) return;

    for(int i=0; i<results.size(); i++)
    {
      JobContractServiceResult jResult = (JobContractServiceResult)results.get(i);

      boolean validSplitPct = doSplit(jResult.getPrebills());
      if(!validSplitPct)
      {
        errors.rejectValue("addJobContracts[" + addJobContractIndex + "].addJobContractServices.addedJobContractServices[" + addedJobContractServiceExtIndex + "].results[" + i + "].prebills[0].splitPct", "invalid", "");
      }
    }
  }

  private void split(
    JobContractVesselServiceExt serviceExt,
    int addJobContractIndex,
    int addJobContractVesselIndex,
    int addedJobContractVesselServiceExtIndex,
    Errors errors
  )
  {
    if(serviceExt == null) return;

    boolean[] selects = serviceExt.getSelects();
    List results = serviceExt.getResults();

    if((selects == null) || (results == null)) return;

    if(selects.length != results.size()) return;

    for(int i=0; i<results.size(); i++)
    {
      JobContractVesselServiceResult jResult = (JobContractVesselServiceResult)results.get(i);
      boolean validSplitPct = doSplit(jResult.getPrebills());
      if(!validSplitPct)
      {
        errors.rejectValue("addJobContracts[" + addJobContractIndex + "].addJobContractVessels[" + addJobContractVesselIndex + "].addJobContractVesselServices.addedJobContractVesselServices[" + addedJobContractVesselServiceExtIndex + "].results[" + i + "].prebills[0].splitPct", "invalid", "");
      }
    }
  }

  private void split(
    JobContractProductServiceExt serviceExt,
    int addJobContractIndex,
    int addJobContractVesselIndex,
    int addJobContractProductIndex,
    int addedJobContractProductServiceExtIndex,
    Errors errors
  )
  {
    if(serviceExt == null) return;

    boolean[] selects = serviceExt.getSelects();
    List results = serviceExt.getResults();

    if((selects == null) || (results == null)) return;

    if(selects.length != results.size()) return;

    for(int i=0; i<results.size(); i++)
    {
      JobContractProductServiceResult jResult = (JobContractProductServiceResult)results.get(i);

      boolean validSplitPct = doSplit(jResult.getPrebills());
      if(!validSplitPct)
      {
        errors.rejectValue("addJobContracts[" + addJobContractIndex + "].addJobContractVessels[" + addJobContractVesselIndex + "].addJobContractProds[" + addJobContractProductIndex + "].addJobContractProductServices.addedJobContractProductServices[" + addedJobContractProductServiceExtIndex + "].results[" + i + "].prebills[0].splitPct", "invalid", "");
      }
    }
  }

  private void split(
    AddJobContractProd addJobContractProd,
    int addJobContractIndex,
    int addJobContractVesselIndex,
    int addJobContractProductIndex,
    Errors errors
  )
  {
    if(addJobContractProd == null) return;

    splitTest(addJobContractProd.getJobContractTestExts(), addJobContractIndex, addJobContractVesselIndex, addJobContractProductIndex, errors);
    splitSlate(addJobContractProd.getJobContractSlateExts(), addJobContractIndex, addJobContractVesselIndex, addJobContractProductIndex, errors);
    splitManualTest(addJobContractProd.getJobContractManualTestExts(), addJobContractIndex, addJobContractVesselIndex, addJobContractProductIndex, errors);

    boolean[] selects = addJobContractProd.getSelects();
    List results = addJobContractProd.getResults();

    if((selects == null) || (results == null)) return;

    if(selects.length != results.size()) return;

    for(int i=0; i<results.size(); i++)
    {
      JobContractProductInspectionResult jResult = (JobContractProductInspectionResult)results.get(i);
      boolean validSplitPct = doSplit(jResult.getPrebills());
      if(!validSplitPct)
      {
        errors.rejectValue("addJobContracts[" + addJobContractIndex + "].addJobContractVessels[" + addJobContractVesselIndex + "].addJobContractProds[" + addJobContractProductIndex + "].results[" + i + "].prebills[0].splitPct", "invalid", "");
      }
    }
  }

  private void split(
    AddJobContractVessel addJobContractVessel,
    int addJobContractIndex,
    int addJobContractVesselIndex,
    Errors errors
  )
  {
    if(addJobContractVessel == null) return;

    boolean[] selects = addJobContractVessel.getSelects();
    List results = addJobContractVessel.getResults();

    if((selects == null) || (results == null)) return;

    if(selects.length != results.size()) return;

    for(int i=0; i<results.size(); i++)
    {
      JobContractVesselInspectionResult jResult = (JobContractVesselInspectionResult)results.get(i);
      boolean validSplitPct = doSplit(jResult.getPrebills());
      if(!validSplitPct)
      {
        errors.rejectValue("addJobContracts[" + addJobContractIndex + "].addJobContractVessels[" + addJobContractVesselIndex + "].results[" + i + "].prebills[0].splitPct", "invalid", "");
      }
    }
  }

  private void split(AddJobContract addJobContract, int addJobContractIndex, Errors errors)
  {
    if(addJobContract == null) return;

    boolean[] selects = addJobContract.getSelects();
    List results = addJobContract.getResults();

    if((selects == null) || (results == null)) return;

    if(selects.length != results.size()) return;

    for(int i=0; i<results.size(); i++)
    {
      JobContractInspectionResult jResult = (JobContractInspectionResult)results.get(i);

      boolean validSplitPct = doSplit(jResult.getPrebills());
      if(!validSplitPct)
      {
        errors.rejectValue("addJobContracts[" + addJobContractIndex + "].results[" + i + "].prebills[0].splitPct", "invalid", "");
      }
    }
  }

  private void splitTest(
    List testExts,
    int addJobContractIndex,
    int addJobContractVesselIndex,
    int addJobContractProductIndex,
    Errors errors
  )
  {
    if(testExts == null) return;

    for(int i=0; i<testExts.size(); i++)
    {
      Double mySplitPct = null;
      JobContractTestExt testExt = (JobContractTestExt)testExts.get(i);

      boolean validSplitPct = doSplit(testExt.getTest().getPrebills());
      if(!validSplitPct)
      {
        errors.rejectValue("addJobContracts[" + addJobContractIndex + "].addJobContractVessels[" + addJobContractVesselIndex + "].addJobContractProds[" + addJobContractProductIndex + "].jobContractTestExts[" + i + "].test.prebills[0].splitPct", "invalid", "");
      }
    }
  }

  private void splitSlate(
    List slateExts,
    int addJobContractIndex,
    int addJobContractVesselIndex,
    int addJobContractProductIndex,
    Errors errors
  )
  {
    if(slateExts == null) return;

    for(int i=0; i<slateExts.size(); i++)
    {
      Double mySplitPct = null;
      JobContractSlateExt slateExt = (JobContractSlateExt)slateExts.get(i);

      boolean validSplitPct = doSplit(slateExt.getSlate().getPrebills());
      if(!validSplitPct)
      {
        errors.rejectValue("addJobContracts[" + addJobContractIndex + "].addJobContractVessels[" + addJobContractVesselIndex + "].addJobContractProds[" + addJobContractProductIndex + "].jobContractSlateExts[" + i + "].slate.prebills[0].splitPct", "invalid", "");
      }
    }
  }

  private void splitManualTest(
    List manualTestExts,
    int addJobContractIndex,
    int addJobContractVesselIndex,
    int addJobContractProductIndex,
    Errors errors
  )
  {
    if(manualTestExts == null) return;

    for(int i=0; i<manualTestExts.size(); i++)
    {
      Double mySplitPct = null;
      JobContractManualTestExt manualTestExt = (JobContractManualTestExt)manualTestExts.get(i);

      boolean validSplitPct = doSplit(manualTestExt.getManualTest().getPrebills());
      if(!validSplitPct)
      {
        errors.rejectValue("addJobContracts[" + addJobContractIndex + "].addJobContractVessels[" + addJobContractVesselIndex + "].addJobContractProds[" + addJobContractProductIndex + "].jobContractManualTestExts[" + i + "].manualTest.prebills[0].splitPct", "invalid", "");
      }
    }
  }

  private boolean doSplit(Set prebills)
  {
    if(prebills == null) return true;

    Iterator it = prebills.iterator();
    while(it.hasNext())
    {
      Prebill prebill = (Prebill)it.next();

      Double splitPct = prebill.getSplitPct();
      if(splitPct != null)
      {
        if((splitPct.doubleValue() < 0.0) || (splitPct.doubleValue() > 100.0))
        {
          return false;
        }
      }
    }

    return true;
  }
}
