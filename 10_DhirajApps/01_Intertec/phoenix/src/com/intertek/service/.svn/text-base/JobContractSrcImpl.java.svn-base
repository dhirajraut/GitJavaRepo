package com.intertek.service;

import java.util.Iterator;
import java.util.List;
import java.util.Set;

import com.intertek.entity.JobContract;
import com.intertek.entity.JobContractInspectionResult;
import com.intertek.entity.JobContractManualTest;
import com.intertek.entity.JobContractProd;
import com.intertek.entity.JobContractProdControl;
import com.intertek.entity.JobContractProductInspectionResult;
import com.intertek.entity.JobContractProductService;
import com.intertek.entity.JobContractProductServiceControl;
import com.intertek.entity.JobContractProductServiceResult;
import com.intertek.entity.JobContractService;
import com.intertek.entity.JobContractServiceControl;
import com.intertek.entity.JobContractServiceResult;
import com.intertek.entity.JobContractSlate;
import com.intertek.entity.JobContractTest;
import com.intertek.entity.JobContractVessel;
import com.intertek.entity.JobContractVesselControl;
import com.intertek.entity.JobContractVesselInspectionResult;
import com.intertek.entity.JobContractVesselService;
import com.intertek.entity.JobContractVesselServiceControl;
import com.intertek.entity.JobContractVesselServiceResult;
import com.intertek.entity.Prebill;
import com.intertek.entity.PrebillSplit;

public class JobContractSrcImpl extends BaseService implements JobContractSrc
{
  public JobContract getJobContractByJobContractIdWithDetail_1(Long jobContractId)
  {
    if(jobContractId == null) return null;

    List jobContracts = getDao().find(
      "select jc from JobContract jc "
      + " left join fetch jc.jobContractServices jcs left join fetch jcs.controls left join fetch jcs.results jcsr "
      + " left join fetch jcsr.prebills "
      + " left join fetch jc.jobContractVessels jcv "
      + " left join fetch jcv.controls "
      + " left join fetch jcv.jobContractVesselServices jcvs left join fetch jcvs.controls left join fetch jcvs.results jcvsr "
      + " left join fetch jcvsr.prebills "
      + " left join fetch jcv.jobContractProds jcp "
      + " left join fetch jcp.controls "
      + " left join fetch jcp.jobContractProductServices jcps left join fetch jcps.controls left join fetch jcps.results jcpsr "
      + " left join fetch jcpsr.prebills "
      + " left join fetch jcp.results jcpir "
      + " left join fetch jcpir.prebills "
      + " left join fetch jcp.jobContractTests jct "
      + " left join fetch jct.test "
      + " left join fetch jct.prebills "
      + " left join fetch jcp.jobContractManualTests jcmt "
      + " left join fetch jcmt.prebills "
      + " left join fetch jcp.jobContractSlates jcsl "
      + " left join fetch jcsl.slate "
      + " left join fetch jcsl.prebills "
      + " where jc.id = ? ",
      new Object[] { jobContractId });

    if (jobContracts.size() > 0) return (JobContract) jobContracts.get(0);

    return null;
  }

