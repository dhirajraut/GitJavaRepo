package com.intertek.service;

import java.util.ArrayList;
import java.util.List;

import com.intertek.domain.ShippingAgentSearch;
import com.intertek.entity.Country;
import com.intertek.entity.ShippingAgent;
import com.intertek.entity.State;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;

public class ShippingAgentServiceImpl extends BaseService implements ShippingAgentService
{
  public ShippingAgent getShippingAgentByName(String shipName)
  {
    List agents = getDao().find(
      "from ShippingAgent s left join fetch s.state left join fetch s.country where s.name = ?",
      new Object[] { shipName }
    );

    if(agents.size() > 0) return (ShippingAgent)agents.get(0);

    return null;
  }

  public ShippingAgent getShippingAgentById(Long id)
  {
    ShippingAgent shippingAgent = null;
     List agents = getDao().find(
      "from ShippingAgent s left join fetch s.state left join fetch s.country where s.id = ?",
      new Object[] { id }
    );

   // if(agents.size() > 0) return (ShippingAgent)agents.get(0);
  // return null;
    if(agents != null && agents.size() >0) {
      shippingAgent =(ShippingAgent)agents.get(0);
  } else{
        throw new ServiceException("shippingAgent.error",new Object[] { id });
  }
    return shippingAgent;
  }

  public void saveShippingAgent(ShippingAgent shippingAgent)
  {
    if(shippingAgent == null) return;

    String countryCode = shippingAgent.getCountryCode();
    if(countryCode != null && (!countryCode.trim().equals("")))
    {
      CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");
      Country existedCountry = countryService.getCountryByCode(countryCode);
      if(existedCountry == null)
        throw new ServiceException("country.error",new Object[] {countryCode}, null);
        //throw new RuntimeException("Country does not exist: " + countryCode);
      shippingAgent.setCountry(existedCountry);

      String stateCode = shippingAgent.getStateCode();
      if(stateCode != null && (!stateCode.trim().equals("")))
      {
        State existedState = countryService.getStateByCodeAndCountryCode(
          stateCode,
          countryCode
        );
            if(existedState == null)
            {
          throw new ServiceException("state.error",new Object[] {stateCode,countryCode}, null);
        //throw new RuntimeException("State does not exist: " + stateCode + ", " + countryCode);
            }

            if(!countryCode.equals(existedState.getStateId().getCountryCode()))
            {
        throw new ServiceException("state.country.match.error",new Object[] {stateCode,existedState.getStateId().getCountryCode(),countryCode}, null);
        //throw new RuntimeException("State's country code not match: " + stateCode + ", " + existedState.getStateId().getCountryCode() + ", " + countryCode);
            }
            shippingAgent.setState(existedState);
        }
      else
        shippingAgent.setStateCode(null);
    }
    else
    {
      throw new ServiceException("create.shippingagent.countrycode.error",new Object[] {shippingAgent.getName()}, null);
     // throw new RuntimeException("Country Code can not be null for Shipping Agent: " + shippingAgent.getName());
    }

    getDao().save(shippingAgent);
  }

  public ShippingAgent addShippingAgent(ShippingAgent shippingAgent)
  {
    if(shippingAgent == null) return null;

    ShippingAgent existedShippingAgent = getShippingAgentByName(shippingAgent.getName());
    if(existedShippingAgent != null)
    {
     // throw new RuntimeException("The Ship Agent existed for name:" + shippingAgent.getName());
      throw new ServiceException("create.shippingAgent.name.error",new Object[] {shippingAgent.getName()}, null);
    }

    String countryCode = shippingAgent.getCountryCode();
    if(countryCode != null && (!countryCode.trim().equals("")))
    {
      CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");
      Country existedCountry = countryService.getCountryByCode(countryCode);
      if(existedCountry == null)
        throw new ServiceException("country.error",new Object[] {countryCode}, null);
      shippingAgent.setCountry(existedCountry);

      String stateCode = shippingAgent.getStateCode();
      if(stateCode != null && (!stateCode.trim().equals("")))
      {
        State existedState = countryService.getStateByCodeAndCountryCode(
          stateCode,
          countryCode
        );
            if(existedState == null)
            {
              throw new ServiceException("state.error",new Object[] {stateCode,countryCode}, null);
              //throw new RuntimeException("State does not exist: " + stateCode + ", " + countryCode);
            }

            if(!countryCode.equals(existedState.getStateId().getCountryCode()))
            {
          throw new ServiceException("state.country.match.error",new Object[] {stateCode,existedState.getStateId().getCountryCode(),countryCode}, null);
          //throw new RuntimeException("State's country code not match: " + stateCode + ", " + existedState.getStateId().getCountryCode() + ", " + countryCode);
            }
            shippingAgent.setState(existedState);
      }
      else
        shippingAgent.setStateCode(null);
    }
    else
    {
      throw new ServiceException("create.shippingagent.countrycode.error",new Object[] {shippingAgent.getName()}, null);
     // throw new RuntimeException("Country Code can not be null for Shipping Agent: " + shippingAgent.getName());
    }

    return getDao().save(shippingAgent);
  }

