package com.intertek.tool.transformer.xsl;

import org.jdom.Document;
import org.jdom.transform.XSLTransformer;
import org.springframework.core.io.Resource;

import com.intertek.exception.ServiceException;
import com.intertek.tool.transformer.DataTransformer;

public class XSLDataTransformer implements DataTransformer
{
  private Resource stylesheet;
  private XSLTransformer transformer;

  public void setStylesheet(Resource stylesheet)
  {
    this.stylesheet = stylesheet;
    if(this.stylesheet != null)
    {
      try
      {
        transformer = new XSLTransformer(this.stylesheet.getFile());
      }
      catch(Exception e)
      {
        e.printStackTrace();
      }
    }
  }

  public Document transform(Document input)
  {
    if(input == null) return null;
    if(transformer == null) return input;

    try
    {
      return transformer.transform(input);
    }
    catch(Throwable e)
    {
      e.printStackTrace();
      throw new ServiceException(e);
    }
  }
}
