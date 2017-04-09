package com.intertek.meta;

public abstract class MetaEntity implements java.io.Serializable
{
  private String name;

  public String getName()
  {
    return name;
  }

  public void setName(String name)
  {
    this.name = name;
  }

  public boolean equals(Object obj)
  {
    if(name == null) return false;

    if(obj instanceof MetaEntity)
    {
      MetaEntity tmp = (MetaEntity)obj;
      if(name.equals(tmp.getName())) return true;
    }

    return false;
  }

  public int hashCode()
  {
    if(name != null) return name.hashCode();

    return getClass().hashCode();
  }
}
