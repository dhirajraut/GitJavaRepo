package com.intertek.web.views;

import java.io.PrintWriter;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.View;

public class XmlView implements View
{
  /* (non-Javadoc)
   * @see org.springframework.web.servlet.View#render(java.util.Map, javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
   */
  public void render(
    Map model,
    HttpServletRequest request,
    HttpServletResponse response
  ) throws Exception
  {
    String xml = (String)model.get("Content");
    System.out.println("======== in XmlView: xml = " + xml);

    // Set content to xml
    response.setContentType("text/xml; charset=UTF-8");
    response.setHeader("Cache-Control", "no-cache");
    PrintWriter pw = response.getWriter();
    pw.write(xml);
    pw.close();
  }

  public String getContentType()
  {
    return null;
  }
}
