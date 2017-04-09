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

import com.intertek.calculator.CalculatorManager;
import com.intertek.calculator.CalculatorRequest;
import com.intertek.calculator.CalculatorResult;
import com.intertek.calculator.CalculatorUtil;
import com.intertek.calculator.ControlExt;
import com.intertek.calculator.SamplingIncludesPricer;
import com.intertek.domain.AddJobContract;
import com.intertek.domain.AddJobContractProd;
import com.intertek.domain.AddJobContractVessel;
import com.intertek.domain.AddJobOrder;
import com.intertek.domain.DisplayControl;
import com.intertek.domain.JobContractProductServiceExt;
import com.intertek.domain.JobContractServiceExt;
import com.intertek.domain.JobContractVesselServiceExt;
import com.intertek.entity.CfgContract;
import com.intertek.entity.Control;
import com.intertek.entity.JobContractProductService;
import com.intertek.entity.JobContractService;
import com.intertek.entity.JobContractVesselService;
import com.intertek.entity.Service;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.CalculatorService;
import com.intertek.servicetype.JobServiceType;
import com.intertek.servicetype.ServiceTypeExt;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;
import com.intertek.util.JobContractProductServiceUtil;
import com.intertek.util.JobContractServiceUtil;
import com.intertek.util.JobContractVesselServiceUtil;
import com.intertek.util.JobUtil;

