package com.galaxy.login.controllers;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.galaxy.base.controllers.IController;
import com.galaxy.login.commands.BlankCommand;
import com.galaxy.login.domain.User;
import com.galaxy.login.utils.Constants;

public class SessionReceiverController extends SimpleFormController implements IController  {

    public SessionReceiverController() {
        super();
        setCommandClass(BlankCommand.class);
    }

	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command,
			org.springframework.validation.BindException exception)
			throws Exception {
		System.out.println("In SessionReceiverController.onSubmit");
		User user = (User) request.getSession().getAttribute(
				Constants.LOGGED_IN_USER);
		System.out.println("user.getFirstName() = " + user.getFirstName());
//		return showForm(request, response, exception);
		return ControllerHelper.createModelAndView(user, true);
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		System.out.println(this.getClass().getName() + ": formBackingObject");

		/* This will give 'command' object pre-populated in onSubmit method. */
		return new BlankCommand();
	}
	

	/* When the user submits the page, on submit method gets called,
	 * if the form uses 'Post' method.
	 * If the form uses 'Get' method, still you want to use onSubmit,
	 * you should override 'isFormSubmission(request)' method.
	 * */
	protected boolean isFormSubmission(HttpServletRequest request) {
		return true;
	}
}
