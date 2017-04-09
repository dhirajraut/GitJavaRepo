package com.intertek.phoenix.web.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;
import org.springframework.web.servlet.view.RedirectView;

import com.intertek.phoenix.esb.EntityPublisher;
import com.intertek.phoenix.util.CommonUtil;
import com.intertek.util.SecurityUtil;

/**
 * SSO With JasperServer
 * 
 * @author eric.nguyen
 */
public class JasperServerController extends AbstractController {
    protected EntityPublisher publisher;

    @Override
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String userToken = SecurityUtil.getLoginToken();
        String jasperServerUrl=CommonUtil.getConfigValue("jasper.server.url");
        return new ModelAndView(new RedirectView(jasperServerUrl), "token", (userToken));
    }

}
