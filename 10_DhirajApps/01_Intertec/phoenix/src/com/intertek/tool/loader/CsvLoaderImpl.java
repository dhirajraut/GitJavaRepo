package com.intertek.tool.loader;

import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
import org.springframework.ui.velocity.VelocityEngineUtils;

import au.com.bytecode.opencsv.CSVReader;

import com.intertek.util.Context;
import com.intertek.util.Message;

public class CsvLoaderImpl extends LoaderBase
{
  private VelocityEngine velocityEngine;

  public void setVelocityEngine(VelocityEngine velocityEngine)
  {
    this.velocityEngine = velocityEngine;
  }

  public void loadAsFile(Context context, String filename)
  {
    //int index = filename.lastIndexOf(".");
    //String ctlFileName = filename.substring(0, index) + ".ctl";

    String ctlFileName = getControlFile();
    System.out.println("==== ctlFileName = " + ctlFileName);

    try
    {
      CSVReader reader2 = new CSVReader(new FileReader(filename));
      List allElements = reader2.readAll();

      for(int i=0; i<allElements.size(); i++)
      {
        String[] dataArray = (String[])allElements.get(i);
        if((dataArray != null) && !dataArray[0].startsWith("#"))
        {
          StringBuffer sb = new StringBuffer();
          Map data = new HashMap();
          for(int j=0; j<dataArray.length; j++)
          {
            data.put("column" + j, dataArray[j]);
            sb.append(dataArray[j].trim());
          }

          if(sb.length() == 0) continue;

          String templateStr = VelocityEngineUtils.mergeTemplateIntoString(
            velocityEngine, ctlFileName, data
          );

          loadAsString(context, templateStr);
        }
      }
    }
    catch(Throwable e)
    {
      e.printStackTrace();
      context.addMessage(new Message(
        "CsvParsingError", new Object[] { filename, e.getMessage() }
      ));
    }
  }
}


