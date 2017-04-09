package com.intertek.web.controller.calculator;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.calculator.CalculatorUtil;
import com.intertek.calculator.ControlExt;
import com.intertek.command.RecalculateInspectionCommand;
import com.intertek.domain.AddJobContract;
import com.intertek.domain.AddJobContractVessel;
import com.intertek.domain.AddJobOrder;
import com.intertek.entity.Control;
import com.intertek.entity.JobContract;
import com.intertek.entity.JobContractVessel;
import com.intertek.entity.JobContractVesselControl;
import com.intertek.entity.VesselType;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.CalculatorService;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;
import com.intertek.util.InspectionServiceUtil;
import com.intertek.util.JobUtil;
import com.intertek.util.ServiceUtil;

public class AddJobContractVesselPopupController extends SimpleFormController
{
  public AddJobContractVesselPopupController() {
    super();
    setCommandClass(AddJobContractVessel.class);
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
    AddJobContractVessel addJobContractVessel = (AddJobContractVessel)command;

    try
    {
      String operationType = request.getParameter("operationType");
      if("selectVesselType".equals(operationType))
      {
        VesselType selectedVesselType = CalculatorUtil.getSelectedVesselType(
          addJobContractVessel.getVesselTypes(),
          addJobContractVessel.getJobContractVessel().getType()
        );
        addJobContractVessel.setSelectedVesselType(selectedVesselType);

        return showForm(request, response, errors);
      }

      List controls = new ArrayList();
      ControlExt[] controlExts = addJobContractVessel.getControlExts();
      if(controlExts != null)
      {
        boolean disabledControl = false;
        VesselType selectedVesselType = addJobContractVessel.getSelectedVesselType();
        if(selectedVesselType != null)
        {
          Integer vesselFlag = selectedVesselType.getVesselFlag();
          if((vesselFlag == null) || (vesselFlag.intValue() == 0)) disabledControl = true;
        }

        for(int i=0; i<controlExts.length; i++)
        {
          String dataInput = controlExts[i].getDataInput();

          if(Constants.ADDITIONAL_VESSEL.equals(controlExts[i].getControl().getControlId().getObjectName()))
          {
            if(disabledControl) continue;
          }

          if(dataInput != null)
          {
            controls.add(controlExts[i]);
          }
        }
      }

      HttpSession session = request.getSession();
      AddJobOrder addJobOrder = (AddJobOrder)session.getAttribute("com.intertek.web.controller.job.EditJobSelectChargesFormController.FORM.command");

      if(addJobOrder != null)
      {
        CalculatorUtil.deleteUnusedJobContractVesselControls(
          controls,
          addJobContractVessel.getJobContractVessel().getControls()
        );

        for(int i=0; i<controls.size(); i++)
        {
          ControlExt controlExt = (ControlExt)controls.get(i);
          JobContractVesselControl jControl = CalculatorUtil.getJobContractVesselControl(
            controlExt,
            new ArrayList(addJobContractVessel.getJobContractVessel().getControls())
          );
          if((jControl != null) && (jControl.getId() == 0))
          {
            jControl.setJobContractVessel(addJobContractVessel.getJobContractVessel());
            addJobContractVessel.getJobContractVessel().getControls().add(jControl);
          }
        }
      }

      AddJobContract addJobContract = JobUtil.getAddJobContractByContractIndex(
        addJobOrder,
        addJobContractVessel.getIndex()
      );

      CalculatorService calculatorService = (CalculatorService)ServiceLocator.getInstance().getBean("calculatorService");
      List productGroups = calculatorService.getProductGroups(
        addJobContract.getCfgContract().getProductGroupSet(),
        addJobContract.getJobContract().getContractCode(),
        addJobContract.getCfgContract().getPriceBookId(),
        addJobContractVessel.getJobContractVessel().getType(),
        new Integer(addJobContract.getCfgContract().getUom()),
        addJobContract.getJobContract().getZone(),
        DateUtil.formatDate(addJobContract.getJobContract().getJobOrder().getJobFinishDate(), "yyyyMMdd"),
        addJobContract.getJobContract().getLanguage() // language
      );
      addJobContractVessel.setProductGroups(productGroups);

      JobContractVessel jobContractVessel = addJobContractVessel.getJobContractVessel();
      JobContract jobContract = jobContractVessel.getJobContract();
      if(!jobContract.getJobContractVessels().contains(jobContractVessel))
      {
        jobContract.getJobContractVessels().add(jobContractVessel);
        JobUtil.addAddJobContractVesselToAddJobContract(addJobContract, addJobContractVessel);
      }
      else
      {
        ServiceUtil.applyCommand(addJobContractVessel, addJobContract, new RecalculateInspectionCommand());
      }
    }
    catch(Throwable t)
    {
      t.printStackTrace();
      errors.reject("generic.error", new Object[] {t.getMessage()}, null);
      return showForm(request, response, errors);
    }

    return new ModelAndView("refresh-page", "index", addJobContractVessel.getIndex());
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

    HttpSession session = request.getSession();
    AddJobOrder addJobOrder = (AddJobOrder)session.getAttribute("com.intertek.web.controller.job.EditJobSelectChargesFormController.FORM.command");

    AddJobContract addJobContract = JobUtil.getAddJobContractByContractIndex(
      addJobOrder,
      index
    );
    AddJobContractVessel addJobContractVessel = JobUtil.getAddJobContractVesselByIndex(
      addJobContract,
      selectedVesselIndex
    );
    try {
    if(addJobContract != null)
    {
      CalculatorService calculatorService = (CalculatorService)ServiceLocator.getInstance().getBean("calculatorService");
      if(addJobContractVessel == null)
      {
        JobContractVessel jobContractVessel = new JobContractVessel();
        jobContractVessel.setJobNumber(addJobContract.getJobContract().getJobNumber());
        jobContractVessel.setJobContract(addJobContract.getJobContract());

        if(Constants.ANALYTICAL_SERVICE_JOBTYPE.equals(addJobOrder.getJobOrder().getJobType())
          || Constants.OUTSOURCING_JOBTYPE.equals(addJobOrder.getJobOrder().getJobType())
        )
        {
          jobContractVessel.setType("Other");
        }

        addJobContractVessel = JobUtil.createAddJobContractVessel(jobContractVessel);
        addJobContractVessel.setContractCode(contractCode);
      }
      addJobContractVessel.setIndex(index);
      addJobContractVessel.setSelectedVesselIndex(selectedVesselIndex);

      List vesselTypes = addJobContract.getVesselTypes();
      if(vesselTypes == null)
      {
        vesselTypes = calculatorService.getVesselTypes(
          addJobContract.getCfgContract().getVesselSet(),
          contractCode,
          addJobContract.getCfgContract().getPriceBookId(),
          addJobContract.getJobContract().getZone(),
          DateUtil.formatDate(addJobContract.getJobContract().getJobOrder().getJobFinishDate(), "yyyyMMdd"),
          addJobContract.getJobContract().getLanguage() // language
        );
        addJobContract.setVesselTypes(vesselTypes);
      }
      addJobContractVessel.setVesselTypes(vesselTypes);

      String myVesselType = addJobContractVessel.getJobContractVessel().getType();
      VesselType selectedVesselType = CalculatorUtil.getSelectedVesselType(vesselTypes, myVesselType);
      if(selectedVesselType == null)
      {
        if(vesselTypes.size() > 0)
        {
          selectedVesselType = (VesselType)vesselTypes.get(0);
        }
      }
      addJobContractVessel.setSelectedVesselType(selectedVesselType);

      List controls = calculatorService.getControls(
        Constants.V_INSP,
        contractCode,
        addJobContract.getCfgContract().getPriceBookId(),
        DateUtil.formatDate(addJobContract.getJobContract().getJobOrder().getJobFinishDate(), "yyyyMMdd"),
        addJobContract.getJobContract().getLanguage() // language
      );

      ControlExt[] controlExts = new ControlExt[controls.size()];
      for(int i=0; i<controls.size(); i++)
      {
        Control control = (Control)controls.get(i);

        controlExts[i] = new ControlExt(control);
      }

      addJobContractVessel.setControlExts(controlExts);
      List addedJobContractVesselControls = new ArrayList(addJobContractVessel.getJobContractVessel().getControls());

      InspectionServiceUtil.populateControlsFromJobContractVesselControls(
        addJobContractVessel.getControlExts(),
        addedJobContractVesselControls
      );
    }
    } catch(Throwable t)
    {
        t.printStackTrace();
        throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
    }
    return addJobContractVessel;
  }
}
