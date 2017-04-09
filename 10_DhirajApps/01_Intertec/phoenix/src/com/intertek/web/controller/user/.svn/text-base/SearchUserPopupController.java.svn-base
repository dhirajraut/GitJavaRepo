package com.intertek.web.controller.user;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.intertek.exception.ServiceException;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.domain.UserSearch;
import com.intertek.entity.Country;
import com.intertek.entity.User;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.CountryService;
import com.intertek.service.UserService;
import com.intertek.util.Constants;

public class SearchUserPopupController extends SimpleFormController {
	public SearchUserPopupController() {
		super();
		setCommandClass(UserSearch.class);
		//setSessionForm(true);
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		String pageNumberStr = request.getParameter("pageNumber");
		String sortFlag = request.getParameter("sortFlag");
			String uxcel=request.getParameter("uxcel");
		int pageNumber = 1;
		try {
			pageNumber = new Integer(pageNumberStr).intValue();
		} catch (Exception e) {
		}
		if (pageNumber <= 0)
			pageNumber = 1;

		UserSearch search = (UserSearch) command;
		Pagination pagination = new Pagination(1, 20, pageNumber, 10);
		search.setPagination(pagination);
		//search.setSearchForm(searchForm);

		// START : #119240 : 22/06/09
		String pageSort = request.getParameter("checkPageSort");
	        
        boolean changeSortOrder = false; 
        if(pageNumberStr != null && Integer.valueOf(pageNumberStr).intValue() == 1){
        	if(sortFlag != null && !sortFlag.trim().equals("") && pageSort.equals("")){
        		changeSortOrder = true;
        	}else if(sortFlag == null || sortFlag.trim().equals("")){
        		  search.setSortOrderFlag("");
	  			  search.setCurrentSortFlag("");
	  			  search.setPrevSortFlag("");
        	}
        }
		
		if(pageNumberStr != null && Integer.valueOf(pageNumberStr).intValue() == 1 && changeSortOrder){
			  
			  if(sortFlag != null && !sortFlag.trim().equals("")){
				  search.setCurrentSortFlag(sortFlag);
				  if(search.getCurrentSortFlag()!= null && search.getCurrentSortFlag().trim().equals(search.getPrevSortFlag())){
					  search.setSortOrderFlag("desc");
					  search.setPrevSortFlag("");
				  } else {
					  search.setPrevSortFlag(sortFlag);
					  search.setSortOrderFlag("asc");
				  }
			  } else {
				  search.setSortOrderFlag("");
				  search.setCurrentSortFlag("");
				  search.setPrevSortFlag("");
			  }
		 }
		System.out.println("sortFlag " + sortFlag + " : " + search.getSortOrderFlag());    	
		// END : #119240 : 22/06/09
		
		UserService userService = (UserService) ServiceLocator.getInstance().getBean("userService");
		CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");
		User user = new User();
	    List results = new ArrayList();
	    if(sortFlag != null && !sortFlag.trim().equals(""))
	    {
	    	List finalResults = new ArrayList();
	    	
	    	//if(pageNumberStr.equals("1"))
	    	//{
	    		try
		        {
	    			userService.searchUser(search,sortFlag);
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
		       /* catch(Exception e)
		        {
		        	e.printStackTrace();
					errors.reject("search.user.error", new Object[] { e.getMessage() }, null);
					return showForm(request, response, errors);
		        }*/
	    	/*}
	    	results = search.getTotalResults();
	        results = getPaginationResults(results,search,pageNumberStr);
			search.setResults(results);*/
	        return showForm(request, response, errors);
	    }
	    

    if((uxcel!=null) && "true".equals(uxcel))
	{ 
    List  users=new ArrayList();
	search.setPagination(null);	
	userService.searchUser(search,sortFlag);
	users=search.getResults();
	System.out.println("size of the users is "+ users.size());
	if(users!=null&&users.size()!=0)
	{
	try
		{
		response.setContentType("application/download");
	 	response.setHeader("Content-Disposition", "attachment; filename=\"users.xls\"");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0");

		
		HSSFWorkbook workBook = new HSSFWorkbook();
		HSSFSheet main=null;
		
		main=workBook.createSheet("User");
		HSSFRow   row ;
		HSSFCell  cell;
		HSSFRow headerRow;
		HSSFCell headerCell ;
		PropertyResourceBundle pRB = (PropertyResourceBundle) ResourceBundle.getBundle("TrackerRes");
		String path="";
			if(pRB!= null)
			{
				int  l=0;
				for(int k=0;k<=Constants.UColumnSize;k++)
				{
				path=pRB.getString("UHeading"+k);
				headerRow = main.createRow((short) 0); headerCell =   headerRow.createCell((short) l); headerCell.setCellValue(path);
				l++;
				}
			}


        int j=1;
		for(int i=0;i<users.size();i++)
		{ 
		row = main.createRow(j);cell = row.createCell((short) 0);cell.setCellValue(((User)users.get(i)).getLoginName());
		row = main.createRow(j);cell = row.createCell((short) 1);
		if(((User)users.get(i)).getFirstName()!=null && !((User)users.get(i)).getFirstName().trim().equals(""))
		{cell.setCellValue(((User)users.get(i)).getFirstName());}
		else{cell.setCellValue("");}
		
        row = main.createRow(j);cell = row.createCell((short) 2);
		if(((User)users.get(i)).getLastName()!=null && !((User)users.get(i)).getLastName().trim().equals(""))
		{cell.setCellValue(((User)users.get(i)).getLastName());}
		else{cell.setCellValue("");}

		row = main.createRow(j);cell = row.createCell((short) 3);
        if(((User)users.get(i)).getCountryCode()!=null && !((User)users.get(i)).getCountryCode().trim().equals(""))
		{
			Country country=null;
			country=countryService.getCountryByCode(((User)users.get(i)).getCountryCode());
			if(country!=null)
			{cell.setCellValue(country.getName());}}
		else{cell.setCellValue("");}

		j++;
		}
		
		OutputStream out = response.getOutputStream(); 
		workBook.write(out);
		out.flush();
		out.close();
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
	}
	search.setUxcel("false");
	        return showForm(request, response, errors);
	    }
	   // search.setTotalResults(results);
		try {
			/*For iTrack135193-START */ 
			String searchForm = search.getSearchForm();
			if((searchForm!=null)&&(searchForm.equals("editCustomer")
			    		||searchForm.equals("createCustomer")||searchForm.equals("quickCreate")))
			{  
   			String name = search.getName().getValue();
   			String firstName = search.getFirstName().getValue();
   			String lastName = search.getLastName().getValue();
   			if((name == null || name.equals(""))&& (firstName == null || firstName.equals(""))
   			    	&& (lastName == null) || lastName.equals("")){
   				search.setPagination(null);
   			    }
			}
			/*For iTrack135193-END */ 
			userService.searchUser(search,sortFlag);
		}
		/*catch (Exception e) {
			e.printStackTrace();
			errors.reject("search.user.error", new Object[] { e.getMessage() }, null);
			return showForm(request, response, errors);
		}*/
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

		request.setAttribute("command", search);

		return showForm(request, response, errors);
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest, org.springframework.web.bind.ServletRequestDataBinder)
	 */
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
	}

	/*  protected boolean suppressValidation(HttpServletRequest request)
	 {
	 String searchType = request.getParameter("searchType");
	 if((searchType != null) && ("opsBranch".equals(searchType) || "labBranch".equals(searchType) || searchType.startsWith("assoc")))
	 {
	 return true;
	 }

	 return super.suppressValidation(request);
	 }	*/

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		String inputFieldId = request.getParameter("inputFieldId");
		String div1 = request.getParameter("div1");
		String div2 = request.getParameter("div2");
		String searchForm=request.getParameter("searchForm");

		UserSearch userSearch = new UserSearch();
		userSearch.setInputFieldId(inputFieldId);
		userSearch.setSearchForm(searchForm);
		userSearch.setDiv1(div1);
		userSearch.setDiv2(div2);

		return userSearch;
	}
private List getPaginationResults(List results,UserSearch search,String pageNumberStr){
		
		User user=new User();
	  
	    List finalResults = new ArrayList();
	    
	    int pageNumber = 1;
		try {
			pageNumber = new Integer(pageNumberStr).intValue();
		} catch (Exception e) {
		}
		if (pageNumber <= 0)
			pageNumber = 1;
		
		Pagination userPagination = new Pagination(1, 20, pageNumber,10);
    	
    	search.setPagination(userPagination);
    	if(userPagination != null)
		{
		  if(results.size() > 0){
			  userPagination.setTotalRecord(results.size());
		  }
		  userPagination.calculatePages();
		}
    	int startRecord=Integer.valueOf(pageNumberStr).intValue();
    	int sortedResultsSize = results.size();
    	
    	if(startRecord == 1 )
    	{
    		if(sortedResultsSize >= Constants.RECORDS_PAGE)
    		{
		    	for(int i=0;i<Constants.RECORDS_PAGE;i++)
		    	{
		    		user = (User) results.get(i);
		    		finalResults.add(user);
		    	}
    		}else
    		{
    			for(int i=0;i<sortedResultsSize;i++)
		    	{
    				user = (User) results.get(i);
		    		finalResults.add(user);
		    	}
    		}
    	}else
    	{
    		
    		int end=startRecord * Constants.RECORDS_PAGE;
    		int start=end - Constants.RECORDS_PAGE;
    		if(end<=sortedResultsSize)
    		{
	    		for(int i=start;i<end;i++)
		    	{
	    			user = (User) results.get(i);
		    		finalResults.add(user);
		    	}
    		}else
    		{
    			start=(startRecord -1)*Constants.RECORDS_PAGE;
    			end=sortedResultsSize;
    			for(int i=start;i<end;i++)
		    	{
    				user = (User) results.get(i);
		    		finalResults.add(user);
		    	}
    		}
    		
    	}
    	return finalResults;
	}

/*
Pagination userPagination = new Pagination(1, 20, pageNumber,10);
	    	
	    	search.setPagination(userPagination);
	    	if(userPagination != null)
			{
			  if(results.size() > 0){
				  userPagination.setTotalRecord(results.size());
			  }
			  userPagination.calculatePages();
			}
	    	int startRecord=Integer.valueOf(pageNumberStr).intValue();
	    	int sortedResultsSize = results.size();
	    	
	    	if(startRecord == 1 )
	    	{
	    		if(sortedResultsSize >= Constants.RECORDS_PAGE)
	    		{
			    	for(int i=0;i<Constants.RECORDS_PAGE;i++)
			    	{
			    		user = (User) results.get(i);
			    		finalResults.add(user);
			    	}
	    		}else
	    		{
	    			for(int i=0;i<sortedResultsSize;i++)
			    	{
	    				user = (User) results.get(i);
			    		finalResults.add(user);
			    	}
	    		}
	    	}else
	    	{
	    		
	    		int end=startRecord * Constants.RECORDS_PAGE;
	    		int start=end - Constants.RECORDS_PAGE;
	    		if(end<=sortedResultsSize)
	    		{
		    		for(int i=start;i<end;i++)
			    	{
		    			user = (User) results.get(i);
			    		finalResults.add(user);
			    	}
	    		}else
	    		{
	    			start=(startRecord -1)*Constants.RECORDS_PAGE;
	    			end=sortedResultsSize;
	    			for(int i=start;i<end;i++)
			    	{
	    				user = (User) results.get(i);
			    		finalResults.add(user);
			    	}
	    		}
	    	}
	    	search.setResults(finalResults);
	    	request.setAttribute("command", search);*/
}
