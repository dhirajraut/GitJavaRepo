package com.intertek.util;

public class Message
{
  private String message;
  private Object[] params;
  private Throwable throwable;

  public Message(String message)
  {
    this(message, null);
  }

  public Message(String message, Object[] params)
  {
    this(message, params, null);
  }

  public Message(String message, Object[] params, Throwable throwable)
  {
    this.message = message;
    this.params = params;
    this.throwable = throwable;
  }

  public String getMessage()
  {
    return message;
  }

  public Object[] getParams()
  {
    return params;
  }

  public Throwable getThrowable()
  {
    return throwable;
  }

  public String toString()
  {
    StringBuffer sb = new StringBuffer();
    sb.append(message);
    if( (params != null) && (params.length > 0) )
    {
      sb.append(" (");
      for(int i=0; i<params.length; i++)
      {
        if(i != 0) sb.append(", ");
        sb.append(params[i]);
      }
      sb.append(")");
    }

    return sb.toString();
  }
}
