package com.intertek.servicetype;

import java.util.ArrayList;
import java.util.List;

import com.intertek.entity.Service;
import com.intertek.entity.ServiceType;

/**
 * Represent a service type (ServiceType entity) and its related services (Service entity).
 *
 **/

public class ServiceTypeExt
{
  private ServiceType serviceType;
  private List services = new ArrayList();
  private String rbValue;

  /**
   * Get the wrapped service type entity.
   *
   * @return - the wrapped service type entity.
   **/
  public ServiceType getServiceType()
  {
    return serviceType;
  }

  /**
   * Set the wrapped service type entity.
   *
   * @param serviceType - the wrapped service type entity.
   **/
  public void setServiceType(ServiceType serviceType)
  {
    this.serviceType = serviceType;
  }

  /**
   * Get the list of servcie entities.
   *
   * @return - the list of servcie entities.
   **/
  public List getServices()
  {
    return services;
  }

  /**
   * Set the list of servcie entities.
   *
   * @param services - the list of servcie entities.
   **/
  public void setServices(List services)
  {
    this.services = services;
  }

  /**
   * Get the service entity based on service name.
   *
   * @param serviceName - the service name used to get the service entity.
   **/
  public Service getSelectedService(
    String serviceName
  )
  {
    if((services == null) || (services.size() == 0)) return null;

    if(serviceName == null) return (Service)services.get(0);

    for(int i=0; i<services.size(); i++)
    {
      Service service = (Service)services.get(i);
      if(serviceName.equals(service.getServiceId().getServiceName())) return service;
    }

    return null;
  }

  public void setRbValue(String rbValue)
  {
    this.rbValue = rbValue;
  }

  public String getRbValue()
  {
    return rbValue;
  }
}
