package controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Login;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import service.LoginService;

public class LoginController extends SimpleFormController {

	Login login;
	LoginService loginService;
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

		System.out.println(request.getParameter("name")+"  "+ request.getParameter("password"));
		login.setName(request.getParameter("name"));
		login.setPassword(request.getParameter("password"));

		if(loginService.checkLogin(login)){
		return new ModelAndView(getSuccessView());
		}
		return new ModelAndView(getFormView());
	}

	public void setLogin(Login login) {
		this.login = login;
	}
	
	public void setLoginService(LoginService loginService) {
		this.loginService = loginService;
	}


}
