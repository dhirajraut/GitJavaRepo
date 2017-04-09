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
import com.intertek.domain.EditTestService;
import com.intertek.entity.CfgContract;
import com.intertek.entity.JobContractTest;
import com.intertek.entity.RB;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.CalculatorService;
import com.intertek.util.Constants;
import com.intertek.util.JobUtil;

public class EditTestServicePopupController extends SimpleFormController
{
  public EditTestServicePopupController() {
    super();
    setCommandClass(EditTestService.class);
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
    EditTestService editTestService = (EditTestService)command;

    try
    {
      HttpSession session = request.getSession();
      AddJobOrder addJobOrder = (AddJobOrder)session.getAttribute("com.intertek.web.controller.job.EditJobSelectChargesFormController.FORM.command");

      JobContractTest jobContractTest = editTestService.getJobContractTest();
      AddJobContract addJobContract = JobUtil.getAddJobContractByContractIndex(
        addJobOrder,
        editTestService.getIndex()
      );

      String location = null;

      if((addJobContract != null) && (addJobContract.getJobContract() != null))
      {
        location = addJobContract.getJobContract().getZone();
      }

      CalculatorService calculatorService = (CalculatorService)ServiceLocator.getInstance().getBean("calculatorService");
      if((jobContractTest != null))
      {
      Double qty = jobContractTest.getQuantity();
      if((addJobContract != null) && (qty != null) && (qty > 0))
      {
        JobContractTest tmpJobContractTest = CalculatorUtil.calculateTest(
          jobContractTest,
          editTestService.getRb(),
          editTestService.getContract()
        );

        //if(tmpJobContractTest == null)
        //{
        //  jobContractTest.getJobContractProd().getJobContractTests().remove(jobContractTest);
        //}
      }
      }
    }
    catch(Throwable t)
    {
      t.printStackTrace();
      errors.reject("generic.error", new Object[] {t.getMessage()}, null);
      return showForm(request, response, errors);
    }

    return new ModelAndView("refresh-page", "index", editTestService.getIndex());
  }

  protected boolean suppressValidation(HttpServletRequest request)
  {
    String operation = request.getParameter("operation");
    if((operation != null) && "search".equals(operation))
    {
      return true;
    }

    return super.suppressValidation(request);
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
    String selectedTestIndex = request.getParameter("selectedTestIndex");

    Integer mySelectedTestIndex = null;
    try
    {
      mySelectedTestIndex = new Integer(selectedTestIndex);
    }
    catch(Exception e) {}

    EditTestService editTestService = new EditTestService();
    editTestService.setContractCode(contractCode);
    editTestService.setIndex(index);
    editTestService.setSelectedVesselIndex(selectedVesselIndex);
    editTestService.setSelectedProdIndex(selectedProdIndex);

    HttpSession session = request.getSession();
    AddJobOrder addJobOrder = (AddJobOrder)session.getAttribute("com.intertek.web.controller.job.EditJobSelectChargesFormController.FORM.command");

    AddJobContract addJobContract = JobUtil.getAddJobContractByContractIndex(
      addJobOrder,
      index
    );
    AddJobContractVessel addJobContractVessel = JobUtil.getAddJobContractVesselByIndex(
      addJobContract,
      editTestService.getSelectedVesselIndex()
    );
    AddJobContractProd addJobContractProd = JobUtil.getAddJobContractProdBySelectedProdIndex(
      addJobContractVessel,
      editTestService.getSelectedProdIndex()
    );
    JobContractTest jobContractTest = JobUtil.getJobContractTestByIndex(
      addJobContractProd,
      mySelectedTestIndex
    );
    try {
    if(jobContractTest != null)
    {
      CalculatorService calculatorService = (CalculatorService)ServiceLocator.getInstance().getBean("calculatorService");
      CfgContract contract = addJobContract.getCfgContract();

      editTestService.setContract(contract);

      RB rb = calculatorService.getRbByRbKey(Constants.Test);
      editTestService.setRb(rb);

      editTestService.setJobContractTest(jobContractTest);
    }
    } catch(Throwable t)
    {
        t.printStackTrace();
        throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
    }
    return editTestService;
  }
}
