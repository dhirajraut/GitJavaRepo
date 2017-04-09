package com.intertek.tool.loader;

import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;

import org.exolab.castor.mapping.Mapping;
import org.exolab.castor.xml.Unmarshaller;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;
import org.jdom.output.XMLOutputter;
import org.springframework.context.ApplicationContext;

import com.intertek.util.Constants;
import com.intertek.util.Context;
import com.intertek.util.Message;

public abstract class LoaderBase implements Loader
{
  private ApplicationContext ctx;
  private String mappingFile;
  private Mapping mapping;
  private String controlFile;

  public void setMappingFile(String mappingFile)
  {
    this.mappingFile = mappingFile;
  }

  public String getMappingFile()
  {
    return mappingFile;
  }

  public void setCtx(ApplicationContext ctx)
  {
    this.ctx = ctx;
  }

  public ApplicationContext getCtx()
  {
    return ctx;
  }

  public void setControlFile(String controlFile)
  {
    this.controlFile = controlFile;
  }

  public String getControlFile()
  {
    return controlFile;
  }

  public void loadAsString(Context context, String strData)
  {
    try
    {
      SAXBuilder builder = new SAXBuilder();

      Document document = builder.build(new StringReader(strData));
      Element root = document.getRootElement();

      //XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
      //outputter.output(root, System.out);

      loadAsXml(context, root);
    }
    catch(Throwable e)
    {
      e.printStackTrace();
      context.addMessage(new Message(
        "XmlParsingError", new Object[] { e.getMessage() }
      ));
    }
  }

  public abstract void loadAsFile(Context context, String filename);

  public void loadAsXml(Context context, Element root)
  {
    List loaders = root.getChildren();
    for(int i=0; i<loaders.size(); i++)
    {
      Element loaderElement = (Element)loaders.get(i);
      String className = loaderElement.getAttributeValue(Constants.service);
      if(className == null)
      {
        //throw new RuntimeException("class attribute must be not null for loader element");
        context.addMessage(new Message(
          "XmlParsingError", new Object[] { "Missing service attribute in loader element!" }
        ));

        continue;
      }

      String methodName = loaderElement.getAttributeValue(Constants.api);
      if(methodName == null)
      {
        //throw new RuntimeException("method attribute must be not null for loader element");
        context.addMessage(new Message(
          "XmlParsingError", new Object[] { "Missing api attribute in loader element!" }
        ));

        continue;
      }
      try
      {
        List dataList = loaderElement.getChildren();
        Class[] dataClasses = new Class[dataList.size()];
        Object[] dataObjects = new Object[dataList.size()];
        for(int j=0; j<dataList.size(); j++)
        {
          Element dataElement = (Element)dataList.get(j);
          Object obj = getObjectFromElement(dataElement);

          dataObjects[j] = obj;
          dataClasses[j] = dataObjects[j].getClass();
        }

        Object loaderObject = ctx.getBean(className);

        Class loaderClass = loaderObject.getClass();
        Method loaderMethod = loaderClass.getDeclaredMethod(methodName, dataClasses);

        loaderMethod.invoke(loaderObject, dataObjects);
      }
      catch(Throwable e)
      {
        e.printStackTrace();

        String msg = e.toString();
        Throwable t = e.getCause();
        if(t != null)
        {
          msg = t.toString();
        }

        context.addMessage(new Message(
          "LoaderError", new Object[] { className, methodName, msg }
        ));
      }
    }
  }

  private Object getObjectFromElement(Element dataElement) throws Exception
  {
    String elementName = dataElement.getName();
    if(Constants.CLASS.equals(elementName))
    {
      return getObjectFromClass(dataElement);
    }

    StringWriter sw = new StringWriter();
    XMLOutputter outputter = new XMLOutputter();
    outputter.output(dataElement, sw);

    Reader reader = new StringReader(sw.toString());

    Unmarshaller unmarshaller = new Unmarshaller(getMapping());
    return unmarshaller.unmarshal(reader);
  }

  private Object getObjectFromClass(Element dataElement) throws Exception
  {
    String name = dataElement.getAttributeValue(Constants.name);
    if(name == null) return null;

    String text = dataElement.getText();

    Object result = null;

    Class clazz = Class.forName(name);
    Constructor constructor = clazz.getConstructor(Class.forName("java.lang.String"));

    result = constructor.newInstance(text);

    return result;
  }

  private Mapping getMapping() throws Exception
  {
    if(mapping == null)
    {
      mapping = new Mapping();
      mapping.loadMapping(mappingFile);
    }

    return mapping;
  }
}
