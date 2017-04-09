package com.galaxy.base.servlets;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author 284773
 *
 */
public class GalaxyBaseServlet extends HttpServlet {

    /**
     * Default serialVersionUID
     */
    private static final long serialVersionUID = 1L;

    /**
     * @param request
     * @param response
     */
    public void processRequest(HttpServletRequest request,
                                HttpServletResponse response) {
        System.out.println("Processing Request");
    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServlet#doGet(
     *                          javax.servlet.http.HttpServletRequest,
     *                          javax.servlet.http.HttpServletResponse)
     */
    public final void doGet(final HttpServletRequest request,
                                    final HttpServletResponse response) {
        processRequest(request, response);
    }

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServlet#doPost(
     *                          javax.servlet.http.HttpServletRequest,
     *                          javax.servlet.http.HttpServletResponse)
     */
    public final void doPost(final HttpServletRequest request,
                                final HttpServletResponse response) {
        processRequest(request, response);
    }
}
