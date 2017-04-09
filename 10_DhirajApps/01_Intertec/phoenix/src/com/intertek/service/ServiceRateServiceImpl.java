package com.intertek.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.StringTokenizer;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.intertek.calculator.ContractExpressionExt;
import com.intertek.calculator.ControlExt;
import com.intertek.domain.EditServiceRate;
import com.intertek.domain.ServiceLevel;
import com.intertek.domain.ServiceRateExt;
import com.intertek.domain.ServiceRates;
import com.intertek.domain.ServiceExt;
import com.intertek.domain.ServiceVersionExt;
import com.intertek.domain.RBExt;
import com.intertek.entity.CfgContract;
import com.intertek.entity.Contract;
import com.intertek.entity.ContractExpression;
import com.intertek.entity.Control;
import com.intertek.entity.HighLevelService;
import com.intertek.entity.Service;
import com.intertek.entity.ServiceRate;
import com.intertek.entity.ServiceVersion;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.util.Constants;
import com.intertek.util.ContractServiceUtil;
import com.intertek.util.EntityUtil;

public class ServiceRateServiceImpl extends BaseService implements ServiceRateService
{
  private static Log log = LogFactory.getLog(ServiceRateServiceImpl.class);

  public List getEditHighLevelServiceIdListByContractCodeAndWorkingPb(String contractCode, String workingPb)
  {
    List results = new ArrayList();

    List idList = getDao().find(
      "select distinct o.highLevelServiceId from Service o where o.serviceId.contractId = ?",
      new Object[] { contractCode }
    );

    results.addAll(idList);

    if((workingPb != null) && !"".equals(workingPb.trim()))
    {
      idList = getDao().find(
        "select distinct o.highLevelServiceId from Service o where o.serviceId.contractId in (?, ?) and o.serviceId.serviceName in (select o1.contractExpressionId.serviceName from ContractExpression o1 where o1.contractExpressionId.contractId = ?)",
        new Object[] { workingPb, Constants.MASTER, contractCode }
      );

      for(int i=0; i<idList.size(); i++)
      {
        String id = (String)idList.get(i);
        if(!results.contains(id)) results.add(id);
      }
    }

    return results;
  }

  public List getEditHighLevelServiceListByIds(List idList)
  {
    if((idList == null) || (idList.size() == 0)) return new ArrayList();

    Object[] params = new Object[idList.size()];
    StringBuffer sb = new StringBuffer();
    sb.append("select distinct o from HighLevelService o where o.highLevelServiceId in (");
    for(int i=0; i<idList.size(); i++)
    {
      String id = (String)idList.get(i);

      params[i] = id;
      if(i > 0) sb.append(", ");
      sb.append("?");
    }
    sb.append(")");

    return getDao().find(sb.toString(), params);
  }

  public List getAllHighLevelServiceList()
  {
    return getDao().find(
      "from HighLevelService",
      null
    );
  }

  public List getServiceList(
    HighLevelService highLevelService,
    Contract contract
  )
  {
    if((highLevelService == null) || (contract == null)) return new ArrayList();

    return getDao().find(
      "from Service o where o.highLevelServiceId = ? and o.serviceId.contractId in (?, ?) and o.serviceId.beginDate <= ? and o.endDate >= ?",
      new Object[] {highLevelService.getHighLevelServiceId(), Constants.MASTER, contract.getWorkingPB(), contract.getMasterListDate(), contract.getMasterListDate()}
    );
  }

