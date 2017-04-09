package com.intertek.tool.collector;

import java.util.List;
import java.util.Map;

import org.jdom.Document;

public interface DataCollector
{
  List collect(Map dataMap);
  void updateSuccess(String name);
  void updateFail(String name);
  boolean reset(String id);
  
  String getKey(Object obj);
  String getCreatedUser(Object obj);
  Document getDoc(Object obj);
}
