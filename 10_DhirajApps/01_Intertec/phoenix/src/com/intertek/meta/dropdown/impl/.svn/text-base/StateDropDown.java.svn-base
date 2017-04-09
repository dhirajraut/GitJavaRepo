package com.intertek.meta.dropdown.impl;

import java.util.ArrayList;
import java.util.List;

import com.intertek.entity.State;
import com.intertek.locator.ServiceLocator;
import com.intertek.meta.dropdown.DropDown;
import com.intertek.meta.dropdown.Field;
import com.intertek.service.CountryService;

public class StateDropDown implements DropDown
{
  public List execute(List params)
  {
    List results = new ArrayList();

    int size = params != null ? params.size() : 0;

    if(size < 1) return results;

    CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");

    String country = (String)params.get(0);
    List states = countryService.getStatesByCountryCode(country);

	if(states != null && states.size() > 0)
	  {
		Field field = new Field();
		field.setName("");
		field.setValue("");
		results.add(field);
	  }
    for(int i=0; i<states.size(); i++)
    {
      State state = (State) states.get(i);

      Field field = new Field();

      field.setName(state.getName());
      field.setValue(state.getStateId().getStateCode());
      results.add(field);
    }

    return results;
  }
}
