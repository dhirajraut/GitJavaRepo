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
import org.springframework.web.servlet.view.RedirectView;

import com.intertek.domain.TowingCompanySearch;
import com.intertek.entity.DropDowns;
import com.intertek.entity.ShippingAgent;
import com.intertek.entity.TowingCompany;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.DropdownService;
import com.intertek.service.TowingCompanyService;
import com.intertek.util.Constants;

public class SearchTowingCompanyFormController extends SimpleFormController
{
  public SearchTowingCompanyFormController() {
    super();
    setCommandClass(TowingCompanySearch.class);
    //setSessionForm(true);
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
    TowingCompanySearch search = (TowingCompanySearch)command;
    TowingCompanyService towingCompanyService = (TowingCompanyService)ServiceLocator.getInstance().getBean("towingCompanyService");
	DropdownService dropdownService = (DropdownService)ServiceLocator.getInstance().getBean("dropdownService");

   // TowingCompany towingCompany = new  TowingCompany();

    String pageNumberStr = request.getParameter("pageNumber");
    String sortFlag = request.getParameter("sortFlag");
	String txcel=request.getParameter("txcel");
	String submitFlag=request.getParameter("submitFlag");
	
	/*List results = new ArrayList();
	List finalResults = new ArrayList();
	search.setPagination(null);*/
	int pageNumber = 0;
	try
    {
      pageNumber = new Integer(pageNumberStr).intValue();
    }
    catch(Exception e)
    {
    }
    if(pageNumber <= 0) pageNumber = 1;
    Pagination pagination = new Pagination(1,20, pageNumber, 10);
    search.setPagination(pagination);
  if(sortFlag != null && !sortFlag.trim().equals(""))
	{	  
	//if(pageNumberStr.equals("1")) 
	//{
			try {
			 towingCompanyService.searchTowingCompany(search,sortFlag);
			} catch(Throwable t)
			{
			  t.printStackTrace();
			  errors.reject("generic.error", new Object[] {t.getMessage()}, null);
			  return showForm(request, response, errors);
			}
				/*catch(Exception e)
				{
				e.printStackTrace();
			errors.reject("towingcompany.error", new Object[] {e.getMessage()}, null);
				return showForm(request, response, errors);
				}*/
		
			//}

		   /*	results = search.getTotalResults();
	    	results = getPaginationResults(results,search,pageNumberStr);
			search.setResults(results);	   */    
	        return showForm(request, response, errors);
	    } 
		
    if((submitFlag!=null) && "true".equals(submitFlag))
	{ 
		/*towingCompanyService.searchTowingCompany(search,sortFlag);
		results = search.getResults();
		search.setSubmitFlag("none");
		search.setTotalResults(results);
		results = search.getTotalResults();	
		results = getPaginationResults(results,search,pageNumberStr);
		search.setResults(results);  */
    	search.setSubmitFlag("none");
		try
		{
		 towingCompanyService.searchTowingCompany(search,sortFlag);
		} catch(Throwable t)
		{
		  t.printStackTrace();
		  errors.reject("generic.error", new Object[] {t.getMessage()}, null);
		  return showForm(request, response, errors);
		}
		/*catch(Exception e)
		{
		e.printStackTrace();
		errors.reject("towingcompany.error", new Object[] {e.getMessage()}, null);
		return showForm(request, response, errors);
		}*/
	}
	
	if((txcel!=null) && "true".equals(txcel))
	{ 
    List  tcs=new ArrayList();
	//int pageNumber=-1;
	search.setPagination(null);
	//tcs=search.getTotalResults();
	
	try
	{
	 towingCompanyService.searchTowingCompany(search,sortFlag);
	} catch(Throwable t)
	{
	  t.printStackTrace();
	  errors.reject("generic.error", new Object[] {t.getMessage()}, null);
	  return showForm(request, response, errors);
	}
	/*catch(Exception e)
	{
	e.printStackTrace();
	errors.reject("towingcompany.error", new Object[] {e.getMessage()}, null);
	return showForm(request, response, errors);
	}*/
	tcs=search.getResults();
	if(tcs!=null&&tcs.size()!=0)
	{
	try {
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
	
	List results=search.getResults();
	if(results!=null && results.size()==1){
		TowingCompany tc=(TowingCompany)results.get(0);
		return new ModelAndView(new RedirectView("edit_towing_company.htm"), "id", tc.getId());
	}
	
    request.setAttribute("command", search);
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
  ) throws Exception {

	return new TowingCompanySearch();
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
}

