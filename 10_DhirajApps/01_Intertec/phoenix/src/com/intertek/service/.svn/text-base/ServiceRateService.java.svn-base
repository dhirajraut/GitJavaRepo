package com.intertek.service;

import java.util.Collection;
import java.util.List;
import java.util.Date;

import com.intertek.domain.ContractSearch;
import com.intertek.domain.ServiceRates;
import com.intertek.domain.ServiceExt;
import com.intertek.entity.CfgContract;
import com.intertek.entity.Contract;
import com.intertek.entity.ContractAttach;
import com.intertek.entity.ContractCust;
import com.intertek.entity.ContractCustContact;
import com.intertek.entity.ContractExpression;
import com.intertek.entity.HighLevelService;
import com.intertek.entity.Service;
import com.intertek.pagination.Pagination;

public interface ServiceRateService
{
  List getEditHighLevelServiceIdListByContractCodeAndWorkingPb(String contractCode, String workingPb);
  List getEditHighLevelServiceListByIds(List idList);

  List getAllHighLevelServiceList();
  List getServiceList(
      HighLevelService highLevelService,
      Contract contract
  );

  List getServiceListByContractCodeAndServiceNameListAndParentServiceIdListAndEffectiveDate(
      String contractCode,
      List serviceNameList,
      List parentServiceIdList,
      Date date
  );

  Service getServiceByContractCodeAndServiceNameAndParentServiceIdAndBeginDate(
      String contractCode,
      String serviceName,
      String parentServiceId,
      Date beginDate
  );

  List getContractExpressionListByServiceNameListAndContract(
      List serviceNameList,
      Contract contract
  );

  List getExpressionIdServiceNameControlRbKeyListByServiceNameListAndExpressionIdListAndContract(
      List serviceNameList,
      List expressionIdList,
      Contract contract
  );

  List getControlRbKeyListByServiceNameAndExpressionIdAndContract(
      String serviceName,
      String expressionId,
      Contract contract
  );

  List getContractExpressionListByContractCodeAndSerivceNameListAndExpressionIdList(
    String contractCode,
    List serviceNameList,
    List expressionIdList,
    Date date
  );

  List getContractExpressionListByContractCodeAndSerivceNameAndExpressionIdAndBeginDate(
    String contractCode,
    String serviceName,
    String expressionId,
    Date beginDate
  );

  List getServiceRateExpressionIdListByContractIdAndSerivceNameAndExpressionId(
      String contractCode,
      String serviceName,
      String expressionId
  );

  List getServiceLevelListForServiceRateExpressionIds(
      Collection serviceRateExpressionIdList,
      Contract contract
  );

  List getServiceVersionListByContractCodeAndServiceNameAndExpressionId(
      String contractCode,
      String serviceName,
      String expressionId
  );

  List getPriceBookServiceRateListForServiceRateExpressionIds(
      Collection serviceRateExpressionIds,
      Contract contract
  );

  List getContractSpecificServiceRateListForServiceRateExpressionIds(
      Collection serviceRateExpressionIds,
      Contract contract
  );

  void saveServiceRates(ServiceRates serviceRates);

  List getPbControlListByServiceNameAndExpressionIdAndContract(
      String serviceName,
      String expressionId,
      Contract contract
  );

  List getContractControlListByServiceNameAndExpressionIdAndContractAndBeginDate(
      String serviceName,
      String expressionId,
      Contract contract,
      Date beginDate
  );

  void saveVisibilityForServiceExtList(Contract contract, List serviceExtList, boolean hideAll);

  void saveVisibilityForServiceExt(Contract contract, ServiceExt serviceExt, boolean hideAll);

  List searchHighLevelServices(
      String serviceName,
      boolean includeMasterListServices,
      Pagination pagination,
      String sortFlag
  );
}


