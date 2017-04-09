package com.intertek.web.controller.contract;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.domain.ContractSearch;
import com.intertek.entity.Contract;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.ContractService;

public class SearchContractPopupController extends SimpleFormController
{
  public SearchContractPopupController() {
    super();
    setCommandClass(ContractSearch.class);
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
	   String pageNumberStr = request.getParameter("pageNumber");
	   String sortFlag = request.getParameter("sortFlag");
    int pageNumber = 1;
    try
    {
      pageNumber = new Integer(pageNumberStr).intValue();
    }
    catch(Exception e)
    {
    }
    if(pageNumber <= 0) pageNumber = 1;

    ContractSearch search = (ContractSearch)command;
    Pagination pagination = new Pagination(1, 20, pageNumber, 10);
    search.setPagination(pagination);

   ContractService contractService = (ContractService)ServiceLocator.getInstance().getBean("contractService");
   Contract contract = new Contract();
   List results = new ArrayList();
	Pagination contractPagination = new Pagination(1, 20, pageNumber,10);
   	search.setPagination(contractPagination);
   if(sortFlag != null && !sortFlag.trim().equals(""))
   {
   	List finalResults = new ArrayList();
   	
   	//if(pageNumberStr.equals("1"))
   	//{
   		try
	        {
   			contractService.searchContractCode(search,sortFlag);
	        }
	        catch(Exception e)
	        {
	        	e.printStackTrace();
	            errors.reject("search.contract.error", new Object[] {e.getMessage()}, null);
	            return showForm(request, response, errors);
	        }
   //	}
   //	results = search.getTotalResults();
   	
  /* 	Pagination contractPagination = new Pagination(1, 20, pageNumber,10);
   	
   	search.setPagination(contractPagination);
   	if(contractPagination != null)
		{
		  if(results.size() > 0){
			  contractPagination.setTotalRecord(results.size());
		  }
		  contractPagination.calculatePages();
		}
   	int startRecord=Integer.valueOf(pageNumberStr).intValue();
   	int sortedResultsSize = results.size();
   	
   	if(startRecord == 1 )
   	{
   		if(sortedResultsSize >= Constants.RECORDS_PAGE)
   		{
		    	for(int i=0;i<Constants.RECORDS_PAGE;i++)
		    	{
		    		contract = (Contract) results.get(i);
		    		finalResults.add(contract);
		    	}
   		}else
   		{
   			for(int i=0;i<sortedResultsSize;i++)
		    	{
   				contract = (Contract) results.get(i);
		    		finalResults.add(contract);
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
	    			contract = (Contract) results.get(i);
		    		finalResults.add(contract);
		    	}
   		}else
   		{
   			start=(startRecord -1)*Constants.RECORDS_PAGE;
   			end=sortedResultsSize;
   			for(int i=start;i<end;i++)
		    	{
   				contract = (Contract) results.get(i);
		    		finalResults.add(contract);
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
 contractService.searchContractCode(search,sortFlag);
	}
	catch(Exception e)
    {
      e.printStackTrace();
      errors.reject("search.contract.error", new Object[] {e.getMessage()}, null);
      return showForm(request, response, errors);
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
	
	  String inputFieldId = request.getParameter("inputFieldId");
	  String rowNum = request.getParameter("rowNum");
	  if(rowNum == null || rowNum.trim().equals(""))
		  rowNum="";
	  String searchForm=request.getParameter("searchForm");
	  ContractSearch contractSearch = new ContractSearch();
	  contractSearch.setInputFieldId(inputFieldId.trim());
	  contractSearch.setRowNum(rowNum);
	  contractSearch.setSearchForm(searchForm);
	  return contractSearch;
  }
}
