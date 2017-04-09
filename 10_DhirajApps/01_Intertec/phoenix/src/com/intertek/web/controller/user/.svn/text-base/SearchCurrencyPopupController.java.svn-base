package com.intertek.web.controller.user;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import com.intertek.exception.ServiceException;
import com.intertek.domain.CurrencySearch;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.CurrencyService;

public class SearchCurrencyPopupController extends SimpleFormController
{
  public SearchCurrencyPopupController() {
    super();
    setCommandClass(CurrencySearch.class);
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
    String inputFieldId = request.getParameter("inputFieldId");	
	String searchForm = request.getParameter("searchForm");
    int pageNumber = 1;
    try
    {
      pageNumber = new Integer(pageNumberStr).intValue();
    }
    catch(Exception e)
    {
    }
    if(pageNumber <= 0) pageNumber = 1;

    CurrencySearch search = (CurrencySearch)command;

    Pagination pagination = new Pagination(1, 20, pageNumber, 10);
    search.setPagination(pagination);
	search.setSearchForm(searchForm);

    CurrencyService currencyService = (CurrencyService)ServiceLocator.getInstance().getBean("currencyService");	
	
	try
    {
		currencyService.searchCurrency(search);
	}
	/*catch(Exception e)
    {
      e.printStackTrace();
     errors.reject("search.currency.error", new Object[] {e.getMessage()}, null);
      return showForm(request, response, errors);
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

  protected Object formBackingObject(
    HttpServletRequest request
  ) throws Exception
  {
	
	  String inputFieldId = request.getParameter("inputFieldId");
	  CurrencySearch currencySearch = new CurrencySearch();
	  currencySearch.setInputFieldId(inputFieldId);
	  return currencySearch;
  }
}
