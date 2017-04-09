package com.intertek.service;

import java.util.ArrayList;
import java.util.List;

import com.intertek.domain.TowingCompanySearch;
import com.intertek.entity.Country;
import com.intertek.entity.State;
import com.intertek.entity.TowingCompany;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;

public class TowingCompanyServiceImpl extends BaseService implements TowingCompanyService
{
  public TowingCompany getTowingCompanyByName(String towingCompanyName)
  {
    List tows = getDao().find(
      "from TowingCompany t left join fetch t.country left join fetch t.state where t.name = ?",
      new Object[] { towingCompanyName }
    );

    if(tows.size() > 0) return (TowingCompany)tows.get(0);

    return null;
  }

  public TowingCompany getTowingCompanyById(Long id)
  {
   TowingCompany towingCompany = null;
      List tows = getDao().find(
        "from TowingCompany t left join fetch t.country left join fetch t.state where t.id = ?",
        new Object[] { id }
      );

      if(tows.size() > 0) return (TowingCompany)tows.get(0);

      return null;
  }

  public void saveTowingCompany(TowingCompany towingCompany)
  {
    if(towingCompany == null) return;

    String countryCode = towingCompany.getCountryCode();
    if(countryCode != null && (!countryCode.trim().equals("")))
    {
      CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");
      Country existedCountry = countryService.getCountryByCode(countryCode);
      if(existedCountry == null)
        throw new ServiceException("country.error",new Object[] {countryCode}, null);
        //throw new RuntimeException("Country does not exist: " + countryCode);
      towingCompany.setCountry(existedCountry);

      String stateCode = towingCompany.getStateCode();
      if(stateCode != null && (!stateCode.trim().equals("")))
      {
        State existedState = countryService.getStateByCodeAndCountryCode(
          stateCode,
          countryCode
        );
        if(existedState == null)
        {
      throw new ServiceException("state.error",new Object[] {stateCode,countryCode}, null);
        }

        if(!countryCode.equals(existedState.getStateId().getCountryCode()))
        {
      throw new ServiceException("state.country.match.error",new Object[] {stateCode,existedState.getStateId().getCountryCode(),countryCode}, null);
        }
        towingCompany.setState(existedState);
      }
      else
        towingCompany.setStateCode(null);
    }
    else
    {
    throw new ServiceException("create.towingcompany.countrycode.error",new Object[] {towingCompany.getName()}, null);
    }

    getDao().save(towingCompany);
  }

  public TowingCompany addTowingCompany(TowingCompany towingCompany)
  {
    if(towingCompany == null) return null;

    TowingCompany existedTowingCompany = getTowingCompanyByName(towingCompany.getName());
    if(existedTowingCompany != null)
    {
        throw new ServiceException("create.towingCompany.name.error",new Object[] {towingCompany.getName()}, null);
    }

    String countryCode = towingCompany.getCountryCode();
    if(countryCode != null && (!countryCode.trim().equals("")))
    {
      CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");
      Country existedCountry = countryService.getCountryByCode(countryCode);
    if(existedCountry == null)
      throw new ServiceException("country.error",new Object[] {countryCode}, null);
      //throw new RuntimeException("Country does not exist: " + countryCode);
      towingCompany.setCountry(existedCountry);

      String stateCode = towingCompany.getStateCode();
      if(stateCode != null && (!stateCode.trim().equals("")))
      {
        State existedState = countryService.getStateByCodeAndCountryCode(
          stateCode,
          countryCode
        );
        if(existedState == null)
        {
      throw new ServiceException("state.error",new Object[] {stateCode,countryCode}, null);
        }

        if(!countryCode.equals(existedState.getStateId().getCountryCode()))
        {
      throw new ServiceException("state.country.match.error",new Object[] {stateCode,existedState.getStateId().getCountryCode(),countryCode}, null);
        }
        towingCompany.setState(existedState);
      }
      else
        towingCompany.setStateCode(null);
    }
    else
    {
    throw new ServiceException("create.towingcompany.countrycode.error",new Object[] {towingCompany.getName()}, null);
    }

    return getDao().save(towingCompany);
  }

