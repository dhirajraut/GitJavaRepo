package com.intertek.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

import com.intertek.domain.SelectedTest;
import com.intertek.domain.CopyPbTestPrice;
import com.intertek.domain.TestPriceExt;
import com.intertek.entity.Contract;
import com.intertek.entity.ContractTest;
import com.intertek.entity.Test;
import com.intertek.entity.TestPrice;
import com.intertek.entity.TestPriceId;
import com.intertek.pagination.Pagination;
import com.intertek.util.EntityUtil;
import com.intertek.exception.ServiceException;

public class TestServiceImpl extends BaseService implements TestService
{
	//101771 START
	/* public List contractTests;
	  
	  public List getContractTests() {
		return contractTests;
	}


	public void setContractTests(List contractTests) {
		this.contractTests = contractTests;
	}*/
	//101771 END
	
  public static String SQL_1 = "SELECT DISTINCT t.TEST_ID, t.ITS_TEST_DESCR FROM PS_ITS_TEST_PB tpb, PS_ITSC_TEST t "
    + " WHERE tpb.PRICEBOOK_ID = ? "
    + " AND t.TEST_ID = tpb.TEST_ID "
    + " AND t.TEST_ID NOT IN ( "
    + "   SELECT tpr.TEST_ID FROM PS_ITSC_TEST_PRICE tpr "
    + "   WHERE tpr.CFG_CONTRACT_ID = ?  "
    + " ) ";
  public static String SQL_2 = " ORDER BY t.TEST_ID";

  public Test getTestById(String testId)
  {
    List tests = getDao().find(
      "from Test t where t.testId = ?",
      new Object[] { testId }
    );

    if(tests.size() > 0) return (Test)tests.get(0);

    return null;
  }

  public TestPrice getTestPrice(
    String contractId,
    String priceBookId,
    String testId,
    String location,
    Integer quantity,
    String nominationDateStr
  )
  {
    List results = getDao().findByNamedSqlQuery(
      "getTestPrice_FN",
      new Object[] { contractId, priceBookId, testId, location, quantity, nominationDateStr }
    );

    if(results.size() > 0) return (TestPrice)results.get(0);

    return null;
  }


  public List getTests(
    String contractId,
    String priceBookId,
    String productGroup,
    String contractSearchCD,
    String value,
    String searchType,
    String location,
    String nominationDateStr,
    String languageCD
  )
  {



    List searchResults = getDao().findByNamedSqlQuery(
      "getTest_FN",
      new Object[] {
        contractId,
        priceBookId,
        productGroup,
        contractSearchCD,
        value,
        searchType,
        location,
        nominationDateStr,
        languageCD
      }
    );

    List results = new ArrayList();
    for(int i=0; i<searchResults.size(); i++)
    {
      Object[] objs = (Object[])searchResults.get(i);

      SelectedTest selectedTest = new SelectedTest();
      Test test = new Test();

      test.setTestId((String)objs[0]);
      test.setTestDescription((String)objs[1]);
      selectedTest.setContractRef((String)objs[2]);
      selectedTest.setTest(test);

      results.add(selectedTest);
    }
    
    //101771 START
    /*List allTests = getDao().findByNamedSqlQuery(
    	      "getTest_FN",
    	      new Object[] {
    	        contractId,
    	        priceBookId,
    	        "None",
    	        "BOTH",
    	        "",
    	        "BOTH",
    	        location,
    	        nominationDateStr,
    	        languageCD
    	      }
    	    );
    List completeList = new ArrayList();
    for(int i=0; i<allTests.size(); i++)
    {
      Object[] objs = (Object[])allTests.get(i);

      SelectedTest selectedTest = new SelectedTest();
      Test test = new Test();

      test.setTestId((String)objs[0]);
      test.setTestDescription((String)objs[1]);
      selectedTest.setContractRef((String)objs[2]);
      selectedTest.setTest(test);

      completeList.add(selectedTest);
    }
    //System.out.println("contract Tests....."+completeList);
    //System.out.println("contract Tests results....."+results);
    
    this.contractTests = completeList;*/
    //101771 END
    
    return results;
  }