public class DisplayControlPopupController extends SimpleFormController
{
  public DisplayControlPopupController() {
    super();
    setCommandClass(DisplayControl.class);
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
    DisplayControl displayControl = (DisplayControl)command;
    String operation = request.getParameter("operation");

    if(operation == null)
    {
      return showForm(request, response, errors);
    }
    else if("return".equals(operation))
    {
      return new ModelAndView("refresh-page", "index", displayControl.getIndex());
    }

    try
    {
      HttpSession session = request.getSession();
      AddJobOrder addJobOrder = (AddJobOrder)session.getAttribute("com.intertek.web.controller.job.EditJobSelectChargesFormController.FORM.command");

      AddJobContract addJobContract = JobUtil.getAddJobContractByContractIndex(
        addJobOrder,
        displayControl.getIndex()
      );
      AddJobContractVessel addJobContractVessel = JobUtil.getAddJobContractVesselByIndex(
        addJobContract,
        displayControl.getSelectedVesselIndex()
      );
      AddJobContractProd addJobContractProd = JobUtil.getAddJobContractProdBySelectedProdIndex(
        addJobContractVessel,
        displayControl.getSelectedProdIndex()
      );

      CalculatorService calculatorService = (CalculatorService)ServiceLocator.getInstance().getBean("calculatorService");

      if("selectService".equals(operation))
      {
        if((displayControl.getServiceName() != null) && !"".equals(displayControl.getServiceName()))
        {
          ServiceTypeExt serviceTypeExt = displayControl.getSelectedServiceTypeExt();
          if(serviceTypeExt != null)
          {
            Service selectedService = serviceTypeExt.getSelectedService(
              displayControl.getServiceName()
            );
            displayControl.setSelectedService(selectedService);
          }

          List controls = calculatorService.getControls(
            displayControl.getServiceName(),
            displayControl.getContractCode(),
            displayControl.getContract().getPriceBookId(),
            displayControl.getNominationDateStr(),
            addJobContract.getJobContract().getLanguage() // language
          );

          ControlExt[] controlExts = new ControlExt[controls.size()];
          for(int i=0; i<controls.size(); i++)
          {
            Control control = (Control)controls.get(i);

            controlExts[i] = new ControlExt(control);
          }

          displayControl.setControlExts(controlExts);
        }
        else
        {
          displayControl.setControlExts(new ControlExt[0]);
        }

        return showForm(request, response, errors);
      }

      List controls = new ArrayList();
      ControlExt[] controlExts = displayControl.getControlExts();
      if(controlExts != null)
      {
        for(int i=0; i<controlExts.length; i++)
        {
          String dataInput = controlExts[i].getDataInput();
          if(Constants.GROUP.equals(controlExts[i].getControl().getControlType()))
          {
            if(Constants.LIGHTERAGE_TYPE.equals(controlExts[i].getControl().getControlId().getObjectName()))
            {
              controlExts[i].setParameter(Constants.LIGHTERAGE_TYPE, dataInput);
            }
            else if(Constants.PRODUCT_TYPE.equals(controlExts[i].getControl().getControlId().getObjectName()))
            {
              controlExts[i].setParameter(Constants.PRODUCT_TYPE, dataInput);
            }
            else if(Constants.PRODUCT_TYPE1.equals(controlExts[i].getControl().getControlId().getObjectName()))
            {
              controlExts[i].setParameter(Constants.PRODUCT_TYPE1, dataInput);
            }
            else if(Constants.Yes.equals(dataInput))
            {
              controlExts[i].setParameter(Constants.RADIO_INPUT, Boolean.TRUE);
            }
            else
            {
              controlExts[i].setParameter(Constants.RADIO_INPUT, Boolean.FALSE);
            }
            controls.add(controlExts[i]);
          }
          else if(Constants.STRING_VAL1.equals(controlExts[i].getControl().getControlId().getObjectName()))
          {
            controlExts[i].setParameter(Constants.STRING_VAL1, dataInput);
            controls.add(controlExts[i]);
          }
          else if(Constants.STRING_VAL2.equals(controlExts[i].getControl().getControlId().getObjectName()))
          {
            controlExts[i].setParameter(Constants.STRING_VAL2, dataInput);
            controls.add(controlExts[i]);
          }
          else if(Constants.STRING_VAL3.equals(controlExts[i].getControl().getControlId().getObjectName()))
          {
            controlExts[i].setParameter(Constants.STRING_VAL3, dataInput);
            controls.add(controlExts[i]);
          }
          else
          {
            Double qty = null;
            try
            {
              qty = new Double(dataInput);
            }
            catch(Exception e)
            {
            }

            if((qty != null) && (qty.doubleValue() != 0))
            {
              if(Constants.numPressCylinders.equals(controlExts[i].getControl().getControlId().getObjectName()))
              {
                controlExts[i].setParameter(Constants.numPressCylinders, qty);
              }
              else if(Constants.pressCylinderDays.equals(controlExts[i].getControl().getControlId().getObjectName()))
              {
                controlExts[i].setParameter(Constants.pressCylinderDays, qty);
              }
              else if(SamplingIncludesPricer.containsObjectName(controlExts[i].getControl().getControlId().getObjectName()))
              {
                controlExts[i].setParameter(controlExts[i].getControl().getControlId().getObjectName(), qty);
              }
              else
              {
                controlExts[i].setParameter(Constants.QUANTITY, qty);
              }
              controls.add(controlExts[i]);
            }
          }
        }
      }

      List validResults = new ArrayList();

      String location = null;

      if(controls.size() > 0)
      {
        if((addJobContract != null) && (addJobContract.getJobContract() != null))
        {
          location = addJobContract.getJobContract().getZone();
        }

        CalculatorUtil.checkVesselType(
          addJobContract,
          addJobContractVessel
        );

        CalculatorUtil.checkProductGroup(
          addJobContractVessel,
          addJobContractProd
        );

        CalculatorRequest cr = new CalculatorRequest();
        cr.setParameter(Constants.CONTRACT_ID, displayControl.getContractCode());
        cr.setParameter(Constants.SERVICE_NAME, displayControl.getServiceName());
        cr.setParameter(
          Constants.VESSEL_TYPE,
          addJobContractVessel != null ? addJobContractVessel.getJobContractVessel().getType() : "*"
        );
        cr.setParameter(
          Constants.PRODUCT_GROUP_ID,
          addJobContractProd != null ? addJobContractProd.getJobContractProd().getGroup() : "*"
        );
        cr.setParameter(
          Constants.MASTER_GROUP_ID,
          addJobContractProd != null ? addJobContractProd.getJobContractProd().getProductGroupMaster() : "*"
        );
        //cr.setParameter(Constants.SERVICE_LEVEL, serviceLevel);
        cr.setParameter(
          Constants.LOCATION,
          location != null ? location : "*"
        );
        cr.setParameter(
          Constants.NOMINATION_DATE_STR,
          DateUtil.formatDate(addJobOrder.getJobOrder().getJobFinishDate(), "yyyyMMdd")
        );
        cr.setParameter(
          Constants.NOMINATION_DATE,
          addJobOrder.getJobOrder().getJobFinishDate()
        );
        cr.setParameter(Constants.LANGUAGE_CD, addJobContract.getJobContract().getLanguage()); //hardcoded
        cr.setParameter(Constants.JOB_TYPE, addJobOrder.getJobOrder().getJobType());
        cr.setParameter(Constants.BRANCH_CODE, addJobOrder.getJobOrder().getBranchName());
        cr.setParameter(Constants.JOB_CODE, addJobContract.getJobContract().getProductType());
        cr.setParameter(Constants.CURRENCY_CD, addJobContract.getJobContract().getTransactionCurrencyCd());
        cr.setParameter(Constants.OVERRIDE_CURRENCY_RATE, addJobContract.getJobContract().getOverrideCurrRate());

        cr.setContract(displayControl.getContract());

        List contractExpressions = calculatorService.getContractExpressions(
          displayControl.getServiceName(),
          displayControl.getContractCode(),
          cr.getContract().getPriceBookId(),
          location != null ? location : "*",
          DateUtil.formatDate(addJobOrder.getJobOrder().getJobFinishDate(), "yyyyMMdd"),
          addJobContract.getJobContract().getLanguage() // language
        );

        if(contractExpressions.size() > 0)
        {
          List results = CalculatorManager.calculate(
            cr, controls, contractExpressions
          );

          for(int i=0; i<results.size(); i++)
          {
            CalculatorResult calculatorResult = (CalculatorResult)results.get(i);
            if(calculatorResult != null)
            {
              validResults.add(calculatorResult);
            }
          }
        }
      }

      if(validResults.size() > 0)
      {
        if((addJobContractVessel != null) && (addJobContractProd != null))
        {
          JobContractProductServiceExt jobContractProductServiceExt = displayControl.getSelectedJobContractProductServiceExt();
          if(jobContractProductServiceExt == null)
          {
            jobContractProductServiceExt = new JobContractProductServiceExt();

            JobContractProductService jobContractProductService = new JobContractProductService();
            jobContractProductService.setServiceName(displayControl.getServiceName());
            jobContractProductService.setServiceType(displayControl.getServiceType());
            jobContractProductService.setJobContractProd(addJobContractProd.getJobContractProd());
            addJobContractProd.getJobContractProd().getJobContractProductServices().add(jobContractProductService);

            jobContractProductServiceExt.setService(jobContractProductService);
            addJobContractProd.getAddJobContractProductServices().getAddedJobContractProductServices().add(jobContractProductServiceExt);
          }
          JobContractProductService jobContractProductService = jobContractProductServiceExt.getService();

          JobContractProductServiceUtil.deleteUnusedJobContractProductServiceControls(
            controls,
            jobContractProductServiceExt
          );

          for(int i=0; i<controls.size(); i++)
          {
            ControlExt controlExt = (ControlExt)controls.get(i);
            JobContractProductServiceUtil.populateJobContractProductServiceControl(
              controlExt,
              jobContractProductServiceExt
            );
          }

          JobContractProductServiceUtil.deleteUnusedJobContractProductServiceResults(
            validResults,
            jobContractProductServiceExt
          );

          for(int i=0; i<validResults.size(); i++)
          {
            CalculatorResult calculatorResult = (CalculatorResult)validResults.get(i);
            JobContractProductServiceUtil.populateJobContractProductServiceResult(
              calculatorResult,
              jobContractProductServiceExt,
              addJobContract.getJobContract()
            );
          }
        }
        else if(addJobContractVessel != null)
        {
          JobContractVesselServiceExt jobContractVesselServiceExt = displayControl.getSelectedJobContractVesselServiceExt();
          if(jobContractVesselServiceExt == null)
          {
            jobContractVesselServiceExt = new JobContractVesselServiceExt();

            JobContractVesselService jobContractVesselService = new JobContractVesselService();
            jobContractVesselService.setServiceName(displayControl.getServiceName());
            jobContractVesselService.setServiceType(displayControl.getServiceType());
            jobContractVesselService.setJobContractVessel(addJobContractVessel.getJobContractVessel());
            addJobContractVessel.getJobContractVessel().getJobContractVesselServices().add(jobContractVesselService);

            jobContractVesselServiceExt.setService(jobContractVesselService);
            addJobContractVessel.getAddJobContractVesselServices().getAddedJobContractVesselServices().add(jobContractVesselServiceExt);
          }
          JobContractVesselService jobContractVesselService = jobContractVesselServiceExt.getService();

          JobContractVesselServiceUtil.deleteUnusedJobContractVesselServiceControls(
            controls,
            jobContractVesselServiceExt
          );

          for(int i=0; i<controls.size(); i++)
          {
            ControlExt controlExt = (ControlExt)controls.get(i);
            JobContractVesselServiceUtil.populateJobContractVesselServiceControl(
              controlExt,
              jobContractVesselServiceExt
            );
          }

          JobContractVesselServiceUtil.deleteUnusedJobContractVesselServiceResults(
            validResults,
            jobContractVesselServiceExt
          );

          for(int i=0; i<validResults.size(); i++)
          {
            CalculatorResult calculatorResult = (CalculatorResult)validResults.get(i);
            JobContractVesselServiceUtil.populateJobContractVesselServiceResult(
              calculatorResult,
              jobContractVesselServiceExt,
              addJobContract.getJobContract()
            );
          }
        }
        else
        {
          JobContractServiceExt jobContractServiceExt = displayControl.getSelectedJobContractServiceExt();
          if(jobContractServiceExt == null)
          {
            jobContractServiceExt = new JobContractServiceExt();

            JobContractService jobContractService = new JobContractService();
            jobContractService.setServiceName(displayControl.getServiceName());
            jobContractService.setServiceType(displayControl.getServiceType());
            jobContractService.setJobContract(addJobContract.getJobContract());
            addJobContract.getJobContract().getJobContractServices().add(jobContractService);

            jobContractServiceExt.setService(jobContractService);
            addJobContract.getAddJobContractServices().getAddedJobContractServices().add(jobContractServiceExt);
          }
          JobContractService jobContractService = jobContractServiceExt.getService();

          JobContractServiceUtil.deleteUnusedJobContractServiceControls(
            controls,
            jobContractServiceExt
          );

          for(int i=0; i<controls.size(); i++)
          {
            ControlExt controlExt = (ControlExt)controls.get(i);
            JobContractServiceUtil.populateJobContractServiceControl(
              controlExt,
              jobContractServiceExt
            );
          }

          JobContractServiceUtil.deleteUnusedJobContractServiceResults(
            validResults,
            jobContractServiceExt
          );

          for(int i=0; i<validResults.size(); i++)
          {
            CalculatorResult calculatorResult = (CalculatorResult)validResults.get(i);
            JobContractServiceUtil.populateJobContractServiceResult(
              calculatorResult,
              jobContractServiceExt,
              addJobContract.getJobContract()
            );
          }
        }
      }
      else
      {
        if((addJobContractVessel != null) && (addJobContractProd != null))
        {
        }
        else if(addJobContractVessel != null)
        {
        }
        else
        {
          JobContractServiceExt jobContractServiceExt = displayControl.getSelectedJobContractServiceExt();
          if(jobContractServiceExt != null)
          {
            //addJobContract.getDeletedJobContractServices().add(jobContractService);
            addJobContract.getJobContract().getJobContractServices().remove(jobContractServiceExt.getService());
          }
        }
      }
    }
    catch(ServiceException e)
    {
      e.printStackTrace();
      errors.reject(e.getMessage(), e.getParams(), null);
      return showForm(request, response, errors);
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
      displayControl.setServiceName("");
      displayControl.setControlExts(new ControlExt[0]);
      return showForm(request, response, errors);
    }

    return new ModelAndView("refresh-page", "index", displayControl.getIndex());
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
   * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
   */
  protected Object formBackingObject(
    HttpServletRequest request
  ) throws Exception
  {
    String index = request.getParameter("index");
    String contractCode = request.getParameter("contractCode");
    String serviceName = request.getParameter("serviceName");
    String selectedVesselIndex = request.getParameter("selectedVesselIndex");
    String selectedProdIndex = request.getParameter("selectedProdIndex");
    String selectedServiceIndex = request.getParameter("selectedServiceIndex");
    String serviceType = request.getParameter("serviceType");

    Integer mySelectedServiceIndex = null;
    try
    {
      mySelectedServiceIndex = new Integer(selectedServiceIndex);
    }
    catch(Exception e) {}

    DisplayControl displayControl = new DisplayControl();
    displayControl.setContractCode(contractCode);
    displayControl.setServiceName(serviceName);
    displayControl.setIndex(index);
    displayControl.setSelectedVesselIndex(selectedVesselIndex);
    displayControl.setSelectedProdIndex(selectedProdIndex);
    displayControl.setSelectedServiceIndex(mySelectedServiceIndex);
    displayControl.setServiceType(serviceType);

    HttpSession session = request.getSession();
    AddJobOrder addJobOrder = (AddJobOrder)session.getAttribute("com.intertek.web.controller.job.EditJobSelectChargesFormController.FORM.command");
    try {
    if(addJobOrder != null)
    {
      String nominationDateStr = DateUtil.formatDate(addJobOrder.getJobOrder().getJobFinishDate(), "yyyyMMdd");
      displayControl.setNominationDateStr(nominationDateStr);

      CalculatorService calculatorService = (CalculatorService)ServiceLocator.getInstance().getBean("calculatorService");

      AddJobContract addJobContract = JobUtil.getAddJobContractByContractIndex(
        addJobOrder,
        displayControl.getIndex()
      );
      if(addJobContract != null)
      {
        CfgContract contract = addJobContract.getCfgContract();
        displayControl.setContract(contract);

        List addedJobContractServiceControls = null;
        List addedJobContractVesselServiceControls = null;
        List addedJobContractProductServiceControls = null;

        AddJobContractVessel addJobContractVessel = JobUtil.getAddJobContractVesselByIndex(
          addJobContract,
          displayControl.getSelectedVesselIndex()
        );
        AddJobContractProd addJobContractProd = JobUtil.getAddJobContractProdBySelectedProdIndex(
          addJobContractVessel,
          displayControl.getSelectedProdIndex()
        );

        if((mySelectedServiceIndex != null) && (mySelectedServiceIndex >= 0))
        {
          if((addJobContractVessel != null) && (addJobContractProd != null))
          {
            JobContractProductServiceExt jobContractProductServiceExt = JobContractProductServiceUtil.getJobContractProductServiceExtByIndex(
              addJobContractProd,
              displayControl.getSelectedServiceIndex()
            );

            if(jobContractProductServiceExt != null)
            {
              displayControl.setServiceName(jobContractProductServiceExt.getService().getServiceName());
              displayControl.setServiceType(jobContractProductServiceExt.getService().getServiceType());
              displayControl.setSelectedJobContractProductServiceExt(jobContractProductServiceExt);
              addedJobContractProductServiceControls = new ArrayList(jobContractProductServiceExt.getService().getControls());
            }
          }
          else if(addJobContractVessel != null)
          {
            JobContractVesselServiceExt jobContractVesselServiceExt = JobContractVesselServiceUtil.getJobContractVesselServiceExtByIndex(
              addJobContractVessel,
              displayControl.getSelectedServiceIndex()
            );

            if(jobContractVesselServiceExt != null)
            {
              displayControl.setServiceName(jobContractVesselServiceExt.getService().getServiceName());
              displayControl.setServiceType(jobContractVesselServiceExt.getService().getServiceType());
              displayControl.setSelectedJobContractVesselServiceExt(jobContractVesselServiceExt);
              addedJobContractVesselServiceControls = new ArrayList(jobContractVesselServiceExt.getService().getControls());
            }
          }
          else
          {
            JobContractServiceExt jobContractServiceExt = JobContractServiceUtil.getJobContractServiceExtByIndex(
              addJobContract,
              displayControl.getSelectedServiceIndex()
            );

            if(jobContractServiceExt != null)
            {
              displayControl.setServiceName(jobContractServiceExt.getService().getServiceName());
              displayControl.setServiceType(jobContractServiceExt.getService().getServiceType());
              displayControl.setSelectedJobContractServiceExt(jobContractServiceExt);
              addedJobContractServiceControls = new ArrayList(jobContractServiceExt.getService().getControls());
            }
          }
        }

        JobServiceType jobServiceType = addJobContract.getAddJobContractServices().getJobServiceType();
        displayControl.setJobServiceType(jobServiceType);

        ServiceTypeExt serviceTypeExt = jobServiceType.getServiceTypeExt(displayControl.getServiceType());
        displayControl.setSelectedServiceTypeExt(serviceTypeExt);
        if((mySelectedServiceIndex != null) && (mySelectedServiceIndex >= 0))
        {
          if(serviceTypeExt != null)
          {
            Service selectedService = serviceTypeExt.getSelectedService(
              displayControl.getServiceName()
            );
            displayControl.setSelectedService(selectedService);
            if(selectedService != null)
            {
              displayControl.setServiceName(selectedService.getServiceId().getServiceName());
            }
          }

          List controls = calculatorService.getControls(
            displayControl.getServiceName(),
            contractCode,
            contract.getPriceBookId(),
            nominationDateStr,
            addJobContract.getJobContract().getLanguage() // language
          );

          ControlExt[] controlExts = new ControlExt[controls.size()];
          for(int i=0; i<controls.size(); i++)
          {
            Control control = (Control)controls.get(i);

            controlExts[i] = new ControlExt(control);
          }

          displayControl.setControlExts(controlExts);

          if(addedJobContractProductServiceControls != null)
          {
            JobContractProductServiceUtil.populateControlsFromJobContractProductServiceControls(
              displayControl.getControlExts(),
              addedJobContractProductServiceControls
            );
          }
          else if(addedJobContractVesselServiceControls != null)
          {
            JobContractVesselServiceUtil.populateControlsFromJobContractVesselServiceControls(
              displayControl.getControlExts(),
              addedJobContractVesselServiceControls
            );
          }
          else if(addedJobContractServiceControls != null)
          {
            JobContractServiceUtil.populateControlsFromJobContractServiceControls(
              displayControl.getControlExts(),
              addedJobContractServiceControls
            );
          }
        }
      }
    }
    } catch(Throwable t)
    {
        t.printStackTrace();
        throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
    }
    return displayControl;
  }
}
