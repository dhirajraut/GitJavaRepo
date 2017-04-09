package com.intertek.service;

import java.util.Date;
import java.util.List;

import org.jdom.Document;

import com.intertek.entity.JobContractInvoice;
import com.intertek.entity.ControlSwitch;
import com.intertek.entity.JobOrder;
import com.intertek.entity.WebServiceEntity;
import com.intertek.entity.WebServiceEntityInbound;
import com.intertek.pagination.Pagination;
import com.intertek.tool.collector.DataCollector;
import com.intertek.webservice.outbound.WSOutboundContext;
import com.intertek.webservice.outbound.WebServiceSender;

public interface WSService
{
  void saveWebServiceEntity(WebServiceEntity entity);
  void saveWebServiceEntityInbound(WebServiceEntityInbound entity);
  WebServiceEntity getWebServiceEntityById(long id);
  WebServiceEntityInbound getWebServiceEntityInboundById(long id);

  void sendWebService(
    WSOutboundContext context,
    String name,
    Document doc,
    DataCollector dataCollector,
    WebServiceSender webServiceSender
  );

  List getJobContractInvoicesForOutboundInvoiceMessage(Pagination pagination, String[] businessUnits);
  List<JobContractInvoice> getJobContractInvoicesForAribaOutboundInvoiceMessage(long jobContractId);
  void updateJobContractInvoiceFlag(Long jobContractId, String sentToFinFlag);

  List getConsolidatedInvoicesForOutboundInvoiceMessage(Pagination pagination);
  void updateConsolidatedInvoiceFlag(
	String sentToFinFlag,
    String consolInvoiceNo,
    String custCode,
    String buName
  );

  List getCustomersForOutboundInvoiceMessage(Pagination pagination);
  void updateCustomerFlag(String custCode, String newFlag, String updateFlag);

  List getLimsCustomersForOutboundInvoiceMessage(Pagination pagination);
  void updateLimsCustomerFlag(String custCode, String updateLimsFlag);

  List getContactsForOutboundInvoiceMessage(Pagination pagination);
  void updateContactFlag(Long contactId, String newFlag, String updateFlag);

  List getLimsContactsForOutboundInvoiceMessage(Pagination pagination);
  void updateLimsContactFlag(Long contactId, String updateLimsFlag);

  List getBranchesForOutboundInvoiceMessage(Pagination pagination);
  void updateBranchFlag(String name, String newFlag, String updateFlag);

  JobOrder getJobOrderForOutboundInvoiceMessage(String jobNumber);

  List getJobIntegrationLog(String jobNumber);
  
  List getWebServiceLog(Date startDate, Date endDate);
  List getWebServiceInboundLog(Date startDate, Date endDate);
  
  List getWebServiceLog(String key);
  List getWebServiceInboundLog(String key);

  List<WebServiceEntity> getWebServiceDetailLog(String key, String type, Boolean status, Pagination pagination, String sortBy, boolean desc);
  List<WebServiceEntity> getWebServiceDetailInboundLog(String key, String type, Boolean status, Pagination pagination, String sortBy, boolean desc);

  List<WebServiceEntity> getWebServiceDetailLog(Date startDate, Date endDate, String type, Boolean statuses, Pagination pagination, String sortBy, boolean desc);
  List<WebServiceEntity> getWebServiceDetailInboundLog(Date startDate, Date endDate, String type, Boolean statuses, Pagination pagination, String sortBy, boolean desc);
  
  void updateOutboundWSControlSwitch(String controlSwitchName, String flag);
  ControlSwitch getOutboundWSControlSwitch(String controlSwitchName);
  List<ControlSwitch> getOutboundWSControlSwitches();
  List getAttachSysFileNameByJobContractId(long jobContractId);
  void updateJobContractAribaFlag(Long jobContractInvoiceId);
  
}

