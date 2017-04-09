package com.intertek.util;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.intertek.calculator.CalculatorUtil;
import com.intertek.calculator.ControlExt;
import com.intertek.domain.AddJobContract;
import com.intertek.domain.AddJobContractProd;
import com.intertek.domain.AddJobContractProductServices;
import com.intertek.domain.AddJobContractVessel;
import com.intertek.domain.AddJobContractVesselServices;
import com.intertek.domain.AddJobOrder;
import com.intertek.domain.JobContractManualTestExt;
import com.intertek.domain.JobContractProductServiceExt;
import com.intertek.domain.JobContractSlateExt;
import com.intertek.domain.JobContractTestExt;
import com.intertek.domain.JobContractVesselServiceExt;
import com.intertek.domain.JobSearch;
import com.intertek.domain.SelectedSlate;
import com.intertek.entity.Bank;
import com.intertek.entity.BankAccount;
import com.intertek.entity.CfgContract;
import com.intertek.entity.ContractCustContact;
import com.intertek.entity.JobContract;
import com.intertek.entity.JobContractManualTest;
import com.intertek.entity.JobContractProd;
import com.intertek.entity.JobContractProdControl;
import com.intertek.entity.JobContractProductService;
import com.intertek.entity.JobContractSlate;
import com.intertek.entity.JobContractTest;
import com.intertek.entity.JobContractVessel;
import com.intertek.entity.JobContractVesselService;
import com.intertek.entity.JobManualTest;
import com.intertek.entity.JobOrder;
import com.intertek.entity.JobProd;
import com.intertek.entity.JobProdSample;
import com.intertek.entity.JobSlate;
import com.intertek.entity.JobTest;
import com.intertek.entity.JobVessel;
import com.intertek.entity.PriceBook;
import com.intertek.entity.ReferenceField;
import com.intertek.entity.Slate;
import com.intertek.entity.User;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.JobService;

public class JobUtil
{
  public static AddJobContract getAddJobContractByContractCode(AddJobOrder addJobOrder, String contractCode)
  {
    if((addJobOrder == null) || (contractCode == null)) return null;

    AddJobContract[] addJobContracts = addJobOrder.getAddJobContracts();
    if(addJobContracts != null)
    {
      for(int i=0; i<addJobContracts.length; i++)
      {
        if(contractCode.equals(addJobContracts[i].getJobContract().getContractCode()))
        {
          return addJobContracts[i];
        }
      }
    }

    return null;
  }

  public static AddJobContract getAddJobContractByContractIndex(AddJobOrder addJobOrder, String selectedIndex)
  {
    if((addJobOrder == null) || (selectedIndex == null)) return null;

    Integer tmp = null;
    try
    {
      tmp = new Integer(selectedIndex);
    }
    catch(Exception e)
    {
    }

    if(tmp == null) return null;
    int index = tmp.intValue();

    AddJobContract[] addJobContracts = addJobOrder.getAddJobContracts();
    if(addJobContracts != null)
    {
      if((index >= 0) && (index < addJobContracts.length)) return addJobContracts[index];
    }

    return null;
  }

  public static AddJobContract getAddJobContractByJobContractId(AddJobOrder addJobOrder, long jobContractId)
  {
    if((addJobOrder == null) || (jobContractId == 0)) return null;

    AddJobContract[] addJobContracts = addJobOrder.getAddJobContracts();
    if(addJobContracts != null)
    {
      for(int i=0; i<addJobContracts.length; i++)
      {
        if(jobContractId == addJobContracts[i].getJobContract().getId())
        {
          return addJobContracts[i];
        }
      }
    }

    return null;
  }

  public static AddJobContractVessel getAddJobContractVesselByIndex(AddJobContract addJobContract, String selectedIndex)
  {
    if((addJobContract == null) || (selectedIndex == null)) return null;
    Integer tmp = null;
    try
    {
      tmp = new Integer(selectedIndex);
    }
    catch(Exception e)
    {
    }

    if(tmp == null) return null;
    int index = tmp.intValue();

    AddJobContractVessel[] addJobContractVessels = addJobContract.getAddJobContractVessels();
    if(addJobContractVessels != null)
    {
      if((index >= 0) && (index < addJobContractVessels.length)) return addJobContractVessels[index];
    }

    return null;
  }

  public static void addAddJobContractVesselToAddJobContract(
    AddJobContract addJobContract,
    AddJobContractVessel addJobContractVessel
  )
  {
    if((addJobContract == null) || (addJobContractVessel == null)) return;

    AddJobContractVessel[] oldAddJobContractVessels = addJobContract.getAddJobContractVessels();
    int oldSize = oldAddJobContractVessels != null ? oldAddJobContractVessels.length : 0;
    AddJobContractVessel[] newAddJobContractVessels = new AddJobContractVessel[oldSize + 1];
    for(int i=0; i<oldSize; i++) newAddJobContractVessels[i] = oldAddJobContractVessels[i];
    newAddJobContractVessels[oldSize] = addJobContractVessel;

    addJobContract.setAddJobContractVessels(newAddJobContractVessels);
  }

  public static AddJobContractVessel removeAddJobContractVesselFromAddJobContractByIndex(
    AddJobContract addJobContract,
    String deletedIndexStr
  )
  {
    if((addJobContract == null) || (deletedIndexStr == null)) return null;

    Integer tmp = null;
    try
    {
      tmp = new Integer(deletedIndexStr);
    }
    catch(Exception e)
    {
    }

    if(tmp == null) return null;
    int index = tmp.intValue();

    AddJobContractVessel[] oldAddJobContractVessels = addJobContract.getAddJobContractVessels();
    int oldSize = oldAddJobContractVessels != null ? oldAddJobContractVessels.length : 0;
    if(oldSize <= 0) return null;

    AddJobContractVessel removed = null;

    if(index < oldSize)
    {
      List list = new ArrayList();
      for(int i=0; i<oldSize; i++)
      {
        if(i != index)
        {
          list.add(oldAddJobContractVessels[i]);
        }
        else
        {
          removed = oldAddJobContractVessels[i];
        }
      }

      AddJobContractVessel[] newAddJobContractVessels = (AddJobContractVessel[])list.toArray(new AddJobContractVessel[0]);
      addJobContract.setAddJobContractVessels(newAddJobContractVessels);
    }

    return removed;
  }

