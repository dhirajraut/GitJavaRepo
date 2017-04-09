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

public class SearchBankPopupFormController extends SimpleFormController
{
  public SearchBankPopupFormController() {
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
  /*  Bank bank = new Bank();
    List results = new ArrayList();*/
    if(sortFlag != null && !sortFlag.trim().equals(""))
    {
    /*	List finalResults = new ArrayList();
    	System.out.println("pageNumberStr="+pageNumberStr);
    	if(pageNumberStr.equals("1"))
    	{*/
    		try
	        {
    			jobService.searchBankCode(search,sortFlag);
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
	
	          errors.reject("search.serviceLocation.error", new Object[] {e.getMessage()}, null);
	          return showForm(request, response, errors);
	        }*/
    	/*}
    	results = search.getTotalResults();
    	
    	System.out.println("Results="+results.size());
    	
    	Pagination bankPagination = new Pagination(1, 20, pageNumber,10);
    	
    	search.setPagination(bankPagination);
    	if(bankPagination != null)
		{
		  if(results.size() > 0){
			  bankPagination.setTotalRecord(results.size());
		  }
		  bankPagination.calculatePages();
		}
    	int startRecord=Integer.valueOf(pageNumberStr).intValue();
    	int sortedResultsSize = results.size();
    	
    	if(startRecord == 1 )
    	{
    		if(sortedResultsSize >= Constants.RECORDS_PAGE)
    		{
		    	for(int i=0;i<Constants.RECORDS_PAGE;i++)
		    	{
		    		bank = (Bank) results.get(i);
		    		finalResults.add(bank);
		    	}
    		}else
    		{
    			for(int i=0;i<sortedResultsSize;i++)
		    	{
    				bank = (Bank) results.get(i);
		    		finalResults.add(bank);
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
	    			bank = (Bank) results.get(i);
		    		finalResults.add(bank);
		    	}
    		}else
    		{
    			start=(startRecord -1)*Constants.RECORDS_PAGE;
    			end=sortedResultsSize;
    			for(int i=start;i<end;i++)
		    	{
    				bank = (Bank) results.get(i);
		    		finalResults.add(bank);
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
      jobService.searchBankCode(search,sortFlag);
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
	String pageNumberStr = request.getParameter("pageNumber");
	  String inputFieldId = request.getParameter("inputFieldId");	
	  String rowNum=request.getParameter("rowNum");
	   String buName=request.getParameter("buName");
	  String sortFlag = "";
	   String currency=request.getParameter("currency");
	   String searchForm = request.getParameter("searchForm");
	   
      BankSearch bankSearch = new BankSearch();
	  bankSearch.setInputFieldId(inputFieldId);
	  bankSearch.setRowNum(rowNum);
	  bankSearch.setSearchForm(searchForm);
	  bankSearch.getBuName().setValue(buName);
	  bankSearch.getCurrency().setValue(currency);
	  JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");	
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
		    {
			   jobService.searchBankCode(bankSearch,sortFlag);
		    } catch(ServiceException e)
		    {
		    	throw new ServiceException(e.getMessage(), e.getParams(), e);
		    }
		    catch(Throwable t)
		    {
		        t.printStackTrace();
		        throw new ServiceException("generic.error", new Object[] {t.getMessage()}, t);
		    }
		   /* catch(Exception e)
		    {
		        e.printStackTrace();
		    }*/
     request.setAttribute("command", bankSearch);
	  return bankSearch;
  }
}
