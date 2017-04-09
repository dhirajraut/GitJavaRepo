package com.intertek.webservice.outbound.spring;

import javax.xml.transform.Result;
import javax.xml.transform.Source;

import org.springframework.ws.WebServiceMessage;
import org.springframework.ws.client.core.WebServiceMessageCallback;
import org.springframework.ws.client.core.WebServiceTemplate;
import org.springframework.ws.soap.SoapMessage;

import com.intertek.webservice.outbound.WebServiceSender;

public class SpringWebServiceSender implements WebServiceSender
{
  private WebServiceTemplate webServiceTemplate = new WebServiceTemplate();
  private String soapAction;
  private String url;

  public void setSoapAction(String soapAction)
  {
    this.soapAction = soapAction;
  }

  public void setUrl(String url)
  {
    this.url = url;
  }

  public boolean sendSourceAndReceiveToResult(
    Source requestPayload,
    Result responseResult
  )
  {
    if(url != null)
    {
      return webServiceTemplate.sendSourceAndReceiveToResult(
        url,
        requestPayload,
        new WebServiceMessageCallback()
        {
          public void doWithMessage(WebServiceMessage message)
          {
            if(soapAction != null)
            {
              ((SoapMessage)message).setSoapAction(soapAction);
            }
          }
        },
        responseResult
      );
    }

    return false;
  }
}
