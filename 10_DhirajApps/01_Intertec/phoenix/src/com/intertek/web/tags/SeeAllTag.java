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

public class SeeAllTag extends SimpleTagSupport
{
  public void doTag() throws JspException, IOException
  {
    PageContext ctx = (PageContext)getJspContext();
    Locale locale = I18nUtil.getLocale((HttpServletRequest)ctx.getRequest());

    List menuTree = SecurityUtil.getMenuTree();

    StringBuffer sb = new StringBuffer();

    sb.append("<table cellpadding=\"0\" cellspacing=\"0\" class=\"allTableStyle\" style=\"border:#DBE2F2 1px solid;\">");
    sb.append("<tr>");
    sb.append("<td valign=\"top\" style=\"padding-right:10px;\">");

    int menuSize = menuTree != null ? menuTree.size() : 0;
    for(int i=0; i<menuSize; i++)
    {
      Menu menu = (Menu)menuTree.get(i);
      sb.append("<a");
      if(menu.getUrl() != null)
      {
        sb.append(" href=\"");
        sb.append(menu.getUrl());
        sb.append("\"");
      }
      sb.append(">");

      sb.append("<strong>");
      if((i != 0) &&(i != 2) && (i != 3))
      {
        sb.append("<br />");
      }
      sb.append(I18nUtil.getString(locale, menu.getName()));
      sb.append("</strong>");
      sb.append("</a>");

      displayChildMenus(menu, sb);

      if((i == 1) || (i == 2))
      {
        sb.append("</td>");
        sb.append("<td valign=\"top\" style=\"padding-right:10px;\">");
      }
    }

    sb.append("</td>");
    sb.append("</tr>");
    sb.append("</table>");

    getJspContext().getOut().print(sb.toString());
  }

  private void displayChildMenus(Menu menu, StringBuffer sb)
  {
    if( (menu == null) || (sb == null) ) return;

    PageContext ctx = (PageContext)getJspContext();
    Locale locale = I18nUtil.getLocale((HttpServletRequest)ctx.getRequest());

    Iterator it = menu.getChildMenus().iterator();
    while(it.hasNext())
    {
      Menu childMenu = (Menu)it.next();
      sb.append("<a");
      if(childMenu.getUrl() != null)
      {
        sb.append(" href=\"");
        sb.append(childMenu.getUrl());
        sb.append("\"");
      }

      if(childMenu.getChildMenus().size() > 0)
      {
        sb.append(" style=\"font-weight:bold; color:#1f55ba;\">");
      }
      else
      {
        sb.append(" style=\"padding-left:3px;\">");
      }
      sb.append(I18nUtil.getString(locale, childMenu.getName()));
      sb.append("</a>");

      displayChildMenus(childMenu, sb);
    }
  }
}
