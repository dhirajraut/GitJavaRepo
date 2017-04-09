package com.intertek.web.controller.job;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ajaxtags.helpers.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.AbstractController;

import com.intertek.locator.ServiceLocator;
import com.intertek.service.JobService;

public class TemplateController extends AbstractController
{
  private static Log log = LogFactory.getLog(TemplateController.class);

  /**
   * .ctor
   */
  public TemplateController() {
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
	  JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
	 
	 
	 String searchTmpNm = request.getParameter("searchTmp");
	 String branchName = request.getParameter("branchNam");

	 List template = new ArrayList();
	 String xml = "";
	
	  if(searchTmpNm == null) searchTmpNm = "";
	  
	  if((branchName!=null) && (!branchName.trim().equals("")))
	  {
		  template=jobService.getTemplateByNameAndBranch(searchTmpNm, branchName);
		  xml=new AjaxXmlBuilder().addItems(template,"tempName","jobNumber").toString();
	  }

		  Map model = new HashMap();
		  model.put("Content", xml);

		  View view = (View)getApplicationContext().getBean("xmlView");
		 return new ModelAndView(view, model);
  }



}