  public List getServiceListByContractCodeAndServiceNameListAndParentServiceIdListAndEffectiveDate(
    String contractCode,
    List serviceNameList,
    List parentServiceIdList,
    Date date
  )
  {
    if((contractCode == null) || (serviceNameList == null) || (parentServiceIdList == null)) return new ArrayList();
    if((serviceNameList.size() == 0) || (parentServiceIdList.size() == 0)) return new ArrayList();

    Object[] params = new Object[serviceNameList.size() + parentServiceIdList.size() + 3];
    params[0] = contractCode;
    params[1] = date;
    params[2] = date;

    StringBuilder sb = new StringBuilder();
    sb.append("from Service o where o.serviceId.contractId = ? ");
    sb.append("and o.serviceId.beginDate <= ? and o.endDate >= ? ");
    sb.append("and o.serviceId.serviceName in ( ");
    for(int i=0; i<serviceNameList.size(); i++)
    {
      params[i + 3] = serviceNameList.get(i);
      if(i > 0) sb.append(", ");

      sb.append("?");
    }
    sb.append(") ");

    sb.append("and o.serviceId.parentServiceId in ( ");
    for(int i=0; i<parentServiceIdList.size(); i++)
    {
      params[i + serviceNameList.size() + 3] = parentServiceIdList.get(i);
      if(i > 0) sb.append(", ");

      sb.append("?");
    }
    sb.append(") ");

    log.info("getServiceListByContractCodeAndServiceNameListAndParentServiceIdListAndEffectiveDate: " + sb.toString());
    log.info("getServiceListByContractCodeAndServiceNameListAndParentServiceIdListAndEffectiveDate: " + params);

    return getDao().find(sb.toString(), params);
  }

  public Service getServiceByContractCodeAndServiceNameAndParentServiceIdAndBeginDate(
    String contractCode,
    String serviceName,
    String parentServiceId,
    Date beginDate
  )
  {
    if((contractCode == null) || (serviceName == null) || (parentServiceId == null)) return null;

    List serviceList = getDao().find(
      "from Service o where o.serviceId.contractId = ? and o.serviceId.serviceName = ? and o.serviceId.parentServiceId = ? and o.serviceId.beginDate = ?",
      new Object[] {contractCode, serviceName, parentServiceId, beginDate}
    );

    if(serviceList.size() > 0) return (Service)serviceList.get(0);

    return null;
  }

  public List getContractExpressionListByServiceNameListAndContract(
    List serviceNameList,
    Contract contract
  )
  {
    if((contract == null) || (serviceNameList == null)) return new ArrayList();
    if(serviceNameList.size() == 0) return new ArrayList();

    Object[] params = new Object[serviceNameList.size() + 4];
    params[0] = Constants.MASTER;
    params[1] = contract.getWorkingPB();
    params[2] = contract.getMasterListDate();
    params[3] = contract.getMasterListDate();

    StringBuilder sb = new StringBuilder();
    sb.append("from ContractExpression o where o.contractExpressionId.contractId in (?, ?) ");
    sb.append("and o.contractExpressionId.beginDate <= ? and o.endDate >= ? ");
    sb.append("and o.contractExpressionId.serviceName in (");

    for(int i=0; i<serviceNameList.size(); i++)
    {
      params[i + 4] = serviceNameList.get(i);
      if(i > 0) sb.append(", ");

      sb.append("?");
    }
    sb.append(")");

    return getDao().find(sb.toString(), params);
  }

  public List getExpressionIdServiceNameControlRbKeyListByServiceNameListAndExpressionIdListAndContract(
    List serviceNameList,
    List expressionIdList,
    Contract contract
  )
  {
    if((serviceNameList == null) || (expressionIdList == null) || (contract == null)) return new ArrayList();
    if((serviceNameList.size() == 0) || (expressionIdList.size() == 0)) return new ArrayList();

    Object[] params = new Object[serviceNameList.size() + expressionIdList.size() + 4];
    params[0] = Constants.MASTER;
    params[1] = contract.getWorkingPB();
    params[2] = Constants.MASTER;
    params[3] = contract.getWorkingPB();

    StringBuilder sb = new StringBuilder();
    sb.append("SELECT distinct cm.EXPRESSION_ID, cm.ITSC_SERVICE, c.RB_KEY ");
    sb.append("FROM PS_ITS_CONTROL_MAP cm, PS_ITSC_CONTROL c ");
    sb.append("WHERE c.CFG_CONTRACT_ID in (?, ?) ");
    sb.append("AND c.ITSC_SERVICE = cm.ITSC_SERVICE ");
    sb.append("AND c.OBJECT_NAME = cm.OBJECT_NAME ");
    sb.append("AND cm.CFG_CONTRACT_ID in (?, ?) ");
    sb.append("AND cm.ITSC_SERVICE in (");
    for(int i=0; i<serviceNameList.size(); i++)
    {
      params[i + 4] = serviceNameList.get(i);
      if(i > 0) sb.append(", ");

      sb.append("?");
    }
    sb.append(") ");

    sb.append("AND cm.EXPRESSION_ID in (");
    for(int i=0; i<expressionIdList.size(); i++)
    {
      params[i + serviceNameList.size() + 4] = expressionIdList.get(i);
      if(i > 0) sb.append(", ");

      sb.append("?");
    }
    sb.append(")");

    return getDao().findBySQL(sb.toString(),params);
  }

