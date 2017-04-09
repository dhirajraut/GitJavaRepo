package com.intertek.web.controller.ws;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.domain.WebServiceLogSearch;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.WSService;

public class SearchWebServiceLogController extends SimpleFormController{
	
	public SearchWebServiceLogController(){
	    super();
	    setCommandClass(WebServiceLogSearch.class);
	}
	
	protected ModelAndView onSubmit(HttpServletRequest request, HttpServletResponse response, Object command, BindException errors) throws Exception{
		WebServiceLogSearch search = (WebServiceLogSearch)command;
		WSService wsService = (WSService)ServiceLocator.getInstance().getBean("wsService");

		String key=search.getKey();
		List outbound;
		List inbound;
		
		if(key!=null && !key.trim().isEmpty()){
			outbound=wsService.getWebServiceLog(key);
			inbound=wsService.getWebServiceInboundLog(key);				
		}
		else{
			String dateFormat="MM/dd/yyyy";	
			Date fromDate=search.getFromDate(dateFormat);
			Date toDate=search.getToDate(dateFormat);
			outbound=wsService.getWebServiceLog(fromDate, toDate);
			inbound=wsService.getWebServiceInboundLog(fromDate, toDate);
		}
		
		List result=new ArrayList();
		result.add(outbound);
		result.add(inbound);
		search.setResults(result);
		return showForm(request, response, errors);
	}
}
