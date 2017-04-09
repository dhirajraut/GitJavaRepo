package com.intertek.web.controller.user;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.domain.AddBusinessUnit;
import com.intertek.entity.*;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.util.*;
import com.intertek.service.UserService;;


public class CreateMultilingualBunitPopupController extends SimpleFormController {
	public CreateMultilingualBunitPopupController() {
		super();
		setSessionForm(true);
		setCommandClass(AddBusinessUnit.class);

	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
		AddBusinessUnit addBusinessUnit = (AddBusinessUnit) command;
		BusinessUnitLanguage businessUnitLanguage=addBusinessUnit.getBusinessUnitLanguage();
		BusinessUnitLanguageId businessUnitLanguageId=businessUnitLanguage.getBusinessUnitLanguageId();		
		
		String lFlag=request.getParameter("languageFlag");		
		String lc=request.getParameter("lcode");		
		String buName=businessUnitLanguage.getBusinessUnitLanguageId().getName();
		if(lFlag.trim().equals("true"))
		{			
			 BusinessUnitLanguage existedBusinessUnitLanguage=null;
			 existedBusinessUnitLanguage=userService.getBuseinessUnitLanguageByName(buName,lc);
			 if(existedBusinessUnitLanguage!=null){
			// existedBusinessUnitLanguage.setBusinessUnitLanguageId(existedBusinessUnitLanguage.getBusinessUnitLanguageId());		   
			 addBusinessUnit.setBusinessUnitLanguage(existedBusinessUnitLanguage);
			 return showForm(request, response, errors);
		     }
		  else{
			   BusinessUnitLanguage businessUnitLanguage1=new BusinessUnitLanguage();			 
			   businessUnitLanguageId.setName(buName);
			   businessUnitLanguage1.setBusinessUnitLanguageId(businessUnitLanguageId);
			   addBusinessUnit.setBusinessUnitLanguage(businessUnitLanguage1);
			   return showForm(request, response, errors);   
		      }
	    }
		
		try
	    {
			  userService.saveBusinessUnitLanguage(businessUnitLanguage);
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

		

		ModelAndView modelAndView = new ModelAndView("common-messagepopup-r");
		
		modelAndView.addObject("param1", businessUnitLanguage.getBusinessUnitLanguageId().getName());
		modelAndView.addObject("param2", businessUnitLanguage.getBusinessUnitLanguageId().getName());
		
		modelAndView.addObject("param3", addBusinessUnit.getDivName());
		modelAndView.addObject("param4", addBusinessUnit.getDivBody());
		modelAndView.addObject("searchForm", addBusinessUnit.getSearchForm());
		modelAndView.addObject("backUrlDescription","Your multilingual branch has been successfully created, ");
		return modelAndView;
		 
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest, org.springframework.web.bind.ServletRequestDataBinder)
	 */
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {

	}

	protected boolean suppressValidation(HttpServletRequest request) {
		String addOrDelete = request.getParameter("addOrDelete");
		if ((addOrDelete != null)	&& ("add".equals(addOrDelete) || "delete".equals(addOrDelete))) {
			return true;
		}

		return super.suppressValidation(request);
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {


		 AddBusinessUnit addBusinessUnit = new AddBusinessUnit();

		 String buName=request.getParameter("buName");
			
		 String div1=request.getParameter("divName");
					
		 String div2=request.getParameter("divBody");
		    
		 
		 UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
		
		 User user=userService.getUserByName(SecurityUtil.getUser().getLoginName());
		 
	    BusinessUnitLanguage businessUnitLanguage = new BusinessUnitLanguage();
	    BusinessUnitLanguageId businessUnitLanguageId=new BusinessUnitLanguageId();
	    BusinessUnitLanguage existedBusinessUnitLanguage=null;
	    
	    existedBusinessUnitLanguage=userService.getBuseinessUnitLanguageByName(buName,user.getLanguage());
	    businessUnitLanguageId.setLanguageCD(user.getLanguage());
	   if(existedBusinessUnitLanguage!=null)
	   {
		   businessUnitLanguageId.setName(existedBusinessUnitLanguage.getBusinessUnitLanguageId().getName());
		   existedBusinessUnitLanguage.setBusinessUnitLanguageId(businessUnitLanguageId);		   
		   addBusinessUnit.setBusinessUnitLanguage(existedBusinessUnitLanguage);
	   }
	   else
	   {
	    businessUnitLanguageId.setName(buName);
		   businessUnitLanguage.setBusinessUnitLanguageId(businessUnitLanguageId);
		   addBusinessUnit.setBusinessUnitLanguage(businessUnitLanguage);
	   }
	 
	   String searchForm = request.getParameter("searchForm");
	   addBusinessUnit.setSearchForm(searchForm);
	   
	   addBusinessUnit.setDivName(div1);
	   addBusinessUnit.setDivBody(div2);
	  
		
		return addBusinessUnit;
	}

	

}