  public List searchShippingAgentsByName(String name)
  {
    System.out.println("======= in searchShippingAgentsByName name = " + name);
    if(name == null) return new ArrayList();

    return getDao().find(
      "from ShippingAgent s where upper(s.name) like '" + name.toUpperCase() + "%'" +"order by s.id",
      null
    );
  }

  public void searchShippingAgent(ShippingAgentSearch search,String sortFlag)
  {
    if(search == null) return;

    boolean hasWhere   = false;
    String countryCode = "";
    String stateCode   = "";
    String name        = "";
    String city        = "";

    StringBuffer sb = new StringBuffer();
    List params     = new ArrayList();

    CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");

    countryCode = search.getCountry().getValue();
    stateCode = search.getState().getValue();
    name = search.getName().getValue();
    city = search.getCity().getValue();

    if(countryCode!= null && (!countryCode.trim().equals(""))){

      Country country = countryService.getCountryByCode(countryCode);
      sb.append("where s.countryCode = ?");
      params.add(countryCode);
      hasWhere = true;
    }
    if(stateCode != null && (!stateCode.trim().equals("")) && countryCode != null && (!countryCode.trim().equals(""))){

        State state = countryService.getStateByCode(stateCode, countryCode);
        if(hasWhere) sb.append(" and ");
        else {
          hasWhere = true;
          sb.append(" where ");
        }

        sb.append("s.stateCode = ?");
        params.add(stateCode);
        System.out.println("params is"+params);
        System.out.println(sb);
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
        sb.append("upper(s.name) like ?");
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
        sb.append(" upper(s.city) like ? ");
        params.add(ct.toUpperCase());
    }

    Pagination pagination = search.getPagination();
    List results = null;
    if(sortFlag != null && sortFlag.equals(""))
    {
    if(pagination != null)
    {
      if(pagination.getTotalRecord() > 0){
        List counts = getDao().find(
          "select count(s.id) from ShippingAgent s " + sb.toString(),
          params.toArray()
        );

        if(counts.size() > 0)
        {
          Number count = (Number)counts.get(0);
          pagination.setTotalRecord(count.intValue());
        }
      }

      results = getDao().find(
      "select s from ShippingAgent s " + sb.toString()+"order by s.name",
      params.toArray(),
      pagination
      );
      System.out.println(" inside pagination ");
      pagination.calculatePages();

    }
    else
    {
      System.out.println(" inside dao ");
      results = getDao().find(
      "select s from ShippingAgent s " + sb.toString()+"order by s.name",
      params.toArray()
      );
    }
    search.setPagination(pagination);
    search.setResults(results);
  }else
  {
    String orderByValue="order by s."+sortFlag;
    if(pagination != null)
    {
      if(pagination.getTotalRecord() > 0){
        List counts = getDao().find(
          "select count(s.id) from ShippingAgent s " + sb.toString(),
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
        "select s from ShippingAgent s left join fetch s.country left join fetch s.state " + sb.toString()+orderByValue,
        params.toArray(),pagination);*/
    if(search.getSortOrderFlag()!=null && !search.getSortOrderFlag().equals("")){
    	results = getDao().find(
            "select s from ShippingAgent s left join fetch s.country left join fetch s.state " + sb.toString()+orderByValue + " " + search.getSortOrderFlag(),
            params.toArray(),pagination);
    }else{
    	results = getDao().find(
    	        "select s from ShippingAgent s left join fetch s.country left join fetch s.state " + sb.toString()+orderByValue,
    	        params.toArray(),pagination);
    }
    // END : #119240
    //search.setTotalResults(results);
     search.setPagination(pagination);
     search.setResults(results);
  }
  }

}


