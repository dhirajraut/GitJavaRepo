package com.intertek.webservice.outbound.resultParser;

public class BranchResultParser implements IResultParser{
	/*
<IBResponse type="success">
  <DefaultTitle>Integration Broker Response</DefaultTitle> 
  <StatusCode>0</StatusCode> 
  <TransactionID>ITS_PHNX_NODE.ITS_LOC_OP_UNIT_SYNC.1</TransactionID> 
  </IBResponse>
  </detail>
  	 */
	public boolean parseResult(String result) {
		return 
			result!=null 
			&& result.toLowerCase().indexOf("<statuscode>0</statuscode>")>=0;
	}
}
