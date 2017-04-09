package com.intertek.web.controller.country;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.intertek.domain.AddCountry;
import com.intertek.domain.CountrySearch;
import com.intertek.entity.Country;
import com.intertek.entity.CountryVAT;
import com.intertek.entity.CountryVATId;
import com.intertek.entity.State;
import com.intertek.entity.StateId;
import com.intertek.entity.TaxCode;
import com.intertek.entity.User;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.CountryService;
import com.intertek.service.TaxService;
import com.intertek.service.UserService;
import com.intertek.util.ContractUtil;
import com.intertek.util.CountryUtil;
import com.intertek.web.controller.BaseSimpleFormController;
import com.intertek.web.propertyeditors.CustomPrimitiveNumberEditor;

//public class EditCountryFormController extends SimpleFormController 
public class EditCountryFormController extends BaseSimpleFormController{
	public EditCountryFormController() {
		super();
		setSessionForm(true);
		setCommandClass(AddCountry.class);

	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		AddCountry addCountry = (AddCountry) command;
		Country country = addCountry.getCountry();
		CountryVAT countryVAT=null;
		String page = addCountry.getTabName();
		
		String addOrDeleteState = request.getParameter("addOrDeleteState");
		String indexs = request.getParameter("stateIndex");
		String addOrDeleteCountryVats = request
				.getParameter("addOrDeleteCountryVats");
		String conutryVatIndexs = request.getParameter("countryVatIndex");

		if ((addOrDeleteState != null)
				&& ("add".equals(addOrDeleteState) || "delete"
						.equals(addOrDeleteState))) {

			if ("add".equals(addOrDeleteState)) {
				addCountry.setStates(addStates(addCountry.getStates()));
			} else {
				addCountry.setStates(deleteStates(addCountry.getStates(),
						addCountry.getStateIndex()));
			}
			addCountry.setStateCount(addCountry.getStates().length);

			addCountry.setAddOrDeleteState("none");
			addCountry.setTabName("2");

			return showForm(request, response, errors);
		}
		if ((addOrDeleteCountryVats != null)
				&& ("add".equals(addOrDeleteCountryVats) || "delete"
						.equals(addOrDeleteCountryVats))) {
			
			if ("add".equals(addOrDeleteCountryVats)) {
				addCountry.setCountryVats(addCountryVats(addCountry
						.getCountryVats()));
			} else {
				addCountry.setCountryVats(deleteCountryVats(addCountry
						.getCountryVats(), addCountry.getCountryVatIndex()));
			}
			addCountry.setCountryVatIndex(addCountry.getCountryVats().length);

			addCountry.setAddOrDeleteCountryVats("none");
			addCountry.setTabName("0");

			return showForm(request, response, errors);
		}
		State[] statesItems = addCountry.getStates();

		if (statesItems != null) {
			country.getStates().clear();
			for (int i = 0; i < statesItems.length; i++) {
				State state = statesItems[i];
				state.setCountry(country);
				state.getStateId().setCountryCode(country.getCountryCode());
				country.getStates().add(state);
			}
		}
		CountryService countryService = (CountryService) ServiceLocator
				.getInstance().getBean("countryService");

		UserService userService = (UserService) ServiceLocator
				.getInstance().getBean("userService");

		TaxService taxService = (TaxService) ServiceLocator
		.getInstance().getBean("taxService");

		CountryVAT[] countryVatsItems = addCountry.getCountryVats();

		if (countryVatsItems != null) {
			country.getCountryVats().clear();
			for (int i = 0; i < countryVatsItems.length; i++) {
				 countryVAT = countryVatsItems[i];

				if (country.getCountryVats() != null
						&& (!countryVAT.getCountryVATId().getStateCode().trim()
								.equals(""))) {
					String countryCode=country.getCountryCode();
					State state=countryService.getStateByCodeAndCountryCode(countryVAT.getCountryVATId()
									.getStateCode(),countryCode);
					TaxCode taxCode=taxService.getTaxCodeByCode(countryVAT.getVatCode());
					TaxCode zeroTaxCode=taxService.getTaxCodeByCode(countryVAT.getZeroRatedVATCode());
					
				
					if (state == null || taxCode == null || zeroTaxCode == null ) {
						if(state == null)
							{
								errors.reject("create.country.error",
									new Object[] { "statecode does not exist : "
											+ countryVAT.getCountryVATId()
													.getStateCode() }, null);
							}
						if(taxCode == null)
							{
								errors.reject("create.country.error",
									new Object[] { "vatcode does not exist : "
											+ countryVAT.getVatCode() }, null);
							
							
							}
						if(zeroTaxCode == null)
							{
								errors.reject("create.country.error",
									new Object[] { "vatcode does not exist : "
											+ countryVAT.getZeroRatedVATCode()}, null);
							}
						return showForm(request, response, errors);
					}
				}
				countryVAT.setCountry(country);
				countryVAT.getCountryVATId().setCountryCode(
						country.getCountryCode());

				if (country.getCountryVats() != null
						&&((!countryVAT.getCountryVATId().getStateCode().trim()
								.equals(""))||(!countryVAT.getVatCode().trim()
								.equals("")) || (!countryVAT.getZeroRatedVATCode().trim()
								.equals(""))))
				country.getCountryVats().add(countryVAT);

			}

		}
		try {
	/*		for (Iterator itr = country.getCountryVats().iterator(); itr
					.hasNext();) {
				countryVAT = (CountryVAT) itr.next();
				System.out.println("Country vat="
						+ countryVAT.getCountryVATId().getStateCode());
				
				if (country.getCountryVats() != null
						&&((!countryVAT.getCountryVATId().getStateCode().trim()
								.equals(""))||(!countryVAT.getCountryVATId().getVatCode().trim()
								.equals("")) || (!countryVAT.getZeroRatedVATCode().trim()
								.equals(""))))
				{*/
				
					countryService.saveCountry(country);
				//}
			//}
			
		}  catch(ServiceException e)
		{
			e.printStackTrace();
			errors.reject(e.getMessage(), e.getParams(), null);
			return showForm(request, response, errors);
		} catch(Throwable t)
		  {
			t.printStackTrace();
			errors.reject("generic.error", new Object[] {t.getMessage()}, null);
			return showForm(request, response, errors);
		  }/* catch (Exception e) {
					e.printStackTrace();

					errors.reject("create.country.error",
							new Object[] { e.getMessage() }, null);
					return showForm(request, response, errors);
				}*/
		
		
		

		// START : #119240 : 17/06/09
		
		/*ModelAndView modelAndView = new ModelAndView("common-message-r");
		modelAndView.addObject("backUrl", "edit_country.htm?countryCode="
				+ country.getCountryCode());
		modelAndView.addObject("backUrlDescription",
				"You can continue to edit this country.");
		modelAndView.addObject("description",
				"Your country has been successfully updated.");*/
		
		String operationType = request.getParameter("operationType");
		String countryCode = null;
		ModelAndView modelAndView = null;
		if(operationType!=null && ("prevCountry".equals(operationType)
				|| "nextCountry".equals(operationType)
			    || "searchCountry".equals(operationType) 
			    || "saveCountry".equals(operationType))   )
		{
			String viewName = null;
			if("searchCountry".equals(operationType))
		    {
				viewName = "search-country-r";
		    }
			
		    if(viewName == null)
		    {
		    	viewName = "edit-country-r"; 
		    }

		    modelAndView = new ModelAndView(viewName);
			
		    if ("prevCountry".equals(operationType) || "nextCountry".equals(operationType)) 
		    {
				countryCode = CountryUtil.getPrevOrNextCountry(country.getCountryCode(), addCountry.getCountrySearch(),
						"prevCountry".equals(operationType));
				if (countryCode == null) {
					if ("prevCountry".equals(operationType)) {
						modelAndView.addObject("saved_message",	"No Previous Country!");
					} else {
						modelAndView.addObject("saved_message",	"No Next Country!");
					}
				}
			}
		    
		    if("saveCountry".equals(operationType))
			{
		    	modelAndView.addObject("saved_message", "Saved successfully");		    	
			}		    
		 }
		
		 if(countryCode == null) 
			 countryCode = country.getCountryCode();
		 modelAndView.addObject("countryCode", countryCode);  
		 modelAndView.addObject("fromEdit", "true");
		 
		// System.out.println("Session : countrySearch :---- " + request.getSession().getAttribute("countrySearch"));
		// END : #119240 : 17/06/09
		 
		 return modelAndView;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest, org.springframework.web.bind.ServletRequestDataBinder)
	 */
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(java.util.Date.class, null,
				new CustomDateEditor(dateFormat, true));

