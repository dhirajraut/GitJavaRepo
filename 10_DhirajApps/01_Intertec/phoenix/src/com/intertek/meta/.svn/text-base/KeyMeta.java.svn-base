package com.intertek.meta;


public class KeyMeta extends MetaEntity
{
  private String value;

  public String getValue()
  {
    return value;
  }

  public void setValue(String value)
  {
    this.value = value;
  }

  public String toString()
  {
    StringBuffer sb = new StringBuffer();

    sb.append("\n<key>");
    sb.append(value);
    sb.append("</key>");

    return sb.toString();
  }

   public boolean equals(Object other)
   {
     if ( (this == other ) ) return true;
     if ( (other == null ) ) return false;
     if ( !(other instanceof KeyMeta) ) return false;
     KeyMeta castOther = ( KeyMeta ) other;

     return ( (this.getValue()==castOther.getValue())
       || ( this.getValue()!=null
            && castOther.getValue()!=null
            && this.getValue().equals(castOther.getValue())
          )
     );
   }

   public int hashCode()
   {
     return (getValue() != null) ? getValue().hashCode() : getClass().hashCode();
   }
}
