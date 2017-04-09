package com.intertek.test;

import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

import com.intertek.entity.JobOrder;
import com.intertek.entity.JobProd;
import com.intertek.entity.JobProdSample;
import com.intertek.entity.JobVessel;
import com.intertek.locator.ServiceLocator;
import com.intertek.service.JobContractSrc;
import com.intertek.service.JobService;
import com.intertek.service.PrebillService;
import com.intertek.service.UserService;
import com.intertek.service.WSService;
import com.intertek.service.ContractService;
import com.intertek.service.ServiceRateService;
import com.intertek.util.*;

public class InvoiceTest
{
  public static void main(String[] args) throws Exception
  {
    ApplicationContext ctx = new FileSystemXmlApplicationContext(
      new String[]
      {
        "../web/WEB-INF/applicationContext.xml",
        //"../web/WEB-INF/applicationContext-ws.xml",
        //"../web/WEB-INF/applicationContext-timer.xml"
      }
    );

    ServiceLocator.getInstance().setApplicationContext(ctx);


    JobService jobService = (JobService)ServiceLocator.getInstance().getBean("jobService");
    JobContractSrc jobContractSrc = (JobContractSrc)ServiceLocator.getInstance().getBean("jobContractSrc");
    UserService userService = (UserService)ServiceLocator.getInstance().getBean("userService");
    ContractService contractService = (ContractService)ServiceLocator.getInstance().getBean("contractService");
    ServiceRateService serviceRateService = (ServiceRateService)ServiceLocator.getInstance().getBean("serviceRateService");

    long startTime = System.currentTimeMillis();
    //JobOrder jobOrder = jobService.getJobOrderByJobNumberWithDetail("US320-0018144");
    //JobOrder jobOrder = jobService.getJobOrderByJobNumberWithPrebills("US120-0003202");

    //JobContract jobContract = jobContractSrc.getJobContractByJobContractIdWithDetail(new Long(21));

    //JobOrder jobOrder = jobService.getJobOrderByJobNumberWithInvoiceInfo("UK260-0002230");
    //Iterator it = jobOrder.getJobContracts().iterator();
    //while(it.hasNext())
    //{
    //  JobContract jobContract = (JobContract)it.next();
    //  System.out.println("invoices: " + jobContract.getJobContractInvoices());
    //}

    List idList = serviceRateService.getEditHighLevelServiceIdListByContractCodeAndWorkingPb("EAMEEUR06", "EAMEEUR06");
    System.out.println("===== idList = " + idList);


    //InvoiceUtil.generateInvoicePDF(new Long(876971), "C:/phoenix/invoice/", "C:/phoenix/invoice/");

    //InvoiceUtil.generateInvoicePDF(new Long(819878), "C:/eclipse/workspace/phoenixprj/conf/invoice/", "C:/eclipse/workspace/phoenixprj/conf/invoice/");
    //User user = userService.getUserByNameWithPermissions("admin");

    //testGetOrder(jobService, "US320-0018144");

    long endTime = System.currentTimeMillis();

    System.out.println(" Time used: " + (endTime - startTime));


    //InvoiceManager.generateInvoice(new Long(21));
    PrebillService prebillService = (PrebillService)ServiceLocator.getInstance().getBean("prebillService");

    startTime = System.currentTimeMillis();
    //prebillService.generateInvoice(new Long(39), InvoiceUtil.getInvoiceDir(), null,null,null);
    //testGetOrder0(jobService, "US320-0018144");
    //prebillService.generateInvoicePDF(876971l, "C:/phoenix/invoice/", "C:/phoenix/jasper/");
    endTime = System.currentTimeMillis();

    System.out.println(" Time used: " + (endTime - startTime));

    //InvoiceDataCollector collector = new InvoiceDataCollector();
    //ContactDataCollector collector = new ContactDataCollector();
    //CustomerDataCollector collector = new CustomerDataCollector();
    //collector.setStartPage(1);
    //collector.setNumInPage(1);

    //Document doc = collector.collect();

    //XMLOutputter xmlOut = new XMLOutputter(Format.getPrettyFormat());
    //xmlOut.output(doc, System.out);

    //WSService wsService = (WSService)ServiceLocator.getInstance().getBean("wsService");
    //WebServiceEntity wsEntity = new WebServiceEntity();
    //wsEntity.setContent("11111111111111");
    //wsEntity.setMessage("123");
    //wsService.saveWebServiceEntity(wsEntity);

    //OutboundWebService ows = (OutboundWebService)ServiceLocator.getInstance().getBean("samOutboundWebService");
    //WSOutboundContext context = new WSOutboundContext();
    //context.getDataMap().put(Constants.JOB_NUMBER, "US320-0018144");
    //ows.sendService(context);

    //OutboundWebService ows = (OutboundWebService)ServiceLocator.getInstance().getBean("limsJobOrderOutboundWebService");
    //WSOutboundContext context = new WSOutboundContext();
    //context.getDataMap().put(Constants.JOB_NUMBER, "US320-0018144");
    //ows.sendService(context);

      WSService wsService = (WSService)ServiceLocator.getInstance().getBean("wsService");
/*
      Pagination pagination = null;
      pagination = new Pagination(0, 1, 1, 1);

      List entities = wsService.getLimsCustomersForOutboundInvoiceMessage(pagination);

      System.out.println("entities = " + entities);
      for(int i=0; i<entities.size(); i++)
      {
        Object[] objs = (Object[])entities.get(i);
        for(int j=0; j<objs.length; j++)
        {
          System.out.println(i + " = " + objs[j]);
        }
      }

      entities = wsService.getLimsContactsForOutboundInvoiceMessage(pagination);

      System.out.println("entities = " + entities);
      for(int i=0; i<entities.size(); i++)
      {
        Object[] objs = (Object[])entities.get(i);
        for(int j=0; j<objs.length; j++)
        {
          System.out.println(i + " = " + objs[j]);
        }
      }
*/
    //wsService.updateContactFlag(new Long("12679"));

    double input = 72.475; //27.5; //72.475;

    double result = NumberUtil.roundHalfUp(input, 2);
    System.out.println("========== round " + result);
    result = NumberUtil.roundHalfUp(input, 2);

    System.out.println("========== roundHalfUp " + result);

    //System.exit(0);

/*
    List jobContractIds = new ArrayList();
    jobContractIds.add(new Long(1));

    prebillService.generateInvoices(jobContractIds, InvoiceUtil.getInvoiceDir());
*/
  }


