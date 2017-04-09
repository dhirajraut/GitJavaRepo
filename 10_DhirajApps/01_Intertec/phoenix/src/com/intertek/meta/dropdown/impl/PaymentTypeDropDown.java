package com.intertek.meta.dropdown.impl;

import java.util.ArrayList;
import java.util.List;

import com.intertek.meta.dropdown.DropDown;
import com.intertek.meta.dropdown.Field;
import com.intertek.phoenix.entity.PaymentType;

public class PaymentTypeDropDown implements DropDown {
	public List<Field> execute(List params){
		List<Field> results = new ArrayList<Field>();
		
		for (PaymentType paymentType : PaymentType.values())
		{				
			  Field field = new Field();
			  
		      field.setName(paymentType.getDescription());		      
		      field.setValue(paymentType.value());		
			
		      results.add(field);
		}
		return results;
	}
}
