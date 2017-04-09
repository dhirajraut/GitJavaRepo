package com.intertek.web.controller.common;

import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

public class MessageController extends AbstractController
{
  /**
   * .ctor
   */
  public MessageController() {
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
   */
  protected ModelAndView handleRequestInternal(
    HttpServletRequest request,
    HttpServletResponse response
  ) throws Exception
  {
    ModelAndView modelAndView = new ModelAndView("common-message");
    Enumeration<String> e = request.getParameterNames();
    while(e.hasMoreElements())
    {
      String key = e.nextElement();
      Object value = request.getParameter(key);

      modelAndView.addObject(key, value);
    }

    return modelAndView;
  }
}