  public static void testGetOrder(JobService jobService, String jobNumber)
  {
    JobOrder jobOrder = jobService.getJobOrderByJobNumberWithDetail(jobNumber);
    List jobVessels = jobService.getJobVesselsByJobNumber(jobNumber);
    if(jobVessels != null && jobVessels.size() > 0)
    {
      for(int i =0;i<jobVessels.size();i++)
      {
        JobVessel jobVessel = (JobVessel)jobVessels.get(i);

        List jobProds = jobService.getJobProductsByJobNumberAndVesselRow(jobNumber,jobVessel.getJobVesselId().getLinkedVslRow());
        if(jobProds != null && jobProds.size() > 0)
        {
          for(int j=0;j<jobProds.size();j++)
          {
            JobProd jobProd = (JobProd)jobProds.get(j);

            //Load Quantity related to each product
            List jobProdQtys = jobService.getJobProdQtysByJobNumberVesselRowAndProductName(jobNumber,jobVessel.getJobVesselId().getLinkedVslRow(),jobProd.getJobProdId().getProdSeqNum());

            //Load Inspection Events related to each product
            List jobEventList = jobService.getJobEventsByJobNumberVesselRowAndProductName(jobNumber,jobVessel.getJobVesselId().getLinkedVslRow(),jobProd.getJobProdId().getProdSeqNum());

            //Load Sample Locations related to each product
            List jobProdSamples = jobService.getJobProdSamplesByJobNumberVesselRowAndProductName(jobNumber,jobVessel.getJobVesselId().getLinkedVslRow(),jobProd.getJobProdId().getProdSeqNum());
            if(jobProdSamples != null && jobProdSamples.size() > 0)
            {
              for(int l=0;l<jobProdSamples.size();l++)
              {
                JobProdSample jobProdSample = (JobProdSample)jobProdSamples.get(l);

                //Load Tests related to each sample location
                List jobTestsList = jobService.getJobTestsByJobNumberVesselRowProductAndSampleLoc(jobNumber,jobVessel.getJobVesselId().getLinkedVslRow(),jobProd.getJobProdId().getProdSeqNum(),jobProdSample.getJobProdSampleId().getSampSeqId());

                //Load Slates related to each sample location
                List jobSlatesList = jobService.getJobSlatesByJobNumberVesselRowProductAndSampleLoc(jobNumber,jobVessel.getJobVesselId().getLinkedVslRow(),jobProd.getJobProdId().getProdSeqNum(),jobProdSample.getJobProdSampleId().getSampSeqId());
              }
            }
          }
        }
      }
    }
  }

  public static void testGetOrder0(JobService jobService, String jobNumber)
  {
    JobOrder jobOrder = jobService.getJobOrderByJobNumberWithDetail(jobNumber);

    List jobVessels = new ArrayList(jobOrder.getJobVessels());
    if(jobVessels != null && jobVessels.size() > 0)
    {
      for(int i =0;i<jobVessels.size();i++)
      {
        JobVessel jobVessel = (JobVessel)jobVessels.get(i);

        List jobProds = new ArrayList(jobVessel.getJobProds());
        if(jobProds != null && jobProds.size() > 0)
        {
          for(int j=0;j<jobProds.size();j++)
          {
            JobProd jobProd = (JobProd)jobProds.get(j);

            //Load Quantity related to each product
            List jobProdQtys = new ArrayList(jobProd.getJobProdQtys());

            //Load Inspection Events related to each product
            List jobEventList = new ArrayList(jobProd.getJobEvents());

            //Load Sample Locations related to each product
            List jobProdSamples = new ArrayList(jobProd.getJobProdSamples());
            if(jobProdSamples != null && jobProdSamples.size() > 0)
            {
              for(int l=0;l<jobProdSamples.size();l++)
              {
                JobProdSample jobProdSample = (JobProdSample)jobProdSamples.get(l);

                //Load Tests related to each sample location
                List jobTestsList = new ArrayList(jobProdSample.getJobTests());

                //Load Slates related to each sample location
                List jobSlatesList = new ArrayList(jobProdSample.getJobSlates());
              }
            }
          }
        }
      }
    }
  }
}
