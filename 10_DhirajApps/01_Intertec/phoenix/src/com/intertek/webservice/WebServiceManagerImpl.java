package com.intertek.webservice;

import java.io.StringWriter;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.jdom.Element;
import org.jdom.output.XMLOutputter;

import com.intertek.entity.WebServiceEntityInbound;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.WSService;
import com.intertek.tool.oxm.OxmManager;
import com.intertek.webservice.handler.Handler;

public class WebServiceManagerImpl implements WebServiceManager
{
  private OxmManager oxmManager;
  private Map handlerMap;

  public void setOxmManager(OxmManager oxmManager)
  {
    this.oxmManager = oxmManager;
  }

  public void setHandlerMap(Map handlerMap)
  {
    this.handlerMap = handlerMap;
  }

  public void processMessage(Element inputMessage)
  {
    if(inputMessage == null) return;
    WSService wsService = (WSService)ServiceLocator.getInstance().getBean("wsService");
    
    List entityElements = inputMessage.getChildren();
    for(int i=0; i<entityElements.size(); i++)
    {
      Element entityElement = (Element)entityElements.get(i);
      String entityName = entityElement.getName();

      WebServiceEntityInbound wsEntity=new WebServiceEntityInbound();
      try{
	      StringWriter sw = new StringWriter();
	      XMLOutputter xmlOut = new XMLOutputter();
	      xmlOut.output(entityElement, sw);
	      wsEntity.setContent(sw.toString());
      }
      catch(Exception e){
    	  wsEntity.setContent("<error>failed to get content from element ("+entityElement.toString()+")</error>");
      }
      
      Handler handler = (Handler)handlerMap.get(entityName);
      if(handler != null)
      {
    	  try{
    		  wsEntity.setType(handler.getType());
    		  Object entity = oxmManager.unmarshal(entityElement);
    		  Object resultEntity=handler.handle(entity);
    		  wsEntity.setEntityKey(handler.getId(resultEntity));
    		  wsEntity.setStatus(true);
    	  }
    	  catch(Exception e){
    	      wsEntity.setStatus(false);
    	      wsEntity.setMessage(e.getMessage()+"<cause>"+e.getCause().toString()+"</cause>");
    	      e.printStackTrace();
    	  }
      }
      else{
    	  wsEntity.setStatus(false);
    	  wsEntity.setMessage("No handler for name="+entityName);
    	  wsEntity.setType(entityName);
      }
      wsEntity.setCreateTime(new Date());
      wsService.saveWebServiceEntityInbound(wsEntity);
    }
  }
}
