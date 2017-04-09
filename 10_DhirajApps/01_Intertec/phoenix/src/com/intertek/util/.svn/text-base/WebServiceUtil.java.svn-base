package com.intertek.util;

import org.jdom.Element;
import org.jdom.Namespace;

public class WebServiceUtil
{
  public static Element getErrorResponse(
    String message,
    Object[] params
  )
  {
    Namespace namespace = Namespace.getNamespace("phx", "http://www.intertek.com/phoenix/schemas");
    Element root = new Element(Constants.RESULT, namespace);

    Element status = new Element(Constants.STATUS, namespace);
    status.setText(Constants.ERROR);
    root.addContent(status);

    Element messageElement = new Element(Constants.MESSAGE, namespace);
    Element value = new Element(Constants.VALUE, namespace);
    value.setText(message);
    messageElement.addContent(value);

    if(params != null)
    {
      for(int i=0; i<params.length; i++)
      {
        if(params[i] != null)
        {
          Element param = new Element(Constants.PARAM, namespace);
          param.setText(params[i].toString());
          messageElement.addContent(param);
        }
      }
    }

    root.addContent(messageElement);
    return root;
  }

  public static Element getSuccessResponse()
  {
    Namespace namespace = Namespace.getNamespace("phx", "http://www.intertek.com/phoenix/schemas");
    Element root = new Element(Constants.RESULT, namespace);

    Element status = new Element(Constants.STATUS, namespace);
    status.setText(Constants.SUCCESS);
    root.addContent(status);

    return root;
  }
}

