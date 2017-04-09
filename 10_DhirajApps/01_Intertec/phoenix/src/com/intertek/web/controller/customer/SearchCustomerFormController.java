package com.intertek.web.controller.customer;

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

import com.intertek.domain.CustomerSearch;
import com.intertek.entity.CustAddress;//96509
import com.intertek.entity.Customer;
import com.intertek.entity.DropDowns;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.CustomerService;
import com.intertek.service.DropdownService;
import com.intertek.util.Constants;

public class SearchCustomerFormController extends SimpleFormController
{
  public SearchCustomerFormController() {
    super();
    setCommandClass(CustomerSearch.class);
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
    CustomerSearch search = (CustomerSearch)command;
    CustomerService customerService = (CustomerService)ServiceLocator.getInstance().getBean("customerService");
    DropdownService dropdownService = (DropdownService)ServiceLocator.getInstance().getBean("dropdownService");

    Customer customer = new Customer();
    String pageNumberStr = request.getParameter("pageNumber");
    String sortFlag = request.getParameter("sortFlag");
	String cxcel=request.getParameter("cxcel");
	String submitFlag=request.getParameter("submitFlag");
    
	List results = new ArrayList();
	List finalResults = new ArrayList();	

    search.setBillToFlag("Y");
    search.setSoldToFlag("Y");
    search.setShipToFlag("N");

    int pageNumber;
	if(pageNumberStr == null || pageNumberStr.trim().equals(""))
		pageNumber = 1;
	else
	pageNumber = Integer.parseInt(pageNumberStr);
	Pagination pagination = new Pagination(1,20,pageNumber,10);
	search.setPagination(pagination);
    if(sortFlag != null && !sortFlag.trim().equals(""))
	{	  
	//if(pageNumberStr.equals("1")) 
	//{
			try
				{
				 customerService.searchCustomer(search,sortFlag);
				}catch(ServiceException e)
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
				/*catch(Exception e)
				{
				e.printStackTrace();
				errors.reject("search.job.error", new Object[] {e.getMessage()}, null);
				return showForm(request, response, errors);
				}	*/	
			//}
		 //  	results = search.getTotalResults();
	    //	results = getPaginationResults(results,search,pageNumberStr);
		//	search.setResults(results);	 
	        return showForm(request, response, errors);
	    } 




    if((submitFlag!=null) && "true".equals(submitFlag))
	{ 
    	/*int pageNumber=-1;
    	pageNumber=-1;
    	search.setPagination(null);
		customerService.searchCustomer(search,sortFlag);
		results = search.getResults();
		search.setSubmitFlag("none");
		search.setTotalResults(results);
		results = search.getTotalResults();	
		results = getPaginationResults(results,search,pageNumberStr);
		search.setResults(results);  */
    	search.setSubmitFlag("none");
    	try {
		 customerService.searchCustomer(search,sortFlag);
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
	/*	catch(Exception e)
		{
		e.printStackTrace();
		errors.reject("search.job.error", new Object[] {e.getMessage()}, null);
		return showForm(request, response, errors);
		}*/
	}
	
	if((cxcel!=null) && "true".equals(cxcel))
	{ 
    List  customers=new ArrayList();
	//int pageNumber=-1;
    //pageNumber=-1;
	search.setPagination(null);
	try
	{
	 customerService.searchCustomer(search,sortFlag);
	}catch(ServiceException e)
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
	/*catch(Exception e)
	{
	e.printStackTrace();
	errors.reject("search.job.error", new Object[] {e.getMessage()}, null);
	return showForm(request, response, errors);
	}	*/
	//customers=search.getTotalResults();
	customers=search.getResults();
	if(customers!=null&&customers.size()!=0)
	{
	try {
		response.setContentType("application/download");
	 	response.setHeader("Content-Disposition", "attachment; filename=\"customers.xls\"");
		response.setHeader("Pragma", "public");
		response.setHeader("Cache-Control", "max-age=0");

		
		HSSFWorkbook workBook = new HSSFWorkbook();
		HSSFSheet main=null;
		
		main=workBook.createSheet("Customer");
		HSSFRow   row ;
		HSSFCell  cell;
		HSSFRow headerRow;
		HSSFCell headerCell ;
		PropertyResourceBundle pRB = (PropertyResourceBundle) ResourceBundle.getBundle("TrackerRes");
		String path="";
			if(pRB!= null)
			{
				int  l=0;
				for(int k=0;k<=(Constants.CColumnSize+1);k++)
				{
				path=pRB.getString("CHeading"+k);
				headerRow = main.createRow((short) 0); headerCell =   headerRow.createCell((short) l); headerCell.setCellValue(path);
				l++;
				}
			}


        int j=1;
		for(int i=0;i<customers.size();i++)
//96509 start
		{
			Customer cust = (Customer)(customerService.getCustomersByCustCode(((CustAddress)customers.get(i)).getCustCode())).get(0);
			row = main.createRow(j);
			
			cell = row.createCell((short) 0);
			//cell.setCellValue(((Customer)customers.get(i)).getCustCode());
			cell.setCellValue(cust.getName());
			row = main.createRow(j);cell = row.createCell((short) 1);
			
			// customer code
			if(cust.getName()!=null && !cust.getName().trim().equals(""))
			{cell.setCellValue(((CustAddress)customers.get(i)).getCustCode());}
			else{cell.setCellValue("");}
			
			String dropdowntype="activeStatus";
			DropDowns dropDown=null;
			dropDown=dropdownService.getDropdownByDropdownCodeAndType(cust.getStatus(),dropdowntype);
			
			// Status
			if(dropDown!=null)
			{//row = main.createRow(j);cell = row.createCell((short) 2);cell.setCellValue(((Customer)customers.get(i)).getStatus());
			row = main.createRow(j);cell = row.createCell((short) 2);cell.setCellValue(dropDown.getFieldValue());}
	        else{cell.setCellValue("");}
			
			// Alternate Name
			row = main.createRow(j);cell = row.createCell((short) 3);
			//if(((CustAddress)customers.get(i)).getAddress1()!=null && !((CustAddress)customers.get(i)).getAddress1().trim().equals(""))
			if( cust.getAlternateName()!= null && !(cust.getAlternateName().trim().equals("")))
			{cell.setCellValue(cust.getAlternateName());}
			else{cell.setCellValue("");}
			
			// country
			row = main.createRow(j);cell = row.createCell((short) 4);
			if(((CustAddress)customers.get(i)).getCountry()!=null && !((CustAddress)customers.get(i)).getCountry().trim().equals(""))
			{cell.setCellValue(((CustAddress)customers.get(i)).getCountry());}
			else{cell.setCellValue("");}
			
			// state
			row = main.createRow(j);cell = row.createCell((short) 5);
			if(((CustAddress)customers.get(i)).getState()!=null && !((CustAddress)customers.get(i)).getState().trim().equals(""))
			{cell.setCellValue(((CustAddress)customers.get(i)).getState());}
			else{cell.setCellValue("");}
			
			// city
			row = main.createRow(j);cell = row.createCell((short) 6);
			if(((CustAddress)customers.get(i)).getCity()!=null && !((CustAddress)customers.get(i)).getCity().trim().equals(""))
			{cell.setCellValue(((CustAddress)customers.get(i)).getCity());}
			else{cell.setCellValue("");}
			
			// address1
			row = main.createRow(j);cell = row.createCell((short) 7);
			if(((CustAddress)customers.get(i)).getAddress1()!=null && !((CustAddress)customers.get(i)).getAddress1().trim().equals(""))
			{cell.setCellValue(((CustAddress)customers.get(i)).getAddress1());}
			else{cell.setCellValue("");}
			
			j++;
//96509 end
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
	search.setCxcel("false");
	return showForm(request, response, errors);
	}
	
	List myResults=search.getResults();
	if(myResults!=null && myResults.size()==1){
		//Customer c=(Customer)myResults.get(0);
		//for itrack issue 96509
		CustAddress custAddress =(CustAddress)myResults.get(0);
		return new ModelAndView(new RedirectView("edit_customer.htm?reqFrom=searchCustomerForm&customer.custCode="+ custAddress.getCustCode()));
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
  ) throws Exception
  {
  	return new CustomerSearch();
  }

   private List getPaginationResults(List results,CustomerSearch search,String pageNumberStr){
		
		Customer customer = new Customer();
	  
	    List finalResults = new ArrayList();
	    
	    int pageNumber = 1;
		try {
			pageNumber = new Integer(pageNumberStr).intValue();
		} catch (Exception e) {
		}
		if (pageNumber <= 0)
			pageNumber = 1;
		
		Pagination customerPagination = new Pagination(1, 20, pageNumber,10);
    	
    	search.setPagination(customerPagination);
    	if(customerPagination != null)
		{
		  if(results.size() > 0){
			  customerPagination.setTotalRecord(results.size());
		  }
		  customerPagination.calculatePages();
		}
    	int startRecord=Integer.valueOf(pageNumberStr).intValue();
    	int sortedResultsSize = results.size();
    	
    	if(startRecord == 1 )
    	{
    		if(sortedResultsSize >= Constants.RECORDS_PAGE)
    		{
		    	for(int i=0;i<Constants.RECORDS_PAGE;i++)
		    	{
		    		customer = (Customer) results.get(i);
		    		finalResults.add(customer);
		    	}
    		}else
    		{
    			for(int i=0;i<sortedResultsSize;i++)
		    	{
    				customer = (Customer) results.get(i);
		    		finalResults.add(customer);
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
	    			customer = (Customer) results.get(i);
		    		finalResults.add(customer);
		    	}
    		}else
    		{
    			start=(startRecord -1)*Constants.RECORDS_PAGE;
    			end=sortedResultsSize;
    			for(int i=start;i<end;i++)
		    	{
    				customer = (Customer) results.get(i);
		    		finalResults.add(customer);
		    	}
    		}
    		
    	}
    	return finalResults;
	}
}
