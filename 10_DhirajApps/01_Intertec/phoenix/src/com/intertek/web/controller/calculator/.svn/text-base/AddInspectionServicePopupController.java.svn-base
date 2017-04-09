package com.intertek.web.controller.calculator;

import java.text.*;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.beans.propertyeditors.CustomNumberEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.RequestUtils;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.calculator.*;
import com.intertek.entity.*;
import com.intertek.exception.*;
import com.intertek.service.*;
import com.intertek.util.*;
import com.intertek.locator.*;
import com.intertek.domain.*;
import com.intertek.pagination.*;

public class AddInspectionServicePopupController extends SimpleFormController
{
  public AddInspectionServicePopupController() {
    super();
    setCommandClass(AddInspectionService.class);
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
    AddInspectionService addInspectionService = (AddInspectionService)command;
    int validCount = 0;

    try
    {
      AddJobContractProd addJobContractProd = addInspectionService.getAddJobContractProd();

      String operationType = request.getParameter("operationType");
      if("selectProductGroup".equals(operationType))
      {
        ProductGroup selectedProductGroup = CalculatorUtil.getSelectedProductGroup(
          addInspectionService.getProductGroups(),
          addJobContractProd.getJobContractProd().getGroup()
        );
        addInspectionService.setSelectedProductGroup(selectedProductGroup);

        return showForm(request, response, errors);
      }

      List controls = new ArrayList();
      ControlExt[] controlExts = addInspectionService.getControlExts();
      ProductGroup selectedProductGroup = addInspectionService.getSelectedProductGroup();
      boolean additionalVessel = CalculatorUtil.getAdditionalVesselFlag(
        addJobContractProd.getJobContractProd().getJobContractVessel()
      );

      if(controlExts != null)
      {
        boolean disabledControl = false;
        if(selectedProductGroup != null)
        {
          Integer additionalVesselPriceFlag = selectedProductGroup.getAdditionalVesselPriceFlag();
          if((additionalVesselPriceFlag == null) || (additionalVesselPriceFlag.intValue() == 0)) disabledControl = true;
        }

        for(int i=0; i<controlExts.length; i++)
        {
          String dataInput = controlExts[i].getDataInput();

          if(Constants.BOOLEAN_VAL_1.equals(controlExts[i].getControl().getControlId().getObjectName()))
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

      AddJobContract addJobContract = JobUtil.getAddJobContractByContractIndex(
        addJobOrder,
        addInspectionService.getIndex()
      );
      AddJobContractVessel addJobContractVessel = JobUtil.getAddJobContractVesselByIndex(
        addJobContract,
        addInspectionService.getSelectedVesselIndex()
      );

      if((addJobContractVessel != null) && (addJobContractProd != null))
      {
        JobContractProd jobContractProd = addJobContractProd.getJobContractProd();

        InspectionServiceUtil.deleteUnusedJobContractProductControls(
          controls,
          addJobContractProd
        );

        for(int i=0; i<controls.size(); i++)
        {
          ControlExt controlExt = (ControlExt)controls.get(i);
          InspectionServiceUtil.populateJobContractProductControl(
            controlExt,
            addJobContractProd
          );
        }

        JobContractVessel jobContractVessel = jobContractProd.getJobContractVessel();
        if(!jobContractVessel.getJobContractProds().contains(jobContractProd))
        {
          jobContractVessel.getJobContractProds().add(jobContractProd);
          JobUtil.addAddJobContractProdToAddJobContractVessel(addJobContractVessel, addJobContractProd);
        }

        if(selectedProductGroup != null) jobContractProd.setProductGroupMaster(selectedProductGroup.getProductGroupMaster());

        Boolean notChargeInd = addJobContractProd.getJobContractProd().getNotChargeInd();
        boolean notChargeIndValue = false;
        if(notChargeInd != null) notChargeIndValue = notChargeInd.booleanValue();

        validCount = InspectionServiceUtil.calculateInspectionService(
          controls,
          addJobContractProd,
          addJobContractVessel,
          addJobContract,
          addJobContract.getCfgContract(),
          new Boolean(additionalVessel),
          selectedProductGroup != null ? selectedProductGroup.getUomFlag() : null,
          notChargeIndValue
        );

        if(notChargeIndValue)
        {
          if(validCount == 0) validCount = 1;
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

    Map dataMap = new HashMap();
    dataMap.put("index", addInspectionService.getIndex());
    if( validCount == 0)
    {
      dataMap.put("messageKey", "CANNOT_CALCULATE_INSPECTION_PRICE");
    }

    return new ModelAndView("refresh-page", dataMap);
  }

  protected boolean suppressValidation(HttpServletRequest request, Object command)
  {
    String operationType = request.getParameter("operationType");
    if("selectProductGroup".equals(operationType))
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

    AddInspectionService addInspectionService = new AddInspectionService();
    addInspectionService.setContractCode(contractCode);
    addInspectionService.setIndex(index);
    addInspectionService.setSelectedVesselIndex(selectedVesselIndex);
    addInspectionService.setSelectedProdIndex(selectedProdIndex);

    HttpSession session = request.getSession();
    AddJobOrder addJobOrder = (AddJobOrder)session.getAttribute("com.intertek.web.controller.job.EditJobSelectChargesFormController.FORM.command");

    AddJobContract addJobContract = JobUtil.getAddJobContractByContractIndex(
      addJobOrder,
      addInspectionService.getIndex()
    );
    AddJobContractVessel addJobContractVessel = JobUtil.getAddJobContractVesselByIndex(
      addJobContract,
      addInspectionService.getSelectedVesselIndex()
    );

    AddJobContractProd addJobContractProd = JobUtil.getAddJobContractProdBySelectedProdIndex(
      addJobContractVessel,
      addInspectionService.getSelectedProdIndex()
    );

    if(addJobContractVessel != null)
    {
      if(addJobContractProd == null)
      {
        JobContractProd jobContractProd = new JobContractProd();
        jobContractProd.setJobContractVessel(addJobContractVessel.getJobContractVessel());

        if(Constants.ANALYTICAL_SERVICE_JOBTYPE.equals(addJobOrder.getJobOrder().getJobType()))
        {
          jobContractProd.setNotChargeInd(true);
        }

        addJobContractProd = JobUtil.createAddJobContractProd(jobContractProd);
      }
      addInspectionService.setAddJobContractProd(addJobContractProd);

      String nominationDateStr = DateUtil.formatDate(addJobOrder.getJobOrder().getJobFinishDate(), "yyyyMMdd");
      CalculatorService calculatorService = (CalculatorService)ServiceLocator.getInstance().getBean("calculatorService");

      List productGroups = addInspectionService.getProductGroups();
      if(productGroups == null)
      {
        productGroups = addJobContractVessel.getProductGroups();
        if(productGroups == null)
        {
          productGroups = calculatorService.getProductGroups(
            addJobContract.getCfgContract().getProductGroupSet(),
            contractCode,
            addJobContract.getCfgContract().getPriceBookId(),
            addJobContractVessel.getJobContractVessel().getType(),
            new Integer(addJobContract.getCfgContract().getUom()),
            addJobContract.getJobContract().getZone(),
            DateUtil.formatDate(addJobContract.getJobContract().getJobOrder().getJobFinishDate(), "yyyyMMdd"),
            addJobContract.getJobContract().getLanguage() // language
          );
          addJobContractVessel.setProductGroups(productGroups);
        }

        addInspectionService.setProductGroups(productGroups);
      }

      String myProductGroup = addJobContractProd.getJobContractProd().getGroup();
      ProductGroup selectedProductGroup = CalculatorUtil.getSelectedProductGroup(productGroups, myProductGroup);
      if(selectedProductGroup == null)
      {
        if(productGroups.size() > 0)
        {
          selectedProductGroup = (ProductGroup)productGroups.get(0);
        }
      }
      addInspectionService.setSelectedProductGroup(selectedProductGroup);

      List controls = calculatorService.getControls(
        Constants.L_INSP,
        contractCode,
        addJobContract.getCfgContract().getPriceBookId(),
        nominationDateStr,
        addJobContract.getJobContract().getLanguage() // language
      );

      ControlExt[] controlExts = new ControlExt[controls.size()];
      for(int i=0; i<controls.size(); i++)
      {
        Control control = (Control)controls.get(i);

        controlExts[i] = new ControlExt(control);
      }

      addInspectionService.setControlExts(controlExts);

      List addedJobContractProdControls = new ArrayList(addJobContractProd.getJobContractProd().getControls());

      JobUtil.populateControlsFromJobContractProdControls(
        addInspectionService.getControlExts(),
        addedJobContractProdControls
      );
    }

    return addInspectionService;
  }
}
