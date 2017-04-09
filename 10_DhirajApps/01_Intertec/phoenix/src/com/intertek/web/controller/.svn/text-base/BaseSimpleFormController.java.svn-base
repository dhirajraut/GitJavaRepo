package com.intertek.web.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.entity.Bank;
import com.intertek.entity.BankAccount;
import com.intertek.entity.CfgContract;
import com.intertek.entity.JobContract;
import com.intertek.entity.JobOrder;
import com.intertek.service.JobService;

public abstract class BaseSimpleFormController extends SimpleFormController
{
  private boolean cleanSession = true;
  private Set keptSessionVariableSet;

  public void setCleanSession(boolean cleanSession)
  {
    this.cleanSession = cleanSession;
  }

  public void setKeptSessionVariableSet(Set keptSessionVariableSet)
  {
    this.keptSessionVariableSet = keptSessionVariableSet;
  }

  protected ModelAndView showForm(
    HttpServletRequest request,
    HttpServletResponse response,
    BindException errors
  ) throws Exception
  {
    if(cleanSession)
    {
      HttpSession session = request.getSession();
      if(session != null)
      {
        String myFormSessionAttributeName = getFormSessionAttributeName(request);
        List<String> removeList = new ArrayList<String>();

        Enumeration<String> e = session.getAttributeNames();
        while(e.hasMoreElements())
        {
          String name = e.nextElement();

          int index = name.indexOf("FORM.command");
          if(index < 0) continue;

          if(name.equals(myFormSessionAttributeName)) continue;

          if((keptSessionVariableSet != null) && (keptSessionVariableSet.size() > 0))
          {
            if(keptSessionVariableSet.contains(name)) continue;
          }

          removeList.add(name);
        }

        for(int i=0; i<removeList.size(); i++)
        {
          String name = removeList.get(i);
          session.removeAttribute(name);
        }
      }
    }

    return super.showForm(request, response, errors);
  }
}
