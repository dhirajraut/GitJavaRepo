package com.intertek.web.controller.job;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.domain.SlateSearch;
import com.intertek.entity.CfgContract;
import com.intertek.entity.JobContract;
import com.intertek.entity.JobOrder;
import com.intertek.entity.PriceBook;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.CalculatorService;
import com.intertek.service.JobService;
import com.intertek.service.SlateService;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;

public class SearchSlatePopupController extends SimpleFormController {
	public SearchSlatePopupController() {
		super();
		setCommandClass(SlateSearch.class);
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
		String submitFlag = request.getParameter("submitFlag");
		String chosenContracts = request.getParameter("chosenContracts");
		String nomDate = request.getParameter("nomDate");
		
		int pageNumber = 1;
		try {
			pageNumber = new Integer(pageNumberStr).intValue();
		} catch (Exception e) {
		}
		if (pageNumber <= 0)
			pageNumber = 1;

		SlateSearch search = (SlateSearch) command;
		Pagination pagination = new Pagination(1, 20, pageNumber, 10);
		search.setPagination(pagination);
		search.setSearchForm(searchForm);
		search.setPageNo(pageNumberStr);
		search.setChosenContracts(chosenContracts);

		JobService jobService = (JobService) ServiceLocator.getInstance().getBean("jobService");
		CalculatorService calculatorService = (CalculatorService) ServiceLocator.getInstance().getBean("calculatorService");
		CfgContract contract = null;
		String pricebookId = null;
		JobContract jobContract = null;
		JobOrder jobOrder = null;
		String location = null;
		String language = null;
		Date jobFinishDate = new Date();
		try {
		if(search.getJobContractId() != null)
		{
			jobContract = jobService.getJobContractByIdWithDetails(new Long(search.getJobContractId()).longValue());
			if(jobContract != null)
			{
			jobOrder = jobContract.getJobOrder();
			
			if(jobOrder.getJobFinishDate() != null && (!jobOrder.getJobFinishDate().toString().trim().equals("")))
				jobFinishDate = jobOrder.getJobFinishDate();
			else
				jobFinishDate = jobOrder.getNominationDate();
			
				 contract = calculatorService.getContractByContractId(
		    	        jobContract.getContractCode(),
		    	        jobFinishDate
		    	      );
		    	      if(contract != null)
		    	      {
		    	        String priceBookId = contract.getPriceBookId();
		    	        if(Constants.CURRENT.equals(priceBookId))
		    	        {
		    	          PriceBook pb = calculatorService.getCurrentPriceBook(
		    	            contract.getPbSeries(),
		    	            contract.getCurrencyCD(),
		    	            jobFinishDate
		    	          );
		    	          if(pb != null) contract.setPriceBookId(pb.getPriceBookId().getPriceBookId());
		    	        }

		    	        
				
			}
			if(contract != null)
				pricebookId = contract.getPriceBookId();
			if(jobContract != null)
			{
				location = jobContract.getZone();
				language = jobContract.getLanguage();
			}
			else
			{
				location = "*";
				language = "English";
			}
		}
		}
		
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
		SlateService slateService = (SlateService)ServiceLocator.getInstance().getBean("slateService");

        try
        {
        	if(jobContract != null)
        	{
            List searchSlates = slateService.getSlates(
            		jobContract.getContractCode(),
            		pricebookId,
            		search.getSearchStr().getValue(), // value
            		search.getCriteria().getValue(), // searchType
            		location,
            		DateUtil.formatDate(jobFinishDate, "yyyyMMdd"),
                    language
                  );
            
            


          search.setResults(searchSlates);
        }
        } catch(Throwable t)
	    {
  	      t.printStackTrace();
  	      errors.reject("generic.error", new Object[] {t.getMessage()}, null);
  	      return showForm(request, response, errors);
  		}
       /* catch (Exception e)
        {
          e.printStackTrace();
          errors.reject("search.slate.error", new Object[] { e.getMessage() }, null);
          return showForm(request, response, errors);
        }*/
		return showForm(request, response, errors);
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest, org.springframework.web.bind.ServletRequestDataBinder)
	 */
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
	}



	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		String inputFieldId = request.getParameter("inputFieldId");

		String searchForm = request.getParameter("searchForm");
		String rowNum = request.getParameter("rowNum");
		String chosenContracts = request.getParameter("chosenContracts");
		String divName1 = request.getParameter("div1");
		String divName2 = request.getParameter("div2");
		String nomDate = request.getParameter("nomDate");
		SlateSearch slateSearch = new SlateSearch();
		slateSearch.setSearchForm(searchForm);
		slateSearch.setInputFieldId(inputFieldId);
		slateSearch.setRowNum(rowNum);
		slateSearch.setChosenContracts(chosenContracts);
		slateSearch.setDivName1(divName1);
		slateSearch.setDivName2(divName2);	
		slateSearch.setChosenContracts(chosenContracts);

		if(nomDate != null && (!nomDate.trim().equals("")))
		{
			
			slateSearch.setNominationDate(DateUtil.parseDate(nomDate, "yyyyMMdd"))	;
		}
		else
			slateSearch.setNominationDate(new Date())	;

		return slateSearch;
	}
}
