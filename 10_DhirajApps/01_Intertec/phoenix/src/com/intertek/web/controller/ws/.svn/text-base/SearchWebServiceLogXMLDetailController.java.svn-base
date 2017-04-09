package com.intertek.web.controller.ws;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.AbstractController;

import com.intertek.entity.WebServiceEntity;
import com.intertek.entity.WebServiceEntityInbound;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.WSService;

public class SearchWebServiceLogXMLDetailController extends AbstractController{
	
	public SearchWebServiceLogXMLDetailController(){
	    super();
	}

	@Override
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		WSService wsService=(WSService)ServiceLocator.getInstance().getBean("wsService");
		Long id=Long.parseLong(request.getParameter("id"));
		String bound=request.getParameter("inOutBoundInd");
		String column=request.getParameter("column");
		HashMap map=new HashMap();

		if(bound.equalsIgnoreCase("outbound")){
			WebServiceEntity entity=wsService.getWebServiceEntityById(id);
			if(column.equalsIgnoreCase("Content")){
				map.put("Content", entity.getContent());
			}
			else if(column.equalsIgnoreCase("result")){
				map.put("Content", entity.getResult());
			}
			else{
				map.put("Content", "<message>"+entity.getMessage()+"</message>");
			}
		}
		else{
			WebServiceEntityInbound entity=wsService.getWebServiceEntityInboundById(id);
			if(column.equalsIgnoreCase("Content")){
				map.put("Content", entity.getContent());
			}
			else if(column.equalsIgnoreCase("result")){
				map.put("Content", entity.getResult());
			}
			else{
				map.put("Content", "<message>"+entity.getMessage()+"</message>");
			}			
		}

		View view=(View)getApplicationContext().getBean("xmlView");
		return new ModelAndView(view, map);
	}
}
