package com.intertek.web.controller.customer;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.domain.AddContact;
import com.intertek.entity.Contact;
import com.intertek.entity.ContactCust;
import com.intertek.entity.ContactCustId;
import com.intertek.entity.CustAddrSeq;
import com.intertek.entity.CustAddrSeqId;
import com.intertek.entity.Customer;
import com.intertek.util.*;


public class CreateNewContactPopupController extends SimpleFormController {
	public CreateNewContactPopupController() {
		super();
		setSessionForm(true);
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
		CommonUtil commonUtil=null;
		
		if(contact.getFirstName() == null || contact.getFirstName().trim().equals(""))
		{
			errors.reject("contact.firstname.error",new Object[] {}, null);	
			return showForm(request, response, errors);
		}
		if(contact.getLastName() == null || contact.getLastName().trim().equals(""))
		{
			errors.reject("contact.lastname.error",new Object[] {}, null);	
			return showForm(request, response, errors);
		}		
		if(contact.getWorkEmail()!= null && !(contact.getWorkEmail().equals("")))
		{
		    commonUtil= new CommonUtil();
			boolean emailvalid =commonUtil.validateEmail(contact.getWorkEmail()); 
			//if(contact.getWorkEmail().indexOf('@') == -1)
			if(emailvalid == false)
			{
				errors.reject("contact.workemail.error", new Object[] {contact.getWorkEmail() }, null);
				return showForm(request, response, errors);
			}
			
		}
		
		if(contact.getPersonalEmail()!= null && !(contact.getPersonalEmail().equals("")))
		{
			commonUtil= new CommonUtil();
			boolean emailvalid =commonUtil.validateEmail(contact.getPersonalEmail()); 
			//if(contact.getPersonalEmail().indexOf('@') == -1)
			if(emailvalid == false)
			{
				errors.reject("contact.personalemail.error", new Object[] {contact.getPersonalEmail() }, null);
				return showForm(request, response, errors);
			}
			
	    }
		
		 if(contact.getWorkPhone()!= null && !(contact.getWorkPhone().equals("")))
		  {
		      commonUtil= new CommonUtil();		     
		    boolean phonevalid =commonUtil.validatePhone(contact.getWorkPhone());
		    if(phonevalid == false)
		    {
		      errors.reject("contact.workphone.error", new Object[] {contact.getWorkPhone() }, null);
		      return showForm(request, response, errors);
		    }

		  }
		
		 if(contact.getPersonalPhone()!= null && !(contact.getPersonalPhone().equals("")))
		  {
		      commonUtil= new CommonUtil();
		   
		    boolean phonevalid =commonUtil.validatePhone(contact.getPersonalPhone());
		        if(phonevalid == false)
		    {
		      errors.reject("contact.personalphone.error", new Object[] {contact.getPersonalPhone() }, null);
		      return showForm(request, response, errors);
		    }

		  }
		 
		 if(contact.getCellPhone()!= null && !(contact.getCellPhone().equals("")))
		  {
		    commonUtil= new CommonUtil();		    
		    boolean phonevalid =commonUtil.validatePhone(contact.getCellPhone());
		    if(phonevalid == false)
		    {
		      errors.reject("contact.cellphone.error", new Object[] {contact.getCellPhone() }, null);
		      return showForm(request, response, errors);
		    }

		  }

		   //added for the issue-104407
		   if(contact.getFax() != null && !(contact.getFax().equals(""))){
		  	 	commonUtil= new CommonUtil();
		  	    boolean faxvalid =commonUtil.validateFax(contact.getFax());
		  	    if(faxvalid == false)
		  	    {
		  	      errors.reject("contact.fax.error", new Object[] {contact.getFax() }, null);
		  	      return showForm(request, response, errors);
		  	    }
		   }
		
		//Commenting out save as we need to save the contact only after user saves the  customer entity on main edit customer screen
		
		/*
		CustomerService customerService = (CustomerService) ServiceLocator	.getInstance().getBean("customerService");
		try {
			contact = customerService.addContact(contact);
		} catch (Exception e) {
			e.printStackTrace();

			errors.reject("create.contact.error", new Object[] { e.getMessage() }, null);
			return showForm(request, response, errors);
		}

		*/

		ModelAndView modelAndView = new ModelAndView("common-messagepopup-r");

		String inputfield = String.valueOf(addContact.getInputFieldId());
		String id = String.valueOf(contact.getId());
		String divparam = "newcontact" + addContact.getRowNum();
		String divparambody = "newcontactbody" + addContact.getRowNum();


		modelAndView.addObject("param1", addContact.getInputFieldId());
		modelAndView.addObject("param2", contact.getId());
		modelAndView.addObject("param3", divparam);
		modelAndView.addObject("param4", divparambody);
		modelAndView.addObject("searchForm", addContact.getSearchForm());
		modelAndView.addObject("description",	"Your contact has been successfully created.");
		
	    HttpSession session = request.getSession();
	    session.setAttribute("EditCustomerFormController.NewContact", contact);
		return modelAndView;
		// return showForm(request, response, errors);
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest, org.springframework.web.bind.ServletRequestDataBinder)
	 */
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {

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

		String contactId = request.getParameter("contactid");
		Contact contact = new Contact();
		AddContact addContact = new AddContact();
		String inputFieldId = request.getParameter("inputFieldId");
		String vatflag = request.getParameter("vatRateFlag");
		String rowNum = request.getParameter("rowNum");
		String searchForm = request.getParameter("searchForm");
		addContact.setSearchForm(searchForm);
		addContact.setInputFieldId(inputFieldId);
		addContact.setRowNum(rowNum);
		addContact.setRowNum(rowNum);

		String countStr = request.getParameter("contactCustCount");
		if (contactId != null && !contactId.equals("")) {
			long id = (Long.valueOf(contactId)).longValue();
			addContact.setId(id);
		}

		if (countStr == null || countStr.trim().equals(""))
			countStr = "0";

		int count = 0;
		try {
			count = new Integer(countStr).intValue();
		} catch (Exception e) {
		}

		addContact.setContactCustCount(count);
		ContactCust[] contactCusts = new ContactCust[count];
		for (int i = 0; i < count; i++) {
			ContactCust contactCust = new ContactCust();
			Customer customer = new Customer();
			CustAddrSeq custAddrSeq = new CustAddrSeq();
			contactCust.setCustomer(customer);
			contactCust.setCustAddrSeq(custAddrSeq);

			contactCusts[i] = contactCust;
		}
		addContact.setContactCusts(contactCusts);

		addContact.setContact(contact);
		addContact.setTabName("2");
		return addContact;
	}

