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
import com.intertek.domain.SlatePriceExt;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.SlateService;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;
import com.intertek.util.SlateUtil;
import com.intertek.web.propertyeditors.CustomDiscountEditor;

public class ModifySlatePricePopupController extends SimpleFormController
{
  public ModifySlatePricePopupController() {
    super();
    setCommandClass(SlatePriceExt.class);
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
    SlatePriceExt slatePriceExt = (SlatePriceExt)command;

    String refreshType = "PB".equals(slatePriceExt.getPriceType()) ? "searchPriceBookSlatePrice" : "searchContractSlatePrice";
    String operation = request.getParameter("operation");
    if(operation == null)
    {
      return showForm(request, response, errors);
    }
    else if("refresh".equals(operation))
    {
      return showForm(request, response, errors);
    }
    else if("return".equals(operation))
    {
      ModelAndView modelAndVeiw = new ModelAndView("test-slate-refresh-page");
      modelAndVeiw.addObject("operationType", refreshType);
      modelAndVeiw.addObject("activeId", slatePriceExt.getSlateId());
      modelAndVeiw.addObject("pageNumber", slatePriceExt.getPageNumber());

      return modelAndVeiw;
    }

    try
    {
      if("addSlatePrice".equals(operation))
      {
        SlateUtil.addSlatePrice(slatePriceExt);

        return showForm(request, response, errors);
      }
      else if("deleteSlatePrice".equals(operation))
      {
        int index = -1;
        try
        {
          index = new Integer(request.getParameter("index")).intValue();
        }
        catch (Exception e)
        {
        }

        if(index >= 0) SlateUtil.removeSlatePrice(slatePriceExt, index);

        return showForm(request, response, errors);
      }
      else
      {
        SlateUtil.saveSlatePrices(slatePriceExt);
      }
    }
    catch(Throwable t)
    {
      t.printStackTrace();
      errors.reject("generic.error", new Object[] {t.getMessage()}, null);
      return showForm(request, response, errors);
    }

    if("submit".equals(operation))
    {
      try
      {
        SlateUtil.loadSlatePrices(slatePriceExt);
      }
      catch(Throwable t)
      {
        t.printStackTrace();
        errors.reject("generic.error", new Object[] {t.getMessage()}, null);
        return showForm(request, response, errors);
      }

      return showForm(request, response, errors);
    }

    ModelAndView modelAndVeiw = new ModelAndView("test-slate-refresh-page");
    modelAndVeiw.addObject("operationType", refreshType);
    modelAndVeiw.addObject("activeId", slatePriceExt.getSlateId());
    modelAndVeiw.addObject("pageNumber", slatePriceExt.getPageNumber());

    return modelAndVeiw;
  }

  protected boolean suppressValidation(HttpServletRequest request, Object command)
  {
    String operation = request.getParameter("operation");
    if((operation != null) && ("addSlatePrice".equals(operation) || "deleteSlatePrice".equals(operation) || "return".equals(operation)))
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
    String contractCode = request.getParameter("contractCode");
    String priceBookId = request.getParameter("priceBookId");
    String slateId = request.getParameter("slateId");
    String priceType = request.getParameter("priceType");
    String pageNumber = request.getParameter("pageNumber");

    SlatePriceExt slatePriceExt = new SlatePriceExt();
    slatePriceExt.setContractCode(contractCode);
    slatePriceExt.setPriceBookId(priceBookId);
    slatePriceExt.setSlateId(slateId);
    slatePriceExt.setPriceType(priceType);
    slatePriceExt.setPageNumber(pageNumber);

    if((contractCode != null) && (priceBookId != null) && (slateId != null))
    {
      try
      {
        SlateUtil.loadSlatePrices(slatePriceExt);
      }
      catch(Throwable t)
      {
        t.printStackTrace();
        throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
      }
    }

    return slatePriceExt;
  }
}
