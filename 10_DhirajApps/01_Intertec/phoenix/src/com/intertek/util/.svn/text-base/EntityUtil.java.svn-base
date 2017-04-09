package com.intertek.util;

import java.util.Iterator;

import com.intertek.entity.Branch;
import com.intertek.entity.CfgContract;
import com.intertek.entity.CfgContractId;
import com.intertek.entity.ConsolidatedInvoice;
import com.intertek.entity.ConsolidatedInvoiceId;
import com.intertek.entity.Contact;
import com.intertek.entity.ContactCust;
import com.intertek.entity.ContactCustId;
import com.intertek.entity.ContractExpression;
import com.intertek.entity.ContractExpressionId;
import com.intertek.entity.Control;
import com.intertek.entity.ControlId;
import com.intertek.entity.Country;
import com.intertek.entity.CustAddrSeq;
import com.intertek.entity.CustAddrSeqId;
import com.intertek.entity.CustAddress;
import com.intertek.entity.Customer;
import com.intertek.entity.InvoiceLine;
import com.intertek.entity.InvoiceLineSplit;
import com.intertek.entity.JobContract;
import com.intertek.entity.JobContractInvoice;
import com.intertek.entity.JobInspectionEvent;
import com.intertek.entity.JobInspectionEventId;
import com.intertek.entity.JobOrder;
import com.intertek.entity.JobProd;
import com.intertek.entity.JobProdId;
import com.intertek.entity.JobProdSample;
import com.intertek.entity.JobProdSampleId;
import com.intertek.entity.JobVessel;
import com.intertek.entity.JobVesselId;
import com.intertek.entity.Prebill;
import com.intertek.entity.PrebillSplit;
import com.intertek.entity.ProductGroup;
import com.intertek.entity.ProductGroupId;
import com.intertek.entity.RB;
import com.intertek.entity.RBId;
import com.intertek.entity.ServiceLocation;
import com.intertek.entity.ServiceRate;
import com.intertek.entity.ServiceRateId;
import com.intertek.entity.ShippingAgent;
import com.intertek.entity.TaxArticle;
import com.intertek.entity.TestPrice;
import com.intertek.entity.TestPriceId;
import com.intertek.entity.Service;
import com.intertek.entity.ServiceId;
import com.intertek.entity.SlatePrice;
import com.intertek.entity.SlatePriceId;
import com.intertek.entity.TowingCompany;
import com.intertek.entity.ReferenceField;
import com.intertek.entity.ReferenceFieldId;
import com.intertek.entity.VesselType;
import com.intertek.entity.VesselTypeId;
import com.intertek.entity.InspectionRate;
import com.intertek.entity.InspectionRateId;
import com.intertek.entity.InspectionVersion;
import com.intertek.entity.InspectionVersionId;

public class EntityUtil
{
  public static void copyJobOrder(JobOrder dest, JobOrder orig)
  {
    if( (dest == null) || (orig == null) ) return;
    BeanUtil.copySimpleProperties(dest, orig, true);

    TowingCompany tcOrig = orig.getTowingCompany();
    if(tcOrig != null)
    {
      TowingCompany tcDest = new TowingCompany();
      BeanUtil.copySimpleProperties(tcDest, tcOrig, true);

      dest.setTowingCompany(tcDest);
    }

    ShippingAgent saOrig = orig.getShippingAgent();
    if(saOrig != null)
    {
      ShippingAgent saDest = new ShippingAgent();

      dest.setShippingAgent(saDest);
    }

    ServiceLocation slOrig = orig.getServiceLocation();
    if(slOrig != null)
    {
      ServiceLocation slDest = new ServiceLocation();
      BeanUtil.copySimpleProperties(slDest, slOrig, true);

      dest.setServiceLocation(slDest);
    }

    Branch bOrig = orig.getBranch();
    if(bOrig != null)
    {
      Branch bDest = new Branch();
      BeanUtil.copySimpleProperties(bDest, bOrig, true);

      dest.setBranch(bDest);
    }
  }

