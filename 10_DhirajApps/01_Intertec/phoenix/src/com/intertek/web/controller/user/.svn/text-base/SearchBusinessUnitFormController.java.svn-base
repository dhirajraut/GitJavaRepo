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

import com.intertek.domain.BusinessUnitSearch;
import com.intertek.entity.BusinessUnit;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.UserService;
import com.intertek.util.Constants;

public class SearchBusinessUnitFormController extends SimpleFormController
{
  public SearchBusinessUnitFormController() {
    super();
    setCommandClass(BusinessUnitSearch.class);
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
    BusinessUnitSearch search = (BusinessUnitSearch)command;
    UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
    BusinessUnit businessUnit = new BusinessUnit();

    String pageNumberStr = request.getParameter("pageNo");
    String sortFlag = request.getParameter("sortFlag");
    String bxcel=request.getParameter("bxcel");
	String submitFlag=request.getParameter("submitFlag");

	List results = new ArrayList();
	List finalResults = new ArrayList();	
	//search.setPagination(null);
	int pageNumber;
	if(pageNumberStr == null || pageNumberStr.trim().equals(""))
		pageNumber = 1;
	else
	pageNumber = Integer.parseInt(pageNumberStr);
	Pagination pagination = new Pagination(1,20,pageNumber,10);
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
				 userService.searchBusinessUnit(search,sortFlag);
				}
				/*catch(Exception e)
				{
				e.printStackTrace();
				errors.reject("search.job.error", new Object[] {e.getMessage()}, null);
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
		
			//}

		   //	results = search.getTotalResults();
	    	//results = getPaginationResults(results,search,pageNumberStr);
			//search.setResults(results);	       
	        return showForm(request, response, errors);
	    } 
		

if((submitFlag!=null) && "true".equals(submitFlag))
	{ 
		/*userService.searchBusinessUnit(search,sortFlag);
		results = search.getResults();
		search.setSubmitFlag("none");
		search.setTotalResults(results);
		results = search.getTotalResults();	
		results = getPaginationResults(results,search,pageNumberStr);
		search.setResults(results); */
		search.setSubmitFlag("none");
	try
	{
	 userService.searchBusinessUnit(search,sortFlag);
	}
	/*catch(Exception e)
	{
	e.printStackTrace();
	errors.reject("search.job.error", new Object[] {e.getMessage()}, null);
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
	
	}

if((bxcel!=null) && "true".equals(bxcel))
	{ 
    List bus=new ArrayList();
	//int pageNumber=-1;
	search.setPagination(null);
	try
	{
	 userService.searchBusinessUnit(search,sortFlag);
	}
	/*catch(Exception e)
	{
	e.printStackTrace();
	errors.reject("search.job.error", new Object[] {e.getMessage()}, null);
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
	//bus=search.getTotalResults();
	bus=search.getResults();
	if(bus!=null&&bus.size()!=0)
	{
	try
		{
		response.setContentType("application/download");
	 	response.setHeader("Content-Disposition", "attachment; filename=\"businessunits.xls\"");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0");

		
		HSSFWorkbook workBook = new HSSFWorkbook();
		HSSFSheet main=null;
		
		main=workBook.createSheet("BusinessUnit");
		HSSFRow   row ;
		HSSFCell  cell;
		HSSFRow headerRow;
		HSSFCell headerCell ;
		PropertyResourceBundle pRB = (PropertyResourceBundle) ResourceBundle.getBundle("TrackerRes");
		String path="";
			if(pRB!= null)
			{
				int  l=0;
				for(int k=0;k<=Constants.BuColumnSize;k++)
				{
				path=pRB.getString("BuHeading"+k);
				headerRow = main.createRow((short) 0); headerCell =   headerRow.createCell((short) l); headerCell.setCellValue(path);
				l++;
				}
			}


        int j=1;
		for(int i=0;i<bus.size();i++)
		{ 
		row = main.createRow(j);cell = row.createCell((short) 0);cell.setCellValue(((BusinessUnit)bus.get(i)).getName());
		row = main.createRow(j);cell = row.createCell((short) 1);
		if(((BusinessUnit)bus.get(i)).getDescription()!=null && !((BusinessUnit)bus.get(i)).getDescription().trim().equals(""))
		{cell.setCellValue(((BusinessUnit)bus.get(i)).getDescription());}
		else{cell.setCellValue("");}
		row = main.createRow(j);cell = row.createCell((short) 2);
		if(((BusinessUnit)bus.get(i)).getCountryCode()!=null && !((BusinessUnit)bus.get(i)).getCountryCode().trim().equals(""))
		{cell.setCellValue(((BusinessUnit)bus.get(i)).getCountryCode());}
		else{cell.setCellValue("");}
		row = main.createRow(j);cell = row.createCell((short) 3);
		if(((BusinessUnit)bus.get(i)).getStateCode()!=null && !((BusinessUnit)bus.get(i)).getStateCode().trim().equals(""))
		{cell.setCellValue(((BusinessUnit)bus.get(i)).getStateCode());}
		else{cell.setCellValue("");}
		row = main.createRow(j);cell = row.createCell((short) 4);
		if(((BusinessUnit)bus.get(i)).getCity()!=null && !((BusinessUnit)bus.get(i)).getCity().trim().equals(""))
		{cell.setCellValue(((BusinessUnit)bus.get(i)).getCity());}
		else{cell.setCellValue("");}
// 96509 start
		row = main.createRow(j);cell = row.createCell((short) 5);
		if(((BusinessUnit)bus.get(i)).getAddress1()!=null && !((BusinessUnit)bus.get(i)).getAddress1().trim().equals(""))
		{cell.setCellValue(((BusinessUnit)bus.get(i)).getAddress1());}
		else{cell.setCellValue("");}
// 96509 end
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
    
    // START : #119240 : 19/06/09 
    HttpSession session = request.getSession();
    session.setAttribute("businessUnitSearch", search);
    // START for Itrack note : 27-Jul-2009
    List<BusinessUnit> myResults = search.getResults();
    if(myResults != null && myResults.size() == 1){
    	BusinessUnit bu=(BusinessUnit)myResults.get(0);
    	return new ModelAndView(new RedirectView("edit_business_unit.htm?businessUnit.name=" + bu.getName()));
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
	  // START : #119240 : 29/06/09
   // return new BusinessUnitSearch();
		BusinessUnitSearch  businessUnitSearch = null;
	    UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
		HttpSession session = request.getSession();
		String fromEdit = request.getParameter("fromEdit");
		
		if (fromEdit == null) {
			session.removeAttribute("businessUnitSearch");
		}

		if (session != null) {
			if (session.getAttribute("businessUnitSearch") != null) {
				businessUnitSearch = (BusinessUnitSearch) session.getAttribute("businessUnitSearch");
				// START for Itrack note : 27-Jul-2009
				//userService.searchBusinessUnit(businessUnitSearch,businessUnitSearch.getSortFlag());
				if(businessUnitSearch.getResults()!=null && businessUnitSearch.getResults().size() > 1){
					userService.searchBusinessUnit(businessUnitSearch,businessUnitSearch.getSortFlag());	
	  			}else{
	  				businessUnitSearch.setResults(null);
	  			}
				// END for Itrack note : 27-Jul-2009
			}
		}

		if (businessUnitSearch == null)
			businessUnitSearch = new BusinessUnitSearch();

		return businessUnitSearch;
		// END : #119240 : 29/06/09
  }
 private List getPaginationResults(List results,BusinessUnitSearch search,String pageNumberStr){
		
 BusinessUnit businessUnit = new BusinessUnit();

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
		    		businessUnit = (BusinessUnit) results.get(i);
		    		finalResults.add(businessUnit);
		    	}
   		}else
   		{
   			for(int i=0;i<sortedResultsSize;i++)
		    	{
   				businessUnit = (BusinessUnit) results.get(i);
		    		finalResults.add(businessUnit);
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
	    			businessUnit = (BusinessUnit) results.get(i);
		    		finalResults.add(businessUnit);
		    	}
   		}else
   		{
   			start=(startRecord -1)*Constants.RECORDS_PAGE;
   			end=sortedResultsSize;
   			for(int i=start;i<end;i++)
		    	{
   				businessUnit = (BusinessUnit) results.get(i);
		    		finalResults.add(businessUnit);
		    	}
   		}
   	}
    	return finalResults;
	}
}