  public static AddJobContractProd getAddJobContractProdBySelectedProdIndex(
    AddJobContractVessel addJobContractVessel,
    String selectedJobContractProdIndex
  )
  {
    if((addJobContractVessel == null) || (selectedJobContractProdIndex == null)) return null;
    Integer tmp = null;
    try
    {
      tmp = new Integer(selectedJobContractProdIndex);
    }
    catch(Exception e)
    {
    }

    if(tmp == null) return null;
    int index = tmp.intValue();

    AddJobContractProd[] addJobContractProds = addJobContractVessel.getAddJobContractProds();
    if(addJobContractProds != null)
    {
      if((index >= 0) && (index < addJobContractProds.length)) return addJobContractProds[index];
    }

    return null;
  }

  public static void addAddJobContractProdToAddJobContractVessel(
    AddJobContractVessel addJobContractVessel,
    AddJobContractProd addJobContractProd
  )
  {
    if((addJobContractVessel == null) || (addJobContractProd == null)) return;

    AddJobContractProd[] oldAddJobContractProds = addJobContractVessel.getAddJobContractProds();
    int oldSize = oldAddJobContractProds != null ? oldAddJobContractProds.length : 0;
    AddJobContractProd[] newAddJobContractProds = new AddJobContractProd[oldSize + 1];
    for(int i=0; i<oldSize; i++) newAddJobContractProds[i] = oldAddJobContractProds[i];
    newAddJobContractProds[oldSize] = addJobContractProd;

    addJobContractVessel.setAddJobContractProds(newAddJobContractProds);
  }

  public static AddJobContractProd removeAddJobContractProdFromAddJobContractVesselByIndex(
    AddJobContractVessel addJobContractVessel,
    String deletedIndexStr
  )
  {
    if((addJobContractVessel == null) || (deletedIndexStr == null)) return null;

    Integer tmp = null;
    try
    {
      tmp = new Integer(deletedIndexStr);
    }
    catch(Exception e)
    {
    }

    if(tmp == null) return null;
    int index = tmp.intValue();

    AddJobContractProd[] oldAddJobContractProds = addJobContractVessel.getAddJobContractProds();
    int oldSize = oldAddJobContractProds != null ? oldAddJobContractProds.length : 0;
    if(oldSize <= 0) return null;

    AddJobContractProd removed = null;

    if(index < oldSize)
    {
      List list = new ArrayList();
      for(int i=0; i<oldSize; i++)
      {
        if(i != index)
        {
          list.add(oldAddJobContractProds[i]);
        }
        else
        {
          removed = oldAddJobContractProds[i];
        }
      }

      AddJobContractProd[] newAddJobContractProds = (AddJobContractProd[])list.toArray(new AddJobContractProd[0]);
      addJobContractVessel.setAddJobContractProds(newAddJobContractProds);
    }

    return removed;
  }

  public static SelectedSlate[] getSelectedSlates(List slateSearchResults)
  {
    int size = slateSearchResults != null ? slateSearchResults.size() : 0;
    SelectedSlate[] selectedSlates = new SelectedSlate[size];
    for(int i=0; i<size; i++)
    {
      selectedSlates[i] = new SelectedSlate();
      selectedSlates[i].setSlate((Slate)slateSearchResults.get(i));
    }

    return selectedSlates;
  }


  public static void populateControlsFromJobContractProdControls(
    ControlExt[] controlExts,
    List jobContractProdControls
  )
  {
    if((controlExts == null) || (jobContractProdControls == null)) return;

    for(int i=0; i<controlExts.length; i++)
    {
      ControlExt controlExt = controlExts[i];
      for(int j=0; j<jobContractProdControls.size(); j++)
      {
        JobContractProdControl jControl = (JobContractProdControl)jobContractProdControls.get(j);
        if(jControl.getObjectName().equals(
          controlExt.getControl().getControlId().getObjectName()
        ))
        {
          controlExt.setDataInput(jControl.getInputValue0());
        }
      }
    }
  }

  public static void removeJobContractTestByIndex(
    AddJobContractProd addJobContractProd,
    Integer deletedIndex
  )
  {
    if((addJobContractProd == null) || (deletedIndex == null)) return;

    if(deletedIndex < 0) return;

    List testExts = addJobContractProd.getJobContractTestExts();
    if(testExts != null)
    {
      if(deletedIndex < testExts.size())
      {
        JobContractTestExt testExt = (JobContractTestExt)testExts.get(deletedIndex);

        if(testExt != null)
        {
          testExts.remove(testExt);
          addJobContractProd.getJobContractProd().getJobContractTests().remove(testExt.getTest());
        }
      }
    }
  }

  public static JobContractTest getJobContractTestByIndex(
    AddJobContractProd addJobContractProd,
    Integer index
  )
  {
    if((addJobContractProd == null) || (index == null)) return null;

    if(index < 0) return null;

    List jobContractTestExts = addJobContractProd.getJobContractTestExts();
    for(int i=0; i<jobContractTestExts.size(); i++)
    {
      JobContractTestExt testExt = (JobContractTestExt)jobContractTestExts.get(i);
      if(index == i) return testExt.getTest();
    }

    return null;
  }

