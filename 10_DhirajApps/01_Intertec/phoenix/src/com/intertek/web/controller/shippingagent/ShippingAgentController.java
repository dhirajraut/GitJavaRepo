package com.intertek.web.controller.shippingagent;

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

import com.intertek.entity.ShippingAgent;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.ShippingAgentService;

public class ShippingAgentController extends AbstractController
{
  private static Log log = LogFactory.getLog(ShippingAgentController.class);

  /**
   * .ctor
   */
  public ShippingAgentController() {
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
    String name = request.getParameter("shippingAgent.name");

    ShippingAgentService shippingAgentService = (ShippingAgentService)ServiceLocator.getInstance().getBean("shippingAgentService");

    List agents = shippingAgentService.searchShippingAgentsByName(name);
    log.info("======== in ShippingAgentController: agents = " + agents);
    AjaxXmlBuilder xmlBuilder =  new AjaxXmlBuilder();
    String xml = "";
    
    for(int i=0;i<agents.size();i++)
	{
    	ShippingAgent shippingAgent=(ShippingAgent)agents.get(i);
		if(shippingAgent.getPhone()==null){
			shippingAgent.setPhone("");
		}
		if(shippingAgent.getCity()==null){
			shippingAgent.setCity("");
		}
		String shipName = shippingAgent.getName() + "," + shippingAgent.getCity();
		String phone = shippingAgent.getPhone();
		xmlBuilder.addItem(shipName,phone );
	}
		//String xml = new AjaxXmlBuilder().addItems(agents, "name", "phone").toString();
    xml = xmlBuilder.toString();
    log.info("======== in ShippingAgentController: xml = " + xml);

    Map model = new HashMap();
    model.put("Content", xml);

    View view = (View)getApplicationContext().getBean("xmlView");

    return new ModelAndView(view, model);
  }
}