package com.intertek.webservice.outbound.resultParser;

public class InvoiceResultParser implements IResultParser{
	/*
 		<IBResponse type="success"> <DefaultTitle>Integration Broker Response</DefaultTitle> 
 		<StatusCode>0</StatusCode> 
 		<TransactionID>ITS_PHNX_NODE.ITS_INVOICE_GENERATION.42</TransactionID> </IBResponse> 
	 */
	public boolean parseResult(String result) {
		return 
			result!=null 
			&& result.toLowerCase().indexOf("<statuscode>0</statuscode>")>=0;
	}
}