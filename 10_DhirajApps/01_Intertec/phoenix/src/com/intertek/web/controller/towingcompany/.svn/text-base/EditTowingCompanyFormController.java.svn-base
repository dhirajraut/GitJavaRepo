package com.intertek.web.controller.towingcompany;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.intertek.entity.TowingCompany;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.TowingCompanyService;
import com.intertek.web.controller.BaseSimpleFormController;

//public class EditTowingCompanyFormController extends SimpleFormController
public class EditTowingCompanyFormController extends BaseSimpleFormController
{
  public EditTowingCompanyFormController() {
    super();
    setCommandClass(TowingCompany.class);
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
    TowingCompany towingCompany = (TowingCompany)command;

    TowingCompanyService towingCompanyService = (TowingCompanyService)ServiceLocator.getInstance().getBean("towingCompanyService");
    try
    {
      towingCompanyService.saveTowingCompany(towingCompany);
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

    ModelAndView modelAndView = new ModelAndView("common-message-r");
    modelAndView.addObject("backUrl", "edit_towing_company.htm?id=" + towingCompany.getId());
    modelAndView.addObject("backUrlDescription", "You can continue to edit this towingCompany.");
    modelAndView.addObject("description", "Your towingCompany has been successfully updated.");

    return modelAndView;
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
    String towingCompanyId = request.getParameter("id");


		TowingCompany towingCompany = null;

		TowingCompanyService towingCompanyService = (TowingCompanyService)ServiceLocator.getInstance().getBean("towingCompanyService");
    try
    {
      towingCompany = towingCompanyService.getTowingCompanyById(new Long(towingCompanyId));
    }
    catch(ServiceException e)
    {
    	throw new ServiceException(e.getMessage(), e.getParams(), e);
    }
    catch(Throwable t)
    {
        t.printStackTrace();
        throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
    }
    if(towingCompany == null)
    {
      towingCompany = new TowingCompany();
    }

    return towingCompany;
  }
}
