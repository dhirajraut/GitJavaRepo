package com.intertek.util;

import java.io.*;
import java.util.*;

import com.intertek.locator.*;
import com.intertek.service.*;
import com.intertek.util.*;
import com.intertek.pagination.*;
import com.intertek.domain.*;
import com.intertek.entity.*;
import com.intertek.meta.*;
import com.intertek.meta.dropdown.*;
import com.intertek.servicetype.*;
import com.intertek.calculator.*;
import com.intertek.exception.*;

public class ContractServiceUtil
{
  public static List getNextServiceRateExtListByCurrentServiceRate(
    List serviceRateExtList,
    ServiceRate serviceRate,
    int startIndex
  )
  {
    List results = new ArrayList();
    if((serviceRateExtList == null) || (serviceRate == null)) return results;

    for(int i=startIndex; i<serviceRateExtList.size(); i++)
    {
      ServiceRateExt serviceRateExt = (ServiceRateExt)serviceRateExtList.get(i);
      ServiceRate tmpServiceRate = serviceRateExt.getServiceRate();

      if(tmpServiceRate == null) continue;

      if(serviceRate.getServiceRateId().getLocation().equals(tmpServiceRate.getServiceRateId().getLocation())
        && serviceRate.getServiceRateId().getExpressionId().equals(tmpServiceRate.getServiceRateId().getExpressionId())
        && serviceRate.getServiceRateId().getGroupId().equals(tmpServiceRate.getServiceRateId().getGroupId())
        && serviceRate.getServiceRateId().getVesselType().equals(tmpServiceRate.getServiceRateId().getVesselType())
        && serviceRate.getServiceRateId().getWithInspection().equals(tmpServiceRate.getServiceRateId().getWithInspection())
        && serviceRate.getServiceRateId().getFloatData2().equals(tmpServiceRate.getServiceRateId().getFloatData2())
      )
      {
        results.add(serviceRateExt);
      }
    }

    return results;
  }

  public static HighLevelService getHighLevelServiceById(
    Collection highLevelServiceCol,
    String highLevelServiceId
  )
  {
    if(highLevelServiceId == null) return null;

    if(highLevelServiceCol == null) return null;

    Iterator it = highLevelServiceCol.iterator();
    while(it.hasNext())
    {
      HighLevelService highLevelService = (HighLevelService)it.next();
      if(highLevelServiceId.equals(highLevelService.getHighLevelServiceId())) return highLevelService;
    }

    return null;
  }