  public static void removeJobContractSlateByIndex(
    AddJobContractProd addJobContractProd,
    Integer deletedIndex
  )
  {
    if((addJobContractProd == null) || (deletedIndex == null)) return;

    if(deletedIndex < 0) return;

    List slateExts = addJobContractProd.getJobContractSlateExts();
    if(slateExts != null)
    {
      if(deletedIndex < slateExts.size())
      {
        JobContractSlateExt slateExt = (JobContractSlateExt)slateExts.get(deletedIndex);

        if(slateExt != null)
        {
          slateExts.remove(slateExt);
          addJobContractProd.getJobContractProd().getJobContractSlates().remove(slateExt.getSlate());
        }
      }
    }
  }

  public static JobContractSlate getJobContractSlateByIndex(
    AddJobContractProd addJobContractProd,
    Integer index
  )
  {
    if((addJobContractProd == null) || (index == null)) return null;

    if(index < 0) return null;

    List slateExts = addJobContractProd.getJobContractSlateExts();
    for(int i=0; i<slateExts.size(); i++)
    {
      JobContractSlateExt slateExt = (JobContractSlateExt)slateExts.get(i);
      if(index == i) return slateExt.getSlate();
    }

    return null;
  }

  public static void removeJobContractManualTestByIndex(
    AddJobContractProd addJobContractProd,
    Integer deletedIndex
  )
  {
    if((addJobContractProd == null) || (deletedIndex == null)) return;

    if(deletedIndex < 0) return;

    List manualTestExts = addJobContractProd.getJobContractManualTestExts();
    if(manualTestExts != null)
    {
      if(deletedIndex < manualTestExts.size())
      {
        JobContractManualTestExt manualTestExt = (JobContractManualTestExt)manualTestExts.get(deletedIndex);

        if(manualTestExt != null)
        {
          manualTestExts.remove(manualTestExt);
          addJobContractProd.getJobContractProd().getJobContractManualTests().remove(manualTestExt.getManualTest());
        }
      }
    }
  }

  public static JobContractManualTestExt getJobContractManualTestExtByIndex(
    AddJobContractProd addJobContractProd,
    Integer index
  )
  {
    if((addJobContractProd == null) || (index == null)) return null;

    if(index < 0) return null;

    List testExts = addJobContractProd.getJobContractManualTestExts();
    for(int i=0; i<testExts.size(); i++)
    {
      JobContractManualTestExt testExt = (JobContractManualTestExt)testExts.get(i);
      if(index == i) return testExt;
    }

    return null;
  }

  public static void copyJobVesselsToJobContractFromJobOrder(
    JobOrder jobOrder,
    JobContract jobContract
  )
  {
    if((jobOrder == null) || (jobContract == null)) return;

    int count = 0;
    Iterator it = jobOrder.getJobVessels().iterator();
    while(it.hasNext())
    {
      JobVessel jobVessel = (JobVessel)it.next();

      copyJobVesselToJobContract(jobVessel, jobContract, count);
      count ++;
    }
  }

  public static void copyJobVesselsToJobContract(
    List jobVessels,
    JobContract jobContract
  )
  {
    if((jobVessels == null) || (jobContract == null)) return;

    for(int i=0; i<jobVessels.size(); i++)
    {
      JobVessel jobVessel = (JobVessel)jobVessels.get(i);

      copyJobVesselToJobContract(jobVessel, jobContract, i);
    }
  }

  public static void copyJobVesselToJobContract(
    JobVessel jobVessel,
    JobContract jobContract,
    int seqNum
  )
  {
    if((jobVessel == null) || (jobContract == null)) return;

    JobContractVessel jobContractVessel = createJobContractVesselFromJobVessel(jobVessel, jobContract);
    jobContractVessel.setSortOrderNum(seqNum);

    jobContractVessel.setJobContract(jobContract);
    jobContract.getJobContractVessels().add(jobContractVessel);

    // calculate the price for the copied tests/slates/manualTests
    Iterator it0 = jobContractVessel.getJobContractProds().iterator();
    while(it0.hasNext())
    {
      JobContractProd jobContractProd = (JobContractProd)it0.next();

      Iterator it1 = jobContractProd.getJobContractTests().iterator();
      while(it1.hasNext())
      {
        JobContractTest jobContractTest = (JobContractTest)it1.next();
        CalculatorUtil.calculateTest(jobContractTest);
      }

      it1 = jobContractProd.getJobContractSlates().iterator();
      while(it1.hasNext())
      {
        JobContractSlate jobContractSlate = (JobContractSlate)it1.next();
        CalculatorUtil.calculateSlate(jobContractSlate);
      }

      it1 = jobContractProd.getJobContractManualTests().iterator();
      while(it1.hasNext())
      {
        JobContractManualTest jobContractManualTest = (JobContractManualTest)it1.next();
        CalculatorUtil.calculateManualTest(jobContractManualTest);
      }
    }
  }

  public static JobContractVessel createJobContractVesselFromJobVessel(JobVessel jobVessel, JobContract jobContract)
  {
    JobContractVessel jobContractVessel = new JobContractVessel();

    jobContractVessel.setJobNumber(jobVessel.getJobVesselId().getJobNumber());
    jobContractVessel.setLinkedVslRow(jobVessel.getJobVesselId().getLinkedVslRow());

    jobContractVessel.setVesselName(jobVessel.getVesselName());
    if(jobVessel.getType() == null)
    {
      jobContractVessel.setType("*");
    }
    else
    {
      jobContractVessel.setType(jobVessel.getType());
    }

    if(Constants.ANALYTICAL_SERVICE_JOBTYPE.equals(jobVessel.getJobOrder().getJobType())
      || Constants.OUTSOURCING_JOBTYPE.equals(jobVessel.getJobOrder().getJobType())
    )
    {
      jobContractVessel.setType("Other");
    }

    Set jobProdContracts = jobContract.getJobProdContracts();

    if(jobProdContracts.size() > 0)
    {
      Iterator it = jobVessel.getJobProds().iterator();
      int count = 0;
      while(it.hasNext())
      {
        JobProd jobProd = (JobProd)it.next();
        if(!jobProdContracts.contains(jobProd)) continue;

        List jobContractProds = createJobContractProdsFromJobProd(jobProd);

        for(int i=0; i<jobContractProds.size(); i++)
        {
          JobContractProd jobContractProd = (JobContractProd)jobContractProds.get(i);
          jobContractProd.setJobContractVessel(jobContractVessel);
          jobContractProd.setSortOrderNum(count);
          jobContractVessel.getJobContractProds().add(jobContractProd);

          // need to change
          jobContractProd.setProductGroupMaster(jobProd.getGroup());

          count ++;
        }
      }
    }

    return jobContractVessel;
  }

