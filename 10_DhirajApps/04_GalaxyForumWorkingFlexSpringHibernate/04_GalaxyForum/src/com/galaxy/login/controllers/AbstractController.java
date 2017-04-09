package com.galaxy.login.controllers;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.galaxy.login.utils.Constants;
import com.galaxy.utils.XMLGenerator;
import com.galaxy.utils.XMLView;

public class AbstractController extends SimpleFormController {
	public String buildContentsXML (final String result) {
		StringBuilder sb = new StringBuilder();
		sb.append(Constants.SERVER_RESPONSE_START_TAG)
			.append(result)
			.append(Constants.SERVER_RESPONSE_END_TAG);
		
		return sb.toString();
	}
	
	public ModelAndView createModelAndView(final Object object, final boolean isXML) {
		
		View view = new XMLView();
		Map<String, String> model = new HashMap<String, String>();
		String result = "";
		if(isXML) {
			result = XMLGenerator.generateXML(object);
		}
		else {
			result = object.toString();
		}
		System.out.println("************ Result = " + result);
		model.put(Constants.SERVER_RESPONSE_MODEL_KEY,
								buildContentsXML(result));
		return new ModelAndView(view, model);
	}
}
