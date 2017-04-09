package com.intertek.meta;


public class ObjectRefMeta extends MetaEntity
{
  private String key;

  public String getKey()
  {
    return key;
  }

  public void setKey(String key)
  {
    this.key = key;
  }

  public String toString()
  {
    StringBuffer sb = new StringBuffer();

    sb.append("\n<object_ref key=\"");
    sb.append(key);
    sb.append("\" />");

    return sb.toString();
  }

  public boolean equals(Object obj)
  {
    if(key == null) return false;

    if(obj instanceof ObjectRefMeta)
    {
      ObjectRefMeta tmp = (ObjectRefMeta)obj;
      if(key.equals(tmp.getKey())) return true;
    }

    return false;
  }

  public int hashCode()
  {
    if(key != null) return key.hashCode();

    return getClass().hashCode();
  }
}
