package com.intertek.tool.collector.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jdom.Document;

import com.intertek.domain.NameValuePair;
import com.intertek.entity.ConsolidatedInvoice;
import com.intertek.entity.ConsolidatedInvoiceId;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.WSService;
import com.intertek.tool.oxm.OxmManager;
import com.intertek.util.Constants;

public class ConsolidatedInvoiceDataCollector extends BaseDataCollector
{
  public List collect(Map dataMap)
  {
    List results = new ArrayList();

    try
    {
      WSService wsService = (WSService)ServiceLocator.getInstance().getBean("wsService");
      OxmManager oxmManager = (OxmManager)ServiceLocator.getInstance().getBean("oxmManager");

      Pagination pagination = null;
      if((startPage > 0) && (numInPage > 0))
      {
        pagination = new Pagination(0, numInPage, startPage, 1);
      }

      List entities = wsService.getConsolidatedInvoicesForOutboundInvoiceMessage(pagination);
      System.out.println("========= entities = " + entities);
      if(entities!=null && entities.size() > 0)
      {
        for(int i=0; i<entities.size(); i++)
        {
        	try{
	          ConsolidatedInvoice consolidatedInvoice = (ConsolidatedInvoice)entities.get(i);
	          System.out.println("========= consolidatedInvoice.getBillStatus() = " + consolidatedInvoice.getBillStatus());
	          ConsolidatedInvoiceId consolidatedInvoiceId = consolidatedInvoice.getConsolidatedInvoiceId();
	          System.out.println("========= consolidatedInvoiceId = " + consolidatedInvoiceId);
	          if(consolidatedInvoiceId == null) continue;
	
	          String name = consolidatedInvoiceId.getConsolInvoiceNo()
	            + "::" + consolidatedInvoice.getCustCode()
	            + "::" + consolidatedInvoiceId.getBuName();
	
	          Document doc = oxmManager.marshal(consolidatedInvoice);
	
	          NameValuePair nv = new NameValuePair(name, doc);
	          results.add(nv);
        	}
        	catch(Exception e){
        		e.printStackTrace();
        	}
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
	  update(Constants.SENT, id);
  }
  
  public void updateFail(String id){
	  update(Constants.FAIL, id);
  }
  
  public boolean reset(String id){
	  try{
		  return update(Constants.NEW, id);
	  }
	  catch(Exception e){
		  return false;
	  }
  }
  
  public boolean update(String sentToFinFlag, String id)
  {
    if(id == null) return false;

    String[] ids = id.split("::");
    if(ids == null) return false;

    if(ids.length != 3) return false;

    String consolInvoiceNo = ids[0];
    String custCode = ids[1];
    String buName = ids[2];

    WSService wsService = (WSService)ServiceLocator.getInstance().getBean("wsService");
    wsService.updateConsolidatedInvoiceFlag(sentToFinFlag, consolInvoiceNo, custCode, buName);
    return true;
  }
  
  public Document getDoc(Object obj){
	  return null;
  }
  
  public String getKey(Object obj){
	  return null;
  }
  
  public String getCreatedUser(Object obj){
	  return null;
  }
}
