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

import com.intertek.domain.CopyPbTestPrice;
import com.intertek.domain.ContractTestExt;
import com.intertek.entity.ContractTest;
import com.intertek.entity.ContractTestId;
import com.intertek.entity.Test;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.TestService;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;
import com.intertek.util.TestUtil;
import com.intertek.web.propertyeditors.CustomDiscountEditor;

public class CreateContractTestPopupController extends SimpleFormController
{
  public CreateContractTestPopupController() {
    super();
    setCommandClass(ContractTestExt.class);
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
    ContractTestExt contractTestExt = (ContractTestExt)command;

    String operation = request.getParameter("operation");
    if(operation == null)
    {
      return showForm(request, response, errors);
    }
    else if("return".equals(operation))
    {
      ModelAndView modelAndVeiw = new ModelAndView("test-slate-refresh-page");
      modelAndVeiw.addObject("operationType", "searchContractTestPrice");
      modelAndVeiw.addObject("pageNumber", contractTestExt.getPageNumber());

      return modelAndVeiw;
    }

    try
    {
      TestUtil.addContractTest(contractTestExt);
    }
    catch(Throwable t)
    {
      t.printStackTrace();
      errors.reject("generic.error", new Object[] {t.getMessage()}, null);
      return showForm(request, response, errors);
    }

    ModelAndView modelAndVeiw = new ModelAndView("test-slate-refresh-page");
    modelAndVeiw.addObject("operationType", "searchContractTestPrice");
    modelAndVeiw.addObject("pageNumber", contractTestExt.getPageNumber());

    return modelAndVeiw;
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
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
   */
  protected Object formBackingObject(
    HttpServletRequest request
  ) throws Exception
  {
    String contractCode = request.getParameter("contractCode");
    String pageNumber = request.getParameter("pageNumber");

    ContractTestExt contractTestExt = new ContractTestExt();
    contractTestExt.setContractCode(contractCode);
    contractTestExt.setPageNumber(pageNumber);

    ContractTest contractTest = new ContractTest();
    ContractTestId contractTestId = new ContractTestId();
    contractTestId.setContractId(contractCode);
    contractTestId.setTestType("C");
    contractTest.setContractTestId(contractTestId);
    contractTest.setBillingGuideLine("D");

    Test test = new Test();
    contractTest.setTest(test);

    contractTestExt.setContractTest(contractTest);

    return contractTestExt;
  }
}
