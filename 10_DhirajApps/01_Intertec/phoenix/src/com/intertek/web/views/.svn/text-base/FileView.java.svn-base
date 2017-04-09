package com.intertek.web.views;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.View;

public class FileView implements View
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
	  
	  
    String fileName = (String)model.get("FileName");
    if(fileName != null)
    {
	    String contentType = (String)model.get("ContentType");
	    byte[] buf = new byte[4097];
	
	    response.setContentType("application/" + contentType + "; charset=UTF-8");
  	    String originalFileName = (String)model.get("OriginalFileName");
		
		if(originalFileName == null){
			int trimIndex = fileName.lastIndexOf("\\");
			if(trimIndex>=0)
		    	 originalFileName = fileName.substring(trimIndex +1, fileName.length());
		}
		
		System.out.println("original file name before replacing is "+ originalFileName);
		
		if(originalFileName!=null && !originalFileName.trim().equals("")&& originalFileName.contains("/")){
			originalFileName=originalFileName.replace("/","_");
		}
		System.out.println("original file name after replacing is "+ originalFileName);
	    
		response.setHeader("Content-Disposition","attachment; filename=\""+originalFileName+"\"");
	
	    response.setHeader("Cache-Control", "no-cache");
	    OutputStream os = response.getOutputStream();
    
      try
      {
    	FileInputStream file = new FileInputStream(fileName);
    	if(file!=null){
	        InputStream is = new BufferedInputStream(file);
	
	        int off = 0;
	        int len = is.read(buf, 0, 4096);
	
	        while( len > 0)
	        {
	          os.write(buf, 0, len);
	          len = is.read(buf, 0, 4096);
	        }
    	}
      }
      catch(Throwable t)
      {
        t.printStackTrace();
      }
      os.close();
    }
  }

  public String getContentType()
  {
    return null;
  }
}
