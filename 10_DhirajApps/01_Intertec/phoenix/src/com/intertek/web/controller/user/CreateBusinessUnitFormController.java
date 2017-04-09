package com.intertek.web.controller.user;

import java.text.SimpleDateFormat;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.intertek.exception.ServiceException;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.intertek.domain.AddBusinessUnit;
import com.intertek.entity.BusUnitVatLoc;
import com.intertek.entity.BusUnitVatLocId;
import com.intertek.entity.BusinessUnit;
import com.intertek.entity.Country;
import com.intertek.entity.Organization;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.CountryService;
import com.intertek.service.UserService;
import com.intertek.util.SecurityUtil;
import com.intertek.web.controller.BaseSimpleFormController;

public class CreateBusinessUnitFormController extends BaseSimpleFormController
//public class CreateBusinessUnitFormController extends SimpleFormController
{
  public CreateBusinessUnitFormController() {
    super();
    setCommandClass(AddBusinessUnit.class);
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
   */
  protected ModelAndView onSubmit(
    HttpServletRequest request,
    HttpServletResponse response,
    Object command,
    BindException errors
  ) throws Exception
  {
	Country country = null;
    AddBusinessUnit addbu = (AddBusinessUnit) command;
    String addOrDelete = request.getParameter("addOrDelete");
    if((addOrDelete != null) && ("add".equals(addOrDelete) || "delete".equals(addOrDelete)))
    {
			if("add".equals(addOrDelete))
			{
				addbu.setBusUnitVatLocs(addBusUnitVatLoc(addbu.getBusUnitVatLocs()));
			}
			else
			{
				addbu.setBusUnitVatLocs(deleteBusUnitVatLoc(addbu.getBusUnitVatLocs(), addbu.getIndex()));
			}
		  addbu.setBusUnitVatLocCount(addbu.getBusUnitVatLocs().length);

		  addbu.setAddOrDelete("none");

      return showForm(request, response, errors);
    }

		BusinessUnit bu = addbu.getBusinessUnit();
		BusUnitVatLoc[] busUnitVatLocItems = addbu.getBusUnitVatLocs();

    UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
    CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");
    boolean defaultVatLocInd = false;

		if (busUnitVatLocItems != null && bu.getVatEnabledInd())
		{
				for(int i=0; i< busUnitVatLocItems.length; i++)
			{
				BusUnitVatLoc busUnitVatLoc = busUnitVatLocItems[i];
				
				if(busUnitVatLoc.getDefaultLocInd() && (!defaultVatLocInd))
					defaultVatLocInd = busUnitVatLoc.getDefaultLocInd();
				else if(busUnitVatLoc.getDefaultLocInd() && defaultVatLocInd)
				{
					addbu.setBusUnitVatLocs(busUnitVatLocItems);
					//errors.reject("create.business.unit.error", new Object[] {"Only one Default Vat Location is allowed"}, null);
					errors.reject("default.vat.location.error", new Object[] {}, null);
				      return showForm(request, response, errors);
				}

				//If no location has been made default vat location throw error
				if(busUnitVatLocItems.length == i+1 && (!defaultVatLocInd))
				{
					//errors.reject("create.business.unit.error", new Object[] {"One Default Vat Location is required"}, null);
					errors.reject("default.vat.location.required", new Object[] {}, null);
				      return showForm(request, response, errors);
				}
				
				BusUnitVatLocId busUnitVatLocId = busUnitVatLoc.getBusUnitVatLocId();
				if(busUnitVatLocId == null)
					busUnitVatLocId = new BusUnitVatLocId();
				
				busUnitVatLocId.setBuName(bu.getName());
				busUnitVatLocId.setCountryCode(busUnitVatLoc.getCountry().getCountryCode());
				busUnitVatLoc.setBusUnitVatLocId(busUnitVatLocId);
				bu.getBusUnitVatLocs().add(busUnitVatLoc);

			}
		}
		 if(bu.getStateCode()== null || bu.getStateCode().equals(""))
			{
			 if(bu.getCountryCode() == null || bu.getCountryCode().equals(""))
			 {
			  errors.reject("country.business.unit.error", new Object[] {bu.getName()}, null);
		      return showForm(request, response, errors);
			 }
		    	country = countryService.getCountryByCode(bu.getCountryCode());
		    	Set s =country.getStates();
		    	
		    	if(s.size()!=0)
		    	{
					//errors.reject("create.business.unit.error",	new Object[] { "Please select State for Country: "	+ bu.getCountryCode() }, null);
					errors.reject("select.state.error",	new Object[] {bu.getCountryCode() }, null);					
					return showForm(request, response, errors);
					
		    	}
			}
    try
    {
			bu.setOrgName(bu.getOrganization().getName());
      bu = userService.addBusinessUnit(bu);
    }
   catch(ServiceException e)
  {
    e.printStackTrace();
    errors.reject(e.getMessage(), e.getParams(), null);
    return showForm(request, response, errors);
  }
  catch(Throwable t)
  {
    t.printStackTrace();
    errors.reject("generic.error", new Object[] {t.getMessage()}, null);
    return showForm(request, response, errors);
  }

    return new ModelAndView(new RedirectView("edit_business_unit.htm"), "businessUnit.name", bu.getName());
  }

  protected boolean suppressValidation(HttpServletRequest request)
  {
    String addOrDelete = request.getParameter("addOrDelete");
    if((addOrDelete != null) && ("add".equals(addOrDelete) || "delete".equals(addOrDelete)))
    {
      return true;
    }

    return super.suppressValidation(request);
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest, org.springframework.web.bind.ServletRequestDataBinder)
   */
  protected void initBinder(
    HttpServletRequest request,
    ServletRequestDataBinder binder
  ) throws Exception
  {
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
    dateFormat.setLenient(false);
    binder.registerCustomEditor(java.util.Date.class, null, new CustomDateEditor(dateFormat, true));
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
   */
  protected Object formBackingObject(
    HttpServletRequest request
  ) throws Exception
  {
	  String countStr = request.getParameter("busUnitVatLocCount");
	  if( countStr == null || countStr.trim().equals("") ) countStr = "0";

		int count = 0;
		try
		{
			count = new Integer(countStr).intValue();
		}
		catch(Exception e) { }

		AddBusinessUnit addBusinessUnit = new AddBusinessUnit();

		addBusinessUnit.setBusUnitVatLocCount(count);
		BusUnitVatLoc[] busUnitVatLocs = new BusUnitVatLoc[count];
		for(int i=0; i<count; i++)
		{
			BusUnitVatLoc busUnitVatLoc = new BusUnitVatLoc();
			// START : #119240 : 06/07/09
			BusUnitVatLocId busUnitVatLocId = busUnitVatLoc.getBusUnitVatLocId();
			if(busUnitVatLocId == null)
				busUnitVatLocId = new BusUnitVatLocId();
			
			busUnitVatLoc.setBusUnitVatLocId(busUnitVatLocId);
			// END : #119240 : 06/07/09
			
			Country country = new Country();
			country.setName("United States of America");
			busUnitVatLoc.setCountry(country);

			busUnitVatLoc.setDefaultLocInd(false);
			busUnitVatLocs[i] = busUnitVatLoc;
		}
		addBusinessUnit.setBusUnitVatLocs(busUnitVatLocs);

    BusinessUnit bu = new BusinessUnit();
    Organization org = new Organization();
    org.setName(
      SecurityUtil.getUser().getBranch().getBusinessUnit().getOrganization().getName()
    );

    bu.setOrganization(org);
	  addBusinessUnit.setBusinessUnit(bu);
	  // setting user associated country code
	  if(bu.getCountryCode() == null || bu.getCountryCode().trim().equals(""))
		  bu.setCountryCode(SecurityUtil.getUser().getCountryCode());
	 
	  // START : #119240 : 29/06/09
	  HttpSession session = request.getSession();
	 // System.out.println("---  Session : businessUnitSearch : " + session.getAttribute("businessUnitSearch"));
	  if(session.getAttribute("businessUnitSearch")!=null){
		session.removeAttribute("businessUnitSearch");		
	  } 	  
	  // END : #119240 : 29/06/09
	  
    return addBusinessUnit;
  }

  private BusUnitVatLoc[] addBusUnitVatLoc(BusUnitVatLoc[] vatLocs)
  {
		BusUnitVatLoc vatLoc = new BusUnitVatLoc();
		BusUnitVatLocId vatLocId = new BusUnitVatLocId();
		Country country = SecurityUtil.getUser().getCountry();
		if(country == null)
			country = new Country();
		//country.setName("United States of America");
		//country.setName(SecurityUtil.getUser().getCountry().getName());
		vatLoc.setCountry(country);
		vatLocId.setCountryCode(country.getCountryCode());
		vatLoc.setOutOfScope("false");
		System.out.println("vatloc country :"+country.getCountryCode());
		vatLoc.setBusUnitVatLocId(vatLocId);

		
		//Set the first vat location as the default vat location
		
		if(vatLocs == null || vatLocs.length == 0)
			vatLoc.setDefaultLocInd(true);
		else
			vatLoc.setDefaultLocInd(false);

		BusUnitVatLoc[] results = null;
		if(vatLocs == null) results = new BusUnitVatLoc[1];
		else
		{
			results = new BusUnitVatLoc[vatLocs.length + 1];
			
			
		}
		if(vatLocs != null)
		{
		for(int i=0; i<vatLocs.length; i++)
		{
			results[i] = vatLocs[i];
		}
		}

		results[results.length - 1] = vatLoc;
			
		return results;
	}

  private BusUnitVatLoc[] deleteBusUnitVatLoc(BusUnitVatLoc[] vatLocs, int index)
  {
		if(vatLocs == null) return null;
		if(vatLocs.length <= 0) return vatLocs;

		BusUnitVatLoc[] results = new BusUnitVatLoc[vatLocs.length - 1];

    int count = 0;
		for(int i=0; i<vatLocs.length; i++)
		{
			if(i == index) continue;
			results[count] = vatLocs[i];

			count ++;
		}

		return results;
	}
}