  public int getTotalRecordOfPbTestIdAndDescriptions(
    String priceBookId,
    String contractCode,
    String testId,
    String description
  )
  {
    if((priceBookId == null) || (contractCode == null)) return 0;

    int count = 0;
    StringBuffer sb = new StringBuffer();
    sb.append("select count(*) from (");
    sb.append(SQL_1);
    if(testId != null)
    {
      sb.append(" AND t.TEST_ID LIKE ? ");
      count ++;
    }

    if(description != null)
    {
      sb.append(" AND t.ITS_TEST_DESCR LIKE ? ");
      count ++;
    }
    sb.append(")");

    Object[] objects = new Object[ 2 + count];
    objects[0] = priceBookId;
    objects[1] = contractCode;
    for(int i=0; i<count; i++)
    {
      if(i == 0) objects[2] = "%" + testId + "%";
      if(i == 1) objects[3] = "%" + description + "%";
    }

    List results = getDao().findBySQL(
      sb.toString(),
      objects
    );

    if(results.size() > 0)
    {
      Number number = (Number)results.get(0);
      return number.intValue();
    }

    return 0;
  }

  public List getPbTestIdAndDescriptions(
    String priceBookId,
    String contractCode,
    String testId,
    String description,
    Pagination pagination
  )
  {
    if((priceBookId == null) || (contractCode == null)) return new ArrayList();

    int count = 0;
    StringBuffer sb = new StringBuffer();
    sb.append(SQL_1);
    if(testId != null)
    {
      sb.append(" AND t.TEST_ID LIKE ? ");
      count ++;
    }

    if(description != null)
    {
      sb.append(" AND t.ITS_TEST_DESCR LIKE ? ");
      count ++;
    }
    sb.append(SQL_2);

    if(pagination.getTotalRecord() > 0)
    {
      int totalRecord = getTotalRecordOfPbTestIdAndDescriptions(priceBookId, contractCode, testId, description);
      pagination.setTotalRecord(totalRecord);
    }

    Object[] objects = new Object[ 2 + count];
    objects[0] = priceBookId;
    objects[1] = contractCode;
    for(int i=0; i<count; i++)
    {
      if(i == 0) objects[2] = "%" + testId + "%";
      if(i == 1) objects[3] = "%" + description + "%";
    }

    return getDao().findBySQL(
      sb.toString(),
      objects,
      pagination
    );
  }

  public int getTotalRecordOfPriceBookTestIdsByPriceBookIdAndContractCode(
    String priceBookId,
    String contractCode
  )
  {
    if(contractCode == null) return 0;

    List results = getDao().findByNamedSqlQuery(
      "getTotalRecordOfPriceBookTestIdsByPriceBookIdAndContractCode",
      new Object[] {priceBookId, contractCode}
    );

    if(results.size() > 0)
    {
      Number number = (Number)results.get(0);
      return number.intValue();
    }

    return 0;
  }

  public List loadPriceBookTestIdsByPriceBookIdAndContractCode(
    String priceBookId,
    String contractCode,
    Pagination pagination
  )
  {
    if((priceBookId == null) || (contractCode == null)) return new ArrayList();

    if(pagination != null)
    {
      if(pagination.getTotalRecord() > 0)
      {
        int totalRecord = getTotalRecordOfPriceBookTestIdsByPriceBookIdAndContractCode(priceBookId, contractCode);
        pagination.setTotalRecord(totalRecord);
      }

      return getDao().findByNamedSqlQuery(
        "getPriceBookTestIdsByPriceBookIdAndContractCode",
        new Object[] {priceBookId, contractCode},
        pagination
      );
    }
    else
    {
      return getDao().findByNamedSqlQuery(
        "getPriceBookTestIdsByPriceBookIdAndContractCode",
        new Object[] {priceBookId, contractCode}
      );
    }
  }

  public List loadTestPricesByTestIds(
    String priceBookId,
    String contractCode,
    List testIds
  )
  {
    if((contractCode == null) || (priceBookId == null) || (testIds == null) || (testIds.size() == 0)) return new ArrayList();

    Object[] objects = new Object[testIds.size() + 2];
    StringBuffer sb = new StringBuffer();
    sb.append("from TestPrice tp where (tp.testPriceId.contractId = ? or tp.testPriceId.contractId = ?) and tp.testPriceId.testId in (");
    objects[0] = priceBookId;
    objects[1] = contractCode;
    for(int i=0; i<testIds.size(); i++)
    {
      if(i > 0) sb.append(",");
      sb.append("?");
      objects[i + 2] = testIds.get(i);
    }
    sb.append(") order by tp.testPriceId.testId, tp.testPriceId.contractId, tp.testPriceId.location, tp.testPriceId.beginDate desc");

    return getDao().find(sb.toString(), objects);
  }

