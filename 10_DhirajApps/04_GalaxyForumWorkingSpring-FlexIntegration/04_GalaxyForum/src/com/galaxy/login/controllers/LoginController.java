package com.galaxy.login.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.InternalResourceView;

import com.galaxy.login.command.LoginCommand;
import com.galaxy.login.impls.IService;
import com.galaxy.utils.XMLView;

public class LoginController extends SimpleFormController {

	private IService service = null;

	public IService getService() {
		return service;
	}

	public void setService(IService service) {
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

		System.out.println(this.getClass().getName() + ": onSubmit" + isFormSubmission(request));
		System.out.println("param1 = " + request.getParameter("param1"));
		service.execute();
//		View view = (View) getApplicationContext().getBean("xmlView");
		View view = new XMLView();
		Map<String, String> model = new HashMap<String, String>();
		model.put("Content", "<name>Dhiraj</name>");
		return new ModelAndView(view, model);
//		return new ModelAndView(new InternalResourceView("main.html"));
//		return showForm(request, response, errors);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		System.out.println(this.getClass().getName() + ": formBackingObject");
		return new LoginCommand();
	}

	protected boolean suppressValidation(HttpServletRequest request,
			Object command) {
		return true;
	}

	/* When the user submits the page, on submit method gets called,
	 * if the form uses 'Post' method.
	 * If the form uses 'Get' method, still you want to use onSubmit,
	 * you should override 'isFormSubmission(request)' method.
	 * */
	protected boolean isFormSubmission(HttpServletRequest request) {
		return true;
	}
	
	// Override processFormSubmission @ Todo
}
