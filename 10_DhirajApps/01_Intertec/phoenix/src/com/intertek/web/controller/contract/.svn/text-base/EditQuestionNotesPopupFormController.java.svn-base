package com.intertek.web.controller.contract;

import java.util.List;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.calculator.ContractExpressionExt;
import com.intertek.domain.EditServiceRate;
import com.intertek.domain.EditRBExt;
import com.intertek.domain.RBExt;
import com.intertek.domain.ServiceRates;
import com.intertek.domain.ServiceLevel;
import com.intertek.entity.RB;
import com.intertek.entity.RBId;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.RbService;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;
import com.intertek.util.EntityUtil;
import com.intertek.web.propertyeditors.CustomDiscountEditor;

public class EditQuestionNotesPopupFormController extends SimpleFormController
{
  public EditQuestionNotesPopupFormController() {
    super();
    setCommandClass(EditRBExt.class);
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
    EditRBExt editRbExt = (EditRBExt)command;

    RbService rbService = (RbService)ServiceLocator.getInstance().getBean("rbService");

    RBExt oldRbExt = editRbExt.getOldRbExt();
    RBExt rbExt = editRbExt.getRbExt();
    if((oldRbExt != null) && (rbExt != null))
    {
      try
      {
        rbService.saveRbExt(rbExt);

        RB oldRb = oldRbExt.getRb();
        if(oldRb != null)
        {
          RB rb = rbExt.getRb();

          EntityUtil.copyRb(oldRb, rb);
        }

        RB oldNotesRb = oldRbExt.getNotesRb();
        RB notesRb = rbExt.getNotesRb();
        String notesText = notesRb.getRbValue();
        if((notesText != null) && !"".equals(notesText.trim()))
        {
          if(oldNotesRb == null)
          {
            oldNotesRb = new RB();
            oldRbExt.setNotesRb(oldNotesRb);
          }
          EntityUtil.copyRb(oldNotesRb, notesRb);
        }
      }
      catch(Exception e)
      {
        e.printStackTrace();
      }
    }

    return new ModelAndView("contract-refresh-page", "operationType", "editRates_edit_question_notesnt");
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
    HttpSession session = request.getSession();
    EditServiceRate editServiceRate = (EditServiceRate)session.getAttribute("com.intertek.web.controller.contract.EditContractServiceFormController.FORM.command");

    EditRBExt editRBExt = new EditRBExt();
    RBExt rbExt = new RBExt();
    editRBExt.setRbExt(rbExt);

    if(editServiceRate != null)
    {
      List questionRbExtList = editServiceRate.getServiceRates().getServiceLevel().getQuestionRbExtList();

      String rbIndexStr = request.getParameter("rbIndex");
      int rbIndex = -1;
      try
      {
        rbIndex = new Integer(rbIndexStr).intValue();
      }
      catch(Exception e)
      {
      }

      if(questionRbExtList != null)
      {
        if((rbIndex >= 0) && (rbIndex < questionRbExtList.size()))
        {
          RBExt tmpRbExt = (RBExt)questionRbExtList.get(rbIndex);
          editRBExt.setOldRbExt(tmpRbExt);

          String contractCode = editServiceRate.getContract().getContractCode();

          RB rb = tmpRbExt.getRb();
          RB newRb = new RB();
          EntityUtil.copyRb(newRb, rb);

          newRb.getRbId().setContractId(contractCode);
          rbExt.setRb(newRb);

          RB newNotesRb = new RB();
          RB notesRb = tmpRbExt.getNotesRb();
          if(notesRb != null)
          {
            EntityUtil.copyRb(newNotesRb, notesRb);
          }
          else
          {
            EntityUtil.copyRb(newNotesRb, rb);

            newNotesRb.getRbId().setRbKey(Constants.Notes + "." + newNotesRb.getRbId().getRbKey());
            newNotesRb.setRbType(Constants.NTS);
            newNotesRb.setRbValue(null);
          }

          newNotesRb.getRbId().setContractId(contractCode);
          rbExt.setNotesRb(newNotesRb);
        }
      }
    }

    return editRBExt;
  }
}
