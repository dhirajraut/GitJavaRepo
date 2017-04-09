package com.intertek.domain;

import org.springmodules.validation.bean.conf.loader.annotation.handler.CascadeValidation;

import com.intertek.entity.Country;
import com.intertek.entity.CountryVAT;
import com.intertek.entity.State;

public class AddCountry
{
    @CascadeValidation

	private Country country;
	private int stateCount;
	private int countryVatsCount;
	private String addOrDeleteState;
	private String addOrDeleteCountryVats;
	private int stateIndex;
	private int countryVatIndex;
	private String tabName = "0";
	private State[] states;
	private CountryVAT[] countryVats;
	private String rowNum;
	
	// START : #119240 : 17/06/09 
	private CountrySearch countrySearch;
	// END : #119240 : 17/06/09
	
	public void setCountry(Country country)
	{
	this.country = country;
	}
	public Country getCountry()
	{
	return country;
	}

	public State[] getStates()
	{
	return states;
	}

	public void setStates(State[] states)
	{
	this.states = states;
	}

	public CountryVAT[] getCountryVats()
	{
	return countryVats;
	}

	public void setCountryVats(CountryVAT[] countryVats)
	{
	this.countryVats = countryVats;
	}
	
	public String getTabName()
	{
	return tabName;
	}

	public void setTabName(String tabName)
	{
	this.tabName = tabName;
	} 

	public int getStateCount()
	{
	return stateCount;
	}

	public void setStateCount(int stateCount)
	{
	this.stateCount = stateCount;
	}

	public int getCountryVatsCount()
	{
	return  countryVatsCount;
	}

	public void setCountryVatsCount(int countryVatsCount)
	{
	this. countryVatsCount = countryVatsCount;
	}

	public String getAddOrDeleteState()
	{
		return addOrDeleteState;
	}

	public void setAddOrDeleteState(String addOrDeleteState)
	{
		this.addOrDeleteState = addOrDeleteState;
	}


   public String getAddOrDeleteCountryVats()
	{
		return addOrDeleteCountryVats;
	}

	public void setAddOrDeleteCountryVats(String addOrDeleteCountryVats)
	{
		this.addOrDeleteCountryVats = addOrDeleteCountryVats;
	}

	public int getStateIndex()
	{
		return stateIndex;
	}

	public void setStateIndex(int stateIndex)
	{
		this.stateIndex = stateIndex;
	}


	public int getCountryVatIndex()
	{
		return countryVatIndex;
	}

	public void setCountryVatIndex(int countryVatIndex)
	{
		this.countryVatIndex = countryVatIndex;
	}

	public String getRowNum() {
		return rowNum;
	}

	public void setRowNum(String rowNum) {
		this.rowNum = rowNum;
	}
	// START : #119240 : 17/06/09 
	public CountrySearch getCountrySearch() {
		return countrySearch;
	}
	
	public void setCountrySearch(CountrySearch countrySearch) {
		this.countrySearch = countrySearch;
	}
	// END : #119240 : 17/06/09 
}
