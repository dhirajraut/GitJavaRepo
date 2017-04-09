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

public class SearchCompanyNamePopupController extends SimpleFormController {
	public SearchCompanyNamePopupController() {
		super();
		setCommandClass(CustomerSearch.class);
		//setSessionForm(true);
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		String pageNumberStr = request.getParameter("pageNumber");
		String searchType = request.getParameter("searchType");
		String searchForm = request.getParameter("searchForm");
		String sortFlag = request.getParameter("sortFlag");

		String parentCustomerSearchFlag = null;
		int pageNumber = 1;
		try {
			pageNumber = new Integer(pageNumberStr).intValue();
		} catch (Exception e) {
		}
		if (pageNumber <= 0)
			pageNumber = 1;

		CustomerSearch search = (CustomerSearch) command;
		Pagination pagination = new Pagination(1, 20, pageNumber, 10);
		search.setPagination(pagination);
		search.setSearchForm(searchForm);
	    search.setBillToFlag("Y");
	    search.setSoldToFlag("Y");
	    search.setShipToFlag("N");
	    
		CustomerService customerService = (CustomerService) ServiceLocator.getInstance().getBean("customerService");
		Customer customer = new Customer();
	    List results = new ArrayList();
	    if(sortFlag != null && !sortFlag.trim().equals(""))
	    {
	    	List finalResults = new ArrayList();
	    	
	    	//if(pageNumberStr.equals("1"))
	    	//{
	    		try
		        {
	    			customerService.searchCustomerByName(search,parentCustomerSearchFlag,sortFlag);
		        }
		        catch(Exception e)
		        {
		        	e.printStackTrace();
					errors.reject("search.customer.error", new Object[] { e.getMessage() }, null);
					return showForm(request, response, errors);
		        }
	    	//}
	    	/*results = search.getTotalResults();
	    	
	    	System.out.println("Results="+results.size());
	    	
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
		try {
			customerService.searchCustomerByName(search,parentCustomerSearchFlag,sortFlag);
		} catch (Exception e) {
			e.printStackTrace();
			errors.reject("search.customer.error", new Object[] { e.getMessage() }, null);
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

	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		System.out.println(" Inside Companyname popup frombacking ");
		String inputFieldId = request.getParameter("inputFieldId");
		String searchForm = request.getParameter("searchForm");
		String custCode = request.getParameter("custCode");
		//String parentcustomerType = request.getParameter("parentcustomerType");
		System.out.println("custCode : "+custCode);
		CustomerSearch customerSearch = new CustomerSearch();
		//parentCompanySearch.setSearchType(parentcustomerType);
		customerSearch.setInputFieldId(inputFieldId);
		customerSearch.setSearchForm(searchForm);
		
		
		if(custCode != null && (!custCode.trim().equals("")) && (!custCode.trim().equals("undefined")))
		{
			try {
			CustomerService customerService = (CustomerService) ServiceLocator.getInstance().getBean("customerService");
			//customerSearch.getCustomerId().setValue(custCode);
			customerSearch.getCustomer().setValue(custCode);
			customerSearch.setCustCode(custCode);
			Pagination pagination = new Pagination(1, 20, 1, 10);
			customerSearch.setPagination(pagination);
			customerService.searchCustomerByName(customerSearch,null,"custCode");
			customerSearch.setResults(customerSearch.getTotalResults());
		    } catch(ServiceException e)
	        {
	      	  throw new ServiceException(e.getMessage(), e.getParams(), e);
	        }
	        catch(Throwable t)
	        {
	          t.printStackTrace();
	          throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
	        }
		}
		return customerSearch;
	}
}