  public List searchTowingCompanysByName(String name)
  {
    System.out.println("======= in searchTowingCompanysByName name = " + name);
    if(name == null) return new ArrayList();

    return getDao().find(
      "from TowingCompany t where upper(t.name) like '" + name.toUpperCase() + "%'"+"order by t.id",
      null
    );
  }

  public void searchTowingCompany(TowingCompanySearch search,String sortFlag)
  {
    if(search == null) return;
  boolean hasWhere = false;
  String countryCode="";
  String stateCode="";
  String name = "";
  String city = "";

    StringBuffer sb = new StringBuffer();
    List params     = new ArrayList();
  CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");

  countryCode=search.getCountry().getValue();
  stateCode=search.getState().getValue();
  name = search.getName().getValue();
  city = search.getCity().getValue();

  if(countryCode!= null && (!countryCode.trim().equals(""))) {

      Country country = countryService.getCountryByCode(countryCode);
      sb.append("where t.countryCode = ?");
      params.add(countryCode);
      hasWhere = true;
    }
  if(stateCode != null && (!stateCode.trim().equals("")) && countryCode!= null && (!countryCode.trim().equals(""))){

       State state = countryService.getStateByCode(stateCode, countryCode);
      if(hasWhere) sb.append(" and ");
      else
      {
        hasWhere = true;
        sb.append(" where ");
      }

       sb.append("t.stateCode = ?");
       params.add(stateCode);

       hasWhere = true;
  }
   if((name != null) && !"".equals(name.trim())){

    if(hasWhere) sb.append(" and ");
      else
      {
      hasWhere = true;
      sb.append(" where ");
      }
      String nm = '%' + name +'%';
      sb.append("upper(t.name) like ?");
      params.add(nm.toUpperCase());
      hasWhere = true;

  }
  if((city != null) && !"".equals(city.trim())){
        System.out.println("City=" + city);
      if(hasWhere)
        sb.append(" and ");
      else
      {
      hasWhere = true;
      sb.append(" where ");
      }
      String ct = '%' + city +'%';
      sb.append(" upper(t.city) like ? ");
      params.add(ct.toUpperCase());
  }

    Pagination pagination = search.getPagination();
    List results = null;
    if(sortFlag != null && sortFlag.equals(""))
  {
    if(pagination != null)
    {
      if(pagination.getTotalRecord() > 0)
      {
        List counts = getDao().find(
          "select count(t.id) from TowingCompany t " + sb.toString(),
          params.toArray()
        );

        if(counts.size() > 0)
        {
          Number count = (Number)counts.get(0);
          pagination.setTotalRecord(count.intValue());
        }
      }

      results = getDao().find(
        "select t from TowingCompany t " + sb.toString()+"order by t.id",
        params.toArray(),
        pagination
      );

      pagination.calculatePages();
    }
    else
    {
      results = getDao().find(
        "select t from TowingCompany t " + sb.toString()+"order by t.id",
        params.toArray()
      );
    }
    search.setPagination(pagination);
    search.setResults(results);
  }
  else
  {
    String orderByValue="order by t."+sortFlag;
    System.out.println("orderByValue="+orderByValue);
    if(pagination != null)
      {
        if(pagination.getTotalRecord() > 0)
        {
          List counts = getDao().find(
            "select count(t.id) from TowingCompany t " + sb.toString(),
            params.toArray()
          );

          if(counts.size() > 0)
          {
            Number count = (Number)counts.get(0);
            pagination.setTotalRecord(count.intValue());
          }
        }
        pagination.calculatePages();
      }
    
    // START : #119240
    /*results = getDao().find(
            "select t from TowingCompany t left join fetch t.country left join fetch t.state " + sb.toString()+orderByValue,
            params.toArray(),pagination
          );*/
    if(search.getSortOrderFlag()!=null && !search.getSortOrderFlag().equals("")){
    	results = getDao().find(
            "select t from TowingCompany t left join fetch t.country left join fetch t.state " + sb.toString()+orderByValue + " " + search.getSortOrderFlag(),
            params.toArray(),pagination
          );
    }else{
    	results = getDao().find(
                "select t from TowingCompany t left join fetch t.country left join fetch t.state " + sb.toString()+orderByValue,
                params.toArray(),pagination
              );
    }
    // END : #119240
    
   // search.setTotalResults(results);
    search.setPagination(pagination);
    search.setResults(results);

  }

}
}
