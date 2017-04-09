package com.intertek.web.controller.contract;

import java.util.ArrayList;
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
import com.intertek.domain.EditProductGroupSet;
import com.intertek.domain.ProductGroupExt;
import com.intertek.domain.RBExt;
import com.intertek.entity.CfgContract;
import com.intertek.entity.RB;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.ProductGroupService;
import com.intertek.util.Constants;
import com.intertek.util.ContractUtil;
import com.intertek.util.DateUtil;
import com.intertek.util.ProductGroupSetUtil;

public class EditProductGroupSetPopupFormController extends SimpleFormController
{
  public EditProductGroupSetPopupFormController() {
    super();
    setCommandClass(EditProductGroupSet.class);
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
    EditProductGroupSet editProductGroupSet = (EditProductGroupSet)command;
    String operation = request.getParameter("operation");

    if(operation == null)
    {
      return showForm(request, response, errors);
    }

    try
    {
      ProductGroupService productGroupService = (ProductGroupService)ServiceLocator.getInstance().getBean("productGroupService");

      if("split".equals(operation))
      {
        String productGroupIndex = request.getParameter("productGroupIndex");
        if((productGroupIndex != null) && !"".equals(productGroupIndex))
        {
          ProductGroupSetUtil.addProductGroupExt(editProductGroupSet, productGroupIndex);
        }

        return showForm(request, response, errors);
      }
      else if("editDescription".equals(operation))
      {
        String productGroupIndex = request.getParameter("productGroupIndex");
        if((productGroupIndex != null) && !"".equals(productGroupIndex))
        {
          ProductGroupSetUtil.setEditDescription(editProductGroupSet, productGroupIndex);
        }

        return showForm(request, response, errors);
      }
      else if("editDescriptionAddNew".equals(operation))
      {
        ProductGroupSetUtil.addNewDescription(editProductGroupSet);

        return showForm(request, response, errors);
      }
      else if("editDescriptionDelete".equals(operation))
      {
        ProductGroupSetUtil.deleteDescription(editProductGroupSet, request.getParameter("rbExtIndex"));

        return showForm(request, response, errors);
      }
      else if("editDescriptionSubmit".equals(operation))
      {
        ProductGroupSetUtil.saveDescription(editProductGroupSet);
        editProductGroupSet.setEditRBExt(null);

        return showForm(request, response, errors);
      }
      else if("editDescriptionCancel".equals(operation))
      {
        editProductGroupSet.setEditRBExt(null);

        return showForm(request, response, errors);
      }
      else if("changeBaseProductGroupSet".equals(operation))
      {
        String baseProductGroupSetName = editProductGroupSet.getBaseProductGroupSetName();
        List productGroupExts = productGroupService.getProductGroupsByProductGroupSetName(
          baseProductGroupSetName,
          new Date()
        );
        List baseProductGroupExts = new ArrayList();
        baseProductGroupExts.addAll(productGroupExts);
        editProductGroupSet.setBaseProductGroupExts(baseProductGroupExts);

        CfgContract cfgContract = editProductGroupSet.getCfgContract();
        if(cfgContract.getCfgContractId().getContractId().equals(cfgContract.getProductGroupSet()))
        {
          boolean changed = ProductGroupSetUtil.checkProductGroupChanged(editProductGroupSet);
          if(changed)
          {
            List customizedProductGroupExts = ProductGroupSetUtil.getChangedProductGroupExts(editProductGroupSet);

            List mergedProductGroupExts = ProductGroupSetUtil.mergeProductGroupExts(
              productGroupExts,
              customizedProductGroupExts
            );
            editProductGroupSet.setProductGroupExts(mergedProductGroupExts);
          }
          else
          {
            editProductGroupSet.setProductGroupExts(productGroupExts);
          }
        }
        else
        {
          editProductGroupSet.setProductGroupExts(productGroupExts);
        }

        ProductGroupSetUtil.loadProductGroupRBs(editProductGroupSet, false);

        return showForm(request, response, errors);
      }
      else if("reset".equals(operation))
      {
        String baseProductGroupSetName = editProductGroupSet.getOldProductGroupSetName();
        boolean existProductGroupSet = productGroupService.existProductGroupSetByName(
          baseProductGroupSetName
        );
        if(!existProductGroupSet) baseProductGroupSetName = Constants.Pricebook;
        editProductGroupSet.setBaseProductGroupSetName(baseProductGroupSetName);

        List productGroupExts = productGroupService.getProductGroupsByProductGroupSetName(
          baseProductGroupSetName,
          new Date()
        );
        List baseProductGroupExts = new ArrayList();
        baseProductGroupExts.addAll(productGroupExts);
        editProductGroupSet.setBaseProductGroupExts(baseProductGroupExts);

        List customizedProductGroupExts = editProductGroupSet.getCustomizedProductGroupExts();
        if(customizedProductGroupExts != null)
        {
          List mergedProductGroupExts = ProductGroupSetUtil.mergeProductGroupExts(
            productGroupExts,
            customizedProductGroupExts
          );
          editProductGroupSet.setProductGroupExts(mergedProductGroupExts);
        }
        else
        {
          editProductGroupSet.setProductGroupExts(productGroupExts);
        }

        ProductGroupSetUtil.loadProductGroupRBs(editProductGroupSet, false);

        return showForm(request, response, errors);
      }

      boolean sameAsBase = ProductGroupSetUtil.checkProductGroupSameAsBase(editProductGroupSet);
      if(sameAsBase)
      {
        editProductGroupSet.setChangedProductGroupSetName(editProductGroupSet.getBaseProductGroupSetName());
      }
      else
      {
        boolean changed = ProductGroupSetUtil.checkProductGroupChanged(editProductGroupSet);
        if(changed)
        {
          ProductGroupSetUtil.setChangedProductGroups(editProductGroupSet);
        }
      }
    }
    catch(Throwable t)
    {
      t.printStackTrace();
      errors.reject("generic.error", new Object[] {t.getMessage()}, null);
      return showForm(request, response, errors);
    }

    return new ModelAndView("contract-refresh-page", "operationType", "changeProductGroupSet");
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
        || "editDescriptionCancel".equals(operation)
        || "editDescriptionAddNew".equals(operation)
        || "editDescriptionDelete".equals(operation)
        || "changeBaseProductGroupSet".equals(operation)
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

    EditProductGroupSet editProductGroupSet = null;

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
          ProductGroupSetUtil.createEditProductGroupSet(addContract, cfgContractExt, false);
          editProductGroupSet = cfgContractExt.getEditProductGroupSet();
        }
      }
      catch(Throwable e)
      {
        e.printStackTrace();
      }
    }
    else
    {
      editProductGroupSet = new EditProductGroupSet();
    }

    editProductGroupSet.setIndex(index);
    editProductGroupSet.setAddContract(addContract);

    return editProductGroupSet;
  }
}
