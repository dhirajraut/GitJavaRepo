package com.intertek.tool.collector.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jdom.Document;

import com.intertek.domain.NameValuePair;
import com.intertek.entity.JobContract;
import com.intertek.entity.JobContractInvoice;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.WSService;
import com.intertek.tool.oxm.OxmManager;
import com.intertek.util.Constants;

public class InvoiceDataCollector extends BaseDataCollector
{
  private String[] businessUnits;

  public void setBusinessUnits(String[] businessUnits)
  {
    this.businessUnits = businessUnits;
  }

  public String[] getBusinessUnits()
  {
    return businessUnits;
  }

  public List collect(Map dataMap)
  {
    List results = new ArrayList();

    try
    {
      WSService wsService = (WSService)ServiceLocator.getInstance().getBean("wsService");
      //OxmManager oxmManager = (OxmManager)ServiceLocator.getInstance().getBean("oxmManager");

      Pagination pagination = null;
      if((startPage > 0) && (numInPage > 0))
      {
        pagination = new Pagination(0, numInPage, startPage, 1);
      }

      List entities = wsService.getJobContractInvoicesForOutboundInvoiceMessage(pagination, businessUnits);
      if(entities.size() > 0)
      {
        for(int i=0; i<entities.size(); i++)
        {
          JobContractInvoice jobContractInvoice = (JobContractInvoice)entities.get(i);
          //cleanUnusedInvoiceData(jobContract);

          String name = jobContractInvoice.getId() + "";
          //Document doc = oxmManager.marshal(jobContractInvoice);

          NameValuePair nv = new NameValuePair(name, jobContractInvoice);
          results.add(nv);
        }
      }
    }
    catch(Throwable t)
    {
      t.printStackTrace();
    }

    return results;
  }

  public void updateSuccess(String id){
	  updateFlag(id, Constants.SENT);
  }
  
  public void updateFail(String id){
	  updateFlag(id, Constants.FAIL);
  }

  public boolean reset(String id){
	  try{
		  return updateFlag(id, Constants.NEW);
	  }
	  catch(Exception e){
		  return false;
	  }
  }

  public boolean updateFlag(String id, String sentToFinFlag)
  {
    if(id == null) return false;

    Long idLong = null;
    try
    {
      idLong = new Long(id);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

    if(idLong != null)
    {
      WSService wsService = (WSService)ServiceLocator.getInstance().getBean("wsService");
      wsService.updateJobContractInvoiceFlag(idLong, sentToFinFlag);
      return true;
    }
    return false;
  }

  private void cleanUnusedInvoiceData(JobContract jobContract)
  {
    if(jobContract == null) return;
    String currentInvoice = jobContract.getInvoice();
    if(currentInvoice == null) return;

    List invoices = new ArrayList();
    invoices.addAll(jobContract.getJobContractInvoices());

    List newInvoices = new ArrayList();
    long maxId = 0;
    int count = -1;
    for(int i=0; i<invoices.size(); i++)
    {
      JobContractInvoice invoice = (JobContractInvoice)invoices.get(i);
      if( (!Constants.CREDIT_INDICATOR_C.equals(invoice.getCreditInd()))
        && (invoice.getId() > maxId))
      {
        maxId = invoice.getId();
        count = i;
      }

      if(currentInvoice.equals(invoice.getInvoice()))
      {
        newInvoices.add(invoice);
      }
    }

    if(count != -1) newInvoices.add(invoices.get(count));

    jobContract.getJobContractInvoices().clear();
    jobContract.getJobContractInvoices().addAll(newInvoices);
  }
  
  public Document getDoc(Object obj){
	  OxmManager oxmManager = (OxmManager)ServiceLocator.getInstance().getBean("oxmManager");
	  return oxmManager.marshal(obj);
  }
  
  public String getKey(Object obj){
	  JobContractInvoice ci=(JobContractInvoice)obj;
	  return ci.getInvoice()+"::"+ci.getId();
  }
  
  public String getCreatedUser(Object obj){
	  JobContractInvoice ci=(JobContractInvoice)obj;
	  return ci.getCreationUserName();
  }
}