  public JobContract getJobContractByJobContractIdWithDetail(Long jobContractId)
  {
    if(jobContractId == null) return null;

    List jobContracts = getDao().find(
      "select jc from JobContract jc "
      + " left join fetch jc.jobProdContracts "
      + " left join fetch jc.results "
      + " left join fetch jc.jobContractServices jcs "
      //+ " left join fetch jcs.controls left join fetch jcs.results jcsr "
      //+ " left join fetch jcsr.prebills "
      + " left join fetch jc.jobContractVessels jcv "
      + " left join fetch jcv.results "
      //+ " left join fetch jcv.controls "
      + " left join fetch jcv.jobContractVesselServices jcvs "
      //+ " left join fetch jcvs.controls left join fetch jcvs.results jcvsr "
      //+ " left join fetch jcvsr.prebills "
      + " left join fetch jcv.jobContractProds jcp "
      //+ " left join fetch jcp.controls "
      + " left join fetch jcp.jobContractProductServices jcps "
      //+ " left join fetch jcps.controls left join fetch jcps.results jcpsr "
      //+ " left join fetch jcpsr.prebills "
      + " left join fetch jcp.results jcpir "
      //+ " left join fetch jcpir.prebills "
      + " left join fetch jcp.jobContractTests jct "
      + " left join fetch jct.test "
      //+ " left join fetch jct.prebills "
      + " left join fetch jcp.jobContractManualTests jcmt "
      //+ " left join fetch jcmt.prebills "
      + " left join fetch jcp.jobContractSlates jcsl "
      + " left join fetch jcsl.slate "
      //+ " left join fetch jcsl.prebills "
      + " where jc.id = ? ",
      new Object[] { jobContractId });

    JobContract jobContract = null;
    if (jobContracts.size() > 0)
    {
      jobContract = (JobContract) jobContracts.get(0);

      Iterator it = jobContract.getJobContractServices().iterator();
      while(it.hasNext())
      {
        JobContractService service = (JobContractService)it.next();
        String serviceName = service.getServiceName();

        Iterator it1 = service.getControls().iterator();
        while(it1.hasNext())
        {
          JobContractServiceControl control = (JobContractServiceControl)it1.next();
          String objectName = control.getObjectName();
        }

        it1 = service.getResults().iterator();
        while(it1.hasNext())
        {
          JobContractServiceResult result = (JobContractServiceResult)it1.next();
          String expressionid = result.getExpressionId();

          iteratePrebills(result.getPrebills());
        }
      }

      Iterator itci = jobContract.getResults().iterator();
      while(itci.hasNext())
      {
        JobContractInspectionResult result = (JobContractInspectionResult)itci.next();
        String expressionid = result.getExpressionId();

        iteratePrebills(result.getPrebills());
      }

      Iterator itv = jobContract.getJobContractVessels().iterator();
      while(itv.hasNext())
      {
        JobContractVessel jcv = (JobContractVessel)itv.next();
        Iterator it1 = jcv.getJobContractVesselServices().iterator();
        while(it1.hasNext())
        {
          JobContractVesselService service = (JobContractVesselService)it1.next();
          String serviceName = service.getServiceName();

          Iterator it2 = service.getControls().iterator();
          while(it2.hasNext())
          {
            JobContractVesselServiceControl control = (JobContractVesselServiceControl)it2.next();
            String objectName = control.getObjectName();
          }

          it2 = service.getResults().iterator();
          while(it2.hasNext())
          {
            JobContractVesselServiceResult result = (JobContractVesselServiceResult)it2.next();
            String expressionid = result.getExpressionId();

            iteratePrebills(result.getPrebills());
          }
        }

        Iterator itvi = jcv.getResults().iterator();
        while(itvi.hasNext())
        {
          JobContractVesselInspectionResult result = (JobContractVesselInspectionResult)itvi.next();
          String expressionid = result.getExpressionId();

          iteratePrebills(result.getPrebills());
        }

        Iterator itc = jcv.getControls().iterator();
        while(itc.hasNext())
        {
          JobContractVesselControl control = (JobContractVesselControl)itc.next();
          String objectName = control.getObjectName();
        }

        Iterator itp = jcv.getJobContractProds().iterator();
        while(itp.hasNext())
        {
          JobContractProd jcp = (JobContractProd)itp.next();

          it1 = jcp.getJobContractProductServices().iterator();
          while(it1.hasNext())
          {
            JobContractProductService service = (JobContractProductService)it1.next();
            String serviceName = service.getServiceName();

            Iterator it2 = service.getControls().iterator();
            while(it2.hasNext())
            {
              JobContractProductServiceControl control = (JobContractProductServiceControl)it2.next();
              String objectName = control.getObjectName();
            }

            it2 = service.getResults().iterator();
            while(it2.hasNext())
            {
              JobContractProductServiceResult result = (JobContractProductServiceResult)it2.next();
              String expressionid = result.getExpressionId();

              iteratePrebills(result.getPrebills());
            }
          }

          Iterator itpc = jcp.getControls().iterator();
          while(itpc.hasNext())
          {
            JobContractProdControl control = (JobContractProdControl)itpc.next();
            String objectName = control.getObjectName();
          }

          Iterator iti = jcp.getResults().iterator();
          while(iti.hasNext())
          {
            JobContractProductInspectionResult result = (JobContractProductInspectionResult)iti.next();
            String expressionid = result.getExpressionId();

            iteratePrebills(result.getPrebills());
          }

          Iterator itt = jcp.getJobContractTests().iterator();
          while(itt.hasNext())
          {
            JobContractTest test = (JobContractTest)itt.next();
            Double quantity = test.getQuantity();

            iteratePrebills(test.getPrebills());
          }

          Iterator its = jcp.getJobContractSlates().iterator();
          while(its.hasNext())
          {
            JobContractSlate slate = (JobContractSlate)its.next();
            Double quantity = slate.getQuantity();

            iteratePrebills(slate.getPrebills());
          }

          Iterator itmt = jcp.getJobContractManualTests().iterator();
          while(itmt.hasNext())
          {
            JobContractManualTest test = (JobContractManualTest)itmt.next();
            Double quantity = test.getQuantity();

            iteratePrebills(test.getPrebills());
          }
        }
      }
    }

    return jobContract;
  }

  private void iteratePrebills(Set prebills)
  {
    Iterator it3 = prebills.iterator();
    while(it3.hasNext())
    {
      Prebill prebill = (Prebill)it3.next();
      String level0 = prebill.getLevel0();

      Iterator it4 = prebill.getPrebillSplits().iterator();
      while(it4.hasNext())
      {
        PrebillSplit prebillSplit = (PrebillSplit)it4.next();
        String branchName = prebillSplit.getBranchName();
      }
    }
  }
}