  public List loadTestPricesByTestIds(
    String contractCode,
    List testIds
  )
  {
    if((contractCode == null) || (testIds == null) || (testIds.size() == 0)) return new ArrayList();

    Object[] objects = new Object[testIds.size() + 1];
    StringBuffer sb = new StringBuffer();
    sb.append("from TestPrice tp where (tp.testPriceId.contractId = ?) and tp.testPriceId.testId in (");
    objects[0] = contractCode;
    for(int i=0; i<testIds.size(); i++)
    {
      if(i > 0) sb.append(",");
      sb.append("?");
      objects[i + 1] = testIds.get(i);
    }
    sb.append(") order by tp.testPriceId.testId, tp.testPriceId.contractId, tp.testPriceId.location, tp.testPriceId.beginDate desc");

    return getDao().find(sb.toString(), objects);
  }

  public List loadTestsByTestIds(List testIds)
  {
    if((testIds == null) || (testIds.size() == 0)) return new ArrayList();

    Object[] objects = new Object[testIds.size()];
    StringBuffer sb = new StringBuffer();
    sb.append("from Test t where t.testId in (");
    for(int i=0; i<testIds.size(); i++)
    {
      if(i > 0) sb.append(",");
      sb.append("?");
      objects[i] = testIds.get(i);
    }
    sb.append(")");

    return getDao().find(sb.toString(), objects);
  }

  public int getTotalRecordOfContractTestIdsByPriceBookIdAndContractCode(String contractCode)
  {
    if(contractCode == null) return 0;

    List results = getDao().findByNamedSqlQuery(
      "getTotalRecordOfContractTestIdsByContractCode",
      new Object[] { contractCode}
    );

    if(results.size() > 0)
    {
      Number number = (Number)results.get(0);
      return number.intValue();
    }

    return 0;
  }


  public List loadContractTestIdsByPriceBookIdAndContractCode(
    String contractCode,
    Pagination pagination
  )
  {
    if(contractCode == null) return new ArrayList();

    if(pagination != null)
    {
      if(pagination.getTotalRecord() > 0)
      {
        int totalRecord = getTotalRecordOfContractTestIdsByPriceBookIdAndContractCode(contractCode);
        pagination.setTotalRecord(totalRecord);
      }

      return getDao().findByNamedSqlQuery(
        "getContractTestIdsByContractCode",
        new Object[] { contractCode},
        pagination
      );
    }
    else
    {
      return getDao().findByNamedSqlQuery(
        "getContractTestIdsByContractCode",
        new Object[] { contractCode}
      );
    }
  }

  public List loadContractTestsByTestIds(List testIds)
  {
    if((testIds == null) || (testIds.size() == 0)) return new ArrayList();

    Object[] objects = new Object[testIds.size()];
    StringBuffer sb = new StringBuffer();
    sb.append("from ContractTest ct where ct.contractTestId.testId in (");
    for(int i=0; i<testIds.size(); i++)
    {
      if(i > 0) sb.append(",");
      sb.append("?");
      objects[i] = testIds.get(i);
    }
    sb.append(")");

    List ctList = getDao().find(sb.toString(), objects);

    List tList = loadTestsByTestIds(testIds);
    Map testMap = new HashMap();

    for(int i=0; i<tList.size(); i++)
    {
      Test test = (Test)tList.get(i);
      testMap.put(test.getTestId(), test);
    }

    for(int i=0; i<ctList.size(); i++)
    {
      ContractTest ct = (ContractTest)ctList.get(i);
      Test test = (Test)testMap.get(ct.getContractTestId().getTestId());
      ct.setTest(test);
    }

    return ctList;
  }

