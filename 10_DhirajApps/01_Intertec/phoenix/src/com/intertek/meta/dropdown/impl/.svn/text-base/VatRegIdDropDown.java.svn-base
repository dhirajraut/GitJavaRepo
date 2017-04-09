package com.intertek.meta.dropdown.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.TreeSet;

import com.intertek.entity.Country;
import com.intertek.entity.CustVatRegistration;
import com.intertek.locator.ServiceLocator;
import com.intertek.meta.dropdown.DropDown;
import com.intertek.meta.dropdown.Field;
import com.intertek.service.CustomerService;

public class VatRegIdDropDown implements DropDown
{
  public List execute(List params)
  {
    List results = new ArrayList();
    int size = params != null ? params.size() : 0;
    
    //Commenting out because if no params then we need to display all BUs across all Orgs (mainly for Job Flow Page)
    if(size == 0) return results;
    
    String custCode = (String)params.get(0);

    CustomerService customerService = (CustomerService)ServiceLocator.getInstance().getBean("customerService");
    
    List vatRegIds = null;

    vatRegIds = customerService.getCustomerVatRegistrationsByCustCode(custCode);
    
    HashMap map = new LinkedHashMap();
    Field field1 = new Field();
    field1.setName("");
    field1.setValue("");
    results.add(field1);
    
    if(vatRegIds != null && vatRegIds.size() > 0)
    {
	    for(int i=0; i<vatRegIds.size(); i++)
	    {
	    	  CustVatRegistration custVatReg = (CustVatRegistration) vatRegIds.get(i);
	    	  String ctryCode = "";
	    	  if(custVatReg != null){
	    	  Country custVatRegCtry = custVatReg.getCountry();
	    	  if(custVatRegCtry != null)
	    	  {
	    		  ctryCode = custVatRegCtry.getCountry2();
	    		  if(ctryCode==null)
	    			  ctryCode="";
	    	  }
	
		      Field field = new Field();
		      String name = custVatReg.getCustVatRegistrationId().getRegistrationId();
		      String displayName = "";
		      if(!ctryCode.trim().equals(""))
		    	  displayName = ctryCode + ": " + custVatReg.getCustVatRegistrationId().getRegistrationId();
		      else
		    	  displayName = name;
		      field.setName(displayName);
		      field.setValue(name);
		      map.put(field.getName(),field.getValue()); 
	    	  }
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
}
