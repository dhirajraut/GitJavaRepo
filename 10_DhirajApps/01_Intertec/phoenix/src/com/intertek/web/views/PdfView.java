package com.intertek.web.views;

import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.View;

public class PdfView implements View
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
	System.out.println("in pdf view");
    byte[] pdfData = (byte[])model.get("Content");
    response.setContentType("application/pdf; charset=UTF-8");
    String fileName = (String)model.get("FileName");
    System.out.println("fileName"+fileName);
    if(fileName != null)
    	response.setHeader("Content-disposition","attachment; filename=\""+fileName+"\"");
    response.setHeader("Cache-Control", "no-cache");
    
    OutputStream os = response.getOutputStream();
    if(pdfData != null) os.write(pdfData);
    else
    	System.out.println("pdf data in pdf view is null");
    os.close();
  }

  public String getContentType()
  {
    return null;
  }
}
