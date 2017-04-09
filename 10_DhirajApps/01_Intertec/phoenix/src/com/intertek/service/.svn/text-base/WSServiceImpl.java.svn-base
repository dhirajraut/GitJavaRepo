package com.intertek.service;

import java.io.StringReader;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.jdom.Document;
import org.jdom.output.XMLOutputter;

import com.intertek.entity.Branch;
import com.intertek.entity.ConsolidatedInvoice;
import com.intertek.entity.Contact;
import com.intertek.entity.ControlSwitch;
import com.intertek.entity.Customer;
import com.intertek.entity.JobContractInvoice;
import com.intertek.entity.JobOrder;
import com.intertek.entity.WebServiceEntity;
import com.intertek.entity.WebServiceEntityInbound;
import com.intertek.exception.ServiceException;
import com.intertek.pagination.Pagination;
import com.intertek.tool.collector.DataCollector;
import com.intertek.util.Constants;
import com.intertek.util.EntityUtil;
import com.intertek.webservice.outbound.WSOutboundContext;
import com.intertek.webservice.outbound.WebServiceSender;

public class WSServiceImpl extends BaseService implements WSService
{
  private static final String CONTROL_SWITCH_TYPE="OUTBOUND_WS";

  public void saveWebServiceEntity(WebServiceEntity entity)
  {
    getDao().save(entity);
  }

  public void saveWebServiceEntityInbound(WebServiceEntityInbound entity) {
     getDao().save(entity);
  }

  public WebServiceEntity getWebServiceEntityById(long id)
  {
    List results = getDao().find(
      "from WebServiceEntity e where e.id = ?",
      new Object[] { id }
    );

    if(results.size() > 0) return (WebServiceEntity)results.get(0);

    return null;
  }

  public WebServiceEntityInbound getWebServiceEntityInboundById(long id)
  {
    List results = getDao().find(
      "from WebServiceEntityInbound e where e.id = ?",
      new Object[] { id }
    );

    if(results.size() > 0) return (WebServiceEntityInbound)results.get(0);

    return null;
  }

  public void sendWebService(
    WSOutboundContext context,
    String name,
    Document doc,
    DataCollector dataCollector,
    WebServiceSender webServiceSender
  )
  {
    if((doc == null) || (webServiceSender == null) || (context == null)) return;
    WebServiceEntity wsEntity = context.getWebServiceEntity();

    try
    {
      //JDOMSource source = new JDOMSource(transformed.getRootElement());
      //JDOMResult result = new JDOMResult();

      //System.out.println("=========== source = " + source);

      StringWriter sw = new StringWriter();
      XMLOutputter xmlOut = new XMLOutputter();
      xmlOut.output(doc.getRootElement(), sw);
      String content = sw.toString();
      StreamSource source = new StreamSource(new StringReader(content));
      if(wsEntity != null) wsEntity.setContent(content);

      StringWriter resultSW = new StringWriter();
      StreamResult result = new StreamResult(resultSW);

      webServiceSender.sendSourceAndReceiveToResult(
        source,
        result
      );

      String resultStr = resultSW.toString();
      //System.out.println("=========== resultStr = " + resultStr);
      if(wsEntity != null) wsEntity.setResult(resultStr);

      //This update should be done in the caller level -- Eric
     // if(dataCollector != null) dataCollector.updateSuccess(name);
    }
    catch(Exception e)
    {
      e.printStackTrace();
      throw new ServiceException(e.getMessage(), e);
    }
  }

