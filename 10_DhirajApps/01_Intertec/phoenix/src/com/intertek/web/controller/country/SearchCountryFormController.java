package com.intertek.web.controller.country;

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

import com.intertek.domain.CountrySearch;
import com.intertek.entity.Country;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.CountryService;
import com.intertek.util.Constants;

public class SearchCountryFormController extends SimpleFormController
{
  public SearchCountryFormController() {
    super();
    setCommandClass(CountrySearch.class);
    // START : #119240 : 18/06/09
   // setSessionForm(true);
    setSessionForm(true);
    // END : #119240 : 18/06/09
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
    CountrySearch search = (CountrySearch)command;
    CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");
    Country country = new Country();
	String pageNumberStr = request.getParameter("pageNo");
    String sortFlag = request.getParameter("sortFlag");
	String cxcel=request.getParameter("cxcel");
	String submitFlag=request.getParameter("submitFlag");
	
	/*List results = new ArrayList();
	List finalResults = new ArrayList();	
   search.setPagination(null);*/
	int pageNumber = 1;
	try {
		pageNumber = new Integer(pageNumberStr).intValue();
	} catch (Exception e) {
	}
	if (pageNumber <= 0)
		pageNumber = 1;

	
	Pagination pagination = new Pagination(1, 20, pageNumber, 10);
	search.setPagination(pagination);
	
	// START : #119240 : 16/06/09
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
	// END : #119240 : 16/06/09
	
	if(sortFlag != null && !sortFlag.trim().equals(""))
	{	  
		//if(pageNumberStr.equals("1")) 
		//{
			try
				{
   			countryService.searchCountryByCountryCode(search,sortFlag);
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

		  /* 	results = search.getTotalResults();
	    	results = getPaginationResults(results,search,pageNumberStr);
			search.setResults(results);	 */      
	        return showForm(request, response, errors);
	    } 

    if((submitFlag!=null) && "true".equals(submitFlag))
	{ 
   	  /*  countryService.searchCountryByCountryCode(search,sortFlag);
		results = search.getResults();
		search.setSubmitFlag("none");
		search.setTotalResults(results);
		results = search.getTotalResults();	
		results = getPaginationResults(results,search,pageNumberStr);
		search.setResults(results);  */
    	search.setSubmitFlag("none");
    	try
		{
		countryService.searchCountryByCountryCode(search,sortFlag);
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

	if((cxcel!=null) && "true".equals(cxcel))
	{ 
    List  countries=new ArrayList();
	/*int pageNumber=-1;
	search.setPagination(null);
	countries=search.getTotalResults();*/
    search.setPagination(null);
    try
	{
	countryService.searchCountryByCountryCode(search,sortFlag);
	}
	catch(Exception e)
	{
	e.printStackTrace();
	errors.reject("search.job.error", new Object[] {e.getMessage()}, null);
	return showForm(request, response, errors);
	}
	countries = search.getResults();
	if(countries!=null&&countries.size()!=0)
	{
	try
		{
		response.setContentType("application/download");
	 	response.setHeader("Content-Disposition", "attachment; filename=\"countries.xls\"");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0");

		
		HSSFWorkbook workBook = new HSSFWorkbook();
		HSSFSheet main=null;
		
		main=workBook.createSheet("Countrys");
		HSSFRow   row ;
		HSSFCell  cell;
		HSSFRow headerRow;
		HSSFCell headerCell ;
		PropertyResourceBundle pRB = (PropertyResourceBundle) ResourceBundle.getBundle("TrackerRes");
		String path="";
			if(pRB!= null)
			{
				int  l=0;
				for(int k=0;k<=Constants.CounColumnSize;k++)
				{
				path=pRB.getString("CounHeading"+k);//added constants in TrackerRes.properties for status
				headerRow = main.createRow((short) 0);//for row 
				headerCell =   headerRow.createCell((short) l);//for column
				headerCell.setCellValue(path);//setting the value
				l++;
				}
			}


        int j=1;
		for(int i=0;i<countries.size();i++)
		{ 
		row = main.createRow(j);cell = row.createCell((short) 0);cell.setCellValue(((Country)countries.get(i)).getCountryCode());
		row = main.createRow(j);cell = row.createCell((short) 1);
		if(((Country)countries.get(i)).getName()!=null && !((Country)countries.get(i)).getName().trim().equals(""))
		{cell.setCellValue(((Country)countries.get(i)).getName());}
		else{cell.setCellValue("");}
//      start issue 115770
		row = main.createRow(j);cell = row.createCell((short) 2);
		if(((Country)countries.get(i)).getStatus()!=null && !((Country)countries.get(i)).getStatus().trim().equals(""))
		{cell.setCellValue(((Country)countries.get(i)).getStatus());}
		else{cell.setCellValue("");}
//		end 115770
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
	search.setCxcel("false");
	return showForm(request, response, errors);
	}
    request.setAttribute("command", search);
    
    // START : #119240 : 19/06/09 
    HttpSession session = request.getSession();
    session.setAttribute("countrySearch", search);
    // START for Itrack note : 27-Jul-2009
    List<Country> myResults = search.getResults();
    if(myResults != null && myResults.size() == 1){
    	Country c = (Country)myResults.get(0);
    	return new ModelAndView(new RedirectView("edit_country.htm?countryCode=" + c.getCountryCode()));
    }
    // END for Itrack note : 27-Jul-2009
    
    // END : #119240 : 19/06/09
    
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
	  // START : #119240 : 16/06/09
		// return new CountrySearch();
	  	CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");
	  	CountrySearch countrySearch = null;
		HttpSession session = request.getSession();
		String fromEdit = request.getParameter("fromEdit");
		
		if (fromEdit == null) {
			session.removeAttribute("countrySearch");
		}

		if (session != null) {
			if (session.getAttribute("countrySearch") != null) {
				countrySearch = (CountrySearch) session.getAttribute("countrySearch");
				// START for Itrack note : 27-Jul-2009
				//countryService.searchCountryByCountryCode(countrySearch,countrySearch.getSortFlag());
				if(countrySearch.getResults()!=null && countrySearch.getResults().size() > 1){
					countryService.searchCountryByCountryCode(countrySearch,countrySearch.getSortFlag());
	  			}else{
	  				countrySearch.setResults(null);
	  			}
				// END for Itrack note : 27-Jul-2009
			}
		}

		if (countrySearch == null)
			countrySearch = new CountrySearch();

		return countrySearch;
		// END : #119240 : 16/06/09
  }
  
private List getPaginationResults(List results,CountrySearch search,String pageNumberStr){
		
		 Country country = new Country();
	  
	    List finalResults = new ArrayList();
	    
	    int pageNumber = 1;
		try {
			pageNumber = new Integer(pageNumberStr).intValue();
		} catch (Exception e) {
		}
		if (pageNumber <= 0)
			pageNumber = 1;
		
		Pagination countryPagination = new Pagination(1, 20, pageNumber,10);
    	
    	search.setPagination(countryPagination);
    	if(countryPagination != null)
	 {
		  if(results.size() > 0){
			  countryPagination.setTotalRecord(results.size());
		  }
		  countryPagination.calculatePages();
	 }
   	int startRecord=Integer.valueOf(pageNumberStr).intValue();
   	int sortedResultsSize = results.size();
   	if(startRecord == 1 )
   	{
   		if(sortedResultsSize >= Constants.RECORDS_PAGE)
   		{
		    	for(int i=0;i<Constants.RECORDS_PAGE;i++)
		    	{
		    		country = (Country) results.get(i);
		    		finalResults.add(country);
		    	}
   		}else
   		{
   			for(int i=0;i<sortedResultsSize;i++)
		    	{
   				country = (Country) results.get(i);
		    		finalResults.add(country);
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
	    			country = (Country) results.get(i);
		    		finalResults.add(country);
		    	}
   		}else
   		{
   			start=(startRecord -1)*Constants.RECORDS_PAGE;
   			end=sortedResultsSize;
   			for(int i=start;i<end;i++)
		    {
   			 country = (Country) results.get(i);
		     finalResults.add(country);
		    }
   		}
   	}
    	return finalResults;
	}
}
