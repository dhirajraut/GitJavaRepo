package com.intertek.service;

import java.util.List;

import com.intertek.entity.ServiceType;

public interface ServiceTypeService
{
  void addServiceType(ServiceType serviceType);

  List getServiceTypesByJobType(String jobType);
}

