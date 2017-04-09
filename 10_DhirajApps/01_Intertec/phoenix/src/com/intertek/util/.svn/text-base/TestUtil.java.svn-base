package com.intertek.util;

import java.io.*;
import java.util.*;

import com.intertek.locator.*;
import com.intertek.service.*;
import com.intertek.util.*;
import com.intertek.pagination.*;
import com.intertek.domain.*;
import com.intertek.entity.*;
import com.intertek.meta.*;
import com.intertek.meta.dropdown.*;
import com.intertek.servicetype.*;
import com.intertek.calculator.*;

public class TestUtil
{
  public static void checkTests(List testPriceList)
  {
    if(testPriceList == null) return;

    for(int i=0; i<testPriceList.size(); i++)
    {
      TestPrice testPrice = (TestPrice)testPriceList.get(i);

      if(testPrice.getTest() == null)
      {
        Test test = new Test();

        System.out.println("========== test id " + testPrice.getTestPriceId().getTestId());

        testPrice.setTest(test);
      }
    }
  }

  public static List getTestPriceExtList(List testIds, List testPriceList, List testList, String contractCode)
  {
    List results = new ArrayList();
    if((testIds == null) || (testPriceList == null) || (testList == null) || (contractCode == null)) return results;

    Map extMap = new HashMap();

    for(int i=0; i<testPriceList.size(); i++)
    {
      TestPrice testPrice = (TestPrice)testPriceList.get(i);

      String testId = testPrice.getTestPriceId().getTestId();
      TestPriceExt testPriceExt = (TestPriceExt)extMap.get(testId);
      if(testPriceExt == null)
      {
        testPriceExt = new TestPriceExt();
        testPriceExt.setTestId(testId);

        extMap.put(testId, testPriceExt);
      }

      if(contractCode.equals(testPrice.getTestPriceId().getContractId()))
      {
        testPriceExt.getTestPrices().add(testPrice);
      }
      else
      {
        testPriceExt.getPriceBookTestPrices().add(testPrice);
      }
    }

    for(int i=0; i<testList.size(); i++)
    {
      Test test = (Test)testList.get(i);
      String testId = test.getTestId();
      TestPriceExt testPriceExt = (TestPriceExt)extMap.get(testId);
      if(testPriceExt != null)
      {
        testPriceExt.setTest(test);
      }
    }


    for(int i=0; i<testIds.size(); i++)
    {
      String testId = (String)testIds.get(i);

      TestPriceExt testPriceExt = (TestPriceExt)extMap.get(testId);
      if(testPriceExt != null)
      {
        results.add(testPriceExt);
      }
    }

    return results;
  }

  public static List getContractTestPriceExtList(List testIds, List testPriceList, List contractTestList)
  {
    List results = new ArrayList();
    if((testIds == null) || (testPriceList == null) || (contractTestList == null)) return results;

    Map extMap = new HashMap();

    for(int i=0; i<testIds.size(); i++)
    {
      String testId = (String)testIds.get(i);

      ContractTestPriceExt testPriceExt = (ContractTestPriceExt)extMap.get(testId);
      if(testPriceExt == null)
      {
        testPriceExt = new ContractTestPriceExt();
        testPriceExt.setTestId(testId);

        extMap.put(testId, testPriceExt);

        results.add(testPriceExt);
      }
    }

    for(int i=0; i<testPriceList.size(); i++)
    {
      TestPrice testPrice = (TestPrice)testPriceList.get(i);

      String testId = testPrice.getTestPriceId().getTestId();
      ContractTestPriceExt testPriceExt = (ContractTestPriceExt)extMap.get(testId);
      if(testPriceExt != null)
      {
        testPriceExt.getTestPrices().add(testPrice);
      }
    }

    for(int i=0; i<contractTestList.size(); i++)
    {
      ContractTest contractTest = (ContractTest)contractTestList.get(i);
      String testId = contractTest.getContractTestId().getTestId();
      ContractTestPriceExt testPriceExt = (ContractTestPriceExt)extMap.get(testId);
      if(testPriceExt != null)
      {
        testPriceExt.setContractTest(contractTest);
      }
    }

    return results;
  }

