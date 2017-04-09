package com.intertek.report.dataSource;

import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import net.sf.jasperreports.engine.JRDataSource;

import org.apache.log4j.Logger;

import com.intertek.phoenix.util.ArrayMap;
import com.intertek.report.AbstractJasperDataSource;
import com.intertek.report.ArrayMapDataSource;
import com.intertek.report.ReportUtil;

public class CEInvoiceHeaderDataSource extends AbstractJasperDataSource {
    private static final Logger log = Logger.getLogger(InvoiceHeaderDataSource.class);
    private static String[] fieldNames = {
        "INVOICE",
        "CUST_CODE",
        "CREDIT_IND",
        "INVOICE_TYPE",
        "PYMNT_TERMS_CD",
        "PYMNT_TERMS_DESC",
        "BILLING_CONTACT_NAME",
        "INVOICE_LABEL1",
        "INVOICE_VALUE1",
        "INVOICE_LABEL2",
        "INVOICE_VALUE2",
        "INVOICE_LABEL3",
        "INVOICE_VALUE3",
        "INVOICE_LABEL4",
        "INVOICE_VALUE4",
        "INVOICE_LABEL5",
        "INVOICE_VALUE5",
        "CUST_REF_NUM",
//        "REASON_CODE",
//        "REASON_DESCR",
        "JOB_FINISH_DATE",
        "JOB_NUMBER",
        "NOMINATION_DT",
//        "VESSEL_NAMES",
//        "PRODUCT_NAMES",
        "JOB_DESCRIPTION",
        "SERVICE_LOCATION_CODE",
        "ADDRESS1",
        "ADDRESS2",
        "ADDRESS3",
        "ADDRESS4",
        "CITY",
        "COUNTY",
        "COUNTRY",
        "POSTAL",
        "STATE",
        "CUSTOMERS_NAME",
        "STATE_NAME",
        "COUNTRY_NAME",
       "INVOICE_DATE",
       "ACCOUNTING_DATE",
        "BRANCH_NAME",
        "LOGO_NAME",
        "sellers_country",
        "QUOTE_ID",
        "SHIP_ADDRESS1",
        "SHIP_ADDRESS2",
        "SHIP_ADDRESS3",
        "SHIP_ADDRESS4",
        "SHIP_CITY",
        "SHIP_COUNTY",
        "SHIP_COUNTRY",
        "SHIP_POSTAL",
        "SHIP_STATE",
        "SHIP_CUSTOMERS_NAME",
        "SHIP_STATE_NAME",
        "SHIP_COUNTRY_NAME",
        "VAT_subtotal"
    };
    
    private static String[] fieldTypes = {
        "JOB_FINISH_DATE",  "Timestamp", 
        "NOMINATION_DT",    "Timestamp", 
        "INVOICE_DATE",     "Timestamp", 
        "ACCOUNTING_DATE",  "Timestamp"
    };
    
    public CEInvoiceHeaderDataSource(){
        super.fieldNames = fieldNames;
        super.typeMap = ReportUtil.mapFieldTypes(fieldTypes);
    }
    