  public List getControlRbKeyListByServiceNameAndExpressionIdAndContract(
    String serviceName,
    String expressionId,
    Contract contract
  )
  {
    if((serviceName == null) || (expressionId == null) || (contract == null)) return new ArrayList();

    return getDao().findByNamedSqlQuery(
      "getControlRbKeyListByWorkingPbIdAndMasterAndServiceNameAndExpressionId",
      new Object[] {Constants.MASTER, contract.getWorkingPB(), Constants.MASTER, contract.getWorkingPB(), serviceName, expressionId}
    );
  }

  public List getContractExpressionListByContractCodeAndSerivceNameListAndExpressionIdList(
    String contractCode,
    List serviceNameList,
    List expressionIdList,
    Date date
  )
  {
    if((contractCode == null) || (serviceNameList == null) || (expressionIdList == null) || (date == null)) return new ArrayList();
    if((serviceNameList.size() == 0) || (expressionIdList.size() == 0)) return new ArrayList();

    Object[] params = new Object[serviceNameList.size() + expressionIdList.size() + 3];
    params[0] = contractCode;
    params[1] = date;
    params[2] = date;

    StringBuilder sb = new StringBuilder();
    sb.append("from ContractExpression o where o.contractExpressionId.contractId = ? ");
    sb.append("and o.contractExpressionId.beginDate <= ? and o.endDate >= ?");
    sb.append("and o.contractExpressionId.serviceName in (");
    for(int i=0; i<serviceNameList.size(); i++)
    {
      params[i + 3] = serviceNameList.get(i);
      if(i > 0) sb.append(", ");

      sb.append("?");
    }
    sb.append(") ");
    sb.append("and o.contractExpressionId.expressionId in (");
    for(int i=0; i<expressionIdList.size(); i++)
    {
      params[i + serviceNameList.size() + 3] = expressionIdList.get(i);
      if(i > 0) sb.append(", ");

      sb.append("?");
    }
    sb.append(")");

    return getDao().find(sb.toString(), params);
  }

  public List getContractExpressionListByContractCodeAndSerivceNameAndExpressionIdAndBeginDate(
    String contractCode,
    String serviceName,
    String expressionId,
    Date beginDate
  )
  {
    if((contractCode == null) || (serviceName == null) || (expressionId == null)) return new ArrayList();

    return getDao().find(
      "from ContractExpression o where o.contractExpressionId.contractId = ? and o.contractExpressionId.serviceName = ? and o.contractExpressionId.expressionId = ? and o.contractExpressionId.beginDate = ?",
      new Object[] {contractCode, serviceName, expressionId, beginDate}
    );
  }

  public List getServiceRateExpressionIdListByContractIdAndSerivceNameAndExpressionId(
    String contractCode,
    String serviceName,
    String expressionId
  )
  {
    if((contractCode == null) || (serviceName == null) || (expressionId == null)) return new ArrayList();

    return getDao().findByNamedSqlQuery(
      "getServiceRateExpressionIdListByContractIdAndSerivceNameAndExpressionId",
      new Object[] {contractCode, serviceName, expressionId}
    );
  }

