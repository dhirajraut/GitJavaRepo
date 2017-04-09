package com.intertek.web.controller.contract;

import java.util.List;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.domain.VesselTypeExt;
import com.intertek.domain.EditInspectionRate;
import com.intertek.domain.InspectionVersionExt;
import com.intertek.domain.SelectProducts;
import com.intertek.entity.CfgContract;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.ContractService;
import com.intertek.service.InspectionRateService;
import com.intertek.util.Constants;
import com.intertek.util.ProductGroupSetUtil;
import com.intertek.util.InspectionRateUtil;
import com.intertek.web.propertyeditors.CustomDiscountEditor;

public class EditContractSelectProductsPopupFormController extends SimpleFormController
{
  public EditContractSelectProductsPopupFormController() {
    super();
    setCommandClass(SelectProducts.class);
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
    SelectProducts selectProducts = (SelectProducts)command;
    InspectionRateService inspectionRateService = (InspectionRateService)ServiceLocator.getInstance().getBean("inspectionRateService");

    String operation = request.getParameter("operation");
    if(operation == null)
    {
      return showForm(request, response, errors);
    }
    else if("return".equals(operation))
    {
      copyProductGroup(selectProducts);
      return new ModelAndView("contract-refresh-page", "operationType", "selectProductsDone");
    }

    try
    {
      InspectionRateUtil.copyPbInspectionRatesForSelectedProducts(selectProducts);
    }
    catch(Throwable t)
    {
      t.printStackTrace();
      errors.reject("generic.error", new Object[] {t.getMessage()}, null);
      return showForm(request, response, errors);
    }

    copyProductGroup(selectProducts);
    return new ModelAndView("contract-refresh-page", "operationType", "selectProductsDone");
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
    SelectProducts selectProducts = new SelectProducts();
    String vesselTypeName = request.getParameter("vesselType");

    ContractService contractService = (ContractService)ServiceLocator.getInstance().getBean("contractService");
    InspectionRateService inspectionRateService = (InspectionRateService)ServiceLocator.getInstance().getBean("inspectionRateService");

    HttpSession session = request.getSession();
    EditInspectionRate editInspectionRate = (EditInspectionRate)session.getAttribute("com.intertek.web.controller.contract.EditContractInspectionFormController.FORM.command");

    if(editInspectionRate != null)
    {
      selectProducts.setEditInspectionRate(editInspectionRate);
      InspectionVersionExt activeInspectionVersionExt = InspectionRateUtil.getActiveInspectionVersionExt(editInspectionRate);

      VesselTypeExt vesselTypeExt = InspectionRateUtil.getVesselTypeExt(activeInspectionVersionExt, vesselTypeName);
      selectProducts.setVesselTypeExt(vesselTypeExt);

      int vesselTypeExtIndex = InspectionRateUtil.getVesselTypeExtIndex(activeInspectionVersionExt, vesselTypeName);
      activeInspectionVersionExt.setActiveVesselTypeExtIndex(vesselTypeExtIndex);

      CfgContract cfgContract = activeInspectionVersionExt.getCfgContract();
      if(cfgContract != null)
      {
        try
        {
          List productGroupExtList = ProductGroupSetUtil.getProductGroupExtList(cfgContract);
          selectProducts.setProductGroupExtList(productGroupExtList);

          Map uomsMapByGroupId = inspectionRateService.getUomsMapByGroupId(
            editInspectionRate.getContract().getWorkingPB(),
            "Inspection",
            vesselTypeExt.getVesselType().getVesselTypeId().getVesselType()
          );

          InspectionRateUtil.configProductGroupExtList(selectProducts, uomsMapByGroupId);
        }
        catch(Exception e)
        {
          e.printStackTrace();
        }
      }
    }

    return selectProducts;
  }

  private void copyProductGroup(SelectProducts selectProducts)
  {
    EditInspectionRate editInspectionRate = selectProducts.getEditInspectionRate();

    InspectionVersionExt activeInspectionVersionExt = InspectionRateUtil.getActiveInspectionVersionExt(editInspectionRate);
    InspectionRateUtil.copyProductGroupByChangeType(activeInspectionVersionExt, "fromTab1");
  }
}
