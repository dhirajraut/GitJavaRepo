package com.intertek.web.controller.towingcompany;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import com.intertek.domain.AddTowingCompany;
import com.intertek.entity.TowingCompany;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.TowingCompanyService;

public class CreateNewTowingCompanyPopupController extends SimpleFormController {
	public CreateNewTowingCompanyPopupController() {
		super();
		setCommandClass(AddTowingCompany.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      org.springframework.validation.BindException)
	 */
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		AddTowingCompany addTowingCompany = (AddTowingCompany) command;
		TowingCompany towingCompany = addTowingCompany.getTowingCompany();

		String name = towingCompany.getName();
		String phone = towingCompany.getPhone();
		String countryCode = towingCompany.getCountryCode();
		String stateCode = towingCompany.getStateCode();
		
		if(towingCompany.getEmail()!= null && !towingCompany.getEmail().equals(""))
		{
			if(towingCompany.getEmail().indexOf('@')==-1)
			{
				errors.reject("create.towingCompany.error",
						new Object[] { "towing company email does not have @ symbol: "
								+ towingCompany.getEmail() }, null);
				return showForm(request, response, errors);
			}
			
		}

		if (name == null || name.trim().equals("") || phone == null
				|| phone.trim().equals("") || stateCode == null
				|| stateCode.trim().equals("") || countryCode == null
				|| countryCode.trim().equals("")) {
			if (name == null || name.trim().equals("")) {

				errors.reject("create.towingCompany.error",
						new Object[] { "towing company name cannot be null : "
								+ towingCompany.getName() }, null);

			}

			if (phone == null || phone.trim().equals("")) {

				errors
						.reject(
								"create.towingCompany.error",
								new Object[] { "towing company telephonenumber cannot be null : "
										+ towingCompany.getPhone() }, null);

			}
			if (stateCode == null || stateCode.trim().equals("")) {

				errors
						.reject(
								"create.towingCompany.error",
								new Object[] { "towing company statecode cannot be null : "
										+ towingCompany.getStateCode() }, null);

			}
			if (countryCode == null || countryCode.trim().equals("")) {

				errors
						.reject(
								"create.towingCompany.error",
								new Object[] { "towing company countrycode cannot be null : "
										+ towingCompany.getCountryCode() },
								null);

			}
			return showForm(request, response, errors);
		}

		TowingCompanyService towingCompanyService = (TowingCompanyService) ServiceLocator
				.getInstance().getBean("towingCompanyService");
		try {
			towingCompany = towingCompanyService
					.addTowingCompany(towingCompany);
		} catch(ServiceException e)
		{
			  e.printStackTrace();
			  errors.reject(e.getMessage(), e.getParams(), null);
			  return showForm(request, response, errors);
		} catch(Throwable t)
		{
			  t.printStackTrace();
			  errors.reject("generic.error", new Object[] {t.getMessage()}, null);
			  return showForm(request, response, errors);
		}
		/* catch (Exception e) {
			e.printStackTrace();

			errors.reject("create.towingCompany.error", new Object[] { e
					.getMessage() }, null);
			return showForm(request, response, errors);
		}*/
		
	/*	if(addTowingCompany.getOkButton()==null)
		{
			System.out.println("setting ok button");
			addTowingCompany.setOkButton("ok");

		}*/
		return new ModelAndView(
				new RedirectView("create_new_towingcompany.htm"), "id",
				towingCompany.getId());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest,
	 *      org.springframework.web.bind.ServletRequestDataBinder)
	 */
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(java.util.Date.class, null,
				new CustomDateEditor(dateFormat, true));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		AddTowingCompany addTowingCompany = new AddTowingCompany();

		TowingCompany towingCompany = new TowingCompany();
		addTowingCompany.setTowingCompany(towingCompany);

		String towingCompanyId = request.getParameter("id");
		String inputFieldId = request.getParameter("inputFieldId");
		String country = request.getParameter("country");
		String countryCode = request.getParameter("countryCode");
		System.out.println("Id value=" + towingCompanyId);
		System.out.println("country value=" + country);
		towingCompany.setCountryCode(countryCode);
		if (inputFieldId != null && (!inputFieldId.trim().equals("")))
			addTowingCompany.setInputFieldId(inputFieldId);
		else
			addTowingCompany.setInputFieldId("jobOrder.towingCompany.name");
		if (towingCompanyId != null)
		{
			addTowingCompany.setOkButton("ok");
			TowingCompanyService towingCompanyService = (TowingCompanyService) ServiceLocator
					.getInstance().getBean("towingCompanyService");
			try {
				towingCompany = towingCompanyService
						.getTowingCompanyById(new Long(towingCompanyId));
			} catch(ServiceException e)
		    {
		    	throw new ServiceException(e.getMessage(), e.getParams(), e);
		    }
		    catch(Throwable t)
		    {
		        t.printStackTrace();
		        throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
		    }
			
			addTowingCompany.setTowingCompany(towingCompany);
		}else
			addTowingCompany.setOkButton(null);
		return addTowingCompany;
	}
}
