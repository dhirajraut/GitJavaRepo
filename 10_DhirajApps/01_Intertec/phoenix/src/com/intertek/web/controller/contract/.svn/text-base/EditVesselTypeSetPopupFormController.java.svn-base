package com.intertek.web.controller.contract;

import java.util.Date;
import java.util.List;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.domain.AddContract;
import com.intertek.domain.CfgContractExt;
import com.intertek.domain.EditVesselTypeSet;
import com.intertek.domain.VesselTypeExt;
import com.intertek.domain.RBExt;
import com.intertek.entity.CfgContract;
import com.intertek.entity.RB;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.VesselTypeService;
import com.intertek.util.Constants;
import com.intertek.util.ContractUtil;
import com.intertek.util.DateUtil;
import com.intertek.util.VesselTypeSetUtil;

public class EditVesselTypeSetPopupFormController extends SimpleFormController
{
  public EditVesselTypeSetPopupFormController() {
    super();
    setCommandClass(EditVesselTypeSet.class);
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
    EditVesselTypeSet editVesselTypeSet = (EditVesselTypeSet)command;
    String operation = request.getParameter("operation");

    if(operation == null)
    {
      return showForm(request, response, errors);
    }

    try
    {
      VesselTypeService vesselTypeService = (VesselTypeService)ServiceLocator.getInstance().getBean("vesselTypeService");

      if("editDescription".equals(operation))
      {
        String vesselTypeIndex = request.getParameter("vesselTypeIndex");
        if((vesselTypeIndex != null) && !"".equals(vesselTypeIndex))
        {
          VesselTypeSetUtil.setEditDescription(editVesselTypeSet, vesselTypeIndex);
        }

        return showForm(request, response, errors);
      }
      else if("editDescriptionAddNew".equals(operation))
      {
        VesselTypeSetUtil.addNewDescription(editVesselTypeSet);

        return showForm(request, response, errors);
      }
      else if("editDescriptionDelete".equals(operation))
      {
        VesselTypeSetUtil.deleteDescription(editVesselTypeSet, request.getParameter("rbExtIndex"));

        return showForm(request, response, errors);
      }
      else if("editDescriptionSubmit".equals(operation))
      {
        VesselTypeSetUtil.saveDescription(editVesselTypeSet);
        editVesselTypeSet.setEditRBExt(null);

        return showForm(request, response, errors);
      }
      else if("editDescriptionCancel".equals(operation))
      {
        editVesselTypeSet.setEditRBExt(null);

        return showForm(request, response, errors);
      }
      else if("changeBaseVesselTypeSet".equals(operation))
      {
        String baseVesselTypeSetName = editVesselTypeSet.getBaseVesselTypeSetName();
        List vesselTypeExts = vesselTypeService.getVesselTypesByVesselTypeSetName(
          baseVesselTypeSetName,
          new Date()
        );
        editVesselTypeSet.setBaseVesselTypeExts(vesselTypeExts);
        CfgContract cfgContract = editVesselTypeSet.getCfgContract();
        if(cfgContract.getCfgContractId().getContractId().equals(cfgContract.getVesselSet()))
        {
          boolean changed = VesselTypeSetUtil.checkVesselTypeChanged(editVesselTypeSet);
          if(changed)
          {
            List customizedVesselTypeExts = VesselTypeSetUtil.getChangedVesselTypeExts(editVesselTypeSet);

            List mergedVesselTypeExts = VesselTypeSetUtil.mergeVesselTypeExts(
              vesselTypeExts,
              customizedVesselTypeExts
            );
            editVesselTypeSet.setVesselTypeExts(mergedVesselTypeExts);
          }
          else
          {
            editVesselTypeSet.setVesselTypeExts(vesselTypeExts);
          }
        }
        else
        {
          editVesselTypeSet.setVesselTypeExts(vesselTypeExts);
        }

        VesselTypeSetUtil.loadVesselTypeRBs(editVesselTypeSet, false);

        return showForm(request, response, errors);
      }
      else if("reset".equals(operation))
      {
        String baseVesselTypeSetName = editVesselTypeSet.getOldVesselTypeSetName();
        boolean existVesselTypeSet = vesselTypeService.existVesselTypeSetByName(
          baseVesselTypeSetName
        );
        if(!existVesselTypeSet) baseVesselTypeSetName = Constants.Pricebook;
        editVesselTypeSet.setBaseVesselTypeSetName(baseVesselTypeSetName);

        List vesselTypeExts = vesselTypeService.getVesselTypesByVesselTypeSetName(
          baseVesselTypeSetName,
          new Date()
        );
        editVesselTypeSet.setBaseVesselTypeExts(vesselTypeExts);

        List customizedVesselTypeExts = editVesselTypeSet.getCustomizedVesselTypeExts();
        if(customizedVesselTypeExts != null)
        {
          List mergedVesselTypeExts = VesselTypeSetUtil.mergeVesselTypeExts(
            vesselTypeExts,
            customizedVesselTypeExts
          );
          editVesselTypeSet.setVesselTypeExts(mergedVesselTypeExts);
        }
        else
        {
          editVesselTypeSet.setVesselTypeExts(vesselTypeExts);
        }

        VesselTypeSetUtil.loadVesselTypeRBs(editVesselTypeSet, false);

        return showForm(request, response, errors);
      }

      boolean sameAsBase = VesselTypeSetUtil.checkVesselTypeSameAsBase(editVesselTypeSet);
      if(sameAsBase)
      {
        editVesselTypeSet.setChangedVesselTypeSetName(editVesselTypeSet.getBaseVesselTypeSetName());
      }
      else
      {
        boolean changed = VesselTypeSetUtil.checkVesselTypeChanged(editVesselTypeSet);
        if(changed)
        {
          VesselTypeSetUtil.setChangedVesselTypes(editVesselTypeSet);
        }
      }
    }
    catch(Throwable t)
    {
      t.printStackTrace();
      errors.reject("generic.error", new Object[] {t.getMessage()}, null);
      return showForm(request, response, errors);
    }

    return new ModelAndView("contract-refresh-page", "operationType", "changeVesselTypeSet");
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

  protected boolean suppressValidation(HttpServletRequest request, Object command)
  {
    String operation = request.getParameter("operation");
    if((operation != null)
      && ("return".equals(operation)
        || "split".equals(operation)
        || "editDescription".equals(operation)
        || "editDescriptionAddNew".equals(operation)
        || "editDescriptionDelete".equals(operation)
        || "editDescriptionCancel".equals(operation)
        || "changeBaseVesselTypeSet".equals(operation)
        || "reset".equals(operation)
      )
    )
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

    EditVesselTypeSet editVesselTypeSet = null;

    HttpSession session = request.getSession();
    AddContract addContract = (AddContract)session.getAttribute("com.intertek.web.controller.contract.CreateContractMainFormController.FORM.command");
    if(addContract == null)
    {
      addContract = (AddContract)session.getAttribute("com.intertek.web.controller.contract.EditContractMainFormController.FORM.command");
    }

    if(addContract != null)
    {
      try
      {
        CfgContractExt cfgContractExt = ContractUtil.getCfgContractExtByIndex(
          addContract,
          index
        );
        if(cfgContractExt != null)
        {
          CfgContract cfgContract = cfgContractExt.getCfgContract();
          VesselTypeSetUtil.createEditVesselTypeSet(addContract, cfgContractExt, false);
          editVesselTypeSet = cfgContractExt.getEditVesselTypeSet();
        }
      }
      catch(Throwable e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      editVesselTypeSet = new EditVesselTypeSet();
    }

    editVesselTypeSet.setIndex(index);
    editVesselTypeSet.setAddContract(addContract);

    return editVesselTypeSet;
  }
}
