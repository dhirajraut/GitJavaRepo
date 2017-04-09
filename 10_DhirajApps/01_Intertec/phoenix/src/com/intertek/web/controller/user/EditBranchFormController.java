package com.intertek.web.controller.user;

import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
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
import com.intertek.domain.BranchSearch;
import com.intertek.domain.CountrySearch;
import com.intertek.entity.AssocBranch;
import com.intertek.entity.AssocBranchId;
import com.intertek.entity.Branch;
import com.intertek.entity.BusinessUnit;
import com.intertek.entity.Country;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.CountryService;
import com.intertek.service.UserService;
import com.intertek.util.Constants;
import com.intertek.util.CountryUtil;
import com.intertek.web.controller.BaseSimpleFormController;

public class EditBranchFormController extends BaseSimpleFormController
//public class EditBranchFormController extends SimpleFormController
{
	 BusinessUnit bu = null;
	 public EditBranchFormController() {
	 super();
	 setCommandClass(AddBranch.class);
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
    AddBranch addBranch = (AddBranch) command;
	Branch branch = addBranch.getBranch();

	AssocBranch[] assocBranchItems = addBranch.getAssocBranches();

    UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
    CountryService countryService = (CountryService) ServiceLocator
	.getInstance().getBean("countryService");


   String addOrDelete = request.getParameter("addOrDelete");
   String rowNum = addBranch.getRowNum();
   String assocBranchFlag = addBranch.getBranchTypeFlag();
   System.out.println("assocBranch Flag :"+assocBranchFlag);
   if(assocBranchFlag != null && (!assocBranchFlag.trim().equals("")) && (!assocBranchFlag.trim().equals("none")))
   {
	   System.out.println("rowNum :"+rowNum);
	   if(assocBranchFlag.trim().equals("addAssocBranch"))
	   {
		   if(rowNum != null && (!rowNum.trim().equals("")) && (!rowNum.trim().equalsIgnoreCase("none")))
		   {
			   AssocBranch assocBranch = addBranch.getAssocBranches()[Integer.parseInt(rowNum)];
			  // Branch assocBranchRef = userService.getBranchByName(assocBranch.getAssocBranchId().getAssocBranchName());
			   Branch assocBranchRef=null;
			   try 
    	       { 
        	    assocBranchRef = userService.getBranchByName(assocBranch.getAssocBranchId().getAssocBranchName());
    	       } 
				catch(ServiceException e)
				{
				  addBranch.setRowNum("none");
				  addBranch.setBranchTypeFlag("none");
				  e.printStackTrace();
				  errors.reject(e.getMessage(), e.getParams(), null);
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
			  /* if(assocBranchRef == null)
			   {
					  errors.reject("create.customer.error", new Object[] {"Invalid Assoc Branch"}, null);
					  addBranch.setRowNum("none");
					  addBranch.setBranchTypeFlag("none");
					  return showForm(request, response, errors);			  
			   }*/
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
	   // START : #119240 : 02/07/09
	   if(assocBranchFlag.trim().equals("Vat"))
	   {
		   addBranch.setRowNum("none");
		   addBranch.setBranchTypeFlag("none");
		   return showForm(request, response, errors);   
	   }
	   // END : #119240 : 02/07/09
	   
   }

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
    Set assocBranchSet = new HashSet();
	branch.getAssocBranch().clear();

	if (assocBranchItems != null )
	{
		for(int i=0; i< assocBranchItems.length; i++)
		{
			AssocBranch assocBranch = assocBranchItems[i];
			assocBranchSet.add(assocBranch);
		}
		branch.setAssocBranch(assocBranchSet);
	}
	if(branch.getStateCode()== null || branch.getStateCode().equals(""))
	{
    	country = countryService.getCountryByCode(branch.getCountryCode());
    	Set s =country.getStates();
    	
    	if(s.size()!=0)
    	{
			errors.reject("select.state.error",new Object[] {branch.getCountryCode() }, null);
			return showForm(request, response, errors);
    	}
	}
    
    try
    {
		  userService.saveBranch(branch);
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
		
		/* ModelAndView modelAndView = new ModelAndView("common-message-r");
	    modelAndView.addObject("backUrl", "edit_branch.htm?branch.name=" + branch.getName());
	    modelAndView.addObject("backUrlDescription", "You can continue to edit this Branch.");
	    modelAndView.addObject("description", "Your Branch has been successfully updated.");*/
		
		
		String operationType = request.getParameter("operationType");
		String name = null;
		ModelAndView modelAndView = null;
		if(operationType!=null && !operationType.equals("") && ("prevBranch".equals(operationType)
				|| "nextBranch".equals(operationType)
			    || "searchBranch".equals(operationType) 
			    || "saveBranch".equals(operationType))   )
		{
			String viewName = null;
			if("searchBranch".equals(operationType))
		    {
				viewName = "search-branch-r";
		    }
			
		    if(viewName == null)
		    {
		    	viewName = "edit-branch-r"; 
		    }

		    modelAndView = new ModelAndView(viewName);
			
		    if ("prevBranch".equals(operationType) || "nextBranch".equals(operationType)) 
		    {
				name = this.getPrevOrNextBranch(branch.getName(), addBranch.getBranchSearch(),
						"prevBranch".equals(operationType));
				if (name == null) {
					if ("prevBranch".equals(operationType)) {
						modelAndView.addObject("saved_message",	"No Previous Branch!");
					} else {
						modelAndView.addObject("saved_message",	"No Next Branch!");
					}
				}
			}
		    
		    if("saveBranch".equals(operationType))
			{
		    	modelAndView.addObject("saved_message", "Saved successfully");		    	
			}		    
		
		
		 if(name == null) 
			 name = branch.getName();
		 modelAndView.addObject("branch.name", name);  
		 modelAndView.addObject("fromEdit", "true");
		 
	//	 System.out.println("Session : branchSearch :---- " + request.getSession().getAttribute("branchSearch"));
	    
	// END : #119240 : 29/06/09
	    
	//    System.out.println(" SEARCH FORM NAME :  FB :-----------  " + addBranch.getSearchForm());
	    
	    return modelAndView;
	 }		
		return showForm(request, response, errors);
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
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
   */
  protected Object formBackingObject(
    HttpServletRequest request
  ) throws Exception
  {
		AddBranch addBranch = new AddBranch();

		String branchName = request.getParameter("branch.name");

		String countStr = request.getParameter("assocBranchCount");
		if( countStr == null || countStr.trim().equals("") ) countStr = "0";

		int count = 0;
		try
		{
			count = new Integer(countStr).intValue();
		}
		catch(Exception e) { }

		//Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");

		addBranch.setAssocBranchCount(count);


	  Branch branch = null;
	  try
	  {
			branch = userService.getBranchByName(branchName);
			System.out.println("assoc branch size :"+branch.getAssocBranch().size());
			
			if(branch !=null && branch.getAssocBranch() != null && branch.getAssocBranch().size()>0)
			{
				AssocBranch[] assocBranch = new AssocBranch[branch.getAssocBranch().size()];
				int counter = 0;
				Iterator iter = branch.getAssocBranch().iterator();
				while(iter.hasNext())
				{
					AssocBranch assocBranchItem = (AssocBranch) iter.next();
					Branch assocBranchRef = userService.getBranchByName(assocBranchItem.getAssocBranchId().getAssocBranchName());
					assocBranchItem.setAssocBranch(assocBranchRef);
					assocBranch[counter] = assocBranchItem;
					if(assocBranch[counter].getAssocBranch().getType().equalsIgnoreCase("OPS"))
						assocBranch[counter].getAssocBranch().setType(Constants.OPS_BRANCH_TYPE);
					if(assocBranch[counter].getAssocBranch().getType().equalsIgnoreCase("LAB"))
						assocBranch[counter].getAssocBranch().setType(Constants.LAB_BRANCH_TYPE);
					if(assocBranch[counter].getAssocBranch().getType().equalsIgnoreCase("OPSL"))
						assocBranch[counter].getAssocBranch().setType(Constants.OPSLAB_BRANCH_TYPE);
					if(assocBranch[counter].getAssocBranch().getType().equalsIgnoreCase("ADMN"))
						assocBranch[counter].getAssocBranch().setType(Constants.ADMIN_BRANCH_TYPE);
					if(assocBranch[counter].getAssocBranch().getType().equalsIgnoreCase("OPSC"))
						assocBranch[counter].getAssocBranch().setType(Constants.OPSCENTER_BRANCH_TYPE);
					
					counter++;
				}
				addBranch.setAssocBranches(assocBranch);

			}
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

		System.out.println("========== branch = " + branch);
    if(branch == null)
    {
			// should handle this since branch has to exist in order to edit.
			branch = new Branch();

		  //BusinessUnit bu = new BusinessUnit();
		  //branch.setBusinessUnit(bu);
		}

		addBranch.setBranch(branch);

		// START : #119240 : 29/06/09
		BranchSearch branchSearch = null;
	    HttpSession session = request.getSession();
	    if(session != null)
	    {
	    	if(session.getAttribute("branchSearch")!=null){
	    	//	System.out.println("Session : branchSearch :--- "+ session.getAttribute("branchSearch"));
	    		branchSearch = (BranchSearch)session.getAttribute("branchSearch");
			}
	    }
	    addBranch.setBranchSearch(branchSearch);
	//	System.out.println("Session : branchSearch : FB :---- " + session.getAttribute("branchSearch"));
	
		// END : #119240 : 29/06/09

//	  System.out.println(" SEARCH FORM NAME :  FB :-----------  " + addBranch.getSearchForm());
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
  
  
  
//START : #119240 : 29/06/09
  public String  getPrevOrNextBranch(String currentName, BranchSearch branchSearch, boolean prev){
	  
	  if(currentName == null || branchSearch == null) 
		  return null;
	  
	  UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
	  List<Branch> results =  branchSearch.getResults();
	  
	  if(results == null) return null;

	    for(int i=0; i<results.size(); i++)
	    {
	    	Branch branch = (Branch)results.get(i);
	    	if(branch.getName().equals(currentName))
	    	{
	    		if(prev)
	    		{
	    			if(i == 0)
	    			{
	    				  int currentPageNum = branchSearch.getPagination().getCurrentPageNum();
	    		            if(currentPageNum > 1)
	    		            {
	    		            	branchSearch.getPagination().setCurrentPageNum(currentPageNum - 1);
	    		            	userService.searchBranch(branchSearch,branchSearch.getSortOrderFlag());

		   		              	List newResults = branchSearch.getResults();
		   		              	if(newResults == null) return null;
	
		   		              	if(newResults.size() == 0) return null;
	
		    		            return ((Branch)newResults.get(newResults.size() - 1)).getName();
	    		            }
	    		            else
	    		            {
	    		              return null;
	    		            }
	    			}
	    			return ((Branch)results.get(i-1)).getName();
	    		}else{
	    			
	    			if(i == results.size() - 1)
	    			{
	   				  	int totalPages = branchSearch.getPagination().getTotalPages();
	    		        int currentPageNum = branchSearch.getPagination().getCurrentPageNum();
	    		        if(currentPageNum < totalPages)
	    		        {
	    		        	branchSearch.getPagination().setCurrentPageNum(currentPageNum + 1);
	    		        	userService.searchBranch(branchSearch,branchSearch.getSortOrderFlag());
	    		        	List newResults = branchSearch.getResults();
	    		        	if(newResults == null) return null;
	
    		              	if(newResults.size() == 0) return null;

    		              	return ((Branch)newResults.get(0)).getName();
	    		        }else
	    		        {
	    		        	return null;
	    		        }
	    			}
	    			
	    			return ((Branch)results.get(i+1)).getName();	    			
	    		}
	    	}	    	
	    }
	  
	  return null;
  }
// END : #119240 : 29/06/09

}
