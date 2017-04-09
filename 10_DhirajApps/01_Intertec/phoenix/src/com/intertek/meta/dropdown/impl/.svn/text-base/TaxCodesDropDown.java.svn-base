package com.intertek.meta.dropdown.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeSet;

import com.intertek.entity.TaxCode;
import com.intertek.entity.TaxRate;
import com.intertek.locator.ServiceLocator;
import com.intertek.meta.dropdown.DropDown;
import com.intertek.meta.dropdown.Field;
import com.intertek.service.TaxService;
import com.intertek.util.TaxUtil;

public class TaxCodesDropDown implements DropDown
{
  public List execute(List params)
  {
    List results = new ArrayList();
    List taxRates = new ArrayList();
    List salesTaxCodes = new ArrayList();
    HashMap map = new LinkedHashMap();
    int size = params != null ? params.size() : 0;

    //if(size < 2) return results;

    TaxService taxService = (TaxService)ServiceLocator.getInstance().getBean("taxService");

    String taxType = (String)params.get(0);
    String buName = (String)params.get(1);
    String serviceLocationCode = (String)params.get(2);
    String custCode = (String)params.get(3);
    String invoiceDate = (String)params.get(4);
    String contactid= (String)params.get(5);
    long contactId=Long.valueOf(contactid).longValue();
    

    if(taxType.trim().equals("S"))
    {
      //taxRates = taxService.getTaxRatesByType(taxType);
    	salesTaxCodes = taxService.getAllTaxCodes(taxType);
    	Field field1 = new Field();
        field1.setName("");
        field1.setValue("");
        results.add(field1);
        
        if(salesTaxCodes != null)
        {
          for(int i=0; i<salesTaxCodes.size(); i++)
          {
        	  TaxCode salesTaxCode = (TaxCode)salesTaxCodes.get(i);

            Field field = new Field();
            String taxcode = salesTaxCode.getTaxCodeHeader();
            String description = salesTaxCode.getTaxDescr();
            if(description != null) description =  taxcode + " - " + description;
            else 
            	description = taxcode;
            field.setName(description);
             field.setValue(taxcode);
            results.add(field);
          }
        }

           return results;
    }
    else if(taxType.trim().equals("V"))
    {
      taxRates = TaxUtil.determineVATCodesByCountry(buName, serviceLocationCode, custCode,contactId, invoiceDate,"multiple");

    Field field1 = new Field();
    field1.setName("");
    field1.setValue("");
    results.add(field1);
    if(taxRates != null)
    {
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
		Date jobFinishDate= new Date();
		try {
			jobFinishDate = sdf.parse(invoiceDate);
		} catch (ParseException e) {
		}
        for(int i=0; i<taxRates.size(); i++)
        {
	        TaxCode taxCode = (TaxCode)taxRates.get(i);
	        Field field = new Field();
	        String taxcode = taxCode.getTaxCodeHeader();
	        //The description should come from TaxRate which has the right pct according to the effective date
	        TaxRate taxRate = taxService.getTaxRateByTaxCodeHdr(taxcode, jobFinishDate);
	        String description = taxRate.getDescription();
	        if(description != null) description =  taxcode + " - " + description;
	        else 
	        	description = taxcode;
	        field.setName(description);
	         field.setValue(taxcode);
	         map.put(field.getName(),field.getValue());
        }
        List mapKeys = new ArrayList(map.keySet());
  	    TreeSet sortedSet = new TreeSet(mapKeys);
  	
  	    Iterator itrr=sortedSet.iterator();
  	    int sortSize=sortedSet.size();
  	    while(sortSize>0)
  	    {
	  		Field field = new Field();
	  		field.setName((String)itrr.next());
	  		field.setValue((String)map.get(field.getName()));
	  		results.add(field);
	  		sortSize--;
	    }
    }

       return results;
  }
    return null;
  }
}