  public static HighLevelServiceExt getHighLevelServiceExt(
    HighLevelService highLevelService,
    Contract contract
  )
  {
    if((highLevelService == null) || (contract == null)) return null;

    HighLevelServiceExt highLevelServiceExt = new HighLevelServiceExt();

    highLevelServiceExt.setHighLevelService(highLevelService);

    ServiceRateService serviceRateService = (ServiceRateService)ServiceLocator.getInstance().getBean("serviceRateService");
    RbService rbService = (RbService)ServiceLocator.getInstance().getBean("rbService");

    List serviceExtList = new ArrayList();
    try
    {
      List serviceList = serviceRateService.getServiceList(highLevelService, contract);

      List serviceNameList = getSerivceNameListFromServiceList(serviceList);
      List parentServiceIdList = getParentServiceIdListFromServiceList(serviceList);

      List serviceListForContract = serviceRateService.getServiceListByContractCodeAndServiceNameListAndParentServiceIdListAndEffectiveDate(
        contract.getContractCode(),
        serviceNameList,
        parentServiceIdList,
        contract.getMasterListDate()
      );

      List allContractExpressionList = serviceRateService.getContractExpressionListByServiceNameListAndContract(
        serviceNameList,
        contract
      );

      List expressionIdList = getExpressionIdListFromContractExpressionList(allContractExpressionList);
      List expressionIdServiceNameControlRbKeyList = serviceRateService.getExpressionIdServiceNameControlRbKeyListByServiceNameListAndExpressionIdListAndContract(
        serviceNameList,
        expressionIdList,
        contract
      );
      List rbKeyList = getRbKeyListFromExpressionIdServiceNameControlRbKeyList(expressionIdServiceNameControlRbKeyList);
      List rbList = rbService.getRbList(contract.getContractCode(), new Date(), rbKeyList);
      Map rbMap = ContractUtil.mapRBList(rbList);

      List ceListForContract = serviceRateService.getContractExpressionListByContractCodeAndSerivceNameListAndExpressionIdList(
        contract.getContractCode(),
        serviceNameList,
        expressionIdList,
        new Date()
      );

      for(int i=0; i<serviceList.size(); i++)
      {
        Service service = (Service)serviceList.get(i);
        ServiceExt serviceExt = new ServiceExt();
        serviceExt.setService(service);

        RB rb = RbUtil.getRb(contract.getContractCode(), service.getRbKey());
        if(rb != null)
        {
          serviceExt.setDescription(rb.getRbValue());
        }
        else
        {
          serviceExt.setDescription(service.getRbKey());
        }

        Service serviceForContract = getServiceByServiceNameAndParentServiceIdFromServiceList(
          service.getServiceId().getServiceName(),
          service.getServiceId().getParentServiceId(),
          serviceListForContract
        );

        serviceExt.setServiceForContract(serviceForContract);
        if(serviceForContract != null)
        {
          boolean hide = "HIDE".equals(serviceForContract.getVisibility());
          serviceExt.setHide(hide);
        }

        List ceExtList = new ArrayList();

        List contractExpressionList = getCeListByServiceNameFromContractExpressionList(
          service.getServiceId().getServiceName(),
          allContractExpressionList
        );

        for(int j=0; j<contractExpressionList.size(); j++)
        {
          ContractExpression ce = (ContractExpression)contractExpressionList.get(j);

          ContractExpressionExt ceExt = new ContractExpressionExt();
          ceExt.setContractExpression(ce);

          List controlRbKeyList = getControlRbKeyListByServiceNameAndExpressionIdFromExpressionIdServiceNameControlRbKeyList(
            service.getServiceId().getServiceName(),
            ce.getContractExpressionId().getExpressionId(),
            expressionIdServiceNameControlRbKeyList
          );

          StringBuffer controlQuestions = new StringBuffer();
          for(int k=0; k<controlRbKeyList.size(); k++)
          {
            String rbKey = (String)controlRbKeyList.get(k);

            RB controlRb = (RB)rbMap.get(rbKey);
            if(controlRb == null)
            {
              controlRb = rbService.getRb(rbKey);
            }
            else
            {
              ceExt.setItemChangedFlag(true);
              ceExt.setChangedItems("TEXTCHANGE");
            }

            if(k > 0) controlQuestions.append(";");
            if(controlRb == null) controlQuestions.append(rbKey);
            else controlQuestions.append(controlRb.getRbValue());
          }

          ceExt.setControlQuestions(controlQuestions.toString());

          if(!ceExt.isItemChangedFlag())
          {
            checkContractExpressionChanged(
              ceExt,
              contract.getContractCode(),
              service.getServiceId().getServiceName(),
              ce.getContractExpressionId().getExpressionId(),
              ceListForContract
            );
          }

          ceExtList.add(ceExt);
        }
        serviceExt.setContractExpressionExtList(ceExtList);

        serviceExtList.add(serviceExt);
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

    highLevelServiceExt.setServiceExtList(serviceExtList);

    return highLevelServiceExt;
  }

  public static List getCeListByServiceNameFromContractExpressionList(
    String serviceName,
    List ceList
  )
  {
    List results = new ArrayList();
    if((serviceName == null) || (ceList == null)) return results;

    for(int i=0; i<ceList.size(); i++)
    {
      ContractExpression ce = (ContractExpression)ceList.get(i);

      if(serviceName.equals(ce.getContractExpressionId().getServiceName()))
      {
        results.add(ce);
      }
    }

    return results;
  }

  public static List getParentServiceIdListFromServiceList(List serviceList)
  {
    List results = new ArrayList();
    if(serviceList == null) return results;

    for(int i=0; i<serviceList.size(); i++)
    {
      Service service = (Service)serviceList.get(i);
      results.add(service.getServiceId().getParentServiceId());
    }

    return results;
  }

  public static List getSerivceNameListFromServiceList(List serviceList)
  {
    List results = new ArrayList();
    if(serviceList == null) return results;

    for(int i=0; i<serviceList.size(); i++)
    {
      Service service = (Service)serviceList.get(i);
      results.add(service.getServiceId().getServiceName());
    }

    return results;
  }

  public static Service getServiceByServiceNameAndParentServiceIdFromServiceList(
    String serviceName,
    String parentServiceId,
    List serviceList
  )
  {
    if((serviceName == null) || (parentServiceId == null)|| (serviceList == null)) return null;

    for(int i=0; i<serviceList.size(); i++)
    {
      Service service = (Service)serviceList.get(i);
      if(serviceName.equals(service.getServiceId().getServiceName())
        && parentServiceId.equals(service.getServiceId().getParentServiceId())
      )
      {
        return service;
      }
    }

    return null;
  }

  public static List getControlRbKeyListByServiceNameAndExpressionIdFromExpressionIdServiceNameControlRbKeyList(
    String serviceName,
    String expressionId,
    List expressionIdServiceNameControlRbKeyList
  )
  {
    List results = new ArrayList();

    if((serviceName == null) || (expressionId == null) || (expressionIdServiceNameControlRbKeyList == null)) return results;

    for(int i=0; i<expressionIdServiceNameControlRbKeyList.size(); i++)
    {
      Object[] objects = (Object[])expressionIdServiceNameControlRbKeyList.get(i);
      if((objects != null) && (objects.length > 2))
      {
        String tmpExpressionId = (String)objects[0];
        String tmpServiceName = (String)objects[1];
        if(expressionId.equals(tmpExpressionId)
          && serviceName.equals(tmpServiceName)
        )
        {
          if(objects[2] != null) results.add(objects[2]);
        }
      }
    }

    return results;
  }

  public static List getRbKeyListFromExpressionIdServiceNameControlRbKeyList(
    List expressionIdServiceNameControlRbKeyList
  )
  {
    List results = new ArrayList();

    if(expressionIdServiceNameControlRbKeyList == null) return results;

    for(int i=0; i<expressionIdServiceNameControlRbKeyList.size(); i++)
    {
      Object[] objects = (Object[])expressionIdServiceNameControlRbKeyList.get(i);
      if((objects != null) && (objects.length > 2))
      {
        if(objects[2] != null) results.add(objects[2]);
      }
    }

    return results;
  }

  public static List getExpressionIdListFromContractExpressionList(List contractExpressionList)
  {
    List results = new ArrayList();

    if(contractExpressionList == null) return results;

    for(int j=0; j<contractExpressionList.size(); j++)
    {
      ContractExpression ce = (ContractExpression)contractExpressionList.get(j);
      results.add(ce.getContractExpressionId().getExpressionId());
    }

    return results;
  }

  public static ContractExpression getContractExpressionByServicenNameExpressionIdFromContractExpressionList(
    String serviceName,
    String expressionId,
    List ceList
  )
  {
    if((serviceName == null) || (expressionId == null) || (ceList == null)) return null;

    for(int i=0; i<ceList.size(); i++)
    {
      ContractExpression ce = (ContractExpression)ceList.get(i);
      if(expressionId.equals(ce.getContractExpressionId().getExpressionId())
        && serviceName.equals(ce.getContractExpressionId().getServiceName())
      )
      {
        return ce;
      }
    }

    return null;
  }

  public static void checkContractExpressionChanged(
    ContractExpressionExt ceExt,
    String contractCode,
    String serviceName,
    String expressionId,
    List ceListForContract
  )
  {
    if((ceExt == null) || (contractCode == null) || (serviceName == null) || (expressionId == null) || (ceListForContract == null)) return;

    ContractExpression ce = getContractExpressionByServicenNameExpressionIdFromContractExpressionList(
      serviceName,
      expressionId,
      ceListForContract
    );

    if(ce == null) return;

    StringBuffer sb = new StringBuffer();
    if(ceExt.getChangedItems() != null) sb.append(ceExt.getChangedItems());

    if("HIDE".equals(ce.getVisibility()))
    {
      ceExt.setItemChangedFlag(true);
    }
    else
    {
      if("CONTR".equals(ce.getContractComponent()))
      {
        ceExt.setItemChangedFlag(true);
        if(ceExt.getChangedItems() != null) sb.append(" | ");
        sb.append("FLATRATE");
      }
      else if("PB".equals(ce.getContractComponent()))
      {
        ceExt.setItemChangedFlag(true);
        if(ceExt.getChangedItems() != null) sb.append(" | ");
        sb.append("PBDISCOUNT");
      }
    }
  }

  public static ServiceVersionExt getServiceVersionExtByIndex(
    ServiceLevel serviceLevel,
    int index
  )
  {
    if(serviceLevel == null) return null;
    List serviceVersionExtList = serviceLevel.getServiceVersionExtList();

    if(serviceVersionExtList == null) return null;

    if((index >= 0) && (index < serviceVersionExtList.size()))
    {
      return (ServiceVersionExt)serviceVersionExtList.get(index);
    }

    return null;
  }

  public static void consolidateSharedLevels(List serviceLevelList)
  {
    if(serviceLevelList == null) return;

    List sharedList = new ArrayList();

    for(int i=0; i<serviceLevelList.size(); i++)
    {
      ServiceLevel serviceLevel = (ServiceLevel)serviceLevelList.get(i);

      if(sharedList.contains(serviceLevel)) continue;

      List sharedLevelNameList = new ArrayList();
      for(int j=i+1; j<serviceLevelList.size(); j++)
      {
        ServiceLevel tmpServiceLevel = (ServiceLevel)serviceLevelList.get(j);
        if(serviceLevel.getServiceName().equals(tmpServiceLevel.getServiceName())
          && serviceLevel.getExpressionId().equals(tmpServiceLevel.getExpressionId())
        )
        {
          sharedList.add(tmpServiceLevel);
          if(!sharedLevelNameList.contains(tmpServiceLevel.getServiceLevel()))
          {
            sharedLevelNameList.add(tmpServiceLevel.getServiceLevel());
          }
        }
      }

      serviceLevel.setSharedLevelNameList(sharedLevelNameList);
    }

    serviceLevelList.removeAll(sharedList);
  }

  public static ContractExpressionExt getAllZoneContractExpressionExt(
    List ceExtList
  )
  {
    if(ceExtList == null) return null;

    for(int i=0; i<ceExtList.size(); i++)
    {
      ContractExpressionExt ceExt = (ContractExpressionExt)ceExtList.get(i);

      if("*".equals(ceExt.getContractExpression().getContractExpressionId().getLocation()))
      {
        return ceExt;
      }
    }

    return null;
  }

  public static void adjustBeginEndDatesIfNecessary(ServiceRates serviceRates)
  {
    if(serviceRates == null) return;

    ServiceLevel serviceLevel = serviceRates.getServiceLevel();
    if(serviceLevel == null) return;

    int versionIndex = serviceLevel.getActiveServiceVersionIndex();
    List serviceVersionExtList = serviceLevel.getServiceVersionExtList();

    ServiceVersionExt serviceVersionExt = null;
    if((versionIndex >= 0) && (versionIndex < serviceVersionExtList.size()))
    {
      serviceVersionExt = (ServiceVersionExt)serviceVersionExtList.get(versionIndex);
    }

    if(serviceVersionExt == null) return;

    ServiceVersionExt serviceVersionExt1 = serviceVersionExt;
    for(int i=versionIndex-1; i>=0; i--)
    {
      ServiceVersionExt serviceVersionExt2 = (ServiceVersionExt)serviceVersionExtList.get(i);
      adjustServiceVersionBeginDateAndEndDateIfNecessary(serviceVersionExt1, serviceVersionExt2, false);

      serviceVersionExt1 = serviceVersionExt2;
    }

    serviceVersionExt1 = serviceVersionExt;
    for(int i=versionIndex+1; i<serviceVersionExtList.size(); i++)
    {
      ServiceVersionExt serviceVersionExt2 = (ServiceVersionExt)serviceVersionExtList.get(i);
      adjustServiceVersionBeginDateAndEndDateIfNecessary(serviceVersionExt1, serviceVersionExt2, true);

      serviceVersionExt1 = serviceVersionExt2;
    }
  }

  public static void adjustServiceVersionBeginDateAndEndDateIfNecessary(
    ServiceVersionExt serviceVersionExt1,
    ServiceVersionExt serviceVersionExt2,
    boolean ascend
  )
  {
    if((serviceVersionExt1 == null) || (serviceVersionExt2 == null)) return;

    Date[] datePair1 = new Date[2];
    datePair1[0] = serviceVersionExt1.getServiceVersion().getServiceVersionId().getBeginDate();
    datePair1[1] = serviceVersionExt1.getServiceVersion().getEndDate();

    Date[] datePair2 = new Date[2];
    datePair2[0] = serviceVersionExt2.getServiceVersion().getServiceVersionId().getBeginDate();
    datePair2[1] = serviceVersionExt2.getServiceVersion().getEndDate();

    if(ascend) DateUtil.adjustBeginDateAndEndDateAscend(datePair1, datePair2);
    else DateUtil.adjustBeginDateAndEndDateDescend(datePair1, datePair2);

    serviceVersionExt2.getServiceVersion().getServiceVersionId().setBeginDate(datePair2[0]);
    serviceVersionExt2.getServiceVersion().setEndDate(datePair2[1]);
  }

  public static void populateServiceRates(ServiceRates serviceRates)
  {
    if(serviceRates == null) return;

    ServiceLevel serviceLevel = serviceRates.getServiceLevel();
    if(serviceLevel == null) return;

    ServiceExt serviceExt = serviceLevel.getServiceExt();
    ContractExpressionExt ceExt = serviceLevel.getContractExpressionExt();
    EditServiceRate editServiceRate = serviceRates.getEditServiceRate();

    if((serviceExt == null) || (ceExt == null) || (editServiceRate == null)) return;

    ServiceRateService serviceRateService = (ServiceRateService)ServiceLocator.getInstance().getBean("serviceRateService");

    String ceContractCode = null;
    if(Constants.MASTER.equals(ceExt.getContractExpression().getContractExpressionId().getContractId()))
    {
      ceContractCode = Constants.MASTER;
    }
    else
    {
      ceContractCode = editServiceRate.getContract().getWorkingPB();
    }

    List serviceRateExpressionIdList = serviceRateService.getServiceRateExpressionIdListByContractIdAndSerivceNameAndExpressionId(
      ceContractCode,
      serviceExt.getService().getServiceId().getServiceName(),
      ceExt.getContractExpression().getContractExpressionId().getExpressionId()
    );

    serviceRates.setServiceRateExpressionIdList(serviceRateExpressionIdList);

    List serviceLevelList = serviceRateService.getServiceLevelListForServiceRateExpressionIds(
      serviceRateExpressionIdList,
      editServiceRate.getContract()
    );
    //consolidateSharedLevels(serviceLevelList);

    serviceRates.setServiceLevelList(serviceLevelList);

    List serviceVersionList = serviceRateService.getServiceVersionListByContractCodeAndServiceNameAndExpressionId(
      editServiceRate.getContract().getContractCode(),
      serviceExt.getService().getServiceId().getServiceName(),
      ceExt.getContractExpression().getContractExpressionId().getExpressionId()
    );
    List serviceVersionExtList = createServiceVersionExtList(serviceVersionList, serviceLevel);
    serviceLevel.setServiceVersionExtList(serviceVersionExtList);

    if(serviceLevelList != null)
    {
      List emptyServiceLevelList = new ArrayList();
      for(int i=0; i<serviceLevelList.size(); i++)
      {
        ServiceLevel tmpServiceLevel = (ServiceLevel)serviceLevelList.get(i);
        if(tmpServiceLevel.getServiceName().equals(serviceLevel.getServiceName())
          && tmpServiceLevel.getExpressionId().equals(serviceLevel.getExpressionId())
          && tmpServiceLevel.getServiceLevel().equals(serviceLevel.getServiceLevel()))
        {
          serviceLevelList.set(i, serviceLevel);
          serviceLevel.setSharedLevelNameList(tmpServiceLevel.getSharedLevelNameList());
          tmpServiceLevel = serviceLevel;
        }
        else
        {
          ServiceExt tmpServiceExt = ContractServiceUtil.getServiceExtByServiceNameAndLevel(
            tmpServiceLevel.getServiceName(),
            tmpServiceLevel.getServiceLevel(),
            editServiceRate.getHighLevelServiceExt().getServiceExtList()
          );
          tmpServiceLevel.setServiceExt(tmpServiceExt);

          if(tmpServiceExt != null)
          {
            ContractExpressionExt tmpCeExt = ContractServiceUtil.getContractExpressionExtByContractIdAndExpressionId(
             tmpServiceLevel.getCfgContractId(),
             tmpServiceLevel.getExpressionId(),
             tmpServiceExt.getContractExpressionExtList()
            );
            tmpServiceLevel.setContractExpressionExt(tmpCeExt);

            List tmpServiceVersionList = serviceRateService.getServiceVersionListByContractCodeAndServiceNameAndExpressionId(
              editServiceRate.getContract().getContractCode(),
              tmpServiceExt.getService().getServiceId().getServiceName(),
              tmpCeExt.getContractExpression().getContractExpressionId().getExpressionId()
            );
            List tmpServiceVersionExtList = createServiceVersionExtList(tmpServiceVersionList, tmpServiceLevel);
            tmpServiceLevel.setServiceVersionExtList(tmpServiceVersionExtList);
          }
          else
          {
            emptyServiceLevelList.add(tmpServiceLevel);
          }
        }

        List tmpServiceVersionExtList = tmpServiceLevel.getServiceVersionExtList();
        if(tmpServiceVersionExtList == null) continue;

        for(int j=0; j<tmpServiceVersionExtList.size(); j++)
        {
          ServiceVersionExt serviceVersionExt = (ServiceVersionExt)tmpServiceVersionExtList.get(j);
          getToggleLevelFromServiceVersion(serviceVersionExt);
          loadContractExpressionForContract(serviceVersionExt, editServiceRate.getContract());

          List contractControlExtList = getContractControlExtListByServiceNameAndExpressionIdAndContractAndBeginDate(
            tmpServiceLevel.getServiceName(),
            tmpServiceLevel.getExpressionId(),
            editServiceRate.getContract(),
            serviceVersionExt.getServiceVersion().getServiceVersionId().getBeginDate()
          );
          serviceVersionExt.setContractControlExtList(contractControlExtList);
          for(int k=0; k<contractControlExtList.size(); k++)
          {
            ControlExt controlExt = (ControlExt)contractControlExtList.get(k);

            if(Constants.HIDE.equals(controlExt.getControl().getVisibility())) serviceVersionExt.setHide(true);
          }

          if(Constants.enableVesselSet.contains(tmpServiceLevel.getServiceLevel()))
          {
            serviceVersionExt.setEnableVessel(true);
            setVesselCheckedForServiceVersionExt(serviceVersionExt);
          }
          if(Constants.enableProductGroupSet.contains(tmpServiceLevel.getServiceLevel()))
          {
            serviceVersionExt.setEnableProductGroup(true);
            setProductGroupCheckedForServiceVersionExt(serviceVersionExt);
          }

          Service serviceForContract = serviceRateService.getServiceByContractCodeAndServiceNameAndParentServiceIdAndBeginDate(
            editServiceRate.getContract().getContractCode(),
            tmpServiceLevel.getServiceExt().getService().getServiceId().getServiceName(),
            tmpServiceLevel.getServiceExt().getService().getServiceId().getParentServiceId(),
            serviceVersionExt.getServiceVersion().getServiceVersionId().getBeginDate()
          );
          if(serviceForContract != null)
          {
            ServiceExt serviceExtForContract = new ServiceExt();
            serviceExtForContract.setServiceForContract(serviceForContract);

            Service newServiceForContract = new Service();
            EntityUtil.copyService(newServiceForContract, serviceForContract);

            serviceExtForContract.setService(serviceForContract);

            serviceVersionExt.setServiceExtForContract(serviceExtForContract);
          }

          if(tmpServiceLevel == serviceLevel)
          {
            String rateApplied = serviceVersionExt.getRateApplied();
            //if((rateApplied == null) || Constants.U.equals(rateApplied)) serviceVersionExt.setRateApplied(Constants.T);
            serviceVersionExt.setApplyToThisLevel(Constants.Yes);
          }
        }
      }

      serviceLevelList.removeAll(emptyServiceLevelList);
    }

    List controlRbKeyList = serviceRateService.getControlRbKeyListByServiceNameAndExpressionIdAndContract(
      serviceExt.getService().getServiceId().getServiceName(),
      ceExt.getContractExpression().getContractExpressionId().getExpressionId(),
      editServiceRate.getContract()
    );

    List questionRbExtList = new ArrayList();
    for(int i=0; i<controlRbKeyList.size(); i++)
    {
      String rbKey = (String)controlRbKeyList.get(i);

      RB controlRb = RbUtil.getRb(editServiceRate.getContract().getContractCode(), rbKey);

      RB notesRb = RbUtil.getRb(editServiceRate.getContract().getContractCode(), "Notes." + rbKey);

      if(controlRb != null)
      {
        RBExt rbExt = new RBExt();
        rbExt.setRb(controlRb);
        rbExt.setNotesRb(notesRb);

        questionRbExtList.add(rbExt);
      }
    }

    serviceLevel.setQuestionRbExtList(questionRbExtList);

    List priceBookServiceRateList = serviceRateService.getPriceBookServiceRateListForServiceRateExpressionIds(
      serviceRateExpressionIdList,
      editServiceRate.getContract()
    );
    serviceRates.setPriceBookServiceRateList(priceBookServiceRateList);

    List contractSpecificServiceRateList = serviceRateService.getContractSpecificServiceRateListForServiceRateExpressionIds(
      serviceRateExpressionIdList,
      editServiceRate.getContract()
    );
    serviceRates.setContractSpecificServiceRateList(contractSpecificServiceRateList);

    processMasterListItems(serviceLevelList, priceBookServiceRateList, serviceRateExpressionIdList);

    createServiceVersion(serviceRates, false);

    serviceLevel.setActiveServiceVersionIndex(serviceLevel.getServiceVersionExtList().size() - 1);
    setEnableApplyAllFlag(serviceRates);

    splitContractSpecificServiceRateToServiceLevel(serviceRates);

    Date earliestContractBeginDate = getEarliestBeginDateOfContract(editServiceRate.getContract().getContractCode());
    serviceRates.setEarliestContractBeginDate(earliestContractBeginDate);
  }

  public static List createServiceVersionExtList(List serviceVersionList, ServiceLevel serviceLevel)
  {
    List results = new ArrayList();
    if((serviceVersionList == null) || (serviceLevel == null)) return results;

    for(int i=0; i<serviceVersionList.size(); i++)
    {
      ServiceVersion serviceVersion = (ServiceVersion)serviceVersionList.get(i);
      ServiceVersionExt serviceVersionExt = new ServiceVersionExt();
      serviceVersionExt.setServiceLevel(serviceLevel);
      serviceVersionExt.setServiceVersion(serviceVersion);

      results.add(serviceVersionExt);
    }

    return results;
  }

  public static ServiceExt getServiceExtByServiceNameAndLevel(
    String serviceName,
    String level,
    List serviceExtList
  )
  {
    if((serviceExtList == null) || (serviceName == null) || (level == null)) return null;

    for(int i=0; i<serviceExtList.size(); i++)
    {
      ServiceExt serviceExt = (ServiceExt)serviceExtList.get(i);

      if(serviceName.equals(serviceExt.getService().getServiceId().getServiceName())
        && level.equals(serviceExt.getService().getServiceLevel())
      )
      {
        return serviceExt;
      }
    }

    return null;
  }

  public static ContractExpressionExt getContractExpressionExtByContractIdAndExpressionId(
    String contractId,
    String expressionId,
    List ceExtList
  )
  {
    if((ceExtList == null) || (expressionId == null) || (contractId == null)) return null;

    for(int i=0; i<ceExtList.size(); i++)
    {
      ContractExpressionExt ceExt = (ContractExpressionExt)ceExtList.get(i);

      if(contractId.equals(ceExt.getContractExpression().getContractExpressionId().getContractId())
        && expressionId.equals(ceExt.getContractExpression().getContractExpressionId().getExpressionId())
      )
      {
        return ceExt;
      }
    }

    return null;
  }

  public static void processMasterListItems(List serviceLevelList, List priceBookServiceRateList, List serviceRateExpressionIdList)
  {
    if((serviceLevelList == null) || (priceBookServiceRateList == null) || (serviceRateExpressionIdList == null)) return;

    for(int i=0; i<serviceLevelList.size(); i++)
    {
      ServiceLevel serviceLevel = (ServiceLevel)serviceLevelList.get(i);

      if(Constants.MASTER.equals(serviceLevel.getCfgContractId()))
      {
        boolean find = false;
        for(int j=0; j<priceBookServiceRateList.size(); j++)
        {
          ServiceRate serviceRate = (ServiceRate)priceBookServiceRateList.get(j);

          if(serviceLevel.getServiceLevel().equals(serviceRate.getServiceRateId().getServiceLevel()))
          {
            find = true;
            break;
          }
        }

        if(!find)
        {
          for(int j=0; j<serviceRateExpressionIdList.size(); j++)
          {
            ServiceRate serviceRate = new ServiceRate();
            ServiceRateId serviceRateId = new ServiceRateId();
            serviceRate.setServiceRateId(serviceRateId);

            serviceRateId.setExpressionId((String)serviceRateExpressionIdList.get(j));
            serviceRateId.setServiceLevel(serviceLevel.getServiceLevel());
            serviceRateId.setLocation("*");
            serviceRateId.setWithInspection("*");
            serviceRateId.setGroupId("*");
            serviceRateId.setVesselType("*");
            serviceRateId.setIntData2(0);
            serviceRateId.setFloatData2(0.0);
            serviceRate.setIntData3((long)999999);
            serviceRate.setFloatData3(999999.0);

            priceBookServiceRateList.add(serviceRate);
          }
        }
      }
    }
  }

  public static String switchPbOrContract(
    ServiceRates serviceRates,
    ServiceLevel serviceLevel,
    int versionIndex
  )
  {
    if((serviceRates == null) || (serviceLevel == null)) return null;

    ServiceExt serviceExt = serviceLevel.getServiceExt();
    EditServiceRate editServiceRate = serviceRates.getEditServiceRate();

    if((serviceExt == null) || (editServiceRate == null)) return null;

    ServiceVersionExt serviceVersionExt = null;
    List serviceVersionExtList = serviceLevel.getServiceVersionExtList();
    if((versionIndex >= 0) && (versionIndex < serviceVersionExtList.size()))
    {
      serviceVersionExt = (ServiceVersionExt)serviceVersionExtList.get(versionIndex);
    }

    if(serviceVersionExt == null) return null;

    ContractExpressionExt ceExt = serviceVersionExt.getAllZoneCeExtForContract();
    if(ceExt == null) return null;

    if(Constants.PRICE_BOOK.equals(ceExt.getContractExpression().getContractComponent()))
    {
      ceExt.getContractExpression().setCfgDiscountPercent(null);

      return Constants.PRICE_BOOK;
    }
    else
    {
      ceExt.getContractExpression().setCfgDiscountPercent(new Double(0.0));

      return Constants.CONTRACT;
    }
  }

  public static void copyPriceBookRatesToContractRates(
    ServiceRates serviceRates,
    ServiceLevel serviceLevel,
    int versionIndex
  )
  {
    if((serviceRates == null) || (serviceLevel == null)) return;

    ServiceExt serviceExt = serviceLevel.getServiceExt();
    ContractExpressionExt ceExt = serviceLevel.getContractExpressionExt();
    EditServiceRate editServiceRate = serviceRates.getEditServiceRate();

    if((serviceExt == null) || (ceExt == null) || (editServiceRate == null)) return;

    ServiceVersionExt serviceVersionExt = null;
    List serviceVersionExtList = serviceLevel.getServiceVersionExtList();
    if((versionIndex >= 0) && (versionIndex < serviceVersionExtList.size()))
    {
      serviceVersionExt = (ServiceVersionExt)serviceVersionExtList.get(versionIndex);
    }

    if(serviceVersionExt == null) return;

    List contractServiceRateExtList = serviceVersionExt.getContractServiceRateExtList();
    if(contractServiceRateExtList == null)
    {
      contractServiceRateExtList = new ArrayList();
      serviceVersionExt.setContractServiceRateExtList(contractServiceRateExtList);
    }

    for(int i=0; i<serviceRates.getPriceBookServiceRateList().size(); i++)
    {
      ServiceRate pbServiceRate = (ServiceRate)serviceRates.getPriceBookServiceRateList().get(i);
      if(serviceLevel.getContractExpressionExt().getContractExpression().getServiceLevel().equals(pbServiceRate.getServiceRateId().getServiceLevel())
        || serviceLevel.getServiceLevel().equals(pbServiceRate.getServiceRateId().getServiceLevel())
      )
      {
        ServiceRate contractServiceRate = new ServiceRate();
        EntityUtil.copyServiceRate(contractServiceRate, pbServiceRate);
        contractServiceRate.setRateId(null);
        contractServiceRate.setContractRef(Constants.CONT);
        contractServiceRate.getServiceRateId().setServiceLevel(serviceLevel.getServiceLevel());

        contractServiceRate.getServiceRateId().setBeginDate(serviceVersionExt.getServiceVersion().getServiceVersionId().getBeginDate());
        contractServiceRate.setEndDate(DateUtil.parseDate("12/31/2999", "MM/dd/yyyy"));
        contractServiceRate.getServiceRateId().setContractId(editServiceRate.getContract().getContractCode());

        ServiceRateExt contractServiceRateExt = new ServiceRateExt();
        contractServiceRateExt.setServiceRate(contractServiceRate);
        contractServiceRateExt.setNewFlag(true);

        contractServiceRateExtList.add(contractServiceRateExt);
      }
    }
  }

  public static void copyContractRates(
    int serviceRateIndex,
    ServiceRates serviceRates,
    ServiceLevel serviceLevel,
    int versionIndex
  )
  {
    if((serviceRates == null) || (serviceLevel == null)) return;

    ServiceExt serviceExt = serviceLevel.getServiceExt();
    ContractExpressionExt ceExt = serviceLevel.getContractExpressionExt();
    EditServiceRate editServiceRate = serviceRates.getEditServiceRate();

    if((serviceExt == null) || (ceExt == null) || (editServiceRate == null)) return;

    ServiceVersionExt serviceVersionExt = null;
    List serviceVersionExtList = serviceLevel.getServiceVersionExtList();
    if((versionIndex >= 0) && (versionIndex < serviceVersionExtList.size()))
    {
      serviceVersionExt = (ServiceVersionExt)serviceVersionExtList.get(versionIndex);
    }

    if(serviceVersionExt == null) return;

    List contractServiceRateExtList = serviceVersionExt.getContractServiceRateExtList();
    if(contractServiceRateExtList == null) return;

    if((serviceRateIndex < 0) || (serviceRateIndex >= contractServiceRateExtList.size())) return;

    int rowsToAdd = serviceVersionExt.getRowsToAdd();
    if(rowsToAdd <= 0) rowsToAdd = 1;
    serviceVersionExt.setRowsToAdd(rowsToAdd);

    boolean addAtEnd = serviceRateIndex == contractServiceRateExtList.size() - 1;

    ServiceRateExt selectedServiceRateExt = (ServiceRateExt)contractServiceRateExtList.get(serviceRateIndex);
    for(int i=0; i<rowsToAdd; i++)
    {
      ServiceRate newServiceRate = new ServiceRate();
      EntityUtil.copyServiceRate(newServiceRate, selectedServiceRateExt.getServiceRate());
      newServiceRate.setRateId(null);

      ServiceRateExt newServiceRateExt = new ServiceRateExt();
      newServiceRateExt.setServiceRate(newServiceRate);
      newServiceRateExt.setNewFlag(true);

      if(addAtEnd)
      {
        contractServiceRateExtList.add(newServiceRateExt);
      }
      else
      {
        contractServiceRateExtList.add(serviceRateIndex + 1, newServiceRateExt);
      }
    }
  }

  public static void createServiceVersion(ServiceRates serviceRates, boolean createdIfExists)
  {
    if(serviceRates == null) return;

    EditServiceRate editServiceRate = serviceRates.getEditServiceRate();
    if(editServiceRate == null) return;

    ServiceLevel serviceLevel = serviceRates.getServiceLevel();
    if(serviceLevel == null) return;

    List serviceLevelList = serviceRates.getServiceLevelList();
    if(serviceLevelList != null)
    {
      for(int i=0; i<serviceLevelList.size(); i++)
      {
        ServiceLevel tmpServiceLevel = (ServiceLevel)serviceLevelList.get(i);

        List serviceVersionExtList = tmpServiceLevel.getServiceVersionExtList();
        if(serviceVersionExtList == null)
        {
          serviceVersionExtList = new ArrayList();
          tmpServiceLevel.setServiceVersionExtList(serviceVersionExtList);
        }

        boolean needToCreate = true;

        if(!createdIfExists && (serviceVersionExtList.size() > 0)) needToCreate = false;

        if(needToCreate)
        {
          ServiceVersionExt serviceVersionExt = new ServiceVersionExt();
          serviceVersionExt.setServiceLevel(tmpServiceLevel);
          ServiceVersion serviceVersion = new ServiceVersion();
          serviceVersionExt.setServiceVersion(serviceVersion);
          ServiceVersionId serviceVersionId = new ServiceVersionId();
          serviceVersion.setServiceVersionId(serviceVersionId);
          serviceVersionId.setContractId(editServiceRate.getContract().getContractCode());
          serviceVersionId.setExpressionId(tmpServiceLevel.getExpressionId());
          serviceVersionId.setServiceName(tmpServiceLevel.getServiceName());
          serviceVersionId.setBeginDate(new Date());
          serviceVersion.setEndDate(DateUtil.parseDate("12/31/2999", "MM/dd/yyyy"));
          serviceVersion.setLevelToggle(Constants.U);

          if(serviceVersionExtList.size() > 0)
          {
            ServiceVersionExt lastServiceVersionExt = (ServiceVersionExt)serviceVersionExtList.get(serviceVersionExtList.size() - 1);
            ServiceVersion lastServiceVersion = lastServiceVersionExt.getServiceVersion();

            Date[] newDatePair = new Date[] {
              serviceVersion.getServiceVersionId().getBeginDate(),
              serviceVersion.getEndDate()
            };

            Date[] oldDatePair = new Date[] {
              lastServiceVersion.getServiceVersionId().getBeginDate(),
              lastServiceVersion.getEndDate()
            };

            DateUtil.calculateBeginDateAndEndDate(newDatePair, oldDatePair);

            serviceVersion.getServiceVersionId().setBeginDate(newDatePair[0]);
            serviceVersion.setEndDate(newDatePair[1]);

            lastServiceVersion.getServiceVersionId().setBeginDate(oldDatePair[0]);
            lastServiceVersion.setEndDate(oldDatePair[1]);
          }

          serviceVersionExtList.add(serviceVersionExt);
          if(serviceLevel == tmpServiceLevel)
          {
            createContractExpressionExt(serviceVersionExt);

            serviceVersionExt.setAllZoneCeExtForContract(
              getAllZoneContractExpressionExt(serviceVersionExt.getCeExtListForContract())
            );
          }
        }
      }

      packContractExpressionsByServiceLevel(serviceLevel);
    }
  }

  public static ContractExpressionExt createContractExpressionExt(
    ServiceVersionExt serviceVersionExt
  )
  {
    if(serviceVersionExt == null) return null;

    ServiceLevel serviceLevel = serviceVersionExt.getServiceLevel();
    ServiceVersion serviceVersion = serviceVersionExt.getServiceVersion();

    if((serviceLevel == null) || (serviceVersion == null)) return null;

    ContractExpressionExt newCeExt = new ContractExpressionExt();

    ContractExpressionExt existedContractExpressionExt = serviceLevel.getContractExpressionExt();
    List ceExtListForContract = serviceVersionExt.getCeExtListForContract();
    if((ceExtListForContract != null) && (ceExtListForContract.size() > 0))
    {
      existedContractExpressionExt = (ContractExpressionExt)ceExtListForContract.get(ceExtListForContract.size() - 1);

      newCeExt.setOldContractExpression(existedContractExpressionExt.getContractExpression());
    }

    ContractExpression newContractExpression = new ContractExpression();
    EntityUtil.copyContractExpression(newContractExpression, existedContractExpressionExt.getContractExpression());

    newContractExpression.getContractExpressionId().setContractId(serviceVersion.getServiceVersionId().getContractId());
    newContractExpression.getContractExpressionId().setBeginDate(serviceVersion.getServiceVersionId().getBeginDate());
    newContractExpression.setEndDate(serviceVersion.getEndDate());

    newContractExpression.setServiceLevel(serviceLevel.getServiceLevel());

    newCeExt.setContractExpression(newContractExpression);

    if(ceExtListForContract == null)
    {
      ceExtListForContract = new ArrayList();
      serviceVersionExt.setCeExtListForContract(ceExtListForContract);
    }

    ceExtListForContract.add(newCeExt);

    return newCeExt;
  }

  public static void loadContractExpressionForContract(
    ServiceVersionExt serviceVersionExt,
    Contract contract
  )
  {
    if((serviceVersionExt == null) || (contract == null)) return;

    ServiceLevel serviceLevel = serviceVersionExt.getServiceLevel();
    if(serviceLevel == null) return;

    ServiceVersion serviceVersion = serviceVersionExt.getServiceVersion();
    if(serviceVersion == null) return;

    ServiceRateService serviceRateService = (ServiceRateService)ServiceLocator.getInstance().getBean("serviceRateService");

    List ceList = serviceRateService.getContractExpressionListByContractCodeAndSerivceNameAndExpressionIdAndBeginDate(
      contract.getContractCode(),
      serviceLevel.getServiceName(),
      serviceLevel.getExpressionId(),
      serviceVersion.getServiceVersionId().getBeginDate()
    );

    List ceExtList = getContractExpressionExtListByContractExpressionList(ceList);

    serviceVersionExt.setCeExtListForContract(ceExtList);

    ContractExpressionExt allZoneCeExtForContract = getAllZoneContractExpressionExt(ceExtList);
    if(allZoneCeExtForContract == null)
    {
      allZoneCeExtForContract = createContractExpressionExt(serviceVersionExt);
    }

    serviceVersionExt.setAllZoneCeExtForContract(allZoneCeExtForContract);
  }

  public static List getContractExpressionExtListByContractExpressionList(List ceList)
  {
    List results = new ArrayList();

    if(ceList == null) return results;

    for(int i=0; i<ceList.size(); i++)
    {
      ContractExpression ce = (ContractExpression)ceList.get(i);

      ContractExpressionExt ceExt = new ContractExpressionExt();
      ceExt.setOldContractExpression(ce);

      ContractExpression newCe = new ContractExpression();
      EntityUtil.copyContractExpression(newCe, ce);

      ceExt.setContractExpression(newCe);

      results.add(ceExt);
    }

    return results;
  }

  public static List getControlExtListByControlList(List controlList)
  {
    List results = new ArrayList();

    if(controlList == null) return results;

    for(int i=0; i<controlList.size(); i++)
    {
      Control control = (Control)controlList.get(i);

      ControlExt controlExt = new ControlExt();
      controlExt.setOldControl(control);

      Control newControl = new Control();
      EntityUtil.copyControl(newControl, control);

      controlExt.setControl(newControl);

      results.add(controlExt);
    }

    return results;
  }

  public static void splitContractSpecificServiceRateToServiceLevel(ServiceRates serviceRates)
  {
    if(serviceRates == null) return;

    ServiceLevel serviceLevel = serviceRates.getServiceLevel();
    if(serviceLevel == null) return;

    EditServiceRate editServiceRate = serviceRates.getEditServiceRate();
    if(editServiceRate == null) return;

    List contractSpecificServiceRateList = serviceRates.getContractSpecificServiceRateList();
    if((contractSpecificServiceRateList == null) || (contractSpecificServiceRateList.size() == 0)) return;

    //List serviceLevelList = serviceRates.getServiceLevelList();
    //if(serviceLevelList != null)
    //{
    //  for(int i=0; i<serviceLevelList.size(); i++)
    //  {
    //    ServiceLevel serviceLevel = (ServiceLevel)serviceLevelList.get(i);

        List serviceVersionExtList = serviceLevel.getServiceVersionExtList();
        if(serviceVersionExtList != null)
        {
          for(int j=0; j<serviceVersionExtList.size(); j++)
          {
            ServiceVersionExt serviceVersionExt = (ServiceVersionExt)serviceVersionExtList.get(j);
            List serviceRateList = selectServiceRateListByServiceVersionExt(
              contractSpecificServiceRateList,
              serviceVersionExt,
              false
            );
            //if(serviceRateList.size() == 0)
            //{
              List serviceRateListForAllLevel = selectServiceRateListByServiceVersionExt(
                contractSpecificServiceRateList,
                serviceVersionExt,
                true
              );
            //}
            List serviceRateListForAllLevelExcludingCurrentLevel = excludeServiceRateList(
              serviceRateListForAllLevel,
              serviceRateList
            );
            serviceRateList.addAll(serviceRateListForAllLevelExcludingCurrentLevel);

            List serviceRateExtList = new ArrayList();
            for(int k=0; k<serviceRateList.size(); k++)
            {
              ServiceRate serviceRate = (ServiceRate)serviceRateList.get(k);

              ServiceRate newServiceRate = new ServiceRate();
              EntityUtil.copyServiceRate(newServiceRate, serviceRate);

              ServiceRateExt serviceRateExt = new ServiceRateExt();
              serviceRateExt.setOldServiceRate(serviceRate);
              serviceRateExt.setServiceRate(newServiceRate);
              serviceRateExt.setNewFlag(false);

              serviceRateExtList.add(serviceRateExt);
            }

            serviceVersionExt.setContractServiceRateExtList(serviceRateExtList);
          }
        }
      //}
    //}
  }

  public static void splitContractSpecificServiceRateToServiceVersionIfNecessary(
    ServiceRates serviceRates,
    ServiceVersionExt serviceVersionExt
  )
  {
    if((serviceRates == null) || (serviceVersionExt == null)) return;

    ServiceLevel serviceLevel = serviceRates.getServiceLevel();
    if(serviceLevel == null) return;

    EditServiceRate editServiceRate = serviceRates.getEditServiceRate();
    if(editServiceRate == null) return;

    List contractSpecificServiceRateList = serviceRates.getContractSpecificServiceRateList();
    if((contractSpecificServiceRateList == null) || (contractSpecificServiceRateList.size() == 0)) return;

    List serviceRateExtList = serviceVersionExt.getContractServiceRateExtList();
    if((serviceRateExtList != null) && (serviceRateExtList.size() > 0)) return;

    List serviceRateList = selectServiceRateListByServiceVersionExt(
      contractSpecificServiceRateList,
      serviceVersionExt,
      false
    );
    //if(serviceRateList.size() == 0)
    //{
      List serviceRateListForAllLevel = selectServiceRateListByServiceVersionExt(
        contractSpecificServiceRateList,
        serviceVersionExt,
        true
      );
    //}
    List serviceRateListForAllLevelExcludingCurrentLevel = excludeServiceRateList(
      serviceRateListForAllLevel,
      serviceRateList
    );
    serviceRateList.addAll(serviceRateListForAllLevelExcludingCurrentLevel);

    serviceRateExtList = new ArrayList();
    for(int k=0; k<serviceRateList.size(); k++)
    {
      ServiceRate serviceRate = (ServiceRate)serviceRateList.get(k);

      ServiceRate newServiceRate = new ServiceRate();
      EntityUtil.copyServiceRate(newServiceRate, serviceRate);

      ServiceRateExt serviceRateExt = new ServiceRateExt();
      serviceRateExt.setOldServiceRate(serviceRate);
      serviceRateExt.setServiceRate(newServiceRate);
      serviceRateExt.setNewFlag(false);

      serviceRateExtList.add(serviceRateExt);
    }

    serviceVersionExt.setContractServiceRateExtList(serviceRateExtList);
  }

  public static List selectServiceRateListByServiceVersionExt(
    List serviceRateList,
    ServiceVersionExt serviceVersionExt,
    boolean useAllFlag
  )
  {
    List results = new ArrayList();

    if((serviceVersionExt == null) || serviceRateList == null) return results;

    ServiceLevel serviceLevel = serviceVersionExt.getServiceLevel();
    ServiceVersion serviceVersion = serviceVersionExt.getServiceVersion();

    if((serviceLevel == null) || serviceVersion == null) return results;

    for(int i=0; i<serviceRateList.size(); i++)
    {
      ServiceRate serviceRate = (ServiceRate)serviceRateList.get(i);
      if(useAllFlag)
      {
        if("*".equals(serviceRate.getServiceRateId().getServiceLevel()) )
        {
          boolean matched = DateUtil.rateDateMatch(
            serviceVersion.getServiceVersionId().getBeginDate(),
            serviceVersion.getEndDate(),
            serviceRate.getServiceRateId().getBeginDate(),
            serviceRate.getEndDate()
          );
          if(matched) results.add(serviceRate);
        }
      }
      else if(serviceLevel.getServiceLevel().equals(serviceRate.getServiceRateId().getServiceLevel()))
      {
        boolean matched = DateUtil.rateDateMatch(
            serviceVersion.getServiceVersionId().getBeginDate(),
            serviceVersion.getEndDate(),
            serviceRate.getServiceRateId().getBeginDate(),
            serviceRate.getEndDate()
          );
          if(matched) results.add(serviceRate);
      }
    }

    return results;
  }

  public static ServiceRate selectServiceRateByServiceRateWithInputLevel(
    List serviceRateList,
    ServiceRate serviceRate,
    String level
  )
  {
    if((serviceRateList == null) || (serviceRate == null) || (level == null)) return null;

    for(int i=0; i<serviceRateList.size(); i++)
    {
      ServiceRate tmpServiceRate = (ServiceRate)serviceRateList.get(i);
      if(level.equals(tmpServiceRate.getServiceRateId().getServiceLevel())
        && serviceRate.getServiceRateId().getExpressionId().equals(tmpServiceRate.getServiceRateId().getExpressionId())
        && serviceRate.getServiceRateId().getLocation().equals(tmpServiceRate.getServiceRateId().getLocation())
        && serviceRate.getServiceRateId().getGroupId().equals(tmpServiceRate.getServiceRateId().getGroupId())
        && serviceRate.getServiceRateId().getVesselType().equals(tmpServiceRate.getServiceRateId().getVesselType())
        && serviceRate.getServiceRateId().getWithInspection().equals(tmpServiceRate.getServiceRateId().getWithInspection())
        && serviceRate.getServiceRateId().getIntData2().equals(tmpServiceRate.getServiceRateId().getIntData2())
        && serviceRate.getServiceRateId().getFloatData2().equals(tmpServiceRate.getServiceRateId().getFloatData2())
      )
      {
        boolean matched = DateUtil.rateDateMatch(
          tmpServiceRate.getServiceRateId().getBeginDate(),
          tmpServiceRate.getEndDate(),
          serviceRate.getServiceRateId().getBeginDate(),
          serviceRate.getEndDate()
        );
        if(matched) return tmpServiceRate;
      }
    }

    return null;
  }

  public static Map groupCeExtListForContractMapByZoneId(ServiceVersionExt serviceVersionExt)
  {
    Map results = new HashMap();

    if(serviceVersionExt == null) return results;

    List ceExtListForContract = serviceVersionExt.getCeExtListForContract();
    if(ceExtListForContract == null) return results;

    for(int i=0; i<ceExtListForContract.size(); i++)
    {
      ContractExpressionExt ceExt = (ContractExpressionExt)ceExtListForContract.get(i);

      String zoneId = ceExt.getContractExpression().getContractExpressionId().getLocation();
      List ceExtList = (List)results.get(zoneId);

      if(ceExtList == null)
      {
        ceExtList = new ArrayList();
        results.put(zoneId, ceExtList);
      }

      ceExtList.add(ceExt);
    }

    return results;
  }

  public static void getToggleLevelFromServiceVersion(ServiceVersionExt serviceVersionExt)
  {
    if(serviceVersionExt == null) return;

    ServiceVersion serviceVersion = serviceVersionExt.getServiceVersion();
    if(serviceVersion == null) return;

    serviceVersionExt.setRateApplied(serviceVersion.getLevelToggle());

    if(Constants.T.equals(serviceVersionExt.getRateApplied()))
    {
      serviceVersionExt.setApplyToThisLevel(Constants.No);
    }
    else if(Constants.A.equals(serviceVersionExt.getRateApplied()))
    {
      serviceVersionExt.setApplyToThisLevel(Constants.Yes);
    }
  }

  public static void setToggleLevelToServiceVersion(ServiceLevel serviceLevel)
  {
    if(serviceLevel == null) return;

    List serviceVersionExtList = serviceLevel.getServiceVersionExtList();
    if(serviceVersionExtList  == null) return;

    for(int i=0; i<serviceVersionExtList.size(); i++)
    {
      ServiceVersionExt serviceVersionExt = (ServiceVersionExt)serviceVersionExtList.get(i);
      serviceVersionExt.getServiceVersion().setLevelToggle(serviceVersionExt.getRateApplied());
    }
  }

  public static ContractExpressionExt findContractExpressionExtByCeExtLocation(
    List ceExtList,
    ContractExpressionExt ceExt
  )
  {
    if((ceExtList == null) || (ceExt == null)) return null;

    for(int i=0; i<ceExtList.size(); i++)
    {
      ContractExpressionExt tmpCeExt = (ContractExpressionExt)ceExtList.get(i);

      if(ceExt.getContractExpression().getContractExpressionId().getLocation().equals(
        tmpCeExt.getContractExpression().getContractExpressionId().getLocation()
      ))
      {
        return tmpCeExt;
      }
    }

    return null;
  }

  public static ControlExt findControlExtByControlExtObjectName(
    List controlExtList,
    ControlExt controlExt
  )
  {
    if((controlExtList == null) || (controlExt == null)) return null;

    for(int i=0; i<controlExtList.size(); i++)
    {
      ControlExt tmpControlExt = (ControlExt)controlExtList.get(i);

      if(controlExt.getControl().getControlId().getObjectName().equals(
        tmpControlExt.getControl().getControlId().getObjectName()
      ))
      {
        return tmpControlExt;
      }
    }

    return null;
  }

  public static void addNewVersion(ServiceRates serviceRates)
  {
    if(serviceRates == null) return;

    ServiceLevel serviceLevel = serviceRates.getServiceLevel();
    if(serviceLevel == null) return;

    createServiceVersion(serviceRates, true);
    setEnableApplyAllFlag(serviceRates);
  }

  public static void deleteContractRates(ServiceRates serviceRates, String indexStr)
  {
    if(serviceRates == null) return;

    ServiceLevel serviceLevel = serviceRates.getServiceLevel();
    if(serviceLevel == null) return;

    int versionIndex = serviceLevel.getActiveServiceVersionIndex();

    ServiceVersionExt serviceVersionExt = null;
    List serviceVersionExtList = serviceLevel.getServiceVersionExtList();
    if((versionIndex >= 0) && (versionIndex < serviceVersionExtList.size()))
    {
      serviceVersionExt = (ServiceVersionExt)serviceVersionExtList.get(versionIndex);
    }

    if(serviceVersionExt == null) return;

    int index = -1;
    try
    {
      index = new Integer(indexStr).intValue();
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

    List contractServiceRateExtList = serviceVersionExt.getContractServiceRateExtList();
    if(contractServiceRateExtList != null)
    {
      if((index >= 0) && (index < contractServiceRateExtList.size()))
      {
        ServiceRateExt deletedServiceRateExt = (ServiceRateExt)contractServiceRateExtList.remove(index);
        if(deletedServiceRateExt != null)
        {
          serviceVersionExt.getDeletedContractServiceRateExtList().add(deletedServiceRateExt);
        }
      }
    }
  }

  public static List findContractExpressionExtListByZoneId(ServiceVersionExt serviceVersionExt, String zoneId)
  {
    List results = new ArrayList();

    if((serviceVersionExt == null) || (zoneId == null)) return results;

    List ceExtListForContract = serviceVersionExt.getCeExtListForContract();
    if(ceExtListForContract == null) return results;

    for(int i=0; i<ceExtListForContract.size(); i++)
    {
      ContractExpressionExt ceExt = (ContractExpressionExt)ceExtListForContract.get(i);
      if(zoneId.equals(ceExt.getContractExpression().getContractExpressionId().getLocation())) results.add(ceExt);
    }

    return results;
  }

  public static void addZoneDiscount(ServiceRates serviceRates)
  {
    if(serviceRates == null) return;

    ServiceLevel serviceLevel = serviceRates.getServiceLevel();
    if(serviceLevel == null) return;

    int versionIndex = serviceLevel.getActiveServiceVersionIndex();

    ServiceVersionExt serviceVersionExt = null;
    List serviceVersionExtList = serviceLevel.getServiceVersionExtList();
    if((versionIndex >= 0) && (versionIndex < serviceVersionExtList.size()))
    {
      serviceVersionExt = (ServiceVersionExt)serviceVersionExtList.get(versionIndex);
    }

    if(serviceVersionExt == null) return;

    String selectedZoneId = serviceVersionExt.getSelectedZoneId();
    if(selectedZoneId == null) return;

    List existedCeExtList = findContractExpressionExtListByZoneId(serviceVersionExt, selectedZoneId);
    if(existedCeExtList.size() > 0) return;

    ContractExpressionExt ceExt = createContractExpressionExt(serviceVersionExt);
    if(ceExt != null) ceExt.getContractExpression().getContractExpressionId().setLocation(selectedZoneId);
  }

  public static void deleteZoneDiscount(ServiceRates serviceRates)
  {
    if(serviceRates == null) return;

    ServiceLevel serviceLevel = serviceRates.getServiceLevel();
    if(serviceLevel == null) return;

    int versionIndex = serviceLevel.getActiveServiceVersionIndex();

    ServiceVersionExt serviceVersionExt = null;
    List serviceVersionExtList = serviceLevel.getServiceVersionExtList();
    if((versionIndex >= 0) && (versionIndex < serviceVersionExtList.size()))
    {
      serviceVersionExt = (ServiceVersionExt)serviceVersionExtList.get(versionIndex);
    }

    if(serviceVersionExt == null) return;

    String selectedZoneId = serviceVersionExt.getSelectedZoneId();
    if(selectedZoneId == null) return;

    List existedCeExtList = findContractExpressionExtListByZoneId(serviceVersionExt, selectedZoneId);
    if(existedCeExtList.size() == 0) return;

    List ceExtListForContract = serviceVersionExt.getCeExtListForContract();
    if(ceExtListForContract == null) return;

    ceExtListForContract.removeAll(existedCeExtList);

    serviceVersionExt.getDeletedCeExtListForContract().addAll(existedCeExtList);
  }

  public static void copyControlListFromPbToContract(List fromList, List toList)
  {
    if((fromList == null) || (toList == null)) return;

    for(int i=0; i<fromList.size(); i++)
    {
      Control fromControl = (Control)fromList.get(i);

      ControlExt controlExt = new ControlExt();
      Control toControl = new Control();
      EntityUtil.copyControl(toControl, fromControl);

      controlExt.setControl(toControl);

      toList.add(controlExt);
    }
  }

  public static void copyServiceVersionDatesToControlExtList(ServiceVersionExt serviceVersionExt)
  {
    if(serviceVersionExt == null) return;

    List controlExtList = serviceVersionExt.getContractControlExtList();
    if(controlExtList == null) return;

    ServiceVersion serviceVersion = serviceVersionExt.getServiceVersion();

    for(int i=0; i<controlExtList.size(); i++)
    {
      ControlExt controlExt = (ControlExt)controlExtList.get(i);
      Control control = controlExt.getControl();

      control.getControlId().setContractId(serviceVersion.getServiceVersionId().getContractId());
      control.getControlId().setBeginDate(serviceVersion.getServiceVersionId().getBeginDate());
      control.setEndDate(serviceVersion.getEndDate());
    }
  }

  public static Map groupControlListByObjectName(List controlList)
  {
    Map results = new HashMap();

    if(controlList == null) return results;

    for(int i=0; i<controlList.size(); i++)
    {
      Control control = (Control)controlList.get(i);

      String objectName = control.getControlId().getObjectName();
      List list = (List)results.get(objectName);

      if(list == null)
      {
        list = new ArrayList();
        results.put(objectName, list);
      }

      list.add(control);
    }

    return results;
  }

  public static Control createControlFromObjects(Object[] objects)
  {
    if(objects == null) return null;

    Control control = new Control();
    ControlId controlId = new ControlId();

    control.setControlId(controlId);

    controlId.setContractId((String)objects[0]);
    controlId.setServiceName((String)objects[1]);
    controlId.setQuestionId((String)objects[2]);
    controlId.setObjectName((String)objects[3]);
    controlId.setBeginDate((Date)objects[4]);

    control.setRbKey((String)objects[5]);
    control.setAttributes((String)objects[6]);
    control.setControlType((String)objects[7]);
    control.setParameters((String)objects[8]);

    Number number = (Number)objects[9];
    if(number != null)
    {
      control.setSortOrderNum(new Integer(number.intValue()));
    }
    control.setHelpUrl((String)objects[10]);
    control.setVisibility((String)objects[11]);
    control.setEndDate((Date)objects[12]);

    return control;
  }

  public static List getContractControlExtListByServiceNameAndExpressionIdAndContractAndBeginDate(
    String serviceName,
    String expressionId,
    Contract contract,
    Date beginDate
  )
  {
    ServiceRateService serviceRateService = (ServiceRateService)ServiceLocator.getInstance().getBean("serviceRateService");

    List controlList = serviceRateService.getContractControlListByServiceNameAndExpressionIdAndContractAndBeginDate(
      serviceName,
      expressionId,
      contract,
      beginDate
    );

    List results = new ArrayList();
    for(int i=0; i<controlList.size(); i++)
    {
      Control control = (Control)controlList.get(i);

      ControlExt controlExt = new ControlExt();
      controlExt.setOldControl(control);

      Control newControl = new Control();
      EntityUtil.copyControl(newControl, control);

      controlExt.setControl(newControl);

      results.add(controlExt);
    }

    return results;
  }

  public static List getPbControlListByServiceNameAndExpressionIdAndContract(
    String serviceName,
    String expressionId,
    Contract contract
  )
  {
    ServiceRateService serviceRateService = (ServiceRateService)ServiceLocator.getInstance().getBean("serviceRateService");

    return serviceRateService.getPbControlListByServiceNameAndExpressionIdAndContract(
      serviceName,
      expressionId,
      contract
    );
  }

  public static void updateContractControlExtList(Contract contract, ServiceVersionExt serviceVersionExt)
  {
    ServiceLevel serviceLevel = serviceVersionExt.getServiceLevel();

    List contractControlExtList = serviceVersionExt.getContractControlExtList();
    if(contractControlExtList == null)
    {
      contractControlExtList = new ArrayList();
      serviceVersionExt.setContractControlExtList(contractControlExtList);
    }

    if(contractControlExtList.size() == 0)
    {
      List controlList = getPbControlListByServiceNameAndExpressionIdAndContract(
        serviceLevel.getServiceName(),
        serviceLevel.getExpressionId(),
        contract
      );

      if(controlList.size() > 0)
      {
        ContractServiceUtil.copyControlListFromPbToContract(controlList, contractControlExtList);
      }
    }
  }

  public static void setVesselCheckedForServiceVersionExt(ServiceVersionExt serviceVersionExt)
  {
    if(serviceVersionExt == null) return;
    ServiceLevel serviceLevel = serviceVersionExt.getServiceLevel();
    if(serviceLevel == null) return;

    List ceExtListForContract = serviceVersionExt.getCeExtListForContract();
    ContractExpressionExt ceExt = null;
    if((ceExtListForContract != null) && (ceExtListForContract.size() > 0))
    {
      for(int i=0; i<ceExtListForContract.size(); i++)
      {
        ceExt = (ContractExpressionExt)ceExtListForContract.get(i);
      }
    }
    else
    {
      ceExt = serviceLevel.getContractExpressionExt();
    }

    if(ceExt != null)
    {
      Double useVesselId = ceExt.getContractExpression().getUseVesselId();
      if(useVesselId != null)
      {
        if(useVesselId.doubleValue() == 1.0)
        {
          serviceVersionExt.setVesselChecked(true);
        }
      }
    }
  }

  public static void setProductGroupCheckedForServiceVersionExt(ServiceVersionExt serviceVersionExt)
  {
    if(serviceVersionExt == null) return;
    ServiceLevel serviceLevel = serviceVersionExt.getServiceLevel();
    if(serviceLevel == null) return;

    List ceExtListForContract = serviceVersionExt.getCeExtListForContract();
    ContractExpressionExt ceExt = null;
    if((ceExtListForContract != null) && (ceExtListForContract.size() > 0))
    {
      for(int i=0; i<ceExtListForContract.size(); i++)
      {
        ceExt = (ContractExpressionExt)ceExtListForContract.get(i);
      }
    }
    else
    {
      ceExt = serviceLevel.getContractExpressionExt();
    }

    if(ceExt != null)
    {
      Double useGroupId = ceExt.getContractExpression().getUseGroupId();
      if(useGroupId != null)
      {
        if(useGroupId.doubleValue() == 1.0)
        {
          serviceVersionExt.setProductGroupChecked(true);
        }
      }
    }
  }

  public static void setEnableApplyAllFlag(ServiceRates serviceRates)
  {
    if(serviceRates == null) return;

    ServiceLevel serviceLevel = serviceRates.getServiceLevel();
    if(serviceLevel == null) return;

    //int versionIndex = serviceLevel.getActiveServiceVersionIndex();
    List serviceVersionExtList = serviceLevel.getServiceVersionExtList();
    //ServiceVersionExt serviceVersionExt = (ServiceVersionExt)serviceVersionExtList.get(versionIndex);
    if(serviceVersionExtList == null) return;

    for(int i=0; i<serviceVersionExtList.size(); i++)
    {
      ServiceVersionExt serviceVersionExt = (ServiceVersionExt)serviceVersionExtList.get(i);

      //String rateApplied = serviceVersionExt.getRateApplied();
      //if((rateApplied == null) || Constants.U.equals(rateApplied)) serviceVersionExt.setRateApplied(Constants.T);

      if(Constants.MASTER.equals(serviceLevel.getCfgContractId()))
      {
        serviceVersionExt.setEnableApplyAll(false);
      }
      else if(!Constants.A.equals(serviceVersionExt.getRateApplied()))
      {
        boolean hasApplyToAllInOtherLevel = false;
        List serviceLevelList = serviceRates.getServiceLevelList();
        if(serviceLevelList != null)
        {
          for(int j=0; j<serviceLevelList.size(); j++)
          {
            ServiceLevel tmpServiceLevel = (ServiceLevel)serviceLevelList.get(j);

            if(serviceLevel == tmpServiceLevel) continue;

            List tmpServiceVersionExtList = tmpServiceLevel.getServiceVersionExtList();

            ServiceVersionExt tmpServiceVersionExt = (ServiceVersionExt)tmpServiceVersionExtList.get(i);
            if(Constants.A.equals(tmpServiceVersionExt.getRateApplied()))
            {
              hasApplyToAllInOtherLevel = true;
              break;
            }
          }
        }

        serviceVersionExt.setEnableApplyAll(!hasApplyToAllInOtherLevel);
      }
      else
      {
        serviceVersionExt.setEnableApplyAll(true);
      }
    }
  }

  public static void packContractExpressionsByServiceLevel(ServiceLevel serviceLevel)
  {
    if(serviceLevel == null) return;

    List serviceVersionExtList = serviceLevel.getServiceVersionExtList();
    if(serviceVersionExtList == null) return;

    for(int i=0; i<serviceVersionExtList.size(); i++)
    {
      ServiceVersionExt serviceVersionExt = (ServiceVersionExt)serviceVersionExtList.get(i);
      ServiceVersion serviceVersion = serviceVersionExt.getServiceVersion();

      serviceVersionExt.getCeExtListForContractBuffer().clear();
      List ceExtListForContract = serviceVersionExt.getCeExtListForContract();

      if(ceExtListForContract != null)
      {
        for(int j=0; j<ceExtListForContract.size(); j++)
        {
          ContractExpressionExt ceExt = (ContractExpressionExt)ceExtListForContract.get(j);

          ceExt.getContractExpression().getContractExpressionId().setBeginDate(serviceVersion.getServiceVersionId().getBeginDate());
          ceExt.getContractExpression().setEndDate(serviceVersion.getEndDate());

          if(Constants.T.equals(serviceVersionExt.getRateApplied()))
          {
            if(Constants.PRICE_BOOK.equals(ceExt.getContractExpression().getContractComponent()))
            {
              ceExt.getContractExpression().setServiceLevel(
                serviceLevel.getContractExpressionExt().getContractExpression().getServiceLevel()
              );
            }
            else
            {
              ceExt.getContractExpression().setServiceLevel(serviceLevel.getServiceLevel());
            }
          }
          else if(Constants.A.equals(serviceVersionExt.getRateApplied()))
          {
            ceExt.getContractExpression().setServiceLevel("*");
          }

          if(serviceVersionExt.getEnableVessel())
          {
            if(serviceVersionExt.getVesselChecked())
            {
              ceExt.getContractExpression().setUseVesselId(new Double(1.0));
            }
            else
            {
              ceExt.getContractExpression().setUseVesselId(new Double(-1.0));
            }
          }

          if(serviceVersionExt.getEnableProductGroup())
          {
            if(serviceVersionExt.getProductGroupChecked())
            {
              ceExt.getContractExpression().setUseGroupId(new Double(1.0));
            }
            else
            {
              ceExt.getContractExpression().setUseGroupId(new Double(-1.0));
            }
          }
        }

        serviceVersionExt.getCeExtListForContractBuffer().addAll(ceExtListForContract);
      }
    }
  }

  public static void packServicesByServiceLevel(ServiceLevel serviceLevel, Contract contract)
  {
    if((serviceLevel == null) || (contract == null)) return;

    List serviceVersionExtList = serviceLevel.getServiceVersionExtList();
    if(serviceVersionExtList != null)
    {
      for(int i=0; i<serviceVersionExtList.size(); i++)
      {
        ServiceVersionExt serviceVersionExt = (ServiceVersionExt)serviceVersionExtList.get(i);
        ServiceVersion serviceVersion = serviceVersionExt.getServiceVersion();

        ServiceExt serviceExt = serviceLevel.getServiceExt();
        if(Constants.MASTER.equals(serviceExt.getService().getServiceId().getContractId()))
        {
          ServiceExt serviceExtForContract = serviceVersionExt.getServiceExtForContract();
          if(serviceExtForContract == null)
          {
            serviceExtForContract = new ServiceExt();
            serviceVersionExt.setServiceExtForContract(serviceExtForContract);
          }

          Service oldServiceForContract = serviceExtForContract.getServiceForContract();
          Service oldService = oldServiceForContract;
          if(oldServiceForContract == null)
          {
            oldService = serviceExt.getService();
          }

          Service serviceForContract = serviceExtForContract.getService();
          if(serviceForContract == null)
          {
            serviceForContract = new Service();

            EntityUtil.copyService(serviceForContract, oldService);
            serviceExtForContract.setService(serviceForContract);
          }

          serviceForContract.getServiceId().setContractId(contract.getContractCode());
          serviceForContract.setVisibility(Constants.ADDL);
          serviceForContract.getServiceId().setBeginDate(serviceVersion.getServiceVersionId().getBeginDate());
          serviceForContract.setEndDate(serviceVersion.getEndDate());
        }
      }
    }
  }

  public static void packControlsByServiceLevel(ServiceLevel serviceLevel, Contract contract)
  {
    if((serviceLevel == null) || (contract == null)) return;

    List serviceVersionExtList = serviceLevel.getServiceVersionExtList();
    if(serviceVersionExtList != null)
    {
      for(int i=0; i<serviceVersionExtList.size(); i++)
      {
        ServiceVersionExt serviceVersionExt = (ServiceVersionExt)serviceVersionExtList.get(i);
        ServiceVersion serviceVersion = serviceVersionExt.getServiceVersion();

        serviceVersionExt.getContractControlExtListBuffer().clear();

        if(Constants.MASTER.equals(serviceLevel.getCfgContractId()))
        {
          ContractServiceUtil.updateContractControlExtList(contract, serviceVersionExt);
          List contractControlExtList = serviceVersionExt.getContractControlExtList();
          if(contractControlExtList != null)
          {
            for(int k=0; k<contractControlExtList.size(); k++)
            {
              ControlExt controlExt = (ControlExt)contractControlExtList.get(k);

              controlExt.getControl().setVisibility(Constants.ADDL);
            }
          }
        }
        else
        {
          if(!serviceVersionExt.getHide())
          {
            List contractControlExtList = serviceVersionExt.getContractControlExtList();
            if(contractControlExtList != null)
            {
              for(int k=0; k<contractControlExtList.size(); k++)
              {
                ControlExt controlExt = (ControlExt)contractControlExtList.get(k);

                controlExt.getControl().setVisibility("-1");

                // this row should be deleted since it is not 'MASTER' row. see itrack:117663
                controlExt.setDeleted(true);
              }
            }
          }
          else
          {
            ContractServiceUtil.updateContractControlExtList(contract, serviceVersionExt);
            List contractControlExtList = serviceVersionExt.getContractControlExtList();

            if(contractControlExtList != null)
            {
              for(int k=0; k<contractControlExtList.size(); k++)
              {
                ControlExt controlExt = (ControlExt)contractControlExtList.get(k);

                controlExt.getControl().setVisibility(Constants.HIDE);
              }
            }
          }
        }

        copyServiceVersionDatesToControlExtList(serviceVersionExt);

        List contractControlExtList = serviceVersionExt.getContractControlExtList();
        if(contractControlExtList != null)
        {
          serviceVersionExt.getContractControlExtListBuffer().addAll(contractControlExtList);
        }
      }
    }
  }

  public static void packServiceRatesForSave(ServiceRates serviceRates)
  {
    if(serviceRates == null) return;

    ServiceLevel serviceLevel = serviceRates.getServiceLevel();
    if(serviceLevel == null) return;

    EditServiceRate editServiceRate = serviceRates.getEditServiceRate();
    if(editServiceRate == null) return;

    List serviceVersionExtList = serviceLevel.getServiceVersionExtList();
    if(serviceVersionExtList != null)
    {
      for(int i=0; i<serviceVersionExtList.size(); i++)
      {
        ServiceVersionExt serviceVersionExt = (ServiceVersionExt)serviceVersionExtList.get(i);
        ServiceVersion serviceVersion = serviceVersionExt.getServiceVersion();

        if(serviceVersion.getLevelToggle() == null) serviceVersion.setLevelToggle(Constants.U);
      }
    }

    packContractExpressionsByServiceLevel(serviceLevel);

    packServicesByServiceLevel(serviceLevel, editServiceRate.getContract());

    packControlsByServiceLevel(serviceLevel, editServiceRate.getContract());
  }

  public static void applyToServiceVersion(
    ServiceVersionExt serviceVersionExt0,
    ServiceVersionExt serviceVersionExt1
  )
  {
    if((serviceVersionExt0 == null) || (serviceVersionExt1 == null)) return;
    if(serviceVersionExt0 == serviceVersionExt1) return;

    ServiceVersion serviceVersion0 = serviceVersionExt0.getServiceVersion();
    ServiceVersion serviceVersion1 = serviceVersionExt1.getServiceVersion();

    serviceVersion1.getServiceVersionId().setBeginDate(serviceVersion0.getServiceVersionId().getBeginDate());
    serviceVersion1.setEndDate(serviceVersion0.getEndDate());

    serviceVersion1.setLevelToggle(serviceVersionExt1.getRateApplied());
    if(Constants.A.equals(serviceVersionExt0.getRateApplied()))
    {
      if(Constants.Yes.equals(serviceVersionExt1.getApplyToThisLevel()))
      {
        serviceVersion1.setLevelToggle(Constants.A);
      }
    }

    if(serviceVersion1.getLevelToggle() == null) serviceVersion1.setLevelToggle(Constants.U);
  }

  public static void applyToContractExpressions(
    ServiceVersionExt serviceVersionExt0,
    ServiceVersionExt serviceVersionExt1
  )
  {
    if((serviceVersionExt0 == null) || (serviceVersionExt1 == null)) return;
    if(serviceVersionExt0 == serviceVersionExt1) return;

    ServiceVersion serviceVersion0 = serviceVersionExt0.getServiceVersion();
    ServiceVersion serviceVersion1 = serviceVersionExt1.getServiceVersion();

    serviceVersionExt1.getCeExtListForContractBuffer().clear();

    List ceExtListForContract1 = serviceVersionExt1.getCeExtListForContract();
    if(ceExtListForContract1 != null)
    {
      for(int j=0; j<ceExtListForContract1.size(); j++)
      {
        ContractExpressionExt ceExt = (ContractExpressionExt)ceExtListForContract1.get(j);

        ceExt.getContractExpression().getContractExpressionId().setBeginDate(serviceVersion0.getServiceVersionId().getBeginDate());
        ceExt.getContractExpression().setEndDate(serviceVersion0.getEndDate());

        ContractExpression oldCe = ceExt.getOldContractExpression();
        if(oldCe != null)
        {
          serviceVersionExt1.getCeExtListForContractBuffer().add(ceExt);
        }
      }
    }

    if(Constants.A.equals(serviceVersionExt0.getRateApplied()))
    {
      if(Constants.Yes.equals(serviceVersionExt1.getApplyToThisLevel()))
      {
        List ceExtListForContract0 = serviceVersionExt0.getCeExtListForContract();

        if(ceExtListForContract0 != null)
        {
          for(int k=0; k<ceExtListForContract0.size(); k++)
          {
            ContractExpressionExt ceExt = (ContractExpressionExt)ceExtListForContract0.get(k);

            ContractExpressionExt newCeExt = new ContractExpressionExt();
            ContractExpression newCe = new ContractExpression();
            newCeExt.setContractExpression(newCe);
            EntityUtil.copyContractExpression(newCe, ceExt.getContractExpression());
            newCe.getContractExpressionId().setServiceName(serviceVersionExt1.getServiceLevel().getServiceName());
            newCe.getContractExpressionId().setExpressionId(serviceVersionExt1.getServiceLevel().getExpressionId());
            newCe.setServiceLevel("*");

            ContractExpressionExt existedCeExt = findContractExpressionExtByCeExtLocation(ceExtListForContract1, ceExt);
            if(existedCeExt != null)
            {
              newCeExt.setOldContractExpression(existedCeExt.getOldContractExpression());
              serviceVersionExt1.getCeExtListForContractBuffer().remove(existedCeExt);
            }


            serviceVersionExt1.getCeExtListForContractBuffer().add(newCeExt);
          }
        }
      }
    }
  }

  public static void applyToControls(
    ServiceVersionExt serviceVersionExt0,
    ServiceVersionExt serviceVersionExt1
  )
  {
    if((serviceVersionExt0 == null) || (serviceVersionExt1 == null)) return;
    if(serviceVersionExt0 == serviceVersionExt1) return;

    ServiceVersion serviceVersion0 = serviceVersionExt0.getServiceVersion();
    ServiceVersion serviceVersion1 = serviceVersionExt1.getServiceVersion();

    serviceVersionExt1.getContractControlExtListBuffer().clear();

    List contractControlExtList1 = serviceVersionExt1.getContractControlExtList();
    if(contractControlExtList1 != null)
    {
      for(int j=0; j<contractControlExtList1.size(); j++)
      {
        ControlExt controlExt = (ControlExt)contractControlExtList1.get(j);

        controlExt.getControl().getControlId().setBeginDate(serviceVersion0.getServiceVersionId().getBeginDate());
        controlExt.getControl().setEndDate(serviceVersion0.getEndDate());
      }

      serviceVersionExt1.getContractControlExtListBuffer().addAll(contractControlExtList1);
    }

    if(Constants.A.equals(serviceVersionExt0.getRateApplied()))
    {
      if(Constants.Yes.equals(serviceVersionExt1.getApplyToThisLevel()))
      {
        List contractControlExtList0 = serviceVersionExt0.getContractControlExtList();

        if(contractControlExtList0 != null)
        {
          for(int k=0; k<contractControlExtList0.size(); k++)
          {
            ControlExt controlExt = (ControlExt)contractControlExtList0.get(k);

            ControlExt newControlExt = new ControlExt();
            Control newControl = new Control();
            newControlExt.setControl(newControl);
            EntityUtil.copyControl(newControl, controlExt.getControl());
            newControl.getControlId().setServiceName(serviceVersionExt1.getServiceLevel().getServiceName());
            newControl.getControlId().setQuestionId(controlExt.getControl().getControlId().getQuestionId());

            ControlExt existedControlExt = findControlExtByControlExtObjectName(contractControlExtList1, controlExt);
            if(existedControlExt != null)
            {
              newControlExt.setOldControl(existedControlExt.getOldControl());
              serviceVersionExt1.getContractControlExtListBuffer().remove(existedControlExt);
            }


            serviceVersionExt1.getContractControlExtListBuffer().add(newControlExt);
          }
        }
      }
    }
  }

  public static void applyLevels(ServiceRates serviceRates)
  {
    if(serviceRates == null) return;

    ServiceLevel serviceLevel = serviceRates.getServiceLevel();
    if(serviceLevel == null) return;

    List serviceVersionExtList = serviceLevel.getServiceVersionExtList();
    for(int i=0; i<serviceVersionExtList.size(); i++)
    {
      ServiceVersionExt serviceVersionExt = (ServiceVersionExt)serviceVersionExtList.get(i);
      ServiceVersion serviceVersion = serviceVersionExt.getServiceVersion();

      List serviceLevelList = serviceRates.getServiceLevelList();
      for(int j=0; j<serviceLevelList.size(); j++)
      {
        ServiceLevel tmpServiceLevel = (ServiceLevel)serviceLevelList.get(j);
        if(tmpServiceLevel == serviceLevel) continue;

        List tmpServiceVersionExtList = tmpServiceLevel.getServiceVersionExtList();
        if(tmpServiceVersionExtList == null) continue;
        if(i >= tmpServiceVersionExtList.size()) continue;

        ServiceVersionExt tmpServiceVersionExt = (ServiceVersionExt)tmpServiceVersionExtList.get(i);

        applyToServiceVersion(serviceVersionExt, tmpServiceVersionExt);

        applyToContractExpressions(serviceVersionExt, tmpServiceVersionExt);

        applyToControls(serviceVersionExt, tmpServiceVersionExt);
      }

      if((serviceVersionExt.getRateApplied() == null)
        || Constants.U.equals(serviceVersionExt.getRateApplied())
      )
      {
        continue;
      }

      ContractExpressionExt ceExt = serviceVersionExt.getAllZoneCeExtForContract();
      if((ceExt != null) && Constants.PRICE_BOOK.equals(ceExt.getContractExpression().getContractComponent()))
      {
        continue;
      }

      List contractServiceRateExtList = serviceVersionExt.getContractServiceRateExtList();

      if(contractServiceRateExtList != null)
      {
        if(Constants.T.equals(serviceVersionExt.getRateApplied()))
        {
          serviceVersionExt.getContractServiceRateExtListBuffer().clear();
          for(int j=0; j<contractServiceRateExtList.size(); j++)
          {
            ServiceRateExt serviceRateExt = (ServiceRateExt)contractServiceRateExtList.get(j);

            ServiceRate serviceRate = serviceRateExt.getServiceRate();
            if("*".equals(serviceRate.getServiceRateId().getServiceLevel()))
            {
              ServiceRateExt newServiceRateExt = new ServiceRateExt();
              ServiceRate newServiceRate = new ServiceRate();
              newServiceRateExt.setServiceRate(newServiceRate);
              EntityUtil.copyServiceRate(newServiceRate, serviceRate);

              newServiceRateExt.getServiceRate().getServiceRateId().setServiceLevel(
                serviceLevel.getServiceLevel()
              );

              serviceVersionExt.getContractServiceRateExtListBuffer().add(newServiceRateExt);
            }
            else
            {
              serviceVersionExt.getContractServiceRateExtListBuffer().add(serviceRateExt);
            }
          }
        }
        else if(Constants.A.equals(serviceVersionExt.getRateApplied()))
        {
          serviceVersionExt.getContractServiceRateExtListBuffer().clear();
          for(int j=0; j<contractServiceRateExtList.size(); j++)
          {
            ServiceRateExt serviceRateExt = (ServiceRateExt)contractServiceRateExtList.get(j);
            serviceVersionExt.getContractServiceRateExtListBuffer().add(serviceRateExt);

            ServiceRate serviceRate = serviceRateExt.getServiceRate();

            if(serviceRate.getRateId() == null)
            {
              serviceRate.getServiceRateId().setServiceLevel("*");
            }

            if(!"*".equals(serviceRate.getServiceRateId().getServiceLevel()))
            {
              ServiceRate existedServiceRateForAllLevel = selectServiceRateByServiceRateWithInputLevel(
                serviceRates.getContractSpecificServiceRateList(),
                serviceRate,
                "*"
              );

              ServiceRateExt newServiceRateExt = new ServiceRateExt();
              ServiceRate newServiceRate = new ServiceRate();
              newServiceRateExt.setServiceRate(newServiceRate);
              EntityUtil.copyServiceRate(newServiceRate, serviceRate);

              if(existedServiceRateForAllLevel != null)
              {
                newServiceRateExt.setOldServiceRate(existedServiceRateForAllLevel);
              }

              newServiceRateExt.getServiceRate().getServiceRateId().setServiceLevel("*");
              serviceVersionExt.getContractServiceRateExtListBuffer().add(newServiceRateExt);
            }
          }
        }
      }
    }
  }

  public static void saveServiceRates(ServiceRates serviceRates)
  {
    ServiceRateService serviceRateService = (ServiceRateService)ServiceLocator.getInstance().getBean("serviceRateService");

    packServiceRatesForSave(serviceRates);

    applyLevels(serviceRates);

    serviceRateService.saveServiceRates(serviceRates);
  }

  public static void saveVisibilityForHighLevelServiceExt(Contract contract, HighLevelServiceExt highLevelServiceExt)
  {
    if((contract == null) || (highLevelServiceExt == null)) return;

    ServiceRateService serviceRateService = (ServiceRateService)ServiceLocator.getInstance().getBean("serviceRateService");

    serviceRateService.saveVisibilityForServiceExtList(
      contract,
      highLevelServiceExt.getServiceExtList(),
      highLevelServiceExt.getHide()
    );
  }

  public static List createHighLevelServiceExtList(
    List highLevelServiceList,
    Set oldHighLevelServiceSet
  )
  {
    List results = new ArrayList();

    if(highLevelServiceList == null) return results;

    for(int i=0; i<highLevelServiceList.size(); i++)
    {
      HighLevelService highLevelService = (HighLevelService)highLevelServiceList.get(i);

      HighLevelServiceExt highLevelServiceExt = new HighLevelServiceExt();
      highLevelServiceExt.setHighLevelService(highLevelService);

      if((oldHighLevelServiceSet != null) && oldHighLevelServiceSet.contains(highLevelService))
      {
        highLevelServiceExt.setViewOnly(true);
      }

      results.add(highLevelServiceExt);
    }

    return results;
  }

  public static void normalizeServiceRateBeginDateAndEndDate(ServiceRates serviceRates)
  {
    if(serviceRates == null) return;

    List serviceLevelList = serviceRates.getServiceLevelList();
    for(int i=0; i<serviceLevelList.size(); i++)
    {
      ServiceLevel tmpServiceLevel = (ServiceLevel)serviceLevelList.get(i);

      List serviceVersionExtList = tmpServiceLevel.getServiceVersionExtList();
      if(serviceVersionExtList != null)
      {
        for(int j=0; j<serviceVersionExtList.size(); j++)
        {
          ServiceVersionExt serviceVersionExt = (ServiceVersionExt)serviceVersionExtList.get(j);

          List contractServiceRateExtList = serviceVersionExt.getContractServiceRateExtList();
          if(contractServiceRateExtList != null)
          {
            for(int k=0; k<contractServiceRateExtList.size(); k++)
            {
              ServiceRateExt serviceRateExt = (ServiceRateExt)contractServiceRateExtList.get(k);

              normalizeServiceRateBeginAndEndDateByServiceVersion(
                serviceRateExt,
                serviceVersionExt
              );
            }
          }
        }
      }
    }
  }

  public static void normalizeServiceRateBeginAndEndDateByServiceVersion(
    ServiceRateExt serviceRateExt,
    ServiceVersionExt serviceVersionExt
  )
  {
    if((serviceRateExt == null) || (serviceVersionExt == null)) return;

    Calendar srBeginCal = DateUtil.getCalendarForDateWithDatePartOnly(serviceRateExt.getServiceRate().getServiceRateId().getBeginDate());
    Calendar srEndCal = DateUtil.getCalendarForDateWithDatePartOnly(serviceRateExt.getServiceRate().getEndDate());
    Calendar svBeginCal = DateUtil.getCalendarForDateWithDatePartOnly(serviceVersionExt.getServiceVersion().getServiceVersionId().getBeginDate());
    Calendar svEndCal = DateUtil.getCalendarForDateWithDatePartOnly(serviceVersionExt.getServiceVersion().getEndDate());

    if(srBeginCal.compareTo(svBeginCal) < 0)
    {
      serviceRateExt.getServiceRate().getServiceRateId().setBeginDate(
        serviceVersionExt.getServiceVersion().getServiceVersionId().getBeginDate()
      );
    }

    if(srEndCal.compareTo(svEndCal) > 0)
    {
      serviceRateExt.getServiceRate().setEndDate(
        serviceVersionExt.getServiceVersion().getEndDate()
      );
    }
  }

  public static void applyDatesForAllRates(ServiceRates serviceRates)
  {
    if(serviceRates == null) return;

    ServiceLevel serviceLevel = serviceRates.getServiceLevel();
    if(serviceLevel == null) return;

    int versionIndex = serviceLevel.getActiveServiceVersionIndex();

    ServiceVersionExt serviceVersionExt = null;
    List serviceVersionExtList = serviceLevel.getServiceVersionExtList();
    if((versionIndex >= 0) && (versionIndex < serviceVersionExtList.size()))
    {
      serviceVersionExt = (ServiceVersionExt)serviceVersionExtList.get(versionIndex);
    }

    if(serviceVersionExt == null) return;

    List contractServiceRateExtList = serviceVersionExt.getContractServiceRateExtList();
    if(contractServiceRateExtList == null) return;

    Date newBeginDate = serviceVersionExt.getAllBeginDate();
    Date newEndDate = serviceVersionExt.getAllEndDate();

    for(int i=0; i<contractServiceRateExtList.size(); i++)
    {
      ServiceRateExt serviceRateExt = (ServiceRateExt)contractServiceRateExtList.get(i);
      if(serviceRateExt.getChecked())
      {
        if(newBeginDate != null)
        {
          serviceRateExt.getServiceRate().getServiceRateId().setBeginDate(newBeginDate);
        }

        if(newEndDate != null)
        {
          serviceRateExt.getServiceRate().setEndDate(newEndDate);
        }
      }
    }
  }

  public static void deleteSelectedForRates(ServiceRates serviceRates)
  {
    if(serviceRates == null) return;

    ServiceLevel serviceLevel = serviceRates.getServiceLevel();
    if(serviceLevel == null) return;

    int versionIndex = serviceLevel.getActiveServiceVersionIndex();

    ServiceVersionExt serviceVersionExt = null;
    List serviceVersionExtList = serviceLevel.getServiceVersionExtList();
    if((versionIndex >= 0) && (versionIndex < serviceVersionExtList.size()))
    {
      serviceVersionExt = (ServiceVersionExt)serviceVersionExtList.get(versionIndex);
    }

    if(serviceVersionExt == null) return;

    List contractServiceRateExtList = serviceVersionExt.getContractServiceRateExtList();
    if(contractServiceRateExtList == null) return;

    List deletedList = new ArrayList();

    for(int i=0; i<contractServiceRateExtList.size(); i++)
    {
      ServiceRateExt serviceRateExt = (ServiceRateExt)contractServiceRateExtList.get(i);
      if(serviceRateExt.getChecked())
      {
        deletedList.add(serviceRateExt);
      }
    }

    serviceVersionExt.getDeletedContractServiceRateExtList().addAll(deletedList);
    contractServiceRateExtList.removeAll(deletedList);
  }

  public static Date getEarliestBeginDateOfContract(String contractCode)
  {
    ContractService contractService = (ContractService)ServiceLocator.getInstance().getBean("contractService");

    return contractService.getEarliestBeginDateOfContract(contractCode);
  }

  public static List excludeServiceRateList(List serviceRateList1, List serviceRateList2)
  {
    List results = new ArrayList();

    if(serviceRateList1 == null) return results;

    if(serviceRateList2 == null)
    {
      results.addAll(serviceRateList1);
      return results;
    }

    for(int i=0; i<serviceRateList1.size(); i++)
    {
      ServiceRate serviceRate1 = (ServiceRate)serviceRateList1.get(i);

      boolean found = false;
      for(int j=0; j<serviceRateList2.size(); j++)
      {
        ServiceRate serviceRate2 = (ServiceRate)serviceRateList2.get(j);

        boolean matched = isServiceRateTheSame(serviceRate1, serviceRate2);
        if(matched)
        {
          found = true;
          break;
        }
      }

      if(!found) results.add(serviceRate1);
    }

    return results;
  }

  public static boolean isServiceRateTheSame(
    ServiceRate serviceRate1,
    ServiceRate serviceRate2
  )
  {
    if((serviceRate1 == null) || (serviceRate2 == null)) return false;

    if(serviceRate1.getServiceRateId().getExpressionId().equals(serviceRate2.getServiceRateId().getExpressionId())
      && serviceRate1.getServiceRateId().getLocation().equals(serviceRate2.getServiceRateId().getLocation())
      && serviceRate1.getServiceRateId().getGroupId().equals(serviceRate2.getServiceRateId().getGroupId())
      && serviceRate1.getServiceRateId().getVesselType().equals(serviceRate2.getServiceRateId().getVesselType())
      && serviceRate1.getServiceRateId().getWithInspection().equals(serviceRate2.getServiceRateId().getWithInspection())
      && serviceRate1.getServiceRateId().getIntData2().equals(serviceRate2.getServiceRateId().getIntData2())
      && serviceRate1.getServiceRateId().getFloatData2().equals(serviceRate2.getServiceRateId().getFloatData2())
    )
    {
      return true;
    }

    return false;
  }
}

