package com.intertek.web.servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.intertek.entity.User;

public class AuthorizationFilter implements Filter
{
  private FilterConfig config;
  private ServletContext servletContext;

  public AuthorizationFilter()
  {
  }

  public void init(FilterConfig filterConfig) throws ServletException
  {
    config = filterConfig;
    servletContext = config.getServletContext();
  }

  public void doFilter(
    ServletRequest request,
    ServletResponse response,
    FilterChain chain
  ) throws IOException, ServletException
  {
    try
    {
      HttpServletRequest httpRequest = (HttpServletRequest)request;
      HttpServletResponse httpResponse = (HttpServletResponse)response;
      HttpSession session = httpRequest.getSession();

      System.out.println("========== target0 = " + request.getParameter("_target0"));
      System.out.println("========== target1 = " + request.getParameter("_target1"));
      System.out.println("========== target2 = " + request.getParameter("_target2"));
      System.out.println("========== target3 = " + request.getParameter("_target3"));
      System.out.println("========== target4 = " + request.getParameter("_target4"));

      System.out.println("========== _finish = " + request.getParameter("_finish"));
      System.out.println("========== _cancel = " + request.getParameter("_cancel"));

      boolean redirect = false;
      String requestURI = httpRequest.getRequestURI();

      System.out.println("======== requestURI = " + requestURI);

      if((requestURI != null) && (requestURI.indexOf("login.htm") < 0))
      {
        User user = (User)session.getAttribute("user");
        System.out.println("======== user = " + user);
        if(user == null)
        {
          redirect = true;
          httpResponse.sendRedirect(httpRequest.getContextPath() + "/login.htm");
        }
      }

        System.out.println("======== redirect = " + redirect);
      if(!redirect)
      {
        chain.doFilter(request, response);
      }
    }
    catch(Throwable e)
    {
      e.printStackTrace();
    }
  }

  public void destroy()
  {
  }
}
