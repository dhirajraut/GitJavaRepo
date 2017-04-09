package com.intertek.web.controller.user;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.intertek.exception.ServiceException;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.intertek.domain.AddBusinessUnit;
import com.intertek.domain.BusinessUnitSearch;
import com.intertek.domain.CountrySearch;
import com.intertek.entity.BusUnitVatLoc;
import com.intertek.entity.BusUnitVatLocId;
import com.intertek.entity.BusinessUnit;
import com.intertek.entity.Contract;
import com.intertek.entity.Country;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.CountryService;
import com.intertek.service.UserService;
import com.intertek.util.CountryUtil;
import com.intertek.util.SecurityUtil;
import com.intertek.web.controller.BaseSimpleFormController;

public class EditBusinessUnitFormController extends BaseSimpleFormController

//public class EditBusinessUnitFormController extends SimpleFormController
{
  public EditBusinessUnitFormController() {
    super();
	setSessionForm(true);
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
		Set buVatLocSet = new HashSet();
		UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
		CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");
	    boolean defaultVatLocInd = false;
	    
		bu.getBusUnitVatLocs().clear();
		
		
		if (busUnitVatLocItems != null && bu.getVatEnabledInd())
		{
			for(int i=0; i< busUnitVatLocItems.length; i++)
			{
				BusUnitVatLoc busUnitVatLoc = busUnitVatLocItems[i];
				
				if(busUnitVatLoc.getBusUnitVatLocId().getVatRegistrationId() == null || busUnitVatLoc.getBusUnitVatLocId().getVatRegistrationId().trim().length() == 0 ){
					errors.reject("bus.vat.location.registrationid.required", new Object[] {}, null);
				      return showForm(request, response, errors);
				}
				
				if(busUnitVatLoc.getBusUnitVatLocId().getCountryCode() == null || busUnitVatLoc.getBusUnitVatLocId().getCountryCode().trim().length() == 0 ){
					errors.reject("country.bu.error", new Object[] {}, null);
				      return showForm(request, response, errors);
				}
				
				if(busUnitVatLoc.getDefaultLocInd() && (!defaultVatLocInd))
					defaultVatLocInd = busUnitVatLoc.getDefaultLocInd();
				else if(busUnitVatLoc.getDefaultLocInd() && defaultVatLocInd)
				{
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
				//busUnitVatLocId.setCountryCode(busUnitVatLoc.getCountry().getCountryCode());
				
				Country ctry = countryService.getCountryByCode(busUnitVatLocId.getCountryCode());
			     if(ctry == null) {
			    	  errors.reject("country.error", new Object[] {busUnitVatLocId.getCountryCode()}, null);
				      return showForm(request, response, errors);
			     }
		        busUnitVatLoc.setCountry(ctry);
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
		    	if(s.size()!=0)		    	{
					//errors.reject("save.business.unit.error", new Object[] { "Please select State for Country: "+ bu.getCountryCode() }, null);
					errors.reject("select.state.error",	new Object[] {bu.getCountryCode() }, null);
					return showForm(request, response, errors);
					
		    	}
			}
    try
    {
			bu.setOrgName(bu.getOrganization().getName());
		    userService.saveBusinessUnit(bu);
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

   

 // START : #119240 : 29/06/09
	
  /*ModelAndView modelAndView = new ModelAndView("common-message-r");
  modelAndView.addObject("backUrl", "edit_business_unit.htm?businessUnit.name=" + bu.getName());
  modelAndView.addObject("backUrlDescription", "You can continue to edit this Business Unit.");
  modelAndView.addObject("description", "Your Business Unit has been successfully updated.");*/
	
	String operationType = request.getParameter("operationType");
	String name = null;
	ModelAndView modelAndView = null;
	if(operationType!=null && ("prevBusinessUnit".equals(operationType)
			|| "nextBusinessUnit".equals(operationType)
		    || "searchBusinessUnit".equals(operationType) 
		    || "saveBusinessUnit".equals(operationType))   )
	{
		String viewName = null;
		if("searchBusinessUnit".equals(operationType))
	    {
			viewName = "search-business-unit-r";
	    }
		
	    if(viewName == null)
	    {
	    	viewName = "edit-business-unit-r"; 
	    }

	    modelAndView = new ModelAndView(viewName);
		
	    if ("prevBusinessUnit".equals(operationType) || "nextBusinessUnit".equals(operationType)) 
	    {
			name = this.getPrevOrNextBU(bu.getName(), addbu.getBusinessUnitSearch(),
					"prevBusinessUnit".equals(operationType));
			if (name == null) {
				if ("prevBusinessUnit".equals(operationType)) {
					modelAndView.addObject("saved_message",	"No Previous Business Unit!");
				} else {
					modelAndView.addObject("saved_message",	"No Next Business Unit!");
				}
			}
		}
	    
	    if("saveBusinessUnit".equals(operationType))
		{
	    	modelAndView.addObject("saved_message", "Saved successfully");		    	
		}		    
	 }

	 if(name == null) 
		 name = bu.getName();
	 modelAndView.addObject("businessUnit.name", name);  
	 modelAndView.addObject("fromEdit", "true");
	 
	// System.out.println("Session : businessUnitSearch :---- " + request.getSession().getAttribute("businessUnitSearch"));
	 
	// END : #119240 : 29/06/09
    return modelAndView;
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
			  System.out.println("inside formBackingObject");

    String buName = request.getParameter("businessUnit.name");
		String countStr = request.getParameter("busUnitVatLocCount");
		String deleteIndex = request.getParameter("index");
		int delRow = 0;

	  if( deleteIndex != null && (!deleteIndex.trim().equals("")) )
	  {
		try
		{
			delRow = new Integer(deleteIndex).intValue();
		}
		catch(Exception e) { }
	  }

    BusinessUnit bu = null;
		AddBusinessUnit addBusinessUnit = new AddBusinessUnit();

		UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
    try
    {
      bu = userService.getBusinessUnitByName(buName);
    }
    catch(ServiceException e)
		{
		throw new ServiceException(e.getMessage(), e.getParams(), e);    	
		}
			catch(Throwable t)
			{
			t.printStackTrace();
			throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
			}

    if(bu == null)
    {
      bu = new BusinessUnit();
    }

		addBusinessUnit.setBusinessUnit(bu);
		Set buVatLocSet = bu.getBusUnitVatLocs();

		int count = 0;

	  if( countStr != null && (!countStr.trim().equals("")) )
	  {
		try
		{
			count = new Integer(countStr).intValue();
		}
		catch(Exception e) { }
	  }
	  else
	  {
		  count = buVatLocSet.size();
	  }

		if(count < buVatLocSet.size())
	  {
			buVatLocSet.remove(delRow);
	  }


		addBusinessUnit.setBusUnitVatLocCount(count);
		BusUnitVatLoc[] busUnitVatLocs = new BusUnitVatLoc[count];
		Iterator iter = buVatLocSet.iterator();
		int vatCount = 0;

		while(iter.hasNext())
	  {

			BusUnitVatLoc busUnitVatLoc = (BusUnitVatLoc) iter.next();
			busUnitVatLocs[vatCount] = busUnitVatLoc;
			vatCount++;
	  }
	  if(count > vatCount) //New row added
	  {
			BusUnitVatLoc busUnitVatLoc = new BusUnitVatLoc();
			Country country = new Country();
			country.setName("United States of America");
			busUnitVatLoc.setCountry(country);


			busUnitVatLoc.setDefaultLocInd(false);
			busUnitVatLocs[vatCount]  = busUnitVatLoc;

	  }


		addBusinessUnit.setBusUnitVatLocs(busUnitVatLocs);

		// START : #119240 : 29/06/09
		BusinessUnitSearch businessUnitSearch = null;
	    HttpSession session = request.getSession();
	    if(session != null)
	    {
	    	if(session.getAttribute("businessUnitSearch")!=null){
	    	//	System.out.println("Session : BusinessUnitSearch :--- "+ session.getAttribute("businessUnitSearch"));
	    		businessUnitSearch = (BusinessUnitSearch)session.getAttribute("businessUnitSearch");
			}
	    }
	    addBusinessUnit.setBusinessUnitSearch(businessUnitSearch);
			
		// END : #119240 : 29/06/09
		
    return addBusinessUnit;

  }

  private BusUnitVatLoc[] addBusUnitVatLoc(BusUnitVatLoc[] vatLocs)
  {
	  
		BusUnitVatLoc vatLoc = new BusUnitVatLoc();
		BusUnitVatLocId vatLocId = new BusUnitVatLocId();
		Country country = SecurityUtil.getUser().getCountry();
		//if(country == null)
			//country = new Country();
		//country.setName("United States of America");
		//country.setName(SecurityUtil.getUser().getCountry().getName());
		vatLoc.setCountry(country);
		vatLocId.setCountryCode(country.getCountryCode());
		System.out.println("vatloc country :"+country.getCountryCode());
		vatLoc.setBusUnitVatLocId(vatLocId);
		vatLoc.setOutOfScope("false");
		

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
  
  // START : #119240 : 29/06/09
  
public String  getPrevOrNextBU(String currentName, BusinessUnitSearch businessUnitSearch, boolean prev){
	  
	  if(currentName == null || businessUnitSearch == null) 
		  return null;
	  
	  UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
	  List<BusinessUnit> results =  businessUnitSearch.getResults();
	  
	  if(results == null) return null;

	    for(int i=0; i<results.size(); i++)
	    {
	    	BusinessUnit businessUnit = (BusinessUnit)results.get(i);
	    	if(businessUnit.getName().equals(currentName))
	    	{
	    		if(prev)
	    		{
	    			if(i == 0)
	    			{
	    				  int currentPageNum = businessUnitSearch.getPagination().getCurrentPageNum();
	    		            if(currentPageNum > 1)
	    		            {
	    		            	businessUnitSearch.getPagination().setCurrentPageNum(currentPageNum - 1);
	    		            	userService.searchBusinessUnit(businessUnitSearch,businessUnitSearch.getSortFlag());

		   		              	List newResults = businessUnitSearch.getResults();
		   		              	if(newResults == null) return null;
	
		   		              	if(newResults.size() == 0) return null;
	
		    		            return ((BusinessUnit)newResults.get(newResults.size() - 1)).getName();
	    		            }
	    		            else
	    		            {
	    		              return null;
	    		            }
	    			}
	    			return ((BusinessUnit)results.get(i-1)).getName();
	    		}else{
	    			
	    			if(i == results.size() - 1)
	    			{
	   				  	int totalPages = businessUnitSearch.getPagination().getTotalPages();
	    		        int currentPageNum = businessUnitSearch.getPagination().getCurrentPageNum();
	    		        if(currentPageNum < totalPages)
	    		        {
	    		        	businessUnitSearch.getPagination().setCurrentPageNum(currentPageNum + 1);
	    		        	userService.searchBusinessUnit(businessUnitSearch,businessUnitSearch.getSortFlag());
	    		        	List newResults = businessUnitSearch.getResults();
	    		        	if(newResults == null) return null;
	
    		              	if(newResults.size() == 0) return null;

    		              	return ((BusinessUnit)newResults.get(0)).getName();
	    		        }else
	    		        {
	    		        	return null;
	    		        }
	    			}
	    			
	    			return ((BusinessUnit)results.get(i+1)).getName();	    			
	    		}
	    	}	    	
	    }
	  
	  return null;
  }
  // END : #119240 : 29/06/09

}
