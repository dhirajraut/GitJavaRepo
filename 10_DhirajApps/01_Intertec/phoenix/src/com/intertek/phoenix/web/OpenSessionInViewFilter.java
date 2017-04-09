/**
 * @Company: Intertek
 * @Project : Phoenix 2.0 for Commercial and Electronics
 * @Copyright: Intertek 2009
 * 
 */
package com.intertek.phoenix.web;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.FlushMode;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
import org.hibernate.context.ManagedSessionContext;

import com.intertek.phoenix.util.HibernateUtil;
import com.intertek.util.Constants;

/**
 * 
 * @author richard.qin
 * @author eric.nguyen
 */
public class OpenSessionInViewFilter implements Filter {

    private static Logger log = Logger.getLogger(OpenSessionInViewFilter.class);

    private SessionFactory sf;

    private boolean applyFilter(ServletRequest request) {
        HttpServletRequest r = (HttpServletRequest) request;
        String url = (r.getRequestURI() + "").toLowerCase();
        return !url.contains("updateentityservice.ws") 
                && (url.contains("/phx_") || url.endsWith(".ws"));
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        boolean doFilter = applyFilter(request);
        try {
            if (doFilter) {
                HibernateUtil.beginTransaction();
            }
            // Call the next filter (continue request processing)
            chain.doFilter(request, response);

            if (doFilter) {
                doPostTransaction(request);
            }
        }
        catch (Throwable ex) {
            // Rollback only
            ex.printStackTrace();
            if (doFilter) {
                doPostTransaction(request);
            }
            // Let others handle it... maybe another interceptor for exceptions?
            throw new ServletException(ex);
        }
    }
    
    private void doPostTransaction(ServletRequest request) {
        boolean rollback = (request.getAttribute(Constants.ROLL_BACK) + "").equalsIgnoreCase("true");
        HibernateUtil.endTransaction(rollback);
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        log.debug("Initializing filter...");
        log.debug("Obtaining SessionFactory from static HibernateUtil singleton");
        sf = HibernateUtil.getSessionFactory();
    }

    public void destroy() {
    }

}