  public static List createJobContractProdsFromJobProd(JobProd jobProd)
  {
    List results = new ArrayList();

    if(!Constants.ANALYTICAL_SERVICE_JOBTYPE.equals(jobProd.getJobVessel().getJobOrder().getJobType()))
    {
      JobContractProd jobContractProd = new JobContractProd();
      copyJobContractProdFromJobProd(jobContractProd, jobProd);
      results.add(jobContractProd);
    }

    Iterator it = jobProd.getJobProdSamples().iterator();
    while(it.hasNext())
    {
      JobProdSample jobProdSample = (JobProdSample)it.next();

      JobContractProd jobContractProd = createJobContractProdFromJobProdSample(jobProdSample);

      if(Constants.ANALYTICAL_SERVICE_JOBTYPE.equals(jobProd.getJobVessel().getJobOrder().getJobType()))
      {
        jobContractProd.setNotChargeInd(true);
      }

      results.add(jobContractProd);
    }

    return results;
  }

  public static void copyJobContractProdFromJobProd(JobContractProd jobContractProd, JobProd jobProd)
  {
    jobContractProd.setProdSeqNum(jobProd.getJobProdId().getProdSeqNum());
    jobContractProd.setJobNumber(jobProd.getJobProdId().getJobNumber());
    jobContractProd.setLinkedVslRow(jobProd.getJobProdId().getLinkedVslRow());

    jobContractProd.setJobProductName(jobProd.getJobProductName());
    jobContractProd.setDestination(jobProd.getDestination());
    jobContractProd.setGroup(jobProd.getGroup());
    jobContractProd.setOption(jobProd.getOption());
    jobContractProd.setPlusMinus(jobProd.getPlusMinus());
    jobContractProd.setDrafts(jobProd.getDrafts());
    jobContractProd.setTanks(jobProd.getTanks());
    jobContractProd.setPlusMinusPct(jobProd.getPlusMinusPct());
    jobContractProd.setProductQty(jobProd.getProductQty());
    jobContractProd.setUom(jobProd.getUom());
  }

  public static JobContractProd createJobContractProdFromJobProdSample(JobProdSample jobProdSample)
  {
    JobContractProd jobContractProd = new JobContractProd();
    copyJobContractProdFromJobProd(jobContractProd, jobProdSample.getJobProd());
    jobContractProd.setSampSeqId(jobProdSample.getJobProdSampleId().getSampSeqId());

    String sampleLocation = jobProdSample.getJobSampleLocation();
    if(sampleLocation != null)
    {
      List list = new ArrayList();
      list.add("sampleLocation");
      sampleLocation = CommonUtil.getDropdownLabel("staticDropdown", list, sampleLocation);
    }

    String sampleTiming = jobProdSample.getSampleTiming();
    if(sampleTiming != null)
    {
      List list = new ArrayList();
      list.add("sampleTiming");
      sampleTiming = CommonUtil.getDropdownLabel("staticDropdown", list, sampleTiming);
    }

    jobContractProd.setJobProductName(
      jobProdSample.getJobProd().getJobProductName()
      + " - " + sampleLocation
      + " - " + (jobProdSample.getTankNumber() != null ? jobProdSample.getTankNumber() : "")
      + " - " + (sampleTiming != null ? sampleTiming : "")
    );

    List list = new ArrayList(jobProdSample.getJobTests());
    JobUtil.sortJobTestsById(list);
    for(int i=0; i<list.size(); i++)
    {
      JobTest jobTest = (JobTest)list.get(i);
      JobContractTest jobContractTest = copyJobContractTestFromJobTest(null, jobTest);
      jobContractProd.getJobContractTests().add(jobContractTest);
      jobContractTest.setJobContractProd(jobContractProd);
      jobContractTest.setSortOrderNum(i);
    }

    list = new ArrayList(jobProdSample.getJobManualTests());
    JobUtil.sortJobManualTestsById(list);
    for(int i=0; i<list.size(); i++)
    {
      JobManualTest jobManualTest = (JobManualTest)list.get(i);
      JobContractManualTest jobContractManualTest = copyJobContractManualTestFromJobManualTest(null, jobManualTest);
      jobContractProd.getJobContractManualTests().add(jobContractManualTest);
      jobContractManualTest.setJobContractProd(jobContractProd);
      jobContractManualTest.setSortOrderNum(i);
    }

    list = new ArrayList(jobProdSample.getJobSlates());
    JobUtil.sortJobSlatesById(list);
    for(int i=0; i<list.size(); i++)
    {
      JobSlate jobSlate = (JobSlate)list.get(i);
      JobContractSlate jobContractSlate = copyJobContractSlateFromJobSlate(null, jobSlate);
      jobContractProd.getJobContractSlates().add(jobContractSlate);
      jobContractSlate.setJobContractProd(jobContractProd);
      jobContractSlate.setSortOrderNum(i);
    }

    return jobContractProd;
  }

  public static JobContractTest copyJobContractTestFromJobTest(
    JobContractTest jobContractTest,
    JobTest jobTest
  )
  {
    if(jobContractTest == null) jobContractTest = new JobContractTest();

    jobContractTest.setJobTestId(jobTest.getId());
    jobContractTest.setTest(jobTest.getTest());
    jobContractTest.setQuantity(jobTest.getQuantity());
    jobContractTest.setOt(jobTest.getOt());

    return jobContractTest;
  }

