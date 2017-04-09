package com.intertek.web.controller.calculator;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.calculator.CalculatorUtil;
import com.intertek.domain.AddJobContract;
import com.intertek.domain.AddJobContractProd;
import com.intertek.domain.AddJobContractVessel;
import com.intertek.domain.AddJobOrder;
import com.intertek.domain.AddTestService;
import com.intertek.domain.JobContractTestExt;
import com.intertek.domain.SelectedTest;
import com.intertek.entity.CfgContract;
import com.intertek.entity.JobContractTest;
import com.intertek.entity.RB;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.CalculatorService;
import com.intertek.service.TestService;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;
import com.intertek.util.JobUtil;

public class AddTestServicePopupController extends SimpleFormController
{
  public AddTestServicePopupController() {
    super();
    setCommandClass(AddTestService.class);
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
    AddTestService addTestService = (AddTestService)command;
    String operation = request.getParameter("operation");
    if(operation == null)
    {
      return showForm(request, response, errors);
    }
    else if("return".equals(operation))
    {
      return new ModelAndView("refresh-page", "index", addTestService.getIndex());
    }

    try
    {
      HttpSession session = request.getSession();
      AddJobOrder addJobOrder = (AddJobOrder)session.getAttribute("com.intertek.web.controller.job.EditJobSelectChargesFormController.FORM.command");

      AddJobContract addJobContract = JobUtil.getAddJobContractByContractIndex(
        addJobOrder,
        addTestService.getIndex()
      );
      AddJobContractVessel addJobContractVessel = JobUtil.getAddJobContractVesselByIndex(
        addJobContract,
        addTestService.getSelectedVesselIndex()
      );
      AddJobContractProd addJobContractProd = JobUtil.getAddJobContractProdBySelectedProdIndex(
        addJobContractVessel,
        addTestService.getSelectedProdIndex()
      );

      String location = null;

      if((addJobContract != null) && (addJobContract.getJobContract() != null))
      {
        location = addJobContract.getJobContract().getZone();
      }

      if("search".equals(operation))
      {
        String pageNumberStr = request.getParameter("pageNumber");

        int pageNumber = 1;
        try
        {
          pageNumber = new Integer(pageNumberStr).intValue();
        }
        catch (Exception e)
        {
        }
        if (pageNumber <= 0) pageNumber = 1;

        TestService testService = (TestService) ServiceLocator.getInstance().getBean("testService");

        try
        {
          List searchTests = testService.getTests(
            addTestService.getContractCode(),
            addTestService.getContract().getPriceBookId(),
            addTestService.getProductGroup(),
            addTestService.getContractSearchCD(),
            addTestService.getSearchText(),
            addTestService.getSearchType(),
            location != null ? location : "*",
            DateUtil.formatDate(addJobOrder.getJobOrder().getJobFinishDate(), "yyyyMMdd"),
            addJobContract.getJobContract().getLanguage()
          );

          SelectedTest[] newSelectedTests = (SelectedTest[])searchTests.toArray(new SelectedTest[0]);
          addTestService.setSelectedTests(newSelectedTests);
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
        if((addJobContractVessel != null) && (addJobContractProd != null))
        {
          SelectedTest[] selectedTests = addTestService.getSelectedTests();
          int size = selectedTests != null ? selectedTests.length : 0;
          for(int i=0; i<size; i++)
          {
            Double qty = selectedTests[i].getQty();
            if(selectedTests[i].isSelected() && (qty != null) && (qty > 0))
            {
              JobContractTest jobContractTest = new JobContractTest();
              jobContractTest.setJobContractProd(addJobContractProd.getJobContractProd());

              jobContractTest.setQuantity(qty);
              jobContractTest.setTest(selectedTests[i].getTest());

              JobContractTest tmpJobContractTest = CalculatorUtil.calculateTest(
                jobContractTest,
                addTestService.getRb(),
                addTestService.getContract()
              );

              if(tmpJobContractTest != null)
              {
                JobContractTestExt testExt = new JobContractTestExt();
                testExt.setTest(jobContractTest);
                addJobContractProd.getJobContractTestExts().add(testExt);
                addJobContractProd.getJobContractProd().getJobContractTests().add(jobContractTest);
              }
            }
          }
        }
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
      request.setAttribute("operation", operation);
      SelectedTest[] selectedTests = addTestService.getSelectedTests();
      StringBuffer sb = new StringBuffer();
      int count = 0;
      if(selectedTests != null)
      {
        for(int i=0; i<selectedTests.length; i++)
        {
          if(selectedTests[i] != null)
          {
            if(selectedTests[i].isSelected())
            {
              if(count > 0) sb.append(",");
              sb.append(selectedTests[i].getTest().getTestId());

              count ++;
            }
            selectedTests[i].setSelected(false);
            selectedTests[i].setQty(1.0);
          }
        }
      }

      if(count > 0)
      {
        request.setAttribute("tests_added", sb.toString());
      }
      else
      {
        request.setAttribute("tests_added", null);
      }

      return showForm(request, response, errors);
    }

    return new ModelAndView("refresh-page", "index", addTestService.getIndex());
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
    String index = request.getParameter("index");
    String selectedVesselIndex = request.getParameter("selectedVesselIndex");
    String selectedProdIndex = request.getParameter("selectedProdIndex");

    AddTestService addTestService = new AddTestService();
    addTestService.setContractCode(contractCode);
    addTestService.setIndex(index);
    addTestService.setSelectedVesselIndex(selectedVesselIndex);
    addTestService.setSelectedProdIndex(selectedProdIndex);

    HttpSession session = request.getSession();
    AddJobOrder addJobOrder = (AddJobOrder)session.getAttribute("com.intertek.web.controller.job.EditJobSelectChargesFormController.FORM.command");
    AddJobContract addJobContract = JobUtil.getAddJobContractByContractIndex(
      addJobOrder,
      index
    );
    try {
    if(addJobContract != null)
    {
      CalculatorService calculatorService = (CalculatorService)ServiceLocator.getInstance().getBean("calculatorService");
      CfgContract contract = addJobContract.getCfgContract();
      addTestService.setContract(contract);

      RB rb = calculatorService.getRbByRbKey(Constants.Test);
      addTestService.setRb(rb);
    }
    } catch(Throwable t)
    {
        t.printStackTrace();
        throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
    }
    return addTestService;
  }
}
