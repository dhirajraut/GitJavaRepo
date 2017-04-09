package com.intertek.web.controller.job;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.domain.TestSearch;
import com.intertek.entity.CfgContract;
import com.intertek.entity.JobContract;
import com.intertek.entity.JobOrder;
import com.intertek.entity.PriceBook;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.CalculatorService;
import com.intertek.service.JobService;
import com.intertek.service.TestService;
import com.intertek.util.Constants;
import com.intertek.util.DateUtil;

public class SearchTestPopupController extends SimpleFormController {
	public SearchTestPopupController() {
		super();
		setCommandClass(TestSearch.class);
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
		TestSearch search = (TestSearch) command;
		
	/*	if(submitFlag != null && (!submitFlag.trim().equals("")))
		{
			if(submitFlag.equals("addTest"))
			{
				request.setAttribute("command", search);
				return showForm(request, response, errors);
			}
		}
*/
		int pageNumber = 1;
		try {
			pageNumber = new Integer(pageNumberStr).intValue();
		} catch (Exception e) {
		}
		if (pageNumber <= 0)
			pageNumber = 1;

		
		Pagination pagination = new Pagination(1, 20, pageNumber, 10);
		search.setPagination(pagination);
		search.setSearchForm(searchForm);
		search.setPageNo(pageNumberStr);
		
		JobService jobService = (JobService) ServiceLocator.getInstance().getBean("jobService");
		CalculatorService calculatorService = (CalculatorService) ServiceLocator.getInstance().getBean("calculatorService");
		
		CfgContract cfgContract = null;
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
			
				
			      CfgContract contract = calculatorService.getContractByContractId(
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

			    	        

			    	        
				//cfgContract = jobService.getCfgContractByContractCode(jobContract.getContractCode());
				
			}
			if(contract != null)
				pricebookId = contract.getPriceBookId();
				location = jobContract.getZone();
				language = jobContract.getLanguage();
			}
			else
			{
				location = "*";
				language = "English";
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
		
        TestService testService = (TestService) ServiceLocator.getInstance().getBean("testService");

        try
        {
        	if(jobContract != null)
        	{
          List searchTests = testService.getTests(
            jobContract.getContractCode(),
            pricebookId,
            search.getProductGroup().getValue(),
            search.getCriteria1().getValue(),
            search.getTestSearch().getValue(),
            search.getCriteria2().getValue(),
            location,
            DateUtil.formatDate(jobFinishDate, "yyyyMMdd"),
            language
          );

          search.setResults(searchTests);
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
          errors.reject("search.test.error", new Object[] { e.getMessage() }, null);
          return showForm(request, response, errors);
        }*/


		//request.setAttribute("command", search);

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
		

		TestSearch testSearch = new TestSearch();
		testSearch.setSearchForm(searchForm);
		testSearch.setInputFieldId(inputFieldId);
		testSearch.setRowNum(rowNum);
		testSearch.setChosenContracts(chosenContracts);
		testSearch.setDivName1(divName1);
		testSearch.setDivName2(divName2);
		if(nomDate != null && (!nomDate.trim().equals("")))
		{
			
			testSearch.setNominationDate(DateUtil.parseDate(nomDate, "yyyyMMdd"))	;
		}
		else
			testSearch.setNominationDate(new Date())	;
		
			

		
		
		//StringSearchField vesselSetSearch = new StringSearchField(Constants.CONTAINS);
		//vesselSetSearch.setValue("Pricebook");
		//vesselSearch.setVesselSet(vesselSetSearch);
		

		return testSearch;
	}
}
