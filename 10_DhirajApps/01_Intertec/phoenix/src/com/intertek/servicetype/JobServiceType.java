package com.intertek.servicetype;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.intertek.util.Constants;

/**
 * It represents a job type and its related ServiceTypeExts in JOB level, VESSEL level and LOT level.
 **/
public class JobServiceType
{
  private String jobType;
  private Map dataMap = new HashMap();

  /**
   * Get the job type name.
   *
   * @return - the job type name.
   **/
  public String getJobType()
  {
    return jobType;
  }

  /**
   * Set the job type name.
   *
   * @param jobType - the job type name.
   **/
  public void setJobType(String jobType)
  {
    this.jobType = jobType;
  }

  /**
   * Get the data map used to store the ServiceTypeExts in JOB level, VESSEL level and LOT level of this job type.
   *
   * @return - the job type name.
   **/
  public Map getDataMap()
  {
    return dataMap;
  }

  /**
   * Get the SeviceTypeExt based on service type name. It will search the JOB level first, then VESSEL levvel and then LOT level.
   *
   * @return - the SeviceTypeExt based on service type name.
   **/
  public ServiceTypeExt getServiceTypeExt(String serviceTypeName)
  {
    if(serviceTypeName == null) return null;

    List steList = (List)dataMap.get(Constants.JOB);
    if(steList != null)
    {
      for(int i=0; i<steList.size(); i++)
      {
        ServiceTypeExt ste = (ServiceTypeExt)steList.get(i);
        if(serviceTypeName.equals(ste.getServiceType().getServiceTypeId().getServiceName()))
        {
          return ste;
        }
      }
    }

    steList = (List)dataMap.get(Constants.VESSEL);
    if(steList != null)
    {
      for(int i=0; i<steList.size(); i++)
      {
        ServiceTypeExt ste = (ServiceTypeExt)steList.get(i);
        if(serviceTypeName.equals(ste.getServiceType().getServiceTypeId().getServiceName()))
        {
          return ste;
        }
      }
    }

    steList = (List)dataMap.get(Constants.LOT);
    if(steList != null)
    {
      for(int i=0; i<steList.size(); i++)
      {
        ServiceTypeExt ste = (ServiceTypeExt)steList.get(i);
        if(serviceTypeName.equals(ste.getServiceType().getServiceTypeId().getServiceName()))
        {
          return ste;
        }
      }
    }

    return null;
  }
}