		 NumberFormat nf = NumberFormat.getInstance();
    CustomPrimitiveNumberEditor longEditor = new CustomPrimitiveNumberEditor(Long.class, nf, new Long(0));
    binder.registerCustomEditor(Long.class, "country.state.numericCd", longEditor);
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		CountryService countryService = (CountryService) ServiceLocator
				.getInstance().getBean("countryService");

		Country country = null;

		State state = new State();
		StateId stateId = new StateId();
		AddCountry addCountry = new AddCountry();
		CountryVAT countryVAT = new CountryVAT();
		CountryVATId countryVATId = new CountryVATId();

		String inputFieldId = request.getParameter("inputFieldId");
		String rowNum = request.getParameter("rowNum");
		String countStr = request.getParameter("countryVatsCount");
		String countryCode = request.getParameter("countryCode");

		addCountry.setRowNum(rowNum);
	try 
    { 
		country = countryService.getCountryByCode(countryCode);
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
		if (countStr == null || countStr.trim().equals(""))
			countStr = "0";

		int count = 0;
		try {
			count = new Integer(countStr).intValue();
		} catch (Exception e) {
		}

		addCountry.setCountryVatsCount(count);
		CountryVAT[] countryVats = new CountryVAT[count];

		for (int i = 0; i < count; i++) {
			countryVats[i] = countryVAT;
		}
		if (country != null) {
			int i = 0;
			if(country.getStates()!= null)
			{
				State[] states = new State[(country.getStates()).size()];
				for (Iterator itr = (country.getStates()).iterator(); itr.hasNext();) {
					
					states[i] = (State) itr.next();
					i++;
				}
				addCountry.setStates(states);
			}else
			{
				State[] st = new State[0];
				addCountry.setStates(st);
			}
			if(country.getCountryVats() != null)
			{
				i = 0;
				CountryVAT[] countryVat = new CountryVAT[(country.getCountryVats())
						.size()];
				for (Iterator itr = (country.getCountryVats()).iterator(); itr
						.hasNext();) {
				
					countryVat[i] = (CountryVAT) itr.next();
					i++;
				}
	
				
				addCountry.setCountryVats(countryVat);
			}else
			{
				CountryVAT[] countryVat = new CountryVAT[0];
				addCountry.setCountryVats(countryVat);
			}
		}else
			country = new Country();	

		state.setStateId(stateId);
		state.setCountry(country);

		countryVAT.setCountryVATId(countryVATId);
		countryVAT.setCountry(country);

		addCountry.setCountry(country);
		
		// START : #119240 : 17/06/09
		CountrySearch countrySearch = null;
	    HttpSession session = request.getSession();
	    if(session != null)
	    {
	    	if(session.getAttribute("countrySearch")!=null){
	    	//	System.out.println("Session : CountrySearch :--- "+ session.getAttribute("countrySearch"));
				countrySearch = (CountrySearch)session.getAttribute("countrySearch");
			}
	    }
		addCountry.setCountrySearch(countrySearch);
		// END : #119240 : 17/06/09

		return addCountry;
	}