  public static void loadPriceBookTestPrices(EditContractTest editContractTest, int pageNumber)
  {
    if(editContractTest == null) return;
    TestService testService = (TestService)ServiceLocator.getInstance().getBean("testService");

    Pagination priceBookTestPricePagination = null;
    if(pageNumber >= 1) priceBookTestPricePagination = new Pagination(1,10, pageNumber, 10);
    List priceBookTestIds = testService.loadPriceBookTestIdsByPriceBookIdAndContractCode(
      editContractTest.getContract().getWorkingPB(),
      editContractTest.getContract().getContractCode(),
      priceBookTestPricePagination
    );
    if(pageNumber >= 1) priceBookTestPricePagination.calculatePages();
    priceBookTestIds = cleanList(priceBookTestIds);
    editContractTest.setPriceBookTestPricePagination(priceBookTestPricePagination);

    List priceBookTestPriceList = testService.loadTestPricesByTestIds(
      editContractTest.getContract().getWorkingPB(),
      editContractTest.getContract().getContractCode(),
      priceBookTestIds
    );
    List priceBookTestList = testService.loadTestsByTestIds(priceBookTestIds);

    List priceBookTestPriceExtList = TestUtil.getTestPriceExtList(
      priceBookTestIds,
      priceBookTestPriceList,
      priceBookTestList,
      editContractTest.getContract().getContractCode()
    );
    editContractTest.setTestPriceExtList(priceBookTestPriceExtList);
  }

  public static void loadContractTestPrices(EditContractTest editContractTest, int pageNumber)
  {
    if(editContractTest == null) return;
    TestService testService = (TestService)ServiceLocator.getInstance().getBean("testService");

    Pagination contractTestPricePagination = null;
    if(pageNumber >= 1) contractTestPricePagination = new Pagination(1, 10, pageNumber, 10);
    List contractTestIds = testService.loadContractTestIdsByPriceBookIdAndContractCode(
      editContractTest.getContract().getContractCode(),
      contractTestPricePagination
    );
    if(pageNumber >= 1) contractTestPricePagination.calculatePages();
    contractTestIds = cleanList(contractTestIds);
    editContractTest.setContractTestPricePagination(contractTestPricePagination);

    List contractTestPriceList = testService.loadTestPricesByTestIds(
      editContractTest.getContract().getContractCode(),
      contractTestIds
    );
    List contractTestList = testService.loadContractTestsByTestIds(contractTestIds);

    List contractTestPriceExtList = TestUtil.getContractTestPriceExtList(
      contractTestIds,
      contractTestPriceList,
      contractTestList
    );
    editContractTest.setContractTestPriceExtList(contractTestPriceExtList);
  }

  public static List cleanList(List list)
  {
    List results = new ArrayList();
    if(list == null) return results;

    for(int i=0; i<list.size(); i++)
    {
      Object object = list.get(i);
      if(object instanceof Object[])
      {
        Object[] objects = (Object[])object;
        results.add(objects[0]);
      }
      else results.add(object);
    }

    return results;
  }

  public static void loadSelectedTestsForPb(
    CopyPbTestPrice copyPbTestPrice,
    int pageNumber
  )
  {
    List results = new ArrayList();

    if((copyPbTestPrice == null) || (pageNumber < 1)) return;
    TestService testService = (TestService)ServiceLocator.getInstance().getBean("testService");

    Pagination pagination = new Pagination(1, 10, pageNumber, 10);
    List testIdAndDescs = testService.getPbTestIdAndDescriptions(
      copyPbTestPrice.getPriceBookId(),
      copyPbTestPrice.getContractCode(),
      copyPbTestPrice.getTestId(),
      copyPbTestPrice.getDescription(),
      pagination
    );
    pagination.calculatePages();
    copyPbTestPrice.setPagination(pagination);

    for(int i=0; i<testIdAndDescs.size(); i++)
    {
      Object[] objects = (Object[])testIdAndDescs.get(i);
      SelectedTest selectedTest = new SelectedTest();
      Test test = new Test();
      for(int j=0; j<objects.length; j++)
      {
        if(j == 0) test.setTestId((String)objects[0]);
        if(j == 1) test.setTestDescription((String)objects[1]);
      }
      selectedTest.setTest(test);
      results.add(selectedTest);
    }

    copyPbTestPrice.setSelectedTests(results);
  }

  public static void copySelectedTestPricesFromPb(CopyPbTestPrice copyPbTestPrice)
  {
    if(copyPbTestPrice == null) return;
    TestService testService = (TestService)ServiceLocator.getInstance().getBean("testService");

    testService.copySelectedTestPricesFromPb(copyPbTestPrice);
  }

  public static void loadTestPrices(TestPriceExt testPriceExt)
  {
    if(testPriceExt == null) return;
    TestService testService = (TestService)ServiceLocator.getInstance().getBean("testService");

    List testIds = new ArrayList();
    testIds.add(testPriceExt.getTestId());

    List testPriceList = testService.loadTestPricesByTestIds(
      testPriceExt.getContractCode(),
      testIds
    );
    testPriceExt.setTestPrices(testPriceList);

    Test test = testService.getTestById(testPriceExt.getTestId());
    testPriceExt.setTest(test);
  }

