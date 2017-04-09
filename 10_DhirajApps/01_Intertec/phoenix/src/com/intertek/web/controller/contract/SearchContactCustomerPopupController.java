package com.intertek.web.controller.contract;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.BindException;
import org.springframework.web.bind.ServletRequestDataBinder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.SimpleFormController;

import com.intertek.domain.ContactSearch;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.service.CustomerService;

public class SearchContactCustomerPopupController extends SimpleFormController {
	public SearchContactCustomerPopupController() {
		super();
		setCommandClass(ContactSearch.class);
		setSessionForm(true);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.mvc.SimpleFormController#onSubmit(javax
	 * .servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse,
	 * java.lang.Object, org.springframework.validation.BindException)
	 */
	protected ModelAndView onSubmit(HttpServletRequest request,
			HttpServletResponse response, Object command, BindException errors)
			throws Exception {
		ContactSearch search = (ContactSearch) command;
		CustomerService customerService = (CustomerService) ServiceLocator
				.getInstance().getBean("customerService");

		String pageNumberStr = request.getParameter("pageNumber");
		String sortFlag = request.getParameter("sortFlag");
		String submitFlag = request.getParameter("submitFlag");

		int pageNumber;
		if (pageNumberStr == null || pageNumberStr.trim().equals("")) {
			pageNumber = 1;
		} else {
			pageNumber = Integer.parseInt(pageNumberStr);
		}

		Pagination pagination = new Pagination(1, 20, pageNumber, 10);
		search.setPagination(pagination);

		if (sortFlag != null && !sortFlag.trim().equals("")) {

			try {
				customerService.getContractCustContacts(search, sortFlag);
			} catch (ServiceException e) {
				e.printStackTrace();
				errors.reject(e.getMessage(), e.getParams(), null);
				return showForm(request, response, errors);
			} catch (Throwable t) {
				t.printStackTrace();
				errors.reject("generic.error", new Object[] { t.getMessage() },
						null);
				return showForm(request, response, errors);
			}

			return showForm(request, response, errors);
		}

		if ((submitFlag != null) && "true".equals(submitFlag)) {

			search.setSubmitFlag("none");
			try {
				customerService.getContractCustContacts(search, sortFlag);
			} catch (ServiceException e) {
				e.printStackTrace();
				errors.reject(e.getMessage(), e.getParams(), null);
				return showForm(request, response, errors);
			} catch (Throwable t) {
				t.printStackTrace();
				errors.reject("generic.error", new Object[] { t.getMessage() },
						null);
				return showForm(request, response, errors);
			}

		}

		request.setAttribute("command", search);
		return showForm(request, response, errors);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.mvc.BaseCommandController#initBinder(
	 * javax.servlet.http.HttpServletRequest,
	 * org.springframework.web.bind.ServletRequestDataBinder)
	 */
	protected void initBinder(HttpServletRequest request,
			ServletRequestDataBinder binder) throws Exception {
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.springframework.web.servlet.mvc.AbstractFormController#formBackingObject
	 * (javax.servlet.http.HttpServletRequest)
	 */
	protected Object formBackingObject(HttpServletRequest request)
			throws Exception {
		ContactSearch contactSearch = new ContactSearch();
		System.out.println("In formbacking contractCode::"
				+ request.getParameter("contractCode"));
		contactSearch.setContractCode(request.getParameter("contractCode"));
		return contactSearch;
	}
}
