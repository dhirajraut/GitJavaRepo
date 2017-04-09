package com.intertek.web.controller.user;

import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import com.intertek.exception.ServiceException;
import com.intertek.domain.AddBranch;
import com.intertek.entity.AssocBranch;
import com.intertek.entity.AssocBranchId;
import com.intertek.entity.Branch;
import com.intertek.entity.Country;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.CountryService;
import com.intertek.service.UserService;
import com.intertek.util.Constants;
import com.intertek.util.SecurityUtil;
import com.intertek.web.controller.BaseSimpleFormController;
import com.intertek.web.propertyeditors.CustomPrimitiveNumberEditor;

public class CreateBranchFormController extends BaseSimpleFormController
//public class CreateBranchFormController extends SimpleFormController
{
  public CreateBranchFormController() {
    super();
	setSessionForm(true);
    setCommandClass(AddBranch.class);
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
    AddBranch addBranch = (AddBranch) command;
	Branch branch = addBranch.getBranch();
	//Branch newOpsBranch = branch.getOpsBranch();
	//Branch newLabBranch = branch.getLabBranch();
	AssocBranch[] assocBranchItems = addBranch.getAssocBranches();

    UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
    CountryService countryService = (CountryService) ServiceLocator
	.getInstance().getBean("countryService");



   String addOrDelete = request.getParameter("addOrDelete");
   String rowNum = addBranch.getRowNum();
   String assocBranchFlag = addBranch.getBranchTypeFlag();

   if((addOrDelete != null) && ("add".equals(addOrDelete) || "delete".equals(addOrDelete)))
   {
			if("add".equals(addOrDelete))
			{
				addBranch.setAssocBranches(addAssocBranch(addBranch.getAssocBranches()));
			}
			else
			{
				addBranch.setAssocBranches(deleteAssocBranch(addBranch.getAssocBranches(), addBranch.getIndex()));
			}
		  addBranch.setAssocBranchCount(addBranch.getAssocBranches().length);

		  addBranch.setAddOrDelete("none");

     return showForm(request, response, errors);
   }
   
   if(assocBranchFlag != null && (!assocBranchFlag.trim().equals("")) && (!assocBranchFlag.trim().equals("none")))
   {
	   if(assocBranchFlag.trim().equals("addAssocBranch"))
	   {
		   if(rowNum != null && (!rowNum.trim().equals("")) && (!rowNum.trim().equalsIgnoreCase("none")))
		   {
			   AssocBranch assocBranch = addBranch.getAssocBranches()[Integer.parseInt(rowNum)];
			    Branch assocBranchRef=null;
			    try 
    	       { 
			   assocBranchRef = userService.getBranchByName(assocBranch.getAssocBranchId().getAssocBranchName());
			   }catch(ServiceException e)
				{
				  addBranch.setRowNum("none");
				  addBranch.setBranchTypeFlag("none");
				  e.printStackTrace();
				 // errors.reject(e.getMessage(), e.getParams(), null);
				  return showForm(request, response, errors);
				}
					catch(Throwable t)
					{
                      addBranch.setRowNum("none");
					  addBranch.setBranchTypeFlag("none");
					  t.printStackTrace();
					  errors.reject("generic.error", new Object[] {t.getMessage()}, null);
					  return showForm(request, response, errors);
					}
			   if(assocBranchRef == null)
			   {
					  errors.reject("create.customer.error", new Object[] {"Invalid Assoc Branch"}, null);
					  addBranch.setRowNum("none");
					  addBranch.setBranchTypeFlag("none");
					  return showForm(request, response, errors);			  
			   }
			   System.out.println("Associte Branch reqest2");
			   if(assocBranchRef.getType().equalsIgnoreCase("OPS"))
				   assocBranchRef.setType(Constants.OPS_BRANCH_TYPE);
				if(assocBranchRef.getType().equalsIgnoreCase("LAB"))
					assocBranchRef.setType(Constants.LAB_BRANCH_TYPE);
				if(assocBranchRef.getType().equalsIgnoreCase("OPSL"))
					assocBranchRef.setType(Constants.OPSLAB_BRANCH_TYPE);
				if(assocBranchRef.getType().equalsIgnoreCase("ADMN"))
					assocBranchRef.setType(Constants.ADMIN_BRANCH_TYPE);
				if(assocBranchRef.getType().equalsIgnoreCase("OPSC"))
					assocBranchRef.setType(Constants.OPSCENTER_BRANCH_TYPE);
			   assocBranch.setAssocBranch(assocBranchRef);
			   addBranch.getAssocBranches()[Integer.parseInt(rowNum)] = assocBranch;
			   addBranch.setRowNum("none");
			   addBranch.setBranchTypeFlag("none");
			   return showForm(request, response, errors);
		   }

	   }
	   else if(assocBranchFlag.trim().equals("Ops") || assocBranchFlag.trim().equals("Lab"))
	   {
		   addBranch.setRowNum("none");
		   addBranch.setBranchTypeFlag("none");
		   return showForm(request, response, errors);   
	   }
   }

    branch.getAssocBranch().clear();
		if (assocBranchItems != null && assocBranchItems.length > 0)
		{
			System.out.println("assoc branches is not null");
			for(int i=0; i< assocBranchItems.length; i++)
			{
				//Branch assocBr = userService.getBranchByName(assocBranchItems[i].getName());
				branch.getAssocBranch().add(assocBranchItems[i]);
				System.out.println("assoc branch name :"+assocBranchItems[i].getAssocBranchId().getAssocBranchName());
			}
			System.out.println("size of assoc branches :"+branch.getAssocBranch().size());
		}
		 if(branch.getStateCode()== null || branch.getStateCode().equals(""))
			{
		    	country = countryService.getCountryByCode(branch.getCountryCode());
		    	Set s =country.getStates();
		    	
		    	if(s.size()!=0)
		    	{
					//errors.reject("create.branch.error",new Object[] { "Please select State for Country: "+ branch.getCountryCode() }, null);
					errors.reject("select.state.error",new Object[] {branch.getCountryCode() }, null);
					return showForm(request, response, errors);
					
		    	}
			}
		try
		{
		  branch = userService.addBranch(branch);
		}
		catch(ServiceException e)
		{
		branch.setOpsBranch(new Branch());
		branch.setLabBranch(new Branch());
		e.printStackTrace();
		errors.reject(e.getMessage(), e.getParams(), null);
		return showForm(request, response, errors);
		}
		catch(Throwable t)
		{
		branch.setOpsBranch(new Branch());
		branch.setLabBranch(new Branch());
		t.printStackTrace();
		errors.reject("generic.error", new Object[] {t.getMessage()}, null);
		return showForm(request, response, errors);
		}


	 // START : #119240 : 29/06/09
	    /*ModelAndView modelAndView = new ModelAndView("common-message-r");
	    modelAndView.addObject("backUrl", "edit_branch.htm?branch.name=" + branch.getName());
	    modelAndView.addObject("backUrlDescription", "You can continue to edit this branch.");
	    modelAndView.addObject("description", "Your branch has been successfully created.");*/
	   
		 
		 ModelAndView modelAndView = new ModelAndView("edit-branch-r");
		 modelAndView.addObject("branch.name", branch.getName());
		 modelAndView.addObject("saved_message", "Saved successfully");
		
	// END : #119240 : 29/06/09	
		
    return modelAndView;


  }

  protected boolean suppressValidation(HttpServletRequest request)
  {
    String addOrDelete = request.getParameter("addOrDelete");
    String branchTypeFlag = request.getParameter("branchTypeFlag");
    if((addOrDelete != null) && ("add".equals(addOrDelete) || "delete".equals(addOrDelete)))
    {
      return true;
    }
    if((branchTypeFlag != null) &&(!branchTypeFlag.trim().equals("")) && (!"none".equals(branchTypeFlag)))
    {
      return true;
    }
    return super.suppressValidation(request);
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

    NumberFormat nf = NumberFormat.getInstance();

    CustomPrimitiveNumberEditor integerEditor = new CustomPrimitiveNumberEditor(Integer.class, nf, 0);
    binder.registerCustomEditor(Integer.class, "branch.fax", integerEditor);
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
   */
  protected Object formBackingObject(
    HttpServletRequest request
  ) throws Exception
  {
    System.out.println("inside formBacking object : CreateBranchFormController");
	  String countStr = request.getParameter("assocBranchCount");
	  if( countStr == null || countStr.trim().equals("") ) countStr = "0";

		int count = 0;
		try
		{
			count = new Integer(countStr).intValue();
		}
		catch(Exception e) { }

		AddBranch addBranch = new AddBranch();

		addBranch.setAssocBranchCount(count);
		AssocBranch[] assocBranches = new AssocBranch[count];
		/*		for(int i=0; i<count; i++)
		{
			Branch assocBranch = new Branch();
			BusinessUnit assocBu = new BusinessUnit();
			assocBranch.setBusinessUnit(assocBu);

			if(!"POST".equals(request.getMethod()))
			{
			  //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
			  UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");

			  //User user = userService.getUserByNameWithOrgHierarchy(SecurityUtil.getUser().getLoginName());
			  String assocBuName = userService.getFirstBusinessUnitNameByDivisionName(
				SecurityUtil.getUser().getBranch().getBusinessUnit().getOrganization().getName()
			  );

			  assocBu.setName(assocBuName);
			}
			assocBranches[i] = assocBranch;
		}*/
		addBranch.setAssocBranches(assocBranches);
		

    Branch branch = new Branch();

    if(!"POST".equals(request.getMethod()))
    {
      //Authentication auth = SecurityContextHolder.getContext().getAuthentication();
      UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");

      //User user = userService.getUserByNameWithOrgHierarchy(SecurityUtil.getUser().getLoginName());
	 String buName=null;
    try 
    { 
	 buName = userService.getFirstBusinessUnitNameByDivisionName(
        SecurityUtil.getUser().getBranch().getBusinessUnit().getOrganization().getName()
      );
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

      branch.setBuName(buName);
    }
    if(branch.getCountryCode() == null || branch.getCountryCode().trim().equals(""))
    	branch.setCountryCode(SecurityUtil.getUser().getCountryCode());
    System.out.println("country code :"+branch.getCountryCode());
		addBranch.setBranch(branch);


	// START : #119240 : 29/06/09
	HttpSession session = request.getSession();
	if(session.getAttribute("branchSearch")!=null){
		
		session.removeAttribute("branchSearch");	
	}
	// END : #119240 : 29/06/09	
		
    return addBranch;
  }


    private AssocBranch[] addAssocBranch(AssocBranch[] assocBranches)
  {
    	AssocBranch assocBranch = new AssocBranch();
    	Branch assocBranchRef = new Branch();
    	assocBranch.setAssocBranch(assocBranchRef);
		assocBranch.setAssocBranchId(new AssocBranchId());
		
		AssocBranch[] results = null;
		if(assocBranches == null) results = new AssocBranch[1];
		else results = new AssocBranch[assocBranches.length + 1];
		if(assocBranches != null)
		{
		for(int i=0; i<assocBranches.length; i++)
		{
			results[i] = assocBranches[i];
		}
		}

		results[results.length - 1] = assocBranch;

		return results;
	}

  private AssocBranch[] deleteAssocBranch(AssocBranch[] assocBranches, int index)
  {
		if(assocBranches == null) return null;
		if(assocBranches.length <= 0) return assocBranches;

		AssocBranch[] results = new AssocBranch[assocBranches.length - 1];

		 int count = 0;
		for(int i=0; i<assocBranches.length; i++)
		{
			if(i == index) continue;
			results[count] = assocBranches[i];

			count ++;
		}

		return results;
	}

}