  public static void copyJobOrderWithDetail(JobOrder dest, JobOrder orig)
  {
    if( (dest == null) || (orig == null) ) return;
    copyJobOrder(dest, orig);

    Iterator it = orig.getJobContracts().iterator();
    while(it.hasNext())
    {
      JobContract jcOrig = (JobContract)it.next();
      JobContract jcDest = new JobContract();

      copyJobContractWithCustomerContactOnly(jcDest, jcOrig);

      dest.getJobContracts().add(jcDest);
    }

    it = orig.getJobVessels().iterator();
    while(it.hasNext())
    {
      JobVessel jvOrig = (JobVessel)it.next();
      JobVessel jvDest = new JobVessel();

      copyJobVessel(jvDest, jvOrig);

      dest.getJobVessels().add(jvDest);
    }
  }

  public static void copyJobVessel(JobVessel dest, JobVessel orig)
  {
    if( (dest == null) || (orig == null) ) return;
    BeanUtil.copySimpleProperties(dest, orig, true);

    JobVesselId idOrig = orig.getJobVesselId();
    if(idOrig != null)
    {
      JobVesselId idDest = new JobVesselId();
      BeanUtil.copySimpleProperties(idDest, idOrig, true);

      dest.setJobVesselId(idDest);
    }

    Iterator it = orig.getJobProds().iterator();
    while(it.hasNext())
    {
      JobProd jpOrig = (JobProd)it.next();
      JobProd jpDest = new JobProd();

      copyJobProd(jpDest, jpOrig);

      dest.getJobProds().add(jpDest);
    }
  }

  public static void copyJobProd(JobProd dest, JobProd orig)
  {
    if( (dest == null) || (orig == null) ) return;
    BeanUtil.copySimpleProperties(dest, orig, true);

    JobProdId idOrig = orig.getJobProdId();
    if(idOrig != null)
    {
      JobProdId idDest = new JobProdId();
      BeanUtil.copySimpleProperties(idDest, idOrig, true);

      dest.setJobProdId(idDest);
    }

    Iterator it = orig.getJobInspectionEvents().iterator();
    while(it.hasNext())
    {
      JobInspectionEvent jieOrig = (JobInspectionEvent)it.next();
      JobInspectionEvent jieDest = new JobInspectionEvent();

      copyJobInspectionEvent(jieDest, jieOrig);

      dest.getJobInspectionEvents().add(jieDest);
    }

    it = orig.getJobProdSamples().iterator();
    while(it.hasNext())
    {
      JobProdSample jpsOrig = (JobProdSample)it.next();
      JobProdSample jpsDest = new JobProdSample();

      copyJobProdSample(jpsDest, jpsOrig);

      dest.getJobProdSamples().add(jpsDest);
    }
  }

  public static void copyJobProdSample(JobProdSample dest, JobProdSample orig)
  {
    if( (dest == null) || (orig == null) ) return;
    BeanUtil.copySimpleProperties(dest, orig, true);

    JobProdSampleId idOrig = orig.getJobProdSampleId();
    JobProdSampleId idDest = new JobProdSampleId();
    BeanUtil.copySimpleProperties(idDest, idOrig, true);

    dest.setJobProdSampleId(idDest);
  }

  public static void copyJobInspectionEvent(JobInspectionEvent dest, JobInspectionEvent orig)
  {
    if( (dest == null) || (orig == null) ) return;
    BeanUtil.copySimpleProperties(dest, orig, true);

    JobInspectionEventId idOrig = orig.getJobInspectionEventId();
    JobInspectionEventId idDest = new JobInspectionEventId();
    BeanUtil.copySimpleProperties(idDest, idOrig, true);

    dest.setJobInspectionEventId(idDest);
  }

  public static void copyJobContractWithCustomerContactOnly(JobContract dest, JobContract orig)
  {
    if( (dest == null) || (orig == null) ) return;
    BeanUtil.copySimpleProperties(dest, orig, true);

    Customer cOrig = orig.getCustomer();
    if(cOrig != null)
    {
      Customer cDest = new Customer();
      copyCustomer(cDest, cOrig);

      dest.setCustomer(cDest);
    }

    Contact ctOrig = orig.getContact();
    if(ctOrig != null)
    {
      Contact ctDest = new Contact();
      copyContact(ctDest, ctOrig);

      dest.setContact(ctDest);
    }
  }

