package com.intertek.web.controller.job;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.domain.CopyProduct;

public class CopyProductPopupController extends SimpleFormController {
	public CopyProductPopupController() {
		super();
		setCommandClass(CopyProduct.class);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest,
	 *      javax.servlet.http.HttpServletResponse, java.lang.Object,
	 *      org.springframework.validation.BindException)
	 */
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {

	
				
				return showForm(request, response, errors);

			}

	

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.mvc.BaseCommandController#initBinder(javax.servlet.http.HttpServletRequest,
	 *      org.springframework.web.bind.ServletRequestDataBinder)
	 */
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(java.util.Date.class, null,
				new CustomDateEditor(dateFormat, true));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject(javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {

		
		CopyProduct copyProduct = new CopyProduct();
		

		String searchForm = request.getParameter("searchForm");
		String vesselIndex = request.getParameter("vesselIndex");
		String productIndex = request.getParameter("productIndex");
		String vesselCount = request.getParameter("vesselCount");
		
		copyProduct.setProductIndex(productIndex);
		copyProduct.setVesselIndex(vesselIndex);
		copyProduct.setSearchForm(searchForm);
		copyProduct.setVesselCount(Integer.parseInt(vesselCount));
		
		String[] vesselNames = new String[Integer.parseInt(vesselCount)];
		for(int i=0;i<Integer.parseInt(vesselCount);i++)
		{
			int counter = i + 1; 
			String vesselName = "Vessel "+counter;
			vesselNames[i] = vesselName;
		}
		copyProduct.setVesselNames(vesselNames);
		System.out.println("vesselnames size :"+copyProduct.getVesselNames().length);


		return copyProduct;

	}
}
