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
import com.intertek.service.JobService;
import com.intertek.util.InvoiceUtil;

public class JobOrderPdfViewController extends AbstractController
{
  private static Log log = LogFactory.getLog(JobOrderPdfViewController.class);

  /**
   * .ctor
   */
  public JobOrderPdfViewController() {
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
    String jobNumber = request.getParameter("jobNumber");

    JobService jobService = (JobService) ServiceLocator.getInstance().getBean("jobService");

    byte[] pdfData = jobService.getJobOrderPdfData(
      jobNumber,
      InvoiceUtil.getInvoiceDir(),
      InvoiceUtil.getJasperDir()
    );

    Map model = new HashMap();
    model.put("Content", pdfData);
    model.put("FileName", jobNumber+ "_JobOrder" + ".pdf");
    View view = (View) getApplicationContext().getBean("pdfView");
    
    return new ModelAndView(view, model);
  }
}