  public static void addTestPrice(TestPriceExt testPriceExt)
  {
    if(testPriceExt == null) return;

    List testPriceList = testPriceExt.getTestPrices();
    if(testPriceList == null) return;

    int rowsToAdd = testPriceExt.getRowsToAdd();
    if(rowsToAdd <= 0) rowsToAdd = 1;
    testPriceExt.setRowsToAdd(rowsToAdd);

    for(int i=0; i<rowsToAdd; i++)
    {
      TestPrice testPrice = createTestPrice();

      if(testPriceList.size() > 0)
      {
        TestPrice lastTestPrice = (TestPrice)testPriceList.get(0);

        Date[] newDatePair = new Date[] {
          testPrice.getTestPriceId().getBeginDate(),
          testPrice.getEndDate()
        };

        Date[] oldDatePair = new Date[] {
          lastTestPrice.getTestPriceId().getBeginDate(),
          lastTestPrice.getEndDate()
        };

        DateUtil.calculateBeginDateAndEndDate(newDatePair, oldDatePair);

        testPrice.getTestPriceId().setBeginDate(newDatePair[0]);
        testPrice.setEndDate(newDatePair[1]);

        lastTestPrice.getTestPriceId().setBeginDate(oldDatePair[0]);
        lastTestPrice.setEndDate(oldDatePair[1]);

        testPrice.getTestPriceId().setLocation(lastTestPrice.getTestPriceId().getLocation());
      }
      else
      {
        testPrice.getTestPriceId().setLocation("*");
      }

      testPrice.getTestPriceId().setTestId(testPriceExt.getTestId());
      testPrice.getTestPriceId().setContractId(testPriceExt.getContractCode());

      testPriceList.add(0, testPrice);
    }
  }

  public static void removeTestPrice(TestPriceExt testPriceExt, int index)
  {
    if((testPriceExt == null) || (index < 0)) return;

    List testPriceList = testPriceExt.getTestPrices();
    if(testPriceList == null) return;

    if(index < testPriceList.size()) testPriceList.remove(index);
  }

  public static TestPrice createTestPrice()
  {
    TestPrice testPrice = new TestPrice();

    TestPriceId testPriceId = new TestPriceId();
    testPriceId.setTestType("C");
    testPriceId.setBeginDate(new Date());
    testPriceId.setMinQty(0);
    testPriceId.setMaxQty(9999);
    testPrice.setTestPriceId(testPriceId);

    testPrice.setContractRef("CONT");
    testPrice.setEndDate(DateUtil.parseDate("12/31/2099", "MM/dd/yyyy"));
    testPrice.setSeqNum(0);
    testPrice.setCurrencyCD("USD");

    return testPrice;
  }

  public static void saveTestPrices(TestPriceExt testPriceExt)
  {
    if(testPriceExt == null) return;
    TestService testService = (TestService)ServiceLocator.getInstance().getBean("testService");

    testService.saveTestPrices(testPriceExt);
  }

  public static List getNextTestPriceList(List testPrices, String zone, int startIndex)
  {
    List results = new ArrayList();

    if((testPrices == null) || (zone == null)) return results;

    for(int i=startIndex; i<testPrices.size(); i++)
    {
      TestPrice testPrice = (TestPrice)testPrices.get(i);
      if(zone.equals(testPrice.getTestPriceId().getLocation()))
      {
        results.add(testPrice);
      }
    }

    return results;
  }

  public static void addContractTest(ContractTestExt contractTestExt)
  {
    if(contractTestExt == null) return;

    TestService testService = (TestService)ServiceLocator.getInstance().getBean("testService");

    ContractTest contractTest = contractTestExt.getContractTest();
    if(contractTest == null) return;

    Test test = contractTest.getTest();
    if(test == null) return;

    test.setTestId(contractTest.getContractTestId().getTestId());
    populateTest(test);

    testService.addContractTest(contractTest);
  }

  public static void populateTest(Test test)
  {
    if(test == null) return;

    test.setRbKey("D");
    test.setMethodNum("D");
    test.setMethodTitle("D");
    test.setBusStream("D");
    test.setLinkedMethod("D");
    test.setMethodPbNote("D");
    test.setTechRemarks("D");
    test.setEquivMethod("D");
    test.setBillingGuideLine("D");
    test.setTestCategory("D");
    test.setTestStatus("D");
    test.setSdo("D");
    test.setTechnique("D");
    test.setSearchText254("D");
    test.setNotes254("D");
    test.setPbDesc("D");
  }
}

