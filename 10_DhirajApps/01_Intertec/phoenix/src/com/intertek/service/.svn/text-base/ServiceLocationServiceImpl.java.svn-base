package com.intertek.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.intertek.domain.ServiceLocationSearch;
import com.intertek.entity.Branch;
import com.intertek.entity.Country;
import com.intertek.entity.CustAddrSeq;
import com.intertek.entity.CustAddrSeqId;
import com.intertek.entity.CustAddress;
import com.intertek.entity.Customer;
import com.intertek.entity.ServiceLocation;
import com.intertek.entity.State;
import com.intertek.entity.User;
import com.intertek.exception.ServiceException;
import com.intertek.locator.ServiceLocator;
import com.intertek.pagination.Pagination;
import com.intertek.util.Constants;
import com.intertek.util.SecurityUtil;

public class ServiceLocationServiceImpl extends BaseService implements ServiceLocationService
{
  public ServiceLocation getServiceLocationByServiceLocationCode(String serviceLocationCode)
  {
  ServiceLocation serviceLocation = null;
    List locations = getDao().find(
      "from ServiceLocation s left join fetch s.country left join fetch s.state left join fetch s.branch left join fetch s.shipToCustAddress cs left join fetch cs.custAddresses where s.serviceLocationCode = ?",
      new Object[] { serviceLocationCode }
    );

    if(locations.size() > 0) return (ServiceLocation)locations.get(0);

    return null;
  }
  public ServiceLocation getServiceLocationByServiceLocationNameAndCity(String serviceLocationName,String serviceLocationCity){

    if(!serviceLocationCity.equals("")&&!serviceLocationName.equals(""))
    {
        List locations = getDao().find(
        "from ServiceLocation s left join fetch s.country left join fetch s.state left join fetch s.branch left join fetch s.shipToCustAddress cs left join fetch cs.custAddresses where upper(trim(s.name)) = ? and upper(s.city) = ?",
          new Object[] { serviceLocationName.trim().toUpperCase(),serviceLocationCity.toUpperCase() }
        );
        if(locations.size()>0) return (ServiceLocation)locations.get(0);
      return null;

    }else
    {
      List locations = getDao().find(
            "from ServiceLocation s left join fetch s.country left join fetch s.state left join fetch s.branch left join fetch s.shipToCustAddress cs left join fetch cs.custAddresses where trim(s.name) = ? and s.city is null",
              new Object[] { serviceLocationName.trim()}
            );
      if(locations.size()>0) return (ServiceLocation)locations.get(0);
      return null;
    }
  }
  public ServiceLocation getServiceLocationByName(String serviceLocationName)
  {
    List locations = getDao().find(
      "from ServiceLocation s left join fetch s.country left join fetch s.state where trim(s.name) = ?",
      new Object[] { serviceLocationName.trim() }
    );

    if(locations.size() > 0)
      return (ServiceLocation)locations.get(0);
    else
    return null;
  }
  public ServiceLocation getServiceLocationByCity(String serviceLocationCity)
  {
    List locations = getDao().find(
      "from ServiceLocation s left join fetch s.country left join fetch s.state where s.city = ? and s.branchName is null",
      new Object[] { serviceLocationCity.trim() }
    );

    if(locations.size() > 0)
      return (ServiceLocation)locations.get(0);
    else
      return null;
  }

