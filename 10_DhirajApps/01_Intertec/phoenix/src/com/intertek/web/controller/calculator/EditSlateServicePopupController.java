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
import com.intertek.domain.EditSlateService;
import com.intertek.entity.CfgContract;
import com.intertek.entity.JobContractSlate;
import com.intertek.entity.RB;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.CalculatorService;
import com.intertek.util.Constants;
import com.intertek.util.JobUtil;

public class EditSlateServicePopupController extends SimpleFormController
{
  public EditSlateServicePopupController() {
    super();
    setCommandClass(EditSlateService.class);
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
    EditSlateService editSlateService = (EditSlateService)command;

    try
    {
      HttpSession session = request.getSession();
      AddJobOrder addJobOrder = (AddJobOrder)session.getAttribute("com.intertek.web.controller.job.EditJobSelectChargesFormController.FORM.command");

      JobContractSlate jobContractSlate = editSlateService.getJobContractSlate();
      AddJobContract addJobContract = JobUtil.getAddJobContractByContractIndex(
        addJobOrder,
        editSlateService.getIndex()
      );

      String location = null;

      if((addJobContract != null) && (addJobContract.getJobContract() != null))
      {
        location = addJobContract.getJobContract().getZone();
      }

      CalculatorService calculatorService = (CalculatorService)ServiceLocator.getInstance().getBean("calculatorService");
      if(jobContractSlate != null)
      {
      Double qty = jobContractSlate.getQuantity();
      if((addJobContract != null) && (qty != null) && (qty > 0))
      {
        JobContractSlate tmpJobContractSlate = CalculatorUtil.calculateSlate(
          jobContractSlate,
          editSlateService.getRb(),
          editSlateService.getContract()
        );

        //if(tmpJobContractSlate == null)
        //{
        //  jobContractSlate.getJobContractProd().getJobContractSlates().remove(jobContractSlate);
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

    return new ModelAndView("refresh-page", "index", editSlateService.getIndex());
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
    String selectedSlateIndex = request.getParameter("selectedSlateIndex");

    Integer mySelectedSlateIndex = null;
    try
    {
      mySelectedSlateIndex = new Integer(selectedSlateIndex);
    }
    catch(Exception e) {}

    EditSlateService editSlateService = new EditSlateService();
    editSlateService.setContractCode(contractCode);
    editSlateService.setIndex(index);
    editSlateService.setSelectedVesselIndex(selectedVesselIndex);
    editSlateService.setSelectedProdIndex(selectedProdIndex);

    HttpSession session = request.getSession();
    AddJobOrder addJobOrder = (AddJobOrder)session.getAttribute("com.intertek.web.controller.job.EditJobSelectChargesFormController.FORM.command");

    AddJobContract addJobContract = JobUtil.getAddJobContractByContractIndex(
      addJobOrder,
      index
    );
    AddJobContractVessel addJobContractVessel = JobUtil.getAddJobContractVesselByIndex(
      addJobContract,
      editSlateService.getSelectedVesselIndex()
    );
    AddJobContractProd addJobContractProd = JobUtil.getAddJobContractProdBySelectedProdIndex(
      addJobContractVessel,
      editSlateService.getSelectedProdIndex()
    );
    JobContractSlate jobContractSlate = JobUtil.getJobContractSlateByIndex(
      addJobContractProd,
      mySelectedSlateIndex
    );
    try {
    if(jobContractSlate != null)
    {
      CalculatorService calculatorService = (CalculatorService)ServiceLocator.getInstance().getBean("calculatorService");
      CfgContract contract = addJobContract.getCfgContract();
      editSlateService.setContract(contract);

      RB rb = calculatorService.getRbByRbKey(Constants.Slate);
      editSlateService.setRb(rb);

      editSlateService.setJobContractSlate(jobContractSlate);
    }
    } catch(Throwable t)
    {
        t.printStackTrace();
        throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
    }
    return editSlateService;
  }
}