  public List getServiceLevelListForServiceRateExpressionIds(
    Collection serviceRateExpressionIds,
    Contract contract
  )
  {
    List results = new ArrayList();

    if((serviceRateExpressionIds == null) || (contract == null)) return results;

    Object[] params = new Object[serviceRateExpressionIds.size() + 6];

    StringBuffer sb = new StringBuffer();
    sb.append("SELECT DISTINCT cx.CFG_CONTRACT_ID, cx.ITSC_SERVICE, cx.EXPRESSION_ID, s.ITS_SERVICE_LEVEL ");
    sb.append("FROM PS_ITS_CX_RATE_VW cx, PS_ITSC_SERVICE s ");
    sb.append("WHERE cx.ITS_SRVC_RT_EXP_ID in (");
    Iterator it = serviceRateExpressionIds.iterator();
    int count = 0;
    while(it.hasNext())
    {
      String id = (String)it.next();
      params[count] = id;
      if(count > 0) sb.append(", ");
      sb.append("?");

      count ++;
    }
    sb.append(") AND cx.CFG_CONTRACT_ID in (?, ?) ");
    sb.append("AND cx.BEGIN_DATE <= ? ");
    sb.append("AND cx.END_DATE >= ? ");
    sb.append("AND cx.ITSC_SERVICE = s.ITSC_SERVICE ");
    sb.append("AND s.CFG_CONTRACT_ID in (?, ?) ");
    sb.append("ORDER BY ITSC_SERVICE, EXPRESSION_ID");

    params[count ++] = contract.getWorkingPB();
    params[count ++] = Constants.MASTER;
    params[count ++] = contract.getMasterListDate();
    params[count ++] = contract.getMasterListDate();
    params[count ++] = contract.getWorkingPB();
    params[count ++] = Constants.MASTER;

    List list = getDao().findBySQL(
      sb.toString(),
      params
    );

    for(int i=0; i<list.size(); i++)
    {
      Object[] objects = (Object[])list.get(i);

      if(objects.length >= 4)
      {
        ServiceLevel serviceLevel = new ServiceLevel();
        serviceLevel.setCfgContractId((String)objects[0]);
        serviceLevel.setServiceName((String)objects[1]);
        serviceLevel.setExpressionId((String)objects[2]);
        serviceLevel.setServiceLevel((String)objects[3]);

        results.add(serviceLevel);
      }
    }

    return results;
  }

  public List getServiceVersionListByContractCodeAndServiceNameAndExpressionId(
    String contractCode,
    String serviceName,
    String expressionId
  )
  {
    return getDao().find(
      "from ServiceVersion o where o.serviceVersionId.contractId = ? and o.serviceVersionId.serviceName = ? and o.serviceVersionId.expressionId = ? order by o.serviceVersionId.beginDate",
      new Object[] { contractCode, serviceName, expressionId }
    );
  }

  public List getPriceBookServiceRateListForServiceRateExpressionIds(
    Collection serviceRateExpressionIds,
    Contract contract
  )
  {
    if((serviceRateExpressionIds == null) || (contract == null)) return new ArrayList();

    Object[] params = new Object[serviceRateExpressionIds.size() + 1];

    StringBuffer sb = new StringBuffer();
    sb.append("from ServiceRate o where o.serviceRateId.expressionId in (");
    Iterator it = serviceRateExpressionIds.iterator();
    int count = 0;
    while(it.hasNext())
    {
      String id = (String)it.next();
      params[count] = id;
      if(count > 0) sb.append(", ");
      sb.append("?");

      count ++;
    }
    sb.append(") and o.serviceRateId.contractId = ?");

    params[count ++] = contract.getWorkingPB();

    return getDao().find(
      sb.toString(),
      params
    );
  }

