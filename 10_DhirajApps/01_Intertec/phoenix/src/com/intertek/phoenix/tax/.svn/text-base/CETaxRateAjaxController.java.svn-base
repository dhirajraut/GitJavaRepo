/*
 * Copyright © 2009 Intertek Group, plc. All Rights Reserved.
 *  
 * Reproduction or use of this file without express written consent is prohibited.
 */
package com.intertek.phoenix.tax;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import com.intertek.phoenix.util.DateUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.ajaxtags.helpers.AjaxXmlBuilder;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.AbstractController;
import com.intertek.entity.TaxCode;
import com.intertek.entity.TaxCodeTaxRate;
import com.intertek.entity.TaxRate;
import com.intertek.phoenix.ServiceManager;

/**
 * @author patni
 *
 */
public class CETaxRateAjaxController extends AbstractController {

    /**
     * .ctor
     */
    public CETaxRateAjaxController() {
    }

    /* (non-Javadoc)
     * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
     */
    protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {

        TaxSrvc taxSrvc = ServiceManager.getTaxSrvc();
        String taxCode = request.getParameter("taxcode");
        String taxType = request.getParameter("taxType");
        String taxDateStr = request.getParameter("taxDate");
        Date taxDate = null;
        if(taxDateStr ==null || taxDateStr.trim().equals("")){
            taxDate = new java.util.Date();
        }
        else {
            taxDate=DateUtil.stringToDate(taxDateStr);
        }
        String taxPercent = "";
        String xml = "";

        if (taxCode != null && (!taxCode.trim().equals(""))) {
            if (taxType != null && (taxType.trim().equals("S") || (taxType.trim().equals("V")))) {

                TaxCode myTaxCode = taxSrvc.getTaxCodeByCode(taxCode);
                Set<TaxCodeTaxRate> salesTaxRates = myTaxCode.getTaxCodeTaxRates();
                if (salesTaxRates != null && salesTaxRates.size() > 0) {

                    Iterator<TaxCodeTaxRate> iter = salesTaxRates.iterator();
                    double taxPct = 0;
                    while (iter.hasNext()) {
                        TaxCodeTaxRate salesTaxRate =  iter.next();
                        TaxRate effTaxRate = taxSrvc.getTaxRate(salesTaxRate.getTaxCodeTaxRateId().getTaxCode(),taxDate, taxType);
                        if (effTaxRate != null) {
                            taxPct = taxPct + effTaxRate.getTaxPct();
                        }
                    }
                    taxPercent = ((Double) taxPct).toString();
                }
            }
        }

        xml = new AjaxXmlBuilder().addItem(taxPercent, taxPercent).toString();

        Map<String, String> model = new HashMap<String, String>();
        model.put("Content", xml);

        View view = (View) getApplicationContext().getBean("xmlView");
        return new ModelAndView(view, model);
    }
}
