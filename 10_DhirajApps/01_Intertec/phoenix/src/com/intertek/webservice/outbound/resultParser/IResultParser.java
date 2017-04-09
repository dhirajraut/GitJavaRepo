package com.intertek.webservice.outbound.resultParser;

public interface IResultParser {
	boolean parseResult(String result); //return true if the given result of transaction is successful
}
