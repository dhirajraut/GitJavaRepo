package com.intertek.web.controller.ws;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.domain.WebServiceLogDetailSearch;
import com.intertek.entity.WebServiceEntity;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.WSService;

public class SearchWebServiceLogDetailController extends SimpleFormController{
	
	public SearchWebServiceLogDetailController(){
	    super();
	    setCommandClass(WebServiceLogDetailSearch.class);
	}
	
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception{
		WebServiceLogDetailSearch search = (WebServiceLogDetailSearch)command;
		WSService wsService = (WSService)ServiceLocator.getInstance().getBean("wsService");

		List<WebServiceEntity> result=null;
		
		String key=search.getKey();
		
		if(key!=null && !key.trim().isEmpty()){
			if(search.getInOutBoundInd().equalsIgnoreCase("outbound")){
				result=wsService.getWebServiceDetailLog(key, search.getType(), search.getBooleanStatus(), search.getPagination(), search.getSortBy(), search.isSortDesc());
			}
			else{
				result=wsService.getWebServiceDetailInboundLog(key, search.getType(), search.getBooleanStatus(), search.getPagination(), search.getSortBy(), search.isSortDesc());
			}			
		}
		else{
			String dateFormat="MM/dd/yyyy";
			Date fromDate=search.getFromDate(dateFormat);
			Date toDate=search.getToDate(dateFormat);
			if(search.getInOutBoundInd().equalsIgnoreCase("outbound")){
				result=wsService.getWebServiceDetailLog(fromDate, toDate, search.getType(), search.getBooleanStatus(), search.getPagination(), search.getSortBy(), search.isSortDesc());
			}
			else{
				result=wsService.getWebServiceDetailInboundLog(fromDate, toDate, search.getType(), search.getBooleanStatus(), search.getPagination(), search.getSortBy(), search.isSortDesc());
			}
		}
		
		
		search.setResults(result);
		search.getPagination().calculatePages();
		return showForm(request, response, errors);
	}
}
