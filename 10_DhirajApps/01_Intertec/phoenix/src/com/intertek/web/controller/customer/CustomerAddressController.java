package com.intertek.web.controller.customer;

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
import com.intertek.service.CustomerService;

public class CustomerAddressController extends AbstractController
{
  private static Log log = LogFactory.getLog(CustomerAddressController.class);

  /**
   * .ctor
   */
  public CustomerAddressController() {
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

	String addressId = "";
	String customerId = "";
	List address = new ArrayList();
	String xml = "";
    System.out.println("inside handleRequestInternal:");

	  if
		  //(request.getParameter("addressId") != null && (!request.getParameter("addressId").trim().equals(""))&&
		   (request.getParameter("customerId") != null && (!request.getParameter("customerId").trim().equals("")))
	  {
		  addressId = request.getParameter("addressId");
		  customerId = request.getParameter("customerId");

		  System.out.println("inside handleRequestInternal:"+addressId+customerId);

		  address = customerService.getCustomerAddressesByAddrSeqNumber(customerId,addressId);

		System.out.println("address size is : "+address.size());
		
  		  xml = new AjaxXmlBuilder().addItems(address, "custAddrSeqId.locationNumber", "addressDescr").toString();

	  }
		  Map model = new HashMap();
		  model.put("Content", xml);

		  View view = (View)getApplicationContext().getBean("xmlView");

    return new ModelAndView(view, model);
  }
}