package com.intertek.tool.oxm.castor;

import org.exolab.castor.mapping.GeneralizedFieldHandler;

public class YesNoBooleanFormater extends GeneralizedFieldHandler {
	@Override
	public Object convertUponGet(Object arg0) {
		if(arg0!=null){
			Boolean b=(Boolean)arg0;
			if(b){
				return "Y";
			}
		}
		return "N";
	}

	@Override
	public Object convertUponSet(Object arg0) {
		if(arg0!=null && arg0.toString().equalsIgnoreCase("Y")){
			return new Boolean(true);
		}
		return new Boolean(false);
	}

	@Override
	public Class getFieldType() {
		return java.lang.Boolean.class;
	}
}
