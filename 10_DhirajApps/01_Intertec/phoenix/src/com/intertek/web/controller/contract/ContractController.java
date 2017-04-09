package com.intertek.web.controller.contract;

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
import com.intertek.service.ContractService;
import com.intertek.service.CustomerService;

public class ContractController extends AbstractController
{
  private static Log log = LogFactory.getLog(ContractController.class);

  /**
   * .ctor
   */
  public ContractController() {
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
	  CustomerService customerService = (CustomerService)ServiceLocator.getInstance().getBean("customerService");
	  ContractService contractService =(ContractService)ServiceLocator.getInstance().getBean("contractService");

	  String originator="";
	String signatory="";
	List originators=new ArrayList();
	 List signators=new ArrayList();
	 String xml = "";


	  if(request.getParameter("originator")!=null && (!request.getParameter("originator").trim().equals("")))
		 {
				 originator=request.getParameter("originator");
				  System.out.println("account owner  name is "+originator);
				   originators=customerService.getAccountOwner(originator);
				  System.out.println("inside handle requestInternal:"+originators.size());
				  xml=new AjaxXmlBuilder().addItems(originators,"loginName","email").toString();
			/*	   AjaxXmlBuilder xmlBuilder =  new AjaxXmlBuilder();
				  for(int i=0;i<originators.size();i++)
				  {		  
					  System.out.println("size is "+originators.size());
						User user=(User)originators.get(i);
					  String fname = user.getFirstName();
					  String lname= 	 user.getLastName();
					  String value = user.getFirstName() +","+ user.getLastName() ;	  
					 // String value=fname+lname;
					  xmlBuilder.addItem(fname,value);
				  }
		 xml = xmlBuilder.toString();		  */
	  }

		   if(request.getParameter("signatory")!=null && (!request.getParameter("signatory").trim().equals("")))
		 {
				 signatory=request.getParameter("signatory");
				  System.out.println("account owner  name is "+signatory);
				   signators=customerService.getAccountOwner(signatory);
				  System.out.println("inside handle requestInternal:"+signators.size());
				  xml=new AjaxXmlBuilder().addItems(signators,"loginName","email").toString();
			/*	   AjaxXmlBuilder xmlBuilder =  new AjaxXmlBuilder();
				  for(int i=0;i<signators.size();i++)
				  {		  
					  System.out.println("size is "+signators.size());
						User user=(User)signators.get(i);
					  String fname = user.getFirstName();
					  String lname= 	 user.getLastName();
					  String value = user.getFirstName() +","+ user.getLastName() ;	  
					 // String value=fname+lname;
					  xmlBuilder.addItem(fname,value);
				  }
		 xml = xmlBuilder.toString();		  */
	  }
	      Map model = new HashMap();
		  model.put("Content", xml);
		  View view = (View)getApplicationContext().getBean("xmlView");
		 return new ModelAndView(view, model);
  }
}