  public List getContractSpecificServiceRateListForServiceRateExpressionIds(
    Collection serviceRateExpressionIds,
    Contract contract
  )
  {
    if((serviceRateExpressionIds == null) || (contract == null)) return new ArrayList();

    Object[] params = new Object[serviceRateExpressionIds.size() + 1];

    StringBuffer sb = new StringBuffer();
    sb.append("from ServiceRate o where o.serviceRateId.expressionId in (");
    Iterator it = serviceRateExpressionIds.iterator();
    int count = 0;
    while(it.hasNext())
    {
      String id = (String)it.next();
      params[count] = id;
      if(count > 0) sb.append(", ");
      sb.append("?");

      count ++;
    }
    sb.append(") and o.serviceRateId.contractId = ?");

    params[count ++] = contract.getContractCode();

    return getDao().find(
      sb.toString(),
      params
    );
  }

  public void saveServiceRates(ServiceRates serviceRates)
  {
    if(serviceRates == null) return;

    ServiceLevel serviceLevel = serviceRates.getServiceLevel();
    if(serviceLevel == null) return;

    EditServiceRate editServiceRate = serviceRates.getEditServiceRate();
    if(editServiceRate == null) return;

    SequenceNumberService sequenceNumberService = (SequenceNumberService)ServiceLocator.getInstance().getBean("sequenceNumberService");

    clearServiceRates(serviceRates);

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
          ServiceVersion serviceVersion = serviceVersionExt.getServiceVersion();

          getDao().save(serviceVersion);

          List ceExtListForContract = serviceVersionExt.getCeExtListForContractBuffer();
          if(ceExtListForContract != null)
          {
            for(int k=0; k<ceExtListForContract.size(); k++)
            {
              ContractExpressionExt ceExt = (ContractExpressionExt)ceExtListForContract.get(k);

              getDao().save(ceExt.getContractExpression());
            }
          }

          ServiceExt serviceExtForContract = serviceVersionExt.getServiceExtForContract();
          if(serviceExtForContract != null)
          {
            Service serviceForContract = serviceExtForContract.getService();

            getDao().save(serviceForContract);
          }

          List contractControlExtList = serviceVersionExt.getContractControlExtListBuffer();
          if(contractControlExtList != null)
          {
            for(int k=0; k<contractControlExtList.size(); k++)
            {
              ControlExt controlExt = (ControlExt)contractControlExtList.get(k);

              if(controlExt.getDeleted()) continue;

              getDao().save(controlExt.getControl());
            }
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

          List contractServiceRateExtList = serviceVersionExt.getContractServiceRateExtListBuffer();
          if(contractServiceRateExtList != null)
          {
            for(int k=0; k<contractServiceRateExtList.size(); k++)
            {
              ServiceRateExt serviceRateExt = (ServiceRateExt)contractServiceRateExtList.get(k);

              ServiceRate serviceRate = serviceRateExt.getServiceRate();
              Integer rateId = serviceRate.getRateId();
              if(rateId == null)
              {
                Long newNumber = sequenceNumberService.getSequenceNumber(Constants.Service_Rate_ID_Seq);
                rateId = new Integer(newNumber.intValue());
                serviceRate.setRateId(rateId);
              }

              getDao().save(serviceRate);
            }
          }
        }
      }
    }

    List questionRbExtList = serviceLevel.getQuestionRbExtList();
    if(questionRbExtList != null)
    {
      RbService rbService = (RbService)ServiceLocator.getInstance().getBean("rbService");
      for(int i=0; i<questionRbExtList.size(); i++)
      {
        RBExt rbExt = (RBExt)questionRbExtList.get(i);
        rbService.saveRbExt(rbExt);
      }
    }
  }

  private void clearServiceRates(ServiceRates serviceRates)
  {
    if(serviceRates == null) return;

    ServiceLevel serviceLevel = serviceRates.getServiceLevel();
    if(serviceLevel == null) return;

    EditServiceRate editServiceRate = serviceRates.getEditServiceRate();
    if(editServiceRate == null) return;

    List serviceLevelList = serviceRates.getServiceLevelList();
    for(int i=0; i<serviceLevelList.size(); i++)
    {
      ServiceLevel tmpServiceLevel = (ServiceLevel)serviceLevelList.get(i);

      removeServiceVersionsByContractIdAndServiceNameAndExpressionId(
        editServiceRate.getContract().getContractCode(),
        tmpServiceLevel.getServiceName(),
        tmpServiceLevel.getExpressionId()
      );
      List serviceVersionExtList = tmpServiceLevel.getServiceVersionExtList();
      if(serviceVersionExtList != null)
      {
        for(int j=0; j<serviceVersionExtList.size(); j++)
        {
          ServiceVersionExt serviceVersionExt = (ServiceVersionExt)serviceVersionExtList.get(j);
          ServiceVersion serviceVersion = serviceVersionExt.getServiceVersion();

          ServiceExt serviceExtForContract = serviceVersionExt.getServiceExtForContract();
          if(serviceExtForContract != null)
          {
            Service oldServiceForContract = serviceExtForContract.getServiceForContract();
            if(oldServiceForContract != null)
            {
              getDao().remove(oldServiceForContract);
            }
          }

          List ceExtListForContract = serviceVersionExt.getCeExtListForContractBuffer();
          if(ceExtListForContract != null)
          {
            for(int k=0; k<ceExtListForContract.size(); k++)
            {
              ContractExpressionExt ceExt = (ContractExpressionExt)ceExtListForContract.get(k);
              ContractExpression oldCe = ceExt.getOldContractExpression();
              if(oldCe != null) getDao().remove(oldCe);
            }
          }

          List deletedCeExtListForContract = serviceVersionExt.getDeletedCeExtListForContract();
          if(deletedCeExtListForContract != null)
          {
            for(int k=0; k<deletedCeExtListForContract.size(); k++)
            {
              ContractExpressionExt ceExt = (ContractExpressionExt)deletedCeExtListForContract.get(k);
              ContractExpression oldCe = ceExt.getOldContractExpression();
              if(oldCe != null) getDao().remove(oldCe);
            }
          }

          List contractControlExtList = serviceVersionExt.getContractControlExtListBuffer();
          if(contractControlExtList != null)
          {
            for(int k=0; k<contractControlExtList.size(); k++)
            {
              ControlExt controlExt = (ControlExt)contractControlExtList.get(k);
              Control oldControl = controlExt.getOldControl();
              if(oldControl != null) getDao().remove(oldControl);
            }
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
            List deletedContractServiceRateExtList = serviceVersionExt.getDeletedContractServiceRateExtList();
            if(deletedContractServiceRateExtList != null)
            {
              for(int k=0; k<deletedContractServiceRateExtList.size(); k++)
              {
                ServiceRateExt serviceRateExt = (ServiceRateExt)deletedContractServiceRateExtList.get(k);
                ServiceRate oldServiceRate = serviceRateExt.getOldServiceRate();
                if(oldServiceRate != null)
                {
                  if(tmpServiceLevel.getServiceLevel().equals(oldServiceRate.getServiceRateId().getServiceLevel()))
                  {
                    getDao().remove(oldServiceRate);
                  }
                }
              }
            }

            List contractServiceRateExtList = serviceVersionExt.getContractServiceRateExtList();
            if(contractServiceRateExtList != null)
            {
              for(int k=0; k<contractServiceRateExtList.size(); k++)
              {
                ServiceRateExt serviceRateExt = (ServiceRateExt)contractServiceRateExtList.get(k);
                ServiceRate oldServiceRate = serviceRateExt.getOldServiceRate();
                if(oldServiceRate != null)
                {
                  if(tmpServiceLevel.getServiceLevel().equals(oldServiceRate.getServiceRateId().getServiceLevel()))
                  {
                    getDao().remove(oldServiceRate);
                  }
                }
              }
            }

            continue;
          }


          List deletedContractServiceRateExtList = serviceVersionExt.getDeletedContractServiceRateExtList();
          if(deletedContractServiceRateExtList != null)
          {
            for(int k=0; k<deletedContractServiceRateExtList.size(); k++)
            {
              ServiceRateExt serviceRateExt = (ServiceRateExt)deletedContractServiceRateExtList.get(k);
              ServiceRate oldServiceRate = serviceRateExt.getOldServiceRate();
              if(oldServiceRate != null) getDao().remove(oldServiceRate);
            }
          }

          List contractServiceRateExtList = serviceVersionExt.getContractServiceRateExtListBuffer();
          if(contractServiceRateExtList != null)
          {
            for(int k=0; k<contractServiceRateExtList.size(); k++)
            {
              ServiceRateExt serviceRateExt = (ServiceRateExt)contractServiceRateExtList.get(k);
              ServiceRate oldServiceRate = serviceRateExt.getOldServiceRate();
              if(oldServiceRate != null) getDao().remove(oldServiceRate);
            }
          }
        }
      }
    }
  }

  private void removeServiceVersionsByContractIdAndServiceNameAndExpressionId(
    String contractId,
    String serviceName,
    String expressionId
  )
  {
    if((contractId == null) || (serviceName == null) || (expressionId == null)) return;

    getDao().bulkUpdate(
      "delete from ServiceVersion o where o.serviceVersionId.contractId = ? and o.serviceVersionId.serviceName = ? and o.serviceVersionId.expressionId = ?",
      new Object[] { contractId, serviceName, expressionId }
    );
  }

  private void removeContractExpressionsByContractIdAndServiceNameAndExpressionId(
    String contractId,
    String serviceName,
    String expressionId
  )
  {
    if((contractId == null) || (serviceName == null) || (expressionId == null)) return;

    getDao().bulkUpdate(
      "delete from ContractExpression o where o.contractExpressionId.contractId = ? and o.contractExpressionId.serviceName = ? and o.contractExpressionId.expressionId = ?",
      new Object[] { contractId, serviceName, expressionId }
    );
  }

  private void removeServiceRatesByContractIdAndExpressionIdAndServiceLevel(
    String contractId,
    String expressionId,
    String serviceLevel
  )
  {
    if((contractId == null) || (serviceLevel == null) || (expressionId == null)) return;

    getDao().bulkUpdate(
      "delete from ServiceRate o where o.serviceRateId.contractId = ? and o.serviceRateId.expressionId = ? and o.serviceRateId.serviceLevel = ?",
      new Object[] { contractId, expressionId, serviceLevel }
    );
  }

  public List getPbControlListByServiceNameAndExpressionIdAndContract(
    String serviceName,
    String expressionId,
    Contract contract
  )
  {
    List results = new ArrayList();
    if((serviceName == null) || (expressionId == null) || (contract == null)) return results;

    List list = getDao().findByNamedSqlQuery(
      "getPbControlListByWorkingPbIdAndMasterAndServiceNameAndExpressionId",
      new Object[] {Constants.MASTER, contract.getWorkingPB(), Constants.MASTER, contract.getWorkingPB(), serviceName, expressionId}
    );

    for(int i=0; i<list.size(); i++)
    {
      Object[] objects = (Object[])list.get(i);

      Control control = ContractServiceUtil.createControlFromObjects(objects);
      if(control != null) results.add(control);
    }

    return results;
  }

  public List getContractControlListByServiceNameAndExpressionIdAndContractAndBeginDate(
    String serviceName,
    String expressionId,
    Contract contract,
    Date beginDate
  )
  {
    List results = new ArrayList();
    if((serviceName == null) || (expressionId == null) || (contract == null)) return results;

    List list = getDao().findByNamedSqlQuery(
      "getContractControlListByWorkingPbIdAndMasterAndServiceNameAndExpressionIdAndBeginDate",
      new Object[] {contract.getContractCode(), Constants.MASTER, contract.getWorkingPB(), serviceName, expressionId, beginDate}
    );

    for(int i=0; i<list.size(); i++)
    {
      Object[] objects = (Object[])list.get(i);

      Control control = ContractServiceUtil.createControlFromObjects(objects);
      if(control != null) results.add(control);
    }

    return results;
  }

  public void saveControlList(List controlList)
  {
    if(controlList == null) return;

    for(int i=0; i<controlList.size(); i++)
    {
      Control control = (Control)controlList.get(i);

      removeServiceRatesByContractIdAndServiceNameAndObjectName(
        control.getControlId().getContractId(),
        control.getControlId().getServiceName(),
        control.getControlId().getObjectName()
      );
    }

    for(int i=0; i<controlList.size(); i++)
    {
      Control control = (Control)controlList.get(i);

      getDao().save(control);
    }
  }

  private void removeServiceRatesByContractIdAndServiceNameAndObjectName(
    String contractId,
    String serviceName,
    String objectName
  )
  {
    if((contractId == null) || (serviceName == null) || (objectName == null)) return;

    getDao().bulkUpdate(
      "delete from Control o where o.controlId.contractId = ? and o.controlId.serviceName = ? and o.controlId.objectName = ?",
      new Object[] { contractId, serviceName, objectName }
    );
  }

  public void saveVisibilityForServiceExtList(Contract contract, List serviceExtList, boolean hideAll)
  {
    if((contract == null) || (serviceExtList == null)) return;

    for(int i=0; i<serviceExtList.size(); i++)
    {
      ServiceExt serviceExt = (ServiceExt)serviceExtList.get(i);

      saveVisibilityForServiceExt(contract, serviceExt, hideAll);
    }
  }

  public void saveVisibilityForServiceExt(Contract contract, ServiceExt serviceExt, boolean hideAll)
  {
    if((contract == null) || (serviceExt == null)) return;

    if(serviceExt.getHide() || hideAll)
    {
      Service serviceForContract = serviceExt.getServiceForContract();
      if(serviceForContract != null)
      {
        serviceForContract.setVisibility(Constants.HIDE);
      }
      else
      {
        serviceForContract = new Service();
        EntityUtil.copyService(serviceForContract, serviceExt.getService());
        serviceForContract.getServiceId().setContractId(contract.getContractCode());
        serviceForContract.setVisibility(Constants.HIDE);
      }
      getDao().save(serviceForContract);
    }
    else
    {
      Service serviceForContract = serviceExt.getServiceForContract();
      if(serviceForContract != null)
      {
        if(!Constants.MASTER.equals(serviceExt.getService().getServiceId().getContractId()))
        {
          getDao().remove(serviceForContract);
        }
        else
        {
          serviceForContract.setVisibility(Constants.ADDL);
          getDao().save(serviceForContract);
        }
      }
    }
  }

  public List searchHighLevelServices(
    String serviceName,
    boolean includeMasterListServices,
    Pagination pagination,
    String sortFlag
  )
  {
    StringBuffer sb = new StringBuffer();
    List params = new ArrayList();

    boolean hasWhere = false;

    if((serviceName != null) && !"".equals(serviceName.trim()))
    {
      String strNameSearch = '%' + serviceName.toUpperCase() + '%';

      sb.append(" where upper(o.highLevelServiceId) like ? or upper(o.serviceDescription) like ?");
      params.add(strNameSearch);
      params.add(strNameSearch);
      hasWhere = true;
    }

    if(!includeMasterListServices)
    {
      if(hasWhere) sb.append(" and ");
      else
      {
        hasWhere = true;
        sb.append(" where ");
      }

      sb.append(" o.priceBookInd = 'Y' ");
    }

    if(pagination != null)
    {
      if(pagination.getTotalRecord() > 0)
      {
        List counts = getDao().find("select count(*) from HighLevelService o " + sb.toString(), params.toArray());

        if(counts.size() > 0)
        {
          Number count = (Number)counts.get(0);
          pagination.setTotalRecord(count.intValue());
        }
      }
    }

    if((sortFlag != null) && !"".equals(sortFlag.trim()))
    {
      sb.append(" order by o.").append(sortFlag);
    }

    List results = null;

    if(pagination != null)
    {
      results = getDao().find("select o from HighLevelService o " + sb.toString(), params.toArray(), pagination);
      pagination.calculatePages();
    }
    else
    {
      results = getDao().find("select o from HighLevelService o " + sb.toString(), params.toArray());
    }

    return results;
  }
}