  public void saveServiceLocation(ServiceLocation serviceLocation,String taxCode)
  {
    if(serviceLocation == null) return;



   UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
   CustomerService customerService = (CustomerService)ServiceLocator.getInstance().getBean("customerService");
    if(serviceLocation.getCountryCode() != null && (!serviceLocation.getCountryCode().trim().equals("")))
    {
      CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");
      Country existedCountry = countryService.getCountryByCode(serviceLocation.getCountryCode());
      if(existedCountry == null)
        throw new ServiceException("country.error",new Object[] {serviceLocation.getCountryCode()}, null);
        //throw new RuntimeException("Country does not exist: " + serviceLocation.getCountryCode());
       serviceLocation.setCountry(existedCountry);

      if(serviceLocation.getStateCode() != null && (!serviceLocation.getStateCode().trim().equals("")))
      {
        State existedState = countryService.getStateByCodeAndCountryCode(
          serviceLocation.getStateCode(),
          serviceLocation.getCountryCode()
        );
         serviceLocation.setState(existedState);
      }
      else
        serviceLocation.setStateCode(null);

    }
    else
      serviceLocation.setCountryCode(null);
    CustAddrSeq custAddrSeq  = serviceLocation.getShipToCustAddress();


    //CustAddress custAddress = new CustAddress();
    CustAddress custAddress;
    Iterator itr = serviceLocation.getShipToCustAddress().getCustAddresses().iterator();
    while(itr.hasNext())
    {
     custAddress = (CustAddress)itr.next();
    custAddress.setCustAddrSeq(serviceLocation.getShipToCustAddress());
      custAddress.setAddress1(serviceLocation.getAddress1());
    custAddress.setAddress2(serviceLocation.getAddress2());
    custAddress.setAddress3(serviceLocation.getAddress3());
    custAddress.setAddress4(serviceLocation.getAddress4());
      custAddress.setState(serviceLocation.getStateCode());
      custAddress.setCountry(serviceLocation.getCountryCode());
      custAddress.setCity(serviceLocation.getCity());
      custAddress.setPostal(serviceLocation.getPostal());
      custAddress.setCounty(serviceLocation.getHouseType());
    custAddress.setTaxCode(taxCode);
    custAddress.setLocationNumber(serviceLocation.getShipToCustAddress().getCustAddrSeqId().getLocationNumber());
    custAddress.setCustCode(serviceLocation.getCustCode());
    getDao().save(custAddress);
    }
      //   saving customer record

     //Customer customer = serviceLocation.getShipToCustomer();
     Customer customer = customerService.getCustomerByCustCode(serviceLocation.getCustCode());

     String custName = Constants.SERVICE_LOCATION + serviceLocation.getName();

     customer.setName(custName);
    // customer.setStatusDate(new Date());
     customer.setPrimaryShipToAddress(new Integer(1));
     customer.setUpdateFlag(Constants.NEW);
     customer.setUpdateLimsFlag(Constants.NEW);

     getDao().save(customer);
     //end
     if((serviceLocation.getBranchName() != null) && ( serviceLocation.getBranchName() != ""))
    {
     Branch exitedBranch = null;
     exitedBranch = userService.getBranchByName(serviceLocation.getBranchName());
     if(exitedBranch != null)
     serviceLocation.setBranch(exitedBranch);
    }



    if(serviceLocation.getBranchName()== null || (serviceLocation.getBranchName().trim().equals("")))
      {
       Branch branch = null;
     serviceLocation.setBranch(branch);
    }


       serviceLocation.setAddress1("");
     serviceLocation.setAddress2("");
     serviceLocation.setAddress3("");
     serviceLocation.setAddress4("");
     ServiceLocationService serviceLocationService = (ServiceLocationService) ServiceLocator
       .getInstance().getBean("serviceLocationService");

     if (serviceLocation.getServiceLocationCode() != null ) {
         ServiceLocation exitedServiceLocation = getServiceLocationByServiceLocationCode(serviceLocation.getServiceLocationCode());
         if (exitedServiceLocation == null) {
           throw new ServiceException("servicelocation.name.error",new Object[] {serviceLocation.getCountryCode()}, null);
          // throw new RuntimeException("Service Location save error: " + serviceLocation.getServiceLocationCode());

         }
       }

    getDao().save(serviceLocation);
  }

