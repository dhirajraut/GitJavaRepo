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

import com.intertek.domain.EditContractTest;
import com.intertek.domain.ContractSearch;
import com.intertek.entity.Contract;
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
import com.intertek.util.ContractUtil;
import com.intertek.util.DateUtil;
import com.intertek.util.ProductGroupSetUtil;
import com.intertek.util.SecurityUtil;
import com.intertek.util.VesselTypeSetUtil;
import com.intertek.util.ZoneUtil;
import com.intertek.util.TestUtil;
import com.intertek.web.controller.BaseSimpleFormController;
import com.intertek.web.propertyeditors.CustomDiscountEditor;

public class EditContractTestFormController extends BaseEditContractFormController
{
  public EditContractTestFormController() {
    super();
    setSessionForm(true);
    setCommandClass(EditContractTest.class);
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
    EditContractTest editContractTest = (EditContractTest)command;
    Contract contract = editContractTest.getContract();
    ContractService contractService = (ContractService)ServiceLocator.getInstance().getBean("contractService");
    TestService testService = (TestService)ServiceLocator.getInstance().getBean("testService");

    editContractTest.setActiveId(request.getParameter("activeId"));

    String operationType = request.getParameter("operationType");
    if("searchPriceBookTestPrice".equals(operationType))
    {
      String pageNumberStr = request.getParameter("pageNumber");

      int pageNumber = 1;
      try
      {
        pageNumber = Integer.parseInt(pageNumberStr);
      }
      catch(Exception e) {}

      editContractTest.setActiveType("PB");
      loadPriceBookTestPrices(editContractTest, pageNumber, errors);
    }
    else if("searchContractTestPrice".equals(operationType))
    {
      String pageNumberStr = request.getParameter("pageNumber");

      int pageNumber = 1;
      try
      {
        pageNumber = Integer.parseInt(pageNumberStr);
      }
      catch(Exception e) {}
      editContractTest.setActiveType("Contract");
      loadContractTestPrices(editContractTest, pageNumber, errors);
    }
    else if("showAllForPB".equals(operationType))
    {
      loadPriceBookTestPrices(editContractTest, 0, errors);
    }
    else if("showPagesForPB".equals(operationType))
    {
      loadPriceBookTestPrices(editContractTest, 1, errors);
    }
    else if("showAllForContract".equals(operationType))
    {
      loadContractTestPrices(editContractTest, 0, errors);
    }
    else if("showPagesForContract".equals(operationType))
    {
      loadContractTestPrices(editContractTest, 1, errors);
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
        "test",
        contract,
        editContractTest.getContractSearch(),
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
    EditContractTest editContractTest = new EditContractTest();

    editContractTest.setPbTestPageNumber(1);
    editContractTest.setContractTestPageNumber(1);

    Contract contract = null;

    ContractService contractService = (ContractService)ServiceLocator.getInstance().getBean("contractService");
    TestService testService = (TestService)ServiceLocator.getInstance().getBean("testService");

    try
    {
      contract = contractService.loadContractByContractCode(contractCode);
      if(contract != null)
      {
        editContractTest.setContract(contract);
        TestUtil.loadPriceBookTestPrices(editContractTest, 1);
        TestUtil.loadContractTestPrices(editContractTest, 1);
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
    editContractTest.setContract(contract);

    boolean editable = SecurityUtil.isAnyGranted(new String[] {"CreateContract"});
    editContractTest.setViewOnly(editable == false);

    editContractTest.setContractSearch(getContractSearch(request));

    return editContractTest;
  }

  private void loadPriceBookTestPrices(
    EditContractTest editContractTest,
    int pageNumber,
    BindException errors
  )
  {
    editContractTest.setPbTestPageNumber(pageNumber);

    try
    {
      TestUtil.loadPriceBookTestPrices(editContractTest, pageNumber);
    }
    catch(Throwable t)
    {
      t.printStackTrace();
      errors.reject("edit.contract.error", new Object[] {t.getMessage()}, null);
    }
  }

  private void loadContractTestPrices(
    EditContractTest editContractTest,
    int pageNumber,
    BindException errors
  )
  {
    editContractTest.setContractTestPageNumber(pageNumber);

    try
    {
      TestUtil.loadContractTestPrices(editContractTest, pageNumber);
    }
    catch(Throwable t)
    {
      t.printStackTrace();
      errors.reject("edit.contract.error", new Object[] {t.getMessage()}, null);
    }
  }
}
