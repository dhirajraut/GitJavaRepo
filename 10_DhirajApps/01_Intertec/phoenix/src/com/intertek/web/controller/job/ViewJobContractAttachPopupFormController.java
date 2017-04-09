package com.intertek.web.controller.job;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.AbstractController;

import com.intertek.util.StringUtil;


public class ViewJobContractAttachPopupFormController extends AbstractController 
{
  private static Log log = LogFactory.getLog(ViewJobContractAttachPopupFormController.class);

  public ViewJobContractAttachPopupFormController() {
  }

  protected ModelAndView handleRequestInternal(
		    HttpServletRequest request,
		    HttpServletResponse response
		  ) throws Exception
	  {
	    PropertyResourceBundle pRB = (PropertyResourceBundle) ResourceBundle.getBundle("config");
	  	String path ="";
		if(pRB != null)
			path = pRB.getString(com.intertek.util.Constants.jobcontractfilepath).concat("/");
		 
	    String fileName = request.getParameter("systemFileName");
	    // Special characters resetting
	    fileName = StringUtil.replaceSpecialCodes(fileName);
	    //End
	    Map model = new HashMap();
	    model.put("ContentType", "x-download");
	    model.put(
	      "OriginalFileName",
	      fileName
	    );
	    
	    if(fileName != null)
	    	fileName = path.concat(fileName);
	   
	    model.put("ContentType", "x-download");
	    model.put(
	      "FileName",
	      fileName
	    );
	    		
	    int len=0;
	    
		   
	       byte[] buf = new byte[4097];
	        try{
	    	FileInputStream file = new FileInputStream(fileName);
	    	if(file!=null){
		        InputStream is = new BufferedInputStream(file);
		
		        int off = 0;
		        len = is.read(buf, 0, 4096);  
	    	}
	        }
	        catch(Exception e)
	        {	        	
	        }
	    	 if(len>0)
		        { 	
	    		    View view = (View) getApplicationContext().getBean("fileView");
		    	    return new ModelAndView(view, model);
		        }
		      else
		        {	        	
		        	ModelAndView modelAndView = new ModelAndView("message-error-r");
		        	modelAndView.addObject("msg", "The selected file doesn't have any content!");
		        	return modelAndView;
		        }	    	 
	  }
  
}
