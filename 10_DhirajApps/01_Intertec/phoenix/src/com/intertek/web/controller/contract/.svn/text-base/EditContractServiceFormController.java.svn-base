package com.intertek.web.controller.contract;

import java.io.File;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Set;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.intertek.calculator.ContractExpressionExt;
import com.intertek.domain.EditServiceRate;
import com.intertek.domain.HighLevelServiceExt;
import com.intertek.domain.ServiceRates;
import com.intertek.domain.ServiceLevel;
import com.intertek.domain.ServiceExt;
import com.intertek.domain.ServiceVersionExt;
import com.intertek.domain.ContractSearch;
import com.intertek.entity.CfgContract;
import com.intertek.entity.Contact;
import com.intertek.entity.Contract;
import com.intertek.entity.HighLevelService;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.ContractService;
import com.intertek.service.ServiceRateService;
import com.intertek.service.TestService;
import com.intertek.service.UserService;
import com.intertek.service.ZoneService;
import com.intertek.statemachine.ExecutionContext;
import com.intertek.statemachine.StateMachine;
import com.intertek.statemachine.StateMachineManager;
import com.intertek.util.Constants;
import com.intertek.util.ContractUtil;
import com.intertek.util.DateUtil;
import com.intertek.util.SecurityUtil;
import com.intertek.util.ContractServiceUtil;
import com.intertek.web.controller.BaseSimpleFormController;
import com.intertek.web.propertyeditors.CustomDiscountEditor;
import com.intertek.sort.HighLevelServiceComparator;