  public List getJobContractInvoicesForOutboundInvoiceMessage(Pagination pagination, String[] businessUnits)
  {
    int size = businessUnits != null ? businessUnits.length : 0;
    Object[] params = new Object[size + 2];
    params[0] = Constants.NEW;
    params[size + 1] = Constants.NEW;

    StringBuffer sb = new StringBuffer();

    sb.append("SELECT distinct jci.job_contract_invoice_id FROM JOB_CONTRACT_INVOICE jci ");
    if(size > 0)
    {
      sb.append(", job_contracts jc, JOB_ORDERS job ");
    }

    sb.append("WHERE jci.sent_to_fin_flag = ? ");
    if(size > 0)
    {
      sb.append("AND jc.job_contract_id = jci.job_contract_id ");
      sb.append("AND jc.job_number = job.job_number ");
      sb.append("AND job.bu_name IN ( ");
      for(int i=0; i<businessUnits.length; i++)
      {
        params[i + 1] = businessUnits[i];
        if(i > 0) sb.append(", ");
        sb.append("? ");
      }
      sb.append(") ");
    }

    sb.append("AND NOT EXISTS ( ");
    sb.append("  SELECT jci0.invoice FROM JOB_CONTRACT_INVOICE jci0 ");
    sb.append("  WHERE jci0.sent_to_fin_flag = ? ");
    sb.append("  AND jci0.invoice = jci.invoice_to_adjust ");
    sb.append(") ");
    sb.append("ORDER BY job_contract_invoice_id ");

    String sql = sb.toString();
    System.out.println("sql = " + sql);

    List ids = null;
    if(pagination != null)
    {
      ids = getDao().findBySQL(sql, params, pagination);
    }
    else
    {
      ids = getDao().findBySQL(sql, params);
    }

    List results = new ArrayList();

    if(ids.size() == 0) return results;

    sb = new StringBuffer();
    params = new Object[ids.size()];

    sb.append("select jci from JobContractInvoice jci ");
    sb.append(" left join fetch jci.jobContract jc ");
    sb.append(" left join fetch jc.jobOrder job ");
      //+ " left join fetch jc.prebills prebill left join fetch prebill.prebillSplits"
      //+ " left join fetch jc.customer customer left join fetch customer.custAddrSeqs "
      //+ " left join fetch jc.jobContractInvoices "
    sb.append(" where jci.id in ( ");

    for(int i=0; i<ids.size(); i++)
    {
      Number number = (Number)ids.get(i);
      params[i] = new Long(number.longValue());

      if(i > 0) sb.append(", ");
      sb.append("?");
    }
    sb.append(")");

    String hql = sb.toString();

    List entities = entities = getDao().find(hql, params);
    for(int i=0; i<entities.size(); i++)
    {
      JobContractInvoice orig = (JobContractInvoice)entities.get(i);
      JobContractInvoice dest = new JobContractInvoice();
      EntityUtil.copyJobContractInvoice(dest, orig);

      results.add(dest);
    }

    return results;
  }

  public List getJobContractInvoicesForAribaOutboundInvoiceMessage(long jobContractId)
  {
    if(jobContractId<=0)
      return null;
    StringBuffer sb = new StringBuffer();
    sb.append("select distinct jci from JobContractInvoice jci ");
    sb.append(" left join fetch jci.jobContract jc ");
    sb.append(" left join fetch jci.invoiceLines l ");
    sb.append(" left join fetch jc.jobOrder job ");
    sb.append(" where ");
    sb.append("  jc.id=? and (jci.aribaFlag is null or (jci.aribaFlag is not null and jci.aribaFlag != 'SENT')) " +
        "order by l.lineNumber");
    String hql = sb.toString();
    Object[] params = new Object[] { jobContractId };
    List  entities = getDao().find(hql, params);
    List results = new ArrayList();
    for(int i=0; i<entities.size(); i++)
    {
      JobContractInvoice orig = (JobContractInvoice)entities.get(i);
      JobContractInvoice dest = new JobContractInvoice();
      EntityUtil.copyJobContractInvoice(dest, orig);
      results.add(dest);
    }
    return results;
  }

  public void updateJobContractInvoiceFlag(Long jobContractId, String sentToFinFlag)
  {
    getDao().bulkUpdate(
      "update JobContractInvoice set sentToFinFlag = ? where id = ?",
      new Object[] { sentToFinFlag, jobContractId }
    );
  }

