package com.intertek.webservice.outbound.resultParser;

public class CustomerResultParser implements IResultParser{
	/*
		<IBResponse type="success"> <DefaultTitle>Integration Broker Response</DefaultTitle> 
		<StatusCode>0</StatusCode>  
	 */
	public boolean parseResult(String result) {
		return 
			result!=null 
			&& result.toLowerCase().indexOf("<statuscode>0</statuscode>")>=0;
	}
}
