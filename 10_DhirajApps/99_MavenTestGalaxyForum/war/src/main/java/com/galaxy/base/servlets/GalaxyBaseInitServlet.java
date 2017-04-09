package com.galaxy.base.servlets;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.galaxy.base.locators.ServiceLocator;
import com.galaxy.loginmodule.controllers.LoginController;
import com.galaxy.loginmodule.impls.LoginService;

public class GalaxyBaseInitServlet extends HttpServlet {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public void init (ServletConfig config) throws ServletException {

		// Call Servlet Initialization.
		super.init(config);
		
		// Set Application Context.
		System.out.println("GalaxyBaseInitServlet.init - Setting ApplicationContext" + config.getServletContext());
		ApplicationContext applicationContext = WebApplicationContextUtils.
						getWebApplicationContext(getServletContext());
		ServiceLocator.getInstance().setApplicationContext(applicationContext);
		System.out.println("applicationContext = " + applicationContext);
		if (null != applicationContext) {
			System.out.println("bean = " + ((LoginController)applicationContext.getBean("login")).getService());
		}
	}


}
