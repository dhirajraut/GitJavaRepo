package com.intertek.web.tags;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.SimpleTagSupport;

import com.intertek.entity.Menu;
import com.intertek.util.I18nUtil;
import com.intertek.util.SecurityUtil;

public class MenuTag extends SimpleTagSupport
{
  private String id;

  public void setId(String id)
  {
    this.id = id;
  }

  public String getId()
  {
    return id;
  }

  public void doTag() throws JspException, IOException
  {
    PageContext ctx = (PageContext)getJspContext();
    Locale locale = I18nUtil.getLocale((HttpServletRequest)ctx.getRequest());

    List menuTree = SecurityUtil.getMenuTree();

    StringBuffer sb = new StringBuffer();

    sb.append("<ul id=\"");
    sb.append(id);
    sb.append("\" >");

    int menuSize = menuTree != null ? menuTree.size() : 0;
    for(int i=0; i<menuSize; i++)
    {
      Menu menu = (Menu)menuTree.get(i);
      sb.append("<li> <a href=\"");
      if(menu.getUrl() != null) sb.append(menu.getUrl());
      else sb.append("#");
      sb.append("\">");
      sb.append(I18nUtil.getString(locale, menu.getName()));
      sb.append("</a>");

      displayChildMenus(menu, sb);


      sb.append("</li>");
    }

    sb.append("</ul>");

    getJspContext().getOut().print(sb.toString());
  }

  private void displayChildMenus(Menu menu, StringBuffer sb)
  {
    if( (menu == null) || (sb == null) ) return;

    PageContext ctx = (PageContext)getJspContext();
    Locale locale = I18nUtil.getLocale((HttpServletRequest)ctx.getRequest());

    if(menu.getChildMenus().size() > 0)
    {
      sb.append("<ul nowrap=\"yes\"");
      if((menu.getDisplaySize() != null) && (menu.getDisplaySize() > 0))
      {
        sb.append(" style=\"width:");
        sb.append(menu.getDisplaySize());
        sb.append("px;\">");
      }
      else
      {
        sb.append(">");
      }

      Iterator it = menu.getChildMenus().iterator();
      while(it.hasNext())
      {
        Menu childMenu = (Menu)it.next();
        sb.append("<li");
        if((menu.getDisplaySize() != null) && (menu.getDisplaySize() > 0))
        {
          sb.append(" style=\"width:");
          sb.append(menu.getDisplaySize());
          sb.append("px;\">");
        }
        else
        {
          sb.append(">");
        }
        sb.append("<a href=\"");
        if(childMenu.getUrl() != null) sb.append(childMenu.getUrl());
        else sb.append("#");
        sb.append("\"><IMG SRC=\"images/medium_red_right.gif\" BORDER=\"0\" style=\"float:left; margin-top:4px;\">");
        if(childMenu.getChildMenus().size() > 0)
        {
          sb.append("<IMG SRC=\"images/arrows_6_hl.gif\" BORDER=\"0\" style=\"float:right; margin-top:3px;\">");
        }
        sb.append(I18nUtil.getString(locale, childMenu.getName()));
        sb.append("</a>");

        displayChildMenus(childMenu, sb);

        sb.append("</li>");
      }

      sb.append("</ul>");
    }
  }
}
