package com.intertek.meta;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ViewMeta extends MetaEntity
{
  protected KeysMeta keysMeta;
  protected Map<String, ObjectMeta> objectMetaMap = new HashMap<String, ObjectMeta>();

  public KeysMeta getKeysMeta()
  {
    return keysMeta;
  }

  public void setKeysMeta(KeysMeta keysMeta)
  {
    this.keysMeta = keysMeta;
  }

  public ObjectMeta getObjectMeta(String name)
  {
    return objectMetaMap.get(name);
  }

  public void setObjectMeta(String name, ObjectMeta objectMeta)
  {
    objectMetaMap.put(name, objectMeta);
  }

  public String toString()
  {
    StringBuffer sb = new StringBuffer();
    sb.append("\n<view>");

    sb.append(keysMeta);

    Iterator<String> it = objectMetaMap.keySet().iterator();
    while(it.hasNext())
    {
      String name = it.next();
      ObjectMeta objectMeta = objectMetaMap.get(name);
      sb.append(objectMeta);
    }

    sb.append("</view>\n");

    return sb.toString();
  }
}
