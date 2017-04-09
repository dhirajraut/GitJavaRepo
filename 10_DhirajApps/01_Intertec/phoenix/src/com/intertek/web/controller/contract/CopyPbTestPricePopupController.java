package com.intertek.web.controller.contract;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.domain.CopyPbTestPrice;
import com.intertek.domain.SelectedTest;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.TestService;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;
import com.intertek.util.TestUtil;

public class CopyPbTestPricePopupController extends SimpleFormController
{
  public CopyPbTestPricePopupController() {
    super();
    setCommandClass(CopyPbTestPrice.class);
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
    CopyPbTestPrice copyPbTestPrice = (CopyPbTestPrice)command;
    String operation = request.getParameter("operation");
    if(operation == null)
    {
      return showForm(request, response, errors);
    }
    else if("return".equals(operation))
    {
      ModelAndView modelAndVeiw = new ModelAndView("test-slate-refresh-page");
      modelAndVeiw.addObject("operationType", "searchPriceBookTestPrice");
      modelAndVeiw.addObject("pageNumber", copyPbTestPrice.getPageNumber());

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

        TestService testService = (TestService) ServiceLocator.getInstance().getBean("testService");

        try
        {
          TestUtil.loadSelectedTestsForPb(
            copyPbTestPrice,
            searchPageNumber
          );
        }
        catch (Exception e)
        {
          e.printStackTrace();
          errors.reject("search.test.error", new Object[] { e.getMessage() }, null);
          return showForm(request, response, errors);
        }

        return showForm(request, response, errors);
      }
      else
      {
        TestUtil.copySelectedTestPricesFromPb(copyPbTestPrice);
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
      TestUtil.loadSelectedTestsForPb(
        copyPbTestPrice,
        copyPbTestPrice.getPagination().getCurrentPageNum()
      );

      return showForm(request, response, errors);
    }

    ModelAndView modelAndVeiw = new ModelAndView("test-slate-refresh-page");
    modelAndVeiw.addObject("operationType", "searchPriceBookTestPrice");
    modelAndVeiw.addObject("pageNumber", copyPbTestPrice.getPageNumber());

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

    CopyPbTestPrice copyPbTestPrice = new CopyPbTestPrice();
    copyPbTestPrice.setContractCode(contractCode);
    copyPbTestPrice.setPriceBookId(priceBookId);
    copyPbTestPrice.setPageNumber(pageNumber);

    if((contractCode != null) && (priceBookId != null))
    {
      try
      {
        TestUtil.loadSelectedTestsForPb(
          copyPbTestPrice,
          1
        );
      }
      catch(Throwable t)
      {
        t.printStackTrace();
        throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
      }
    }

    return copyPbTestPrice;
  }
}
