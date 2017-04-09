package com.intertek.util;

import java.util.ArrayList;
import java.util.List;

import com.intertek.meta.dropdown.Field;
import com.intertek.meta.dropdown.impl.StaticDropDown;

public class DWRUtil {

	public String[] dwrTest(String paymentTypeFlag){
		System.out.println("Testing......"+paymentTypeFlag);
		List lst = new ArrayList();
		String[] str=null;
		    /*For iTrack#135193 -START */
		    if(paymentTypeFlag != null){
		  	  StaticDropDown sddObj = new StaticDropDown();
		  	  List params = new ArrayList();
		  	  params.add("paymentType");
		  	  System.out.println("11111");
		  	  if(paymentTypeFlag.trim().equals("true")){
		  		  System.out.println("?????");
		  		  params.add("Y");
		  	  } else {
		  		  System.out.println("22222");
		  		  params.add(""); 
		  	  }
		  	  
		  	  lst = sddObj.execute(params);
		  	  str = new String[lst.size()];
		  	  for(int i=0;i<lst.size();i++){
		  		  Field fld = (Field)lst.get(i);
		  		  System.out.println("name..."+fld.getName()+"...value..."+fld.getValue());
		  		  str[i] = fld.getName();
		  		  System.out.println("one more....."+lst.get(i));
		  	  }
		  	      	  
		    }/*For iTrack#135193 -END */
		    return str; 
		}
}
