package com.intertek.meta;

import java.util.ArrayList;
import java.util.List;

public class KeysMeta extends MetaEntity
{
  protected List<KeyMeta> keyMetaList = new ArrayList<KeyMeta>();

  public List<KeyMeta> getKeyMetaList()
  {
    return keyMetaList;
  }

  public void addKeyMeta(KeyMeta keyMeta)
  {
    keyMetaList.add(keyMeta);
  }

  public String toString()
  {
    StringBuffer sb = new StringBuffer();
    sb.append("\n<keys>");

    for(int i=0; i<keyMetaList.size(); i++)
    {
      KeyMeta keyMeta = keyMetaList.get(i);
      sb.append(keyMeta);
    }

    sb.append("</keys>\n");

    return sb.toString();
  }

   public boolean equals(Object other)
   {
     if ( (this == other ) ) return true;
     if ( (other == null ) ) return false;
     if ( !(other instanceof KeysMeta) ) return false;
     KeysMeta castOther = ( KeysMeta ) other;

     if(getKeyMetaList().size() != castOther.getKeyMetaList().size()) return false;

     for(int i=0; i<getKeyMetaList().size(); i++)
     {
       KeyMeta keyMeta0 = getKeyMetaList().get(i);
       KeyMeta keyMeta1 = castOther.getKeyMetaList().get(i);
       if(!keyMeta0.equals(keyMeta1)) return false;
     }

     return true;
   }

   public int hashCode()
   {
     int result = 0;
     for(int i=0; i<getKeyMetaList().size(); i++)
     {
       result += getKeyMetaList().get(i).hashCode();
     }

     System.out.println("========== hashCode of KeysMeta: " + result);
     return result;
   }
}
