package com.intertek.tool.collector.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.jdom.Document;

import com.intertek.domain.NameValuePair;
import com.intertek.entity.Contact;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.WSService;
import com.intertek.tool.oxm.OxmManager;
import com.intertek.util.Constants;

public class ContactDataCollector extends BaseDataCollector
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

      List entities = wsService.getContactsForOutboundInvoiceMessage(pagination);
      if(entities.size() > 0)
      {
        for(int i=0; i<entities.size(); i++)
        {
          Contact contact = (Contact)entities.get(i);

          String name = contact.getId() + "";
          Document doc = oxmManager.marshal(contact);

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

  public void updateSuccess(String id){
	  updateFlag(id, Constants.SENT, Constants.SENT);
  }
  
  public void updateFail(String id){
	  updateFlag(id, Constants.FAIL, Constants.FAIL);
  }

  public boolean reset(String id){
	  try{
		  return updateFlag(id, Constants.NEW, Constants.NEW);
	  }
	  catch(Exception e){
		  return false;
	  }
  }
  
  public boolean updateFlag(String id, String newFlag, String updateFlag)
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
      wsService.updateContactFlag(idLong, newFlag, updateFlag);
      return true;
    }
    return false;
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
