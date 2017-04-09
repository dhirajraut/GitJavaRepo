package com.intertek.service;

import java.util.List;

import com.intertek.entity.ServiceType;

public class ServiceTypeServiceImpl extends BaseService implements ServiceTypeService
{
  public void addServiceType(ServiceType serviceType)
  {
    getDao().save(serviceType);
  }

  public List getServiceTypesByJobType(String jobType)
  {
    return getDao().find(
      "from ServiceType st where st.serviceTypeId.jobType = ?",
      new Object[] { jobType }
    );
  }
}

