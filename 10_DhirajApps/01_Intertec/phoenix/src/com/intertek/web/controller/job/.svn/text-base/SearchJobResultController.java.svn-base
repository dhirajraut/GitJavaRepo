package com.intertek.web.controller.job;

import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.RequestUtils;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.intertek.entity.*;
import com.intertek.service.*;
import com.intertek.util.*;
import com.intertek.locator.*;
import com.intertek.domain.*;

public class SearchJobResultController extends AbstractController
{
  /**
   * .ctor
   */
  public SearchJobResultController() {
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
   */
  protected ModelAndView handleRequestInternal(
    HttpServletRequest request,
    HttpServletResponse response
  ) throws Exception
  {
    String view = request.getParameter("view");
    if(view == null) view = "search-job-result-main";

    List jobs = null;
    JobSearch jobSearch = (JobSearch)request.getSession().getAttribute("MySearchJobOrder");
    if(jobSearch == null) jobs = new ArrayList();
    else jobs = jobSearch.getResults();

    return new ModelAndView(view, "command", jobSearch);
  }
}
