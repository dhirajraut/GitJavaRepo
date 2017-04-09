package com.intertek.tool.loader;

import java.io.File;

import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.intertek.util.Context;
import com.intertek.util.Message;

public class XmlLoaderImpl extends LoaderBase
{
  public void loadAsFile(Context context, String filename)
  {
    try
    {
      SAXBuilder builder = new SAXBuilder();

      Document document = builder.build(new File(filename));
      Element root = document.getRootElement();

      //XMLOutputter outputter = new XMLOutputter(Format.getPrettyFormat());
      //outputter.output(root, System.out);

      loadAsXml(context, root);
    }
    catch(Throwable e)
    {
      //e.printStackTrace();
      context.addMessage(new Message(
        "XmlParsingError", new Object[] { filename, e.getMessage() }
      ));
    }
  }
}
