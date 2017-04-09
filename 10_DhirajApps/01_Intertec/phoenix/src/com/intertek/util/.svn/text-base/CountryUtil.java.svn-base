package com.intertek.util;

import java.util.List;

import com.intertek.domain.ContractSearch;
import com.intertek.domain.CountrySearch;
import com.intertek.entity.Contract;
import com.intertek.entity.Country;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.CountryService;

public class CountryUtil
{
  public static void copyCountryAttributes(Country from, Country to)
  {
    if( (from == null) || (to == null)) return;

    if(from.getName() != null) to.setName(from.getName());
    if(from.getShortName() != null) to.setShortName(from.getShortName());
    if(from.getCountry2() != null) to.setCountry2(from.getCountry2());
    if(from.getEuMemberId() != null) to.setEuMemberId(from.getEuMemberId());

    if(from.getAddr1Lbl() != null) to.setAddr1Lbl(from.getAddr1Lbl());
    if(from.getAddr1Avail() != null) to.setAddr1Avail(from.getAddr1Avail());
    if(from.getAddr2Lbl() != null) to.setAddr2Lbl(from.getAddr2Lbl());
    if(from.getAddr2Avail() != null) to.setAddr2Avail(from.getAddr2Avail());
    if(from.getAddr3Lbl() != null) to.setAddr3Lbl(from.getAddr3Lbl());
    if(from.getAddr3Avail() != null) to.setAddr3Avail(from.getAddr3Avail());
    if(from.getAddr4Lbl() != null) to.setAddr4Lbl(from.getAddr4Lbl());
    if(from.getAddr4Avail() != null) to.setAddr4Avail(from.getAddr4Avail());

    if(from.getOtherLabel() != null) to.setOtherLabel(from.getOtherLabel());
    if(from.getOtherAvail() != null) to.setOtherAvail(from.getOtherAvail());

    if(from.getCounty() != null) to.setCounty(from.getCounty());
    if(from.getCountyAvail() != null) to.setCountyAvail(from.getCountyAvail());

    if(from.getState() != null) to.setState(from.getState());
    if(from.getStateAvail() != null) to.setStateAvail(from.getStateAvail());

    if(from.getPostal() != null) to.setPostal(from.getPostal());
    if(from.getPostalAvail() != null) to.setPostalAvail(from.getPostalAvail());

    if(from.getCity() != null) to.setCity(from.getCity());
    if(from.getCityAvail() != null) to.setCityAvail(from.getCityAvail());

    if(from.getVatCountry() != null) to.setVatCountry(from.getVatCountry());
    if(from.getVatByProvince() != null) to.setVatByProvince(from.getVatByProvince());
    if(from.getForeignBuyer() != null) to.setForeignBuyer(from.getForeignBuyer());
  }

  public static String validateCountryAttributes(Country country)
  {
    String errorMessage = "";

    if( (country.getAddr1Avail() != null) && country.getAddr1Avail())
    {
      if (country.getAddr1Lbl() == null || country.getAddr1Lbl().equals(""))
      {
        errorMessage += "Address 1 does not have address 1 label.";
      }
    }

    if( (country.getAddr2Avail() != null) && country.getAddr2Avail())
    {
      if (country.getAddr2Lbl() == null || country.getAddr2Lbl().equals(""))
      {
        errorMessage += " Address 2 does not have address 2 label.";
      }
    }

    if( (country.getAddr3Avail() != null) && country.getAddr3Avail())
    {
      if (country.getAddr3Lbl() == null || country.getAddr3Lbl().equals(""))
      {
        errorMessage += " Address 3 does not have address 3 label.";
      }
    }

    if( (country.getAddr4Avail() != null) && country.getAddr4Avail())
    {
      if (country.getAddr4Lbl() == null || country.getAddr4Lbl().equals(""))
      {
        errorMessage += " Address 4 does not have address 4 label. ";
      }
    }

    if( (country.getCityAvail() != null) && country.getCityAvail())
    {
      if (country.getCity() == null || country.getCity().equals(""))
      {
        errorMessage += " City does not have city label. ";
      }
    }

    if( (country.getCountyAvail() != null) && country.getCountyAvail())
    {
      if (country.getCounty() == null || country.getCounty().equals(""))
      {
        errorMessage += " County does not have country label.";
      }
    }

    if( (country.getStateAvail() != null) && country.getStateAvail())
    {
      if (country.getState() == null || country.getState().equals(""))
      {
        errorMessage += " State does not have state label. ";
      }
    }

    if( (country.getPostalAvail() != null) && country.getPostalAvail())
    {
      if (country.getPostal() == null || country.getPostal().equals(""))
      {
        errorMessage += " Postal does not have postal label. ";
      }
    }

    return errorMessage;
  }
  
// START : #119240 : 19/06/09
  public static String  getPrevOrNextCountry(String currentCountry, CountrySearch countrySearch, boolean prev){
	  
	  if(currentCountry == null || countrySearch == null) 
		  return null;
	  
	  CountryService countryService = (CountryService) ServiceLocator.getInstance().getBean("countryService");
	  
	  List<Country> results =  countrySearch.getResults();
	  
	  if(results == null) return null;

	    for(int i=0; i<results.size(); i++)
	    {
	    	Country country = (Country)results.get(i);
	    	if(country.getCountryCode().equals(currentCountry))
	    	{
	    		if(prev)
	    		{
	    			if(i == 0)
	    			{
	    				  int currentPageNum = countrySearch.getPagination().getCurrentPageNum();
	    		            if(currentPageNum > 1)
	    		            {
	    		            	countrySearch.getPagination().setCurrentPageNum(currentPageNum - 1);
	    		            	countryService.searchCountryByCountryCode(countrySearch, countrySearch.getSortFlag());

		   		              	List newResults = countrySearch.getResults();
		   		              	if(newResults == null) return null;
	
		   		              	if(newResults.size() == 0) return null;
	
		    		            return ((Country)newResults.get(newResults.size() - 1)).getCountryCode();
	    		            }
	    		            else
	    		            {
	    		              return null;
	    		            }
	    			}
	    			return ((Country)results.get(i-1)).getCountryCode();
	    		}else{
	    			
	    			if(i == results.size() - 1)
	    			{
	   				  	int totalPages = countrySearch.getPagination().getTotalPages();
	    		        int currentPageNum = countrySearch.getPagination().getCurrentPageNum();
	    		        if(currentPageNum < totalPages)
	    		        {
	    		        	countrySearch.getPagination().setCurrentPageNum(currentPageNum + 1);
	    		        	countryService.searchCountryByCountryCode(countrySearch, countrySearch.getSortFlag());
	    		        	List newResults = countrySearch.getResults();
	    		        	if(newResults == null) return null;
	
    		              	if(newResults.size() == 0) return null;

    		              	return ((Country)newResults.get(0)).getCountryCode();
	    		        }else
	    		        {
	    		        	return null;
	    		        }
	    			}
	    			
	    			return ((Country)results.get(i+1)).getCountryCode();	    			
	    		}
	    	}	    	
	    }
	  
	  return null;
  }
// END : #119240 : 19/06/09
}
