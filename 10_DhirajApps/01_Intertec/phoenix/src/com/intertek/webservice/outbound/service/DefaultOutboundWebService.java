package com.intertek.webservice.outbound.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import org.jdom.Document;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.springframework.mail.javamail.JavaMailSender;

import com.intertek.domain.NameValuePair;
import com.intertek.entity.ControlSwitch;
import com.intertek.entity.User;
import com.intertek.entity.WebServiceEntity;
import com.intertek.locator.ServiceLocator;
import com.intertek.mail.VelocityMimeMessagePreparator;
import com.intertek.service.WSService;
import com.intertek.tool.collector.DataCollector;
import com.intertek.tool.transformer.DataTransformer;
import com.intertek.util.SecurityUtil;
import com.intertek.webservice.outbound.OutboundWebService;
import com.intertek.webservice.outbound.WSOutboundContext;
import com.intertek.webservice.outbound.WebServiceSender;
import com.intertek.webservice.outbound.resultParser.IResultParser;

public class DefaultOutboundWebService implements OutboundWebService
{
  protected String type;
  protected String webServiceUrl;
  protected DataCollector dataCollector;
  protected DataTransformer dataTransformer;
  protected WebServiceSender webServiceSender;
  protected IResultParser resultParser;

  public IResultParser getResultParser() {
	return resultParser;
  }

  public void setResultParser(IResultParser resultParser) {
	this.resultParser = resultParser;
  }

  public String getType() {
	return type;
  }
  
  public void setType(String type)
  {
    this.type = type;
  }

  public void setWebServiceUrl(String webServiceUrl)
  {
    this.webServiceUrl = webServiceUrl;
  }

  public String getWebServiceUrl()
  {
    return webServiceUrl;
  }

  public void setDataCollector(DataCollector dataCollector)
  {
    this.dataCollector = dataCollector;
  }

  public DataCollector getDataCollector()
  {
    return dataCollector;
  }

  public void setDataTransformer(DataTransformer dataTransformer)
  {
    this.dataTransformer = dataTransformer;
  }

  public DataTransformer getDataTransformer()
  {
    return dataTransformer;
  }

  public void setWebServiceSender(WebServiceSender webServiceSender)
  {
    this.webServiceSender = webServiceSender;
  }

  public WebServiceSender getWebServiceSender()
  {
    return webServiceSender;
  }

  public boolean sendService(WSOutboundContext context)
  {
	if(!isEnabled()) return false;
	
    if((context == null) || (dataCollector == null) || (webServiceSender == null)) return false;

    WSService wsService = (WSService)ServiceLocator.getInstance().getBean("wsService");

    List nvs = dataCollector.collect(context.getDataMap());
    if(nvs == null) return false;

    boolean result=true;
    for(int i=0; i<nvs.size(); i++)
    {
      WebServiceEntity wsEntity = new WebServiceEntity();
      context.setWebServiceEntity(wsEntity);

      String message = null;
      boolean status = true;

      NameValuePair nv = (NameValuePair)nvs.get(i);
      String name = nv.getName();
      Object valueObj=nv.getValue();
      Document doc;
      if(valueObj instanceof Document){
    	  doc=(Document)valueObj;
      }
      else{
    	  doc=dataCollector.getDoc(valueObj);
      }

      try
      {
        XMLOutputter xmlOut = new XMLOutputter(Format.getPrettyFormat());
        //xmlOut.output(doc, System.out);

        Document transformed = doc;
        if(dataTransformer != null)
        {
          transformed = dataTransformer.transform(doc);
        }

        if(transformed == null) continue;
        //xmlOut.output(transformed, System.out);

        //System.out.println("=========== wsEntity = " + wsEntity);
        wsService.sendWebService(context, name, transformed, dataCollector, webServiceSender);

        if(resultParser!=null){
        	status=resultParser.parseResult(wsEntity.getResult());
        	if(status==false){
        		dataCollector.updateFail(name);
        	}
        	else{
        		 dataCollector.updateSuccess(name);
        	}
        }
        else{
        	 dataCollector.updateSuccess(name);
        }
        result=result && status;
      }
      catch(Exception e)
      {
        e.printStackTrace();
        message = e.getMessage();
        status = false;
        result=false;
      }

      //System.out.println("=========== message = " + message);

      wsEntity.setMessage(message);
      wsEntity.setStatus(status);
      try
      {
        if((wsEntity.getContent() != null) || (wsEntity.getMessage() != null))
        {
          wsEntity.setType(type);
          String entityKey=dataCollector.getKey(valueObj);
          if(entityKey!=null){
        	  wsEntity.setEntityKey(entityKey);
          }
          else{
        	  wsEntity.setEntityKey(name);
          }

          String createdByUser=dataCollector.getCreatedUser(valueObj);
          if(createdByUser!=null){
        	  wsEntity.setCreateUser(createdByUser);
          }
          else{
              User user = SecurityUtil.getUser();
              if(user != null){
                wsEntity.setCreateUser(user.getLoginName());
              }
          }

          wsEntity.setCreateTime(new Date());

          wsService.saveWebServiceEntity(wsEntity);

          if(wsEntity.getStatus() == false)
          {
            sendOutEmail(wsEntity);
          }
        }
      }
      catch(Exception e)
      {
        e.printStackTrace();
      }
    }
    return result;
  }

  private String getEntityKey(List entityKeys)
  {
    if(entityKeys == null) return null;

    StringBuffer sb = new StringBuffer();
    for(int i=0; i<entityKeys.size(); i++)
    {
      if(i != 0) sb.append(",");
      Object obj = entityKeys.get(i);
      sb.append(obj);
    }

    return sb.toString();
  }

  private String getEmailTo(){
	  try{
		  PropertyResourceBundle pRB = (PropertyResourceBundle) ResourceBundle.getBundle("config");
		  return pRB.getString(this.getType().toUpperCase()+"_ERROR_EMAIL_TO");
	  }
	  catch(Exception e){
	  }
	  return null;
  }
  
  private void sendOutEmail(WebServiceEntity wsEntity)
  {
    try
    {
      JavaMailSender sender = (JavaMailSender) ServiceLocator.getInstance().getBean("sender");
      VelocityMimeMessagePreparator preparator =(VelocityMimeMessagePreparator) ServiceLocator.getInstance().getBean("integrationMailPreparator");

      String emailTo=getEmailTo();
      if(emailTo!=null && !emailTo.equals("")){
    	  preparator.setTo(emailTo);
      }
      
      if((sender != null) && (preparator != null))
      {
        Map data = new HashMap();
        data.put("wsEntity",wsEntity);
        preparator.setData(data);

        sender.send(preparator);
      }
    }
    catch(Throwable t)
    {
      t.printStackTrace();
    }
  }
  
  public boolean isEnabled(){
	  WSService wsService = (WSService)ServiceLocator.getInstance().getBean("wsService");
	  ControlSwitch cs=wsService.getOutboundWSControlSwitch(getType());
	  boolean result=cs==null || "enabled".equalsIgnoreCase(cs.getFlag()+"");
	  return result;
  }
}
