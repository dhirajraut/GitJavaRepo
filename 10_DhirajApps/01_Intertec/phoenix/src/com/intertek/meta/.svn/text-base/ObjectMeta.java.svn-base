package com.intertek.meta;

import java.util.ArrayList;
import java.util.List;

public class ObjectMeta extends MetaEntity
{
  private String key;
  private List<FieldMeta> fieldMetas = new ArrayList<FieldMeta>();

  public String getKey()
  {
    return key;
  }

  public void setKey(String key)
  {
    this.key = key;
  }

  public List<FieldMeta> getFieldMetas()
  {
    return fieldMetas;
  }

  public FieldMeta getFieldMeta(String name)
  {
    for(int i=0; i<fieldMetas.size(); i++)
    {
      FieldMeta fieldMeta = fieldMetas.get(i);
      if(fieldMeta.getName().equals(name)) return fieldMeta;
    }

    return null;
  }

  public String toString()
  {
    StringBuffer sb = new StringBuffer();

    sb.append("\n<object name=\"");
    sb.append(getName());
    sb.append("\" key=\"");
    sb.append(getKey());
    sb.append("\">\n");

    for(FieldMeta fieldMeta : fieldMetas)
    {
      sb.append(fieldMeta);
    }

    sb.append("\n</object>");

    return sb.toString();
  }
}
