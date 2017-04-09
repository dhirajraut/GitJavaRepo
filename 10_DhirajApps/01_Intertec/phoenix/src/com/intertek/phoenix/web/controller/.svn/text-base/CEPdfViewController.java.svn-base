/*
 * JobOrderCEPdfViewController.java
 * 
 * @version
 * 
 * Jun 25, 2009
 * 
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 */
package com.intertek.phoenix.web.controller;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.AbstractController;

import com.intertek.phoenix.ServiceManager;
import com.intertek.phoenix.entity.CEInvoice;
import com.intertek.phoenix.entity.CEJobOrder;
import com.intertek.phoenix.report.ReportService;

/**
 * The Class JobOrderCEPdfViewController.
 * 
 * @author Patni
 */
public class CEPdfViewController extends AbstractController {
    /**
     * Instantiates a new job order ce pdf view controller.
     */
    public CEPdfViewController() {
    }

    /*
     * (non-Javadoc)
     * 
     * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest,
     *      javax.servlet.http.HttpServletResponse)
     */
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        ReportService reportService=null;
        byte[] pdfData =null;
        Map<String,Object> model = new HashMap<String,Object>();
        if(request.getParameter("jobNumber")!=null){
        String jobNumber = request.getParameter("jobNumber");
        model.put("FileName", jobNumber+ "_JobOrder" + ".pdf");
        reportService = ServiceManager.getReportService();
        CEJobOrder jobOrder = new CEJobOrder();
        jobOrder.setJobNumber(jobNumber);
        pdfData = reportService.generateJobOrderPdf(jobOrder);
        }
        else if(request.getParameter("invNum")!=null)
        {
            String invoiceNumber = request.getParameter("invNum");
            reportService = ServiceManager.getReportService();
            model.put("FileName", invoiceNumber+ "_Preview" + ".pdf");
            CEInvoice ceinvoice = new CEInvoice();
            ceinvoice.setInvoiceNumber(invoiceNumber);
            pdfData = reportService.generateInvoicePreviewPdf(ceinvoice);
        }
       model.put("Content", pdfData);
       View view = (View) getApplicationContext().getBean("pdfView");
       return new ModelAndView(view, model);
    }
}
