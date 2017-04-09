package com.intertek.web.controller.user;

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

import com.intertek.entity.State;
import com.intertek.entity.StateId;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.CountryService;

public class CountryController extends AbstractController
{
  private static Log log = LogFactory.getLog(CountryController.class);

  /**
   * .ctor
   */
  public CountryController() {
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
    String country = request.getParameter("country.countryCode");

    CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");

    List States = countryService.getStatesByCountryCode(country);
    
    /* changed code for state dropdown population to have a blank item in state dropdown */
    
    State state = new State();
    StateId stateId = new StateId();
    stateId.setStateCode("");
    state.setName("");
    state.setStateId(stateId);
    States.add(state);
 
  
   String xml = new AjaxXmlBuilder().addItems(States, "name", "stateId.stateCode").toString();

   String stateCode=request.getParameter("stateCode"); 
    String countryCode=request.getParameter("countryCode"); 
   System.out.println("Before getting StateCode=" + stateCode);
   System.out.println("Before getting countryCode=" + countryCode);

   if(stateCode!=null && countryCode!= null)
	  {
	   System.out.println("StateCode=" + stateCode);
		System.out.println("CountryCode=" + countryCode);
	   List stateCodes= countryService.getStatesByCodeAndCountryCode(stateCode,countryCode);
	  System.out.println("Statecode size= " + stateCodes.size());
	   xml = new AjaxXmlBuilder().addItems(stateCodes, "stateId.stateCode", "stateId.stateCode").toString();

	  
	  }


    Map model = new HashMap();
    model.put("Content", xml);

    View view = (View)getApplicationContext().getBean("xmlView");

    return new ModelAndView(view, model);
  }
}