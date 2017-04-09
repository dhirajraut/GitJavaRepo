package com.intertek.web.controller.contract;

import java.util.List;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.calculator.ContractExpressionExt;
import com.intertek.domain.EditServiceRate;
import com.intertek.domain.ServiceExt;
import com.intertek.domain.ServiceRates;
import com.intertek.domain.ServiceLevel;
import com.intertek.domain.ServiceVersionExt;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.ContractService;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;
import com.intertek.util.ContractServiceUtil;
import com.intertek.web.propertyeditors.CustomDiscountEditor;

public class ChooseApplyLevelsPopupFormController extends SimpleFormController
{
  public ChooseApplyLevelsPopupFormController() {
    super();
    setCommandClass(ServiceRates.class);
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
    ServiceRates serviceRates = (ServiceRates)command;

    String operation = request.getParameter("operation");
    if("doSelect".equals(operation))
    {
      String selectIndexStr = request.getParameter("selectIndex");
      int selectIndex = -1;
      try
      {
        selectIndex = new Integer(selectIndexStr).intValue();
      }
      catch(Exception e)
      {
      }

      List serviceLevelList = serviceRates.getServiceLevelList();
      if((selectIndex >= 0) && (selectIndex < serviceLevelList.size()))
      {
        ServiceLevel serviceLevel = serviceRates.getServiceLevel();
        int versionIndex = serviceLevel.getActiveServiceVersionIndex();

        ServiceLevel selectedServiceLevel = (ServiceLevel)serviceLevelList.get(selectIndex);

        List selectedServiceVersionExtList = serviceLevel.getServiceVersionExtList();

        ServiceVersionExt selectedServiceVersionExt = ContractServiceUtil.getServiceVersionExtByIndex(
          selectedServiceLevel,
          versionIndex
        );
        if(selectedServiceVersionExt != null)
        {
          for(int i=0; i<serviceLevelList.size(); i++)
          {
            ServiceLevel tmpServiceLevel = (ServiceLevel)serviceLevelList.get(i);
            if(tmpServiceLevel == selectedServiceVersionExtList) continue;
            if(tmpServiceLevel == serviceLevel) continue;

            if(selectedServiceLevel.getServiceName().equals(tmpServiceLevel.getServiceName())
              && selectedServiceLevel.getExpressionId().equals(tmpServiceLevel.getExpressionId())
            )
            {
              ServiceVersionExt tmpServiceVersionExt = ContractServiceUtil.getServiceVersionExtByIndex(
                tmpServiceLevel,
                versionIndex
              );
              if(tmpServiceVersionExt != null)
              {
                tmpServiceVersionExt.setApplyToThisLevel(selectedServiceVersionExt.getApplyToThisLevel());
              }
            }
          }
        }
      }

      return showForm(request, response, errors);
    }
    return new ModelAndView("contract-refresh-page", "operationType", "editRates_choose_apply_levels");
  }

  protected boolean suppressValidation(HttpServletRequest request, Object command)
  {
    String operation = request.getParameter("operation");
    if((operation != null) && ("search".equals(operation) || "return".equals(operation)))
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
    SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtil.getUserDateFormat());
    dateFormat.setLenient(false);
    binder.registerCustomEditor(java.util.Date.class, null, new CustomDateEditor(dateFormat, true));
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
   */
  protected Object formBackingObject(
    HttpServletRequest request
  ) throws Exception
  {
    ContractService contractService = (ContractService)ServiceLocator.getInstance().getBean("contractService");

    HttpSession session = request.getSession();
    EditServiceRate editServiceRate = (EditServiceRate)session.getAttribute("com.intertek.web.controller.contract.EditContractServiceFormController.FORM.command");

    ServiceRates serviceRates = null;
    if(editServiceRate != null)
    {
      serviceRates = editServiceRate.getServiceRates();
    }

    return serviceRates;
  }
}
