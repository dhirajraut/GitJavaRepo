package com.intertek.meta.dropdown.impl;

import java.util.ArrayList;
import java.util.List;

import com.intertek.entity.Country;
import com.intertek.locator.ServiceLocator;
import com.intertek.meta.dropdown.DropDown;
import com.intertek.meta.dropdown.Field;
import com.intertek.service.CountryService;

public class CountryDropDown implements DropDown
{
  public List execute(List params)
  {
    List results = new ArrayList();

    CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");

    List countries = countryService.getCountries();
    
    	if(countries != null && countries.size() > 0)
	  {
		Field field = new Field();
		field.setName("");
		field.setValue("");
		results.add(field);
	  }
    
    for(int i=0; i<countries.size(); i++)
    {
      Country country = (Country) countries.get(i);

      Field field = new Field();
      String name = country.getName();
      String code = country.getCountryCode();
      field.setName(code + "-" + name);
      field.setValue(code);
      results.add(field);
    }

    return results;
  }
}
