package com.intertek.web.controller.contract;

import java.util.List;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Comparator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.domain.EditZone;
import com.intertek.domain.SelectZones;
import com.intertek.domain.ZoneExt;
import com.intertek.entity.PortLocation;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.ZoneService;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;
import com.intertek.util.ZoneUtil;

public class EditContractSelectZonePopupFormController extends SimpleFormController
{
  public EditContractSelectZonePopupFormController() {
    super();
    setCommandClass(SelectZones.class);
    setSessionForm(true);
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
   */
  protected ModelAndView onSubmit(
    HttpServletRequest request,
    HttpServletResponse response,
    Object command,
    BindException errors
  ) throws Exception
  {
    SelectZones selectZones = (SelectZones)command;

    String operation = request.getParameter("operation");
    if(operation == null)
    {
      return showForm(request, response, errors);
    }
    else if("return".equals(operation))
    {
      return new ModelAndView("contract-refresh-page", "operationType", "selectZoneCancel");
    }

    return new ModelAndView("contract-refresh-page", "operationType", "selectZoneDone");
  }

  protected boolean suppressValidation(HttpServletRequest request, Object command)
  {
    String operation = request.getParameter("operation");
    if((operation != null) && "return".equals(operation))
    {
      return true;
    }

    return super.suppressValidation(request, command);
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest, org.springframework.web.bind.ServletRequestDataBinder)
   */
  protected void initBinder(
    HttpServletRequest request,
    ServletRequestDataBinder binder
  ) throws Exception
  {
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
   */
  protected Object formBackingObject(
    HttpServletRequest request
  ) throws Exception
  {
    SelectZones selectZones = null;
    ZoneService zoneService = (ZoneService)ServiceLocator.getInstance().getBean("zoneService");

    HttpSession session = request.getSession();
    EditZone editZone = (EditZone)session.getAttribute("com.intertek.web.controller.contract.EditContractZoneFormController.FORM.command");

    if(editZone != null)
    {
      selectZones = editZone.getSelectZones();
      if(selectZones == null)
      {
        selectZones = new SelectZones();
        editZone.setSelectZones(selectZones);

        try
        {
          List portLocationList = zoneService.getPortLocationsByContractCode(
            editZone.getContract().getWorkingPB()
          );
          Collections.sort(
            portLocationList,
            new Comparator()
            {
              public int compare(Object o1, Object o2)
              {
                PortLocation p1 = (PortLocation)o1;
                PortLocation p2 = (PortLocation)o2;

                String zoneId1 = p1.getPortLocationId().getLocation();
                String zoneId2 = p2.getPortLocationId().getLocation();

                return zoneId1.compareTo(zoneId2);
              }
            }
          );
          selectZones.setPortLocationList(portLocationList);
        }
        catch(Exception e)
        {
          e.printStackTrace();
        }
      }

      String zoneIndexStr = request.getParameter("zoneIndex");
      selectZones.setZoneIndex(zoneIndexStr);

      ZoneExt zoneExt = ZoneUtil.getZoneExtByIndexStr(editZone, zoneIndexStr);
      selectZones.setZoneExt(zoneExt);
      if(zoneExt != null)
      {
        selectZones.setSelectedZoneId(zoneExt.getPriceBookZoneId());
      }
    }
    else
    {
      selectZones = new SelectZones();
    }

    return selectZones;
  }
}
