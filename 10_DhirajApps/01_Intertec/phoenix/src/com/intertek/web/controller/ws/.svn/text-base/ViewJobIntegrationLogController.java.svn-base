package com.intertek.web.controller.ws;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.intertek.locator.ServiceLocator;
import com.intertek.service.WSService;

public class ViewJobIntegrationLogController extends AbstractController
{
  /**
   * .ctor
   */
  public ViewJobIntegrationLogController() {
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
   */
  protected ModelAndView handleRequestInternal(
    HttpServletRequest request,
    HttpServletResponse response
  ) throws Exception
  {
    String jobNumber = request.getParameter("jobNumber");

    WSService wsService = (WSService)ServiceLocator.getInstance().getBean("wsService");
    List wsEntities = wsService.getJobIntegrationLog(jobNumber);

    Map command = new HashMap();
    command.put("wsEntities", wsEntities);

    return new ModelAndView("view-job-integration-log-popup", "command", command);
  }
}
