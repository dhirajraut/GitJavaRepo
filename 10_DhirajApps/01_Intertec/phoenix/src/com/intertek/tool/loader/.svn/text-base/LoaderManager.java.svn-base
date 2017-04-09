package com.intertek.tool.loader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.intertek.locator.ServiceLocator;
import com.intertek.util.Context;
import com.intertek.util.Message;

public class LoaderManager
{
  public static void main(String[] args) throws Exception
  {
    if(args.length < 2)
    {
      System.out.println("Usage: java LoaderManager filename mapping");
      System.exit(1);
    }

    String fileName = args[0];
    String mapping = args[1];
    String controlFile = null;
    if(args.length == 3) controlFile = args[2];

    ApplicationContext ctx = new FileSystemXmlApplicationContext(
      new String[]
      {
        "../web/WEB-INF/applicationContext.xml",
        "../web/WEB-INF/applicationContext-Loader.xml"
      }
    );

    ServiceLocator.getInstance().setApplicationContext(ctx);

    int index = fileName.lastIndexOf(".");
    if(index <= 0)
    {
      throw new RuntimeException("File name must end with .xml or .csv");
    }

    String type = fileName.substring(index + 1);
    Loader loader = null;

    if(type.equals("xml"))
    {
      loader = (Loader)ctx.getBean("xmlLoader");
    }
    else if(type.equals("csv"))
    {
      loader = (Loader)ctx.getBean("csvLoader");
    }

    if(loader == null)
    {
      System.out.println("Only csv and xml type loaders are accepted.");
      System.exit(1);
    }

    Context context = new Context();

    long start = System.currentTimeMillis();
    loader.setCtx(ctx);
    loader.setMappingFile(mapping);
    loader.setControlFile(controlFile);
    loader.loadAsFile(context, fileName);
    long end = System.currentTimeMillis();

    if(context.getMessageList().size() > 0)
    {
      //System.out.println("Message: " + context.getMessagesStr());
      for(int i=0; i<context.getMessageList().size(); i++)
      {
        Message message = (Message)context.getMessageList().get(i);
        System.out.println(i + ": " + message);
      }

      System.out.println("Message size: " + context.getMessageList().size());
    }

    System.out.println("Total time used (minutes): " + (end - start)/60000.0);

    System.out.println("Done");
    System.exit(0);
  }
}


