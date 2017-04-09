package com.intertek.web.controller.jobtype;

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
import org.springframework.web.servlet.view.RedirectView;

import com.intertek.domain.JobTypeSearch;
import com.intertek.entity.DropDowns;
import com.intertek.entity.Operation;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.DropdownService;
import com.intertek.service.JobTypeService;
import com.intertek.util.Constants;

public class SearchOperationsFormController extends SimpleFormController
{
  public SearchOperationsFormController() {
    super();
    setCommandClass(JobTypeSearch.class);
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
    JobTypeSearch search = (JobTypeSearch)command;
 	JobTypeService jobTypeService=(JobTypeService)ServiceLocator.getInstance().getBean("jobTypeService");
	DropdownService dropdownService = (DropdownService)ServiceLocator.getInstance().getBean("dropdownService");

	//Operation operation = new Operation();

    String pageNumberStr = request.getParameter("pageNo");
    String sortFlag = request.getParameter("sortFlag");
	String oxcel=request.getParameter("oxcel");
	String submitFlag=request.getParameter("submitFlag");
	
	/*List results = new ArrayList();
	List finalResults = new ArrayList();
	search.setPagination(null);*/
	int pageNumber = 1;
    try
    {
      pageNumber = new Integer(pageNumberStr).intValue();
    }
    catch(Exception e)
    {
    }
    if(pageNumber <= 0) pageNumber = 1;
    Pagination pagination = new Pagination(1, 20, pageNumber, 10);
   
    search.setPagination(pagination);

  if(sortFlag != null && !sortFlag.trim().equals(""))
	{	  
	//if(pageNumberStr.equals("1")) 
	//{
			try
				{
				jobTypeService.searchOperation(search,sortFlag);
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
				errors.reject("search.operation.error", new Object[] {e.getMessage()}, null);
				return showForm(request, response, errors);
				}*/
		
			/*}

		   	results = search.getTotalResults();
	    	results = getPaginationResults(results,search,pageNumberStr);
			search.setResults(results);	*/       
	        return showForm(request, response, errors);
	    } 
		
    if((submitFlag!=null) && "true".equals(submitFlag))
	{ 
		/*jobTypeService.searchOperation(search,sortFlag);
		results = search.getResults();
		search.setSubmitFlag("none");
		search.setTotalResults(results);
		results = search.getTotalResults();	
		results = getPaginationResults(results,search,pageNumberStr);
		search.setResults(results); */
    	search.setSubmitFlag("none");
    	try
		{
		jobTypeService.searchOperation(search,sortFlag);
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
		errors.reject("search.operation.error", new Object[] {e.getMessage()}, null);
		return showForm(request, response, errors);
		}*/
	}
	
	if((oxcel!=null) && "true".equals(oxcel))
	{ 
    List  ops=new ArrayList();
	//int pageNumber=-1;
	//ops=search.getTotalResults();
    search.setPagination(null);
	try
	{
	jobTypeService.searchOperation(search,sortFlag);
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
	ops=search.getResults();
	if(ops!=null&&ops.size()!=0)
	{
	try
		{
		response.setContentType("application/download");
	 	response.setHeader("Content-Disposition", "attachment; filename=\"operations.xls\"");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0");

		
		HSSFWorkbook workBook = new HSSFWorkbook();
		HSSFSheet main=null;
		
		main=workBook.createSheet("Operation");
		HSSFRow   row ;
		HSSFCell  cell;
		HSSFRow headerRow;
		HSSFCell headerCell ;
		PropertyResourceBundle pRB = (PropertyResourceBundle) ResourceBundle.getBundle("TrackerRes");
		String path="";
			if(pRB!= null)
			{
				int  l=0;
				for(int k=0;k<=Constants.OColumnSize;k++)
				{
				path=pRB.getString("OHeading"+k);
				headerRow = main.createRow((short) 0); headerCell =   headerRow.createCell((short) l); headerCell.setCellValue(path);
				l++;
				}
			}


        int j=1;
		for(int i=0;i<ops.size();i++)
		{ 
		row = main.createRow(j);cell = row.createCell((short) 0);cell.setCellValue(((Operation)ops.get(i)).getOperationCode());
		row = main.createRow(j);cell = row.createCell((short) 1);
		if(((Operation)ops.get(i)).getDescription()!=null && !((Operation)ops.get(i)).getDescription().trim().equals(""))
		{cell.setCellValue(((Operation)ops.get(i)).getDescription());}
		else{cell.setCellValue("");}
		
        row = main.createRow(j);cell = row.createCell((short) 2);
		String dropdowntype="activeStatus";
		DropDowns dropDown = null;
		dropDown=dropdownService.getDropdownByDropdownCodeAndType(((Operation)ops.get(i)).getStatus(),dropdowntype);
		if(dropDown!=null)
		{
		cell.setCellValue(dropDown.getFieldValue());}
		else{cell.setCellValue("");}


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
	search.setOxcel("false");
	return showForm(request, response, errors);
	}
	
    request.setAttribute("command", search);
	List myResults=search.getResults();
	if(myResults!=null && myResults.size()==1){
		Operation c=(Operation)myResults.get(0);
		return new ModelAndView(new RedirectView("create_operation_event.htm?name="+c.getOperationCode()));
	}    
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
    return new JobTypeSearch();
  }
private List getPaginationResults(List results,JobTypeSearch search,String pageNumberStr){
		
		Operation operation = new Operation();
	  
	    List finalResults = new ArrayList();
	    
	    int pageNumber = 1;
		try {
			pageNumber = new Integer(pageNumberStr).intValue();
		} catch (Exception e) {
		}
		if (pageNumber <= 0)
			pageNumber = 1;
		
			Pagination operationPagination = new Pagination(1, 20, pageNumber,10);
	   	
	   	search.setPagination(operationPagination);
	   	if(operationPagination != null)
			{
			  if(results.size() > 0){
				  operationPagination.setTotalRecord(results.size());
			  }
			  operationPagination.calculatePages();
			 
			}
	   	int startRecord=Integer.valueOf(pageNumberStr).intValue();
	   	int sortedResultsSize = results.size();
	   	
	   	if(startRecord == 1 )
	   	{
	   		if(sortedResultsSize >= Constants.RECORDS_PAGE)
	   		{
			    	for(int i=0;i<Constants.RECORDS_PAGE;i++)
			    	{
			    		operation = (Operation) results.get(i);
			    		finalResults.add(operation);
			    	}
	   		}else
	   		{
	   			for(int i=0;i<sortedResultsSize;i++)
			    	{
	   				operation = (Operation) results.get(i);
			    		finalResults.add(operation);
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
		    			operation = (Operation) results.get(i);
			    		finalResults.add(operation);
			    	}
	   		}else
	   		{
	   			start=(startRecord -1)*Constants.RECORDS_PAGE;
	   			end=sortedResultsSize;
	   			for(int i=start;i<end;i++)
			    	{
	   				operation = (Operation) results.get(i);
			    		finalResults.add(operation);
			    	}
	   		}
	   		
	   	}
    	return finalResults;
	}
}

