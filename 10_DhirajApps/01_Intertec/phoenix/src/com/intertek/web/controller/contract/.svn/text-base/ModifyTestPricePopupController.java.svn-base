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
import com.intertek.domain.TestPriceExt;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.TestService;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;
import com.intertek.util.TestUtil;
import com.intertek.web.propertyeditors.CustomDiscountEditor;

public class ModifyTestPricePopupController extends SimpleFormController
{
  public ModifyTestPricePopupController() {
    super();
    setCommandClass(TestPriceExt.class);
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
    TestPriceExt testPriceExt = (TestPriceExt)command;

    String refreshType = "PB".equals(testPriceExt.getPriceType()) ? "searchPriceBookTestPrice" : "searchContractTestPrice";
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
      modelAndVeiw.addObject("activeId", testPriceExt.getTestId());
      modelAndVeiw.addObject("pageNumber", testPriceExt.getPageNumber());

      return modelAndVeiw;
    }

    try
    {
      if("addTestPrice".equals(operation))
      {
        TestUtil.addTestPrice(testPriceExt);

        return showForm(request, response, errors);
      }
      else if("deleteTestPrice".equals(operation))
      {
        int index = -1;
        try
        {
          index = new Integer(request.getParameter("index")).intValue();
        }
        catch (Exception e)
        {
        }

        if(index >= 0) TestUtil.removeTestPrice(testPriceExt, index);

        return showForm(request, response, errors);
      }
      else
      {
        TestUtil.saveTestPrices(testPriceExt);
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
        TestUtil.loadTestPrices(testPriceExt);
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
    modelAndVeiw.addObject("activeId", testPriceExt.getTestId());
    modelAndVeiw.addObject("pageNumber", testPriceExt.getPageNumber());

    return modelAndVeiw;
  }

  protected boolean suppressValidation(HttpServletRequest request, Object command)
  {
    String operation = request.getParameter("operation");
    if((operation != null) && ("addTestPrice".equals(operation) || "deleteTestPrice".equals(operation) || "return".equals(operation)))
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
    String testId = request.getParameter("testId");
    String priceType = request.getParameter("priceType");
    String pageNumber = request.getParameter("pageNumber");

    TestPriceExt testPriceExt = new TestPriceExt();
    testPriceExt.setContractCode(contractCode);
    testPriceExt.setPriceBookId(priceBookId);
    testPriceExt.setTestId(testId);
    testPriceExt.setPriceType(priceType);
    testPriceExt.setPageNumber(pageNumber);

    if((contractCode != null) && (priceBookId != null) && (testId != null))
    {
      try
      {
        TestUtil.loadTestPrices(testPriceExt);
      }
      catch(Throwable t)
      {
        t.printStackTrace();
        throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
      }
    }

    return testPriceExt;
  }
}