  public static void copyJobContract(JobContract dest, JobContract orig)
  {
    if( (dest == null) || (orig == null) ) return;
    BeanUtil.copySimpleProperties(dest, orig, true);

    JobOrder joOrig = orig.getJobOrder();
    if(joOrig != null)
    {
      JobOrder joDest = new JobOrder();
      copyJobOrder(joDest, joOrig);

      dest.setJobOrder(joDest);
    }

    Iterator it1 = orig.getPrebills().iterator();
    while(it1.hasNext())
    {
      Prebill prebillOrig = (Prebill)it1.next();
      Prebill prebillDest = new Prebill();

      copyPrebill(prebillDest, prebillOrig);

      dest.getPrebills().add(prebillDest);
    }

    Customer cOrig = orig.getCustomer();
    if(cOrig != null)
    {
      Customer cDest = new Customer();
      copyCustomer(cDest, cOrig);

      dest.setCustomer(cDest);
    }

    it1 = orig.getJobContractInvoices().iterator();
    while(it1.hasNext())
    {
      JobContractInvoice invoiceOrig = (JobContractInvoice)it1.next();
      JobContractInvoice invoiceDest = new JobContractInvoice();

      BeanUtil.copySimpleProperties(invoiceDest, invoiceOrig, true);

      dest.getJobContractInvoices().add(invoiceDest);
    }

    Country vatRegCountryOrig = orig.getVatRegCountry();
    if(vatRegCountryOrig != null)
    {
      Country vatRegCountryDest = new Country();
      copyCountry(vatRegCountryDest, vatRegCountryOrig);

      dest.setVatRegCountry(vatRegCountryDest);
    }
  }

  public static void copyCountry(Country dest, Country orig)
  {
    if( (dest == null) || (orig == null) ) return;
    BeanUtil.copySimpleProperties(dest, orig, true);
  }

  public static void copyPrebill(Prebill dest, Prebill orig)
  {
    if( (dest == null) || (orig == null) ) return;
    BeanUtil.copySimpleProperties(dest, orig, true);

    Iterator it2 = orig.getPrebillSplits().iterator();
    while(it2.hasNext())
    {
      PrebillSplit splitOrig = (PrebillSplit)it2.next();
      PrebillSplit splitDest = new PrebillSplit();

      copyPrebillSplit(splitDest, splitOrig);

      dest.getPrebillSplits().add(splitDest);
    }

    TaxArticle taxArticleOrig = orig.getTaxArticle();
    if(taxArticleOrig != null)
    {
      TaxArticle taxArticleDest = new TaxArticle();
      BeanUtil.copySimpleProperties(taxArticleDest, taxArticleOrig, true);

      dest.setTaxArticle(taxArticleDest);
    }
  }

  public static void copyPrebillSplit(PrebillSplit dest, PrebillSplit orig)
  {
    if( (dest == null) || (orig == null) ) return;
    BeanUtil.copySimpleProperties(dest, orig, true);
  }

  public static void copyContact(Contact dest, Contact orig)
  {
    if( (dest == null) || (orig == null) ) return;
    BeanUtil.copySimpleProperties(dest, orig, true);

    Iterator it1 = orig.getContactCusts().iterator();
    while(it1.hasNext())
    {
      ContactCust ccOrig = (ContactCust)it1.next();
      ContactCust ccDest = new ContactCust();

      copyContactCust(ccDest, ccOrig);

      dest.getContactCusts().add(ccDest);
    }
  }

  public static void copyContactCust(ContactCust dest, ContactCust orig)
  {
    if( (dest == null) || (orig == null) ) return;
    BeanUtil.copySimpleProperties(dest, orig, true);

    ContactCustId idOrig = orig.getContactCustId();
    ContactCustId idDest = new ContactCustId();
    BeanUtil.copySimpleProperties(idDest, idOrig, true);

    dest.setContactCustId(idDest);
  }

