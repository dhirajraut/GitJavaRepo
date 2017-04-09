package com.intertek.meta.dropdown.impl;

import java.util.ArrayList;
import java.util.List;

import com.intertek.meta.dropdown.DropDown;
import com.intertek.meta.dropdown.Field;
import com.intertek.phoenix.entity.InvoiceStatus;

public class InvoiceStatusDropDown implements DropDown {
	public List<Field> execute(List params){
		List<Field> results = new ArrayList<Field>();
		
		for (InvoiceStatus invoiceStatus : InvoiceStatus.values())
		{				
			  Field field = new Field();
			  
		      field.setName(invoiceStatus.getDescription());		      
		      field.setValue(String.valueOf(invoiceStatus.status()));		
			
		      results.add(field);
		}
		return results;
	}
}
