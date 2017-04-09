package com.intertek.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.context.ApplicationContext;

public class Context
{
  private List messageList = new ArrayList();
  private int status;
  private ApplicationContext appCtx;

  public void setApplicationContext(ApplicationContext appCtx)
  {
    this.appCtx = appCtx;
  }

  public ApplicationContext getApplicationContext()
  {
    return appCtx;
  }

  public void addMessage(Message msg)
  {
    messageList.add(msg);
  }

  public List getMessageList()
  {
    return messageList;
  }

  public void clearMessageList()
  {
    messageList.clear();
  }

  public String getMessagesStr()
  {
    StringBuffer sb = new StringBuffer();

    int count = 0;
    Iterator it = messageList.iterator();
    while(it.hasNext())
    {
      Message msg = (Message)it.next();

      if(count > 0) sb.append(": ");
      sb.append(msg);

      count ++;
    }

    return sb.toString();
  }

  public int getStatus()
  {
    return status;
  }

  public void setStatus(int status)
  {
    this.status = status;
  }
}
