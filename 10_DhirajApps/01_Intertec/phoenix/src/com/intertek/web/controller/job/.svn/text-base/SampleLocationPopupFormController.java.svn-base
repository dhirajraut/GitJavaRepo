package com.intertek.web.controller.job;



import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;
import com.intertek.domain.AddJobOrder;


public class SampleLocationPopupFormController extends SimpleFormController {
	public SampleLocationPopupFormController() {
		super();
		setCommandClass(AddJobOrder.class);		
	}

	/* (non-Javadoc)
	 * @see org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, java.lang.Object, org.springframework.validation.BindException)
	 */
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		AddJobOrder addJobOrder = (AddJobOrder)command;		
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

		String searchForm = request.getParameter("searchForm");		
		String divName1 = request.getParameter("div1");
		String divName2 = request.getParameter("div2");	
		AddJobOrder addJobOrder= new AddJobOrder();
		addJobOrder.setSearchForm(searchForm);
		addJobOrder.setDiv1(divName1);
		addJobOrder.setDiv2(divName2);
		return addJobOrder;
	}
}
