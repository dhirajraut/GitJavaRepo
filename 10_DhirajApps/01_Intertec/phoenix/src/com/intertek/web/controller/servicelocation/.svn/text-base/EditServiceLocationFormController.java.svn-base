package com.intertek.web.controller.servicelocation;

import java.text.SimpleDateFormat;
import java.util.HashSet;
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
import com.intertek.entity.CustAddrSeq;
import com.intertek.entity.CustAddress;
import com.intertek.entity.ServiceLocation;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.CountryService;
import com.intertek.service.ServiceLocationService;
import com.intertek.service.UserService;
import com.intertek.util.SecurityUtil;
import com.intertek.web.controller.BaseSimpleFormController;

//public class EditServiceLocationFormController extends SimpleFormController
public class EditServiceLocationFormController extends BaseSimpleFormController
{
  public EditServiceLocationFormController() {
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
     AddServiceLocation addServiceLocation = (AddServiceLocation)command;


	Country country=null;
	String existingBranchFlag = request.getParameter("existingBranchFlag");
	String labelFlagValue = request.getParameter("labelFlag");
	String taxCode=addServiceLocation.getTaxCode();
	ServiceLocation serviceLocation = addServiceLocation.getServiceLocation();
	String  branchName = serviceLocation.getBranchName();
    UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
    CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");
	
    if(existingBranchFlag != null && existingBranchFlag.trim().equals("Y") && branchName != null)
	  {
			Branch branch = userService.getBranchByName(branchName);
			
			serviceLocation.setName(branch.getDescription());
			serviceLocation.setAddress1(branch.getAddress1());
			serviceLocation.setAddress2(branch.getAddress2());
			serviceLocation.setAddress3(branch.getAddress3());
			serviceLocation.setAddress4(branch.getAddress4());

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
			if(telephoneNo != null)
			serviceLocation.setPhone(telephoneNo);
			serviceLocation.setEmail(branch.getBranchEmail());
			serviceLocation.setCity(branch.getCity());
			serviceLocation.setPostal(branch.getPostal());
			String ctryCode = branch.getCountryCode();
			String stateCode = branch.getStateCode();
			country = countryService.getCountryByCode(ctryCode);
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

			addServiceLocation.setExistingBranchFlag("none");
			return showForm(request, response, errors);
	  }
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
    if(serviceLocation.getStateCode()== null || serviceLocation.getStateCode().trim().equals(""))
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
		//			new Object[] { serviceLocation.getCountryCode() }, null);
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
    
	ServiceLocationService serviceLocationService = (ServiceLocationService)ServiceLocator.getInstance().getBean("serviceLocationService");
	try
	{
		 serviceLocationService.saveServiceLocation(serviceLocation,taxCode);
	}
	catch(Exception e)
	{
		 errors.reject("save.Servicelocation.error", new Object[] {e.getMessage()}, null);
		 return showForm(request, response, errors);
	}

	ModelAndView modelAndView = new ModelAndView("common-message-r");
	modelAndView.addObject("backUrl", "edit_service_location.htm?serviceLocationCode=" + serviceLocation.getServiceLocationCode());
	modelAndView.addObject("backUrlDescription", "You can continue to edit this service location.");
	modelAndView.addObject("description", "Your service location has been successfully updated.");

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
  protected Object formBackingObject(
    HttpServletRequest request
  ) throws ClassCastException
  {
		String serviceLocationCode = request.getParameter("serviceLocationCode");
		System.out.println("Service location id =" + serviceLocationCode);
		AddServiceLocation addServiceLocation = new AddServiceLocation();
		ServiceLocation serviceLocation = null;
		
		
		ServiceLocationService serviceLocationService = (ServiceLocationService)ServiceLocator.getInstance().getBean("serviceLocationService");
		UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
		CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");
		

		try{
			serviceLocation = serviceLocationService.getServiceLocationByServiceLocationCode(serviceLocationCode);
		}catch(ServiceException e)
	    {
	    	throw new ServiceException(e.getMessage(), e.getParams(), e);
	    }
	    catch(Throwable t)
	    {
	        t.printStackTrace();
	        throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
		}

		Set custAddresses = new HashSet();
		if(serviceLocation.getShipToCustAddress() != null)
		{
			CustAddrSeq custAddrSeq  = serviceLocation.getShipToCustAddress();
			System.out.println("Service location custAddrSeq =" + serviceLocation.getShipToCustAddress());

	  
			if(serviceLocation.getShipToCustAddress().getCustAddresses() != null)
			{
				Iterator itr = serviceLocation.getShipToCustAddress().getCustAddresses().iterator();
				  while(itr.hasNext())
				  {
					  CustAddress custAddress = (CustAddress)itr.next();
				  
					serviceLocation.setAddress1(custAddress.getAddress1());
					serviceLocation.setAddress2(custAddress.getAddress2());
					serviceLocation.setAddress3(custAddress.getAddress3());
					serviceLocation.setAddress4(custAddress.getAddress4());
					addServiceLocation.setTaxCode(custAddress.getTaxCode());
					System.out.println("After Save tax Code before display"+custAddress.getTaxCode());
				  }
			}
		}
		
	  
/*        System.out.println("Branch instance " + serviceLocation.getBranch());
		if(serviceLocation.getBranch()== null)
	    {
		serviceLocation.setBranch(branch);
		System.out.println(" Branch id is null");
		}
	*/
        
       
    		System.out.println("country code value ="+serviceLocation.getCountryCode());
    		Country country = countryService.getCountryByCode(serviceLocation.getCountryCode());
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
    		
    		boolean editable = SecurityUtil.isAnyGranted(new String[] {"CreateServiceBranch"});
    		addServiceLocation.setViewOnly(editable == false);
    		
		addServiceLocation.setServiceLocation(serviceLocation);

		
		return addServiceLocation;
  }
}