	private State[] addStates(State[] states) {
		State state = new State();
		StateId stateId = new StateId();

		state.setStateId(stateId);
		State[] results = null;

		if (states == null)
			results = new State[1];
		else
			results = new State[states.length + 1];

		for (int i = 0; i < states.length; i++) {
			results[i] = states[i];
		}

		results[results.length - 1] = state;

		return results;
	}

	private State[] deleteStates(State[] states, int index) {

		if (states == null)
			return null;
		if (states.length <= 0)
			return states;

		State[] results = new State[states.length - 1];

		int count = 0;
		for (int i = 0; i < states.length; i++) {
			if (i == index)
				continue;
			results[count] = states[i];

			count++;
		}

		return results;
	}

	private CountryVAT[] addCountryVats(CountryVAT[] countryVats) {

		CountryVAT countryVAT = new CountryVAT();

		CountryVATId countryVATId = new CountryVATId();
	
		countryVAT.setCountryVATId(countryVATId);
		CountryVAT[] results = null;

		if (countryVats == null)
			results = new CountryVAT[1];
		else
			results = new CountryVAT[countryVats.length + 1];

		for (int i = 0; i < countryVats.length; i++) {
			results[i] = countryVats[i];
		}

		results[results.length - 1] = countryVAT;
	
		return results;
	}

	private CountryVAT[] deleteCountryVats(CountryVAT[] countryVats, int index) {

		if (countryVats == null)
			return null;
		if (countryVats.length <= 0)
			return countryVats;

		CountryVAT[] results = new CountryVAT[countryVats.length - 1];

		int count = 0;
		for (int i = 0; i < countryVats.length; i++) {
			if (i == index)
				continue;
			results[count] = countryVats[i];

			count++;
		}

		return results;
	}
}
