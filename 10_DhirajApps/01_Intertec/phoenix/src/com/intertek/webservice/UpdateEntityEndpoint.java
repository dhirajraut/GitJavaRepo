package com.intertek.webservice;

import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;
import org.springframework.ws.server.endpoint.AbstractJDomPayloadEndpoint;

import com.intertek.exception.ServiceException;
import com.intertek.util.WebServiceUtil;

/**
 * This endpoint handles updateEntity requests.
 *
 */
public class UpdateEntityEndpoint extends AbstractJDomPayloadEndpoint
{
  private WebServiceManager webServiceManager;

  public void setWebServiceManager(WebServiceManager webServiceManager)
  {
    this.webServiceManager = webServiceManager;
  }

  protected Element invokeInternal(Element requestElement) throws Exception
  {
    XMLOutputter xmlOut = new XMLOutputter(Format.getPrettyFormat());
    xmlOut.output(requestElement, System.out);

    String errorMessage = null;
    Object[] params = null;
    try
    {
      if(webServiceManager != null)
      {
        webServiceManager.processMessage(requestElement);
      }
    }
    catch(ServiceException e)
    {
      e.printStackTrace();
      errorMessage = e.getMessage();
      params = e.getParams();
    }
    catch(Exception e)
    {
      e.printStackTrace();
      errorMessage = e.getMessage();
      if(errorMessage == null) errorMessage = e.toString();
    }

    Element result = null;

    if(errorMessage != null)
    {
      result = WebServiceUtil.getErrorResponse(errorMessage, params);
    }
    else
    {
      result=  WebServiceUtil.getSuccessResponse();
    }

    xmlOut = new XMLOutputter(Format.getPrettyFormat());
    xmlOut.output(result, System.out);

    return result;
  }
}
