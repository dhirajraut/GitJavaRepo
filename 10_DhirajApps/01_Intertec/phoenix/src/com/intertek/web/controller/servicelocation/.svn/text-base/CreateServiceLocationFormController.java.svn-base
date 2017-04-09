package com.intertek.web.controller.servicelocation;

import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;

import com.intertek.domain.AddServiceLocation;
import com.intertek.entity.Branch;
import com.intertek.entity.Country;
import com.intertek.entity.ServiceLocation;
import com.intertek.entity.State;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.CountryService;
import com.intertek.service.ServiceLocationService;
import com.intertek.service.UserService;
import com.intertek.util.Constants;
import com.intertek.util.SecurityUtil;
import com.intertek.web.controller.BaseSimpleFormController;

//public class CreateServiceLocationFormController extends SimpleFormController
public class CreateServiceLocationFormController extends BaseSimpleFormController
{
  public CreateServiceLocationFormController() {
    super();
    setCommandClass(AddServiceLocation.class);
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
    Country country=null;
    AddServiceLocation addServiceLocation = (AddServiceLocation)command;
	String existingBranchFlag = request.getParameter("existingBranchFlag");
	//String branchTypeFlag = request.getParameter("branchTypeFlag");
	String labelFlagValue = request.getParameter("labelFlag");
	
	
	ServiceLocation serviceLocation = addServiceLocation.getServiceLocation();
	String taxCode=addServiceLocation.getTaxCode();
	ServiceLocationService serviceLocationService = (ServiceLocationService)ServiceLocator.getInstance().getBean("serviceLocationService");
    UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
	CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");
	
	if(labelFlagValue!=null && !labelFlagValue.equals(""))
	{
		 country = countryService.getCountryByCode(labelFlagValue);
		if(country != null)
		{
			if(country.getCounty()!= null )
			{
				
				addServiceLocation.setCountyLabel(country.getCounty());
			
			}else
			{
				addServiceLocation.setCountyLabel(null);
			}
			if(country.getState()!= null )
			{
				
				addServiceLocation.setStateLabel(country.getState());
				
			}else
			{
				addServiceLocation.setStateLabel(null);
			}
			if(country.getPostal()!= null )
			{
				
				addServiceLocation.setPostalLabel(country.getPostal());
				
			}else
			{
				 addServiceLocation.setPostalLabel(null);
			}
			if(country.getAddr1Lbl()!= null )
			{
				
				addServiceLocation.setAddress1Label(country.getAddr1Lbl());
				
			}else
			{
				 addServiceLocation.setAddress1Label(null);
			}
			if(country.getAddr2Lbl()!= null )
			{
				
				addServiceLocation.setAddress2Label(country.getAddr2Lbl());
				
			}else
			{
				 addServiceLocation.setAddress2Label(null);
			}
			if(country.getAddr3Lbl()!= null )
			{
				
				addServiceLocation.setAddress3Label(country.getAddr3Lbl());
				
			}else
			{
				 addServiceLocation.setAddress3Label(null);
			}
			if(country.getAddr4Lbl()!= null )
			{
				
				addServiceLocation.setAddress4Label(country.getAddr4Lbl());
				
			}else
			{
				 addServiceLocation.setAddress4Label(null);
			}
			
		
		}
	
		addServiceLocation.setLabelFlag("");
		return showForm(request, response, errors);
	}

    if(serviceLocation.getEmail()!= null && !(serviceLocation.getEmail().trim().equals("")))
	{
		if(serviceLocation.getEmail().indexOf('@')==-1)
		{
			errors.reject("service.location.email.error",
					new Object[] { serviceLocation.getEmail()}, null);
			return showForm(request, response, errors);
		}
		
	}
	
    if(existingBranchFlag != null && existingBranchFlag.trim().equals("Y") && serviceLocation.getBranchName() != null)
	
	{
		
		Branch branch = userService.getBranchByName(serviceLocation.getBranchName());
			
		serviceLocation.setName(branch.getDescription());
		serviceLocation.setAddress1(branch.getAddress1());
		serviceLocation.setAddress2(branch.getAddress2());
		serviceLocation.setAddress3(branch.getAddress3());
		serviceLocation.setAddress4(branch.getAddress4());
		String ctryCode = branch.getCountryCode();
		String stateCode = branch.getStateCode();
		country = countryService.getCountryByCode(ctryCode);
		if(country != null)
		serviceLocation.setCountryCode(country.getCountryCode());
		
		if(country != null)
		{
			if(country.getCounty()!= null )
			{
				
				addServiceLocation.setCountyLabel(country.getCounty());
			
			}else
			{
				addServiceLocation.setCountyLabel(null);
			}
			if(country.getState()!= null )
			{
				
				addServiceLocation.setStateLabel(country.getState());
				
			}else
			{
				addServiceLocation.setStateLabel(null);
			}
			if(country.getPostal()!= null )
			{
				
				addServiceLocation.setPostalLabel(country.getPostal());
				
			}else
			{
				 addServiceLocation.setPostalLabel(null);
			}
			if(country.getAddr1Lbl()!= null )
			{
				
				addServiceLocation.setAddress1Label(country.getAddr1Lbl());
				
			}else
			{
				 addServiceLocation.setAddress1Label(null);
			}
			if(country.getAddr2Lbl()!= null )
			{
				
				addServiceLocation.setAddress2Label(country.getAddr2Lbl());
				
			}else
			{
				 addServiceLocation.setAddress2Label(null);
			}
			if(country.getAddr3Lbl()!= null )
			{
				
				addServiceLocation.setAddress3Label(country.getAddr3Lbl());
				
			}else
			{
				 addServiceLocation.setAddress3Label(null);
			}
			if(country.getAddr4Lbl()!= null )
			{
				
				addServiceLocation.setAddress4Label(country.getAddr4Lbl());
				
			}else
			{
				 addServiceLocation.setAddress4Label(null);
			}
		}
		//Set s =country.getStates();

		Set s=country.getStates();
		if(s!=null && !s.isEmpty() && s.iterator()!=null && s.iterator().hasNext()){
			Iterator itr = s.iterator();
	        State state =(State)itr.next();
	        if(state!=null){
	        	serviceLocation.setStateCode(state.getStateId().getStateCode());
	        }
		}
		String preFix = branch.getPhonePrefix();
		String phoneNumber = branch.getPhoneNumber();
		String phoneExt = branch.getPhoneExtension();
		
		String telephoneNo = "";
		if(preFix != null)
		telephoneNo = preFix+"-"+phoneNumber;
		else
			telephoneNo = phoneNumber;
		
		if(phoneExt != null)
			telephoneNo = telephoneNo +"-"+phoneExt;
		
		if((preFix== null && phoneNumber == null) && phoneExt == null)
		serviceLocation.setPhone("");
		else
		serviceLocation.setPhone(telephoneNo);
		serviceLocation.setCity(branch.getCity());
		serviceLocation.setPostal(branch.getPostal());
		serviceLocation.setEmail(branch.getBranchEmail());
		addServiceLocation.setExistingBranchFlag("none");
		if(serviceLocation.getBranchName()!= null ){
	    	if(!serviceLocation.getBranchName().equals(""))
	    		{
			    	 System.out.println("Servicelocation branch type code setting");
			    	 String servLocCode=Constants.BRANCH+serviceLocation.getBranchName();
			    	 serviceLocation.setServiceLocationCode(servLocCode);
	    		}
	    }
		
		return showForm(request, response, errors);
      
    }
    
    if(serviceLocation.getStateCode()== null || serviceLocation.getStateCode().equals(""))
	{
    	country = countryService.getCountryByCode(serviceLocation.getCountryCode());
    	
    	if(country.getStateAvail() && country.getStateRequiredInAddress()){
        	errors.reject("service.location.state.error", new Object[] {serviceLocation.getCountryCode() }, null);
        	return showForm(request, response, errors);
    	}
    	
    	//Set s =country.getStates();
    	
    	//if(s.size()!=0)
    	//{
		//	errors.reject("service.location.state.error",
		//			new Object[] {serviceLocation.getCountryCode() }, null);
		//	return showForm(request, response, errors);
			
    	//}
	}

//  Servicelocation address validation starts
    if(serviceLocation.getCountryCode().trim().equalsIgnoreCase("USA")||serviceLocation.getCountryCode().trim().equalsIgnoreCase("CAN"))
	 {
	    if(serviceLocation.getAddress1().trim().equals("")&& serviceLocation.getCity().trim().equals("") && serviceLocation.getStateCode().trim().equals(""))
		{
		errors.reject("customer.address.error", new Object[] { serviceLocation.getCountryCode()}, null);
		return showForm(request, response, errors);
		}
		if(serviceLocation.getAddress1().trim().equals(""))
		{
		errors.reject("address.required.error", new Object[] { serviceLocation.getCountryCode()}, null);
		return showForm(request, response, errors);
		}

		if(serviceLocation.getCity().trim().equals(""))
		{
		errors.reject("city.required.error", new Object[] { serviceLocation.getCountryCode()}, null);
		return showForm(request, response, errors);
		}

		//if(serviceLocation.getStateCode().trim().equals(""))
		//{
		//errors.reject("state.required.error", new Object[] { serviceLocation.getCountryCode()}, null);
		//return showForm(request, response, errors);
		//}					

	} else if(serviceLocation.getCountryCode().trim().equalsIgnoreCase("NLD"))
	{
		if( serviceLocation.getAddress1().trim().equals("")&& serviceLocation.getCity().trim().equals("") && serviceLocation.getAddress2().trim().equals(""))
		{
		errors.reject("address1.address2.city.required.error", new Object[] { serviceLocation.getCountryCode()}, null);
		return showForm(request, response, errors);
		}

		if(serviceLocation.getAddress1().trim().equals(""))
		{
		errors.reject("address.required.error", new Object[] {serviceLocation.getCountryCode()}, null);
		return showForm(request, response, errors);
		}

		if(serviceLocation.getCity().trim().equals(""))
		{
		errors.reject("city.required.error", new Object[] {serviceLocation.getCountryCode()}, null);
		return showForm(request, response, errors);
		}

		if(serviceLocation.getAddress2().trim().equals(""))
		{
		errors.reject("address2.required.error", new Object[] { serviceLocation.getCountryCode()}, null);
		return showForm(request, response, errors);
		}

	} else 
	{
		if(serviceLocation.getAddress1().trim().equals("")&&  serviceLocation.getCity().trim().equals(""))
		{
		errors.reject("address.city.required.error", new Object[] { serviceLocation.getCountryCode()}, null);
		return showForm(request, response, errors);
		}

		if(serviceLocation.getAddress1().trim().equals(""))
		{
		errors.reject("address.required.error", new Object[] { serviceLocation.getCountryCode()}, null);
		return showForm(request, response, errors);
		}

		if(serviceLocation.getCity().trim().equals(""))
		{
		errors.reject("city.required.error", new Object[] { serviceLocation.getCountryCode()}, null);
		return showForm(request, response, errors);
		}	
	}
    //Servicelocation address validation end
    try
	{
		serviceLocation = serviceLocationService.addServiceLocation(serviceLocation,taxCode);
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
    modelAndView.addObject("backUrl", "edit_service_location.htm?serviceLocationCode=" + serviceLocation.getServiceLocationCode());
    modelAndView.addObject("backUrlDescription", "You can continue to edit this service location.");
    modelAndView.addObject("description", "Your service location has been successfully created.");

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

 

    protected boolean suppressValidation(HttpServletRequest request,Object command)
  {
    String existingBranchFlag = request.getParameter("existingBranchFlag");
    String labelFlagValue = request.getParameter("labelFlag");
	
    if((existingBranchFlag != null) && ("Y".equals(existingBranchFlag)))
    {
      return true;
    }
    if(labelFlagValue != null)
    {
      return true;
    }

    return super.suppressValidation(request);
  }


  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
   */
  protected Object formBackingObject(HttpServletRequest request) throws Exception {
		
		String branchName= request.getParameter("name");
		
		AddServiceLocation addServiceLocation = new AddServiceLocation();
		ServiceLocation serviceLocation		  = new ServiceLocation();
	
		addServiceLocation.setCountyLabel(null);
		addServiceLocation.setStateLabel(null);
		addServiceLocation.setPostalLabel(null);

		addServiceLocation.setServiceLocation(serviceLocation);
		
		boolean editable = SecurityUtil.isAnyGranted(new String[] {"CreateServiceBranch"});
		addServiceLocation.setViewOnly(editable == false);
		

		return addServiceLocation;
  }
}
