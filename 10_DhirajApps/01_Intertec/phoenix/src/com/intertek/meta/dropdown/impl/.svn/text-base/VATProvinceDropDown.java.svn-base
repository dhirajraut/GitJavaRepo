package com.intertek.meta.dropdown.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.intertek.entity.BusinessUnit;
import com.intertek.entity.Country;
import com.intertek.entity.CountryVAT;
import com.intertek.entity.State;
import com.intertek.locator.ServiceLocator;
import com.intertek.meta.dropdown.DropDown;
import com.intertek.meta.dropdown.Field;
import com.intertek.service.CountryService;
import com.intertek.service.UserService;

public class VATProvinceDropDown implements DropDown
{
  public List execute(List params)
  {
    List results = new ArrayList();

    int size = params != null ? params.size() : 0;

    if(size < 1) return results;

    UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
    CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");

    String buName = (String)params.get(0);
    //BusinessUnit businessUnit = userService.getBusinessUnitByNameandDefaultLocId(buName);
    BusinessUnit businessUnit = userService.getBusinessUnitByName(buName);
    
    Country country = businessUnit.getCountry();
    Set countryVat = new HashSet();
	countryVat = country.getCountryVats();
	
	if(country.getVatCountry() != null && country.getVatCountry()!= false && 
	(country.getSameProvinceValidation()== null ||(country.getSameProvinceValidation()!= null && country.getSameProvinceValidation()== false)))
    {
		Field field = new Field();
		field.setName("All");
		field.setValue("All");
		results.add(field);
    }
	   if(countryVat != null && countryVat.size() > 0)
		  {
			Field field = new Field();
			field.setName("");
			field.setValue("");
			results.add(field);
		  }
	
    for(Iterator itr = countryVat.iterator();itr.hasNext();)
    {
      CountryVAT countryVAT = (CountryVAT) itr.next();
      Field field = new Field();
      
      State state = null;
      if(countryVAT.getCountryVATId().getStateCode() != null && !countryVAT.getCountryVATId().getStateCode().trim().equals(""))
      state = countryService.getStateByCode(countryVAT.getCountryVATId().getStateCode(),country.getCountryCode());
      if(state != null)
      {
	      field.setName(state.getName());
	      field.setValue(countryVAT.getCountryVATId().getStateCode());
	      results.add(field);
      }
    }

    return results;
  }
}