  public static JobContractManualTest copyJobContractManualTestFromJobManualTest(
    JobContractManualTest jobContractManualTest,
    JobManualTest jobManualTest
  )
  {
    if(jobContractManualTest == null) jobContractManualTest = new JobContractManualTest();

    jobContractManualTest.setJobManualTestId(jobManualTest.getId());

    jobContractManualTest.setQuantity(jobManualTest.getQuantity());
    jobContractManualTest.setOt(jobManualTest.getOt());

    jobContractManualTest.setTestId(jobManualTest.getTestId());
    jobContractManualTest.setTestDescription(jobManualTest.getTestDescription());
    jobContractManualTest.setUnitPrice(jobManualTest.getUnitPrice());
    jobContractManualTest.setLineDescription(jobManualTest.getLineDescription());
    jobContractManualTest.setContractRefNo(jobManualTest.getContractRefNo());
    jobContractManualTest.setDiscount(jobManualTest.getDiscount());
    jobContractManualTest.setTotalPrice(jobManualTest.getTotalPrice());

    return jobContractManualTest;
  }

  public static JobContractSlate copyJobContractSlateFromJobSlate(
    JobContractSlate jobContractSlate,
    JobSlate jobSlate
  )
  {
    if(jobContractSlate == null) jobContractSlate = new JobContractSlate();

    jobContractSlate.setJobSlateId(jobSlate.getId());

    jobContractSlate.setSlate(jobSlate.getSlate());
    jobContractSlate.setQuantity(jobSlate.getQuantity());
    jobContractSlate.setOt(jobSlate.getOt());

    return jobContractSlate;
  }

  public static AddJobContractVessel createAddJobContractVessel(JobContractVessel jobContractVessel)
  {
    AddJobContractVessel addJobContractVessel = new AddJobContractVessel();
    addJobContractVessel.setJobContractVessel(jobContractVessel);

    AddJobContractVesselServices addJobContractVesselServices = new AddJobContractVesselServices();

    Iterator it0 = jobContractVessel.getJobContractVesselServices().iterator();
    while(it0.hasNext())
    {
      JobContractVesselService jobContractVesselService = (JobContractVesselService)it0.next();
      JobContractVesselServiceExt jobContractVesselServiceExt = new JobContractVesselServiceExt();
      jobContractVesselServiceExt.setService(jobContractVesselService);
      jobContractVesselServiceExt.getResults().addAll(jobContractVesselService.getResults());
      jobContractVesselServiceExt.setSelects(
        CommonUtil.createBooleans(jobContractVesselServiceExt.getResults().size())
      );

      addJobContractVesselServices.getAddedJobContractVesselServices().add(jobContractVesselServiceExt);
    }

    addJobContractVessel.setAddJobContractVesselServices(addJobContractVesselServices);

    Iterator it = jobContractVessel.getJobContractProds().iterator();
    AddJobContractProd[] addJobContractProds = new AddJobContractProd[jobContractVessel.getJobContractProds().size()];
    int count = 0;
    while(it.hasNext())
    {
      JobContractProd jobContractProd = (JobContractProd)it.next();
      AddJobContractProd addJobContractProd = JobUtil.createAddJobContractProd(jobContractProd);

      addJobContractProds[count] = addJobContractProd;

      count ++;
    }
    addJobContractVessel.setAddJobContractProds(addJobContractProds);

    addJobContractVessel.getResults().addAll(jobContractVessel.getResults());
    addJobContractVessel.setSelects(
      CommonUtil.createBooleans(addJobContractVessel.getResults().size())
    );

    return addJobContractVessel;
  }

  public static AddJobContractProd createAddJobContractProd(JobContractProd jobContractProd)
  {
    AddJobContractProd addJobContractProd = new AddJobContractProd();
    addJobContractProd.setJobContractProd(jobContractProd);

    AddJobContractProductServices addJobContractProductServices = new AddJobContractProductServices();

    List addedJobContractProductServices = new ArrayList(jobContractProd.getJobContractProductServices());

    Iterator it0 = jobContractProd.getJobContractProductServices().iterator();
    while(it0.hasNext())
    {
      JobContractProductService jobContractProductService = (JobContractProductService)it0.next();
      JobContractProductServiceExt jobContractProductServiceExt = new JobContractProductServiceExt();
      jobContractProductServiceExt.setService(jobContractProductService);
      jobContractProductServiceExt.getResults().addAll(jobContractProductService.getResults());
      jobContractProductServiceExt.setSelects(
        CommonUtil.createBooleans(jobContractProductServiceExt.getResults().size())
      );

      addJobContractProductServices.getAddedJobContractProductServices().add(jobContractProductServiceExt);
    }

    addJobContractProd.setAddJobContractProductServices(addJobContractProductServices);

    addJobContractProd.getResults().addAll(jobContractProd.getResults());
    addJobContractProd.setSelects(
      CommonUtil.createBooleans(addJobContractProd.getResults().size())
    );

    List testExts = new ArrayList();
    Iterator it = jobContractProd.getJobContractTests().iterator();
    while(it.hasNext())
    {
      JobContractTestExt testExt = new JobContractTestExt();
      testExt.setTest((JobContractTest)it.next());
      testExts.add(testExt);
    }
    addJobContractProd.setJobContractTestExts(testExts);

    List manualTestExts = new ArrayList();
    it = jobContractProd.getJobContractManualTests().iterator();
    while(it.hasNext())
    {
      JobContractManualTestExt manualTestExt = new JobContractManualTestExt();
      manualTestExt.setManualTest((JobContractManualTest)it.next());
      manualTestExts.add(manualTestExt);
    }
    addJobContractProd.setJobContractManualTestExts(manualTestExts);

    List slateExts = new ArrayList();
    it = jobContractProd.getJobContractSlates().iterator();
    while(it.hasNext())
    {
      JobContractSlateExt slateExt = new JobContractSlateExt();
      slateExt.setSlate((JobContractSlate)it.next());
      slateExts.add(slateExt);
    }
    addJobContractProd.setJobContractSlateExts(slateExts);

    return addJobContractProd;
  }

