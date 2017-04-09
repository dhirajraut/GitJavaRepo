package com.intertek.web.controller.shippingagent;

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

import com.intertek.domain.ShippingAgentSearch;
import com.intertek.domain.StringSearchField;
import com.intertek.entity.Branch;
import com.intertek.entity.DropDowns;
import com.intertek.entity.ShippingAgent;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Page;
import com.intertek.pagination.Pagination;
import com.intertek.service.DropdownService;
import com.intertek.service.ShippingAgentService;
import com.intertek.service.UserService;
import com.intertek.util.Constants;

public class SearchShippingAgentPopupController extends SimpleFormController {
	public SearchShippingAgentPopupController() {
		super();
		setCommandClass(ShippingAgentSearch.class);
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
		String pageNumberStr = request.getParameter("pageNumber");
		String sortFlag = request.getParameter("sortFlag");
		String searchForm = request.getParameter("searchForm");
		String inputFieldId = request.getParameter("inputFieldId");
		String sxcel=request.getParameter("sxcel");
		
		int pageNumber = 1;
		try {
			pageNumber = new Integer(pageNumberStr).intValue();
		} catch (Exception e) {
		}
		if (pageNumber <= 0)
			pageNumber = 1;

		ShippingAgentSearch search = (ShippingAgentSearch) command;
		Pagination pagination = new Pagination(1, 20, pageNumber, 10);
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
		
		ShippingAgentService shippingAgentService = (ShippingAgentService)ServiceLocator.getInstance().getBean("shippingAgentService");
		DropdownService dropdownService = (DropdownService)ServiceLocator.getInstance().getBean("dropdownService");
		/*ShippingAgent shippingAgent = new  ShippingAgent();
		List results = new ArrayList();*/
		if(sortFlag != null && !sortFlag.trim().equals(""))
	    {
	    	/*List finalResults = new ArrayList();
	    	
	    	if(pageNumberStr.equals("1"))
	    	{*/
	    		try
		        {
	    			shippingAgentService.searchShippingAgent(search,sortFlag);
		        } catch(Throwable t)
				{
				  t.printStackTrace();
				  errors.reject("generic.error", new Object[] {t.getMessage()}, null);
				  return showForm(request, response, errors);
				}
		       /* catch(Exception e)
		        {
		          e.printStackTrace();
		
					errors.reject("search.shippingAgent.error", new Object[] {e.getMessage()}, null);
		          return showForm(request, response, errors);
		        }*/
	    	/*}
	    	results = search.getTotalResults();
	        results = getPaginationResults(results,search,pageNumberStr);
			search.setResults(results);*/
	        return showForm(request, response, errors);
	    	
	    }

  if((sxcel!=null) && "true".equals(sxcel))
	{ 
    List  ships=new ArrayList();
	//int pageNumber=-1;
	search.setPagination(null);
	shippingAgentService.searchShippingAgent(search,sortFlag);
	ships=search.getResults();
	if(ships!=null&&ships.size()!=0)
	{
	try
		{
		response.setContentType("application/download");
	 	response.setHeader("Content-Disposition", "attachment; filename=\"shippingagent.xls\"");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0");

		
		HSSFWorkbook workBook = new HSSFWorkbook();
		HSSFSheet main=null;
		
		main=workBook.createSheet("Shipping");
		HSSFRow   row ;
		HSSFCell  cell;
		HSSFRow headerRow;
		HSSFCell headerCell ;
		PropertyResourceBundle pRB = (PropertyResourceBundle) ResourceBundle.getBundle("TrackerRes");
		String path="";
			if(pRB!= null)
			{
				int  l=0;
				for(int k=0;k<=Constants.SColumnSize;k++)
				{
				path=pRB.getString("SHeading"+k);
				headerRow = main.createRow((short) 0); headerCell =   headerRow.createCell((short) l); headerCell.setCellValue(path);
				l++;
				}
			}


        int j=1;
		for(int i=0;i<ships.size();i++)
		{ 
		row = main.createRow(j);cell = row.createCell((short) 0);cell.setCellValue(((ShippingAgent)ships.get(i)).getId());
		row = main.createRow(j);cell = row.createCell((short) 1);
		if(((ShippingAgent)ships.get(i)).getName()!=null && !((ShippingAgent)ships.get(i)).getName().trim().equals(""))
		{cell.setCellValue(((ShippingAgent)ships.get(i)).getName());}
		else{cell.setCellValue("");}
        String dropdowntype="activeStatus";
		DropDowns dropDown=new DropDowns();
		dropDown=dropdownService.getDropdownByDropdownCodeAndType(((ShippingAgent)ships.get(i)).getStatus(),dropdowntype);
		if(dropDown!=null)
		{
		//row = main.createRow(j);cell = row.createCell((short) 2);cell.setCellValue(((ShippingAgent)ships.get(i)).getStatus());
		row = main.createRow(j);cell = row.createCell((short) 2);cell.setCellValue(dropDown.getFieldValue());}
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
	search.setSxcel("false");
	        return showForm(request, response, errors);
	    }
		//search.setTotalResults(results);   
		    try
		    {
		      shippingAgentService.searchShippingAgent(search,sortFlag);
		    } catch(Throwable t)
			{
			  t.printStackTrace();
			  errors.reject("generic.error", new Object[] {t.getMessage()}, null);
			  return showForm(request, response, errors);
			}
		   /* catch(Exception e)
		    {
		      e.printStackTrace();

				errors.reject("search.shippingAgent.error", new Object[] {e.getMessage()}, null);
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
		 Branch branch=new Branch();
		 int pageNumber = 1;
		String inputFieldId = request.getParameter("inputFieldId");
		
		String branchName=request.getParameter("branchName");
		String sortFlag ="";
        String shippingAgentName = request.getParameter("shipAgentName");
        
        UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
		branch=userService.getBranchByName(branchName);
		
		
		ShippingAgentService shippingAgentService = (ShippingAgentService)ServiceLocator.getInstance().getBean("shippingAgentService");
		ShippingAgentSearch shippingAgentSearch = new ShippingAgentSearch();
		Pagination pagination = new Pagination(1, 20, pageNumber, 10);
		shippingAgentSearch.setPagination(pagination);
		StringSearchField stringSearchField1 = new StringSearchField();
		StringSearchField stringSearchField2 = new StringSearchField();
		try {
		if((shippingAgentName!= null) && !"".equals(shippingAgentName.trim())) {
			
			
			stringSearchField1.setValue(shippingAgentName);
			
			shippingAgentSearch.setName(stringSearchField1);
			
			
		   // try
		   // {
		    	shippingAgentService.searchShippingAgent(shippingAgentSearch,sortFlag);
		   // }
		   /* catch(Exception e)
		    {
		        e.printStackTrace();
		    }*/
		}
		
		
		
		if(branch != null)
		{
			
			stringSearchField2.setValue(branch.getCountryCode());
		
			shippingAgentSearch.setCountry(stringSearchField2);
			//try
		   // {
		    	shippingAgentService.searchShippingAgent(shippingAgentSearch,sortFlag);
		   /*}
		   catch(Exception e)
		   {
		       e.printStackTrace();
		   }*/
		}
		shippingAgentSearch.setInputFieldId(inputFieldId);
		} catch(ServiceException e)
	    {
	    	throw new ServiceException(e.getMessage(), e.getParams(), e);
	    }
	    catch(Throwable t)
	    {
	        t.printStackTrace();
	        throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
		}
		
		 
		

		return shippingAgentSearch;
	}
 private List getPaginationResults(List results,ShippingAgentSearch search,String pageNumberStr){
		
		ShippingAgent shippingAgent = new  ShippingAgent();
	    List finalResults = new ArrayList();
	    int pageNumber = 1;
		try {
			pageNumber = new Integer(pageNumberStr).intValue();
		} catch (Exception e) {
		}
		if (pageNumber <= 0)
			pageNumber = 1;		
		Pagination shipAgentPagination = new Pagination(1, 20, pageNumber,10);
	    	search.setPagination(shipAgentPagination);
	    	if(shipAgentPagination != null)
			{
			  if(results.size() > 0){
				  shipAgentPagination.setTotalRecord(results.size());
			  }
			  shipAgentPagination.calculatePages();
			}
	    	int startRecord=Integer.valueOf(pageNumberStr).intValue();
	    	int sortedResultsSize = results.size();
	    	
	    	if(startRecord == 1 )
	    	{
	    		if(sortedResultsSize >= Constants.RECORDS_PAGE)
	    		{
			    	for(int i=0;i<Constants.RECORDS_PAGE;i++)
			    	{
			    		shippingAgent = (ShippingAgent) results.get(i);
			    		finalResults.add(shippingAgent);
			    	}
	    		}else
	    		{
	    			for(int i=0;i<sortedResultsSize;i++)
			    	{
	    				shippingAgent = (ShippingAgent) results.get(i);
			    		finalResults.add(shippingAgent);
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
		    			shippingAgent = (ShippingAgent) results.get(i);
			    		finalResults.add(shippingAgent);
			    	}
	    		}else
	    		{
	    			start=(startRecord -1)*Constants.RECORDS_PAGE;
	    			end=sortedResultsSize;
	    			for(int i=start;i<end;i++)
			    	{
	    				shippingAgent = (ShippingAgent) results.get(i);
			    		finalResults.add(shippingAgent);
			    	}
	    		}
	    		
	    	}
    	return finalResults;
	}
/*
Pagination shipAgentPagination = new Pagination(1, 20, pageNumber,10);
	    	search.setPagination(shipAgentPagination);
	    	if(shipAgentPagination != null)
			{
			  if(results.size() > 0){
				  shipAgentPagination.setTotalRecord(results.size());
			  }
			  shipAgentPagination.calculatePages();
			}
	    	int startRecord=Integer.valueOf(pageNumberStr).intValue();
	    	int sortedResultsSize = results.size();
	    	
	    	if(startRecord == 1 )
	    	{
	    		if(sortedResultsSize >= Constants.RECORDS_PAGE)
	    		{
			    	for(int i=0;i<Constants.RECORDS_PAGE;i++)
			    	{
			    		shippingAgent = (ShippingAgent) results.get(i);
			    		finalResults.add(shippingAgent);
			    	}
	    		}else
	    		{
	    			for(int i=0;i<sortedResultsSize;i++)
			    	{
	    				shippingAgent = (ShippingAgent) results.get(i);
			    		finalResults.add(shippingAgent);
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
		    			shippingAgent = (ShippingAgent) results.get(i);
			    		finalResults.add(shippingAgent);
			    	}
	    		}else
	    		{
	    			start=(startRecord -1)*Constants.RECORDS_PAGE;
	    			end=sortedResultsSize;
	    			for(int i=start;i<end;i++)
			    	{
	    				shippingAgent = (ShippingAgent) results.get(i);
			    		finalResults.add(shippingAgent);
			    	}
	    		}
	    	}
	    	search.setResults(finalResults);
	    	request.setAttribute("command", search);
	       
	        return showForm(request, response, errors);*/
}
