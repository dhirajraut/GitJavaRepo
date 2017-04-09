package com.intertek.service;

import java.util.List;
import java.util.Iterator;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.intertek.domain.AddJobContract;
import com.intertek.entity.JobContract;
import com.intertek.entity.JobOrder;
import com.intertek.entity.JobContractService;
import com.intertek.entity.JobContractServiceResult;
import com.intertek.entity.JobContractInspectionResult;
import com.intertek.entity.JobContractVessel;
import com.intertek.entity.JobContractVesselService;
import com.intertek.entity.JobContractVesselServiceResult;
import com.intertek.entity.JobContractVesselInspectionResult;
import com.intertek.entity.JobContractProd;
import com.intertek.entity.JobContractProductService;
import com.intertek.entity.JobContractProductServiceResult;
import com.intertek.entity.JobContractProductInspectionResult;
import com.intertek.entity.JobContractTest;
import com.intertek.entity.JobContractManualTest;
import com.intertek.entity.JobContractSlate;
import com.intertek.entity.Prebill;

public class JobContractResultServiceImpl extends BaseService implements JobContractResultService
{
  private static Log log = LogFactory.getLog(JobContractResultServiceImpl.class);

  public void saveJobContractResults(AddJobContract[] addJobContracts)
  {
    if(addJobContracts != null)
    {
      JobOrder jobOrder = null;
      for(int i=0; i<addJobContracts.length; i++)
      {
        JobContract jobContract = addJobContracts[i].getJobContract();
        //logPrebills(jobContract, "Before saving jobContract to DB");
        JobContract newJobContract = getDao().save(jobContract);
        //logPrebills(newJobContract, "After saving jobContract to DB");

        if(jobOrder == null)
        {
          jobOrder = jobContract.getJobOrder();
        }
      }

      if(jobOrder != null)
      {
        if((jobOrder.getPageNumber() == null) || (jobOrder.getPageNumber().intValue() < 3))
        {
          getDao().bulkUpdate(
            "update JobOrder set pageNumber = 3 where jobNumber = ?",
            new Object[] { jobOrder.getJobNumber() }
          );
        }
      }
    }
  }

  public List getJobContractServices(Long jobContractId)
  {
    return getDao().find(
      "select distinct jcs from JobContractService jcs left join fetch jcs.controls left join fetch jcs.results result left join fetch result.prebills where jcs.jobContract.id = ? order by jcs.id",
      new Object[] { jobContractId }
    );
  }

  public List getJobContractVesselServices(Long jobContractId, Integer vesselId)
  {
    return getDao().find(
      "select distinct jcvs from JobContractVesselService jcvs left join fetch jcvs.controls left join fetch jcvs.results result left join fetch result.prebills where jcvs.jobContract.id = ? and jcvs.jobVessel.jobVesselId.linkedVslRow = ? order by jcvs.id",
      new Object[] { jobContractId, vesselId }
    );
  }

  public List getJobContractProductServices(Long jobContractId, Integer vesselId, Integer prodSeqNum)
  {
    return getDao().find(
      "select distinct jcps from JobContractProductService jcps left join fetch jcps.controls left join fetch jcps.results result left join fetch result.prebills where jcps.jobContract.id = ? and jcps.jobProd.jobProdId.linkedVslRow = ? and jcps.jobProd.jobProdId.prodSeqNum = ? order by jcps.id",
      new Object[] { jobContractId, vesselId, prodSeqNum }
    );
  }

  public List getJobContractProductInspectionResults(Long jobContractId, Integer vesselId, Integer prodSeqNum)
  {
    return getDao().find(
      "select distinct jcpir from JobContractProductInspectionResult jcpir left join fetch jcpir.prebills where jcpir.jobContract.id = ? and jcpir.jobProd.jobProdId.linkedVslRow = ? and jcpir.jobProd.jobProdId.prodSeqNum = ? order by jcpir.id",
      new Object[] { jobContractId, vesselId, prodSeqNum }
    );
  }

