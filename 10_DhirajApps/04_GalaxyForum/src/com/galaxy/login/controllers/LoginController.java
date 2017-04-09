package com.galaxy.login.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.galaxy.login.commands.LoginCommand;
import com.galaxy.login.domain.User;
import com.galaxy.base.controllers.IController;
import com.galaxy.base.services.IService;
import com.galaxy.login.utils.Constants;

public class LoginController extends SimpleFormController implements IController {

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
		
		/* Execute the service. */
		User user = (User) service.execute(loginCommand);
		
		/* Respond back with results. */
		String result = Constants.SERVER_RESPONSE_FAILURE;
		if (null != user) {
			result = Constants.SERVER_RESPONSE_SUCCESS;
			request.getSession().setAttribute(Constants.LOGGED_IN_USER, user);
		}
		
		/* Call XML generator for composing response. */
		return ControllerHelper.createModelAndView(result, false);
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
