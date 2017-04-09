package com.intertek.web.controller.user;

import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ajaxtags.helpers.AjaxXmlBuilder;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.mvc.AbstractController;

import com.intertek.entity.JobOrder;
import com.intertek.entity.TaxCode;
import com.intertek.entity.TaxCodeTaxRate;
import com.intertek.entity.TaxRate;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.JobService;
import com.intertek.service.TaxService;

public class TaxRateController extends AbstractController
{
  private static Log log = LogFactory.getLog(BranchController.class);

  /**
   * .ctor
   */
  public TaxRateController() {
  }

  /* (non-Javadoc)
   * @see org.springframework.web.servlet.mvc.AbstractController#handleRequestInternal(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
   * <context dynamic="true|false">
       <User Name="jaloia111" />
       <Type Name="MANIFEST_FILE" />
     </context>
   */
  protected ModelAndView handleRequestInternal(
    HttpServletRequest request,
    HttpServletResponse response
  ) throws Exception
  {
	 TaxService taxService = (TaxService)ServiceLocator.getInstance().getBean("taxService");
	 JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
	 
	 String xml ="";
	  String taxCode = "";
	  String taxType = "";
	  String jobNumber  = "";
	  JobOrder jobOrder = null;
	  Date jobFinishDate;
	  String taxPercent = "0.0";
	  
	  System.out.println("inside TaxRateController");
	  if(request.getParameter("taxcode") != null && (!request.getParameter("taxcode").trim().equals("")))
	  {
		  taxCode = request.getParameter("taxcode");
		  taxType = request.getParameter("taxType");
		  jobNumber = request.getParameter("jobNumber");
		  System.out.println("inside taxCode :"+taxCode);
		  System.out.println("inside taxType :"+taxType);
		  System.out.println("inside jobNumber :"+jobNumber);
		  
		  if(taxType != null && (taxType.trim().equals("S") || (taxType.trim().equals("V"))))
		  {
			  if(jobNumber != null)
				  jobOrder = jobService.getJobOrderByJobNumber(jobNumber);
			  if(jobOrder != null)
				  jobFinishDate = jobOrder.getJobFinishDate();
			  
		    	TaxCode myTaxCode = taxService.getTaxCodeByCodeWithTaxRates(taxCode);
		    	
		    	Set salesTaxRates = myTaxCode.getTaxCodeTaxRates();
		    	if(salesTaxRates != null && salesTaxRates.size() > 0)
		    	{
		    		 System.out.println("inside salesTaxRates not null :"+salesTaxRates.size());
		    		Iterator iter = salesTaxRates.iterator();
		    		double taxPct = 0;
		    		while(iter.hasNext())
		    		{
		    			TaxCodeTaxRate salesTaxRate = (TaxCodeTaxRate) iter.next();
		    			System.out.println("parameter for eff tax rate :"+myTaxCode.getTaxCodeHeader()+","+jobOrder.getJobFinishDate());
		    			TaxRate effTaxRate = taxService.getTaxRateByTaxCodeAndEffDate(salesTaxRate.getTaxCodeTaxRateId().getTaxCode(),jobOrder.getJobFinishDate(),taxType);
		    			if(effTaxRate != null)
		    			{
		    				System.out.println("taxpct not null");
		    				taxPct = taxPct + effTaxRate.getTaxPct();
		    			}
		    		}
		    		
			        taxPercent = ((Double)taxPct).toString();
		    	}
		    	
		    	xml = new AjaxXmlBuilder().addItem(taxPercent, taxPercent).toString();
		    	
				  Map model = new HashMap();
				  model.put("Content", xml);
		
				  View view = (View)getApplicationContext().getBean("xmlView");
				  return new ModelAndView(view, model);
		  }
		  else
		  {
			  TaxRate tax = taxService.getTaxRateByCode(taxCode);
	
		
	  		  xml = new AjaxXmlBuilder().addItem(((Double) tax.getTaxPct()).toString(), ((Double) tax.getTaxPct()).toString()).toString();
	
			  Map model = new HashMap();
			  model.put("Content", xml);
	
			  View view = (View)getApplicationContext().getBean("xmlView");
			  return new ModelAndView(view, model);
		  }
		  
	  }
	   Map model = new HashMap();
	    model.put("Content", xml);

	    View view = (View)getApplicationContext().getBean("xmlView");

	    return new ModelAndView(view, model);
  }

}