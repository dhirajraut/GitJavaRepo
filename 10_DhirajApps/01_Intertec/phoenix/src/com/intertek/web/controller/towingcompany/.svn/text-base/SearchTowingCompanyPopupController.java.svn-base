package com.intertek.web.controller.towingcompany;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.PropertyResourceBundle;
import java.util.ResourceBundle;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.domain.StringSearchField;
import com.intertek.domain.TowingCompanySearch;
import com.intertek.entity.Branch;
import com.intertek.entity.DropDowns;
import com.intertek.entity.TowingCompany;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Page;
import com.intertek.pagination.Pagination;
import com.intertek.service.DropdownService;
import com.intertek.service.TowingCompanyService;
import com.intertek.service.UserService;
import com.intertek.util.Constants;

public class SearchTowingCompanyPopupController extends SimpleFormController {
	public SearchTowingCompanyPopupController() {
		super();
		setCommandClass(TowingCompanySearch.class);
		//setSessionForm(true);
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		int count = 0;
		int pages = 0;
		int num = 0;
		int pageNumber = 1;
		String pageNumberStr = request.getParameter("pageNumber");
		String searchForm = request.getParameter("searchForm");
		String sortFlag = request.getParameter("sortFlag");
		String inputFieldId = request.getParameter("inputFieldId");
			String txcel=request.getParameter("txcel");

		
		try {
			pageNumber = new Integer(pageNumberStr).intValue();
		} catch (Exception e) {
		}
		if (pageNumber <= 0)
			pageNumber = 1;

		TowingCompanySearch search = (TowingCompanySearch) command;
		Pagination pagination = new Pagination(1, 20, pageNumber,10);
		search.setPagination(pagination);
		
		search.setSearchForm(searchForm);
		
		// START : #119240 
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
		    	
		// END : #119240 
		
		
		/*TowingCompany towingCompany = new  TowingCompany();
		List results = new ArrayList();*/
		TowingCompanyService towingCompanyService = (TowingCompanyService)ServiceLocator.getInstance().getBean("towingCompanyService");
		DropdownService dropdownService = (DropdownService)ServiceLocator.getInstance().getBean("dropdownService");
		if(sortFlag != null && !sortFlag.trim().equals(""))
	    {
	    	/*List finalResults = new ArrayList();
	    	
	    	if(pageNumberStr.equals("1"))
	    	{*/
	    		try
		        {
	    			towingCompanyService.searchTowingCompany(search,sortFlag);
		        } catch(Throwable t)
				{
				  t.printStackTrace();
				  errors.reject("generic.error", new Object[] {t.getMessage()}, null);
				  return showForm(request, response, errors);
				}
		       /* catch(Exception e)
		        {
		          e.printStackTrace();
		
				    errors.reject("search.towingCompany.error", new Object[] {e.getMessage()}, null);
		          return showForm(request, response, errors);
		        }*/
	    	/*}
	    	results = search.getTotalResults();
	        results = getPaginationResults(results,search,pageNumberStr);
			search.setResults(results);*/
	        return showForm(request, response, errors);
       
	    }
	if((txcel!=null) && "true".equals(txcel))
	{ 
    List  tcs=new ArrayList();
	//int pageNumber=-1;
	search.setPagination(null);
	towingCompanyService.searchTowingCompany(search,sortFlag);
	tcs=search.getResults();
	if(tcs!=null&&tcs.size()!=0)
	{
	try
		{
		response.setContentType("application/download");
	 	response.setHeader("Content-Disposition", "attachment; filename=\"towingcompany.xls\"");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0");

		
		HSSFWorkbook workBook = new HSSFWorkbook();
		HSSFSheet main=null;
		
		main=workBook.createSheet("TowingCompany");
		HSSFRow   row ;
		HSSFCell  cell;
		HSSFRow headerRow;
		HSSFCell headerCell ;
		PropertyResourceBundle pRB = (PropertyResourceBundle) ResourceBundle.getBundle("TrackerRes");
		String path="";
			if(pRB!= null)
			{
				int  l=0;
				for(int k=0;k<=Constants.TCColumnSize;k++)
				{
				path=pRB.getString("TCHeading"+k);
				headerRow = main.createRow((short) 0); headerCell =   headerRow.createCell((short) l); headerCell.setCellValue(path);
				l++;
				}
			}


        int j=1;
		for(int i=0;i<tcs.size();i++)
		{ 
		row = main.createRow(j);cell = row.createCell((short) 0);cell.setCellValue(((TowingCompany)tcs.get(i)).getId());
		row = main.createRow(j);cell = row.createCell((short) 1);
		if(((TowingCompany)tcs.get(i)).getName()!=null && !((TowingCompany)tcs.get(i)).getName().trim().equals(""))
		{cell.setCellValue(((TowingCompany)tcs.get(i)).getName());}
		else{cell.setCellValue("");}
		
        row = main.createRow(j);cell = row.createCell((short) 2);
		if(((TowingCompany)tcs.get(i)).getStatus()!=null && !((TowingCompany)tcs.get(i)).getStatus().trim().equals(""))
		{
		String dropdowntype="activeStatus";
		DropDowns dropDown=null;
		dropDown=dropdownService.getDropdownByDropdownCodeAndType(((TowingCompany)tcs.get(i)).getStatus(),dropdowntype);
		if(dropDown!=null)
		{
		cell.setCellValue(dropDown.getFieldValue());
		}
		}
        else{cell.setCellValue("");}
		j++;
		}
		OutputStream out = response.getOutputStream(); 
		workBook.write(out);
		out.flush();
		out.close();
		} catch(ServiceException e)
		{
		  e.printStackTrace();
		  errors.reject(e.getMessage(), e.getParams(), null);
		  return showForm(request, response, errors);
		} catch(Throwable t)
		{
		  t.printStackTrace();
		  errors.reject("generic.error", new Object[] {t.getMessage()}, null);
		  return showForm(request, response, errors);
		}
		/*catch (Exception ioe) {
		System.out.println("Exception..." +  ioe.toString());
		}*/
	}
	search.setTxcel("false");
	        return showForm(request, response, errors);
	    }
		//search.setTotalResults(results);
		try
		    {
		      towingCompanyService.searchTowingCompany(search,sortFlag);
		    } catch(Throwable t)
			{
				  t.printStackTrace();
				  errors.reject("generic.error", new Object[] {t.getMessage()}, null);
				  return showForm(request, response, errors);
			}
		   /* catch(Exception e)
		    {
		      e.printStackTrace();

		      errors.reject("search.towingCompany.error", new Object[] {e.getMessage()}, null);
		      return showForm(request, response, errors);
		    }*/
	
		count=pagination.getTotalRecord();
		pages=pagination.getPagesToDisplay();
		num=pagination.getNumInPage();
		List pagesList=pagination.getPages();
		if(pagesList.size()>0)
		{
			Page page =(Page)pagesList.get(0);
			String pageName=page.getName();
			if(pageName.equals("Prev"))
			search.setPageName(pageName);
			else if(count <= (num*pages))
			search.setPageName(pageName);
			else
			search.setPageName(null);
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

	/* protected boolean suppressValidation(HttpServletRequest request)
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
		int pageNumber = 1;

		String inputFieldId = request.getParameter("inputFieldId");
		String towingCoName = request.getParameter("tcoName");
		String branchName=request.getParameter("branchName");
		String sortFlag ="";
		
		 UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");

		
		Branch branch=null;
		
		branch=userService.getBranchByName(branchName);
		
		TowingCompanyService towingCompanyService = (TowingCompanyService)ServiceLocator.getInstance().getBean("towingCompanyService");
		TowingCompanySearch towingCompanySearch = new TowingCompanySearch();
		Pagination pagination = new Pagination(1,20, pageNumber, 10);
		towingCompanySearch.setPagination(pagination);
		StringSearchField stringSearchField1 = new StringSearchField();
		StringSearchField stringSearchField2 = new StringSearchField();
		towingCompanySearch.setPageName(null);
		towingCompanySearch.setInputFieldId(inputFieldId);
		try {
		if((towingCoName!= null) && !"".equals(towingCoName.trim())) {
			stringSearchField1.setValue(towingCoName);
			towingCompanySearch.setName(stringSearchField1);
		    towingCompanyService.searchTowingCompany(towingCompanySearch,sortFlag);
		}
		if(branch != null)
		{
			stringSearchField2.setValue(branch.getCountryCode());
			towingCompanySearch.setCountry(stringSearchField2);
		    towingCompanyService.searchTowingCompany(towingCompanySearch,sortFlag);
		}
		} catch(ServiceException e)
	    {
	    	throw new ServiceException(e.getMessage(), e.getParams(), e);
	    }
	    catch(Throwable t)
	    {
	        t.printStackTrace();
	        throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
	    }
		return towingCompanySearch;
	}
private List getPaginationResults(List results, TowingCompanySearch search,String pageNumberStr){
		
    TowingCompany towingCompany = new  TowingCompany();
	  
	    List finalResults = new ArrayList();
	    
	    int pageNumber = 1;
		try {
			pageNumber = new Integer(pageNumberStr).intValue();
		} catch (Exception e) {
		}
		if (pageNumber <= 0)
			pageNumber = 1;
		
		Pagination towingCompanyPagination = new Pagination(1, 20, pageNumber,10);
    	search.setPagination(towingCompanyPagination);
    	if(towingCompanyPagination != null)
		{
		  if(results.size() > 0){
			  towingCompanyPagination.setTotalRecord(results.size());
		  }
		  towingCompanyPagination.calculatePages();
		}
    	int startRecord=Integer.valueOf(pageNumberStr).intValue();
    	int sortedResultsSize = results.size();
    	
    	if(startRecord == 1 )
    	{
    		if(sortedResultsSize >= Constants.RECORDS_PAGE)
    		{
		    	for(int i=0;i<Constants.RECORDS_PAGE;i++)
		    	{
		    		towingCompany = (TowingCompany) results.get(i);
		    		finalResults.add(towingCompany);
		    	}
    		}else
    		{
    			for(int i=0;i<sortedResultsSize;i++)
		    	{
    				towingCompany = (TowingCompany) results.get(i);
		    		finalResults.add(towingCompany);
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
	    			towingCompany = (TowingCompany) results.get(i);
		    		finalResults.add(towingCompany);
		    	}
    		}else
    		{
    			start=(startRecord -1)*Constants.RECORDS_PAGE;
    			end=sortedResultsSize;
    			for(int i=start;i<end;i++)
		    	{
    				towingCompany = (TowingCompany) results.get(i);
		    		finalResults.add(towingCompany);
		    	}
    		}
    	}
    	return finalResults;
	}
	/*
	Pagination towingCompanyPagination = new Pagination(1, 20, pageNumber,10);
	    	search.setPagination(towingCompanyPagination);
	    	if(towingCompanyPagination != null)
			{
			  if(results.size() > 0){
				  towingCompanyPagination.setTotalRecord(results.size());
			  }
			  towingCompanyPagination.calculatePages();
			}
	    	int startRecord=Integer.valueOf(pageNumberStr).intValue();
	    	int sortedResultsSize = results.size();
	    	
	    	if(startRecord == 1 )
	    	{
	    		if(sortedResultsSize >= Constants.RECORDS_PAGE)
	    		{
			    	for(int i=0;i<Constants.RECORDS_PAGE;i++)
			    	{
			    		towingCompany = (TowingCompany) results.get(i);
			    		finalResults.add(towingCompany);
			    	}
	    		}else
	    		{
	    			for(int i=0;i<sortedResultsSize;i++)
			    	{
	    				towingCompany = (TowingCompany) results.get(i);
			    		finalResults.add(towingCompany);
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
		    			towingCompany = (TowingCompany) results.get(i);
			    		finalResults.add(towingCompany);
			    	}
	    		}else
	    		{
	    			start=(startRecord -1)*Constants.RECORDS_PAGE;
	    			end=sortedResultsSize;
	    			for(int i=start;i<end;i++)
			    	{
	    				towingCompany = (TowingCompany) results.get(i);
			    		finalResults.add(towingCompany);
			    	}
	    		}
	    	}
	    	search.setResults(finalResults);
	    	request.setAttribute("command", search);*/
}