  public List getConsolidatedInvoicesForOutboundInvoiceMessage(Pagination pagination)
  {
     String hql = "select distinct ci from ConsolidatedInvoice ci "
      + " left join fetch ci.contactCust cc "
      + " left join fetch ci.contact"
      + " left join fetch ci.jobContractInvoices jci"
      + " left join fetch jci.jobContract jc"
      + " left join fetch jc.jobOrder"
      + " where ci.sentToFinFlag = ?";

    Object[] params = new Object[] { Constants.NEW };

    List entities = null;
    if(pagination != null)
    {
      entities = getDao().find(hql, params, pagination);
    }
    else
    {
      entities = getDao().find(hql, params);
    }

    List results = new ArrayList();
    for(int i=0; i<entities.size(); i++)
    {
      ConsolidatedInvoice orig = (ConsolidatedInvoice)entities.get(i);
      ConsolidatedInvoice dest = new ConsolidatedInvoice();

      try{
        EntityUtil.copyConsolidatedInvoice(dest, orig);
        results.add(dest);
      }
      catch(Throwable e){
        try{
        System.out.println("failing with ID="+orig.getConsolidatedInvoiceId().getConsolInvoiceNo());
        }
        catch(Throwable t){
        t.printStackTrace();
        }
        e.printStackTrace();
      }
    }

    return results;
  }

  public void updateConsolidatedInvoiceFlag(
  String sentToFinFlag,
    String consolInvoiceNo,
    String custCode,
    String buName
  )
  {
    getDao().bulkUpdate(
      "update ConsolidatedInvoice set sentToFinFlag = ? where consolidatedInvoiceId.consolInvoiceNo = ? and custCode = ? and consolidatedInvoiceId.buName = ?",
      new Object[] { sentToFinFlag, consolInvoiceNo, custCode, buName }
    );
  }

  public List getCustomersForOutboundInvoiceMessage(Pagination pagination)
  {
    String hql = "select c from Customer c where c.newFlag = '" + Constants.NEW + "' or c.updateFlag = '" + Constants.NEW + "'";
      //+ " left join fetch c.custAddrSeqs cas"
      //+ " left join fetch cas.custAddresses"
      //+ " left join fetch c.contactCusts";

    List entities = null;
    if(pagination != null)
    {
      entities = getDao().find(hql, null, pagination);
    }
    else
    {
      entities = getDao().find(hql, null);
    }

    List results = new ArrayList();
    for(int i=0; i<entities.size(); i++)
    {
      Customer orig = (Customer)entities.get(i);
      Customer dest = new Customer();
      EntityUtil.copyCustomer(dest, orig);

      results.add(dest);
    }

    return results;
  }

  public void updateCustomerFlag(String custCode, String newFlag, String updateFlag)
  {
    getDao().bulkUpdate(
      "update Customer set newFlag = ?, updateFlag = ? where custCode = ?",
      new Object[] { newFlag, updateFlag, custCode }
    );
  }

  public List getLimsCustomersForOutboundInvoiceMessage(Pagination pagination)
  {
    return getDao().findByNamedSqlQuery("getCustomersForLims", null, pagination);
  }

  public void updateLimsCustomerFlag(String custCode, String updateLimsFlag)
  {
    getDao().bulkUpdate(
      "update Customer set updateLimsFlag = ? where custCode = ?",
      new Object[] { updateLimsFlag, custCode }
    );
  }

  public List getContactsForOutboundInvoiceMessage(Pagination pagination)
  {
    String hql = "select ct from Contact ct "
    + " where (ct.newFlag = '" + Constants.NEW + "' or ct.updateFlag = '" + Constants.NEW + "')"
    + " and ct.id not in ("
    + "   select cc.contactCustId.contactId from ContactCust cc "
    + "   where cc.customer.newFlag = '" + Constants.NEW + "' "
    + "   or cc.customer.updateFlag = '" + Constants.NEW + "')";

    List entities = null;
    if(pagination != null)
    {
      entities = getDao().find(hql, null, pagination);
    }
    else
    {
      entities = getDao().find(hql, null);
    }

    List results = new ArrayList();
    for(int i=0; i<entities.size(); i++)
    {
      Contact orig = (Contact)entities.get(i);
      Contact dest = new Contact();
      EntityUtil.copyContact(dest, orig);

      results.add(dest);
    }

    return results;
  }

  public void updateContactFlag(Long contactId, String newFlag, String updateFlag)
  {
    getDao().bulkUpdate(
      "update Contact set newFlag = ?, updateFlag = ? where id = ?",
      new Object[] { newFlag, updateFlag, contactId }
    );
  }

  public List getLimsContactsForOutboundInvoiceMessage(Pagination pagination)
  {
    return getDao().findByNamedSqlQuery("getContactsForLims", null, pagination);
  }

