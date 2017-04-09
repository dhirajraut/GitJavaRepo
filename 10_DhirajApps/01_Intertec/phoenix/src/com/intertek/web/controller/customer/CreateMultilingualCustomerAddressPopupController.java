package com.intertek.web.controller.customer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.domain.AddCustomer;
import com.intertek.domain.CustomerSearch;
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
import com.intertek.web.controller.user.BusinessUnitController;

public class CreateMultilingualCustomerAddressPopupController extends SimpleFormController {
	private static Log log = LogFactory.getLog(CreateMultilingualCustomerAddressPopupController.class);
	public CreateMultilingualCustomerAddressPopupController() {
		super();
		setCommandClass(AddCustomer.class);
		//setSessionForm(true);
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	
	Map custAddrLangmap = new HashMap();
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		AddCustomer addCustomer = (AddCustomer)command;
		CustAddressLanguage custAddressLanguage = addCustomer.getCustAddressLanguage();
    CustomerService customerService = (CustomerService)ServiceLocator.getInstance().getBean("customerService");
    HttpSession session = request.getSession();
   
    String lFlag=request.getParameter("languageFlag");
	String lc=request.getParameter("lcode");		
	
	long custAddrId = addCustomer.getCustAddrId();

	
	if(lFlag.trim().equals("true"))
	{
		if(custAddrId != 0)
		{
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
		else
		{
			 CustAddressLanguage custAddressLanguage1=new CustAddressLanguage();
			 custAddressLanguage1.setCustAddressLanguageId(custAddressLanguage.getCustAddressLanguageId());
		     addCustomer.setCustAddressLanguage(custAddressLanguage1);
		     return showForm(request, response, errors);
		}
       }
		
	if(custAddrId != 0)
	{
		addCustomer.getCustAddrLangmap().put(custAddrId, custAddressLanguage);
	} else
	{
		addCustomer.getCustAddrLangmap().put(addCustomer.getRowNum(),custAddressLanguage);
	}

	session.setAttribute("customerAddressMultilingual", addCustomer.getCustAddrLangmap());
	
   // ModelAndView modelAndView = new ModelAndView();
	ModelAndView modelAndView = new ModelAndView("common-messagepopup-r");
    modelAndView.addObject("param1", custAddressLanguage.getCustAddressLanguageId().getCustAddressId());
	modelAndView.addObject("param2", custAddressLanguage.getCustAddressLanguageId().getCustAddressId());
	
	modelAndView.addObject("param3", addCustomer.getDivName());
	modelAndView.addObject("param4", addCustomer.getDivBody());
	modelAndView.addObject("searchForm", addCustomer.getSearchForm());
	modelAndView.addObject("backUrlDescription","Your multilingual customer address has been added, ");
    
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
		// String custAddrId = request.getParameter("custAddrId");
		 String custCode = request.getParameter("ccode");

		 long custAddrId = 0;
		 long contactAddrId = 0;
		String customerAddrId = request.getParameter("custAddrId");
		
		
		 log.info("======== in multilingual customer : buName = " + request);
		 String divName=request.getParameter("divName");
		 String divBody=request.getParameter("divbody");
		 String searchForm = request.getParameter("searchForm");
		 String custAddress = request.getParameter("custAddress");
		 String contactAddress = request.getParameter("contactAddress");
		 String index=request.getParameter("rowNum");
		 index = index+"_";
		
         AddCustomer addCustomer = new AddCustomer();         
         addCustomer.setSearchForm(searchForm);		   
         addCustomer.setDivName(divName);
         addCustomer.setDivBody(divBody);
         addCustomer.setCustAddress(custAddress);
         addCustomer.setContactAddress(contactAddress);
         addCustomer.setRowNum(index);
         CustAddressLanguage custAddressLanguage = null;
         
         UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
         CustomerService customerService = (CustomerService)ServiceLocator.getInstance().getBean("customerService");
         HttpSession session = request.getSession();
   		
		 User user=userService.getUserByName(SecurityUtil.getUser().getLoginName());
		
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
			 
			   
			   Map custAddrLang =(Map)session.getAttribute("customerAddressMultilingual");
			  
			   CustAddressLanguage customerAddrLang=null;
			   if(custAddrLang!=null )
			   {
				   List mapKeys = new ArrayList(custAddrLang.keySet());
				   
				   customerAddrLang =(CustAddressLanguage)custAddrLang.get(custAddrId);
				
				    if(customerAddrLang!= null && custAddrId != 0)
				    { 
				    	addCustomer.setCustAddressLanguage(customerAddrLang); 
				    } else if(index != null && custAddrId == 0){
				    
				    	 CustAddressLanguage customerAddrLang2 =(CustAddressLanguage)custAddrLang.get(index); 
				    	 if(customerAddrLang2 != null)
				    	 {
				    		 addCustomer.setCustAddressLanguage(customerAddrLang2); 
				    	 }
				    	 else
				    	 {
				    		 custAddressLanguage = new CustAddressLanguage();
							 CustAddressLanguageId custAddressLanguageId = new CustAddressLanguageId();
							 custAddressLanguage.setCustAddressLanguageId(custAddressLanguageId);
							 addCustomer.setCustAddressLanguage(custAddressLanguage);
							 addCustomer.getCustAddressLanguage().getCustAddressLanguageId().setLanguageCD(user.getLanguage());
				    	 }
				    } else
				    {
				       custAddressLanguage = new CustAddressLanguage();
					   CustAddressLanguageId custAddressLanguageId = new CustAddressLanguageId();
					   custAddressLanguage.setCustAddressLanguageId(custAddressLanguageId);
					   addCustomer.setCustAddressLanguage(custAddressLanguage);
					   addCustomer.getCustAddressLanguage().getCustAddressLanguageId().setLanguageCD(user.getLanguage());
					  
				    }
			   } else
			   {
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
		addCustomer.setCustAddrLangmap(custAddrLangmap);
				 
        return addCustomer;
	}
}
