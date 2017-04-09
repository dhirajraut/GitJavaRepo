/**
 * 
 */
package com.lnt.rm.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.lnt.rm.commands.RMLoginCommand;
import com.lnt.rm.services.RMServiceInterface;
import com.lnt.rm.utils.LoggerUtil;

/**
 * @author 284773
 *
 */
public class RMLoginController extends SimpleFormController {

	private RMServiceInterface service = null;

	public RMServiceInterface getService() {
		return service;
	}

	public void setService(RMServiceInterface service) {
		this.service = service;
	}

	/* This method gets called when the user submits the page.
	 * One more requirement is, the form should use 'Post' method.
	 * If the form uses 'Get' method, still you want to use onSubmit,
	 * you should override 'isFormSubmission(request)' method.
	 * */
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		LoggerUtil.log(this, "Entering onSubmit");
		if (null != service) {
			service.execute();
		}
		LoggerUtil.log(this, "Exiting onSubmit");
		return showForm(request, response, errors);
		//return new ModelAndView("loginview");
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		LoggerUtil.log(this, "Entering formBackingObject");
		return new RMLoginCommand();
	}

	/**
	 * @Todo Validations.
	 */
	protected boolean suppressValidation(HttpServletRequest request,
			Object command) {
		return true;
	}

	/** 
	 * When the user submits the page, on submit method gets called,
	 * if the form uses 'Post' method.
	 * If the form uses 'Get' method, still you want to use onSubmit,
	 * you should override 'isFormSubmission(request)' method.
	 * */
	protected boolean isFormSubmission(HttpServletRequest request) {
		return true;
	}
	
	// Override processFormSubmission @ Todo
}
