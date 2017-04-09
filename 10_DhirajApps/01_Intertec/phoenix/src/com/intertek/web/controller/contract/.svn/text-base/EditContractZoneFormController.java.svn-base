package com.intertek.web.controller.contract;

import java.io.File;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.intertek.domain.EditZone;
import com.intertek.domain.ContractSearch;
import com.intertek.domain.SelectZones;
import com.intertek.domain.ZoneExt;
import com.intertek.entity.CfgContract;
import com.intertek.entity.Contact;
import com.intertek.entity.Contract;
import com.intertek.entity.ContractAttach;
import com.intertek.entity.ContractCust;
import com.intertek.entity.ContractCustContact;
import com.intertek.entity.ContractCustContactId;
import com.intertek.entity.ContractCustId;
import com.intertek.entity.Customer;
import com.intertek.entity.User;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.ContractService;
import com.intertek.service.ZoneService;
import com.intertek.statemachine.ExecutionContext;
import com.intertek.statemachine.StateMachine;
import com.intertek.statemachine.StateMachineManager;
import com.intertek.util.Constants;
import com.intertek.util.ContractUtil;
import com.intertek.util.DateUtil;
import com.intertek.util.ProductGroupSetUtil;
import com.intertek.util.SecurityUtil;
import com.intertek.util.VesselTypeSetUtil;
import com.intertek.util.ZoneUtil;
import com.intertek.web.controller.BaseSimpleFormController;
import com.intertek.web.propertyeditors.CustomDiscountEditor;

public class EditContractZoneFormController extends BaseEditContractFormController
{
  public EditContractZoneFormController() {
    super();
    setSessionForm(true);
    setCommandClass(EditZone.class);
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
    EditZone editZone = (EditZone)command;
    Contract contract = editZone.getContract();
    ContractService contractService = (ContractService)ServiceLocator.getInstance().getBean("contractService");
    ZoneService zoneService = (ZoneService)ServiceLocator.getInstance().getBean("zoneService");

    ZoneUtil.packZoneData(editZone);

    String operationType = request.getParameter("operationType");
    if("addZone".equals(operationType))
    {
      ZoneUtil.addZone(editZone);
    }
    else if("swichCustomPriceBook".equals(operationType))
    {
      ZoneUtil.clearCustomOrPriceBookZoneId(editZone, request.getParameter("zoneIndex"));
    }
    else if("deleteZone".equals(operationType))
    {
      ZoneUtil.deleteZone(editZone, request.getParameter("zoneIndex"));
    }
    else if("addLocationDiscount".equals(operationType))
    {
      ZoneUtil.addLocationDiscount(editZone, request.getParameter("zoneIndex"));
    }
    else if("deleteLocationDiscount".equals(operationType))
    {
      ZoneUtil.deleteLocationDiscount(editZone, request.getParameter("zoneIndex"), request.getParameter("zoneSubIndex"));
    }
    else if("addBranchLocation".equals(operationType))
    {
      ZoneUtil.addBranchLocation(editZone, request.getParameter("zoneIndex"));
    }
    else if("deleteBranchLocation".equals(operationType))
    {
      ZoneUtil.deleteBranchLocation(editZone, request.getParameter("zoneIndex"), request.getParameter("zoneSubIndex"));
    }
    else if("addPortLocation".equals(operationType))
    {
      ZoneUtil.addPortLocation(editZone, request.getParameter("zoneIndex"));
    }
    else if("deletePortLocation".equals(operationType))
    {
      ZoneUtil.deletePortLocation(editZone, request.getParameter("zoneIndex"), request.getParameter("zoneSubIndex"));
    }
    else if("applyDatesForAllBranches".equals(operationType))
    {
      ZoneUtil.applyDatesForAllBranches(editZone, request.getParameter("zoneIndex"));
    }
    else if("deleteSelectedBranches".equals(operationType))
    {
      ZoneUtil.deleteSelectedForBranchLocations(editZone, request.getParameter("zoneIndex"));
    }
    else if("selectZoneDone".equals(operationType))
    {
      SelectZones selectZones = editZone.getSelectZones();
      if(selectZones != null)
      {
        ZoneExt zoneExt = selectZones.getZoneExt();
        if(zoneExt != null)
        {
          zoneExt.setPriceBookZoneId(selectZones.getSelectedZoneId());
        }
      }
    }
    else if("saveContract".equals(operationType)
      || "switchTab".equals(operationType)
      || "prevContract".equals(operationType)
      || "nextContract".equals(operationType)
      || "searchContract".equals(operationType)
    )
    {
      if("saveContract".equals(operationType) && (!editZone.getViewOnly()))
      {
        try
        {
          zoneService.saveZonesFromEditZone(editZone);
        }
        catch(Exception e)
        {
          e.printStackTrace();

          errors.reject("edit.contract.error", new Object[] {e.getMessage()}, null);
          return showForm(request, response, errors);
        }
      }

      ModelAndView modelAndView = getModelAndView(
        operationType,
        request.getParameter("goToContractTab"),
        "zone",
        contract,
        editZone.getContractSearch(),
        errors
      );
      if(modelAndView == null) return showForm(request, response, errors);

      return modelAndView;
    }

    return showForm(request, response, errors);
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
    NumberFormat nf = NumberFormat.getInstance();
    binder.registerCustomEditor(java.lang.Double.class, null, new CustomDiscountEditor(Double.class, nf, new Double(-1)));
  }

  protected boolean suppressValidation(HttpServletRequest request, Object command)
  {
    String operationType = request.getParameter("operationType");
    if(!"saveContract".equals(operationType))
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
    String contractCode = request.getParameter("contractCode");
    EditZone editZone = new EditZone();
    Contract contract = null;

    ContractService contractService = (ContractService)ServiceLocator.getInstance().getBean("contractService");
    ZoneService zoneService = (ZoneService)ServiceLocator.getInstance().getBean("zoneService");

    try
    {
      contract = contractService.loadContractByContractCode(contractCode);
      if(contract != null)
      {
        List priceBookZoneIdList = zoneService.getPriceBookZoneIdsForPriceBookId(contract.getWorkingPB());
        priceBookZoneIdList.add(0, "");
        editZone.setPriceBookZoneIdList(priceBookZoneIdList);

        List zoneExtList = ZoneUtil.getZoneExtsByContractCode(contractCode);
        editZone.setZoneExtList(zoneExtList);

        ZoneUtil.loadBranchDescriptions(zoneExtList);

        ZoneUtil.setCustomFlagForZoneExtList(zoneExtList, priceBookZoneIdList);
      }
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

    if(contract == null) contract = new Contract();
    if(contract.getInvoiceType()==null)
    {
      contract.setInvoiceType("Reg");
    }
    editZone.setContract(contract);

    boolean editable = SecurityUtil.isAnyGranted(new String[] {"CreateContract"});
    editZone.setViewOnly(editable == false);

    editZone.setContractSearch(getContractSearch(request));

    return editZone;
  }
}