  public static void copyContactCustWithContact(ContactCust dest, ContactCust orig)
  {
    if( (dest == null) || (orig == null) ) return;
    BeanUtil.copySimpleProperties(dest, orig, true);

    ContactCustId idOrig = orig.getContactCustId();
    ContactCustId idDest = new ContactCustId();
    BeanUtil.copySimpleProperties(idDest, idOrig, true);

    dest.setContactCustId(idDest);

    Contact conctactOrig = orig.getContact();
    if(conctactOrig != null)
    {
      Contact conctactDest = new Contact();
      BeanUtil.copySimpleProperties(conctactDest, conctactOrig, true);

      dest.setContact(conctactDest);
    }
  }

  public static void copyServiceLocation(ServiceLocation dest, ServiceLocation orig)
  {
    if( (dest == null) || (orig == null) ) return;
    BeanUtil.copySimpleProperties(dest, orig, true);

    CustAddrSeq casOrig = orig.getShipToCustAddress();
    if(casOrig != null)
    {
      CustAddrSeq casDest = new CustAddrSeq();

      copyCustAddrSeq(casDest, casOrig);

      dest.setShipToCustAddress(casDest);
    }
  }

  public static void copyCustomer(Customer dest, Customer orig)
  {
    if( (dest == null) || (orig == null) ) return;
    BeanUtil.copySimpleProperties(dest, orig, true);

    Iterator it1 = orig.getCustAddrSeqs().iterator();
    while(it1.hasNext())
    {
      CustAddrSeq casOrig = (CustAddrSeq)it1.next();
      CustAddrSeq casDest = new CustAddrSeq();

      copyCustAddrSeq(casDest, casOrig);

      dest.getCustAddrSeqs().add(casDest);
    }

    it1 = orig.getContactCusts().iterator();
    while(it1.hasNext())
    {
      ContactCust ccOrig = (ContactCust)it1.next();
      ContactCust ccDest = new ContactCust();

      copyContactCust(ccDest, ccOrig);

      dest.getContactCusts().add(ccDest);
    }
  }

  public static void copyCustAddrSeq(CustAddrSeq dest, CustAddrSeq orig)
  {
    if( (dest == null) || (orig == null) ) return;
    BeanUtil.copySimpleProperties(dest, orig, true);

    CustAddrSeqId idOrig = orig.getCustAddrSeqId();
    CustAddrSeqId idDest = new CustAddrSeqId();
    BeanUtil.copySimpleProperties(idDest, idOrig, true);

    Iterator it = orig.getCustAddresses().iterator();
    while(it.hasNext())
    {
      CustAddress caOrig = (CustAddress)it.next();
      CustAddress caDest = new CustAddress();

      copyCustAddress(caDest, caOrig);

      dest.getCustAddresses().add(caDest);
    }

    dest.setCustAddrSeqId(idDest);
  }

  public static void copyCustAddress(CustAddress dest, CustAddress orig)
  {
    if( (dest == null) || (orig == null) ) return;
    BeanUtil.copySimpleProperties(dest, orig, true);
  }

  public static void copyBranch(Branch dest, Branch orig)
  {
    if( (dest == null) || (orig == null) ) return;
    BeanUtil.copySimpleProperties(dest, orig, true);
  }

  public static void copyConsolidatedInvoice(ConsolidatedInvoice dest, ConsolidatedInvoice orig)
  {
    if( (dest == null) || (orig == null) ) return;
    BeanUtil.copySimpleProperties(dest, orig, true);

    ConsolidatedInvoiceId idOrig = orig.getConsolidatedInvoiceId();
    ConsolidatedInvoiceId idDest = new ConsolidatedInvoiceId();
    BeanUtil.copySimpleProperties(idDest, idOrig, true);
    dest.setConsolidatedInvoiceId(idDest);

    Iterator it1 = orig.getJobContractInvoices().iterator();

    while(it1.hasNext())
    {
      JobContractInvoice jciOrig = (JobContractInvoice)it1.next();
      JobContractInvoice jciDest = new JobContractInvoice();

      BeanUtil.copySimpleProperties(jciDest, jciOrig, true);

      dest.getJobContractInvoices().add(jciDest);


      JobContract jcOrig = jciOrig.getJobContract();
      if(jcOrig != null)
      {
        JobContract jcDest = new JobContract();
        BeanUtil.copySimpleProperties(jcDest, jcOrig, true);
        jciDest.setJobContract(jcDest);

        JobOrder joOrig = jcOrig.getJobOrder();
        if(joOrig != null)
        {
          JobOrder joDest = new JobOrder();
          BeanUtil.copySimpleProperties(joDest, joOrig, true);

          jcDest.setJobOrder(joDest);
        }
      }
    }

    Contact ctOrig = orig.getContact();
    if(ctOrig != null)
    {
      Contact ctDest = new Contact();
      copyContact(ctDest, ctOrig);

      dest.setContact(ctDest);
    }

    ContactCust ccOrig = orig.getContactCust();
    if(ccOrig != null)
    {
      ContactCust ccDest = new ContactCust();
      copyContactCust(ccDest, ccOrig);

      dest.setContactCust(ccDest);
    }
  }