public class EditContractServiceFormController extends BaseEditContractFormController
{
  public EditContractServiceFormController() {
    super();
    setSessionForm(true);
    setCommandClass(EditServiceRate.class);
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
    EditServiceRate editServiceRate = (EditServiceRate)command;
    Contract contract = editServiceRate.getContract();

    try
    {
      String operationType = request.getParameter("operationType");
      if("editRates".equals(operationType))
      {
        ServiceRates serviceRates = editServiceRate.getServiceRates();
        if(serviceRates == null) return showForm(request, response, errors);

        String operation = request.getParameter("operation");
        if(operation == null)
        {
          return showForm(request, response, errors);
        }
        else if("switchPbOrContract".equals(operation))
        {
          String pbOrContract = ContractServiceUtil.switchPbOrContract(
            serviceRates,
            serviceRates.getServiceLevel(),
            serviceRates.getServiceLevel().getActiveServiceVersionIndex()
          );

          if(Constants.PRICE_BOOK.equals(pbOrContract))
          {
            errors.reject("edit.contract.service.switch.to.pricebook.message", null, null);
          }

          return showForm(request, response, errors);
        }
        else if("changeDate".equals(operation))
        {
          return showForm(request, response, errors);
        }
        else if("refresh".equals(operation))
        {
          return showForm(request, response, errors);
        }
        else if("addVersion".equals(operation))
        {
          ContractServiceUtil.addNewVersion(serviceRates);
          ContractServiceUtil.normalizeServiceRateBeginDateAndEndDate(serviceRates);

          return showForm(request, response, errors);
        }
        else if("addNewRate".equals(operation))
        {
          ContractServiceUtil.copyPriceBookRatesToContractRates(
            serviceRates,
            serviceRates.getServiceLevel(),
            serviceRates.getServiceLevel().getActiveServiceVersionIndex()
          );

          return showForm(request, response, errors);
        }
        else if("addServiceRateWithIndex".equals(operation))
        {
          int serviceRateIndex = -1;
          try
          {
            serviceRateIndex = new Integer(request.getParameter("selectedContractRateIndex")).intValue();
          }
          catch(Exception e)
          {
          }

          if(serviceRateIndex >= 0)
          {
            ContractServiceUtil.copyContractRates(
              serviceRateIndex,
              serviceRates,
              serviceRates.getServiceLevel(),
              serviceRates.getServiceLevel().getActiveServiceVersionIndex()
            );
          }

          return showForm(request, response, errors);
        }
        else if("applyDatesForAllRates".equals(operation))
        {
          ContractServiceUtil.applyDatesForAllRates(serviceRates);

          return showForm(request, response, errors);
        }
        else if("deleteSelectedRates".equals(operation))
        {
          ContractServiceUtil.deleteSelectedForRates(serviceRates);

          return showForm(request, response, errors);
        }
        else if("addZoneDiscount".equals(operation))
        {
          ContractServiceUtil.addZoneDiscount(serviceRates);

          return showForm(request, response, errors);
        }
        else if("deleteZoneDiscount".equals(operation))
        {
          ContractServiceUtil.deleteZoneDiscount(serviceRates);

          return showForm(request, response, errors);
        }
        else if("toggleLevel".equals(operation))
        {
          ContractServiceUtil.setToggleLevelToServiceVersion(serviceRates.getServiceLevel());

          int versionIndex = serviceRates.getServiceLevel().getActiveServiceVersionIndex();
          List serviceVersionExtList = serviceRates.getServiceLevel().getServiceVersionExtList();
          if((versionIndex >= 0) && (versionIndex < serviceVersionExtList.size()))
          {
            ServiceVersionExt serviceVersionExt = (ServiceVersionExt)serviceVersionExtList.get(versionIndex);

            ContractServiceUtil.splitContractSpecificServiceRateToServiceVersionIfNecessary(
              serviceRates,
              serviceVersionExt
            );
            if(Constants.A.equals(serviceVersionExt.getRateApplied()))
            {
              request.setAttribute("runPopChooseLevelsToApply", "true");
            }
          }

          return showForm(request, response, errors);
        }
        else if("selectVersion".equals(operation))
        {
          String activeServiceVersion = request.getParameter("activeServiceVersion");
          int activeServiceVersionIndex = 0;
          try
          {
            activeServiceVersionIndex = new Integer(activeServiceVersion).intValue();
          }
          catch(Exception e)
          {
            e.printStackTrace();
          }

          if(activeServiceVersionIndex >=0 && activeServiceVersionIndex < serviceRates.getServiceLevel().getServiceVersionExtList().size())
          {
            serviceRates.getServiceLevel().setActiveServiceVersionIndex(activeServiceVersionIndex);
          }

          return showForm(request, response, errors);
        }
        else if("return".equals(operation))
        {
          editServiceRate.setServiceRates(null);

          HighLevelServiceExt highLevelServiceExt = editServiceRate.getHighLevelServiceExt();
          if(highLevelServiceExt != null)
          {
            initHighLevelServiceExt(
              editServiceRate,
              highLevelServiceExt.getHighLevelService().getHighLevelServiceId()
            );
          }

          return showForm(request, response, errors);
        }
        else if("saveOnly".equals(operation))
        {
          ContractServiceUtil.saveServiceRates(serviceRates);

          editServiceRate.setServiceRates(null);

          initServiceRates(
            editServiceRate,
            serviceRates.getServiceLevel().getCfgContractId(),
            serviceRates.getServiceLevel().getExpressionId(),
            serviceRates.getServiceLevel().getServiceName(),
            serviceRates.getServiceLevel().getServiceLevel()
          );

          return showForm(request, response, errors);
        }
        else if("showAll".equals(operation))
        {
          editServiceRate.setDisplayAll(true);

          return showForm(request, response, errors);
        }
        else if("showTabs".equals(operation))
        {
          editServiceRate.setDisplayAll(false);

          return showForm(request, response, errors);
        }

        ContractServiceUtil.saveServiceRates(serviceRates);

        editServiceRate.setServiceRates(null);

        HighLevelServiceExt highLevelServiceExt = editServiceRate.getHighLevelServiceExt();
        if(highLevelServiceExt != null)
        {
          initHighLevelServiceExt(
            editServiceRate,
            highLevelServiceExt.getHighLevelService().getHighLevelServiceId()
          );
        }
      }
      else if("editRates_edit_question_notes".equals(operationType))
      {
      }
      else if("editRates_choose_apply_levels".equals(operationType))
      {
      }
      else if("editRates_save_and_switch".equals(operationType))
      {
        ServiceRates serviceRates = editServiceRate.getServiceRates();
        if(serviceRates == null) return showForm(request, response, errors);

        ContractServiceUtil.saveServiceRates(serviceRates);

        editServiceRate.setServiceRates(null);

        initServiceRates(editServiceRate, request);
      }
      else if("editRates_init".equals(operationType))
      {
        initServiceRates(editServiceRate, request);
      }
      else if("displayService".equals(operationType))
      {
        String highLevelServiceId = request.getParameter("highLevelServiceId");
        initHighLevelServiceExt(editServiceRate, highLevelServiceId);

        if((highLevelServiceId != null) && !"".equals(highLevelServiceId.trim()))
        {
          HighLevelService highLevelService = ContractServiceUtil.getHighLevelServiceById(
            editServiceRate.getSelectedHighLevelServiceList(),
            highLevelServiceId
          );
          HighLevelServiceExt highLevelServiceExt = ContractServiceUtil.getHighLevelServiceExt(
            highLevelService,
            editServiceRate.getContract()
          );
          if(highLevelServiceExt != null)
          {
            editServiceRate.setHighLevelServiceExt(highLevelServiceExt);
          }
        }
      }
      else if("saveContract".equals(operationType)
        || "switchTab".equals(operationType)
        || "prevContract".equals(operationType)
        || "nextContract".equals(operationType)
        || "searchContract".equals(operationType)
      )
      {
        if("saveContract".equals(operationType) && (!editServiceRate.getViewOnly()))
        {
          ContractServiceUtil.saveVisibilityForHighLevelServiceExt(
            editServiceRate.getContract(),
            editServiceRate.getHighLevelServiceExt()
          );
        }

        ModelAndView modelAndView = getModelAndView(
          operationType,
          request.getParameter("goToContractTab"),
          "service",
          contract,
          editServiceRate.getContractSearch(),
          errors
        );
        if(modelAndView == null) return showForm(request, response, errors);

        return modelAndView;
      }
    }
    catch(Throwable t)
    {
      t.printStackTrace();

      errors.reject("edit.contract.error", new Object[] {t.getMessage()}, null);
    }

    return showForm(request, response, errors);
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
    NumberFormat nf = NumberFormat.getInstance();
    binder.registerCustomEditor(java.lang.Double.class, null, new CustomDiscountEditor(Double.class, nf, new Double(0)));
  }

