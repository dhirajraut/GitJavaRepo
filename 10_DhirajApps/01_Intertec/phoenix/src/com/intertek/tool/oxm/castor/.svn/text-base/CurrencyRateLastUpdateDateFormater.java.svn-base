package com.intertek.tool.oxm.castor;

import java.text.SimpleDateFormat;

import org.exolab.castor.mapping.GeneralizedFieldHandler;

public class CurrencyRateLastUpdateDateFormater extends GeneralizedFieldHandler {
	private static final SimpleDateFormat formatter=new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.000000Z");
	@Override
	public Object convertUponGet(Object arg0) {
		Object obj=formatter.format(arg0);
		return obj;
	}

	@Override
	public Object convertUponSet(Object arg0) {
		try {
			Object obj=formatter.parseObject((String)arg0);
			return obj;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Class getFieldType() {
		return java.util.Date.class;
	}

}
