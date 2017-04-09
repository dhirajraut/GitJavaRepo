package com.intertek.tool.collector.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jdom.Document;

import com.intertek.domain.NameValuePair;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.WSService;
import com.intertek.tool.oxm.OxmManager;
import com.intertek.util.Constants;

public class LimsCustomerDataCollector extends BaseDataCollector
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

      List entities = wsService.getLimsCustomersForOutboundInvoiceMessage(pagination);
      if(entities.size() > 0)
      {
        for(int i=0; i<entities.size(); i++)
        {
          //Customer customer = (Customer)entities.get(i);

          //String name = customer.getCustCode();
          //Document doc = oxmManager.marshal(customer);

          Object[] objs = (Object[])entities.get(i);
          if(objs.length == 0) continue;

          String name = null;
          if(objs[0] != null) name = objs[0].toString();
          if(name == null) continue;

          for(int j=0; j<objs.length; j++)
          {
            if(objs[j] == null) objs[j] = "";
          }
          Document doc = oxmManager.marshal(objs);

          NameValuePair nv = new NameValuePair(name, doc);
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

  public void updateSuccess(String custCode){
	  updateFlag(custCode, Constants.SENT);
  }

  public void updateFail(String custCode){
	  updateFlag(custCode, Constants.FAIL);
  }

  public boolean reset(String id){
	  try{
		  return updateFlag(id, Constants.NEW);
	  }
	  catch(Exception e){
		  return false;
	  }
  }

  public boolean updateFlag(String custCode, String updateLimsFlag)
  {
    if(custCode == null) return false;

    WSService wsService = (WSService)ServiceLocator.getInstance().getBean("wsService");
    wsService.updateLimsCustomerFlag(custCode, updateLimsFlag);
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