	private ContactCust[] addContactCust(ContactCust[] contactCusts) {
		ContactCust contactCust = new ContactCust();
		ContactCustId contactCustId = new ContactCustId();
		contactCust.setContactCustId(contactCustId);
		Customer customer = new Customer();
		CustAddrSeq custAddrSeq = new CustAddrSeq();
		CustAddrSeqId custAddrSeqId = new CustAddrSeqId();
		custAddrSeq.setCustAddrSeqId(custAddrSeqId);
		contactCust.setCustomer(customer);
		contactCust.setCustAddrSeq(custAddrSeq);

		ContactCust[] results = null;
		if (contactCusts == null)
			results = new ContactCust[1];
		else
		{
			results = new ContactCust[contactCusts.length + 1];
			for (int i = 0; i < contactCusts.length; i++) {
			results[i] = contactCusts[i];
			}
		}

		results[results.length - 1] = contactCust;

		return results;
	}

	private ContactCust[] deleteContactCust(ContactCust[] contactCusts,
			int index) {
		if (contactCusts == null)
			return null;
		if (contactCusts.length <= 0)
			return contactCusts;

		ContactCust[] results = new ContactCust[contactCusts.length - 1];

		int count = 0;
		for (int i = 0; i < contactCusts.length; i++) {
			if (i == index)
				continue;
			results[count] = contactCusts[i];

			count++;
		}

		return results;
	}

}
