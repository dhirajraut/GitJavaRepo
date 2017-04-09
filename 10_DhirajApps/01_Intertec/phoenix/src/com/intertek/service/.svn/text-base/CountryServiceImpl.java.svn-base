package com.intertek.service;


import java.util.ArrayList;
import java.util.List;

import com.intertek.exception.ServiceException;
import com.intertek.domain.CountrySearch;
import com.intertek.entity.BusinessUnit;
import com.intertek.entity.Country;
import com.intertek.entity.CountryVAT;
import com.intertek.entity.CountryVATId;
import com.intertek.entity.CustVatRegistration;
import com.intertek.entity.State;
import com.intertek.entity.StateId;
import com.intertek.pagination.Pagination;
import com.intertek.util.CountryUtil;

public class CountryServiceImpl extends BaseService implements CountryService
{
  public Country getCountryById(Long countryId)
  {
    List countrys = getDao().find(
      "from Country country left join fetch country.states where country.id = ?",
      new Object[] { countryId }
    );

    if(countrys.size() > 0) return (Country)countrys.get(0);

    return null;
  }

  public State getStateById(Long stateId)
  {


    List states = getDao().find(
      "from State state where state.id = ?",
      new Object[] { stateId }
    );

    if(states.size() > 0) return (State)states.get(0);

    return null;
  }
  public Country getCountryByCode(String code)
  {
    List countrys = getDao().find(
      "from Country country left join fetch country.states left join fetch country.countryVats where country.countryCode = ?",
      new Object[] { code }
    );

    if(countrys != null && countrys.size() > 0)
      return (Country)countrys.get(0);
    else
    return null;
  }
  public Country getCountryByName(String name)
  {
    List countrys = getDao().find(
      "from Country country where country.name = ?",
      new Object[] { name }
    );

    if(countrys.size() > 0) return (Country)countrys.get(0);

    return null;
  }
  public State getStateByCode(String code, String country)
  {
    System.out.println("in getStateByCode code is"+ code);
    List states = getDao().find(
    "from State state left join fetch state.country where state.stateId.stateCode = ? and state.stateId.countryCode = ? ",
    new Object[] { code, country }
    );

    if(states.size() > 0) return (State)states.get(0);

    return null;
  }
  public State getStateByName(String name)
  {
    List states = getDao().find(
    "from State state left join fetch state.country where state.name = ?",
    new Object[] { name }
    );

    if(states.size() > 0) return (State)states.get(0);

    return null;
  }

  public Country addCountry(Country country)
  {
    if (country == null) return null;

    String errorMessage = CountryUtil.validateCountryAttributes(country);
    if( (errorMessage != null) && (errorMessage.length() > 0))
    {
     throw new ServiceException("create.country.error", new Object[] {errorMessage}, null);
    }

    Country existedCountry = getCountryByCode(country.getCountryCode());
    if (existedCountry != null)
    {
     // throw new RuntimeException( "Country with the same code exists: " + country.getCountryCode());
    throw new ServiceException("country.exists.error", new Object[] {country.getCountryCode()}, null);
    }

    return getDao().save(country);
  }

  public void updateCountryHeaderOnly(Country country)
  {
    if (country == null) return;

    Country tmpCountry = getCountryByCode(country.getCountryCode());
    if (tmpCountry == null)
    {
      tmpCountry = country;
    }
    else
    {
      CountryUtil.copyCountryAttributes(country, tmpCountry);
    }

    String errorMessage = CountryUtil.validateCountryAttributes(tmpCountry);
    if( (errorMessage != null) && (errorMessage.length() > 0))
    {
      //throw new RuntimeException(errorMessage);
    throw new ServiceException("create.country.error", new Object[] {errorMessage}, null);
    }

    getDao().save(tmpCountry);
  }

  public void saveCountry(Country country)
  {
    if (country == null) return;

    String errorMessage = CountryUtil.validateCountryAttributes(country);
    if( (errorMessage != null) && (errorMessage.length() > 0))
    {
   throw new ServiceException("create.country.error", new Object[] {errorMessage}, null);
    }

    Country existedCountry = getCountryByCode(country.getCountryCode());
    if (existedCountry == null)
    {
    throw new ServiceException("country.not.exists.error", new Object[] {country.getCountryCode()}, null);
    }

    getDao().save(country);
  }