  protected boolean suppressValidation(HttpServletRequest request, Object command)
  {
    EditServiceRate editServiceRate = (EditServiceRate)command;
    if(editServiceRate != null)
    {
      ServiceRates serviceRates = editServiceRate.getServiceRates();
      if(serviceRates != null)
      {
        ContractServiceUtil.adjustBeginEndDatesIfNecessary(serviceRates);

        ContractServiceUtil.normalizeServiceRateBeginDateAndEndDate(serviceRates);
      }
    }

    String operation = request.getParameter("operation");
    if((operation != null)
      && ("refresh".equals(operation)
      || "return".equals(operation)
      || "addNewRate".equals(operation)
      || "applyDatesForAllRates".equals(operation)
      || "deleteSelectedRates".equals(operation)
      || "showAll".equals(operation)
      || "showTabs".equals(operation)
      || "toggleLevel".equals(operation)
      || "addServiceRateWithIndex".equals(operation)
    ))
    {
      return true;
    }

    String operationType = request.getParameter("operationType");
    if("switchTab".equals(operationType)
      || "prevContract".equals(operationType)
      || "nextContract".equals(operationType)
      || "searchContract".equals(operationType)
    )
    {
      return true;
    }

    return super.suppressValidation(request, command);
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
   */
  protected Object formBackingObject(
    HttpServletRequest request
  ) throws Exception
  {
    String contractCode = request.getParameter("contractCode");
    EditServiceRate editServiceRate = new EditServiceRate();
    Contract contract = null;

    ContractService contractService = (ContractService)ServiceLocator.getInstance().getBean("contractService");
    ServiceRateService serviceRateService = (ServiceRateService)ServiceLocator.getInstance().getBean("serviceRateService");

    try
    {
      contract = contractService.loadContractByContractCode(contractCode);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

    if(contract == null) contract = new Contract();
    if(contract.getInvoiceType()==null)
    {
      contract.setInvoiceType("Reg");
    }
    editServiceRate.setContract(contract);

    List highLevelServiceList = null;
    try
    {
      List idList = serviceRateService.getEditHighLevelServiceIdListByContractCodeAndWorkingPb(
        contract.getContractCode(),
        contract.getWorkingPB()
      );

      highLevelServiceList = serviceRateService.getEditHighLevelServiceListByIds(idList);

      Collections.sort(highLevelServiceList, new HighLevelServiceComparator());

      editServiceRate.getSelectedHighLevelServiceList().addAll(highLevelServiceList);
      editServiceRate.getOldHighLevelServices().addAll(highLevelServiceList);
    }
    catch(Throwable t)
    {
      t.printStackTrace();
    }

    boolean editable = SecurityUtil.isAnyGranted(new String[] {"CreateContract"});
    editServiceRate.setViewOnly(editable == false);

    editServiceRate.setContractSearch(getContractSearch(request));

    return editServiceRate;
  }

  private void initServiceRates(EditServiceRate editServiceRate, HttpServletRequest request)
  {
    String ceContractId = request.getParameter("ceContractId");
    String ceExpressionId = request.getParameter("ceExpressionId");
    String serviceName = request.getParameter("serviceName");
    String serviceLevelStr = request.getParameter("serviceLevel");

    initServiceRates(editServiceRate, ceContractId, ceExpressionId, serviceName, serviceLevelStr);
  }

  private void initServiceRates(
    EditServiceRate editServiceRate,
    String ceContractId,
    String ceExpressionId,
    String serviceName,
    String serviceLevelStr
  )
  {
    ServiceRates serviceRates = new ServiceRates();

    ServiceLevel serviceLevel = new ServiceLevel();
    serviceLevel.setCfgContractId(ceContractId);
    serviceLevel.setServiceName(serviceName);
    serviceLevel.setExpressionId(ceExpressionId);
    serviceLevel.setServiceLevel(serviceLevelStr);

    serviceRates.setServiceLevel(serviceLevel);

    serviceRates.setEditServiceRate(editServiceRate);

    ServiceExt serviceExt = ContractServiceUtil.getServiceExtByServiceNameAndLevel(
      serviceName,
      serviceLevelStr,
      editServiceRate.getHighLevelServiceExt().getServiceExtList()
    );
    serviceLevel.setServiceExt(serviceExt);

    if(serviceExt != null)
    {
      ContractExpressionExt ceExt = ContractServiceUtil.getContractExpressionExtByContractIdAndExpressionId(
       ceContractId,
       ceExpressionId,
       serviceExt.getContractExpressionExtList()
      );
      serviceLevel.setContractExpressionExt(ceExt);
    }

    ContractServiceUtil.populateServiceRates(serviceRates);
    editServiceRate.setServiceRates(serviceRates);
  }

  private void initHighLevelServiceExt(EditServiceRate editServiceRate, String highLevelServiceId)
  {
    if((highLevelServiceId != null) && !"".equals(highLevelServiceId.trim()))
    {
      HighLevelService highLevelService = ContractServiceUtil.getHighLevelServiceById(
        editServiceRate.getSelectedHighLevelServiceList(),
        highLevelServiceId
      );
      HighLevelServiceExt highLevelServiceExt = ContractServiceUtil.getHighLevelServiceExt(
        highLevelService,
        editServiceRate.getContract()
      );
      if(highLevelServiceExt != null)
      {
        editServiceRate.setHighLevelServiceExt(highLevelServiceExt);
      }
    }
  }
}