  public static boolean CheckZoneLocationDescription(
    String zone,
    String locationDesc,
    String contractCode,
    Date jobDate,String priceBookId
  )
  {
    if((zone == null) || (locationDesc == null) || (contractCode == null)) return false;

    if(Constants.None.equalsIgnoreCase(zone))
    {
      if(Constants.None.equalsIgnoreCase(locationDesc))
      {
        JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
        if(jobDate == null) jobDate = new Date();

        String dt = DateUtil.formatDate(jobDate,"dd-MMM-yyyy");

        List portCodes=jobService.getPortCodeByContractCode(contractCode,priceBookId, dt);
        if(portCodes.size() > 0) return false;
      }
    }

    return true;
  }

  public static JobContract populateDefaultJobContractFields(JobContract jobContract,ContractCustContact contractCustContact,JobOrder jobOrder,User user)
  {
    String zonedes="";
    String pricebookId="";
    CfgContract cfgContract=null;

    Date asOfDate=new Date();
    if(jobOrder.getJobFinishDate()!=null )
    {
      asOfDate=jobOrder.getJobFinishDate();
    }
    else if(jobOrder.getJobFinishDate()==null && jobOrder.getEtaDate()!=null )
    {
      asOfDate=jobOrder.getEtaDate();
    }
    else if(jobOrder.getJobFinishDate()==null &&  jobOrder.getEtaDate()==null )
    {
      asOfDate=new Date();
    }

    JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
    if(contractCustContact != null && contractCustContact.getContractCust() != null && contractCustContact.getContractCust().getContract() != null && contractCustContact.getContractCust().getContract().getContractCode() != null)
      cfgContract =jobService.getPriceBookByContractCode(contractCustContact.getContractCust().getContract().getContractCode(),asOfDate);
    if(cfgContract!=null)
    {
        if(!cfgContract.getPriceBookId().equalsIgnoreCase("CURRENT"))
      {
        pricebookId=cfgContract.getPriceBookId();
        }
        else
      {
      PriceBook priceBook=jobService.getPriceBookIdByCurrencyandSeries(cfgContract.getCurrencyCD(),cfgContract.getPbSeries(),asOfDate);
          if(priceBook!=null)
      {
       pricebookId=priceBook.getPriceBookId().getPriceBookId();
      }
      }

        if(cfgContract.getCurrencyCD() != null)
        {
          if(jobContract.getTransactionCurrencyCd() == null || jobContract.getTransactionCurrencyCd().trim().equals(""))
            jobContract.setTransactionCurrencyCd(cfgContract.getCurrencyCD());
        }
    }

    //Set default Zone Description in JobContract if null
    System.out.println("jobContract zone:"+jobContract.getZone());
    if(jobContract.getZone() == null || jobContract.getZone().trim().equals(""))
    {
     if(contractCustContact != null && contractCustContact.getContractCust() != null && contractCustContact.getContractCust().getContract() != null)
       zonedes= jobService.getZoneByBranchCodeandContractCode(jobOrder.getBranchName(),contractCustContact.getContractCust().getContract().getContractCode(),pricebookId,asOfDate);
      if(zonedes!=null&&!zonedes.trim().equals(""))
      {
        jobContract.setZone(zonedes);
      }
      else
      {
        jobContract.setZone("NONE");
      }
    }
    List referenceFields = new ArrayList();
    if(contractCustContact != null && contractCustContact.getContractCust() != null && contractCustContact.getContractCust().getContract() != null)
      referenceFields=jobService.getReferenceFieldsByContractCode(contractCustContact.getContractCust().getContract().getContractCode());
      if(referenceFields.size()==0)
      {
        if(pricebookId!=null&& !pricebookId.trim().equals(""))
        {
        referenceFields=jobService.getReferenceFieldsByContractCode(pricebookId);
        }
      }


    //sheida cfgContract=jobService.getCfgContractByContractCode(contractCustContact.getContractCust().getContract().getContractCode());
    /*newly added*/
    if(jobOrder!=null && cfgContract!=null)
    {

      List banks=jobService.getBankCodebyBunameandCurrency(jobOrder.getBuName(),cfgContract.getCurrencyCD());
      if(banks!=null && banks.size()==1)
      {
      Bank bank=(Bank)banks.get(0);

      if(jobContract.getBankCd() == null || jobContract.getBankCd().trim().equals(""))
      {
        jobContract.setBankCd(bank.getBankCode());
      }
      }

      List bankAccounts=jobService.getBankAccountByBankCodeAndCurrency(jobOrder.getBuName(),cfgContract.getCurrencyCD(),jobContract.getBankCd());
      if(bankAccounts!=null && bankAccounts.size()==1)
      {
        BankAccount bankAccount=(BankAccount)bankAccounts.get(0);
        if(jobContract.getBankAcctKey() == null || jobContract.getBankAcctKey().trim().equals(""))
        {
          jobContract.setBankAcctKey(bankAccount.getBankAccountId().getBankAcctCode());
        }
      }
    }


    for(int k=0;k<referenceFields.size();k++)
    {
      ReferenceField  referenceField=(ReferenceField)referenceFields.get(k);

      if(referenceField.getSortOrderNum()==1&& referenceField.getSortOrderNum()!=null)
      {
        if(jobContract.getInvoiceLabel1() == null || jobContract.getInvoiceLabel1().trim().equals(""))
          jobContract.setInvoiceLabel1(referenceField.getReferenceFieldId().getReferenceFieldId());
      }
      if(referenceField.getSortOrderNum()==2 && referenceField.getSortOrderNum()!=null)
      {
        if(jobContract.getInvoiceLabel2() == null || jobContract.getInvoiceLabel2().trim().equals(""))
          jobContract.setInvoiceLabel2(referenceField.getReferenceFieldId().getReferenceFieldId());
      }
      if(referenceField.getSortOrderNum()==3 && referenceField.getSortOrderNum()!=null)
      {
        if(jobContract.getInvoiceLabel3() == null || jobContract.getInvoiceLabel3().trim().equals(""))
          jobContract.setInvoiceLabel3(referenceField.getReferenceFieldId().getReferenceFieldId());
      }
      if(referenceField.getSortOrderNum()==4 && referenceField.getSortOrderNum()!=null)
      {
        if(jobContract.getInvoiceLabel4() == null || jobContract.getInvoiceLabel4().trim().equals(""))
          jobContract.setInvoiceLabel4(referenceField.getReferenceFieldId().getReferenceFieldId());
      }
      if(referenceField.getSortOrderNum()==5 && referenceField.getSortOrderNum()!=null)
      {
        if(jobContract.getInvoiceLabel5() == null || jobContract.getInvoiceLabel5().trim().equals(""))
          jobContract.setInvoiceLabel5(referenceField.getReferenceFieldId().getReferenceFieldId());
      }

    }// end for(k)'

    if(jobContract.getProductType() == null || jobContract.getProductType().trim().equals(""))
    {
      if(user.getProductType()!=null && !user.getProductType().trim().equals(""))
      {
        jobContract.setProductType(user.getProductType());
      }
      else
      {
        jobContract.setProductType("Hydro");
      }
    }
    return jobContract;
  }