  public ServiceLocation addServiceLocation(ServiceLocation serviceLocation,String taxCode)
  {
    if(serviceLocation == null) return null;
   UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
   CustomerService customerService = (CustomerService)ServiceLocator.getInstance().getBean("customerService");
   SequenceNumberService sequenceNumberService = (SequenceNumberService)ServiceLocator.getInstance().getBean("sequenceNumberService");


    if(serviceLocation.getCountryCode() != null && (!serviceLocation.getCountryCode().trim().equals("")))
    {
      CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");
      Country existedCountry = countryService.getCountryByCode(serviceLocation.getCountryCode());
      if(existedCountry == null)
        throw new ServiceException("country.error",new Object[] {serviceLocation.getCountryCode()}, null);
        //throw new RuntimeException("Country does not exist: " + serviceLocation.getCountryCode());
       serviceLocation.setCountry(existedCountry);

      if(serviceLocation.getStateCode() != null && (!serviceLocation.getStateCode().trim().equals("")))
      {
        State existedState = countryService.getStateByCodeAndCountryCode(
          serviceLocation.getStateCode(),
          serviceLocation.getCountryCode()
        );
         serviceLocation.setState(existedState);
      }
      else
        serviceLocation.setStateCode(null);

    }
    else
    {
    throw new ServiceException("servicelocation.country.error",new Object[] {serviceLocation.getName()}, null);
   // throw new RuntimeException("Country can not be null for Branch: " +   serviceLocation.getName());
    }

  Branch exitedBranch = null;

    if((serviceLocation.getBranchName() != null) && ( serviceLocation.getBranchName() != ""))
    {

     exitedBranch = userService.getBranchByName(serviceLocation.getBranchName());
     if( exitedBranch == null)
    {
     // throw new RuntimeException("Branch Id does not  exist: " + serviceLocation.getBranchName());
      throw new ServiceException("branch.error",new Object[] {serviceLocation.getBranchName()}, null);
    }
    }

      // if(serviceLocation.getBranchName()!= null ){

      //if(serviceLocation.getBranchName().equals(""))
    if(serviceLocation.getBranchName() == null || serviceLocation.getBranchName().trim().equals(""))
    {
         String finalNum="";
         String seqNumber=(sequenceNumberService.getSequenceNumber(Constants.SERVICE_LOCATION_SEQ)).toString();
           int sufixNumPartlength=seqNumber.length();
         if(sufixNumPartlength==1)

           finalNum="000000"+ seqNumber;

           if(sufixNumPartlength==2)

                       finalNum="00000"+seqNumber;

           if(sufixNumPartlength==3)

                       finalNum="0000"+seqNumber;

           if(sufixNumPartlength==4)

                       finalNum="000"+seqNumber;

           if(sufixNumPartlength==5)

                       finalNum="00"+seqNumber;

           if(sufixNumPartlength==6)

                       finalNum="0"+seqNumber;
           String servLocCode=Constants.USR+finalNum;
         serviceLocation.setServiceLocationCode(servLocCode);
     }
      //}
       Customer customer = new Customer();
     CustAddrSeq custAddrSeq = new CustAddrSeq();
     CustAddress custAddress = new CustAddress();


     String custName = Constants.SERVICE_LOCATION + serviceLocation.getName();

     customer.setName(custName);
     String custCodeSequence = (sequenceNumberService.getSequenceNumber(Constants.CUSTOMER_SEQ)).toString();
     customer.setCustCode(custCodeSequence);
     customer.setShipTo("Y");
     customer.setBillTo("N");
     customer.setSoldTo("N");
     customer.setInterunitInd(false);
     customer.setInterunitPendingInd(false);

     customer.setParentCustomer(customer);


       custAddress.setAddress1(serviceLocation.getAddress1());
     custAddress.setAddress2(serviceLocation.getAddress2());
       custAddress.setAddress3(serviceLocation.getAddress3());
       custAddress.setAddress4(serviceLocation.getAddress4());
     custAddress.setState(serviceLocation.getStateCode());
     custAddress.setCountry(serviceLocation.getCountryCode());
     custAddress.setCity(serviceLocation.getCity());
     custAddress.setPostal(serviceLocation.getPostal());
     custAddress.setCounty(serviceLocation.getHouseType());
       custAddress.setCustCode(custCodeSequence);
       custAddress.setTaxCode(taxCode);
       custAddress.setLocationNumber(new Integer(1));
        // Setting custAddress values
        int intYear = 103;
    int intMon = 0;
    int intDate = 1;
        Date dt = new Date(intYear,intMon,intDate);
        if(custAddress.getEffDate()==null)
          custAddress.setEffDate(dt);
        custAddress.setEffStatus(Constants.STATUS_A);
       //end
     Set custAddresses = new HashSet();

     custAddresses.add(custAddress);

     custAddrSeq.setCustAddresses(custAddresses);
     custAddrSeq.setCustomer(customer);

     CustAddrSeqId custAddrSeqId = new CustAddrSeqId();
     custAddrSeqId.setLocationNumber(new Integer(1));
     custAddrSeqId.setCustCode(customer.getCustCode());

     custAddrSeq.setCustAddrSeqId(custAddrSeqId);
     custAddress.setCustAddrSeq(custAddrSeq);
       // Setting custAddrSeq billTo,soldTo Corresponence and shipTo fields
     custAddrSeq.setBillTo(false);
     custAddrSeq.setSoldTo(false);
     custAddrSeq.setCorresponence(false);
     custAddrSeq.setShipTo(true);
     //end

       Set custAddrSeqs = new HashSet();
     custAddrSeqs.add(custAddrSeq);

     customer.setCustAddrSeqs(custAddrSeqs);

    // customer.setStatusDate(new Date());
     customer.setPrimaryShipToAddress(new Integer(1));
     customer.setNewFlag(Constants.NEW);
     customer.setUpdateFlag(Constants.NEW);
     customer.setUpdateLimsFlag(Constants.NEW);

     //Setting customer fields
     User user=userService.getUserByName(SecurityUtil.getUser().getLoginName());
     customer.setCurrencyCd(user.getCurrencyCd());
     customer.setSinceDate(dt);
     customer.setStatusDate(dt);
     customer.setStatus(Constants.STATUS_A);
     //end

     customer = getDao().save(customer);

     serviceLocation.setBranch(exitedBranch);

       serviceLocation.setCustCode(customer.getCustCode());

     if(customer.getCustAddrSeqs()!= null)
    {
     Iterator itr = (customer.getCustAddrSeqs()).iterator();
     custAddrSeq=(CustAddrSeq)itr.next();
       serviceLocation.setLocationNumber(custAddrSeq.getCustAddrSeqId().getLocationNumber());
    }

       serviceLocation.setAddress1("");
     serviceLocation.setAddress2("");
     serviceLocation.setAddress3("");
     serviceLocation.setAddress4("");

     ServiceLocationService serviceLocationService = (ServiceLocationService) ServiceLocator
       .getInstance().getBean("serviceLocationService");

     if (serviceLocation.getServiceLocationCode() != null ) {
       ServiceLocation exitedServiceLocation = getServiceLocationByServiceLocationCode(serviceLocation.getServiceLocationCode());
       if (exitedServiceLocation != null) {
        // throw new RuntimeException("Service Location already exist with this branch: " + serviceLocation.getServiceLocationCode());
         throw new ServiceException("servicelocation.branch.error",new Object[] {serviceLocation.getName()}, null);
       }
     }
  return getDao().save(serviceLocation);


  }

