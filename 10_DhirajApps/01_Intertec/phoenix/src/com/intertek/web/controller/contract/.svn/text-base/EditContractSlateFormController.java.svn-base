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

import com.intertek.domain.EditContractSlate;
import com.intertek.domain.ContractSearch;
import com.intertek.entity.Contract;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.ContractService;
import com.intertek.service.CustomerService;
import com.intertek.service.SlateService;
import com.intertek.service.UserService;
import com.intertek.service.ZoneService;
import com.intertek.statemachine.ExecutionContext;
import com.intertek.statemachine.StateMachine;
import com.intertek.statemachine.StateMachineManager;
import com.intertek.util.Constants;
import com.intertek.util.ContractUtil;
import com.intertek.util.DateUtil;
import com.intertek.util.ProductGroupSetUtil;
import com.intertek.util.SecurityUtil;
import com.intertek.util.VesselTypeSetUtil;
import com.intertek.util.ZoneUtil;
import com.intertek.util.SlateUtil;
import com.intertek.web.controller.BaseSimpleFormController;
import com.intertek.web.propertyeditors.CustomDiscountEditor;

public class EditContractSlateFormController extends BaseEditContractFormController
{
  public EditContractSlateFormController() {
    super();
    setSessionForm(true);
    setCommandClass(EditContractSlate.class);
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
    EditContractSlate editContractSlate = (EditContractSlate)command;
    Contract contract = editContractSlate.getContract();
    ContractService contractService = (ContractService)ServiceLocator.getInstance().getBean("contractService");
    SlateService slateService = (SlateService)ServiceLocator.getInstance().getBean("slateService");

    editContractSlate.setActiveId(request.getParameter("activeId"));

    String operationType = request.getParameter("operationType");
    if("searchPriceBookSlatePrice".equals(operationType))
    {
      String pageNumberStr = request.getParameter("pageNumber");

      int pageNumber = 1;
      try
      {
        pageNumber = Integer.parseInt(pageNumberStr);
      }
      catch(Exception e) {}

      editContractSlate.setActiveType("PB");
      loadPriceBookSlatePrices(editContractSlate, pageNumber, errors);
    }
    else if("searchContractSlatePrice".equals(operationType))
    {
      String pageNumberStr = request.getParameter("pageNumber");

      int pageNumber = 1;
      try
      {
        pageNumber = Integer.parseInt(pageNumberStr);
      }
      catch(Exception e) {}
      editContractSlate.setActiveType("Contract");
      loadContractSlatePrices(editContractSlate, pageNumber, errors);
    }
    else if("showAllForPB".equals(operationType))
    {
      loadPriceBookSlatePrices(editContractSlate, 0, errors);
    }
    else if("showPagesForPB".equals(operationType))
    {
      loadPriceBookSlatePrices(editContractSlate, 1, errors);

    }
    else if("showAllForContract".equals(operationType))
    {
      loadContractSlatePrices(editContractSlate, 0, errors);
    }
    else if("showPagesForContract".equals(operationType))
    {
      loadContractSlatePrices(editContractSlate, 1, errors);
    }
    else if("saveContract".equals(operationType)
      || "switchTab".equals(operationType)
      || "prevContract".equals(operationType)
      || "nextContract".equals(operationType)
      || "searchContract".equals(operationType)
    )
    {
      ModelAndView modelAndView = getModelAndView(
        operationType,
        request.getParameter("goToContractTab"),
        "slate",
        contract,
        editContractSlate.getContractSearch(),
        errors
      );
      if(modelAndView == null) return showForm(request, response, errors);

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
    binder.registerCustomEditor(java.lang.Double.class, null, new CustomDiscountEditor(Double.class, nf, new Double(-1)));
  }

  protected boolean suppressValidation(HttpServletRequest request, Object command)
  {
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
    EditContractSlate editContractSlate = new EditContractSlate();

    editContractSlate.setPbSlatePageNumber(1);
    editContractSlate.setContractSlatePageNumber(1);

    Contract contract = null;

    ContractService contractService = (ContractService)ServiceLocator.getInstance().getBean("contractService");
    SlateService slateService = (SlateService)ServiceLocator.getInstance().getBean("slateService");

    try
    {
      contract = contractService.loadContractByContractCode(contractCode);
      if(contract != null)
      {
        editContractSlate.setContract(contract);
        SlateUtil.loadPriceBookSlatePrices(editContractSlate, 1);
        SlateUtil.loadContractSlatePrices(editContractSlate, 1);
      }
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
    editContractSlate.setContract(contract);

    boolean editable = SecurityUtil.isAnyGranted(new String[] {"CreateContract"});
    editContractSlate.setViewOnly(editable == false);

    editContractSlate.setContractSearch(getContractSearch(request));

    return editContractSlate;
  }

  private void loadPriceBookSlatePrices(
    EditContractSlate editContractSlate,
    int pageNumber,
    BindException errors
  )
  {
    editContractSlate.setPbSlatePageNumber(pageNumber);

    try
    {
      SlateUtil.loadPriceBookSlatePrices(editContractSlate, pageNumber);
    }
    catch(Throwable t)
    {
      t.printStackTrace();
      errors.reject("edit.contract.error", new Object[] {t.getMessage()}, null);
    }
  }

  private void loadContractSlatePrices(
    EditContractSlate editContractSlate,
    int pageNumber,
    BindException errors
  )
  {
    editContractSlate.setContractSlatePageNumber(pageNumber);

    try
    {
      SlateUtil.loadContractSlatePrices(editContractSlate, pageNumber);
    }
    catch(Throwable t)
    {
      t.printStackTrace();
      errors.reject("edit.contract.error", new Object[] {t.getMessage()}, null);
    }
  }
}
