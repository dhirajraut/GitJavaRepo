package com.intertek.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.intertek.entity.JobContract;
import com.intertek.entity.JobEvent;
import com.intertek.entity.JobOrder;
import com.intertek.entity.JobProd;
import com.intertek.entity.JobProdSample;
import com.intertek.entity.JobVessel;
import com.intertek.entity.RequiredField;
import com.intertek.entity.ServiceLocation;
import com.intertek.entity.ShippingAgent;
import com.intertek.entity.TowingCompany;
import com.intertek.util.BeanUtil;

public class RequiredFieldServiceImpl extends BaseService implements RequiredFieldService
{
  private static Log log = LogFactory.getLog(RequiredFieldServiceImpl.class);

  public void saveRequiredField(RequiredField rf)
  {
    if(rf == null) return;

    getDao().save(rf);
  }

  public List getRequiredFieldsByClassName(String className)
  {
    if(className == null) return new ArrayList();

    return getDao().find(
      "from RequiredField rf where rf.requiredFieldId.className = ?",
      new Object[] { className }
    );
  }

  public RequiredField getRequiredFieldsByClassNameAndFieldName(String className, String fieldName)
  {
    if(className == null) return null;

    List rfs = getDao().find(
      "from RequiredField rf where rf.requiredFieldId.className = ? and rf.requiredFieldId.fieldName = ?",
      new Object[] { className, fieldName }
    );

    if(rfs.size() > 0) return (RequiredField)rfs.get(0);

    return null;
  }

  public List checkRequiredFields(Object obj, String className, String type, Set ignoreFieldNames)
  {
    List results = new ArrayList();
    if((obj == null) || (className == null) || (type == null)) return results;

    List rfs = getRequiredFieldsByClassName(className);
    for(int i=0; i<rfs.size(); i++)
    {
      RequiredField rf = (RequiredField)rfs.get(i);
      String fieldName = rf.getRequiredFieldId().getFieldName();
      System.out.println("fieldName = " + fieldName);
      if((ignoreFieldNames != null) && ignoreFieldNames.contains(fieldName)) continue;

      Boolean mask = rf.getMask();
      System.out.println("mask = " + mask);
      if((mask != null) && (mask.booleanValue() == true))
      {
        Object criteriaObj = BeanUtil.getProperty(rf, type);
        System.out.println("criteriaObj = " + criteriaObj);
        if(criteriaObj == null) continue;

        if(criteriaObj instanceof Boolean)
        {
          Boolean criteria = (Boolean)criteriaObj;
          if(criteria)
          {
            Object value = BeanUtil.getProperty(obj, fieldName);
            System.out.println("value = " + value);
            if(value == null)
            {
              results.add(className + "." + fieldName);
            }
          }
        }
      }
    }

    return results;
  }

  public Object checkRequiredField(Object obj, String className, String fieldName, String type)
  {
    if((obj == null) || (className == null) || (fieldName == null) || (type == null)) return null;

    RequiredField rf = getRequiredFieldsByClassNameAndFieldName(className, fieldName);
    System.out.println("================ rf = " + rf);
    if(rf != null)
    {
      Boolean mask = rf.getMask();
      System.out.println("mask = " + mask);
      if((mask != null) && (mask.booleanValue() == true))
      {
        Object criteriaObj = BeanUtil.getProperty(rf, type);
        System.out.println("criteriaObj = " + criteriaObj);
        if(criteriaObj == null) return null;

        if(criteriaObj instanceof Boolean)
        {
          Boolean criteria = (Boolean)criteriaObj;
          if(criteria)
          {
            Object value = BeanUtil.getProperty(obj, fieldName);
            System.out.println("value = " + value);
            if(value == null)
            {
              return className + "." + fieldName;
            }
          }
        }
      }
    }

    return null;
  }

  public List checkRequiredFieldsForJobOrder(String jobNumber)
  {
    List results = new ArrayList();
    if(jobNumber == null) return results;

    JobOrder jobOrder = getJobOrderForRequiredFieldChecking(jobNumber);
    if(jobOrder == null) return results;

    String jobType = jobOrder.getJobType();
    if(jobType == null)
    {
      results.add("JobOrder.jobType");
      return results;
    }

    jobType = jobType.toLowerCase();

    Set ignoreSet = new HashSet();
    ignoreSet.add("otApprovedby");
    ignoreSet.add("otApproved");
    results.addAll(checkRequiredFields(jobOrder, "JobOrder", jobType, ignoreSet));

    Boolean otApproved = jobOrder.getOtApproved();
    System.out.println("============= otApproved = " + otApproved);
    if((otApproved != null) && (otApproved.booleanValue() == true))
    {
      Object otApprovedbyResult = checkRequiredField(jobOrder, "JobOrder", "otApprovedby", jobType);
      System.out.println("============= otApprovedbyResult = " + otApprovedbyResult);
      if(otApprovedbyResult != null) results.add(otApprovedbyResult);
    }

    ServiceLocation serviceLocation = jobOrder.getServiceLocation();
    if(serviceLocation != null)
    {
      results.addAll(checkRequiredFields(serviceLocation, "ServiceLocation", jobType, null));
    }

    ShippingAgent shippingAgent = jobOrder.getShippingAgent();
    if(shippingAgent != null)
    {
      results.addAll(checkRequiredFields(shippingAgent, "ShippingAgent", jobType, null));
    }

    TowingCompany towingCompany = jobOrder.getTowingCompany();
    if(towingCompany != null)
    {
      results.addAll(checkRequiredFields(towingCompany, "TowingCompany", jobType, null));
    }

    Iterator it = jobOrder.getJobContracts().iterator();
    while(it.hasNext())
    {
      JobContract jobContract = (JobContract)it.next();

      results.addAll(checkRequiredFields(jobContract, "JobContract", jobType, null));
    }

    it = jobOrder.getJobVessels().iterator();
    while(it.hasNext())
    {
      JobVessel jobVessel = (JobVessel)it.next();

      results.addAll(checkRequiredFields(jobVessel, "JobVessel", jobType, null));

      Iterator it1 = jobVessel.getJobProds().iterator();
      while(it1.hasNext())
      {
        JobProd jobProd = (JobProd)it1.next();
        results.addAll(checkRequiredFields(jobProd, "JobProd", jobType, null));

        Iterator it2 = jobProd.getJobProdSamples().iterator();
        while(it2.hasNext())
        {
          JobProdSample jobProdSample = (JobProdSample)it2.next();
          results.addAll(checkRequiredFields(jobProdSample, "JobProdSample", jobType, null));
        }

        it2 = jobProd.getJobEvents().iterator();
        while(it2.hasNext())
        {
          JobEvent jobEvent = (JobEvent)it2.next();
          results.addAll(checkRequiredFields(jobEvent, "JobEvent", jobType, null));
        }
      }
    }

    return results;
  }

  public JobOrder getJobOrderForRequiredFieldChecking(String jobNumber)
  {
    if(jobNumber == null) return null;

    List jobOrders = getDao().find(
      "from JobOrder jo left join fetch jo.jobVessels where jo.jobNumber = ?",
      new Object[] { jobNumber }
    );

    if(jobOrders.size() > 0) return (JobOrder)jobOrders.get(0);

    return null;
  }


  public List<RequiredField> getRequiredFields() {
      List<RequiredField> list=getDao().find(
          "from RequiredField r where r.mask=1 and (r.insp=1 or r.mar=1 or r.fst=1 or r.out=1)",
          null
      );
      return list;
  }
}

