package com.intertek.web.controller.user;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import org.springframework.web.servlet.view.RedirectView;

import com.intertek.domain.BranchSearch;
import com.intertek.entity.Branch;
import com.intertek.entity.DropDowns;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.DropdownService;
import com.intertek.service.UserService;
import com.intertek.util.Constants;

public class SearchBranchFormController extends SimpleFormController
{
  public SearchBranchFormController() {
    super();
    setCommandClass(BranchSearch.class);
    // START : #119240 : 29/06/09
    //setSessionForm(true);
    setSessionForm(true);
    // END : #119240 : 29/06/09
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
    BranchSearch search = (BranchSearch)command;

    UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
	DropdownService dropdownService = (DropdownService)ServiceLocator.getInstance().getBean("dropdownService");

    Branch branch = new Branch();
    int pageNumber = 1;
   

    String pageNumberStr = request.getParameter("pageNo");
    String sortFlag = request.getParameter("sortFlag");
	String bxcel=request.getParameter("bxcel");
	String submitFlag=request.getParameter("submitFlag");
	
	/*List results = new ArrayList();
	List finalResults = new ArrayList();*/

	 try
    {
      pageNumber = new Integer(pageNumberStr).intValue();
    }
    catch(Exception e)
    {
    }
    if(pageNumber <= 0) pageNumber = 1;


    Pagination pagination = new Pagination(1,20, pageNumber, 10);
   // search.setPagination(null);
    search.setPagination(pagination);
  
    // START : #119240 : 19/06/09
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
	// END : #119240 : 19/06/09
    
if(sortFlag != null && !sortFlag.trim().equals(""))
	{	  
	//if(pageNumberStr.equals("1")) 
	//{
			try
				{
				userService.searchBranch(search,sortFlag);
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
		
			/*}

			results = search.getTotalResults();
	    	results = getPaginationResults(results,search,pageNumberStr);
			search.setResults(results);	 */      
	        return showForm(request, response, errors);
	    } 
		
		if((submitFlag!=null) && "true".equals(submitFlag))
	    { 
		/*userService.searchBranch(search,sortFlag);
		results = search.getResults();
		search.setSubmitFlag("none");
		search.setTotalResults(results);
		results = search.getTotalResults();	
		results = getPaginationResults(results,search,pageNumberStr);
		search.setResults(results);  */
			search.setSubmitFlag("none");
			
			try
			{
			userService.searchBranch(search,sortFlag);
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
   
   if((bxcel!=null) && "true".equals(bxcel))
	{ 
    List  branches=new ArrayList();
	search.setPagination(null);
	try
	{
	userService.searchBranch(search,sortFlag);
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
	/*catch(Exception e)
	{
	e.printStackTrace();
	errors.reject("search.job.error", new Object[] {e.getMessage()}, null);
	return showForm(request, response, errors);
	}*/
	//branches=search.getTotalResults();
	branches=search.getResults();
	if(branches!=null&&branches.size()!=0)
	{
	try
		{
		response.setContentType("application/download");
	 	response.setHeader("Content-Disposition", "attachment; filename=\"branches.xls\"");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0");

		
		HSSFWorkbook workBook = new HSSFWorkbook();
		HSSFSheet main=null;
		
		main=workBook.createSheet("Branche");
		HSSFRow   row ;
		HSSFCell  cell;
		HSSFRow headerRow;
		HSSFCell headerCell ;
		PropertyResourceBundle pRB = (PropertyResourceBundle) ResourceBundle.getBundle("TrackerRes");
		String path="";
			if(pRB!= null)
			{
				int  l=0;
				for(int k=0;k<=Constants.BrColumnSize;k++)
				{
				path=pRB.getString("BrHeading"+k);
				headerRow = main.createRow((short) 0); headerCell =   headerRow.createCell((short) l); headerCell.setCellValue(path);
				l++;
				}
			}


        int j=1;
		for(int i=0;i<branches.size();i++)
		{ 
		row = main.createRow(j);cell = row.createCell((short) 0);cell.setCellValue(((Branch)branches.get(i)).getBusinessUnit().getName());
		row = main.createRow(j);cell = row.createCell((short) 1);cell.setCellValue(((Branch)branches.get(i)).getName());
        row = main.createRow(j);cell = row.createCell((short) 2);
		if(((Branch)branches.get(i)).getDescription()!=null && !((Branch)branches.get(i)).getDescription().trim().equals(""))
		{cell.setCellValue(((Branch)branches.get(i)).getDescription());}
		else{cell.setCellValue("");}

		String dropdowntype="activeStatus";
		DropDowns dropDown=null;
		dropDown=dropdownService.getDropdownByDropdownCodeAndType(((Branch)branches.get(i)).getStatus(),dropdowntype);
		if(dropDown!=null)
		{
		row = main.createRow(j);cell = row.createCell((short) 3);cell.setCellValue(dropDown.getFieldValue());}
        else{cell.setCellValue("");}
		
		row = main.createRow(j);cell = row.createCell((short) 4);
		if(((Branch)branches.get(i)).getCountryCode()!=null && !((Branch)branches.get(i)).getCountryCode().trim().equals(""))
		{cell.setCellValue(((Branch)branches.get(i)).getCountryCode());}
		else{cell.setCellValue("");}
			
		row = main.createRow(j);cell = row.createCell((short) 5);
		if(((Branch)branches.get(i)).getStateCode()!=null && !((Branch)branches.get(i)).getStateCode().trim().equals(""))
		{cell.setCellValue(((Branch)branches.get(i)).getStateCode());}
		else{cell.setCellValue("");}
				
		row = main.createRow(j);cell = row.createCell((short) 6);
		if(((Branch)branches.get(i)).getCity()!=null && !((Branch)branches.get(i)).getCity().trim().equals(""))
		{cell.setCellValue(((Branch)branches.get(i)).getCity());}
		else{cell.setCellValue("");}
		//96509 start
		row = main.createRow(j);cell = row.createCell((short) 7);
		if(((Branch)branches.get(i)).getAddress1()!=null && !((Branch)branches.get(i)).getAddress1().trim().equals(""))
		{cell.setCellValue(((Branch)branches.get(i)).getAddress1());}
		else{cell.setCellValue("");}
		//96509 end
		j++;
		}
		
		OutputStream out = response.getOutputStream(); 
		workBook.write(out);
		out.flush();
		out.close();
		}
		/*catch (Exception ioe) {
		System.out.println("Exception..." +  ioe.toString());
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
	}
	search.setBxcel("false");
	return showForm(request, response, errors);
	}


    request.setAttribute("command", search);
    
    // START : #119240 : 29/06/09 
    HttpSession session = request.getSession();
    session.setAttribute("branchSearch", search);
    
    // START for Itrack note : 27-Jul-2009
    List<Branch> myResults = search.getResults();
    if(myResults != null && myResults.size() == 1){
    	Branch b = (Branch)myResults.get(0);
    	return new ModelAndView(new RedirectView("edit_branch.htm?branch.name="+ b.getName()));
    }
    // END for Itrack note : 27-Jul-2009
    
    // END : #119240 : 29/06/09
    return showForm(request, response, errors);
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest, org.springframework.web.bind.ServletRequestDataBinder)
   */
  protected void initBinder(
    HttpServletRequest request,
    ServletRequestDataBinder binder
  ) throws Exception
  {
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
   */
  protected Object formBackingObject(
    HttpServletRequest request
  ) throws Exception
  {
	  BranchSearch BranchSearch = new BranchSearch();
	 
	// START : #119240 : 29/06/09
	  UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
	  HttpSession session = request.getSession();
	  String fromEdit = request.getParameter("fromEdit");
	 
	  if (fromEdit == null) {
		session.removeAttribute("branchSearch");
	  }

	  if(session != null) {
		if(session.getAttribute("branchSearch") != null) {
	
			BranchSearch = (BranchSearch) session.getAttribute("branchSearch");
			// START for Itrack note : 27-Jul-2009
			// userService.searchBranch(BranchSearch, BranchSearch.getSortOrderFlag());
			if(BranchSearch.getResults()!=null && BranchSearch.getResults().size() > 1){
				userService.searchBranch(BranchSearch, BranchSearch.getSortOrderFlag());
  			}else{
  				BranchSearch.setResults(null);
  			}
			// END for Itrack note : 27-Jul-2009
		}	
	}	
	  
	// END : #119240 : 29/06/09
	  
	BranchSearch.setSearchForm("branchForm"); 
    return BranchSearch;
  }
private List getPaginationResults(List results,BranchSearch search,String pageNumberStr){

	    Branch branch =new Branch();
	  
	    List finalResults = new ArrayList();
	    
	    int pageNumber = 1;
		try {
			pageNumber = new Integer(pageNumberStr).intValue();
		} catch (Exception e) {
		}
		if (pageNumber <= 0)
			pageNumber = 1;
		
	Pagination barnchPagination = new Pagination(1, 20, pageNumber,10);
   	  	search.setPagination(barnchPagination);
   	if(barnchPagination != null)
		{
		  if(results.size() > 0){
			  barnchPagination.setTotalRecord(results.size());
		  }
		  barnchPagination.calculatePages();
		}
   	int startRecord=Integer.valueOf(pageNumberStr).intValue();
   	int sortedResultsSize = results.size();
   	
   	if(startRecord == 1 )
   	{
   		if(sortedResultsSize >= Constants.RECORDS_PAGE)
   		{
		    	for(int i=0;i<Constants.RECORDS_PAGE;i++)
		    	{
		    		branch = (Branch) results.get(i);
		    		finalResults.add(branch);
		    	}
   		}else
   		{
   			for(int i=0;i<sortedResultsSize;i++)
		    	{
   				branch = (Branch) results.get(i);
		    		finalResults.add(branch);
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
	    			branch = (Branch) results.get(i);
		    		finalResults.add(branch);
		    	}
   		}else
   		{
   			start=(startRecord -1)*Constants.RECORDS_PAGE;
   			end=sortedResultsSize;
   			for(int i=start;i<end;i++)
		    	{
   				branch = (Branch) results.get(i);
		    		finalResults.add(branch);
		    	}
   		}
   	}
    	return finalResults;
	}
}