  public void updateLimsContactFlag(Long contactId, String updateLimsFlag)
  {
    getDao().bulkUpdate(
      "update Contact set updateLimsFlag = ? where id = ?",
      new Object[] { updateLimsFlag, contactId }
    );
  }

  public List getBranchesForOutboundInvoiceMessage(Pagination pagination)
  {
    String hql = "select b from Branch b where b.newFlag = '" + Constants.NEW + "' or b.updateFlag = '" + Constants.NEW + "'";

    List entities = null;
    if(pagination != null)
    {
      entities = getDao().find(hql, null, pagination);
    }
    else
    {
      entities = getDao().find(hql, null);
    }

    List results = new ArrayList();
    for(int i=0; i<entities.size(); i++)
    {
      Branch orig = (Branch)entities.get(i);
      Branch dest = new Branch();
      EntityUtil.copyBranch(dest, orig);

      results.add(dest);
    }

    return results;
  }

  public void updateBranchFlag(String name, String newFlag, String updateFlag){
    getDao().bulkUpdate(
      "update Branch set newFlag = ?, updateFlag = ? where name = ?",
      new Object[] { newFlag, updateFlag, name }
    );
  }

  public JobOrder getJobOrderForOutboundInvoiceMessage(String jobNumber)
  {
    if(jobNumber == null) return null;

    String hql = "select job from JobOrder job "
      + " left join fetch job.towingCompany "
      + " left join fetch job.shippingAgent"
      + " left join fetch job.serviceLocation sl left join fetch sl.shipToCustAddress "
      + " left join fetch job.branch "
      + " where job.jobNumber = ?";

    Object[] params = new Object[] { jobNumber };

    List jobs = getDao().find(hql, params);

    if(jobs.size() > 0)
    {
      JobOrder orig = (JobOrder)jobs.get(0);
      JobOrder dest = new JobOrder();
      EntityUtil.copyJobOrderWithDetail(dest, orig);

      return dest;
    }

    return null;
  }

  public List getJobIntegrationLog(String jobNumber)
  {
    return getDao().find(
      "from WebServiceEntity e where (e.type = ? or e.type = ?) and e.entityKey = ? order by e.id",
      new Object[] { Constants.SAM, Constants.LIMS, jobNumber }
    );
  }

  public List getDataForOutboundMessageByNamedSql(String namedSql, Pagination pagination)
  {
    return getDao().findByNamedSqlQuery(namedSql, null, pagination);
  }

  public void updateFlag(String hql, Object id)
  {
    getDao().bulkUpdate(
      hql,
      new Object[] { id }
    );
  }

  public List<String> getAttachSysFileNameByJobContractId(long jobContractId){
    return getDao().find(
        "select systemFileName from JobContractAttach a where a.jobContractId = ?",
        new Object[] {jobContractId }
        );
  }

  public void updateJobContractAribaFlag(Long jobContractInvoiceId)
  {
    getDao().bulkUpdate(
      "update JobContractInvoice set aribaFlag = ? where id = ?",
      new Object[] { Constants.SENT, jobContractInvoiceId }
    );
  }

  public List getWebServiceLog(Date startDate, Date endDate){
    return getDao().findByNamedSqlQuery("getWebServiceLog", new Object[]{startDate, endDate});
  }

  public List getWebServiceLog(String key){
    String myKey=(key+"").trim().toUpperCase()+"%";
    return getDao().findByNamedSqlQuery("getWebServiceLogByKey", new Object[]{myKey});
  }

  public List getWebServiceInboundLog(Date startDate, Date endDate){
    return getDao().findByNamedSqlQuery("getWebServiceInboundLog", new Object[]{startDate, endDate});
  }

  public List getWebServiceInboundLog(String key){
    String myKey=(key+"").trim().toUpperCase()+"%";
    return getDao().findByNamedSqlQuery("getWebServiceInboundLogByKey", new Object[]{myKey});
  }