  public void copySelectedTestPricesFromPb(CopyPbTestPrice copyPbTestPrice)
  {
    if(copyPbTestPrice == null) return;

    String contractCode = copyPbTestPrice.getContractCode();
    if(contractCode == null) return;

    String priceBookId = copyPbTestPrice.getPriceBookId();
    if(priceBookId == null) return;

    List selectedTests = copyPbTestPrice.getSelectedTests();
    if(selectedTests == null) return;

    for(int i=0; i<selectedTests.size(); i++)
    {
      SelectedTest selectedTest = (SelectedTest)selectedTests.get(i);

      if(selectedTest.isSelected())
      {
        Test test = selectedTest.getTest();
        if(test == null) continue;

        copySelectedTestPriceFromPb(test.getTestId(), priceBookId, contractCode);
      }
    }
  }

  public void copySelectedTestPriceFromPb(String testId, String priceBookId, String contractCode)
  {
    if((testId == null) || (priceBookId == null) || (contractCode == null)) return;

    List pbTestPrices = getPbTestPricesByTestIdAndPriceBookId(testId, priceBookId);
    for(int i=0; i<pbTestPrices.size(); i++)
    {
      TestPrice pbTestPrice = (TestPrice)pbTestPrices.get(i);

      TestPrice contractTestPrice = new TestPrice();
      EntityUtil.copyTestPrice(contractTestPrice, pbTestPrice);

      contractTestPrice.getTestPriceId().setContractId(contractCode);
      contractTestPrice.getTestPriceId().setTestType("C");
      contractTestPrice.setContractRef("CONT");

      getDao().save(contractTestPrice);
    }
  }

  public List getPbTestPricesByTestIdAndPriceBookId(String testId, String priceBookId)
  {
    if((testId == null) || (priceBookId == null)) return new ArrayList();

    return getDao().find(
      "from TestPrice tp where tp.testPriceId.testId = ? and tp.testPriceId.contractId = ?",
      new Object[] { testId, priceBookId }
    );
  }

  public void deleteTestPricesByContractCodeAndTestId(String contractCode, String testId)
  {
    getDao().bulkUpdate(
      "delete from TestPrice o where o.testPriceId.contractId = ? and o.testPriceId.testId = ?",
      new Object[] { contractCode, testId }
    );
  }

  public void saveTestPrices(List testPrices)
  {
    if(testPrices == null) return;

    for(int i=0; i<testPrices.size(); i++)
    {
      TestPrice testPrice = (TestPrice)testPrices.get(i);
      getDao().save(testPrice);
    }
  }

  public void saveTestPrices(TestPriceExt testPriceExt)
  {
    if(testPriceExt == null) return;

    String contractCode = testPriceExt.getContractCode();
    String testId = testPriceExt.getTestId();
    if((contractCode == null) || (testId == null)) return;

    deleteTestPricesByContractCodeAndTestId(contractCode, testId);

    saveTestPrices(testPriceExt.getTestPrices());

    Test test = testPriceExt.getTest();
    if(test != null)
    {
      getDao().save(test);
    }
  }

  public ContractTest getContractTestById(String contractCode, String testId, String testType)
  {
    if((contractCode == null) || (testId == null) || (testType == null)) return null;

    List tests = getDao().find(
      "from ContractTest o where o.contractTestId.contractId = ? and o.contractTestId.testId = ? and o.contractTestId.testType = ?",
      new Object[] { contractCode, testId, testType }
    );

    if(tests.size() > 0) return (ContractTest)tests.get(0);

    return null;
  }

  public void addContractTest(ContractTest contractTest)
  {
    if(contractTest == null) return;

    ContractTest existedContractTest = getContractTestById(
      contractTest.getContractTestId().getContractId(),
      contractTest.getContractTestId().getTestId(),
      contractTest.getContractTestId().getTestType()
    );

    if(existedContractTest != null)
    {
      throw new ServiceException(
        "contract_test_exists",
        new Object[] {
          contractTest.getContractTestId().getContractId(),
          contractTest.getContractTestId().getTestId(),
          contractTest.getContractTestId().getTestType()
        }
      );
    }

    Test test = contractTest.getTest();
    if(test != null)
    {
      Test existedTest = getTestById(test.getTestId());
      if(existedTest != null)
      {
        throw new ServiceException("test_exists", new Object[] { test.getTestId() });
      }

      getDao().save(test);
    }

    getDao().save(contractTest);
  }
}
