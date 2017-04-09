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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.intertek.domain.EditInspectionRate;
import com.intertek.domain.InspectionVersionExt;
import com.intertek.domain.VesselTypeExt;
import com.intertek.domain.ContractSearch;
import com.intertek.entity.CfgContract;
import com.intertek.entity.Contact;
import com.intertek.entity.Contract;
import com.intertek.entity.ContractAttach;
import com.intertek.entity.ContractCust;
import com.intertek.entity.ContractCustContact;
import com.intertek.entity.ContractCustContactId;
import com.intertek.entity.ContractCustId;
import com.intertek.entity.Customer;
import com.intertek.entity.User;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.ContractService;
import com.intertek.service.CustomerService;
import com.intertek.service.TestService;
import com.intertek.service.UserService;
import com.intertek.service.ZoneService;
import com.intertek.statemachine.ExecutionContext;
import com.intertek.statemachine.StateMachine;
import com.intertek.statemachine.StateMachineManager;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;
import com.intertek.util.ContractUtil;
import com.intertek.util.InspectionRateUtil;
import com.intertek.util.SecurityUtil;
import com.intertek.web.controller.BaseSimpleFormController;
import com.intertek.web.propertyeditors.CustomDiscountEditor;

public class EditContractInspectionFormController extends BaseEditContractFormController
{
  public EditContractInspectionFormController() {
    super();
    setSessionForm(true);
    setCommandClass(EditInspectionRate.class);
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
    EditInspectionRate editInspectionRate = (EditInspectionRate)command;
    Contract contract = editInspectionRate.getContract();
    ContractService contractService = (ContractService)ServiceLocator.getInstance().getBean("contractService");

    String operationType = request.getParameter("operationType");

    if("changeDate".equals(operationType))
    {
    }
    else if("changeTowMaxCheckBox".equals(operationType))
    {
      InspectionVersionExt activeInspectionVersionExt = InspectionRateUtil.getActiveInspectionVersionExt(editInspectionRate);
      if(activeInspectionVersionExt != null)
      {
        InspectionRateUtil.configTowMaxInspectionRateExt(activeInspectionVersionExt);
      }
    }
    else if("fillVesselMax".equals(operationType))
    {
      InspectionVersionExt activeInspectionVersionExt = InspectionRateUtil.getActiveInspectionVersionExt(editInspectionRate);
      if(activeInspectionVersionExt != null)
      {
        InspectionRateUtil.fillVesselMax(activeInspectionVersionExt);
      }
    }
    else if("addInspectionRate".equals(operationType))
    {
      InspectionVersionExt activeInspectionVersionExt = InspectionRateUtil.getActiveInspectionVersionExt(editInspectionRate);
      if(activeInspectionVersionExt != null)
      {
        int inspectionRateIndex = -1;
        try
        {
          inspectionRateIndex = new Integer(request.getParameter("inspectionRateIndex")).intValue();
        }
        catch(Exception e)
        {
        }

        InspectionRateUtil.addInspectionRates(activeInspectionVersionExt, inspectionRateIndex);
      }
    }
    else if("changeProductGroup".equals(operationType))
    {
      InspectionVersionExt activeInspectionVersionExt = InspectionRateUtil.getActiveInspectionVersionExt(editInspectionRate);
      if(activeInspectionVersionExt != null)
      {
        String changeType = request.getParameter("changeType");

        InspectionRateUtil.copyProductGroupByChangeType(activeInspectionVersionExt, changeType);
      }
    }
    else if("addVersion".equals(operationType))
    {
      InspectionVersionExt newInspectionVersionExt = InspectionRateUtil.addNewVersion(editInspectionRate);
      InspectionRateUtil.loadInspectionVersionExtData(editInspectionRate.getContract(), newInspectionVersionExt);
    }
    else if("selectVersion".equals(operationType))
    {
      String activeInspectionVersion = request.getParameter("activeInspectionVersion");
      int activeInspectionVersionIndex = 0;
      try
      {
        activeInspectionVersionIndex = new Integer(activeInspectionVersion).intValue();
      }
      catch(Exception e)
      {
        e.printStackTrace();
      }

      if(activeInspectionVersionIndex >=0 && activeInspectionVersionIndex < editInspectionRate.getInspectionVersionExtList().size())
      {
        editInspectionRate.setActiveInspectionVersionIndex(activeInspectionVersionIndex);
      }
    }
    else if("deleteVersion".equals(operationType))
    {
      String activeInspectionVersion = request.getParameter("activeInspectionVersion");
      int activeInspectionVersionIndex = 0;
      try
      {
        activeInspectionVersionIndex = new Integer(activeInspectionVersion).intValue();
      }
      catch(Exception e)
      {
        e.printStackTrace();
      }

      if(activeInspectionVersionIndex >=0 && activeInspectionVersionIndex < editInspectionRate.getInspectionVersionExtList().size())
      {
        InspectionRateUtil.deleteVersion(editInspectionRate, activeInspectionVersionIndex);
      }
    }
    else if("selectVesselType".equals(operationType))
    {
      InspectionVersionExt activeInspectionVersionExt = InspectionRateUtil.getActiveInspectionVersionExt(editInspectionRate);
      if(activeInspectionVersionExt != null)
      {
        String selectedVesselTypeName = request.getParameter("selectedVesselTypeName");
        int vesselTypeExtInt = InspectionRateUtil.getVesselTypeExtIndex(activeInspectionVersionExt, selectedVesselTypeName);
        activeInspectionVersionExt.setActiveVesselTypeExtIndex(vesselTypeExtInt);

        InspectionRateUtil.copyProductGroupByChangeType(activeInspectionVersionExt, "fromTab1");
      }
    }
    else if("showAll".equals(operationType))
    {
      InspectionVersionExt activeInspectionVersionExt = InspectionRateUtil.getActiveInspectionVersionExt(editInspectionRate);
      if(activeInspectionVersionExt != null)
      {
        VesselTypeExt activeVesselTypeExt = activeInspectionVersionExt.getActiveVesselTypeExt();
        if(activeInspectionVersionExt != null)
        {
          activeVesselTypeExt.setDisplayAll(true);
        }
      }
    }
    else if("showTabs".equals(operationType))
    {
      InspectionVersionExt activeInspectionVersionExt = InspectionRateUtil.getActiveInspectionVersionExt(editInspectionRate);
      if(activeInspectionVersionExt != null)
      {
        VesselTypeExt activeVesselTypeExt = activeInspectionVersionExt.getActiveVesselTypeExt();
        if(activeInspectionVersionExt != null)
        {
          activeVesselTypeExt.setDisplayAll(false);
        }
      }
    }
    else if("selectContractSpecificRatesCheckbox".equals(operationType))
    {
      InspectionVersionExt activeInspectionVersionExt = InspectionRateUtil.getActiveInspectionVersionExt(editInspectionRate);
      if(activeInspectionVersionExt != null)
      {
        InspectionRateUtil.setOtherCheckBoxesViewOnly(activeInspectionVersionExt);
      }
    }
    else if("deleteSelectedProducts".equals(operationType))
    {
      InspectionRateUtil.processDeleteSelectedProducts(editInspectionRate);
    }
    else if("saveContract".equals(operationType)
      || "switchTab".equals(operationType)
      || "prevContract".equals(operationType)
      || "nextContract".equals(operationType)
      || "searchContract".equals(operationType)
    )
    {
      if("saveContract".equals(operationType) && (!editInspectionRate.getViewOnly()))
      {
        try
        {
          InspectionRateUtil.saveEditInspectionRate(editInspectionRate);
        }
        catch(Throwable e)
        {
          e.printStackTrace();

          errors.reject("edit.contract.error", new Object[] {e.getMessage()}, null);
          return showForm(request, response, errors);
        }
      }

      ModelAndView modelAndView = getModelAndView(
        operationType,
        request.getParameter("goToContractTab"),
        "inspection",
        contract,
        editInspectionRate.getContractSearch(),
        errors
      );
      if(modelAndView == null) return showForm(request, response, errors);

      if("saveContract".equals(operationType))
      {
        InspectionVersionExt activeInspectionVersionExt = InspectionRateUtil.getActiveInspectionVersionExt(editInspectionRate);
        if(activeInspectionVersionExt != null)
        {
          VesselTypeExt activeVesselTypeExt = activeInspectionVersionExt.getActiveVesselTypeExt();
          if(activeVesselTypeExt != null)
          {
            modelAndView.addObject(
              "selectedVesselTypeName",
              activeVesselTypeExt.getVesselType().getVesselTypeId().getVesselType()
            );

            modelAndView.addObject(
              "selectedInspectionRateTab",
              activeVesselTypeExt.getInspectionRateTab()
            );

            modelAndView.addObject(
              "showAll",
              activeVesselTypeExt.getDisplayAll()
            );
          }
        }

        modelAndView.addObject("saved_message", "Saved successfully");
      }

      return modelAndView;
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
    nf.setMaximumFractionDigits(6);
    binder.registerCustomEditor(java.lang.Double.class, null, new CustomDiscountEditor(Double.class, nf, new Double(0)));
  }

  protected boolean suppressValidation(HttpServletRequest request, Object command)
  {
    EditInspectionRate editInspectionRate = (EditInspectionRate)command;
    if(editInspectionRate != null)
    {
      InspectionRateUtil.adjustBeginEndDatesIfNecessary(editInspectionRate);
    }

    String operationType = request.getParameter("operationType");
    if(!"saveContract".equals(operationType))
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
    EditInspectionRate editInspectionRate = new EditInspectionRate();
    Contract contract = null;

    ContractService contractService = (ContractService)ServiceLocator.getInstance().getBean("contractService");

    try
    {
      contract = contractService.loadContractByContractCode(contractCode);
      if(contract != null)
      {
        editInspectionRate.setContract(contract);

        InspectionRateUtil.loadEditInspectionRate(editInspectionRate);

        InspectionVersionExt activeInspectionVersionExt = InspectionRateUtil.getActiveInspectionVersionExt(editInspectionRate);
        if(activeInspectionVersionExt != null)
        {
          String selectedVesselTypeName = request.getParameter("selectedVesselTypeName");
          int vesselTypeExtInt = InspectionRateUtil.getVesselTypeExtIndex(activeInspectionVersionExt, selectedVesselTypeName);
          activeInspectionVersionExt.setActiveVesselTypeExtIndex(vesselTypeExtInt);

          int selectedInspectionRateTab = 0;
          String selectedInspectionRateTabStr = request.getParameter("selectedInspectionRateTab");
          if(selectedInspectionRateTabStr != null)
          {
            try
            {
              selectedInspectionRateTab = new Integer(selectedInspectionRateTabStr).intValue();
            }
            catch(Exception e)
            {
            }
          }

          if((selectedInspectionRateTab < 0) || (selectedInspectionRateTab > 2)) selectedInspectionRateTab = 0;

          VesselTypeExt vesselTypeExt = activeInspectionVersionExt.getActiveVesselTypeExt();
          if(vesselTypeExt != null)
          {
            vesselTypeExt.setInspectionRateTab(selectedInspectionRateTab);

            boolean showAll = true;
            String showAllStr = request.getParameter("showAll");
            if(showAllStr != null)
            {
              try
              {
                showAll = new Boolean(showAllStr).booleanValue();
              }
              catch(Exception e)
              {
              }
            }
            vesselTypeExt.setDisplayAll(showAll);
          }

          InspectionRateUtil.copyProductGroupByChangeType(activeInspectionVersionExt, "fromTab1");
        }
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

    boolean editable = SecurityUtil.isAnyGranted(new String[] {"CreateContract"});
    editInspectionRate.setViewOnly(editable == false);

    editInspectionRate.setContractSearch(getContractSearch(request));

    return editInspectionRate;
  }
}
