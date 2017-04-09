package com.intertek.web.servlet;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.intertek.locator.ServiceLocator;
import com.intertek.meta.MetaDataManager;
import com.intertek.meta.dropdown.DropDownManager;
import com.intertek.statemachine.StateMachineManager;
import com.intertek.cache.ITSCacheManager;


public class InitServlet extends HttpServlet
{
  public void init(ServletConfig config) throws ServletException
  {
    super.init(config);

    ApplicationContext context = WebApplicationContextUtils.getWebApplicationContext(
      config.getServletContext()
    );

    ServiceLocator.getInstance().setApplicationContext(context);

    MetaDataManager metaDataMgr = (MetaDataManager)ServiceLocator.getInstance().getBean("metaDataMgr");
    metaDataMgr.loadMetaData();

    DropDownManager dropDownMgr = (DropDownManager)ServiceLocator.getInstance().getBean("dropDownMgr");
    dropDownMgr.loadDropDownData();

    StateMachineManager stateMachineMgr = (StateMachineManager)ServiceLocator.getInstance().getBean("stateMachineMgr");
    stateMachineMgr.loadStateMachineData();
    ITSCacheManager.initCache();
  }

  public void doGet(
    HttpServletRequest arg0,
    HttpServletResponse arg1
  ) throws ServletException, IOException
  {
  }

  public void doPost(
    HttpServletRequest request,
    HttpServletResponse response
  ) throws ServletException, IOException
  {
  }
}
