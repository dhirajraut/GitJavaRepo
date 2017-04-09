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
import com.intertek.domain.UpdateUomNotes;
import com.intertek.domain.UomNote;
import com.intertek.entity.CfgContract;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.ContractService;
import com.intertek.service.InspectionRateService;
import com.intertek.service.RbService;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;
import com.intertek.util.RbUtil;
import com.intertek.util.InspectionRateUtil;
import com.intertek.web.propertyeditors.CustomDiscountEditor;

public class EditContractUpdateUomNotesPopupFormController extends SimpleFormController
{
  public EditContractUpdateUomNotesPopupFormController() {
    super();
    setCommandClass(UpdateUomNotes.class);
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
    UpdateUomNotes updateUomNotes = (UpdateUomNotes)command;
    InspectionRateService inspectionRateService = (InspectionRateService)ServiceLocator.getInstance().getBean("inspectionRateService");

    String operation = request.getParameter("operation");
    if(operation == null)
    {
      return showForm(request, response, errors);
    }
    else if("addUomNote".equals(operation))
    {
      UomNote uomNote = InspectionRateUtil.createNewUomNote(
        updateUomNotes.getPbRbList(),
        updateUomNotes.getUomTableList()
      );
      if(uomNote != null)
      {
        updateUomNotes.getUomNoteList().add(uomNote);
      }

      return showForm(request, response, errors);
    }
    else if("deleteUomNote".equals(operation))
    {
      String uomNoteIndexStr = request.getParameter("uomNoteIndex");

      InspectionRateUtil.deleteUomNoteByIndex(updateUomNotes, uomNoteIndexStr);

      return showForm(request, response, errors);
    }
    else if("changeUom".equals(operation))
    {
      String uomNoteIndexStr = request.getParameter("uomNoteIndex");

      InspectionRateUtil.changeUomNoteByIndex(updateUomNotes, uomNoteIndexStr);

      return showForm(request, response, errors);
    }
    else if("apply".equals(operation))
    {
      InspectionRateUtil.saveUpdateUomNotes(updateUomNotes);
      updateUomNotes.getDeletedUomNoteList().clear();

      initUpdateUomNotes(updateUomNotes);

      return showForm(request, response, errors);
    }
    else if("return".equals(operation))
    {
      return new ModelAndView("contract-refresh-page", "operationType", "updateUomNotesDone");
    }

    try
    {
      InspectionRateUtil.saveUpdateUomNotes(updateUomNotes);
    }
    catch(Throwable t)
    {
      t.printStackTrace();
      errors.reject("generic.error", new Object[] {t.getMessage()}, null);
      return showForm(request, response, errors);
    }

    return new ModelAndView("contract-refresh-page", "operationType", "updateUomNotesDone");
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
    SimpleDateFormat dateFormat = new SimpleDateFormat(DateUtil.getUserDateFormat());
    dateFormat.setLenient(false);
    binder.registerCustomEditor(java.util.Date.class, null, new CustomDateEditor(dateFormat, true));
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
   */
  protected Object formBackingObject(
    HttpServletRequest request
  ) throws Exception
  {
    UpdateUomNotes updateUomNotes = new UpdateUomNotes();

    HttpSession session = request.getSession();
    EditInspectionRate editInspectionRate = (EditInspectionRate)session.getAttribute("com.intertek.web.controller.contract.EditContractInspectionFormController.FORM.command");

    if(editInspectionRate != null)
    {
      updateUomNotes.setEditInspectionRate(editInspectionRate);

      initUpdateUomNotes(updateUomNotes);
    }

    return updateUomNotes;
  }

  private void initUpdateUomNotes(UpdateUomNotes updateUomNotes)
  {
    ContractService contractService = (ContractService)ServiceLocator.getInstance().getBean("contractService");
    InspectionRateService inspectionRateService = (InspectionRateService)ServiceLocator.getInstance().getBean("inspectionRateService");
    RbService rbService = (RbService)ServiceLocator.getInstance().getBean("rbService");

    EditInspectionRate editInspectionRate = updateUomNotes.getEditInspectionRate();

    try
    {
      List uomTableList = inspectionRateService.getUomTableList();
      updateUomNotes.setUomTableList(uomTableList);

      List rbKeyList = InspectionRateUtil.getRbKeyListFromUomTableList(uomTableList);

      List rbList = rbService.getRbList(
        editInspectionRate.getContract().getContractCode(),
        rbKeyList
      );

      List uomNoteList = InspectionRateUtil.createUomNoteList(
        RbUtil.createRbExtList(rbList),
        uomTableList
      );
      updateUomNotes.setUomNoteList(uomNoteList);

      rbList = rbService.getRbList(
        "NONE",
        rbKeyList
      );
      updateUomNotes.setPbRbList(rbList);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }
  }
}
