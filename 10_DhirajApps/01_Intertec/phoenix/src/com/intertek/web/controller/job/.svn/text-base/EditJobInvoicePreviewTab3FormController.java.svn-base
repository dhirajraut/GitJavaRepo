package com.intertek.web.controller.job;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.domain.AddJobContract;
import com.intertek.domain.AddJobOrder;
import com.intertek.util.JobUtil;

public class EditJobInvoicePreviewTab3FormController extends SimpleFormController
{
  public EditJobInvoicePreviewTab3FormController() {
    super();
    setCommandClass(AddJobContract.class);
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
    AddJobContract addJobContract = (AddJobContract)command;

    String refreshing = request.getParameter("refreshing");
    String tabSrc = request.getParameter("tabSrc");
    if(tabSrc != null) addJobContract.setInvoicePreviewTabSrc(tabSrc);

    if((refreshing != null) && "true".equals(refreshing))
    {
      return new ModelAndView("invoice-preview-refresh-page");
    }

    return showForm(request, response, errors);
  }

  protected boolean suppressValidation(HttpServletRequest request)
  {
    String refreshing = request.getParameter("refreshing");
    if((refreshing != null) && "true".equals(refreshing))
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
    SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
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
    String contractCode = request.getParameter("contractCode");
    String contractIndex = request.getParameter("contractIndex");
    HttpSession session = request.getSession();
    AddJobOrder addJobOrder = (AddJobOrder)session.getAttribute("com.intertek.web.controller.job.EditJobInvoicePreviewFormController.FORM.command");

    AddJobContract addJobContract = JobUtil.getAddJobContractByContractIndex(
      addJobOrder,
      contractIndex
    );

    if(addJobContract == null)
    {
      addJobContract = new AddJobContract();
    }

    return addJobContract;
  }
}
