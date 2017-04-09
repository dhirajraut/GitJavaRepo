package com.intertek.web.controller.calculator;

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
import com.intertek.domain.AddManualTestService;
import com.intertek.domain.JobContractManualTestExt;
import com.intertek.entity.CfgContract;
import com.intertek.entity.JobContractManualTest;
import com.intertek.entity.RB;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.CalculatorService;
import com.intertek.util.Constants;
import com.intertek.util.JobUtil;

public class AddManualTestServicePopupController extends SimpleFormController
{
  public AddManualTestServicePopupController() {
    super();
    setCommandClass(AddManualTestService.class);
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
    AddManualTestService addTestService = (AddManualTestService)command;
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

      if((addJobContractVessel != null) && (addJobContractProd != null))
      {
        JobContractManualTestExt manualTestExt = addTestService.getJobContractManualTestExt();
        if(manualTestExt != null)
        {
          manualTestExt.getManualTest().setJobContractProd(addJobContractProd.getJobContractProd());

          JobContractManualTest tmpJobContractManualTest = CalculatorUtil.calculateManualTest(
            manualTestExt.getManualTest(),
            addTestService.getRb(),
            addTestService.getContract()
          );

          if(tmpJobContractManualTest != null)
          {
            if(!addJobContractProd.getJobContractManualTestExts().contains(manualTestExt))
            {
              addJobContractProd.getJobContractManualTestExts().add(manualTestExt);
              addJobContractProd.getJobContractProd().getJobContractManualTests().add(manualTestExt.getManualTest());
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
      JobContractManualTestExt jobContractManualTestExt = new JobContractManualTestExt();
      JobContractManualTest manualTest = new JobContractManualTest();
      jobContractManualTestExt.setManualTest(manualTest);
      addTestService.setJobContractManualTestExt(jobContractManualTestExt);

      request.setAttribute("operation", operation);

      return showForm(request, response, errors);
    }

    return new ModelAndView("refresh-page", "index", addTestService.getIndex());
  }

  protected boolean suppressValidation(HttpServletRequest request, Object command)
  {
    String operation = request.getParameter("operation");
    if((operation != null) && "return".equals(operation))
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
    String selectedManualTestIndex = request.getParameter("selectedManualTestIndex");

    Integer mySelectedManualTestIndex = null;
    try
    {
      mySelectedManualTestIndex = new Integer(selectedManualTestIndex);
    }
    catch(Exception e) {}

    AddManualTestService addTestService = new AddManualTestService();
    addTestService.setContractCode(contractCode);
    addTestService.setIndex(index);
    addTestService.setSelectedVesselIndex(selectedVesselIndex);
    addTestService.setSelectedProdIndex(selectedProdIndex);
    addTestService.setSelectedManualTestIndex(mySelectedManualTestIndex);

    JobContractManualTestExt jobContractManualTestExt = null;

    HttpSession session = request.getSession();
    AddJobOrder addJobOrder = (AddJobOrder)session.getAttribute("com.intertek.web.controller.job.EditJobSelectChargesFormController.FORM.command");

    AddJobContract addJobContract = JobUtil.getAddJobContractByContractIndex(
      addJobOrder,
      index
    );
    AddJobContractVessel addJobContractVessel = JobUtil.getAddJobContractVesselByIndex(
      addJobContract,
      addTestService.getSelectedVesselIndex()
    );
    AddJobContractProd addJobContractProd = JobUtil.getAddJobContractProdBySelectedProdIndex(
      addJobContractVessel,
      addTestService.getSelectedProdIndex()
    );
    try {
    if(addJobContractProd != null)
    {
      CalculatorService calculatorService = (CalculatorService)ServiceLocator.getInstance().getBean("calculatorService");
      CfgContract contract = addJobContract.getCfgContract();
      addTestService.setContract(contract);

      RB rb = calculatorService.getRbByRbKey(Constants.Test);
      addTestService.setRb(rb);

      jobContractManualTestExt = JobUtil.getJobContractManualTestExtByIndex(
        addJobContractProd,
        mySelectedManualTestIndex
      );
    }

    if(jobContractManualTestExt == null)
    {
      jobContractManualTestExt = new JobContractManualTestExt();
      JobContractManualTest manualTest = new JobContractManualTest();
      jobContractManualTestExt.setManualTest(manualTest);
    }
    addTestService.setJobContractManualTestExt(jobContractManualTestExt);
    } catch(Throwable t)
    {
        t.printStackTrace();
        throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
    }
    return addTestService;
  }
}