  public void searchCountry(CountrySearch search)
  {
    if(search == null) return;

    StringBuffer sb = new StringBuffer();
    List params = new ArrayList();

    boolean hasWhere = false;
    String name = search.getName().getValue();
    if((name != null) && !"".equals(name.trim()))
    {
      sb.append(" where u.loginName = ?");
      params.add(name);
      hasWhere = true;
    }

    Pagination pagination = search.getPagination();
    List results = null;

    if(pagination != null)
    {
      if(pagination.getTotalRecord() > 0)
      {
        List counts = getDao().find(
          "select count(c.id) from Country c " + sb.toString(),
          params.toArray()
        );

        if(counts.size() > 0)
        {
          Number count = (Number)counts.get(0);
          pagination.setTotalRecord(count.intValue());
        }
      }

      results = getDao().find(
        "select distinct c from Country c left join fetch c.states " + sb.toString(),
        params.toArray(),
        pagination
      );

      pagination.calculatePages();
    }
    else
    {
      results = getDao().find(
        "select distinct c from Country c left join fetch c.states " + sb.toString(),
        params.toArray()
      );
    }

    search.setResults(results);
  }

  public List getCountries()
  {
      List params = new ArrayList();
/*      List countries = getDao().find(
              "select country from Country  country order by country.countryCode ",
              params.toArray()
            );*/
	  List countries = getDao().find(
              "select country from Country  country where country.status = ? order by country.countryCode ",
              new Object[]{new String("A")});
	  
            if(countries.size() > 0) return countries;
            else
            return null;
    //return getDao().getAll(Country.class);
  }

  public State getStateByCodeAndCountryCode(String stateCode, String countryCode)
  {
    List states = getDao().find(
      "from State state left join fetch state.country where state.stateId.stateCode = ? and state.stateId.countryCode = ? order by state.stateId.stateCode ",
      new Object[] { stateCode, countryCode }
    );

    if(states.size() > 0)
    {
      System.out.println("states.size() > 0");
      return (State)states.get(0);
    }

    else
    return null;
  }

  public List getStatesByCodeAndCountryCode(String stateCode, String countryCode)
  {
   String strCodeSearch = null;
   if ((stateCode != null) && !"".equals(stateCode.trim()))
      strCodeSearch = '%' + stateCode.toUpperCase() + '%';
    List states = getDao().find(
      "from State state left join fetch state.country where upper(state.stateId.stateCode) like ? and state.stateId.countryCode = ? order by state.stateId.stateCode ",
      new Object[] { strCodeSearch, countryCode }
    );
    if(states.size() > 0) return states;

    return null;
  }

  public State addState(State state)
  {
    if(state == null) return null;

    StateId stateId = state.getStateId();
    if(stateId == null)
    {
      //throw new RuntimeException("No stateId for adding state. ");
    throw new ServiceException("stateId.not.exists.error");
    }

    Country existedCountry = getCountryByCode(stateId.getCountryCode());
    if(existedCountry == null)
    {
    throw new ServiceException("country.error", new Object[] {stateId.getCountryCode()}, null);
    }

    State existedState = getStateByCodeAndCountryCode(stateId.getStateCode(), stateId.getCountryCode());
    if(existedState == null)
    {
      return getDao().save(state);
    }
    else
    {
      return existedState;
    }
  }

  public void saveState(State state)
  {
    if(state == null) return;

    State existedState = getStateByCodeAndCountryCode(state.getStateId().getStateCode(), state.getStateId().getCountryCode());
    if(existedState == null)
    {
     // throw new RuntimeException("State not exist for code: " + state.getStateId().getStateCode() + ", " + state.getStateId().getCountryCode());
   {throw new ServiceException("state.countrycode.not.exists.error", new Object[] {state.getStateId().getStateCode() + ", " + state.getStateId().getCountryCode()}, null);}
    }

    getDao().save(state);
  }

  public List getStatesByCountryCode(String countryCode)
  {
    return getDao().find(
      "from State s where s.stateId.countryCode = ?",
      new Object[] { countryCode }
    );
  }

