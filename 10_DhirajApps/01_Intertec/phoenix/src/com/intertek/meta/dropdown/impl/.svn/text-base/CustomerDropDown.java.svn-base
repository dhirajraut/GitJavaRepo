package com.intertek.meta.dropdown.impl;

import java.util.ArrayList;
import java.util.List;

import com.intertek.entity.Customer;
import com.intertek.locator.ServiceLocator;
import com.intertek.meta.dropdown.DropDown;
import com.intertek.meta.dropdown.Field;
import com.intertek.service.CustomerService;
import com.intertek.service.UserService;

public class CustomerDropDown implements DropDown
{
  public List execute(List params)
  {
    List results = new ArrayList();
    List customers = new ArrayList();
    int size = params != null ? params.size() : 0;
    CustomerService customerService = (CustomerService)ServiceLocator.getInstance().getBean("customerService");
	
    if(size != 0){
    	String custType = (String)params.get(0);
    	customers = customerService.getParentCustomers(custType);
    } else {
    	customers = customerService.getChildCustomers(null);
    }
    Field field1 = new Field();
    field1.setName("");
    field1.setValue("");
    results.add(field1);
    for(int i=0; i<customers.size(); i++)
    {
      Customer customer = (Customer)customers.get(i);
      Field field = new Field();
      String name = customer.getName();
      field.setName(name.trim());
      field.setValue(name.trim());
      results.add(field);
    }
    return results;
  }
}