  public List getPrebillsWithDetailByJobContractId(Long jobContractId)
  {
    //StringBuffer sb = new StringBuffer();
    //sb.append("select distinct p from Prebill p left join fetch p.jobContract ");
    //sb.append("left join fetch p.jobContractServiceResult.jobContractService ");
    //sb.append("left join fetch p.jobContractVesselServiceResult.jobContractVesselService.jobVessel ");
    //sb.append("left join fetch p.jobContractProductServiceResult.jobContractProductService.jobProd.jobVessel ");
    //sb.append("left join fetch p.jobContractProductServiceResult.jobContractProductService.jobProd.jobVessel ");
    //return getDao().find(
    //  "select distinct p from Prebill p left join fetch p.jobContract left join fetch p.jobContractServiceResult.jobContractService where jcpir.jobContract.id = ? and jcpir.jobProd.jobProdId.linkedVslRow = ? and jcpir.jobProd.jobProdId.jobProductName = ? order by jcpir.id",
    //  new Object[] { jobContractId, vesselId, jobProductName }
    //);

    return null;
  }

  private void logPrebills_old(JobContract jobContract, String message)
  {
    log.info(message);

    if(jobContract == null) return;

    try
    {
      Iterator<Prebill> it2 = jobContract.getPrebills().iterator();
      while(it2.hasNext())
      {
        Prebill prebill = it2.next();
        log.info("debugging unicode " + prebill.getId() + " " + prebill.getNetPrice());
      }
    }
    catch(Throwable t)
    {
      //t.printStackTrace();
    }

    try
    {
      Iterator<JobContractService> jcsIt = jobContract.getJobContractServices().iterator();
      while(jcsIt.hasNext())
      {
        JobContractService jobContractService = jcsIt.next();
        Iterator<JobContractServiceResult> it1 = jobContractService.getResults().iterator();
        while(it1.hasNext())
        {
          JobContractServiceResult result = it1.next();
          Iterator<Prebill> it2 = result.getPrebills().iterator();
          while(it2.hasNext())
          {
            Prebill prebill = it2.next();
            log.info("debugging unicode " + prebill.getId() + " " + prebill.getNetPrice());
          }
        }
      }
    }
    catch(Throwable t)
    {
      //t.printStackTrace();
    }

    try
    {
      Iterator<JobContractInspectionResult> jcirIt = jobContract.getResults().iterator();
      while(jcirIt.hasNext())
      {
        JobContractInspectionResult result = jcirIt.next();
        Iterator<Prebill> it2 = result.getPrebills().iterator();
        while(it2.hasNext())
        {
          Prebill prebill = it2.next();
          log.info("debugging unicode " + prebill.getId() + " " + prebill.getNetPrice());
        }
      }
    }
    catch(Throwable t)
    {
      //t.printStackTrace();
    }

    try
    {
      Iterator<JobContractVessel> vesselIt = jobContract.getJobContractVessels().iterator();
      while(vesselIt.hasNext())
      {
        JobContractVessel jobContractVessel = vesselIt.next();

        try
        {
          Iterator<JobContractVesselInspectionResult> jcvirIt = jobContractVessel.getResults().iterator();
          while(jcvirIt.hasNext())
          {
            JobContractVesselInspectionResult result = jcvirIt.next();
            Iterator<Prebill> it2 = result.getPrebills().iterator();
            while(it2.hasNext())
            {
              Prebill prebill = it2.next();
              log.info("debugging unicode " + prebill.getId() + " " + prebill.getNetPrice());
            }
          }
        }
        catch(Throwable t1)
        {
          //t1.printStackTrace();
        }

        try
        {
          Iterator<JobContractVesselService> jcvsIt = jobContractVessel.getJobContractVesselServices().iterator();
          while(jcvsIt.hasNext())
          {
            JobContractVesselService jobContractVesselService = jcvsIt.next();
            Iterator<JobContractVesselServiceResult> it1 = jobContractVesselService.getResults().iterator();
            while(it1.hasNext())
            {
              JobContractVesselServiceResult result = it1.next();
              Iterator<Prebill> it2 = result.getPrebills().iterator();
              while(it2.hasNext())
              {
                Prebill prebill = it2.next();
                log.info("debugging unicode " + prebill.getId() + " " + prebill.getNetPrice());
              }
            }
          }
        }
        catch(Throwable t1)
        {
          //t1.printStackTrace();
        }

        try
        {
          Iterator<JobContractProd> prodIt = jobContractVessel.getJobContractProds().iterator();
          while(prodIt.hasNext())
          {
            JobContractProd jobContractProd = prodIt.next();

            try
            {
              Iterator<JobContractProductInspectionResult> jcpirIt = jobContractProd.getResults().iterator();
              while(jcpirIt.hasNext())
              {
                JobContractProductInspectionResult result = jcpirIt.next();
                Iterator<Prebill> it2 = result.getPrebills().iterator();
                while(it2.hasNext())
                {
                  Prebill prebill = it2.next();
                  log.info("debugging unicode " + prebill.getId() + " " + prebill.getNetPrice());
                }
              }
            }
            catch(Throwable t2)
            {
              //t2.printStackTrace();
            }

            try
            {
              Iterator<JobContractProductService> jcpsIt = jobContractProd.getJobContractProductServices().iterator();
              while(jcpsIt.hasNext())
              {
                JobContractProductService jobContractProductService = jcpsIt.next();

                Iterator<JobContractProductServiceResult> it1 = jobContractProductService.getResults().iterator();
                while(it1.hasNext())
                {
                  JobContractProductServiceResult result = it1.next();
                  Iterator<Prebill> it2 = result.getPrebills().iterator();
                  while(it2.hasNext())
                  {
                    Prebill prebill = it2.next();
                    log.info("debugging unicode " + prebill.getId() + " " + prebill.getNetPrice());
                  }
                }
              }
            }
            catch(Throwable t2)
            {
              //t2.printStackTrace();
            }

            try
            {
              Iterator<JobContractTest> testIt = jobContractProd.getJobContractTests().iterator();
              while(testIt.hasNext())
              {
                JobContractTest test = testIt.next();
                Iterator<Prebill> it2 = test.getPrebills().iterator();
                while(it2.hasNext())
                {
                  Prebill prebill = it2.next();
                  log.info("debugging unicode " + prebill.getId() + " " + prebill.getNetPrice());
                }
              }
            }
            catch(Throwable t2)
            {
              //t2.printStackTrace();
            }

            try
            {
              Iterator<JobContractManualTest> manualTestIt = jobContractProd.getJobContractManualTests().iterator();
              while(manualTestIt.hasNext())
              {
                JobContractManualTest test = manualTestIt.next();
                Iterator<Prebill> it2 = test.getPrebills().iterator();
                while(it2.hasNext())
                {
                  Prebill prebill = it2.next();
                  log.info("debugging unicode " + prebill.getId() + " " + prebill.getNetPrice());
                }
              }
            }
            catch(Throwable t2)
            {
              //t2.printStackTrace();
            }

            try
            {
              Iterator<JobContractSlate> slateIt = jobContractProd.getJobContractSlates().iterator();
              while(slateIt.hasNext())
              {
                JobContractSlate slate = slateIt.next();
                Iterator<Prebill> it2 = slate.getPrebills().iterator();
                while(it2.hasNext())
                {
                  Prebill prebill = it2.next();
                  log.info("debugging unicode " + prebill.getId() + " " + prebill.getNetPrice());
                }
              }
            }
            catch(Throwable t2)
            {
              //t2.printStackTrace();
            }
          }
        }
        catch(Throwable t1)
        {
          //t1.printStackTrace();
        }
      }
    }
    catch(Throwable t)
    {
      //t.printStackTrace();
    }
  }
}
