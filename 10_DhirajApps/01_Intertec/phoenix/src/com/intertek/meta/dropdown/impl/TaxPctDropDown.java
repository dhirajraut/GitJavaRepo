package com.intertek.meta.dropdown.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.intertek.entity.JobOrder;
import com.intertek.entity.TaxCode;
import com.intertek.entity.TaxCodeTaxRate;
import com.intertek.entity.TaxRate;
import com.intertek.locator.ServiceLocator;
import com.intertek.meta.dropdown.DropDown;
import com.intertek.meta.dropdown.Field;
import com.intertek.service.JobService;
import com.intertek.service.TaxService;

public class TaxPctDropDown implements DropDown
{
  public List execute(List params)
  {
    List results = new ArrayList();
    List taxRates = new ArrayList();
    int size = params != null ? params.size() : 0;

    //if(size < 2) return results;

    TaxService taxService = (TaxService)ServiceLocator.getInstance().getBean("taxService");
    JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");

    String taxCode = (String)params.get(0);
    String taxType = (String)params.get(1);
    String jobNumber = (String)params.get(2);
    
    JobOrder jobOrder = jobService.getJobOrderByJobNumber(jobNumber);
    
    Field field1 = new Field();
    field1.setName("0.0");
    field1.setValue("0.0");
    results.add(field1);
    
    if(taxType != null && (!taxType.trim().equals("")) && ((taxType.trim().equals("S")||(taxType.trim().equals("V")))))
    {
    	if(taxCode != null)
    	{
    	TaxCode salesTaxCode = taxService.getTaxCodeByCodeWithTaxRates(taxCode);
    	
    	if(salesTaxCode != null)
    	{
    	Set salesTaxRates = salesTaxCode.getTaxCodeTaxRates();
    	if(salesTaxRates != null && salesTaxRates.size() > 0)
    	{
    		
    		Iterator iter = salesTaxRates.iterator();
    		double taxPct = 0;
    		while(iter.hasNext())
    		{
    			TaxCodeTaxRate salesTaxRate = (TaxCodeTaxRate) iter.next();
    			TaxRate effTaxRate = taxService.getTaxRateByTaxCodeAndEffDate(salesTaxRate.getTaxCodeTaxRateId().getTaxCode(),jobOrder.getJobFinishDate(),taxType);
    			if(effTaxRate != null)
    			{
    				taxPct = taxPct + effTaxRate.getTaxPct();
    			}
    		}
    		Field field = new Field();
	        String taxPercent = ((Double)taxPct).toString();
	
	        field.setName(taxPercent);
	        field.setValue(taxPercent);
	        results.add(field);
    	}
    	}
    	}
    	
    }
    else
    {
	    if(taxCode != null && (!taxCode.trim().equals("")))
	    {
	      TaxRate taxRate = taxService.getTaxRateByCode(taxCode);
	
	      if(taxRate != null)
	      {
	          Field field = new Field();
	        String taxPct = ((Double)taxRate.getTaxPct()).toString();
	
	        field.setName(taxPct);
	        field.setValue(taxPct);
	        results.add(field);
	      }
	    }
    }
    return results;
  }

}
