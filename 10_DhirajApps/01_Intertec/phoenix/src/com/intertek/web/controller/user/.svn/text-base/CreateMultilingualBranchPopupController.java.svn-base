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

import com.intertek.domain.AddBranch;
import com.intertek.entity.*;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.util.*;
import com.intertek.service.UserService;;


public class CreateMultilingualBranchPopupController extends SimpleFormController {
	public CreateMultilingualBranchPopupController() {
		super();
		setSessionForm(true);
		setCommandClass(AddBranch.class);

	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
		AddBranch addBranch = (AddBranch) command;
		BranchLanguage branchLanguage=addBranch.getBranchLanguage();
		BranchLanguageId branchLanguageId=branchLanguage.getBranchLanguageId();
		
		
		
		String lFlag=request.getParameter("languageFlag");		
		String lc=request.getParameter("lcode");		
		String branchCode=branchLanguage.getBranchLanguageId().getName();
		if(lFlag.trim().equals("true"))
		{			
			BranchLanguage existedBranchLanguage=null;
			existedBranchLanguage=userService.getBranchLanguageByName(branchCode,lc);
			 if(existedBranchLanguage!=null){
				// existedBranchLanguage.setBranchLanguageId(existedBranchLanguage.getBranchLanguageId());		   
				   addBranch.setBranchLanguage(existedBranchLanguage);
			      return showForm(request, response, errors);
		     }
		  else{
			  BranchLanguage branchLanguage1=new BranchLanguage();			 
			  branchLanguageId.setName(branchCode);
			  branchLanguage1.setBranchLanguageId(branchLanguageId);
			  addBranch.setBranchLanguage(branchLanguage1);
			   return showForm(request, response, errors);   
		      }
	    }
		
		
		
		try
	    {
			  userService.saveBranchLanguage(branchLanguage);
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
		
		modelAndView.addObject("param1", branchLanguage.getBranchLanguageId().getName());
		modelAndView.addObject("param2", branchLanguage.getBranchLanguageId().getName());
		
		modelAndView.addObject("param3", addBranch.getDivName());
		modelAndView.addObject("param4", addBranch.getDivBody());
		modelAndView.addObject("searchForm", addBranch.getSearchForm());
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
		 AddBranch addBranch = new AddBranch();
		 UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
		String branchCode=request.getParameter("branchCode");
		User user=userService.getUserByName(SecurityUtil.getUser().getLoginName());
		String div1=request.getParameter("divName");
				
	    String div2=request.getParameter("divBody");
	    
		
	    BranchLanguage branchLanguage = new BranchLanguage();
	    BranchLanguageId branchLanguageId=new BranchLanguageId();
	    BranchLanguage existedBranchLanguage=null;
	    
	   existedBranchLanguage=userService.getBranchLanguageByName(branchCode,user.getLanguage());
	   branchLanguageId.setLanguageCD(user.getLanguage());
	   if(existedBranchLanguage!=null &&!existedBranchLanguage.equals(""))
	   {
		   branchLanguageId.setName(existedBranchLanguage.getBranchLanguageId().getName());
		   existedBranchLanguage.setBranchLanguageId(branchLanguageId);		   
		   addBranch.setBranchLanguage(existedBranchLanguage);
	   }
	   else
	   {
		   branchLanguageId.setName(branchCode);
		   branchLanguage.setBranchLanguageId(branchLanguageId);
		   addBranch.setBranchLanguage(branchLanguage);
	   }
	 
	   String searchForm = request.getParameter("searchForm");
	   addBranch.setSearchForm(searchForm);
	   
	   addBranch.setDivName(div1);
	   addBranch.setDivBody(div2);
	  
		
		return addBranch;
	}

	

}
