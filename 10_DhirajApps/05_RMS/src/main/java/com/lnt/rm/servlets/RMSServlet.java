package com.lnt.rm.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.DispatcherServlet;

/**
 *
 */
public class RMSServlet extends DispatcherServlet {

    /**
     * Default serialVersionUID
     */
    private static final long serialVersionUID = 1L;
    
    protected void doDispatch(final HttpServletRequest request,
    						final HttpServletResponse response)
    						throws Exception{
    	System.out.println("In GalaxyBaseSpringServlet.doDispatch");
    	super.doDispatch(request, response);
    }
}
