package com.intertek.webservice.outbound.resultParser;

public class LIMSContactResultParser implements IResultParser{
	/*
<?xml version="1.0" encoding="UTF-8" ?> 
- <UpdateTableResponse xmlns="http://tempuri.org/">
  <UpdateTableResult><Success/></UpdateTableResult> 
  </UpdateTableResponse>
*/
	public boolean parseResult(String result) {
		return 
			result!=null 
			&& (
					result.toLowerCase().indexOf("<updatetableresult><success/></updatetableresult>")>=0
					|| result.toLowerCase().indexOf("<updatetableresult>&lt;success/&gt;</updatetableresult>")>=0
				);
	}
}
