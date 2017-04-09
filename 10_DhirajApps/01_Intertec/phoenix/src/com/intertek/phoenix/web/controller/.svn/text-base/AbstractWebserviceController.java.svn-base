package com.intertek.phoenix.web.controller;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.jdom.Document;
import org.jdom.output.XMLOutputter;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.AbstractController;

import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.tool.oxm.OxmManager;
import com.intertek.tool.transformer.DataTransformer;
import com.intertek.webservice.outbound.spring.SpringWebServiceSender;

/**
 * The abstract base class for handling webservice calls
 * 
 * @author eric.nguyen
 */
public abstract class AbstractWebserviceController extends AbstractController {
	/*
	 * the Object to be sent - this object must be marshalable via the
	 * OxmManager class. See convertToDoc(Object obj) for details.
	 */
	abstract protected Object getObj(HttpServletRequest request, HttpServletResponse response);

	/*
	 * the url to send the object to
	 */
	abstract protected String getUrl();

	protected DataTransformer getDataTransformer(){
		return null;
	}
	/*
	 * soap action
	 */
	protected String getSoapAction(){
		return null;
	}

	/*
	 * given the children classes to have the chance to manipulate the xml
	 * result before return to the view.
	 */
	protected String finalizeResult(String xmlResult){
		return xmlResult;
	}

	protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception{
		try{
			Map model = new HashMap();
			Object obj = getObj(request, response);
			String xml = finalizeResult(send(obj));
			model.put("Content", xml);
			View view = (View) getApplicationContext().getBean("xmlView");
			return new ModelAndView(view, model);
		}
		catch (Exception e){
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/*
	 * perform the sending and return the result xml
	 */
	public String send(Object obj){
		if(obj == null){
			return null;
		}

		try{
			String content = null;
			if(obj instanceof String){
				content = (String) obj;
			}
			else{
				Document doc = convertToDoc(obj);
				content = convertToXml(doc);
			}
			StreamSource source = new StreamSource(new StringReader(content));
			StringWriter resultSW = new StringWriter();
			StreamResult result = new StreamResult(resultSW);
			getWebserviceSender().sendSourceAndReceiveToResult(source, result);
			return resultSW.toString();
		}
		catch (Exception e){
			e.printStackTrace();
			throw new ServiceException(e.getMessage(), e);
		}
	}

	/*
	 * convert the given object to xml Document using OxmManager
	 */
	public Document convertToDoc(Object obj){
		OxmManager oxmManager = (OxmManager) ServiceLocator.getInstance().getBean("oxmManager");
		Document doc = oxmManager.marshal(obj);
		return doc;
	}

	/*
	 * convert to xml document to xml String
	 */
	public String convertToXml(Document doc) throws IOException{
		Document myDoc=doc;
		
		DataTransformer dataTransformer=getDataTransformer();		
		if(dataTransformer != null){
			myDoc = dataTransformer.transform(myDoc);
		}
		
		StringWriter sw = new StringWriter();
		XMLOutputter xmlOut = new XMLOutputter();
		xmlOut.output(myDoc.getRootElement(), sw);
		String content = sw.toString();
		return content;
	}

	/*
	 * create a webserviceSender to perform the sending
	 */
	public SpringWebServiceSender getWebserviceSender(){
		SpringWebServiceSender swss = new SpringWebServiceSender();
		swss.setUrl(getUrl());
		swss.setSoapAction(getSoapAction());
		return swss;
	}
}
