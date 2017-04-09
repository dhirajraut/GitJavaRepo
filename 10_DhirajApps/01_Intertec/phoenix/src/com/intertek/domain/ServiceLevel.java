package com.intertek.domain;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.intertek.calculator.ContractExpressionExt;
import com.intertek.entity.Service;
import com.intertek.util.Constants;

public class ServiceLevel
{
  private String cfgContractId;
  private String serviceName;
  private String expressionId;
  private String serviceLevel;

  private List sharedLevelNameList;

  private ServiceExt serviceExt;
  private ContractExpressionExt ceExt;

  private List serviceVersionExtList;
  private List questionRbExtList;

  private int activeServiceVersionIndex;

  public void setCfgContractId(String cfgContractId)
  {
    this.cfgContractId = cfgContractId;
  }

  public String getCfgContractId()
  {
    return cfgContractId;
  }

  public void setServiceName(String serviceName)
  {
    this.serviceName = serviceName;
  }

  public String getServiceName()
  {
    return serviceName;
  }

  public void setExpressionId(String expressionId)
  {
    this.expressionId = expressionId;
  }

  public String getExpressionId()
  {
    return expressionId;
  }

  public void setServiceLevel(String serviceLevel)
  {
    this.serviceLevel = serviceLevel;
  }

  public String getServiceLevel()
  {
    return serviceLevel;
  }

  public void setSharedLevelNameList(List sharedLevelNameList)
  {
    this.sharedLevelNameList = sharedLevelNameList;
  }

  public List getSharedLevelNameList()
  {
    return sharedLevelNameList;
  }

  public void setServiceExt(ServiceExt serviceExt)
  {
    this.serviceExt = serviceExt;
  }

  public ServiceExt getServiceExt()
  {
    return serviceExt;
  }

  public void setContractExpressionExt(ContractExpressionExt ceExt)
  {
    this.ceExt = ceExt;
  }

  public ContractExpressionExt getContractExpressionExt()
  {
    return ceExt;
  }

  public void setServiceVersionExtList(List serviceVersionExtList)
  {
    this.serviceVersionExtList = serviceVersionExtList;
  }

  public List getServiceVersionExtList()
  {
    return serviceVersionExtList;
  }

  public void setQuestionRbExtList(List questionRbExtList)
  {
    this.questionRbExtList = questionRbExtList;
  }

  public List getQuestionRbExtList()
  {
    return questionRbExtList;
  }

  public void setActiveServiceVersionIndex(int activeServiceVersionIndex)
  {
    this.activeServiceVersionIndex = activeServiceVersionIndex;
  }

  public int getActiveServiceVersionIndex()
  {
    return activeServiceVersionIndex;
  }
}
