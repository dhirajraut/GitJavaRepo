package com.intertek.test;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.intertek.entity.BusUnitVatLoc;
import com.intertek.entity.Country;
import com.intertek.entity.State;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.MenuService;

public class UserTest
{
  public static void main(String[] args) throws Exception
  {
    ApplicationContext ctx = new FileSystemXmlApplicationContext(
      new String[]
      {
        "../web/WEB-INF/applicationContext.xml"
      }
    );

    ServiceLocator.getInstance().setApplicationContext(ctx);
/*
    MetaDataManager metaDataMgr = (MetaDataManager)ServiceLocator.getInstance().getBean("metaDataMgr");
    metaDataMgr.loadMetaData();

    //MetaDataSet.getInstance().loadMetaData();
    ViewMeta viewMeta = metaDataMgr.getViewMeta(new String[] {"Division2", "INSP"});
    System.out.println(viewMeta);

    viewMeta = metaDataMgr.getViewMeta(new String[] {"Division1", "INSP"});
    System.out.println(viewMeta);

    JobOrder jobOrder = new JobOrder();
    System.out.println("job.getClass().getSimpleName() = " + jobOrder.getClass().getSimpleName());

    UserService userService = (UserService)ctx.getBean("userService");
    User user1 = userService.getUserByName("123");
    String orgName1 = user1.getBranch().getBusinessUnit().getOrganization().getName();

    System.out.println("=========== orgName1 = " + orgName1);
    viewMeta = metaDataMgr.getViewMeta(new String[] {orgName1, "INSP"});
    System.out.println(viewMeta);
    ObjectMeta objectMeta = viewMeta.getObjectMeta(jobOrder.getClass().getSimpleName());
    System.out.println(objectMeta);


    User user2 = userService.getUserByName("456");
    String orgName2 = user2.getBranch().getBusinessUnit().getOrganization().getName();

    System.out.println("=========== orgName2 = " + orgName2);
    viewMeta = metaDataMgr.getViewMeta(new String[] {orgName2, "INSP"});
    //System.out.println(viewMeta2);
    objectMeta = viewMeta.getObjectMeta(jobOrder.getClass().getSimpleName());
    System.out.println(objectMeta);

    DropDownManager dropDownMgr = (DropDownManager)ServiceLocator.getInstance().getBean("dropDownMgr");
    dropDownMgr.loadDropDownData();
    System.out.println("==== dropdown: " + dropDownMgr);

    ShippingAgentService shippingAgentService = (ShippingAgentService)ServiceLocator.getInstance().getBean("shippingAgentService");
    System.out.println("======== in ShippingAgentController: shippingAgentService = " + shippingAgentService);

    List agents = shippingAgentService.searchShippingAgentsByName("myT");
    System.out.println("==== agents: " + agents);

    JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
    try
    {
      JobOrder jobOrder = jobService.getJobOrderByJobNumber("123");
      System.out.println("==== jobOrder: " + jobOrder);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

    CustomerService customerService = (CustomerService)ServiceLocator.getInstance().getBean("customerService");
    try
    {
      ContactSearch search = new ContactSearch();
      search.getContactId().setValue(new Long(24));
      Pagination pagination = new Pagination(1, 5, 1, 10);
      search.setPagination(pagination);

      customerService.searchContact(search);
      System.out.println("==== search.getResults(): " + search.getResults());
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

    ShippingAgentService shippingAgentService = (ShippingAgentService)ServiceLocator.getInstance().getBean("shippingAgentService");
    try
    {
      ShippingAgentSearch search = new ShippingAgentSearch();
      search.getName().setValue("123");
      Pagination pagination = new Pagination(1, 5, 1, 10);
      search.setPagination(pagination);

      shippingAgentService.searchShippingAgent(search);
      System.out.println("==== search.getResults(): " + search.getResults());
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

    UserService userService = (UserService)ctx.getBean("userService");
    try
    {
      BusinessUnit bu = userService.getBusinessUnitByName("USA801");

      System.out.println("==== bu.getBusUnitVatLocs().size(): " + bu.getBusUnitVatLocs().size());

      BusUnitVatLoc oldVatLoc = (BusUnitVatLoc)bu.getBusUnitVatLocs().iterator().next();

      BusUnitVatLoc vatLoc = createBusUnitVatLoc("United States of America", "New Jersey");
      vatLoc.setBusinessUnit(bu);
      bu.getBusUnitVatLocs().add(vatLoc);

      boolean isEqual = oldVatLoc.equals(vatLoc);

      System.out.println("========== isEqual = " + isEqual);

      System.out.println("===a= bu.getBusUnitVatLocs().size(): " + bu.getBusUnitVatLocs().size());

      vatLoc = createBusUnitVatLoc("United States of America", "Pennsylvania");
      vatLoc.setBusinessUnit(bu);
      bu.getBusUnitVatLocs().add(vatLoc);

      System.out.println("===b= bu.getBusUnitVatLocs().size(): " + bu.getBusUnitVatLocs().size());

      userService.saveBusinessUnit(bu);

      System.out.println("==== bu.getBusUnitVatLocs().size(): " + bu.getBusUnitVatLocs().size());
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }

    ShippingAgentService shippingAgentService = (ShippingAgentService)ServiceLocator.getInstance().getBean("shippingAgentService");
    CountryService countryService = (CountryService)ServiceLocator.getInstance().getBean("countryService");
    try
    {
      ShippingAgent sa = shippingAgentService.getShippingAgentByName("testAgent1");
      System.out.println("======== sa.getCountryCode(): " + sa.getCountryCode());
      System.out.println("======== sa.getCountry().getCountryCode(): " + sa.getCountry().getCountryCode());
      System.out.println("======== sa.getState().getStateId().getCountryCode(): " + sa.getState().getStateId().getCountryCode());
      System.out.println("======== sa.getState().getStateId().getStateCode(): " + sa.getState().getStateId().getStateCode());


      sa = new ShippingAgent();
      sa.setName("MyTestSA222");
      Country country = countryService.getCountryByCode("USA");
      State state = countryService.getStateByCodeAndCountryCode("AK", "USA");
      sa.setCountryCode("USA");
      sa.setStateCode("AK");

      //sa.setCountry(country);
      //sa.setState(state);

      shippingAgentService.saveShippingAgent(sa);
    }
    catch(Exception e)
    {
      e.printStackTrace();
    }


    CalculatorService calculatorService = (CalculatorService)ctx.getBean("calculatorService");
    ServiceRate sr = calculatorService.getServiceRate(
      "k1", "e1", "*", "*", "*", "*", 100, 100.0, "20071020"
    );

    System.out.println("======= sr = " + sr);
    if(sr != null)
    {
      System.out.println("sr.getServiceRateId().getContractId() = " + sr.getServiceRateId().getContractId());
    }

    List results = calculatorService.getContractExpressions(
      "s1", "k1", "pb1", "*", "20071020", "ENG"
    );

    System.out.println("======= ContractExpressions = " + results);
    for(int i=0; i<results.size(); i++)
    {
      ContractExpression ce = (ContractExpression)results.get(i);
      System.out.println("=========== ce.getContractExpressionId().getServiceName() = " + ce.getContractExpressionId().getServiceName());
      System.out.println("=========== ce.getVisibility() = " + ce.getVisibility());
    }

    results = calculatorService.getControls(
      "s1", "k1", "pb1", "20071020", "ENG"
    );

    System.out.println("======= controls = " + results);
    for(int i=0; i<results.size(); i++)
    {
      Control c = (Control)results.get(i);
      System.out.println("=========== Control.getControlId().getServiceName() = " + c.getControlId().getServiceName());
      System.out.println("=========== c.getVisibility() = " + c.getVisibility());
    }

    results = calculatorService.getServices(
      "k1", "pb1", "20071020", "ENG"
    );

    System.out.println("======= services = " + results);
    for(int i=0; i<results.size(); i++)
    {
      Service s = (Service)results.get(i);
      System.out.println("=========== service.getServiceId().getServiceName() = " + s.getServiceId().getServiceName());
      System.out.println("=========== s.getVisibility() = " + s.getVisibility());
    }
*/
    MenuService menuService = (MenuService)ServiceLocator.getInstance().getBean("menuService");
    List menuTree = menuService.getMenuTree();

    System.out.println("Done");
    System.exit(0);
  }

  public static BusUnitVatLoc createBusUnitVatLoc(String countryName, String stateName)
  {
    BusUnitVatLoc vatLoc = new BusUnitVatLoc();

    Country country = new Country();
    country.setName(countryName);

    State state = new State();
    state.setName(stateName);

    state.setCountry(country);

    vatLoc.setCountry(country);
    //vatLoc.setState(state);

    return vatLoc;
  }
}
