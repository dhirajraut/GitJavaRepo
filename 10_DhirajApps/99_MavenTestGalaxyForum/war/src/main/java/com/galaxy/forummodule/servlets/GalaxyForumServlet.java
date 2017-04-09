package com.galaxy.forummodule.servlets;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.galaxy.base.servlets.GalaxyBaseSpringServlet;

/**
 *
 */
public class GalaxyForumServlet extends GalaxyBaseSpringServlet {

    /**
     * Default serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    protected void doDispatch(final HttpServletRequest request,
    						final HttpServletResponse response)
    						throws Exception{
    	System.out.println("In GalaxyForumServlet.doDispatch");
    	super.doDispatch(request, response);
    }
}