  public void searchStateByCountryCode(CountrySearch search,String sortFlag)
  {
    if (search == null) return;

    StringBuffer sb = new StringBuffer();
    List params = new ArrayList();

    boolean hasWhere = false;


    String strCodeSearch = "";

    String stateCode = search.getStateCode().getValue();
    String countryCode = search.getCountryCode();
    if ((stateCode != null) && !"".equals(stateCode.trim()))
      strCodeSearch = '%' + stateCode.toUpperCase() + '%';

    if ((stateCode != null) && !"".equals(stateCode.trim())) {
          hasWhere = true;
        sb.append(" where ");
        sb.append(" upper(s.stateId.stateCode) like ? ");
          params.add(strCodeSearch);
    }

    if((countryCode != null) && !"".equals(countryCode.trim()))
    {
      if(hasWhere)
      sb.append("and");
      else
        sb.append(" where ");

        sb.append(" upper(s.stateId.countryCode) = ? ");
        params.add(countryCode.toUpperCase());
    }



    Pagination pagination = search.getPagination();
    List results = null;
    if(sortFlag != null && sortFlag.equals(""))
  {
    if (pagination != null) {

      if (pagination.getTotalRecord() > 0) {
        List counts = getDao().find(
            "select count(s.stateId.stateCode) from State s "
                + sb.toString(), params.toArray());

        if (counts.size() > 0) {
          Number count = (Number) counts.get(0);
          pagination.setTotalRecord(count.intValue());
        }
      }
      results = getDao().find(
          "select distinct s from State s " + sb.toString()
              + " order by s.stateId.stateCode", params.toArray(),
          pagination);

      pagination.calculatePages();
    } else {
      results = getDao().find(
          "select distinct s from State s " + sb.toString(),
          params.toArray());
    }
    search.setResults(results);
    search.setPagination(pagination);
  }else
    {
      String orderByValue=" order by s."+sortFlag;
      if (pagination != null) {

          if (pagination.getTotalRecord() > 0) {
            List counts = getDao().find(
                "select count(s.stateId.stateCode) from State s "
                    + sb.toString(), params.toArray());

            if (counts.size() > 0) {
              Number count = (Number) counts.get(0);
              pagination.setTotalRecord(count.intValue());
            }
          }
          results = getDao().find(
              "select distinct s from State s " + sb.toString()
                  + " order by s.stateId.stateCode", params.toArray(),
              pagination);

          pagination.calculatePages();
        }

      // START : #119240 : 22/06/09
      /*results = getDao().find("select distinct s from State s " + sb.toString()
                + orderByValue, params.toArray(),pagination
            );*/
      if(search.getSortOrderFlag()!=null && !search.getSortOrderFlag().equals("")){
    	  results = getDao().find("select distinct s from State s " + sb.toString()
              + orderByValue + " " + search.getSortOrderFlag(), params.toArray(),pagination
          );
      }else{
    	  results = getDao().find("select distinct s from State s " + sb.toString()
                  + orderByValue, params.toArray(),pagination
           );
      }
      // END : #119240 : 22/06/09
      
    //search.setTotalResults(results);
      search.setResults(results);
      search.setPagination(pagination);
    }
  }

  public void searchCountryByCountryCode(CountrySearch search,String sortFlag)
  {


  if (search == null)
      return;

    StringBuffer sb = new StringBuffer();
    List params = new ArrayList();

    boolean hasWhere = false;


    String strCodeSearch = "";
    String strDescSearch="";
    String strStatusSearch = "";


    String countryCode = search.getCountryCodes().getValue();
    String countryDesc = search.getName().getValue();
//  start issue 115770
    String countryStatus = search.getStatus().getValue();
//end 115770

    if ((countryCode != null) && !"".equals(countryCode.trim()))
      strCodeSearch = '%' + countryCode.toUpperCase() + '%';

    if ((countryCode != null) && !"".equals(countryCode.trim())) {

          hasWhere = true;
        sb.append(" where ");
        sb.append(" upper(c.countryCode) like ? ");
          params.add(strCodeSearch);
    }
       if ((countryDesc!= null) && !"".equals(countryDesc.trim()))
      strDescSearch = '%' + countryDesc.toUpperCase() + '%';

    if((countryDesc != null) && !"".equals(countryDesc.trim()))
    {
      if(hasWhere)
      sb.append("and");
      else{
        sb.append(" where ");
        hasWhere = true;
      }

        sb.append(" upper(c.name) like ? ");
        params.add(strDescSearch);
    }

//      start issue 115770
    if(null != countryStatus && !"".equals(countryStatus)){
    	strStatusSearch = '%' + countryStatus.toUpperCase() + '%';
    	if(hasWhere)
    	      sb.append("and");
    	      else{
    	        sb.append(" where ");
    	        hasWhere = true;
    	      }        
        sb.append(" upper(c.status) like ? ");
        params.add(strStatusSearch);
    }
//end 115770
    Pagination pagination = search.getPagination();
    List results = null;
    if(sortFlag != null && sortFlag.equals(""))
    {
    if (pagination != null) {

      if (pagination.getTotalRecord() > 0) {
        List counts = getDao().find(
            "select count(c.countryCode) from Country c "
                + sb.toString(), params.toArray());

        if (counts.size() > 0) {
          Number count = (Number) counts.get(0);
          pagination.setTotalRecord(count.intValue());
        }
      }
      results = getDao().find(
          "select distinct c from Country c " + sb.toString()
              + " order by c.countryCode", params.toArray(),
          pagination);

      pagination.calculatePages();
    } else {
      results = getDao().find(
          "select distinct c from Country c " + sb.toString(),
          params.toArray());
    }
    search.setResults(results);
    search.setPagination(pagination);
    }else
    {
      String orderByValue=" order by c."+sortFlag;
      if (pagination != null) {

          if (pagination.getTotalRecord() > 0) {
            List counts = getDao().find(
                "select count(c.countryCode) from Country c "
                    + sb.toString(), params.toArray());

            if (counts.size() > 0) {
              Number count = (Number) counts.get(0);
              pagination.setTotalRecord(count.intValue());
            }
          }
          pagination.calculatePages();
        }
      // START : #119240 : 18/06/09
      /*results = getDao().find(
                "select distinct c from Country c " + sb.toString()
                    +orderByValue , params.toArray(),pagination);*/
      
      if(search.getSortOrderFlag()!=null && !search.getSortOrderFlag().equals("")){
    	  results = getDao().find(
                  "select distinct c from Country c " + sb.toString()
                      +orderByValue +" "+ search.getSortOrderFlag(), params.toArray(),pagination);  
      }else{
	      results = getDao().find(
	              "select distinct c from Country c " + sb.toString()
	                  +orderByValue , params.toArray(),pagination);
      }
      // END : #119240 : 18/06/09
      
   // search.setTotalResults(results);
      search.setResults(results);
      search.setPagination(pagination);
    }

  }

