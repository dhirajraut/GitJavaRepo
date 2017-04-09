package com.intertek.web.controller.shippingagent;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import com.intertek.domain.AddShippingAgent;
import com.intertek.entity.ShippingAgent;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.ShippingAgentService;

public class CreateNewShippingAgentPopupController extends SimpleFormController {
	public CreateNewShippingAgentPopupController() {
		super();
		setCommandClass(AddShippingAgent.class);
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

		AddShippingAgent addShippingAgent = (AddShippingAgent) command;
		ShippingAgent shippingAgent = addShippingAgent.getShippingAgent();

		String name = shippingAgent.getName();
		String phone = shippingAgent.getPhone();
		String countryCode = shippingAgent.getCountryCode();
		String stateCode = shippingAgent.getStateCode();
		
		if(shippingAgent.getEmail()!= null && !shippingAgent.getEmail().equals(""))
		{
			if(shippingAgent.getEmail().indexOf('@')==-1)
			{
				errors.reject("create.shippingAgent.error",
						new Object[] { "shiiping agent email does not have @ symbol: "
								+ shippingAgent.getEmail() }, null);
				return showForm(request, response, errors);
			}
			
		}
		if (name == null || name.trim().equals("") || phone == null
				|| phone.trim().equals("") || stateCode == null
				|| stateCode.trim().equals("") || countryCode == null
				|| countryCode.trim().equals("")) {

			if (name == null || name.trim().equals("")) {

				errors.reject("create.shippingAgent.error",
						new Object[] { "shipping agent name cannot be null : "
								+ shippingAgent.getName() }, null);

			}

			if (phone == null || phone.trim().equals("")) {

				errors
						.reject(
								"create.shippingAgent.error",
								new Object[] { "shipping agent telephonenumber cannot be null : "
										+ shippingAgent.getPhone() }, null);

			}
			if (stateCode == null || stateCode.trim().equals("")) {

				errors
						.reject(
								"create.shippingAgent.error",
								new Object[] { "shipping agent statecode cannot be null : "
										+ shippingAgent.getStateCode() }, null);

			}
			if (countryCode == null || countryCode.trim().equals("")) {

				errors
						.reject(
								"create.shippingAgent.error",
								new Object[] { "shipping agent countrycode cannot be null : "
										+ shippingAgent.getCountryCode() },
								null);

			}
			return showForm(request, response, errors);
		}

		ShippingAgentService shippingAgentService = (ShippingAgentService) ServiceLocator
				.getInstance().getBean("shippingAgentService");
		try {
			shippingAgent = shippingAgentService
					.addShippingAgent(shippingAgent);
		} catch(ServiceException e)
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
	    }/* catch (Exception e) {
			e.printStackTrace();

			errors.reject("create.shippingAgent.error", new Object[] { e
					.getMessage() }, null);
			return showForm(request, response, errors);
		}*/

		return new ModelAndView(
				new RedirectView("create_new_shippingagent.htm"), "id",
				shippingAgent.getId());

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

		AddShippingAgent addShippingAgent = new AddShippingAgent();

		ShippingAgent shippingAgent = new ShippingAgent();

		addShippingAgent.setShippingAgent(shippingAgent);

		String shippingAgentId = request.getParameter("id");
		String inputFieldId = request.getParameter("inputFieldId");
		String countryCode = request.getParameter("countryCode");
		
		
		System.out.println("Id value=" + shippingAgentId);
		System.out.println("countryvalue=" + countryCode);
		shippingAgent.setCountryCode(countryCode);
		try {
		if (inputFieldId != null && (!inputFieldId.trim().equals("")))
			addShippingAgent.setInputFieldId(inputFieldId);
		else
			addShippingAgent.setInputFieldId("jobOrder.shippingAgent.name");
		if (shippingAgentId != null)
		{
			addShippingAgent.setOkButton("ok");
			ShippingAgentService shippingAgentService = (ShippingAgentService) ServiceLocator
					.getInstance().getBean("shippingAgentService");
			
				   shippingAgent = shippingAgentService.getShippingAgentById(new Long(shippingAgentId));
			
			addShippingAgent.setShippingAgent(shippingAgent);
		}else
			addShippingAgent.setOkButton(null);
		
		} catch(ServiceException e)
	    {
	    	throw new ServiceException(e.getMessage(), e.getParams(), e);
	    }
	    catch(Throwable t)
	    {
	        t.printStackTrace();
	        throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
	    }
		return addShippingAgent;

	}
}