    /* (non-Javadoc)
     * @see com.intertek.report.JasperDataSource#getDataSource(java.lang.String, java.util.Map)
     */
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params) {
    	  String invoice =(params.get("InvType")!=null && params.get("InvType").equals("deposit"))?"DepositInvoice":"CEInvoice";
    	  String jobContractId=(params.get("InvType")!=null && params.get("InvType").equals("deposit"))?"ce.jobContractId":"ce.jobOrderContractId"; 
    	  String hql="select"
              + "     ce.invoiceNumber as INVOICE,"
              + "     cu.custCode as CUST_CODE,"
              + "     ce.status as CREDIT_IND,"
              + "     jc.invoiceType as INVOICE_TYPE,"
              + "     jc.paymentTerms as PYMNT_TERMS_CD,"
              + "     jc.paymentTerms  as PYMNT_TERMS_DESC," 
              + "     (cont.firstName||' '||cont.lastName) as BILLING_CONTACT_NAME,"
              + "     jc.invoiceLabel1 as INVOICE_LABEL1,"
              + "     jc.invoiceValue1 as INVOICE_VALUE1,"
              + "     jc.invoiceLabel2 as INVOICE_LABEL2,"
              + "     jc.invoiceValue2 as INVOICE_VALUE2,"
              + "     jc.invoiceLabel3 as INVOICE_LABEL3,"
              + "     jc.invoiceValue3 as invoiceValue3,"
              + "     jc.invoiceLabel4 as INVOICE_LABEL4,"
              + "     jc.invoiceValue4 as invoiceValue4,"
              + "     jc.invoiceLabel5 as INVOICE_LABEL5,"
              + "     jc.invoiceValue5 as INVOICE_VALUE5,"
              + "     jc.custRefNum as CUST_REF_NUM,"
              + "     jo.actualReadyDate as JOB_FINISH_DATE,"   
              + "     jo.jobNumber as JOB_NUMBER,"
              + "     jo.actualReadyDate as NOMINATION_DT,"
          //  + "      as VESSEL_NAMES,"
           // + "      as PRODUCT_NAMES,"
              + "     jo.description as JOB_DESCRIPTION,"
              + "     jo.serviceLocationCode as SERVICE_LOCATION_CODE,"
              + "     cAdd.address1 as ADDRESS1,"
              + "     cAdd.address2 as ADDRESS2,"
              + "     cAdd.address3 as ADDRESS3,"
              + "     cAdd.address4 as ADDRESS4,"
              + "     cAdd.city as CITY,"
              + "     cAdd.county as COUNTY,"
              + "     cAdd.country as COUNTRY,"
              + "     cAdd.postal as POSTAL,"
              + "     cAdd.state as STATE,"
              + "     bcust.name as CUSTOMERS_NAME,"
              + "	  cAdd.state as STATE_NAME,"
              + "	  cAdd.country as COUNTRY_NAME,"
              + "	  ce.invoiceDate as INVOICE_DATE,"
              + "	  ce.accountingDate as ACCOUNTING_DATE,"
              + "     br.name as BRANCH_NAME,"
              + "     br.logoName as LOGO_NAME, "  
              + "    (select co.countryCode from Country co, CEJobContract jc ,CEJobOrder jo," +invoice+ " ce, BusinessUnit bu"
              + "       where jc.jobOrderNumber=jo.jobNumber and jo.buName=bu.name and bu.countryCode=co.countryCode"
              + "       and jc.id ="+jobContractId 
              + "       and  ce.invoiceNumber = ?) as sellers_country, "
              +	"     jo.quoteId as QUOTE_ID," 
              + "     suppAd.address1 as SHIP_ADDRESS1,"
              + "     suppAd.address2 as SHIP_ADDRESS2,"
              + "     suppAd.address3 as SHIP_ADDRESS3,"
              + "     suppAd.address4 as SHIP_ADDRESS4,"
              + "     suppAd.city as SHIP_CITY,"
              + "     suppAd.county as SHIP_COUNTY,"
              + "     suppAd.country as SHIP_COUNTRY,"
              + "     suppAd.postal as SHIP_POSTAL,"
              + "     suppAd.state as SHIP_STATE,"
              + "     scust.name as SHIP_CUSTOMERS_NAME,"
              + "     suppAd.state as SHIP_STATE_NAME,"
              + "     suppAd.country as SHIP_COUNTRY_NAME, "   
              + "    (select sum(ceil.vatAmt) from  CEInvoiceLineItem ceil left join ceil.invoice inv where inv.invoiceNumber=?) as VAT_subtotal"//for deposit invoice report it should not be added
              + "    from "+invoice+ " ce"
             + "     left join ce.jobContract jc"
             + "     left join jc.customer cu left join jc.billingAddress cAdd left join jc.billingContact cont " 
             + "     left join jc.supplierAddress suppAd" 
             + "     left join jc.supplierContact suppCont" 
             + "     left join jc.billingCustomer bcust " 
             + "     left join jc.supplierCustomer scust "
             + "     left join jc.jobOrder jo   left join jo.bu bu left join bu.country c left join jo.branch br"
             + "     where ce.invoiceNumber = ?"  ;  
           
    	  log.debug(hql);
 
    	  
 
 /*SearchService searchService = (SearchService) ServiceLocator.getInstance().getBean("searchService");*/
 ArrayList<Object> paramList = new ArrayList<Object>();
 paramList.add(params.get("Invoice"));
 paramList.add(params.get("Invoice"));
 paramList.add(params.get("Invoice"));
// paramList.add(params.get("job_number"));

 //List<Object[]> result =searchService.searchByHql(hql,CEInvoice.class,paramList);
 List<Object[]> result =ReportUtil.hibernateJasperDataSourceHelperByHql(hql,paramList,typeMap,fieldNames);
 if(result != null){
     return new ArrayMapDataSource(new ArrayMap<String, Object>(fieldNames), result, this, params);
 }
 else
 {
     return null;
 }
 }

    /* (non-Javadoc)
     * @see com.intertek.report.JasperDataSource#getDataSource(java.lang.String, java.util.Map, java.sql.Connection)
     */
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params, Connection con) {
    
        return getDataSource(reportName,params);
    }
    
    @Override
    public JRDataSource getDataSource(String reportName, Map<String, Object> params, String path) {
        JRDataSource ds = super.getDataSource(reportName, params, path);
        
       /* // give it a chance to load some additional data
        loadAdditionalData((ArrayMapDataSource)ds, params, null);
       */ return ds;
    }
    @Override
    public Object calculateFieldValue(String fieldName, 
                    Map<String, Object> params, ArrayMapDataSource source) {
        if(fieldName.equals("TEXT_23")){
            return DataSourceHelper.calculateInvoiceReportTitle(fieldName, params, source);
        }
        if(fieldName.equals("HAS_SHIPTO")){
            Boolean bval=Boolean.valueOf(source.getFieldValue("SHIP_CUSTOMERS_NAME")==null||
                                         source.getFieldValue("SHIP_CUSTOMERS_NAME").toString().isEmpty()?false:true);
            return bval;
        }
        return source.getCalculatedField(fieldName);
    }
    @Override
    public void dataLoaded(ArrayMapDataSource amds, Map<String, Object> params) {
       amds.addCalculatedField("PO_AUTH_TEXT",ReportUtil.getPoAuth(params.get("Invoice").toString()));
       amds.addCalculatedField("DEPOSIT_INVOICE",params.get("InvType")==null?"":"DEPOSITINVOICE");
    }


    
}
