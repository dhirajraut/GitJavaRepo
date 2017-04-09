package com.intertek.web.controller.customer;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.domain.AddContact;
import com.intertek.entity.Contact;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.CustomerService;

public class ViewContactPopupController extends SimpleFormController {
	public ViewContactPopupController() {
		super();
		//setSessionForm(true);
		setCommandClass(AddContact.class);

	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		AddContact addContact = (AddContact) command;
		Contact contact = addContact.getContact();
		ModelAndView modelAndView = new ModelAndView("common-messagepopup-r");
		return modelAndView;
		// return showForm(request, response, errors);
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest, org.springframework.web.bind.ServletRequestDataBinder)
	 */
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(java.util.Date.class, null,new CustomDateEditor(dateFormat, true));
	}

	protected boolean suppressValidation(HttpServletRequest request) {
		String addOrDelete = request.getParameter("addOrDelete");
		if ((addOrDelete != null)	&& ("add".equals(addOrDelete) || "delete".equals(addOrDelete))) {
			return true;
		}

		return super.suppressValidation(request);
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		String contactId = request.getParameter("contactId");
		Contact contact = new Contact();
		AddContact addContact = new AddContact();
		
	
		String div1=request.getParameter("div1");   
		 String div2=request.getParameter("div2");
		 addContact.setDiv1(div1);
		 addContact.setDiv2(div2);

		CustomerService customerService = (CustomerService)ServiceLocator.getInstance().getBean("customerService");
    try
    {
      contact = customerService.getContactById(new Long(contactId));

    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

    if(contact == null)
    {
      contact = new Contact();
    }

		addContact.setContact(contact);
		return addContact;
	}
}
