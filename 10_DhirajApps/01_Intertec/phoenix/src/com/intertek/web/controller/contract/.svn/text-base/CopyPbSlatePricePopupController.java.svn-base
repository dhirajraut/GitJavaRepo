package com.intertek.web.controller.contract;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.domain.CopyPbSlatePrice;
import com.intertek.domain.SelectedSlate;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.SlateService;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;
import com.intertek.util.SlateUtil;

public class CopyPbSlatePricePopupController extends SimpleFormController
{
  public CopyPbSlatePricePopupController() {
    super();
    setCommandClass(CopyPbSlatePrice.class);
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
    CopyPbSlatePrice copyPbSlatePrice = (CopyPbSlatePrice)command;
    String operation = request.getParameter("operation");
    if(operation == null)
    {
      return showForm(request, response, errors);
    }
    else if("return".equals(operation))
    {
      ModelAndView modelAndVeiw = new ModelAndView("test-slate-refresh-page");
      modelAndVeiw.addObject("operationType", "searchPriceBookSlatePrice");
      modelAndVeiw.addObject("pageNumber", copyPbSlatePrice.getPageNumber());

      return modelAndVeiw;
    }

    try
    {
      if("search".equals(operation))
      {
        String searchPageNumberStr = request.getParameter("searchPageNumber");

        int searchPageNumber = 1;
        try
        {
          searchPageNumber = new Integer(searchPageNumberStr).intValue();
        }
        catch (Exception e)
        {
        }
        if (searchPageNumber <= 0) searchPageNumber = 1;

        SlateService slateService = (SlateService) ServiceLocator.getInstance().getBean("slateService");

        try
        {
          SlateUtil.loadSelectedSlatesForPb(
            copyPbSlatePrice,
            searchPageNumber
          );
        }
        catch (Exception e)
        {
          e.printStackTrace();
          errors.reject("search.slate.error", new Object[] { e.getMessage() }, null);
          return showForm(request, response, errors);
        }

        return showForm(request, response, errors);
      }
      else
      {
        SlateUtil.copySelectedSlatePricesFromPb(copyPbSlatePrice);
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
      SlateUtil.loadSelectedSlatesForPb(
        copyPbSlatePrice,
        copyPbSlatePrice.getPagination().getCurrentPageNum()
      );

      return showForm(request, response, errors);
    }

    ModelAndView modelAndVeiw = new ModelAndView("test-slate-refresh-page");
    modelAndVeiw.addObject("operationType", "searchPriceBookSlatePrice");
    modelAndVeiw.addObject("pageNumber", copyPbSlatePrice.getPageNumber());

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
    String priceBookId = request.getParameter("priceBookId");
    String pageNumber = request.getParameter("pageNumber");

    CopyPbSlatePrice copyPbSlatePrice = new CopyPbSlatePrice();
    copyPbSlatePrice.setContractCode(contractCode);
    copyPbSlatePrice.setPriceBookId(priceBookId);
    copyPbSlatePrice.setPageNumber(pageNumber);

    if((contractCode != null) && (priceBookId != null))
    {
      try
      {
        SlateUtil.loadSelectedSlatesForPb(
          copyPbSlatePrice,
          1
        );
      }
      catch(Throwable t)
      {
        t.printStackTrace();
        throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
      }
    }

    return copyPbSlatePrice;
  }
}
