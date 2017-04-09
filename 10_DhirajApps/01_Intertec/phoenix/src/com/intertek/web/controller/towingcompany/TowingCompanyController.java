package com.intertek.web.controller.towingcompany;

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

import com.intertek.entity.TowingCompany;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.TowingCompanyService;

public class TowingCompanyController extends AbstractController
{
  private static Log log = LogFactory.getLog(TowingCompanyController.class);

  /**
   * .ctor
   */
  public TowingCompanyController() {
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
    String towingCompanyName = request.getParameter("towingCompany.name");
    log.info("======== in TowingCompanyController: towingCompanyName = " + towingCompanyName);

    TowingCompanyService towingCompanyService = (TowingCompanyService)ServiceLocator.getInstance().getBean("towingCompanyService");
    log.info("======== in TowingCompanyController: towingCompanyService = " + towingCompanyService);

    List towingCompanys = towingCompanyService.searchTowingCompanysByName(towingCompanyName);
    log.info("======== in TowingCompanyController: towingCompanys = " + towingCompanys);
    //String xml = new AjaxXmlBuilder().addItems(towingCompanys, "towingCompanyName", "towingCompanyName").toString();
    
    AjaxXmlBuilder xmlBuilder =  new AjaxXmlBuilder();
    String xml = "";
	for(int i=0;i<towingCompanys.size();i++)
	{
		TowingCompany towingCompany=(TowingCompany)towingCompanys.get(i);
		if(towingCompany.getPhone()==null){
			towingCompany.setPhone("");
		}
		if(towingCompany.getCity()==null){
			towingCompany.setCity("");
		}
		String towName = towingCompany.getName() + "," + towingCompany.getCity();
		String phone = towingCompany.getPhone();
		xmlBuilder.addItem(towName,phone );
		
	}
	xml = xmlBuilder.toString();
		//String xml = new AjaxXmlBuilder().addItems(towingCompanys, "name","phone").toString();
	
    log.info("======== in TowingCompanyController: xml = " + xml);

    Map model = new HashMap();
    model.put("Content", xml);

    View view = (View)getApplicationContext().getBean("xmlView");

    return new ModelAndView(view, model);
  }
}