package com.intertek.phoenix.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import com.intertek.phoenix.esb.EntityPublisher;

/**
 * The abstract base class for handling webservice calls
 * 
 * @author eric.nguyen
 */
public class WebServicePublisher extends AbstractController {
    protected EntityPublisher publisher;
    
    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String entityName=request.getParameter("entity");
        System.out.println("publishing entityName="+entityName);
        publisher.addToQueue(entityName);
        return null;
    }

    public EntityPublisher getPublisher() {
        return publisher;
    }

    public void setPublisher(EntityPublisher publisher) {
        this.publisher = publisher;
    }
}
