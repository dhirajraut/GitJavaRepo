package com.intertek.webservice.outbound.resultParser;

public class LIMSResultParser implements IResultParser{
	/*
		<?xml version="1.0" encoding="UTF-8"?>
		<ProcessDSResponse xmlns="http://tempuri.org/">
		<ProcessDSResult>Success</ProcessDSResult>
		</ProcessDSResponse>
	*/
	public boolean parseResult(String result) {
		return result!=null && result.toLowerCase().indexOf("<processdsresult>success</processdsresult>")>=0;
	}
}
