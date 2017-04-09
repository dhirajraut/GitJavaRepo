package com.intertek.web.controller.customer;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.domain.AddCustomer;
import com.intertek.domain.CustomerSearch;
import com.intertek.entity.CustAddrSeq;
import com.intertek.entity.CustAddressLanguage;
import com.intertek.entity.CustAddressLanguageId;
import com.intertek.entity.CustomerLanguage;
import com.intertek.entity.CustomerLanguageId;
import com.intertek.entity.User;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.CustomerService;
import com.intertek.service.UserService;
import com.intertek.util.SecurityUtil;

public class CreateMultilingualQuickCustomerAddrPopupController extends SimpleFormController {
	public CreateMultilingualQuickCustomerAddrPopupController() {
		super();
		setCommandClass(AddCustomer.class);
		setSessionForm(true);
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		AddCustomer addCustomer = (AddCustomer)command;
		CustAddressLanguage custAddressLanguage = addCustomer.getCustAddressLanguage();
		CustomerService customerService = (CustomerService)ServiceLocator.getInstance().getBean("customerService");
		HttpSession session = request.getSession();
   
    String custLFlag=request.getParameter("custLanguageFlag");
    String contactLFlag=request.getParameter("contactLanguageFlag");
	String lc=request.getParameter("lcode");		
	
	long custAddrId = addCustomer.getCustAddrId();
	long contactAddrId = addCustomer.getContactAddrId();
	
	String custAddrLangReq = addCustomer.getCustAddress();
	String contactAddrLangReq = addCustomer.getContactAddress();
    
	if(custLFlag.trim().equals("true"))
	{
		if(custAddrId == 0)
		{
			CustAddressLanguage customerAddr =(CustAddressLanguage) session.getAttribute("customerAddrMultilingual");
        	if(customerAddr != null)
        	{
	        	custAddrId = customerAddr.getCustAddressLanguageId().getCustAddressId();
	        	addCustomer.setCustAddressLanguage(customerAddr);
	        	return showForm(request, response, errors);
        	} else {
        		return showForm(request, response, errors);
        	}
		}
		if(custAddrId != 0)
        {
			// System.out.println("Session custAddrId="+custAddrId);
			 addCustomer.setCustAddrId(custAddrId);
			 CustAddressLanguage custAddressLang = null;
			 custAddressLang = customerService.getCustomerAddrLanguageById(custAddrId,lc);
			 
			 if(custAddressLang != null)
			 {
			   addCustomer.setCustAddressLanguage(custAddressLang);
			   return showForm(request, response, errors);
			 } else
			 {
				 CustAddressLanguage custAddressLanguage1=new CustAddressLanguage();
				 custAddressLanguage1.setCustAddressLanguageId(custAddressLanguage.getCustAddressLanguageId());
			     addCustomer.setCustAddressLanguage(custAddressLanguage1);
			   return showForm(request, response, errors);
			 }
        } 
	}
	if(contactLFlag.trim().equals("true"))
	{
		if(contactAddrId == 0)
		{
			CustAddressLanguage contactAddr =(CustAddressLanguage) session.getAttribute("contactAddrMultilingual");
			if(contactAddr != null)
			{
				contactAddrId = contactAddr.getCustAddressLanguageId().getCustAddressId();
				addCustomer.setCustAddressLanguage(contactAddr);
	        	return showForm(request, response, errors);
			} else {
				return showForm(request, response, errors);
			}
		}
		if(contactAddrId != 0)
        {
		// System.out.println("Session contactAddrId="+contactAddrId);
       	 addCustomer.setContactAddrId(contactAddrId);
       	 CustAddressLanguage custAddressLang = null;
       	 custAddressLang = customerService.getCustomerAddrLanguageById(contactAddrId,lc);
			 
		 if(custAddressLang != null)
		 {
		   addCustomer.setCustAddressLanguage(custAddressLang);
		   return showForm(request, response, errors);
		 } else
		 {
			 CustAddressLanguage custAddressLanguage2=new CustAddressLanguage();
			 custAddressLanguage2.setCustAddressLanguageId(custAddressLanguage.getCustAddressLanguageId());
		     addCustomer.setCustAddressLanguage(custAddressLanguage2);
			 return showForm(request, response, errors);
		 }
        }
    }
    if(addCustomer.getCustAddrId()!= 0 && addCustomer.getCustAddress()!= null && addCustomer.getCustAddress().trim().equals("custAddress"))
    {
    	custAddressLanguage.getCustAddressLanguageId().setCustAddressId(addCustomer.getCustAddrId());
    	session.setAttribute("customerAddrMultilingual", custAddressLanguage);
    } else
    {
    	if(addCustomer.getCustAddress()!= null && addCustomer.getCustAddress().trim().equals("custAddress"))
    	session.setAttribute("customerAddrMultilingual", custAddressLanguage);
    }
    if(addCustomer.getContactAddrId() != 0 && addCustomer.getContactAddress() != null && addCustomer.getContactAddress().trim().equals("contactAddress"))
    {
    	custAddressLanguage.getCustAddressLanguageId().setCustAddressId(addCustomer.getContactAddrId());
    	session.setAttribute("contactAddrMultilingual", custAddressLanguage);
    } else {
    	if(addCustomer.getContactAddress() != null && addCustomer.getContactAddress().trim().equals("contactAddress"))
    	session.setAttribute("contactAddrMultilingual", custAddressLanguage);
    }
  
  //  ModelAndView modelAndView = new ModelAndView();
    ModelAndView modelAndView = new ModelAndView("common-messagepopup-r");
	modelAndView.addObject("param1", custAddressLanguage.getCustAddressLanguageId().getCustAddressId());
	modelAndView.addObject("param2", custAddressLanguage.getCustAddressLanguageId().getCustAddressId());
	modelAndView.addObject("param3", addCustomer.getDivName());
	modelAndView.addObject("param4", addCustomer.getDivBody());
	modelAndView.addObject("searchForm", addCustomer.getSearchForm());
	modelAndView.addObject("backUrlDescription","Your multilingual customeraddress has been successfully added.");
	//modelAndView.addObject("description","Your multilingual customeraddress has been successfully created.");*/
    return modelAndView;
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest, org.springframework.web.bind.ServletRequestDataBinder)
	 */
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
	}

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		
		 long custAddrId = 0;
		 long contactAddrId = 0;
		 String customerAddrId = request.getParameter("custAddrId");
		 String contactAddressId = request.getParameter("contactAddrId");
		 String custCode = request.getParameter("custCode");
		 String divName=request.getParameter("divName");
		 String divBody=request.getParameter("divbody");
		 String searchForm = request.getParameter("searchForm");
		 String custAddress = request.getParameter("custAddress");
		 String contactAddress = request.getParameter("contactAddress");
		
         AddCustomer addCustomer = new AddCustomer();
         
         addCustomer.setSearchForm(searchForm);
		   
         addCustomer.setDivName(divName);
         addCustomer.setDivBody(divBody);
         addCustomer.setCustAddress(custAddress);
         addCustomer.setContactAddress(contactAddress);
         
         UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
         CustomerService customerService = (CustomerService)ServiceLocator.getInstance().getBean("customerService");
   		
		 User user=userService.getUserByName(SecurityUtil.getUser().getLoginName());
		 
	    System.out.println("custAddrId="+custAddrId);
	    System.out.println("contactAddrId="+contactAddrId);
	   
	    if(customerAddrId != null)
        {
	    	 if(customerAddrId != null && (customerAddrId.trim().equals("")||customerAddrId.trim().equals("0")))
			 {
				 custAddrId = 0;
			 } else if(customerAddrId != null)
			 {
				 custAddrId = Long.valueOf(customerAddrId).longValue();
			 } else
			 {
				 custAddrId = 0;
			 }
	    	 
			   addCustomer.setCustAddrId(custAddrId);
			
			   HttpSession session = request.getSession();
			   CustAddressLanguage customerAddrLang =(CustAddressLanguage) session.getAttribute("customerAddrMultilingual");
			  
			  // if(custAddrId != null && customerAddrLang != null && customerAddrLang.getCustAddressLanguageId().getCustAddressId()!= null && customerAddrLang.getCustAddressLanguageId().getCustAddressId().trim().equals(custAddrId))
			   if(custAddrId != 0 && customerAddrLang != null && customerAddrLang.getCustAddressLanguageId().getCustAddressId()== Long.valueOf(custAddrId))
			   {
				   addCustomer.setCustAddressLanguage(customerAddrLang); 
				   addCustomer.setCustAddrId(customerAddrLang.getCustAddressLanguageId().getCustAddressId());
			   } else if(customerAddrLang != null && custAddrId == 0)
			   {
				   addCustomer.setCustAddressLanguage(customerAddrLang); 
				   addCustomer.setCustAddrId(customerAddrLang.getCustAddressLanguageId().getCustAddressId());
			   } else
			   {     
				     
				     CustAddressLanguage custAddressLanguage = null;
					 custAddressLanguage = customerService.getCustomerAddrLanguageById(custAddrId,user.getLanguage());
					 
					 if(custAddressLanguage != null)
					 {
					   addCustomer.setCustAddressLanguage(custAddressLanguage);
					 } else
					 {
					   custAddressLanguage = new CustAddressLanguage();
					   CustAddressLanguageId custAddressLanguageId = new CustAddressLanguageId();
					   custAddressLanguage.setCustAddressLanguageId(custAddressLanguageId);
					   addCustomer.setCustAddressLanguage(custAddressLanguage);
					   addCustomer.getCustAddressLanguage().getCustAddressLanguageId().setLanguageCD(user.getLanguage());
					 }
				}
		 }
        
		if(contactAddressId != null)
        {
			if(contactAddressId!= null && (contactAddressId.trim().equals("")||contactAddressId.trim().equals("0")))
			 {
				 contactAddrId = 0;
			 } else if(contactAddressId != null)
			 {
				 contactAddrId = Long.valueOf(contactAddressId).longValue();
			 } else
			 {
				 contactAddrId = 0;
			 }
       	       addCustomer.setContactAddrId(contactAddrId);
       	
			   HttpSession session = request.getSession();
			   CustAddressLanguage contactAddrLang =(CustAddressLanguage) session.getAttribute("contactAddrMultilingual");
			
			   CustomerLanguage customerLang = null;
			   customerLang =(CustomerLanguage)session.getAttribute("customermultilingual");
			 
			   // setting session null for different customers  
			   if(custCode != null && customerLang != null && customerLang.getCustomerLanguageId().getCustCode()!= null && !customerLang.getCustomerLanguageId().getCustCode().trim().equals(custCode))
			   {
				   contactAddrLang = null;
			   }
			   if(contactAddrLang != null )
			   {
				   addCustomer.setCustAddressLanguage(contactAddrLang);
				   addCustomer.setContactAddrId(contactAddrLang.getCustAddressLanguageId().getCustAddressId());
			   } else {
				     CustAddressLanguage custAddressLanguage = null;
					 custAddressLanguage = customerService.getCustomerAddrLanguageById(contactAddrId,user.getLanguage());
					 
					 if(custAddressLanguage != null)
					 {
					   addCustomer.setCustAddressLanguage(custAddressLanguage);
					 } else
					 {
					   custAddressLanguage = new CustAddressLanguage();
					   CustAddressLanguageId custAddressLanguageId = new CustAddressLanguageId();
					   custAddressLanguage.setCustAddressLanguageId(custAddressLanguageId);
					   addCustomer.setCustAddressLanguage(custAddressLanguage);
					   addCustomer.getCustAddressLanguage().getCustAddressLanguageId().setLanguageCD(user.getLanguage());
			        }
			 }
        }
        return addCustomer;
	}
}

