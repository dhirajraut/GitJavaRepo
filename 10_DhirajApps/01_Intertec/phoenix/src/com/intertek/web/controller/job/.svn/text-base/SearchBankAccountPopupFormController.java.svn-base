package com.intertek.web.controller.job;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.domain.BankSearch;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.JobService;

public class SearchBankAccountPopupFormController extends SimpleFormController
{
  public SearchBankAccountPopupFormController() {
    super();
    setCommandClass(BankSearch.class);
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
	String searchForm = request.getParameter("searchForm");
	  	  String inputFieldId = request.getParameter("inputFieldId");
	String sortFlag = request.getParameter("sortFlag");
		  
    int pageNumber = 1;
    try
    {
      pageNumber = new Integer(pageNumberStr).intValue();
   }
    catch(Exception e)
    {
		e.printStackTrace();
    }
    if(pageNumber <= 0) pageNumber = 1;
    BankSearch search = (BankSearch)command;
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
	
	
    JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");	
   /* BankAccount bankAccount = new BankAccount();
    List results = new ArrayList();*/
    if(sortFlag != null && !sortFlag.trim().equals(""))
    {
    	/*List finalResults = new ArrayList();
    	if(pageNumberStr.equals("1"))
    	{*/
    		try
	        {
    			jobService.searchBankAccount(search,sortFlag);
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
	       /* catch(Exception e)
	        {
	        	e.printStackTrace();
	            errors.reject("search.bank.error", new Object[] {e.getMessage()}, null);
	             return showForm(request, response, errors);
	        }*/
    	/*}
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
		    		bankAccount = (BankAccount) results.get(i);
		    		finalResults.add(bankAccount);
		    	}
    		}else
    		{
    			for(int i=0;i<sortedResultsSize;i++)
		    	{
    				bankAccount = (BankAccount) results.get(i);
		    		finalResults.add(bankAccount);
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
	    			bankAccount = (BankAccount) results.get(i);
		    		finalResults.add(bankAccount);
		    	}
    		}else
    		{
    			start=(startRecord -1)*Constants.RECORDS_PAGE;
    			end=sortedResultsSize;
    			for(int i=start;i<end;i++)
		    	{
    				bankAccount = (BankAccount) results.get(i);
		    		finalResults.add(bankAccount);
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
	jobService.searchBankAccount(search,sortFlag);
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
	/*catch(Exception e)
    {
      e.printStackTrace();
     errors.reject("search.bank.error", new Object[] {e.getMessage()}, null);
      return showForm(request, response, errors);
    }*/
    request.setAttribute("command", search);
    return showForm(request, response, errors);
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest, org.springframework.web.bind.ServletRequestDataBinder)
   */
  protected void initBinder(HttpServletRequest request,ServletRequestDataBinder binder) throws Exception
  {
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
   */
  protected Object formBackingObject(
    HttpServletRequest request
  ) throws Exception
  {
	 JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");	
      String pageNumberStr = request.getParameter("pageNumber");
	  String inputFieldId = request.getParameter("inputFieldId");	
	  String rowNum=request.getParameter("rowNum");
	  String buName=request.getParameter("buName");
	  String currency=request.getParameter("currency");
	  String bankCode=request.getParameter("bankCode");
	  String sortFlag = "";
 	  BankSearch bankSearch = new BankSearch();
	  bankSearch.setInputFieldId(inputFieldId);
	  bankSearch.setRowNum(rowNum);
	  bankSearch.getBuName().setValue(buName);
	  bankSearch.getCurrency().setValue(currency);
	  bankSearch.getBankCode().setValue(bankCode);
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
		bankSearch.setPagination(pagination);
    try
    { jobService.searchBankAccount(bankSearch,sortFlag);
		 
     request.setAttribute("command", bankSearch);
    } catch(ServiceException e)
    {
    	throw new ServiceException(e.getMessage(), e.getParams(), e);
    }
    catch(Throwable t)
    {
        t.printStackTrace();
        throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
    }

	  return bankSearch;
  }
}
