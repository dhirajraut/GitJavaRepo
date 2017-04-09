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
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import com.intertek.domain.AddServiceLocation;
import com.intertek.entity.Branch;
import com.intertek.entity.Country;
import com.intertek.entity.CustAddrSeq;
import com.intertek.entity.CustAddress;
import com.intertek.entity.ServiceLocation;
import com.intertek.entity.State;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.CountryService;
import com.intertek.service.ServiceLocationService;
import com.intertek.service.UserService;
import com.intertek.util.Constants;
import com.intertek.util.SecurityUtil;

public class CreateNewServiceLocationPopupController extends
		SimpleFormController {
	public CreateNewServiceLocationPopupController() {
		super();
		setCommandClass(AddServiceLocation.class);
		setSessionForm(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      org.springframework.validation.BindException)
	 */
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		Country country=null;
		String existingBranchFlag = request.getParameter("existingBranchFlag");
		String labelFlagValue = request.getParameter("labelFlag");
		
		AddServiceLocation addServiceLocation = (AddServiceLocation) command;
		
		ServiceLocation serviceLocation = addServiceLocation
				.getServiceLocation();
		String taxCode=addServiceLocation.getTaxCode();
		String name = serviceLocation.getName();
		String phone = serviceLocation.getPhone();
		String countryCode = serviceLocation.getCountryCode();
		String stateCode = serviceLocation.getStateCode();
		String city = serviceLocation.getCity();
		
		ServiceLocationService serviceLocationService = (ServiceLocationService) ServiceLocator
		.getInstance().getBean("serviceLocationService");
		UserService userService = (UserService) ServiceLocator.getInstance()
				.getBean("userService");
		CountryService countryService = (CountryService) ServiceLocator
				.getInstance().getBean("countryService");
		
				
		if(labelFlagValue!=null && !labelFlagValue.equals(""))
		{
			System.out.println("country code value ="+ labelFlagValue);
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
		
		 if(existingBranchFlag != null && existingBranchFlag.trim().equals("Y") && serviceLocation.getBranchName() != null)
				
			{
				
				Branch branch = userService.getBranchByName(serviceLocation.getBranchName());
					
					serviceLocation.setName(branch.getDescription());
					serviceLocation.setAddress1(branch.getAddress1());
					serviceLocation.setAddress2(branch.getAddress2());
					serviceLocation.setAddress3(branch.getAddress3());
					serviceLocation.setAddress4(branch.getAddress4());
					System.out.println("Address1 : " + branch.getAddress1());
					String ctryCode = branch.getCountryCode();
					stateCode = branch.getStateCode();
					country = countryService.getCountryByCode(ctryCode);
					if(country != null)
					serviceLocation.setCountryCode(country.getCountryCode());
					
					Iterator itr = (country.getStates()).iterator();
			        State state =(State)itr.next();
		           
					serviceLocation.setStateCode(state.getStateId().getStateCode());
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
					return showForm(request, response, errors);
		      
		    }
	 		
		 if(serviceLocation.getStateCode()== null || serviceLocation.getStateCode().equals(""))
			{
		    	country = countryService.getCountryByCode(serviceLocation.getCountryCode());
		    	Set s =country.getStates();
		    	
		    	if(s.size()!=0)
		    	{
					errors.reject("create.service.location.error",
							new Object[] { "Please select State for Country: "
									+ serviceLocation.getCountryCode() }, null);
					return showForm(request, response, errors);
					
		    	}
			}
		 
		//Servicelocation address validation starts
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

			if(serviceLocation.getStateCode().trim().equals(""))
			{
			errors.reject("state.required.error", new Object[] { serviceLocation.getCountryCode()}, null);
			return showForm(request, response, errors);
			}					

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
		try {
			serviceLocation = serviceLocationService
					.addServiceLocation(serviceLocation,taxCode);
		} catch(ServiceException e)
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

		return new ModelAndView(new RedirectView(
				"create_new_servicelocation.htm?div1="+addServiceLocation.getDiv1()+"&div2="+addServiceLocation.getDiv2()+"&inputFieldId="+addServiceLocation.getInputFieldId()), "serviceLocationCode",serviceLocation.getServiceLocationCode());

	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest,
	 *      org.springframework.web.bind.ServletRequestDataBinder)
	 */
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(java.util.Date.class, null,
				new CustomDateEditor(dateFormat, true));
	}

	protected boolean suppressValidation(HttpServletRequest request,Object command) {
		String existingBranchFlag = request.getParameter("existingBranchFlag");
		if ((existingBranchFlag != null) && ("Y".equals(existingBranchFlag))) {
			System.out.println("Supress validation");
			return true;
		}

		return super.suppressValidation(request);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		// String branchName= request.getParameter("name");

		AddServiceLocation addServiceLocation = new AddServiceLocation();
		ServiceLocation serviceLocation = new ServiceLocation();

		addServiceLocation.setServiceLocation(serviceLocation);
		String div1 = request.getParameter("div1");
		String div2 = request.getParameter("div2");
		String serviceLocationCode = request.getParameter("serviceLocationCode");
		String inputFieldId = request.getParameter("inputFieldId");
		CountryService countryService = (CountryService) ServiceLocator
		.getInstance().getBean("countryService");
		addServiceLocation.setCountyLabel(null);
		addServiceLocation.setStateLabel(null);
		addServiceLocation.setPostalLabel(null);
		try {
		if (inputFieldId != null && (!inputFieldId.trim().equals("")))
			addServiceLocation.setInputFieldId(inputFieldId);
		else
			addServiceLocation.setInputFieldId("jobOrder.serviceLocation.name");
		
		if (serviceLocationCode != null)

		{
			
			addServiceLocation.setOkButton("ok");
			addServiceLocation.setBranchCode(null);
			ServiceLocationService serviceLocationService = (ServiceLocationService) ServiceLocator
					.getInstance().getBean("serviceLocationService");

			try{
				serviceLocation = serviceLocationService.getServiceLocationByServiceLocationCode(serviceLocationCode);
			} catch(Exception e){
			  e.printStackTrace();
			}
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
			Set custAddresses = new HashSet();

			CustAddrSeq custAddrSeq  = serviceLocation.getShipToCustAddress();
			System.out.println("Service location custAddrSeq =" + serviceLocation.getShipToCustAddress());
			CustAddress custAddress = new CustAddress();
	     
		  Iterator itr = serviceLocation.getShipToCustAddress().getCustAddresses().iterator();
		  while(itr.hasNext())
		  {
			  custAddress = (CustAddress)itr.next();
			 
			  //Setting custAddress values to Servicelocation address
			  serviceLocation.setAddress1(custAddress.getAddress1());
			  serviceLocation.setAddress2(custAddress.getAddress2());
			  serviceLocation.setAddress3(custAddress.getAddress3());
			  serviceLocation.setAddress4(custAddress.getAddress4());
			  //End
		  }
			  addServiceLocation.setTaxCode(custAddress.getTaxCode());
			
		}else
		{
			addServiceLocation.setOkButton(null);
		}	
		boolean editable = SecurityUtil.isAnyGranted(new String[] {"CreateServiceBranch"});
		addServiceLocation.setViewOnly(editable == false);
		addServiceLocation.setServiceLocation(serviceLocation);
		addServiceLocation.setDiv1(div1);
		addServiceLocation.setDiv2(div2);
		} catch(ServiceException e)
	    {
	    	throw new ServiceException(e.getMessage(), e.getParams(), e);
	    }
	    catch(Throwable t)
	    {
	        t.printStackTrace();
	        throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
	    }
		return addServiceLocation;
	}
}