  public CountryVAT getCountryVATByCode(String stateCode)
  {
    List countryVATId=getDao().find("from CountryVAT cv where cv.countryVATId.stateCode=?",new Object[]{stateCode});

    if(countryVATId.size()>0) return (CountryVAT)countryVATId.get(0);

    return null;
  }

  public void saveCountryVAT(CountryVAT countryVAT)
  {
    CountryVATId countryVATId = countryVAT.getCountryVATId();
    if(countryVATId == null) return;

    String stateCode = countryVATId.getStateCode();
    if((stateCode == null) || "".equals(stateCode))
    {
      countryVATId.setStateCode(" ");
    }

    getDao().save(countryVAT);
  }




/* (non-Javadoc)
 * @see com.intertek.service.CountryService#getCustomerVatRegistrationByCustCodeandCountryCode(java.lang.String, java.lang.String, java.lang.String)
 * If the state is null or empty, it just looks for country
 * If not, First it looks for comination of state and country.
 * If not found, it looks for the country and empty state
 */
public CustVatRegistration getCustomerVatRegistrationByCustCodeandCountryCode(String custCode,String countryCode, String state)
  {
   List custVats = new ArrayList();
   if(state==null || state.trim().length()==0){
     custVats=getDao().find("from CustVatRegistration c where c.custVatRegistrationId.custCode=? and c.custVatRegistrationId.countryCode=? and (c.state is null or c.state='') ", new Object[]{custCode,countryCode});
      if(custVats.size()>0)
        return (CustVatRegistration)custVats.get(0);
   }

  custVats=getDao().find("from CustVatRegistration c where c.custVatRegistrationId.custCode=? and c.custVatRegistrationId.countryCode=? and c.state=? ", new Object[]{custCode,countryCode, state});
  if(custVats.size()>0)
    return (CustVatRegistration)custVats.get(0);
  else{
    custVats=getDao().find("from CustVatRegistration c where c.custVatRegistrationId.custCode=? and c.custVatRegistrationId.countryCode=? and (c.state is null or c.state='') ", new Object[]{custCode,countryCode});
    if(custVats.size()>0)
      return (CustVatRegistration)custVats.get(0);
  }
  return null;
  }

public CountryVAT getDefaultStateByCountryCodeandVatCode(String countrycode,String vatCode)
{
   List countries = getDao().find(
          //"from BusinessUnit bu left eft join fetch bu.organization left join fetch bu.busUnitVatLocs vatLocs left join fetch vatLocs.country ctry left join fetch ctry.countryVats where bu.name = ? and vatLocs.defaultLocInd = 1 ",
      " from CountryVAT c where c.countryVATId.countryCode =? and (c.zeroRatedVATCode=? or c.vatCode = ?) and c.countryVATId.stateCode is not null and c.countryVATId.stateCode!=' '",
          new Object[] {countrycode,vatCode,vatCode}
        );

        if(countries.size() > 0) return (CountryVAT)countries.get(0);

        return null;


}

}

