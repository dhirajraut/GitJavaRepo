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

import com.intertek.domain.CopyPbSlatePrice;
import com.intertek.domain.ContractSlateExt;
import com.intertek.entity.ContractSlate;
import com.intertek.entity.ContractSlateId;
import com.intertek.entity.Slate;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.SlateService;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;
import com.intertek.util.SlateUtil;
import com.intertek.web.propertyeditors.CustomDiscountEditor;

public class CreateContractSlatePopupController extends SimpleFormController
{
  public CreateContractSlatePopupController() {
    super();
    setCommandClass(ContractSlateExt.class);
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
    ContractSlateExt contractSlateExt = (ContractSlateExt)command;

    String operation = request.getParameter("operation");
    if(operation == null)
    {
      return showForm(request, response, errors);
    }
    else if("return".equals(operation))
    {
      ModelAndView modelAndVeiw = new ModelAndView("test-slate-refresh-page");
      modelAndVeiw.addObject("operationType", "searchContractSlatePrice");
      modelAndVeiw.addObject("pageNumber", contractSlateExt.getPageNumber());

      return modelAndVeiw;
    }

    try
    {
      SlateUtil.addContractSlate(contractSlateExt);
    }
    catch(Throwable t)
    {
      t.printStackTrace();
      errors.reject("generic.error", new Object[] {t.getMessage()}, null);
      return showForm(request, response, errors);
    }

    ModelAndView modelAndVeiw = new ModelAndView("test-slate-refresh-page");
    modelAndVeiw.addObject("operationType", "searchContractSlatePrice");
    modelAndVeiw.addObject("pageNumber", contractSlateExt.getPageNumber());

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

    ContractSlateExt contractSlateExt = new ContractSlateExt();
    contractSlateExt.setContractCode(contractCode);
    contractSlateExt.setPageNumber(pageNumber);

    ContractSlate contractSlate = new ContractSlate();
    ContractSlateId contractSlateId = new ContractSlateId();
    contractSlateId.setContractId(contractCode);
    contractSlateId.setSlateType("C");
    contractSlate.setContractSlateId(contractSlateId);

    Slate slate = new Slate();
    contractSlate.setSlate(slate);

    contractSlateExt.setContractSlate(contractSlate);

    return contractSlateExt;
  }
}
