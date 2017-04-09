package com.intertek.tool.collector.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jdom.Document;

import com.intertek.domain.NameValuePair;
import com.intertek.entity.Branch;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.WSService;
import com.intertek.tool.oxm.OxmManager;
import com.intertek.util.Constants;

public class BranchDataCollector extends BaseDataCollector
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

      List entities = wsService.getBranchesForOutboundInvoiceMessage(pagination);
      if(entities.size() > 0)
      {
        for(int i=0; i<entities.size(); i++)
        {
          Branch branch = (Branch)entities.get(i);

          String name = branch.getName();
          Document doc = oxmManager.marshal(branch);

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

  public void updateSuccess(String name)
  {
    update(name, Constants.SENT, Constants.SENT);
  }
  
  public void updateFail(String name){
  	update(name, Constants.FAIL, Constants.FAIL);
  }
  
  public boolean reset(String id){
	  try{
		  return update(id, Constants.NEW, Constants.NEW);
	  }
	  catch(Exception e){
		  return false;
	  }
  }
  
  public boolean update(String name, String newFlag, String updateFlag){
	if(name == null) return false;
	
	WSService wsService = (WSService)ServiceLocator.getInstance().getBean("wsService");
	wsService.updateBranchFlag(name, newFlag, updateFlag);
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

