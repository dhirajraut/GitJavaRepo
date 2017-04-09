package com.galaxy.generic.servlet.http;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

import com.galaxy.generic.log.LogManager;

/**
 * This is a Generic HTTP Servlet.
 */
public class GenericHttpServlet extends HttpServlet {

    /**
     * Serial Version Id.
     */
    private static final long serialVersionUID = -7791507385737513942L;
    private static Logger logger = LogManager.getLogger(GenericHttpServlet.class);

    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public void doGet(final HttpServletRequest request, final HttpServletResponse response){
        processRequest(request, response);
    }
    
    /* (non-Javadoc)
     * @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    public void doPost(final HttpServletRequest request, final HttpServletResponse response){
        processRequest(request, response);
    }
    
    /**
     * This method is a common implementation of the doGet and doPost methods.
     * @param request - Input request object.
     * @param response - Input response object.
     */
    private void processRequest(final HttpServletRequest request, final HttpServletResponse response){
//        logger.error("Error");
//        logger.info("Info");
//        logger.warn("Warning");
//        logger.trace("Trace");
        try {
            dispatchRequest(request, response);
        }
        catch (ServletException e){
            logger.error("Exception in Dispatching Request.");
            e.printStackTrace();
        }
        catch (IOException e){
            logger.error("Exception in Dispatching Request.");
            e.printStackTrace();
        }

    }
    
    /**
     * 
     */
    private void dispatchRequest (
            final HttpServletRequest request, final HttpServletResponse response)
                                        throws ServletException, IOException {
        getServletContext().getRequestDispatcher("/jsp/LoginSuccess.jsp").forward(request, response);
    }
    public static void main(String args[]) {
        new GenericHttpServlet().processRequest(null, null);
    }
}
