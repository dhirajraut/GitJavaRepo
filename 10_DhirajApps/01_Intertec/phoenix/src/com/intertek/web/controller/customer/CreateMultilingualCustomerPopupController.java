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
import com.intertek.entity.CustomerLanguage;
import com.intertek.entity.CustomerLanguageId;
import com.intertek.entity.User;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.CustomerService;
import com.intertek.service.UserService;
import com.intertek.util.SecurityUtil;

public class CreateMultilingualCustomerPopupController extends SimpleFormController {
	public CreateMultilingualCustomerPopupController() {
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
		CustomerLanguage customerLanguage = addCustomer.getCustomerLanguage();
    CustomerService customerService = (CustomerService)ServiceLocator.getInstance().getBean("customerService");
   CustomerLanguageId customerLanguageId=customerLanguage.getCustomerLanguageId();
    
    String lFlag=request.getParameter("languageFlag");
    
	String lc=request.getParameter("lcode");		
	String custCode=customerLanguage.getCustomerLanguageId().getCustCode();
	String name=customerLanguage.getName();
	if(lFlag.trim().equals("true"))
	{			
		CustomerLanguage existedCustomerLanguage=null;
		existedCustomerLanguage=customerService.getCustomerLanguageByCode(custCode,lc);
		 if(existedCustomerLanguage!=null){
			// existedCustomerLanguage.setName(name);
			 addCustomer.setCustomerLanguage(existedCustomerLanguage);
		      return showForm(request, response, errors);
	     }
	    else {
		     CustomerLanguage customerLanguage1=new CustomerLanguage();
		     customerLanguage1.setName(name);
		     customerLanguageId.setCustCode(custCode);
		     customerLanguage1.setCustomerLanguageId(customerLanguageId);
			 addCustomer.setCustomerLanguage(customerLanguage1);
		   return showForm(request, response, errors);   
	      }
    }
  
    
    HttpSession session = request.getSession();
    
    session.setAttribute("customermultilingual", customerLanguage);
    //ModelAndView modelAndView = new ModelAndView();
	
    ModelAndView modelAndView = new ModelAndView("common-messagepopup-r");
	modelAndView.addObject("param1", customerLanguage.getName());
	modelAndView.addObject("param2", customerLanguage.getName());
	modelAndView.addObject("param3", addCustomer.getDivName());
	modelAndView.addObject("param4", addCustomer.getDivBody());
	modelAndView.addObject("searchForm", addCustomer.getSearchForm());
	modelAndView.addObject("backUrlDescription","Your multilingual customer has been successfully added.");
	
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
		 
		 String custCode = request.getParameter("custCode");
		 String custName = request.getParameter("custName");
		 String divName=request.getParameter("divName");
		 String divBody=request.getParameter("divbody");
		 String searchForm = request.getParameter("searchForm");
		
         AddCustomer addCustomer = new AddCustomer();
         
         addCustomer.setSearchForm(searchForm);
		   
         addCustomer.setDivName(divName);
         addCustomer.setDivBody(divBody);
         CustomerService customerService = (CustomerService)ServiceLocator.getInstance().getBean("customerService");
		 
         UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
 		
		 User user=userService.getUserByName(SecurityUtil.getUser().getLoginName());
	
		   HttpSession session = request.getSession();
		   CustomerLanguage customerLang = null;
		   if(session != null)
		   customerLang =(CustomerLanguage)session.getAttribute("customermultilingual");
		
		   if(custCode != null && customerLang != null && customerLang.getCustomerLanguageId().getCustCode()!= null && customerLang.getCustomerLanguageId().getCustCode().trim().equals(custCode))
		   {
			   addCustomer.setCustomerLanguage(null);
			   addCustomer.setCustomerLanguage(customerLang);
		   } else if(customerLang != null && custCode == null)
		   {
			   addCustomer.setCustomerLanguage(null);
			   addCustomer.setCustomerLanguage(customerLang);
		   } else {
		     CustomerLanguage exitcustomerLanguage = customerService.getCustomerLanguageByCode(custCode,user.getLanguage());
			 if(exitcustomerLanguage != null)
			 {
				   addCustomer.setCustomerLanguage(exitcustomerLanguage);
			 } else
			 {
				   CustomerLanguage customerLanguage = new CustomerLanguage();
				   CustomerLanguageId customerLanguageId = new CustomerLanguageId();
				   //customerLanguage.setName(custName);
			       customerLanguageId.setCustCode(custCode);
			       customerLanguage.setCustomerLanguageId(customerLanguageId);
				   addCustomer.setCustomerLanguage(customerLanguage);
				   addCustomer.getCustomerLanguage().getCustomerLanguageId().setLanguageCD(user.getLanguage());
			 }
		   }
       return addCustomer;
	}
}
