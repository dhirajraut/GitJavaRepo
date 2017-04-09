package com.intertek.web.controller.shippingagent;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.intertek.entity.ShippingAgent;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.ShippingAgentService;
import com.intertek.web.controller.BaseSimpleFormController;

//public class EditShippingAgentFormController extends SimpleFormController
public class EditShippingAgentFormController extends BaseSimpleFormController
{
  public EditShippingAgentFormController() {
    super();
	setSessionForm(true);
    setCommandClass(ShippingAgent.class);
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
    ShippingAgent shippingAgent = (ShippingAgent)command;

    ShippingAgentService shippingAgentService = (ShippingAgentService)ServiceLocator.getInstance().getBean("shippingAgentService");
    try{
      shippingAgentService.saveShippingAgent(shippingAgent);
    } catch(ServiceException e)
    {
      e.printStackTrace();
      errors.reject(e.getMessage(), e.getParams(), null);
      return showForm(request, response, errors);
    } catch(Throwable t)
    {
      t.printStackTrace();
      errors.reject("generic.error", new Object[] {t.getMessage()}, null);
      return showForm(request, response, errors);
    }

    ModelAndView modelAndView = new ModelAndView("common-message-r");
    modelAndView.addObject("backUrl", "edit_shipping_agent.htm?id=" + shippingAgent.getId());
    modelAndView.addObject("backUrlDescription", "You can continue to edit this shippingAgent.");
    modelAndView.addObject("description", "Your shippingAgent has been successfully updated.");

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
	  String shippingAgentId = request.getParameter("id");
		ShippingAgent shippingAgent = null;

		ShippingAgentService shippingAgentService = (ShippingAgentService)ServiceLocator.getInstance().getBean("shippingAgentService");
		try {
		   shippingAgent = shippingAgentService.getShippingAgentById(new Long(shippingAgentId));
		}catch(ServiceException e)
	    {
	    	throw new ServiceException(e.getMessage(), e.getParams(), e);
	    }
	    catch(Throwable t)
	    {
	        t.printStackTrace();
	        throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
		}

    return shippingAgent;
  }
}
