package com.intertek.web.controller.job;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.AbstractController;

import com.intertek.locator.ServiceLocator;
import com.intertek.service.PrebillService;
import com.intertek.util.InvoiceUtil;

public class InvoicePreviewController extends AbstractController
{
  private static Log log = LogFactory.getLog(InvoicePreviewController.class);

  /**
   * .ctor
   */
  public InvoicePreviewController() {
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
   * <context dynamic="true|false">
   <User Name="jaloia111" />
   <Type Name="MANIFEST_FILE" />
   </context>
   */
  protected ModelAndView handleRequestInternal(
    HttpServletRequest request,
    HttpServletResponse response
  ) throws Exception
  {
	String idstr = request.getParameter("id");
    String jobNumber = request.getParameter("jobNumber");
    String uid20 = request.getParameter("uid20");
    Map model = new HashMap();
    
    if(idstr!=null && jobNumber != null && jobNumber.trim().length()>0){
    	PrebillService prebillService = (PrebillService) ServiceLocator.getInstance().getBean("prebillService");
    	long id = Long.parseLong(idstr);
    	String fileName = jobNumber.concat("_").concat("Preview").concat(".pdf");
	    byte[] pdfData = prebillService.previewInvoice(
	    		id,
	    		jobNumber,
	    		uid20,
	    		InvoiceUtil.getInvoiceDir(),
	    		InvoiceUtil.getJasperDir()
	    );
	    model.put("Content", pdfData);
	    model.put("FileName", fileName);
    }
	    View view = (View) getApplicationContext().getBean("pdfView");
    
    return new ModelAndView(view, model);
  }
}
