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

import com.intertek.config.PhoenixConfiguration;
import com.intertek.util.InvoiceUtil;

public class InvoiceViewController extends AbstractController
{
  private static Log log = LogFactory.getLog(InvoiceViewController.class);

  /**
   * .ctor
   */
  public InvoiceViewController() {
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
    String invoice = request.getParameter("invoice");
    String invoicetype = request.getParameter("invoicetype");
    String invoiceDir = InvoiceUtil.getInvoiceDir();
    if(invoicetype !=null && invoicetype.equalsIgnoreCase("DepositInvoice"))
    {
        invoiceDir=invoiceDir+"DepositInvoice/";
    }
    if(invoicetype !=null && invoicetype.equals("consol"))
    	invoiceDir = invoiceDir + "CONSOLS\\";
    
    
    if(!invoice.contains(".pdf"))
       invoice = invoice + ".pdf"; 
    
    Map model = new HashMap();
    model.put("ContentType", "pdf");
    
    model.put(
    	      "OriginalFileName",
    	       invoice
    	    );
    
    model.put(
      "FileName",
      invoiceDir + invoice
    );

    View view = (View) getApplicationContext().getBean("fileView");

    return new ModelAndView(view, model);
  }
}
