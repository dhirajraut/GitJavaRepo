package com.intertek.tool.collector.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jdom.Document;

import com.intertek.domain.NameValuePair;
import com.intertek.entity.JobOrder;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.WSService;
import com.intertek.tool.collector.DataCollector;
import com.intertek.tool.modifier.IObjectModifier;
import com.intertek.tool.oxm.OxmManager;
import com.intertek.util.Constants;

public class JobOrderDataCollector implements DataCollector
{
  public List collect(Map dataMap)
  {
    List results = new ArrayList();

    try
    {
      WSService wsService = (WSService)ServiceLocator.getInstance().getBean("wsService");
      OxmManager oxmManager = (OxmManager)ServiceLocator.getInstance().getBean("oxmManager");

      String jobNumber = (String)dataMap.get(Constants.JOB_NUMBER);
      if(jobNumber == null) return null;

      JobOrder jobOrder = wsService.getJobOrderForOutboundInvoiceMessage(jobNumber);
      if(jobOrder != null)
      {
    	IObjectModifier modifier=(IObjectModifier)dataMap.get(Constants.OBJECT_MODIFIER);
    	if(modifier!=null){
    		modifier.doModify(jobOrder);
    	}
        String name = jobOrder.getJobNumber();
        Document doc = oxmManager.marshal(jobOrder);

        NameValuePair nv = new NameValuePair(name, doc);
        results.add(nv);
      }
    }
    catch(Throwable t)
    {
      t.printStackTrace();
    }

    return results;
  }

  public void updateSuccess(String id)
  {
  }
  
  public void updateFail(String id)
  {
  }
  

  public boolean reset(String id){
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
