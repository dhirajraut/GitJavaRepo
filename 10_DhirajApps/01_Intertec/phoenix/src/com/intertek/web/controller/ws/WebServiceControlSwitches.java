package com.intertek.web.controller.ws;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.intertek.entity.ControlSwitch;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.WSService;

public class WebServiceControlSwitches extends AbstractController{

	public WebServiceControlSwitches(){
	    super();
	}
	
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
		WSService wsService = (WSService)ServiceLocator.getInstance().getBean("wsService");		
		String doUpdate=request.getParameter("doUpdate");
		if(doUpdate!=null && doUpdate.equalsIgnoreCase("true")){
			try{
				List myList=wsService.getOutboundWSControlSwitches();
				for(int i=0; myList!=null && i<myList.size(); i++){
					ControlSwitch cs=(ControlSwitch)myList.get(i);
					String serviceName=cs.getControlSwitchId().getControlSwitchName();
					String flag=request.getParameter(serviceName);
					if(";enabled;disabled;".indexOf(";"+flag+";")>=0){
						wsService.updateOutboundWSControlSwitch(serviceName, flag);
					}
				}
				request.setAttribute("messageColor", "green");
				request.setAttribute("message", "WEBSERVICE_CONTROL_SWITCH_UPDATED_SUCCESSFULLY");
			}
			catch(Exception e){
				e.printStackTrace();
				request.setAttribute("messageColor", "red");
				request.setAttribute("message", "WEBSERVICE_CONTROL_SWITCH_UPDATED_FAIL");
			}
		}

		Map map=new HashMap();
		map.put("services", wsService.getOutboundWSControlSwitches());
		return new ModelAndView("webservice-control-switches", map);
	}
}
