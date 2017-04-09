package com.intertek.web.tags;

import java.io.IOException;
import java.util.List;
import java.util.ArrayList;

import javax.servlet.jsp.JspException;

public class ListTag extends VarTagSupport
{
  private ArrayList list;
  private Object items;

  public ListTag()
  {
    list = new ArrayList();
  }

  public void add(Object item)
  {
    list.add(item);
  }

  public void doTag() throws JspException, IOException
  {
    if(getJspBody() != null)
    {
      getJspBody().invoke(null);
    }

    if(items != null)
    {
      if(items instanceof List)
      {
        List itemList = (List)items;
        for(int i=0; i<itemList.size(); i++)
        {
          list.add(itemList.get(i));
        }
      }
      else
      {
      list.add(items);
      }
    }

    export(list);
  }

  public void setItems(Object items)
  {
    this.items = items;
  }
}