  public List<WebServiceEntity> getWebServiceDetailLog(Date startDate, Date endDate, String type, Boolean status, Pagination pagination, String sortBy, boolean desc){
    if(status!=null){
      return getDao().find("from WebServiceEntity e " +
          "where e.createTime between ? and ? " +
          "and e.type=? " +
          "and (e.status=? or (e.status is null and ?=false)) " +
          "order by e."+sortBy+(desc?" desc":" asc"),
          new Object[]{startDate, endDate, type, status, status}, pagination);
    }
    else{
      return getDao().find("from WebServiceEntity e " +
          "where e.createTime between ? and ? " +
          "and e.type=? " +
          "order by e."+sortBy+(desc?" desc":" asc"),
          new Object[]{startDate, endDate, type}, pagination);
    }
  }

  public List<WebServiceEntity> getWebServiceDetailLog(String key, String type, Boolean status, Pagination pagination, String sortBy, boolean desc){
  String myKey=(key+"").trim().toUpperCase()+"%";
    if(status!=null){
        return getDao().find("from WebServiceEntity e " +
            "where e.entityKey like ?  " +
            "and e.type=? " +
            "and (e.status=? or (e.status is null and ?=false)) " +
            "order by e."+sortBy+(desc?" desc":" asc"),
            new Object[]{myKey, type, status, status}, pagination);
      }
      else{
        return getDao().find("from WebServiceEntity e " +
            "where e.entityKey like ? " +
            "and e.type=? " +
            "order by e."+sortBy+(desc?" desc":" asc"),
            new Object[]{myKey, type}, pagination);
      }
  }

  public List<WebServiceEntity> getWebServiceDetailInboundLog(Date startDate, Date endDate, String type, Boolean status, Pagination pagination, String sortBy, boolean desc){
    if(status!=null){
      return getDao().find("from WebServiceEntityInbound e " +
          "where e.createTime between ? and ? " +
          "and e.type=? " +
          "and (e.status=? or (e.status is null and ?=false)) " +
          "order by e."+sortBy+(desc?" desc":" asc"),
          new Object[]{startDate, endDate, type, status, status}, pagination);
    }
    else{
      return getDao().find("from WebServiceEntityInbound e " +
          "where e.createTime between ? and ? " +
          "and e.type=? " +
          "order by e."+sortBy+(desc?" desc":" asc"),
          new Object[]{startDate, endDate, type}, pagination);
    }
  }

  public List<WebServiceEntity> getWebServiceDetailInboundLog(String key, String type, Boolean status, Pagination pagination, String sortBy, boolean desc){
  String myKey=(key+"").trim().toUpperCase()+"%";
    if(status!=null){
        return getDao().find("from WebServiceEntityInbound e " +
            "where e.entityKey like ? " +
            "and e.type=? " +
            "and (e.status=? or (e.status is null and ?=false)) " +
            "order by e."+sortBy+(desc?" desc":" asc"),
            new Object[]{myKey, type, status, status}, pagination);
      }
      else{
        return getDao().find("from WebServiceEntityInbound e " +
            "where e.entityKey like ? " +
            "and e.type=? " +
            "order by e."+sortBy+(desc?" desc":" asc"),
            new Object[]{myKey, type}, pagination);
      }
  }

  public void updateOutboundWSControlSwitch(String controlSwitchName, String flag){
  getDao().bulkUpdate(
    "update ControlSwitch c set flag=? where c.controlSwitchId.controlSwitchName=? and c.controlSwitchId.controlSwitchType=?",
      new Object[] {flag, controlSwitchName, CONTROL_SWITCH_TYPE}
  );
  }

  public List<ControlSwitch> getOutboundWSControlSwitches(){
    List<ControlSwitch> result=getDao().find(
    "from ControlSwitch c where c.controlSwitchId.controlSwitchType=? order by c.controlSwitchId.controlSwitchName",
    new Object[]{CONTROL_SWITCH_TYPE});
    return result;
  }

  public ControlSwitch getOutboundWSControlSwitch(String controlSwitchName){
    List result=getDao().find(
    "from ControlSwitch c where c.controlSwitchId.controlSwitchName=? and c.controlSwitchId.controlSwitchType=? order by c.controlSwitchId.controlSwitchName",
    new Object[]{controlSwitchName, CONTROL_SWITCH_TYPE});
    if(result!=null && result.size()>0){
      return (ControlSwitch)result.get(0);
    }
    return null;
  }
}