  public List searchServiceLocationsByName(String name)
  {
    if(name == null) return new ArrayList();

    return getDao().find(
      "from ServiceLocation s left join fetch s.country left join fetch s.state where upper(s.name) like '" + name.toUpperCase() + "%'and s.branchName is null order by s.name",
      null
    );
  }
  public List searchServiceLocationsByCity(String name)
  {
    if(name == null) return new ArrayList();

    return getDao().find(
          "select distinct s from ServiceLocation s left join fetch s.country left join fetch s.state where upper(s.city) like '" + name.toUpperCase() + "%' and s.branchName is null order by s.city,s.stateCode,s.countryCode",null
    );
  }
 public void searchServiceLocation(ServiceLocationSearch search,String reqForm,String sortFlag)
  {
   if(search == null) return;

    boolean hasWhere   = false;
    long stateId       = 0;
    long countryId     = 0;
    long sid       = 0;
    String countryCode = "";
    String stateCode   = "";
    String id          = "";
    String city        = "";
    String name      = "";
    String servCity    = "";
    String servStateCode= "";
    String servCountryCode ="";
    String serviceLocationCode = "";
    List results = null;

    StringBuffer sb = new StringBuffer();
    List params     = new ArrayList();

    CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");
    
    countryCode = search.getCountry().getValue();
    stateCode = search.getState().getValue();
    name = search.getName().getValue();
    serviceLocationCode = search.getServiceLocationCode().getValue();

    city = search.getCity().getValue();
    if((reqForm != null) && (reqForm.trim().equals("jobsForm")||reqForm.trim().equals("portJobsForm")||reqForm.trim().equals("destinationForm")))
    {
    /*  if(city.indexOf(',')!= -1)
      {
        String servc[]=city.split("\\,");
        servCity=servc[0];
        if(servc.length==2)
        {
          servStateCode=servc[1];
          stateCode=servStateCode;
        }
        if(servc.length==3)
        {
          servStateCode=servc[1];
          stateCode=servStateCode;
          servCountryCode=servc[2];
          countryCode=servCountryCode;
        }
      city=servCity;
    }*/


    if(countryCode!= null && (!countryCode.trim().equals(""))){

      Country country = countryService.getCountryByCode(countryCode);
      if(country != null)
      countryCode = country.getCountryCode();
        sb.append(" and s.countryCode = ?");
        params.add(countryCode);
        hasWhere = true;
      }
      if(stateCode != null && (!stateCode.trim().equals("")) && countryCode != null && (!countryCode.trim().equals(""))){

          State state = countryService.getStateByCode(stateCode, countryCode);
          if(state!= null)
          stateCode = state.getStateId().getStateCode();
          if(hasWhere) sb.append(" and ");
          else {
            hasWhere = true;
            sb.append(" and ");
          }

          sb.append("s.stateCode = ?");
          params.add(stateCode);
          hasWhere = true;
      }
      if((city != null) && !"".equals(city.trim())){
          if(hasWhere)
            sb.append(" and ");
          else
          {
          hasWhere = true;
          sb.append(" and ");
          }
          String ct =city +'%';
          sb.append(" upper(s.city) like ? ");
          params.add(ct.toUpperCase());
      }
           name = search.getName().getValue();
      if((name!= null)&&!"".equals(name.trim()))  {

        if(hasWhere) sb.append(" and ");
        else
        {
          hasWhere = true;
          sb.append(" and ");
        }
           String nm = name +'%';
          sb.append(" upper(s.name) like ?");
          params.add(nm.toUpperCase());
      }
      if((serviceLocationCode!= null)&&!"".equals(serviceLocationCode.trim()))  {

        if(hasWhere) sb.append(" and ");
        else
        {
          hasWhere = true;
          sb.append(" and ");
        }
           String nm = serviceLocationCode +'%';
          sb.append(" upper(s.serviceLocationCode) like ?");
          params.add(nm.toUpperCase());
      }
      Pagination pagination = search.getPagination();
      if(sortFlag != null && sortFlag.equals(""))
      {
      if(pagination != null)
      {
        if(pagination.getTotalRecord() > 0){

          List counts = getDao().find(
            "select count(s) from ServiceLocation s where s.branchName is null" + sb.toString(),
            params.toArray()
          );
          if(counts.size() > 0)
          {
            Number count = (Number)counts.get(0);
            pagination.setTotalRecord(count.intValue());
          }

        }

        results = getDao().find(
          "select distinct s from ServiceLocation s left join fetch s.shipToCustAddress cs left join fetch cs.custAddresses left join fetch s.country where s.branchName is null" + sb.toString()+" order by s.name",
        params.toArray(),
        pagination
        );

        pagination.calculatePages();

      }
      else
      {
          results = getDao().find(
                "select distinct s from ServiceLocation s left join fetch s.shipToCustAddress cs left join fetch cs.custAddresses left join fetch s.country where s.branchName is null" + sb.toString()+" order by s.name",
        params.toArray()
        );

      }
      setServiceLocationAddressWithComas(results);
      if(reqForm.trim().equals("destinationForm")){    	 
      results = getDistinctDestinationValues(results,countryService);

      }
      search.setPagination(pagination);
        search.setResults(results);
      }else
      {
        //  new code
        String orderByValue="";
        if(sortFlag.equals("address1"))
          orderByValue=" order by s.address1||s.address2||s.address3||s.address4";
        else
          orderByValue=" order by s."+sortFlag;
        if(pagination != null)
        {
          if(pagination.getTotalRecord() > 0){

            List counts = getDao().find(
              "select count(s) from ServiceLocation s where s.branchName is null" + sb.toString(),
              params.toArray()
            );
            if(counts.size() > 0)
            {
              Number count = (Number)counts.get(0);
              pagination.setTotalRecord(count.intValue());
            }

          }

          results = getDao().find(
            "select distinct s from ServiceLocation s left join fetch s.shipToCustAddress cs left join fetch cs.custAddresses left join fetch s.country where s.branchName is null" + sb.toString()+" order by s.name",
          params.toArray(),
          pagination
          );

          pagination.calculatePages();

        }
        // START : #119240
        /*results = getDao().find(
              "select distinct s from ServiceLocation s left join fetch s.shipToCustAddress cs left join fetch cs.custAddresses left join fetch s.country where s.branchName is null" + sb.toString()+orderByValue,
        params.toArray(),pagination
          );*/

        if(search.getSortOrderFlag()!=null && !search.getSortOrderFlag().equals("")){
        	results = getDao().find(
                    "select distinct s from ServiceLocation s left join fetch s.shipToCustAddress cs left join fetch cs.custAddresses left join fetch s.country where s.branchName is null" + sb.toString()+orderByValue+" "+search.getSortOrderFlag() ,
              params.toArray(),pagination
             );	
        }else{
        	results = getDao().find(
                "select distinct s from ServiceLocation s left join fetch s.shipToCustAddress cs left join fetch cs.custAddresses left join fetch s.country where s.branchName is null" + sb.toString()+orderByValue,
                params.toArray(),pagination
            );
        }
        // END : #119240
        
        setServiceLocationAddressWithComas(results);

        // search.setTotalResults(results);
        
        if(reqForm.trim().equals("destinationForm")){    	 
        results = getDistinctDestinationValues(results,countryService);
        }
        search.setPagination(pagination);
        search.setResults(results);
      }
    }else
    {

      if(countryCode!= null && (!countryCode.trim().equals(""))){

          Country country = countryService.getCountryByCode(countryCode);
          if(country != null)
          countryCode = country.getCountryCode();
      sb.append("where s.countryCode = ?");
      params.add(countryCode);
      hasWhere = true;
    }
    if(stateCode != null && (!stateCode.trim().equals("")) && countryCode != null && (!countryCode.trim().equals(""))){

        State state = countryService.getStateByCode(stateCode, countryCode);
        if(state != null)
        stateCode = state.getStateId().getStateCode();
        if(hasWhere) sb.append(" and ");
        else {
          hasWhere = true;
          sb.append(" where ");
        }

        sb.append("s.stateCode = ?");
        params.add(stateCode);
        hasWhere = true;
    }


    if((city != null) && !"".equals(city.trim())){
        if(hasWhere)
          sb.append(" and ");
        else
        {
        hasWhere = true;
        sb.append(" where ");
        }
        String ct =city +'%';
        sb.append(" upper(s.city) like ? ");
        params.add(ct.toUpperCase());
    }


         name = search.getName().getValue();
    if((name!= null)&&!"".equals(name.trim()))  {

      if(hasWhere) sb.append(" and ");
      else
      {
        hasWhere = true;
        sb.append(" where ");
      }
         String nm = name +'%';
        sb.append(" upper(s.name) like ?");
        params.add(nm.toUpperCase());
    }
    if((serviceLocationCode!= null)&&!"".equals(serviceLocationCode.trim()))  {

      if(hasWhere) sb.append(" and ");
      else
      {
        hasWhere = true;
        sb.append(" where ");
      }
         String nm = serviceLocationCode +'%';
        sb.append(" upper(s.serviceLocationCode) like ?");
        params.add(nm.toUpperCase());

    }


    Pagination pagination = search.getPagination();
    if(sortFlag != null && sortFlag.equals(""))
    {
    if(pagination != null)
    {
      if(pagination.getTotalRecord() > 0){
        List counts = getDao().find(
          "select count(s.serviceLocationCode) from ServiceLocation s " + sb.toString(),
          params.toArray()
        );

        if(counts.size() > 0)
        {
          Number count = (Number)counts.get(0);
          pagination.setTotalRecord(count.intValue());
        }
      }

      results = getDao().find(
            "select distinct s from ServiceLocation s left join fetch s.shipToCustAddress cs left join fetch cs.custAddresses left join fetch s.country left join fetch s.state " + sb.toString()+"order by s.name",
      params.toArray(),
      pagination
      );
      pagination.calculatePages();

    }
    else
    {
      results = getDao().find(
            "select distinct s from ServiceLocation s left join fetch s.shipToCustAddress cs left join fetch cs.custAddresses left join fetch s.country left join fetch s.state " + sb.toString()+"order by s.name",
      params.toArray()
      );

    }
    setServiceLocationAddressWithComas(results);
    search.setPagination(pagination);
       search.setResults(results);
    }
    else
    {
      //  new code
      String orderByValue="";
      if(sortFlag.equals("address1"))
        orderByValue=" order by s.address1||s.address2||s.address3||s.address4";
      else
        orderByValue=" order by s."+sortFlag;

      if(pagination != null)
      {
        if(pagination.getTotalRecord() > 0){
          List counts = getDao().find(
            "select count(s.serviceLocationCode) from ServiceLocation s " + sb.toString(),
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
       results = getDao().find(
            "select distinct s from ServiceLocation s left join fetch s.shipToCustAddress cs left join fetch cs.custAddresses left join fetch s.country left join fetch s.state " + sb.toString()+orderByValue,
        params.toArray(),pagination
        );

      setServiceLocationAddressWithComas(results);

        // search.setTotalResults(results);
      search.setPagination(pagination);
      search.setResults(results);

    }
  }
  }
 private void  setServiceLocationAddressWithComas(List results)
 {
   for (int i=0; i<results.size(); i++)
    {

         ServiceLocation serviceLocation =(ServiceLocation) results.get(i);

         CustAddress custAddress = new CustAddress();
       Iterator itr = serviceLocation.getShipToCustAddress().getCustAddresses().iterator();
       while(itr.hasNext())
       {
         boolean hasComa = false;
        custAddress = (CustAddress)itr.next();
        if(custAddress.getAddress1()!= null && !custAddress.getAddress1().trim().equals(""))
        {
          serviceLocation.setAddress1(custAddress.getAddress1());
          hasComa = true;
        }
        if(custAddress.getAddress2()!= null && !custAddress.getAddress2().trim().equals(""))
        {
          String servLocAddress = "";
          if(hasComa)
          {
            servLocAddress = ","+custAddress.getAddress2();
            serviceLocation.setAddress2(servLocAddress);
          }
          else
          {
           serviceLocation.setAddress2(custAddress.getAddress2());
           hasComa = true;
          }
        }
        if(custAddress.getAddress3()!= null && !custAddress.getAddress3().trim().equals(""))
        {
          String servLocAddress = "";
          if(hasComa)
          {
          servLocAddress = ","+custAddress.getAddress3();
          serviceLocation.setAddress3(servLocAddress);
          }
          else
          {
          serviceLocation.setAddress3(custAddress.getAddress3());
          hasComa = true;
          }
        }
        if(custAddress.getAddress4()!= null && !custAddress.getAddress4().trim().equals(""))
        {
          String servLocAddress ="";
          if(hasComa)
          {
            servLocAddress = ","+custAddress.getAddress4();
            serviceLocation.setAddress4(servLocAddress);
          }
          else
          serviceLocation.setAddress4(custAddress.getAddress4());
        }
    }
    }
  }

  public List getServiceLocationByNameAndCity(String name,String city){
      if(name == null) return new ArrayList();

      return getDao().find(
        "from ServiceLocation s where upper(trim(s.name)) like ?  and upper(s.city) like ?  and (s.branchName is null or s.branchName=' ')",
        new Object[]{name.trim().toUpperCase() , city.toUpperCase() }
      );
  }
 public List getServiceLocationByNameAndPortValues(String name,String city,String stateName,String countryName){
      if(name == null) return new ArrayList();

      if(!stateName.equals(""))
      {
       /* return getDao().find(
          "from ServiceLocation s left join fetch s.country c left join fetch s.state st where upper(s.name) like '" + name.trim().toUpperCase() + "%'" + " and c.name='"+countryName.trim()+"' and st.name='"+stateName.trim()+"' and upper(s.city)='"+city.trim().toUpperCase()+"' and (s.branchName is null or s.branchName=' ')",null
        );*/

        return getDao().find(
              "from ServiceLocation s left join fetch s.country c left join fetch s.state st where upper(trim(s.name)) like '" + name.trim().toUpperCase() + "%'" + " and c.name=? and st.name=? and upper(s.city)=? and (s.branchName is null or s.branchName=' ')",new Object[] {countryName.trim(),stateName.trim(),city.trim().toUpperCase()},null
            );
      }else
      {
         /* return getDao().find(
          "from ServiceLocation s left join fetch s.country c left join fetch s.state st where upper(s.name) like '" + name.trim().toUpperCase() + "%'" + " and c.name='"+countryName.trim()+"' and upper(s.city)='"+city.trim().toUpperCase()+"' and (s.stateCode is null or s.stateCode=' ') and (s.branchName is null or s.branchName=' ')",null
        );*/

        return getDao().find(
              "from ServiceLocation s left join fetch s.country c left join fetch s.state st where upper(s.name) like '" + name.trim().toUpperCase() + "%'" + " and c.name=? and upper(s.city)=? and (s.stateCode is null or s.stateCode=' ') and (s.branchName is null or s.branchName=' ')",new Object[] {countryName.trim(),city.trim().toUpperCase()},null
            );
      }
  }
 public List getServiceLocationByPortValues(String name,String city,String stateName,String countryName){


      if(name == null) return new ArrayList();

      if(!stateName.equals(""))
      {
        /*return getDao().find(
        "from ServiceLocation s left join fetch s.country c left join fetch s.state st where upper(s.name) = '" + name.trim().toUpperCase() + "'" + " and c.name='"+countryName.trim()+"' and st.name='"+stateName.trim()+"' and upper(s.city)='"+city.trim().toUpperCase()+"' and (s.branchName is null or s.branchName=' ')",null
        );*/

        return getDao().find(
              "from ServiceLocation s left join fetch s.country c left join fetch s.state st where upper(trim(s.name)) = ? and c.name= ? and st.name= ? and upper(s.city)= ? and (s.branchName is null or s.branchName=' ')",new Object[]{name.trim().toUpperCase(),countryName.trim(),stateName.trim(),city.trim().toUpperCase()},null
            );
      }else
      {

         /* return getDao().find(
          "from ServiceLocation s left join fetch s.country c left join fetch s.state st where upper(s.name) = '" + name.trim().toUpperCase() + "'" + " and c.name='"+countryName.trim()+"' and s.stateCode is null and upper(s.city)='"+city.trim().toUpperCase()+"' and (s.branchName is null or s.branchName=' ')" ,null
        );*/

          return getDao().find(
              "from ServiceLocation s left join fetch s.country c left join fetch s.state st where upper(s.name) = ? and c.name=? and s.stateCode is null and upper(s.city)= ? and (s.branchName is null or s.branchName=' ')" ,new Object[]{name.trim().toUpperCase(),countryName.trim(),city.trim().toUpperCase()},null
            );
      }
}
 public ServiceLocation getServiceLocationByBranchName(String name){

   if(name == null) return new ServiceLocation();
   List servLoc = new ArrayList();

   servLoc=getDao().find(
          "from ServiceLocation s left join fetch s.country left join fetch s.state where s.branchName = '" + name +"'",
          null
        );
   if(servLoc.size()>0)
     return (ServiceLocation)servLoc.get(0);
   else
     return null;

 }
 //For itrack issue 24329
 /**
  * Name :getServiceLocationByCityAndStateAndCountry 
  * Date :May 2, 2009 
  * Purpose : to get Service location instance
  * @param city
  * @param state
  * @param country
  * @return List
  */
 public List getServiceLocationByCityAndStateAndCountry(String city, String state, String country){
	 
	  List serViceLocations = null;
      if((city != null && !city.trim().equals("")) && (state != null && !state.trim().equals(""))&& (country != null && !country.trim().equals(""))){
    	  serViceLocations = getDao().find(
	        "from ServiceLocation s left join fetch s.state st left join fetch s.country ct where upper(s.city) = ? and st.name = ? and ct.name = ?",
	        new Object[]{city.trim().toUpperCase(), state, country}
	      );
    	  if(serViceLocations != null && serViceLocations.size()>0){
    		  return serViceLocations;
    	  }
      } else if((city != null && !city.trim().equals(""))&& (country != null && !country.trim().equals(""))){
    	  serViceLocations = getDao().find(
  		        "from ServiceLocation s left join fetch s.country ct where upper(s.city) = ? and ct.name = ?",
  		        new Object[]{city.trim().toUpperCase() , country}
  		      );
    	  if(serViceLocations != null && serViceLocations.size()>0){
    		  return serViceLocations;
    	  }
      } 
    return serViceLocations;
}
 /**
  * Name :getDistinctDestinationValues 
  * Date :May 7, 2009 
  * Purpose : to get distinct destination instance
  * @param serviceLocations
  * @param countryService
  * @return List
  */
 public List getDistinctDestinationValues(List serviceLocations,CountryService countryService){
	 ArrayList al = new ArrayList();
     List servLocations = new ArrayList();
	 for(int i=0;i<serviceLocations.size();i++){
			String servCountry="";
			String servState=""; 
			ServiceLocation serviceLocation = (ServiceLocation)serviceLocations.get(i);
			Country country =null;
			if(serviceLocation.getCountryCode()!= null)
			{
				country = countryService.getCountryByCode(serviceLocation.getCountryCode());
			}				
			if(serviceLocation.getStateCode()!= null && serviceLocation.getCountryCode()!= null)
			{
				State state = countryService.getStateByCodeAndCountryCode(serviceLocation.getStateCode(),serviceLocation.getCountryCode());
				if(state != null && (country==null || country.getShowState())){
					servState =","+ state.getName();
				}
			}
			if(serviceLocation.getCountryCode()!= null)
			{
				if(country != null)
				servCountry = ","+country.getName();
			}
			String portValue = serviceLocation.getCity() + servState + servCountry;
		    String value =serviceLocation.getCity();
			boolean uniqueFlag = true;
            if(al != null && al.size() > 0)
            {      
         	 for(int j=0;j<al.size();j++)
              {
                  String alStr = (String) al.get(j);
                  if(alStr.trim().equalsIgnoreCase(portValue))
                  {
                      uniqueFlag = false;
                      break;
                  }
                  else { 
                 	  uniqueFlag = true;
                  }
              }
           }
           if(uniqueFlag){
			 al.add(portValue);
			 servLocations.add(serviceLocation);
           }
		}
	 return servLocations;
 }
}

