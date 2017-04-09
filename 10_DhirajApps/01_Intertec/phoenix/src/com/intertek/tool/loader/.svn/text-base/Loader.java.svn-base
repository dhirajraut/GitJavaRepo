package com.intertek.tool.loader;

import org.jdom.Element;
import org.springframework.context.ApplicationContext;

import com.intertek.util.Context;

public interface Loader
{
  void setCtx(ApplicationContext ctx);
  void setMappingFile(String mappingFile);
  void setControlFile(String controlFile);
  void loadAsString(Context context, String strData);
  void loadAsFile(Context context, String filename);
  void loadAsXml(Context context, Element root);
}
