package com.intertek.web.controller.customer;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.domain.CustomerSearch;
import com.intertek.entity.Customer;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.CustomerService;
import com.intertek.util.Constants;

public class SearchCustomerIdPopupController extends SimpleFormController
{
  public SearchCustomerIdPopupController() {
    super();
    setCommandClass(CustomerSearch.class);
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
	   String pageNumberStr = request.getParameter("pageNumber");
	   String sortFlag = request.getParameter("sortFlag");
    int pageNumber = 1;
    try
    {
      pageNumber = new Integer(pageNumberStr).intValue();
    } catch(Exception e)
    {
    }
    if(pageNumber <= 0) pageNumber = 1;

    CustomerSearch search = (CustomerSearch)command;
    Pagination pagination = new Pagination(1, 20, pageNumber, 10);
    search.setPagination(pagination);
    search.setBillToFlag("Y");
    search.setSoldToFlag("Y");
    search.setShipToFlag("N");
    
   CustomerService customerService = (CustomerService)ServiceLocator.getInstance().getBean("customerService");
   Customer customer = new Customer();
   List results = new ArrayList();
   if(sortFlag != null && !sortFlag.trim().equals(""))
   {
   //	List finalResults = new ArrayList();
   	
   /*	if(pageNumberStr.equals("1"))
   	{*/
   		try {
	    customerService.searchCustomerId(search,sortFlag);
        } catch(Throwable t)
        {
        t.printStackTrace();
        errors.reject("generic.error", new Object[] {t.getMessage()}, null);
        return showForm(request, response, errors);
        }
	       /* catch(Exception e)
	        {
	        	e.printStackTrace();
	            errors.reject("search.customer.error", new Object[] {e.getMessage()}, null);
	            return showForm(request, response, errors);
	        }*/
   /*	}
  	results = search.getTotalResults();
   
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
   	search.setResults(finalResults);*/
   	request.setAttribute("command", search);
      
       return showForm(request, response, errors);
   }
  // search.setTotalResults(results);
	
	try
    {
		 customerService.searchCustomerId(search,sortFlag);
	} catch(Throwable t)
    {
      t.printStackTrace();
      errors.reject("generic.error", new Object[] {t.getMessage()}, null);
      return showForm(request, response, errors);
    }
	/*catch(Exception e)
    {
      e.printStackTrace();
     errors.reject("search.customer.error", new Object[] {e.getMessage()}, null);
      return showForm(request, response, errors);
    }*/

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
	
	  String inputFieldId = request.getParameter("inputFieldId");
	  String rowNum = request.getParameter("rowNum");
	  String searchForm=request.getParameter("searchForm");
	  CustomerSearch customerSearch = new CustomerSearch();
	  customerSearch.setInputFieldId(inputFieldId.trim());
	  customerSearch.setRowNum(rowNum);
	  customerSearch.setSearchForm(searchForm);
	  return customerSearch;
  }
}
