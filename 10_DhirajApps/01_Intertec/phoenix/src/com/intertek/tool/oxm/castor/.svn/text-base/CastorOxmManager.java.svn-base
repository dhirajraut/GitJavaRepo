package com.intertek.tool.oxm.castor;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.Marshaller;
import org.exolab.castor.xml.Unmarshaller;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXHandler;
import org.jdom.output.XMLOutputter;
import org.springframework.core.io.Resource;

import com.intertek.exception.ServiceException;
import com.intertek.tool.oxm.OxmManager;

public class CastorOxmManager implements OxmManager
{
  private Resource mappingLocation;
  private Mapping mapping;
  private Unmarshaller unmarshaller;

  public void setMappingLocation(Resource mappingLocation)
  {
    this.mappingLocation = mappingLocation;
    try
    {
      mapping = new Mapping();
      mapping.loadMapping(mappingLocation.getURL());

      unmarshaller = new Unmarshaller(mapping);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }

  public Object unmarshal(Element element)
  {
    try
    {
      StringWriter sw = new StringWriter();
      XMLOutputter outputter = new XMLOutputter();
      outputter.output(element, sw);

      Reader reader = new StringReader(sw.toString());
      return unmarshal(reader);
    }
    catch(ServiceException e)
    {
      e.printStackTrace();
      throw e;
    }
    catch(Exception e)
    {
      e.printStackTrace();
      throw new ServiceException("UNMARHAL_ERROR", new Object[] {e.getMessage()}, e);
    }
  }

  public Object unmarshal(Reader reader)
  {
    try
    {
      return unmarshaller.unmarshal(reader);
    }
    catch(Exception e)
    {
      e.printStackTrace();
      throw new ServiceException("UNMARSHAL_ERROR", new Object[] {e.getMessage()}, e);
    }
  }

  public Document marshal(java.lang.Object object)
  {
    try
    {
      SAXHandler saxHandler = new SAXHandler();
      Marshaller marshaller = new Marshaller(saxHandler);
      marshaller.setMapping(mapping);
      marshaller.marshal(object);

      return saxHandler.getDocument();
    }
    catch(Exception e)
    {
      e.printStackTrace();
      throw new ServiceException("MARSHAL_ERROR", new Object[] {e.getMessage()}, e);
    }
  }
}
