package com.intertek.servicetype;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.intertek.entity.RB;
import com.intertek.entity.Service;
import com.intertek.entity.ServiceType;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.RbService;
import com.intertek.service.ServiceTypeService;
import com.intertek.util.Constants;
import com.intertek.util.ContractUtil;

/**
 * It is the manager class used to load the job type and its related service types.
 *
 **/
public class ServiceTypeManager
{
  /**
   * Load the job service type from job type name and a list of services.
   *
   * @param jobType - the job type name.
   * @param services - the list of services.
   * @return - the job service type for this job type.
   **/
  public static JobServiceType getJobServiceType(String jobType, List services)
  {
    ServiceTypeService stService = (ServiceTypeService)ServiceLocator.getInstance().getBean("serviceTypeService");
    RbService rbService = (RbService)ServiceLocator.getInstance().getBean("rbService");
    JobServiceType jobServiceType = new JobServiceType();
    jobServiceType.setJobType(jobType);

    List serviceTypes = stService.getServiceTypesByJobType(jobType);
    if(serviceTypes.size() == 0) return jobServiceType;

    List rbKeyList = new ArrayList();
    for(int i=0; i<serviceTypes.size(); i++)
    {
      ServiceType serviceType = (ServiceType)serviceTypes.get(i);
      String rbKey = serviceType.getRbKey();
      if((rbKey != null) && !"".equals(rbKey)) rbKeyList.add(rbKey);
    }

    List rbList = rbService.getRbList("NONE", rbKeyList);
    Map rbMap = ContractUtil.mapRBList(rbList);

    List jobLevelServiceTypes = new ArrayList();
    List vesselLevelServiceTypes = new ArrayList();
    List lotLevelServiceTypes = new ArrayList();

    for(int i=0; i<serviceTypes.size(); i++)
    {
      ServiceType serviceType = (ServiceType)serviceTypes.get(i);

      ServiceTypeExt ste = new ServiceTypeExt();
      ste.setServiceType(serviceType);

      RB rb = null;
      String rbKey = serviceType.getRbKey();
      if((rbKey != null) && !"".equals(rbKey))
      {
        rb = (RB)rbMap.get(rbKey);
      }
      if(rb != null) ste.setRbValue(rb.getRbValue());
      else ste.setRbValue(serviceType.getServiceTypeId().getServiceName());

      List selectedServices = getServicesByParentServiceIdAndInputComponentType(
        services,
        serviceType.getParentServiceId(),
        serviceType.getInputComponentType(),
        serviceType.getServiceLevel()
      );

      ste.getServices().addAll(selectedServices);
      if(Constants.JOB.equals(serviceType.getDisplayLevel()))
      {
        jobLevelServiceTypes.add(ste);
      }
      else if(Constants.VESSEL.equals(serviceType.getDisplayLevel()))
      {
        vesselLevelServiceTypes.add(ste);
      }
      else if(Constants.LOT.equals(serviceType.getDisplayLevel()))
      {
        lotLevelServiceTypes.add(ste);
      }
    }

    jobServiceType.getDataMap().put(Constants.JOB, jobLevelServiceTypes);
    jobServiceType.getDataMap().put(Constants.VESSEL, vesselLevelServiceTypes);
    jobServiceType.getDataMap().put(Constants.LOT, lotLevelServiceTypes);

    return jobServiceType;
  }

  /**
   * Get the list of selected services based on the all services, parent service id, input component type, and service level.
   *
   * @param services - the all services input.
   * @param parentServiceId - parent service id
   * @param inputComponentType - input component type.
   * @param serviceLevel - the service level.
   * @return the list of selected services.
   **/
  public static List getServicesByParentServiceIdAndInputComponentType(
    List services,
    String parentServiceId,
    String inputComponentType,
    String serviceLevel
  )
  {
    List results = new ArrayList();

    if((parentServiceId == null) || (services == null))
    {
      return results;
    }

    for(int i=0; i<services.size(); i++)
    {
      Service service = (Service)services.get(i);

      if(parentServiceId.equals(service.getServiceId().getParentServiceId()))
      {
        if((inputComponentType == null) || inputComponentType.equals(service.getInputComponentType()))
        {
          if((serviceLevel == null) || serviceLevel.equals(service.getServiceLevel()))
          {
            results.add(service);
          }
        }
      }
    }

    return results;
  }
}
