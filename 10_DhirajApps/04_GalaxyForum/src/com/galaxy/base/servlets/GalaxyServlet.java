package com.galaxy.base.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.DispatcherServlet;

public class GalaxyServlet extends DispatcherServlet {

    protected void doDispatch(final HttpServletRequest request,
            final HttpServletResponse response) throws Exception {
        System.out.println("In GalaxyServlet.doDispatch");
        super.doDispatch(request, response);
    }
}
