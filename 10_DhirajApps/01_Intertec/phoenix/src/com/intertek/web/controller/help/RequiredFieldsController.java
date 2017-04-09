package com.intertek.web.controller.help;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.intertek.entity.RequiredField;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.RequiredFieldService;

public class RequiredFieldsController extends AbstractController{
	public RequiredFieldsController(){
	    super();
	}
	
	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response){
		RequiredFieldService rfs=(RequiredFieldService)ServiceLocator.getInstance().getBean("requiredFieldService");
		List<RequiredField> list=rfs.getRequiredFields();
	    Map command = new HashMap();
	    command.put("rfs", list);
	    return new ModelAndView("required-fields", "command", command);		
	}
}