  public static void sortJobTestsById(List jobTests)
  {
    if(jobTests == null) return;

    Collections.sort(
      jobTests,
      new Comparator()
      {
        public int compare(Object o1, Object o2)
        {
          JobTest p1 = (JobTest)o1;
          JobTest p2 = (JobTest)o2;

          int result = 0;

          Long v1 = p1 != null ? p1.getId() : null;
          Long v2 = p2 != null ? p2.getId() : null;

          if(v1 == null)
          {
            if(v2 != null) result = -1;
          }
          else
          {
            if(v2 == null) result = 1;
            else result = v1.compareTo(v2);
          }

          return result;
        }
      }
    );
  }

  public static void sortJobManualTestsById(List jobManualTests)
  {
    if(jobManualTests == null) return;

    Collections.sort(
      jobManualTests,
      new Comparator()
      {
        public int compare(Object o1, Object o2)
        {
          JobManualTest p1 = (JobManualTest)o1;
          JobManualTest p2 = (JobManualTest)o2;

          int result = 0;

          Long v1 = p1 != null ? p1.getId() : null;
          Long v2 = p2 != null ? p2.getId() : null;

          if(v1 == null)
          {
            if(v2 != null) result = -1;
          }
          else
          {
            if(v2 == null) result = 1;
            else result = v1.compareTo(v2);
          }

          return result;
        }
      }
    );
  }

  public static void sortJobSlatesById(List jobSlates)
  {
    if(jobSlates == null) return;

    Collections.sort(
      jobSlates,
      new Comparator()
      {
        public int compare(Object o1, Object o2)
        {
          JobSlate p1 = (JobSlate)o1;
          JobSlate p2 = (JobSlate)o2;

          int result = 0;

          Long v1 = p1 != null ? p1.getId() : null;
          Long v2 = p2 != null ? p2.getId() : null;

          if(v1 == null)
          {
            if(v2 != null) result = -1;
          }
          else
          {
            if(v2 == null) result = 1;
            else result = v1.compareTo(v2);
          }

          return result;
        }
      }
    );
  }

  public static void sortJobContractsById(List jobContracts)
  {
    if(jobContracts == null) return;

    Collections.sort(
      jobContracts,
      new Comparator()
      {
        public int compare(Object o1, Object o2)
        {
          JobContract p1 = (JobContract)o1;
          JobContract p2 = (JobContract)o2;

          int result = 0;

          Long v1 = p1 != null ? p1.getId() : null;
          Long v2 = p2 != null ? p2.getId() : null;

          if(v1 == null)
          {
            if(v2 != null) result = -1;
          }
          else
          {
            if(v2 == null) result = 1;
            else result = v1.compareTo(v2);
          }

          return result;
        }
      }
    );
  }
  /**
   * This method is like truth table for performing ETA restiction
   * validation. If the return value of this method is FALSE then
   * no validation to be done if it is TRUE, then validation to be done.
   * @param pAddJobOrder as com.intertek.domain.AddJobOrder
   * @return boolean
   */
  public static boolean restrictETA(AddJobOrder pAddJobOrder){
	  boolean blnRtn = false;
		System.out.println("restrictETA / JobFinishDate::"+pAddJobOrder.getJobOrder().getJobFinishDate());
		System.out.println("restrictETA / ETA Date::"+pAddJobOrder.getJobOrder().getEtaDate());
		System.out.println("restrictETA / ETA Time::"+pAddJobOrder.getJobOrder().getEtaTime());
	  if(null == pAddJobOrder.getJobOrder().getJobFinishDate()){
		  if(null != pAddJobOrder.getJobOrder().getEtaDate()){
			  if(null != pAddJobOrder.getJobOrder().getEtaTime()){
				  blnRtn = false;  
			  }
			  else{
				  blnRtn = true;
			  }
		  }
		  else if(null != pAddJobOrder.getJobOrder().getEtaDate()){
			  blnRtn = false;
		  }
	  }
	  else if(null != pAddJobOrder.getJobOrder().getJobFinishDate()){
		  blnRtn = false;
	  }
	  System.out.println("restrictETA / blnRtn::"+blnRtn);
	  return blnRtn;
  }
 
