package com.intertek.web.controller.job;

import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.domain.AddTest;
import com.intertek.entity.JobManualTest;

public class AddManualTestPopupController extends SimpleFormController {
	public AddManualTestPopupController() {
		super();
		setCommandClass(AddTest.class);
		setSessionForm(true);
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

		AddTest addTest = (AddTest) command;
		JobManualTest jobManualTest = addTest.getJobManualTest();
		String testId = addTest.getJobManualTest().getTestId();
		String testDesc = addTest.getJobManualTest().getTestDescription();
		Double quantity = addTest.getJobManualTest().getQuantity();
		Double totalPrice = addTest.getJobManualTest().getTotalPrice();

			if (testId == null || testId.trim().equals("")) {

				errors.reject("create.test.error",
						new Object[] { "Methodology cannot be null  "
								}, null);
				return showForm(request, response, errors);
			}

			if (quantity == null || quantity.equals("")) {

				errors.reject("create.test.error",
						new Object[] { "quantity cannot be null  "
								}, null);
				return showForm(request, response, errors);
			}

			if (totalPrice == null || totalPrice.equals("")) {

				errors
						.reject(
								"create.test.error",
								new Object[] { "totalPrice cannot be null "
										}, null);
				
				return showForm(request, response, errors);

			}
			if(null == testDesc)
				testDesc="";

		try {
			
			
//			jobManualTest = jobService.addManualTest(jobManualTest);
			 
			 //101771 START -- 
				String submitFlag = request.getParameter("flag");
				 if(null != submitFlag && submitFlag.equals("submit")){
					 JobManualTest jobManualTest1 = new JobManualTest();
					 jobManualTest1.setTestId("");
					 jobManualTest1.setTestDescription("");
					 addTest.setJobManualTest(jobManualTest1);
					 /*addTest.setQuantity(0.0d);
					 addTest.setTotalPrice(0.0d);*/
					 return showForm(request, response, errors);
				 }
			 //101771 END
			 
					
		} catch (Exception e) {
			e.printStackTrace();

			errors.reject("create.test.error", new Object[] { e
					.getMessage() }, null);
			return showForm(request, response, errors);
		}
		addTest.setJobManualTest(jobManualTest);
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

		
		AddTest addTest = new AddTest();
		JobManualTest jobManualTest = new JobManualTest();
		addTest.setJobManualTest(jobManualTest);

		String inputFieldId = request.getParameter("inputFieldId");
		String div1 = request.getParameter("div1");
		String div2 = request.getParameter("div2");
		addTest.setDiv1(div1);
		addTest.setDiv2(div2);
		String submitFlag = request.getParameter("flag");
		 if(null != submitFlag && submitFlag.equals("submit")){
			 JobManualTest jobManualTest1 = new JobManualTest();
			 addTest.setJobManualTest(jobManualTest1);
			 addTest.setQuantity(0.0d);
			 addTest.setTotalPrice(0.0d);	
		 }

		return addTest;

	}
}