  public static void copyJobContractInvoice(JobContractInvoice dest, JobContractInvoice orig)
  {
    if( (dest == null) || (orig == null) ) return;
    BeanUtil.copySimpleProperties(dest, orig, true);

    JobContract jcOrig = orig.getJobContract();
    if(jcOrig != null)
    {
      JobContract jcDest = new JobContract();
      BeanUtil.copySimpleProperties(jcDest, jcOrig, true);
      dest.setJobContract(jcDest);

      JobOrder joOrig = jcOrig.getJobOrder();
      if(joOrig != null)
      {
        JobOrder joDest = new JobOrder();

        BeanUtil.copySimpleProperties(joDest, joOrig, true);
        jcDest.setJobOrder(joDest);
      }
    }

    Iterator it1 = orig.getInvoiceLines().iterator();
    while(it1.hasNext())
    {
      InvoiceLine ilOrig = (InvoiceLine)it1.next();
      InvoiceLine ilDest = new InvoiceLine();

      copyInvoiceLine(ilDest, ilOrig);

      dest.getInvoiceLines().add(ilDest);
    }
  }

  public static void copyInvoiceLine(InvoiceLine dest, InvoiceLine orig)
  {
    if( (dest == null) || (orig == null) ) return;
    BeanUtil.copySimpleProperties(dest, orig, true);

    Iterator it1 = orig.getInvoiceLineSplits().iterator();
    while(it1.hasNext())
    {
      InvoiceLineSplit ilsOrig = (InvoiceLineSplit)it1.next();
      InvoiceLineSplit ilsDest = new InvoiceLineSplit();

      copyInvoiceLineSplit(ilsDest, ilsOrig);

      dest.getInvoiceLineSplits().add(ilsDest);
    }
  }

  public static void copyInvoiceLineSplit(InvoiceLineSplit dest, InvoiceLineSplit orig)
  {
    if( (dest == null) || (orig == null) ) return;
    BeanUtil.copySimpleProperties(dest, orig, true);
  }

  public static void copyTestPrice(TestPrice dest, TestPrice orig)
  {
    if( (dest == null) || (orig == null) ) return;
    BeanUtil.copySimpleProperties(dest, orig, true);

    TestPriceId idOrig = orig.getTestPriceId();
    TestPriceId idDest = new TestPriceId();
    BeanUtil.copySimpleProperties(idDest, idOrig, true);

    dest.setTestPriceId(idDest);
  }


  public static void copySlatePrice(SlatePrice dest, SlatePrice orig)
  {
    if( (dest == null) || (orig == null) ) return;
    BeanUtil.copySimpleProperties(dest, orig, true);

    SlatePriceId idOrig = orig.getSlatePriceId();
    SlatePriceId idDest = new SlatePriceId();
    BeanUtil.copySimpleProperties(idDest, idOrig, true);

    dest.setSlatePriceId(idDest);
  }

  public static void copyReferenceField(ReferenceField dest, ReferenceField orig)
  {
    if( (dest == null) || (orig == null) ) return;
    BeanUtil.copySimpleProperties(dest, orig, true);

    ReferenceFieldId idOrig = orig.getReferenceFieldId();
    ReferenceFieldId idDest = new ReferenceFieldId();
    BeanUtil.copySimpleProperties(idDest, idOrig, true);

    dest.setReferenceFieldId(idDest);
  }

