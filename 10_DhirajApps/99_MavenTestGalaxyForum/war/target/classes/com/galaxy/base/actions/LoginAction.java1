package com.galaxy.base.actions;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;

import org.apache.struts2.interceptor.ServletRequestAware;

import com.galaxy.base.constants.ApplicationConstants;

/**
 * @author 284773
 *
 */
public class LoginAction implements ServletRequestAware{

	private ServletRequest request;
	
    public ServletRequest getServletRequest() {
		return request;
	}

	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

    /**
     * @return success/failure
     */
	public final String execute() {
		System.out.println("username = " + request.getParameter("username"));
		if (request.getParameter("username").equalsIgnoreCase("Dhiraj")) {
            return ApplicationConstants.RESULT_SUCCESS;
        }

        return ApplicationConstants.RESULT_FAILURE;
    }
}
