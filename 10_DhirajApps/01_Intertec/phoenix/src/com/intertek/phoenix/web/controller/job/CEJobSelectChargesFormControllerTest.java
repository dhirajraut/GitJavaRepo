/**
 * This file is auto generated by Achilles Version 0.1, the TestNG test case generator 
 * for the Phoenix project 
 * Copyright: Intertek 2009
 * Author: Richard Qin
 */

package com.intertek.phoenix.web.controller.job;

import java.util.*;

import javax.servlet.http.HttpServletResponse;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.validation.BindException;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.testng.ITestContext;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.intertek.locator.ServiceLocator;


/**
 * Test case for com.intertek.phoenix.job.CEJobSrvcImpl
 *
 */
public class CEJobSelectChargesFormControllerTest {

    @BeforeClass
    public void setupClass() {
        
        
        // TODO do your class-wise setup here
    }

    @AfterClass
    public void tearDownClass() {
        // TODO do your class-wise cleanup here
    }

    @BeforeMethod
    public void setupTest() {
        // TODO do your pre-test setup here
    }

    @AfterMethod
    public void tearDownTest() {
        // TODO do your post-test cleanup here
    }

    
    @Test(groups = { "unit" })
    public void test_createTest_1() {
        try {
            ApplicationContext applicationContext = 
                new FileSystemXmlApplicationContext("D:\\myWorkspace\\phoenix\\web\\WEB-INF\\applicationContext.xml");
            ServiceLocator.getInstance().setApplicationContext(applicationContext);
           
            MockHttpServletRequest request = new MockHttpServletRequest();
            
            request.addParameter("operation", "refresh");
            request.addParameter("jobNumber", "US101-0004809");
            request.setMethod("POST");
            request.setRequestURI("http://localhost:8080/phoenix/phx_ce_job_select_charges.htm");
            HttpServletResponse response = new MockHttpServletResponse();
            CEJobSelectChargesFormController selectChargesController = new CEJobSelectChargesFormController();
            ModelAndView modelAndView = selectChargesController.handleRequest(request, response);
            //ModelAndView modelAndView = selectChargesController.
            //onSubmit(request, response, new CESelectChargeForm(), new BindException(new Object(), ""));
            assert modelAndView!=null;
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    
}
