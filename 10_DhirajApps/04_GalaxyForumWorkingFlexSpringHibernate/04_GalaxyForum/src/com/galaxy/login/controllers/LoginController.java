package com.galaxy.login.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.galaxy.forum.domain.ForumUser;
import com.galaxy.login.command.LoginCommand;
import com.galaxy.login.domain.User;
import com.galaxy.login.impls.IService;
import com.galaxy.login.utils.Constants;
import com.galaxy.utils.XMLGenerator;
import com.galaxy.utils.XMLView;

public class LoginController extends AbstractController {

	private IService service = null;

	public IService getService() {
		return service;
	}

	public void setService(IService service) {
		this.service = service;
	}
    public LoginController() {
        super();
        setCommandClass(LoginCommand.class);
    }
	/* This method gets called when the user submits the page.
	 * One more requirement is, the form should use 'Post' method.
	 * If the form uses 'Get' method, still you want to use onSubmit,
	 * you should override 'isFormSubmission(request)' method.
	 * */
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		/* To be converted in AOP. */
		System.out.println(this.getClass().getName() + ": onSubmit " + isFormSubmission(request));

		/* Type cast command object. */
		LoginCommand loginCommand = (LoginCommand) command;
		System.out.println("userId = " + loginCommand.getUserId());
		System.out.println("password = " + loginCommand.getPassword());
		
		/* Execute the service. */
		User user = (User) service.execute(loginCommand);
		System.out.println("User = " + user);
		
		/* Respond back with results. */
		String result = Constants.SERVER_RESPONSE_FAILURE;
		if (null != user) {
			result = Constants.SERVER_RESPONSE_SUCCESS;
			request.getSession().setAttribute(Constants.LOGGED_IN_USER, user);
		}
		
		/* Call XML generator for composing response. */
		return createModelAndView(result, false);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		System.out.println(this.getClass().getName() + ": formBackingObject");
		
		/* This will give 'command' object pre-populated in onSubmit method. */
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