  // START : #119240
  public static AddJobOrder setPrevNextJobFlags(AddJobOrder addJobOrder, JobSearch jobSearch, JobOrder jobOrder, String orginFromJobSearch){
	 	
	//	if (jobSearch != null) {
		if(jobSearch != null && orginFromJobSearch != null && !orginFromJobSearch.trim().equals("")){
			List jobSearchResults = jobSearch.getJobNumbers();
			String jobNum = "";
			if (jobSearchResults != null && jobSearchResults.size() > 0) {
				jobNum = (String) jobSearchResults.get((jobSearchResults
						.size() - 1));
				if (jobOrder != null
						&& jobOrder.getJobNumber() != null
						&& jobOrder.getJobNumber().trim().equals(
								jobNum.trim())) {
					addJobOrder.setNextListFlag("false");
				} else
					addJobOrder.setNextListFlag("true");
				jobNum = (String) jobSearchResults.get(0);
				if (jobOrder != null
						&& jobOrder.getJobNumber() != null
						&& jobOrder.getJobNumber().trim().equals(
								jobNum.trim())) {
					addJobOrder.setPrevListFlag("false");
				} else
					addJobOrder.setPrevListFlag("true");
			}
		} else {
			addJobOrder.setNextListFlag("false");
			addJobOrder.setPrevListFlag("false");
		}
		
		if(orginFromJobSearch != null && orginFromJobSearch.equals("jobLog"))
			addJobOrder.setOriginateFromSearchJob(Constants.JOB_LOG);
		if(orginFromJobSearch != null && orginFromJobSearch.equals("jobSearch"))
			addJobOrder.setOriginateFromSearchJob(Constants.JOB_SEARCH);
		if(orginFromJobSearch != null && orginFromJobSearch.trim().equals(Constants.JOB_LOG))
			addJobOrder.setOriginateFromSearchJob(orginFromJobSearch);
		if(orginFromJobSearch != null && orginFromJobSearch.trim().equals(Constants.JOB_SEARCH))
			addJobOrder.setOriginateFromSearchJob(orginFromJobSearch);	
		
		return addJobOrder;
	  
  }
  
  
  
  public static String getUrl(String key, String jobType, String jobStatus){
	  HashMap<String, String> urlMapper = new HashMap<String, String>();
	  String url = "";
	  
	  loadUrlMap(jobType, jobStatus, urlMapper);
	  if(urlMapper != null && urlMapper.size() > 0){
		 url = (String)urlMapper.get(key);
	  }
	  return url; 
  }
  
  private static void loadUrlMap(String jobType, String jobStatus, HashMap<String, String> urlMapper){
	  
	  if(jobStatus.equals(Constants.OPEN_STATUS) || jobStatus.equals(Constants.REOPENED_STATUS))
	  {
		  //urlMapper = new HashMap<String, String>(); 
	 	  if(jobType.equalsIgnoreCase(Constants.INSPECTION_JOBTYPE)){
	 		 urlMapper.put(Constants.JOB_ENTRY_FORM,Constants.INSP);  
	 	  }else if(jobType.equalsIgnoreCase(Constants.MARINE_JOBTYPE)){
	 		 urlMapper.put(Constants.JOB_ENTRY_FORM,Constants.MAR);  
	 	  }else if(jobType.equalsIgnoreCase(Constants.ANALYTICAL_SERVICE_JOBTYPE)){
	 		 urlMapper.put(Constants.JOB_ENTRY_FORM,Constants.FST);  
	 	  }else if(jobType.equalsIgnoreCase(Constants.OUTSOURCING_JOBTYPE)){
	 		 urlMapper.put(Constants.JOB_ENTRY_FORM,Constants.OUT);  
	 	  }
	 	  urlMapper.put(Constants.JOB_INSTRUCTIONS, Constants.JOB_INSTR);
	 	  urlMapper.put(Constants.JOB_SELECT_CHARGES, Constants.SELECT_CHARGES);
	 	  urlMapper.put(Constants.JOB_PREVIEW_INVOICE, Constants.INV_PREVIEW);
	 	  urlMapper.put(Constants.JOB_INVOICE, Constants.VIEW_INV);
	 	  
	  }else 
	  {
		  //urlMapper = new HashMap<String, String>(); 
     	  if(jobType.equalsIgnoreCase(Constants.INSPECTION_JOBTYPE)){
     		 urlMapper.put(Constants.JOB_ENTRY_FORM,Constants.VIEW_INSP);  
     	  }else if(jobType.equalsIgnoreCase(Constants.MARINE_JOBTYPE)){
     		 urlMapper.put(Constants.JOB_ENTRY_FORM,Constants.VIEW_MAR);  
     	  }else if(jobType.equalsIgnoreCase(Constants.ANALYTICAL_SERVICE_JOBTYPE)){
     		 urlMapper.put(Constants.JOB_ENTRY_FORM,Constants.VIEW_FST);  
     	  }else if(jobType.equalsIgnoreCase(Constants.OUTSOURCING_JOBTYPE)){
     		 urlMapper.put(Constants.JOB_ENTRY_FORM,Constants.VIEW_OUT);  
     	  }
     	  urlMapper.put(Constants.JOB_INSTRUCTIONS, Constants.VIEW_JOB_INSTR);
     	  urlMapper.put(Constants.JOB_SELECT_CHARGES, Constants.VIEW_SELECT_CHARGES);
     	  //urlMapper.put(Constants.JOB_SELECT_CHARGES, Constants.SELECT_CHARGES);
     	  urlMapper.put(Constants.JOB_PREVIEW_INVOICE, Constants.INV_PREVIEW);
     	  urlMapper.put(Constants.JOB_INVOICE, Constants.VIEW_INV);	
	  }
 	  
  }
  
  // END : #119240
  
}