  public static void copyServiceRate(ServiceRate dest, ServiceRate orig)
  {
    if( (dest == null) || (orig == null) ) return;
    BeanUtil.copySimpleProperties(dest, orig, true);

    ServiceRateId idOrig = orig.getServiceRateId();
    ServiceRateId idDest = new ServiceRateId();
    BeanUtil.copySimpleProperties(idDest, idOrig, true);

    dest.setServiceRateId(idDest);
  }

  public static void copyContractExpression(ContractExpression dest, ContractExpression orig)
  {
    if( (dest == null) || (orig == null) ) return;
    BeanUtil.copySimpleProperties(dest, orig, true);

    ContractExpressionId idOrig = orig.getContractExpressionId();
    ContractExpressionId idDest = new ContractExpressionId();
    BeanUtil.copySimpleProperties(idDest, idOrig, true);

    dest.setContractExpressionId(idDest);
  }

  public static void copyRb(RB dest, RB orig)
  {
    if( (dest == null) || (orig == null) ) return;
    BeanUtil.copySimpleProperties(dest, orig, true);

    RBId idOrig = orig.getRbId();
    RBId idDest = new RBId();
    BeanUtil.copySimpleProperties(idDest, idOrig, true);

    dest.setRbId(idDest);
  }

  public static void copyControl(Control dest, Control orig)
  {
    if( (dest == null) || (orig == null) ) return;
    BeanUtil.copySimpleProperties(dest, orig, true);

    ControlId idOrig = orig.getControlId();
    ControlId idDest = new ControlId();
    BeanUtil.copySimpleProperties(idDest, idOrig, true);

    dest.setControlId(idDest);
  }

  public static void copyService(Service dest, Service orig)
  {
    if( (dest == null) || (orig == null) ) return;
    BeanUtil.copySimpleProperties(dest, orig, true);

    ServiceId idOrig = orig.getServiceId();
    ServiceId idDest = new ServiceId();
    BeanUtil.copySimpleProperties(idDest, idOrig, true);

    dest.setServiceId(idDest);
  }

  public static void copyProductGroup(ProductGroup dest, ProductGroup orig)
  {
    if( (dest == null) || (orig == null) ) return;
    BeanUtil.copySimpleProperties(dest, orig, true);

    ProductGroupId idOrig = orig.getProductGroupId();
    ProductGroupId idDest = new ProductGroupId();
    BeanUtil.copySimpleProperties(idDest, idOrig, true);

    dest.setProductGroupId(idDest);
  }

  public static void copyVesselType(VesselType dest, VesselType orig)
  {
    if( (dest == null) || (orig == null) ) return;
    BeanUtil.copySimpleProperties(dest, orig, true);

    VesselTypeId idOrig = orig.getVesselTypeId();
    VesselTypeId idDest = new VesselTypeId();
    BeanUtil.copySimpleProperties(idDest, idOrig, true);

    dest.setVesselTypeId(idDest);
  }

  public static void copyInspectionRate(InspectionRate dest, InspectionRate orig)
  {
    if( (dest == null) || (orig == null) ) return;
    BeanUtil.copySimpleProperties(dest, orig, true);

    InspectionRateId idOrig = orig.getInspectionRateId();
    InspectionRateId idDest = new InspectionRateId();
    BeanUtil.copySimpleProperties(idDest, idOrig, true);

    dest.setInspectionRateId(idDest);
  }

  public static void copyInspectionVersion(InspectionVersion dest, InspectionVersion orig)
  {
    if( (dest == null) || (orig == null) ) return;
    BeanUtil.copySimpleProperties(dest, orig, true);

    InspectionVersionId idOrig = orig.getInspectionVersionId();
    InspectionVersionId idDest = new InspectionVersionId();
    BeanUtil.copySimpleProperties(idDest, idOrig, true);

    dest.setInspectionVersionId(idDest);
  }

  public static void copyCfgContract(CfgContract dest, CfgContract orig)
  {
    if( (dest == null) || (orig == null) ) return;
    BeanUtil.copySimpleProperties(dest, orig, true);

    CfgContractId idOrig = orig.getCfgContractId();
    CfgContractId idDest = new CfgContractId();
    BeanUtil.copySimpleProperties(idDest, idOrig, true);

    dest.setCfgContractId(idDest);
  }
}
