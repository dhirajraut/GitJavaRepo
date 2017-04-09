package com.intertek.web.controller.country;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import com.intertek.exception.ServiceException;
import com.intertek.domain.AddCountry;
import com.intertek.entity.Country;
import com.intertek.entity.CountryVAT;
import com.intertek.entity.CountryVATId;
import com.intertek.entity.State;
import com.intertek.entity.StateId;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.CountryService;
import com.intertek.web.controller.BaseSimpleFormController;
import com.intertek.web.propertyeditors.CustomPrimitiveNumberEditor;
//public class CreateCountryFormController extends SimpleFormController
public class CreateCountryFormController extends BaseSimpleFormController
{
  public CreateCountryFormController() {
    super();
	//setSessionForm(true);
    setCommandClass(AddCountry.class);
	
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


    AddCountry addCountry = (AddCountry)command;
	Country country       = addCountry.getCountry();
	
	String addOrDeleteState = request.getParameter("addOrDeleteState");
	
	String indexs = request.getParameter("stateIndex");
	String addOrDeleteCountryVats = request.getParameter("addOrDeleteCountryVats");
    String conutryVatIndexs = request.getParameter("countryVatIndex");

	
	
	if((addOrDeleteState != null) && ("add".equals(addOrDeleteState) || "delete".equals(addOrDeleteState)))
    {
		
			if("add".equals(addOrDeleteState))
			{
				addCountry.setStates(addStates(addCountry.getStates()));
			}
			else
			{
				addCountry.setStates(deleteStates(addCountry.getStates(), addCountry.getStateIndex()));
			}
		  addCountry.setStateCount(addCountry.getStates().length);

		  addCountry.setAddOrDeleteState("none");
		  addCountry.setTabName("2");
		
		  
      return showForm(request, response, errors);
    } 

	if((addOrDeleteCountryVats != null) && ("add".equals(addOrDeleteCountryVats) || "delete".equals(addOrDeleteCountryVats)))
    {
			if("add".equals(addOrDeleteCountryVats))
			{
				addCountry.setCountryVats(addCountryVats(addCountry.getCountryVats()));
			}
			else
			{
				addCountry.setCountryVats(deleteCountryVats(addCountry.getCountryVats(), addCountry.getCountryVatIndex()));
			}
		  addCountry.setCountryVatIndex(addCountry.getCountryVats().length);

		  addCountry.setAddOrDeleteCountryVats("none");
		  addCountry.setTabName("0");
		
		  
      return showForm(request, response, errors);
    } 

	State[] statesItems = addCountry.getStates();
        
		if (statesItems  != null )
		{
			for(int i=0; i< statesItems.length; i++)
			{
				State state = statesItems[i];
				state.setCountry(country);
				state.getStateId().setCountryCode(country.getCountryCode());
				if(state.getName() != null && (!state.getName().trim().equals("")))
				country.getStates().add(state);
			}
		}
    CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");
try 
  { 
	  country = countryService.addCountry(country);
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

 
	 return new ModelAndView(new RedirectView("edit_country.htm"), "countryCode", country.getCountryCode());
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
	NumberFormat nf = NumberFormat.getInstance();
    CustomPrimitiveNumberEditor longEditor = new CustomPrimitiveNumberEditor(Long.class, nf, Long.valueOf(0));
    binder.registerCustomEditor(Long.class, "country.state.numericCd", longEditor);
   
  }

   protected boolean suppressValidation(HttpServletRequest request)
  {
    String addOrDelete = request.getParameter("addOrDeleteState");
	String addOrDeleteCountryVat= request.getParameter("addOrDeleteCountryVats");
    
    if((addOrDelete != null) && ("add".equals(addOrDelete) || "delete".equals(addOrDelete)))
    {
      return true;
    }
   if((addOrDeleteCountryVat!= null) && ("add".equals(addOrDeleteCountryVat) || "delete".equals(addOrDeleteCountryVat)))
    {
      return true;
    }
   
    return super.suppressValidation(request);
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
   */
  protected Object formBackingObject(
    HttpServletRequest request
  ) throws Exception
  {
	   
		String countStr = request.getParameter("stateCount");

		AddCountry addCountry		= new AddCountry();
		Country country				= new Country();
		State state					= new State();
		StateId stateId				= new StateId();
		CountryVAT countryVAT       = new CountryVAT();
		CountryVATId countryVATId   = new CountryVATId();
		
		if( countStr == null || countStr.trim().equals("") )
			  countStr = "0";

			int count = 0;
			try
			{
				count = new Integer(countStr).intValue();
			}
			catch(Exception e) { }

			 
           

			 
			 addCountry.setStateCount(count);
			
			 State[] states = new State[count];
			
			 for(int i=0; i<count; i++)
			{
				states[i] = state;
			}
			 countryVAT.setCountry(country);
			  addCountry.setCountryVatsCount(count);
		     CountryVAT[] countryVats = new CountryVAT[count];
		   for(int i=0; i<count; i++)
			{
				countryVats[i] = countryVAT;
			}
		
		addCountry.setStates(states); 
		state.setStateId(stateId);
		state.setCountry(country);

		addCountry.setCountry(country);
	
		addCountry.setCountryVats(countryVats); 
		countryVAT.setCountryVATId(countryVATId);
		countryVAT.setCountry(country);

		addCountry.setCountry(country);
	
		// START : #119240 : 26/06/09
		HttpSession session = request.getSession();
		if(session.getAttribute("countrySearch")!=null)
			session.removeAttribute("countrySearch");
		// END : #119240 : 26/06/09
		
		return addCountry;
  }

 private State[] addStates(State[] states){
		State state     = new State();
		StateId stateId = new StateId();

		state.setStateId(stateId); 
		State[] results = null;
		
		if(states == null) results = new State[1];
		else results = new State[states.length + 1];

		if(states != null)
		{
		for(int i=0; i<states.length; i++)
		{
			results[i] = states[i];
		}
		}

		results[results.length - 1] = state;

		return results;
	}

  private State[] deleteStates(State[] states, int index){
		if(states == null) return null;
		if(states.length <= 0) return states;

		State[] results = new State[states.length - 1];

		int count = 0;
		for(int i=0; i<states.length; i++)
		{
			if(i == index) continue;
			results[count] = states[i];

			count ++;
		}

		return results;
	}
 private CountryVAT[] addCountryVats(CountryVAT[] countryVats){
		
		CountryVAT countryVAT = new CountryVAT();
		
        CountryVATId countryVATId = new CountryVATId();
		countryVAT.setCountryVATId(countryVATId); 
		CountryVAT[] results = null;
		
		if(countryVats == null) results = new CountryVAT[1];
		else results = new CountryVAT[countryVats.length + 1];
		if(countryVats != null)
		{
		for(int i=0; i<countryVats.length; i++)
		{
			results[i] = countryVats[i];
		}
		}

		results[results.length - 1] = countryVAT;
		return results;
	}

  private CountryVAT[] deleteCountryVats(CountryVAT[] countryVats, int index){
		
		if(countryVats == null) return null;
		if(countryVats.length <= 0) return countryVats;

		CountryVAT[] results = new CountryVAT[countryVats.length - 1];

		int count = 0;
		for(int i=0; i<countryVats.length; i++)
		{
			if(i == index) continue;
			results[count] = countryVats[i];

			count ++;
		}

		return results;
	}

}
