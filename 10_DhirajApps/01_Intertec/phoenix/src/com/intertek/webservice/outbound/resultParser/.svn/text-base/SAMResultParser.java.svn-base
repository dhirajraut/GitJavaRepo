package com.intertek.webservice.outbound.resultParser;

public class SAMResultParser implements IResultParser{
	/* 
		<setNominationResponse xmlns="http://businessobjects.intertek.com"> - <tns:out xmlns:tns="http://businessobjects.intertek.com"> 
		<tns:status>success</tns:status> 
		<tns:nominationId>US140-0008213</tns:nominationId> </tns:out> </setNominationResponse>	
	 */
	public boolean parseResult(String result) {
		return 
			result!=null 
			&& result.toLowerCase().indexOf("<tns:status>success</tns:status>")>=0;
	}
}
