package com.intertek.service;

import java.util.List;

import com.intertek.domain.ServiceLocationSearch;
import com.intertek.entity.ServiceLocation;

public interface ServiceLocationService
{
  ServiceLocation getServiceLocationByServiceLocationCode(String serviceLocationCode);
  ServiceLocation getServiceLocationByServiceLocationNameAndCity(String serviceLocationName,String serviceLocationCity);
  ServiceLocation getServiceLocationByName(String name);
  ServiceLocation getServiceLocationByCity(String name);
  ServiceLocation getServiceLocationByBranchName(String name);

  ServiceLocation addServiceLocation(ServiceLocation serviceLocation,String taxCode);
  void saveServiceLocation(ServiceLocation serviceLocation,String taxCode);

  void searchServiceLocation(ServiceLocationSearch search,String reqForm,String sortFlag);
  List searchServiceLocationsByName(String name);
  List getServiceLocationByNameAndCity(String name,String city);
  List searchServiceLocationsByCity(String city);
  List getServiceLocationByNameAndPortValues(String name,String city,String stateCode,String countryCode);
  List getServiceLocationByPortValues(String name,String city,String stateCode,String countryCode);
//For itrack issue 24329
  List getServiceLocationByCityAndStateAndCountry(String city, String state, String country);
}

