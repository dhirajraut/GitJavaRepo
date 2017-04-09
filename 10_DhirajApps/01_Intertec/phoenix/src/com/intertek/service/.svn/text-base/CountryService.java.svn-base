package com.intertek.service;

import java.util.List;

import com.intertek.domain.CountrySearch;
import com.intertek.entity.Country;
import com.intertek.entity.CountryVAT;
import com.intertek.entity.CustVatRegistration;
import com.intertek.entity.State;

public interface CountryService
{
  Country getCountryById(Long countryId);
  Country getCountryByCode(String code);
  Country getCountryByName(String name);
  State getStateByName(String name);
  Country addCountry(Country country);
  State getStateByCode(String code, String country);

  void updateCountryHeaderOnly(Country country);
  void saveCountry(Country country);
  void searchCountry(CountrySearch search);
  void searchCountryByCountryCode(CountrySearch search,String sortFlag);
  void searchStateByCountryCode(CountrySearch search,String sortFlag);

  List getCountries();

  State getStateById(Long stateId);
  State getStateByCodeAndCountryCode(String stateCode, String countryCode);
  State addState(State state);
  void saveState(State state);

  List getStatesByCountryCode(String countryCode);
  List getStatesByCodeAndCountryCode(String stateCode, String countryCode);

  CountryVAT getCountryVATByCode(String stateCode);
  void saveCountryVAT(CountryVAT countryVAT);

  CustVatRegistration getCustomerVatRegistrationByCustCodeandCountryCode(String custCode,String countryCode, String state);
  CountryVAT getDefaultStateByCountryCodeandVatCode(String countrycode,String vatCode);

}

